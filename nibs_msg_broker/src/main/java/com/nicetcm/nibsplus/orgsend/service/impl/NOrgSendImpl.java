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
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicetcm.nibsplus.broker.msg.model.MsgBrokerConf;
import com.nicetcm.nibsplus.orgsend.common.OrgSend;
import com.nicetcm.nibsplus.orgsend.common.OrgSendException;
import com.nicetcm.nibsplus.orgsend.constant.NibsDataSource;
import com.nicetcm.nibsplus.orgsend.model.OrgSendExternalVO;
import com.nicetcm.nibsplus.orgsend.model.OrgSendQryParamVO;
import com.nicetcm.nibsplus.orgsend.service.MsgTransferService;
import com.nicetcm.nibsplus.orgsend.service.NOrgSendService;

/**
 *
 * 여기에 클래스(한글)명.
 * <pre>
 * Usage : OrgAutoSend [org_cd] [msg_type]
 *                     g_szOrgCd szMsgType
 *
 * g_class_idx 설정: ORGSEND_MTYPE_NM의 순번과 동일
 * g_brand_idx 설정: 브랜드제휴 전문 구분 (HEADER의 SERVICE_GB에 값('1')을 넣어주는 전문목록, 일괄업무전문과 브랜드제휴전문을 구분하기 위해 설정함)
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
public class NOrgSendImpl implements NOrgSendService
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name= "sqlSessionFactory_OP")
    private SqlSessionFactory sqlSessionFactoryOP;

    @Resource(name= "sqlSessionFactory_IN")
    private SqlSessionFactory sqlSessionFactoryIN;

    @Resource(name= "orgSend")
    private OrgSend orgSend;


    //@Resource(name= "MsgRmiTransfer")
    @Resource(name= "MsgLocalTransfer")
    private MsgTransferService msgTransferService;


    private MsgBrokerConf makeHeader(OrgSendExternalVO orgSendExternalVO) throws OrgSendException {

        if(orgSend == null) {
            logger.debug("orgSend is null");
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
    public void execute(OrgSendExternalVO orgSendExternalVO) throws OrgSendException
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

            OrgSendResultHandler orgSendResultHandler = new OrgSendResultHandler(orgSendExternalVO);

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


    private class OrgSendResultHandler implements ResultHandler {

        private OrgSendExternalVO orgSendExternalVO;

        public OrgSendResultHandler(OrgSendExternalVO orgSendExternalVO) {
            this.orgSendExternalVO = orgSendExternalVO;
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
                msgBrokerConf = makeHeader(orgSendExternalVO);

                Map<String, String> msgBodyMap = new HashMap<String, String>();

                for(Map.Entry<String, Object> entry : resultMap.entrySet()) {

                    if(entry.getKey().equalsIgnoreCase("branch_cd")) {
                        msgBodyMap.put("brch_cd", String.valueOf(entry.getValue()));
                    } else {
                        msgBodyMap.put(entry.getKey().toLowerCase(), String.valueOf(entry.getValue()));
                    }

                }

                logger.debug(msgBodyMap.toString());
                msgTransferService.send(msgBrokerConf, msgBodyMap, orgSendExternalVO.getTransferType());

            } catch (OrgSendException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
