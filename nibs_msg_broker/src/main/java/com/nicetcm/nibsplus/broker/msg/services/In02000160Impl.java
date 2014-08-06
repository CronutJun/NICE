package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 코너 설치 신규/철수
 *
 * <pre>
 * MngSM_SaveSiteSetChange
 * </pre>
 *
 *           2014. 08. 05    K.D.J.
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtOrgSiteChange;

@Service("in02000160")
public class In02000160Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02000160Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCtOrgSiteChange orgSiteChange = new TCtOrgSiteChange();

        orgSiteChange.setOrgCd       ( parsed.getString("CM.org_cd"     ) );
        orgSiteChange.setBranchCd    ( parsed.getString("brch_cd"       ) );
        orgSiteChange.setOrgSiteCd   ( parsed.getString("old_site_cd"   ) );
        orgSiteChange.setChangeDate  ( parsed.getString("change_date"   ) );
        orgSiteChange.setOperDate    ( parsed.getString("oper_date"     ) );
        orgSiteChange.setNewOrgSiteCd( parsed.getString("new_site_cd"   ) );
        orgSiteChange.setNewOrgSiteNm( parsed.getString("new_site_nm"   ) );
        orgSiteChange.setNewOperType ( parsed.getString("new_oper_type" ) );
        orgSiteChange.setNewOperTime ( parsed.getString("new_oper_time" ) );
        orgSiteChange.setNewSetAddr  ( parsed.getString("new_set_addr"  ) );
        orgSiteChange.setNewServiceCd( parsed.getString("new_service_cd") );

        comPack.insertOrgSigeChnage( safeData, orgSiteChange );
    }

}
