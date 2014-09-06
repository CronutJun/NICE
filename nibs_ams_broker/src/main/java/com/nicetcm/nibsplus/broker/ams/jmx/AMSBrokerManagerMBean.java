package com.nicetcm.nibsplus.broker.ams.jmx;

public interface AMSBrokerManagerMBean {

    public void shutdownServer();
    public void reloadSchema();
    public int getRMIResTimeout();
    public void setRMIResTimeout(int timeout);
}
