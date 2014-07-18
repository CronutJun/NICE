package com.nicetcm.nibsplus.broker.msg.services;

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
    public void getMacNoIntoSite( TMacInfo MacInfo ) throws Exception;
    public void getMacInfo( TMacInfo MacInfo ) throws Exception;
    public void insertErrMng( TCtErrorMng ErrMng, TCtErrorRcpt ErrRcpt, TCtErrorNoti ErrNoti, TCtErrorCall ErrCall,
            TCtErrorTxn ErrTxn, TMacInfo MacInfo, String PartMngYn ) throws Exception;
    public void updateErrMng( int WorkType, String DbMode, TCtErrorMng ErrMng, TCtErrorRcpt ErrRcpt, TCtErrorNoti ErrNoti, TCtErrorCall ErrCall,
            TCtErrorTxn ErrTxn, TMacInfo MacInfo, byte[] curMacStateError ) throws Exception;
    public void insertUpdateMacOpen( TMacInfo MacInfo, TCtErrorMng ErrMng ) throws Exception;
    public byte[] getCurrentErrorState( ErrorState ErrorState ) throws Exception;
}
