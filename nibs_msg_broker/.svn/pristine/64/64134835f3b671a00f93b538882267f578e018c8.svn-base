package com.nicetcm.nibsplus.broker.msg;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorRcpt;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.nicetcm.nibsplus.broker.msg.MsgBrokerAppConfig.class})
public class TCtErrorMngTest extends CmAllTestSuite
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Ignore("skip")
    @Test
    public void testCase1() throws Exception {
        TCtErrorMng tCtErrorMng = new TCtErrorMng();
        tCtErrorMng.setAcceptDate("shin0682");
        tCtErrorMng.setErrorNo("12345");
        tCtErrorMng.setMadeErrCd("a000");

        TCtErrorBasic tCtErrorBasic = new TCtErrorBasic();

        TCtErrorRcpt tCtErrorRcpt = new TCtErrorRcpt();

        System.out.println(tCtErrorBasic.toString());

        BeanUtils.copyProperties(tCtErrorBasic , tCtErrorMng );
        BeanUtils.copyProperties(tCtErrorRcpt , tCtErrorMng );

        System.out.println(tCtErrorBasic.toString());
        System.out.println(tCtErrorBasic.getErrorNo());

        System.out.println(tCtErrorRcpt.toString());
        System.out.println(tCtErrorRcpt.getErrorNo());
    }

    @Test
    public void testField() throws IllegalArgumentException, IllegalAccessException {
        TCtErrorBasic tCtErrorBasic = new TCtErrorBasic();
        tCtErrorBasic.setMadeErrCd("AAA");

        Field[] tCtErrorBasicFields = (tCtErrorBasic.getClass()).getDeclaredFields();

        for(Field field : tCtErrorBasicFields) {
            field.setAccessible(true);

            if(field.get(tCtErrorBasic) == null) {
                logger.info(field.getName() + " is NULL");
            } else {
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + field.getName() + ": " + field.get(tCtErrorBasic));
            }

        }
    }
}
