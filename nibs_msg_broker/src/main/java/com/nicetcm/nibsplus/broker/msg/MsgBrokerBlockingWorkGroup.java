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

    public MsgBrokerBlockingWorkGroup( boolean forceResp, String redirectTo, boolean noResp, int BlockCount ) {
        MsgBrokerBlockingWorker th;

        for( int i=0; i < BlockCount; i++ ) {

            th =  new MsgBrokerBlockingWorker(forceResp, redirectTo, noResp);
            blockThreads.add( th );
            th.start();
        }
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

}
