package com.nicetcm.nibsplus.broker.msg;

import java.io.FileOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerRMI;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerTimeoutException;
import com.nicetcm.nibsplus.broker.msg.services.OutMsgHandler;

public class MsgBrokerRMIImpl implements MsgBrokerRMI {

    public static final ConcurrentMap<String, BlockingQueue<byte[]>> rmiSyncAns = new ConcurrentHashMap<String, BlockingQueue<byte[]>>();
    public static final ConcurrentMap<String, byte[]> rmiOrigMsg = new ConcurrentHashMap<String, byte[]>();

    //private TMiscMapper      miscMap;
    private StoredProcMapper splMap;

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerRMIImpl.class);

    public MsgBrokerRMIImpl() {
    }

    public byte[] callBrokerSync(byte[] msg, int timeout) throws Exception {

        byte[] respMsg = null;
        int defTimeout = Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.response.timeout"));
        BlockingQueue<byte[]> waitQ = new LinkedBlockingQueue<byte[]>();
        MsgBrokerData msgThrdSafeData = new MsgBrokerData();
        logger.warn("RMI-I-MSG [{}]", new String(msg) );

        msgThrdSafeData.setNoOutData( false );

        MsgBrokerLib.BufferAndQName ret = MsgBrokerLib.allocAndFindSchemaName(msg, "O", true);
        logger.warn("QNm = {}", ret.QNm);

        //miscMap = (TMiscMapper)MsgBrokerSpringMain.sprCtx.getBean(TMiscMapper.class);
        splMap = (StoredProcMapper)MsgBrokerSpringMain.sprCtx.getBean(StoredProcMapper.class);

        MsgParser msgPsr = MsgParser.getInstance(ret.QNm).parseMessage(ret.buf);
        try {
            try {
                logger.warn("Getting trans_seq_no..");
                //msgPsr.setString("CM.trans_seq_no", miscMap.fGeTransSeqNo());
                TMisc misc = new TMisc();
                misc.setOrgCd     ( msgPsr.getString("CM.org_cd")     );
                misc.setCreateDate( msgPsr.getString("CM.trans_date") );
                splMap.spCmTransSeqNo( misc );
                msgPsr.setString("CM.trans_seq_no", misc.getTransSeqNo());
                logger.warn("trans_seq_no = {}", msgPsr.getString("CM.trans_seq_no"));

                if( msgPsr.getString("CM.trans_seq_no").length() == 0 )
                    throw new Exception("trans_seq_no is empty!!");

                try {
                    /*
                     *  Find and invoke method of instance of biz
                     */
                    OutMsgHandler bizBranch = (OutMsgHandler)MsgBrokerSpringMain
                            .sprCtx.getBean("out" + msgPsr.getString("CM.msg_type") + msgPsr.getString("CM.work_type"));
                    bizBranch.outMsgHandle( msgThrdSafeData, msgPsr );
                }
                catch( Exception e ) {
                    logger.debug("There's no spring bean : {}", "out" + msgPsr.getString("CM.msg_type") + msgPsr.getString("CM.work_type"));
                }

                if( !msgThrdSafeData.isNoOutData() ) {
                    rmiSyncAns.putIfAbsent(msgPsr.getString("CM.trans_seq_no"), waitQ);
                    rmiOrigMsg.putIfAbsent(msgPsr.getString("CM.trans_seq_no"), msg);
                    MsgBrokerManageRMIImpl.putRMIOrigMsgAvailability(msgPsr.getString("CM.trans_seq_no"), msg);
                    try {
                        MsgBrokerProducer.putDataToPrd(msgPsr);

                        /**
                         * TImeout 무시한다.
                        if( timeout > 0)
                            defTimeout = timeout;
                         */

                        respMsg = waitQ.poll(defTimeout, TimeUnit.SECONDS);
                        if( respMsg == null )
                            throw new MsgBrokerTimeoutException(String.format("Timeout [%d]", timeout));
                     }
                     catch (InterruptedException ie ) {
                         logger.debug("Interrupted..");
                     }
                     catch ( MsgBrokerTimeoutException re ) {
                         logger.debug("callBrokerSync Timeout error raised.. {}", re.getMessage());
                         throw re;
                     }
                }
                else {
                    if( msgThrdSafeData.getRespMsg() != null )
                        return msgThrdSafeData.getRespMsg();
                }
            }
            catch( Exception e ) {
                logger.error("Error raised. Message = {}", e.getMessage() );
                for( StackTraceElement se: e.getStackTrace() )
                    logger.error(se.toString());
                throw e;
            }
        }
        finally {
            MsgBrokerManageRMIImpl.removeRMIOrigMsgAvailability(msgPsr.getString("CM.trans_seq_no"));
            rmiOrigMsg.remove(msgPsr.getString("CM.trans_seq_no"));
            rmiSyncAns.remove(msgPsr.getString("CM.trans_seq_no"));
            msgPsr.clearMessage();
            logger.debug("remove element of rmiSyncAns");
        }
        return respMsg;
    }

    public void callBrokerAsync(byte[] msg) throws Exception {
        MsgBrokerData msgThrdSafeData = new MsgBrokerData();

        msgThrdSafeData.setNoOutData( false );

        MsgBrokerLib.BufferAndQName ret = MsgBrokerLib.allocAndFindSchemaName(msg, "O", true);
        logger.warn("QNm = {}", ret.QNm);

        //miscMap = (TMiscMapper)MsgBrokerSpringMain.sprCtx.getBean(TMiscMapper.class);
        splMap = (StoredProcMapper)MsgBrokerSpringMain.sprCtx.getBean(StoredProcMapper.class);

        MsgParser msgPsr = MsgParser.getInstance(ret.QNm).parseMessage(ret.buf);
        try {
            try {
                //msgPsr.setString("CM.trans_seq_no", miscMap.fGeTransSeqNo());
                TMisc misc = new TMisc();
                misc.setOrgCd     ( msgPsr.getString("CM.org_cd")     );
                misc.setCreateDate( msgPsr.getString("CM.trans_date") );
                splMap.spCmTransSeqNo( misc );
                msgPsr.setString("CM.trans_seq_no", misc.getTransSeqNo());
                logger.debug("trans_seq_no = {}", msgPsr.getString("CM.trans_seq_no"));

                if( msgPsr.getString("CM.trans_seq_no").length() == 0 )
                    throw new Exception("trans_seq_no is empty!!");

                try {
                    /*
                     *  Find and invoke method of instance of biz
                     */
                    OutMsgHandler bizBranch = (OutMsgHandler)MsgBrokerSpringMain
                            .sprCtx.getBean("out" + msgPsr.getString("CM.msg_type") + msgPsr.getString("CM.work_type"));
                    bizBranch.outMsgHandle( msgThrdSafeData, msgPsr );
                }
                catch( Exception e ) {
                    logger.debug("There's no spring bean : {}", "out" + msgPsr.getString("CM.msg_type") + msgPsr.getString("CM.work_type"));
                }

                if( !msgThrdSafeData.isNoOutData() ) {
                    MsgBrokerProducer.putDataToPrd(msgPsr);
                }
            }
            catch( Exception e ) {
                logger.error("Error raised. Message = {}", e.getMessage() );
                for( StackTraceElement se: e.getStackTrace() )
                    logger.error(se.toString());
                throw e;
            }
        }
        finally {
            msgPsr.clearMessage();
        }
    }
}
