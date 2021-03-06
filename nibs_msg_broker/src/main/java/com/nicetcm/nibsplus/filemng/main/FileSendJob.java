/** 
 * com.nicetcm.nibsplus.filesend.main.FileSendMain
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 10. 10.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.filemng.main;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.nicetcm.nibsplus.filemng.service.FileSendService;
import com.nicetcm.nibsplus.scheduler.common.AbstractJob;

@DisallowConcurrentExecution
public class FileSendJob extends AbstractJob  {

	private Logger logger = Logger.getLogger(this.getClass());
	private Logger errorLogger = Logger.getLogger("FilemngERROR");
	
	@Override
	public void execte(JobExecutionContext context)  {
		ApplicationContext applicationContext = (ApplicationContext)context.getMergedJobDataMap().get("applicationContext");
        String args = (String)context.getMergedJobDataMap().get("REAL_TIME_COMMAND");

	    FileSendService fileSendService = (FileSendService) applicationContext.getBean("fileSendService");

		logger.info(this.getClass().getName() + " execute...");
		
	    try {
	    	if (args == null) {
	    		fileSendService.execute();
	    	} else {
	    		fileSendService.execute(args);
	    	}
	    } catch (Exception e) {
        	errorLogger.error(e.getMessage(), e.getCause());
	    }

		logger.info(this.getClass().getName() + " Done");
	}

}
