package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtServiceFeeMonthlyMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtServiceFeeMonthly;
import com.nicetcm.nibsplus.broker.msg.model.TCtServiceFeeMonthlySpec;

/**
 *
 * 1130 용역료관리
 * <pre>
 * MngIQ_AP_SaveSendMonthlyFee( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04101130")
public class In04101130Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCtServiceFeeMonthlyMapper tCtServiceFeeMonthlyMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        if(parsed.getString("CM.msg_type").substring(2).equals("00")) {

            boolean isDbDupData = false;

            try
            {
                TCtServiceFeeMonthly tCtServiceFeeMonthly = new TCtServiceFeeMonthly();
                tCtServiceFeeMonthly.setOrgCd       (parsed.getString("CM.org_cd"         ));
                tCtServiceFeeMonthly.setBranchCd    (parsed.getString("brch_cd"       ));
                tCtServiceFeeMonthly.setSiteCd      (parsed.getString("site_cd"        ));
                tCtServiceFeeMonthly.setYearMon     (parsed.getString("fee_ym"         ));
                tCtServiceFeeMonthly.setBranchNm     (parsed.getString("brch_nm"       ));
                tCtServiceFeeMonthly.setSiteNm      (parsed.getString("site_nm"        ));
                tCtServiceFeeMonthly.setOfficeNm    (parsed.getString("office_nm"      ));
                tCtServiceFeeMonthly.setMacNo       (parsed.getString("mac_no"         ));
                tCtServiceFeeMonthly.setLocateType  (parsed.getString("site_type"      ));
                tCtServiceFeeMonthly.setOperTimeType(parsed.getString("oper_type"      ));
                tCtServiceFeeMonthly.setMacCnt      (parsed.getString("mac_cnt"        ));
                tCtServiceFeeMonthly.setBaseFee     (parsed.getString("svc_fee_base"   ));
                tCtServiceFeeMonthly.setMonthFee    (parsed.getString("monthly_fee"    ));
                tCtServiceFeeMonthly.setAddFee      (parsed.getString("add_fee"        ));
                tCtServiceFeeMonthly.setFeeVatEsc   (parsed.getString("svc_extra_vat"  ));
                tCtServiceFeeMonthly.setOvertimeFee (parsed.getString("time_over_fee"  ));
                tCtServiceFeeMonthly.setCheckFee    (parsed.getString("check_over_fee" ));
                tCtServiceFeeMonthly.setGuardMacFee (parsed.getString("guard_mac_fee"  ));
                tCtServiceFeeMonthly.setSurtax      (parsed.getString("surtax"         ));
                tCtServiceFeeMonthly.setDemandFee   (parsed.getString("req_extra_vat"  ));
                tCtServiceFeeMonthly.setSiteFee     (parsed.getString("site_fee"       ));
                tCtServiceFeeMonthly.setOperDay     (parsed.getString("oper_day_cnt"   ));
                tCtServiceFeeMonthly.setPenaltyAmt  (parsed.getString("penalty_amt"    ));
                tCtServiceFeeMonthly.setJumSum      (parsed.getString("jijum_total_amt"));
                tCtServiceFeeMonthly.setRemark      (parsed.getString("message"        ));
                tCtServiceFeeMonthly.setCheckYn     (parsed.getString("check_yn"       ));
                tCtServiceFeeMonthly.setOrgSendYn   (parsed.getString("0"));
                tCtServiceFeeMonthly.setInsertDate  (safeData.getDSysDate());
                tCtServiceFeeMonthly.setUpdateDate  (safeData.getDSysDate());

                tCtServiceFeeMonthlyMapper.insertSelective(tCtServiceFeeMonthly);

            } catch( org.springframework.dao.DataIntegrityViolationException e ) {
                isDbDupData = true;

            } catch (Exception e)
            {
                logger.info(">>> [MngIQ_AP_SaveSendMonthlyFee] T_CT_SERVICE_FEE_MONTHLY INSERT ERROR [{}]", e.getMessage());
                throw e;
            }

            if(isDbDupData) {
                /* 전문이 이미 존재 한다면 업데이트 한다.               */

                try
                {
                    TCtServiceFeeMonthly tCtServiceFeeMonthly = new TCtServiceFeeMonthly();

                    tCtServiceFeeMonthly.setBranchCd    (parsed.getString("brch_cd"       ));
                    tCtServiceFeeMonthly.setSiteNm      (parsed.getString("site_nm"        ));
                    tCtServiceFeeMonthly.setOfficeNm    (parsed.getString("office_nm"      ));
                    tCtServiceFeeMonthly.setMacNo       (parsed.getString("mac_no"         ));
                    tCtServiceFeeMonthly.setLocateType  (parsed.getString("site_type"      ));
                    tCtServiceFeeMonthly.setOperTimeType(parsed.getString("oper_type"      ));
                    tCtServiceFeeMonthly.setMacCnt      (parsed.getString("mac_cnt"        ));
                    tCtServiceFeeMonthly.setBaseFee     (parsed.getString("svc_fee_base"   ));
                    tCtServiceFeeMonthly.setMonthFee    (parsed.getString("monthly_fee"    ));
                    tCtServiceFeeMonthly.setAddFee      (parsed.getString("add_fee"        ));
                    tCtServiceFeeMonthly.setFeeVatEsc   (parsed.getString("svc_extra_vat"  ));
                    tCtServiceFeeMonthly.setOvertimeFee (parsed.getString("time_over_fee"  ));
                    tCtServiceFeeMonthly.setCheckFee    (parsed.getString("check_over_fee" ));
                    tCtServiceFeeMonthly.setGuardMacFee (parsed.getString("guard_mac_fee"  ));
                    tCtServiceFeeMonthly.setSurtax      (parsed.getString("surtax"         ));
                    tCtServiceFeeMonthly.setDemandFee   (parsed.getString("req_extra_vat"  ));
                    tCtServiceFeeMonthly.setSiteFee     (parsed.getString("site_fee"       ));
                    tCtServiceFeeMonthly.setOperDay     (parsed.getString("oper_day_cnt"   ));
                    tCtServiceFeeMonthly.setPenaltyAmt  (parsed.getString("penalty_amt"    ));
                    tCtServiceFeeMonthly.setJumSum      (parsed.getString("jijum_total_amt"));
                    tCtServiceFeeMonthly.setRemark      (parsed.getString("message"        ));
                    tCtServiceFeeMonthly.setCheckYn     (parsed.getString("check_yn"       ));
                    tCtServiceFeeMonthly.setOrgSendYn   (parsed.getString("0"));
                    tCtServiceFeeMonthly.setInsertDate  (safeData.getDSysDate());
                    tCtServiceFeeMonthly.setUpdateDate  (safeData.getDSysDate());

                    TCtServiceFeeMonthlySpec tCtServiceFeeMonthlySpec = new TCtServiceFeeMonthlySpec();
                    tCtServiceFeeMonthlySpec.createCriteria()
                    .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                    .andBranchCdEqualTo(parsed.getString("brch_cd"))
                    .andSiteCdEqualTo(parsed.getString("site_cd"))
                    .andYearMonEqualTo(parsed.getString("fee_ym"));

                    tCtServiceFeeMonthlyMapper.updateBySpecSelective(tCtServiceFeeMonthly, tCtServiceFeeMonthlySpec);

                } catch (Exception e)
                {
                    logger.info(">>> [MngIQ_AP_SaveSendMonthlyFee] T_CT_SERVICE_FEE_MONTHLY UPDATE ERROR [{}]", e.getMessage());
                    throw e;
                }

            }

        } else {
            try
            {
                /* 기업은행일경우 해당 년월 없음 */
                if(parsed.getString("fee_ym").length() <= 0) {
                    if(parsed.getString("mac_no").length() == 0) {
                        TCtServiceFeeMonthly tCtServiceFeeMonthly = new TCtServiceFeeMonthly();
                        tCtServiceFeeMonthly.setOrgSendYn("1");

                        TCtServiceFeeMonthlySpec tCtServiceFeeMonthlySpec = new TCtServiceFeeMonthlySpec();
                        tCtServiceFeeMonthlySpec.createCriteria()
                        .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                        .andBranchCdEqualTo(parsed.getString("brch_cd"))
                        .andSiteCdEqualTo(parsed.getString("site_cd"))
                        .andOrgSendYnEqualTo("0");

                        tCtServiceFeeMonthlyMapper.updateBySpecSelective(tCtServiceFeeMonthly, tCtServiceFeeMonthlySpec);

                    } else {
                        TCtServiceFeeMonthly tCtServiceFeeMonthly = new TCtServiceFeeMonthly();
                        tCtServiceFeeMonthly.setOrgSendYn("1");

                        TCtServiceFeeMonthlySpec tCtServiceFeeMonthlySpec = new TCtServiceFeeMonthlySpec();
                        tCtServiceFeeMonthlySpec.createCriteria()
                        .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                        .andBranchCdEqualTo(parsed.getString("brch_cd"))
                        .andSiteCdEqualTo(parsed.getString("site_cd"))
                        .andMacNoEqualTo(parsed.getString("mac_no"))
                        .andOrgSendYnEqualTo("0");

                        tCtServiceFeeMonthlyMapper.updateBySpecSelective(tCtServiceFeeMonthly, tCtServiceFeeMonthlySpec);
                    }
                } else {
                    if(parsed.getString("mac_no").length() == 0) {
                        TCtServiceFeeMonthly tCtServiceFeeMonthly = new TCtServiceFeeMonthly();
                        tCtServiceFeeMonthly.setOrgSendYn("1");

                        TCtServiceFeeMonthlySpec tCtServiceFeeMonthlySpec = new TCtServiceFeeMonthlySpec();
                        tCtServiceFeeMonthlySpec.createCriteria()
                        .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                        .andBranchCdEqualTo(parsed.getString("brch_cd"))
                        .andSiteCdEqualTo(parsed.getString("site_cd"))
                        .andYearMonEqualTo(parsed.getString("fee_ym"))
                        .andOrgSendYnEqualTo("0");

                        tCtServiceFeeMonthlyMapper.updateBySpecSelective(tCtServiceFeeMonthly, tCtServiceFeeMonthlySpec);

                    } else {
                        TCtServiceFeeMonthly tCtServiceFeeMonthly = new TCtServiceFeeMonthly();
                        tCtServiceFeeMonthly.setOrgSendYn("1");

                        TCtServiceFeeMonthlySpec tCtServiceFeeMonthlySpec = new TCtServiceFeeMonthlySpec();
                        tCtServiceFeeMonthlySpec.createCriteria()
                        .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                        .andBranchCdEqualTo(parsed.getString("brch_cd"))
                        .andSiteCdEqualTo(parsed.getString("site_cd"))
                        .andMacNoEqualTo(parsed.getString("mac_no"))
                        .andYearMonEqualTo(parsed.getString("fee_ym"))
                        .andOrgSendYnEqualTo("0");

                        tCtServiceFeeMonthlyMapper.updateBySpecSelective(tCtServiceFeeMonthly, tCtServiceFeeMonthlySpec);
                    }
                }
            } catch (Exception e)
            {
                logger.info("[T_CT_SERVICE_FEE_MONTHLY] Update Error [{}]", e.getMessage());
                throw e;
            }

            logger.info( "[T_CT_SERVICE_FEE_MONTHLY] Update OK" );
        }

    }//end method
}