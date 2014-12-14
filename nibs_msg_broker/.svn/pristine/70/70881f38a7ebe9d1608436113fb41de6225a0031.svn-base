package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnCarryMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnCarry;
import com.nicetcm.nibsplus.broker.msg.model.TFnCarrySpec;
import com.nicetcm.nibsplus.broker.msg.model.TFnEmartAmtSpec;

/**
 *
 * 0260 매출등록
 * <pre>
 * MngCM_SaveRegSale( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03000260")
public class In03000260Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03000260Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnCarryMapper tFnCarryMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        boolean isDbDupData = false;

        if(parsed.getString("work_type").equals("1")) {
            /* 등록, 수정 */
            try
            {
                TFnCarry tFnCarry = new TFnCarry();
                tFnCarry.setCarryDate    (parsed.getString("sale_date"));
                tFnCarry.setCarryGroupCd ("10");
                tFnCarry.setCarryOrgCd   (parsed.getString("carry_org_cd"));
                tFnCarry.setCashAmt      (parsed.getLong("cash_amt"));
                tFnCarry.setCheckAmt     (parsed.getLong("check_amt"));
                tFnCarry.setInsertDate   (safeData.getDSysDate());
                tFnCarry.setInsertUid    (parsed.getString("ETC_In"));
                tFnCarry.setUpdateDate   (safeData.getDSysDate());
                tFnCarry.setUpdateUid    (parsed.getString("ETC_In"));

                tFnCarryMapper.insertSelective(tFnCarry);

            } catch( org.springframework.dao.DataIntegrityViolationException e ) {
                isDbDupData = true;

            } catch (Exception e)
            {
                logger.warn(">>> [T_FN_CARRY] INSERT ERROR {}", e.getMessage());
                throw e;
            }

            if(isDbDupData) {

                try
                {
                    TFnCarry tFnCarry = new TFnCarry();
                    tFnCarry.setCashAmt      (parsed.getLong("cash_amt"));
                    tFnCarry.setCheckAmt     (parsed.getLong("check_amt"));
                    tFnCarry.setInsertDate   (safeData.getDSysDate());
                    tFnCarry.setInsertUid    (parsed.getString("ETC_Up"));
                    tFnCarry.setUpdateDate   (safeData.getDSysDate());
                    tFnCarry.setUpdateUid    (parsed.getString("ETC_Up"));

                    TFnCarrySpec tFnCarrySpec = new TFnCarrySpec();
                    tFnCarrySpec.createCriteria()
                    .andCarryDateEqualTo(parsed.getString("sale_date"))
                    .andCarryGroupCdEqualTo("10")
                    .andCarryOrgCdEqualTo(parsed.getString("carry_org_cd"));

                    tFnCarryMapper.updateBySpecSelective(tFnCarry, tFnCarrySpec);

                } catch (Exception e)
                {
                    logger.warn(">>> [T_FN_CARRY] UPDATE ERROR {}", e.getMessage());
                    throw e;
                }
            }

        } else {
            /* 삭제 */

            TFnCarrySpec tFnCarrySpec = new TFnCarrySpec();
            tFnCarrySpec.createCriteria()
            .andCarryDateEqualTo(parsed.getString("sale_date"))
            .andCarryGroupCdEqualTo("10")
            .andCarryOrgCdEqualTo(parsed.getString("carry_org_cd"));

            try
            {
                tFnCarryMapper.deleteBySpec(tFnCarrySpec);
            } catch (Exception e)
            {
                logger.warn(">>> [T_FN_CARRY] DELETE ERROR {}", e.getMessage());
                throw e;
            }
        }
    }
}
