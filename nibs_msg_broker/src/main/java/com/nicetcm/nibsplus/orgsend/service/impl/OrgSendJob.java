package com.nicetcm.nibsplus.orgsend.service.impl;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.nicetcm.nibsplus.orgsend.common.MsgLogger;
import com.nicetcm.nibsplus.orgsend.constant.NibsDataSource;
import com.nicetcm.nibsplus.orgsend.constant.TransferType;
import com.nicetcm.nibsplus.orgsend.model.OrgSendExternalVO;
import com.nicetcm.nibsplus.orgsend.rmi.MsgBrokerCallAgent;
import com.nicetcm.nibsplus.orgsend.service.NOrgSendService;

/**
 *
 * 기관전송 프로그램
 * OrgSendJob (AS-IS OrgAutoSend.pc, OrgOnlySend.pc)
 * Quartz와 외부RMI에서 호출 가능
 * <pre>
 *
 * Class Annotation
 * PersistJobDataAfterExecution: JobDataMap을 유지시켜주는 StatefulJob
 * DisallowConcurrentExecution: 동시실행 방지
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@DisallowConcurrentExecution
public class OrgSendJob implements Job {

	private Logger errorLogger = Logger.getLogger(this.getClass());
	
    /**
     * Quartz에서 실행시킴
     * <pre>
     *
     * </pre>
     *
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        JobDataMap jobDataMap = context.getMergedJobDataMap();

        ApplicationContext applicationContext = (ApplicationContext)jobDataMap.get("applicationContext");
        // Map<String, String> mtype = ((OrgSend)applicationContext.getBean("orgSend", OrgSend.class)).getOrgSendMtype();
    	MsgLogger msgLogger = (MsgLogger)applicationContext.getBean("msgLogger");
        
        NOrgSendService nOrgSendService = applicationContext.getBean("NOrgSendImpl", NOrgSendService.class);

        if (MsgBrokerCallAgent.msgBrokerConfig == null) {
        	MsgBrokerCallAgent.msgBrokerConfig = (Properties)applicationContext.getBean("msgBrokerConfig");
        }

        String orgCd = String.valueOf(jobDataMap.get("T_ARG2"));
        String queryName = String.valueOf(jobDataMap.get("T_ARG3"));
        TransferType transferType = TransferType.valueOf(String.valueOf(jobDataMap.get("T_ARG1") + "_SEND"));
        NibsDataSource nibsDataSource = NibsDataSource.valueOf(String.valueOf(jobDataMap.get("T_ARG4")));

        OrgSendExternalVO orgSendExternalVO = new OrgSendExternalVO(applicationContext, queryName, orgCd, transferType, nibsDataSource);

        try {
            nOrgSendService.execute(orgSendExternalVO);
        } catch (Exception e) {
        	msgLogger.info(queryName, orgCd, String.format("%s", e.getMessage()));
        	errorLogger.error(String.format("%s %s", Thread.currentThread().getName(), e.getMessage()), e.getCause());
        	e.printStackTrace(System.err);
        }
    }
    
}
