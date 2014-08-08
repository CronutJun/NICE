package com.nicetcm.nibsplus.broker.msg.rmi;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 에러 처리 Exception 클래스
 * 
 * 
 *           2014. 07. 07    K.D.J.
 */

public class MsgBrokerTimeoutException extends Exception {

    static final long serialVersionUID = 7889361468333625812L;
    
    private int errorCode;
    
    public MsgBrokerTimeoutException() {
        super("");
        errorCode = 0;
    }
    
    public MsgBrokerTimeoutException( String msg ) {
        super(msg);
        errorCode = 0;
    }
    
    public MsgBrokerTimeoutException( int errorCode ) {
        super("");
        this.errorCode = errorCode;
    }
    
    public MsgBrokerTimeoutException( String msg, int errorCode ) {
        super(msg);
        this.errorCode = errorCode;
    }
    
    public int getErrorCode() {
        return errorCode;
    }

}
