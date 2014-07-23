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
public class In03000180ImplTest extends CmAllTestSuite
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="in03000180")
    InMsgHandler inMsgHandler;

    @Test
    public void testCase1() throws Exception {
        final String testMsg = "003B00  HOST   0422201407160838471681786         CM03000180          037161681786        00441    105                                                                                   20140718       000000040000000                                                                                                                                                                                                                                                                                                          ";
        inMsgHandler.inMsgHandle(new MsgBrokerData(), getMsgParser(testMsg));
    }
}
