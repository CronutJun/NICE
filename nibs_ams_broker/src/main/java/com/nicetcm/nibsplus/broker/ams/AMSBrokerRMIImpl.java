package com.nicetcm.nibsplus.broker.ams;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * AMSBrokerRMIImpl
 *
 *  AMS Broker와 NIBS Web Client간 RMI인터페이스 구현
 *
 *
 * @author  K.D.J
 * @since   2014.08.18
 */

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.CronScheduleBuilder.cronSchedule;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.io.*;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.ams.rmi.*;
import com.nicetcm.nibsplus.broker.common.MsgCommon;

public class AMSBrokerRMIImpl implements AMSBrokerRMI {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerRMIImpl.class);
    private FileOutputStream fOut;

    public AMSBrokerRMIImpl() {
        /*super();*/
    }

    public void dataUploadToBroker( byte[] data, boolean isFirst, boolean hasNext ) {
        try {
            if( isFirst && hasNext) {
                fOut = new FileOutputStream("C:/sample.pptx");
            }
            if( data.length > 0 ) {
                fOut.write(data);
            }
            if( !isFirst && !hasNext ) {
                fOut.flush();
                fOut.close();
                fOut = null;
            }
        }
        catch (Exception err) {
            logger.warn(err.getMessage());
        }
    }

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
    public void makeUpdatesSchedule( String grpCd, String mkrCd, String modelCd, String verId, String deployDate, String deployTime ) throws Exception {

        AMSBrokerMain.getScheduler().unscheduleJob( TriggerKey.triggerKey( verId, grpCd) );

        JobDetail updJob = newJob(AMSBrokerSchedJob.class)
                         .withIdentity("UPDATES",  String.format("%-4.4s%-4.4s", mkrCd, modelCd))
                         .build();

        CronTrigger updTrig = newTrigger()
                            .withIdentity( verId, grpCd )
                            .withSchedule( cronSchedule(String.format("%s %s %s %s %s ? %s",
                                                                      deployTime.substring(4),
                                                                      deployTime.substring(2, 4),
                                                                      deployTime.substring(0, 2),
                                                                      deployDate.substring(6),
                                                                      deployDate.substring(4, 6),
                                                                      deployDate.substring(0, 4))) )
                            .build();
        AMSBrokerMain.getScheduler().scheduleJob( updJob, updTrig );
        logger.info("Job is successfully scheduled [Group = {}, Version = {}, deployDate = {}, deployTime = {}",
                    grpCd, verId, deployDate, deployTime);
    }

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
    public void reqEnvInfToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, int timeOut ) throws Exception {

        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, true);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo  ( trxNo );
            reqJob.setTrxCd  ( trxCd );
            reqJob.setActCd  ( actCd );
            reqJob.setTrxUid ( trxUid );
            reqJob.setTimeOut( timeOut );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqEnvInfToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs ) throws Exception {
        try {
            for( String macNo: macs ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo  ( trxNo );
                reqJob.setTrxCd  ( trxCd );
                reqJob.setActCd  ( actCd );
                reqJob.setTrxUid ( trxUid );
                reqJob.setTimeOut( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqRegInfToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqRegInfo reqRegInfo, int timeOut ) throws Exception {
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqRegInfo.getMacNo(), true);
            reqJob.setTrxDate   ( trxDate );
            reqJob.setTrxNo     ( trxNo );
            reqJob.setTrxCd     ( trxCd );
            reqJob.setActCd     ( actCd );
            reqJob.setTrxUid    ( trxUid );
            reqJob.setReqRegInfo( reqRegInfo );
            reqJob.setTimeOut   ( timeOut );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqRegInfToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqRegInfo> reqRegInfos ) throws Exception {
        try {
            for( RMIReqRegInfo reqRegInfo: reqRegInfos ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqRegInfo.getMacNo(), false);
                reqJob.setTrxDate   ( trxDate );
                reqJob.setTrxNo     ( trxNo );
                reqJob.setTrxCd     ( trxCd );
                reqJob.setActCd     ( actCd );
                reqJob.setTrxUid    ( trxUid );
                reqJob.setReqRegInfo( reqRegInfo );
                reqJob.setTimeOut   ( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqIniInfToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqIniInfo reqIniInfo, int timeOut ) throws Exception {
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqIniInfo.getMacNo(), true);
            reqJob.setTrxDate   ( trxDate );
            reqJob.setTrxNo     ( trxNo );
            reqJob.setTrxCd     ( trxCd );
            reqJob.setActCd     ( actCd );
            reqJob.setTrxUid    ( trxUid );
            reqJob.setReqIniInfo( reqIniInfo );
            reqJob.setTimeOut   ( timeOut );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqIniInfToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqIniInfo> reqIniInfos ) throws Exception {
        try {
            for( RMIReqIniInfo reqIniInfo: reqIniInfos ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqIniInfo.getMacNo(), false);
                reqJob.setTrxDate   ( trxDate );
                reqJob.setTrxNo     ( trxNo );
                reqJob.setTrxCd     ( trxCd );
                reqJob.setActCd     ( actCd );
                reqJob.setTrxUid    ( trxUid );
                reqJob.setReqIniInfo( reqIniInfo );
                reqJob.setTimeOut   ( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqEnvChgToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, ArrayList<RMIEnvValue> envValues ) throws Exception {

        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
            reqJob.setTrxDate  ( trxDate );
            reqJob.setTrxNo    ( trxNo );
            reqJob.setTrxCd    ( trxCd );
            reqJob.setActCd    ( actCd );
            reqJob.setTrxUid   ( trxUid );
            reqJob.setTimeOut  ( 0 );
            reqJob.setEnvValues( envValues );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }

    }

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
    public void reqEnvChgToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, RMIEnvValue envValue ) throws Exception {

        try {
            for( String macNo: macs ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo   ( trxNo );
                reqJob.setTrxCd   ( trxCd );
                reqJob.setActCd   ( actCd );
                reqJob.setTrxUid  ( trxUid );
                reqJob.setTimeOut ( 0 );
                reqJob.setEnvValue( envValue );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }

    }

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
    public void reqRegChgToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqRegInfo reqRegInfo ) throws Exception {
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqRegInfo.getMacNo(), false);
            reqJob.setTrxDate   ( trxDate );
            reqJob.setTrxNo     ( trxNo );
            reqJob.setTrxCd     ( trxCd );
            reqJob.setActCd     ( actCd );
            reqJob.setTrxUid    ( trxUid );
            reqJob.setReqRegInfo( reqRegInfo );
            reqJob.setTimeOut   ( 0 );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqRegChgToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqRegInfo> reqRegInfos ) throws Exception {
        try {
            for( RMIReqRegInfo reqRegInfo: reqRegInfos ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqRegInfo.getMacNo(), false);
                reqJob.setTrxDate   ( trxDate );
                reqJob.setTrxNo     ( trxNo );
                reqJob.setTrxCd     ( trxCd );
                reqJob.setActCd     ( actCd );
                reqJob.setTrxUid    ( trxUid );
                reqJob.setReqRegInfo( reqRegInfo );
                reqJob.setTimeOut   ( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqIniChgToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqIniInfo reqIniInfo ) throws Exception {
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqIniInfo.getMacNo(), false);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo     ( trxNo );
            reqJob.setTrxCd     ( trxCd );
            reqJob.setActCd     ( actCd );
            reqJob.setTrxUid    ( trxUid );
            reqJob.setReqIniInfo( reqIniInfo );
            reqJob.setTimeOut   ( 0 );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqIniChgToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqIniInfo> reqIniInfos ) throws Exception {
        try {
            for( RMIReqIniInfo reqIniInfo: reqIniInfos ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(reqIniInfo.getMacNo(), false);
                reqJob.setTrxDate   ( trxDate );
                reqJob.setTrxNo     ( trxNo );
                reqJob.setTrxCd     ( trxCd );
                reqJob.setActCd     ( actCd );
                reqJob.setTrxUid    ( trxUid );
                reqJob.setReqIniInfo( reqIniInfo );
                reqJob.setTimeOut   ( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqRebootToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo ) throws Exception {
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo  ( trxNo );
            reqJob.setTrxCd  ( trxCd );
            reqJob.setActCd  ( actCd );
            reqJob.setTrxUid ( trxUid );
            reqJob.setTimeOut( 0 );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqRebootToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs ) throws Exception {
        try {
            for( String macNo: macs ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo  ( trxNo );
                reqJob.setTrxCd  ( trxCd );
                reqJob.setActCd  ( actCd );
                reqJob.setTrxUid ( trxUid );
                reqJob.setTimeOut( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqPwroffToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo ) throws Exception {
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo  ( trxNo );
            reqJob.setTrxCd  ( trxCd );
            reqJob.setActCd  ( actCd );
            reqJob.setTrxUid ( trxUid );
            reqJob.setTimeOut( 0 );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqPwroffToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs ) throws Exception {
        try {
            for( String macNo: macs ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo  ( trxNo );
                reqJob.setTrxCd  ( trxCd );
                reqJob.setActCd  ( actCd );
                reqJob.setTrxUid ( trxUid );
                reqJob.setTimeOut( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqDevResetToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String devCd ) throws Exception {
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo  ( trxNo );
            reqJob.setTrxCd  ( trxCd );
            reqJob.setActCd  ( actCd );
            reqJob.setTrxUid ( trxUid );
            reqJob.setDevCd  ( devCd );
            reqJob.setTimeOut( 0 );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqDevResetToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, String devCd ) throws Exception {
        try {
            for( String macNo: macs ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo  ( trxNo );
                reqJob.setTrxCd  ( trxCd );
                reqJob.setActCd  ( actCd );
                reqJob.setTrxUid ( trxUid );
                reqJob.setDevCd  ( devCd );
                reqJob.setTimeOut( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqDevCollectToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String devCd ) throws Exception {
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo  ( trxNo );
            reqJob.setTrxCd  ( trxCd );
            reqJob.setActCd  ( actCd );
            reqJob.setTrxUid ( trxUid );
            reqJob.setDevCd  ( devCd );
            reqJob.setTimeOut( 0 );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqDevCollectToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, String devCd ) throws Exception {
        try {
            for( String macNo: macs ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo  ( trxNo );
                reqJob.setTrxCd  ( trxCd );
                reqJob.setActCd  ( actCd );
                reqJob.setTrxUid ( trxUid );
                reqJob.setDevCd  ( devCd );
                reqJob.setTimeOut( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqDevReturnToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String devCd ) throws Exception {
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo  ( trxNo );
            reqJob.setTrxCd  ( trxCd );
            reqJob.setActCd  ( actCd );
            reqJob.setTrxUid ( trxUid );
            reqJob.setDevCd  ( devCd );
            reqJob.setTimeOut( 0 );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqDevReturnToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, String devCd ) throws Exception {
        try {
            for( String macNo: macs ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo  ( trxNo );
                reqJob.setTrxCd  ( trxCd );
                reqJob.setActCd  ( actCd );
                reqJob.setTrxUid ( trxUid );
                reqJob.setDevCd  ( devCd );
                reqJob.setTimeOut( 0 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
     * @throws Exception
     */
    public void reqCallNoticeToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String empId, String empPhone, String arrivalTime, int timeout ) throws Exception {
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, true);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo      ( trxNo );
            reqJob.setTrxCd      ( trxCd );
            reqJob.setActCd      ( actCd );
            reqJob.setTrxUid     ( trxUid );
            reqJob.setEmpId      ( empId );
            reqJob.setEmpPhone   ( empPhone );
            reqJob.setArrivalTime( arrivalTime );
            reqJob.setTimeOut    ( 10 );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqSFileUpToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String fileDate, String fileType ) throws Exception {
        logger.warn("start reqSFileUpToMac");
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
            reqJob.setTrxDate ( trxDate );
            reqJob.setTrxNo   ( trxNo );
            reqJob.setTrxCd   ( trxCd );
            reqJob.setActCd   ( actCd );
            reqJob.setTrxUid  ( trxUid );
            reqJob.setFileDate( fileDate );
            reqJob.setFileType( fileType );
            reqJob.setTimeOut ( 120 );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqSFileUpToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, String fileDate, String fileType ) throws Exception {
        logger.warn("start reqSFileUpToMacs");
        try {
            for( String macNo: macs ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo   ( trxNo );
                reqJob.setTrxCd   ( trxCd );
                reqJob.setActCd   ( actCd );
                reqJob.setTrxUid  ( trxUid );
                reqJob.setFileDate( fileDate );
                reqJob.setFileType( fileType );
                reqJob.setTimeOut ( 60 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqSFileDownToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String createDate, String fileSeq, String fileType, String fileName ) throws Exception {
        logger.warn("start reqSFileDownToMac");
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
            reqJob.setTrxDate       ( trxDate );
            reqJob.setTrxNo         ( trxNo );
            reqJob.setTrxCd         ( trxCd );
            reqJob.setActCd         ( actCd );
            reqJob.setTrxUid        ( trxUid );
            reqJob.setFileCreateDate( createDate );
            reqJob.setFileSeq       ( fileSeq );
            reqJob.setFileType      ( fileType );
            reqJob.setFileName      ( fileName );
            reqJob.setTimeOut       ( 60 );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqSFileDownToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, String createDate, String fileSeq, String fileType, String fileName ) throws Exception {
        logger.warn("start reqSFileDownToMacs");
        try {
            for( String macNo: macs ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
                reqJob.setTrxDate       ( trxDate );
                reqJob.setTrxNo         ( trxNo );
                reqJob.setTrxCd         ( trxCd );
                reqJob.setActCd         ( actCd );
                reqJob.setTrxUid        ( trxUid );
                reqJob.setFileCreateDate( createDate );
                reqJob.setFileSeq       ( fileSeq );
                reqJob.setFileType      ( fileType );
                reqJob.setFileName      ( fileName );
                reqJob.setTimeOut       ( 60 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqGFileUpToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String filePath, String fileName ) throws Exception {
        logger.warn("start reqGFileUpToMac");
        try {
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
            reqJob.setTrxDate( trxDate );
            reqJob.setTrxNo   ( trxNo );
            reqJob.setTrxCd   ( trxCd );
            reqJob.setActCd   ( actCd );
            reqJob.setTrxUid  ( trxUid );
            reqJob.setFilePath( filePath );
            reqJob.setFileName( fileName );
            reqJob.setTimeOut ( 60 );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqGFileUpToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, String filePath, String fileName ) throws Exception {
        logger.warn("start reqGFileUpToMacs");
        try {
            for( String macNo: macs ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
                reqJob.setTrxDate( trxDate );
                reqJob.setTrxNo   ( trxNo );
                reqJob.setTrxCd   ( trxCd );
                reqJob.setActCd   ( actCd );
                reqJob.setTrxUid  ( trxUid );
                reqJob.setFilePath( filePath );
                reqJob.setFileName( fileName );
                reqJob.setTimeOut ( 30 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqGFileDownToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, String createDate, String fileSeq, String filePath, String fileName ) throws Exception {
        logger.warn("start reqGFileDownToMac");
        try {
            if( createDate == null || createDate.length() == 0 ) {
                throw new Exception("createDate is required.");
            }
            if( fileSeq == null || fileSeq.length() == 0 ) {
                throw new Exception("fileSeq is required.");
            }
            AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
            reqJob.setTrxDate       ( trxDate );
            reqJob.setTrxNo         ( trxNo );
            reqJob.setTrxCd         ( trxCd );
            reqJob.setActCd         ( actCd );
            reqJob.setTrxUid        ( trxUid );
            reqJob.setFileCreateDate( createDate );
            reqJob.setFileSeq       ( fileSeq );
            reqJob.setFilePath      ( filePath );
            reqJob.setFileName      ( fileName );
            reqJob.setTimeOut       ( 30 );
            reqJob.requestJob();
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

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
    public void reqGFileDownToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, String createDate, String fileSeq, String filePath, String fileName ) throws Exception {
        logger.warn("start reqGFileDownToMacs");
        try {
            for( String macNo: macs ) {
                AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macNo, false);
                reqJob.setTrxDate       ( trxDate );
                reqJob.setTrxNo         ( trxNo );
                reqJob.setTrxCd         ( trxCd );
                reqJob.setActCd         ( actCd );
                reqJob.setTrxUid        ( trxUid );
                reqJob.setFileCreateDate( createDate );
                reqJob.setFileSeq       ( fileSeq );
                reqJob.setFilePath      ( filePath );
                reqJob.setFileName      ( fileName );
                reqJob.setTimeOut       ( 60 );
                reqJob.requestJob();
            }
        }
        catch( Exception e ) {
            logger.warn(e.getMessage());
            throw e;
        }
    }

}
