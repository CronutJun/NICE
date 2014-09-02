package com.nicetcm.nibsplus.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TableInsert extends DataBaseObj {

    public TableInsert(Connection con, String sql) throws SQLException {
        super(con, sql);
    }

    public int run(List<String> strArrParameter) throws SQLException {
        super.setStrArrParameter(strArrParameter);
        super.addSqlParamString();
        System.out.println("set parameter complete");
        return this.run();
    }

    public int run() throws SQLException {
        return super.executeUpdate();
    }

    public void close() throws SQLException {
        super.close();
    }
}
