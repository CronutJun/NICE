/** 
 * com.nicetcm.nibsplus.filemng.main.NibsFilemngMain
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 11. 12.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.filemng.main;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FilemngTest {
	public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        /*{
        	String jobKey = "CasherFilemngJob";
        	JobDataMap jobData = new JobDataMap();
        	jobData.put("applicationContext", new ClassPathXmlApplicationContext("classpath:filemng/spring/context-filemng.xml", "classpath:filemng/spring/context-filemng-casherJob.xml"));
            JobDetail job = JobBuilder.newJob(CasherFilemngJob.class).withIdentity(jobKey, "SqlService").usingJobData(jobData).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobKey, "SqlService").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            scheduler.scheduleJob(job, trigger);
        }

        {
        	String jobKey = "ElandReadJob";
        	JobDataMap jobData = new JobDataMap();
        	jobData.put("applicationContext", new ClassPathXmlApplicationContext("classpath:filemng/spring/context-filemng.xml", "classpath:filemng/spring/context-filemng-elandReadJob.xml"));
            JobDetail job = JobBuilder.newJob(ElandReadJob.class).withIdentity(jobKey, "FilemngService").usingJobData(jobData).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobKey, "FilemngService").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            scheduler.scheduleJob(job, trigger);
        }*/
        
        /*{
        	String jobKey = "ERRMonJob";
        	JobDataMap jobData = new JobDataMap();
        	jobData.put("applicationContext", new ClassPathXmlApplicationContext("classpath:filemng/spring/context-filemng.xml"));
            JobDetail job = JobBuilder.newJob(ERRMonJob.class).withIdentity(jobKey, "FilemngService").usingJobData(jobData).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobKey, "FilemngService").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            scheduler.scheduleJob(job, trigger);
        }*/
        
        {
        	String jobKey = "FileSendJob";
        	JobDataMap jobData = new JobDataMap();
        	jobData.put("applicationContext", new ClassPathXmlApplicationContext("classpath:filemng/spring/context-filemng.xml"));
            JobDetail job = JobBuilder.newJob(FileSendJob.class).withIdentity(jobKey, "SqlService").usingJobData(jobData).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobKey, "FilemngService").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            scheduler.scheduleJob(job, trigger);
        }

        /*{
        	String jobKey = "VpnIpReadJob";
        	JobDataMap jobData = new JobDataMap();
        	jobData.put("applicationContext", new ClassPathXmlApplicationContext("classpath:filemng/spring/context-filemng.xml", "classpath:filemng/spring/context-filemng-vpnJob.xml"));
            JobDetail job = JobBuilder.newJob(VpnIpReadJob.class).withIdentity(jobKey, "SqlService").usingJobData(jobData).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobKey, "FilemngService").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            scheduler.scheduleJob(job, trigger);
        }*/

        /*{
        	String jobKey = "NhFilemngJob";
        	JobDataMap jobData = new JobDataMap();
        	jobData.put("applicationContext", new ClassPathXmlApplicationContext("classpath:filemng/spring/context-filemng.xml", "classpath:filemng/spring/context-filemng-nhJob.xml"));
            JobDetail job = JobBuilder.newJob(NhFilemngJob.class).withIdentity(jobKey, "SqlService").usingJobData(jobData).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobKey, "FilemngService").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            scheduler.scheduleJob(job, trigger);
        }*/

        /*{
        	String jobKey = "FileSendJob2";
            JobDataMap jobData = new JobDataMap();
        	jobData.put("applicationContext", new ClassPathXmlApplicationContext("classpath:filemng/spring/context-filesend.xml"));
            jobData.put("REAL_TIME_COMMAND", "999999990GV");
            JobDetail job = JobBuilder.newJob(FileSendJob.class).withIdentity(jobKey, "SqlService").usingJobData(jobData).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobKey, "FilemngService").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            scheduler.scheduleJob(job, trigger);
        }*/
        
        scheduler.start();
	}
}
