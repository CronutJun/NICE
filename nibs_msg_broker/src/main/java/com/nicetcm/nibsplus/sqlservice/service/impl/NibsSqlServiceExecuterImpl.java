package com.nicetcm.nibsplus.sqlservice.service.impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.sqlservice.dao.SqlServiceMapper;
import com.nicetcm.nibsplus.sqlservice.service.ScheduleExecuter;

/**
 * @author 박상철
 * @version 1.0
 * @see
 */
@Service("NibsSqlServiceExecuter")
public class NibsSqlServiceExecuterImpl implements ScheduleExecuter {

    private final static Logger logger = LoggerFactory.getLogger(NibsSqlServiceExecuterImpl.class);

    @Autowired
    private SqlServiceMapper schedulerMapper;
    
    private JobDataMap createJobDataMap(String key, LinkedList<String> jobList) {
        JobDataMap newJobDataMap = new JobDataMap();
        newJobDataMap.put("mapper", schedulerMapper);
        newJobDataMap.put("jobKey", key);
        if (jobList != null) newJobDataMap.put("jobList", jobList);

        return newJobDataMap;
    }

    @Override
    public void startSchedule(LinkedHashMap<String, LinkedList<String>> scheduledJob, LinkedHashMap<String, String> scheduledJobExpression) throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        int createCnt = 1;

        //스케쥴리스트 반복
        for(String key : scheduledJob.keySet().toArray(new String[]{})) {
        	LinkedList<String> arrJob = scheduledJob.get(key);
        	
            JobDetail job = JobBuilder.newJob(SqlServiceJob.class)
        		.withIdentity(key, "SqlService")
                .usingJobData(createJobDataMap(key, arrJob)).build();

            Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(key, "SqlService")
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduledJobExpression.get(key))).build();

            scheduler.scheduleJob(job, trigger);

            logger.info("[" + (createCnt++) + "]" + key + " " + scheduledJobExpression.get(key) + " ==> Scheduled");
        }
        
        {
        	String jobKey = "sp_ct_month_use_status_003.sh";
            JobDetail job = JobBuilder.newJob(SpCtMonthUseStatus003Job.class)
        		.withIdentity(jobKey, "SqlService")
                .usingJobData(createJobDataMap(jobKey, null)).build();

            Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobKey, "SqlService")
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduledJobExpression.get(jobKey))).build();

            scheduler.scheduleJob(job, trigger);

            logger.info("[" + (createCnt++) + "]" + jobKey + " " + scheduledJobExpression.get(jobKey) + " ==> Scheduled");
        }
        
        {
        	String jobKey = "sp_cm_password_change.sh";
            JobDetail job = JobBuilder.newJob(SpCmPasswordChangeJob.class)
        		.withIdentity("sp_cm_password_change", "SqlService")
                .usingJobData(createJobDataMap(jobKey, null)).build();

            Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobKey, "SqlService")
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduledJobExpression.get(jobKey))).build();

            scheduler.scheduleJob(job, trigger);

            logger.info("[" + (createCnt++) + "]" + jobKey + " " + scheduledJobExpression.get(jobKey) + " ==> Scheduled");
        }
        
        {
        	String jobKey = "t_ct_error_mng_003_brand_temp.sh";
            JobDetail job = JobBuilder.newJob(TCtErrorMng003BrandTempJob.class)
        		.withIdentity(jobKey, "SqlService")
                .usingJobData(createJobDataMap(jobKey, null)).build();

            Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobKey, "SqlService")
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduledJobExpression.get(jobKey))).build();

            scheduler.scheduleJob(job, trigger);

            logger.info("[" + (createCnt++) + "]" + jobKey + " " + scheduledJobExpression.get(jobKey) + " ==> Scheduled");
        }

        try {
            // TriggerListener orgSendAllTriggerListener = new OrgSendAllTriggerListener(OrgSendAllTriggerListener.class.getSimpleName());
            // scheduler.getListenerManager().addTriggerListener(orgSendAllTriggerListener);

            scheduler.start();

            logger.info("총 실행대상 스케쥴: {}개 start", createCnt-1);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        logger.info("스케쥴 로딩 완료");
    }

}
