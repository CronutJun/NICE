package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * Out21004002Impl
 *
 *   장치 리셋,회수,반환명령 요청
 *
 *
 * @author  K.D.J
 * @since   2014.08.21
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqInfo;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;

@Service("out21004002")
public class Out21004002Impl implements OutMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(Out21004002Impl.class);

    @Override
    public void outMsgHandle(MsgParser outMsg, AMSBrokerData safeData, AMSBrokerReqJob reqJob, AMSBrokerReqInfo reqInfo, TRmMsg msg) throws Exception {
        reqInfo.getMsg().position(0);
        outMsg.setString( "CM._AOCMsgCode",           msg.getMsgCd())
              .setString( "CM._AOCServiceCode",       msg.getSvcCd())
              .setString( "CM._AOCMsgSendDate",       safeData.getMsgDate() )
              .setString( "CM._AOCMsgSendTime",       safeData.getMsgTime() )
              .setInt   ( "CM._AOCMsgLen",            outMsg.getMessageLength() - 9 )
              .setString( "CM._AOCTranNo",            msg.getMsgSeq() );
        if( reqJob.getDevCd() == null || reqJob.getDevCd().length() == 0 )
            outMsg.setString( "CmdType",  "1" );
        else
            outMsg.setString( "CmdType",  "2" );
        outMsg.setString( "DevId",                    reqJob.getDevCd() );
        if( reqJob.getActCd().equals("503") )
            outMsg.setString( "DevCmd",   "1" );
        else if( reqJob.getActCd().equals("505") )
            outMsg.setString( "DevCmd",   "2" );
        else
            outMsg.setString( "DevCmd",   "3" );
        outMsg.syncMessage();
    }

}
