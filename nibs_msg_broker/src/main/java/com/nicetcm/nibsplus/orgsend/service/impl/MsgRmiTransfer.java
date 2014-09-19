package com.nicetcm.nibsplus.orgsend.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.model.MsgBrokerConf;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerCallAgent;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerCallBack;
import com.nicetcm.nibsplus.orgsend.constant.TransferType;
import com.nicetcm.nibsplus.orgsend.service.MsgTransferService;

/**
 * MsgBroker과 RMI 통신
 * <pre>
 * 전문 프로토콜에 맞게 생성후 RMI 전송
 * TransferType 에 따라 동기/비동기 구분
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("MsgRmiTransfer")
public class MsgRmiTransfer implements MsgTransferService
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void send(MsgBrokerConf msgBrokerConf, final Map<String, String> msgBodyMap, TransferType transferType)
    {

        //전문의 필수값 설정
        //MsgBrokerConf msgBrokerConf = new MsgBrokerConf(ORG_CD, "FORMAT_TYPE", "MSG_TYPE", "WORK_TYPE");

        //전문값 설정(사용자 정의 VO객체)
        //MsgSampleVO msgSampleVO = new MsgSampleVO();
        //msgSampleVO.setBrchCd("XXXX");  //String형(문자열)
        //msgSampleVO.setInCnt(9999);     //int형(건수)
        //msgSampleVO.setInAmt(380000);   //Long형(금액)

        //콜백 인터페이스 구현
        MsgBrokerCallBack<Object> callback = new MsgBrokerCallBack<Object>()
        {
            //전문 호출전
            //전문에 Message 셋팅
            @Override
            public void doPreCallBroker(MsgParser parsed, Object params) throws Exception
            {
                //Body 셋팅
                if(msgBodyMap != null) {
                    for(Map.Entry<String, String> columnEntry : msgBodyMap.entrySet()) {
                        //logger.debug("[columnEntry key:{}, value:{}]", columnEntry.getKey(), columnEntry.getValue());
                        if(columnEntry.getValue() != null) {
                            parsed.setString(columnEntry.getKey(), columnEntry.getValue());
                        }
                    }
                }
            }

            //전문 호출후
            //결과값 얻기
            @Override
            public void doPostCallBroker(MsgParser parsed, Object params) throws Exception
            {
                //전문 결과값을 VO객체에 설정
                //params.setPreCloseInAmt(parsed.getLong("pre_close_in_amt"));

                //※ 비동기식 전문호출은 현재 메소드를 수행하지 않습니다.
            }
        };

        //전문호출 객체 생성
        MsgBrokerCallAgent<Object> agent = new MsgBrokerCallAgent<Object>(msgBrokerConf, null, callback);


        try
        {
            if(transferType.equals(TransferType.AUTO_SEND)) {
                //동기식 전문호출(Timeout설정)
                logger.info("Call " + Thread.currentThread().getName() + "★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
                agent.callBrokerSync(10); //타임아웃 10초 설정
                logger.info("End " + Thread.currentThread().getName() + "★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
            } else {
                //비동기식 전문호출
                agent.callBrokerAync();
            }
        } catch (Exception e)
        {
            //통신중에 Exception이 발생한 경우 적절한 처리
            e.printStackTrace();
        }
    }//end method
}
