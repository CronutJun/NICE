package com.nicetcm.nibsplus.broker.msg.mapper;

import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicetcm.nibsplus.broker.msg.model.TCmMacNo;
import com.nicetcm.nibsplus.broker.msg.model.TCmMacNoSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtInputCheckList;
import com.nicetcm.nibsplus.broker.msg.model.TCtInputCheckListSpec;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;
import com.nicetcm.nibsplus.broker.msg.services.In03101110Impl.CloseAmt;
import com.nicetcm.nibsplus.broker.msg.services.In03101130Impl.CmCash;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.nicetcm.nibsplus.broker.msg.MsgBrokerAppConfig.class})
public class TMiscMapperTest extends MapperTestSuite
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCmMacNoMapper      cmMacNoMap;

    @Autowired private TCtInputCheckListMapper tCtInputCheckListMapper;

    @Ignore
    @Test
    public void selectCloseAmt() throws Exception {

        CloseAmt closeAmt = null;
        try
        {
            closeAmt = tMiscMapper.selectCloseAmt(getTMacInfo());
        } catch (Exception e)
        {


            logger.error("******************************: " + e.getMessage());
        }
        assertTrue("tobe NO_DATA_FOUND", closeAmt != null);
        //logger.debug("closeAmt: " + closeAmt.toString());
    }

    @Ignore
    @Test
    public void selectCountCmCash() throws Exception {

            CmCash requestCmCash = new CmCash();

            requestCmCash.setInqDate("20140717");
            requestCmCash.setOrgCd("005");
            requestCmCash.setBranchCd("0438");
            requestCmCash.setMacNo("058");

            CmCash resultCmCash = tMiscMapper.selectCountCmCash(requestCmCash);

            logger.debug("requestCmCash: " + requestCmCash.getHisClose());
            logger.debug("resultCmCash: " + resultCmCash.getHisClose());

            assertTrue("tobe NO_DATA_FOUND", resultCmCash != null && resultCmCash.getHisClose() > 0);

        //logger.debug("closeAmt: " + closeAmt.toString());
    }

    //@Test
    public void timestampTest() {

            TCmMacNo macNo = new TCmMacNo();
            macNo.setOrgCd("081");
            macNo.setBranchCd("0842");
            macNo.setMacNo("0058");

            TCmMacNoSpec macNoSpec = new TCmMacNoSpec();
            macNoSpec.createCriteria().andOrgCdEqualTo("081")
                                      .andBranchCdEqualTo("0842")
                                      .andMacNoEqualTo("0058");
            macNo.setErrorDate(new GregorianCalendar().getTime());
            cmMacNoMap.updateBySpec(macNo, macNoSpec);

    }

    @Ignore
    @Test
    public void timestampTest2() {
        TCtInputCheckList updateTCtInputCheckList = new TCtInputCheckList();
        updateTCtInputCheckList.setOwnCheckCnt   (120L);
        updateTCtInputCheckList.setUpdateDate(new GregorianCalendar().getTime());

        TCtInputCheckListSpec tCtInputCheckListSpec = new TCtInputCheckListSpec();
        tCtInputCheckListSpec.createCriteria().andFromDateEqualTo("20140715")
                                                .andToDateEqualTo("20140715")
                                                .andOrgCdEqualTo("005")
                                                .andBranchCdEqualTo("0191")
                                                .andMacNoEqualTo("066");
        //private String fromDate;
        //private String toDate;
        //private String orgCd;
        //private String branchCd;
        //private String macNo;

        tCtInputCheckListMapper.updateBySpecSelective(updateTCtInputCheckList, tCtInputCheckListSpec);

    }

    @Ignore
    @Test
    public void getMadeComCd() {

        TMisc tMisc = tMiscMapper.getMadeComCd("096", "9600", "0202");

        if(tMisc == null) {
            logger.debug("tMisc is null");
        } else {
            logger.debug(tMisc.toString());
        }

    }

    @Test
    public void test() {
        CmCash cmCash = new CmCash();
        List<TCtErrorMng> tCtEDrrorMngList = tMiscMapper.selectCtErrorMng(new TCtErrorMng());

        for(TCtErrorMng tCtErrorMng : tCtEDrrorMngList) {
            logger.info(tCtErrorMng.toString());
        }



    }
}
