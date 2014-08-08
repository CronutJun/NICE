package com.nicetcm.nibsplus.broker.msg;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.services.InMsgHandler;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.nicetcm.nibsplus.broker.msg.MsgBrokerAppConfig.class})
public class In01000140ImplTest extends CmAllTestSuite
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="in01000140")
    InMsgHandler inMsgHandler;

    @Test
    public void testCase1() throws Exception {
        final String testMsg = "003B00  HOST   0340201407160832201681704         EM01000140          037161681704        020140716083218201407160822274   0441    298         A34   0111101 1   kt텔레캅 민화욱 대원요청/고객과 통화.. 점심시간에 연락드리고 통장 교부하기로함..                                                                                                                        KI901_32                                                              ";
        inMsgHandler.inMsgHandle(new MsgBrokerData(), getMsgParser(testMsg));
    }


}
