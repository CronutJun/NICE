package com.nicetcm.nibsplus.broker.msg.jmx;

public interface MsgBrokerManagerMBean {

    public String shutdownServer(String operation);
    public void reloadSchema() throws Exception;
    public String hotSwapBean(String beanClassName);
    public String setLogLevel(String logLevel);
    public void listBean();
    public int getRMIResTimeout();
    public void setRMIResTimeout(int timeout);
    public int getStopRepairMsg();
    public void setStopRepairMsg(int stopped);
    public String reattachConsumer(String consumerName);
}
