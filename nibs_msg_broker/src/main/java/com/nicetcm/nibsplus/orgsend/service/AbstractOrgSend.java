package com.nicetcm.nibsplus.orgsend.service;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerMain;
import com.nicetcm.nibsplus.orgsend.common.OrgSend;
import com.nicetcm.nibsplus.orgsend.common.OrgSendException;
import com.nicetcm.nibsplus.orgsend.constant.TransferType;
import com.nicetcm.nibsplus.orgsend.model.OrgSendExternalVO;

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
public abstract class AbstractOrgSend implements OrgSendService, Job
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ApplicationContext applicationContext;

    protected DataSource dataSourceOP;
    protected SqlSessionFactory sqlSessionFactoryOP;
    protected QueryParser queryParser;
    protected OrgSend orgSend;
    protected MsgTransferService msgTransferService;

    protected String queryName;
    protected String orgCd;
    protected TransferType transferType;

    static {
        InputStream is = MsgBrokerMain.class.getResourceAsStream("/uat/msg.properties");

        try
        {
            MsgCommon.msgProps.load(is);
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("schema_path: " + MsgCommon.msgProps.getProperty("schema_path"));
    }

    private void injection(JobExecutionContext context) {
        if(applicationContext == null) {
            logger.error("applicationContext is null");
        } else {
            try
            {
                dataSourceOP = applicationContext.getBean("dataSource_OP", DataSource.class);
                sqlSessionFactoryOP = applicationContext.getBean("sqlSessionFactory_OP", SqlSessionFactory.class);
                queryParser = applicationContext.getBean("QryFileParser", QueryParser.class);
                orgSend = applicationContext.getBean("orgSend", OrgSend.class);
                msgTransferService = applicationContext.getBean("MsgLocalTransfer", MsgTransferService.class);
            } catch (BeansException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if(context != null) {
            JobDataMap jobDataMap = context.getMergedJobDataMap();
            logger.info("JobDataMap.orgCd:{}, JobDataMap.queryName:{}, JobDataMap.transferType:{}", jobDataMap.get("orgCd"), jobDataMap.get("queryName"), jobDataMap.get("transferType"));
            orgCd = String.valueOf(jobDataMap.get("orgCd"));
            queryName = String.valueOf(jobDataMap.get("queryName"));
            transferType = TransferType.valueOf(String.valueOf(jobDataMap.get("transferType")));
        }
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        applicationContext = (ApplicationContext) context.getMergedJobDataMap().get("applicationContext");

        injection(context);

        try
        {
            executeJob(context);
        } catch (OrgSendException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void executeExternal(OrgSendExternalVO orgSendExternalVO)
    {
        this.applicationContext = orgSendExternalVO.getApplicationContext();
        this.queryName = orgSendExternalVO.getQueryName();
        this.orgCd = orgSendExternalVO.getOrgCd();
        this.transferType = orgSendExternalVO.getTransferType();

        injection(null);

        try
        {
            executeJob(null);
        } catch (OrgSendException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    protected abstract void executeJob(JobExecutionContext context) throws OrgSendException;

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