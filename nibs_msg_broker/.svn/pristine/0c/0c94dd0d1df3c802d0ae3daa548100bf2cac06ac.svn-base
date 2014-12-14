package com.nicetcm.nibsplus.filemng.main;

import java.util.Calendar;
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
public class VpnIpReadJob implements org.quartz.Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ApplicationContext applicationContext = (ApplicationContext)context.getMergedJobDataMap().get("applicationContext");
		Properties config = applicationContext.getBean("config", Properties.class);

        TransferVO transferVO = new TransferVO();
        transferVO.setHost(config.getProperty("host.host"));
        transferVO.setAvailableServerPort(Integer.parseInt(config.getProperty("host.availableServerPort")));
        transferVO.setUserId(config.getProperty("host.userid"));
        transferVO.setPassword(config.getProperty("host.password"));
        transferVO.setRemotePath(config.getProperty("vpnip.remote.path"));
        transferVO.setLocalPath(config.getProperty("vpnip.local.path"));
		
        try {
			List<String> files = SFtpTransfer.getFileNames(transferVO, "vpn*");
			int count = 0;
			
			JobLauncher jobLauncher = (JobLauncher) applicationContext.getBean("jobLauncher");
			// ex) "/Project_NIBS/FTP_RECEIVE/vpn/vpn81.tb"
			// File[] files = new File(config.getProperty("vpnip.local.path")).listFiles();
	
			if (files != null) {
				for (String fileName : files) {
					try {
						// JobParameters jobParameters = new JobParametersBuilder().addString("pid", "10").toJobParameters();
						Map<String, JobParameter> parameters = new LinkedHashMap<String, JobParameter>();
						// 의미없는 값이지만 파라미터를 중복해서 여러번 실행이 불가능 하다. 그런 이유로 추가함.
						parameters.put("temp", new JobParameter(Calendar.getInstance().getTimeInMillis()));
						parameters.put("fileName", new JobParameter(fileName));
						parameters.put("vpn.file.name", new JobParameter(transferVO.getLocalPath() + "/" + fileName));
			
						JobParameters jobParameters = new JobParameters(parameters);
						JobExecution execution = jobLauncher.run((Job)applicationContext.getBean("vpnJob"), jobParameters);

						SFtpTransfer.renameToBak(transferVO, fileName);
						
						System.out.println("Exit Status : " + execution.getStatus());
					} catch (Exception e) {
						e.printStackTrace();
					}
						
					count++;
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