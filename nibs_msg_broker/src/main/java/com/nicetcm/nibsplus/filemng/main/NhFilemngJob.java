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
import org.springframework.context.support.ClassPathXmlApplicationContext;

@DisallowConcurrentExecution
public class NhFilemngJob implements org.quartz.Job {
	
	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		String[] springConfig = {
			"classpath:/filemng/spring/context-filemng.xml",
			"classpath:/filemng/spring/context-filemng-nhJob.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
		Properties config = context.getBean("config", Properties.class);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		// ex) /Project_NIBS/FTP_RECEIVE/nh/R20140924B11.txt
		File[] files = new File(config.getProperty("nh.local.path")).listFiles();

		if (files != null) {
			for (File file : files) {
				try {
					// JobParameters jobParameters = new JobParametersBuilder().addString("pid", "10").toJobParameters();
					Map<String, JobParameter> parameters = new LinkedHashMap<String, JobParameter>();
					parameters.put("nh.file.name", new JobParameter(file.getAbsolutePath()));
	
					JobParameters jobParameters = new JobParameters(parameters);
					JobExecution execution = jobLauncher.run((Job) context.getBean("nhJob"), jobParameters);
					
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