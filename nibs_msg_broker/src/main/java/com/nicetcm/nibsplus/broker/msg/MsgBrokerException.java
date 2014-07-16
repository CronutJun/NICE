package com.nicetcm.nibsplus.broker.msg;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 에러 처리 Exception 클래스
 * 
 * 
 *           2014. 07. 07    K.D.J.
 */

public class MsgBrokerException extends Exception {
    
    private int errorCode;
    
    public MsgBrokerException() {
        super("");
        errorCode = 0;
    }
    
    public MsgBrokerException( String msg ) {
        super(msg);
        errorCode = 0;
    }
    
    public MsgBrokerException( int errorCode ) {
        super("");
        this.errorCode = errorCode;
    }
    
    public MsgBrokerException( String msg, int errorCode ) {
        super(msg);
        this.errorCode = errorCode;
    }
    
    public int getErrorCode() {
        return errorCode;
    }

}
