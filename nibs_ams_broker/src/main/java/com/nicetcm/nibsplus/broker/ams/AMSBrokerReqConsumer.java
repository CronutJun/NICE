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

import java.io.File;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.io.FileUtils;
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
        this.setName(String.format("<T>%s-C-%s:%s", this.macNo, this.getId(), AMSBrokerMain.serverNo));
    }

    public void run()  {

        logger.warn("Listen Start");
        AMSBrokerReqJob reqJob = null;

        for( ; ; ) {
            try {
                logger.warn("before take");
                reqJob = listenQueue.take();
                logger.warn("after take");
                acceptJob( reqJob );
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warn("Thread [{}] is interrupted", this.getName() );
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
            logger.warn("acceptJob");
            AMSBrokerReqInfo reqInfo = new AMSBrokerReqInfo();

            reqJob.setReceiveAns(false);
            reqMsg.reqMsgHandle( amsSafeData, reqJob, reqInfo );
            logger.warn("after reqMsgHandle, reqJob timeout = {}", reqJob.getTimeOut() );

            try {
                AMSBrokerClient client = AMSBrokerClient.getInstance( reqInfo.getDestIP(), reqInfo.getDestPort(), reqJob );
                ByteBuffer rslt = client.outboundCall( reqInfo.getMsg(), reqInfo.getStrm(), reqJob.getTimeOut() );

                if( !reqJob.isDownMD5Result() ) {
                    if( reqJob.getDownMD5RetryCount() < AMSBrokerLib.FILE_MD5_RETRY_COUNT ) {
                        String errMsg = "";
                        if( reqJob.getDownMD5RetryCount() < AMSBrokerLib.FILE_MD5_RETRY_COUNT ) {
                            reqJob.addDownMD5RetryCount();
                            errMsg = String.format("다운로드 MD5 해쉬 불일치. 재시도 [%d]회", reqJob.getDownMD5RetryCount());
                        }
                        else {
                            errMsg = "다운로드 MD5 해쉬 불일치. 송신종료.";
                        }
                        ByteBuffer err = ByteBuffer.allocateDirect(errMsg.getBytes().length);
                        err.position(0);
                        err.put(errMsg.getBytes());
                        ansMsg.ansMsgHandle( amsSafeData, reqJob, err, "5" );
                        if( reqJob.getDownMD5RetryCount() < AMSBrokerLib.FILE_MD5_RETRY_COUNT ) {
                            reqJob.getDownComplete().reset();
                            reqJob.setDownMD5Result(true);
                            acceptJob( reqJob );
                            return;
                        }
                    }
                }

                if( reqJob.isUpMD5Result() )
                    ansMsg.ansMsgHandle( amsSafeData, reqJob, rslt, "9" );
                else if( reqJob.getUpMD5RetryCount() < AMSBrokerLib.FILE_MD5_RETRY_COUNT ) {
                    String errMsg = "";
                    if( reqJob.getUpMD5RetryCount() < AMSBrokerLib.FILE_MD5_RETRY_COUNT ) {
                        reqJob.addUpMD5RetryCount();
                        errMsg = String.format("업로드 MD5 해쉬 불일치. 재시도 [%d]회", reqJob.getUpMD5RetryCount());
                    }
                    else {
                        errMsg = "업로드 MD5 해쉬 불일치. 송신종료.";
                    }
                    ByteBuffer err = ByteBuffer.allocateDirect(errMsg.getBytes().length);
                    err.position(0);
                    err.put(errMsg.getBytes());
                    ansMsg.ansMsgHandle( amsSafeData, reqJob, err, "5" );
                    if( reqJob.getUpMD5RetryCount() < AMSBrokerLib.FILE_MD5_RETRY_COUNT ) {
                        reqJob.getUpComplete().reset();
                        reqJob.setTempFileName(null);
                        reqJob.setTempFilePos(0);
                        reqJob.setUpMD5Result(true);
                        acceptJob( reqJob );
                        return;
                    }
                }
                if( reqJob.getIsBlocking() )
                    reqJob.getAns().put(rslt);
            }
            catch( AMSBrokerTimeoutException te) {
                logger.warn("timeout error");
                String errMsg = "";
                if( !reqJob.isReceiveAns() ) {
                    if( reqJob.getDownFileRetryCount() < AMSBrokerLib.FILE_RETRY_COUNT ) {
                        reqJob.addDownFileRetryCount();
                        errMsg = String.format("다운로드 시간초과 발생. 재시도 [%d]회", reqJob.getDownFileRetryCount());
                    }
                    else {
                        errMsg = "다운로드 시간초과 발생. 송신종료.";
                    }
                }
                else {
                    if( reqJob.getUpFileRetryCount() < AMSBrokerLib.FILE_RETRY_COUNT ) {
                        reqJob.addUpFileRetryCount();
                        errMsg = String.format("업로드 시간초과 발생. 재시도 [%d]회", reqJob.getUpFileRetryCount());
                    }
                    else {
                        errMsg = "업로드 시간초과 발생. 송신종료.";
                    }
                }
                ByteBuffer err = ByteBuffer.allocateDirect(errMsg.getBytes().length);
                err.position(0);
                err.put(errMsg.getBytes());
                ansMsg.ansMsgHandle( amsSafeData, reqJob, err, "3" );
                /** 재 시도 */
                if( !reqJob.isReceiveAns() ) {
                    if( reqJob.getDownFileRetryCount() <= AMSBrokerLib.FILE_RETRY_COUNT ) {
                        reqJob.getDownComplete().reset();
                        acceptJob( reqJob );
                        return;
                    }
                }
                else {
                    if( reqJob.getUpFileRetryCount() <= AMSBrokerLib.FILE_RETRY_COUNT ) {
                        reqJob.getUpComplete().reset();
                        File tg = new File(reqJob.getTempFileName());
                        if( tg.exists() ) {
                            reqJob.setTempFilePos(FileUtils.sizeOf(tg));
                        }
                        else {
                            reqJob.setTempFilePos(0);
                        }
                        acceptJob( reqJob );
                        return;
                    }
                }
                if( reqJob.getIsBlocking() ) {
                    ByteBuffer ret = ByteBuffer.allocateDirect(3);
                    ret.position(0);
                    ret.put("TMO".getBytes());
                    reqJob.getAns().put(ret);
                }
                File tg = new File(reqJob.getTempFileName());
                if( tg.exists() ) {
                    if( tg.delete() )
                        logger.warn("Successful delete temporary file");
                }
            }
            catch( Exception e ) {
                logger.warn(e.getMessage());
                for( StackTraceElement se: e.getStackTrace() )
                    logger.warn(se.toString());
                ByteBuffer err = ByteBuffer.allocateDirect(e.getMessage().length());
                err.position(0);
                err.put(e.getMessage().getBytes());
                ansMsg.ansMsgHandle( amsSafeData, reqJob, err, "5" );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            if( reqJob.getIsBlocking() )
                reqJob.getAns().put(ByteBuffer.allocateDirect(1));
            File tg = new File(reqJob.getTempFileName());
            if( tg.exists() ) {
                if( tg.delete() )
                    logger.warn("Successful delete temporary file");
            }
            throw e;
        }
    }

}
