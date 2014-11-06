package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtUseStatusMonthlyMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtUseStatusMonthly;
import com.nicetcm.nibsplus.broker.msg.model.TCtUseStatusMonthlySpec;

/**
 *
 * 1400 월이용현황
 * <pre>
 * MngCM_UpdateMonthUsage( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03001400")
public class In03001400Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03001400Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TCtUseStatusMonthlyMapper tCtUseStatusMonthlyMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        /* 응답받은 데이터 테이블의 org_send_yn = '1'으로 바꿈.            */

        /***************************************************
         브랜드제휴 기기
        ***************************************************/

        if(parsed.getString("CM.service_gb").equals("1")) {
            /*  초기 전송 건에 대해서는 ORG_SEND_YN = '1' 로 업데이트 한다.  */
            TCtUseStatusMonthly tCtUseStatusMonthly = new TCtUseStatusMonthly();
            tCtUseStatusMonthly.setOrgSendYn("1");

            TCtUseStatusMonthlySpec tCtUseStatusMonthlySpec = new TCtUseStatusMonthlySpec();
            tCtUseStatusMonthlySpec.createCriteria()
            .andYearMonEqualTo(parsed.getString("year_mon"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"))
            .andOrgSendYnEqualTo("0");

            int updateCount = 0;

            updateCount = tCtUseStatusMonthlyMapper.updateBySpecSelective(tCtUseStatusMonthly, tCtUseStatusMonthlySpec);

            if(updateCount == 0) {
                /*  이후 전송 건에 대해서는 그 이상으로 업데이트 한다.*/
                tCtUseStatusMonthly.setOrgSendYn("2");

                TCtUseStatusMonthlySpec tCtUseStatusMonthlySpec2 = new TCtUseStatusMonthlySpec();
                tCtUseStatusMonthlySpec2.createCriteria()
                .andYearMonEqualTo(parsed.getString("year_mon"))
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andMacNoEqualTo(parsed.getString("mac_no"))
                .andOrgSendYnEqualTo("1");

                tCtUseStatusMonthlyMapper.updateBySpecSelective(tCtUseStatusMonthly, tCtUseStatusMonthlySpec2);
            }

        } else {
            /***************************************************
             브랜드제휴가 아닌 기기는 아직 처리할 데이터가 없다.
            ***************************************************/


        }

        logger.warn(String.format("[MonthUsage]UPDATE SUCCESS - ORG[%s] MAC_NO[%s]", parsed.getString("CM.org_cd"), parsed.getString("mac_no")));

    }
}
