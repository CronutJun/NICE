package com.nicetcm.nibsplus.broker.ams.jmx;

public interface AMSBrokerManagerMBean {

    public String shutdownServer(String operation);
    public void reloadSchema();
    public int getRMIResTimeout();
    public void setRMIResTimeout(int timeout);
}
