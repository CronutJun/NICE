package com.nicetcm.nibsplus.broker.ams.jmx;

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

import com.nicetcm.nibsplus.broker.ams.AMSBrokerShutdown;
import com.nicetcm.nibsplus.broker.common.MsgCommon;

public class AMSBrokerManager extends NotificationBroadcasterSupport implements AMSBrokerManagerMBean {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerManager.class);
    
    private long sequenceNumber = 1;
    private int RMIResTimeout = Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.response.timeout", "0"));

    @Override
    public void shutdownServer() {
        new AMSBrokerShutdown().start();
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
                                                          "CacheSize changed",
                                                          "CacheSize",
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
