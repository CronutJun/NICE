package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 인증정보
 *
 * <pre>
 * MngMM_NiceAuthentication
 * </pre>
 *
 *           2014. 08. 04    K.D.J.
 */

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

@Service("inM1000200")
public class InM1000200Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InM1000200Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        if( parsed.getString("user_telno").length() < 8 ) {
            logger.info( String.format(" 고객전화번호 이상. 전화번호[%s]", parsed.getString("user_telno")) );
            throw new MsgBrokerException( String.format(" 고객전화번호 이상. 전화번호[%s]", parsed.getString("user_telno")), -86);
        }
        /*
         *  난수를 발생하여 SMS 전송 후 응답처리
         */
        Random rd = new Random();
        String sRand = String.format("%06d", rd.nextInt(999999));
        String sMsg  = String.format("한국전자금융 인증번호  [%s]", sRand);

        logger.info(String.format(" 인증번호 발생 [%s]-[%s]", sRand, parsed.getString("user_telno")) );
        TMisc arg = new TMisc();
        arg.setTelNo( parsed.getString("user_telno") );
        arg.setSendMsg( sMsg );
        try {
            splMap.sendSMS( arg );
        }
        catch( Exception e ) {
            logger.info("sendSMS call error {}", e.getLocalizedMessage() );
            throw e;
        }
        parsed.setString( "authenti_no", sRand );
    }
}
