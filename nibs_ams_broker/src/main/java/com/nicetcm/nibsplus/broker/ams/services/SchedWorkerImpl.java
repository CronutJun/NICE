package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * SchedWorker
 *
 *  스케쥴 처리 클래스
 *
 *
 * @author  K.D.J
 * @since   2014.09.03
 */

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import static com.nicetcm.nibsplus.broker.ams.AMSBrokerConst.*;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerLib;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerSchedWorkGroup;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerSchedWorker;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerTransaction;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMacEnvMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TPmUpdsMacMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TPmPgmVerMapper;
import com.nicetcm.nibsplus.broker.ams.model.TRmMacEnv;
import com.nicetcm.nibsplus.broker.ams.model.TRmMacEnvSpec;
import com.nicetcm.nibsplus.broker.ams.model.TPmUpdsMac;
import com.nicetcm.nibsplus.broker.ams.model.TPmUpdsMacSpec;
import com.nicetcm.nibsplus.broker.ams.model.TPmPgmVer;
import com.nicetcm.nibsplus.broker.ams.model.TPmPgmVerKey;
import com.nicetcm.nibsplus.broker.common.MsgCommon;

@Service("schedWorker")
public class SchedWorkerImpl implements SchedWorker {

    private static final Logger logger = LoggerFactory.getLogger(SchedWorkerImpl.class);

    @Autowired protected SqlSession                   sqlSession;
    @Autowired protected DataSourceTransactionManager amsTX;

    @Autowired protected CommonPack                   comPack;

    @Autowired private   TRmMacEnvMapper              macEnvMap;
    @Autowired private   TPmUpdsMacMapper             updsMacMap;
    @Autowired private   TPmPgmVerMapper              pgmVerMap;

    @Override
    public void doWork(AMSBrokerData safeData, String workType, String grpCd, String mkrCd, String modelCd, String verId) throws Exception {

        logger.warn("**********************{} SCHEDULE BEGIN *********************************", workType);

        safeData.setTXS(amsTX.getTransaction( AMSBrokerTransaction.defAMSTX ));
        safeData.setSysDate( AMSBrokerLib.getSysDate() );
        safeData.setMsgDate( AMSBrokerLib.getMsgDate(safeData.getSysDate()) );
        safeData.setMsgTime( AMSBrokerLib.getMsgTime(safeData.getSysDate()) );
        try {
            /**
             * 배포 스케쥴
             */
            if( workType.equals("UPDATES") ) {
                TPmPgmVerKey pgmVerKey = new TPmPgmVerKey();
                pgmVerKey.setMkrCd  ( mkrCd );
                pgmVerKey.setModelCd( modelCd );
                pgmVerKey.setVerId  ( verId );
                TPmPgmVer pgmVer = pgmVerMap.selectByPrimaryKey( pgmVerKey );
                if( pgmVer == null ) {
                    throw new Exception(String.format("version not found : %s,%s, %s", mkrCd, modelCd, verId));
                }

                TPmUpdsMacSpec updsMacSpec = new TPmUpdsMacSpec();

                updsMacSpec.createCriteria().andGrpCdEqualTo(grpCd);
                List<TPmUpdsMac> updsMacs = updsMacMap.selectBySpec( updsMacSpec );
                for( TPmUpdsMac updsMac: updsMacs ) {
                    AMSBrokerReqJob reqJob = new AMSBrokerReqJob(updsMac.getMacNo(), true); // 요청거래는 Blocking하여 순차적으로 결과를 받는다.(성능이슈)
                    try {
                        reqJob.setTrxCd         ( TRX_CD_UPL_DWL );
                        reqJob.setActCd         ( ACT_CD_SPC_DWL );
                        reqJob.setTrxUid        ( "SCHEDULE" );
                        reqJob.setDownCmdType   ( "0" );
                        reqJob.setFileType      ( "9" );
                        reqJob.setFileCreateDate( pgmVer.getCreateDate() );
                        reqJob.setFileSeq       ( pgmVer.getFileSeq()    );
                        reqJob.setFileVersion   ( pgmVer.getVerId()      );
                        reqJob.setTimeOut       ( Integer.parseInt(MsgCommon.msgProps.getProperty("ams.req.timeout", "180")) );
                        AMSBrokerSchedWorkGroup.getInstance().execute(new AMSBrokerSchedWorker(reqJob));
                    }
                    catch( Exception e ) {
                        logger.error("Raised error while doing schedule work (PGM Updates) : {}", e.getMessage());
                    }
                }
            }
            /**
             * 저널업로드 스케쥴
             */
            else if( workType.equals("JOURNAL") ) {
                TRmMacEnvSpec macEnvSpec = new TRmMacEnvSpec();

                /**
                 * 상태 정상기기 대상으로 요청
                 */
                macEnvSpec.createCriteria().andStsEqualTo("0");
                List<TRmMacEnv> macEnvs = macEnvMap.selectBySpec( macEnvSpec );
                for( TRmMacEnv macEnv: macEnvs ) {
                    AMSBrokerReqJob reqJob = new AMSBrokerReqJob(macEnv.getMacNo(), true); // 요청거래는 Blocking하여 순차적으로 결과를 받는다.(성능이슈)
                    reqJob.setTrxCd   ( TRX_CD_UPL_DWL );
                    reqJob.setActCd   ( ACT_CD_SPC_UPL );
                    reqJob.setTrxUid  ( "SCHEDULE" );
                    reqJob.setFileDate( AMSBrokerLib.getMsgDate(-1) ); // 전일자로 요청 해야 함.
                    //reqJob.setFileDate( "20140901" );
                    reqJob.setFileType( "1" );
                    reqJob.setTimeOut ( Integer.parseInt(MsgCommon.msgProps.getProperty("ams.req.timeout", "180")) );
                    try {
                        AMSBrokerSchedWorkGroup.getInstance().execute(new AMSBrokerSchedWorker(reqJob));
                    }
                    catch( Exception e ) {
                        logger.error("Raised error while doing schedule work (Journal upload) : {}", e.getMessage());
                    }
                }
            }
            amsTX.commit(safeData.getTXS());
            logger.warn("**********************{} SCHEDULE IS SUCCESSFULLY END ******************************", workType);

        }
        catch( Exception e ) {
            amsTX.rollback(safeData.getTXS());
            logger.warn("**********************{} SCHEDULE ERROR  ******************************", workType);
            throw e;
        }
    }

}
