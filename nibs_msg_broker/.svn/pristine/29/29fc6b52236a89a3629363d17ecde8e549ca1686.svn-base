package com.nicetcm.nibsplus.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public abstract class DataBaseObj {

    private Connection con = null;
    private PreparedStatement pstmt = null;
    private String sql = null;
    private List<String> strArrParameter = null;

    protected DataBaseObj(Connection con, String sql) throws SQLException {
        this.con = con;
        this.sql = sql;
        this.pstmt = this.con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    }

    protected DataBaseObj(Connection con, String sql, int mode) throws SQLException {
        this.con = con;
        this.sql = sql;
        if(mode == 0) {
            this.pstmt = this.con.prepareStatement(sql);
        }
    }

    protected void setStrArrParameter(List<String> strArrParameter) throws SQLException {
        this.strArrParameter = strArrParameter;
    }

    protected void addSqlParamString() throws SQLException {
        for(int i = 1, j = 0; j < strArrParameter.size(); i++, j++) {
            System.out.println("pstmt[" + j + "] " + strArrParameter.get(j));
            this.pstmt.setString(i, strArrParameter.get(j));
        }
    }

    protected int executeUpdate() throws SQLException {
        try {
            return this.pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
        }
    }

    protected CResultSet executeQuery() throws SQLException {
        CResultSet crs = null;
        ResultSet rs = null;

        try {
            rs = this.pstmt.executeQuery();
            crs = new CResultSet(rs);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(rs != null) { rs.close(); };
        }

        return crs;
    }

    protected ResultSet executeQueryResultSet() throws SQLException {
        ResultSet rs = null;

        try {
            rs = this.pstmt.executeQuery();
        } catch (SQLException e) {
            throw e;
        } finally {
        }

        return rs;
    }

    /**
     * List 한개당 Map한개 (Map은 <컬럼명, 값>
     * @return
     * @throws SQLException
     */
    protected List<Map<String, String>> executeQueryList() throws SQLException {
        List<Map<String, String>> returnValue = new ArrayList<Map<String, String>>();
        CResultSet crs = null;

        crs = this.executeQuery();
        Properties ps = crs.getColumnNamesProperties();

        while(crs.next()) {
            Iterator<String> it = (Iterator)ps.keys();
            Map<String, String> mapRow = new HashMap<String, String>();

            while(it.hasNext()) {
                String col = it.next().toString();
                mapRow.put(col, crs.getString(col));
            }

            returnValue.add(mapRow);
        }

        return returnValue;
    }

    /**
     * Map<<String>컬럼명(단수), <List>값의복수> 값은 row순서에 의해 저장
     * @return
     * @throws SQLException
     */
    protected Map<String, List<String>> executeQueryMap() throws SQLException {
        Map<String, List<String>> returnValue = new HashMap<String, List<String>>();
        CResultSet crs = null;

        crs = this.executeQuery();
        Properties ps = crs.getColumnNamesProperties();

        List<List<String>> arrTotalValue = new ArrayList<List<String>>();
        List<String> strTotalCol = new ArrayList<String>();

        //column네임 리스트에 add
        Iterator<String> itCol = (Iterator)ps.keys();
        while(itCol.hasNext()) {
            strTotalCol.add(itCol.next().toString());
        }

        //값을저장할 리스트 생성
        for(int i = 0; i < ps.size(); i++) {
            arrTotalValue.add(new ArrayList<String>());
        }

        //crs를 돌면서 각 리스트순번에 값저장
        for(int i = 0; crs.next(); i++) {
            Iterator<String> it = (Iterator)ps.keys();

            int j = 0;
            while(it.hasNext()) {
                String col = it.next().toString();
                arrTotalValue.get(j).add(crs.getString(col));
                j++;
            }
        }

        // 컬럼명 리스트와 값리스트를 순서에 의해 저장
        for(int i = 0; i < strTotalCol.size(); i++) {
            returnValue.put(strTotalCol.get(i), arrTotalValue.get(i));
        }

        return returnValue;
    }

    protected void close() throws SQLException {
        if(this.pstmt != null) {pstmt.close();}
    }
}
