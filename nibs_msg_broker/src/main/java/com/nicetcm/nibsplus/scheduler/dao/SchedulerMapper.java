package com.nicetcm.nibsplus.scheduler.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nicetcm.nibsplus.scheduler.model.JobVO;
import com.nicetcm.nibsplus.scheduler.model.SchedulerVO;

/**
 * DB에서 스케쥴정보 조회
 * <pre>
 *
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public interface SchedulerMapper
{
    List<SchedulerVO> selectEnableSchedule(@Param("quartzNodeName") String quartzNodeName);

    SchedulerVO selectScheduleByPk(JobVO jobVO);

}
