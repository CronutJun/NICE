package com.nicetcm.nibsplus.orgsend.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicetcm.nibsplus.orgsend.constant.TransferType;
import com.nicetcm.nibsplus.orgsend.model.OrgSendExternalVO;
import com.nicetcm.nibsplus.orgsend.service.OrgSendService;

import static com.nicetcm.nibsplus.orgsend.common.OrgSend.SpringContextXml;
public class OrgSendMain
{
    /**
     * OrgSend를 직접실행
     * <pre>
     * Usage : OrgSendMain [org_cd] [msg_type] [transfer_type]
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {

        if(args != null && args.length == 2) {

        } else {
            //System.out.println("Usage : OrgSendMain [org_cd] [msg_type] [transfer_type]");
            //System.exit(-1);
        }
        args = new String[3];
        args[0] = new String("088");
        args[1] = new String("ARRIVAL");
        args[2] = new String("ONLY_SEND");

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(SpringContextXml.ORG_SEND, SpringContextXml.ORG_SEND_BEAN);

        OrgSendService orgSendService = applicationContext.getBean("OrgSendImpl", OrgSendService.class);

        orgSendService.executeExternal(new OrgSendExternalVO(applicationContext, args[1], args[0], TransferType.valueOf(args[2])));

    }
}
