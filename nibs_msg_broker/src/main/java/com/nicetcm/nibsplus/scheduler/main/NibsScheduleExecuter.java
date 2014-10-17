package com.nicetcm.nibsplus.scheduler.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicetcm.nibsplus.scheduler.model.JobVO;
import com.nicetcm.nibsplus.scheduler.model.SchedulerVO;
import com.nicetcm.nibsplus.scheduler.service.JobExecuter;
import com.nicetcm.nibsplus.scheduler.service.ScheduleInfoProvider;

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

    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        try {
        	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/scheduler/spring/context-scheduler.xml");

            JobVO jobVO = new JobVO();
            jobVO.setQuartzNodeName(args[0]); // "OrgSendService");
            jobVO.setJobGroup(args[1]); // "ADD_CASH");
            jobVO.setJobName(args[2]); // "003");
            
            ScheduleInfoProvider scheduleInfoProvider = applicationContext.getBean("ScheduleDBInfoProvider", ScheduleInfoProvider.class);
            SchedulerVO schedulerVO = scheduleInfoProvider.selectScheduleByPk(jobVO);

            if(schedulerVO == null) {
            	logger.info("해당하는 스케쥴이 존재하지 않습니다.");
            } else {
                ApplicationContext jobContext = scheduleInfoProvider.getApplicationContext(schedulerVO.getSpringContextXml());
                
                Class<JobExecuter> jobClass = null;
                jobClass = (Class<JobExecuter>)Class.forName(schedulerVO.getJobClass());

                JobExecuter jobExecuter = jobClass.newInstance();
                jobExecuter.executeJob(jobContext, schedulerVO);

                logger.info("정상적으로 실행 완료 되었습니다.");
            }
        } catch (Exception e) {
        	logger.error("Nibs Quartz Scheduler를 [비정상적] 으로 종료 되었습니다.\n" + e.getMessage());
        	e.printStackTrace();
        }

        System.exit(-1);
    }
}
