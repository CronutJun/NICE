package com.nicetcm.nibsplus.broker.msg;

import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.msg.services.CommonPack;
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
        byte[] bOrgCd   = new byte[3];
        byte[] bMsgType = new byte[4];
        byte[] bWrkType = new byte[4];
        String inQNm;
        boolean skipDBProc = false;

        try {
            buf = ByteBuffer.allocateDirect(msg.length);
            buf.put(msg);
            buf.position(0);
            buf.get(bOrgCd);
            buf.position(MsgBrokerConst.MSG_TYPE_OFS);
            buf.get(bMsgType);
            buf.get(bWrkType);
            buf.position(0);

            Thread.currentThread().setName(String.format("<T>%s-%s%s-%s", new String(bOrgCd).trim(), new String(bMsgType).trim(), new String(bWrkType).trim(), Thread.currentThread().getId()));
            logger.info("Income message  size : [{}], data : [{}]", msg.length, new String(msg));
            /*
             * 응답 전문의 경우에 스키마 파일은 원본 요청 전문에 해당하는 스키마를 읽도록 한다.
             */
            if( bMsgType[2] == '1')
                bMsgType[2] = '0';

            inQNm = MsgCommon.msgProps.getProperty("schema_path") + new String(bMsgType).trim() + new String(bWrkType).trim() + ".json";
            logger.debug("inQNm = " + inQNm);

            msgPsr = MsgParser.getInstance(inQNm).parseMessage(buf);
            logger.debug("Parse OK");
            msgThrdSafeData = new MsgBrokerData();
            try {
                try {
                    /*
                     * 응답전문 수신 일 경우에는 오류코드 체크
                     */
                    if( msgPsr.getString("CM.msg_type").substring(2, 4).equals("10") ) {
                        if( !msgPsr.getString("CM.format_type").equals(MsgBrokerConst.EM_CODE) ) {

                            CommonPack comPack = MsgBrokerSpringMain.sprCtx.getBean(CommonPack.class);

                            if( comPack.getError( msgPsr.getString("CM.ret_cd_src"), msgPsr.getString("CM.org_cd"), msgPsr.getString("CM.ret_cd") ) < 0 ) {
                                /*
                                 * 부산은행 현금인도(장전) 통보의경우는 은행 오류 코드로 들어오더라도 DB 저장 한다.
                                 */
                                if( msgPsr.getString("CM.msg_type").equals(MsgBrokerConst.CM_ANS)
                                &&  msgPsr.getString("CM.work_type").equals("1133") ) {
                                    if( msgPsr.getString("CM.org_cd").equals(MsgBrokerConst.BU_CODE)
                                    ||  msgPsr.getString("CM.org_cd").equals(MsgBrokerConst.BUATMS_CODE) ) {
                                        logger.debug("오류[{}], DB저장 안함", msgPsr.getString("CM.ret_cd_src") );
                                    }
                                }
                                else {
                                    /*
                                     * 오류가 있는경우는 Update 하지 않고 AP로 응답 보냄.
                                     * 응답전문일 경우는 ap로 전송하도록
                                     */
                                    skipDBProc = true;
                                    logger.debug( "오류[{}], DB저장 안함", msgPsr.getString("CM.ret_cd_src") );
                                }
                            }
                        }
                        if( !skipDBProc ) {
                            /*
                             *  Find and invoke method of instance of biz
                             */
                            InMsgHandler bizBranch = (InMsgHandler)MsgBrokerSpringMain
                                    .sprCtx.getBean("in" + msgPsr.getString("CM.msg_type") + msgPsr.getString("CM.work_type"));
                            bizBranch.inMsgHandle( msgThrdSafeData, msgPsr );
                        }
                    }
                    else {
                        /*
                         *  Find and invoke method of instance of biz
                         */
                        InMsgHandler bizBranch = (InMsgHandler)MsgBrokerSpringMain
                                .sprCtx.getBean("in" + msgPsr.getString("CM.msg_type") + msgPsr.getString("CM.work_type"));
                        bizBranch.inMsgHandle( msgThrdSafeData, msgPsr );
                    }

                    /*
                     * 요청전문에 대해서만 Ack/Nak 전송
                     */
                    if( msgPsr.getString("CM.msg_type").substring(2, 4).equals(MsgBrokerConst.REQ_CODE) ) {
                        /*
                         * Respond Ack Normal
                         */
                        RespAckNakHandler resp = (RespAckNakHandler)MsgBrokerSpringMain
                                .sprCtx.getBean("respAckNak");
                        resp.procAckNak( msgThrdSafeData, msgPsr, 0 );

                        MsgBrokerProducer.putDataToPrd(msgPsr, new String(bOrgCd));
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
                    if( msgPsr.getString("CM.msg_type").substring(2, 4).equals(MsgBrokerConst.REQ_CODE) ) {
                        if( me.getErrorCode() != -99 ) {
                            /*
                             * Respond Nak Error
                             */
                            RespAckNakHandler resp = (RespAckNakHandler)MsgBrokerSpringMain
                                .sprCtx.getBean("respAckNak");
                            resp.procAckNak( msgThrdSafeData, msgPsr, me.getErrorCode() );

                            MsgBrokerProducer.putDataToPrd(msgPsr, new String(bOrgCd));
                        }
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
                    /*
                     * 요청전문에 대해서만 Ack/Nak 전송
                     */
                    if( msgPsr.getString("CM.msg_type").substring(2, 4).equals(MsgBrokerConst.REQ_CODE) ) {
                        /*
                         * Respond Nak Error
                         */
                        RespAckNakHandler resp = (RespAckNakHandler)MsgBrokerSpringMain
                            .sprCtx.getBean("respAckNak");
                        resp.procAckNak( msgThrdSafeData, msgPsr, -1 );

                        MsgBrokerProducer.putDataToPrd(msgPsr, new String(bOrgCd));
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
