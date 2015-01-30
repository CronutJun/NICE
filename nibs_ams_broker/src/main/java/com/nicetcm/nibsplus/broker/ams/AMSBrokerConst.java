package com.nicetcm.nibsplus.broker.ams;

public class AMSBrokerConst {

    public static final String SVR_TYPE = System.getProperty("server.type", "dev");
    public static final String MAX_CONCURRENT_THREAD  = "10";         // 스케쥴 동시 처리 쓰레드 기본 갯수

    public static final int   COMMON_LEN              = 121;          // Common헤더 길이
    public static final int   MSG_LEN_INFO_LEN        = 9;            // 전문헤더의 전체전문길이 정보 길이

    public static final String GENERAL_FILE_PATH      = "macs/general/";  // 일반파일 저장 경로
    public static final String SPECIFIC_FILE_PATH     = "macs/specific/"; // 특정파일 저장 경로
    public static final String JOURNAL_FILE_PATH      = "macs/journal/";  // 저널파일 저장 경로

    public static final String NICE_ORG_CD            = "096";
    public static final String NICE_BR_CD             = "9600";

    public static final String BIZ_CL_SM              = "SM";         // 업무구분(시스템관리)
    public static final String BIZ_CL_PM              = "PM";         //         (프로그램관리)
    public static final String BIZ_CL_RM              = "RM";         //         (원격관리(공통))
    public static final String BIZ_CL_RC              = "RC";         //         (원격제어)

    public static final String MSG_CD_REQ             = "2100";       // 전문코드 - 요청
    public static final String MSG_CD_ANS             = "1200";       // 전문코드 - 요청응답

    public static final String MSG_CD_RCV             = "1100";       // 전문코드 - 수신
    public static final String MSG_CD_RSP             = "2200";       // 전문코드 - 수신응답

    public static final String SVC_CD_NTI_POL         = "1000";       // POLL
    public static final String SVC_CD_NTI_OPN         = "1001";       // 개국
    public static final String SVC_CD_NTI_CLS         = "1003";       // 폐국
    public static final String SVC_CD_INQ_ENV         = "2002";       // 환경정보 조회
    public static final String SVC_CD_INQ_REG         = "2011";       // Registry 정보 조회
    public static final String SVC_CD_INQ_INI         = "2012";       // INI 정보 조회
    public static final String SVC_CD_CHG_ENV         = "3002";       // Env 설정 변경
    public static final String SVC_CD_CHG_REG         = "3011";       // Registry 설정 변경
    public static final String SVC_CD_CHG_INI         = "3012";       // INI 설정 변경
    public static final String SVC_CD_CMD_RBT         = "4001";       // Reboot 명령
    public static final String SVC_CD_CMD_PWO         = "4001";       // Poweroff 명령
    public static final String SVC_CD_CMD_DEV         = "4002";       // 장치리셋,회수,배출명령
    public static final String SVC_CD_EXE_GFL         = "4003";       // 일반파일 실행요청
    public static final String SVC_CD_NTI_CLL         = "3004";       // 출동요청 안내문 통지
    public static final String SVC_CD_AUP_SPC         = "5001";       // 특정파일 자동업로드
    public static final String SVC_CD_INQ_DWL         = "5002";       // 배포파일 다운로드 요청
    public static final String SVC_CD_UPL_SPC         = "5003";       // 특정파일 업로드   요청
    public static final String SVC_CD_DWL_UPD         = "5004";       // 배포파일 다운로드 요청
    public static final String SVC_CD_UPL_GEN         = "5011";       // 일반파일 업로드   요청
    public static final String SVC_CD_DWL_GEN         = "5012";       // 일반파일 다운로드 요청


    public static final String TRX_CD_DEV_CTL         = "720101";
    public static final String TRX_CD_DEV_CTL_ERR     = "720102";
    public static final String TRX_CD_INQ_ENV         = "720103";
    public static final String TRX_CD_INQ_ENV_M       = "720104";
    public static final String TRX_CD_NTI_CLL         = "720105";
    public static final String TRX_CD_REG             = "720106";
    public static final String TRX_CD_INI             = "720107";
    public static final String TRX_CD_UPL_DWL         = "720108";
    public static final String TRX_CD_EXE_GFL         = "720109";
    public static final String TRX_CD_JNL_UPL         = "720302";

    public static final String ACT_CD_GEN_UPL         = "100";
    public static final String ACT_CD_GEN_DWL         = "110";
    public static final String ACT_CD_SPC_UPL         = "200";
    public static final String ACT_CD_SPC_DWL         = "210";
    public static final String ACT_CD_EXEC            = "500";
    public static final String ACT_CD_EXEC1           = "501";
    public static final String ACT_CD_EXEC2           = "502";
    public static final String ACT_CD_EXEC3           = "503";
    public static final String ACT_CD_EXEC4           = "504";
    public static final String ACT_CD_EXEC5           = "505";
    public static final String ACT_CD_INQ             = "600";

}
