package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * ReqMsgHandlerImpl
 *
 *  수신전문 선 처리
 *
 *
 * @author  K.D.J
 * @since   2014.08.25
 */

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerConst;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerLib;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerTransaction;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMacEnvMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmTrxMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMsgMapper;
import com.nicetcm.nibsplus.broker.ams.model.TRmTrx;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsgHis;
import com.nicetcm.nibsplus.broker.common.MsgParser;

@Service("rcvMsg")
public class RcvMsgHandlerImpl implements RcvMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(RcvMsgHandlerImpl.class);

    @Autowired protected SqlSession sqlSession;
    @Autowired protected DataSourceTransactionManager amsTX;

    @Autowired private   CommonPack                   comPack;
    @Autowired private   TRmMacEnvMapper              macEnvMap;
    @Autowired private   TRmTrxMapper                 trxMap;
    @Autowired private   TRmMsgMapper                 msgMap;

    @Override
    public void rcvMsgHandle(AMSBrokerData safeData, MsgParser parsed, TRmTrx trx, TRmMsg msg) throws Exception {

        logger.warn("Start...");
        safeData.setSysDate( AMSBrokerLib.getSysDate() );

        safeData.setTXS( amsTX.getTransaction( AMSBrokerTransaction.defAMSTX ));
        safeData.setMsgDate( AMSBrokerLib.getMsgDate(safeData.getSysDate()) );
        safeData.setMsgTime( AMSBrokerLib.getMsgTime(safeData.getSysDate()) );
        try {
            /**
             * 거래내역 생성하지 않고 기기관련 정보 셋
             */
            trx.setTrxDate( safeData.getMsgDate()   );
            trx.setTrxNo  ( null    );
            trx.setTrxTime(  safeData.getMsgTime()  );
            trx.setTrxCd  ( parsed.getString( "CM._AOCServiceCode")     );
            trx.setActCd  ( parsed.getString( "CM._AOCMsgCode" )        );
            trx.setTrxUid(  parsed.getString( "CM._SSTNo").substring(2) );

            /**
            trxMap.insert( trx );
            */

            TRmMsgHis msgHis = new TRmMsgHis();

            msg.setMsgSeq( msgMap.generateKey() );
            msg.setIoCl    ( "I" );
            msg.setMsgSts  ( "0" );
            msg.setMsgCd   ( parsed.getString( "CM._AOCMsgCode" )        );
            msg.setSvcCd   ( parsed.getString( "CM._AOCServiceCode")     );
            msg.setMacNo   ( parsed.getString( "CM._SSTNo").substring(2) );
            msg.setMacSerNo( parsed.getString( "CM._AOCMsgSerialNo")     );
            msg.setBranchCd( parsed.getString( "CM._BranchCode")         );
            msg.setOrgCd   ( parsed.getString( "CM._BankCode")           );

            if( msg.getMsgCd().equals(AMSBrokerConst.MSG_CD_RCV) ) {
                /**
                 * POLL Skip
                 */
                if( msg.getSvcCd().equals(AMSBrokerConst.SVC_CD_NTI_POL) ) {
                    amsTX.rollback(safeData.getTXS());
                    return;
                }
                /**
                 * 개국
                 */
                else if( msg.getSvcCd().equals(AMSBrokerConst.SVC_CD_NTI_OPN) ) {
                    msg.setMsgType( "SM" );
                }
                /**
                 * 폐국
                 */
                else if( msg.getSvcCd().equals(AMSBrokerConst.SVC_CD_NTI_CLS) ) {
                    msg.setMsgType( "SM" );
                }
                /**
                 * 특정파일 자동 UPLOAD
                 */
                else if( msg.getSvcCd().equals(AMSBrokerConst.SVC_CD_AUP_SPC) ) {
                    msg.setMsgType( "PM" );
                }
                /**
                 * 배포파일 DOWNLOAD 요청
                 */
                else if( msg.getSvcCd().equals(AMSBrokerConst.SVC_CD_INQ_DWL) ) {
                    msg.setMsgType( "PM" );
                }
            }

            byte[] read = new byte[parsed.getMessage().limit()];
            parsed.getMessage().position(0);
            parsed.getMessage().get(read);
            logger.warn(new String(read));

            msgHis.setMsgCtx( new String(read) );

            comPack.insUpdMsg(safeData, msg.getMacNo(), trx, msg, msgHis);

            amsTX.commit(safeData.getTXS());
        }
        catch( Exception e ) {
            amsTX.rollback(safeData.getTXS());
            logger.warn("rcvMsgHandle raise error [{}]", e.getMessage() );
            throw e;
        }
    }

}
