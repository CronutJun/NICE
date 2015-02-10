package com.nicetcm.nibsplus.broker.ams;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * AMSBrokerClientQ
 *
 *  Client 요청처리 후 결과 대기시 사용하는 Queue Data
 *
 *
 * @author  K.D.J
 * @since   2014.08.31
 */

import io.netty.buffer.ByteBuf;

public class AMSBrokerClientQData {

    private boolean isContinue;
    private boolean isTimeout;
    private ByteBuf clientData;

    public AMSBrokerClientQData( boolean isContinue, boolean isTimeout, ByteBuf clientData ) {
        this.isContinue = isContinue;
        this.isTimeout  = isTimeout;
        this.clientData = clientData;
    }

    public boolean isContinue() {
        return isContinue;
    }

    public boolean isTimeout() {
        return isTimeout;
    }

    public ByteBuf getClientData() {
        return clientData;
    }

}
