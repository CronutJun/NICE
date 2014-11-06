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
 * 1220 지점별 기기마감 일괄조회
 * <pre>
 * MngIQ_AP_SaveCalcCashierInfo( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04101220")
public class In04101220Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCmEmartMemberMapper tCmEmartMemberMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
/*
        EXEC SQL update T_CM_EMART_MEMBER
        set
                 MEMBER_NM   = :suBody.casher_nm
                ,USE_YN      = :suBody.use_yn
                ,CASHIER_TYPE= :suBody.casher_type
                ,MEMO        = :suBody.meg
                ,UPDATE_DATE = SYSDATE
                ,UPDATE_UID  = 'DataMg'
        where   MEMBER_ID   = :suBody.casher_id
        and     JIJUM_CD    = :suBody.jijum_cd;
*/


        int resultCnt;

        try
        {
            TCmEmartMember tCmEmartMember = new TCmEmartMember();
            tCmEmartMember.setMemberNm(parsed.getString("casher_nm"));
            tCmEmartMember.setUseYn(parsed.getString("use_yn"));
            tCmEmartMember.setCashierType(parsed.getString("casher_type"));
            tCmEmartMember.setMemo(parsed.getString("meg"));
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
            tCmEmartMember.setMemo(parsed.getString("meg"));
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