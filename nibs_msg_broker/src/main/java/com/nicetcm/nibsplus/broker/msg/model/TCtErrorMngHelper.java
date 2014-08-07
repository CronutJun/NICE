package com.nicetcm.nibsplus.broker.msg.model;

public class TCtErrorMngHelper extends TCtErrorMngHelperKey
{
/*
    private TCtErrorBasic ErrBasic ;
    private TCtErrorRcpt ErrRcpt   ;
    private TCtErrorNoti ErrNoti   ;
    private TCtErrorCall ErrCall   ;
    private TCtErrorTxn ErrTxn     ;

    T_CT_ERROR_BASIC
    T_CT_ERROR_RCPT
    T_CT_ERROR_NOTI
    T_CT_ERROR_CALL
    T_CT_ERROR_TXN
*/
/*
    @ErrorBasic private String orgCd                ;   //기관
    @ErrorBasic private String branchCd             ;   //지점
    @ErrorBasic private String macNo                ;   //기번
    @ErrorBasic private String siteCd               ;   //사이트
    @ErrorBasic private String errorCd              ;   //장애코드
    @ErrorBasic private String madeErrCd            ;   //제조사 장애 코드
    @ErrorBasic private String msg                  ;   //특이사항
    @ErrorBasic private String errorStatus          ;   //처리상황
    @ErrorBasic private String sendYn               ;   //기통보여부
    @ErrorBasic private String orgMsgNo             ;   //1차출동요청번호(기관에서수신)
    @ErrorBasic private String currentDate          ;   //작업중(화면오픈일자)
    @ErrorBasic private String currentUid           ;   //작업중(화면오픈자사번)
    @ErrorBasic private String formatType           ;   //장애형태
    @ErrorBasic private String transDate            ;   //1차출동요청일자(기관에서수신)
    @ErrorBasic private String midErrorCd           ;   //중분류코드
    @ErrorBasic private String groupErrorCd         ;   //집계분류코드
    @ErrorBasic private String atmState             ;   //ATM상태
    @ErrorBasic private String asMedium             ;   //AS매체코드
    @ErrorBasic private String orgMsg               ;   //기관메모
    @ErrorBasic private String orgSendYn            ;   //기관전송여부
    @ErrorBasic private String deptCd               ;   //부서 코드
    @ErrorBasic private String officeCd             ;   //지사 코드
    @ErrorBasic private String teamCd               ;   //팀 코드
    @ErrorBasic private String sec                  ;   //출동요청차수+출동요청사유(통신에서 임의로 사용중)
    @ErrorBasic private String orgCustTel           ;   //고객대기자연락처
    @ErrorBasic private String orgCustNm            ;   //고객대기자명
    @ErrorBasic private String orgCustMsg           ;   //고객대기특이사항
    @ErrorBasic private String orgCustRecvYn        ;   //고객대기여부
    @ErrorBasic private String sendStartTime        ;   //통보시작시간
    @ErrorBasic private String sendFinishTime       ;   //통보완료시간
    @ErrorBasic private String unlockDate           ;   //경비해제일자
    @ErrorBasic private String unlockTime           ;   //경비해제시간
    @ErrorBasic private String orgErrSendYn         ;   //기관 장애 전송 여부
    @ErrorBasic private String conflictYn           ;   //고객 SMS전송유무
    @ErrorBasic private String workStatus           ;   //업무상태
    @ErrorBasic private String sendCount            ;   //통보회차
    @ErrorBasic private String remark               ;   //비고
    @ErrorBasic private String orgSiteCd            ;   //기관사이트코드
    @ErrorBasic private String lockDate             ;   //경비설정일자
    @ErrorBasic private String lockTime             ;   //경비설정시간
    @ErrorBasic private String realErrorCd          ;   //실 장애 코드
    @ErrorBasic private String orgCallCnt           ;   //출동요청차수
    @ErrorBasic private String crtNo                ;
    @ErrorBasic private String closeYn              ;
    @ErrorBasic private String ivrYn                ;

    @ErrorRcpt private String acceptDate            ;   //접수일자
    @ErrorRcpt private String acceptTime            ;   //접수시간
    @ErrorRcpt private String acceptNm              ;   //접수직원
    @ErrorRcpt private String acceptUid             ;   //접수자

    @ErrorNoti private String sendType              ;   //통보 형태 코드
    @ErrorNoti private String sendDate              ;   //통보 일자
    @ErrorNoti private String sendTime              ;   //통보 일시
    @ErrorNoti private String sendNm                ;   //통보자 명
    @ErrorNoti private String sendUid               ;   //통보자 ID
    @ErrorNoti private String sendCheckYn           ;   //장애 중복 여부
    @ErrorNoti private String sendCheckDatetime     ;   //
    @ErrorNoti private String sendPlanDatetime      ;   //통보 예정 일시
    @ErrorNoti private String sendPlanNm            ;   //통보 예정자 명
    @ErrorNoti private String sendPlanUid           ;   //통보 예정자 ID
    @ErrorNoti private String sendStatus            ;   //통보 상태 코드
    @ErrorNoti private String sendTool              ;   //통보 방법 코드
    @ErrorNoti private String sendSmsStatus         ;   //수신 상태 코드
    @ErrorNoti private String orgUserType           ;   //기관원 구분 코드
    @ErrorNoti private String orgUserNm             ;   //기관원 명
    @ErrorNoti private String recvPlace             ;   //수신처
    @ErrorNoti private String recvUserNm            ;   //수신자 명
    @ErrorNoti private String recvUserUid           ;   //수신자 ID
    @ErrorNoti private String recvTeleNo            ;   //수신자 연락처
    @ErrorNoti private String recvDate              ;   //수신 일자
    @ErrorNoti private String recvTime              ;   //수신 시간
    @ErrorNoti private String recvPdaDate           ;   //PDA 수신 일자
    @ErrorNoti private String recvPdaTime           ;   //PDA 수신 시간
    @ErrorNoti private String recvXpos              ;   //PDA 수신 X 좌표
    @ErrorNoti private String recvYpos              ;   //PDA 수신 Y 좌표
    @ErrorNoti private String recvSido              ;   //PDA 수신 시도 명
    @ErrorNoti private String recvGugun             ;   //PDA 수신 구군 명
    @ErrorNoti private String recvDong              ;   //PDA 수신 동 명
    @ErrorNoti private String arrivalEstDate        ;   //도착 예정 일자
    @ErrorNoti private String arrivalEstTime        ;   //도착 예정 시간

    @ErrorCall private String arrivalDate           ;   //도착일자
    @ErrorCall private String arrivalTime           ;   //도착시간
    @ErrorCall private String arrivalPdaDate        ;   //PDA 도착 일자
    @ErrorCall private String arrivalPdaTime        ;   //PDA 도착 시간
    @ErrorCall private String arrivalXpos           ;   //PDA 도착 X 좌표
    @ErrorCall private String arrivalYpos           ;   //PDA 도착 Y 좌표
    @ErrorCall private String arrivalSido           ;   //PDA 도착 시/도
    @ErrorCall private String arrivalGugun          ;   //PDA 도착 구/군
    @ErrorCall private String arrivalDong           ;   //PDA 도착 동
    @ErrorCall private String arrivalType           ;   //출동처
    @ErrorCall private String arrivalUid            ;   //출동자사번
    @ErrorCall private String arrivalNm             ;   //출동자명

    @ErrorTxn private String repairDate             ;   //복구일자
    @ErrorTxn private String repairTime             ;   //복구시각
    @ErrorTxn private String finishDate             ;   //조치일자
    @ErrorTxn private String finishTime             ;   //조치시간
    @ErrorTxn private String finishPdaDate          ;   //PDA 조치 일자
    @ErrorTxn private String finishPdaTime          ;   //PDA 조치 시간
    @ErrorTxn private String finishXpos             ;   //PDA 조치 X 좌표
    @ErrorTxn private String finishYpos             ;   //PDA 조치 Y 좌표
    @ErrorTxn private String finishSido             ;   //PDA 조치 시/도
    @ErrorTxn private String finishGugun            ;   //PDA 조치 구/군
    @ErrorTxn private String finishDong             ;   //PDA 조치 동
    @ErrorTxn private String finishType             ;   //처리처
    @ErrorTxn private String finishNm               ;   //처리자명
    @ErrorTxn private String finishUid              ;   //처리자사번
*/
}
