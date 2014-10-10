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
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicetcm.nibsplus.broker.msg.model.MsgBrokerConf;
import com.nicetcm.nibsplus.orgsend.common.DreamAutoSend;
import com.nicetcm.nibsplus.orgsend.common.DreamAutoSendException;
import com.nicetcm.nibsplus.orgsend.constant.NibsDataSource;
import com.nicetcm.nibsplus.orgsend.model.OrgSendExternalVO;
import com.nicetcm.nibsplus.orgsend.model.OrgSendQryParamVO;
import com.nicetcm.nibsplus.orgsend.service.MsgTransferService;

/**
 *
 * DreamAutoSend의 중요 Class
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
@Service("NDreamAutoSendImpl")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/uat/context-orgsend.xml"})
public class NDreamAutoSendImpl extends NOrgSendImpl
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name= "sqlSessionFactory_OP")
    private SqlSessionFactory sqlSessionFactoryOP;

    @Resource(name= "sqlSessionFactory_IN")
    private SqlSessionFactory sqlSessionFactoryIN;

    @Resource(name= "dreamAutoSend")
    private DreamAutoSend dreamAutoSend;


    //@Resource(name= "MsgRmiTransfer")
    @Resource(name= "MsgLocalTransfer")
    private MsgTransferService msgTransferService;


    private MsgBrokerConf makeHeader(OrgSendExternalVO orgSendExternalVO) throws DreamAutoSendException {

        if(dreamAutoSend == null) {
            logger.debug("dreamAutoSend is null");
        }
        String orgSendMtypeCd = dreamAutoSend.getDreamAutoSendMtype().get(orgSendExternalVO.getQueryName());

        MsgBrokerConf msgBrokerConf = new MsgBrokerConf(orgSendExternalVO.getOrgCd(), dreamAutoSend.getMessageTypeCode(orgSendExternalVO.getQueryName()), orgSendMtypeCd.substring(0, 4), orgSendMtypeCd.substring(4, 8));

        /*************************************************************
            service_gb 코드
            0 : 일괄업무
            1 : 브랜드제휴(CD-VAN)

            2013.01 현재 적용대상기관 : 기업은행
        **************************************************************/
        if(dreamAutoSend.getDreamAutoSendMtypeBrand().contains(orgSendExternalVO.getQueryName())) {
            msgBrokerConf.setServiceGb("1");
        } else {
            msgBrokerConf.setServiceGb("0");
        }

        return msgBrokerConf;
    }

    @Override
    public void execute(OrgSendExternalVO orgSendExternalVO) throws Exception
    {

        SqlSessionFactory sqlSessionFactory = null;

        if(orgSendExternalVO.getNibsDataSource().equals(NibsDataSource.OP)) {
            sqlSessionFactory = sqlSessionFactoryOP;
        } else {
            sqlSessionFactory = sqlSessionFactoryIN;
        }

        Configuration configuration = sqlSessionFactory.getConfiguration();

        Collection<String> mappedStatementNames = configuration.getMappedStatementNames();

        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession(ExecutorType.REUSE, false);

            //logger.info("s " + Thread.currentThread().getName() + " length: " + mappedStatementNames.size());

            OrgSendQryParamVO orgSendQryParamVO = new OrgSendQryParamVO();
            orgSendQryParamVO.setOrgCd(orgSendExternalVO.getOrgCd());

            DreamAutoSendResultHandler orgSendResultHandler = new DreamAutoSendResultHandler(orgSendExternalVO);

            String perfix = orgSendExternalVO.getQueryName() + ".select";

            if(mappedStatementNames.contains(perfix + orgSendExternalVO.getOrgCd())) {
                session.select(perfix + orgSendExternalVO.getOrgCd(), orgSendQryParamVO, orgSendResultHandler);
            } else {
                session.select(perfix + "Default", orgSendQryParamVO, orgSendResultHandler);
            }

            //logger.info("e " + Thread.currentThread().getName());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    private class DreamAutoSendResultHandler implements ResultHandler {

        private OrgSendExternalVO dreamAutoSendExternalVO;

        public DreamAutoSendResultHandler(OrgSendExternalVO dreamAutoSendExternalVO) {
            this.dreamAutoSendExternalVO = dreamAutoSendExternalVO;
        }

        @Override
        public void handleResult(ResultContext context)
        {
            //ResultContext Object에서 Mapper Interface에서 return되는 Object를 얻는다.

            @SuppressWarnings({ "unchecked", "rawtypes" })
            Map<String, Object> resultMap = (HashMap)context.getResultObject();

            MsgBrokerConf msgBrokerConf = null;

            try
            {
                msgBrokerConf = makeHeader(dreamAutoSendExternalVO);

                Map<String, String> msgBodyMap = new HashMap<String, String>();

                for(Map.Entry<String, Object> entry : resultMap.entrySet()) {

                    //branch_cd를 brch_cd로 자동으로 바꿔줌
                    if(entry.getKey().equalsIgnoreCase("branch_cd")) {
                        msgBodyMap.put("brch_cd", String.valueOf(entry.getValue()));
                    } else {
                        msgBodyMap.put(entry.getKey().toLowerCase(), String.valueOf(entry.getValue()));
                    }

                }

                logger.debug(msgBodyMap.toString());
                msgTransferService.send(msgBrokerConf, msgBodyMap, dreamAutoSendExternalVO.getTransferType());

            } catch (DreamAutoSendException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
