package com.nicetcm.nibsplus.broker.ams;

public class AMSBrokerConst {

    public static final String SVR_TYPE = System.getProperty("server.type", "dev");

    public static final int   COMMON_LEN              = 121;          // Common헤더 길이

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

    public static final String SVC_CD_INQ_ENV         = "2002";       // 환경정보 조회
    public static final String SVC_CD_INQ_REG         = "2011";       // Registry 정보 조회
    public static final String SVC_CD_INQ_INI         = "2012";       // INI 정보 조회
    public static final String SVC_CD_CHG_ENV         = "3002";       // Env 설정 변경
    public static final String SVC_CD_CHG_REG         = "3011";       // Registry 설정 변경
    public static final String SVC_CD_CHG_INI         = "3012";       // INI 설정 변경
    public static final String SVC_CD_CMD_RBT         = "4001";       // Reboot 명령
    public static final String SVC_CD_CMD_PWO         = "4001";       // Poweroff 명령
    public static final String SVC_CD_CMD_DEV         = "4002";       // 장치리셋,회수,배출명령
    public static final String SVC_CD_NTI_CLL         = "4002";       // 출동요청 안내문 통지
    public static final String SVC_CD_DWL_GEN         = "5012";       // 일반파일 다운로드 요청
    public static final String SVC_CD_DWL_UPD         = "5004";       // 배포파일 다운로드 요청

}
