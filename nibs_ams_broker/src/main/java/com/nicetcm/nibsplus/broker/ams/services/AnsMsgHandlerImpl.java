package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * ReqMsgHandlerImpl
 *
 *  요청 후  응답전문 처리
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
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerLib;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerTransaction;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMsgMapper;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsgKey;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsgHis;
import com.nicetcm.nibsplus.broker.ams.model.TRmTrx;

@Service("ansMsg")
public class AnsMsgHandlerImpl implements AnsMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(AnsMsgHandlerImpl.class);

    @Autowired protected SqlSession sqlSession;
    @Autowired protected DataSourceTransactionManager amsTX;

    @Autowired private   CommonPack                   comPack;
    @Autowired private   TRmMsgMapper                 msgMap;

    @Override
    public void ansMsgHandle(AMSBrokerData safeData, AMSBrokerReqJob reqJob, ByteBuffer rsltMsg, String ansSts) throws Exception {
        safeData.setSysDate( AMSBrokerLib.getSysDate() );

        safeData.setTXS(amsTX.getTransaction( AMSBrokerTransaction.defAMSTX ));
        safeData.setMsgDate( AMSBrokerLib.getMsgDate(safeData.getSysDate()) );
        safeData.setMsgTime( AMSBrokerLib.getMsgTime(safeData.getSysDate()) );
        try {
            logger.warn("Start ansMsgHandle");
            logger.warn( "TrxCd = {}, ActCd = {}", reqJob.getTrxCd(), reqJob.getActCd() );

            TRmMsgKey msgKey = new TRmMsgKey();
            TRmMsgHis msgHis = new TRmMsgHis();

            msgKey.setCreateDate( reqJob.getOrgCreateDate() );
            msgKey.setMsgSeq( reqJob.getOrgMsgSeq() );
            TRmMsg msg = msgMap.selectByPrimaryKey( msgKey );

            msg.setMsgSts( ansSts );

            if( rsltMsg != null && ansSts.equals("9") ) {
                byte[] bMsgCd    = new byte[4];
                byte[] bSvcCd    = new byte[4];
                byte[] bMacNo    = new byte[12];
                byte[] bMacSerNo = new byte[20];
                byte[] bOrgCd    = new byte[3];
                byte[] bBrchCd   = new byte[8];
                byte[] read      = new byte[rsltMsg.limit()];

                rsltMsg.position(0);
                rsltMsg.get(read);
                logger.warn("MSG = [{}]", new String(read));

                rsltMsg.position(9);
                rsltMsg.get(bMsgCd);
                rsltMsg.get(bSvcCd);

                rsltMsg.position(78);
                rsltMsg.get(bMacNo);
                rsltMsg.get(bMacSerNo);
                rsltMsg.get(bBrchCd);
                rsltMsg.get(bOrgCd);

                msg.setMsgCd( new String(bMsgCd).trim() );
                msg.setSvcCd( new String(bSvcCd).trim() );
                msg.setMacNo( new String(bMacNo).trim().substring(2) );
                msg.setMacSerNo( new String(bMacSerNo).trim() );
                msg.setOrgCd( AMSBrokerConst.NICE_ORG_CD );
                msg.setBranchCd( AMSBrokerConst.NICE_BR_CD );

                msgHis.setMsgCtx( new String(read) );
            }
            else if( rsltMsg != null && ansSts.equals("5") ) {
                byte[] read      = new byte[rsltMsg.limit()];

                rsltMsg.position(0);
                rsltMsg.get(read);

                msgHis.setMsgCtx( new String(read) );
            }


            TRmTrx rmTrx = new TRmTrx();

            rmTrx.setTrxDate( reqJob.getTrxDate() );
            rmTrx.setTrxNo( reqJob.getTrxNo() );
            rmTrx.setTrxCd( reqJob.getTrxCd() );
            rmTrx.setActCd( reqJob.getActCd() );
            rmTrx.setTrxUid( reqJob.getTrxUid() );

            comPack.insUpdMsg(safeData, reqJob.getMacNo(), rmTrx, msg, msgHis);
            amsTX.commit(safeData.getTXS());
        }
        catch( Exception e ) {
            logger.info( "AnsMessaage raise Error {}", e.getLocalizedMessage() );
            amsTX.rollback(safeData.getTXS());
            throw e;
        }
    }

}
