package com.nicetcm.nibsplus.orgsend.common;

import java.util.List;
import java.util.Map;

import com.nicetcm.nibsplus.orgsend.constant.ExceptionType;

/**
 * DreamAutoSend 관련 상수 정의
 * <pre>
 * 값은 /org_send/spring/context-orgsend-bean.xml 에서 주입
 * </pre>
 *
 * @author 박상철
 * @version 1.0
 * @see
 */
public class DreamAutoSend
{

    private Map<String, String> dreamAutoSendMtype;

    private Map<String, String> dreamAutoSendFtype;

    private List<String> dreamAutoSendMtypeBrand;

    public String getMessageTypeCode(String queryName) throws DreamAutoSendException {
        if(dreamAutoSendMtype.containsKey(queryName)) {
            String messageTypeCode = dreamAutoSendFtype.get(dreamAutoSendMtype.get(queryName).substring(0, 4));

            if(messageTypeCode == null) {
                throw new DreamAutoSendException(ExceptionType.VM_STOP, "context-orgsend-bean.xml dreamAutoSendFtype에 정의된 key=value가 없습니다.");
            } else {
                return messageTypeCode;
            }
        } else {
            throw new DreamAutoSendException(ExceptionType.VM_STOP, "context-orgsend-bean.xml dreamAutoSendMtype에 정의된 key=value가 없습니다.");
        }
    }

    /**
     * @return the dreamAutoSendMtype
     */
    public Map<String, String> getDreamAutoSendMtype()
    {
        return dreamAutoSendMtype;
    }

    /**
     * @param dreamAutoSendMtype the dreamAutoSendMtype to set
     */
    public void setDreamAutoSendMtype(Map<String, String> dreamAutoSendMtype)
    {
        this.dreamAutoSendMtype = dreamAutoSendMtype;
    }

    /**
     * @return the dreamAutoSendFtype
     */
    public Map<String, String> getDreamAutoSendFtype()
    {
        return dreamAutoSendFtype;
    }

    /**
     * @param dreamAutoSendFtype the dreamAutoSendFtype to set
     */
    public void setDreamAutoSendFtype(Map<String, String> dreamAutoSendFtype)
    {
        this.dreamAutoSendFtype = dreamAutoSendFtype;
    }

    /**
     * @return the dreamAutoSendMtypeBrand
     */
    public List<String> getDreamAutoSendMtypeBrand()
    {
        return dreamAutoSendMtypeBrand;
    }

    /**
     * @param dreamAutoSendMtypeBrand the dreamAutoSendMtypeBrand to set
     */
    public void setDreamAutoSendMtypeBrand(List<String> dreamAutoSendMtypeBrand)
    {
        this.dreamAutoSendMtypeBrand = dreamAutoSendMtypeBrand;
    }
}
