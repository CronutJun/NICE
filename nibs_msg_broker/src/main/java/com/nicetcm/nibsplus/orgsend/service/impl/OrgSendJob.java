package com.nicetcm.nibsplus.orgsend.service.impl;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.nicetcm.nibsplus.orgsend.common.OrgSendException;
import com.nicetcm.nibsplus.orgsend.constant.NibsDataSource;
import com.nicetcm.nibsplus.orgsend.constant.TransferType;
import com.nicetcm.nibsplus.orgsend.model.OrgSendExternalVO;
import com.nicetcm.nibsplus.orgsend.service.NOrgSendService;
import com.nicetcm.nibsplus.scheduler.model.SchedulerVO;
import com.nicetcm.nibsplus.scheduler.service.JobExecuter;

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
public class OrgSendJob implements Job, JobExecuter
{

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
        NOrgSendService nOrgSendService = applicationContext.getBean("NOrgSendImpl", NOrgSendService.class);

        String orgCd = String.valueOf(jobDataMap.get("T_ARG2"));
        String queryName = String.valueOf(jobDataMap.get("T_ARG3"));
        TransferType transferType = TransferType.valueOf(String.valueOf(jobDataMap.get("T_ARG1") + "_SEND"));
        NibsDataSource nibsDataSource = NibsDataSource.valueOf(String.valueOf(jobDataMap.get("T_ARG4")));

        OrgSendExternalVO orgSendExternalVO = new OrgSendExternalVO(applicationContext, queryName, orgCd, transferType, nibsDataSource);

        try
        {
            nOrgSendService.execute(orgSendExternalVO);
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    /**
     * 외부(화면WAS)에서 실행시킴
     * <pre>
     *
     * </pre>
     *
     * @param applicationContext
     * @param schedulerVO
     * @throws OrgSendException
     */
    @Override
    public void executeJob(ApplicationContext applicationContext, SchedulerVO schedulerVO) throws Exception
    {
        NOrgSendService nOrgSendService = applicationContext.getBean("NOrgSendImpl", NOrgSendService.class);

        String orgCd = schedulerVO.gettArg2();
        String queryName = schedulerVO.gettArg3();
        TransferType transferType = TransferType.valueOf(schedulerVO.gettArg1() + "_SEND");
        NibsDataSource nibsDataSource = NibsDataSource.valueOf(schedulerVO.gettArg4());

        OrgSendExternalVO orgSendExternalVO = new OrgSendExternalVO(applicationContext, queryName, orgCd, transferType, nibsDataSource);

        nOrgSendService.execute(orgSendExternalVO);
    }


}
