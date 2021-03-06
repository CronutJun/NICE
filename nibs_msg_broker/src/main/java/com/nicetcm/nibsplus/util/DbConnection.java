package com.nicetcm.nibsplus.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    //private static final String JDBC = "jdbc:oracle:thin:@147.6.184.102:1521:BILRSS20";
/*    private static final String OWNER = "bilimpfgrp";
    private static final String PASSWD = "bilimpfgrp!";*/

    //private static final String OWNER = "bilimpf";
    //private static final String PASSWD = "bilimpf!";

/*    private static final String OWNER = "bilim";
    private static final String PASSWD = "bilim!";*/

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    private static String jdbc;
    private static String owner;
    private static String passwd;

    static {
        jdbc = NiceConfig.getProp("jdbc.url");
        owner = NiceConfig.getProp("jdbc.username");
        passwd = NiceConfig.getProp("jdbc.password");

        System.out.println("▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼");
        System.out.println("JDBC: " + jdbc);
        System.out.println("OWNER: " + owner);
        System.out.println("PASSWD: " + passwd);
        System.out.println("▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲");
    }

    public static Connection getConnection(boolean isAutoCommit) throws SQLException {

        Connection conn = null;

        try {
            DriverManager.registerDriver(new com.ibm.db2.jcc.DB2Driver());

            conn = DriverManager.getConnection(jdbc, owner, passwd);
            conn.setAutoCommit(isAutoCommit);
        } catch (SQLException e) {
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {

        }
        return conn;
    }
}
