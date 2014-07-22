package com.nicetcm.nibsplus.broker.msg;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker Library
 *
 *       전역 공통함수 Library
 *
 *           2014. 06. 24    K.D.J.
 *
 *           @author  K.D.J
 */

import java.util.Date;
import java.util.Locale;
import java.util.Calendar;

import org.apache.commons.lang.time.*;

public class MsgBrokerLib {

    /**
     * 현재 날짜를 구한다
     * @return String  현재날짜
     */
    public static String SysDate() {
        Date today = new Date();

        FastDateFormat fdt = FastDateFormat.getInstance("yyyyMMdd", Locale.getDefault());
        return fdt.format(today);
    }

    /**
     * 현재 날짜를 기준으로 amount만큼 가감한 날짜를 구한다
     * @param int  amount
     * @return String  현재날짜
     */
    public static String SysDate(int amount) {
        Date calcDay = DateUtils.addDays(new Date(), amount);

        FastDateFormat fdt = FastDateFormat.getInstance("yyyyMMdd", Locale.getDefault());
        return fdt.format(calcDay);
    }

    /**
     * 현재 날짜를 구한다
     * @return Date  현재날짜를 Date형식으로 구한다.
     * @see java.util.Date
     */
    public static Date SysDateD(int amount) {

        return DateUtils.addDays(new Date(), amount);

    }

    /**
     * 현재 시간을 HHmmss형으로 구한다
     * @return String  현재시간
     */
    public static String SysTime() {
        Date today = new Date();

        FastDateFormat fdt = FastDateFormat.getInstance("HHmmss", Locale.getDefault());
        return fdt.format(today);
    }

    /**
     * 현재 요일을 정수형으로 구한다
     * 1 - Sun, 2 - Mon, 3 - Tue, 4 - Wed, 5 - Fri, 6 - Sat
     * @return int  현재요일
     */
    public static int currentDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 날자 형식의 문자열을 Date형으로 변환한다.
     *
     * @param strDate - 날짜형 문자열
     * @param fmt    - 날짜형 문자열의 포맷 (SimpleDateFormat 규칙 준수)
     * @return Date - 변환된 날짜
     */
    public static Date toDate(String strDate, String fmt) throws Exception {
        return DateUtils.parseDate( strDate, new String[]{fmt} );
    }

    /**
     * 주어진 길이만큼 좌측을 주어진 값으로 채움
     *
     * @param src    원본 문자열
     * @param len    길이
     * @param padStr 채울 값
     * @return       채워진 문자열
     */
    public static String lpad(String src, int len, String padStr) {

        String padding = "";
        for( int i = src.length(); i < len; i++ )
            padding += padStr;

        return padding + src.substring( (src.length() - len ) > 0 ? src.length() - len : 0 );
    }

    /**
     * 주어진 길이만큼 우측을 주어진 값으로 채움
     *
     * @param src    원본 문자열
     * @param len    길이
     * @param padStr 채울 값
     * @return       채워진 문자열
     */
    public static String rpad(String src, int len, String padStr) {

        String padding = "";
        for( int i = src.length(); i < len; i++ )
            padding += padStr;

        return src.substring( 0, (src.length() - len ) > 0 ? len : src.length() ) + padding;
    }

    /**
     *
     * 왼쪽공백제거
     * <pre>
     * Implemented by removing the white spaces.
     * </pre>
     *
     * @param s
     * @return
     */
    public static String ltrim(String s) {
        int i = 0;
        while (i < s.length() && Character.isWhitespace(s.charAt(i))){
            i++;
        }
        return s.substring(i);
    }

    /**
     *
     * 오른쪽공백제거
     * <pre>
     * Implemented by removing the white spaces.
     * </pre>
     *
     * @param s
     * @return
     */
    public static String rtrim(String s) {
        int i = s.length()-1;
        while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
            i--;
        }
        return s.substring(0,i+1);
    }
}
