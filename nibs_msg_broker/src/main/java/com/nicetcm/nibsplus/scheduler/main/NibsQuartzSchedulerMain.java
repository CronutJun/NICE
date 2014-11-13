package com.nicetcm.nibsplus.scheduler.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicetcm.nibsplus.scheduler.common.SchduleException;
import com.nicetcm.nibsplus.scheduler.service.ScheduleExecuter;

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
public class NibsQuartzSchedulerMain {
    private final static Logger logger = LoggerFactory.getLogger(NibsQuartzSchedulerMain.class);

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
        try {
            logger.info("Nibs Quartz Scheduler를 시작 합니다.");

            String[] springConfig  =
                {   "classpath:/scheduler/spring/context-scheduler.xml",
                    "classpath:/scheduler/spring/context-scheduler-rmi.xml"
                };
            
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext(springConfig);

            ScheduleExecuter scheduleExecuter = applicationContext.getBean("NibsQuartzScheduler", ScheduleExecuter.class);
            scheduleExecuter.startSchedule();

            logger.info("Nibs Quartz Scheduler를 [정상적] 으로 실행 되었습니다.");
        } catch (SchduleException e) {
            //e.printStackTrace();
            logger.error(e.getMessage());
            logger.error("Nibs Quartz Scheduler를 [비정상적] 으로 종료 되었습니다.");
            System.exit(-1);
        }
    }

}
