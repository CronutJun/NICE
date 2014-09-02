package com.nicetcm.nibsplus.orgsend.model;

import org.springframework.context.ApplicationContext;

import com.nicetcm.nibsplus.orgsend.constant.TransferType;

public class OrgSendExternalVO
{
    private ApplicationContext applicationContext;

    private String queryName;
    private String orgCd;
    private TransferType transferType;

    public OrgSendExternalVO() {
        super();
    }

    /**
     * <pre>
     * 여기에 Constructors 설명을 삽입하십시오.
     * </pre>
     *
     * @param applicationContext
     * @param queryName
     * @param orgCd
     */
    public OrgSendExternalVO(ApplicationContext applicationContext, String queryName, String orgCd, TransferType transferType)
    {
        super();
        this.applicationContext = applicationContext;
        this.queryName = queryName;
        this.orgCd = orgCd;
        this.transferType = transferType;
    }

    /**
     * @return the applicationContext
     */
    public ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }
    /**
     * @param applicationContext the applicationContext to set
     */
    public void setApplicationContext(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }
    /**
     * @return the queryName
     */
    public String getQueryName()
    {
        return queryName;
    }
    /**
     * @param queryName the queryName to set
     */
    public void setQueryName(String queryName)
    {
        this.queryName = queryName;
    }
    /**
     * @return the orgCd
     */
    public String getOrgCd()
    {
        return orgCd;
    }
    /**
     * @param orgCd the orgCd to set
     */
    public void setOrgCd(String orgCd)
    {
        this.orgCd = orgCd;
    }

    /**
     * @return the transferType
     */
    public TransferType getTransferType()
    {
        return transferType;
    }

    /**
     * @param transferType the transferType to set
     */
    public void setTransferType(TransferType transferType)
    {
        this.transferType = transferType;
    }
}
