package com.nicetcm.nibsplus.broker.msg.mapper;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerAppConfig;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerMain;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerSpringMain;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

@RunWith(Suite.class)
@SuiteClasses({  })
public class MapperTestSuite
{

    @Autowired protected TMiscMapper tMiscMapper;

    private String orgCd = "096";
    private String branchCd = "9600";
    private String macNo = "0202";

    @Before
    public void test() {
        InputStream is = MsgBrokerMain.class.getResourceAsStream("/uat/msg.properties");
        try
        {
            MsgCommon.msgProps.load(is);
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected TMacInfo getTMacInfo() {
        TMacInfo tMacInfo = new TMacInfo();
        tMacInfo.setOrgCd(this.orgCd);
        tMacInfo.setBranchCd(this.branchCd);
        tMacInfo.setMacNo(this.macNo);

        return tMacInfo;
    };

}
