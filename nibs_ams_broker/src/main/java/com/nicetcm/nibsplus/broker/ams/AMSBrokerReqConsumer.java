package com.nicetcm.nibsplus.broker.ams;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * AMSOutboundQ
 *
 *  AMSBroker를 통한 기기 전문요청 큐리스너 객체
 *
 *
 * @author  K.D.J
 * @since   2014.08.18
 */

import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.ams.rmi.AMSBrokerTimeoutException;
import com.nicetcm.nibsplus.broker.ams.services.ReqMsgHandler;
import com.nicetcm.nibsplus.broker.ams.services.AnsMsgHandler;

public class AMSBrokerReqConsumer extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerReqConsumer.class);

    private final BlockingQueue<AMSBrokerReqJob> listenQueue;

    private AMSBrokerData amsSafeData = new AMSBrokerData();
    private ReqMsgHandler reqMsg;
    private AnsMsgHandler ansMsg;
    private String        macNo;

    public AMSBrokerReqConsumer( BlockingQueue<AMSBrokerReqJob> queue, String macNo ) {
        listenQueue = queue;
        reqMsg = (ReqMsgHandler)AMSBrokerSpringMain.sprCtx.getBean("reqMsg");
        ansMsg = (AnsMsgHandler)AMSBrokerSpringMain.sprCtx.getBean("ansMsg");
        this.macNo = macNo;
        this.setName(String.format("ReqConsumer-%s", this.macNo));
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
                logger.debug("Thread [{}] is interrupted", this.getName() );
                break;
            }
            catch( Exception e ) {
                logger.error("Consumer raise error. {}", e.getMessage() );
                logger.error("              Class = {}", e.getClass().getName() );
                for( StackTraceElement se: e.getStackTrace() )
                    logger.error(se.toString());
            }
        }
    }

    private void acceptJob(AMSBrokerReqJob reqJob) throws Exception {
        try {
            AMSBrokerReqInfo reqInfo = new AMSBrokerReqInfo();

            reqMsg.reqMsgHandle( amsSafeData, reqJob, reqInfo );

            try {
                AMSBrokerClient client = AMSBrokerClient.getInstance( reqInfo.getDestIP(), reqInfo.getDestPort(), reqJob );
                ByteBuffer rslt = client.outboundCall( reqInfo.getMsg(), reqInfo.getStrm(), reqJob.getTimeOut() );

                ansMsg.ansMsgHandle( amsSafeData, reqJob, rslt, "9" );
                if( reqJob.getIsBlocking() )
                    reqJob.getAns().put(rslt);
            }
            catch( AMSBrokerTimeoutException te) {
                logger.debug("timeout error");
                ansMsg.ansMsgHandle( amsSafeData, reqJob, null, "3" );
                if( reqJob.getIsBlocking() ) {
                    ByteBuffer ret = ByteBuffer.allocateDirect(3);
                    ret.position(0);
                    ret.put("TMO".getBytes());
                    reqJob.getAns().put(ret);
                }
            }
            catch( Exception e ) {
                logger.debug(e.getMessage());
                ByteBuffer err = ByteBuffer.allocateDirect(e.getMessage().length());
                err.position(0);
                err.put(e.getMessage().getBytes());
                ansMsg.ansMsgHandle( amsSafeData, reqJob, err, "5" );
                if( reqJob.getIsBlocking() )
                    reqJob.getAns().put(ByteBuffer.allocateDirect(1));
                throw e;
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
