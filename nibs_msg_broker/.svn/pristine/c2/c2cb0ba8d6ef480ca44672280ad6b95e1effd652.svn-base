package com.nicetcm.nibsplus.filemng.main;


import java.util.Properties;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.nicetcm.nibsplus.filemng.model.TransferVO;
import com.nicetcm.nibsplus.scheduler.common.AbstractJob;

public class SampleJob extends AbstractJob  {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private Logger errorLogger = Logger.getLogger("FilemngERROR");
	
	@Override
	public void execte(JobExecutionContext context) {
        try {
    		
        	//T_CM_SCHEDULE 테이블 SPRING_CONTEXT_XML 필드에 등록된 spring xml 파일을 일어온다.
        	//db 커넥션 , 환경 변수, 업무에 등록된 빈
        	ApplicationContext applicationContext = 
        			(ApplicationContext)context.getMergedJobDataMap().get("applicationContext");
    		
        	//context-filemng.xml에 등록된 config 빈을 생성하여 사용
        	Properties config = applicationContext.getBean("config", Properties.class);

            TransferVO transferVO = new TransferVO();
            transferVO.setHost(config.getProperty("host.host"));
            
            //db 사용
    		//context-filemng.xml에 id 가 "sqlSessionFactory_OP"로 등록된  SqlSessionFactory 빈 클래스를 생성
    		SqlSessionFactory sessionFactory  = 
    				(SqlSessionFactory) applicationContext.getBean("sqlSessionFactory_OP");
    		
         	SqlSession session = sessionFactory.openSession(ExecutorType.REUSE, false);
         	
         	//쿼리 실행(namespace 와 동일하게 적어준다.)
         	String userYn = session.selectOne("SampleMapper.selectSample");
            
            
            //업무 로직 작성
            
        } catch(Exception e) {
        	errorLogger.error(e.getMessage(), e.getCause());
        }
	}
}