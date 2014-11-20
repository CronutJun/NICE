/** 
 * com.nicetcm.nibsplus.filemng.main.ElandFtpRead
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 11. 12.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.filemng.main;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.ArrayUtils;
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
public class ElandReadJob implements org.quartz.Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ApplicationContext applicationContext = (ApplicationContext)context.getMergedJobDataMap().get("applicationContext");
		Properties config = applicationContext.getBean("config", Properties.class);
		
		JobLauncher jobLauncher = (JobLauncher) applicationContext.getBean("jobLauncher");
		File[] files = new File[0];
		files = (File[])ArrayUtils.addAll(files, new File(config.getProperty("sapma.local.path")).listFiles(new ElandFilenameFilter(null)));
		files = (File[])ArrayUtils.addAll(files, new File(config.getProperty("coupon.local.path")).listFiles(new ElandFilenameFilter(null)));
		String filePath, fileName;

		if (files != null) {
			for (File file : files) {
				filePath = file.getAbsolutePath();
				fileName = file.getName().toUpperCase();
	
				try {
					// JobParameters jobParameters = new JobParametersBuilder().addString("pid", "10").toJobParameters();
					Map<String, JobParameter> parameters = new LinkedHashMap<String, JobParameter>();
					// 의미없는 값이지만 파라미터를 중복해서 여러번 실행이 불가능 하다. 그런 이유로 추가함.
					parameters.put("temp", new JobParameter(Calendar.getInstance().getTimeInMillis()));
					parameters.put("eland.file.name", new JobParameter(filePath));
	
					JobParameters jobParameters = new JobParameters(parameters);
					JobExecution execution;
					
					if (fileName.endsWith("MASTER.DAT")) {
						execution = jobLauncher.run((Job) applicationContext.getBean("sapMasterJob"), jobParameters);
					} else if (fileName.endsWith("DETAIL.DAT")) {
						execution = jobLauncher.run((Job) applicationContext.getBean("sapDetailJob"), jobParameters);
					} else if (fileName.endsWith("COUPON.DAT")) {
						execution = jobLauncher.run((Job) applicationContext.getBean("couponJob"), jobParameters);
					} else {
						throw new Exception("Do not execution. file name is '" + fileName + "'");
					}
	
					System.out.println("Exit Status : " + execution.getStatus());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			System.out.println("Done");
		} else {
			System.out.println("No data.");
		}
	}
	
	class ElandFilenameFilter implements FilenameFilter {
		private String date = null;
		
		public ElandFilenameFilter(String date) {
			this.date = date;
		}
		@Override
		public boolean accept(File dir, String name) {
			return name.toUpperCase().startsWith("NICE_" + (date != null ? date : NibsBatchUtil.SysDate())) && name.toUpperCase().endsWith(".DAT");
		}
	}
}