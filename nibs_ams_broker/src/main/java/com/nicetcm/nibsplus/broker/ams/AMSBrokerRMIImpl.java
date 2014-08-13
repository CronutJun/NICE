package com.nicetcm.nibsplus.broker.ams;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.*;
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

    public void reqEnvInfToMac( Date trxDate, String trxNo, String macNo, int timeOut ) throws Exception {

        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob("3301");
            reqJob.requestJob();
            ByteBuffer rslt = reqJob.getAns().take();
            logger.debug("reqEnvInfToMac result = {}",rslt.capacity());
        }
        catch( Exception e ) {
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
    }

    public void reqEnvInfToMacs( Date trxDate, String trxNo, ArrayList<String> macs ) throws Exception {

    }

}
