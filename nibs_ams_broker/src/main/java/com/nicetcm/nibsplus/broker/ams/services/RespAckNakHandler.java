package com.nicetcm.nibsplus.broker.ams.services;

import io.netty.channel.ChannelHandlerContext;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.model.TRmTrx;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;

public interface RespAckNakHandler {

    public void procAckNak( ChannelHandlerContext ctx, AMSBrokerData safeData, MsgParser parsed, TRmTrx trx, TRmMsg msg ) throws Exception;

}
