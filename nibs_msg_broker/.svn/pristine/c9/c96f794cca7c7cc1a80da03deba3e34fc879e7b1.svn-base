package com.nicetcm.nibsplus.orgsend.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.runner.RunWith;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("OrgSendImpl")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/uat/context-orgsend.xml"})
public class OrgSendImpl extends AbstractOrgSend
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void executeJob(JobExecutionContext context)
    {
        logger.info("Job Start [QUERY-NAME: {}, ORG_CD: {}] {}", queryName, orgCd, context);

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

            for(int i = 0; i < rsmd.getColumnCount(); i++) {
                //logger.debug("rs-column {}: {}", i, rsmd.getColumnName(i + 1));
            }

            for(int i = 0; rs.next(); i++) {
                //logger.debug("rs-test: {}", rs.getString("aabbcc"));

            }

            logger.debug("transferType: {}", transferType);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            try {
                try {
                    if (rs != null)     rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (ps != null)     ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (conn != null)    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//end method executeJob

}
