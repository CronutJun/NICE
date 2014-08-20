package com.nicetcm.nibsplus.broker.ams;

/*
 * Copyright 2014 The NIBS Project
 *
 * AMS 기기관리시스템 - AMSBrokerReqJob
 *
 * 기기와 접속 정보관리 및 대기열 관리를 위한 객체
 *
 * @author  K.D.J
 * @since   2014.08.18
 */

import java.util.concurrent.*;
import java.nio.ByteBuffer;
import com.nicetcm.nibsplus.broker.ams.rmi.RMIReqRegInfo;
import com.nicetcm.nibsplus.broker.ams.rmi.RMIReqIniInfo;
import com.nicetcm.nibsplus.broker.ams.rmi.RMIEnvValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AMSBrokerReqJob {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerReqJob.class);

    private static final ConcurrentMap<String, AMSBrokerOutboundQ> macMap = new ConcurrentHashMap<String, AMSBrokerOutboundQ>();

    private final String macNo;
    private final boolean isBlocking;
    private final BlockingQueue<ByteBuffer> ans;
    private String trxDate;
    private String trxNo;
    private String trxCd;
    private String actCd;
    private String trxUid;
    private String OrgCreateDate;
    private String OrgMsgSeq;
    private RMIReqRegInfo reqRegInfo;
    private RMIReqIniInfo reqIniInfo;
    private RMIEnvValue envValue;
    private int timeOut;

    public AMSBrokerReqJob(String macNo, boolean isBlocking) {

        this.macNo = macNo;
        this.isBlocking = isBlocking;
        if( isBlocking )
            this.ans = new LinkedBlockingQueue<ByteBuffer>();
        else this.ans = null;

    }

    private static BlockingQueue<AMSBrokerReqJob> getQueue(String macNo) throws Exception {

        BlockingQueue<AMSBrokerReqJob> queue;

        if( macMap.containsKey(macNo) ) {
            queue =  macMap.get(macNo).getOutQ();
        }
        else {
            AMSBrokerOutboundQ outQ = new AMSBrokerOutboundQ();
            macMap.put( macNo,outQ );
            queue = outQ.getOutQ();
        }
        return queue;
    }

    public void requestJob() throws Exception {
        logger.debug("requestJob");
        BlockingQueue<AMSBrokerReqJob> reqQue = getQueue( this.macNo );
        reqQue.put( this );
        logger.debug("requestJob OK");
    }

    public BlockingQueue<ByteBuffer> getAns() {
        return ans;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public String getTrxNo() {
        return trxNo;
    }

    public void setTrxNo(String trxNo) {
        this.trxNo = trxNo;
    }

    public String getTrxCd() {
        return trxCd;
    }

    public void setTrxCd(String trxCd) {
        this.trxCd = trxCd;
    }

    public String getActCd() {
        return actCd;
    }

    public void setActCd(String actCd) {
        this.actCd = actCd;
    }

    public String getTrxUid() {
        return trxUid;
    }

    public void setTrxUid(String trxUid) {
        this.trxUid = trxUid;
    }

    public String getOrgCreateDate() {
        return OrgCreateDate;
    }

    public void setOrgCreateDate(String orgCreateDate) {
        OrgCreateDate = orgCreateDate;
    }

    public String getOrgMsgSeq() {
        return OrgMsgSeq;
    }

    public void setOrgMsgSeq(String orgMsgSeq) {
        OrgMsgSeq = orgMsgSeq;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getMacNo() {
        return macNo;
    }

    public boolean getIsBlocking() {
        return isBlocking;
    }

    public RMIReqRegInfo getReqRegInfo() {
        return reqRegInfo;
    }

    public void setReqRegInfo(RMIReqRegInfo reqRegInfo) {
        this.reqRegInfo = reqRegInfo;
    }

    public RMIReqIniInfo getReqIniInfo() {
        return reqIniInfo;
    }

    public void setReqIniInfo(RMIReqIniInfo reqIniInfo) {
        this.reqIniInfo = reqIniInfo;
    }

    public RMIEnvValue getEnvValue() {
        return envValue;
    }

    public void setEnvValue(RMIEnvValue envValue) {
        this.envValue = envValue;
    }

}
