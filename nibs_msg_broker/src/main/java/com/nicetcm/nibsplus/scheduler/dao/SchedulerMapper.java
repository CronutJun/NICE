package com.nicetcm.nibsplus.scheduler.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nicetcm.nibsplus.scheduler.model.SchedulerVO;

public interface SchedulerMapper
{
    List<SchedulerVO> selectEnableSchedule(@Param("quartzNodeName") String quartzNodeName);
}
