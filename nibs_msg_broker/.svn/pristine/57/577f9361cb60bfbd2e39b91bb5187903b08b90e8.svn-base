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
public class In03101193ImplTest extends CmAllTestSuite
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="in03101193")
    InMsgHandler inMsgHandler;

    @Test
    public void testCase1() throws Exception {
        final String testMsg = "003B00  HOST   0422201407160900000114444         CM03101193                              1   20140716201407172014071701       000009800000000                              000004409270000000002794618000                                                                                             000000000000000000000000000000000008185348000000001614652000                              201407152014071500                                                                                                              ";
        inMsgHandler.inMsgHandle(new MsgBrokerData(), getMsgParser(testMsg));
    }


}
