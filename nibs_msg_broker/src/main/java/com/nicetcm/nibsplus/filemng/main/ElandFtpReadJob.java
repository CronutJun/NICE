/** 
 * com.nicetcm.nibsplus.filemng.main.ElandFtpRead
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 11. 12.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.filemng.main;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;

import com.nicetcm.nibsplus.util.NibsBatchUtil;

@DisallowConcurrentExecution
public class ElandFtpReadJob implements org.quartz.Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ApplicationContext applicationContext = (ApplicationContext)context.getMergedJobDataMap().get("applicationContext");
		JobLauncher jobLauncher = (JobLauncher) applicationContext.getBean("jobLauncher");

		try {
			// JobParameters jobParameters = new JobParametersBuilder().addString("pid", "10").toJobParameters();
			Map<String, JobParameter> parameters = new LinkedHashMap<String, JobParameter>();
			// 의미없는 값이지만 파라미터를 중복해서 여러번 실행이 불가능 하다. 그런 이유로 추가함.
			parameters.put("temp", new JobParameter(Calendar.getInstance().getTimeInMillis()));
			parameters.put("yyyymmdd", new JobParameter(NibsBatchUtil.SysDate()));
			// if (args.length >= 2) parameters.put("branchCd", new JobParameter(args[1]));

			JobParameters jobParameters = new JobParameters(parameters);

			// sapma, sapde, coupon 순차 처리
			JobExecution execution = jobLauncher.run((Job)applicationContext.getBean("elandFtpReadJob"), jobParameters);
			
			System.out.println("Exit Status : " + execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");
	}
}
