package com.nicetcm.nibsplus.scheduler.main;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.quartz.Calendar;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicetcm.nibsplus.orgsend.common.OrgSend;
import com.nicetcm.nibsplus.scheduler.model.JobVO;
import com.nicetcm.nibsplus.scheduler.model.SchedulerVO;
import com.nicetcm.nibsplus.scheduler.service.ScheduleInfoProvider;
import com.nicetcm.nibsplus.scheduler.service.impl.NibsQuartzScheduler;

/**
 * 여기에 클래스(한글)명.
 * <pre>
 * 여기에 클래스 설명 및 변경 이력을 기술하십시오.
 * </pre>
 *
 * @author 박상철
 * @version 1.0
 * @see
 */
public class NibsScheduleExecuter {
    private final static Logger logger = LoggerFactory.getLogger(NibsScheduleExecuter.class);
    
	public NibsScheduleExecuter(String[] args) {
        try {
        	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/scheduler/spring/context-scheduler.xml");
            ScheduleInfoProvider scheduleInfoProvider = applicationContext.getBean("ScheduleDBInfoProvider", ScheduleInfoProvider.class);

            if (args[0].equals("-L")) {
            	ApplicationContext orgSendBeanContext = new ClassPathXmlApplicationContext("classpath:/org_send/spring/context-orgsend-bean.xml");
                Map<String, String> mtype = ((OrgSend)orgSendBeanContext.getBean("orgSend", OrgSend.class)).getOrgSendMtype();
                String type;
                
            	List<SchedulerVO> scheduleList = scheduleInfoProvider.selectScheduleJobGroup();
            	int count = 1;
            	
            	System.out.println(StringUtils.leftPad("", 65, "-"));
            	System.out.println(String.format("%3s\t%-20s%-25s%s", "NO", "QuartzNodeName", "JobGroup", "MessageCode"));
            	System.out.println(StringUtils.leftPad("", 65, "-"));
            	
            	for (SchedulerVO jobGroup : scheduleList) {
            		System.out.println(String.format("%3d\t%-20s%-25s%s", count++, jobGroup.getQuartzNodeName(), jobGroup.getJobGroup(), (type = mtype.get(jobGroup.getJobGroup()))) == null ? "-" : type);
            	}

            	System.out.println(StringUtils.leftPad("", 65, "-"));
            } else {
	            JobVO jobVO = new JobVO();
	            jobVO.setQuartzNodeName(args[0]); // "OrgSend"
	            jobVO.setJobGroup(args[1]); // "ADD_CASH"
	            jobVO.setJobName(args[2]); // "003"
	            
	            if (args.length == 4) {
	            	jobVO.setParam(args[3]); // "AUTO", "ONLY"...
	            }
	
	            NibsScheduleExecuter.executeJob(scheduleInfoProvider.selectScheduleByPk(jobVO));
	
            }
        } catch (Exception e) {
        	logger.error("Nibs Quartz Scheduler를 [비정상적] 으로 종료 되었습니다.\n" + e.getMessage());
        	e.printStackTrace();
        }
    	
    }
    
    @SuppressWarnings("unchecked")
	public static void executeJob(final SchedulerVO schedulerVO) throws ClassNotFoundException, JobExecutionException, InstantiationException, IllegalAccessException {
        if(schedulerVO == null) {
        	logger.info("해당하는 스케쥴이 존재하지 않습니다.");
        } else {
            Class<Job> jobClass = (Class<Job>)Class.forName(schedulerVO.getJobClass());
            jobClass.newInstance().execute(new JobExecutionContext() {
				@Override
				public JobDataMap getMergedJobDataMap() {
					return NibsQuartzScheduler.createJobDataMap2(schedulerVO);
				}
				@Override
				public void setResult(Object result) {}
				@Override
				public void put(Object key, Object value) {}
				@Override
				public boolean isRecovering() { return false; }
				@Override
				public Trigger getTrigger() { return null; }
				@Override
				public Scheduler getScheduler() { return null; }
				@Override
				public Date getScheduledFireTime() { return null; }
				@Override
				public Object getResult() { return null; }
				@Override
				public int getRefireCount() { return 0; }
				@Override
				public TriggerKey getRecoveringTriggerKey() throws IllegalStateException { return null; }
				@Override
				public Date getPreviousFireTime() { return null; }
				@Override
				public Date getNextFireTime() { return null; }
				@Override
				public long getJobRunTime() { return 0; }
				@Override
				public Job getJobInstance() { return null; }
				@Override
				public JobDetail getJobDetail() { return null; }
				@Override
				public Date getFireTime() { return null; }
				@Override
				public String getFireInstanceId() { return null; }
				@Override
				public Calendar getCalendar() { return null; }
				@Override
				public Object get(Object key) { return null; }
			});

            logger.info("정상적으로 실행 완료 되었습니다.");
        }
    }

	public static void main(String[] args) {
		new NibsScheduleExecuter(args);

        System.exit(-1);
    }
}
