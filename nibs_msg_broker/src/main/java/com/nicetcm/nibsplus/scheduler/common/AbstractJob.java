package com.nicetcm.nibsplus.scheduler.common;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicetcm.nibsplus.orgsend.common.MsgLogger;
import com.nicetcm.nibsplus.scheduler.model.SchedulerVO;

public abstract class AbstractJob implements Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		String userYn = null;
		
		JobDataMap jobDataMap = context.getMergedJobDataMap();
		
		SchedulerVO schedulerVO = (SchedulerVO)jobDataMap.get("SchedulerVO");
		
		String quartzNodeName = String.valueOf(schedulerVO.getQuartzNodeName());
		String jobGroup = String.valueOf(schedulerVO.getJobGroup());
		String jobName = String.valueOf(schedulerVO.getJobName());
		
		AbstractJobVO abstractJobVO = new AbstractJobVO();
		
		abstractJobVO.setQuartzNodeName(quartzNodeName);
		abstractJobVO.setJobGroup(jobGroup);
		abstractJobVO.setJobName(jobName);
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/job/spring/context-job.xml");
		
		SqlSessionFactory sessionFactory  = (SqlSessionFactory) applicationContext.getBean("sqlSessionFactory_OP_JOB");
		MsgLogger msgLogger  = (MsgLogger) applicationContext.getBean("msgLogger");
		
     	SqlSession session = sessionFactory.openSession(ExecutorType.REUSE, false);
     	
		try{
			userYn = session.selectOne("ABSTRACT_JOB.selectJobUseYn", abstractJobVO);
		}finally{
			if (session != null) session.close();
		}
     	
     	if(userYn != null && userYn.equals("Y")){
     		msgLogger.info(schedulerVO.getJobGroup(), schedulerVO.getJobName(), "Y 샐행");
     		execte(context);
     	} else {
     		msgLogger.info(schedulerVO.getJobGroup(), schedulerVO.getJobName(), "N 실행 안됨");
     	}
	}
	
	public abstract void execte(JobExecutionContext context);

}
