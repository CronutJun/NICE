package com.nicetcm.nibsplus.broker.ams.rmi;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * AMS Broker 에러 처리 Exception 클래스
 *
 *
 *           2014. 08. 14    K.D.J.
 */

public class AMSBrokerTimeoutException extends Exception {

    static final long serialVersionUID = -4458221324543766466L;

    private int errorCode;

    public AMSBrokerTimeoutException() {
        super("");
        errorCode = 0;
    }

    public AMSBrokerTimeoutException( String msg ) {
        super(msg);
        errorCode = 0;
    }

    public AMSBrokerTimeoutException( int errorCode ) {
        super("");
        this.errorCode = errorCode;
    }

    public AMSBrokerTimeoutException( String msg, int errorCode ) {
        super(msg);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }


}
