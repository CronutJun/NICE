package com.nicetcm.nibsplus.broker.ams;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * AMSBrokerLib
 *
 *  전역 공통함수 Library
 *
 *
 * @author  K.D.J
 * @since   2014.06.29
 */

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AMSBrokerLib {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerLib.class);

    public static Date getSysDate() throws Exception {
        logger.debug("getSysDate");
        return Calendar.getInstance().getTime();
    }

    public static String getMsgDate(Date dt) throws Exception {
        FastDateFormat fdt = FastDateFormat.getInstance("yyyyMMdd", Locale.getDefault());
        return  fdt.format(dt);
    }

    public static String getMsgTime(Date dt) throws Exception {
        FastDateFormat fdt = FastDateFormat.getInstance("HHmmss", Locale.getDefault());
        return  fdt.format(dt);
    }
}
