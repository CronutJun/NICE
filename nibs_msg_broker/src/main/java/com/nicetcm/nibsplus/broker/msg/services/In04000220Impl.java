package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtPenaltyListMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtPenaltyList;
import com.nicetcm.nibsplus.broker.msg.model.TCtPenaltyListSpec;

/**
 *
 * 0220 패널티 적용 명세
 * <pre>
 * MngIQ_SavePenaltyList( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04000220")
public class In04000220Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In04000220Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TMiscMapper tMiscMapper;

    @Autowired private TCtPenaltyListMapper tCtPenaltyListMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        if(parsed.getString("CM.msg_type").substring(2).equals("00")) {
            /**************************************************************************
             기관에서 들어온 요청전문일 경우
            **************************************************************************/

            if(parsed.getString("CM.service_gb").equals("1")) {
                /**************************************************************************
                    브랜드제휴의 경우
                **************************************************************************/


                if(MsgBrokerConst.KIUP_CODE.equals(parsed.getString("CM.org_cd"))) {
                    /**************************************************************************
                        [기업은행] 브랜드제휴 패널티적용명세
                         - 관리점코드와 코너(사이트)코드가 비어서 들어오므로,
                           데이터가 입력되기 전에 강제로 넣어준다.
                    **************************************************************************/

                    String siteCd = null;

                    try
                    {
                        siteCd = tMiscMapper.getSiteCdOrg096(parsed.getString("mac_no"));
                    } catch (Exception e)
                    {

                    }

                    if(siteCd == null || siteCd.equals("")) {
                        logger.info(String.format("[SavePenaltyList] 브랜드제휴 페널티 사이트코드 없음. org_cd[%s] mac_no[%s]", parsed.getString("CM.org_cd"), parsed.getString("mac_no")));
                    };

                    /*******************************************************************
                        통보유형(notice_type)을 분류한다.
                        - 0 : 명세통보(1차)
                        - 1 : 최종명세통보(2차)
                        NULL일 때, 전문을 처리하지 않음
                    ********************************************************************/

                    if(parsed.getString("notice_type").equals("0")) {

                        /* 1차 명세통보 */

                        boolean isDbDupData = false;

                        try
                        {
                            TCtPenaltyList tCtPenaltyList = tMiscMapper.generateSeqPenaltyList();
                            /*tCtPenaltyList.setSeqNo         (parsed.getString(""));*/
                            tCtPenaltyList.setOrgCd         (parsed.getString("096"));
                            tCtPenaltyList.setBranchCd       (parsed.getString("9600"));
                            tCtPenaltyList.setMacNo         (parsed.getString("mac_no"));
                            tCtPenaltyList.setSiteCd        (parsed.getString("site_cd"));
                            tCtPenaltyList.setSiteNm        (parsed.getString("site_nm"));
                            tCtPenaltyList.setCreateDate    (parsed.getString("create_date"));
                            tCtPenaltyList.setCreateTime    (parsed.getString("create_time"));
                            tCtPenaltyList.setCallDate      (parsed.getString("call_date").equals("") ? parsed.getString("create_date") : parsed.getString("call_date"));
                            tCtPenaltyList.setCallTime      (parsed.getString("call_time").equals("") ? parsed.getString("create_time") : parsed.getString("call_time"));
                            tCtPenaltyList.setArrivalDate   (parsed.getString("arrival_date"));
                            tCtPenaltyList.setArrivalTime   (parsed.getString("arrival_time"));
                            tCtPenaltyList.setRepairDate    (parsed.getString("repair_date"));
                            tCtPenaltyList.setRepairTime    (parsed.getString("repair_time"));
                            tCtPenaltyList.setMacModel      (parsed.getString("mac_model"));
                            tCtPenaltyList.setMemo          (parsed.getString("memo"));
                            tCtPenaltyList.setPenaltyAmt    (parsed.getLong("penalty_amt"));
                            tCtPenaltyList.setPenaltyYn     ("");
                            tCtPenaltyList.setPenaltyReason (null);
                            tCtPenaltyList.setUpdateUid     ("ETCmng");
                            tCtPenaltyList.setUpdateDate    (safeData.getDSysDate());
                            tCtPenaltyList.setOrgSendYn     ("0");
                            tCtPenaltyList.setTransDate     (null);
                            tCtPenaltyList.setOrgMsgNo      (parsed.getString("org_msg_no"));
                            tCtPenaltyList.setOrgCallCnt    (parsed.getString("org_call_cnt"));
                            tCtPenaltyList.setOrgCallClass  (parsed.getString("org_call_class"));
                            tCtPenaltyList.setRepairElapse  (parsed.getString("repair_elapse").trim());
                            tCtPenaltyList.setBrandOrgCd    (parsed.getString("CM.org_cd"));
                            tCtPenaltyList.setPenaltyCd     (parsed.getString("penalty_cd").trim());
                            tCtPenaltyList.setGroupErrorCd  (parsed.getString("group_error_cd"));
                            tCtPenaltyList.setStdErrorCd    (parsed.getString("std_error_cd"));
                            tCtPenaltyList.setErrorCd       (parsed.getString("error_cd"));
                            tCtPenaltyList.setExceptCd      (parsed.getString("except_cd"));

                            tCtPenaltyListMapper.insertSelective(tCtPenaltyList);

                        } catch( org.springframework.dao.DataIntegrityViolationException e ) {
                            isDbDupData = true;

                        } catch (Exception e)
                        {
                            logger.info("[T_CT_PENALTY_LIST] Insert Error(브랜드) [{}]", e.getMessage());
                            throw e;
                        }

                        if(isDbDupData) {
                            TCtPenaltyList tCtPenaltyList = new TCtPenaltyList();

                            tCtPenaltyList.setSiteCd        (parsed.getString("site_cd"));
                            tCtPenaltyList.setSiteNm        (parsed.getString("site_nm"));


                            tCtPenaltyList.setArrivalDate   (parsed.getString("arrival_date"));
                            tCtPenaltyList.setArrivalTime   (parsed.getString("arrival_time"));
                            tCtPenaltyList.setRepairDate    (parsed.getString("repair_date"));
                            tCtPenaltyList.setRepairTime    (parsed.getString("repair_time"));
                            tCtPenaltyList.setPenaltyAmt    (parsed.getLong("penalty_amt"));

                            tCtPenaltyList.setPenaltyYn     ("");
                            tCtPenaltyList.setPenaltyReason ("");
                            tCtPenaltyList.setTransDate     (null);
                            tCtPenaltyList.setGroupErrorCd  (parsed.getString("group_error_cd"));
                            tCtPenaltyList.setStdErrorCd    (parsed.getString("std_error_cd"));
                            tCtPenaltyList.setErrorCd       (parsed.getString("error_cd"));
                            tCtPenaltyList.setMacModel      (parsed.getString("mac_model"));
                            tCtPenaltyList.setMemo          (parsed.getString("memo"));
                            tCtPenaltyList.setUpdateUid     ("ETCmng");
                            tCtPenaltyList.setUpdateDate    (safeData.getDSysDate());
                            tCtPenaltyList.setOrgSendYn     ("0");
                            tCtPenaltyList.setRepairElapse  (parsed.getString("repair_elapse").trim());
                            tCtPenaltyList.setBrandOrgCd    (parsed.getString("CM.org_cd"));
                            tCtPenaltyList.setPenaltyCd     (parsed.getString("penalty_cd").trim());
                            tCtPenaltyList.setExceptCd      (parsed.getString("except_cd"));

                            TCtPenaltyListSpec tCtPenaltyListSpec = new TCtPenaltyListSpec();
                            tCtPenaltyListSpec.createCriteria()
                            .andOrgCdEqualTo("096")
                            .andBranchCdEqualTo("9600")
                            .andMacNoEqualTo(parsed.getString("mac_no"))
                            .andCreateDateEqualTo(parsed.getString("create_date"))
                            .andCreateTimeEqualTo(parsed.getString("create_time"))
                            .andErrorCdEqualTo(parsed.getString("error_cd"));


                            try
                            {
                                tCtPenaltyListMapper.updateBySpecSelective(tCtPenaltyList, tCtPenaltyListSpec);
                            } catch (Exception e)
                            {
                                logger.info("[T_CT_PENALTY_LIST] 1차 명세통보 Update Error(브랜드) [{}]", e.getMessage());
                                throw e;
                            }



                        }

                        logger.info( String.format("[T_CT_PENALTY_LIST] 1차 명세통보 Insert/Update 완료(브랜드) BRAND_ORG_CD[%s] MAC_NO[%s] CREATE_DATE[%s] CREATE_TIME[%s]",
                                        parsed.getString("CM.org_cd"), parsed.getString("mac_no"), parsed.getString("create_date"), parsed.getString("create_time"))  );
                    } else if(parsed.getString("notice_type").equals("2")) {
                        /* 2차 명세통보(최종) */
                    }



                }

            }
        }
    }
}
