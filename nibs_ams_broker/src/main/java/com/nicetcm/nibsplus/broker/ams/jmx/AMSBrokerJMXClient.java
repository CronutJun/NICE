package com.nicetcm.nibsplus.broker.ams.jmx;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class AMSBrokerJMXClient {

    public static void printParameter() {
        System.out.println("Usage: java AMSBrokerJMXClient [parameters...]");
        System.out.println("parameters - [shutdown]");
        //System.out.println("             [-reset [consumer name]]");
    }

    public static void main(String[] args) throws Exception {
        if( args.length == 0 ) {
            printParameter();
            return;
        }
        if( !args[0].equals("shutdown")
        &&  !args[0].equals("-reset") && !args[0].equals("-close") && !args[0].equals("-start") ) {
            printParameter();
            return;
        }
        if( args[0].equals("shutdown") && args.length > 1 ) {
            System.out.println("Usage: java AMSBrokerJMXClient shutdown");
            return;
        }

        JMXServiceURL url =
        //        new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + "localhost" + ":" + "10899" + "/jmxrmi");
                new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + ":" + "10999" + "/jmxrmi");

        JMXConnector jmxConnector = JMXConnectorFactory.connect(url);
        MBeanServerConnection mbeanServerConnection = jmxConnector.getMBeanServerConnection();

        ObjectName mbeanName = new ObjectName("com.nicetcm.nibsplus.broker.ams.jmx:type=AMSBrokerManager");

        AMSBrokerJMXClientListener listener = new AMSBrokerJMXClientListener();
        mbeanServerConnection.addNotificationListener(mbeanName, listener, null, null);

        AMSBrokerManagerMBean mbeanProxy =
                (AMSBrokerManagerMBean) MBeanServerInvocationHandler.newProxyInstance(
                    mbeanServerConnection, mbeanName, AMSBrokerManagerMBean.class, true);

        if( args[0].equals("shutdown") )
            System.out.println(mbeanProxy.shutdownServer("ShutDown"));

        mbeanServerConnection.removeNotificationListener(mbeanName, listener);
        jmxConnector.close();
    }

}
