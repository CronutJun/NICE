package com.nicetcm.nibsplus.broker.ams;

/*
 * Copyright 2014 The NIBS Project
 *
 * AMS 기기관리시스템 - AMSBrokerReqInfo
 *
 * 기기와 접속처리 전 접속정보를 보관하는 모델 객체
 *
 * @author  K.D.J
 * @since   2014.08.18
 */

import java.nio.ByteBuffer;
import java.io.InputStream;

public class AMSBrokerReqInfo {

    private ByteBuffer msg;
    private String  destIP;
    private int    destPort;
    private InputStream strm;

    public ByteBuffer getMsg() {
        return msg;
    }

    public void setMsg(ByteBuffer msg) {
        this.msg = msg;
    }

    public String getDestIP() {
        return destIP;
    }

    public void setDestIP(String destIP) {
        this.destIP = destIP;
    }

    public int getDestPort() {
        return destPort;
    }

    public void setDestPort(int destPort) {
        this.destPort = destPort;
    }

    public InputStream getStrm() {
        return strm;
    }

    public void setStrm(InputStream strm) {
        this.strm = strm;
    }

}
