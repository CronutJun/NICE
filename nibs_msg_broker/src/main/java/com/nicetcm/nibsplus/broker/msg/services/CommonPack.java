package com.nicetcm.nibsplus.broker.msg.services;

import java.lang.reflect.InvocationTargetException;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.model.*;
/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 서비스 공통 interface
 *
 *
 *           2014. 06. 24    K.D.J.
 */

public interface CommonPack {

    public void checkBranchMacLength( TMacInfo MacInfo ) throws Exception;
            void checkBranchMacLength(MsgParser parsed) throws Exception;
    public void getMacNoIntoSite( TMacInfo MacInfo ) throws Exception;
    public void getMacInfo( TMacInfo MacInfo ) throws Exception;
    public void insertErrBasic( TCtErrorBasic ErrMng, TCtErrorRcpt ErrRcpt, TCtErrorNoti ErrNoti, TCtErrorCall ErrCall,
            TCtErrorTxn ErrTxn, TMacInfo MacInfo, String PartMngYn ) throws Exception;
    public void updateErrBasic( MsgBrokerData safeData, int WorkType, String DbMode, TCtErrorBasic ErrMng, TCtErrorRcpt ErrRcpt, TCtErrorNoti ErrNoti, TCtErrorCall ErrCall,
            TCtErrorTxn ErrTxn, TMacInfo MacInfo, byte[] curMacStateError ) throws Exception;
    public void insertUpdateMacOpen( MsgBrokerData safeData, TMacInfo MacInfo, TCtErrorBasic ErrBasic ) throws Exception;
    public byte[] getCurrentErrorState( ErrorState ErrorState ) throws Exception;
    public void updateMacInfo( MsgBrokerData safeData, TMacInfo MacInfo, TCmMac Mac) throws Exception;
    public void insertUpdateMacSet( MsgBrokerData safeData, int WorkType, TCtMacSetMng MacSetMng ) throws Exception;
    public void insertOrgSigeChnage( MsgBrokerData safeData, TCtOrgSiteChange OrgSiteChange ) throws Exception;
    boolean getDupErrorMng(TCtErrorBasic ErrBasic, int CancelYN) throws Exception;
    int getOwnTradeSeqYN(TMisc tMisc);
    int getBoxRecvYN(TMisc tMisc);
    int getTicketDealRecvYN(TMisc tMisc);
    int updateErrorMng(TCtErrorMng updateTCtErrorMng, TCtErrorMngSpec tCtErrorMngSpec) throws IllegalAccessException, InvocationTargetException;

}
