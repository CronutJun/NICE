package com.nicetcm.nibsplus.broker.ams;

import java.util.concurrent.*;
import java.util.Date;
import java.nio.ByteBuffer;
import com.nicetcm.nibsplus.broker.ams.rmi.RMIReqRegInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AMSBrokerReqJob {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerReqJob.class);

    private static final ConcurrentMap<String, AMSBrokerOutboundQ> macMap = new ConcurrentHashMap<String, AMSBrokerOutboundQ>();

    private final String macNo;
    private final boolean isBlocking;
    private final BlockingQueue<ByteBuffer> ans;
    private Date trxDate;
    private String trxNo;
    private String trxCd;
    private String actCd;
    private RMIReqRegInfo reqRegInfo;
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

    public Date getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Date trxDate) {
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

}
