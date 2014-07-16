package com.nicetcm.nibsplus.broker.ams.services;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;

@Service("respAckNak")
public class RespAckNakImpl implements RespAckNakHandler {

    private static final Logger logger = LoggerFactory.getLogger(RespAckNakImpl.class);

    @Override
    public void procAckNak( ChannelHandlerContext ctx, MsgParser parsed ) throws Exception {
    
    }
}
