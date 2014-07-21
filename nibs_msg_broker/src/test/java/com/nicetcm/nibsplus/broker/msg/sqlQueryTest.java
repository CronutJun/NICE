package com.nicetcm.nibsplus.broker.msg;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicetcm.nibsplus.broker.msg.mapper.*;
import com.nicetcm.nibsplus.broker.msg.model.*;

import java.util.List;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.nicetcm.nibsplus.broker.msg.MsgBrokerAppConfig.class})
public class sqlQueryTest extends CmAllTestSuite {

    @Autowired protected DataSourceTransactionManager msgTX;
    @Autowired private StoredProcMapper splMap;
    @Autowired private TCtErrorMngMapper ctErrMngMap;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testCase1() {
        logger.debug("start test case#1");
        MsgBrokerData safeData = new MsgBrokerData();
        
        safeData.setTXS(msgTX.getTransaction( MsgBrokerTransaction.defMSGTX ));
        try {
            TCtErrorMng cond = new TCtErrorMng();
            TCtErrorTxn txn = new TCtErrorTxn();
            cond.setOrgCd("020");
            cond.setBranchCd("0900");
            cond.setMacNo("1111");
            txn.setRepairDate("20120721");
            txn.setRepairTime("080000");
            List<TCtErrorMng> rsltErrMng = ctErrMngMap.selectByCond4(cond, txn);
        }
        catch( Exception e ) {
            msgTX.rollback(safeData.getTXS());
        }
    }

}
