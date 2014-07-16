package com.nicetcm.nibsplus.broker.msg;

import javax.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.session.SqlSessionFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;


@Configuration
@MapperScan("com.nicetcm.nibsplus.broker.msg.mapper")
public class MsgBrokerAppConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerAppConfig.class);
    
    @Autowired private DataSource ds;
    @Autowired private SqlSessionFactory factory;

    @Bean(name="dataSource")
    public BasicDataSource dataSource() {
        
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
        ds.setUrl(MsgCommon.msgProps.getProperty("db_url"));
        ds.setUsername(MsgCommon.msgProps.getProperty("db_user"));
        ds.setPassword(MsgCommon.msgProps.getProperty("db_pass"));
        ds.setInitialSize(2);
        ds.setMaxActive(5);
        
        this.ds = ds;
        
        return ds;
        
    }
    
    @Bean(name="transactionManager")
    @DependsOn("dataSource")
    public DataSourceTransactionManager transactionManager() {
        
        DataSourceTransactionManager tx = new DataSourceTransactionManager();
        
        tx.setDataSource(ds);
        
        return tx;
    }

    @Bean(name="sqlSessionFactory")
    @DependsOn("dataSource")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();

        factory.setDataSource(ds);
        factory.setConfigLocation(MsgBrokerSpringMain.sprCtx.getResource("classpath:msg_db_conf.xml"));
        
        this.factory = factory.getObject();
        
        return factory.getObject();
    }
    
    @Bean(name="sqlSession")
    @DependsOn("sqlSessionFactory")
    public SqlSessionTemplate sqlSessionTemplate() {
        
        return new SqlSessionTemplate(factory);
        
    }
}
