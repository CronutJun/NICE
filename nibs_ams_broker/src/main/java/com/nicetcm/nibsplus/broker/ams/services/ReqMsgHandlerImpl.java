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

import com.nicetcm.nibsplus.broker.ams.AMSBrokerConst;
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
            logger.debug("Start reqMsgHandle");
            TRmMacEnvKey macEnvKey = new TRmMacEnv();
            macEnvKey.setOrgCd   ( AMSBrokerConst.NICE_ORG_CD );
            macEnvKey.setBranchCd( AMSBrokerConst.NICE_BR_CD  );
            macEnvKey.setMacNo   ( reqJob.getMacNo()          );
            TRmMacEnv rslt = macEnvMap.selectByPrimaryKey( macEnvKey );
            if( rslt == null )
                throw new Exception("Mac Environment not found");

            reqInfo.setDestIP  ( rslt.getPriIpAddr()                );
            reqInfo.setDestPort( Integer.parseInt(rslt.getIpPort()) );
            //reqInfo.setDestIP("10.3.28.114");
            //reqInfo.setDestPort(33001);
            logger.debug( "TrxCd = {}, ActCd = {}", reqJob.getTrxCd(), reqJob.getActCd() );

            TRmMsg msg       = new TRmMsg();
            TRmMsgHis msgHis = new TRmMsgHis();

            msg.setMsgSeq( msgMap.generateKey() );
            msg.setIoCl( "O" );
            msg.setMsgSts( "0" );
            msg.setMsgType( "RC" );

            /**
             * 환경정보 조회
             */
            if( reqJob.getTrxCd().equals("720103") && reqJob.getActCd().equals("500") ) {
                msg.setMsgCd( "2100" );
                msg.setSvcCd( "2002" );
            }
            /**
             * Registry 정보 조회
             */
            else if( reqJob.getTrxCd().equals("2000") && reqJob.getActCd().equals("1000") ) {
                msg.setMsgCd( "2100" );
                msg.setSvcCd( "2011" );
            }
            /**
             * Ini 정보 조회
             */
            else if( reqJob.getTrxCd().equals("3000") && reqJob.getActCd().equals("1000") ) {
                msg.setMsgCd( "2100" );
                msg.setSvcCd( "2012" );
            }
            /**
             * Env 설정 변경
             */
            else if( reqJob.getTrxCd().equals("4000") && reqJob.getActCd().equals("1000") ) {
                msg.setMsgCd( "2100" );
                msg.setSvcCd( "3002" );
            }
            /**
             * Reg 설정 변경
             */
            else if( reqJob.getTrxCd().equals("5000") && reqJob.getActCd().equals("1000") ) {
                msg.setMsgCd( "2100" );
                msg.setSvcCd( "3011" );
            }
            /**
             * Ini 설정 변경
             */
            else if( reqJob.getTrxCd().equals("6000") && reqJob.getActCd().equals("1000") ) {
                msg.setMsgCd( "2100" );
                msg.setSvcCd( "3012" );
            }
            /**
             * Reboot 예약
             */
            else if( reqJob.getTrxCd().equals("7000") && reqJob.getActCd().equals("501") ) {
                msg.setMsgCd( "2100" );
                msg.setSvcCd( "4001" );
            }
            /**
             * Poweroff 예약
             */
            else if( reqJob.getTrxCd().equals("7000") && reqJob.getActCd().equals("502") ) {
                msg.setMsgCd( "2100" );
                msg.setSvcCd( "4001" );
            }
            /**
             * 장치리셋 예약
             */
            else if( reqJob.getTrxCd().equals("7000") && reqJob.getActCd().equals("503") ) {
                msg.setMsgCd( "2100" );
                msg.setSvcCd( "4002" );
            }
            /**
             * 장치회수 예약
             */
            else if( reqJob.getTrxCd().equals("7000") && reqJob.getActCd().equals("504") ) {
                msg.setMsgCd( "2100" );
                msg.setSvcCd( "4002" );
            }
            /**
             * 장치배출 예약
             */
            else if( reqJob.getTrxCd().equals("7000") && reqJob.getActCd().equals("505") ) {
                msg.setMsgCd( "2100" );
                msg.setSvcCd( "4002" );
            }

            OutMsgHandler outMsg = (OutMsgHandler)AMSBrokerSpringMain.sprCtx.getBean(String.format("out%s%s", msg.getMsgCd(), msg.getSvcCd()));
            reqInfo.setMsg( ByteBuffer.allocateDirect(MsgCommon.READ_BUF_SIZE) );
            MsgParser msgPsr = MsgParser.getInstance(String.format("%s%s%s.json", MsgCommon.msgProps.getProperty("schema_path"), msg.getMsgCd(), msg.getSvcCd()))
                                        .newMessage(reqInfo.getMsg());
            try {

                outMsg.outMsgHandle(msgPsr, safeData, reqJob, reqInfo, msg);

                logger.debug("Message Length = "  + msgPsr.getMessageLength());
                logger.debug("Last Position = "   + msgPsr.lastPosition());
                reqInfo.getMsg().limit(msgPsr.lastPosition());
                byte[] read = new byte[reqInfo.getMsg().limit()];
                reqInfo.getMsg().position(0);
                reqInfo.getMsg().get(read);
                logger.debug(new String(read));

                msgHis.setMsgCtx( new String(read) );
            }
            finally {
                msgPsr.clearMessage();
            }

            comPack.insUpdMsg(safeData, reqJob, msg, msgHis);

            reqJob.setOrgCreateDate( msg.getCreateDate() );
            reqJob.setOrgMsgSeq( msg.getMsgSeq() );

            amsTX.commit(safeData.getTXS());
        }
        catch( Exception e ) {
            amsTX.rollback(safeData.getTXS());
            throw e;
        }
    }

}
