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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AMSBrokerLib {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerLib.class);

    public static String ROOT_FILE_PATH;
    public static String TEMP_FILE_PATH;

    /**
     * 현재날짜를 구한다.
     *
     * @return   현재날짜
     * @throws Exception
     */
    public static Date getSysDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 날짜를 yyyyMMdd format의 string으로 변환한다.
     *
     * @param dt    날짜
     * @return
     * @throws Exception
     */
    public static String getMsgDate(Date dt) {
        FastDateFormat fdt = FastDateFormat.getInstance("yyyyMMdd", Locale.getDefault());
        return  fdt.format(dt);
    }

    /**
     * 현재 날짜를 기준으로 amount만큼 가감한 날짜를 구한다
     * @param int  amount
     * @return String  가감한 날짜
     */
    public static String getMsgDate(int amount) {
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
    public static String getMsgDate(Date date, int amount) {
        FastDateFormat fdt = FastDateFormat.getInstance("yyyyMMdd", Locale.getDefault());
        return fdt.format(DateUtils.addDays(date, amount));
    }

    /**
     * 시간을 HHmmss format의 string으로 변환한다.
     *
     * @param dt    시간
     * @return
     * @throws Exception
     */
    public static String getMsgTime(Date dt) {
        FastDateFormat fdt = FastDateFormat.getInstance("HHmmss", Locale.getDefault());
        return  fdt.format(dt);
    }

    /**
     * 날자 형식의 문자열을 Date형으로 변환한다.
     *
     * @param strDate   - 날짜형 문자열
     * @param fmt       - 날짜형 문자열의 포맷 (SimpleDateFormat 규칙 준수)
     * @return Date     - 변환된 날짜
     */
    public static Date toDate(String strDate, String fmt) throws Exception {
        return DateUtils.parseDate( strDate, new String[]{fmt} );
    }

    /**
     * 압축파일을푼다.
     *
     * @param zipFile       압축파일명
     * @param outputFolder  대상디렉토리
     * @throws Exception
     */
    public static void unZip(String zipFile, String outputFolder) throws Exception {

        byte[] buffer = new byte[1024];

        try{

           //create output directory is not exists
           File folder = new File(outputFolder);
           if( !folder.exists() ){
               folder.mkdir();
           }

           //get the zip file content
           ZipInputStream zis =  new ZipInputStream(new FileInputStream(zipFile));
           //get the zipped file list entry
           ZipEntry ze = zis.getNextEntry();

           while( ze != null ) {

              String fileName = ze.getName();
              File newFile = new File(outputFolder + File.separator + fileName);

              logger.debug("file unzip : "+ newFile.getAbsoluteFile());

               //create all non exists folders
               //else you will hit FileNotFoundException for compressed folder
               new File(newFile.getParent()).mkdirs();

               FileOutputStream fos = new FileOutputStream(newFile);

               int len;
               while ((len = zis.read(buffer)) > 0) {
                   fos.write(buffer, 0, len);
               }

               fos.close();
               ze = zis.getNextEntry();
           }

           zis.closeEntry();
           zis.close();

           logger.debug("Done");

        }
        catch( Exception e ) {
            logger.debug("unzip has Exception : {}", e.getMessage() );
        }
    }
}
