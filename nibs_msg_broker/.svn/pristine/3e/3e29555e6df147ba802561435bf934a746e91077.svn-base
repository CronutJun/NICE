package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;

/**
 *
 * InIfLogingImpl
 * <pre>
 *   들어오는 전문 로깅처리
 * </pre>
 *
 * @author K.D.J
 * @version 1.0
 * @see
 */
@Service("inIfLoging")
public class InIfLogingImpl extends InMsgHandlerImpl {

    private final static Logger logger = LoggerFactory.getLogger(InIfLogingImpl.class);

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        comPack.insertIfDataLog( safeData, "I", parsed );
    }
}