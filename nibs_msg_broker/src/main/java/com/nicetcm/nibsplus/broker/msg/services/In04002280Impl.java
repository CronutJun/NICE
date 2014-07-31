package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;

/**
 *
 * 2280 CRT번호등록
 * <pre>
 * MngIQ_SendRegCRTNo( pRecvData, pSendBuf, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04002280")
public class In04002280Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In04002280Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        if(MsgBrokerConst.SHATMS_CODE.equals(parsed.getString("CM.org_cd"))
           || MsgBrokerConst.SHB_CODE.equals(parsed.getString("CM.org_cd"))
        ) {
            //TODO
            //전문 전송하는 유형이 기존패턴과 다른거 같음
        }
    }
}
