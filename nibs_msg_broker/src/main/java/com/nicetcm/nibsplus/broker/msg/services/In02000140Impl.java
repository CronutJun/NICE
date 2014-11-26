package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 요청 취소
 *
 * <pre>
 * MngSM_SaveSetCancel
 * </pre>
 *
 *           2014. 08. 05    K.D.J.
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

@Service("in02000140")
public class In02000140Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02000140Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCtMacSetMng macSetMng = new TCtMacSetMng();
        
        macSetMng.setOrgCd      ( parsed.getString("SM.org_cd"    ) );  
        macSetMng.setBranchCd   ( parsed.getString("branch_cd"    ) );
        macSetMng.setSiteCd     ( parsed.getString("org_site_cd"  ) );
        macSetMng.setMacNo      ( parsed.getString("mac_no"       ) );
        macSetMng.setSetType    ( parsed.getString("cancel_type"  ) );
        macSetMng.setSetDate    ( parsed.getString("set_date"     ) );
        macSetMng.setSetTime    ( parsed.getString("set_time"     ) );
        macSetMng.setSiteNm     ( parsed.getString("org_site_nm"  ) );
        macSetMng.setMadeComCd  ( parsed.getString("made_com_cd"  ) );
        macSetMng.setMacSeriald ( parsed.getString("mac_seriald"  ) );
        macSetMng.setMacModel   ( parsed.getString("mac_model"    ) );
        macSetMng.setMacSubModel( parsed.getString("mac_sub_model") );
        macSetMng.setOrgMemo    ( parsed.getString("org_memo"     ) );
        macSetMng.setSetSerial  ( parsed.getString("set_seriald"  ) );

        comPack.insertUpdateMacSet( safeData, MsgBrokerConst.DB_WORK_CANCEL, macSetMng );

    }

}
