package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 신규설치
 *
 * <pre>
 * MngSM_SaveSiteSetMng
 * </pre>
 *
 *           2014. 08. 06    K.D.J.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtMacSetMng;

@Service("in02000170")
public class In02000170Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02000170Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCtMacSetMng macSetMng = new TCtMacSetMng();
        
        macSetMng.setOrgCd    ( parsed.getString("CM.org_cd"  ) );
        macSetMng.setBranchCd ( parsed.getString("brch_cd"    ) );
        macSetMng.setSiteCd   ( parsed.getString("org_site_cd") );
        macSetMng.setMacNo    ( parsed.getString("mac_no"     ) );
        macSetMng.setSetType  ( parsed.getString("set_type"   ) );
        macSetMng.setSetDate  ( parsed.getString("set_date"   ) );
        macSetMng.setSetTime  ( parsed.getString(""           ) );
        macSetMng.setOperDate ( parsed.getString("set_date"   ) );
        macSetMng.setSiteNm   ( parsed.getString("org_site_nm") );
        macSetMng.setMadeComCd( parsed.getString("made_com_cd") );
        macSetMng.setServiceCd( parsed.getString("service_cd" ) );
        macSetMng.setBranchNm ( parsed.getString("brch_nm"    ) );
        macSetMng.setMacModel ( parsed.getString("mac_model"  ) );
        macSetMng.setOperType ( parsed.getString("oper_type"  ) );
        macSetMng.setOperTime ( parsed.getString("oper_time"  ) );
        macSetMng.setSetAddr  ( parsed.getString("set_addr"   ) );
        macSetMng.setSetPlace ( parsed.getString("set_place"  ) );

        comPack.insertUpdateMacSet( safeData, MsgBrokerConst.DB_WORK_INSERT, macSetMng );

    }

}
