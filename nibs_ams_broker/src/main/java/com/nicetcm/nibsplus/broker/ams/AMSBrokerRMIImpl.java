package com.nicetcm.nibsplus.broker.ams;

//import java.rmi.RemoteException;
import java.nio.ByteBuffer;
import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.*;

//import java.rmi.server.UnicastRemoteObject;

public class AMSBrokerRMIImpl /*extends UnicastRemoteObject*/ implements AMSBrokerRMI {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerRMIImpl.class);
    private FileOutputStream fOut;
    
    public AMSBrokerRMIImpl()/* throws RemoteException */{
        /*super();*/
    }
    
    /*@Override*/
    public String sayHello(String name)/* throws RemoteException */{
        
        AMSBrokerClient client = new AMSBrokerClient("localhost", 8080 );
        try {
            FileInputStream file = new FileInputStream("D:/CronutWorks/NICE/Documents/Design/07. �����������/��������/P140217_DS 06 ȭ�����Ǽ�_AMS v1.1.pptx");
            ByteBuffer msg = ByteBuffer.allocateDirect(MsgCommon.READ_BUF_SIZE);
            MsgParser msgPsr = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path") + "21003003.json").newMessage(msg);
            msg.position(0);
            msgPsr.setString("CM._AOCMsgCode",     "2100")
                  .setString("CM._AOCServiceCode", "3003")
                  .setString("CM._AOCMsgSendDate", "20140527")
                  .setString("CM._AOCMsgSendTime", "112600")
                  .setInt   ("CM._AOCMsgLen", msgPsr.getMessageLength() + file.available() - 9)
                  .syncMessage();
            logger.debug("Message Length = "  + msgPsr.getMessageLength());
            logger.debug("Last Position = "   + msgPsr.lastPosition());
            msg.limit(msgPsr.lastPosition());
            byte[] read = new byte[msg.limit()];
            msg.position(0);
            msg.get(read);
            logger.debug(new String(read));
            client.outboundCall(msg, file);
        }
        catch (Exception err) {
            err.printStackTrace();
        }
        return "Hello world " + name + "!";
        
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
}