package com.nicetcm.nibsplus.broker.ams;

import java.util.ArrayList;

public class MacNoExpression {

    public String parseExpression(String macNoColNm, String expr) throws Exception {
        if( macNoColNm == null || macNoColNm.length() == 0 ) throw new Exception("must be set macNoColNm.");
        if( expr == null || expr.length() == 0 ) throw new Exception("must be set expr.");

        checkBracket( expr );

        checkMacNoDigits( expr );

        String incExpr = extractIncludeExpr( expr );
        String excExpr = extractExcludeExpr( expr );

        System.out.println("incExpr = " + incExpr);
        System.out.println("excExpr = " + excExpr);

        String incExprs[] = incExpr.replaceAll("\\s", "").split(",");
        String excExprs[] = excExpr.replaceAll("\\s", "").split(",");

        StringBuffer sqlWhere = new StringBuffer();
        sqlWhere.append("(");
        sqlWhere.append("(");
        ArrayList<String> sqlBetween = new ArrayList<String>();
        ArrayList<String> sqlIn      = new ArrayList<String>();
        String range[];
        String inSQL = "";
        for(String inc: incExprs) {
            if( inc.trim().length() == 0 ) continue;
            range = inc.trim().split("-");
            if( range.length > 1) {
                sqlBetween.add(String.format("( %s BETWEEN '%s' AND '%s' )", macNoColNm.toUpperCase(), range[0], range[1]));
            }
            else {
                sqlIn.add(inc.trim());
            }
        }
        if( sqlIn.size() > 0 ) {
            sqlWhere.append(String.format("( %s IN (", macNoColNm.toUpperCase()));
            for(int i = 0; i < sqlIn.size(); i++) {
                if( (i+1) == sqlIn.size() )
                    sqlWhere.append(String.format("'%s')", sqlIn.get(i)));
                else
                    sqlWhere.append(String.format("'%s', ", sqlIn.get(i)));
            }
            if( sqlBetween.size() > 0 )
                sqlWhere.append(" ) OR ");
            else
                sqlWhere.append(" )");
        }
        if( sqlBetween.size() > 0 ) {
            for(int i = 0; i < sqlBetween.size(); i++) {
                if( (i+1) == sqlBetween.size() )
                    sqlWhere.append(String.format("%s", sqlBetween.get(i)));
                else
                    sqlWhere.append(String.format("%s OR ", sqlBetween.get(i)));
            }
        }

        sqlIn.clear();
        sqlBetween.clear();
        for(String exc: excExprs) {
            if( exc.trim().length() == 0 ) continue;
            range = exc.trim().split("-");
            if( range.length > 1) {
                sqlBetween.add(String.format("( %s NOT BETWEEN '%s' AND '%s' )", macNoColNm.toUpperCase(), range[0], range[1]));
            }
            else {
                sqlIn.add(exc.trim());
            }
        }
        if( sqlIn.size() > 0 || sqlBetween.size() > 0 ) {
            sqlWhere.append(") AND (");
        }
        if( sqlIn.size() > 0 ) {
            sqlWhere.append(String.format("( %s NOT IN (", macNoColNm.toUpperCase()));
            for(int i = 0; i < sqlIn.size(); i++) {
                if( (i+1) == sqlIn.size() )
                    sqlWhere.append(String.format("'%s')", sqlIn.get(i)));
                else
                    sqlWhere.append(String.format("'%s', ", sqlIn.get(i)));
            }
            if( sqlBetween.size() > 0 )
                sqlWhere.append(" ) AND ");
            else
                sqlWhere.append(" )");
        }
        if( sqlBetween.size() > 0 ) {
            for(int i = 0; i < sqlBetween.size(); i++) {
                if( (i+1) == sqlBetween.size() )
                    sqlWhere.append(String.format("%s", sqlBetween.get(i)));
                else
                    sqlWhere.append(String.format("%s AND ", sqlBetween.get(i)));
            }
        }

        sqlWhere.append(")");
        sqlWhere.append(")");

        return sqlWhere.toString();
    }

    private void checkBracket(String expr) throws Exception {
        int opnIdx = expr.indexOf("[");
        int clsIdx = expr.indexOf("]");

        if( opnIdx < 0 && clsIdx < 0 ) return;
        if( opnIdx < 0 || clsIdx < 0 )
            throw new Exception("No pair brackets. Open = [" + opnIdx + "], Close = [" + clsIdx + "]");
        if( clsIdx < opnIdx )
            throw new Exception("Opening bracket's index is greater than Closing bracket's index. Open = [" + opnIdx + "], Close = [" + clsIdx + "]");

        checkBracket(expr.substring(clsIdx+1));
    }

    private void checkMacNoDigits(String expr) throws Exception {
        String exprs[] = expr. replaceAll("\\[", "")
                             .replaceAll("\\]", "")
                             .split(",");
        String trimedStr;
        String range[];

        for( String macCond: exprs ) {
            if( macCond.trim().length() == 0 ) continue;

            trimedStr = macCond.trim();
            range = trimedStr.split("-");
            if( range.length > 1) {
                if( range[0].indexOf(" ") > 0 )
                    throw new Exception("Mac no of from range is invalid. From range = " + range[0]);
                if( range[1].indexOf(" ") > 0 )
                    throw new Exception("Mac no of to range is invalid. To range = " + range[1]);
                if( range[0].length() != 4 )
                    throw new Exception("Length of Mac no of from range is invalid. From range = " + range[0]);
                if( range[1].length() != 4 )
                    throw new Exception("Length of Mac no of to range is invalid. To range = " + range[1]);
                if( range[1].compareTo(range[0]) < 0 )
                    throw new Exception(String.format("To range is greater than From range, From range = %s, To range = %s", range[0], range[1]));
            }
            else {
                if( trimedStr.indexOf(" ") > 0 )
                    throw new Exception("Mac no is invalid. Mac no = " + trimedStr);
                if( trimedStr.length() != 4 )
                    throw new Exception("Length of Mac no is invalid. Mac no = " + trimedStr);
            }
        }
    }

    private String extractIncludeExpr(String expr) {
        int opnIdx = expr.indexOf("[");
        int clsIdx = expr.indexOf("]");

        if( opnIdx < 0 && clsIdx < 0 ) return expr;

        return expr.substring(0, opnIdx) + "," + extractIncludeExpr(expr.substring(clsIdx+1));
    }

    private String extractExcludeExpr(String expr) {
        int opnIdx = expr.indexOf("[");
        int clsIdx = expr.indexOf("]");

        if( opnIdx < 0 && clsIdx < 0 ) return "";

        return expr.substring(opnIdx+1, clsIdx) + "," + extractExcludeExpr(expr.substring(clsIdx+1));
    }

    public static void main( String args[] ) {
        try {
            MacNoExpression ex = new MacNoExpression();
            System.out.println(ex.parseExpression("mac_no", "7000-7900, 0202, [7286],9600,[7511-7566,7900]"));
            System.out.println(String.format("%-4.4s%-4.4s", "001", "001"));
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
