package com.nicetcm.nibsplus.broker.msg.jmx;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * AMSBrokerManager
 *
 *  AMSBroker 서버의 관리 ( 셧다운 및 기타 자원관리 )
 *
 *
 * @author  K.D.J
 * @since   2014.09.05
 */

import java.util.Map.Entry;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerClassLoader;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConsumer;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerProducer;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerShutdown;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerSpringMain;

public class MsgBrokerManager extends NotificationBroadcasterSupport implements MsgBrokerManagerMBean {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerManager.class);

    private long sequenceNumber = 1;

    @Override
    public String shutdownServer(String operation) {
        if( operation.equals("ShutDown") ) {
            new MsgBrokerShutdown().start();
            return "OK. Going to shutdown";
        }
        else {
            return "Give me the right parameter. [ShutDown]";
        }
    }

    @Override
    public String hotSwapBean(String beanClassName) {
        String beanName = "";
        String retMsg = "";
        //AutowireCapableBeanFactory beanFactory;

        try {
            logger.warn("Bean Class Name : {}", beanClassName);

            MsgBrokerClassLoader classLoader = new MsgBrokerClassLoader();
            Class<?> changeClass = classLoader.loadClass(beanClassName);

            if( changeClass.isAnnotationPresent(org.springframework.stereotype.Component.class) ) {
                logger.warn("Component annotation is present");
                beanName = ((org.springframework.stereotype.Component)changeClass
                               .getAnnotation(org.springframework.stereotype.Component.class)).value();
            }
            else if( changeClass.isAnnotationPresent(org.springframework.stereotype.Service.class) ) {
                logger.warn("Service annotation is present");
                beanName = ((org.springframework.stereotype.Service)changeClass
                               .getAnnotation(org.springframework.stereotype.Service.class)).value();
            }
            else if( changeClass.isAnnotationPresent(org.springframework.stereotype.Repository.class) ) {
                logger.warn("Repository annotation is present");
                beanName = ((org.springframework.stereotype.Repository)changeClass
                               .getAnnotation(org.springframework.stereotype.Repository.class)).value();
            }
            else if( changeClass.isAnnotationPresent(org.springframework.stereotype.Controller.class) ) {
                logger.warn("Controller annotation is present");
                beanName = ((org.springframework.stereotype.Controller)changeClass
                               .getAnnotation(org.springframework.stereotype.Controller.class)).value();
            }
            if( beanName == null || beanName.length() == 0 ) {
                if( changeClass.getName().lastIndexOf(".") >= 0 )
                    beanName = changeClass.getName().substring(changeClass.getName().lastIndexOf(".")+1);
                else
                    beanName = changeClass.getName();
            }
            Object changedInstance = null;
            try {
                changedInstance = MsgBrokerSpringMain.sprCtx.getBean(beanName);
            }
            catch( org.springframework.beans.factory.NoSuchBeanDefinitionException e ) {
                beanName = beanName.substring(0,1).toLowerCase() + beanName.substring(1);
                try { changedInstance = MsgBrokerSpringMain.sprCtx.getBean(beanName); } catch ( Exception se ) {}
            }
            logger.warn("bean {} is {}", beanName, changedInstance);

            if( changedInstance != null ) {
                logger.warn("Going to destroyBean: {}", beanClassName );
                ((BeanDefinitionRegistry)MsgBrokerSpringMain.sprCtx.getBeanFactory()).removeBeanDefinition(beanName);
                retMsg = "Successfully Swaped";
            }

            logger.warn("Going to registerBean: {}", beanClassName );
            GenericBeanDefinition beanDef = new GenericBeanDefinition();
            beanDef.setBeanClass(changeClass);
            beanDef.setLazyInit(false);
            beanDef.setAbstract(false);
            beanDef.setAutowireCandidate(true);
            beanDef.setScope(BeanDefinition.SCOPE_SINGLETON);
            ((BeanDefinitionRegistry)MsgBrokerSpringMain.sprCtx.getBeanFactory()).registerBeanDefinition(beanName, beanDef);

            changedInstance = MsgBrokerSpringMain.sprCtx.getBean(beanName);
            logger.warn("Is there {}? {}", beanName, changedInstance);

            //beanFactory = MsgBrokerSpringMain.sprCtx.getBean(AutowireCapableBeanFactory.class);

            if( retMsg.length() == 0 )
                return "Succcessfully Registered.";
            else
                return retMsg;
        }
        catch( Exception e ) {
            logger.error("hotSwapBean exception is raised: {}", e.getMessage() );
            return e.getMessage();
        }
    }

    @Override
    public String setLogLevel(String logLevel) {
        try {
            if( logLevel == null ) return "please set the log level.";
            if( logLevel.equals("ALL") ) {
                LogManager.getRootLogger().setLevel(Level.ALL);
                LogManager.getLogger("com.nicetcm.nibsplus").setLevel(Level.ALL);
                LogManager.getLogger("com.bogogt").setLevel(Level.ALL);
                LogManager.getLogger("java.sql").setLevel(Level.ALL);
                LogManager.getLogger("org.springframework").setLevel(Level.ALL);
                LogManager.getLogger("org.apache.activemq").setLevel(Level.ALL);
            }
            else if( logLevel.equals("TRACE") ) {
                LogManager.getRootLogger().setLevel(Level.TRACE);
                LogManager.getLogger("com.nicetcm.nibsplus").setLevel(Level.TRACE);
                LogManager.getLogger("com.bogogt").setLevel(Level.TRACE);
                LogManager.getLogger("java.sql").setLevel(Level.TRACE);
                LogManager.getLogger("org.springframework").setLevel(Level.TRACE);
                LogManager.getLogger("org.apache.activemq").setLevel(Level.TRACE);
            }
            else if( logLevel.equals("DEBUG") ) {
                LogManager.getRootLogger().setLevel(Level.DEBUG);
                LogManager.getLogger("com.nicetcm.nibsplus").setLevel(Level.DEBUG);
                LogManager.getLogger("com.bogogt").setLevel(Level.DEBUG);
                LogManager.getLogger("java.sql").setLevel(Level.DEBUG);
                LogManager.getLogger("org.springframework").setLevel(Level.DEBUG);
                LogManager.getLogger("org.apache.activemq").setLevel(Level.DEBUG);
            }
            else if( logLevel.equals("INFO") ) {
                LogManager.getRootLogger().setLevel(Level.INFO);
                LogManager.getLogger("com.nicetcm.nibsplus").setLevel(Level.INFO);
                LogManager.getLogger("com.bogogt").setLevel(Level.INFO);
                LogManager.getLogger("java.sql").setLevel(Level.INFO);
                LogManager.getLogger("org.springframework").setLevel(Level.INFO);
                LogManager.getLogger("org.apache.activemq").setLevel(Level.INFO);
            }
            else if( logLevel.equals("WARN") ) {
                LogManager.getRootLogger().setLevel(Level.WARN);
                LogManager.getLogger("com.nicetcm.nibsplus").setLevel(Level.WARN);
                LogManager.getLogger("com.bogogt").setLevel(Level.WARN);
                LogManager.getLogger("java.sql").setLevel(Level.WARN);
                LogManager.getLogger("org.springframework").setLevel(Level.WARN);
                LogManager.getLogger("org.apache.activemq").setLevel(Level.WARN);
            }
            else if( logLevel.equals("ERROR") ) {
                LogManager.getRootLogger().setLevel(Level.ERROR);
                LogManager.getLogger("com.nicetcm.nibsplus").setLevel(Level.ERROR);
                LogManager.getLogger("com.bogogt").setLevel(Level.ERROR);
                LogManager.getLogger("java.sql").setLevel(Level.ERROR);
                LogManager.getLogger("org.springframework").setLevel(Level.ERROR);
                LogManager.getLogger("org.apache.activemq").setLevel(Level.ERROR);
            }
            else if( logLevel.equals("FATAL") ) {
                LogManager.getRootLogger().setLevel(Level.FATAL);
                LogManager.getLogger("com.nicetcm.nibsplus").setLevel(Level.FATAL);
                LogManager.getLogger("com.bogogt").setLevel(Level.FATAL);
                LogManager.getLogger("java.sql").setLevel(Level.FATAL);
                LogManager.getLogger("org.springframework").setLevel(Level.FATAL);
                LogManager.getLogger("org.apache.activemq").setLevel(Level.FATAL);
            }
            else if( logLevel.equals("OFF") ) {
                LogManager.getRootLogger().setLevel(Level.OFF);
                LogManager.getLogger("com.nicetcm.nibsplus").setLevel(Level.OFF);
                LogManager.getLogger("com.bogogt").setLevel(Level.OFF);
                LogManager.getLogger("java.sql").setLevel(Level.OFF);
                LogManager.getLogger("org.springframework").setLevel(Level.OFF);
                LogManager.getLogger("org.apache.activemq").setLevel(Level.OFF);
            }
            else return String.format("Invalid log level %s", logLevel);

            return "change succeed";
        }
        catch( Exception e ) {
            logger.error("setLogLevel exception is raised: {}", e.getMessage() );
            return "Fail!";
        }
    }


    @Override
    public void listBean() {
        try {
            String names[] = ((BeanDefinitionRegistry)MsgBrokerSpringMain.sprCtx.getBeanFactory()).getBeanDefinitionNames();
            for( String nm: names ) {
                logger.warn("Name = {}", nm);
            }
        }
        catch( Exception e ) {
            logger.error("listBean exception is raised: {}", e.getMessage() );
        }
    }

    @Override
    public void reloadSchema() throws Exception {
        MsgParser.clearSchemas();
    }

    @Override
    public int getRMIResTimeout() {

        return Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.response.timeout", "0"));
    }

    @Override
    public void setRMIResTimeout(int timeout) {
        int oldTimeout = Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.response.timeout", "0"));
        MsgCommon.msgProps.setProperty("rmi.response.timeout", Integer.toString(timeout) );

        Notification n =  new AttributeChangeNotification(this,
                                                          sequenceNumber++,
                                                          System.currentTimeMillis(),
                                                          "RMI Response timeout changed",
                                                          "RMIResTimeout",
                                                          "int",
                                                          oldTimeout,
                                                          Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.response.timeout", "0")));

        sendNotification(n);
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[] {
                            AttributeChangeNotification.ATTRIBUTE_CHANGE
                         };
        String name = AttributeChangeNotification.class.getName();
        String description = "An attribute of this MBean has changed";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[] { info };
    }

    @Override
    public String reattachConsumer(String consumerName) {
        try {
            if( consumerName != null && consumerName.equals("ALL") ) {
                for( Entry<String, MsgBrokerProducer> e: MsgBrokerProducer.producers.entrySet()) {
                    e.getValue().init();
                }
                for( Entry<String, MsgBrokerConsumer> e: MsgBrokerConsumer.consumers.entrySet()) {
                    e.getValue().init();
                }
            }
            else {
                MsgBrokerConsumer con = MsgBrokerConsumer.consumers.get(consumerName);
                if( con == null )
                    return String.format("No Consumer: %s, set the right name or set \"ALL\"", consumerName);
                con.init();
            }
            return "reattachment is succeed";
        }
        catch( Exception e ) {
            return e.getMessage();
        }
    }
}
