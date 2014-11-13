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

public class FilemngTest {
	public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        {
        	String jobKey = "ElandFtpReadJob";
            JobDetail job = JobBuilder.newJob(ElandFtpReadJob.class).withIdentity(jobKey, "SqlService").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobKey, "SqlService").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            scheduler.scheduleJob(job, trigger);
        }

        {
        	String jobKey = "FileSendJob";
            JobDetail job = JobBuilder.newJob(FileSendJob.class).withIdentity(jobKey, "SqlService").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobKey, "SqlService").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            scheduler.scheduleJob(job, trigger);
        }

        {
        	String jobKey = "VpnIpReadJob";
            JobDetail job = JobBuilder.newJob(VpnIpReadJob.class).withIdentity(jobKey, "SqlService").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobKey, "SqlService").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            scheduler.scheduleJob(job, trigger);
        }

        {
        	String jobKey = "NhFilemngJob";
            JobDetail job = JobBuilder.newJob(NhFilemngJob.class).withIdentity(jobKey, "SqlService").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobKey, "SqlService").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            scheduler.scheduleJob(job, trigger);
        }

        {
        	String jobKey = "CasherFilemngJob";
            JobDetail job = JobBuilder.newJob(CasherFilemngJob.class).withIdentity(jobKey, "SqlService").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobKey, "SqlService").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            scheduler.scheduleJob(job, trigger);
        }

        {
        	String jobKey = "FileSendJob2";
            JobDataMap newJobDataMap = new JobDataMap();
            newJobDataMap.put("REAL_TIME_COMMAND", "999999990GV");
            JobDetail job = JobBuilder.newJob(FileSendJob.class).withIdentity(jobKey, "SqlService").usingJobData(newJobDataMap).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobKey, "SqlService").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
            scheduler.scheduleJob(job, trigger);
        }
        
        scheduler.start();
	}
}
