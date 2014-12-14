package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtManyErrorMngMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtManyErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtManyErrorMngSpec;

/**
 *
 * 동시다발장애 출동요청 응답처리 - 기기사
 * <pre>
 * MngEM_AP_SaveGuardManyErr( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01101480")
public class In01101480Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCtManyErrorMngMapper tCtManyErrorMngMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        String hSEND_YN = null;

        if(parsed.getInt("CM.ret_cd") == 0) {
            hSEND_YN = "1";
        } else {
            if(parsed.getString("CM.ret_cd_src").equals("B")) {
                hSEND_YN = "B";
            } else {
                hSEND_YN = "H";
            }
        }

        TCtManyErrorMng tCtManyErrorMng = new TCtManyErrorMng();
        tCtManyErrorMng.setOrgSendYn  (hSEND_YN);
        tCtManyErrorMng.setUpdateDate (safeData.getDSysDate());
        tCtManyErrorMng.setUpdateUid  ("APmngEM");

        TCtManyErrorMngSpec tCtManyErrorMngSpec = new TCtManyErrorMngSpec();
        tCtManyErrorMngSpec.createCriteria()
        .andCallDateEqualTo(parsed.getString("call_date"))
        .andCallNoEqualTo(parsed.getString("call_no"));

        try
        {
            tCtManyErrorMngMapper.updateBySpecSelective(tCtManyErrorMng, tCtManyErrorMngSpec);
        } catch (Exception e)
        {
            logger.warn("[T_CT_ERROR_MNG_GUARD] Update Err [{}]", e.getMessage());
            throw new Exception("[T_CT_ERROR_MNG_GUARD] Update Err [" + e.getMessage() + "]");
        }

    }//end method
}