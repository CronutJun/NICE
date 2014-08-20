package com.nicetcm.nibsplus.broker.ams;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * AMSBrokerAppConfig
 *
 *  Spring 및 AMSBroker가 필요한 자원의 설정값을 annotation기반으로 관리
 *
 *
 * @author  K.D.J
 * @since   2014.08.18
 */

import javax.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import org.springframework.core.io.ClassPathResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.session.SqlSessionFactory;


@Configuration
@MapperScan("com.nicetcm.nibsplus.broker.ams.mapper")
@ComponentScan("com.nicetcm.nibsplus.broker.ams.services")
public class AMSBrokerAppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerAppConfig.class);

    @Bean
    public static PropertyPlaceholderConfigurer propertyConfigurer() {

        PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
        ClassPathResource resources[] = new ClassPathResource[] {new ClassPathResource(String.format("/%s/ams.properties", AMSBrokerConst.SVR_TYPE))};
        props.setLocations(resources);
        props.setIgnoreUnresolvablePlaceholders(true);

        return props;
    }

    @Value("${db_url}") private String db_url;
    @Value("${db_user}") private String db_user;
    @Value("${db_pass}") private String db_pass;
    @Value("${db_init_conn}") private String db_init_conn;
    @Value("${db_max_conn}") private String db_max_conn;

    @Autowired private DataSource ds;
    @Autowired private SqlSessionFactory factory;

    @Bean(name="dataSource")
    public BasicDataSource dataSource() {

        logger.debug("db_usr = {}", db_url);
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
        ds.setUrl(db_url);
        ds.setUsername(db_user);
        ds.setPassword(db_pass);
        ds.setInitialSize(Integer.parseInt(db_init_conn));
        ds.setMaxActive(Integer.parseInt(db_max_conn));

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
        factory.setConfigLocation(AMSBrokerSpringMain.sprCtx.getResource(String.format("classpath:%s/ams_db_conf.xml", AMSBrokerConst.SVR_TYPE)));

        this.factory = factory.getObject();

        return factory.getObject();
    }

    @Bean(name="sqlSession")
    @DependsOn("sqlSessionFactory")
    public SqlSessionTemplate sqlSessionTemplate() {

        return new SqlSessionTemplate(factory);

    }
}
