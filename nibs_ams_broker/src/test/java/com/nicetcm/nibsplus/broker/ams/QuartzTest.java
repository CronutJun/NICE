package com.nicetcm.nibsplus.broker.ams;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class QuartzTest {
    
    private static final Logger logger = LoggerFactory.getLogger(QuartzTest.class);
    
    private SchedulerFactory schedFact;
    private Scheduler sched;
    
    public QuartzTest() {
        try {
            // 스케쥴 생성후 시작
            schedFact = new StdSchedulerFactory();
            sched = schedFact.getScheduler();
            sched.start();
            
            // Job1 생성 (Parameter : 1.Job Name, 2.Job Group Name, 3.Job Class)
            JobDetail job1 = newJob(TestJob1.class)
                           .withIdentity("job1",  "group1")
                           .build();// Trigger1 생성 (Parameter : 1.Trigger Name, 2.Trigger Group Name, 3.Cron Expression)
            CronTrigger trigger1 = newTrigger()
                                 .withIdentity("trigger1", "group1")
                                 .withSchedule(cronSchedule("0 * * * * ?"))
                                 .build();
            sched.scheduleJob(job1, trigger1);
             
            // Job2 생성후 등록   
            JobDetail job2 = newJob(TestJob2.class)
                        .withIdentity("job2",  "group2")
                        .build();// Trigger1 생성 (Parameter : 1.Trigger Name, 2.Trigger Group Name, 3.Cron Expression)
            CronTrigger trigger2 = newTrigger()
                          .withIdentity("trigger2", "group2")
                          .withSchedule(cronSchedule("30 * * * * ?"))
                          .build();
            sched.scheduleJob(job2, trigger2);
            //JobDetail job2 = new JobDetail("job2", "group2", TestJob2.class);
            //CronTrigger trigger2 = new CronTrigger("trigger2", "group2", "30 * * * * ?");
            //sched.scheduleJob(job2, trigger2); // Job2 삭제 (30초 후)<br>   // Thread.sleep(30000);<br>   // sched.deleteJob("job2", "group2");<br><br><br>  <br><br>  } catch (SchedulerException e) {
            //e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) {
        new QuartzTest();
    }
}
