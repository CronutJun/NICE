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

import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;

import com.nicetcm.nibsplus.broker.common.MsgCommon;

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
     * 해당 날짜를 기준으로 amount만큼 가감한 날짜를 구한다
     * @param date
     * @param amount
     * @return
     */
    public static String SysDate(Date date, int amount) {
        FastDateFormat fdt = FastDateFormat.getInstance("yyyyMMdd", Locale.getDefault());
        return fdt.format(DateUtils.addDays(date, amount));
    }

    /**
     * 해당 날짜를 기준으로 amount만큼 가감한 날짜를 구한다
     * @param yyyymmdd
     * @param amount
     * @return
     * @throws Exception
     */
    public static String SysDate(String yyyymmdd, int amount) throws Exception {
        FastDateFormat fdt = FastDateFormat.getInstance("yyyyMMdd", Locale.getDefault());
        return fdt.format(DateUtils.addDays(MsgBrokerLib.toDate(yyyymmdd, "yyyyMMdd"), 1));
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
        if( src == null ) src = new String("");
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
        if( src == null ) src = new String("");
        for( int i = src.length(); i < len; i++ )
            padding += padStr;

        return src.substring( 0, (src.length() - len ) > 0 ? len : src.length() ) + padding;
    }

    /**
     * 문자열 NULL치환
     *
     * @param src    원본 문자열
     * @return       치환 문자열
     */
    public static String nstr(String src) {

        if( src == null ) return "";
        return src;

    }

    /**
     * 문자열 자르기
     *
     * @param src    원본 문자열
     * @param pos    시작위치 0 index
     * @param ePos   자를 위치 다음 index
     * @return       자른 문자열
     */
    public static String substr(String src, int pos, int ePos) {

        if( src == null ) return "";
        if( src.length()-1 < pos ) return "";
        if( src.length() < ePos ) return src.substring(pos);
        return src.substring( pos, ePos );
    }

    /**
     * 문자열 자르기
     *
     * @param src    원본 문자열
     * @param pos    시작위치 0 index
     * @return       자른 문자열
     */
    public static String substr(String src, int pos) {

        if( src == null ) return "";
        if( src.length()-1 < pos ) return "";
        return src.substring( pos );
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

    /**
    *
    * 버퍼를 할당하고 Incomming전문의 Q명을 돌려준다.
    * <pre>
    * Implemented by removing the white spaces.
    * </pre>
    *
    * @param buf 생성하려는 ByteBuffer instance pointer
    * @param msg 원문전문
    * @return
    */
    public static class BufferAndQName {
        public ByteBuffer buf;
        public String QNm;
        public String orgCd;
        public String msgType;
        public String wrkType;
    }

    public static BufferAndQName allocAndFindSchemaName(byte[] msg, String IO, boolean isSetThreadID) {

        BufferAndQName ret = new BufferAndQName();
        byte[] bOrgCd   = new byte[3];
        byte[] bMsgType = new byte[4];
        byte[] bWrkType = new byte[4];

        ret.buf = ByteBuffer.allocateDirect(msg.length);
        ret.buf.put(msg);
        ret.buf.position(0);
        ret.buf.get(bOrgCd);
        ret.buf.position(MsgBrokerConst.MSG_TYPE_OFS);
        ret.buf.get(bMsgType);
        ret.buf.get(bWrkType);
        ret.buf.position(0);

        ret.orgCd   = new String(bOrgCd);
        ret.msgType = new String(bMsgType);
        ret.wrkType = new String(bWrkType);

        if( IO.equals("O") && bMsgType[2] == '0')
            bMsgType[2] = '1';
        else if( IO.equals("I") && bMsgType[2] == '1')
            bMsgType[2] = '1';
        if( isSetThreadID )
            Thread.currentThread().setName(String.format("<T>%s-%s%s-%s", new String(bOrgCd).trim(), new String(bMsgType).trim(), new String(bWrkType).trim(), Thread.currentThread().getId()));

        /*
         * 응답 전문의 경우에 스키마 파일은 원본 요청 전문에 해당하는 스키마를 읽도록 한다.
         */
        bMsgType[2] = '0';
        ret.QNm = MsgCommon.msgProps.getProperty("schema_path") + new String(bMsgType).trim() + new String(bWrkType).trim() + ".json";

        return ret;
    }

    /**
     *
     * Oracle의 Decode 함수 구현
     * <pre>
     * Decoding Expresssions to values same as 'DECODE' function in Oracle
     * </pre>
     *
     * @param args
     * @return
     * @throws Exception
     */
    public static Object decode(Object... args) throws Exception {
        Object compareItem = args[0];
        int maxIndex = args.length - 1;
        int indx = 1;

        if (compareItem != null)
        {
            for (; indx < maxIndex; indx += 2)
            {
                if (compareItem.equals(args[indx]))
                {
                    return args[indx + 1];
                }
            }
        } else
        {
            throw new Exception("MsgBrokerLib.decode :: First Element in array is null value");
        }

        return indx == maxIndex ? args[indx] : null;

    }

    public static String camelToLayoutStyle(String str) {
        String regex = "([a-z])([A-Z])";
        String replacement = "$1_$2";

        return str.replaceAll(regex, replacement);
    }
}
