package com.nicetcm.nibsplus.broker.msg.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nicetcm.nibsplus.broker.msg.MsgBrokerLib.nstr;
import static com.nicetcm.nibsplus.broker.msg.MsgBrokerLib.substr;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorMngMadeComMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorMngMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngMadeCom;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngMadeComSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngSpec;

/**
 * 도착통보
 * <pre>
 * MngEM_SaveArrival( char * pRecvData, int nRecvLen )
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01001160")
public class In01001160Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCtErrorMngMadeComMapper tCtErrorMngMadeComMapper;

    @Autowired private TMiscMapper tMiscMapper;

    @Autowired private TCtErrorMngMapper ctErrorMngMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        if(MsgBrokerConst.SHATMS_CODE.equals(parsed.getString("CM.org_cd"))) {

            TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
            updateTCtErrorMng.setArrivalDate(parsed.getString("arrival_date"));
            updateTCtErrorMng.setArrivalTime(parsed.getString("arrival_time"));
            updateTCtErrorMng.setArrivalNm      ("부분관리");
            updateTCtErrorMng.setRecvTeleNo(parsed.getString("arrival_tel_no"));
            updateTCtErrorMng.setWorkStatus      ("6060");
            updateTCtErrorMng.setUpdateDate      (safeData.getDSysDate());
            updateTCtErrorMng.setUpdateUid       ("ERRmng");

            TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
            tCtErrorMngSpec.createCriteria()
            .andCreateDateGreaterThan(Integer.parseInt(MsgBrokerLib.SysDate(-10)))
            .andTransDateEqualTo(parsed.getString("trans1_date"))
            .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"));

            try
            {
                comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMngSpec);
            } catch (Exception e)
            {
                logger.info("[T_CT_ERROR_MNG] Update Err [{}]", e.getMessage());
                throw e;
            }

        } else if(MsgBrokerConst.ATM_HY_CODE.equals(parsed.getString("CM.org_cd")) || MsgBrokerConst.ATM_CH_CODE.equals(parsed.getString("CM.org_cd")) || MsgBrokerConst.ATM_LG_CODE.equals(parsed.getString("CM.org_cd"))) {
            /* 기기사에서 수신 받은 전문은 2차 출동요청 테이블에서 처리 */

            /* 현재 기기사와 전문 연결하여 요청하는 기관은 신한은행만 이므로
                기관코드를 임시로 '088'로 넣어 주도록 한다.
                - 전기관 확대시 기기사와의 전문 사양도 수정 필요
            */
            parsed.setString("CM.org_cd", MsgBrokerConst.SHATMS_CODE);

            Short horgCallCnt = Short.parseShort(comPack.parseErrorMsg(parsed.getString("error_msg"), 15));

            TCtErrorMngMadeComSpec tCtErrorMngMadeComSpec = new TCtErrorMngMadeComSpec();
            tCtErrorMngMadeComSpec.createCriteria()
            .andAsAcptDateEqualTo(parsed.getString("trans1_date"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"))
            .andOrgCallCntEqualTo(horgCallCnt);

            List<TCtErrorMngMadeCom> tCtErrorMngMadeComList = null;
            try
            {
                tCtErrorMngMadeComList = tCtErrorMngMadeComMapper.selectBySpec(tCtErrorMngMadeComSpec);
            } catch (Exception e)
            {
                logger.info(String.format(">>> [T_CT_ERROR_MNG_MADE_COM-ARRIVAL] 2차출동건건검색실패 AS_ACPT_date[%s], ORG_CD[%s], JIJUM_CD[%s], MAC_NO[%s], ORG_CALL_CNT[%s] [%.200s]",
                                parsed.getString("trans1_date"), parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"), horgCallCnt, e.getMessage()));
                throw e;
            }

            if(tCtErrorMngMadeComList == null || tCtErrorMngMadeComList.size() == 0) {
                logger.info(String.format("[T_CT_ERROR_MNG_MADE_COM-ARRIVAL] 해당데이터가 없습니다. AS_ACPT_date[%s], ORG_CD[%s], JIJUM_CD[%s], MAC_NO[%s], ORG_CALL_CNT[%s]",
                                parsed.getString("trans1_date"), parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"), horgCallCnt));
                throw new MsgBrokerException(-1);
            }

            TCtErrorMngMadeCom tCtErrorMngMadeCom = tCtErrorMngMadeComList.get(0);
            int htimeConf = 0;

            if(Long.parseLong(tCtErrorMngMadeCom.getSendDate() + tCtErrorMngMadeCom.getSendTime()) > Long.parseLong(parsed.getString("arrival_date") + parsed.getString("arrival_time"))) {
                htimeConf = 0;
            } else {
                htimeConf = 1;
            }

            /* 이미 완료된 건은 update 하지 않도록 한다. */
            if(nstr(tCtErrorMngMadeCom.getFinishStatus()).equals("7000")) {
                logger.info(String.format("[SaveErrArrival] 완료 2차출동에 대한 도착통보 수신 AS_ACPT_date[%s], ORG_CD[%s], JIJUM_CD[%s], MAC_NO[%s], ORG_CALL_CNT[%s]",
                                parsed.getString("trans1_date"), parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"), horgCallCnt));
                throw new MsgBrokerException(-2);
            }

            if(tCtErrorMngMadeCom.getArrivalTime() != null
            && Integer.parseInt(tCtErrorMngMadeCom.getArrivalTime()) > 0) {
                logger.info(String.format("[SaveErrArrival] 이미 도착시간이 설정되어 있습니다. AS_ACPT_date[%s], ORG_CD[%s], JIJUM_CD[%s], MAC_NO[%s], ORG_CALL_CNT[%s]",
                                parsed.getString("trans1_date"), parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"), horgCallCnt));
                throw new MsgBrokerException(-2);
            }

            parsed.setString("arrival_tel_no", parsed.getString("arrival_tel_no").replaceAll("-", ""));

            TCtErrorMngMadeCom tCtErrorMngMadeCom2 = new TCtErrorMngMadeCom();
            tCtErrorMngMadeCom2.setArrivalDate(parsed.getString("arrival_date"));
            tCtErrorMngMadeCom2.setArrivalTime(parsed.getString("arrival_time"));
            tCtErrorMngMadeCom2.setComManNm(parsed.getString("arrival_nm"));
            tCtErrorMngMadeCom2.setComManTelNo(parsed.getString("arrival_tel_no"));

            tCtErrorMngMadeCom2.setAsAcptDate(parsed.getString("trans1_date"));
            tCtErrorMngMadeCom2.setOrgCd(parsed.getString("CM.org_cd"));
            tCtErrorMngMadeCom2.setBranchCd(parsed.getString("brch_cd"));
            tCtErrorMngMadeCom2.setMacNo(parsed.getString("mac_no"));
            tCtErrorMngMadeCom2.setOrgCallCnt(horgCallCnt);

            try
            {
                tMiscMapper.updateCtErrorMngMadeCom2(tCtErrorMngMadeCom2);
            } catch (Exception e)
            {
                logger.info( "[T_CT_ERROR_MNG_MADE_COM-ARRIVAL] Update Err {}", e.getMessage());
                throw e;
            }


        //endof 기기사에서 수신 받은 전문은 2차 출동요청 테이블에서 처리
        } else {

            TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
            tCtErrorMngSpec.createCriteria()
            .andCreateDateEqualTo(parsed.getInt("trans1_date"))
            .andErrorNoEqualTo(parsed.getString("trans1_seq"));

            List<TCtErrorMng> tCtErrorMngList = null;
            try
            {
                tCtErrorMngList = ctErrorMngMap.selectBySpec(tCtErrorMngSpec);
            } catch (Exception e)
            {
                logger.info(String.format("[SaveErrArrival] 장애건검색실패. create_date[%s], error_no[%s]", parsed.getInt("trans1_date"), parsed.getString("trans1_seq")));
                throw new Exception(String.format("[SaveErrArrival] 장애건검색실패. create_date[%s], error_no[%s]", parsed.getInt("trans1_date"), parsed.getString("trans1_seq")));
            }

            if(tCtErrorMngList == null || tCtErrorMngList.size() == 0) {
                logger.info(String.format("[SaveErrArrival] 해당데이터가 없습니다. create_date[%s], error_no[%s]", parsed.getInt("trans1_date"), parsed.getString("trans1_seq")));
                throw new Exception(String.format("[SaveErrArrival] 해당데이터가 없습니다. create_date[%s], error_no[%s]", parsed.getInt("trans1_date"), parsed.getString("trans1_seq")));
            }

            TCtErrorMng tCtErrorMng = tCtErrorMngList.get(0);

            int htimeConf = 0;

            if(Long.parseLong(tCtErrorMng.getSendDate() + tCtErrorMng.getSendTime()) > Long.parseLong(parsed.getString("arrival_date") + parsed.getString("arrival_time"))) {
                htimeConf = 0;
            } else {
                htimeConf = 1;
            }

            /* 이미 완료된 건은 update 하지 않도록 한다. */
            if(nstr(tCtErrorMng.getErrorStatus()).equals("7000")) {
                logger.info(String.format(">>> [SaveErrArrival] 완료장애에 대한 도착통보 수신 create_date[%s], error_no[%s]", parsed.getInt("trans1_date"), parsed.getString("trans1_seq")));
                throw new MsgBrokerException(-2);
            }

            if(tCtErrorMng.getArrivalTime() != null
            && Integer.parseInt(tCtErrorMng.getArrivalTime()) > 0) {
                logger.info(String.format("[SaveErrArrival] 이미 도착시간이 설정되어 있습니다. create_date[%s], error_no[%s], old_arrival_date[%s], old_arrival_time[%s]",
                                parsed.getString("trans1_date"), parsed.getString("trans1_seq"), tCtErrorMng.getArrivalDate(), tCtErrorMng.getArrivalTime()));
                throw new MsgBrokerException(-2);
            }

            /* 통보시간 보다 빠른시간으로 도착이 들어온건은  통보실패 SMS 통보실패로 저장해 준다.
            20090928 BY 김희천 */
            if(htimeConf == 0) {
                TCtErrorMng tCtErrorMng2 = new TCtErrorMng();
                tCtErrorMng2.setSendStatus     ("3");
                tCtErrorMng2.setSendSmsStatus  ("6030");    /*통보실패*/
                tCtErrorMng2.setWorkStatus     ("6030");
                tCtErrorMng2.setUpdateDate     (safeData.getDSysDate());
                tCtErrorMng2.setUpdateUid      ("ERRmng");

                TCtErrorMngSpec tCtErrorMngSpec2 = new TCtErrorMngSpec();
                tCtErrorMngSpec2.createCriteria()
                .andCreateDateEqualTo(parsed.getInt("trans1_date"))
                .andErrorNoEqualTo(parsed.getString("trans1_seq"));

                try
                {
                    comPack.updateErrorMng(tCtErrorMng2, tCtErrorMngSpec2);
                } catch (Exception e)
                {
                    logger.info( "[T_CT_ERROR_MNG] Update Err {}", e.getMessage());
                    throw e;
                }

                logger.info( "[T_CT_ERROR_MNG] 통보보다 빠른 도착 통보실패로 ...Update OK" );

                return;
            }

            /*
            경비사 도착보고
            arrival_date
            arrival_time
            arrival_nm
            work_status = '6060',
            update_date
            update_uid
            */

            /* 경비사에서 수신된 농협 도착 통보는 무시->경비 해제만 인정 함. 20120810 조규석 요청*/
            if(MsgBrokerConst.NONGH_CODE.equals(parsed.getString("CM.org_cd"))) {
                logger.info( "[T_CT_ERROR_MNG] 농협 경비사 도착 보고 무시" );
                return;
            }

            if(parsed.getString("arrival_nm").equals("")) {
                parsed.setString("arrival_nm", tMiscMapper.getArrivalNm(parsed.getString("trans1_date"), parsed.getString("trans1_seq")));
            }

            parsed.setString("arrival_tel_no", parsed.getString("arrival_tel_no").replaceAll("-", ""));

            TCtErrorMng tCtErrorMng2 = new TCtErrorMng();
            tCtErrorMng2.setArrivalDate (parsed.getString("arrival_date"));
            tCtErrorMng2.setArrivalTime (parsed.getString("arrival_time"));
            tCtErrorMng2.setArrivalNm   (parsed.getString("arrival_nm"));
            tCtErrorMng2.setRecvTeleNo  (parsed.getString("arrival_tel_no"));
            tCtErrorMng2.setWorkStatus  ("6060");
            tCtErrorMng2.setUpdateDate  (safeData.getDSysDate());
            tCtErrorMng2.setUpdateUid   ("ERRmng");

            TCtErrorMngSpec tCtErrorMngSpec2 = new TCtErrorMngSpec();
            tCtErrorMngSpec2.createCriteria()
            .andCreateDateEqualTo(parsed.getInt("trans1_date"))
            .andErrorNoEqualTo(parsed.getString("trans1_seq"));


            try
            {
                comPack.updateErrorMng(tCtErrorMng2, tCtErrorMngSpec2);
            } catch (Exception e)
            {
                logger.info( "[T_CT_ERROR_MNG] Update Err {}", e.getMessage());
                throw e;
            }
        }

        logger.info( "[T_CT_ERROR_MNG] Update OK" );

        /*************************************************************
        * 기업은행 브랜드제휴 전송
        **************************************************************/
    }//end method
}