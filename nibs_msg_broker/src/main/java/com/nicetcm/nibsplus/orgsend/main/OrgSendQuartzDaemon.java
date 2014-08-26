package com.nicetcm.nibsplus.orgsend.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicetcm.nibsplus.orgsend.common.OrgSend.SpringContextXml;

public class OrgSendQuartzDaemon
{
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(SpringContextXml.ORG_SEND, SpringContextXml.ORG_SEND_QUARTZ);
    }
}
