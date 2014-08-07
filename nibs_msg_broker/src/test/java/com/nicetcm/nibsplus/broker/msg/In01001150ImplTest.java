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
public class In01001150ImplTest extends CmAllTestSuite
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="in01001150")
    InMsgHandler inMsgHandler;

    @Test
    public void testCase1() throws Exception {
        final String testMsg = "003S    AutoSnd05102014071600320401075461982698  EM01001150                              02014071600301820140716004600201407160030192   0440    465         9XH   1                                                                   KI909   00                                                          11  9   0online  online    9999999 임재민    01073929799                                                                                                      지연개국출동 점검요청 요청차수[01]                                                                                                              ";

        //.andTransDateEqualTo(parsed.getString("trans1_date"))
        //.andCreateDateGreaterThan(Integer.parseInt(MsgBrokerLib.SysDate(-1000)))
        ////.andCreateDateGreaterThan(Integer.parseInt(MsgBrokerLib.SysDate(-10)))
        //.andOrgMsgNoEqualTo(parsed.getString("trans1_seq"))
        //.andOrgCdEqualTo(parsed.getString("CM.org_cd"))
        //.andBranchCdEqualTo(parsed.getString("brch_cd"))
        //.andMacNoEqualTo(parsed.getString("mac_no"));

        MsgParser msgParser = getMsgParser(testMsg);
        msgParser.setString("trans1_date", "20140710");
        msgParser.setString("trans1_seq", "0720048");
        msgParser.setString("CM.org_cd", "003");
        msgParser.setString("brch_cd", "0441");
        msgParser.setString("mac_no", "045");

        inMsgHandler.inMsgHandle(new MsgBrokerData(), msgParser);
    }


}
