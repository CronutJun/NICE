package com.nicetcm.nibsplus.broker.msg;

import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.msg.services.InMsgHandler;
import com.nicetcm.nibsplus.broker.msg.services.RespAckNakHandler;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;

public class MsgBrokerWorker implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerWorker.class);

    private byte[] msg;
    private ByteBuffer buf;
    private MsgParser msgPsr;
    private MsgBrokerData msgThrdSafeData;

    public MsgBrokerWorker( byte[] msg ) {
        this.msg = msg;
    }

    public void run() {
        byte[] bMsgType = new byte[4];
        byte[] bWrkType = new byte[4];
        String inQNm;

        try {
            logger.debug("Income message  size = " + msg.length + ", data = "+ new String(msg));
            buf = ByteBuffer.allocateDirect(msg.length);
            buf.put(msg);
            buf.position(51);
            buf.get(bMsgType);
            buf.get(bWrkType);
            buf.position(0);

            /*
             * 응답 전문의 경우에 스키마 파일은 원본 요청 전문에 해당하는 스키마를 읽도록 한다.
             */
            if( bMsgType[2] == '1')
                bMsgType[2] = '0';

            inQNm = MsgCommon.msgProps.getProperty("schema_path") + new String(bMsgType) + new String(bWrkType) + ".json";
            logger.debug("inQNm = " + inQNm);

            msgPsr = MsgParser.getInstance(inQNm).parseMessage(buf);
            logger.debug("Parse OK");
            msgThrdSafeData = new MsgBrokerData();
            try {
                try {
                    /*
                     *  Find and invoke method of instance of biz
                     */
                    InMsgHandler bizBranch = (InMsgHandler)MsgBrokerSpringMain
                            .sprCtx.getBean("in" + msgPsr.getString("CM.msg_type") + msgPsr.getString("CM.work_type"));
                    bizBranch.inMsgHandle( msgThrdSafeData, msgPsr );

                    /*
                     * 요청전문에 대해서만 Ack/Nak 전송
                     */
                    if( msgPsr.getString("CM.msg_type").substring(2, 4).equals("00") ) {
                        /*
                         * Respond Ack Normal
                         */
                        RespAckNakHandler resp = (RespAckNakHandler)MsgBrokerSpringMain
                                .sprCtx.getBean("respAckNak");
                        resp.procAckNak( msgThrdSafeData, msgPsr, 0 );

                        MsgBrokerProducer.putDataToPrd(msgPsr);
                    }
                    /*
                     * nibsplus 또는 기타 AP요청의 응답인지
                     * MsgBrokerImpl의 rmiSyncAns Map에서 전문번호로 찾아
                     * 존재하면 응답처리 한다.
                     */
                    else {
                        BlockingQueue<byte[]> waitQ = MsgBrokerRMIImpl.rmiSyncAns.get(msgPsr.getString("CM.trans_seq_no"));
                        if( waitQ != null ) {
                            waitQ.put( msg );
                        }
                    }
                }
                catch (MsgBrokerException me) {
                    /*
                     * 요청전문에 대해서만 Ack/Nak 전송
                     */
                    if( msgPsr.getString("CM.msg_type").substring(2, 4).equals("00") ) {
                        /*
                         * Respond Nak Error
                         */
                        RespAckNakHandler resp = (RespAckNakHandler)MsgBrokerSpringMain
                            .sprCtx.getBean("respAckNak");
                        resp.procAckNak( msgThrdSafeData, msgPsr, me.getErrorCode() );

                        MsgBrokerProducer.putDataToPrd(msgPsr);
                    }
                    /*
                     * nibsplus 또는 기타 AP요청의 응답인지
                     * MsgBrokerImpl의 rmiSyncAns Map에서 전문번호로 찾아
                     * 존재하면 응답처리 한다.
                     */
                    else {
                        BlockingQueue<byte[]> waitQ = MsgBrokerRMIImpl.rmiSyncAns.get(msgPsr.getString("CM.trans_seq_no"));
                        if( waitQ != null ) {
                            waitQ.put( msg );
                        }
                    }
                }
                catch (Exception e) {
                    throw e;
                }
            }
            finally {
                msgPsr.clearMessage();
            }
        }
        catch (Exception e) {
           logger.error("Error raised. Message = {}", e.getMessage() );
           logger.error("              Class = {}", e.getClass().getName() );
           for( StackTraceElement se: e.getStackTrace() )
               logger.error(se.toString());

        }
    }

}
