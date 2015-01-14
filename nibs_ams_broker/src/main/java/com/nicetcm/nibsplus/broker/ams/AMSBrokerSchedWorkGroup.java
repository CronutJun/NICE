package com.nicetcm.nibsplus.broker.ams;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * AMSBrokerSchedWorkGroup
 *
 *  AMS 스케쥴잡 관리 - 최대 동시 요청갯수 조절
 *
 *
 * @author  K.D.J
 * @since   2014.08.28
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerSchedWorker;

public class AMSBrokerSchedWorkGroup {

    private static class BlockingQueuePut implements RejectedExecutionHandler {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            }
            catch (InterruptedException ie) {
                throw new RejectedExecutionException(ie);
            }
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerSchedWorkGroup.class);

//    private static final ExecutorService svc = new ThreadPoolExecutor(0,
//                                                      Integer.parseInt(MsgCommon.msgProps.getProperty("schedule.max.concurrent.thread",
//                                                                                                      AMSBrokerConst.MAX_CONCURRENT_THREAD)),
//                                                      60L, TimeUnit.SECONDS,
//                                                      new SynchronousQueue<Runnable>(),
//                                                      new BlockingQueuePut());
    private static final ExecutorService svc = Executors.newFixedThreadPool(Integer.parseInt(MsgCommon.msgProps.getProperty("schedule.max.concurrent.thread",
                                                                                                      AMSBrokerConst.MAX_CONCURRENT_THREAD)));

    private static AMSBrokerSchedWorkGroup schedGrp = null;

    private AMSBrokerSchedWorkGroup() {

    }

    public static AMSBrokerSchedWorkGroup getInstance() throws Exception {
        if( schedGrp == null )
            schedGrp = new AMSBrokerSchedWorkGroup();

        return schedGrp;
    }

    public void execute(AMSBrokerSchedWorker worker) throws Exception {
        svc.execute(worker);
    }

    public void shutdown() throws Exception {
        svc.shutdown();
    }

    public void shutdownNow() throws Exception {
        svc.shutdownNow();
    }

}
