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

/**
 *
 * 1190 운영자금청구서
 * <pre>
 * MngCM_AP_SaveOperFunds( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101190")
public class In03101190Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101190Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TMiscMapper tMiscMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        /******************************************************************************************
        [우체국토탈]
        - 등록된 지점길이(6)와 운영자금청구용 지점길이(4, '0002')가 다르므로, 길이체크를 패스한다.
        - 효성측 요청에 의해, 지점별 청구가 아닌 통합하여 청구하도록 개발하였음.
        ******************************************************************************************/

        if(MsgBrokerConst.WC_CODE.equals(parsed.getString("CM.org_cd"))) {
            logger.info("우체국 지점코드체크 Pass");
        } else {
            comPack.checkBranchMacLength( parsed );
        }

        TMacInfo tMacInfo = new TMacInfo();
        tMacInfo.setOrgCd( parsed.getString("CM.org_cd") );
        tMacInfo.setBranchCd( parsed.getString("brch_cd") );
        tMacInfo.setMacNo( parsed.getString("mac_no") );
        tMacInfo.setReqDate(parsed.getString("req_date"));

        /* 경남은행, 새마을금고의 경우 T_FN_ATMS_DEMAND_MAC */
        if(   MsgBrokerConst.KNB_CODE.equals(parsed.getString("CM.org_cd"))
           || MsgBrokerConst.KFCC_CODE.equals(parsed.getString("CM.org_cd"))
           || MsgBrokerConst.KNATMS_CODE.equals(parsed.getString("CM.org_cd"))
        ) {
            if(MsgBrokerConst.KNB_CODE.equals(parsed.getString("CM.org_cd")) || MsgBrokerConst.KNATMS_CODE.equals(parsed.getString("CM.org_cd"))) {
                /* 경남은행은 이전에 추가현송 전송 오류 분을 당일 더하여 전송 한 후 */
                /* 이전날 추가현송 관련 ROW를 'c'로 표해 두고 당일 분을 '1'로 설정    */
                try
                {
                    tMiscMapper.updateFnAtmsDemandMac01(tMacInfo);
                } catch (Exception e)
                {
                    logger.info("[T_FN_ATMS_DEMAND_MAC] Update Err {}", e.getMessage());
                    throw e;
                }

                msgTX.commit(safeData.getTXS());
                logger.info( "[T_FN_ATMS_DEMAND_MAC] - 이전 추가 현송 미 전송분  Update OK" );
            }

            try
            {
                tMiscMapper.updateFnAtmsDemandMac02(tMacInfo);
            } catch (Exception e)
            {
                logger.info( "[T_FN_ATMS_DEMAND_MAC2] Update Err {}", e.getMessage() );
                throw e;
            }

            msgTX.commit(safeData.getTXS());
            logger.info( "[T_FN_ATMS_DEMAND_MAC2] Update OK" );

        } else {
            /* 신한은행 운영자금의 경우는 그냥 나이스 관리점으로 송/수신 받음 */
            /* 대구은행은 지점코드를 '9999'로 보내고, 빈칸으로 받는다.*/
            try
            {
                tMiscMapper.updateFnAtmsOperfundsDemand(tMacInfo);
            } catch (Exception e)
            {
                logger.info( String.format("[T_FN_ATMS_OPERFUNDS_DEMAND1][%s][%s][%s] Update Err[%.200s]\n", parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("req_date"), e.getMessage()));
                throw e;
            }

            msgTX.commit(safeData.getTXS());
            logger.info( "[T_FN_ATMS_OPERFUNDS_DEMAND1] Update OK" );

        }//endif
    }//end method
}
