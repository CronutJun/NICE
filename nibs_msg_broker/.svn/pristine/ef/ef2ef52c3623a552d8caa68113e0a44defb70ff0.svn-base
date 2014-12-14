package com.nicetcm.nibsplus.scheduler.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.util.DbConnection;
import com.nicetcm.nibsplus.util.ExcelRowParser;
import com.nicetcm.nibsplus.util.TableDelete;
import com.nicetcm.nibsplus.util.TableInsert;

/**
 *
 * OP.T_CM_SCHEDULE 에 Quartz 스케쥴 정보 로딩
 *
 * <pre>
 * 엑셀정보를 POI 라이브러리를 이용 DB Table에 로딩
 *
 * drop table op.T_CM_SCHEDULE;
 *
 * create table OP.T_CM_SCHEDULE (
 * QUARTZ_NODE_NAME     VARCHAR2(128 ) NOT NULL
 * ,JOB_GROUP           VARCHAR2(128 ) NOT NULL
 * ,JOB_NAME            VARCHAR2(128 ) NOT NULL
 * ,JOB_EXPLAIN         VARCHAR2(2048) NOT NULL
 * ,CRON_Expression     VARCHAR2(256 ) NOT NULL
 * ,STATUS              VARCHAR2(64  ) NOT NULL
 * ,REAL_TIME_COMMAND   VARCHAR2(64  )
 * ,T_ARG1              VARCHAR2(128 ) NOT NULL
 * ,T_ARG2              VARCHAR2(128 ) NOT NULL
 * ,T_ARG3              VARCHAR2(128 ) NOT NULL
 * ,T_ARG4              VARCHAR2(128 ) NOT NULL
 * ,JOB_PRIORITY        NUMBER  (3   ) NOT NULL
 * ,JOB_CLASS           VARCHAR2(1024) NOT NULL
 * ,SPRING_CONTEXT_XML  VARCHAR2(1024) NOT NULL
 * ,JOB_LISTENER_CLASS  VARCHAR2(1024)
 * ,USE_YN              VARCHAR2(1   ) NOT NULL
 * ,REGMN_ID            VARCHAR2(10  ) NOT NULL
 * ,REG_YMD             VARCHAR2(8   ) NOT NULL
 * );
 *
 * ALTER TABLE OP.T_CM_SCHEDULE ADD CONSTRAINT PK_CM_SCHEDULE PRIMARY KEY (QUARTZ_NODE_NAME, JOB_GROUP, JOB_NAME);
 *
 * commit;
 *
 * select * from op.T_CM_SCHEDULE;
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public class TCmScheduleLoader {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String TABLE_NAME;
    private static HSSFWorkbook WB;

    private static String EXCEL_FILE_NAME = "/scheduler/properties/OP.T_CM_SCHEDULE.local.xls";

    private final int firstDataNum = 2;

    private HSSFSheet sheet = null;

    private StringBuffer sbInsert;

    private StringBuffer sbDelete;

    private List<String> columnList;

    public static void main(String[] args) {
        FileInputStream fis = null;
        POIFSFileSystem fs = null;
        InputStream is = null;

        try {
            if( args.length > 0 ) {
                fis = new FileInputStream(args[0]);
                fs = new POIFSFileSystem(fis);
            }
            else {
                is = TCmScheduleLoader.class.getResourceAsStream(TCmScheduleLoader.EXCEL_FILE_NAME);
                fs = new POIFSFileSystem(is);
            }
            WB = new HSSFWorkbook(fs);

            Connection conn = null;

            try {
                conn = DbConnection.getConnection(false);

                List<String> columnList = Arrays.asList(
                    "QUARTZ_NODE_NAME"
                    ,"JOB_GROUP"
                    ,"JOB_NAME"
                    ,"JOB_EXPLAIN"
                    ,"CRON_Expression"
                    ,"STATUS"
                    ,"REAL_TIME_COMMAND"
                    ,"T_ARG1"
                    ,"T_ARG2"
                    ,"T_ARG3"
                    ,"T_ARG4"
                    ,"JOB_PRIORITY"
                    ,"JOB_CLASS"
                    ,"SPRING_CONTEXT_XML"
                    ,"JOB_LISTENER_CLASS"
                    ,"USE_YN"
                    ,"REGMN_ID"
                    ,"REG_YMD"
                );

                new TCmScheduleLoader("OP.T_CM_SCHEDULE", columnList).run(conn);

                conn.commit();
                System.out.println("DB Commit 성공 / 로딩 완료");
            } catch (Exception e) {
                if(conn != null) {conn.rollback();}
                e.printStackTrace();
                System.out.println("DB Commit 실패 / 로딩 실패");
            } finally {
                if(conn != null) {conn.close();}
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if( args.length > 0 )
                    fis.close();
                else
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public TCmScheduleLoader(String tableName, List<String> columnList) throws SQLException {
        this.TABLE_NAME = tableName;
        this.sheet = WB.getSheet(TABLE_NAME);
        this.columnList = columnList;
        setSbInsert();
        setSbdelete();
        System.out.println(this.sbInsert);
    }

    private void setSbdelete() {
        sbDelete = new StringBuffer();
        sbDelete.append("delete from " + TABLE_NAME);
    }

    private void setSbInsert() {
        sbInsert = new StringBuffer();

        sbInsert.append(" INSERT INTO " + TABLE_NAME + " ( ");

        for(int i = 0; i<columnList.size(); i++) {
            if(i == columnList.size() - 1) {
                sbInsert.append(" " + columnList.get(i) + " ");
            } else {
                sbInsert.append(" " + columnList.get(i) + ", ");
            }
        }

        sbInsert.append(" ) ");
        sbInsert.append(" VALUES ( ");

        for(int j = 0; j<columnList.size(); j++) {
            if(j == columnList.size() - 1) {
                //sbInsert.append(" :" + column + " ");
                sbInsert.append(" " + "?" + " ");
            } else {
                sbInsert.append(" " + "?" + ", ");
            }
        }

        sbInsert.append(" ) ");
    }

    private int deleteInit(Connection conn) throws Exception {
        TableDelete tbd = null;

        try {
            tbd = new TableDelete(conn, this.sbDelete.toString());
            return tbd.run();
        } catch (Exception e) {
            throw e;
        } finally {
            if(tbd != null) {tbd.close();}
        }
    }

    public int run(Connection conn) throws Exception {

        int deleteCount = this.deleteInit(conn);
        System.out.println("기존자료 삭제 건수: " + deleteCount);
        TableInsert tbi = null;
        int returnResult = 0;

        ExcelRowParser xrp = new ExcelRowParser(this.sheet, this.columnList.size());
        List<Map<String, String>> arrSheet = xrp.getArrSheet(firstDataNum);

        try {
            tbi = new TableInsert(conn, this.sbInsert.toString());
            for(int i = 0; i < arrSheet.size(); i++) {
                Map<String, String> mapRow = arrSheet.get(i);
                List<String> strArrParameter = new ArrayList<String>();

                int j = 0;
                for(String column : columnList) {
                    strArrParameter.add(mapRow.get(column));
                    System.out.println("[" + j + "] " + mapRow.get(column));
                    j++;
                }
                //System.out.println(strArrParameter);
                tbi.run(strArrParameter);
                returnResult++;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            System.out.println("신규자료 입력 건수: " + returnResult);
            if(tbi != null) {tbi.close();}
        }

        return returnResult;
    }
}
