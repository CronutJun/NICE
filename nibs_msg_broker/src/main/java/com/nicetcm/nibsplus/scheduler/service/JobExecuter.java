package com.nicetcm.nibsplus.scheduler.service;

import org.springframework.context.ApplicationContext;

import com.nicetcm.nibsplus.scheduler.model.SchedulerVO;

public interface JobExecuter
{
     public void executeJob(ApplicationContext applicationContext, SchedulerVO schedulerVO) throws Exception;
}
