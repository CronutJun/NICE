package com.nicetcm.nibsplus.broker.msg;

import java.io.*;
import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicetcm.nibsplus.broker.common.*;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerRMI;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerTimeoutException;

public class MsgBrokerRMIImpl implements MsgBrokerRMI {

    public static final ConcurrentMap<String, BlockingQueue<byte[]>> rmiSyncAns = new ConcurrentHashMap<String, BlockingQueue<byte[]>>();

    private TMiscMapper miscMap;

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerRMIImpl.class);
    private FileOutputStream fOut;

    public MsgBrokerRMIImpl() {
    }

    public byte[] callBrokerSync(byte[] msg, int timeout) throws Exception {

        byte[] respMsg = null;
        int defTimeout = Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.response.timeout"));
        BlockingQueue<byte[]> waitQ = new LinkedBlockingQueue<byte[]>();

        MsgBrokerLib.BufferAndQName ret = MsgBrokerLib.allocAndFindSchemaName(msg, "O", true);
        logger.debug("QNm = {}", ret.QNm);

        miscMap = (TMiscMapper)MsgBrokerSpringMain.sprCtx.getBean(TMiscMapper.class);

        MsgParser msgPsr = MsgParser.getInstance(ret.QNm).parseMessage(ret.buf);
        try {
            msgPsr.setString("CM.trans_seq_no", miscMap.fGeTransSeqNo());
            logger.debug("trans_seq_no = {}", msgPsr.getString("CM.trans_seq_no"));

            if( msgPsr.getString("CM.trans_seq_no").length() == 0 )
                throw new Exception("trans_seq_no is empty!!");

            rmiSyncAns.putIfAbsent(msgPsr.getString("CM.trans_seq_no"), waitQ);
            try {
                MsgBrokerProducer.putDataToPrd(msgPsr);

                if( timeout > 0)
                    defTimeout = timeout;

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
             catch ( Exception e) {
                 logger.debug("callBrokerSync error raised.. {}", e.getMessage());
             }
        }
        finally {
            rmiSyncAns.remove(msgPsr.getString("CM.trans_seq_no"));
            msgPsr.clearMessage();
            logger.debug("remove element of rmiSyncAns");
        }
        return respMsg;
    }

    public void callBrokerAsync(byte[] msg) throws Exception {

        MsgBrokerLib.BufferAndQName ret = MsgBrokerLib.allocAndFindSchemaName(msg, "O", true);
        logger.debug("QNm = {}", ret.QNm);

        miscMap = (TMiscMapper)MsgBrokerSpringMain.sprCtx.getBean(TMiscMapper.class);

        MsgParser msgPsr = MsgParser.getInstance(ret.QNm).parseMessage(ret.buf);
        try {
            msgPsr.setString("CM.trans_seq_no", miscMap.fGeTransSeqNo());
            logger.debug("trans_seq_no = {}", msgPsr.getString("CM.trans_seq_no"));

            if( msgPsr.getString("CM.trans_seq_no").length() == 0 )
                throw new Exception("trans_seq_no is empty!!");

            MsgBrokerProducer.putDataToPrd(msgPsr);
        }
        finally {
            msgPsr.clearMessage();
        }
    }
}
