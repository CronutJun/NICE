package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 기기 설치/철수기기정보
 *
 * <pre>
 * MngSM_SaveSetMacInfo
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
import com.nicetcm.nibsplus.broker.msg.mapper.TCmSetMacInfoMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetMacInfo;

@Service("in02000320")
public class In02000320Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02000320Impl.class);

    @Autowired private StoredProcMapper    splMap;
    @Autowired private TCmSetMacInfoMapper cmSetMacInfoMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmSetMacInfo cmSetMacInfo = new TCmSetMacInfo();

        cmSetMacInfo.setOrgCd           ( parsed.getString("CM.org_cd"        ) );
        cmSetMacInfo.setWorkSeq         ( parsed.getString("work_seq"         ) );
        cmSetMacInfo.setWorkClass       ( parsed.getString("work_class"       ) );
        cmSetMacInfo.setCloseType       ( parsed.getString("close_type"       ) );
        cmSetMacInfo.setMacSeqNo        ( parsed.getString("mac_seq_no"       ) );
        cmSetMacInfo.setChangeNo        ( parsed.getShort ("change_no"        ) );
        cmSetMacInfo.setDataType        ( parsed.getString("data_type"        ) );
        cmSetMacInfo.setOrgBranchCd     ( parsed.getString("org_brch_cd"      ) );
        cmSetMacInfo.setOrgSiteCd       ( parsed.getString("org_site_cd"      ) );
        cmSetMacInfo.setMacNo           ( parsed.getString("mac_no"           ) );
        cmSetMacInfo.setMadeComCd       ( parsed.getString("made_com_cd"      ) );
        cmSetMacInfo.setMacModel        ( parsed.getString("mac_model"        ) );
        cmSetMacInfo.setMacRentYn       ( parsed.getString("mac_rent_yn"      ) );
        cmSetMacInfo.setMacSetType      ( parsed.getString("mac_set_type"     ) );
        cmSetMacInfo.setMacFee          ( parsed.getString("mac_fee"          ) );
        cmSetMacInfo.setGwIp            ( parsed.getString("gw_ip"            ) );
        cmSetMacInfo.setMacIp           ( parsed.getString("mac_ip"           ) );
        cmSetMacInfo.setSubnetMask      ( parsed.getString("subnet_mask"      ) );
        cmSetMacInfo.setExpectDate      ( parsed.getString("expect_date"      ) );
        cmSetMacInfo.setExpectTime      ( parsed.getString("expect_time"      ) );
        cmSetMacInfo.setCloseOrgBranchCd( parsed.getString("close_org_brch_cd") );
        cmSetMacInfo.setCloseOrgSiteCd  ( parsed.getString("close_org_site_cd") );
        cmSetMacInfo.setCloseMacNo      ( parsed.getString("close_mac_no"     ) );
        cmSetMacInfo.setCloseMadeComCd  ( parsed.getString("close_made_com_cd") );
        cmSetMacInfo.setCloseMacModel   ( parsed.getString("close_mac_model"  ) );
        cmSetMacInfo.setCloseMacRentYn  ( parsed.getString("close_mac_rent_yn") );

        try {
            cmSetMacInfoMap.insert( cmSetMacInfo );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                cmSetMacInfoMap.updateByPrimaryKeySelective(  cmSetMacInfo );
            }
            catch( Exception e) {
                logger.info( "[T_CM_SET_MAC_INFO] Update Error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.info( "[T_CM_SET_MAC_INFO] Update Error [{}]", e.getLocalizedMessage() );
            throw e;
        }
    }

}
