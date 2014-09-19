package com.nicetcm.nibsplus.orgsend.service;

import java.util.Map;

import com.nicetcm.nibsplus.broker.msg.model.MsgBrokerConf;
import com.nicetcm.nibsplus.orgsend.constant.TransferType;

/**
 * 쿼리후 결과값으로 전문생성후 MsgBroker모듈과 통신함
 * <pre>
 *
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public interface MsgTransferService
{
    void send(MsgBrokerConf msgBrokerConf, Map<String, String> msgBodyMap, TransferType transferType);
}
