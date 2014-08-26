package com.nicetcm.nibsplus.orgsend.service;

import javax.sql.DataSource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.nicetcm.nibsplus.orgsend.constant.TransferType;
import com.nicetcm.nibsplus.orgsend.service.model.OrgSendExternalVO;

/**
 *
 * 여기에 클래스(한글)명.
 * <pre>
 * Usage : OrgAutoSend [org_cd] [msg_type]
 *
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public abstract class AbstractOrgSend extends QuartzJobBean implements OrgSendService
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ApplicationContext applicationContext;

    protected DataSource dataSourceOP;
    protected QueryParser queryParser;

    protected String queryName;
    protected String orgCd;

    protected TransferType transferType;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");

        if(applicationContext == null) {
            logger.error("applicationContext is null");
        } else {
            try
            {
                dataSourceOP = applicationContext.getBean("dataSource_OP", DataSource.class);
                queryParser = applicationContext.getBean("QryFileParser", QueryParser.class);
            } catch (BeansException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            executeJob(context);
        }
    }

    @Override
    public void executeExternal(OrgSendExternalVO orgSendExternalVO)
    {
        this.applicationContext = orgSendExternalVO.getApplicationContext();
        this.queryName = orgSendExternalVO.getQueryName();
        this.orgCd = orgSendExternalVO.getOrgCd();

        if(applicationContext == null) {
            logger.error("applicationContext is null");
        } else {
            try
            {
                dataSourceOP = applicationContext.getBean("dataSource_OP", DataSource.class);
                queryParser = applicationContext.getBean("QryFileParser", QueryParser.class);
            } catch (BeansException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            executeJob(null);
        }
    }

    protected abstract void executeJob(JobExecutionContext context);

    /**
     * @param orgCd the orgCd to set
     */
    public void setOrgCd(String orgCd)
    {
        this.orgCd = orgCd;
    }

    /**
     * @param queryName the queryName to set
     */
    public void setQueryName(String queryName)
    {
        this.queryName = queryName;
    }

    /**
     * @param transferType the transferType to set
     */
    public void setTransferType(TransferType transferType)
    {
        this.transferType = transferType;
    }
}
