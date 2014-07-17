package com.nicetcm.nibsplus.broker.ams;

import javax.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;


@Configuration
@MapperScan("com.nicetcm.nibsplus.broker.ams.mapper")
public class AMSBrokerAppConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerAppConfig.class);
    
    @Autowired
    private DataSource ds;
    
    @Autowired
    private SqlSessionFactory factory;

    @Bean(name="dataSource")
    public BasicDataSource dataSource() {
        
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
        ds.setUrl(MsgCommon.msgProps.getProperty("db_url"));
        ds.setUsername(MsgCommon.msgProps.getProperty("db_user"));
        ds.setPassword(MsgCommon.msgProps.getProperty("db_pass"));
        ds.setInitialSize(2);
        ds.setMaxActive(5);
        return ds;
        
    }
    
    @Bean(name="transactionManager")
    public DataSourceTransactionManager transactionManager() {
        
        DataSourceTransactionManager tx = new DataSourceTransactionManager();
        
        tx.setDataSource(ds);
        
        return tx;
    }

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory() {
        
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();

        factory.setDataSource(ds);
        factory.setConfigLocation(AMSBrokerSpringMain.sprCtx.getResource("classpath:ams_db_conf.xml"));
        
        return factory;
    }
    
    @Bean(name="sqlSession")
    public SqlSessionTemplate sqlSessionTemplate() {
        
        return new SqlSessionTemplate(factory);
        
    }
}
