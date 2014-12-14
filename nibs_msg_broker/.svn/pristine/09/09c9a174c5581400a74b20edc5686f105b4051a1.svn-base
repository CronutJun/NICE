package com.nicetcm.nibsplus.broker.msg;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgBrokerBlockingWorker extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerBlockingWorker.class);

    private BlockingQueue<byte[]> iq;
    private boolean forceResp;
    private String   redirectTo;
    private boolean noResp;

    public MsgBrokerBlockingWorker( boolean forceResp, String redirectTo, boolean noResp ) {
        iq = new LinkedBlockingQueue<byte[]>(1);

        this.forceResp  = forceResp;
        this.redirectTo = redirectTo;
        this.noResp     = noResp;
    }

    public BlockingQueue<byte []> getInQueue() {
        return iq;
    }

    public void stopWork() {
        this.interrupt();
    }

    @Override
    public void run() {
        for(;;) {
            try {
                byte[] msg = iq.take();
                new MsgBrokerWork( msg, forceResp, redirectTo, noResp ).doWork();
            }
            catch( InterruptedException ie) {
                logger.warn("Blocking Worker is interrupted");
                break;
            }
        }
    }
}
