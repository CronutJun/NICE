package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * CommonPackImpl
 *
 *  전문관련 DB처리 공통
 *
 *
 * @author  K.D.J
 * @since   2014.08.18
 */

import org.apache.commons.beanutils.BeanUtils;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMsgMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMsgHisMapper;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsgHis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Component
public class CommonPackImpl implements CommonPack {

    private static final Logger logger = LoggerFactory.getLogger(CommonPackImpl.class);

    @Autowired protected DataSourceTransactionManager msgTX;

    @Autowired private TRmMsgMapper                   msgMap;
    @Autowired private TRmMsgHisMapper                msgHisMap;

    @Override
    public void insUpdMsg(AMSBrokerData safeData, AMSBrokerReqJob reqJob, TRmMsg msg, TRmMsgHis msgHis) throws Exception {

        if( msg.getCreateDate() == null )
            msg.setCreateDate( safeData.getMsgDate() );
        if( msg.getCreateTime() == null )
            msg.setCreateTime( safeData.getMsgTime() );
        if( msg.getMacNo() == null )
            msg.setMacNo( reqJob.getMacNo() );

        msg.setInsertDate( safeData.getSysDate() );
        msg.setInsertUid( reqJob.getTrxUid() );
        msg.setUpdateDate( safeData.getSysDate() );
        msg.setUpdateUid( reqJob.getTrxUid() );
        msg.setTrxDate( reqJob.getTrxDate() );
        msg.setTrxNo( reqJob.getTrxNo() );

        try {
            msgMap.insert( msg );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                msgMap.updateByPrimaryKeySelective( msg );
            }
            catch( Exception e ) {
                logger.info( String.format("T_RM_MSG update error, CreateDate = %s, MsgSeq = %s, error = %s",
                                             msg.getCreateDate(), msg.getMsgSeq(), e.getLocalizedMessage()) );
            }
        }
        catch( Exception e ) {
            logger.info( String.format("T_RM_MSG insert error, CreateDate = %s, MsgSeq = %s, error = %s",
                    msg.getCreateDate(), msg.getMsgSeq(), e.getLocalizedMessage()) );
        }

        BeanUtils.copyProperties(msgHis, msg);
        msgHis.setHisSeq( msgHisMap.generateKey(msgHis) );
        try {
            msgHisMap.insert( msgHis );
        }
        catch( Exception e ) {
            logger.info( String.format("T_RM_MSG_HIS insert error, CreateDate = %s, MsgSeq = %s, error = %s",
                    msg.getCreateDate(), msg.getMsgSeq(), e.getLocalizedMessage()) );
        }

    }

}
