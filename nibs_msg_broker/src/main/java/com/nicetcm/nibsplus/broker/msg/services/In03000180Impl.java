package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnAtmsCashPlanMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnAtmsCashPlan;
import com.nicetcm.nibsplus.broker.msg.model.TFnAtmsCashPlanSpec;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

/**
 *
 * 0180 확정 현송계획서
 * <pre>
 * MngCM_SaveFundsConfPlan( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03000180")
public class In03000180Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03000180Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnAtmsCashPlanMapper tFnAtmsCashPlanMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TMacInfo macInfo = new TMacInfo();
        macInfo.setOrgCd( parsed.getString("CM.org_cd") );
        macInfo.setBranchCd( parsed.getString("brch_cd") );
        macInfo.setMacNo( parsed.getString("mac_no") );

        comPack.getMacInfo(macInfo);

        logger.warn("기관[{}] 지점[{}] 기번[{}] 기기명[{}] 부서[{}] 사무소[{}] 지소[{}]",
                        macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo(),
                        macInfo.getMacNm(), macInfo.getDeptCd(), macInfo.getOfficeCd(), macInfo.getTeamCd() );

        String hConfirmYn;

        /* 신한atms 는 초기 수신 시 "1" 지사 수정 후 확정 시 "2", 신한 전송 후 "3"으로 설정 */
        if(MsgBrokerConst.SHATMS_CODE.equals(parsed.getString("CM.org_cd"))) {
            hConfirmYn = "1";
        } else {
            /* 기업은행은 은행 확정 그대로 지사 확정하므로 "2" 로 설정 */
            hConfirmYn = "2";
        }

        /* 20081118 은행현송예측금액을 확인할 수 있도록 ORG_CASH_AMT에도 저장 */
        /* CONFIRM_CASH_AMT와 같이 넣어준다.   */
        boolean isDbDupData = false;

        try
        {
            TFnAtmsCashPlan tFnAtmsCashPlan = new TFnAtmsCashPlan();
            tFnAtmsCashPlan.setOrgCd           (parsed.getString("CM.org_cd"));
            tFnAtmsCashPlan.setBranchCd        (parsed.getString("brch_cd"));
            tFnAtmsCashPlan.setSiteCd          (macInfo.getSiteCd());
            tFnAtmsCashPlan.setMacNo           (parsed.getString("mac_no"));
            tFnAtmsCashPlan.setCashDate        (parsed.getString("cash_date"));
            tFnAtmsCashPlan.setConfirmCashAmt  (parsed.getLong("cash_amt"));
            tFnAtmsCashPlan.setOrgSendConfirmYn(hConfirmYn);
            tFnAtmsCashPlan.setOrgCashAmt      (parsed.getLong("cash_amt"));
            tFnAtmsCashPlan.setUpdateDate      (safeData.getDSysDate());

            tFnAtmsCashPlanMapper.insertSelective(tFnAtmsCashPlan);

        } catch( org.springframework.dao.DataIntegrityViolationException e ) {
            isDbDupData = true;
        } catch (Exception e)
        {
            logger.warn(">>> [DBUpdateFunds] (T_FN_ATMS_CASH_PLAN) INSERT ERROR {}", e.getMessage());
            throw e;
        }

        if(isDbDupData) {
            logger.warn("...확정현송데이터 중복수신 << ...");

            TFnAtmsCashPlan tFnAtmsCashPlan = new TFnAtmsCashPlan();
            tFnAtmsCashPlan.setConfirmCashAmt  (parsed.getLong("cash_amt"));
            tFnAtmsCashPlan.setOrgSendConfirmYn(hConfirmYn);
            tFnAtmsCashPlan.setOrgCashAmt      (parsed.getLong("cash_amt"));
            tFnAtmsCashPlan.setUpdateDate      (safeData.getDSysDate());

            TFnAtmsCashPlanSpec tFnAtmsCashPlanSpec = new TFnAtmsCashPlanSpec();
            tFnAtmsCashPlanSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andSiteCdEqualTo(macInfo.getSiteCd())
            .andMacNoEqualTo(parsed.getString("mac_no"))
            .andCashDateEqualTo(parsed.getString("cash_date"));

            try
            {
                tFnAtmsCashPlanMapper.updateBySpecSelective(tFnAtmsCashPlan, tFnAtmsCashPlanSpec);
            } catch (Exception e)
            {
                logger.warn(">>> [DBUpdateFunds] (T_FN_ATMS_CASH_PLAN) UPDATE ERROR {}", e.getMessage());
                throw e;
            }

            logger.warn("!!!확정현송금액 처리완료(UPDATE)!!!");

        }

        logger.warn("!!!확정현송금액 처리완료!!!");
    }
}
