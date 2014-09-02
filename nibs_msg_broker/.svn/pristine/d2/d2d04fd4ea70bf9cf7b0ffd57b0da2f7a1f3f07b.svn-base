package com.nicetcm.nibsplus.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TableDelete extends DataBaseObj {

    public TableDelete(Connection con, String sql) throws SQLException {
        super(con, sql);
    }

    public int run(List<String> strArrParameter) throws SQLException {
        super.setStrArrParameter(strArrParameter);
        super.addSqlParamString();
        return super.executeUpdate();
    }

    public int run() throws SQLException {
        return super.executeUpdate();
    }

    public void close() throws SQLException {
        super.close();
    }

}
