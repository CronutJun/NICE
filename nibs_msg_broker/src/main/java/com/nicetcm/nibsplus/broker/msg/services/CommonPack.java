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
    public void insertErrMng( TCtErrorMng ErrMng, TMacInfo MacInfo, String PartMngYn ) throws Exception;
}
