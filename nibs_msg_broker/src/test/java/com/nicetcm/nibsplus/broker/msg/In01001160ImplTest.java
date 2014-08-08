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
public class In01001160ImplTest extends CmAllTestSuite
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="in03101110")
    InMsgHandler inMsgHandler;

    @Test
    public void testCase1() throws Exception {

        final String testMsg = "003S    AutoSnd04222014071600380501075551404946  EM01001160                              0201407160030177   0440    350                        1 00                                                                           2014071600371211                                                                                                                                                            9QW                                                                                                                   ";
        MsgParser msgParser = getMsgParser(testMsg);

        logger.info("error_msg:" + msgParser.getString("error_msg"));


        //inMsgHandler.inMsgHandle(new MsgBrokerData(), getMsgParser(testMsg));

        //"        a "
        logger.info("org_call_cnt:{}", msgParser.getString("error_msg").substring(15, 17));


    }


}
