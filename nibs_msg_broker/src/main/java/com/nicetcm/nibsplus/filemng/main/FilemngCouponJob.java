package com.nicetcm.nibsplus.filemng.main;

import java.util.LinkedHashMap;
import java.util.Map;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@DisallowConcurrentExecution
public class FilemngCouponJob {
	
	public FilemngCouponJob(String[] args) {
		String[] springConfig = {
			"classpath:/filemng/spring/context-filemng.xml",
			"classpath:/filemng/spring/context-filemng-couponJob.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");

		try {
			// JobParameters jobParameters = new JobParametersBuilder().addString("pid", "10").toJobParameters();
			Map<String, JobParameter> parameters = new LinkedHashMap<String, JobParameter>();
			if (args.length >= 1) parameters.put("yyyymmdd", new JobParameter(args[0]));
			if (args.length >= 2) parameters.put("branchCd", new JobParameter(args[1]));
			
			JobParameters jobParameters = new JobParameters(parameters);

			JobExecution execution = jobLauncher.run((Job)context.getBean("couponJob"), jobParameters);
			
			System.out.println("Exit Status : " + execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");
	}

	public static void main(String[] args) {
		new FilemngCouponJob(args);
	}
}