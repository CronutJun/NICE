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
public class In03101110ImplTest extends CmAllTestSuite
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testCase1() throws Exception {

        final String testMsg = "020B00  HOST   0422201405090626344167064         CM03101110                               20001   64          20140509 062634               4200000        4200000        1515000        0000000000000000              0                                                                                                                                                                                                                                                                                                        ";

        MsgParser msgPsr = getMsgParser(testMsg);

        InMsgHandler inMsgHandler = (InMsgHandler)MsgBrokerSpringMain.sprCtx.getBean("in03101110");
        inMsgHandler.inMsgHandle(msgPsr);
    }


}
