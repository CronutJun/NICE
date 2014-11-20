package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmEmartMemberMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmEmartMember;
import com.nicetcm.nibsplus.broker.msg.model.TCmEmartMemberSpec;

/**
 *
 * 1210 정산기 캐셔 정보
 * <pre>
 * MngIQ_AP_SaveCalcCashierInfo( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04101210")
public class In04101210Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCmEmartMemberMapper tCmEmartMemberMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        int resultCnt;

        try
        {
            TCmEmartMember tCmEmartMember = new TCmEmartMember();
            tCmEmartMember.setMemberNm(parsed.getString("casher_nm"));
            tCmEmartMember.setUseYn(parsed.getString("use_yn"));
            tCmEmartMember.setCashierType(parsed.getString("casher_type"));
            tCmEmartMember.setMemo(parsed.getString("message"));
            tCmEmartMember.setUpdateDate(safeData.getDSysDate());
            tCmEmartMember.setUpdateUid("DataMg");

            TCmEmartMemberSpec tCmEmartMemberSpec = new TCmEmartMemberSpec();
            tCmEmartMemberSpec.createCriteria()
            .andMemberIdEqualTo(parsed.getString("casher_id"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"));

            resultCnt = tCmEmartMemberMapper.updateBySpecSelective(tCmEmartMember, tCmEmartMemberSpec);
        } catch (Exception e)
        {
            logger.warn(">>> [T_CM_EMART_MEMBER] UPDATE ERROR {}", e.getMessage());
            throw e;
        }

        if(resultCnt == 0) {
            TCmEmartMember tCmEmartMember = new TCmEmartMember();
            tCmEmartMember.setMemberId(parsed.getString("casher_id"));
            tCmEmartMember.setMemberNm(parsed.getString("casher_nm"));
            tCmEmartMember.setBranchCd(parsed.getString("brch_cd"));
            tCmEmartMember.setUseYn(parsed.getString("use_yn"));
            tCmEmartMember.setCashierType(parsed.getString("casher_type"));
            tCmEmartMember.setMemo(parsed.getString("message"));
            tCmEmartMember.setUpdateDate(safeData.getDSysDate());
            tCmEmartMember.setUpdateUid("online");

            try
            {
                tCmEmartMemberMapper.insertSelective(tCmEmartMember);
            } catch (Exception e)
            {
                logger.warn( ">>> [MngIQ_AP_SaveCalcCashierInfo] (T_CM_EMART_MEMBER) INSERT ERROR [{}]", e.getMessage());
                throw e;
            }
        }

        logger.warn("!!![MngIQ_AP_SaveCalcCashierInfo] 처리완료(INSERT)!!!");
    }
}