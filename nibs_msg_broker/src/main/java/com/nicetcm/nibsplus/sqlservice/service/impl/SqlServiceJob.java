package com.nicetcm.nibsplus.sqlservice.service.impl;

import java.util.LinkedList;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.sqlservice.dao.SqlServiceMapper;

/**
 * @author mulpats@gmail.com
 * @version 1.0
 * @see
 */
@DisallowConcurrentExecution
public class SqlServiceJob implements Job {

    /**
     * Quartz에서 실행시킴
     * <pre>
     *
     * </pre>
     *
     * @param context
     * @throws JobExecutionException
     */
    @SuppressWarnings("unchecked")
	@Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        
        SqlServiceMapper mapper = (SqlServiceMapper)jobDataMap.get("mapper");
        LinkedList<String> jobList = (LinkedList<String>)jobDataMap.get("jobList");
        String jobKey = (String)jobDataMap.get("jobKey");
        
        Logger logger = LoggerFactory.getLogger(jobKey);
    	logger.info("Start " + jobKey);
    	
    	try {
	    	for (String job : jobList) {
	    		logger.info("Execute : " + job);
	        	mapper.executeProcedure(job);
	    	}

	    	logger.info("Success.");
    	} catch(Exception e) {
    		logger.error(e.getMessage());
        	logger.info("Error.");
    	}
    }
}
