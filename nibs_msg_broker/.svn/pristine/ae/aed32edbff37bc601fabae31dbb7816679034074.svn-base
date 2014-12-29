package com.nicetcm.nibsplus.orgsend.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicetcm.nibsplus.broker.msg.model.MsgBrokerConf;
import com.nicetcm.nibsplus.orgsend.common.MsgLogger;
import com.nicetcm.nibsplus.orgsend.common.OrgSend;
import com.nicetcm.nibsplus.orgsend.common.OrgSendException;
import com.nicetcm.nibsplus.orgsend.model.OrgSendExternalVO;
import com.nicetcm.nibsplus.orgsend.model.OrgSendQryParamVO;
import com.nicetcm.nibsplus.orgsend.service.MsgTransferService;
import com.nicetcm.nibsplus.orgsend.service.NOrgSendService;

/**
 *
 * OrgSend의 중요 Class
 * <pre>
 * msg_type, org_cd를 조건으로 쿼리XML에서 SQL을 가져옴
 * SQL을 실행하여 결과값을 MsgTransferService를 이용하여 MsgBroker모듈과 RMI통신
 *
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("NOrgSendImpl")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/uat/context-orgsend.xml"})
public class NOrgSendImpl implements NOrgSendService {

	/*@Autowired
	private MsgLogger msgLogger;*/
	private Logger errorLogger = Logger.getLogger(this.getClass());

    @Resource(name= "sqlSessionFactory_OP")
    private SqlSessionFactory sqlSessionFactory;

    @Resource(name= "orgSend")
    private OrgSend orgSend;

	@Autowired
	private MsgLogger msgLogger;

    @Resource(name= "MsgRmiTransfer")
    //@Resource(name= "MsgLocalTransfer")
    private MsgTransferService msgTransferService;


    private MsgBrokerConf makeHeader(OrgSendExternalVO orgSendExternalVO) throws OrgSendException {

        if(orgSend == null) {
        	errorLogger.error("orgSend is null");
        }
        String orgSendMtypeCd = orgSend.getOrgSendMtype().get(orgSendExternalVO.getQueryName());

        MsgBrokerConf msgBrokerConf = new MsgBrokerConf(orgSendExternalVO.getOrgCd(), orgSend.getMessageTypeCode(orgSendExternalVO.getQueryName()), orgSendMtypeCd.substring(0, 4), orgSendMtypeCd.substring(4, 8));

        /*************************************************************
            service_gb 코드
            0 : 일괄업무
            1 : 브랜드제휴(CD-VAN)

            2013.01 현재 적용대상기관 : 기업은행
        **************************************************************/
        if(orgSend.getOrgSendMtypeBrand().contains(orgSendExternalVO.getQueryName())) {
            msgBrokerConf.setServiceGb("1");
        } else {
            msgBrokerConf.setServiceGb("0");
        }

        return msgBrokerConf;
    }

    @Override
    public void execute(OrgSendExternalVO orgSendExternalVO) throws Exception {
    	/*
        SqlSessionFactory sqlSessionFactory = null;

        if(orgSendExternalVO.getNibsDataSource().equals(NibsDataSource.OP)) {
            sqlSessionFactory = sqlSessionFactoryOP;
        } else {
            sqlSessionFactory = sqlSessionFactoryIN;
        }
        */
        try {
            Configuration configuration = sqlSessionFactory.getConfiguration();
            Collection<String> mappedStatementNames = configuration.getMappedStatementNames();
        	SqlSession session = sqlSessionFactory.openSession(ExecutorType.REUSE, false);

        	try {
	            OrgSendQryParamVO orgSendQryParamVO = new OrgSendQryParamVO();
	            orgSendQryParamVO.setOrgCd(orgSendExternalVO.getOrgCd());
	
	            OrgSendResultHandler orgSendResultHandler = new OrgSendResultHandler(orgSendExternalVO);
	
	            String perfix = orgSendExternalVO.getQueryName() + ".select";
	
	            if(mappedStatementNames.contains(perfix + orgSendExternalVO.getOrgCd())) {
	            	if(mappedStatementNames.contains(perfix + orgSendExternalVO.getOrgCd() + "_count")) {
	            		try {
	            			int count = session.selectOne(perfix + orgSendExternalVO.getOrgCd() + "_count", orgSendQryParamVO);
	            			msgLogger.info(orgSendExternalVO.getQueryName(), orgSendExternalVO.getOrgCd(), String.format("처리대상 %d 건", count));
	            			
	            			Thread.currentThread().setName(perfix + orgSendExternalVO.getOrgCd() + "_" + count);
	            		} catch(Exception e) {}
	            	}
	            	
	                session.select(perfix + orgSendExternalVO.getOrgCd(), orgSendQryParamVO, orgSendResultHandler);
	            } else {
	                session.select(perfix + "Default", orgSendQryParamVO, orgSendResultHandler);
	            }
        	} finally {
        		session.close();
        	}
        } catch (Exception e) {
        	msgLogger.info(orgSendExternalVO.getQueryName(), orgSendExternalVO.getOrgCd(), e.getMessage());
        	errorLogger.error(e.getMessage(), e.getCause());
        	e.printStackTrace(System.err);
        }
    }


    private class OrgSendResultHandler implements ResultHandler {

        private OrgSendExternalVO orgSendExternalVO;

        public OrgSendResultHandler(OrgSendExternalVO orgSendExternalVO) {
            this.orgSendExternalVO = orgSendExternalVO;
        }

        @Override
        public void handleResult(ResultContext context) {
            //ResultContext Object에서 Mapper Interface에서 return되는 Object를 얻는다.

            @SuppressWarnings({ "unchecked", "rawtypes" })
            Map<String, Object> resultMap = (HashMap)context.getResultObject();

            MsgBrokerConf msgBrokerConf = null;
            Map<String, String> msgBodyMap = new HashMap<String, String>();

            try {
                msgBrokerConf = makeHeader(orgSendExternalVO);

                for(Map.Entry<String, Object> entry : resultMap.entrySet()) {

                    //branch_cd를 brch_cd로 자동으로 바꿔줌
                    if(entry.getKey().equalsIgnoreCase("branch_cd")) {
                        msgBodyMap.put("brch_cd", String.valueOf(entry.getValue()));
                    } else {
                        msgBodyMap.put(entry.getKey().toLowerCase(), String.valueOf(entry.getValue()));
                    }

                }

                msgTransferService.send(msgBrokerConf, msgBodyMap, orgSendExternalVO);
            } catch (OrgSendException e) {
            	errorLogger.error(String.format("%s %s\n%s", Thread.currentThread().getName(), e.getMessage(), msgBodyMap.toString()), e.getCause());
            	e.printStackTrace(System.err);
            }
        }

    }
}
