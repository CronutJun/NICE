package com.nicetcm.nibsplus.broker.ams;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;


public class AMSBrokerTransaction {

    public static final DefaultTransactionDefinition defAMSTX = new DefaultTransactionDefinition();
    static {
        defAMSTX.setName("AMSTX");
        defAMSTX.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    }

}
