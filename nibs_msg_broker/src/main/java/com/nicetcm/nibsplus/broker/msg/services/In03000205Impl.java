package com.nicetcm.nibsplus.broker.msg.services;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnBoxOrgMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnBoxOrg;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

/**
 *
 * 0205 정산기 봉투입금-기타
 * <pre>
 * MngCM_SaveCalcBoxEtc( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03000205")
public class In03000205Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03000205Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnBoxOrgMapper tFnBoxOrgMapper;

    @Autowired private TMiscMapper tMiscMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TMisc tMisc = new TMisc();
        tMisc.setOrgCd(parsed.getString("CM.org_cd"));
        tMisc.setBranchCd(parsed.getString("brch_cd"));
        tMisc.setMacNo(parsed.getString("mac_no"));
        tMisc.setDealDate(parsed.getString("own_trade_date"));
        tMisc.setSeq(parsed.getString("own_seq_no"));

        int nRtn;

        /* 원거래 데이터가 존재하는지 체크 */
        nRtn = comPack.getOwnTradeSeqYN(tMisc);

        /* 이마트 일경우에는 원거래 데이터 체크 하나 이랜드는 안한다 */
        if(MsgBrokerConst.EMART_CODE.equals(parsed.getString("CM.org_cd")) && nRtn < 0) {
            logger.warn( String.format("원거래 데이터 없음 [%s][%s][%s]", parsed.getString("mac_no"), parsed.getString("own_trade_date"), parsed.getString("own_seq_no")) );
            throw new MsgBrokerException(-1);
        }

        final int   nKJCashCnt = 5;         /*  지폐필드 갯수     */
        final int   nKJCoinCnt = 4;         /*  주화필드 갯수     */
        final int   nKJCheckCnt = 4;        /*  수표필드 갯수     */
        final int   nKJNonfixedCnt = 1;     /*  비금액수표 갯수    */
        final int   nKJCouponCnt = 3;       /*  쿠폰필드 갯수     */
        final int   nKJForeignCnt = 4;      /*  외화필드 갯수     */

        final int   nkJCnt = nKJCashCnt+nKJCoinCnt+nKJCheckCnt+nKJNonfixedCnt+nKJCouponCnt+nKJForeignCnt;       /*   21 */

        int         nKJCashIdx = 0;                                     /*  현금필드 시작인덱스 0    */
        int         nKJCoinIdx = nKJCashIdx + nKJCashCnt;               /*  주화필드 시작인덱스 5    */
        int         nKJCheckIdx = nKJCoinIdx + nKJCoinCnt;              /*  수표필드 시작인덱스 9    */
        int         nKJNonfixedIdx = nKJCheckIdx + nKJCheckCnt;         /*  비정액수표필드 시작인덱스 13    */
        int         nKJCouponIdx = nKJNonfixedIdx + nKJNonfixedCnt;     /*  쿠폰필드 시작인덱스 14   */
        int         nKJForeignIdx = nKJCouponIdx + nKJCouponCnt;        /*  외화필드 시작인덱스 17   */

        /* 권종공통코드 : '0001' - 일천원권
                          '0003' - 오천원권
                          '0004' - 일만원권
                          '0005' - 삼만원권
                          '0006' - 오만원권
                          '0007' - 칠만원권
                          '0008' - 십만원권
                          '0009' - 삼십만원권
                          '0010' - 오십만원권
                          '0011' - 백만원권
                          '0002' - 이천원권
                          '0012' - 10원주
                          '0013' - 50원주
                          '0014' - 100원주
                          '0015' - 500원주
                          '0016' - 삼천원권
                          '9999' - 기타권종
        */

        /* 쿠폰은 권종없이 */
        /* 비정액수표는 기타권종 */


        /* 봉투입금-기타는 외화일 경우에만 box_gubun2에 데이터가 들어간다. */

        /** 지폐 Insert */
        for(int i = 0; i < Type03000205.CASH_PAPER.length; i++ ) {
            int szCnt = parsed.getInt(Type03000205.CASH_PAPER[i]);
            long szAmt = szCnt * Type03000205.CASH_PAPER_AMT[i];

            /** 매수가 존재할 경우, 각 화폐(상품권)을 구분하여 코드를 기록한다.        */
            if(szCnt > 0) {
                insertTFnBoxOrg(parsed, safeData, "0001", null, Type03000205.CASH_PAPER_GB[i], szCnt, szAmt);
            }
        }

        /** 동전 Insert */
        for(int i = 0; i < Type03000205.CASH_COIN.length; i++ ) {
            int szCnt = parsed.getInt(Type03000205.CASH_COIN[i]);
            long szAmt = szCnt * Type03000205.CASH_COIN_AMT[i];

            /** 매수가 존재할 경우, 각 화폐(상품권)을 구분하여 코드를 기록한다.        */
            if(szCnt > 0) {
                insertTFnBoxOrg(parsed, safeData, "0002", null, Type03000205.CASH_COIN_GB[i], szCnt, szAmt);
            }
        }

        /** 수표 Insert */
        for(int i = 0; i < Type03000205.CHECK.length; i++ ) {
            int szCnt = parsed.getInt(Type03000205.CHECK[i]);
            long szAmt = szCnt * Type03000205.CHECK_AMT[i];

            /** 매수가 존재할 경우, 각 화폐(상품권)을 구분하여 코드를 기록한다.        */
            if(szCnt > 0) {
                insertTFnBoxOrg(parsed, safeData, "0003", null, Type03000205.CHECK_GB[i], szCnt, szAmt);
            }
        }

        /** 비정액권 수표 Insert */
        if(parsed.getInt("check_cnt_nonfixed") > 0) {
            insertTFnBoxOrg(parsed, safeData, "0003", null, null, parsed.getInt("check_cnt_nonfixed"), parsed.getLong("check_amt_nonfixed"));
        }

        /** 쿠폰 Insert */
        if(parsed.getInt("cupon_cnt") > 0) {
            insertTFnBoxOrg(parsed, safeData, "0005", null, null, parsed.getInt("cupon_cnt"), parsed.getLong("cupon_amt"));
        }

        /** 현금쿠폰 Insert */
        if(parsed.getInt("cash_cupon_cnt") > 0) {
            insertTFnBoxOrg(parsed, safeData, "0007", null, null, parsed.getInt("cash_cupon_cnt"), parsed.getLong("cash_cupon_amt"));
        }

        /** 정률할인권 Insert */
        if(parsed.getInt("rate_cupon_cnt") > 0) {
            insertTFnBoxOrg(parsed, safeData, "0009", null, null, parsed.getInt("rate_cupon_cnt"), 0); /* 정률할인권은 매수만 받음 */
        }

        /** 외화 Insert */

        /** 봉투입금-기타는 외화일 경우에만 box_gubun2에 데이터가 들어간다.
            char szAryBoxGb2[4][5];
            strcpy( szAryBoxGb2[0], "01" );  달러
            strcpy( szAryBoxGb2[1], "02" );  유로
            strcpy( szAryBoxGb2[2], "03" );  엔화
            strcpy( szAryBoxGb2[3], "04" );  위엔
        */
        if(parsed.getInt("dollar_cnt") > 0) {
            insertTFnBoxOrg(parsed, safeData, "0010", "01", null, parsed.getInt("dollar_amt"), parsed.getInt("dollar_cnt"));
        }

        if(parsed.getInt("euro_cnt") > 0) {
            insertTFnBoxOrg(parsed, safeData, "0010", "02", null, parsed.getInt("euro_amt"), parsed.getInt("euro_cnt"));
        }

        if(parsed.getInt("yen_cnt") > 0) {
            insertTFnBoxOrg(parsed, safeData, "0010", "03", null, parsed.getInt("yen_amt"), parsed.getInt("yen_cnt"));
        }

        if(parsed.getInt("yuan_cnt") > 0) {
            insertTFnBoxOrg(parsed, safeData, "0010", "04", null, parsed.getInt("yuan_amt"), parsed.getInt("yuan_cnt"));
        }

    }//end method

    /**
     *
     * 03000205 권종
     * <pre>
     * 03000205 권종 Field Name
     * </pre>
     *
     * @author s7760ker@gmail.com
     * @version 1.0
     * @see
     */
    private static class Type03000205 {
        public static String[] CASH_PAPER = {
            "cash_cnt_100000",
            "cash_cnt_50000",
            "cash_cnt_10000",
            "cash_cnt_5000",
            "cash_cnt_1000"
        };

        public static int[] CASH_PAPER_AMT = {
            100000,
            50000,
            10000,
            5000,
            1000
        };

        public static String[] CASH_PAPER_GB = {
            "0008",
            "0006",
            "0004",
            "0003",
            "0001"
        };

        public static String[] CASH_COIN = {
            "cash_cnt_500",
            "cash_cnt_100",
            "cash_cnt_50",
            "cash_cnt_10"
        };

        public static int[] CASH_COIN_AMT = {
            500,
            100,
            50,
            10
        };

        public static String[] CASH_COIN_GB = {
            "0015", //500원주
            "0014", //100원주
            "0013", //50원주
            "0012"  //10원주
        };

        public static String[] CHECK = {
            "check_cnt_1000000",
            "check_cnt_500000",
            "check_cnt_300000",
            "check_cnt_100000",
        };

        public static int[] CHECK_AMT = {
            1000000,
            500000,
            300000,
            100000
        };

        public static String[] CHECK_GB = {
            "0011", //백만원권
            "0010", //오십만원권
            "0009", //삼십만원권
            "0008"  //십만원권
        };

        public static String[] FOREIGN = {
            "dollar_cnt",
            "dollar_amt",
            "euro_cnt",
            "euro_amt",
            "yen_cnt",
            "yen_amt",
            "yuan_cnt",
            "yuan_amt"
        };
    }

    private void insertTFnBoxOrg(MsgParser parsed, MsgBrokerData safeData, String szBoxGb, String szBoxGb2, String szAryKjGb, int szCnt, long szAmt) throws Exception {

        /* BOX 데이터가 RETRY 되어 중복 수신되었을 경우 저장 하지 않고 정상으로 RETURN 처리*/
        TMisc tMisc = new TMisc();
        tMisc.setOrgCd(parsed.getString("CM.org_cd"));
        tMisc.setBranchCd(parsed.getString("brch_cd"));
        tMisc.setMacNo(parsed.getString("mac_no"));
        tMisc.setDealDate(parsed.getString("own_trade_date"));
        tMisc.setSeq(parsed.getString("own_seq_no"));

        tMisc.setBoxGubun1(szBoxGb);
        tMisc.setBoxGubun2("");

        if(szAryKjGb != null) {
            tMisc.setKjGubun(szAryKjGb);
        }


        if(comPack.getBoxRecvYN(tMisc) > 0) {
            return;
        }

        TFnBoxOrg tFnBoxOrg = tMiscMapper.generateSeqBox();
        tFnBoxOrg.setOrgCd     (parsed.getString("CM.org_cd"));
        tFnBoxOrg.setBranchCd  (parsed.getString("brch_cd"));
        tFnBoxOrg.setMacNo     (parsed.getString("mac_no"));
        tFnBoxOrg.setDealDate  (parsed.getString("own_trade_date"));
        tFnBoxOrg.setSeq       (parsed.getString("own_seq_no"));
        //tFnBoxOrg.setBoxSeq    (parsed.getString(""));TFnBoxOrg 생성시 시퀀스에서 가져옴
        tFnBoxOrg.setBoxGubun1 (szBoxGb);

        if(szBoxGb2 != null) {
            tFnBoxOrg.setBoxGubun2 (szBoxGb2);
        }

        if(szAryKjGb != null) {
            tFnBoxOrg.setKjGubun   (szAryKjGb);
        }

        tFnBoxOrg.setKjCnt     (szCnt);
        tFnBoxOrg.setKjAmt     (szAmt);
        tFnBoxOrg.setUpdateDate(safeData.getDSysDate());
        tFnBoxOrg.setUpdateUid ("online");

        try
        {
            tFnBoxOrgMapper.insertSelective(tFnBoxOrg);
        } catch (Exception e)
        {
            logger.warn( ">>> [MngCM_SaveCalcBoxEtc] (T_FN_INOUT) INSERT ERROR [{}]", e.getMessage());
            throw new MsgBrokerException(-1);
        }

        logger.warn( "!!!처리완료(INSERT)!!!" );
    }
}
