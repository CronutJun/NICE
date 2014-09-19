package com.nicetcm.nibsplus.scheduler.service;

import com.nicetcm.nibsplus.scheduler.model.JobResultVO;
import com.nicetcm.nibsplus.scheduler.model.JobVO;


public interface RemoteScheduleExecuter
{

    public JobResultVO executeJob(JobVO jobVO) throws Exception;
}
