package com.nicetcm.nibsplus.orgsend.service;

import java.util.Map;

import com.nicetcm.nibsplus.broker.msg.model.MsgBrokerConf;

public interface MsgTransferService
{
    public void send(MsgBrokerConf msgBrokerConf, Map<String, String> msgBodyMap);
}
