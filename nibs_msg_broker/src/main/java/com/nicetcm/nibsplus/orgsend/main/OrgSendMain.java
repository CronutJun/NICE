package com.nicetcm.nibsplus.orgsend.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicetcm.nibsplus.orgsend.service.OrgSendService;
import com.nicetcm.nibsplus.orgsend.service.model.OrgSendExternalVO;
import static com.nicetcm.nibsplus.orgsend.common.OrgSend.SpringContextXml;
public class OrgSendMain
{
    public static void main(String[] args) {

        if(args != null && args.length == 2) {

        } else {
            System.out.println("Usage : OrgSendMain [org_cd] [msg_type]");
            System.exit(-1);
        }

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(SpringContextXml.ORG_SEND);

        OrgSendService orgSendService = applicationContext.getBean("OrgSendImpl", OrgSendService.class);

        orgSendService.executeExternal(new OrgSendExternalVO(applicationContext, args[1], args[0]));

    }
}
