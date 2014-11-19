/** 
 * com.nicetcm.nibsplus.filesend.main.FileSendMain
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 10. 10.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.filemng.main;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.nicetcm.nibsplus.filemng.service.FileSendService;

@DisallowConcurrentExecution
public class FileSendJob implements Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ApplicationContext applicationContext = (ApplicationContext)context.getMergedJobDataMap().get("applicationContext");
        String args = (String)context.getMergedJobDataMap().get("REAL_TIME_COMMAND");

	    FileSendService fileSendService = (FileSendService) applicationContext.getBean("fileSendService");

	    try {
	    	fileSendService.execute(args == null ? new String[]{} : new String[]{args});
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    System.out.println("Done");
	}

}
