package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * Out21004003Impl
 *
 *   일반파일 실행요청
 *
 *
 * @author  K.D.J
 * @since   2015.01.23
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqInfo;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;

@Service("out21004003")
public class Out21004003Impl implements OutMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(Out21004003Impl.class);

    @Override
    public void outMsgHandle(MsgParser outMsg, AMSBrokerData safeData, AMSBrokerReqJob reqJob, AMSBrokerReqInfo reqInfo, TRmMsg msg) throws Exception {
        reqInfo.getMsg().position(0);
        outMsg.setString( "CM._AOCMsgCode",           msg.getMsgCd())
              .setString( "CM._AOCServiceCode",       msg.getSvcCd())
              .setString( "CM._AOCMsgSendDate",       safeData.getMsgDate() )
              .setString( "CM._AOCMsgSendTime",       safeData.getMsgTime() )
              .setInt   ( "CM._AOCMsgLen",            outMsg.getMessageLength() - 9 )
              .setString( "CM._AOCTranNo",            msg.getMsgSeq() );
        outMsg.setString( "_AOCExecFilePathName",  reqJob.getFilePath() );
        outMsg.setString( "_AOCExecFileShowType",  reqJob.getFileType() );
        outMsg.syncMessage();
    }

}
