package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 변경기기정보
 *
 * <pre>
 * MngSM_SaveMacModifyInfo
 * </pre>
 *
 *           2014. 08. 06    K.D.J.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtMacInfoModMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtMacInfoMod;
import com.nicetcm.nibsplus.broker.msg.model.TCtMacInfoModSpec;


@Service("in02000161")
public class In02000161Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02000161Impl.class);

    @Autowired private StoredProcMapper    splMap;
    @Autowired private TCtMacInfoModMapper macInfoModMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCtMacInfoMod macInfoMod = new TCtMacInfoMod();

        macInfoMod.setReqDate      ( parsed.getString("req_date"     ) );      //  설치요청일
        macInfoMod.setOrgCd        ( parsed.getString("CM.org_cd"    ) );      //  기관코드
        macInfoMod.setBranchCd     ( parsed.getString("brch_cd"      ) );      //  지점코드
        macInfoMod.setSiteCd       ( parsed.getString("site_cd"      ) );      //  사이트코드
        macInfoMod.setPlaceType    ( parsed.getString("place_type"   ) );      //  장소구분코드
        macInfoMod.setBranchAdminNm( parsed.getString("mng_nm"       ) );      //  영업점담당자성명
        macInfoMod.setBranchTelNo  ( parsed.getString("mng_tel"      ) );      //  영업점담당자전화
        macInfoMod.setWorkDate     ( parsed.getString("work_date"    ) );      //  업무희망일
        macInfoMod.setSiteNm       ( parsed.getString("set_place"    ) );      //  신청장소명
        macInfoMod.setSetAddr      ( parsed.getString("set_addr"     ) );      //  신청장소주소
        macInfoMod.setBoothType    ( parsed.getString("booth_type"   ) );      //  부스형태
        macInfoMod.setMngComCd     ( parsed.getString("mng_com_cd"   ) );      //  관리업체코드
        macInfoMod.setModNm        ( parsed.getString("new_set_place") );      //  변경장소명
        macInfoMod.setModAddr      ( parsed.getString("new_set_addr" ) );      //  변경장소주소
        macInfoMod.setSubmitCd     ( parsed.getString("req_code"     ) );      //  신청구분코드
        macInfoMod.setDetail       ( parsed.getString("detail"       ) );      //  상세내역
        macInfoMod.setMemo         ( parsed.getString("memo"         ) );      //  신청의견
        macInfoMod.setNextBranchCd ( parsed.getString("next_brch_cd" ) );      //  전입관리점코드
        /*
         * 랜드제휴(03)이면, 전문필드[코너코드]에 기기번호가 들어온다.
         */
        if( parsed.getString("place_type").equals("03") ) {
            macInfoMod.setMacNo( parsed.getString("site_cd") );
        }
        macInfoMod.setUpdateDate   ( safeData.getDSysDate()            );
        macInfoMod.setUpdateUid    ( "online"                          );

        try {
            macInfoModMap.insert( macInfoMod );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            TCtMacInfoModSpec spec = new TCtMacInfoModSpec();
            spec.createCriteria().andReqDateEqualTo( macInfoMod.getReqDate() )
                                 .andOrgCdEqualTo( macInfoMod.getOrgCd() )
                                 .andBranchCdEqualTo( macInfoMod.getBranchCd() )
                                 .andSiteCdEqualTo( macInfoMod.getSiteCd() );
            try {
                macInfoModMap.updateBySpecSelective( macInfoMod, spec );
            }
            catch( Exception e ) {
                logger.warn( ">>> [MngSM_SaveMacModifyInfo] INSERT/UPDATE ERROR [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.warn( ">>> [MngSM_SaveMacModifyInfo] INSERT/UPDATE ERROR [{}]", e.getLocalizedMessage() );
            throw e;
        }
    }

}
