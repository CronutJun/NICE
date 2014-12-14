package com.nicetcm.nibsplus.orgsend.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicetcm.nibsplus.orgsend.constant.NibsDataSource;
import com.nicetcm.nibsplus.orgsend.constant.TransferType;
import com.nicetcm.nibsplus.orgsend.model.OrgSendExternalVO;
import com.nicetcm.nibsplus.orgsend.service.NOrgSendService;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/org_send/spring/context-orgsend.xml", "classpath:/org_send/spring/context-orgsend-bean.xml"})
public class OrgSendTest
{

    @Resource(name="NOrgSendImpl")
    NOrgSendService nOrgSendService;

    @Test
    public void all() {

        String orgCd = "003";
        String queryName = "ADD_CASH";
        TransferType transferType = TransferType.ONLY_SEND;
        NibsDataSource nibsDataSource = NibsDataSource.OP;

        OrgSendExternalVO orgSendExternalVO = new OrgSendExternalVO(null, queryName, orgCd, transferType, nibsDataSource);

        try
        {
            nOrgSendService.execute(orgSendExternalVO);
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
