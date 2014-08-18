package com.nicetcm.nibsplus.broker.ams;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.ams.rmi.*;

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
            ByteBuffer rslt = reqJob.getAns().take();
            if( rslt.capacity() == 3 ) {
                throw new AMSBrokerTimeoutException("timeout");
            }
            else if( rslt.capacity() == 1 ) {
                throw new Exception("Error while request information");
            }
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

    public void reqRegInfToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, RMIReqRegInfo reqRegInfo, int timeOut ) throws Exception {

        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, true);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo( trxNo );
            reqJob.setTrxCd( trxCd );
            reqJob.setActCd( actCd );
            reqJob.setTrxUid( trxUid );
            reqJob.setReqRegInfo( reqRegInfo );
            reqJob.setTimeOut( timeOut );
            reqJob.requestJob();
            ByteBuffer rslt = reqJob.getAns().take();
            if( rslt.capacity() == 3 ) {
                throw new AMSBrokerTimeoutException("timeout");
            }
            else if( rslt.capacity() == 1 ) {
                throw new Exception("Error while request information");
            }
        }
        catch( Exception e ) {
            logger.debug(e.getMessage());
            throw e;
        }
    }

}
