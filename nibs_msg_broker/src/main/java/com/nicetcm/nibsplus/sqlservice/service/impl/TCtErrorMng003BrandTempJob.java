package com.nicetcm.nibsplus.sqlservice.service.impl;

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
public class TCtErrorMng003BrandTempJob implements Job {

    /**
     * Quartz에서 실행시킴
     * <pre>
     *
     * </pre>
     *
     * @param context
     * @throws JobExecutionException
     */
	@Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        
        SqlServiceMapper mapper = (SqlServiceMapper)jobDataMap.get("mapper");
        String jobKey = (String)jobDataMap.get("jobKey");
        
        Logger logger = LoggerFactory.getLogger(jobKey);
    	logger.info("Start " + jobKey);
    	
    	try {
    		logger.info("Execute : t_ct_error_mng_003_brand_temp");
        	mapper.t_ct_error_mng_003_brand_temp();

	    	logger.info("Success.");
    	} catch(Exception e) {
    		logger.error(e.getMessage());
        	logger.info("Error.");
    	}
    }
}
