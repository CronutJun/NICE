package com.nicetcm.nibsplus.filemng.main;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;

@DisallowConcurrentExecution
public class CasherFilemngJob implements org.quartz.Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ApplicationContext applicationContext = (ApplicationContext)context.getMergedJobDataMap().get("applicationContext");
		Properties config = applicationContext.getBean("config", Properties.class);

		JobLauncher jobLauncher = (JobLauncher) applicationContext.getBean("jobLauncher");
		// ex) D:/pjt/old_nibs/nibsif/data_sample/casher_file/CK_09260000_001_Z023.txt
		File[] files = new File(config.getProperty("casher.local.path")).listFiles();
		String filePath, fileName;

		if (files != null) {
			for (File file : files) {
				filePath = file.getAbsolutePath();
				fileName = file.getName();
	
				try {
					// JobParameters jobParameters = new JobParametersBuilder().addString("pid", "10").toJobParameters();
					Map<String, JobParameter> parameters = new LinkedHashMap<String, JobParameter>();
					parameters.put("casher.file.name", new JobParameter(filePath));
	
					JobParameters jobParameters = new JobParameters(parameters);
					JobExecution execution;
	
					if (fileName.startsWith("CK_")) {
						execution = jobLauncher.run((Job) applicationContext.getBean("casherCKJob"), jobParameters);
					} else if (fileName.startsWith("TK_")) {
						execution = jobLauncher.run((Job) applicationContext.getBean("casherTKJob"), jobParameters);
					} else if (fileName.startsWith("TR_")) {
						execution = jobLauncher.run((Job) applicationContext.getBean("casherTRJob"), jobParameters);
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
}