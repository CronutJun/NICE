package com.nicetcm.nibsplus.broker.ams;

import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;

public class AMSBrokerReqConsumer extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerReqConsumer.class);

    private final BlockingQueue<AMSBrokerReqJob> listenQueue;

    public AMSBrokerReqConsumer( BlockingQueue<AMSBrokerReqJob> queue ) {
        listenQueue = queue;
    }

    public void run()  {

        logger.debug("Listen Start");
        AMSBrokerReqJob reqJob = null;

        for( ; ; ) {
            try {
                logger.debug("before take");
                reqJob = listenQueue.take();
                logger.debug("after take");
                acceptJob( reqJob );
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            catch( Exception e ) {
                logger.error("Consumer raise error. {}", e.getLocalizedMessage() );
            }
        }
    }

    private void acceptJob(AMSBrokerReqJob reqJob) throws Exception {
        try {
            AMSBrokerClient client = new AMSBrokerClient("10.3.28.114", 33001 );

            ByteBuffer msg = ByteBuffer.allocateDirect(MsgCommon.READ_BUF_SIZE);
            MsgParser msgPsr = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path") + "21002002.json").newMessage(msg);
            msg.position(0);
            msgPsr.setString("CM._AOCMsgCode",     "2100")
                  .setString("CM._AOCServiceCode", "2002")
                  .setString("CM._AOCMsgSendDate", "20140812")
                  .setString("CM._AOCMsgSendTime", "172600")
                  .setInt   ("CM._AOCMsgLen", msgPsr.getMessageLength() - 9)
                  .syncMessage();
            logger.debug("Message Length = "  + msgPsr.getMessageLength());
            logger.debug("Last Position = "   + msgPsr.lastPosition());
            msg.limit(msgPsr.lastPosition());
            byte[] read = new byte[msg.limit()];
            msg.position(0);
            msg.get(read);
            logger.debug(new String(read));
            ByteBuffer rslt = client.outboundCall(msg, null, 5);
            reqJob.getAns().put(rslt);
        }
        catch( Exception e ) {
            logger.debug(e.getLocalizedMessage());
            throw e;
        }
    }

}
