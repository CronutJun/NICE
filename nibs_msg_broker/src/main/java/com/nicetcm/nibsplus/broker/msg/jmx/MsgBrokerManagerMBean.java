package com.nicetcm.nibsplus.broker.msg.jmx;

public interface MsgBrokerManagerMBean {

    public String shutdownServer(String operation);
    public void reloadSchema();
    public String hotSwapBean(String beanClassName);
    public String setLogLevel(String logLevel);
    public void listBean();
    public int getRMIResTimeout();
    public void setRMIResTimeout(int timeout);
}
