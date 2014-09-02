package com.nicetcm.nibsplus.scheduler.service.impl;

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

import com.nicetcm.nibsplus.orgsend.common.OrgSend.SpringContextXml;
import com.nicetcm.nibsplus.orgsend.service.impl.OrgSendImpl;
import com.nicetcm.nibsplus.scheduler.common.SchduleException;
import com.nicetcm.nibsplus.scheduler.service.ScheduleExecuter;

@Service("DBScheduler")
public class QuartzScheduler implements ScheduleExecuter
{

    @SuppressWarnings("unchecked")
    @Override
    public void startSchedule(ApplicationContext applicationContext) throws SchduleException
    {
        Class<Job> jobClass = null;

        try
        {
            jobClass = (Class<Job>)Class.forName("com.nicetcm.nibsplus.orgsend.service.impl.OrgSendImpl");
        } catch (ClassNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // TODO Auto-generated method stub
        //JobDetailBean jobDetailBean = new JobDetailBean();
        //jobDetailBean.setJobClass(jobClass);
        //jobDetailBean.setApplicationContextJobDataKey("applicationContext");
        //jobDetailBean.setGroup("jobGroup");

        JobDataMap newJobDataMap = new JobDataMap();
        newJobDataMap.put("applicationContext", new ClassPathXmlApplicationContext(new String[]{"classpath:org_send/spring/context-orgsend.xml", "classpath:org_send/spring/context-orgsend-bean.xml"}));
        newJobDataMap.put("queryName", "ARR_EST");

        JobKey jobKeyA = new JobKey("jobA", "group1");

        JobDetail jobA = JobBuilder.newJob(jobClass)
        .withIdentity(jobKeyA)
        .usingJobData(newJobDataMap)
        .build();



        Trigger trigger1 = TriggerBuilder
        .newTrigger()
        .withIdentity("dummyTriggerName1", "group1")
        .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
        .usingJobData("orgCd", "004")
        .usingJobData("transferType", "ONLY_SEND")
        .build();

        Scheduler scheduler;
        try
        {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobA, trigger1);



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

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:scheduler/spring/context-scheduler.xml");
        QuartzScheduler dbScheduler = new QuartzScheduler();
        try
        {
            dbScheduler.startSchedule(applicationContext);
        } catch (SchduleException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
