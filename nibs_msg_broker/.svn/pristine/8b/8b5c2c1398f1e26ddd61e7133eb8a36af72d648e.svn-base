package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

/**
 *
 * 동시다발장애출동요청
 * <pre>
 * MngEM_SaveManyErrCall( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01001480")
public class In01001480Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TMiscMapper tMiscMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TMisc tMisc = tMiscMapper.getMadeComCd(parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"));

        if(tMisc == null) {
            logger.info( String.format("[MngEM_SaveManyErrCall] 기기제조사 정보  검색 실패 기관[%s] 지점[%s] 기번[%s]", parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no") ));
            throw new MsgBrokerException(-1);
        }

        //TCtManyErrorMng

    }
}