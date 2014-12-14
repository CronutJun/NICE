package com.nicetcm.nibsplus.broker.msg;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.jms.MapMessage;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.jms.BytesMessage;

import com.nicetcm.nibsplus.broker.msg.util.ActiveMQ;

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

         ActiveMQ mq = new ActiveMQ("tcp://10.3.28.62:61616", "0300.1112", null);
         BytesMessage b = mq.getBytesMessage();
         b.writeBytes("005B00  HOST   0422201407160830013819872         CM03101112                               2014071520140715002300230191    066         000000000000000000000000000000000050000000050000000000000005000000005000000                                                                                                                                                                                                                                                                                                               ".getBytes());
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