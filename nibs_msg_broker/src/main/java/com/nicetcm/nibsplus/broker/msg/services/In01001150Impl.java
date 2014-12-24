package com.nicetcm.nibsplus.broker.msg.services;

import static com.nicetcm.nibsplus.broker.msg.MsgBrokerLib.nstr;

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
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorMngMadeComMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorMngMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngMadeCom;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngMadeComSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngSpec;

/**
 *
 * 도착예정
 * <pre>
 * MngEM_SaveArrivalSchdule( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01001150")
public class In01001150Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCtErrorMngMadeComMapper tCtErrorMngMadeComMapper;

    @Autowired private TMiscMapper tMiscMapper;

    @Autowired private TCtErrorMngMapper ctErrorMngMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        if(MsgBrokerConst.SHATMS_CODE.equals(parsed.getString("CM.org_cd"))) {

            TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
            updateTCtErrorMng.setRecvDate        (safeData.getSysDate());
            updateTCtErrorMng.setRecvTime        (safeData.getSysTime().substring(0, 4));
            updateTCtErrorMng.setArrivalEstDate  (parsed.getString("schdule_date"));
            updateTCtErrorMng.setArrivalEstTime  (parsed.getString("schdule_time"));
            updateTCtErrorMng.setRecvUserNm      ("부분관리");
            updateTCtErrorMng.setSendStatus      ("2");
            updateTCtErrorMng.setSendSmsStatus   ("6040");
            updateTCtErrorMng.setWorkStatus      ("6050");
            updateTCtErrorMng.setUpdateDate      (safeData.getDSysDate());
            updateTCtErrorMng.setUpdateUid       ("ERRmng");

            TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
            tCtErrorMngSpec.createCriteria()
            .andTransDateEqualTo(parsed.getString("trans1_date"))
            .andCreateDateGreaterThan(MsgBrokerLib.SysDate(-10))
            .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"));

            try
            {
                comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMngSpec);
            } catch (Exception e)
            {
                logger.warn("[T_CT_ERROR_MNG] Update Err [{}]", e.getMessage());
                throw e;
            }

        } else if(MsgBrokerConst.ATM_HY_CODE.equals(parsed.getString("CM.org_cd")) || MsgBrokerConst.ATM_CH_CODE.equals(parsed.getString("CM.org_cd")) || MsgBrokerConst.ATM_LG_CODE.equals(parsed.getString("CM.org_cd"))) {
            /* 기기사에서 수신 받은 전문은 2차 출동요청 테이블에서 처리 */

            /* 현재 기기사와 전문 연결하여 요청하는 기관은 신한은행만 이므로
                기관코드를 임시로 '088'로 넣어 주도록 한다.
                - 전기관 확대시 기기사와의 전문 사양도 수정 필요
            */
            parsed.setString("CM.org_cd", MsgBrokerConst.SHATMS_CODE);

            TCtErrorMngMadeComSpec tCtErrorMngMadeComSpec = new TCtErrorMngMadeComSpec();
            tCtErrorMngMadeComSpec.createCriteria()
            .andAsAcptDateEqualTo(parsed.getString("trans1_date"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"))
            .andOrgCallCntEqualTo(parsed.getShort("org_call_cnt"));

            List<TCtErrorMngMadeCom> tCtErrorMngMadeComList = null;
            try
            {
                tCtErrorMngMadeComList = tCtErrorMngMadeComMapper.selectBySpec(tCtErrorMngMadeComSpec);
            } catch (Exception e)
            {
                logger.warn(String.format(">>> [T_CT_ERROR_MNG_MADE_COM-ARRIVSL_EST] 2차출동건건검색실패 AS_ACPT_date[%s], ORG_CD[%s], JIJUM_CD[%s], MAC_NO[%s], ORG_CALL_CNT[%s] [%.200s]",
                                parsed.getString("trans1_date"), parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"), parsed.getString("org_call_cnt"), e.getMessage()));
                throw e;
            }

            if(tCtErrorMngMadeComList == null || tCtErrorMngMadeComList.size() == 0) {
                logger.warn(String.format("[T_CT_ERROR_MNG_MADE_COM-ARRIVSL_EST] 해당데이터가 없습니다. AS_ACPT_date[%s], ORG_CD[%s], JIJUM_CD[%s], MAC_NO[%s], ORG_CALL_CNT[%s]",
                                parsed.getString("trans1_date"), parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"), parsed.getString("org_call_cnt")));
                throw new MsgBrokerException(-1);
            }

            TCtErrorMngMadeCom tCtErrorMngMadeCom = tCtErrorMngMadeComList.get(0);
            tCtErrorMngMadeCom.setArrivalEstDate(tCtErrorMngMadeCom.getArrivalEstDate() != null ? tCtErrorMngMadeCom.getArrivalEstDate().trim() : tCtErrorMngMadeCom.getArrivalEstDate());
            tCtErrorMngMadeCom.setArrivalEstTime(tCtErrorMngMadeCom.getArrivalEstTime() != null ? tCtErrorMngMadeCom.getArrivalEstTime().trim() : tCtErrorMngMadeCom.getArrivalEstTime());

            /* 이미 완료된 건은 update 하지 않도록 한다. */
            if(nstr(tCtErrorMngMadeCom.getFinishStatus()).equals("7000")) {
                logger.warn(String.format("[SaveErrArrSchdule] 완료 2차출동에 대한 도착예정 수신 AS_ACPT_date[%s], ORG_CD[%s], JIJUM_CD[%s], MAC_NO[%s], ORG_CALL_CNT[%s]",
                                parsed.getString("trans1_date"), parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"), parsed.getString("org_call_cnt")));
                throw new MsgBrokerException(-2);
            }

            parsed.setString("arrival_tel_no", parsed.getString("arrival_tel_no").replaceAll("-", ""));

            /* 도착시간이 비어있을 경우에만 도착예정시간을 재전송한다. 20120612 최락경  */
            if((tCtErrorMngMadeCom.getArrivalDate() == null || nstr(tCtErrorMngMadeCom.getArrivalDate()).equals(""))
            && (tCtErrorMngMadeCom.getArrivalTime() == null || nstr(tCtErrorMngMadeCom.getArrivalTime()).equals(""))) {
                /* ORG_SEND_YN 0: 2차출동요청, 1:2차출동요청응답수신->신한도착예정시간 송신준비,
                               2: 신한도착예정시간응답수신->신한도착시간 송신준비,
                               3: 신한도착시간응답수신->신한조치결과 송신준비,
                               4: 신한조치결과응답수신
                */
                TCtErrorMngMadeCom tCtErrorMngMadeCom2 = new TCtErrorMngMadeCom();
                tCtErrorMngMadeCom2.setArrivalEstDate(parsed.getString("schdule_date"));
                tCtErrorMngMadeCom2.setArrivalEstTime(parsed.getString("schdule_time"));
                tCtErrorMngMadeCom2.setComManNm(parsed.getString("arrival_nm"));
                tCtErrorMngMadeCom2.setComManTelNo(parsed.getString("arrival_tel_no"));

                tCtErrorMngMadeCom2.setAsAcptDate(parsed.getString("trans1_date"));
                tCtErrorMngMadeCom2.setOrgCd(parsed.getString("CM.org_cd"));
                tCtErrorMngMadeCom2.setBranchCd(parsed.getString("brch_cd"));
                tCtErrorMngMadeCom2.setMacNo(parsed.getString("mac_no"));
                tCtErrorMngMadeCom2.setOrgCallCnt(parsed.getShort("org_call_cnt"));

                try
                {
                    tMiscMapper.updateCtErrorMngMadeCom(tCtErrorMngMadeCom2);
                } catch (Exception e)
                {
                    logger.warn( "[T_CT_ERROR_MNG_MADE_COM-ARRIVAL_EST] Update Err {}", e.getMessage());
                    throw e;
                }


            } else {
                /* 도착시간이 이미 기록되어있으므로, 도착예정은 무시한다. */
                logger.warn( "[T_CT_ERROR_MNG_MADE_COM] Update Err 기 도착시간이 존재하여, 도착예정 무시" );
                throw new Exception("[T_CT_ERROR_MNG_MADE_COM] Update Err 기 도착시간이 존재하여, 도착예정 무시");
            }

        //endof 기기사에서 수신 받은 전문은 2차 출동요청 테이블에서 처리
        } else {

            TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
            tCtErrorMngSpec.createCriteria()
            .andCreateDateEqualTo(parsed.getString("trans1_date"))
            .andErrorNoEqualTo   (parsed.getString("trans1_seq"));

            List<TCtErrorMng> tCtErrorMngList = null;
            try
            {
                tCtErrorMngList = ctErrorMngMap.selectBySpec(tCtErrorMngSpec);
            } catch (Exception e)
            {
                logger.warn(String.format("[SaveErrArrSchdule] 장애건검색실패. create_date[%s], error_no[%s]", parsed.getInt("trans1_date"), parsed.getString("trans1_seq")));
                throw new Exception(String.format("[SaveErrArrSchdule] 장애건검색실패. create_date[%s], error_no[%s]", parsed.getInt("trans1_date"), parsed.getString("trans1_seq")));
            }

            if(tCtErrorMngList == null || tCtErrorMngList.size() == 0) {
                logger.warn(String.format("[SaveErrArrSchdule] 해당데이터가 없습니다. create_date[%s], error_no[%s]", parsed.getInt("trans1_date"), parsed.getString("trans1_seq")));
                throw new Exception(String.format("[SaveErrArrSchdule] 해당데이터가 없습니다. create_date[%s], error_no[%s]", parsed.getInt("trans1_date"), parsed.getString("trans1_seq")));
            }

            TCtErrorMng tCtErrorMng = tCtErrorMngList.get(0);
            tCtErrorMng.setArrivalEstDate(nstr(tCtErrorMng.getArrivalEstDate()).trim());
            tCtErrorMng.setArrivalEstTime(nstr(tCtErrorMng.getArrivalEstTime()).trim());

            /* 이미 완료된 건은 update 하지 않도록 한다. */
            if(nstr(tCtErrorMng.getErrorStatus()).equals("7000")) {
                logger.warn(String.format(">>> [SaveErrArrSchdule] 완료장애에 대한 도착예정 수신 create_date[%s], error_no[%s]", parsed.getInt("trans1_date"), parsed.getString("trans1_seq")));
                throw new MsgBrokerException(-2);
            }

            /* 신한은행일경우 이미 도착예정시간이 들어가 있다면 UPDATE하지 않는다. 2011.02.16 운총 양유석요청 */
            /* 20120622 운총 박규성 요청 재송신가능하도록 수정
            if( memcmp( suBody.org_cd, SHATMS_CODE, LEN_ORG_CD ) == 0 &&
                strlen( hold_est_time) > 0 )
            {
                logger(">>> [SaveErrArrSchdule] 신한 기 도착예정시간 있음 old[%s-%s], recv[%s-%s]\n",
                                hold_est_date, hold_est_time, suBody.schdule_date, suBody.schdule_time );
                /= 경비사로는 정상응답 - 차후 경비사측과 오류코드 재협의후 처리 고려 =/
                return 0;
            }
            */

            /*
            도착예정수신시 - 경비사 접수시
            recv_date
            recv_time
            arrival_est_date
            arrival_est_time
            send_status = '2',
            SEND_SMS_STATUS = '6040',  --통보완료
            work_status = '6050',  --PDA 장애접수
            update_date ,
            update_uid
            */

            if(nstr(parsed.getString("arrival_nm")).equals("")) {
                parsed.setString("arrival_nm", tMiscMapper.getArrivalNm(parsed.getString("trans1_date"), parsed.getString("trans1_seq")));
            }

            parsed.setString("arrival_tel_no", parsed.getString("arrival_tel_no").replaceAll("-", ""));

            /* 경비사 전문에서 관리지사명 필드에 출동자 사번을 설정하도록 함 */
            /* 출동자명이 없을 경우에는 경비사 default값 9999999을 넣도록 수정 20090302 */

            /* 도착시간이 비어있을 경우에만 도착예정시간을 재전송한다. 20120612 최락경  */
            if((tCtErrorMng.getArrivalDate() == null || nstr(tCtErrorMng.getArrivalDate()).equals(""))
            && (tCtErrorMng.getArrivalTime() == null || nstr(tCtErrorMng.getArrivalTime()).equals(""))) {

                TCtErrorMng tCtErrorMng2 = new TCtErrorMng();
                /* 2014.07.18 경비사 수신확인 전문을 추가 하였으므로 삭제
                tCtErrorMng2.setRecvDate       (safeData.getSysDate());
                tCtErrorMng2.setRecvTime       (safeData.getSysTime().substring(0, 4));
                tCtErrorMng2.setRecvUserNm     (parsed.getString("arrival_nm"));
                tCtErrorMng2.setRecvUserUid    (parsed.getString("office_nm").equals("") ? "9999999" : parsed.getString("office_nm"));
                tCtErrorMng2.setRecvTeleNo     (parsed.getString("arrival_tel_no"));
                */
                tCtErrorMng2.setArrivalEstDate (parsed.getString("schdule_date"));
                tCtErrorMng2.setArrivalEstTime (parsed.getString("schdule_time").substring(0, 4));
                tCtErrorMng2.setSendStatus     ("2");
                tCtErrorMng2.setSendSmsStatus  ("6040");
                tCtErrorMng2.setWorkStatus     ("6050");
                tCtErrorMng2.setUpdateDate     (safeData.getDSysDate());
                tCtErrorMng2.setUpdateUid      ("ERRmng");
                tCtErrorMng2.setOrgSendYn      ("0");

                TCtErrorMngSpec tCtErrorMngSpec2 = new TCtErrorMngSpec();
                tCtErrorMngSpec2.createCriteria()
                .andCreateDateEqualTo(parsed.getString("trans1_date"))
                .andErrorNoEqualTo   (parsed.getString("trans1_seq"))
                .andOrgCdNotEqualTo("096");


                try
                {
                    comPack.updateErrorMng(tCtErrorMng2, tCtErrorMngSpec2);
                } catch (Exception e)
                {
                    logger.warn( "[T_CT_ERROR_MNG] Update Err {}", e.getMessage());
                    throw e;
                }


            } else {
                /* 도착시간이 이미 기록되어있으므로, 도착예정은 무시한다. */
                logger.warn( "[T_CT_ERROR_MNG] Update Err 기 도착시간이 존재하여, 도착예정 무시" );
                throw new Exception("[T_CT_ERROR_MNG] Update Err 기 도착시간이 존재하여, 도착예정 무시");
            }
        }

        logger.warn( "[T_CT_ERROR_MNG] Update OK" );
    }
}