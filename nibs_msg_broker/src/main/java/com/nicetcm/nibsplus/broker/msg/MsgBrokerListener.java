package com.nicetcm.nibsplus.broker.msg;

import javax.jms.Message;
import javax.jms.BytesMessage;
import javax.jms.MessageListener;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgBrokerListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerListener.class);

    private MsgBrokerBlockingWorkGroup blkWrkGrp;
    private int prefetchSize;
    private int parallels;
    private int msgParaPos;
    private int msgParaLen;
    private boolean forceResp;
    private String redirectTo;
    private boolean noResp;

    public MsgBrokerListener(int prefetchSize, int parallels, int msgParaPos, int msgParaLen, boolean forceResp, String redirectTo, boolean noResp) {

        blkWrkGrp = new MsgBrokerBlockingWorkGroup( forceResp, redirectTo, noResp, parallels );

        this.prefetchSize = prefetchSize;
        this.parallels = parallels;
        this.msgParaPos = msgParaPos;
        this.msgParaLen = msgParaLen;
        this.forceResp = forceResp;
        this.redirectTo = redirectTo;
        this.noResp = noResp;

    }

    public MsgBrokerBlockingWorkGroup getBlockingWorkGroup() {
        return blkWrkGrp;
    }

    public void onMessage(Message message) {
         BytesMessage bInst = (BytesMessage)message;
         try {
             int len = (int)bInst.getBodyLength();
             byte[] rcv = new byte[len];
             bInst.readBytes(rcv);
             if( prefetchSize == 0 && parallels == 0 )
                 MsgBrokerWorkGroup.getInstance().execute(new MsgBrokerWorker(rcv, this.forceResp, this.redirectTo, this.noResp));
             else if( parallels > 0 ) {
                 try {
                     if( msgParaPos == 0 )
                         throw new Exception("msg_para_pos is zero");
                     if( msgParaLen == 0 )
                         throw new Exception("msg_para_len is zero");
                     byte[] bIdxInfo = new byte[msgParaLen];
                     System.arraycopy(rcv, msgParaPos-1, bIdxInfo, 0, msgParaLen);

                     int iIdxInfo = 0;
                     for( byte nibble: bIdxInfo ) {
                         iIdxInfo += nibble;
                     }

                     iIdxInfo = iIdxInfo % parallels;
                     blkWrkGrp.putMsg(iIdxInfo, rcv);
                 }
                 catch( Exception e ) {
                     logger.warn("something is wrong in information of parallelism : []", e.getMessage());
                     MsgBrokerWork work = new MsgBrokerWork(rcv, this.forceResp, this.redirectTo, this.noResp);
                     work.doWork();
                 }
             }
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