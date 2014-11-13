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
import org.quartz.TriggerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.scheduler.common.SchduleException;
import com.nicetcm.nibsplus.scheduler.constant.ExceptionType;
import com.nicetcm.nibsplus.scheduler.listener.OrgSendAllTriggerListener;
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

    private final static Logger logger = LoggerFactory.getLogger(NibsQuartzScheduler.class);

    @Resource(name="ScheduleDBInfoProvider")
    private ScheduleInfoProvider scheduleInfoProvider;

    private JobDataMap createJobDataMap(SchedulerVO schedulerVO) {
        JobDataMap newJobDataMap = new JobDataMap();
        if (!"EMPTY".equals(schedulerVO.getSpringContextXml().toUpperCase())) newJobDataMap.put("applicationContext", scheduleInfoProvider.getApplicationContext(schedulerVO.getSpringContextXml()));
        newJobDataMap.put("SchedulerVO", schedulerVO);
        newJobDataMap.put("REAL_TIME_COMMAND", schedulerVO.getRealTimeCommand());
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
        List<SchedulerVO> scheduleList = scheduleInfoProvider.selectEnableSchedule(System.getProperty("QUARTZ_NODE_NAME") == null ? "OrgSendService" :  System.getProperty("QUARTZ_NODE_NAME"));

        if(scheduleList.size() == 0) {
            logger.info("실행할 스케쥴이 존재하지 않습니다.");
            return;
        } else {
            logger.info("총 실행대상 스케쥴은 {}개 입니다.", scheduleList.size());
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

        int createCnt = 1;

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
                                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                                .build();

                scheduler.scheduleJob(jobA, trigger);

                logger.info("[" + createCnt + "]" + schedulerVO.toPrettyString() + " ==> Fired");
                createCnt++;

                //if(createCnt == 2) {
                //    break;
                //}
            } catch (Exception e) {

                e.printStackTrace();
                logger.info(schedulerVO.toPrettyString() + " ==> MisFired !!!!!!!!!!!!!!!!!!!!!!!!");
                throw new SchduleException(ExceptionType.VM_STOP, "MisFired Schedule");
            }
            
            break;
        }//end for

        try
        {

            TriggerListener orgSendAllTriggerListener = new OrgSendAllTriggerListener(OrgSendAllTriggerListener.class.getSimpleName());
            scheduler.getListenerManager().addTriggerListener(orgSendAllTriggerListener);

            scheduler.start();

            logger.info("총 실행대상 스케쥴: {}개중 {}개 start", scheduleList.size(), createCnt-1);

        } catch (SchedulerException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        logger.info("스케쥴 로딩 완료");
    }

}
