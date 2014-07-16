package com.nicetcm.nibsplus.broker.msg;

import java.nio.ByteBuffer;
import javax.jms.BytesMessage;

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
            try {
                try {
                    /*
                     *  Find and invoke method of instance of biz 
                     */
                    InMsgHandler bizBranch = (InMsgHandler)MsgBrokerSpringMain
                            .sprCtx.getBean("in" + msgPsr.getString("CM.msg_type") + msgPsr.getString("CM.work_type"));
                    bizBranch.inMsgHandle( msgPsr );
                    
                    /*
                     * 요청전문에 대해서만 Ack/Nak 전송
                     */
                    if( msgPsr.getString("CM.msg_type").substring(2, 4).equals("00") ) {
                        /*
                         * Respond Ack Normal
                         */
                        RespAckNakHandler resp = (RespAckNakHandler)MsgBrokerSpringMain
                                .sprCtx.getBean("respAckNak");
                        resp.procAckNak( msgPsr, 0 );
                    
                        MsgBrokerProducer prd = MsgBrokerProducer.producers.get( "ATMS." + msgPsr.getString( "CM.org_cd" ) + ".H.Q" );
                        BytesMessage respData = prd.getBytesMessage();

                        byte[] read = new byte[buf.limit()];
                        buf.position(0);
                        buf.get(read);
                        logger.info("Response Data = {}", new String(read) );
                        respData.writeBytes(read);
                        prd.produce( respData );
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
                        resp.procAckNak( msgPsr, me.getErrorCode() );
                    
                        MsgBrokerProducer prd = MsgBrokerProducer.producers.get( "ATMS." + msgPsr.getString( "CM.org_cd" ) + ".H.Q" );
                        BytesMessage respData = prd.getBytesMessage();

                        byte[] read = new byte[buf.limit()];
                        buf.position(0);
                        buf.get(read);
                        logger.info("Response Data = {}", new String(read) );
                        respData.writeBytes(read);
                        prd.produce( respData );
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
