package com.nicetcm.nibsplus.broker.msg;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.nicetcm.nibsplus.broker.msg.MsgBrokerAppConfig.class})
public class MsgBrokerLibTest extends CmAllTestSuite
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Test
    public void addDay() throws Exception {
        FastDateFormat fdt = FastDateFormat.getInstance("yyyyMMdd", Locale.getDefault());

        Date date = MsgBrokerLib.toDate("20140724", "yyyyMMdd");
        System.out.println("org: " + fdt.format(date));

        Date date2 = DateUtils.addDays(date, 1);
        System.out.println("to : " + fdt.format(date2));
    }
}
