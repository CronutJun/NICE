package com.nicetcm.nibsplus.filemng.main;

import java.util.LinkedHashMap;
import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
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
public class DealNoJob implements org.quartz.Job {
	
	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
        JobDataMap jobDataMap = ctx.getMergedJobDataMap();
        String args = (String)jobDataMap.get("REAL_TIME_COMMAND");
        
		String[] springConfig = {
			"classpath:/filemng/spring/context-filemng.xml",
			"classpath:/filemng/spring/context-filemng-dealNoJob.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

		try {
			// JobParameters jobParameters = new JobParametersBuilder().addString("pid", "10").toJobParameters();
			Map<String, JobParameter> parameters = new LinkedHashMap<String, JobParameter>();
			if (args != null) parameters.put("yyyymmdd", new JobParameter(args));

			JobParameters jobParameters = new JobParameters(parameters);

			JobExecution execution = jobLauncher.run((Job)context.getBean("dealNoJob"), jobParameters);
			
			System.out.println("Exit Status : " + execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");
	}
}