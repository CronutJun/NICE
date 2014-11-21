package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 이전설치 요청
 *
 * <pre>
 * MngSM_SaveMacSetMove
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

@Service("in02000120")
public class In02000120Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02000120Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCtMacSetMng macSetMng = new TCtMacSetMng();

        macSetMng.setOrgCd       ( parsed.getString("CM.org_cd"      ) );
        macSetMng.setBranchCd    ( parsed.getString("brch_cd"        ) );
        macSetMng.setSiteCd      ( parsed.getString("org_site_cd"    ) );
        macSetMng.setMacNo       ( parsed.getString("mac_no"         ) );
        macSetMng.setSetType     ( "2"                                 );
        macSetMng.setSetDate     ( parsed.getString("move_date"      ) );
        macSetMng.setSetTime     ( parsed.getString("move_time"      ) );
        macSetMng.setOperDate    ( parsed.getString("oper_date"      ) );
        macSetMng.setSiteNm      ( parsed.getString("org_site_nm"    ) );
        macSetMng.setMadeComCd   ( parsed.getString("made_com_cd"    ) );
        macSetMng.setMacSeriald  ( parsed.getString("mac_seriald"    ) );
        macSetMng.setMacModel    ( parsed.getString("mac_model"      ) );
        macSetMng.setMacSubModel ( parsed.getString("mac_sub_model"  ) );
        macSetMng.setOperType    ( parsed.getString("oper_type"      ) );
        macSetMng.setOperTime    ( parsed.getString("oper_time"      ) );
        //macSetMng.setSiteType    ( parsed.getString("site_type"      ) );
        macSetMng.setSetAddr     ( parsed.getString("set_addr"       ) );
        macSetMng.setSetPlace    ( parsed.getString("set_place"      ) );
        macSetMng.setServiceCd   ( parsed.getString("service_cd"     ) );
        macSetMng.setBranchNm    ( parsed.getString("brch_nm"        ) );
        macSetMng.setCircleType  ( parsed.getString("circle_type"    ) );
        macSetMng.setCdMngIp     ( parsed.getString("cd_mng_ip"      ) );
        macSetMng.setGwIp        ( parsed.getString("gw_ip"          ) );
        macSetMng.setBpIp        ( parsed.getString("bp_ip"          ) );
        macSetMng.setBpPort      ( parsed.getString("bp_port"        ) );
        macSetMng.setAtmIp       ( parsed.getString("atm_ip"         ) );
        macSetMng.setOldBranchCd ( parsed.getString("old_brch_cd"    ) );
        macSetMng.setOldMacNo    ( parsed.getString("old_mac_no"     ) );
        macSetMng.setOldOperType ( parsed.getString("old_oper_type"  ) );
        macSetMng.setOldSetPlace ( parsed.getString("old_site_nm"    ) );
        macSetMng.setOldSetAddr  ( parsed.getString("old_site_addr"  ) );
        macSetMng.setSetSerial   ( parsed.getString("set_seriald"    ) );
        macSetMng.setOldServiceCd( parsed.getString("old_service_cd" ) );
        macSetMng.setMacLoca     ( parsed.getString("mac_location"   ) );
        macSetMng.setOrgMemo     ( parsed.getString("memo"           ) );

        /*
         *  [대구은행]
         *   당일 최종수신으로 UPDATE조건이 있어 다음과 같이 분기한다.
         */
        if( parsed.getString("CM.org_cd").equals(MsgBrokerConst.DGB_CODE) )  {
            if( parsed.getString("info_type").equals(MsgBrokerConst.CD_NEW) ) {
                comPack.insertUpdateMacSet( safeData, MsgBrokerConst.DB_WORK_INSERT, macSetMng );
            }
            else if( parsed.getString("info_type").equals(MsgBrokerConst.CD_MOD) ) {
                comPack.insertUpdateMacSet( safeData, MsgBrokerConst.DB_WORK_UPDATE, macSetMng );
            }
            else if( parsed.getString("info_type").equals(MsgBrokerConst.CD_DEL) ) {
                comPack.insertUpdateMacSet( safeData, MsgBrokerConst.DB_WORK_UPDATE, macSetMng );
            }
        }
        else {
            comPack.insertUpdateMacSet( safeData, MsgBrokerConst.DB_WORK_INSERT, macSetMng );
        }
    }

}
