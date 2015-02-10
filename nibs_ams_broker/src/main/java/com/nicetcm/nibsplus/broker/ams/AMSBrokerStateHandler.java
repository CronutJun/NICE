package com.nicetcm.nibsplus.broker.ams;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.BlockingQueue;

public class AMSBrokerStateHandler extends ChannelDuplexHandler {

    private final AMSBrokerReqJob reqJob;
    private final BlockingQueue<AMSBrokerClientQData> ans;

    public AMSBrokerStateHandler(AMSBrokerReqJob reqJob, BlockingQueue<AMSBrokerClientQData> ans) {
        this.reqJob = reqJob;
        this.ans = ans;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                ans.put(new AMSBrokerClientQData(false, true, false, null));
            } else if (e.state() == IdleState.WRITER_IDLE) {
                ans.put(new AMSBrokerClientQData(false, true, false, null));
            }
        }
    }
}
