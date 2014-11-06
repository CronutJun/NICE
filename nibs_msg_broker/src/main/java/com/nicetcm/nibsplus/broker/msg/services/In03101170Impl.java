package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

/**
 *
 * 1170 현송계획서
 * <pre>
 * MngCM_AP_SaveCashPlan( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101170")
public class In03101170Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101170Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TMiscMapper tMiscMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        comPack.checkBranchMacLength( parsed );

        String hORG_SEND_YN;

        if(parsed.getString("cash_type").startsWith("2")) {
            hORG_SEND_YN = "3";
        } else {
            if( MsgBrokerConst.SHATMS_CODE.equals(parsed.getString("CM.org_cd")) ) {
                /* 신한은행의 경우 현송구분 필드를 수용여부로 사용한다.                        */
                /* 은행의 경우 수용 = '1', 거부 = '2' 이나 NTMS는 반대로 사용하여              */
                /* 수용 = '2', 거부 = '1' 로받아 ORG_SEND_CONFIRM_YN = '4'로 설정하여 구분한다.*/
                hORG_SEND_YN = "4";

            } else {
                hORG_SEND_YN = "1";
            }
        }

        TMisc tMisc = new TMisc();
        tMisc.setOrgCd( parsed.getString("CM.org_cd") );
        tMisc.setBranchCd( parsed.getString("brch_cd") );
        tMisc.setMacNo( parsed.getString("mac_no") );
        tMisc.setOrgSendConfirmYn(hORG_SEND_YN);
        tMisc.setCashDate(parsed.getString("cash_date"));

        try
        {
            tMiscMapper.updateTFnAtmsCashPlan(tMisc);
        } catch (Exception e)
        {
            logger.warn( "[T_FN_ATMS_CASH_PLAN] Update Error {}", e.getMessage() );
            throw e;
        }

        logger.warn( "[T_FN_ATMS_CASH_PLAN] Update OK" );

    }
}
