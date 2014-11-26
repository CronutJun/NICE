package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 코너 신규/폐쇄/정보변경
 *
 * <pre>
 * MngSM_SaveSetSiteInfo
 * </pre>
 *
 *           2014. 08. 07    K.D.J.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmSetChangeSiteStateMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetChangeSiteState;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetChangeSiteStateSpec;

@Service("in02000311")
public class In02000311Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02000311Impl.class);

    @Autowired private StoredProcMapper        splMap;
    @Autowired private TCmSetChangeSiteStateMapper cmSetChangeSiteStateMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmSetChangeSiteState cmCSState = new TCmSetChangeSiteState();
        /*
         *  코너정보변경은 모두 INSERT 한다.
         */


        cmCSState.setOrgCd          ( parsed.getString("CM.org_cd"        ) );
        cmCSState.setWorkSeq        ( parsed.getString("work_seq"             ) );
        cmCSState.setChangeNo       ( parsed.getShort("change_no"            ) );
        cmCSState.setBranchCd       ( parsed.getString("org_brch_cd"          ) );
        cmCSState.setSiteCd         ( parsed.getString("org_site_cd"          ) );
        cmCSState.setSiteNm         ( parsed.getString("org_site_nm"          ) );
        cmCSState.setDataType       ( parsed.getString("data_type"            ) );
        cmCSState.setCloseDate      ( parsed.getString("close_date"           ) );
        cmCSState.setReopenType     ( parsed.getString("reopen_type"          ) );
        cmCSState.setReopenDate     ( parsed.getString("reopen_date"          ) );
        cmCSState.setSetType        ( parsed.getString("set_type"             ) );
        cmCSState.setOperStartTime  ( parsed.getString("oper_start_time"      ) );
        cmCSState.setOperEndTime    ( parsed.getString("oper_end_time"        ) );
        cmCSState.setCheckYn        ( parsed.getString("check_yn"             ) );
        cmCSState.setApplyDate      ( parsed.getString("apply_date"           ) );
        cmCSState.setInsertUid      ( "MngIn" );

        try {
            cmSetChangeSiteStateMap.insert( cmCSState );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                TCmSetChangeSiteStateSpec cmSCSSpec = new TCmSetChangeSiteStateSpec();
                cmSCSSpec.createCriteria().andOrgCdEqualTo(parsed.getString("CM.org_cd"     ))
                                          .andWorkSeqEqualTo(parsed.getString("work_seq"             ));
                cmSetChangeSiteStateMap.updateBySpecSelective( cmCSState, cmSCSSpec);
                //cmSetChangeSiteStateMap.updateByPrimaryKeySelective( cmCSState );
            }
            catch( Exception e ) {
                logger.warn( "[T_CM_SET_CHANGE_SITE_STATE] Save Error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.warn( "[T_CM_SET_CHANGE_SITE_STATE] Save Error [{}]", e.getLocalizedMessage() );
            throw e;
        }

    }

}
