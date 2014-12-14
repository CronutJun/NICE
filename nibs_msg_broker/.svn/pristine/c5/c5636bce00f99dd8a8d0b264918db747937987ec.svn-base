package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;

/**
*
* OutIfLogingImpl
* <pre>
*   나가는 전문 로깅처리
* </pre>
*
* @author K.D.J
* @version 1.0
* @see
*/

@Service("outIfLoging")
public class OutIfLogingImpl extends OutMsgHandlerImpl {

    private final static Logger logger = LoggerFactory.getLogger(OutIfLogingImpl.class);

    @Override
    public void outMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        comPack.insertIfDataLog( safeData, "O", parsed );

    }
}
