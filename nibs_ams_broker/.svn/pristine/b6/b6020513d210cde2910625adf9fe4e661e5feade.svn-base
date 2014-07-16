package com.nicetcm.nibsplus.broker.ams;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AMSBrokerCommon {
    
    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerCommon.class);

    public static Date getSysDate() throws Exception {
        logger.debug("getSysDate");
        return Calendar.getInstance().getTime();
    }
    
    public static String getMsgDate(Date dt) throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat("YYYYMMDD");
        return  fmt.format(dt);
    }

    public static String getMsgTime(Date dt) throws Exception {
        SimpleDateFormat fmt = new SimpleDateFormat("HHmmss");
        return  fmt.format(dt);
    }
}
