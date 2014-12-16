/** 
 * com.nicetcm.nibsplus.orgsend.common.MsgLogger
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 12. 9.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.orgsend.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.nicetcm.nibsplus.util.NibsBatchUtil;


/**
 * 여기에 클래스(한글)명.
 * <pre>
 * 여기에 클래스 설명 및 변경 이력을 기술하십시오.
 * </pre>
 * 
 * @author 박상철
 * @version 1.0
 * @see
 */
public class MsgLogger {
	private String logPath;
	private Map<String, File> logFiles = new HashMap<String, File>();

	private Logger logger = Logger.getLogger(this.getClass());
	private Logger errorLogger = Logger.getLogger("rootError");
	
	public synchronized void info(String job, String org, String msg) {
		logger.info(msg);
		
		try {
			FileUtils.write(getFile(job, org), String.format("[%s] %s %s", NibsBatchUtil.SysDate("yyyy-MM-dd HH:mm:ss"), msg,"\r\n"), true);
		} catch(Exception e) {
			errorLogger.error(e.getMessage(), e.getCause());
		}
	}
	
	private synchronized File getFile(String job, String org) {
		File file;
		
		if (logFiles.containsKey(job + org)) {
			file = logFiles.get(job + org);
			
			if (file.getAbsolutePath().indexOf(NibsBatchUtil.SysDate()) != -1) {
				return file;
			} else {
				return createNew(job, org);
			}
		} else {
			return createNew(job, org);
		}
	}
	
	private synchronized File createNew(String job, String org) {
		File file = new File(logPath + "/" + NibsBatchUtil.SysDate(), job + "_" + org + ".log");
		
		logFiles.put(job + org, file);
		
		return file;
	}

	/**
	 * @return the logPath
	 */
	public String getLogPath() {
		return logPath;
	}

	/**
	 * @param logPath the logPath to set
	 */
	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}
}