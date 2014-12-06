package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker HOST 전일자 데이터 건수 통보
 *
 * <pre>
 * MngNC_NiceTranCnt
 * </pre>
 *
 *           2014. 07. 31    K.D.J.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerFilenameFilter;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnHostTranCntMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnNiceTranMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnHostTranCnt;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranSpec;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

@Service("inN3000900")
public class InN3000900Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN3000900Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnHostTranCntMapper fnHostTranCntMap;
    @Autowired private TFnNiceTranMapper fnNiceTranMap;;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TFnHostTranCnt fnHostTranCnt = new TFnHostTranCnt();

        fnHostTranCnt.setDealDate   ( parsed.getString("deal_date"  ) );
        fnHostTranCnt.setNiceDealCnt( parsed.getInt   ("deal_no_cnt") );

        try {
            fnHostTranCntMap.insert( fnHostTranCnt );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                fnHostTranCntMap.updateByPrimaryKey( fnHostTranCnt );
            }
            catch( Exception e ) {
                logger.warn( "[T_FN_HOST_TRAN_CNT] UPDATE Err [{}-{}][{}]",
                        parsed.getString("deal_date"), parsed.getString("deal_no_cnt"), e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.warn( "[T_FN_HOST_TRAN_CNT] INSERT Err [{}-{}][{}]",
                    parsed.getString("deal_date"), parsed.getString("deal_no_cnt"), e.getLocalizedMessage() );
            throw e;
        }

        TFnNiceTranSpec fnNiceTranSpec = new TFnNiceTranSpec();
        fnNiceTranSpec.createCriteria().andDealDateEqualTo( parsed.getString("deal_date") );
        int iCnt = fnNiceTranMap.countBySpec( fnNiceTranSpec );
        if( iCnt != parsed.getInt("deal_no_cnt") ) {
            /*
             *  거래건수 불일치 문자 전송
             */
            TMisc cond = new TMisc();
            cond.setSendMsg( String.format("[나이스거래누락] [%s] : HOST-%d건 NIBS-%d건",
                                  parsed.getString("deal_date"), parsed.getInt("deal_no_cnt"), iCnt) );
            cond.setSendMode( 0 );
            try {
                splMap.spIfSendSMSTranCntMismatch( cond );
            }
            catch( Exception e ) {
                logger.warn("Call spIfSendSMSTranCntMismatch error [{}]", e.getLocalizedMessage());
            }

            String dealFilePath = MsgCommon.msgProps.getProperty("file.dir.dealno", "");
            if( dealFilePath.length() == 0 ) {
                cond.setSendMsg( String.format("[나이스거래누락] [%s] : DEAL_NO PATH Get Error",
                        parsed.getString("deal_date")) );
                logger.warn( cond.getSendMsg() );
                cond.setSendMode( 2 );
                try {
                    splMap.spIfSendSMSTranCntMismatch( cond );
                }
                catch( Exception e ) {
                    logger.warn("Call spIfSendSMSTranCntMismatch error [{}]", e.getLocalizedMessage());
                }
                throw new MsgBrokerException(cond.getSendMsg(), -1);
            }
            File fDir = new File(dealFilePath);
            String fileName = String.format("%s.dat", parsed.getString("deal_date"));
            MsgBrokerFilenameFilter filter = new MsgBrokerFilenameFilter( fileName );
            if( fDir.isDirectory() ) {
                File[] fList = fDir.listFiles(filter);
                if( fList == null || fList.length == 0 ) {
                    cond.setSendMsg( String.format("[나이스거래누락] [%s] : FTP FILE 수신실패.[%s/%s]",
                            parsed.getString("deal_date"), dealFilePath, fileName ) );
                    logger.warn( cond.getSendMsg() );
                    cond.setSendMode( 2 );
                    try {
                        splMap.spIfSendSMSTranCntMismatch( cond );
                    }
                    catch( Exception e ) {
                        logger.warn("Call spIfSendSMSTranCntMismatch error [{}]", e.getLocalizedMessage());
                    }
                    throw new MsgBrokerException(cond.getSendMsg(), -1);
                }
            }
            else {
                cond.setSendMsg( String.format("[나이스거래누락] [%s] : DEAL_NO PATH is not directory",
                        parsed.getString("deal_date")) );
                logger.warn( cond.getSendMsg() );
                cond.setSendMode( 2 );
                try {
                    splMap.spIfSendSMSTranCntMismatch( cond );
                }
                catch( Exception e ) {
                    logger.warn("Call spIfSendSMSTranCntMismatch error [{}]", e.getLocalizedMessage());
                }
                throw new MsgBrokerException(cond.getSendMsg(), -1);
            }

            /*
             *  HOST에서 수신한 FTP File 읽어 DB에 Upload 하는 'FILEMng DEALNO 거래일자(8)'
             *  명령어를 실행하여 프로세스 가동
             */
            Process p = Runtime.getRuntime().exec( "ivkAutoSend FilemngService DEALNO SH " + parsed.getString("deal_date") );

            BufferedReader stdInput = new BufferedReader(new
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                 InputStreamReader(p.getErrorStream()));

            String ln = null;
            // read the output from the command
            logger.warn("Here is the standard output of the command");
            while ((ln = stdInput.readLine()) != null) {
                logger.warn(ln);
            }

            // read any errors from the attempted command
            logger.warn("Here is the standard error of the command (if any):\n");
            while ((ln = stdError.readLine()) != null) {
                logger.warn(ln);
            }

        }
        else {
            logger.warn("거래건수 일치");
        }
    }
}
