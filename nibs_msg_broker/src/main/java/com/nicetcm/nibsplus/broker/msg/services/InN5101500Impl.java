package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker CD-VAN 원격관리 요청
 *
 * <pre>
 * NQ_AP_SaveNiceRemoteMng
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
import com.nicetcm.nibsplus.broker.msg.mapper.TCtRemoteHistoryMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtRemoteHistory;
import com.nicetcm.nibsplus.broker.msg.model.TCtRemoteHistorySpec;

@Service("inN5101500")
public class InN5101500Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN5101500Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TCtRemoteHistoryMapper ctRemoteHistoryMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCtRemoteHistory ctRemoteHistory = new TCtRemoteHistory();
        TCtRemoteHistorySpec ctRemoteHistorySpec = new TCtRemoteHistorySpec();

        ctRemoteHistorySpec.createCriteria().andCreateDateEqualTo( parsed.getString("create_date") )
                                            .andErrorNoEqualTo( parsed.getString("error_no") )
                                            .andCreateSeqEqualTo( parsed.getString("remote_seq") );
        if( parsed.getString("CM.ret_cd").equals("00") )
            ctRemoteHistory.setRmtStatus( "AB" );
        else if( parsed.getString("CM.ret_cd").equals("71") )
            ctRemoteHistory.setRmtStatus( "EB" );
        else if( parsed.getString("CM.ret_cd").equals("72") )
            ctRemoteHistory.setRmtStatus( "EC" );
        else
            ctRemoteHistory.setRmtStatus( "XX" );
        ctRemoteHistory.setRcvDate( safeData.getDSysDate() );

        try {
            if( ctRemoteHistoryMap.updateBySpecSelective( ctRemoteHistory, ctRemoteHistorySpec ) == 0 ) {

            }
        }
        catch( Exception e ) {
            logger.warn( "t_ct_remote_history UPDATE Error [{}]", e.getLocalizedMessage() );
            throw e;
        }
    }
}
