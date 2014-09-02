package com.nicetcm.nibsplus.orgsend.service;

import java.util.Map;

import com.nicetcm.nibsplus.broker.msg.model.MsgBrokerConf;
import com.nicetcm.nibsplus.orgsend.constant.TransferType;

public interface MsgTransferService
{
    void send(MsgBrokerConf msgBrokerConf, Map<String, String> msgBodyMap, TransferType transferType);
}
