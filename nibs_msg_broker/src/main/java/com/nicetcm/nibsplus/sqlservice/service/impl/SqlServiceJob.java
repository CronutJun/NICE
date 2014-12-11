package com.nicetcm.nibsplus.sqlservice.service.impl;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.nicetcm.nibsplus.scheduler.model.SchedulerVO;
import com.nicetcm.nibsplus.sqlservice.service.SqlService;

/**
 * @author mulpats@gmail.com
 * @version 1.0
 * @see
 */
@DisallowConcurrentExecution
public class SqlServiceJob implements Job {

	private Logger logger = Logger.getLogger(this.getClass());
	private Logger errorLogger = Logger.getLogger("SqlServiceERROR");
    
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
        SchedulerVO schedulerVO = (SchedulerVO)jobDataMap.get("SchedulerVO");
        
        try {
            ApplicationContext applicationContext = (ApplicationContext)jobDataMap.get("applicationContext");
            SqlService sqlService = applicationContext.getBean("sqlServiceImpl", SqlServiceImpl.class);

    		logger.info(String.format("%s %-35s[%s] execute.", Thread.currentThread().getName(), schedulerVO.getJobGroup(), StringUtils.defaultIfEmpty(schedulerVO.getRealTimeCommand(), "")));
    		
			Method method = null;
			try {
				method = SqlService.class.getDeclaredMethod(schedulerVO.getJobGroup(), Logger.class);
			} catch (NoSuchMethodException e) {}
			
			Method methodWithParam = null;
			try {
				methodWithParam = SqlService.class.getDeclaredMethod(schedulerVO.getJobGroup(), Logger.class, String.class);
			} catch(NoSuchMethodException e) {}
			
			Object retObj = null;
			
			if (method != null) {
				retObj = method.invoke(sqlService, logger);
			} else if (methodWithParam != null) {
				retObj = methodWithParam.invoke(sqlService, logger, schedulerVO.getRealTimeCommand());
			} else {
				throw new RuntimeException("Is not exist batch. " + schedulerVO.getJobGroup());
			}

			logger.info(String.format("%s %-35s[%s] success. returnValue:%s", Thread.currentThread().getName(), schedulerVO.getJobGroup(), StringUtils.defaultIfEmpty(schedulerVO.getRealTimeCommand(), ""), StringUtils.defaultIfEmpty((String)retObj, "")));
		} catch (Exception e) {
			errorLogger.info(String.format("%s %s %s[%s]", Thread.currentThread().getName(), e.getMessage(), schedulerVO.getJobGroup(), StringUtils.defaultIfEmpty(schedulerVO.getRealTimeCommand(), "")));
		}
    }
}
