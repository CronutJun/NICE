package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnOperfundsInfoMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnOperfundsInfo;
import com.nicetcm.nibsplus.broker.msg.model.TFnOperfundsInfoSpec;

/**
 *
 * 1192 운영자금 인수정보 통보
 * <pre>
 * MngCM_AP_SaveOperFundsRecvInfo( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101192")
public class In03101192Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101192Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired TFnOperfundsInfoMapper tFnOperfundsInfoMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TFnOperfundsInfo tFnOperfundsInfo = new TFnOperfundsInfo();
        TFnOperfundsInfoSpec tFnOperfundsInfoSpec = new TFnOperfundsInfoSpec();

        /* 운영자금 현송정보 혹은 출금수표 인도 정보라면 */
        if(parsed.getString("req_type").equals("1") || parsed.getString("req_type").equals("2")) {
            /*  T_FN_OPERFUNDS_INFO 에서 신청 구분 방법
            운영자금현송정보    - req_type : '1'
            출금수표 인도     - req_type : '2' && cancel_yn = '0'
            출금수표 인도 취소  - req_type : '2' && cancel_yn = '1' */
            /* 지점코드와 코너코드, 기번은 수표 정보 일경우에만 존재한다. */
            /* 수표 정보 일경우 상대점에 영업점 코드를
               운영자금 현송 정보 일 경우에는 상대점에 '0086' 설정 */


            tFnOperfundsInfo.setOrgSendYn("1");
            tFnOperfundsInfo.setRespCd(parsed.getString("CM.ret_cd"));
            tFnOperfundsInfo.setUpdateDate(safeData.getDSysDate());
            tFnOperfundsInfo.setUpdateUid("APmng");

            tFnOperfundsInfoSpec.createCriteria()
            .andReqDateEqualTo(parsed.getString("req_date"))
            .andReqTypeEqualTo(parsed.getString("req_type"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andSiteCdEqualTo( (parsed.getString("req_type").equals("1") ? "EMPTY" : parsed.getString("site_cd")) )
            .andMacNoEqualTo( (parsed.getString("req_type").equals("1") ? "EMPTY" : parsed.getString("mac_no")) );
        } else if(parsed.getString("req_type").equals("3")) {

            tFnOperfundsInfo.setOrgSendYn("2");
            tFnOperfundsInfo.setRespCd(parsed.getString("CM.ret_cd"));
            tFnOperfundsInfo.setUpdateDate(safeData.getDSysDate());
            tFnOperfundsInfo.setUpdateUid("Cancel");


            tFnOperfundsInfoSpec.createCriteria()
            .andReqDateEqualTo(parsed.getString("req_date"))
            .andReqTypeEqualTo("2")
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andSiteCdEqualTo(parsed.getString("site_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"));
        }

        try
        {
            tFnOperfundsInfoMapper.updateBySpecSelective(tFnOperfundsInfo, tFnOperfundsInfoSpec);
        } catch (Exception e)
        {
            logger.info( "[T_FN_ATMS_ADD_CASH_REPORT] Update Error {}", e.getMessage() );
            throw e;
        }

        logger.info( "[T_FN_ATMS_ADD_CASH_REPORT] Update OK" );

    }
}
