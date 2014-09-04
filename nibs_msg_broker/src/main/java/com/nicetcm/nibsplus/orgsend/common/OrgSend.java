package com.nicetcm.nibsplus.orgsend.common;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.nicetcm.nibsplus.orgsend.constant.ExceptionType;

public class OrgSend
{

    private Map<String, String> orgSendMtype;

    private Map<String, String> orgSendFtype;

    private List<String> orgSendMtypeBrand;

    public static class SpringContextXml {
        public static String ORG_SEND = "classpath:org_send/spring/context-orgsend.xml";
        public static String ORG_SEND_BEAN = "classpath:org_send/spring/context-orgsend-bean.xml";
        public static List<String> QUARTZ;

        static {
            QUARTZ = Arrays.asList(
                ORG_SEND,
                ORG_SEND_BEAN,
                "classpath:org_send/quartz/em/ARR_EST.xml",
                "classpath:org_send/quartz/context-orgsend-quartz.xml"
            );
        }
    }

    public String getMessageTypeCode(String queryName) throws OrgSendException {
        if(orgSendMtype.containsKey(queryName)) {
            String messageTypeCode = orgSendFtype.get(orgSendMtype.get(queryName).substring(0, 4));

            if(messageTypeCode == null) {
                throw new OrgSendException(ExceptionType.VM_STOP, "context-orgsend-bean.xml orgSendFtype에 정의된 key=value가 없습니다.");
            } else {
                return messageTypeCode;
            }
        } else {
            throw new OrgSendException(ExceptionType.VM_STOP, "context-orgsend-bean.xml orgSendMtype에 정의된 key=value가 없습니다.");
        }
    }

    /**
     * @return the orgSendMtype
     */
    public Map<String, String> getOrgSendMtype()
    {
        return orgSendMtype;
    }

    /**
     * @param orgSendMtype the orgSendMtype to set
     */
    public void setOrgSendMtype(Map<String, String> orgSendMtype)
    {
        this.orgSendMtype = orgSendMtype;
    }

    /**
     * @return the orgSendFtype
     */
    public Map<String, String> getOrgSendFtype()
    {
        return orgSendFtype;
    }

    /**
     * @param orgSendFtype the orgSendFtype to set
     */
    public void setOrgSendFtype(Map<String, String> orgSendFtype)
    {
        this.orgSendFtype = orgSendFtype;
    }

    /**
     * @return the orgSendMtypeBrand
     */
    public List<String> getOrgSendMtypeBrand()
    {
        return orgSendMtypeBrand;
    }

    /**
     * @param orgSendMtypeBrand the orgSendMtypeBrand to set
     */
    public void setOrgSendMtypeBrand(List<String> orgSendMtypeBrand)
    {
        this.orgSendMtypeBrand = orgSendMtypeBrand;
    }
}