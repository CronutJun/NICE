package com.nicetcm.nibsplus.orgsend.service.impl;

import java.util.Collection;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.junit.runner.RunWith;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicetcm.nibsplus.broker.msg.model.MsgBrokerConf;
import com.nicetcm.nibsplus.orgsend.common.OrgSendException;
import com.nicetcm.nibsplus.orgsend.model.OrgSendQryParamVO;
import com.nicetcm.nibsplus.orgsend.service.AbstractOrgSend;

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
 * Class Annotation
 * PersistJobDataAfterExecution: JobDataMap을 유지시켜주는 StatefulJob
 * DisallowConcurrentExecution: 동시실행 방지
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("OrgSendImpl")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/uat/context-orgsend.xml"})
@DisallowConcurrentExecution
@Deprecated
public class OrgSendImpl extends AbstractOrgSend
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());



    private MsgBrokerConf makeHeader() throws OrgSendException {

        if(orgSend == null) {
            logger.debug("orgSend is null");
        }
        String orgSendMtypeCd = orgSend.getOrgSendMtype().get(queryName);

        MsgBrokerConf msgBrokerConf = new MsgBrokerConf(orgCd, orgSend.getMessageTypeCode(queryName), orgSendMtypeCd.substring(0, 4), orgSendMtypeCd.substring(4, 8));

        /*************************************************************
            service_gb 코드
            0 : 일괄업무
            1 : 브랜드제휴(CD-VAN)

            2013.01 현재 적용대상기관 : 기업은행
        **************************************************************/
        if(orgSend.getOrgSendMtypeBrand().contains(queryName)) {
            msgBrokerConf.setServiceGb("1");
        } else {
            msgBrokerConf.setServiceGb("0");
        }

        return msgBrokerConf;
    }

    @Override
    protected void executeJob(JobExecutionContext context) throws OrgSendException
    {
/*
        logger.info("context.getMergedJobDataMap(): {}", context.getMergedJobDataMap().getString("AA"));

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        if(context.getMergedJobDataMap().getString("AA") == null) {
            jobDataMap.put("AA", "0");
        } else {
            jobDataMap.put("AA", context.getMergedJobDataMap().getString("AA") + 1);
        }
*/
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        //if(true) {
        //    return;
        //}

        Configuration configuration = sqlSessionFactoryOP.getConfiguration();

        Collection<String> mappedStatementNames = configuration.getMappedStatementNames();

        SqlSession session = null;
        try {
            session = sqlSessionFactoryOP.openSession(false);

            for(String statementNames : mappedStatementNames) {
                logger.info("statementNames: " + statementNames);
                OrgSendQryParamVO orgSendQryParamVO = new OrgSendQryParamVO();
                orgSendQryParamVO.setOrgCd(orgCd);
                session.selectOne(statementNames, orgSendQryParamVO);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

/*
    @Deprecated
    protected void executeJobFromQryFile(JobExecutionContext context) throws OrgSendException
    {
        logger.info("Job Start [QUERY-NAME: {}, ORG_CD: {}, JobExecutionContext: {}]", queryName, orgCd, context);

        //logger.debug("orgSend.getOrgSendMtype(): {}", orgSend.getOrgSendMtype());

        MsgBrokerConf msgBrokerConf = makeHeader();

        Connection conn = DataSourceUtils.getConnection(dataSourceOP);

        try
        {
            conn.setAutoCommit(false);
        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }

        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        try {
            ps = conn.prepareStatement(queryParser.getSql(queryName, orgCd));
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();

            for(int i = 0; rs.next(); i++) {

                Map<String, String> msgBodyMap = new HashMap<String, String>();

                for(int j = 0; j < rsmd.getColumnCount(); j++) {
                    logger.debug("rs-column {}: {}", i, rsmd.getColumnName(j + 1));
                    msgBodyMap.put(rsmd.getColumnName(j + 1).toLowerCase(), rs.getString(rsmd.getColumnName(j + 1)));
                }

                msgTransferService.send(msgBrokerConf, msgBodyMap, transferType);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            try {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//end method executeJob
*/
}
