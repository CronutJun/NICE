package com.nicetcm.nibsplus.sqlservice.main;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicetcm.nibsplus.sqlservice.service.ScheduleExecuter;

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
public class NibsSqlServiceMain {
    private final static Logger logger = LoggerFactory.getLogger(NibsSqlServiceMain.class);

    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        try {
            logger.info("Nibs SqlService Scheduler를 시작 합니다.");

            String[] springConfig = {
        		"classpath:/sqlservice/spring/context-sqlservice.xml"
            };
            
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext(springConfig);
            
            LinkedHashMap<String, LinkedList<String>> scheduledJob = (LinkedHashMap<String, LinkedList<String>>)applicationContext.getBean("scheduledJob", LinkedHashMap.class);
            LinkedHashMap<String, String> scheduledJobExpression = (LinkedHashMap<String, String>)applicationContext.getBean("scheduledJobExpression", LinkedHashMap.class);
            
            ScheduleExecuter scheduleExecuter = applicationContext.getBean("NibsSqlServiceExecuter", ScheduleExecuter.class);
            scheduleExecuter.startSchedule(scheduledJob, scheduledJobExpression);

            logger.info("Nibs SqlService Scheduler를 [정상적] 으로 실행 되었습니다.");
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error(e.getMessage());
            logger.error("Nibs SqlService Scheduler를 [비정상적] 으로 종료 되었습니다.");
            System.exit(-1);
        }
    }

}
