package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnWrVanDemandMapper;

/**
 *
 * 2110 결번요청
 * <pre>
 * MngIQ_SaveReqLostNo( pRecvData, pSendBuf, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04002110")
public class In04002110Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In04002110Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnWrVanDemandMapper tFnWrVanDemandMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        /***************************************************************************
         [기업은행] 브랜드제휴 재전송 - 일련번호 상관 없이, 요청일자 운영자금, 상세 전송
        ***************************************************************************/
        if(MsgBrokerConst.KIUP_CODE.equals(parsed.getString("CM.org_cd")) && parsed.getString("CM.service_gb").equals("1")) {
            /**************************************************************************
             1. 요청일자 운영자금청구 전문 생성, 전송
            **************************************************************************/
            //tFnWrVanDemandMapper.selectBySpec(spec)

        }

    }
}
