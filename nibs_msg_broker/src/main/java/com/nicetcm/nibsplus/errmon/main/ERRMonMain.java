/** 
 * com.nicetcm.nibsplus.errmon.main.ERRMonMain
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 10. 7.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.errmon.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicetcm.nibsplus.errmon.service.ERRMonService;

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
public class ERRMonMain {
	
	public static void main(String[] args) throws InterruptedException {
	    String[] springConfig  =
	        {   "classpath:/filemng/spring/context-filemng.xml",
	            "classpath:/filemng/spring/context-filemng-casherJob.xml"
	        };

	    ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
	    ERRMonService errMonService = (ERRMonService)context.getBean("errMonService");

		while (true) {
			errMonService.execute();
			
			Thread.sleep(60 * 1000); // 60초
		}
	}

}
