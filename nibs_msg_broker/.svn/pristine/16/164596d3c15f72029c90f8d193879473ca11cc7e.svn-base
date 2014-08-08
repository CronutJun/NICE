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
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;

import java.util.List;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.nicetcm.nibsplus.broker.msg.MsgBrokerAppConfig.class})
public class sqlQueryTest extends CmAllTestSuite {

    @Autowired protected DataSourceTransactionManager msgTX;
    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnNiceTranMapper fnNiceTranMap;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testCase1() {
        org.apache.log4j.xml.DOMConfigurator.configure(MsgBrokerMain.class.getResource(
                String.format("/%s/log4j.xml", MsgBrokerConst.SVR_TYPE)));
        logger.debug("start test case#1");
        MsgBrokerData safeData = new MsgBrokerData();

        safeData.setTXS(msgTX.getTransaction( MsgBrokerTransaction.defMSGTX ));
        try {
            TFnNiceTranSpec spec = new TFnNiceTranSpec();
            TFnNiceTranSpec.Criteria cri = spec.createCriteria();

            cri.andAccountNoEqualTo("123")
               .andOfficeCdEqualTo("365");
            spec.or().andAdmisOrgEqualTo("3");
            spec.or().andAtmDealNoEqualTo("b");
            logger.debug("cri getCriteria() size = {}", cri.getAllCriteria().size());
            TFnNiceTranSqlProvider a = new TFnNiceTranSqlProvider();
            logger.debug(a.selectBySpec(spec));
            msgTX.commit(safeData.getTXS());
        }
        catch( Exception e ) {
            msgTX.rollback(safeData.getTXS());
            logger.debug("err msg = {}", e.getLocalizedMessage() );
        }
    }

}
