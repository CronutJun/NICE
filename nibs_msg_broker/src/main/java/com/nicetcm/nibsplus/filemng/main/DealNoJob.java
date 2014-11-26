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
public class DealNoJob implements org.quartz.Job {
	
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

			JobParameters jobParameters = new JobParameters(parameters);

			JobExecution execution = jobLauncher.run((Job)applicationContext.getBean("dealNoJob"), jobParameters);
			
			System.out.println("Exit Status : " + execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");
	}
}