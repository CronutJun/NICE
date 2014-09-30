package com.nicetcm.nibsplus.broker.msg.jmx;

public interface MsgBrokerManagerMBean {

    public String shutdownServer(String operation);
    public void reloadSchema();
    public void hotSwapBean(String beanName, String beanClassName);
    public int getRMIResTimeout();
    public void setRMIResTimeout(int timeout);
}
