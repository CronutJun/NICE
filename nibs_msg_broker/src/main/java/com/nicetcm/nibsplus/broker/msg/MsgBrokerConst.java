package com.nicetcm.nibsplus.broker.msg;

public class MsgBrokerConst {

    public static final String SVR_TYPE = System.getProperty("server.type", "dev");

    /* 전문헤더 길이 */
    public static final int HEADER_LEN = 180;
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

    public static final String NICE_ERROR_LINE_ERROR           = "NI101"; /* 회선장애     */
    public static final String NICE_ERROR_AC_ERROR             = "NI102"; /* AC전원차단   */
    /*
     *  나이스 에러 코드
     */
    public static final String NICE_ERROR_CASHBOX_EMPTY        = "NI110"; /* 지폐함 빔*/
    public static final String NICE_ERROR_COLLECTBOX_EMPTY     = "NI111"; /* 회수함 참*/
    public static final String NICE_ERROR_SPECSBOX_EMPTY       = "NI112"; /* 명세표 빔*/
    public static final String NICE_ERROR_INBOX_FULL           = "NI114"; /* 입금함 참*/
    public static final String NICE_ERROR_CHECKBOX_EMPTY       = "NI115"; /* 수표함 빔*/

    /*
     *  ATM H/W 장애
     */
    public static final String NICE_ERROR_CASHOUT_ERROR        = "NI121"; /* 지폐방출기*/
    public static final String NICE_ERROR_CARDREAD_ERROR       = "NI122"; /* 카드판독기*/
    public static final String NICE_ERROR_SPECS_ERROR          = "NI123"; /* 명세표*/
    public static final String NICE_ERROR_JOURNAL_ERROR        = "NI124"; /* 저널*/
    public static final String NICE_ERROR_INBOX_ERROR          = "NI126"; /* 입금함 */
    public static final String NICE_ERROR_SYSTEM_DISK_ERROR    = "NI127"; /* SYSTEM DISK */
    public static final String NICE_ERROR_BANKBOOK_ERROR       = "NI128"; /* 통장정리부 */
    public static final String NICE_ERROR_SYSTEM_CONTROL_ERROR = "NI119"; /* 시스템 제어부 */
    public static final String NICE_ERROR_DEAL_LIST_ERROR      = "NI129"; /* 거래내역출력부 */
    public static final String NICE_ERROR_PINPAD_ERROR         = "NI118"; /* 암호화장비 */
    public static final String NICE_ERROR_CASHIN_ERROR         = "NI131"; /* 지폐미수취*/
    public static final String NICE_ERROR_TMONEY_ERROR         = "NI132"; /* T-MONEY*/
    public static final String NICE_ERROR_DONGUL_ERROR         = "NI133"; /* 동글이*/
    public static final String NICE_ERROR_DVR_ERROR            = "NI134"; /* DVR 2003.11.13 추가 */
    public static final String NICE_ERROR_PRINTER              = "NI130"; /* 프린터기 장애 20071106 추가 */
    public static final String NICE_ERROR_INPUT_CHECK          = "NI135"; /* 수표입금부   20090701    */
    public static final String NICE_ERROR_OUT_CHECK            = "NI136"; /* 수표출금부 20090701      */
    public static final String NICE_ERROR_INPUT_BOX_50000      = "NI137"; /* 오만원권입금부 20090701    */
    public static final String NICE_ERROR_INPUT_BOX_100000     = "NI138"; /* 십만원권입금부 20090701    */
    public static final String NICE_ERROR_REMAIN_MONEY         = "NI139"; /* 지폐잔류 20100129       */

    /*
     *  ATM 감시 전문
     */
    public static final String NICE_ERROR_SUPERVISOR           = "NI140"; /* 슈퍼바이저*/
    public static final String NICE_ERROR_ATMWATCH_ERROR       = "NI141"; /* 침투상태 */
    public static final String NICE_ERROR_URGENCY_ERROR        = "NI142"; /* 침투상태(긴급) */
    public static final String NICE_ERROR_ATMWATCH_OPEN_ERROR  = "NI143"; /* 침투상태(금고개폐) 20080109 추가 */
    public static final String NICE_ERROR_ATMWATCH_CLOSE_ERROR = "NI144"; /* 침투상태(금고잠금) 20080109 추가 */

    /*
     * 나이스에서 요청한 에러
     */
    public static final String NICE_ERROR_USER_N02             = "NI902"; /*픽에러*/
    public static final String NICE_ERROR_USER_N03             = "NI903"; /*픽이상*/
    public static final String NICE_ERROR_USER_N04             = "NI904"; /*무거래*/
    public static final String NICE_ERROR_USER_N07             = "NI907"; /*무거래(특별) 20080215 추가 */
    public static final String NICE_ERROR_USER_N08             = "NI908"; /*무거래(일반) 20080215 추가 */
    public static final String NICE_ERROR_USER_N05             = "NI905"; /*잔액조회*/
    public static final String NICE_ERROR_USER_N06             = "NI906"; /*지폐방출후 잔액조회*/
    public static final String NICE_ERROR_USER_N09             = "NI909"; /*empty 센서 이상*/
    public static final String NICE_ERROR_USER_N10             = "NI910"; /*IC무거래*/
    public static final String NICE_ERROR_USER_N11             = "NI911"; /*픽에러(EMPTY)*/
    public static final String NICE_ERROR_USER_N12             = "NI912"; /*현금부족(기준금액)*/
    public static final String NICE_ERROR_USER_N13             = "NI913"; /*기준금액없음*/
    public static final String NICE_ERROR_USER_N14             = "NI914"; /*현금부족예보(기준금액)*/
    public static final String NICE_ERROR_USER_N15             = "NI915"; /*금고침투(진동)*/


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


    public static final String MODE_UPDATE_NO_CLEAR      = "0";    /* HW Error No Clear */
    public static final String MODE_UPDATE_HW_ALL_CLEAR  = "1";    /* HW Error ALL Clear */
    public static final String MODE_UPDATE_HW_ONE_CLEAR  = "2";    /* HW Error ONE Clear */
    public static final String MODE_UPDATE_ONLY_HW_CLEAR = "3";    /* 상태복구에의한 Clear가 아닌 H/W장애코드에의한 복구 */

    public static final int    DB_UPDATE_ERROR_MNG   = 200;
    public static final int    DB_UPDATE_CANCEL_MNG  = 210;        /* 신규장애발생 없이 기존것 취소처리 */
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

    public static final int    CURERR_ATM             = 0;     /* 일괄기기 (ATM관리기기)   */
    public static final int    CURERR_NICE            = 1;     /* 나이스기기 (CD_VAN)      */
    public static final int    CURERR_CALC            = 2;     /* 정산기                   */

    public static final byte   STATE_CLEAR            = '0';   /* 복구   */
    public static final byte   STATE_NEAR             = '1';   /* 예보   */
    public static final byte   STATE_END              = '2';   /* 발생 */
    public static final byte   STATE_SKIP1            = '9';   /* 무시1 */
    public static final byte   STATE_SKIP2            = ' ';   /* 무시2 */
    public static final byte   STATE_SKIP3            = 0x00;  /* 무시3 */

    public static final String ALARM_AGENCY_OFF       = "NE291";/* 출동알림 AGENCY_OFF  */
    public static final String ALARM_PLAYER_OFF       = "NE292";/* 출동알림 PLAYER_OFF  */
    public static final String ALARM_SETTOP_OFF       = "NE293";/* 출동알림 SETTOP_OFF  */
    public static final String ALARM_NON_SCHDULE      = "NE294";/* 출동알림 스케줄없음   */
    public static final String ALARM_NON_FILE         = "NE295";/* 출동알림 파일  없음  */

    /* 정산기 감시전문 */
    public static final byte   CALC_MAC_NORMAL        = '0';    /* 정상상태 */
    public static final byte   CALC_MAC_SUPERVISOR    = '1';    /* 슈퍼바이저 모드 상태 */
    public static final byte   CALC_MAC_ERROR         = '2';    /* 침투상태 */

    public static final String  HWERR_CLEAR           = "0";    /* 복구   */
    public static final String  HWERR_ERROR           = "1";    /* 장애   */
    public static final String  HWERR_OPEN            = "2";    /* 개국   */
    public static final String  HWERR_CLOSE           = "3";    /* 폐국   */

    public static final String CD_ERROR_STATE_NEAR    = "NE1"; /* 예보상태 */
    public static final String CD_ERROR_STATE_END     = "NE2"; /* 장애상태 */

    /*
     *  ATM 상태 구분
     */
    public static final String CD_ERROR_CASH                = "00";  /* 만원 부족        */
    public static final String CD_ERROR_CHECK               = "01";  /* 수표 부족        */
    public static final String CD_ERROR_SPECS               = "02";  /* 전표 부족        */
    public static final String CD_ERROR_CASHBOX             = "03";  /* 현금 입금함 참     */
    public static final String CD_ERROR_CHECKBOX            = "04";  /* 수표 입금함 참     */
    public static final String CD_ERROR_RETURNBOX           = "05";  /* 지폐 회수함 참     */
    public static final String CD_ERROR_BOOKBOX             = "06";  /* 통장 회수함 참     */
    public static final String CD_ERROR_CARDBOX             = "07";  /* 카드 회수함 참     */
    public static final String CD_ERROR_SPECSBOX            = "08";  /* 전표 회수함 참     */
    public static final String CD_ERROR_JNRBOX              = "09";  /* 저널 회수함 참     */
    public static final String CD_ERROR_SEND_RECV           = "10";  /* 송수신 장애        */
    public static final String CD_ERROR_LINE_ERR            = "11";  /* 회선 장애        */
    public static final String CD_ERROR_POWER_OFF           = "12";  /* 전원 OFF 장애      */
    public static final String CD_ERROR_JNRLACK             = "13";  /* 저널부족         */
    public static final String CD_ERROR_INPUTSTOP           = "14";  /* 입금부미가동       */
    public static final String CD_ERROR_TIELACK             = "15";  /* 지폐입금부 묶음띠 부족 */
    public static final String CD_ERROR_COINBOX             = "16";  /* 동전 입금함 참     */
    public static final String CD_ERROR_5000                = "17";  /* 오천원 부족        */
    public static final String CD_ERROR_1000                = "18";  /* 천원 부족        */
    public static final String CD_ERROR_500                 = "19";  /* 오백원권 부족      */
    public static final String CD_ERROR_100                 = "20";  /* 백원권 부족        */
    public static final String CD_ERROR_50                  = "21";  /* 오십원권 부족      */
    public static final String CD_ERROR_10                  = "22";  /* 십원권 부족        */
    public static final String CD_ERROR_50000               = "23";  /* 오만원권 부족      */
    public static final String CD_ERROR_100000              = "24";  /* 십만원권 부족      */
    public static final String CD_ERROR_CASHBOX_50000       = "25";  /* 오만원권 입금함 참   */
    public static final String CD_ERROR_CASHBOX_100000      = "26";  /* 십만원권 입금함 참   */
    public static final String CD_ERROR_CASHBOX_5000        = "42";  /* 5천원권 입금함 참    */
    public static final String CD_ERROR_CASHBOX_1000        = "43";  /* 1천원권 입금함 참    */
    public static final String CD_ERROR_CASHBOX_500         = "44";  /* 5백원권 입금함 참    */
    public static final String CD_ERROR_CASHBOX_100         = "45";  /* 1백원권 입금함 참    */
    public static final String CD_ERROR_CASHBOX_50          = "46";  /* 5십원권 입금함 참    */
    public static final String CD_ERROR_CASHBOX_10          = "47";  /* 1십원권 입금함 참    */
    public static final String CD_ERROR_INBOX               = "48";  /* 봉투 입금함 참     */
    public static final String CD_ERROR_TIC_A               = "75";  /* 상품권출금부A 부족   */
    public static final String CD_ERROR_TIC_B               = "76";  /* 상품권출금부B 부족   */
    public static final String CD_ERROR_TIC_C               = "77";  /* 상품권출금부C 부족   */
    public static final String CD_ERROR_TIC_D               = "78";  /* 상품권출금부D 부족   */

    /*
     * ==> 예보없이 장애만 있는 항목 0:정상, 1: 비정상
     */
    public static final String CD_ERROR_STATE_OUTCASH       = "50";  /* 현금출금부가동상태   */
    public static final String CD_ERROR_STATE_OUTCHECK      = "51";  /* 수표출금부가동     */
    public static final String CD_ERROR_STATE_INCASH        = "52";  /* 현금입금부 가동상태    */
    public static final String CD_ERROR_STATE_INCHECK       = "53";  /* 수표입금부 가동상태    */
    public static final String CD_ERROR_STATE_BOOK          = "54";  /* 통장정리부 가동상태    */
    public static final String CD_ERROR_STATE_DOWN          = "55";  /* 기기다운         */
    public static final String CD_ERROR_STATE_RETURNBOX     = "56";  /* 회수함상태       */
    public static final String CD_ERROR_STATE_PRINTER       = "57";  /* A4프린터상태       */
    public static final String CD_ERROR_STATE_DOOR          = "58";  /* 본체문상태       */
    public static final String CD_ERROR_STATE_AGENT         = "59";  /* 자동기에이전트상태     */
    public static final String CD_ERROR_STATE_HOSTSESSION   = "60";  /* 호스트세션상태     */
    public static final String CD_ERROR_STATE_ATMSESSION    = "61";  /* 자동기세션상태     */

    public static final String CD_ERROR_STATE_JIRO          = "66";  /* 지로부상태       */
    public static final String CD_ERROR_STATE_CARD          = "67";  /* 카드부상태       */

    public static final String CD_ERROR_STATE_COIN_IN       = "32";  /* 동전입금부상태     */
    public static final String CD_ERROR_STATE_SPECS         = "33";  /* 명세표부 상태      */
    public static final String CD_ERROR_STATE_BOX_IN        = "34";  /* 봉투입금부 상태      */
    public static final String CD_ERROR_STATE_COIN_OUT      = "35";  /* 동전출금부 상태      */
    public static final String CD_ERROR_STATE_CAM           = "36";  /* 카메라부 상태      */
    public static final String CD_ERROR_STATE_PPAD          = "37";  /* 핀패드 상태        */
    public static final String CD_ERROR_STATE_FCD           = "38";  /* FCD보드 상태       */
    public static final String CD_ERROR_STATE_INSHEET       = "39";  /* 낱장입금부 상태      */

    public static final String CD_ERROR_HANG_BILL           = "62";  /* 명세표걸림       */
    public static final String CD_ERROR_REM_IN_COIN         = "63";  /* 입금동전잔류       */
    public static final String CD_ERROR_REM_OUT_CASH        = "64";  /* 출금지폐잔류       */
    public static final String CD_ERROR_REM_OUT_COIN        = "65";  /* 출금동전잔류       */

    public static final String CD_ERROR_STATE_TIC_A         = "70";  /* 상품권출금부 상태A   */
    public static final String CD_ERROR_STATE_TIC_B         = "71";  /* 상품권출금부 상태B   */
    public static final String CD_ERROR_STATE_TIC_C         = "72";  /* 상품권출금부 상태C   */
    public static final String CD_ERROR_STATE_TIC_D         = "73";  /* 상품권출금부 상태D   */


    public static final String CD_CALC_MAC_SUPERVISOR       = "40";  /* 정산기 슈퍼바이저장애  */
    public static final String CD_CALC_MAC_WATCH_ERR        = "41";  /* 정산기 금고침투 */

    public static final int   DB_WORK_INSERT                = 100;
    public static final int   DB_WORK_UPDATE                = 200;
    public static final int   DB_WORK_CANCEL                = 300;

    public static final String CD_NEW                       = "1";   /* 기기번호신설   */
    public static final String CD_MOD                       = "2";   /* 관리업체변경   */
    public static final String CD_DEL                       = "3";   /* 신규취소     */

    public enum  EnumOrgErrorState {

        IDX_ST_CASH          ("현금부족",           CD_ERROR_CASH             ),
        IDX_ST_CHECK         ("수표부족",           CD_ERROR_CHECK            ),
        IDX_ST_SPECS         ("전표부족",           CD_ERROR_SPECS            ),
        IDX_ST_CASHBOX       ("현금입금함참",       CD_ERROR_CASHBOX          ),
        IDX_ST_CHECKBOX      ("수표입금함참",       CD_ERROR_CHECKBOX         ),
        IDX_ST_RETURNBOX     ("지폐회수함참",       CD_ERROR_RETURNBOX        ),
        IDX_ST_BOOKBOX       ("통장회수함참",       CD_ERROR_BOOKBOX          ),
        IDX_ST_CARDBOX       ("카드회수함참",       CD_ERROR_CARDBOX          ),
        IDX_ST_SPECSBOX      ("명세표회수함참",     CD_ERROR_SPECSBOX         ),
        IDX_ST_JNRBOX        ("저널회수함참",       CD_ERROR_JNRBOX           ),
        IDX_ST_SEND_RECV     ("송수신장애",         CD_ERROR_SEND_RECV        ),
        IDX_ST_LINE_ERR      ("회선장애",           CD_ERROR_LINE_ERR         ),
        IDX_ST_POWER_OFF     ("전원OFF장애",        CD_ERROR_POWER_OFF        ),
        IDX_ST_JNRLACK       ("저널부족",           CD_ERROR_JNRLACK          ),
        IDX_ST_INPUTSTOP     ("입금부미가동",       CD_ERROR_INPUTSTOP        ),
        IDX_ST_5000          ("오천원권부족",       CD_ERROR_5000             ),
        IDX_ST_1000          ("천원권부족",         CD_ERROR_1000             ),
        IDX_ST_500           ("오백원권부족",       CD_ERROR_500              ),
        IDX_ST_100           ("백원권부족",         CD_ERROR_100              ),
        IDX_ST_50            ("오십권부족",         CD_ERROR_50               ),
        IDX_ST_10            ("십원권부족",         CD_ERROR_10               ),
        IDX_ST_50000         ("오만권부족",         CD_ERROR_50000            ),
        IDX_ST_100000        ("십만원권부족",       CD_ERROR_100000           ),
        IDX_ST_CASHBOX_50000 ("오만원권입금함참",   CD_ERROR_CASHBOX_50000    ),
        IDX_ST_CASHBOX_100000("십만원건입금함참",   CD_ERROR_CASHBOX_100000   ),
        IDX_ST_STATE1        ("현금출금부가동상태", CD_ERROR_STATE_OUTCASH    ),
        IDX_ST_STATE2        ("수표출금부가동상태", CD_ERROR_STATE_OUTCHECK   ),
        IDX_ST_STATE3        ("현금입금부가동상태", CD_ERROR_STATE_INCASH     ),
        IDX_ST_STATE4        ("수표입금부가동상태", CD_ERROR_STATE_INCHECK    ),
        IDX_ST_STATE5        ("통장정리부가동상태", CD_ERROR_STATE_BOOK       ),
        IDX_ST_STATE6        ("기기다운상태",       CD_ERROR_STATE_DOWN       ),
        IDX_ST_STATE7        ("회수함상태",         CD_ERROR_STATE_RETURNBOX  ),
        IDX_ST_STATE8        ("A4프린터상태",       CD_ERROR_STATE_PRINTER    ),
        IDX_ST_STATE9        ("본체문상태",         CD_ERROR_STATE_DOOR       ),
        IDX_ST_STATE10       ("자동기에이전트상태", CD_ERROR_STATE_AGENT      ),
        IDX_ST_STATE11       ("호스트세션상태",     CD_ERROR_STATE_HOSTSESSION),
        IDX_ST_STATE12       ("자동기세션상태",     CD_ERROR_STATE_ATMSESSION ),
        IDX_ST_STATE_JIRO    ("지로부상태",         CD_ERROR_STATE_JIRO       ),
        IDX_ST_STATE_CARD    ("카드부상태",         CD_ERROR_STATE_CARD       );

        private String errorName;
        private String errorCd;

        EnumOrgErrorState(String errorName, String errorCd) {
            this.errorName = errorName;
            this.errorCd = errorCd;
        }

        public String getErrorName() {
            return errorName;
        }

        public String getErrorCd() {
            return errorCd;
        }

    }

    public enum EnumStateSkipYN {
        ORG_020("101111111011100000000000000000000000000", "우리"),
        ORG_023("000000000000000000000000000000000000000", "제일"),
        ORG_081("111110010100000000000101000000000000000", "하나"),
        ORG_003("001000000000010000000000000000000000000", "기업"),
        ORG_004("101100101100011000000101000000000000000", "국민"),
        ORG_034("111101011000000000000000000000000000000", "광주"),
        ORG_027("000000000000000000000000000000000000000", "한미"),
        ORG_005("000000000000000000000000000000000000000", "외환"),
        ORG_039("101100000000000000000000000000000000000", "경남"),
        ORG_011("000000000000000000000000000000000000000", "농협"),
        ORG_031("111110000000000000000000000000000000000", "대구은행"),
        ORG_045("111110000000000000000000000000000000011", "새마을금고"),
        ORG_T20("111111001100010000000101000000000000000", "우리ATMS"),
        ORG_T39("111111001100010000000101000000000000000", "경남ATMS"),
        ORG_088("110000000000000000000100000000000000000", "신한은행"),
        ORG_D32("111111010000000000000000000000000000000", "부산은행"),
        ORG_0SL("111110000000000011101100000000000000000", "삼성생명"),
        ORG_0DJ("111110010000010000000101011111111111100", "동양종금"),
        ORG_0HD("111110010000010000000000011111101000000", "현대증권"),
        ORG_0KI("111001110100000000000000000000000000000", "KIBank"),
        ORG_035("111110010000010000000000011111101000000", "제주"),
        ORG_032("000000000000000000000000000000000000000", "부산은행 ATMS"),
        ORG_0WC("111110010000010000000000000000000000000", "우체국"),
        ORG_0TR("101000000000000111100100011001001000011", "KTIS(KTTR"),
        ORG_DFT("000000000000000000000000000000000000000", "Default");

        private String errStates;
        private String bankName;

        EnumStateSkipYN(String errStates, String bankName) {
            this.errStates = errStates;
            this.bankName = bankName;
        }

        public String getErrStates() {
            return errStates;
        }

        public String getBankName() {
            return bankName;
        }
    }

    public enum  EnumCalcErrorState {
        IDX_ST_LINE              ("회선 장애",                CD_ERROR_LINE_ERR       ),
        IDX_ST_LACK_BILL         ("전표 부족",                CD_ERROR_SPECS          ),
        IDX_ST_LACK_TIE          ("지폐입금부 묶음띠 부족",   CD_ERROR_TIELACK        ),
        IDX_ST_LACK_CASH_100000  ("10만원권 부족",            CD_ERROR_100000         ),
        IDX_ST_LACK_CASH_50000   ("5만원권  부족",            CD_ERROR_50000          ),
        IDX_ST_LACK_CASH_10000   ("1만원권  부족",            CD_ERROR_CASH           ),
        IDX_ST_LACK_CASH_5000    ("5천원권  부족",            CD_ERROR_5000           ),
        IDX_ST_LACK_CASH_1000    ("1천원권  부족",            CD_ERROR_1000           ),
        IDX_ST_LACK_COIN_500     ("500원권  부족",            CD_ERROR_500            ),
        IDX_ST_LACK_COIN_100     ("100원권  부족",            CD_ERROR_100            ),
        IDX_ST_LACK_COIN_50      ("50원권   부족",            CD_ERROR_50             ),
        IDX_ST_LACK_COIN_10      ("10원권   부족",            CD_ERROR_10             ),
        IDX_ST_FULL_INCHECK      ("수표 입금함 참",           CD_ERROR_CHECKBOX       ),
        IDX_ST_FULL_INBOX        ("봉투 입금함 참",           CD_ERROR_INBOX          ),
        IDX_ST_FULL_INCASH_100000("십만원권 입금함 참",       CD_ERROR_CASHBOX_100000 ),
        IDX_ST_FULL_INCASH_50000 ("오만원권 입금함 참",       CD_ERROR_CASHBOX_50000  ),
        IDX_ST_FULL_INCASH_10000 ("1만원 입금함 참",          CD_ERROR_CASHBOX        ),
        IDX_ST_FULL_INCASH_5000  ("5천원권 입금함 참",        CD_ERROR_CASHBOX_5000   ),
        IDX_ST_FULL_INCASH_1000  ("1천원권 입금함 참",        CD_ERROR_CASHBOX_1000   ),
        IDX_ST_FULL_INCOIN_500   ("5백원권 입금함 참",        CD_ERROR_CASHBOX_500    ),
        IDX_ST_FULL_INCOIN_100   ("1백원권 입금함 참",        CD_ERROR_CASHBOX_100    ),
        IDX_ST_FULL_INCOIN_50    ("5십원권 입금함 참",        CD_ERROR_CASHBOX_50     ),
        IDX_ST_FULL_INCOIN_10    ("1십원권 입금함 참",        CD_ERROR_CASHBOX_10     ),
        IDX_ST_HOOK_BILL         ("명세표걸림",               CD_ERROR_HANG_BILL      ),
        IDX_ST_REM_INCOIN        ("입금동전잔류",             CD_ERROR_REM_IN_COIN    ),
        IDX_ST_REM_OUTCASH       ("출금지폐잔류",             CD_ERROR_REM_OUT_CASH   ),
        IDX_ST_REM_OUTCOIN       ("출금동전잔류",             CD_ERROR_REM_OUT_COIN   ),
        IDX_ST_INCHECK           ("수표입금부상태",           CD_ERROR_STATE_INCHECK  ),
        IDX_ST_INCASH            ("지폐입금부상태",           CD_ERROR_STATE_INCASH   ),
        IDX_ST_INCOIN            ("동전입금부상태",           CD_ERROR_STATE_COIN_IN  ),
        IDX_ST_BILL              ("명세표부상태",             CD_ERROR_STATE_SPECS    ),
        IDX_ST_INBOX             ("봉투입금부 상태",          CD_ERROR_STATE_BOX_IN   ),
        IDX_ST_OUTCASH           ("현금출금부가동상태",       CD_ERROR_STATE_OUTCASH  ),
        IDX_ST_OUTCOIN           ("통전출금부 상태",          CD_ERROR_STATE_COIN_OUT ),
        IDX_ST_CAM               ("카메라부 상태",            CD_ERROR_STATE_CAM      ),
        IDX_ST_PPAD              ("핀패드 상태",              CD_ERROR_STATE_PPAD     ),
        IDX_ST_FCD               ("FCD보드 상태",             CD_ERROR_STATE_FCD      ),
        IDX_ST_INSHEET           ("낱장입금부 상태",          CD_ERROR_STATE_INSHEET  ),
        IDX_ST_OUTTICKET_A       ("상품권출금부상태A",        CD_ERROR_STATE_TIC_A    ),
        IDX_ST_OUTTICKET_B       ("상품권출금부상태B",        CD_ERROR_STATE_TIC_B    ),
        IDX_ST_OUTTICKET_C       ("상품권출금부상태C",        CD_ERROR_STATE_TIC_C    ),
        IDX_ST_OUTTICKET_D       ("상품권출금부상태D",        CD_ERROR_STATE_TIC_D    ),
        IDX_ST_LACK_OUTTICKET_A  ("상품권출금부A 부족",       CD_ERROR_TIC_A          ),
        IDX_ST_LACK_OUTTICKET_B  ("상품권출금부B 부족",       CD_ERROR_TIC_B          ),
        IDX_ST_LACK_OUTTICKET_C  ("상품권출금부C 부족",       CD_ERROR_TIC_C          ),
        IDX_ST_LACK_OUTTICKET_D  ("상품권출금부D 부족",       CD_ERROR_TIC_D          );

        private String errorName;
        private String errorCd;

        EnumCalcErrorState(String errorName, String errorCd) {
            this.errorName = errorName;
            this.errorCd = errorCd;
        }

        public String getErrorName() {
            return errorName;
        }

        public String getErrorCd() {
            return errorCd;
        }
    }

    public enum EnumCalcMacStateSkipYN {
        ORG_0CS("1110000000001000100100000001111000000000000000", "신세계 첼시"),
        ORG_OEM("0111111111111111111111111111111111111111101110", "이마트"),
        ORG_0EL("0000000000000000000000000000000000000000000000", "이랜드"),
        ORG_DFT("0000000000000000000000000000000000000000000000", "Default");

        private String errStates;
        private String bankName;

        EnumCalcMacStateSkipYN(String errStates, String bankName) {
            this.errStates = errStates;
            this.bankName = bankName;
        }

        public String getErrStates() {
            return errStates;
        }

        public String getBankName() {
            return bankName;
        }
    }
}
