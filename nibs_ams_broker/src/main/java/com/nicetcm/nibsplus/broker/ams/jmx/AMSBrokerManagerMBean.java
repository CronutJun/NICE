package com.nicetcm.nibsplus.broker.ams.jmx;

public interface AMSBrokerManagerMBean {

    public String shutdownServer(String operation);
    public void reloadSchema();
    public String hotSwapBean(String beanClassName);
    public String setLogLevel(String logLevel);
    public int getRMIResTimeout();
    public void setRMIResTimeout(int timeout);
}
