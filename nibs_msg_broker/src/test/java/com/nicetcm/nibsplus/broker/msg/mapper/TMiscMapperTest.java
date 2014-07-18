package com.nicetcm.nibsplus.broker.msg.mapper;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicetcm.nibsplus.broker.msg.services.In03101110Impl.CloseAmt;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.nicetcm.nibsplus.broker.msg.MsgBrokerAppConfig.class})
public class TMiscMapperTest extends MapperTestSuite
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void selectCloseAmt() throws Exception {

        CloseAmt closeAmt = null;
        try
        {
            closeAmt = tMiscMapper.selectCloseAmt(getTMacInfo());
        } catch (Exception e)
        {
            System.out.println("***************************************************");
            System.out.println("***************************************************");
            System.out.println("***************************************************");
            System.out.println("***************************************************");

            logger.error("******************************: " + e.getMessage());
        }
        assertTrue("tobe NO_DATA_FOUND", closeAmt != null);
        //logger.debug("closeAmt: " + closeAmt.toString());
    }
}
