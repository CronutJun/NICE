package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 시설물 물품 정보
 *
 * <pre>
 * MngSM_SaveSetFacItem
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
import com.nicetcm.nibsplus.broker.msg.mapper.TCmSetFacItemMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetFacItem;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetFacItemSpec;

@Service("in02000370")
public class In02000370Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02000370Impl.class);

    @Autowired private StoredProcMapper    splMap;
    @Autowired private TCmSetFacItemMapper cmSetFacItemMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmSetFacItem cmSetFacItem = new TCmSetFacItem();

        /*
         * data_type = '1' 신규라면 insert 처리 단 dup 발생시 update
         * data_type = '3' 삭제라면 use_yn = '0', close_date = sysdate로 처리
         */
        if( parsed.getString("data_type").equals("1") ) {
            cmSetFacItem.setOrgCd     ( parsed.getString("CM.org_cd"   ) );
            cmSetFacItem.setFacType   ( parsed.getString("aft_fac_type") );
            cmSetFacItem.setFacCd     ( parsed.getString("aft_fac_cd"  ) );
            cmSetFacItem.setFacNm     ( parsed.getString("aft_fac_nm"  ) );
            cmSetFacItem.setFacComNm  ( parsed.getString("aft_com_nm"  ) );
            cmSetFacItem.setRentFee   ( parsed.getString("aft_rent_fee") );
            cmSetFacItem.setUseYn     ( "1"                              );
            cmSetFacItem.setInsertDate( safeData.getDSysDate()           );
            cmSetFacItem.setCloseDate ( null                             );
            cmSetFacItem.setUpdateDate( safeData.getDSysDate()           );
            cmSetFacItem.setUpdateUid ( "ETCIn"                          );
            try {
                cmSetFacItemMap.insert( cmSetFacItem );
            }
            catch( org.springframework.dao.DataIntegrityViolationException de ) {
                cmSetFacItem.setUpdateUid ( "ETCUp" );
                try {
                    cmSetFacItemMap.updateByPrimaryKeySelective( cmSetFacItem );
                }
                catch( Exception e ) {
                    logger.warn( "[T_CM_SET_FAC_ITEM] Update Error [{}]", e.getLocalizedMessage() );
                    throw e;
                }
            }
            catch( Exception e ) {
                logger.warn( "[T_CM_SET_FAC_ITEM] Insert Error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        else if( parsed.getString("data_type").equals("2") ) {
            TCmSetFacItemSpec spec = new TCmSetFacItemSpec();

            cmSetFacItem.setOrgCd     ( parsed.getString("CM.org_cd"   ) );
            cmSetFacItem.setFacType   ( parsed.getString("aft_fac_type") );
            cmSetFacItem.setFacCd     ( parsed.getString("aft_fac_cd"  ) );
            cmSetFacItem.setFacNm     ( parsed.getString("aft_fac_nm"  ) );
            cmSetFacItem.setFacComNm  ( parsed.getString("aft_com_nm"  ) );
            cmSetFacItem.setRentFee   ( parsed.getString("aft_rent_fee") );
            cmSetFacItem.setUseYn     ( "1"                              );
            cmSetFacItem.setCloseDate ( null                             );
            cmSetFacItem.setUpdateDate( safeData.getDSysDate()           );
            cmSetFacItem.setUpdateUid ( "ETCUp"                          );

            spec.createCriteria().andOrgCdEqualTo  ( parsed.getString("CM.org_cd"   ) )
                                 .andFacTypeEqualTo( parsed.getString("bef_fac_type") )
                                 .andFacCdEqualTo  ( parsed.getString("bef_fac_cd"  ) );
            try {
                cmSetFacItemMap.updateBySpecSelective( cmSetFacItem, spec );
            }
            catch( Exception e ) {
                logger.warn( "[T_CM_SET_FAC_ITEM] Update Error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        else if( parsed.getString("data_type").equals("3") ) {
            cmSetFacItem.setOrgCd     ( parsed.getString("CM.org_cd"   ) );
            cmSetFacItem.setFacType   ( parsed.getString("aft_fac_type") );
            cmSetFacItem.setFacCd     ( parsed.getString("aft_fac_cd"  ) );
            cmSetFacItem.setUseYn     ( "0"                              );
            cmSetFacItem.setCloseDate ( safeData.getDSysDate()           );
            cmSetFacItem.setUpdateDate( safeData.getDSysDate()           );
            cmSetFacItem.setUpdateUid ( "ETCUp"                          );

            try {
                cmSetFacItemMap.updateByPrimaryKeySelective( cmSetFacItem );
            }
            catch( Exception e ) {
                logger.warn( "[T_CM_SET_FAC_ITEM] Update Error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        else {
            throw new Exception("invalid data_type");
        }
    }

}
