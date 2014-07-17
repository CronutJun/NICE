package com.nicetcm.nibsplus.broker.msg;

import javax.jms.Message;
import javax.jms.BytesMessage;
import javax.jms.MessageListener;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class MsgBrokerListener implements MessageListener {
 
    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerListener.class);
    
    public void onMessage(Message message) {
         logger.debug("Thread = " + Thread.currentThread().getId() + ", Got message '" + message.toString() + "'");
         BytesMessage bInst = (BytesMessage)message;
         try {
             int len = (int)bInst.getBodyLength();
             byte[] rcv = new byte[len];
             bInst.readBytes(rcv);
             MsgBrokerWorkGroup.getInstance().execute(new MsgBrokerWorker(rcv));
         }
         catch( JMSException e ) {
             logger.error(e.getMessage());
         }
         catch( Exception e ) {
             logger.error(e.getMessage());
         }
             
    }
 
}