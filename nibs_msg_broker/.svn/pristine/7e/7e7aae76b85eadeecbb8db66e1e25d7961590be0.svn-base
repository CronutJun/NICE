package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnCheckListMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnCheckList;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

/**
 *
 * MngCM_AP_SaveLastCheck
 * <pre>
 * 1230 최종 발행수표 내역조회
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101230")
public class In03101230Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101230Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TMiscMapper tMiscMapper;

    @Autowired private TFnCheckListMapper tFnCheckListMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        comPack.checkBranchMacLength( parsed );

        TMacInfo tMacInfoParam = new TMacInfo();
        tMacInfoParam.setOrgCd(parsed.getString("CM.org_cd"));
        tMacInfoParam.setBranchCd(parsed.getString("brch_cd"));
        tMacInfoParam.setMacNo(parsed.getString("mac_no"));

        TMacInfo tMacInfoResult = null;

        try
        {
            tMacInfoResult = tMiscMapper.getDeptOfficeCd(tMacInfoParam);

        } catch (Exception e)
        {
            logger.info(String.format(">>> [MngCM_AP_SaveLastCheck-최종발행수표조회] 운영부, 지사 검색 오류. [%.200s]", e.getMessage()));
            throw e;
        }

        if(tMacInfoResult == null) {
            throw new MsgBrokerException(String.format(">>> [MngCM_AP_SaveLastCheck-최종발행수표조회] 운영부, 지사 정보 없음."), -1);
        }

        TFnCheckList tFnCheckList = new TFnCheckList();
        tFnCheckList.setCheckDate (parsed.getString("inq_date"));
        tFnCheckList.setOrgCd     (parsed.getString("CM.org_cd"));
        tFnCheckList.setBranchCd  (parsed.getString("brch_cd"));
        tFnCheckList.setMacNo     (parsed.getString("mac_no"));
        tFnCheckList.setDeptCd    (tMacInfoResult.getDeptCd());
        tFnCheckList.setOfficeCd  (tMacInfoResult.getOfficeCd());
        tFnCheckList.setCheckNo   (parsed.getString("check_no"));
        tFnCheckList.setUpdateDate(safeData.getDSysDate());
        tFnCheckList.setInsertDate(safeData.getDSysDate());
        tFnCheckList.setInsertUid (parsed.getString("CM.msg_id"));

        try
        {
            tFnCheckListMapper.insertSelective(tFnCheckList);
        } catch( org.springframework.dao.DataIntegrityViolationException e ) {
            logger.info( "[T_FN_CHECK_LIST] INSERT Duplicate !! 이미 저장된 건이 있습니다. {}", e.getMessage());
            /* 정상으로 응답 준다. */

        } catch (Exception e)
        {
            logger.info("[T_FN_CHECK_LIST] INSERT Err {}", e.getMessage());
            throw e;
        }

        logger.info("[T_FN_CHECK_LIST] INSERT OK");

    }
}
