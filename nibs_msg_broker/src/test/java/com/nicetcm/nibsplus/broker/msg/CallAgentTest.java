package com.nicetcm.nibsplus.broker.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.model.MsgBrokerConf;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerCallAgent;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerCallBack;


public class CallAgentTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void msgSample2() {

        //전문의 필수값 설정
        //MsgBrokerConf msgBrokerConf = new MsgBrokerConf(ORG_CD, "FORMAT_TYPE", "MSG_TYPE", "WORK_TYPE");
        MsgBrokerConf msgBrokerConf = new MsgBrokerConf("003", "CM", "0300", "1210");

        //전문값 설정(사용자 정의 VO객체)
        MsgSampleVO msgSampleVO = new MsgSampleVO();
        msgSampleVO.setBrchCd("XXXX");  //String형(문자열)
        msgSampleVO.setInCnt(9999);     //int형(건수)
        msgSampleVO.setInAmt(380000);   //Long형(금액)

        //콜백 인터페이스 구현
        MsgBrokerCallBack<MsgSampleVO> callback = new MsgBrokerCallBack<MsgSampleVO>()
        {
            //전문 호출전
            //전문에 Message 셋팅
            @Override
            public void doPreCallBroker(MsgParser parsed, MsgSampleVO params) throws Exception
            {
                parsed.setString("brch_cd", "096");
            }

            //전문 호출후
            //결과값 얻기
            @Override
            public void doPostCallBroker(MsgParser parsed, MsgSampleVO params) throws Exception
            {
                //전문 결과값을 VO객체에 설정
                params.setPreCloseInAmt(parsed.getLong("pre_close_in_amt"));

                //※ 비동기식 전문호출은 현재 메소드를 수행하지 않습니다.
            }
        };

        //전문호출 객체 생성
        MsgBrokerCallAgent<MsgSampleVO> agent = new MsgBrokerCallAgent<MsgSampleVO>(msgBrokerConf, msgSampleVO, callback);

        //동기식 전문호출(Timeout설정)
        try
        {
            agent.callBrokerSync(10); //타임아웃 10초 설정
        } catch (Exception e)
        {
            //통신중에 Exception이 발생한 경우 적절한 처리
            e.printStackTrace();
        }

        //비동기식 전문호출
        //try
        //{
        //    agent.callBrokerAync();
        //} catch (Exception e)
        //{
        //    //통신중에 Exception이 발생한 경우 적절한 처리
        //    e.printStackTrace();
        //}

        logger.info(msgSampleVO.toString());
    }

}