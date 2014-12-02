package com.nicetcm.nibsplus.filemng.main;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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

import com.nicetcm.nibsplus.filemng.model.TransferVO;
import com.nicetcm.nibsplus.filemng.service.impl.SFtpTransfer;

@DisallowConcurrentExecution
public class ShinhanFilemngJob implements org.quartz.Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ApplicationContext applicationContext = (ApplicationContext)context.getMergedJobDataMap().get("applicationContext");
		Properties config = applicationContext.getBean("config", Properties.class);

        TransferVO transferVO = new TransferVO();
        transferVO.setHost(config.getProperty("host.host"));
        transferVO.setAvailableServerPort(Integer.parseInt(config.getProperty("host.availableServerPort")));
        transferVO.setUserId(config.getProperty("host.userid"));
        transferVO.setPassword(config.getProperty("host.password"));
        transferVO.setRemotePath(config.getProperty("shinhan.remote.path"));
        transferVO.setLocalPath(config.getProperty("shinhan.local.path"));

        try {
        	
        	//NibsBatchUtil.SysDate("yyyyMM") + "_21_0510"
			List<String> files = SFtpTransfer.getFileNames(transferVO, "");
			int count = 0;
			
			JobLauncher jobLauncher = (JobLauncher) applicationContext.getBean("jobLauncher");
			
			/**
			 *처리할 업무코드(jobCode) 및 cont_type 추가
			 *0510 : 패널티내역(월)
			 *0520 : 현금부족내역(월)
			 *0620 : 현금부족내역(주)
			 *0570 : 출동평균시간(월)
			 *0670 : 출동편균시간(주)
			 *05F0 : 장애1차처리완료율(월)
			 *06F0 : 장애1차처리완료율(주)
			 */
			HashMap<String,String> contType = new HashMap<String,String>();
			String executionJobCode = "0510,0520,0620,0570,0670,05F0,06F0";
			contType.put("0510", "1");
			contType.put("0520", "2");
			contType.put("0620", "2");
			contType.put("0570", "3");
			contType.put("0670", "3");
			contType.put("05F0", "4");
			contType.put("06F0", "4");
			
			if (files != null) {
				for (String fileName : files) {
					
					//업무코드
					String jobCode = fileName.substring(fileName.length()-4,fileName.length()).trim();
					//처리할 업무코드가 아니면 skip
					if( jobCode == null || executionJobCode.indexOf(jobCode) == -1)
					{
						continue;
					}
					if (!new File(transferVO.getLocalPath(), fileName + ".bak").isFile()) {
						
						try {
							Map<String, JobParameter> parameters = new LinkedHashMap<String, JobParameter>();
							// 의미없는 값이지만 파라미터를 중복해서 여러번 실행이 불가능 하다. 그런 이유로 추가함.
							parameters.put("temp", new JobParameter(Calendar.getInstance().getTimeInMillis()));
							parameters.put("fileName", new JobParameter(fileName));
							parameters.put("jobCode", new JobParameter(jobCode));
							parameters.put("contType", new JobParameter(contType.get(jobCode)));
							parameters.put(
									  "shinhan.file.name"
									, new JobParameter(
											new File(transferVO.getLocalPath() + "/" + fileName).getAbsolutePath()
									  )
							);
							
							JobParameters jobParameters = new JobParameters(parameters);							
							
							JobExecution execution = jobLauncher.run((Job) applicationContext.getBean("shinhanJob"), jobParameters);
							System.out.println("Exit Status : " + execution.getStatus());						
							
						} catch (Exception e) {							
							e.printStackTrace();							
						}
						
						count++;
					}
				}

				if (count > 0) {
					System.out.println("Done");
				} else {
					System.out.println("No data.");
				}
			} else {
				System.out.println("No data.");
			}
        } catch(Exception e) {
			e.printStackTrace();
        }
	}
}