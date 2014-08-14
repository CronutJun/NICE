package com.nicetcm.nibsplus.broker.ams;

import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.rmi.AMSBrokerTimeoutException;
import com.nicetcm.nibsplus.broker.ams.services.ReqMsgHandler;

public class AMSBrokerReqConsumer extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerReqConsumer.class);

    private final BlockingQueue<AMSBrokerReqJob> listenQueue;
    private AMSBrokerData amsSafeData = new AMSBrokerData();

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
                logger.error("Consumer raise error. {}", e.getMessage() );
            }
        }
    }

    private void acceptJob(AMSBrokerReqJob reqJob) throws Exception {
        try {
            AMSBrokerReqInfo reqInfo = new AMSBrokerReqInfo();

            ReqMsgHandler reqMsg = (ReqMsgHandler)AMSBrokerSpringMain.sprCtx.getBean("reqMsg");
            reqMsg.reqMsgHandle( amsSafeData, reqJob, reqInfo );

            AMSBrokerClient client = new AMSBrokerClient( reqInfo.getDestIP(), reqInfo.getDestPort() );
            ByteBuffer rslt = client.outboundCall( reqInfo.getMsg(), null, reqJob.getTimeOut() );
            if( reqJob.getIsBlocking() )
                reqJob.getAns().put(rslt);
        }
        catch( AMSBrokerTimeoutException te) {
            logger.debug("timeout error");
            if( reqJob.getIsBlocking() ) {
                ByteBuffer ret = ByteBuffer.allocateDirect(3);
                ret.position(0);
                ret.put("TMO".getBytes());
                reqJob.getAns().put(ret);
            }
        }
        catch( Exception e ) {
            logger.debug(e.getMessage());
            if( reqJob.getIsBlocking() )
                reqJob.getAns().put(ByteBuffer.allocateDirect(1));
            throw e;
        }
    }

}
