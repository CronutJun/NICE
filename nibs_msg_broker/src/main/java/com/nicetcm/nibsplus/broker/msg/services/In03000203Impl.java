package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;

/**
 *
 * 0203 정산기 실시간거래내역
 * <pre>
 * MngCM_SaveCalcRealInout( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03000203")
public class In03000203Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03000203Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

    }
}
