package com.nicetcm.nibsplus.broker.msg.services;

import java.nio.ByteBuffer;

import javax.jms.BytesMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerProducer;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.IfCashInsert;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

/**
 *
 * 마감시재조회
 * <pre>
 * MngCM_AP_SaveCloseAmt
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101130")
public class In03101130Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101130Impl.class);

    @Autowired private StoredProcMapper storedProcMapper;

    @Autowired private TMiscMapper tMiscMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {


        /* 총지급액이 있으면 총지급액 그대로, 총지급액이 없으면 현금 지급 + 수표 지급 */
        /* 현금입지급액 란은 지운다.   - 자금팀 요청                                */

        if(parsed.getString("close_date").equals("")) {
            /* 대구은행은 마김일자 시간, 조회일자를 넣어주지 않는다. 따라서 header의 일자로 */
            if(MsgBrokerConst.DGB_CODE.equals(parsed.getString("CM.org_cd"))) {
                parsed.setString("close_date", parsed.getString("CM.trans_date"));
            } else {
                /* 마감일자가 없다면 조회일자로 넣어준다 */
                parsed.setString("close_date", parsed.getString("inq_date"));
            }
        }

        if( MsgBrokerConst.WRATMS_CODE.equals(parsed.getString("CM.org_cd")) && (parsed.getString("close_time").equals("") || parsed.getString("close_time").equals("000000")) ) {
            throw new Exception("우리은행 마감시간 없음");
        } else if(parsed.getString("close_time").equals("")) {
            parsed.setString("close_time", "000000");
        }

        if(parsed.getLong("total_out_amt") == 0) {
            parsed.setLong("total_out_amt", parsed.getLong("cash_out_amt") + parsed.getLong("check_out_amt"));
        }

        if(parsed.getLong("total_in_amt") == 0) {
            parsed.setLong("total_in_amt", parsed.getLong("cash_in_amt") + parsed.getLong("check_in_amt"));
        }

        parsed.setLong("cash_out_amt", 0);
        parsed.setLong("cash_in_amt", 0);

        String hcashType;

        /* 신한은행은 마감 구분이 '2'이면 계리 마감으로 t_cm_cash에 cash_type '3'으로 저장 */
        if(MsgBrokerConst.SHATMS_CODE.equals(parsed.getString("CM.org_cd")) && parsed.getString("close_type").equals("2")) {
            hcashType = "3";
        } else {
            hcashType = "2";
        }

        //변경해야함 (AS-IS에서는 해당 전문을 직접 수정함
        comPack.checkBranchMacLength(new TMacInfo());


        long hpreAmt = 0;
        long hremAmt = 0;

        /* 신한은행 계리마감(3) 일 경우는 현송금액을 장전금액 필드에 */
        /* 신한은행 계리마감(3) 일 경우는 조회시점 잔액을 넣어 준다. */
        if(hcashType.equals("3")) {
            hpreAmt = parsed.getLong("send_amt");
            hremAmt = parsed.getLong("cur_rem_amt");

        } else {
            TMisc tMisc = new TMisc();
            tMisc.sethCashType(hcashType);
            tMisc.setInqDate(parsed.getString("close_date"));
            tMisc.setOrgCd(parsed.getString("CM.org_cd"));
            tMisc.setBranchCd(parsed.getString("brch_cd"));
            tMisc.setMacNo(parsed.getString("mac_no"));
            tMisc.sethRtnVal(-1);
            tMisc.sethPreAmt(hpreAmt);

            storedProcMapper.spIfGetpreamt(tMisc);

            if(tMisc.gethRtnVal() != 0) {
                throw new Exception( String.format("[MngCM_AP_SaveCloseAmt] 프로시져 오류 1: [%s]", tMisc.gethRtnMsg()));
            }

            /* 현금 잔액이 없는 경우는 장전금액에서 계산해 준다. */
            if(parsed.getLong("cash_remain_amt") == 0) {
                parsed.setLong("cash_remain_amt", hpreAmt + parsed.getLong("cash_in_amt") - parsed.getLong("cash_out_amt"));
            }

            hremAmt = parsed.getLong("cash_remain_amt");
        }

        IfCashInsert ifCashInsert = new IfCashInsert();

        ifCashInsert.setnUpdateCheckYN    (0);
        ifCashInsert.setpOrgCd            (parsed.getString("CM.org_cd"));
        ifCashInsert.setpJijumCd          (parsed.getString("brch_cd"));
        ifCashInsert.setpMacNo            (parsed.getString("mac_no"));
        ifCashInsert.setpCashType         (hcashType);
        ifCashInsert.setpCashDate         (parsed.getString("close_date"));
        ifCashInsert.setpCashTime         (parsed.getString("close_time"));
        ifCashInsert.setpChargeAmt        (hpreAmt);
        ifCashInsert.setpTotInAmt         (parsed.getLong("total_in_amt"));
        ifCashInsert.setpTotOutAmt        (parsed.getLong("total_out_amt"));
        ifCashInsert.setpMoneyInAmt       (parsed.getLong("cash_in_amt"));
        ifCashInsert.setpMoneyOutAmt      (parsed.getLong("cash_out_amt"));
        ifCashInsert.setpCheckInAmt       (parsed.getLong("check_in_amt"));
        ifCashInsert.setpCheckOutAmt      (parsed.getLong("check_out_amt"));
        ifCashInsert.setpRemainAmt        (hremAmt);
        ifCashInsert.setpInCnt            (0);
        ifCashInsert.setpOutCnt           (0);
        ifCashInsert.setpChkInCnt         (0);
        ifCashInsert.setpChkOutCnt        (0);
        ifCashInsert.setpAddAmt           (0);
        ifCashInsert.setpCollectAmt       (parsed.getLong("collect_amt"));
        ifCashInsert.setpRemainCheckAmt   (0);
        ifCashInsert.setpRemain10Amt      (0);
        ifCashInsert.setpRemain50Amt      (0);
        ifCashInsert.setpRemain100Amt     (0);
        ifCashInsert.setpRemain500Amt     (0);
        ifCashInsert.setpRemain1000Amt    (0);
        ifCashInsert.setpRemain5000Amt    (0);
        ifCashInsert.setpRemain10000Amt   (0);

        ifCashInsert.setpRemain50000Amt   (parsed.getLong("cur_rem_50000_amt"));
        ifCashInsert.setpMoneyIn50000Amt  (parsed.getLong("in_50000_cnt")  * 50000);
        ifCashInsert.setpMoneyOut50000Amt (parsed.getLong("out_50000_cnt") * 50000);
        ifCashInsert.setpMoneyIn5000Amt   (parsed.getLong("in_5000_cnt")   * 5000);
        ifCashInsert.setpMoneyIn1000Amt   (parsed.getLong("in_1000_cnt")   * 1000);
        ifCashInsert.setpTodayChargeAmt   (parsed.getLong("today_charge_amt"));
        ifCashInsert.setpPreChargeAmt     (parsed.getLong("pre_charge_amt"));
        ifCashInsert.setpPreAddAmt        (parsed.getLong("pre_add_amt"));
        ifCashInsert.setpHolyAddAmt       (parsed.getLong("holy_add_amt"));
        ifCashInsert.setpTodayAddAmt      (parsed.getLong("today_add_amt"));
        ifCashInsert.setpSafeNo           (parsed.getString("safe_no"));
        ifCashInsert.setvFirstInqYN       (0);
        ifCashInsert.setvResult           (-1);

        storedProcMapper.spIfCashinsert(ifCashInsert);

        if(ifCashInsert.getvResult() != 0) {
            throw new MsgBrokerException(String.format("[MngCM_AP_SaveCloseAmt] 프로시져 오류 2: %s ", ifCashInsert.getvResultMsg()), -99);
        } else {
            /* 기업은행 첫 마감조회시 정상응답일 경우 현재시재를 자동으로 요청하도록 한다. */
            if( MsgBrokerConst.KIUP_CODE.equals(parsed.getString("CM.org_cd")) && ifCashInsert.getvFirstInqYN() == 1) {
                this.sendKiupMsg(safeData, parsed);
            } else if(MsgBrokerConst.NONGH_CODE.equals(parsed.getString("CM.org_cd"))) {

                /* 농협일 경우 정희성 요청에 의해 T_CM_CASH에 쌓은 후 별도의 프로시져 호출 하도록 함. 20100920 */
                FnMacClose fnMacClose = new FnMacClose();
                fnMacClose.setpCloseDate(parsed.getString("close_date"));
                fnMacClose.setpOrgCode  (parsed.getString("CM.org_cd"));
                fnMacClose.setpJijumCode(parsed.getString("jijum_cd"));
                fnMacClose.setpMacNo    (parsed.getString("mac_no"));
                fnMacClose.setpUserId   (parsed.getString("CM.msg_id"));

                logger.info("[sp_fn_macClose_nh] 프로시져 결과: {}", fnMacClose.getvResult());

                if("OK".equals(fnMacClose.getvResult())) {
                    msgTX.rollback(safeData.getTXS());
                } else {
                    msgTX.commit(safeData.getTXS());
                }

            } else if( MsgBrokerConst.SHATMS_CODE.equals(parsed.getString("CM.org_cd")) && parsed.getString("close_type").equals("2") ) {

                /* 신한은행 계리 마감 전문의 경우 조회시점의 현재시재를 함께 넣어 준다. */
                IfCashInsert ifCashInsert2 = new IfCashInsert();

                ifCashInsert2.setnUpdateCheckYN    (0);
                ifCashInsert2.setpOrgCd            (parsed.getString("CM.org_cd"));
                ifCashInsert2.setpJijumCd          (parsed.getString("brch_cd"));
                ifCashInsert2.setpMacNo            (parsed.getString("mac_no"));
                ifCashInsert2.setpCashType         ("1");
                ifCashInsert2.setpCashDate         (parsed.getString("inq_date"));
                ifCashInsert2.setpCashTime         (safeData.getSysTime());
                ifCashInsert2.setpChargeAmt        (hpreAmt);
                ifCashInsert2.setpTotInAmt         (0);
                ifCashInsert2.setpTotOutAmt        (0);
                ifCashInsert2.setpMoneyInAmt       (0);
                ifCashInsert2.setpMoneyOutAmt      (0);
                ifCashInsert2.setpCheckInAmt       (0);
                ifCashInsert2.setpCheckOutAmt      (0);
                ifCashInsert2.setpRemainAmt        (parsed.getLong("cur_rem_amt"));
                ifCashInsert2.setpInCnt            (0);
                ifCashInsert2.setpOutCnt           (0);
                ifCashInsert2.setpChkInCnt         (0);
                ifCashInsert2.setpChkOutCnt        (0);
                ifCashInsert2.setpAddAmt           (0);
                ifCashInsert2.setpCollectAmt       (0);
                ifCashInsert2.setpRemainCheckAmt   (0);
                ifCashInsert2.setpRemain10Amt      (0);
                ifCashInsert2.setpRemain50Amt      (0);
                ifCashInsert2.setpRemain100Amt     (0);
                ifCashInsert2.setpRemain500Amt     (0);
                ifCashInsert2.setpRemain1000Amt    (0);
                ifCashInsert2.setpRemain5000Amt    (0);
                ifCashInsert2.setpRemain10000Amt   (0);

                ifCashInsert2.setpRemain50000Amt   (parsed.getLong("cur_rem_50000_amt"));
                ifCashInsert2.setpMoneyIn50000Amt  (0);
                ifCashInsert2.setpMoneyOut50000Amt (0);
                ifCashInsert2.setpMoneyIn5000Amt   (0);
                ifCashInsert2.setpMoneyIn1000Amt   (0);
                ifCashInsert2.setpTodayChargeAmt   (0);
                ifCashInsert2.setpPreChargeAmt     (0);
                ifCashInsert2.setpPreAddAmt        (0);
                ifCashInsert2.setpHolyAddAmt       (0);
                ifCashInsert2.setpTodayAddAmt      (0);
                ifCashInsert2.setpSafeNo           ("");
                ifCashInsert2.setvFirstInqYN       (0);
                ifCashInsert2.setvResult           (-1);

                if(ifCashInsert.getvResult() != 0) {
                    logger.info( "[MngCM_AP_SaveCurrentAmt] 프로시져 오류 3: %s", ifCashInsert2.getvResultMsg() );
                    msgTX.rollback(safeData.getTXS());

                } else {
                    msgTX.commit(safeData.getTXS());

                }

                /* 20110831 정희성 요청 계리마감 시 마감조회 (2) 가 없으면 계리마감 데이터로 마감데이터 같이 넣어주도록 */
                int hIS_CLOSE;



            }


        }//endif

    }//end method

    /**
     *
     * 기업은행 현재시재를 자동으로 요청
     * <pre>
     * 기업은행 첫 마감조회시 정상응답일 경우 현재시재를 자동으로 요청
     * </pre>
     *
     * @param parsed
     */
    private void sendKiupMsg(MsgBrokerData safeData, MsgParser parsed) {

        /*
         * 거래 금액의 변화가 있을 경우 나이스 발생 장애 복구 시킴
         * 트리거에서의 부하로 인해 장애 큐로 넘겨 처리 하도록 수정
         * 20090123 by B.H.J
         */

        try {
            MsgParser msgPsr = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path")
                             + "03001110" + ".json");
            ByteBuffer msg = ByteBuffer.allocateDirect( msgPsr.getSchemaLength() );
            msgPsr.newMessage( msg );
            msg.position(0);
            msgPsr.setString( "CM.org_cd", parsed.getString("CM.org_cd") )
                  .setString( "CM.ret_cd_src", "S" )
                  .setString( "CM.msg_id", "MngAuto" )
                  .setInt   ( "CM.body_len", msgPsr.getMessageLength() - MsgBrokerConst.HEADER_LEN )
                  .setString( "CM.trans_date", safeData.getSysDate() )
                  .setString( "CM.trans_time", safeData.getSysTime() )
                  .setString( "CM.format_type", "CM" )
                  .setString( "CM.msg_type", MsgBrokerConst.CM_REQ )
                  .setString( "CM.work_type", "1110" )

                  .setString( "brch_cd",  parsed.getString("brch_cd"))
                  .setString( "mac_no",  parsed.getString("mac_no"))
                  .setString( "inq_date",  parsed.getString("inq_date"))

                  /* inq_close_yn 이 1일 경우는 ap로 응답전문 보내지 않도록 한다. */
                  .setString( "inq_close_yn",  parsed.getString("inq_close_yn"));

            MsgBrokerProducer prd = MsgBrokerProducer.producers.get( "ATMS.003.H.Q" );  //기업은행
            BytesMessage nsData = prd.getBytesMessage();

            byte[] read = new byte[msg.limit()];
            msg.position(0);
            msg.get(read);
            logger.info("NS State Data = {}", new String(read) );
            nsData.writeBytes(read);
            prd.produce( nsData );

        }
        catch ( Exception e ) {
            logger.debug("[sendNICERepairMsg] Error raised..{}", e.getMessage() );
        }
    }//end method

    public class CmCash {
        private String inqDate;
        private String orgCd;
        private String jijumCd;
        private String macNo;

        private long hisClose;

        /**
         * @return the inqDate
         */
        public String getInqDate()
        {
            return inqDate;
        }
        /**
         * @param inqDate the inqDate to set
         */
        public void setInqDate(String inqDate)
        {
            this.inqDate = inqDate;
        }
        /**
         * @return the orgCd
         */
        public String getOrgCd()
        {
            return orgCd;
        }
        /**
         * @param orgCd the orgCd to set
         */
        public void setOrgCd(String orgCd)
        {
            this.orgCd = orgCd;
        }
        /**
         * @return the jijumCd
         */
        public String getJijumCd()
        {
            return jijumCd;
        }
        /**
         * @param jijumCd the jijumCd to set
         */
        public void setJijumCd(String jijumCd)
        {
            this.jijumCd = jijumCd;
        }
        /**
         * @return the macNo
         */
        public String getMacNo()
        {
            return macNo;
        }
        /**
         * @param macNo the macNo to set
         */
        public void setMacNo(String macNo)
        {
            this.macNo = macNo;
        }
        /**
         * @return the hisClose
         */
        public long getHisClose()
        {
            return hisClose;
        }
        /**
         * @param hisClose the hisClose to set
         */
        public void setHisClose(long hisClose)
        {
            this.hisClose = hisClose;
        }

    }

    /**
     *
     * FnMacClose
     * <pre>
     * sp_fn_macClose_nh call input VO
     * </pre>
     *
     * @author s7760ker@gmail.com
     * @version 1.0
     * @see
     */
    public class FnMacClose {
        /*
        pCloseDate      IN        t_fn_close.close_date%TYPE,
        pOrgCode        IN        t_fn_close.org_cd%TYPE,
        pJijumCode      IN        t_fn_close.jijum_cd%TYPE,
        pMacNo          IN        t_fn_close.mac_no%TYPE,
        pUserId         IN        t_fn_close.insert_uid%TYPE,
        vResult         OUT       VARCHAR2
        */

        private String pCloseDate   ;
        private String pOrgCode     ;
        private String pJijumCode   ;
        private String pMacNo       ;
        private String pUserId      ;
        private String vResult      ;
        /**
         * @return the pCloseDate
         */
        public String getpCloseDate()
        {
            return pCloseDate;
        }
        /**
         * @param pCloseDate the pCloseDate to set
         */
        public void setpCloseDate(String pCloseDate)
        {
            this.pCloseDate = pCloseDate;
        }
        /**
         * @return the pOrgCode
         */
        public String getpOrgCode()
        {
            return pOrgCode;
        }
        /**
         * @param pOrgCode the pOrgCode to set
         */
        public void setpOrgCode(String pOrgCode)
        {
            this.pOrgCode = pOrgCode;
        }
        /**
         * @return the pJijumCode
         */
        public String getpJijumCode()
        {
            return pJijumCode;
        }
        /**
         * @param pJijumCode the pJijumCode to set
         */
        public void setpJijumCode(String pJijumCode)
        {
            this.pJijumCode = pJijumCode;
        }
        /**
         * @return the pMacNo
         */
        public String getpMacNo()
        {
            return pMacNo;
        }
        /**
         * @param pMacNo the pMacNo to set
         */
        public void setpMacNo(String pMacNo)
        {
            this.pMacNo = pMacNo;
        }
        /**
         * @return the pUserId
         */
        public String getpUserId()
        {
            return pUserId;
        }
        /**
         * @param pUserId the pUserId to set
         */
        public void setpUserId(String pUserId)
        {
            this.pUserId = pUserId;
        }
        /**
         * @return the vResult
         */
        public String getvResult()
        {
            return vResult;
        }
        /**
         * @param vResult the vResult to set
         */
        public void setvResult(String vResult)
        {
            this.vResult = vResult;
        }

    }
}
