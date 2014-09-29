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

import javax.management.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;

import com.nicetcm.nibsplus.broker.msg.MsgBrokerShutdown;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerSpringMain;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerClassLoader;
import com.nicetcm.nibsplus.broker.common.MsgCommon;

public class MsgBrokerManager extends NotificationBroadcasterSupport implements MsgBrokerManagerMBean {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerManager.class);

    private long sequenceNumber = 1;
    private int RMIResTimeout = Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.response.timeout", "0"));

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
    public void refreshBean(String beanName, String beanClassName) {
        try {
            MsgBrokerClassLoader classLoader = new MsgBrokerClassLoader();
            Class changeClass = classLoader.loadClass(beanClassName);
            Object changedInstance = MsgBrokerSpringMain.sprCtx.getBean(beanName);
            logger.debug("bean {} is {}", beanName, changedInstance);
            changedInstance = changeClass.newInstance();
            logger.debug("Going to destroyBean: {}", beanClassName );
            ((BeanDefinitionRegistry)MsgBrokerSpringMain.sprCtx.getBeanFactory()).removeBeanDefinition(beanName);
            //((DefaultListableBeanFactory)MsgBrokerSpringMain.sprCtx.getBeanFactory()).destroySingleton(beanName);
            logger.debug("Going to registerBean: {}", beanClassName );
            BeanDefinition testDef = new RootBeanDefinition(changedInstance.getClass());
            testDef.setScope(BeanDefinition.SCOPE_SINGLETON);

            //MsgBrokerSpringMain.sprCtx.getBeanFactory().registerSingleton(beanName, testDef);
            MsgBrokerSpringMain.sprCtx.getBeanFactory().registerSingleton(beanName, changedInstance);
            changedInstance = MsgBrokerSpringMain.sprCtx.getBean(beanName);
            logger.debug("Is there {}? {}", beanName, changedInstance);
        }
        catch( Exception e ) {
            logger.debug("refreshBean exception is raised: {}", e.getMessage() );
        }
    }

    @Override
    public void reloadSchema() {
        // Not implemented yet.
    }

    @Override
    public int getRMIResTimeout() {
        return RMIResTimeout;
    }

    @Override
    public void setRMIResTimeout(int timeout) {
        int oldTimeout = RMIResTimeout;
        RMIResTimeout  = timeout;
        // 실제 반영 해야 함... 아직 안했다.

        Notification n =  new AttributeChangeNotification(this,
                                                          sequenceNumber++,
                                                          System.currentTimeMillis(),
                                                          "RMI Response timeout changed",
                                                          "RMIResTimeout",
                                                          "int",
                                                          oldTimeout,
                                                          this.RMIResTimeout);

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
}
