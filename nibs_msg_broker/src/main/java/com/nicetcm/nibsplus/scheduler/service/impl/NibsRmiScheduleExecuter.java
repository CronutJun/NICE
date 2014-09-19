package com.nicetcm.nibsplus.scheduler.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicetcm.nibsplus.scheduler.model.JobResultVO;
import com.nicetcm.nibsplus.scheduler.model.JobVO;
import com.nicetcm.nibsplus.scheduler.model.SchedulerVO;
import com.nicetcm.nibsplus.scheduler.service.JobExecuter;
import com.nicetcm.nibsplus.scheduler.service.RemoteScheduleExecuter;
import com.nicetcm.nibsplus.scheduler.service.ScheduleInfoProvider;

/**
 * 스케쥴을 Quartz가 아닌 원격에서 호출
 * <pre>
 * 파라메터로 받은 PK정보를 이용, DB에서 단건 추출후 스케쥴 실행
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("NibsRmiScheduleExecuter")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/scheduler/spring/context-scheduler.xml"})
public class NibsRmiScheduleExecuter implements RemoteScheduleExecuter
{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="ScheduleDBInfoProvider")
    private ScheduleInfoProvider scheduleInfoProvider;

    @SuppressWarnings("unchecked")
    @Override
    public JobResultVO executeJob(JobVO jobVO) throws Exception
    {
        JobResultVO jobResultVO = new JobResultVO(jobVO);

        SchedulerVO schedulerVO = scheduleInfoProvider.selectScheduleByPk(jobVO);

        try
        {
            if(schedulerVO == null) {
                jobResultVO.setResultMsg("해당하는 스케쥴이 존재하지 않습니다.");

            } else {

                ApplicationContext applicationContext = scheduleInfoProvider.getApplicationContext(schedulerVO.getSpringContextXml());

                Class<JobExecuter> jobClass = null;
                jobClass = (Class<JobExecuter>)Class.forName(schedulerVO.getJobClass());

                JobExecuter jobExecuter = jobClass.newInstance();
                jobExecuter.executeJob(applicationContext, schedulerVO);

                jobResultVO.setResultMsg("정상적으로 실행 완료 되었습니다.");
            }

        } catch (Exception e)
        {
            e.printStackTrace();
            jobResultVO.setResultMsg(e.getMessage());
        }

        return jobResultVO;
    }

    @Test
    public void localTest() {
        JobVO jobVO = new JobVO();
        jobVO.setJobGroup("ADD_CASH");
        jobVO.setJobName("003");
        jobVO.setQuartzNodeName("OrgSendService");

        try
        {
            JobResultVO jobResultVO = executeJob(jobVO);

            if(jobResultVO != null) {
                logger.info(jobResultVO.getResultMsg());
            }
        } catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
