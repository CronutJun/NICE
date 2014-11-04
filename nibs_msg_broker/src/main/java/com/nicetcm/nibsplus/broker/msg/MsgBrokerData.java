package com.nicetcm.nibsplus.broker.msg;

import java.util.Date;

import org.springframework.transaction.TransactionStatus;

public class MsgBrokerData {

    private TransactionStatus txs;
    private String sSysDate;
    private String sNSysDate;
    private String sSysTime;
    private Date   dSysDate;
    private boolean keepResData;

    public TransactionStatus getTXS() {
        return txs;
    }

    public void setTXS(TransactionStatus txs) {
        this.txs = txs;
    }

    public String getSysDate() {
        return sSysDate;
    }

    public void setSysDate(String sSysDate) {
        this.sSysDate = sSysDate;
    }

    public String getNSysDate() {
        return sNSysDate;
    }

    public void setNSysDate(String sNSysDate) {
        this.sNSysDate = sNSysDate;
    }

    public String getSysTime() {
        return sSysTime;
    }

    public void setSysTime(String sSysTime) {
        this.sSysTime = sSysTime;
    }

    public Date getDSysDate() {
        return dSysDate;
    }

    public void setDSysDate(Date dSysDate) {
        this.dSysDate = dSysDate;
    }

    public boolean isKeepResData() {
        return keepResData;
    }

    public void setKeepResData(boolean keepResData) {
        this.keepResData = keepResData;
    }
}
