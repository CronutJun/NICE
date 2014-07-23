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
    @Autowired private TCtErrorBasicMapper ctErrBasicMap;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testCase1() {
        logger.debug("start test case#1");
        MsgBrokerData safeData = new MsgBrokerData();
        
        safeData.setTXS(msgTX.getTransaction( MsgBrokerTransaction.defMSGTX ));
        try {
            TCtErrorBasic cond = new TCtErrorBasic();
            TCtErrorTxn txn = new TCtErrorTxn();
            cond.setOrgCd("096");
            cond.setBranchCd("9600");
            cond.setMacNo("2884");
            cond.setErrorCd("ERR17");
            txn.setRepairDate("20140721");
            txn.setRepairTime("080000");
            List<TCtErrorBasicJoin> rsltErrMng = ctErrBasicMap.selectByJoin3(cond);
            logger.debug("result:  size = {}", rsltErrMng.size());
            for( TCtErrorBasicJoin rslt: rsltErrMng) {
                logger.debug("          error_no = {}",    rslt.getErrorNo());
                logger.debug("          repair_date = {}", rslt.getRepairDate());
                logger.debug("          repair_time = {}", rslt.getRepairTime());
            }
            msgTX.commit(safeData.getTXS());
        }
        catch( Exception e ) {
            msgTX.rollback(safeData.getTXS());
        }
    }

}
