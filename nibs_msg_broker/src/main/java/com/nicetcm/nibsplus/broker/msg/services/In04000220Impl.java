package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
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
                            tCtPenaltyList.setOrgCd         ("096");
                            tCtPenaltyList.setBranchCd      ("9600");
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
                        TCtPenaltyList tCtPenaltyList = new TCtPenaltyList();
                        tCtPenaltyList.setUpdateUid     ("ETCmng");
                        tCtPenaltyList.setUpdateDate    (safeData.getDSysDate());
                        tCtPenaltyList.setOrgSendYn     ("2");
                        tCtPenaltyList.setExceptYnConfirm(parsed.getString("except_yn_confirm").equals("1") ? "Y" : "N");
                        tCtPenaltyList.setExceptYnRsn(parsed.getString("except_yn_reason"));
                        tCtPenaltyList.setFinPenaltyAmt(String.valueOf(parsed.getLong("fin_penalty_amt")));

                        TCtPenaltyListSpec tCtPenaltyListSpec = new TCtPenaltyListSpec();
                        tCtPenaltyListSpec.createCriteria()
                        .andOrgCdEqualTo("096")
                        .andBranchCdEqualTo("9600")
                        .andMacNoEqualTo(parsed.getString("mac_no"))
                        .andCreateDateEqualTo(parsed.getString("create_date"))
                        .andCreateTimeEqualTo(parsed.getString("create_time"))
                        .andErrorCdEqualTo(parsed.getString("error_cd").trim());

                        try
                        {
                            tCtPenaltyListMapper.updateBySpecSelective(tCtPenaltyList, tCtPenaltyListSpec);
                        } catch (Exception e)
                        {
                            logger.info("[T_CT_PENALTY_LIST] 2차 명세통보 Update 실패(브랜드) [{}]", e.getMessage());
                            throw e;
                        }

                        logger.info( String.format("[T_CT_PENALTY_LIST] 2차 명세통보 Update 완료(브랜드) BRAND_ORG_CD[%s] MAC_NO[%s] CREATE_DATE[%s] CREATE_TIME[%s]",
                                        parsed.getString("CM.org_cd"), parsed.getString("mac_no"), parsed.getString("create_date"), parsed.getString("create_time")  ));
                    } else {
                        logger.info("[T_CT_PENALTY_LIST] 통보유형(통보/최종통보)코드 없음");
                        throw new MsgBrokerException(-1);
                    }


                //endof 기업은행
                } else {
                    /**************************************************************************
                     [기타기관] 브랜드제휴 패널티적용명세
                    **************************************************************************/
                    boolean isDbDupData = false;

                    try
                    {
                        TCtPenaltyList tCtPenaltyList = tMiscMapper.generateSeqPenaltyList();
                        /*tCtPenaltyList.setSeqNo         (parsed.getString(""));*/
                        tCtPenaltyList.setOrgCd         ("096");
                        tCtPenaltyList.setBranchCd      ("9600");
                        tCtPenaltyList.setMacNo         (parsed.getString("mac_no"));
                        tCtPenaltyList.setSiteCd        (parsed.getString("site_cd"));
                        tCtPenaltyList.setSiteNm        (parsed.getString("site_nm"));
                        tCtPenaltyList.setCreateDate    (parsed.getString("create_date"));
                        tCtPenaltyList.setCreateTime    (parsed.getString("create_time"));
                        tCtPenaltyList.setCallDate      (parsed.getString("create_date"));
                        tCtPenaltyList.setCallTime      (parsed.getString("create_time"));
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
                        tCtPenaltyList.setPenaltyReason (null);
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
                            logger.info("[T_CT_PENALTY_LIST] Update Error(브랜드) [{}]", e.getMessage());
                            throw e;
                        }



                    }

                    logger.info( String.format("[T_CT_PENALTY_LIST] Insert/Update Complete(브랜드) BRAND_ORG_CD[%s] MAC_NO[%s] CREATE_DATE[%s] CREATE_TIME[%s]",
                                    parsed.getString("CM.org_cd"), parsed.getString("mac_no"), parsed.getString("create_date"), parsed.getString("create_time"))  );



                }
            //endof 브랜드제휴의 경우
            } else {
                /**************************************************************************
                 일괄관리의 경우
                **************************************************************************/

                if(MsgBrokerConst.KIUP_CODE.equals(parsed.getString("CM.org_cd"))) {
                    /*************************************************************************
                     [기업은행] 통보유형에 따라 프로세스가 다름
                    *************************************************************************/
                    if(parsed.getString("notice_type").equals("0")) {
                        /* 1차 명세통보 */
                        boolean isDbDupData = false;

                        try
                        {
                            TCtPenaltyList tCtPenaltyList = tMiscMapper.generateSeqPenaltyList();
                            /*tCtPenaltyList.setSeqNo         (parsed.getString(""));*/
                            tCtPenaltyList.setOrgCd         (parsed.getString("CM.org_cd"));
                            tCtPenaltyList.setBranchCd      (parsed.getString("brch_cd"));
                            tCtPenaltyList.setMacNo         (parsed.getString("mac_no"));
                            tCtPenaltyList.setSiteCd        (parsed.getString("site_cd"));
                            tCtPenaltyList.setSiteNm        (parsed.getString("site_nm"));
                            tCtPenaltyList.setCreateDate    (parsed.getString("create_date"));
                            tCtPenaltyList.setCreateTime    (parsed.getString("create_time"));
                            tCtPenaltyList.setCallDate      (parsed.getString("call_date"));
                            tCtPenaltyList.setCallTime      (parsed.getString("call_time"));
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
                            tCtPenaltyList.setRepairElapse  (tMiscMapper.getElapseTime(parsed.getString("arrival_date") + parsed.getString("arrival_time"), parsed.getString("create_date") + parsed.getString("create_time")));
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
                            logger.info("[T_CT_PENALTY_LIST] 1차 통보 Insert Error [{}]", e.getMessage());
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
                            tCtPenaltyList.setRepairElapse  (tMiscMapper.getElapseTime(parsed.getString("arrival_date") + parsed.getString("arrival_time"), parsed.getString("create_date") + parsed.getString("create_time")));
                            tCtPenaltyList.setGroupErrorCd  (parsed.getString("group_error_cd"));
                            tCtPenaltyList.setStdErrorCd    (parsed.getString("std_error_cd"));
                            tCtPenaltyList.setExceptCd      (parsed.getString("except_cd"));

                            TCtPenaltyListSpec tCtPenaltyListSpec = new TCtPenaltyListSpec();
                            tCtPenaltyListSpec.createCriteria()
                            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                            .andBranchCdEqualTo(parsed.getString("brch_cd"))
                            .andMacNoEqualTo(parsed.getString("mac_no"))
                            .andCreateDateEqualTo(parsed.getString("create_date"))
                            .andCreateTimeEqualTo(parsed.getString("create_time"))
                            .andCallDateEqualTo(parsed.getString("call_date").equals("") ? parsed.getString("create_date") : parsed.getString("call_date"))
                            .andCallTimeEqualTo(parsed.getString("call_time").equals("") ? parsed.getString("create_time") : parsed.getString("call_time"));


                            try
                            {
                                tCtPenaltyListMapper.updateBySpecSelective(tCtPenaltyList, tCtPenaltyListSpec);
                            } catch (Exception e)
                            {
                                logger.info("[T_CT_PENALTY_LIST] 1차 통보 Update Error [{}]", e.getMessage());
                                throw e;
                            }



                        }



                    } else if(parsed.getString("notice_type").equals("2")) {
                        /* 2차 명세통보(최종) */

                        TCtPenaltyList tCtPenaltyList = new TCtPenaltyList();
                        tCtPenaltyList.setFinPenaltyAmt(String.valueOf(parsed.getLong("penalty_amt")));
                        tCtPenaltyList.setUpdateUid     ("ETCmng");
                        tCtPenaltyList.setUpdateDate    (safeData.getDSysDate());
                        tCtPenaltyList.setOrgSendYn     ("2");
                        tCtPenaltyList.setExceptYnConfirm(parsed.getString("except_yn_confirm").equals("1") ? "Y" : "N");
                        tCtPenaltyList.setExceptYnRsn(parsed.getString("except_yn_reason"));


                        TCtPenaltyListSpec tCtPenaltyListSpec = new TCtPenaltyListSpec();
                        tCtPenaltyListSpec.createCriteria()
                        .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                        .andBranchCdEqualTo(parsed.getString("brch_cd"))
                        .andMacNoEqualTo(parsed.getString("mac_no"))
                        .andCreateDateEqualTo(parsed.getString("create_date"))
                        .andCreateTimeEqualTo(parsed.getString("create_time"))
                        .andCallDateEqualTo(parsed.getString("call_date").equals("") ? parsed.getString("create_date") : parsed.getString("call_date"))
                        .andCallTimeEqualTo(parsed.getString("call_time").equals("") ? parsed.getString("create_time") : parsed.getString("call_time"));

                        try
                        {
                            tCtPenaltyListMapper.updateBySpecSelective(tCtPenaltyList, tCtPenaltyListSpec);
                        } catch (Exception e)
                        {
                            logger.info("[T_CT_PENALTY_LIST] 2차 명세통보 Update Error [{}]", e.getMessage());
                            throw e;
                        }
                    } else {
                        /* 통보유형 없음. 등록하지 않는다. */
                        logger.info("[T_CT_PENALTY_LIST] 통보유형(통보/최종통보)코드 없음");
                        throw new MsgBrokerException(-1);
                    }

                //endof 기업은행
                } else {
                    /*************************************************************************
                     [기타기관] 일괄 패널티적용명세
                     *************************************************************************/

                    boolean isDbDupData = false;

                    try
                    {
                        TCtPenaltyList tCtPenaltyList = tMiscMapper.generateSeqPenaltyList();
                        /*tCtPenaltyList.setSeqNo         (parsed.getString(""));*/
                        tCtPenaltyList.setOrgCd         (parsed.getString("CM.org_cd"));
                        tCtPenaltyList.setBranchCd      (parsed.getString("brch_cd"));
                        tCtPenaltyList.setMacNo         (parsed.getString("mac_no"));
                        tCtPenaltyList.setSiteCd        (parsed.getString("site_cd"));
                        tCtPenaltyList.setSiteNm        (parsed.getString("site_nm"));
                        tCtPenaltyList.setCreateDate    (parsed.getString("create_date"));
                        tCtPenaltyList.setCreateTime    (parsed.getString("create_time"));
                        tCtPenaltyList.setCallDate      (parsed.getString("call_date"));
                        tCtPenaltyList.setCallTime      (parsed.getString("call_time"));
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
                        logger.info("[T_CT_PENALTY_LIST] Insert Error [{}]", e.getMessage());
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
                        tCtPenaltyList.setPenaltyCd     (parsed.getString("penalty_cd"));


                        tCtPenaltyList.setGroupErrorCd  (parsed.getString("group_error_cd"));
                        tCtPenaltyList.setStdErrorCd    (parsed.getString("std_error_cd"));
                        tCtPenaltyList.setErrorCd       (parsed.getString("error_cd"));

                        TCtPenaltyListSpec tCtPenaltyListSpec = new TCtPenaltyListSpec();
                        tCtPenaltyListSpec.createCriteria()
                        .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                        .andBranchCdEqualTo(parsed.getString("brch_cd"))
                        .andMacNoEqualTo(parsed.getString("mac_no"))
                        .andCreateDateEqualTo(parsed.getString("create_date"))
                        .andCreateTimeEqualTo(parsed.getString("create_time"))
                        .andCallDateEqualTo(parsed.getString("call_date").equals("") ? parsed.getString("create_date") : parsed.getString("call_date"))
                        .andCallTimeEqualTo(parsed.getString("call_time").equals("") ? parsed.getString("create_time") : parsed.getString("call_time"));


                        try
                        {
                            tCtPenaltyListMapper.updateBySpecSelective(tCtPenaltyList, tCtPenaltyListSpec);
                        } catch (Exception e)
                        {
                            logger.info("[T_CT_PENALTY_LIST] Update Error [{}]", e.getMessage());
                            throw e;
                        }
                    }

                }//endof [기타기관] 일괄 패널티적용명세

            }//endof 일괄관리의 경우

        //endof 기관에서 들어온 요청전문일 경우
        } else {
            /**************************************************************************
             나이스에서 송신한 요청에 대한 응답일경우
             **************************************************************************/
            TCtPenaltyList tCtPenaltyList = new TCtPenaltyList();
            tCtPenaltyList.setOrgSendYn     ("1");

            TCtPenaltyListSpec tCtPenaltyListSpec = new TCtPenaltyListSpec();
            tCtPenaltyListSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("CM.service_gb").equals("1") ? "096" : parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("CM.service_gb").equals("1") ? "9600" : parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"))
            .andCreateDateEqualTo(parsed.getString("create_date"))
            .andCreateTimeEqualTo(parsed.getString("create_time"))
            .andCallDateEqualTo(parsed.getString("call_date").equals("") ? parsed.getString("create_date") : parsed.getString("call_date"))
            .andCallTimeEqualTo(parsed.getString("call_time").equals("") ? parsed.getString("create_time") : parsed.getString("call_time"));

            try
            {
                tCtPenaltyListMapper.updateBySpecSelective(tCtPenaltyList, tCtPenaltyListSpec);
            } catch (Exception e)
            {
                logger.info("[T_CT_PENALTY_LIST] Update Error [{}]", e.getMessage());
                throw e;
            }

            logger.info( String.format("[T_CT_PENALTY_LIST] Update Complete ORG_CD[%s] JIJUM_CD[%s] MAC_NO[%s] CREATE_DATE[%s] CREATE_TIME[%s]",
                            parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("create_date"), parsed.getString("create_time"))  );
        }//endof 나이스에서 송신한 요청에 대한 응답일경우
    }//end method
}
