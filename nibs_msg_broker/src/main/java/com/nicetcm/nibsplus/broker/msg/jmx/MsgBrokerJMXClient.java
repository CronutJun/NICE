package com.nicetcm.nibsplus.broker.msg.jmx;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class MsgBrokerJMXClient {

    public static void main(String[] args) throws Exception {
        JMXServiceURL url =
        //        new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + "localhost" + ":" + "10899" + "/jmxrmi");
                new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + ":" + "10899" + "/jmxrmi");

        JMXConnector jmxConnector = JMXConnectorFactory.connect(url);
        MBeanServerConnection mbeanServerConnection = jmxConnector.getMBeanServerConnection();

        ObjectName mbeanName = new ObjectName("com.nicetcm.nibsplus.broker.msg.jmx:type=MsgBrokerManager");

        MsgBrokerJMXClientListener listener = new MsgBrokerJMXClientListener();
        mbeanServerConnection.addNotificationListener(mbeanName, listener, null, null);

        MsgBrokerManagerMBean mbeanProxy =
                (MsgBrokerManagerMBean) MBeanServerInvocationHandler.newProxyInstance(
                    mbeanServerConnection, mbeanName, MsgBrokerManagerMBean.class, true);

        System.out.println(mbeanProxy.reattachConsumer("ABC"));

        mbeanServerConnection.removeNotificationListener(mbeanName, listener);

        jmxConnector.close();
    }

}
