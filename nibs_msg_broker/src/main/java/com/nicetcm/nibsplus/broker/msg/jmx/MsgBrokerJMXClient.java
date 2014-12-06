package com.nicetcm.nibsplus.broker.msg.jmx;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class MsgBrokerJMXClient {

    public static void printParameter() {
        System.out.println("Usage: java MsgBrokerJMXClient [parameters...]");
        System.out.println("parameters - [shutdown]");
        System.out.println("             [-reset [consumer name]]");
        System.out.println("             [-close [consumer name]]");
        System.out.println("             [-start [consumer name]]");
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
            System.out.println("Usage: java MsgBrokerJMXClient shutdown");
            return;
        }
        else if( args[0].equals("-reset") && args.length != 2 ) {
            System.out.println("Usage: java MsgBrokerJMXClient -reset [consumer name]");
            return;
        }
        else if( args[0].equals("-close") && args.length != 2 ) {
            System.out.println("Usage: java MsgBrokerJMXClient -close [consumer name]");
            return;
        }
        else if( args[0].equals("-start") && args.length != 2 ) {
            System.out.println("Usage: java MsgBrokerJMXClient -start [consumer name]");
            return;
        }

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

        if( args[0].equals("shutdown") )
            System.out.println(mbeanProxy.shutdownServer("ShutDown"));
        else if( args[0].equals("-reset") ) {
            System.out.println(mbeanProxy.reattachConsumer(args[1]));
        }
        else if( args[0].equals("-close") ) {
            System.out.println(mbeanProxy.reattachConsumer(String.format("CLOSE%s", args[1])));
        }
        else if( args[0].equals("-start") ) {
            System.out.println(mbeanProxy.reattachConsumer(String.format("CLOSE%s", args[1])));
        }

        mbeanServerConnection.removeNotificationListener(mbeanName, listener);
        jmxConnector.close();
    }

}
