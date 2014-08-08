package com.nicetcm.nibsplus.broker.msg;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class MsgBrokerTransaction {
    
    public static final DefaultTransactionDefinition defMSGTX = new DefaultTransactionDefinition();
    static {
        defMSGTX.setName("MSGTX");
        defMSGTX.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    }

}
