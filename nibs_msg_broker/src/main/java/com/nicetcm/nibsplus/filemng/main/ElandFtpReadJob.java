/** 
 * com.nicetcm.nibsplus.filemng.main.ElandFtpRead
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 11. 12.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.filemng.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;

import com.nicetcm.nibsplus.filemng.model.TransferVO;
import com.nicetcm.nibsplus.filemng.service.impl.SFtpTransfer;
import com.nicetcm.nibsplus.scheduler.common.AbstractJob;
import com.nicetcm.nibsplus.util.NibsBatchUtil;

@DisallowConcurrentExecution
public class ElandFtpReadJob extends AbstractJob {

	private Logger logger = Logger.getLogger(this.getClass());
	private Logger errorLogger = Logger.getLogger("FilemngERROR");
	
	@Override
	public void execte(JobExecutionContext context) {
        try {
    		ApplicationContext applicationContext = (ApplicationContext)context.getMergedJobDataMap().get("applicationContext");
    		Properties config = applicationContext.getBean("config", Properties.class);

            TransferVO transferVO = new TransferVO();
            transferVO.setHost(config.getProperty("host.host"));
            transferVO.setAvailableServerPort(Integer.parseInt(config.getProperty("host.availableServerPort")));
            transferVO.setUserId(config.getProperty("host.userid"));
            transferVO.setPassword(config.getProperty("host.password"));
            transferVO.setRemotePath(config.getProperty("sapma.remote.path"));
            // transferVO.setLocalPath(config.getProperty("sapma.local.path"));

			List<String> files = new ArrayList<String>();
			files.addAll(SFtpTransfer.getFileNames(transferVO, "NICE*" + NibsBatchUtil.SysDate(-1) + "_MASTER.dat"));
			files.addAll(SFtpTransfer.getFileNames(transferVO, "NICE*" + NibsBatchUtil.SysDate(-1) + "_DETAIL.dat"));
			files.addAll(SFtpTransfer.getFileNames(transferVO, "NICE*" + NibsBatchUtil.SysDate(-1) + "_COUPON.dat"));			
			
			
			int count = 0;
					
			JobLauncher jobLauncher = (JobLauncher) applicationContext.getBean("jobLauncher");
			// File[] files = new File[0];
			// files = (File[])ArrayUtils.addAll(files, new File(config.getProperty("sapma.local.path")).listFiles(new ElandFilenameFilter()));
			// files = (File[])ArrayUtils.addAll(files, new File(config.getProperty("coupon.local.path")).listFiles(new ElandFilenameFilter()));

			logger.info(this.getClass().getName() + " execute...");
			
			for (String fileName : files) {
				try {
					// JobParameters jobParameters = new JobParametersBuilder().addString("pid", "10").toJobParameters();
					Map<String, JobParameter> parameters = new LinkedHashMap<String, JobParameter>();
					// 의미없는 값이지만 파라미터를 중복해서 여러번 실행이 불가능 하다. 그런 이유로 추가함.
					parameters.put("temp", new JobParameter(Calendar.getInstance().getTimeInMillis()));
					parameters.put("yyyymmdd", new JobParameter(NibsBatchUtil.SysDate()));
					parameters.put("fileName", new JobParameter(fileName));
	
					JobExecution execution;
					
					if (fileName.endsWith("MASTER.dat")) {
						parameters.put("eland.file.name", new JobParameter(config.getProperty("sapma.local.path") + "/" + fileName));
						
						execution = jobLauncher.run((Job) applicationContext.getBean("sapMasterJob"), new JobParameters(parameters));
					} else if (fileName.endsWith("DETAIL.dat")) {
						parameters.put("eland.file.name", new JobParameter(config.getProperty("sapde.local.path") + "/" + fileName));
						
						execution = jobLauncher.run((Job) applicationContext.getBean("sapDetailJob"), new JobParameters(parameters));
					} else if (fileName.endsWith("COUPON.dat")) {
						parameters.put("eland.file.name", new JobParameter(config.getProperty("coupon.local.path") + "/" + fileName));
						
						execution = jobLauncher.run((Job) applicationContext.getBean("couponJob"), new JobParameters(parameters));
					} else {
						throw new Exception("Do not execution. file name is '" + fileName + "'");
					}

					SFtpTransfer.renameToBak(transferVO, fileName);

					logger.info(String.format("%s Exit Status : %s - %s", this.getClass().getName(), execution.getStatus(), fileName));
				} catch (Exception e) {
		        	errorLogger.error(e.getMessage(), e.getCause());
				}
				
				count++;
			}

			if (count > 0) {
				logger.info(this.getClass().getName() + " Done");
			} else {
				logger.info(this.getClass().getName() + " No data.");
			}
	    } catch(Exception e) {
	    	errorLogger.error(e.getMessage(), e.getCause());
        }
	}
}
