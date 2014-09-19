package com.nicetcm.nibsplus.orgsend.common;

import java.util.List;
import java.util.Map;

import com.nicetcm.nibsplus.orgsend.constant.ExceptionType;

/**
 * OrgSend 관련 상수 정의
 * <pre>
 * 값은 /org_send/spring/context-orgsend-bean.xml 에서 주입
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public class OrgSend
{

    private Map<String, String> orgSendMtype;

    private Map<String, String> orgSendFtype;

    private List<String> orgSendMtypeBrand;

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
