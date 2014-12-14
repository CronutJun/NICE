package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 작업일정 통보
 *
 * <pre>
 * MngSM_SaveSetSchedule
 * </pre>
 *
 *           2014. 08. 06    K.D.J.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmSetScheduleMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetSchedule;
import com.nicetcm.nibsplus.broker.msg.model.TCtMacInfoModSpec;

@Service("in02000300")
public class In02000300Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02000300Impl.class);

    @Autowired private StoredProcMapper     splMap;
    @Autowired private TCmSetScheduleMapper cmSetScheduleMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmSetSchedule cmSetSchedule = new TCmSetSchedule();

        cmSetSchedule.setOrgCd      ( parsed.getString("CM.org_cd"    ) );
        cmSetSchedule.setWorkSeq    ( parsed.getString("work_seq"     ) );
        cmSetSchedule.setChangeNo   ( parsed.getShort ("change_no"    ) );
        cmSetSchedule.setDataType   ( parsed.getString("data_type"    ) );
        cmSetSchedule.setWorkClass  ( parsed.getString("work_class"   ) );
        cmSetSchedule.setAcceptDate ( parsed.getString("accept_date"  ) );
        cmSetSchedule.setAcceptTime ( parsed.getString("accept_time"  ) );
        cmSetSchedule.setApplyDate  ( parsed.getString("apply_date"   ) );
        cmSetSchedule.setSetupDate  ( parsed.getString("setup_date"   ) );
        cmSetSchedule.setSetMacCnt  ( parsed.getShort ("set_mac_cnt"  ) );
        cmSetSchedule.setSetFacilCnt( parsed.getShort ("set_facil_cnt") );
        cmSetSchedule.setBranchNm   ( parsed.getString("brch_nm"      ) );
        cmSetSchedule.setSiteNm     ( parsed.getString("site_nm"      ) );
        cmSetSchedule.setBranchManNm( parsed.getString("brch_man_nm"  ) );
        cmSetSchedule.setTeleNo     ( parsed.getString("tel_no"       ) );
        cmSetSchedule.setWorkMsg    ( parsed.getString("work_msg"     ) );

        try {
            cmSetScheduleMap.insertSelective( cmSetSchedule );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                cmSetScheduleMap.updateByPrimaryKeySelective( cmSetSchedule );
            }
            catch( Exception e ) {
                logger.warn( "[T_CM_SET_SCHEDULE] Save Error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.warn( "[T_CM_SET_SCHEDULE] Save Error [{}]", e.getLocalizedMessage() );
            throw e;
        }
    }

}
