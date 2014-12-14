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
import com.nicetcm.nibsplus.broker.msg.mapper.TCmChangeSiteInfoMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmSetSiteInfoMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmChangeSiteInfo;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetSiteInfo;

@Service("in02000310")
public class In02000310Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02000310Impl.class);

    @Autowired private StoredProcMapper        splMap;
    @Autowired private TCmChangeSiteInfoMapper cmChangeSiteInfoMap;
    @Autowired private TCmSetSiteInfoMapper    cmSetSiteInfoMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        if( parsed.getString("work_class").equals("3") ) { // 코너정보변경
            TCmChangeSiteInfo cmCSInfo = new TCmChangeSiteInfo();
            /*
             *  코너정보변경은 모두 INSERT 한다.
             */
            cmCSInfo.setRecvDate      ( parsed.getString("accept_date"      ) );
            cmCSInfo.setRecvTime      ( parsed.getString("accept_time"      ) );
            cmCSInfo.setOrgCd         ( parsed.getString("CM.org_cd"        ) );
            cmCSInfo.setOrgBranchCd   ( parsed.getString("org_brch_cd"      ) );
            cmCSInfo.setOrgBranchNm   ( parsed.getString("org_brch_nm"      ) );
            cmCSInfo.setOrgSiteCd     ( parsed.getString("org_site_cd"      ) );
            cmCSInfo.setOrgSiteNm     ( parsed.getString("org_site_nm"      ) );
            cmCSInfo.setDataType      ( parsed.getString("data_type"        ) );
            cmCSInfo.setPlaceType     ( parsed.getString("place_type"       ) );
            cmCSInfo.setJpType        ( parsed.getString("jp_type"          ) );
            cmCSInfo.setOperTimeType  ( parsed.getString("oper_time_type"   ) );
            cmCSInfo.setOperStartTime ( parsed.getString("oper_start_time"  ) );
            cmCSInfo.setOperEndTime   ( parsed.getString("oper_end_time"    ) );
            cmCSInfo.setOperDay       ( parsed.getString("oper_day"         ) );
            cmCSInfo.setOperCheckYn   ( parsed.getString("oper_check_yn"    ) );
            cmCSInfo.setGuardNm       ( parsed.getString("guard_nm"         ) );
            cmCSInfo.setSetAddr       ( parsed.getString("set_addr"         ) );
            cmCSInfo.setSetPlace      ( parsed.getString("set_place"        ) );
            cmCSInfo.setOperStartTimeH( parsed.getString("oper_start_time_h") );
            cmCSInfo.setOperEndTimeH  ( parsed.getString("oper_end_time_h"  ) );
            cmCSInfo.setOutStartTime  ( parsed.getString("out_start_time"   ) );
            cmCSInfo.setOutEndTime    ( parsed.getString("out_end_time"     ) );
            cmCSInfo.setOutStartTimeH ( parsed.getString("out_start_time_h" ) );
            cmCSInfo.setOutEndTimeH   ( parsed.getString("out_end_time_h"   ) );
            cmCSInfo.setOutComCd      ( parsed.getString("out_com_cd"       ) );
            cmCSInfo.setCleanComCd    ( parsed.getString("clean_com_cd"     ) );
            cmCSInfo.setSetDate       ( parsed.getString("set_date"         ) );
            try {
                cmChangeSiteInfoMap.insert( cmCSInfo );
            }
            catch( Exception e ) {
                logger.warn( "[T_CM_CHANGE_SITE_INFO] Save Error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        else { // 코너 신규/폐쇄
            TCmSetSiteInfo cmSSInfo = new TCmSetSiteInfo();

            cmSSInfo.setOrgCd         ( parsed.getString("CM.org_cd"        ) );
            cmSSInfo.setWorkSeq       ( parsed.getString("work_seq"         ) );
            cmSSInfo.setWorkClass     ( parsed.getString("work_class"       ) );
            cmSSInfo.setChangeNo      ( parsed.getShort ("change_no"        ) );
            cmSSInfo.setDataType      ( parsed.getString("data_type"        ) );
            cmSSInfo.setOrgBranchCd   ( parsed.getString("org_brch_cd"      ) );
            cmSSInfo.setOrgBranchNm   ( parsed.getString("org_brch_nm"      ) );
            cmSSInfo.setOrgSiteCd     ( parsed.getString("org_site_cd"      ) );
            cmSSInfo.setOrgSiteNm     ( parsed.getString("org_site_nm"      ) );
            cmSSInfo.setPlaceType     ( parsed.getString("place_type"       ) );
            cmSSInfo.setJpType        ( parsed.getString("jp_type"          ) );
            cmSSInfo.setSpecialType   ( parsed.getString("special_type"     ) );
            cmSSInfo.setOperTimeType  ( parsed.getString("oper_time_type"   ) );
            cmSSInfo.setOperStartTime ( parsed.getString("oper_start_time"  ) );
            cmSSInfo.setOperEndTime   ( parsed.getString("oper_end_time"    ) );
            cmSSInfo.setOperDay       ( parsed.getString("oper_day"         ) );
            cmSSInfo.setOperCheckYn   ( parsed.getString("oper_check_yn"    ) );
            cmSSInfo.setServiceFee    ( parsed.getString("service_fee"      ) );
            cmSSInfo.setGuardCd       ( parsed.getString("guard_cd"         ) );
            cmSSInfo.setGuardNm       ( parsed.getString("guard_nm"         ) );
            cmSSInfo.setFacilRentYn   ( parsed.getString("facil_rent_yn"    ) );
            cmSSInfo.setSetAddr       ( parsed.getString("set_addr"         ) );
            cmSSInfo.setSetPlace      ( parsed.getString("set_place"        ) );
            cmSSInfo.setMsg           ( parsed.getString("msg"              ) );
            cmSSInfo.setOperStartTimeH( parsed.getString("oper_start_time_h") );
            cmSSInfo.setOperEndTimeH  ( parsed.getString("oper_end_time_h"  ) );
            cmSSInfo.setOutStartTime  ( parsed.getString("out_start_time"   ) );
            cmSSInfo.setOutEndTime    ( parsed.getString("out_end_time"     ) );
            cmSSInfo.setOutStartTimeH ( parsed.getString("out_start_time_h" ) );
            cmSSInfo.setOutEndTimeH   ( parsed.getString("out_end_time_h"   ) );
            cmSSInfo.setOutComCd      ( parsed.getString("out_com_cd"       ) );
            cmSSInfo.setCleanComCd    ( parsed.getString("clean_com_cd"     ) );
            cmSSInfo.setSetDate       ( parsed.getString("set_date"         ) );

            try {
                cmSetSiteInfoMap.insert( cmSSInfo );
            }
            catch( org.springframework.dao.DataIntegrityViolationException de ) {
                try {
                    cmSetSiteInfoMap.updateByPrimaryKeySelective( cmSSInfo );
                }
                catch( Exception e ) {
                    logger.warn( "[T_CM_SET_SITE_INFO] Save Error [{}]", e.getLocalizedMessage() );
                    throw e;
                }
            }
            catch( Exception e ) {
                logger.warn( "[T_CM_SET_SITE_INFO] Save Error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
    }

}
