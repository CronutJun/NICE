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
public class in03101170ImplTest extends CmAllTestSuite
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="in03101170")
    InMsgHandler inMsgHandler;

    @Test
    public void testCase1() throws Exception {
        final String testMsg = "005B00  HOST   0422201407160625363817421         CM03101170                               0010    063                                                                                   20140717      122000000                                          강북                                                                                                                                                                                                                                                                   ";
        inMsgHandler.inMsgHandle(new MsgBrokerData(), getMsgParser(testMsg));
    }
}
