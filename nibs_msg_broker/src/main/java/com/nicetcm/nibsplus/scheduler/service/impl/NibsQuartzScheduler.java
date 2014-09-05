package com.nicetcm.nibsplus.scheduler.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.scheduler.common.SchduleException;
import com.nicetcm.nibsplus.scheduler.model.SchedulerVO;
import com.nicetcm.nibsplus.scheduler.service.ScheduleExecuter;
import com.nicetcm.nibsplus.scheduler.service.ScheduleInfoProvider;

/**
 *
 * Nibs Quartz Scheduler
 * <pre>
 * 1. DB에서 스케쥴정보 조회 (자신의 NODE NAME 에 맞는 사용중인것을 조회 ex) T_CM_SCHEDULE.QUARTZ_NODE_NAME = 'OrgSendService' and USE_YN = 'Y')
 * 2. ApplicationContextMap Object에서 Spring Context정보 획득 (없을경우 T_CM_SCHEDULE.SPRING_CONTEXT_XML 정보로 생성후 ApplicationContextMap에 Put)
 * 3. Class 동적 로딩으로 Job 인터페이스를 상속받는 jobClass를 로딩 (T_CM_SCHEDULE.JOB_CLASS)
 * 4. JobDataMap, JobKey, JobDetail, Scheduler 생성
 * 5. Scheduler에 job정보 셋팅 ex) scheduler.scheduleJob(jobA, trigger1);
 * 6. Scheduler 가동 scheduler.start();
 *
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("NibsQuartzScheduler")
public class NibsQuartzScheduler implements ScheduleExecuter
{
    @Resource(name="ScheduleDBInfoProvider")
    private ScheduleInfoProvider scheduleInfoProvider;



    private JobDataMap createJobDataMap(SchedulerVO schedulerVO) {
        JobDataMap newJobDataMap = new JobDataMap();
        newJobDataMap.put("applicationContext", scheduleInfoProvider.getApplicationContext(schedulerVO.getSpringContextXml()));
        newJobDataMap.put("T_ARG1", schedulerVO.gettArg1());
        newJobDataMap.put("T_ARG2", schedulerVO.gettArg2());
        newJobDataMap.put("T_ARG3", schedulerVO.gettArg3());
        newJobDataMap.put("T_ARG4", schedulerVO.gettArg4());

        return newJobDataMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void startSchedule() throws SchduleException
    {

        if(scheduleInfoProvider == null) {
            System.out.println("scheduleInfoProvider is null");
        }
        List<SchedulerVO> scheduleList = scheduleInfoProvider.selectEnableSchedule(System.getProperty("QUARTZ_NODE_NAME") == null ? "OrgSendService" :  System.getProperty("QUARTZ_NODE_NAME"));

        if(scheduleList.size() == 0) {
            System.out.println("실행할 스케쥴이 존재하지 않습니다.");
            return;
        }

        Scheduler scheduler = null;

        try
        {
            scheduler = new StdSchedulerFactory().getScheduler();
        } catch (SchedulerException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        //스케쥴리스트 반복
        for(SchedulerVO schedulerVO : scheduleList) {
            Class<Job> jobClass = null;

            try
            {
                jobClass = (Class<Job>)Class.forName(schedulerVO.getJobClass());

                JobKey jobKey = new JobKey(schedulerVO.getJobName(), schedulerVO.getJobGroup());

                JobDetail jobA = JobBuilder.newJob(jobClass)
                                .withIdentity(jobKey)
                                .usingJobData(createJobDataMap(schedulerVO))
                                .build();

                Trigger trigger = TriggerBuilder
                                .newTrigger()
                                .withIdentity(schedulerVO.getJobName(), schedulerVO.getJobGroup())
                                //.withSchedule(CronScheduleBuilder.cronSchedule(schedulerVO.getCronExpression()))
                                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
                                .build();

                scheduler.scheduleJob(jobA, trigger);

            } catch (ClassNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SchedulerException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try
        {
            scheduler.start();
        } catch (SchedulerException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }








/*
        Map<String, String> jobDataAsMap = new HashMap<String, String>();
        jobDataAsMap.put("queryName", "ARR_EST");
        jobDataAsMap.put("orgCd", "004");
        jobDataAsMap.put("transferType", "ONLY_SEND");
        jobDetailBean.setJobDataAsMap(jobDataAsMap);

        Trigger[] triggers = new Trigger[1];

        CronTriggerBean cronTriggerBean = new CronTriggerBean();
        cronTriggerBean.setJobDetail(jobDetailBean);

        triggers[0] = cronTriggerBean;

        try
        {
            cronTriggerBean.setCronExpression("* * * * * ?");
        } catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try
        {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetailBean, cronTriggerBean);
        } catch (SchedulerException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/


        System.out.println("end");
    }

    private static ApplicationContext addAndGetApplicationContext(ApplicationContext applicationContext, String[] locations) {
        ClassPathXmlApplicationContext newClassPathXmlApplicationContext = new ClassPathXmlApplicationContext(applicationContext);


        if(locations != null && locations.length > 0) {
            newClassPathXmlApplicationContext.setConfigLocations(locations);
        }

        newClassPathXmlApplicationContext.refresh();

        return newClassPathXmlApplicationContext;
    }

    /**
     *
     * Nibs Quartz Scheduler Main
     * <pre>
     * Nibs Quartz Scheduler를 CMD에서 실행
     * 필수 VM인자: -DQUARTZ_NODE_NAME=고유명
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {

        try
        {
            System.out.println("Nibs Quartz Scheduler를 시작 합니다.");

            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:scheduler/spring/context-scheduler.xml");
            NibsQuartzScheduler dbScheduler = applicationContext.getBean("NibsQuartzScheduler", NibsQuartzScheduler.class);
            dbScheduler.startSchedule();
        } catch (SchduleException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            System.out.println("Nibs Quartz Scheduler를 종료 합니다.");
        }
    }

}
