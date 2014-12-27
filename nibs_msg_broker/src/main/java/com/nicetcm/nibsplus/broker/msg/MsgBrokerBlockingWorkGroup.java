package com.nicetcm.nibsplus.broker.msg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;

public class MsgBrokerBlockingWorkGroup {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerBlockingWorkGroup.class);

    private List<MsgBrokerBlockingWorker> blockThreads = new ArrayList<MsgBrokerBlockingWorker>();
    private boolean forceResp;
    private String  redirectTo;
    private boolean noResp;
    private int     blockCount;

    public MsgBrokerBlockingWorkGroup( boolean forceResp, String redirectTo, boolean noResp, int blockCount ) {

        MsgBrokerBlockingWorker th;

        this.forceResp  = forceResp;
        this.redirectTo = redirectTo;
        this.noResp     = noResp;
        this.blockCount = blockCount;

        /** KDJ
        threadStart();
        */
    }

    public void putMsg( int index, byte[] msg ) throws Exception {
        try {
            int lockTimeOut = Integer.parseInt(MsgCommon.msgProps.getProperty("db_lock_timeout", "25")) + 5;
            blockThreads.get(index).getInQueue().offer(msg, lockTimeOut, TimeUnit.SECONDS);
        }
        catch( InterruptedException ie ) {
            logger.warn("BlockingWorkGroup putMsg is interrupted");
            throw new Exception("BlockingWorkGroup putMsg is interrupted");
        }
    }

    public List<MsgBrokerBlockingWorker> getBlockingThreads() {
        return blockThreads;
    }

    public void threadStart() {

        threadStop();

        for( int i=0; i < blockCount; i++ ) {
            MsgBrokerBlockingWorker th =  new MsgBrokerBlockingWorker(forceResp, redirectTo, noResp);
            blockThreads.add( th );
            th.start();
        }
    }

    public void threadStop() {
        while( !blockThreads.isEmpty() ) {
            blockThreads.get(0).stopWork();
            logger.warn("parallel threads {} is stopped", blockThreads.get(0).getName() );
            blockThreads.remove(0);
        }
    }

}
