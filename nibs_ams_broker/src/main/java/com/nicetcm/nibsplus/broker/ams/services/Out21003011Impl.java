package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * Out21003011Impl
 *
 *   레지스트리 설정 변경 요청전문 구성
 *
 *
 * @author  K.D.J
 * @since   2014.08.20
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqInfo;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;

@Service("out21003011")
public class Out21003011Impl implements OutMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(Out21003011Impl.class);

    @Override
    public void outMsgHandle(MsgParser outMsg, AMSBrokerData safeData, AMSBrokerReqJob reqJob, AMSBrokerReqInfo reqInfo, TRmMsg msg) throws Exception {
        reqInfo.getMsg().position(0);
        outMsg.setString( "CM._AOCMsgCode",           msg.getMsgCd())
              .setString( "CM._AOCServiceCode",       msg.getSvcCd())
              .setString( "CM._AOCMsgSendDate",       safeData.getMsgDate() )
              .setString( "CM._AOCMsgSendTime",       safeData.getMsgTime() )
              .setInt   ( "CM._AOCMsgLen",            outMsg.getMessageLength() - 9 )
              .setString( "CM._AOCTranNo",            msg.getMsgSeq() )
              .setString( "_AOCReqRegBaseKey",        reqJob.getReqRegInfo().getBaseKey() )
              .setString( "_AOCReqRegSubKey",         reqJob.getReqRegInfo().getKeyPath() )
              .setString( "_AOCReqRegValueKey",       reqJob.getReqRegInfo().getKeyName() )
              .setString( "_AOCReqRegValue",          reqJob.getReqRegInfo().getValue()   )
              .syncMessage();
    }

}
