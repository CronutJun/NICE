package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmNiceEnvCheckMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmNiceEnvCheck;
import com.nicetcm.nibsplus.broker.msg.model.TCmNiceEnvCheckSpec;

/**
 *
 * 1261 환경점검
 * <pre>
 * MngIQ_SaveEnvChkSimple( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04001261")
public class In04001261Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In04001261Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TCmNiceEnvCheckMapper tCmNiceEnvCheckMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        /*  전송데이터에 대한 테이블의 org_send_yn = '1'로 바꿈(바로 위 함수들 참조  */
        /* 응답코드 B99 로 오면 재송신 하지 않고 차후 모니터링 하도록 처리 */
        /* 브랜드제휴 기기                 */

        if(parsed.getString("CM.service_gb").equals("1")) {
            TCmNiceEnvCheck tCmNiceEnvCheck = new TCmNiceEnvCheck();
            tCmNiceEnvCheck.setOrgSendYn("1");

            TCmNiceEnvCheckSpec tCmNiceEnvCheckSpec = new TCmNiceEnvCheckSpec();
            tCmNiceEnvCheckSpec.createCriteria()
            .andCheckDateEqualTo(parsed.getString("check_date"))
            .andOrgCdEqualTo("096")
            .andBranchCdEqualTo("9600")
            .andMacNoEqualTo(parsed.getString("mac_no"));

            try
            {
                tCmNiceEnvCheckMapper.updateBySpecSelective(tCmNiceEnvCheck, tCmNiceEnvCheckSpec);
            } catch (Exception e)
            {
                logger.warn(">>> [DBUpdateEnvCheckBrand] (T_CM_SITE_ENV_CHECK_BRAND) CANCEL ERROR [{}]", e.getMessage());
                throw e;
            }

            logger.warn(String.format("UPDATE SUCCES - ORG[%s] MAC_NO[%s]", parsed.getString("CM.org_cd"), parsed.getString("mac_no")));

        } else {
            /* 브랜드제휴가 아닌 기기     */
        }
    }
}
