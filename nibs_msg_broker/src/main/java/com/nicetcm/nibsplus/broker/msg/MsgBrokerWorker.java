package com.nicetcm.nibsplus.broker.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgBrokerWorker implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerWorker.class);

    private MsgBrokerWork workThread;

    public MsgBrokerWorker( byte[] msg, boolean forceResp, String redirectTo, boolean noResp ) {
        workThread = new MsgBrokerWork( msg, forceResp, redirectTo, noResp );
    }

    public void run() {
        workThread.doWork();
    }

}
