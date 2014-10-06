package com.nicetcm.nibsplus.broker.ams;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * AMSOutboundQ
 *
 *  AMSBroker를 통한 기기 전문요청 큐데이터 객체
 *
 *
 * @author  K.D.J
 * @since   2014.08.18
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AMSBrokerOutboundQ {

    private String macNo;
    private BlockingQueue<AMSBrokerReqJob> outQ;
    private AMSBrokerReqConsumer consumer;

    public AMSBrokerOutboundQ(String macNo) {

        this.macNo = macNo;

        outQ = new LinkedBlockingQueue<AMSBrokerReqJob>();
        consumer = new AMSBrokerReqConsumer( outQ, this.macNo );
        consumer.start();
    }

    public BlockingQueue<AMSBrokerReqJob> getOutQ() {
        return outQ;
    }

    public AMSBrokerReqConsumer getConsumer() {
        return consumer;
    }

}
