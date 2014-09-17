package com.nicetcm.nibsplus.broker.ams;
import java.util.List;
import java.util.Arrays;

public class MacNoExpression {

    public String parseExpression(String expr) throws Exception {

        List<String> exprs = Arrays.asList(expr.replaceAll("\\s", "").split(","));
        return exprs.get(0);
    }
    public static void main( String args[] ) {
        try {
            MacNoExpression ex = new MacNoExpression();
            System.out.println(ex.parseExpression("23 343-9999, 234 23333, 333"));
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}
