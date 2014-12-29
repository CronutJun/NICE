package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 금융결제원 차액결제자료
 *
 * <pre>
 * MngNQ_AP_RemoteKFTCFileSend
 * </pre>
 *
 *           2014. 08. 04    K.D.J.
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmBatchResultMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmBatchResult;

@Service("inN5104600")
public class InN5104600Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN5104600Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TCmBatchResultMapper cmBatchResultMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        /*
         *  FTP파일 생성 및 전송에 대한 응답전문을 받는다.
         *  T_CM_BATCH_RESULT에 기록한다.
         */
        TCmBatchResult cmBatchResult = new TCmBatchResult();

        cmBatchResult.setJobDate( parsed.getString("CM.trans_date") );
        cmBatchResult.setPgmId( "RemoteKFTCFileSend" );
        cmBatchResult.setPgmResult( "OK" );
        cmBatchResult.setInsertDate( safeData.getDSysDate() );
        cmBatchResult.setInsertUid( "DataMng" );
        try {
            cmBatchResultMap.insertSelective( cmBatchResult );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                cmBatchResultMap.updateByPrimaryKey( cmBatchResult );
            }
            catch( Exception e ) {
                logger.warn( "batch 테이블 기록 덮어씌우기 실패" );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.warn( "batch 테이블 기록 실패 [{}]", e.getLocalizedMessage() );
            throw e;
        }
        logger.warn( "batch 테이블 기록 성공" );
    }
}
