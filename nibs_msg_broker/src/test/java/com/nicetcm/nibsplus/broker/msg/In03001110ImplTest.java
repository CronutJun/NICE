package com.nicetcm.nibsplus.broker.msg;

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
public class In03001110ImplTest extends CmAllTests
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testCase1() throws Exception {

        final String testMsg = "020B    HOST   0590201405090000239296180         EM01000130          205099296180         20140509000023201405082359410   23594120186   98          01    107     3   1   WR901_G2     자동출동:오만원부족(현금출금부)                                                                                                                                                                                               0011                                                                                                                     02                                                                                                                                                        ";

        MsgParser msgPsr = getMsgParser(testMsg);

        InMsgHandler inMsgHandler = (InMsgHandler)MsgBrokerSpringMain.sprCtx.getBean("in03001110");
        inMsgHandler.inMsgHandle(msgPsr);
    }


}
