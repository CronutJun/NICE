package com.nicetcm.nibsplus.broker.msg;

import javax.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@MapperScan("com.nicetcm.nibsplus.broker.msg.mapper")
@ComponentScan("com.nicetcm.nibsplus.broker.msg.services")
public class MsgBrokerAppConfig {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerAppConfig.class);



    @Bean
    public static PropertyPlaceholderConfigurer propertyConfigurer() {

        PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
        ClassPathResource resources[] = new ClassPathResource[] {new ClassPathResource(String.format("/%s/msg.properties", MsgBrokerConst.SVR_TYPE))};
        props.setLocations(resources);
        props.setIgnoreUnresolvablePlaceholders(true);
        System.out.println("message loading");
        return props;
    }

    @Value("${db_url}") private String db_url;
    @Value("${db_user}") private String db_user;
    @Value("${db_pass}") private String db_pass;
    @Value("${db_init_conn}") private String db_init_conn;
    @Value("${db_max_conn}") private String db_max_conn;

    @Autowired private DataSource ds;
    @Autowired private SqlSessionFactory factory;

    //@Bean(name="dataSource")
    //public BasicDataSource dataSource() {
    //
    //    logger.debug("db_usr = {}", db_url);
    //    BasicDataSource ds = new BasicDataSource();
    //    ds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
    //    ds.setUrl(db_url);
    //    ds.setUsername(db_user);
    //    ds.setPassword(db_pass);
    //    ds.setInitialSize(Integer.parseInt(db_init_conn));
    //    ds.setMaxActive(Integer.parseInt(db_max_conn));
    //
    //    this.ds = ds;
    //
    //    return ds;
    //
    //}

    @Bean(name="dataSource")
    public DataSource dataSource() {

        logger.debug("db_usr = {}", db_url);
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
        ds.setUrl(db_url);
        ds.setUsername(db_user);
        ds.setPassword(db_pass);
        ds.setInitialSize(Integer.parseInt(db_init_conn));
        ds.setMaxActive(Integer.parseInt(db_max_conn));

        net.sf.log4jdbc.Log4jdbcProxyDataSource logDs = new  net.sf.log4jdbc.Log4jdbcProxyDataSource(ds);

        this.ds = logDs;

        return logDs;

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
        factory.setConfigLocation(MsgBrokerSpringMain.sprCtx.getResource(String.format("classpath:%s/msg_db_conf.xml", MsgBrokerConst.SVR_TYPE)));

        this.factory = factory.getObject();

        return factory.getObject();
    }

    @Bean(name="sqlSession")
    @DependsOn("sqlSessionFactory")
    public SqlSessionTemplate sqlSessionTemplate() {

        return new SqlSessionTemplate(factory);

    }
}
