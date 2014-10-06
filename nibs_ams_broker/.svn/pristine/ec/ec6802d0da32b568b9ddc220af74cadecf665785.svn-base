package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * Out21005012Impl
 *
 *   일반파일 업로드 요청
 *
 *
 * @author  K.D.J
 * @since   2014.08.29
 */

import java.io.FileInputStream;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerConst;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqInfo;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;

@Service("out21005011")
public class Out21005011Impl implements OutMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(Out21005011Impl.class);

    @Override
    public void outMsgHandle(MsgParser outMsg, AMSBrokerData safeData, AMSBrokerReqJob reqJob, AMSBrokerReqInfo reqInfo, TRmMsg msg) throws Exception {

        reqInfo.getMsg().position(0);
        outMsg.setString( "CM._AOCMsgCode",           msg.getMsgCd())
              .setString( "CM._AOCServiceCode",       msg.getSvcCd())
              .setString( "CM._AOCMsgSendDate",       safeData.getMsgDate() )
              .setString( "CM._AOCMsgSendTime",       safeData.getMsgTime() )
              .setLong  ( "CM._AOCMsgLen",            outMsg.getMessageLength() - AMSBrokerConst.MSG_LEN_INFO_LEN )
              .setString( "CM._AOCTranNo",            msg.getMsgSeq() )
              .setString( "_AOCUpFilePath",           reqJob.getFilePath() )
              .setString( "_AOCUpFileName",           reqJob.getFileName() )
              .syncMessage();

    }

}
