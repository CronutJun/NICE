/** 
 * com.nicetcm.nibsplus.filemng.main.ElandFtpRead
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 11. 12.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.filemng.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class ElandFtpReadJob implements Job {
	
	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		String today = fmt.format(Calendar.getInstance().getTime());
		
		new FilemngSapMaJob(new String[]{today});
		new FilemngSapDeJob(new String[]{today});
		new FilemngCouponJob(new String[]{today});
	}
}
