package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * Out21003002Impl
 *
 *   Env 설정 변경 요청전문 구성
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
import com.nicetcm.nibsplus.broker.ams.rmi.RMIEnvValue;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;

@Service("out21003002")
public class Out21003002Impl implements OutMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(Out21003002Impl.class);

    @Override
    public void outMsgHandle(MsgParser outMsg, AMSBrokerData safeData, AMSBrokerReqJob reqJob, AMSBrokerReqInfo reqInfo, TRmMsg msg) throws Exception {
        reqInfo.getMsg().position(0);
        outMsg.setString( "CM._AOCMsgCode",           msg.getMsgCd())
              .setString( "CM._AOCServiceCode",       msg.getSvcCd())
              .setString( "CM._AOCMsgSendDate",       safeData.getMsgDate() )
              .setString( "CM._AOCMsgSendTime",       safeData.getMsgTime() )
              .setString( "CM._AOCTranNo",            msg.getMsgSeq() );
        if( reqJob.getEnvValues() != null ) {
            outMsg.setInt   ( "FieldCount",               reqJob.getEnvValues().size() );

            RMIEnvValue envValue;
            logger.warn( "envValues size = {}", reqJob.getEnvValues().size() );
            for( int i = 0; i < reqJob.getEnvValues().size(); i++ ) {
                envValue = reqJob.getEnvValues().get(i);
                logger.warn("FieldID = {}", envValue.getEnvId());
                outMsg.addRow( "FD" );
                outMsg.setString( String.format("FD[%d].FieldID",   i), envValue.getEnvId() )
                      .setInt   ( String.format("FD[%d].FieldLen",  i), envValue.getEnvValue().length() )
                      .setString( String.format("FD[%d].FieldData", i), envValue.getEnvValue() );
            }
        }
        else {
            RMIEnvValue envValue = reqJob.getEnvValue();

            outMsg.setInt   ( "FieldCount",               1 );
            outMsg.addRow( "FD" );
            outMsg.setString( String.format("FD[%d].FieldID",   0), envValue.getEnvId() )
                  .setInt   ( String.format("FD[%d].FieldLen",  0), envValue.getEnvValue().length() )
                  .setString( String.format("FD[%d].FieldData", 0), envValue.getEnvValue() );
        }
        outMsg.syncMessage();
        outMsg.getCurrentThrData().isLive = true;
        outMsg.setInt   ( "CM._AOCMsgLen",            outMsg.getMessageLength() - 9 );
        outMsg.getCurrentThrData().isLive = false;
    }

}
