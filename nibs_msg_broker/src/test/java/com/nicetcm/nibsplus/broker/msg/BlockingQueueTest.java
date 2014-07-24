package com.nicetcm.nibsplus.broker.msg;

import java.util.concurrent.*;

public class BlockingQueueTest {
    
    public static void main(String[] args) throws Exception {
        BlockingQueue<String> waitQ = new LinkedBlockingQueue<String>();
        
        
        System.out.println("waitQ size = " + waitQ.size());
        waitQ.put("abc");
        System.out.println("waitQ size = " + waitQ.size());
        waitQ.poll(3, TimeUnit.SECONDS);
        System.out.println("waitQ size = " + waitQ.size());
        //System.out.println("waitQ data = " + ret);
    }
    
}
