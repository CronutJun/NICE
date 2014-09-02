package com.nicetcm.nibsplus.orgsend.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.msg.model.MsgBrokerConf;
import com.nicetcm.nibsplus.orgsend.constant.TransferType;
import com.nicetcm.nibsplus.orgsend.service.MsgTransferService;

@Service("MsgLocalTransfer")
public class MsgLocalTransfer implements MsgTransferService
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void send(MsgBrokerConf msgBrokerConf, final Map<String, String> msgBodyMap, TransferType transferType)
    {
        //TEST기간에는 MsgBroker RMI를 호출하는 대신
        //여기서 로그만 출력 (전송전문에 대한 검증작업)
        logger.info(msgBrokerConf.toString());
        logger.info(msgBodyMap.toString());
        logger.info(transferType.toString());
    }//end method
}
