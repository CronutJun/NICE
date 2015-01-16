package com.nicetcm.nibsplus.broker.ams.services;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;

public interface InitScheduler {

    public void initSchedule( AMSBrokerData safeData, String type ) throws Exception;

}
