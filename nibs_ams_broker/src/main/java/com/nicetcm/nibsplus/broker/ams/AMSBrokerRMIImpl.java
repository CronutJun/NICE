package com.nicetcm.nibsplus.broker.ams;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * AMSBrokerRMIImpl
 *
 *  AMS Broker와 NIBS Web Client간 RMI인터페이스 구현
 *
 *
 * @author  K.D.J
 * @since   2014.08.18
 */

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.ams.rmi.*;
import com.nicetcm.nibsplus.broker.common.MsgCommon;

public class AMSBrokerRMIImpl implements AMSBrokerRMI {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerRMIImpl.class);
    private FileOutputStream fOut;

    public AMSBrokerRMIImpl() {
        /*super();*/
    }

    public String threadTest(String data) throws java.rmi.RemoteException {
        logger.debug("Thread " + Thread.currentThread().getId() + "'s data = " + data);
        try {
            Thread.sleep(10000);
            logger.debug("Thread " + Thread.currentThread().getId() + "call complete");
        }
        catch ( Exception err ) {
            logger.debug("Exception raised " + err.getMessage());
        }
        return data;
    }

    public void dataUploadToBroker( byte[] data, boolean isFirst, boolean hasNext ) {
        try {
            if( isFirst && hasNext) {
                fOut = new FileOutputStream("C:/sample.pptx");
            }
            if( data.length > 0 ) {
                fOut.write(data);
            }
            if( !isFirst && !hasNext ) {
                fOut.flush();
                fOut.close();
                fOut = null;
            }
        }
        catch (Exception err) {
            logger.warn(err.getMessage());
        }
    }

    private void waitBlocking( AMSBrokerReqJob reqJob ) throws Exception {

        boolean interrupted = false;
        try {
            String defTimeOut = MsgCommon.msgProps.getProperty("rmi.response.timeout");
            if( defTimeOut == null )
                defTimeOut = "60";
            ByteBuffer rslt = reqJob.getAns().poll(Integer.parseInt(defTimeOut), TimeUnit.SECONDS);
            if( rslt == null ) {
                throw new AMSBrokerTimeoutException("RMI timeout");
            }
            else if( rslt.capacity() == 3 ) {
                throw new AMSBrokerTimeoutException("timeout");
            }
            else if( rslt.capacity() == 1 ) {
                throw new Exception("Error while request information");
            }
        }
        catch (InterruptedException e) {
            interrupted = true;
        }

        if (interrupted) {
            Thread.currentThread().interrupt();
        }

    }

    public void reqEnvInfToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, int timeOut ) throws Exception {

        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, true);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo( trxNo );
            reqJob.setTrxCd( trxCd );
            reqJob.setActCd( actCd );
            reqJob.setTrxUid( trxUid );
            reqJob.setTimeOut( timeOut );
            reqJob.requestJob();
            waitBlocking( reqJob );
        }
        catch( Exception e ) {
            logger.debug(e.getMessage());
            throw e;
        }
    }

    public void reqEnvInfToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs ) throws Exception {
        try {
            for( String macNo: macs ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo( trxNo );
                reqJob.setTrxCd( trxCd );
                reqJob.setActCd( actCd );
                reqJob.setTrxUid( trxUid );
                reqJob.setTimeOut( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.debug(e.getMessage());
            throw e;
        }
    }

    public void reqRegInfToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqRegInfo reqRegInfo, int timeOut ) throws Exception {
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqRegInfo.getMacNo(), true);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo( trxNo );
            reqJob.setTrxCd( trxCd );
            reqJob.setActCd( actCd );
            reqJob.setTrxUid( trxUid );
            reqJob.setReqRegInfo( reqRegInfo );
            reqJob.setTimeOut( timeOut );
            reqJob.requestJob();
            waitBlocking( reqJob );
        }
        catch( Exception e ) {
            logger.debug(e.getMessage());
            throw e;
        }
    }

    public void reqRegInfToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqRegInfo> reqRegInfos ) throws Exception {
        try {
            for( RMIReqRegInfo reqRegInfo: reqRegInfos ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqRegInfo.getMacNo(), false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo( trxNo );
                reqJob.setTrxCd( trxCd );
                reqJob.setActCd( actCd );
                reqJob.setTrxUid( trxUid );
                reqJob.setReqRegInfo( reqRegInfo );
                reqJob.setTimeOut( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.debug(e.getMessage());
            throw e;
        }
    }

    public void reqIniInfToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqIniInfo reqIniInfo, int timeOut ) throws Exception {
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqIniInfo.getMacNo(), true);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo( trxNo );
            reqJob.setTrxCd( trxCd );
            reqJob.setActCd( actCd );
            reqJob.setTrxUid( trxUid );
            reqJob.setReqIniInfo( reqIniInfo );
            reqJob.setTimeOut( timeOut );
            reqJob.requestJob();
            waitBlocking( reqJob );
        }
        catch( Exception e ) {
            logger.debug(e.getMessage());
            throw e;
        }
    }

    public void reqIniInfToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqIniInfo> reqIniInfos ) throws Exception {
        try {
            for( RMIReqIniInfo reqIniInfo: reqIniInfos ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqIniInfo.getMacNo(), false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo( trxNo );
                reqJob.setTrxCd( trxCd );
                reqJob.setActCd( actCd );
                reqJob.setTrxUid( trxUid );
                reqJob.setReqIniInfo( reqIniInfo );
                reqJob.setTimeOut( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.debug(e.getMessage());
            throw e;
        }
    }

    public void reqEnvChgToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, ArrayList<RMIEnvValue> envValues ) throws Exception {

        try {
            for( RMIEnvValue envValue: envValues ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo( trxNo );
                reqJob.setTrxCd( trxCd );
                reqJob.setActCd( actCd );
                reqJob.setTrxUid( trxUid );
                reqJob.setTimeOut( 0 );
                reqJob.setEnvValue( envValue );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.debug(e.getMessage());
            throw e;
        }

    }

    public void reqEnvChgToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, RMIEnvValue envValue ) throws Exception {

        try {
            for( String macNo: macs ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo( trxNo );
                reqJob.setTrxCd( trxCd );
                reqJob.setActCd( actCd );
                reqJob.setTrxUid( trxUid );
                reqJob.setTimeOut( 0 );
                reqJob.setEnvValue( envValue );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.debug(e.getMessage());
            throw e;
        }

    }

    public void reqRegChgToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqRegInfo reqRegInfo ) throws Exception {
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqRegInfo.getMacNo(), false);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo( trxNo );
            reqJob.setTrxCd( trxCd );
            reqJob.setActCd( actCd );
            reqJob.setTrxUid( trxUid );
            reqJob.setReqRegInfo( reqRegInfo );
            reqJob.setTimeOut( 0 );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.debug(e.getMessage());
            throw e;
        }
    }

    public void reqRegChgToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqRegInfo> reqRegInfos ) throws Exception {
        try {
            for( RMIReqRegInfo reqRegInfo: reqRegInfos ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqRegInfo.getMacNo(), false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo( trxNo );
                reqJob.setTrxCd( trxCd );
                reqJob.setActCd( actCd );
                reqJob.setTrxUid( trxUid );
                reqJob.setReqRegInfo( reqRegInfo );
                reqJob.setTimeOut( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.debug(e.getMessage());
            throw e;
        }
    }

    public void reqIniChgToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqIniInfo reqIniInfo ) throws Exception {
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqIniInfo.getMacNo(), false);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo( trxNo );
            reqJob.setTrxCd( trxCd );
            reqJob.setActCd( actCd );
            reqJob.setTrxUid( trxUid );
            reqJob.setReqIniInfo( reqIniInfo );
            reqJob.setTimeOut( 0 );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.debug(e.getMessage());
            throw e;
        }
    }

    public void reqIniChgToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqIniInfo> reqIniInfos ) throws Exception {
        try {
            for( RMIReqIniInfo reqIniInfo: reqIniInfos ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqIniInfo.getMacNo(), false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo( trxNo );
                reqJob.setTrxCd( trxCd );
                reqJob.setActCd( actCd );
                reqJob.setTrxUid( trxUid );
                reqJob.setReqIniInfo( reqIniInfo );
                reqJob.setTimeOut( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.debug(e.getMessage());
            throw e;
        }
    }

}
