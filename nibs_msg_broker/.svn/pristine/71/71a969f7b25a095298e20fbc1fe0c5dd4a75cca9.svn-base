package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 신규기기정보
 *
 * <pre>
 * MngSM_SaveMacNewInfo
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
import com.nicetcm.nibsplus.broker.msg.mapper.TCtMacInfoNewMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtMacInfoNew;
import com.nicetcm.nibsplus.broker.msg.model.TCtMacInfoNewSpec;

@Service("in02000111")
public class In02000111Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02000111Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TCtMacInfoNewMapper macInfoNewMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCtMacInfoNew macInfoNew = new TCtMacInfoNew();

        macInfoNew.setReqDate      ( parsed.getString("req_date"       ) ); // 설치요청일
        macInfoNew.setOrgCd        ( parsed.getString("CM.org_cd"      ) ); // 기관코드
        macInfoNew.setBranchCd     ( parsed.getString("brch_cd"        ) ); // 지점코드
        macInfoNew.setSiteNm       ( parsed.getString("site_nm"        ) ); // 코너명
        macInfoNew.setBranchAdminNm( parsed.getString("brch_mng_nm"    ) ); // 영업점담당자성명
        macInfoNew.setBranchTelNo  ( parsed.getString("brch_mng_tel"   ) ); // 영업점담당자전화
        macInfoNew.setPlaceType    ( parsed.getString("place_type"     ) ); // 장소구분코드
        macInfoNew.setBoothType    ( parsed.getString("booth_type"     ) ); // 부스형태
        macInfoNew.setFar          ( parsed.getString("distance"       ) ); // 영업점과의거리
        macInfoNew.setSetPlace     ( parsed.getString("set_place"      ) ); // 설치장소
        macInfoNew.setSetAddr      ( parsed.getString("set_addr"       ) ); // 설치주소
        macInfoNew.setContactNm    ( parsed.getString("optr_nm"        ) ); // 실사협의자성명
        macInfoNew.setContactTelNo ( parsed.getString("optr_tel"       ) ); // 실사협의자전화번호
        macInfoNew.setAtmCnt       ( parsed.getString("atm_cnt"        ) ); // ATM기기대수
        macInfoNew.setCdCnt        ( parsed.getString("cd_cnt"         ) ); // CD기기대수
        macInfoNew.setOperStartTime( parsed.getString("oper_start_time") ); // 운영시작시각
        macInfoNew.setOperEndTime  ( parsed.getString("oper_end_time"  ) ); // 운영종료시각
        macInfoNew.setElecFeeYn    ( parsed.getString("elec_fee_type"  ) ); // 전기료여부(기업은행[0:없음,1:영업점부담])
        macInfoNew.setDepositAmt   ( parsed.getString("deposit_amt"    ) ); // 보증금
        macInfoNew.setRentFee      ( parsed.getString("rent_fee"       ) ); // 월임차료
        macInfoNew.setMngFee       ( parsed.getString("mng_fee"        ) ); // 월관리비
        macInfoNew.setSetReqCd     ( parsed.getString("set_rsn_cd"     ) ); // 설치사유코드
        macInfoNew.setMemo         ( parsed.getString("memo"           ) ); // 신청의견
        macInfoNew.setUpdateDate   ( safeData.getDSysDate()              );
        macInfoNew.setUpdateUid    ( "online"                            );

        try {
            macInfoNewMap.insert( macInfoNew );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                TCtMacInfoNewSpec spec = new TCtMacInfoNewSpec();
                spec.createCriteria().andReqDateEqualTo( macInfoNew.getReqDate() )
                                     .andOrgCdEqualTo( macInfoNew.getOrgCd() )
                                     .andBranchCdEqualTo( macInfoNew.getBranchCd() )
                                     .andSiteNmEqualTo( macInfoNew.getSiteNm() );
                macInfoNewMap.updateBySpec( macInfoNew, spec );
            }
            catch( Exception e ) {
                logger.warn( ">>> [MngSM_SaveMacNewInfo] INSERT/UPDATE ERROR [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.warn( ">>> [MngSM_SaveMacNewInfo] INSERT/UPDATE ERROR [{}]", e.getLocalizedMessage() );
            throw e;
        }
    }
}
