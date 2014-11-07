package com.nicetcm.nibsplus.broker.msg.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmCheckMasterMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmCommonMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmOrgMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmSiteMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorMngMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtSiteLockHistoryMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnKebCheckMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnOnsiteAmtMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnSendMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TPhMessagesMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmCheckMaster;
import com.nicetcm.nibsplus.broker.msg.model.TCmCheckMasterSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCmCommon;
import com.nicetcm.nibsplus.broker.msg.model.TCmCommonSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCmOrg;
import com.nicetcm.nibsplus.broker.msg.model.TCmSite;
import com.nicetcm.nibsplus.broker.msg.model.TCmSiteKey;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtSiteLockHistory;
import com.nicetcm.nibsplus.broker.msg.model.TFnKebCheck;
import com.nicetcm.nibsplus.broker.msg.model.TFnKebCheckSpec;
import com.nicetcm.nibsplus.broker.msg.model.TFnOnsiteAmt;
import com.nicetcm.nibsplus.broker.msg.model.TFnOnsiteAmtSpec;
import com.nicetcm.nibsplus.broker.msg.model.TFnSend;
import com.nicetcm.nibsplus.broker.msg.model.TFnSendSpec;
import com.nicetcm.nibsplus.broker.msg.model.TPhMessages;

/**
 *
 * 경비해제수신
 * <pre>
 * MngEM_SaveUnLockTime( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01000161")
public class In01000161Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCmCommonMapper tCmCommonMapper;

    @Autowired private TCtSiteLockHistoryMapper tCtSiteLockHistoryMapper;

    @Autowired private TMiscMapper tMiscMapper;

    @Autowired private TCmCheckMasterMapper tCmCheckMasterMapper;

    @Autowired private TPhMessagesMapper tPhMessagesMapper;

    @Autowired private TFnKebCheckMapper tFnKebCheckMapper;

    @Autowired private TFnOnsiteAmtMapper tFnOnsiteAmtMapper;

    @Autowired private TFnSendMapper tFnSendMapper;

    @Autowired private TCtErrorMngMapper tCtErrorMngMapper;

    @Autowired private TCmOrgMapper tCmOrgMapper;

    @Autowired private TCmSiteMapper tCmSiteMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmOrg  cmOrg        = null;
        TCmSite cmSite       = null;
        TCmSiteKey cmSiteKey = new TCmSiteKey();

        /* 도착통보 기관전송에 대한 응답을 받은경우 아무 처리 하지 않고 응답없음으로 return */
        if(MsgBrokerConst.EM_ANS.equals(parsed.getString("CM.msg_type"))) {
            throw new MsgBrokerException(-99);
        }

        String horg_cq_code = MsgBrokerConst.CQ_CODE;
        String horg_cp_code = MsgBrokerConst.CP_CODE;

        TCmCommonSpec tCmCommonSpec = new TCmCommonSpec();
        tCmCommonSpec.createCriteria()
        .andLargeCdEqualTo("2300")
        .andCdNm4EqualTo(parsed.getString("CM.org_cd").equals(horg_cq_code) ? horg_cp_code : parsed.getString("CM.org_cd"));

        String errMsg = "";
        List<TCmCommon> tCmCommonList = null;

        try
        {
            tCmCommonList = tCmCommonMapper.selectBySpec(tCmCommonSpec);
        } catch (Exception e)
        {
            errMsg = e.getMessage();
        }

        if(errMsg.equals("") == false || tCmCommonList == null || tCmCommonList.size() == 0) {
            logger.warn(String.format(">>> [SaveUnLockTime] (T_CM_COMMON) 경비사 코드[%s] 검색실패 [%.200s]", parsed.getString("CM.org_cd"), errMsg));
            throw new Exception(String.format(">>> [SaveUnLockTime] (T_CM_COMMON) 경비사 코드[%s] 검색실패 [%.200s]", parsed.getString("CM.org_cd"), errMsg));
        }

        /*===> 대구은행 경비사 사이트 등록 완료시 삭제 필요 2013.11.02 */
        if(MsgBrokerConst.DGB_CODE.equals(parsed.getString("org_cd")) && parsed.getString("brch_cd").length() == 3) {
            String szJijumCd = null;
            String szSiteCd = null;

            szJijumCd = "0" + parsed.getString("brch_cd");
            try
            {
                szSiteCd = comPack.fcGetMapSiteCd("0", "031", parsed.getString("brch_cd"), parsed.getString("site_cd"));


            } catch (Exception e)
            {
                logger.warn( ">>> [MngEM_SaveUnLockTime] 코너정보 mapping실패 [{}]", e.getMessage());
            }

            if(szSiteCd.equals("")) {
                logger.warn( ">>> [MngEM_SaveUnLockTime] mapping 테이블 데이터 없음 grd.jijum_cd[{}], grd.site_cd[{}]", parsed.getString("brch_cd"), parsed.getString("site_cd"));
            } else {
                logger.warn( ">>> [MngEM_SaveUnLockTime] 기초정보 mapping jijum_cd[{}->{}], site_cd[{}->{}]", parsed.getString("brch_cd"), szJijumCd, parsed.getString("site_cd"), szSiteCd );
            }
        }

        /*대구은행 경비사 사이트 등록 완료시 삭제 필요 2013.11.02 <===*/
        TCmCommon tCmCommon = tCmCommonList.get(0);

        TCtSiteLockHistory tCtSiteLockHistory = new TCtSiteLockHistory();
        tCtSiteLockHistory.setOrgCd     (parsed.getString("org_cd"));
        tCtSiteLockHistory.setBranchCd  (parsed.getString("brch_cd"));
        tCtSiteLockHistory.setSiteCd    (parsed.getString("site_cd"));
        tCtSiteLockHistory.setGuardComCd(tCmCommon.getSmallCd());
        tCtSiteLockHistory.setInoutDate (parsed.getString("arrival_date"));
        tCtSiteLockHistory.setInoutTime (parsed.getString("arrival_time"));
        tCtSiteLockHistory.setSetYn     (parsed.getShort ("inout_type"));
        tCtSiteLockHistory.setWorkType  (parsed.getString("arrival_type"));
        tCtSiteLockHistory.setCardNo    (parsed.getString("card_no"));
        tCtSiteLockHistory.setUpdateDate(safeData.getDSysDate());

        try
        {
            tCtSiteLockHistoryMapper.insertSelective(tCtSiteLockHistory);

            logger.warn( ">>> [SaveUnLockTime] (T_CT_SITE_LOCK_HISTORY) INSERT OK" );

        } catch( org.springframework.dao.DataIntegrityViolationException e ) {
            logger.warn( ">>> [SaveUnLockTime] (T_CT_SITE_LOCK_HISTORY) INSERT 중복 ERROR [{}]", e.getMessage());
            /* 전문 중복으로 인한 INSERT 실패시에도 LG로 전문 송신은 해야 한다. */
        } catch (Exception e)
        {
            logger.warn( ">>> [SaveUnLockTime] (T_CT_SITE_LOCK_HISTORY) INSERT ERROR [{}]", e.getMessage());
        }



        /* 현장  부가업무 연동              */
        /* 세트 해제일 경우, 아래 루트를 수행한다.  */
        if(parsed.getString("inout_type").startsWith("2")) {
            /* 해당테이블의 대상 Data를 검색하여 Update 치고 push 테이블에도 insert 처리 */

            TCmCheckMaster tCmCheckMasterParam = new TCmCheckMaster();
            tCmCheckMasterParam.setOrgCd(parsed.getString("org_cd"));
            tCmCheckMasterParam.setBranchCd(parsed.getString("brch_cd"));
            tCmCheckMasterParam.setSiteCd(parsed.getString("site_cd"));
            tCmCheckMasterParam.setAcceptDate(parsed.getString("arrival_date"));
            tCmCheckMasterParam.setAcceptTime(parsed.getString("arrival_time"));

            List<TCmCheckMaster> tCmCheckMasterList = null;

            try
            {
                tCmCheckMasterList = tMiscMapper.selectCmCheckMaster(tCmCheckMasterParam);

                //if(tCmCheckMasterList == null || tCmCheckMasterList.size() == 0) {
                //    throw new Exception("tCmCheckMasterList No Data");
                //}

            } catch (Exception e)
            {
                logger.warn( ">>> [T_CM_CHECK_MASTER] Cursor Open Error [{}]", e.getMessage());
                throw e;
            }

            if(tCmCheckMasterList != null || tCmCheckMasterList.size() > 0) {
                for(TCmCheckMaster tCmCheckMaster : tCmCheckMasterList) {
                    TCmCheckMaster updateTCmCheckMaster = new TCmCheckMaster();
                    updateTCmCheckMaster.setArrivalTime(parsed.getString("arrival_time").substring(0, 4));

                    TCmCheckMasterSpec tCmCheckMasterSpec = new TCmCheckMasterSpec();
                    tCmCheckMasterSpec.createCriteria()
                    .andOrgCdEqualTo(parsed.getString("org_cd"))
                    .andBranchCdEqualTo(parsed.getString("brch_cd"))
                    .andSiteCdEqualTo(parsed.getString("site_cd"))
                    .andAcceptDateEqualTo(parsed.getString("arrival_date"))
                    .andAcceptTimeEqualTo(tCmCheckMaster.getAcceptTime())
                    .andMemberIdEqualTo(tCmCheckMaster.getMemberId());


                    try
                    {
                        tCmCheckMasterMapper.updateBySpecSelective(updateTCmCheckMaster, tCmCheckMasterSpec);

                        logger.warn(" [T_CM_CHECK_MASTER] Update Complete.");

                        /* 푸시 테이블에 insert 해주기 위한 작업*/

                        String userIdx = null;

                        try
                        {
                            try {
                                cmOrg = tCmOrgMapper.selectByPrimaryKey( parsed.getString("org_cd") );
                                if( cmOrg == null ) {
                                    cmOrg = new TCmOrg();
                                    logger.warn(String.format("기관없음: %s", parsed.getString("org_cd")));
                                }
                                cmSiteKey.setOrgCd   ( parsed.getString("org_cd")  );
                                cmSiteKey.setBranchCd( parsed.getString("brch_cd") );
                                cmSiteKey.setSiteCd  ( parsed.getString("site_cd") );
                                cmSite = tCmSiteMapper.selectByPrimaryKey( cmSiteKey );
                                if( cmSite == null ) {
                                    cmSite = new TCmSite();
                                    logger.warn(String.format("사이트없음: %s-%s-%s", parsed.getString("org_cd"), parsed.getString("brch_cd"), parsed.getString("site_cd")));
                                }
                            }
                            catch( Exception e ) {
                                if( cmOrg == null ) cmOrg = new TCmOrg();
                                if( cmSite == null ) cmSite = new TCmSite();
                                logger.warn("[기관명: {}, 사이트명: {} 조회 에러]{}", parsed.getString("org_cd"), parsed.getString("site_cd"), e.getMessage());
                            }
                            userIdx = tMiscMapper.getUserIdx(tCmCheckMaster.getMemberId());

                            String v_PushMsg = null;
                            v_PushMsg = String.format("#A01@%s@%s@%s@%s@%s@A@%s@%.*s", cmOrg.getOrgNm(), parsed.getString("brch_cd"), cmSite.getSiteNm(), parsed.getString("arrival_date"), tCmCheckMaster.getAcceptTime(), tCmCheckMaster.getMemberId(), 4, parsed.getString("arrival_time"));

                            TPhMessages tPhMessages = tMiscMapper.getSeqPhMessages();

                            tPhMessages.setUserIdx      (Long.parseLong(userIdx));
                            tPhMessages.setMessage      (v_PushMsg);
                            tPhMessages.setRead         ("0");
                            tPhMessages.setStatus       ("queued");
                            tPhMessages.setDeliveryDate ("");
                            tPhMessages.setDeliveryTime ("");
                            tPhMessages.setCreateDate   (MsgBrokerLib.SysDate());
                            tPhMessages.setCreateTime   (MsgBrokerLib.SysTime());
                            tPhMessages.setWorker       (tCmCheckMaster.getMemberId());
                            tPhMessages.setSender       ("");
                            tPhMessages.setInsertGubun  ("0003");
                            tPhMessages.setEtcGubun1    ("");
                            tPhMessages.setEtcGubun2    ("");
                            tPhMessages.setEtcGubun3    ("");

                            try
                            {
                                tPhMessagesMapper.insertSelective(tPhMessages);

                                logger.warn(" [t_ph_messages] insert Complete.");

                            } catch (Exception e)
                            {
                                logger.warn( ">>> [t_ph_messages] insert 실패 [{}]", e.getMessage());
                                /* 실패시에도 그냥 처리 */
                            }

                            if(userIdx == null) {
                                throw new Exception("userIdx is null");
                            }
                        } catch (Exception e)
                        {
                            logger.warn( ">>> [tb_phs_user] 검색 실패 [{}]", e.getMessage());
                            /* 실패시에도 그냥 처리 */
                        }

                    } catch (Exception e)
                    {
                        logger.warn(" [T_CM_CHECK_MASTER] Update Err [{}]", e.getMessage() );
                    }

                }//end for (EXEC SQL CLOSE CURCMA;)
            }//end list


            if(MsgBrokerConst.KEB_CODE.equals(parsed.getString("org_cd"))) {
                /* 해당테이블의 대상 Data를 검색하여 Update 치고 push 테이블에도 insert 처리 */
                TFnKebCheckSpec tFnKebCheckSpec = new TFnKebCheckSpec();
                tFnKebCheckSpec.createCriteria()
                .andOrgCdEqualTo(parsed.getString("org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andSiteCdEqualTo(parsed.getString("site_cd"))
                .andBaseDateEqualTo(parsed.getString("arrival_date"))
                .andStartTimeGreaterThanOrEqualTo(parsed.getString("arrival_time").substring(0, 4))
                .andFinishTimeIsNull()
                .andStartTimeIsNotNull();

                List<TFnKebCheck> tFnKebCheckList = null;

                try
                {
                    tFnKebCheckList = tFnKebCheckMapper.selectBySpec(tFnKebCheckSpec);

                    //if(tCmCheckMasterList == null || tCmCheckMasterList.size() == 0) {
                    //    throw new Exception("tCmCheckMasterList No Data");
                    //}

                } catch (Exception e)
                {
                    logger.warn( ">>> [t_fn_keb_check] Cursor Open Error [{}]", e.getMessage());
                    throw e;
                }

                if(tFnKebCheckList != null || tFnKebCheckList.size() > 0) {

                    for(TFnKebCheck tFnKebCheck : tFnKebCheckList) {

                        TFnKebCheck updateTFnKebCheck = new TFnKebCheck();
                        updateTFnKebCheck.setFinishTime(parsed.getString("arrival_time").substring(0, 4));

                        TFnKebCheckSpec tFnKebCheckSpec2 = new TFnKebCheckSpec();
                        tFnKebCheckSpec2.createCriteria()
                        .andOrgCdEqualTo(parsed.getString("org_cd"))
                        .andBranchCdEqualTo(parsed.getString("brch_cd"))
                        .andMacNoEqualTo(tFnKebCheck.getMacNo())
                        .andBaseDateEqualTo(parsed.getString("arrival_date"));


                        try
                        {
                            tFnKebCheckMapper.updateBySpecSelective(updateTFnKebCheck, tFnKebCheckSpec2);

                            logger.warn(" [t_fn_keb_check] Update Complete.");

                            /* 푸시 테이블에 insert 해주기 위한 작업*/

                            String userIdx = null;

                            try
                            {
                                try {
                                    cmOrg = tCmOrgMapper.selectByPrimaryKey( parsed.getString("org_cd") );
                                    if( cmOrg == null ) {
                                        cmOrg = new TCmOrg();
                                        logger.warn(String.format("기관없음: %s", parsed.getString("org_cd")));
                                    }
                                }
                                catch( Exception e ) {
                                    if( cmOrg == null ) cmOrg = new TCmOrg();
                                    logger.warn("[기관명: {} 조회 에러]{}", parsed.getString("org_cd"), e.getMessage());
                                }
                                userIdx = tMiscMapper.getUserIdx(tFnKebCheck.getMemberId());

                                String v_PushMsg = null;
                                v_PushMsg = String.format("#A06@%s@%s@%s@%s@F@%s@%.*s", parsed.getString("arrival_date"), cmOrg.getOrgNm(), parsed.getString("brch_cd"), tFnKebCheck.getMacNo(), tFnKebCheck.getMemberId(), 4, parsed.getString("arrival_time"));

                                TPhMessages tPhMessages = tMiscMapper.getSeqPhMessages();

                                tPhMessages.setUserIdx      (Long.parseLong(userIdx));
                                tPhMessages.setMessage      (v_PushMsg);
                                tPhMessages.setRead         ("0");
                                tPhMessages.setStatus       ("queued");
                                tPhMessages.setDeliveryDate ("");
                                tPhMessages.setDeliveryTime ("");
                                tPhMessages.setCreateDate   (MsgBrokerLib.SysDate());
                                tPhMessages.setCreateTime   (MsgBrokerLib.SysTime());
                                tPhMessages.setWorker       (tFnKebCheck.getMemberId());
                                tPhMessages.setSender       ("");
                                tPhMessages.setInsertGubun  ("0005");
                                tPhMessages.setEtcGubun1    ("");
                                tPhMessages.setEtcGubun2    ("");
                                tPhMessages.setEtcGubun3    ("");

                                try
                                {
                                    tPhMessagesMapper.insertSelective(tPhMessages);

                                    logger.warn(" [t_ph_messages] insert Complete.");

                                } catch (Exception e)
                                {
                                    logger.warn( ">>> [t_ph_messages] insert 실패 [{}]", e.getMessage());
                                    /* 실패시에도 그냥 처리 */
                                }

                                if(userIdx == null) {
                                    throw new Exception("userIdx is null");
                                }
                            } catch (Exception e)
                            {
                                logger.warn( ">>> [tb_phs_user] 검색 실패 [{}]", e.getMessage());
                                /* 실패시에도 그냥 처리 */
                            }

                        } catch (Exception e)
                        {
                            logger.warn(" [T_CM_CHECK_MASTER] Update Err [{}]", e.getMessage() );
                        }
                    }//end for (EXEC SQL CLOSE CURCMA;)
                }//end list

            }//end of KEB_CODE

            /* 해당테이블의 대상 Data를 검색하여 Update 치고 push 테이블에도 insert 처리 */
            TFnOnsiteAmtSpec tFnOnsiteAmtSpec = new TFnOnsiteAmtSpec();
            tFnOnsiteAmtSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andSiteCdEqualTo(parsed.getString("site_cd"))
            .andSendDateEqualTo(parsed.getString("arrival_date"))
            .andStartTimeGreaterThanOrEqualTo(parsed.getString("arrival_time").substring(0, 4))
            .andFinishTimeIsNull()
            .andStartTimeIsNotNull();

            List<TFnOnsiteAmt> tFnOnsiteAmtList = null;

            try
            {
                tFnOnsiteAmtList = tFnOnsiteAmtMapper.selectBySpec(tFnOnsiteAmtSpec);

                //if(tCmCheckMasterList == null || tCmCheckMasterList.size() == 0) {
                //    throw new Exception("tCmCheckMasterList No Data");
                //}

            } catch (Exception e)
            {
                logger.warn( ">>> [t_fn_onsite_amt] Cursor Open Error [{}]", e.getMessage());
                throw e;
            }

            if(tFnOnsiteAmtList != null || tFnOnsiteAmtList.size() > 0) {

                for(TFnOnsiteAmt tFnOnsiteAmt : tFnOnsiteAmtList) {

                    TFnOnsiteAmt updateTFnOnsiteAmt = new TFnOnsiteAmt();
                    updateTFnOnsiteAmt.setFinishTime(parsed.getString("arrival_time").substring(0, 4));

                    TFnOnsiteAmtSpec tFnOnsiteAmtSpec2 = new TFnOnsiteAmtSpec();
                    tFnOnsiteAmtSpec2.createCriteria()
                    .andOrgCdEqualTo(parsed.getString("org_cd"))
                    .andBranchCdEqualTo(parsed.getString("brch_cd"))
                    .andMacNoEqualTo(tFnOnsiteAmt.getMacNo())
                    .andSendDateEqualTo(parsed.getString("arrival_date"));

                    try
                    {
                        tFnOnsiteAmtMapper.updateBySpecSelective(updateTFnOnsiteAmt, tFnOnsiteAmtSpec2);

                        logger.warn(" [t_fn_onsite_amt] Update Complete.");

                        /* 푸시 테이블에 insert 해주기 위한 작업*/

                        String userIdx = null;

                        try
                        {
                            try {
                                cmOrg = tCmOrgMapper.selectByPrimaryKey( parsed.getString("org_cd") );
                                if( cmOrg == null ) {
                                    cmOrg = new TCmOrg();
                                    logger.warn(String.format("기관없음: %s", parsed.getString("org_cd")));
                                }
                            }
                            catch( Exception e ) {
                                if( cmOrg == null ) cmOrg = new TCmOrg();
                                logger.warn("[기관명: {} 조회 에러]{}", parsed.getString("org_cd"), e.getMessage());
                            }
                            userIdx = tMiscMapper.getUserIdx(tFnOnsiteAmt.getMemberId());

                            String v_PushMsg = null;
                            v_PushMsg = String.format("#A06@%s@%s@%s@%s@F@%s@%.*s", parsed.getString("arrival_date"), cmOrg.getOrgNm(), parsed.getString("brch_cd"), tFnOnsiteAmt.getMacNo(), tFnOnsiteAmt.getMemberId(), 4, parsed.getString("arrival_time"));

                            TPhMessages tPhMessages = tMiscMapper.getSeqPhMessages();

                            tPhMessages.setUserIdx      (Long.parseLong(userIdx));
                            tPhMessages.setMessage      (v_PushMsg);
                            tPhMessages.setRead         ("0");
                            tPhMessages.setStatus       ("queued");
                            tPhMessages.setDeliveryDate ("");
                            tPhMessages.setDeliveryTime ("");
                            tPhMessages.setCreateDate   (MsgBrokerLib.SysDate());
                            tPhMessages.setCreateTime   (MsgBrokerLib.SysTime());
                            tPhMessages.setWorker       (tFnOnsiteAmt.getMemberId());
                            tPhMessages.setSender       ("");
                            tPhMessages.setInsertGubun  ("0004");
                            tPhMessages.setEtcGubun1    ("");
                            tPhMessages.setEtcGubun2    ("");
                            tPhMessages.setEtcGubun3    ("");

                            try
                            {
                                tPhMessagesMapper.insertSelective(tPhMessages);

                                logger.warn(" [t_ph_messages] insert Complete.");

                            } catch (Exception e)
                            {
                                logger.warn( ">>> [t_ph_messages] insert 실패 [{}]", e.getMessage());
                                /* 실패시에도 그냥 처리 */
                            }

                            if(userIdx == null) {
                                throw new Exception("userIdx is null");
                            }
                        } catch (Exception e)
                        {
                            logger.warn( ">>> [tb_phs_user] 검색 실패 [{}]", e.getMessage());
                            /* 실패시에도 그냥 처리 */
                        }

                    } catch (Exception e)
                    {
                        logger.warn(" [T_CM_CHECK_MASTER] Update Err [{}]", e.getMessage() );
                    }
                }//end for (EXEC SQL CLOSE CURCMA;)
            }//end list

            /* 해당테이블의 대상 Data를 검색하여 Update 치고 push 테이블에도 insert 처리 */
            TFnSendSpec tFnSendSpec = new TFnSendSpec();
            tFnSendSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andSiteCdEqualTo(parsed.getString("site_cd"))
            .andSendDateEqualTo(parsed.getString("arrival_date"))
            .andStartTimeGreaterThanOrEqualTo(parsed.getString("arrival_time").substring(0, 4))
            .andArrivalTimeIsNull()
            .andCompleteTimeIsNull()
            .andSendTypeEqualTo("2")
            .andSendOwnerEqualTo("2")
            .andStartTimeIsNotNull();

            List<TFnSend> tFnSendList = null;

            try
            {
                tFnSendList = tFnSendMapper.selectBySpec(tFnSendSpec);

                //if(tCmCheckMasterList == null || tCmCheckMasterList.size() == 0) {
                //    throw new Exception("tCmCheckMasterList No Data");
                //}

            } catch (Exception e)
            {
                logger.warn( ">>> [t_fn_send] Cursor Open Error [{}]", e.getMessage());
                throw e;
            }

            if(tFnSendList != null || tFnSendList.size() > 0) {

                for(TFnSend tFnSend : tFnSendList) {

                    TFnSend updateTFnSend = new TFnSend();
                    updateTFnSend.setArrivalTime(parsed.getString("arrival_time").substring(0, 4));

                    TFnSendSpec tFnSendSpec2 = new TFnSendSpec();
                    tFnSendSpec2.createCriteria()
                    .andSendDateEqualTo(parsed.getString("arrival_date"))
                    .andOrgCdEqualTo(parsed.getString("org_cd"))
                    .andBranchCdEqualTo(parsed.getString("brch_cd"))
                    .andMacNoEqualTo(tFnSend.getMacNo())
                    .andInsertTimeEqualTo(tFnSend.getInsertTime())
                    .andSendTypeEqualTo("2");

                    try
                    {
                        tFnSendMapper.updateBySpecSelective(updateTFnSend, tFnSendSpec2);

                        logger.warn(" [t_fn_send] Update Complete.");

                        /* 푸시 테이블에 insert 해주기 위한 작업*/

                        String userIdx = null;

                        try
                        {
                            try {
                                cmOrg = tCmOrgMapper.selectByPrimaryKey( parsed.getString("org_cd") );
                                if( cmOrg == null ) {
                                    cmOrg = new TCmOrg();
                                    logger.warn(String.format("기관없음: %s", parsed.getString("org_cd")));
                                }
                            }
                            catch( Exception e ) {
                                if( cmOrg == null ) cmOrg = new TCmOrg();
                                logger.warn("[기관명: {} 조회 에러]{}", parsed.getString("org_cd"), e.getMessage());
                            }
                            userIdx = tMiscMapper.getUserIdx(tFnSend.getCashTeamCd());

                            String v_PushMsg = null;
                            v_PushMsg = String.format("#A07@%s@%s@%s@%s@A@%s@%.*s", parsed.getString("arrival_date"), cmOrg.getOrgNm(), parsed.getString("brch_cd"), parsed.getString("site_cd"), tFnSend.getCashTeamCd(), 4, parsed.getString("arrival_time"));

                            TPhMessages tPhMessages = tMiscMapper.getSeqPhMessages();

                            tPhMessages.setUserIdx      (Long.parseLong(userIdx));
                            tPhMessages.setMessage      (v_PushMsg);
                            tPhMessages.setRead         ("0");
                            tPhMessages.setStatus       ("queued");
                            tPhMessages.setDeliveryDate ("");
                            tPhMessages.setDeliveryTime ("");
                            tPhMessages.setCreateDate   (MsgBrokerLib.SysDate());
                            tPhMessages.setCreateTime   (MsgBrokerLib.SysTime());
                            tPhMessages.setWorker       (tFnSend.getCashTeamCd());
                            tPhMessages.setSender       ("");
                            tPhMessages.setInsertGubun  ("0006");
                            tPhMessages.setEtcGubun1    ("");
                            tPhMessages.setEtcGubun2    ("");
                            tPhMessages.setEtcGubun3    ("");

                            try
                            {
                                tPhMessagesMapper.insertSelective(tPhMessages);

                                logger.warn(" [t_ph_messages] insert Complete.");

                            } catch (Exception e)
                            {
                                logger.warn( ">>> [t_ph_messages] insert 실패 [{}]", e.getMessage());
                                /* 실패시에도 그냥 처리 */
                            }

                            if(userIdx == null) {
                                throw new Exception("userIdx is null");
                            }
                        } catch (Exception e)
                        {
                            logger.warn( ">>> [tb_phs_user] 검색 실패 [{}]", e.getMessage());
                            /* 실패시에도 그냥 처리 */
                        }

                    } catch (Exception e)
                    {
                        logger.warn(" [t_fn_send] Update Err [{}]", e.getMessage() );
                    }
                }//end for (EXEC SQL CLOSE CURCMA;)
            }//end list

        //end of if(parsed.getString("inout_type").startsWith("2")) {
        } else if(parsed.getString("inout_type").startsWith("1")) { /* 경비설정-완료 */
            /* 해당테이블의 대상 Data를 검색하여 Update 치고 push 테이블에도 insert 처리 */


            TCmCheckMaster tCmCheckMasterParam = new TCmCheckMaster();
            tCmCheckMasterParam.setOrgCd(parsed.getString("org_cd"));
            tCmCheckMasterParam.setBranchCd(parsed.getString("brch_cd"));
            tCmCheckMasterParam.setSiteCd(parsed.getString("site_cd"));
            tCmCheckMasterParam.setAcceptDate(parsed.getString("arrival_date"));
            tCmCheckMasterParam.setAcceptTime(parsed.getString("arrival_time"));

            List<TCmCheckMaster> tCmCheckMasterList = null;

            try
            {
                tCmCheckMasterList = tMiscMapper.selectCmCheckMaster(tCmCheckMasterParam);

                //if(tCmCheckMasterList == null || tCmCheckMasterList.size() == 0) {
                //    throw new Exception("tCmCheckMasterList No Data");
                //}

            } catch (Exception e)
            {
                logger.warn( ">>> [T_CM_CHECK_MASTER] Cursor Open Error [{}]", e.getMessage());
                throw e;
            }

            if(tCmCheckMasterList != null || tCmCheckMasterList.size() > 0) {
                for(TCmCheckMaster tCmCheckMaster : tCmCheckMasterList) {
                    TCmCheckMaster updateTCmCheckMaster = new TCmCheckMaster();
                    updateTCmCheckMaster.setArrivalTime(parsed.getString("arrival_time").substring(0, 4));

                    TCmCheckMasterSpec tCmCheckMasterSpec = new TCmCheckMasterSpec();
                    tCmCheckMasterSpec.createCriteria()
                    .andOrgCdEqualTo(parsed.getString("org_cd"))
                    .andBranchCdEqualTo(parsed.getString("brch_cd"))
                    .andSiteCdEqualTo(parsed.getString("site_cd"))
                    .andAcceptDateEqualTo(parsed.getString("arrival_date"))
                    .andAcceptTimeEqualTo(tCmCheckMaster.getAcceptTime())
                    .andMemberIdEqualTo(tCmCheckMaster.getMemberId());


                    try
                    {
                        tCmCheckMasterMapper.updateBySpecSelective(updateTCmCheckMaster, tCmCheckMasterSpec);

                        logger.warn(" [T_CM_CHECK_MASTER] Update Complete.");

                        /* 푸시 테이블에 insert 해주기 위한 작업*/

                        String userIdx = null;

                        try
                        {
                            try {
                                cmOrg = tCmOrgMapper.selectByPrimaryKey( parsed.getString("org_cd") );
                                if( cmOrg == null ) {
                                    cmOrg = new TCmOrg();
                                    logger.warn(String.format("기관없음: %s", parsed.getString("org_cd")));
                                }
                                cmSiteKey.setOrgCd   ( parsed.getString("org_cd")  );
                                cmSiteKey.setBranchCd( parsed.getString("brch_cd") );
                                cmSiteKey.setSiteCd  ( parsed.getString("site_cd") );
                                cmSite = tCmSiteMapper.selectByPrimaryKey( cmSiteKey );
                                if( cmSite == null ) {
                                    cmSite = new TCmSite();
                                    logger.warn(String.format("사이트없음: %s-%s-%s", parsed.getString("org_cd"), parsed.getString("brch_cd"), parsed.getString("site_cd")));
                                }
                            }
                            catch( Exception e ) {
                                if( cmOrg == null ) cmOrg = new TCmOrg();
                                if( cmSite == null ) cmSite = new TCmSite();
                                logger.warn("[기관명: {}, 사이트명: {} 조회 에러]{}", parsed.getString("org_cd"), parsed.getString("site_cd"), e.getMessage());
                            }
                            userIdx = tMiscMapper.getUserIdx(tCmCheckMaster.getMemberId());

                            String v_PushMsg = null;
                            v_PushMsg = String.format("#A01@%s@%s@%s@%s@%s@A@%s@%.*s", cmOrg.getOrgNm(), parsed.getString("brch_cd"), cmSite.getSiteNm(), parsed.getString("arrival_date"), tCmCheckMaster.getAcceptTime(), tCmCheckMaster.getMemberId(), 4, parsed.getString("arrival_time"));

                            TPhMessages tPhMessages = tMiscMapper.getSeqPhMessages();

                            tPhMessages.setUserIdx      (Long.parseLong(userIdx));
                            tPhMessages.setMessage      (v_PushMsg);
                            tPhMessages.setRead         ("0");
                            tPhMessages.setStatus       ("queued");
                            tPhMessages.setDeliveryDate ("");
                            tPhMessages.setDeliveryTime ("");
                            tPhMessages.setCreateDate   (MsgBrokerLib.SysDate());
                            tPhMessages.setCreateTime   (MsgBrokerLib.SysTime());
                            tPhMessages.setWorker       (tCmCheckMaster.getMemberId());
                            tPhMessages.setSender       ("");
                            tPhMessages.setInsertGubun  ("0003");
                            tPhMessages.setEtcGubun1    ("");
                            tPhMessages.setEtcGubun2    ("");
                            tPhMessages.setEtcGubun3    ("");

                            try
                            {
                                tPhMessagesMapper.insertSelective(tPhMessages);

                                logger.warn(" [t_ph_messages] insert Complete.");

                            } catch (Exception e)
                            {
                                logger.warn( ">>> [t_ph_messages] insert 실패 [{}]", e.getMessage());
                                /* 실패시에도 그냥 처리 */
                            }

                            if(userIdx == null) {
                                throw new Exception("userIdx is null");
                            }
                        } catch (Exception e)
                        {
                            logger.warn( ">>> [tb_phs_user] 검색 실패 [{}]", e.getMessage());
                            /* 실패시에도 그냥 처리 */
                        }

                    } catch (Exception e)
                    {
                        logger.warn(" [T_CM_CHECK_MASTER] Update Err [{}]", e.getMessage() );
                    }

                }//end for (EXEC SQL CLOSE CURCMA;)
            }//end list

            if(MsgBrokerConst.KEB_CODE.equals(parsed.getString("org_cd"))) {
                /* 해당테이블의 대상 Data를 검색하여 Update 치고 push 테이블에도 insert 처리 */
                TFnKebCheckSpec tFnKebCheckSpec = new TFnKebCheckSpec();
                tFnKebCheckSpec.createCriteria()
                .andOrgCdEqualTo(parsed.getString("org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andSiteCdEqualTo(parsed.getString("site_cd"))
                .andBaseDateEqualTo(parsed.getString("arrival_date"))
                .andStartTimeGreaterThanOrEqualTo(parsed.getString("arrival_time").substring(0, 4))
                .andFinishTimeIsNull()
                .andStartTimeIsNotNull();

                List<TFnKebCheck> tFnKebCheckList = null;

                try
                {
                    tFnKebCheckList = tFnKebCheckMapper.selectBySpec(tFnKebCheckSpec);

                    //if(tCmCheckMasterList == null || tCmCheckMasterList.size() == 0) {
                    //    throw new Exception("tCmCheckMasterList No Data");
                    //}

                } catch (Exception e)
                {
                    logger.warn( ">>> [t_fn_keb_check] Cursor Open Error [{}]", e.getMessage());
                    throw e;
                }

                if(tFnKebCheckList != null || tFnKebCheckList.size() > 0) {

                    for(TFnKebCheck tFnKebCheck : tFnKebCheckList) {

                        TFnKebCheck updateTFnKebCheck = new TFnKebCheck();
                        updateTFnKebCheck.setFinishTime(parsed.getString("arrival_time").substring(0, 4));

                        TFnKebCheckSpec tFnKebCheckSpec2 = new TFnKebCheckSpec();
                        tFnKebCheckSpec2.createCriteria()
                        .andOrgCdEqualTo(parsed.getString("org_cd"))
                        .andBranchCdEqualTo(parsed.getString("brch_cd"))
                        .andMacNoEqualTo(tFnKebCheck.getMacNo())
                        .andBaseDateEqualTo(parsed.getString("arrival_date"));


                        try
                        {
                            tFnKebCheckMapper.updateBySpecSelective(updateTFnKebCheck, tFnKebCheckSpec2);

                            logger.warn(" [t_fn_keb_check] Update Complete.");

                            /* 푸시 테이블에 insert 해주기 위한 작업*/

                            String userIdx = null;

                            try
                            {
                                try {
                                    cmOrg = tCmOrgMapper.selectByPrimaryKey( parsed.getString("org_cd") );
                                    if( cmOrg == null ) {
                                        cmOrg = new TCmOrg();
                                        logger.warn(String.format("기관없음: %s", parsed.getString("org_cd")));
                                    }
                                }
                                catch( Exception e ) {
                                    if( cmOrg == null ) cmOrg = new TCmOrg();
                                    logger.warn("[기관명: {} 조회 에러]{}", parsed.getString("org_cd"), e.getMessage());
                                }
                                userIdx = tMiscMapper.getUserIdx(tFnKebCheck.getMemberId());

                                String v_PushMsg = null;
                                v_PushMsg = String.format("#A06@%s@%s@%s@%s@F@%s@%.*s", parsed.getString("arrival_date"), cmOrg.getOrgNm(), parsed.getString("brch_cd"), tFnKebCheck.getMacNo(), tFnKebCheck.getMemberId(), 4, parsed.getString("arrival_time"));

                                TPhMessages tPhMessages = tMiscMapper.getSeqPhMessages();

                                tPhMessages.setUserIdx      (Long.parseLong(userIdx));
                                tPhMessages.setMessage      (v_PushMsg);
                                tPhMessages.setRead         ("0");
                                tPhMessages.setStatus       ("queued");
                                tPhMessages.setDeliveryDate ("");
                                tPhMessages.setDeliveryTime ("");
                                tPhMessages.setCreateDate   (MsgBrokerLib.SysDate());
                                tPhMessages.setCreateTime   (MsgBrokerLib.SysTime());
                                tPhMessages.setWorker       (tFnKebCheck.getMemberId());
                                tPhMessages.setSender       ("");
                                tPhMessages.setInsertGubun  ("0005");
                                tPhMessages.setEtcGubun1    ("");
                                tPhMessages.setEtcGubun2    ("");
                                tPhMessages.setEtcGubun3    ("");

                                try
                                {
                                    tPhMessagesMapper.insertSelective(tPhMessages);

                                    logger.warn(" [t_ph_messages] insert Complete.");

                                } catch (Exception e)
                                {
                                    logger.warn( ">>> [t_ph_messages] insert 실패 [{}]", e.getMessage());
                                    /* 실패시에도 그냥 처리 */
                                }

                                if(userIdx == null) {
                                    throw new Exception("userIdx is null");
                                }
                            } catch (Exception e)
                            {
                                logger.warn( ">>> [tb_phs_user] 검색 실패 [{}]", e.getMessage());
                                /* 실패시에도 그냥 처리 */
                            }

                        } catch (Exception e)
                        {
                            logger.warn(" [T_CM_CHECK_MASTER] Update Err [{}]", e.getMessage() );
                        }
                    }//end for (EXEC SQL CLOSE CURCMA;)
                }//end list

            }//end of KEB_CODE

            /* 해당테이블의 대상 Data를 검색하여 Update 치고 push 테이블에도 insert 처리 */
            TFnOnsiteAmtSpec tFnOnsiteAmtSpec = new TFnOnsiteAmtSpec();
            tFnOnsiteAmtSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andSiteCdEqualTo(parsed.getString("site_cd"))
            .andSendDateEqualTo(parsed.getString("arrival_date"))
            .andStartTimeGreaterThanOrEqualTo(parsed.getString("arrival_time").substring(0, 4))
            .andFinishTimeIsNull()
            .andStartTimeIsNotNull();

            List<TFnOnsiteAmt> tFnOnsiteAmtList = null;

            try
            {
                tFnOnsiteAmtList = tFnOnsiteAmtMapper.selectBySpec(tFnOnsiteAmtSpec);

                //if(tCmCheckMasterList == null || tCmCheckMasterList.size() == 0) {
                //    throw new Exception("tCmCheckMasterList No Data");
                //}

            } catch (Exception e)
            {
                logger.warn( ">>> [t_fn_onsite_amt] Cursor Open Error [{}]", e.getMessage());
                throw e;
            }

            if(tFnOnsiteAmtList != null || tFnOnsiteAmtList.size() > 0) {

                for(TFnOnsiteAmt tFnOnsiteAmt : tFnOnsiteAmtList) {

                    TFnOnsiteAmt updateTFnOnsiteAmt = new TFnOnsiteAmt();
                    updateTFnOnsiteAmt.setFinishTime(parsed.getString("arrival_time").substring(0, 4));

                    TFnOnsiteAmtSpec tFnOnsiteAmtSpec2 = new TFnOnsiteAmtSpec();
                    tFnOnsiteAmtSpec2.createCriteria()
                    .andOrgCdEqualTo(parsed.getString("org_cd"))
                    .andBranchCdEqualTo(parsed.getString("brch_cd"))
                    .andMacNoEqualTo(tFnOnsiteAmt.getMacNo())
                    .andSendDateEqualTo(parsed.getString("arrival_date"));

                    try
                    {
                        tFnOnsiteAmtMapper.updateBySpecSelective(updateTFnOnsiteAmt, tFnOnsiteAmtSpec2);

                        logger.warn(" [t_fn_onsite_amt] Update Complete.");

                        /* 푸시 테이블에 insert 해주기 위한 작업*/

                        String userIdx = null;

                        try
                        {
                            try {
                                cmOrg = tCmOrgMapper.selectByPrimaryKey( parsed.getString("org_cd") );
                                if( cmOrg == null ) {
                                    cmOrg = new TCmOrg();
                                    logger.warn(String.format("기관없음: %s", parsed.getString("org_cd")));
                                }
                            }
                            catch( Exception e ) {
                                if( cmOrg == null ) cmOrg = new TCmOrg();
                                logger.warn("[기관명: {} 조회 에러]{}", parsed.getString("org_cd"), e.getMessage());
                            }
                            userIdx = tMiscMapper.getUserIdx(tFnOnsiteAmt.getMemberId());

                            String v_PushMsg = null;
                            v_PushMsg = String.format("#A06@%s@%s@%s@%s@F@%s@%.*s", parsed.getString("arrival_date"), cmOrg.getOrgNm(), parsed.getString("brch_cd"), tFnOnsiteAmt.getMacNo(), tFnOnsiteAmt.getMemberId(), 4, parsed.getString("arrival_time"));

                            TPhMessages tPhMessages = tMiscMapper.getSeqPhMessages();

                            tPhMessages.setUserIdx      (Long.parseLong(userIdx));
                            tPhMessages.setMessage      (v_PushMsg);
                            tPhMessages.setRead         ("0");
                            tPhMessages.setStatus       ("queued");
                            tPhMessages.setDeliveryDate ("");
                            tPhMessages.setDeliveryTime ("");
                            tPhMessages.setCreateDate   (MsgBrokerLib.SysDate());
                            tPhMessages.setCreateTime   (MsgBrokerLib.SysTime());
                            tPhMessages.setWorker       (tFnOnsiteAmt.getMemberId());
                            tPhMessages.setSender       ("");
                            tPhMessages.setInsertGubun  ("0004");
                            tPhMessages.setEtcGubun1    ("");
                            tPhMessages.setEtcGubun2    ("");
                            tPhMessages.setEtcGubun3    ("");

                            try
                            {
                                tPhMessagesMapper.insertSelective(tPhMessages);

                                logger.warn(" [t_ph_messages] insert Complete.");

                            } catch (Exception e)
                            {
                                logger.warn( ">>> [t_ph_messages] insert 실패 [{}]", e.getMessage());
                                /* 실패시에도 그냥 처리 */
                            }

                            if(userIdx == null) {
                                throw new Exception("userIdx is null");
                            }
                        } catch (Exception e)
                        {
                            logger.warn( ">>> [tb_phs_user] 검색 실패 [{}]", e.getMessage());
                            /* 실패시에도 그냥 처리 */
                        }

                    } catch (Exception e)
                    {
                        logger.warn(" [T_CM_CHECK_MASTER] Update Err [{}]", e.getMessage() );
                    }
                }//end for (EXEC SQL CLOSE CURCMA;)
            }//end list

            /* 해당테이블의 대상 Data를 검색하여 Update 치고 push 테이블에도 insert 처리 */
            TFnSendSpec tFnSendSpec = new TFnSendSpec();
            tFnSendSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andSiteCdEqualTo(parsed.getString("site_cd"))
            .andSendDateEqualTo(parsed.getString("arrival_date"))
            .andStartTimeGreaterThanOrEqualTo(parsed.getString("arrival_time").substring(0, 4))
            .andArrivalTimeIsNull()
            .andCompleteTimeIsNull()
            .andSendTypeEqualTo("2")
            .andSendOwnerEqualTo("2")
            .andStartTimeIsNotNull();

            List<TFnSend> tFnSendList = null;

            try
            {
                tFnSendList = tFnSendMapper.selectBySpec(tFnSendSpec);

                //if(tCmCheckMasterList == null || tCmCheckMasterList.size() == 0) {
                //    throw new Exception("tCmCheckMasterList No Data");
                //}

            } catch (Exception e)
            {
                logger.warn( ">>> [t_fn_send] Cursor Open Error [{}]", e.getMessage());
                throw e;
            }

            if(tFnSendList != null || tFnSendList.size() > 0) {

                for(TFnSend tFnSend : tFnSendList) {

                    TFnSend updateTFnSend = new TFnSend();
                    updateTFnSend.setCompleteTime(parsed.getString("arrival_time").substring(0, 4));

                    TFnSendSpec tFnSendSpec2 = new TFnSendSpec();
                    tFnSendSpec2.createCriteria()
                    .andSendDateEqualTo(parsed.getString("arrival_date"))
                    .andOrgCdEqualTo(parsed.getString("org_cd"))
                    .andBranchCdEqualTo(parsed.getString("brch_cd"))
                    .andMacNoEqualTo(tFnSend.getMacNo())
                    .andInsertTimeEqualTo(tFnSend.getInsertTime())
                    .andSendTypeEqualTo("2");

                    try
                    {
                        tFnSendMapper.updateBySpecSelective(updateTFnSend, tFnSendSpec2);

                        logger.warn(" [t_fn_send] Update Complete.");

                        /* 푸시 테이블에 insert 해주기 위한 작업*/

                        String userIdx = null;

                        try
                        {
                            try {
                                cmOrg = tCmOrgMapper.selectByPrimaryKey( parsed.getString("org_cd") );
                                if( cmOrg == null ) {
                                    cmOrg = new TCmOrg();
                                    logger.warn(String.format("기관없음: %s", parsed.getString("org_cd")));
                                }
                                cmSiteKey.setOrgCd   ( parsed.getString("org_cd")  );
                                cmSiteKey.setBranchCd( parsed.getString("brch_cd") );
                                cmSiteKey.setSiteCd  ( parsed.getString("site_cd") );
                                cmSite = tCmSiteMapper.selectByPrimaryKey( cmSiteKey );
                                if( cmSite == null ) {
                                    cmSite = new TCmSite();
                                    logger.warn(String.format("사이트없음: %s-%s-%s", parsed.getString("org_cd"), parsed.getString("brch_cd"), parsed.getString("site_cd")));
                                }
                            }
                            catch( Exception e ) {
                                if( cmOrg == null ) cmOrg = new TCmOrg();
                                if( cmSite == null ) cmSite = new TCmSite();
                                logger.warn("[기관명: {}, 사이트명: {} 조회 에러]{}", parsed.getString("org_cd"), parsed.getString("site_cd"), e.getMessage());
                            }
                            userIdx = tMiscMapper.getUserIdx(tFnSend.getCashTeamCd());

                            String v_PushMsg = null;
                            v_PushMsg = String.format("#A07@%s@%s@%s@%s@A@%s@%.*s", parsed.getString("arrival_date"), cmOrg.getOrgNm(), parsed.getString("brch_cd"), cmSite.getSiteNm(), tFnSend.getCashTeamCd(), 4, parsed.getString("arrival_time"));

                            TPhMessages tPhMessages = tMiscMapper.getSeqPhMessages();

                            tPhMessages.setUserIdx      (Long.parseLong(userIdx));
                            tPhMessages.setMessage      (v_PushMsg);
                            tPhMessages.setRead         ("0");
                            tPhMessages.setStatus       ("queued");
                            tPhMessages.setDeliveryDate ("");
                            tPhMessages.setDeliveryTime ("");
                            tPhMessages.setCreateDate   (MsgBrokerLib.SysDate());
                            tPhMessages.setCreateTime   (MsgBrokerLib.SysTime());
                            tPhMessages.setWorker       (tFnSend.getCashTeamCd());
                            tPhMessages.setSender       ("");
                            tPhMessages.setInsertGubun  ("0006");
                            tPhMessages.setEtcGubun1    ("");
                            tPhMessages.setEtcGubun2    ("");
                            tPhMessages.setEtcGubun3    ("");

                            try
                            {
                                tPhMessagesMapper.insertSelective(tPhMessages);

                                logger.warn(" [t_ph_messages] insert Complete.");

                            } catch (Exception e)
                            {
                                logger.warn( ">>> [t_ph_messages] insert 실패 [{}]", e.getMessage());
                                /* 실패시에도 그냥 처리 */
                            }

                            if(userIdx == null) {
                                throw new Exception("userIdx is null");
                            }
                        } catch (Exception e)
                        {
                            logger.warn( ">>> [tb_phs_user] 검색 실패 [{}]", e.getMessage());
                            /* 실패시에도 그냥 처리 */
                        }

                    } catch (Exception e)
                    {
                        logger.warn(" [t_fn_send] Update Err [{}]", e.getMessage() );
                    }
                }//end for (EXEC SQL CLOSE CURCMA;)
            }//end list

        } //end of if(parsed.getString("inout_type").startsWith("1")) {

        /* 출동 요청 도착 일 경우( 경비(1), 장애(2), 현송(3) 일 경우 ) 장애 테이블에 도착 및 완료 에 update */
        /* 환경일 경우에만 제외 '4') */
        if(parsed.getString("arrival_type").startsWith("4") == false) {
            /* 경비설정('1')일 경우 Lock_date, Lock_time에 해제('2')일 경우 UnLock_date UnLock_time에 */
            /* 해당 사이트의 미완료된 장애건에 대해 모두 저장 */
            if(parsed.getString("inout_type").startsWith("1")) {
                /* 경비 설정은 완료여부와 관계없이    -> 수정(아래참조) */
                /* 경비 해제시간이 있으면서 경비 설정시간이 없는건 중     */
                /* 경비 해제시간 보다 설정시간이 이후 인것               */
                /* 신한은행 ATMS의 경우 내부 임시 기관 코드와           */
                /* 경비사에 통보하는 기관코드가 다르므로 매칭 작업이 필요 하다.   */
                /* 처리시간이 없는 미완료 건일 경우 설정시간으로 완료시간 세팅
                   통보 완료 된 건만
                   AS접수건인 경우는 경비설정무시
                   처리처와 처리자는 수신처와 수신처를 가져오도록 한다.
                   수신된 최초시간만 20090310 김희천 요청 */

                TCtErrorMng tCtErrorMngParam = new TCtErrorMng();
                tCtErrorMngParam.setOrgCd(parsed.getString("org_cd"));
                tCtErrorMngParam.setBranchCd(parsed.getString("brch_cd"));
                tCtErrorMngParam.setSiteCd(parsed.getString("site_cd"));
                tCtErrorMngParam.setUnlockDate(parsed.getString("arrival_date"));
                tCtErrorMngParam.setUnlockTime(parsed.getString("arrival_time"));


                List<TCtErrorMng> tCtErrorMngList = tMiscMapper.selectCtErrorMng(tCtErrorMngParam);

                if(tCtErrorMngList != null && tCtErrorMngList.size() > 0) {

                    for(TCtErrorMng tCtErrorMng : tCtErrorMngList) {
                        TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
                        updateTCtErrorMng.setFinishDate (parsed.getString("arrival_date"));
                        updateTCtErrorMng.setFinishTime (parsed.getString("arrival_time"));
                        updateTCtErrorMng.setErrorStatus("7000");
                        updateTCtErrorMng.setFinishType (tCtErrorMng.getRecvPlace());
                        updateTCtErrorMng.setFinishUid(tCtErrorMng.getRecvUserUid());
                        updateTCtErrorMng.setFinishNm(tCtErrorMng.getRecvUserNm());
                        updateTCtErrorMng.setLockDate(parsed.getString("arrival_date"));
                        updateTCtErrorMng.setLockTime(parsed.getString("arrival_time"));
                        updateTCtErrorMng.setUpdateDate(safeData.getDSysDate());
                        updateTCtErrorMng.setUpdateUid("ERR_Up");

                        try
                        {
                            comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMng);

                            logger.warn( "[T_CT_ERROR_MNG] Update OK");

                        } catch (Exception e)
                        {
                            logger.warn( "[T_CT_ERROR_MNG] Update Err [{}]", e.getMessage());
                            throw e;
                        }
                    }

                }
            } else {

                /* 20081031 운총김희천 대리 요청에의해 경비 해제시간을 도착시간으로 처리   */
                /* 단. 최초 경비해제시간이 들어왔을 경우만                                   */
                /* AS접수건인 경우는 경비해제무시 20090203                                   */
                /* 통보완료된건에 대해서만 20090309 김희천 추가                         */

                TCtErrorMng tCtErrorMngParam = new TCtErrorMng();
                tCtErrorMngParam.setOrgCd(parsed.getString("org_cd"));
                tCtErrorMngParam.setBranchCd(parsed.getString("brch_cd"));
                tCtErrorMngParam.setSiteCd(parsed.getString("site_cd"));
                tCtErrorMngParam.setUnlockDate(parsed.getString("arrival_date"));
                tCtErrorMngParam.setUnlockTime(parsed.getString("arrival_time"));


                List<TCtErrorMng> tCtErrorMngList = tMiscMapper.selectCtErrorMng2(tCtErrorMngParam);

                if(tCtErrorMngList != null && tCtErrorMngList.size() > 0) {

                    for(TCtErrorMng tCtErrorMng : tCtErrorMngList) {
                        TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
                        updateTCtErrorMng.setArrivalDate (parsed.getString("arrival_date"));
                        updateTCtErrorMng.setArrivalTime (parsed.getString("arrival_time"));
                        updateTCtErrorMng.setUnlockDate(parsed.getString("arrival_date"));
                        updateTCtErrorMng.setUnlockTime(parsed.getString("arrival_time"));
                        updateTCtErrorMng.setUpdateDate(safeData.getDSysDate());
                        updateTCtErrorMng.setUpdateUid("ERR_Up");

                        try
                        {
                            comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMng);

                            logger.warn( "[T_CT_ERROR_MNG] Update OK");

                        } catch (Exception e)
                        {
                            logger.warn( "[T_CT_ERROR_MNG] Update Err [{}]", e.getMessage());
                            throw e;
                        }
                    }

                }
            }

        }

    }//end method
}