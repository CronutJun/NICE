package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * ReqMsgHandlerImpl
 *
 *  요청전문 구성
 *
 *
 * @author  K.D.J
 * @since   2014.08.18
 */


import java.nio.ByteBuffer;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import static com.nicetcm.nibsplus.broker.ams.AMSBrokerConst.*;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqInfo;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerSpringMain;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerTransaction;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerLib;
import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.services.CommonPack;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMacEnvMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMsgMapper;
import com.nicetcm.nibsplus.broker.ams.model.TRmMacEnv;
import com.nicetcm.nibsplus.broker.ams.model.TRmMacEnvKey;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsgHis;
import com.nicetcm.nibsplus.broker.ams.model.TRmTrx;

@Service("reqMsg")
public class ReqMsgHandlerImpl implements ReqMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(ReqMsgHandlerImpl.class);

    @Autowired protected SqlSession sqlSession;
    @Autowired protected DataSourceTransactionManager amsTX;

    @Autowired private   CommonPack                   comPack;
    @Autowired private   TRmMacEnvMapper              macEnvMap;
    @Autowired private   TRmMsgMapper                 msgMap;

    @Override
    public void reqMsgHandle(AMSBrokerData safeData, AMSBrokerReqJob reqJob, AMSBrokerReqInfo reqInfo) throws Exception {

        safeData.setSysDate( AMSBrokerLib.getSysDate() );

        safeData.setTXS( amsTX.getTransaction( AMSBrokerTransaction.defAMSTX ));
        safeData.setMsgDate( AMSBrokerLib.getMsgDate(safeData.getSysDate()) );
        safeData.setMsgTime( AMSBrokerLib.getMsgTime(safeData.getSysDate()) );
        try {
            logger.warn("Start reqMsgHandle");
            TRmMacEnvKey macEnvKey = new TRmMacEnv();
            macEnvKey.setOrgCd   ( NICE_ORG_CD );
            macEnvKey.setBranchCd( NICE_BR_CD  );
            macEnvKey.setMacNo   ( reqJob.getMacNo()          );
            TRmMacEnv rslt = macEnvMap.selectByPrimaryKey( macEnvKey );
            if( rslt == null )
                throw new Exception("Mac Environment not found");

            reqInfo.setDestIP  ( rslt.getPriIpAddr()                );
            reqInfo.setDestPort( Integer.parseInt(rslt.getIpPort()) );
            //reqInfo.setDestIP("10.3.28.114");
            //reqInfo.setDestPort(33001);
            logger.warn( "TrxCd = {}, ActCd = {}", reqJob.getTrxCd(), reqJob.getActCd() );

            TRmMsg msg       = new TRmMsg();
            TRmMsgHis msgHis = new TRmMsgHis();

            msg.setMsgSeq( msgMap.generateKey() );
            msg.setIoCl( "O" );
            msg.setMsgSts( "0" );
            msg.setOrgCd( NICE_ORG_CD );
            msg.setBranchCd( NICE_BR_CD );

            /**
             * 환경정보 조회
             */
            if( (reqJob.getTrxCd().equals(TRX_CD_INQ_ENV)
              || reqJob.getTrxCd().equals(TRX_CD_INQ_ENV_M))
            &&  reqJob.getActCd().equals(ACT_CD_INQ) ) {
                msg.setMsgType( BIZ_CL_SM );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_INQ_ENV );
            }
            /**
             * Registry 정보 조회
             */
            else if( reqJob.getTrxCd().equals(TRX_CD_REG)
                  &&  reqJob.getActCd().equals(ACT_CD_INQ) ) {
                msg.setMsgType( BIZ_CL_SM );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_INQ_REG );
            }
            /**
             * Ini 정보 조회
             */
            else if( reqJob.getTrxCd().equals(TRX_CD_INI)
                  &&  reqJob.getActCd().equals(ACT_CD_INQ) ) {
                msg.setMsgType( BIZ_CL_SM );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_INQ_INI );
            }
            /**
             * Env 설정 변경
             */
            else if( (reqJob.getTrxCd().equals(TRX_CD_INQ_ENV)
                    || reqJob.getTrxCd().equals(TRX_CD_INQ_ENV_M))
                  && reqJob.getActCd().equals(ACT_CD_EXEC) ) {
                msg.setMsgType( BIZ_CL_SM  );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_CHG_ENV );
            }
            /**
             * Reg 설정 변경
             */
            else if( reqJob.getTrxCd().equals(TRX_CD_REG)
                  &&  reqJob.getActCd().equals(ACT_CD_EXEC) ) {
                msg.setMsgType( BIZ_CL_SM );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_CHG_REG );
            }
            /**
             * Ini 설정 변경
             */
            else if( reqJob.getTrxCd().equals(TRX_CD_INI)
                  &&  reqJob.getActCd().equals(ACT_CD_EXEC) ) {
                msg.setMsgType( BIZ_CL_SM );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_CHG_INI );
            }
            /**
             * Reboot 예약
             */
            else if( (reqJob.getTrxCd().equals(TRX_CD_DEV_CTL)
                    || reqJob.getTrxCd().equals(TRX_CD_DEV_CTL_ERR))
                  &&  reqJob.getActCd().equals(ACT_CD_EXEC2) ) {
                msg.setMsgType( BIZ_CL_RC );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_CMD_RBT );
            }
            /**
             * Poweroff 예약
             */
            else if( (reqJob.getTrxCd().equals(TRX_CD_DEV_CTL)
                    || reqJob.getTrxCd().equals(TRX_CD_DEV_CTL_ERR))
                  &&  reqJob.getActCd().equals(ACT_CD_EXEC1) ) {
                msg.setMsgType( BIZ_CL_RC );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_CMD_PWO );
            }
            /**
             * 장치리셋 예약
             */
            else if( (reqJob.getTrxCd().equals(TRX_CD_DEV_CTL)
                    || reqJob.getTrxCd().equals(TRX_CD_DEV_CTL_ERR))
                  &&  reqJob.getActCd().equals(ACT_CD_EXEC3) ) {
                msg.setMsgType( BIZ_CL_RC );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_CMD_DEV );
            }
            /**
             * 장치회수 예약
             */
            else if( (reqJob.getTrxCd().equals(TRX_CD_DEV_CTL)
                    || reqJob.getTrxCd().equals(TRX_CD_DEV_CTL_ERR))
                  &&  reqJob.getActCd().equals(ACT_CD_EXEC4) ) {
                msg.setMsgType( BIZ_CL_RC );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_CMD_DEV );
            }
            /**
             * 장치배출 예약
             */
            else if( (reqJob.getTrxCd().equals(TRX_CD_DEV_CTL)
                    || reqJob.getTrxCd().equals(TRX_CD_DEV_CTL_ERR))
                  &&  reqJob.getActCd().equals(ACT_CD_EXEC5) ) {
                msg.setMsgType( BIZ_CL_RC );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_CMD_DEV );
            }
            /**
             * 출동요청 안내문 전송
             */
            else if( reqJob.getTrxCd().equals(TRX_CD_NTI_CLL)
                  &&  reqJob.getActCd().equals(ACT_CD_EXEC) ) {
                msg.setMsgType( BIZ_CL_RC );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_NTI_CLL );
            }
            /**
             * 일반파일 업로드
             */
            else if( reqJob.getTrxCd().equals(TRX_CD_UPL_DWL)
                  &&  reqJob.getActCd().equals(ACT_CD_GEN_UPL) ) {
                msg.setMsgType( BIZ_CL_RC );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_UPL_GEN );
            }
            /**
             * 일반파일 다운로드
             */
            else if( reqJob.getTrxCd().equals(TRX_CD_UPL_DWL)
                  &&  reqJob.getActCd().equals(ACT_CD_GEN_DWL) ) {
                msg.setMsgType( BIZ_CL_RC );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_DWL_GEN );
            }
            /**
             * 배포(특정)파일 다운로드 요청
             */
            else if( reqJob.getTrxCd().equals(TRX_CD_UPL_DWL)
                  &&  reqJob.getActCd().equals(ACT_CD_SPC_DWL) ) {
                msg.setMsgType( BIZ_CL_PM );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_DWL_UPD );
            }
            /**
             * 배포(특정)파일 업로드 요청
             */
            else if( (reqJob.getTrxCd().equals(TRX_CD_UPL_DWL)
                    || reqJob.getTrxCd().equals(TRX_CD_JNL_UPL))
                  &&  reqJob.getActCd().equals(ACT_CD_SPC_UPL) ) {
                msg.setMsgType( BIZ_CL_PM );
                msg.setMsgCd( MSG_CD_REQ );
                msg.setSvcCd( SVC_CD_UPL_SPC );
            }

            OutMsgHandler outMsg = (OutMsgHandler)AMSBrokerSpringMain.sprCtx.getBean(String.format("out%s%s", msg.getMsgCd(), msg.getSvcCd()));
            reqInfo.setMsg( ByteBuffer.allocateDirect(MsgCommon.READ_BUF_SIZE) );
            MsgParser msgPsr = MsgParser.getInstance(String.format("%s%s%s.json", MsgCommon.msgProps.getProperty("schema_path"), msg.getMsgCd(), msg.getSvcCd()))
                                        .newMessage(reqInfo.getMsg());
            try {

                logger.warn("Going to call outbound Handler..");
                outMsg.outMsgHandle(msgPsr, safeData, reqJob, reqInfo, msg);
                logger.warn("Call outbound Handler successfully..");

                logger.warn("Message Length = "  + msgPsr.getMessageLength());
                logger.warn("Last Position = "   + msgPsr.lastPosition());
                reqInfo.getMsg().limit(msgPsr.lastPosition());
                byte[] read = new byte[reqInfo.getMsg().limit()];
                reqInfo.getMsg().position(0);
                reqInfo.getMsg().get(read);
                logger.warn(new String(read));

                msgHis.setMsgCtx( new String(read) );
            }
            finally {
                msgPsr.clearMessage();
            }

            TRmTrx rmTrx = new TRmTrx();

            rmTrx.setTrxDate( reqJob.getTrxDate() );
            rmTrx.setTrxNo( reqJob.getTrxNo() );
            rmTrx.setTrxCd( reqJob.getTrxCd() );
            rmTrx.setActCd( reqJob.getActCd() );
            rmTrx.setTrxUid( reqJob.getTrxUid() );

            comPack.insUpdMsg(safeData, reqJob.getMacNo(), rmTrx, msg, msgHis);

            reqJob.setOrgCreateDate( msg.getCreateDate() );
            reqJob.setOrgMsgSeq( msg.getMsgSeq() );

            amsTX.commit(safeData.getTXS());
        }
        catch( Exception e ) {
            logger.warn("reqMsgHandle has error [{}]", e.getMessage() );
            amsTX.rollback(safeData.getTXS());
            throw e;
        }
    }

}
