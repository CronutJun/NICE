package com.nicetcm.nibsplus.broker.ams;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AMSBrokerOutboundQ {

    private BlockingQueue<AMSBrokerReqJob> outQ;
    private AMSBrokerReqConsumer consumer;

    public AMSBrokerOutboundQ() {
        outQ = new LinkedBlockingQueue<AMSBrokerReqJob>();
        consumer = new AMSBrokerReqConsumer( outQ );
        consumer.start();
    }

    public BlockingQueue<AMSBrokerReqJob> getOutQ() {
        return outQ;
    }

    public AMSBrokerReqConsumer getConsumer() {
        return consumer;
    }

}
