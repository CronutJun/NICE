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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.ams.rmi.AMSBrokerTimeoutException;
import com.nicetcm.nibsplus.broker.ams.rmi.RMIEnvValue;
import com.nicetcm.nibsplus.broker.ams.rmi.RMIReqIniInfo;
import com.nicetcm.nibsplus.broker.ams.rmi.RMIReqRegInfo;
import com.nicetcm.nibsplus.broker.common.MsgCommon;

public class AMSBrokerReqJob {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerReqJob.class);

    private static final ConcurrentMap<String, AMSBrokerOutboundQ> macMap = new ConcurrentHashMap<String, AMSBrokerOutboundQ>();

    private final String macNo;
    private final boolean isBlocking;
    private final BlockingQueue<ByteBuffer> ans;

    private boolean receiveAns = false;

    private String downCmdType = "U"; /** 'U' - Upload, '0' - Download, '1' - Inquiry, '2' - Download continue */
    private long downWritePos = 0;
    private MessageDigest upComplete;
    private MessageDigest downComplete;
    private int upFileRetryCount = 0;
    private int upMD5RetryCount = 0;
    private int downFileRetryCount = 0;
    private int downMD5RetryCount = 0;
    private String tempFileName = null;
    private long tempFilePos = 0;
    private boolean upMD5Result = true;
    private boolean downMD5Result = true;

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


    private ArrayList<RMIEnvValue> envValues;
    private String devCd;
    private String empId;
    private String empPhone;
    private String arrivalTime;
    private String fileCreateDate;
    private String fileSeq;
    private String fileDate;
    private String fileType;
    private String filePath;
    private String fileName;
    private String fileVersion;
    private FileInputStream fIn;
    private FileOutputStream fOut;
    private int timeOut;

    public static void removeListenerInfo(String macNo) throws Exception {
        if( macMap.containsKey(macNo) ) {
            macMap.remove( macNo );
        }
    }

    public static void stopListener(String macNo) throws Exception {
        if( macMap.containsKey(macNo) ) {
            macMap.get(macNo).getConsumer().interrupt();
            macMap.remove( macNo );
        }
    }

    public static void stopListenerAll() throws Exception {
        while( macMap.entrySet().iterator().hasNext() ) {
            Map.Entry<String, AMSBrokerOutboundQ> e = (Map.Entry<String, AMSBrokerOutboundQ>)macMap.entrySet().iterator().next();
            e.getValue().getConsumer().interrupt();
            macMap.remove(e.getKey());
        }
        logger.warn("MAC request listener stopped.");
    }

    private static BlockingQueue<AMSBrokerReqJob> getQueue(String macNo) throws Exception {

        BlockingQueue<AMSBrokerReqJob> queue;

        if( macMap.containsKey(macNo) ) {
            queue =  macMap.get(macNo).getOutQ();
        }
        else {
            AMSBrokerOutboundQ outQ = new AMSBrokerOutboundQ(macNo);
            macMap.put( macNo,outQ );
            queue = outQ.getOutQ();
        }
        return queue;
    }

    public AMSBrokerReqJob(String macNo, boolean isBlocking) {

        this.macNo = macNo;
        this.isBlocking = isBlocking;
        if( isBlocking )
            this.ans = new LinkedBlockingQueue<ByteBuffer>();
        else this.ans = null;
        try {
            this.upComplete = MessageDigest.getInstance("MD5");
        }
        catch( NoSuchAlgorithmException e ) {
            this.upComplete = null;
        }
        try {
            this.downComplete = MessageDigest.getInstance("MD5");
        }
        catch( NoSuchAlgorithmException e ) {
            this.downComplete = null;
        }

    }

    public void requestJob() throws Exception {
        logger.warn("requestJob");
        BlockingQueue<AMSBrokerReqJob> reqQue = getQueue( this.macNo );
        reqQue.put( this );
        logger.warn("requestJob OK");
        try {
            if( !isBlocking ) return;

            logger.warn("requestJob is going to wait answer.");
            String defTimeOut = MsgCommon.msgProps.getProperty("rmi.response.timeout", "60");
            ByteBuffer rslt = ans.poll(Integer.parseInt(defTimeOut), TimeUnit.SECONDS);
            if( rslt == null ) {
                throw new AMSBrokerTimeoutException("timeout");
            }
            else if( rslt.capacity() == 3 ) { // TMO
                throw new AMSBrokerTimeoutException("timeout by app");
            }
            else if( rslt.capacity() == 1 ) {
                throw new Exception("Error while request information");
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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

    /**
     * 요청응답 수신 여부
     * @return
     */
    public boolean isReceiveAns() {
        return receiveAns;
    }

    public void setReceiveAns(boolean receiveAns) {
        this.receiveAns = receiveAns;
    }

    public String getDownCmdType() {
        return downCmdType;
    }

    public void setDownCmdType(String downCmdType) {
        this.downCmdType = downCmdType;
    }

    public long getDownWritePos() {
        return downWritePos;
    }

    public void setDownWritePos(long downWritePos) {
        this.downWritePos = downWritePos;
    }

    public MessageDigest getUpComplete() {
        return upComplete;
    }

    public MessageDigest getDownComplete() {
        return downComplete;
    }

    public String getUpMD5Checksum() {

        String result = "";

        for( byte b : getUpComplete().digest() ) {
            result += Integer.toString( ( b & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }

    public String getDownMD5Checksum() {

        String result = "";

        for( byte b : getDownComplete().digest() ) {
            result += Integer.toString( ( b & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }

    public int getUpFileRetryCount() {
        return upFileRetryCount;
    }

    public void addUpFileRetryCount() {
        upFileRetryCount++;
    }

    public void initUpFileRetryCount() {
        upFileRetryCount = 0;
    }

    public int getDownFileRetryCount() {
        return downFileRetryCount;
    }

    public void addDownFileRetryCount() {
        downFileRetryCount++;
    }

    public void initDownFileRetryCount() {
        downFileRetryCount = 0;
    }

    public int getUpMD5RetryCount() {
        return upMD5RetryCount;
    }

    public void addUpMD5RetryCount() {
        upMD5RetryCount++;
    }

    public void initUpMD5RetryCount() {
        upMD5RetryCount = 0;
    }

    public int getDownMD5RetryCount() {
        return downMD5RetryCount;
    }

    public void addDownMD5RetryCount() {
        downMD5RetryCount++;
    }

    public void initDownMD5RetryCount() {
        downMD5RetryCount = 0;
    }

    public String getTempFileName() {
        return tempFileName;
    }

    public void setTempFileName(String tempFileName) {
        this.tempFileName = tempFileName;
    }

    public void chkTempFile() {

        File chkFile = new File(this.tempFileName);
        if( chkFile.exists() && this.upComplete != null )
        try {
            InputStream fis =  new FileInputStream(chkFile);

            byte[] buffer = new byte[10240];
            int numRead;

            do {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    this.upComplete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);

            fis.close();
        }
        catch( Exception e ) {
            logger.warn( "chkTempFileName has error. {}", e.getMessage() );
        }

    }

    public long getTempFilePos() {
        return tempFilePos;
    }

    public void setTempFilePos(long tempFilePos) {
        this.tempFilePos = tempFilePos;
    }

    public boolean isUpMD5Result() {
        return upMD5Result;
    }

    public void setUpMD5Result(boolean upMD5Result) {
        this.upMD5Result = upMD5Result;
    }

    public boolean isDownMD5Result() {
        return downMD5Result;
    }

    public void setDownMD5Result(boolean downMD5Result) {
        this.downMD5Result = downMD5Result;
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

    public ArrayList<RMIEnvValue> getEnvValues() {
        return envValues;
    }

    public void setEnvValues(ArrayList<RMIEnvValue> envValues) {
        this.envValues = envValues;
    }

    public String getDevCd() {
        return devCd;
    }

    public void setDevCd(String devCd) {
        this.devCd = devCd;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFileCreateDate() {
        return fileCreateDate;
    }

    public void setFileCreateDate(String fileCreateDate) {
        this.fileCreateDate = fileCreateDate;
    }

    public String getFileSeq() {
        return fileSeq;
    }

    public void setFileSeq(String fileSeq) {
        this.fileSeq = fileSeq;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(String fileVersion) {
        this.fileVersion = fileVersion;
    }

    public FileInputStream getfIn() {
        return fIn;
    }

    public void setfIn(FileInputStream fIn) {
        this.fIn = fIn;
    }

    public FileOutputStream getfOut() {
        return fOut;
    }

    public void setfOut(FileOutputStream fOut) {
        this.fOut = fOut;
    }

}
