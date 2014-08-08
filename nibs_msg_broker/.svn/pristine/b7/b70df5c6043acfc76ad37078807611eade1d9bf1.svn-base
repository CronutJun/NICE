package com.nicetcm.nibsplus.broker.msg;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.junit.Ignore;
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
public class MsgBrokerLibTest
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Ignore
    @Test
    public void addDay() throws Exception {
        FastDateFormat fdt = FastDateFormat.getInstance("yyyyMMdd", Locale.getDefault());

        Date date = MsgBrokerLib.toDate("20140724", "yyyyMMdd");
        System.out.println("org: " + fdt.format(date));

        Date date2 = DateUtils.addDays(date, 1);
        System.out.println("to : " + fdt.format(date2));
    }

    @Ignore("skip")
    @Test
    public void decode() throws Exception {

        System.out.println(MsgBrokerLib.decode("A", "A", 1, "B", 2, "C", 3, 4));
        System.out.println(MsgBrokerLib.decode("B", "A", 1, "B", 2, "C", 3, 4));
        System.out.println(MsgBrokerLib.decode("C", "A", 1, "B", 2, "C", 3, 4));
        System.out.println(MsgBrokerLib.decode("D", "A", 1, "B", 2, "C", 3, 4));
        System.out.println(MsgBrokerLib.decode("E", "A", 1, "B", 2, "C", 3));
    }

    @Test
    public void test() {
        logger.info( "...해당 장애 없음 trans_date[{}] org_msg_no[{}]", "1", "2");
        logger.info("010-6601_8989".replaceAll("_", ""));
    }
}
