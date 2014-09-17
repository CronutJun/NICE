package com.nicetcm.nibsplus.broker.ams;
import java.util.List;
import java.util.Arrays;

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
        
        return incExprs[0];
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
            if( macCond.length() == 0 ) continue;
            
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
            System.out.println(ex.parseExpression("mac_no", "2343-9999, 2343, [3333,4444],5555,6666,[7777,8888],9999-8888"));
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
