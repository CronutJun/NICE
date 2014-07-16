package com.nicetcm.nibsplus.broker.msg;

public class MsgBrokerConst {
    
    /* 전문헤더 길이 */
    public static final int HEADER_LEN = 90;
    /*
     * 금융결제원(KFTC)
     */
    public static final String KFTC_CODE = "000";
    /*
     * 우리은행
     */
    public static final String WORI_CODE = "d20";
    /*
     * 제일은행 
     */
    public static final String JEIL_CODE = "023";
    /*
     * 한미은행
     */
    public static final String HANMI_CODE = "027";
    /*
     * 하나은행 (사용안함)
     */
    public static final String HANA_CODE = "08D";
    /*
     * NICE
     */
    public static final String NICE_CODE = "096";
    /*
     * 국민은행service
     */
    public static final String KBST_CODE = "004";
    /*
     * 농협
     */
    public static final String NONGH_CODE = "011";
    /*
     * 기업은행 ATMS
     */
    public static final String KIUP_CODE = "003";
    /*
     * 외환은행
     */
    public static final String KEB_CODE  = "005";
    /*
     * 부산은행
     */
    public static final String BU_CODE = "032";
    /*
     * 부산은행 ATMS
     */
    public static final String BUATMS_CODE = "T32";
    /*
     * 광주은행
     */
    public static final String KJB_CODE = "034";
    /*
     * 경남은행
     */
    public static final String KNB_CODE = "d39";
    /*
     * 새마을 금고
     */
    public static final String KFCC_CODE = "045";
    /*
     * 정통부
     */
    public static final String JTB_CODE = "071";
    /*
     * 정통부2(개별망)
     */
    public static final String JTB2_CODE = "072";
    /*
     * 우체국ATMS(기존과 별도)
     */
    public static final String WC_CODE = "0WC";
    /*
     * 신한(합병)(사용안함)
     */
    public static final String SHB_CODE = "D88";
    /*
     * 신한ATMS
     */
    public static final String SHATMS_CODE = "088";
    /*
     * 하나ATMS 테스트
     */
    public static final String HANAATMS_CODE = "081";

    /*
     * 신세계 첼시
     */
    public static final String CS_CODE  = "0CS";
    /*
     * 삼성생명
     */
    public static final String SL_CODE = "0SL";
    /*
     * S1
     */
    public static final String S1_CODE = "0S1";
    /*
     * CAPS
     */
    public static final String CP_CODE = "0CP";
    /*
     * CAPS 2
     */
    public static final String CQ_CODE = "0CQ";
    /*
     * KT
     */
    public static final String KT_CODE = "0KT";
    /*
     * JE
     */
    public static final String JE_CODE = "0JE";
    /*
     * 대우증권
     */
    public static final String DW_CODE = "0DW";
    /*
     * 동양종금
     */
    public static final String DJ_CODE = "0DJ";
    /*
     * 제일은행브랜드제휴
     */
    public static final String JL_CODE = "0JL";
    /*
     * 미래에셋
     */
    public static final String MR_CODE = "0MR";
    /*
     * 기업은행브랜드제휴
     */
    public static final String BK_CODE = "0BK";
    /*
     * 요넷
     */
    public static final String YN_CODE = "0YN";
    /*
     * KIBanking
     */
    public static final String KI_CODE = "0KI";
    /*
     * 현대증권(일괄관리)
     */
    public static final String HD_CODE = "0HD";
    /*
     * 우리투자증권
     */
    public static final String WM_CODE = "0WM";
    /*
     * 대구은행
     */
    public static final String DGB_CODE = "031";
    /*
     * 삼성증권
     */
    public static final String SG_CODE = "0SG";
    /*
     * 신한금융투자
     */
    public static final String GM_CODE = "278";
    /*
     * 신한카드론
     */
    public static final String LC_CODE = "0LC";
    /*
     * 이마트
     */
    public static final String EMART_CODE = "0EM";
    /*
     * 신협
     */
    public static final String SHYUP_CODE = "048";
    /*
     * 한국투자증권
     */
    public static final String HANT_CODE = "243";
    /*
     * 대신증권
     */
    public static final String DS_CODE = "0DS";
    /*
     * 우리은행ATMS
     */
    public static final String WRATMS_CODE = "020";
    /*
     * 경남은행ATMS 
     */
    public static final String KNATMS_CODE = "039";
    /*
     * 제주은행
     */
    public static final String JEJU_CODE = "035";
    /*
     * 이랜드
     */
    public static final String ELAND_CODE = "0EL";
    /*
     * 출동알림
     */
    public static final String ALARM_CODE = "0DM";
    /*
     * 요넷키오스크
     */
    public static final String YK_CODE = "0YK";
    /*
     * 기업은행 재구축ATMS
     */
    public static final String KIUP_NEW_CODE = "0KU";
    /*
     * 전자상품권
     */
    public static final String GV_CODE = "0GV";
    /*
     * ktTR Tax Refund
     */
    public static final String TAXRF_CODE = "0TR";
    /*
     * 기기사 코드-효성
     */
    public static final String ATM_HY_CODE = "7HS";
    /*
     * 기기사 코드-청호
     */
    public static final String ATM_CH_CODE = "7CH";
    /*
     * 기기사 코드-LG
     */
    public static final String ATM_LG_CODE = "7LG";


    
    /*
     * 출동요청 자동/수동
     */
    public static final String CALL_TYPE_AUTO = "9";
    /*
     * 출동요청 민원
     */
    public static final String CALL_TYPE_TEL = "3";


    public static final String NICE_BRCH_CD = "9600";              /* 나이스지점코드              */
    /*
     * ********** 장애 관련 DEFINE START **********
     */
    public static final int AUTO_SEND_TRUE   = 1;
    public static final int AUTO_SEND_FALSE  = 0;
    public static final int AUTO_FINISH      = 2;
    public static final int AUTO_NOT_CREATE  = 3;
    public static final int AUTO_KB_COPY     = 4;

    /* 
     * 나이스에서 요청한 에러 
     */
    public static final String NICE_ERROR_USER_N02 = "NI902";   /*픽에러*/
    public static final String NICE_ERROR_USER_N03 = "NI903";   /*픽이상*/
    public static final String NICE_ERROR_USER_N04 = "NI904";   /*무거래*/
    public static final String NICE_ERROR_USER_N07 = "NI907";   /*무거래(특별) 20080215 추가 */
    public static final String NICE_ERROR_USER_N08 = "NI908";   /*무거래(일반) 20080215 추가 */
    public static final String NICE_ERROR_USER_N05 = "NI905";   /*잔액조회*/
    public static final String NICE_ERROR_USER_N06 = "NI906";   /*지폐방출후 잔액조회*/
    public static final String NICE_ERROR_USER_N09 = "NI909";   /*empty 센서 이상*/
    public static final String NICE_ERROR_USER_N10 = "NI910";   /*IC무거래*/
    public static final String NICE_ERROR_USER_N11 = "NI911";   /*픽에러(EMPTY)*/
    public static final String NICE_ERROR_USER_N12 = "NI912";   /*현금부족(기준금액)*/
    public static final String NICE_ERROR_USER_N13 = "NI913";   /*기준금액없음*/
    public static final String NICE_ERROR_USER_N14 = "NI914";   /*현금부족예보(기준금액)*/
    public static final String NICE_ERROR_USER_N15 = "NI915";   /*금고침투(진동)*/

    /*======================================================================*/
    /* Message Type Code 정의                                               */
    /*======================================================================*/
    public static final String ES_CODE             = "ES";
    public static final String EM_CODE             = "EM";
    public static final String SM_CODE             = "SM";
    public static final String CM_CODE             = "CM";
    public static final String IQ_CODE             = "IQ";
    public static final String NS_CODE             = "NS";
    public static final String NT_CODE             = "NT";
    public static final String NC_CODE             = "NC";
    public static final String NI_CODE             = "NI";
    public static final String NQ_CODE             = "NQ";
    public static final String MM_CODE             = "MM";

    /*======================================================================*/
    /* 전문구분 Code 정의                                                   */
    /*======================================================================*/
    public static final String EM_REQ              = "0100";
    public static final String EM_ANS              = "0110";
    public static final String ES_REQ              = "0500";
    public static final String ES_ANS              = "0510";
    public static final String SM_REQ              = "0200";
    public static final String SM_ANS              = "0210";
    public static final String CM_REQ              = "0300";
    public static final String CM_ANS              = "0310";
    public static final String IQ_REQ              = "0400";
    public static final String IQ_ANS              = "0410";
    public static final String NS_REQ              = "N200";
    public static final String NS_ANS              = "N210";
    public static final String NC_REQ              = "N300";
    public static final String NC_ANS              = "N310";
    public static final String NI_REQ              = "N400";
    public static final String NI_ANS              = "N410";
    public static final String NQ_REQ              = "N500";
    public static final String NQ_ANS              = "N510";
    public static final String MM_REQ              = "M100";
    public static final String MM_ANS              = "M110";

    public static final String NT_REQ              = "N100";
    public static final String NT_ANS              = "N110";

    public static final String REQ_CODE            = "00";
    public static final String ANS_CODE            = "10";
    

    /*======================================================================*/
    /* 나이스 장애, 개국(복구), 상태                                       */
    /*======================================================================*/
    public static final String WORK_NICE_ERR_STATE   = "0120";

    /* 통신망관리정보 */
    public static final String NICE_STATE_START      = "001";      /* 개국전문 */
    public static final String NICE_STATE_ERR        = "301";      /* 장애정보 */
    public static final String NICE_STATE_LINE_CLEAR = "302";      /* 회선장애 회복 */
    public static final String NICE_STATE_LINE_ERR   = "608";      /* 회선장애 */
    public static final String NICE_STATE_AC_ERR     = "310";      /* AC 전원차단 */
    public static final String NICE_USER_ERR_REPAIR  = "999";      /* 트란에서의 나이스 발생 장애 복구 요청 처리     */
                                                /* HOST와 상관없이 NIBS에서 임의 사용          */

    /* 지폐함 및 용지 상태 */
    public static final String NICE_BOX_GOOD         = "0";        /* 정상상태 */
    public static final String NICE_BOX_NEAREND      = "1";        /* 거의 빔 */
    public static final String NICE_BOX_EMPTY        = "2";        /* 빔 */

    /* ATM H/W 모둘 장애 */
    public static final String NICE_HW_GOOD          = "0";        /* 정상 */
    public static final String NICE_HW_NOTGOOD       = "1";        /* 다소주의 */
    public static final String NICE_HW_WARNING       = "2";        /* 주의요망 */
    public static final String NICE_HW_NEARERROR     = "3";        /* 즉시출동 */
    public static final String NICE_HW_ERROR         = "4";        /* 긴급출동 */
    public static final String NICE_HW_IGN           = "9";        /* 임의사용 - 무시 */

    /* ATM 감시전문 */
    public static final String NICE_NORMAL           = "0";        /* 정상상태 */
    public static final String NICE_ERROR            = "1";        /* 침투상태 */
    public static final String NICE_NO_SET           = "2";        /* 결함상태 */
/*
     * 서버 발생 Error Code 정의
     */
    public static final String ERROR_NORMAL           = "00  "; /* 정상                   */
    public static final String ERROR_NO_MACINFO       = "01  "; /* 점기번정보 없음     */
    public static final String ERROR_TIME_OUT         = "02  "; /* Time Out Error       */
    public static final String ERROR_ILL_FORMAT_TYPE  = "05  "; /* 전문 Format Error  */
    public static final String ERROR_NO_MACINCO_2     = "06  "; /* 관리코너아님           */
    public static final String ERROR_NOT_BUSINESS_DAY = "DT  "; /* 비영업일             */
    public static final String ERROR_DUP_ERROR        = "01  "; /* 중복장애(농협일경우)  */
    public static final String ERROR_DB_PROC          = "09  "; /* 요청전문번호 없음 ?  */
    public static final String ERROR_DATA_LENGTH      = "11  "; /* 전문 길이 이상     */
    public static final String ERROR_DB               = "12  "; /* DB Error             */
    public static final String ERROR_OVER_TERM        = "13  "; /* 조회기간초과           */
    public static final String ERROR_REMOTE_NOT_EXEC  = "71  "; /* 실행할수 없음      */
    public static final String ERROR_FINISH_ERRDATA   = "80  "; /* 이미완료된장애      */
    public static final String ERROR_ILL_BRANCH       = "85  "; /* 해당지점없음       */
    public static final String ERROR_DUPL_DATA        = "98  "; /* 전문 중복 Error      */
    public static final String ERROR_ETC              = "99  "; /* 기타 Error         */
    public static final String ERROR_NO_DATA          = "91  "; /* 해당일자 데이터없음   */
    public static final String ERROR_NORMAL_2         = "0000"; /* 경남은행 정상 응답코드 */
    public static final String ERROR_DELETE           = "DE  "; /* HOST RETRY 중지요청 */
    public static final String ERROR_MISMATCH_FILECNT = "001 "; /* FTP File 개수 맞지 않음 */
    public static final String ERROR_ILL_USER_TELNO   = "86"  ; /* 인증번호요청 전문의 고객전화번호 이상 */
    /* HOST 기정의 코드 68, H*, 72, NE 는 응답코드로 정의 불가 */


    public static final String NS_Q_NAME = "N200.0120";         /* 나이스 상태 Producer Q name       */
    public static final String ES_Q_NAME = "0500.0110";         /* 신한 통 전문 처리 Producer Q name */
    public static final String NS_ERR_STATE = "0120";
    
    public static final int    TRAN_UPDATE_STATE      = 0;
    public static final int    TRAN_INSERT_STATE      = 1;


}
