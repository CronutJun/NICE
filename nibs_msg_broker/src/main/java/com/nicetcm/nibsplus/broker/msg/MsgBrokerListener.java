package com.nicetcm.nibsplus.broker.msg;

import javax.jms.Message;
import javax.jms.BytesMessage;
import javax.jms.MessageListener;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgBrokerListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerListener.class);

    private int prefetchSize;
    private boolean forceResp;
    private String redirectTo;
    private boolean noResp;

    public MsgBrokerListener(int prefetchSize, boolean forceResp, String redirectTo, boolean noResp) {
        this.prefetchSize = prefetchSize;
        this.forceResp = forceResp;
        this.redirectTo = redirectTo;
        this.noResp = noResp;
    }

    public void onMessage(Message message) {
         BytesMessage bInst = (BytesMessage)message;
         try {
             int len = (int)bInst.getBodyLength();
             byte[] rcv = new byte[len];
             bInst.readBytes(rcv);
             if( prefetchSize == 0 )
                 MsgBrokerWorkGroup.getInstance().execute(new MsgBrokerWorker(rcv, this.forceResp, this.redirectTo, this.noResp));
             else {
                 MsgBrokerWork work = new MsgBrokerWork(rcv, this.forceResp, this.redirectTo, this.noResp);
                 work.doWork();
             }

         }
         catch( JMSException e ) {
             logger.error(e.getMessage());
         }
         catch( Exception e ) {
             logger.error(e.getMessage());
         }

    }

}