package com.nicetcm.nibsplus.broker.msg;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.jms.MapMessage;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.BytesMessage;

public class ActiveMQTest {
     public static void main(String[] args) throws Exception {
         //for( int i = 0; i < 50; i++ ) {
         //ActiveMQ mq = new ActiveMQ("tcp://localhost:61616", "academic" + i, new ActiveMQListener());
 
         //TextMessage text = mq.getTextMessage();
         //text.setText("text message");
         //text.setIntProperty("int", 1);
         //mq.produce(text);
 
         //mq.close();
         //}
         
         ActiveMQ mq = new ActiveMQ("tcp://10.3.28.62:61616", "0100.0130", null);
         BytesMessage b = mq.getBytesMessage();
         b.writeBytes("020B    HOST   0590201405090000239296180         EM01000130          205099296180         20140509000023201405082359410   23594120186   98          01    107     3   1   WR901_G2     자동출동:오만원부족(현금출금부)                                                                                                                                                                                               0011                                                                                                                     02                                                                                                                                                        ".getBytes());
         mq.produce(b);
         mq.close();
         /*
         BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

         for (;;){
             
             String line = in.readLine();
             if (line == null) {
                 break;
             }

             if ("bye".equals(line.toLowerCase())) {
                 break;
             }
         }
         */
     }
}