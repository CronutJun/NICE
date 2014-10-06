package com.nicetcm.nibsplus.broker.ams.rmi;

import java.rmi.Remote;
import java.util.ArrayList;

public interface AMSBrokerRMI extends Remote {

    public void dataUploadToBroker( byte[] data, boolean isFirst, boolean hasNext ) throws Exception;

    /**
     * makeUpdatesSchedule
     *
     * 배포그룹별 배포스케쥴 작성
     *
     * @param grpCd         그룹코드
     * @param verId         버전ID
     * @param deployDate    배포일자
     * @param deployTime    배포시간
     * @throws Exception
     */
    public void makeUpdatesSchedule( String grpCd, String mkrCd, String modelCd, String verId, String deployDate, String deployTime ) throws Exception;

    /**
     * reqEnvInfToMac
     *
     * 단독기기 대상 환경정보 요청
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macNo     기기번호
     * @param timeOut   대기시간
     * @throws Exception
     */
    public void reqEnvInfToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, int timeOut ) throws Exception;

    /**
     * reqEnvInfToMacs
     *
     * 복수기기 대상 환경정보 요청
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macs      기번집합
     * @throws Exception
     */
    public void reqEnvInfToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs ) throws Exception;

    /**
     * reqRegInfToMac
     *
     * 단독기기 대상 레지스트리정보 요청
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param reqRegInfo    요청대상 기기 및 레지스트리 항목 정보
     * @param timeOut   대기시간
     * @throws Exception
     */
    public void reqRegInfToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqRegInfo reqRegInfo, int timeOut ) throws Exception;

    /**
     * reqRegInfToMacs
     *
     * 복수기기 대상 레지스트리정보 요청
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param reqRegInfos   요청대상 기기 및 레지스트리 항목 집합
     * @throws Exception
     */
    public void reqRegInfToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqRegInfo> reqRegInfos ) throws Exception;

    /**
     * reqIniInfToMac
     *
     * 단독기기 대상 INI정보 요청
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param reqIniInfo    요청대상 기기 및 INI항목 정보
     * @param timeOut   대기시간
     * @throws Exception
     */
    public void reqIniInfToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqIniInfo reqIniInfo, int timeOut ) throws Exception;

    /**
     * reqIniInfToMacs
     *
     * 복수기기 대상 INI정보 요청
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param reqIniInfos   요청대상 기기 및 INI항목 집합
     * @throws Exception
     */
    public void reqIniInfToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqIniInfo> reqIniInfos ) throws Exception;

    /**
     * reqEnvChgToMac
     *
     * 단독기기 대상 환경설정정보 변경 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macNo     기기번호
     * @param envValues 변경대상 항목 및 값 집합
     * @throws Exception
     */
    public void reqEnvChgToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, ArrayList<RMIEnvValue> envValues ) throws Exception;

    /**
     * reqEnvChgToMacs
     *
     * 복수기기 대상 환경설정정보 변경 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macs      기기번호 집합
     * @param envValue  변경대상 항목 및 값
     * @throws Exception
     */
    public void reqEnvChgToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, RMIEnvValue envValue ) throws Exception;

    /**
     * reqRegChgToMac
     *
     * 단독기기 대상 레지스트리정보 변경 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param reqRegInfo    대상 기기번호 및 변경대상 레지스트리 항목, 값
     * @throws Exception
     */
    public void reqRegChgToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqRegInfo reqRegInfo ) throws Exception;

    /**
     * reqRegChgToMacs
     *
     * 복수기기 대상 레지스트리정보 변경 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param reqRegInfos   대상 기기번호 및 변경대상 레지스트리 항목, 값 집합
     * @throws Exception
     */
    public void reqRegChgToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqRegInfo> reqRegInfos ) throws Exception;

    /**
     * reqIniChgToMac
     *
     * 단독기기 대상 INI정보 변경 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param reqIniInfo    대상 기기번호 및 변경대상 INI항목, 값
     * @throws Exception
     */
    public void reqIniChgToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqIniInfo reqIniInfo ) throws Exception;

    /**
     * reqIniChgToMacs
     *
     * 복수기기 대상 INI정보 변경 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param reqIniInfos   대상 기기번호 및 변경대상 INI항목, 값
     * @throws Exception
     */
    public void reqIniChgToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqIniInfo> reqIniInfos ) throws Exception;

    /**
     * reqRebootToMac
     *
     * 단독기기 대상 Reboot 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macNo     대상기기번호
     * @throws Exception
     */
    public void reqRebootToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo ) throws Exception;

    /**
     * reqRebootToMacs
     *
     * 복수기기 대상 Reboot 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macs      대상기번집합
     * @throws Exception
     */
    public void reqRebootToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs ) throws Exception;

    /**
     * reqPwroffToMac
     *
     * 단독기기 대상 Poweroff 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macNo     대상기기번호
     * @throws Exception
     */
    public void reqPwroffToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo ) throws Exception;

    /**
     * reqPwroffToMacs
     *
     * 복수기기 대상 Poweroff 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macs      대상기번집합
     * @throws Exception
     */
    public void reqPwroffToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs ) throws Exception;

    /**
     * reqDevResetToMac
     *
     * 단독기기 대상 장치 리셋 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macNo     대상기기번호
     * @param devCd     장치코드
     * @throws Exception
     */
    public void reqDevResetToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String devCd ) throws Exception;

    /**
     * reqDevResetToMacs
     *
     * 복수기기 대상 장치 리셋 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macs      대상기번집합
     * @param devCd     장치코드
     * @throws Exception
     */
    public void reqDevResetToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, String devCd ) throws Exception;

    /**
     * reqDevCollectToMac
     *
     * 단독기기 대상 장치 회수 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macNo     대상기기번호
     * @param devCd     장치코드
     * @throws Exception
     */
    public void reqDevCollectToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String devCd ) throws Exception;

    /**
     * reqDevCollectToMacs
     *
     * 복수기기 대상 장치 회수 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macs      대상기번집합
     * @param devCd     장치코드
     * @throws Exception
     */
    public void reqDevCollectToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, String devCd ) throws Exception;

    /**
     * reqDevReturnToMac
     *
     * 단독기기 대상 장치 반환 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macNo     대상기기번호
     * @param devCd     장치코드
     * @throws Exception
     */
    public void reqDevReturnToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String devCd ) throws Exception;

    /**
     * reqDevReturnToMacs
     *
     * 복수기기 대상 장치 반환 예약
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macs      대상기번집합
     * @param devCd     장치코드
     * @throws Exception
     */
    public void reqDevReturnToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, String devCd ) throws Exception;

    /**
     * reqCallNoticeToMac
     *
     * 출동요청 안내문 전송
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macNo     대상기기번호
     * @param empId     출동예정사원ID
     * @param empPhone  출동예정사원 전화번호
     * @param arrivalTime 출동예정 시간
     * @param timeOut   대기시간
     * @throws Exception
     */
    public void reqCallNoticeToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String empId, String empPhone, String arrivalTime, int timeout ) throws Exception;

    /**
     * reqSFileUpToMac
     *
     * 단독기기 대상 특정파일 업로드 요청
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macNo     대상기기
     * @param fileDate  대상파일기준일
     * @param fileType  파일타입
     * @throws Exception
     */
    public void reqSFileUpToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String fileDate, String fileType ) throws Exception;

    /**
     * reqSFileUpToMacs
     *
     * 복수기기 대상 특정파일 업로드 요청
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macs      대상기기집합
     * @param fileDate  대상파일기준일
     * @param fileType  파일타입
     * @throws Exception
     */
    public void reqSFileUpToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, String fileDate, String fileType ) throws Exception;

    /**
     * reqSFileDownToMac
     *
     * 단독기기 대상 특정파일 다운로드 요청
     *
     * @param trxDate       거래일
     * @param trxNo         거래번호
     * @param trxCd         거래코드
     * @param actCd         실행코드
     * @param trxUid        거래처리자코드
     * @param macNo         대상기기
     * @param createDate    파일생성일 (기기로 다운로드할 파일키)
     * @param fileSeq       파일순번   (기기로 다운로드할 파일키)
     * @param fileType      파일타입   (생략 가능)
     * @param fileName      저장파일명 (생략 가능)
     * @throws Exception
     */
    public void reqSFileDownToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String createDate, String fileSeq, String fileType, String fileName ) throws Exception;

    /**
     * reqSFileDownToMacs
     *
     * 복수기기 대상 특정파일 다운로드 요청
     *
     * @param trxDate       거래일
     * @param trxNo         거래번호
     * @param trxCd         거래코드
     * @param actCd         실행코드
     * @param trxUid        거래처리자코드
     * @param macs          대상기기집합
     * @param createDate    파일생성일 (기기로 다운로드할 파일키)
     * @param fileSeq       파일순번   (기기로 다운로드할 파일키)
     * @param fileType      파일타입   (생략 가능)
     * @param fileName      저장파일명 (생략 가능)
     * @throws Exception
     */
    public void reqSFileDownToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, String createDate, String fileSeq, String fileType, String fileName ) throws Exception;

    /**
     * reqGFileUpToMac
     *
     * 단독기기 대상 일반파일 업로드 요청
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macNo     대상기기
     * @param filePath  대상파일경로
     * @param fileName  대상파일명
     * @throws Exception
     */
    public void reqGFileUpToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String filePath, String fileName ) throws Exception;

    /**
     * reqGFileUpToMacs
     *
     * 복수기기 대상 일반파일 업로드 요청
     *
     * @param trxDate   거래일
     * @param trxNo     거래번호
     * @param trxCd     거래코드
     * @param actCd     실행코드
     * @param trxUid    거래처리자코드
     * @param macs      대상기기집합
     * @param filePath  대상파일경로
     * @param fileName  대상파일명
     * @throws Exception
     */
    public void reqGFileUpToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, String filePath, String fileName ) throws Exception;

    /**
     * reqGFileDownToMac
     *
     * 단독기기 대상 일반파일 다운로드 요청
     *
     * @param trxDate       거래일
     * @param trxNo         거래번호
     * @param trxCd         거래코드
     * @param actCd         실행코드
     * @param trxUid        거래처리자코드
     * @param macNo         대상기기
     * @param createDate    파일생성일 (기기로 다운로드할 파일키)
     * @param fileSeq       파일순번   (기기로 다운로드할 파일키)
     * @param filePath      저장파일경로
     * @param fileName      저장파일명
     * @throws Exception
     */
    public void reqGFileDownToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String createDate, String fileSeq, String filePath, String fileName ) throws Exception;

    /**
     * reqGFileDownToMacs
     *
     * 복수기기 대상 일반파일 다운로드 요청
     *
     * @param trxDate       거래일
     * @param trxNo         거래번호
     * @param trxCd         거래코드
     * @param actCd         실행코드
     * @param trxUid        거래처리자코드
     * @param macs          대상기기집합
     * @param createDate    파일생성일 (기기로 다운로드할 파일키)
     * @param fileSeq       파일순번   (기기로 다운로드할 파일키)
     * @param filePath      저장파일경로
     * @param fileName      저장파일명
     * @throws Exception
     */
    public void reqGFileDownToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, String createDate, String fileSeq, String filePath, String fileName ) throws Exception;

}
