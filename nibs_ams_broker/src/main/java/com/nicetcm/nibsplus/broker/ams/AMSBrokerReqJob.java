package com.nicetcm.nibsplus.broker.ams;

import java.util.concurrent.*;
import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AMSBrokerReqJob {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerReqJob.class);

    private static final ConcurrentMap<String, AMSBrokerOutboundQ> macMap = new ConcurrentHashMap<String, AMSBrokerOutboundQ>();

    private final String macNo;
    private final BlockingQueue<ByteBuffer> ans;

    public AMSBrokerReqJob(String macNo) {

        this.macNo = macNo;
        this.ans = new LinkedBlockingQueue<ByteBuffer>();

    }

    private static BlockingQueue<AMSBrokerReqJob> getQueue(String macNo) throws Exception {

        BlockingQueue<AMSBrokerReqJob> queue;

        if( macMap.containsKey(macNo) ) {
            queue =  macMap.get(macNo).getOutQ();
        }
        else {
            AMSBrokerOutboundQ outQ = new AMSBrokerOutboundQ();
            macMap.put( macNo,outQ );
            queue = outQ.getOutQ();
        }
        return queue;
    }

    public void requestJob() throws Exception {
        logger.debug("requestJob");
        BlockingQueue<AMSBrokerReqJob> reqQue = getQueue( this.macNo );
        reqQue.put( this );
        logger.debug("requestJob OK");
    }

    public BlockingQueue<ByteBuffer> getAns() {
        return ans;
    }

}
