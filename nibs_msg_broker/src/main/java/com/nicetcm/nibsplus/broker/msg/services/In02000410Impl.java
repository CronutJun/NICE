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
import com.nicetcm.nibsplus.broker.msg.mapper.TCmSetWorkEtcMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetWorkEtc;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetWorkEtcSpec;

@Service("in02000410")
public class In02000410Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02000410Impl.class);

    @Autowired private StoredProcMapper        splMap;
    @Autowired private TCmSetWorkEtcMapper cmSetWorkEtcMap;
    
    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmSetWorkEtc cmWorkEtc = new TCmSetWorkEtc();
        
        cmWorkEtc.setOrgCd 		    ( parsed.getString("SM.org_cd"		) );
        cmWorkEtc.setWorkSeq  		( parsed.getString("work_seq"		) );
        cmWorkEtc.setBranchCd  		( parsed.getString("branch_cd"		) );
        cmWorkEtc.setSiteCd  		( parsed.getString("site_cd"		) );
        cmWorkEtc.setSiteNm  		( parsed.getString("site_nm"		) );
        cmWorkEtc.setWorkDate  		( parsed.getString("work_date"		) );
        cmWorkEtc.setWorkCont  		( parsed.getString("work_cont"		) );        
        
        try {
        	cmSetWorkEtcMap.insert( cmWorkEtc );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
            	TCmSetWorkEtcSpec cmSetWorkEtcSpec = new TCmSetWorkEtcSpec();
            	cmSetWorkEtcSpec.createCriteria().andOrgCdEqualTo(parsed.getString("SM.org_cd"		))
            	                          .andWorkSeqEqualTo(parsed.getString("work_seq"             ));
            	cmSetWorkEtcMap.updateBySpecSelective( cmWorkEtc, cmSetWorkEtcSpec);
            	//cmSetWorkEtcMap.updateByPrimaryKeySelective( cmWorkEtc );
            }
            catch( Exception e ) {
                logger.warn( "[T_CM_SET_WORK_ETC] Save Error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.warn( "[T_CM_SET_WORK_ETC] Save Error [{}]", e.getLocalizedMessage() );
            throw e;
        }
       
    }

}
