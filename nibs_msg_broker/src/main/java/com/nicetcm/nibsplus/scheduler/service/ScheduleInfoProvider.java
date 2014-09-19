package com.nicetcm.nibsplus.scheduler.service;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.nicetcm.nibsplus.scheduler.model.JobVO;
import com.nicetcm.nibsplus.scheduler.model.SchedulerVO;

public interface ScheduleInfoProvider
{

    List<SchedulerVO> selectEnableSchedule(String quartzNodeName);

    ApplicationContext getApplicationContext(String springContextXml);

    SchedulerVO selectScheduleByPk(JobVO jobVO);

}
