package com.nicetcm.nibsplus.broker.msg;

import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgBrokerWorkGroup {

    private static final ExecutorService svc = Executors.newCachedThreadPool();
    private static MsgBrokerWorkGroup wrkGrp = null;

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerWorkGroup.class);

    private MsgBrokerWorkGroup() {

    }

    public static MsgBrokerWorkGroup getInstance() throws Exception {
        if( wrkGrp == null ) {
            wrkGrp = new MsgBrokerWorkGroup();
        }
        return wrkGrp;
    }

    public void execute(MsgBrokerWorker worker) throws Exception {
        svc.execute(worker);
    }

    public void shutdown() throws Exception {
        svc.shutdown();
        while (!svc.awaitTermination(2, TimeUnit.SECONDS)) {
            logger.warn("Awaiting completion of threads. Thread count = {}", Thread.activeCount() );
        }
    }

    public void shutdownNow() throws Exception {
        svc.shutdownNow();
    }
}
