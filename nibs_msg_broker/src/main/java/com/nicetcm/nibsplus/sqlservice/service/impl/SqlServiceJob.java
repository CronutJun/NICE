package com.nicetcm.nibsplus.sqlservice.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        
        ApplicationContext applicationContext = (ApplicationContext)jobDataMap.get("applicationContext");
        SqlService sqlService = applicationContext.getBean("sqlServiceImpl", SqlServiceImpl.class);
        Logger logger = LoggerFactory.getLogger(schedulerVO.getJobGroup());

    	logger.info("Start " + schedulerVO.getRealTimeCommand());
        
        try {
			Method method = SqlService.class.getDeclaredMethod(schedulerVO.getRealTimeCommand(), Logger.class);
			method.invoke(sqlService, logger);

	        logger.info(schedulerVO.getRealTimeCommand() + " success.");
		} catch (SecurityException e) {
			logger.error(e.getMessage(), e.getLocalizedMessage());
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e.getLocalizedMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), e.getLocalizedMessage());
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e.getLocalizedMessage());
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(), e.getLocalizedMessage());
		}
    }
}
