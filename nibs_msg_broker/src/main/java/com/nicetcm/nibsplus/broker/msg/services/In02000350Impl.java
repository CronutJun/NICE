package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 시설물 설치/철수 정보
 *
 * <pre>
 * MngSM_SaveSetFacInfo
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
import com.nicetcm.nibsplus.broker.msg.mapper.TCmSetFacInfoMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetFacInfo;

@Service("in02000350")
public class In02000350Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02000350Impl.class);

    @Autowired private StoredProcMapper    splMap;
    @Autowired private TCmSetFacInfoMapper cmSetFacInfoMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmSetFacInfo cmSetFacInfo = new TCmSetFacInfo();

        cmSetFacInfo.setOrgCd           ( parsed.getString("CM.org_cd"        ) );
        cmSetFacInfo.setWorkSeq         ( parsed.getString("work_seq"         ) );
        cmSetFacInfo.setWorkClass       ( parsed.getString("work_class"       ) );
        cmSetFacInfo.setFacSeqNo        ( parsed.getString("fac_seq_no"       ) );
        cmSetFacInfo.setCloseType       ( parsed.getString("close_type"       ) );
        cmSetFacInfo.setChangeNo        ( parsed.getShort ("change_no"        ) );
        cmSetFacInfo.setOrgBranchCd     ( parsed.getString("org_brch_cd"      ) );
        cmSetFacInfo.setOrgSiteCd       ( parsed.getString("org_site_cd"      ) );
        cmSetFacInfo.setFacType         ( parsed.getString("fac_type"         ) );
        cmSetFacInfo.setFacCd           ( parsed.getString("fac_cd"           ) );
        cmSetFacInfo.setFacRentYn       ( parsed.getString("fac_rent_yn"      ) );
        cmSetFacInfo.setItemGubun       ( parsed.getString("item_gubun"       ) );
        cmSetFacInfo.setAssetSeqno      ( parsed.getString("asset_seqno"      ) );
        cmSetFacInfo.setFacIp           ( parsed.getString("fac_ip"           ) );
        cmSetFacInfo.setFacGw           ( parsed.getString("fac_gw"           ) );
        cmSetFacInfo.setFacSm           ( parsed.getString("fac_sm"           ) );
        cmSetFacInfo.setRentFee         ( parsed.getString("rent_fee"         ) );
        cmSetFacInfo.setDetailFacInfo   ( parsed.getString("detail_fac_info"  ) );
        cmSetFacInfo.setCloseOrgBranchCd( parsed.getString("close_org_brch_cd") );
        cmSetFacInfo.setCloseOrgSiteCd  ( parsed.getString("close_org_site_cd") );
        cmSetFacInfo.setCloseFacType    ( parsed.getString("close_fac_type"   ) );
        cmSetFacInfo.setCloseFacCd      ( parsed.getString("close_fac_cd"     ) );
        cmSetFacInfo.setCloseAssetSeqno ( parsed.getString("close_asset_seqno") );
        cmSetFacInfo.setUpdateDate      ( safeData.getDSysDate()                );
        cmSetFacInfo.setUpdateUid       ( "ETCIn"                               );
        cmSetFacInfo.setDataType        ( parsed.getString("data_type"        ) );

        /*
         * 변경신규 모두 insert 시도 후 데이터 중복 시 insert 하도록
         */
        try {
            cmSetFacInfoMap.insert( cmSetFacInfo );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            cmSetFacInfo.setUpdateUid       ( "ETCUp" );
            try {
                cmSetFacInfoMap.updateByPrimaryKeySelective( cmSetFacInfo );
            }
            catch( Exception e ) {
                logger.info( "[T_CM_SET_FAC_INFO] Update Error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.info( "[T_CM_SET_FAC_INFO] Update Error [{}]", e.getLocalizedMessage() );
            throw e;
        }
    }

}
