/** 
 * com.nicetcm.nibsplus.filesend.main.FileSendMain
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 10. 10.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.filemng.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicetcm.nibsplus.filemng.service.FileSendService;


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
public class FileSendMain {
	
	public static void main(String[] args) {

	    String springConfig  = "classpath:/filemng/spring/context-filesend.xml";

	    ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

	    FileSendService fileSendService = (FileSendService) context.getBean("fileSendService");

	    try {

	    	fileSendService.execute(0);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    System.out.println("Done");

	}

}
