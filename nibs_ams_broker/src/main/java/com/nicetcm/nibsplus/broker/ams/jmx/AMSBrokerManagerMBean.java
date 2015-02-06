package com.nicetcm.nibsplus.broker.ams.jmx;

public interface AMSBrokerManagerMBean {

    public String shutdownServer(String operation);
    public void reloadSchema() throws Exception;
    public String hotSwapBean(String beanClassName);
    public String setLogLevel(String logLevel);
    public int getAMSReqDefTimeout();
    public void setAMSReqDefTimeout(int timeout);
    public int getRMIResTimeout();
    public void setRMIResTimeout(int timeout);
    public String getJournalUploadTime();
    public void setJournalUploadTime(String uploadTime);
    public boolean getHexDump();
    public void setHexDump(boolean hexDump);
}
