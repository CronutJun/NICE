package com.nicetcm.nibsplus.scheduler.service;

import java.rmi.Remote;

import com.nicetcm.nibsplus.scheduler.model.JobResultVO;
import com.nicetcm.nibsplus.scheduler.model.JobVO;


public interface RemoteScheduleExecuter extends Remote {

    public JobResultVO executeJob(JobVO jobVO) throws Exception;
}
