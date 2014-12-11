package com.nicetcm.nibsplus.broker.msg.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.File;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.fusesource.jansi.AnsiConsole;

import sun.jvmstat.monitor.*;
import sun.management.ConnectorAddressLink;
import sun.tools.jconsole.LocalVirtualMachine;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.Query;
import javax.management.QueryExp;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.openmbean.CompositeData;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerMain;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;


public class ActiveMQManager {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQManager.class);

    private String amqJmxUrl = "service:jmx:rmi:///jndi/rmi://10.3.28.62:1099/jmxrmi";

    private static final String ANSI_CLS = "\u001b[2J";
    private static final String ANSI_HOME = "\u001b[H";
    private static final String ANSI_BOLD = "\u001b[1m";
    private static final String ANSI_AT55 = "\u001b[10;10H";
    private static final String ANSI_REVERSEON = "\u001b[7m";
    private static final String ANSI_NORMAL = "\u001b[0m";
    private static final String ANSI_WHITEONBLUE = "\u001b[37;44m";

    private static final String CONNECTOR_ADDRESS = "com.sun.management.jmxremote.localConnectorAddress";
    private String runOpt;

    public ActiveMQManager( String opt ) {
        this.runOpt = opt;
    }


    public void listAll(String dest) {

        MBeanServerConnection connection;
        String clientServiceName = "org.apache.activemq:brokerName=localhost,type=Broker";
        try {
            AnsiConsole.systemInstall();
            // Acquire a connection to the MBean server
            connection = connect(dest);

            // How many MBeans are running?
            count(connection);

            // Query for a single MBean
            logger.debug("Query single");
            query(connection, clientServiceName);

            Set<ObjectInstance> mbeans = queryForQueue(connection, "*");
            logger.debug(String.valueOf(mbeans.size()));
            AnsiConsole.out.println(String.format("------------------------------------------------------------------------------------------------------"));
            AnsiConsole.out.println(String.format("%-18s%22s%22s%20s%20s", "Queue Name", "Number of Pendings", "Number of Consumers", "Enque Count", "Deque Count"));
            AnsiConsole.out.println(String.format("------------------------------------------------------------------------------------------------------"));
            List<ObjectInstance> lst = new ArrayList<ObjectInstance>(mbeans);
            Collections.sort(lst, new Comparator<ObjectInstance>() {
                public int compare(ObjectInstance o1, ObjectInstance o2) {
                    return (o1.getObjectName().getKeyProperty("destinationName").compareTo(o2.getObjectName().getKeyProperty("destinationName")) < 0 ? -1
                            : (o1.getObjectName().getKeyProperty("destinationName").equals(o2.getObjectName().getKeyProperty("destinationName")) ? 0 : 1));
                }
            });
            for(ObjectInstance que: lst ) {
                AnsiConsole.out.println(String.format("%-18s%22d%22s%20s%20s",
                                                              que.getObjectName().getKeyProperty("destinationName"),
                                                              Integer.parseInt(getMBeanAttr(connection, que.getObjectName(), "EnqueueCount").toString()) -
                                                              Integer.parseInt(getMBeanAttr(connection, que.getObjectName(), "DequeueCount").toString()),
                                                              getMBeanAttr(connection, que.getObjectName(), "ConsumerCount"),
                                                              getMBeanAttr(connection, que.getObjectName(), "EnqueueCount"),
                                                              getMBeanAttr(connection, que.getObjectName(), "DequeueCount")
                                                ));
            }
            //AnsiConsole.out.println(ANSI_CLS);
            AnsiConsole.systemUninstall();
        }
        catch (IOException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
    }

    public void removeAll(String dest) {
        MBeanServerConnection connection;

        String clientServiceName = "org.apache.activemq:brokerName=localhost,type=Broker";

         try {
            // Acquire a connection to the MBean server
            connection = connect(dest);

            // How many MBeans are running?
            count(connection);

            // Query for a single MBean
            logger.debug("Query single");
            query(connection, clientServiceName);

            Set<ObjectInstance> mbeans = queryForQueue(connection, "*");
            logger.debug(String.valueOf(mbeans.size()));
            for(Iterator<ObjectInstance> iter = mbeans.iterator(); iter.hasNext(); ) {
                ObjectInstance que = (ObjectInstance) iter.next();
                System.out.println("+ " + que.getObjectName().getKeyProperty("destinationName"));
                removeQueue(connection, que.getObjectName().getKeyProperty("destinationName"));
            }

//            // Query all MBeans
//            logger.debug("Query all");
//            query(connection, "");
//
//            // Check for the topic first
//            Set mbeans = queryForTopic(connection, topicName);
//
//            logger.info("Located [" + mbeans.size() + "] MBeans");
//
//            if(mbeans.size() > 0) {
//                // Create a new topic on the broker
//                createTopic(connection, topicName);
//            }
//            else {
//                // Remove the topic from the broker and then create it
//                removeTopic(connection, topicName);
//                createTopic(connection, topicName);
//            }
//
//            mbeans = queryForTopic(connection, topicName);

            System.out.println("Located [" + mbeans.size() + "] MBeans");


         }
         catch (IOException e) {
             for( StackTraceElement se: e.getStackTrace() )
                 logger.error(se.toString());
        }
    }

    public void remove(String dest, String queName) {

        MBeanServerConnection connection;

        try {
            // Acquire a connection to the MBean server
            connection = connect(dest);

            Set<ObjectInstance> mbeans = queryForQueue(connection, queName);
            if( mbeans.size() == 0 )
                System.out.println("Queue [" + queName + "] is not found.");
            else
                removeQueue(connection, queName);
        }
        catch (IOException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
    }

    public void browse(String dest, String queName) {

        MBeanServerConnection connection;

        try {
            // Acquire a connection to the MBean server
            connection = connect(dest);

            Set<ObjectInstance> mbeans = queryForQueue(connection, queName);
            if( mbeans.size() == 0 )
                System.out.println("Queue [" + queName + "] is not found.");
            else {
                try {
                    ObjectInstance ques[] = {null};
                    ques = mbeans.toArray(ques);
                    CompositeData ret[] = (CompositeData [])connection.invoke(ques[0].getObjectName(), "browse", null, null);
                    for(CompositeData data: ret) {
                        String mID = (String)data.get("JMSMessageID");
                        System.out.println(String.format("Message ID : [%s]", mID));
                        Byte[] mPrvs = (Byte [])data.get("BodyPreview");
                        byte[] mDatas = new byte[mPrvs.length];
                        for(int i = 0; i < mPrvs.length; i++) {
                            mDatas[i] = mPrvs[i];
                        }
                        System.out.println(String.format("   Preview = [%s]", new String(mDatas)));
                    }
                    //List<ActiveMQBytesMessage> lst = (List<ActiveMQBytesMessage>)connection.invoke(ques[0].getObjectName(), "browseMessages", null, null);
                    //System.out.println(lst.get(0).getClass().getName());
                }
                catch (NullPointerException e) {
                    logger.error(e.getMessage());
                    for( StackTraceElement se: e.getStackTrace() )
                        logger.error(se.toString());
                }
                catch (InstanceNotFoundException e) {
                    logger.error(e.getMessage());
                    for( StackTraceElement se: e.getStackTrace() )
                        logger.error(se.toString());
                }
                catch (MBeanException e) {
                    logger.error(e.getMessage());
                    for( StackTraceElement se: e.getStackTrace() )
                        logger.error(se.toString());
                }
                catch (ReflectionException e) {
                    logger.error(e.getMessage());
                    for( StackTraceElement se: e.getStackTrace() )
                        logger.error(se.toString());
                }
            }
        }
        catch (IOException e) {
            logger.error(e.getMessage());
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }

    }

    private MBeanServerConnection connect(String dest)  throws IOException {
        JMXConnector connector = null;
        MBeanServerConnection connection = null;

        String username = "";
        String password = "";

        Map<String, String[]> env = new HashMap<String, String[]>();
        String[] credentials = new String[] { username, password };
        env.put(JMXConnector.CREDENTIALS, credentials);

        if( runOpt.equals("-llc") || runOpt.equals("-lrc") || runOpt.equals("-lbc") ) {
            connection = localConnect();
            if( connection != null )
                return connection;
        }

        try {
            if( dest.equals("C") ) {
                amqJmxUrl = String.format("service:jmx:rmi:///jndi/rmi://%s:%s/jmxrmi",
                                MsgCommon.msgProps.getProperty("jmx.consumer.host", "10.3.28.62"),
                                MsgCommon.msgProps.getProperty("jmx.consumer.port", "1099"));
            }
            else {
                amqJmxUrl = String.format("service:jmx:rmi:///jndi/rmi://%s:%s/jmxrmi",
                        MsgCommon.msgProps.getProperty("jmx.producer.host", "10.3.28.61"),
                        MsgCommon.msgProps.getProperty("jmx.producer.port", "1099"));
            }
            connector = JMXConnectorFactory.newJMXConnector(new JMXServiceURL(amqJmxUrl), env);
            connector.connect();
            connection = connector.getMBeanServerConnection();
        }
        catch (MalformedURLException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
        catch (IOException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }

        return connection;
    }

    private MBeanServerConnection localConnect()  throws IOException {
        int pid = -1;

        try {
            try {
                final Map<Integer, LocalVirtualMachine> virtualMachines = LocalVirtualMachine.getAllVirtualMachines();
                for (final Entry<Integer, LocalVirtualMachine> entry : virtualMachines.entrySet()) {
                    logger.debug("Currently running process : [{}]", entry.getValue().displayName() );
                    if( entry.getValue().displayName().matches("(.*)activemq(.*)") ) {
                        logger.debug("Found process : [{}]", entry.getValue().displayName() );
                        pid = entry.getKey();
                        break;
                    }
                }

                /*
                MonitoredHost local = MonitoredHost.getMonitoredHost(new HostIdentifier((String) null));
                Set<Integer> vmlist = new HashSet<Integer>(local.activeVms());
                for (Integer id : vmlist) {
                    MonitoredVm vm = local.getMonitoredVm(new VmIdentifier("//" + id));
                    String processname = MonitoredVmUtil.mainClass(vm, true);
                    logger.debug("Currently running process : [{}]", processname );
                    if( processname.matches("(.*)activemq(.*)") ) {
                        pid = id;
                        break;
                    }
                }
                */
                if( pid == -1 ) {
                    logger.debug("pid is -1");
                    return null;
                }
            }
            catch( Exception e ) {
                return null;
            }

            String address = "";

            com.sun.tools.attach.VirtualMachine vm =  com.sun.tools.attach.VirtualMachine.attach(Integer.toString(pid));

            try  {
                // get the connector address
                address = vm.getAgentProperties().getProperty(CONNECTOR_ADDRESS);

                // no connector address, so we start the JMX agent
                if (address == null) {
                    String agent = vm.getSystemProperties().getProperty("java.home") +
                                   File.separator + "lib" + File.separator +
                                   "management-agent.jar";
                    logger.debug("Agent = {}", agent);
                    vm.loadAgent(agent, "com.sun.management.jmxremote");

                    // agent is started, get the connector address
                    address = vm.getAgentProperties().getProperty(CONNECTOR_ADDRESS);
                }
            }
            finally {
                vm.detach();
            }

            //address = ConnectorAddressLink.importFrom(pid);
            logger.debug("address = " + address);
            JMXServiceURL jmxUrl = new JMXServiceURL(address);
            return JMXConnectorFactory.connect(jmxUrl).getMBeanServerConnection();
        }
        catch (IOException e) {
            throw new RuntimeException("Of course you still have to implement a good connection handling");
        }
        catch ( Exception e ) {
            logger.debug("Exception is raised");
            throw new RuntimeException("Exception is raised");
        }

    }

    private void count(MBeanServerConnection conn) throws IOException {
        int numberOfMBeans = conn.getMBeanCount().intValue();
        System.out.println("Number of MBeans currently running: " + numberOfMBeans);
    }

    private void query(MBeanServerConnection conn, String query) throws IOException {
        if (conn != null && query.length() > 0) {
            listMBeans(conn, query);
        }
        else if (conn != null && query.length() == 0) {
            listAllMBeanNames(conn);
        }
        else {
            logger.error("Unable to connect to ServiceMix");
        }
    }

    private void listMBeans(MBeanServerConnection conn, String query) throws IOException {
        ObjectName name;
        Set<ObjectInstance> names = null;
        try {
            name = new ObjectName(query);
            names = conn.queryMBeans(name, name);
            logger.debug("listMBeans = " + names.size());
        }
        catch (MalformedObjectNameException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
        catch (NullPointerException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }

        for(Iterator<ObjectInstance> iter = names.iterator(); iter.hasNext(); ) {
            ObjectInstance obj = iter.next();
            System.out.println("+ " + obj.getClassName());
        }
    }

    private void listAllMBeanNames(MBeanServerConnection conn) throws IOException {
        Set<ObjectName> names = getAllMBeanNames(conn);

        for (Iterator<ObjectName> iter = names.iterator(); iter.hasNext();) {
            ObjectName objName = iter.next();
            System.out.println("+ " + objName + ", Domain = " + objName.getDomain());
        }
    }

    private void listMBeanAttrs(MBeanServerConnection conn, String query) throws IOException {
        ObjectName objName = null;
        try {
            objName = new ObjectName(query);
            System.out.println("+ " + objName.getCanonicalName());

            MBeanInfo info = getMBeanInfo(conn, objName);
            MBeanAttributeInfo[] attrs = info.getAttributes();

            for(int i = 0; i < attrs.length; ++i) {
                Object obj = conn.getAttribute(objName, attrs[i].getName());
                System.out.println("  - " + attrs[i].getName() + obj);
            }
        }
        catch (MalformedObjectNameException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
        catch (NullPointerException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
        catch (AttributeNotFoundException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
        catch (InstanceNotFoundException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
        catch (MBeanException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
        catch (ReflectionException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
    }

    private void createTopic(MBeanServerConnection conn, String topicName) throws IOException {
        String brokerNameQuery = "org.apache.activemq:BrokerName=localhost,Type=Broker";
        String addTopicOperationName = "addTopic";
        Object[] params = { topicName };
        String[] sig = { "java.lang.String" };

        doTopicCrud(conn, topicName, brokerNameQuery, addTopicOperationName, params, sig, "created");
    }

    private void removeTopic(MBeanServerConnection conn, String topicName) throws IOException {
        String brokerNameQuery = "org.apache.activemq:BrokerName=localhost,Type=Broker";
        String removeTopicOperationName = "removeTopic";
        Object[] params = { topicName };
        String[] sig = { "java.lang.String" };

        doTopicCrud(conn, topicName, brokerNameQuery, removeTopicOperationName, params, sig, "removed");
    }

    private void removeQueue(MBeanServerConnection conn, String queueName) throws IOException {
        String brokerNameQuery = "org.apache.activemq:brokerName=localhost,type=Broker";
        String removeTopicOperationName = "removeQueue";
        Object[] params = { queueName };
        String[] sig = { "java.lang.String" };

        doTopicCrud(conn, queueName, brokerNameQuery, removeTopicOperationName, params, sig, "removed");
    }

    private void doTopicCrud(MBeanServerConnection conn, String topicName,
            String brokerNameQuery, String operationName, Object[] params, String[] sig, String verb) throws IOException {
        ObjectName brokerObjName;

        try {
            System.out.println( verb + "ing new topic: [" + topicName + "]");
            brokerObjName = new ObjectName(brokerNameQuery);
            conn.invoke(brokerObjName, operationName, params, sig);
            System.out.println("Topic [" + topicName + "] has been " + verb);
        }
        catch (MalformedObjectNameException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
        catch (NullPointerException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
        catch (InstanceNotFoundException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
        catch (MBeanException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
        catch (ReflectionException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
    }


    private Set queryForTopic(MBeanServerConnection conn, String topicName) throws IOException {
        // Was the topic created?
        String topicsQuery = "org.apache.activemq:BrokerName=localhost,Type=Topic,*";
        // listMBeans(conn, topicsQuery);

        // Use JMX query expressions
        QueryExp queryExp = Query.eq(Query.attr("name"), Query.value(topicName));

        ObjectName objName;
        Set<ObjectInstance> mbeans = null;
        try {
            objName = new ObjectName(topicsQuery);
            System.out.println("Querying for " + topicName);
            mbeans = conn.queryMBeans(objName, queryExp);
        }
        catch (MalformedObjectNameException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
        catch (NullPointerException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }

        return mbeans;
    }

    private Set<ObjectInstance> queryForQueue(MBeanServerConnection conn, String queueName) throws IOException {
        // Was the topic created?
        String topicsQuery = "org.apache.activemq:brokerName=localhost,type=Broker,destinationType=Queue,destinationName=";
        //listMBeans(conn, topicsQuery);

        // Use JMX query expressions
        //QueryExp queryExp = Query.eq(Query.attr("Name"), Query.value(queueName));

        ObjectName objName;
        Set<ObjectInstance> mbeans = null;
        try {
            objName = new ObjectName(topicsQuery + queueName);
            System.out.println("Querying for " + queueName);
            //mbeans = conn.queryMBeans(objName, queryExp);
            mbeans = conn.queryMBeans(objName, objName);
        }
        catch (MalformedObjectNameException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
        catch (NullPointerException e) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }

        return mbeans;
    }

    private void listAllMBeanAttrs(MBeanServerConnection conn, Set<ObjectName> names) throws IOException {

        for (Iterator<ObjectName> iter = names.iterator(); iter.hasNext();) {
            ObjectName objName = iter.next();
            System.out.println("+ " + objName);

            MBeanInfo info = getMBeanInfo(conn, objName);

            MBeanAttributeInfo[] attrs = info.getAttributes();

            if (attrs == null)
                continue;

            for (int i = 0; i < attrs.length; ++i) {
                try {
                    Object obj = conn.getAttribute(objName, attrs[i].getName());
                    System.out.println(" - " + attrs[i].getName() + " = " + obj);
                }
                catch (NullPointerException e) {
                    for( StackTraceElement se: e.getStackTrace() )
                        logger.error(se.toString());
                }
                catch (AttributeNotFoundException e) {
                    for( StackTraceElement se: e.getStackTrace() )
                        logger.error(se.toString());
                }
                catch (InstanceNotFoundException e) {
                    for( StackTraceElement se: e.getStackTrace() )
                        logger.error(se.toString());
                }
                catch (MBeanException e) {
                    for( StackTraceElement se: e.getStackTrace() )
                        logger.error(se.toString());
                }
                catch (ReflectionException e) {
                    for( StackTraceElement se: e.getStackTrace() )
                        logger.error(se.toString());
                }
            }
        }
    }

    private void query(MBeanServerConnection conn, QueryExp queryExp) throws IOException {
        System.out.println("Not yet implemented");
    }

    private MBeanInfo getMBeanInfo(MBeanServerConnection conn, ObjectName objName) throws IOException {
        MBeanInfo info = null;

        try {
            info = conn.getMBeanInfo((ObjectName) objName);
        }
        catch (InstanceNotFoundException e1) {
            for( StackTraceElement se: e1.getStackTrace() )
                logger.error(se.toString());
        }
        catch (IntrospectionException e1) {
            for( StackTraceElement se: e1.getStackTrace() )
                logger.error(se.toString());
        }
        catch (ReflectionException e1) {
            for( StackTraceElement se: e1.getStackTrace() )
                logger.error(se.toString());
        }

        return info;
    }

    private Object getMBeanAttr(MBeanServerConnection conn, ObjectName objName, String attrName) throws IOException {
        Object attr = null;

        try {
            attr = conn.getAttribute(objName, attrName);
        }
        catch (InstanceNotFoundException e1) {
            for( StackTraceElement se: e1.getStackTrace() )
                logger.error(se.toString());
        }
        catch (ReflectionException e1) {
            for( StackTraceElement se: e1.getStackTrace() )
                logger.error(se.toString());
        }
        catch (MBeanException e1) {
            for( StackTraceElement se: e1.getStackTrace() )
                logger.error(se.toString());
        }
        catch (AttributeNotFoundException e1) {
            for( StackTraceElement se: e1.getStackTrace() )
                logger.error(se.toString());
        }

        return attr;
    }

    private Set<ObjectName> getAllMBeanNames(MBeanServerConnection conn) throws IOException {
        return conn.queryNames(null, null);
    }

    private static void printArgInfo() {
        System.out.println("Usage: java ActiveMQManager [-lc : list consumers  ]");
        System.out.println("                            [-rc : remove consumers] [queue name, ALL]");
        System.out.println("                            [-bc : browse consumers] [queue name]");
        System.out.println("                            [-lp : list producers  ]");
        System.out.println("                            [-rp : remove producers] [queue name]");
        System.out.println("                            [-bp : browse producers] [queue name, ALL]");
    }

    public static void main(String[] args) {
        if( args.length < 1) {
            printArgInfo();
            return;
        }
        if( !args[0].equals("-lc") && !args[0].equals("-rc") && !args[0].equals("-bc")
         && !args[0].equals("-llc") && !args[0].equals("-lrc") && !args[0].equals("-lbc")
         && !args[0].equals("-lp") && !args[0].equals("-rp") && !args[0].equals("-bp")) {
            printArgInfo();
            return;
        }
        if( args[0].equals("-rc") || args[0].equals("-lrc") || args[0].equals("-rp") ) {
            if( args.length != 2 ) {
                printArgInfo();
                return;
            }
        }
        if( args[0].equals("-bc") || args[0].equals("-lbc") || args[0].equals("-bp") ) {
            if( args.length != 2 ) {
                printArgInfo();
                return;
            }
        }
        try {
            org.apache.log4j.xml.DOMConfigurator.configure(MsgBrokerMain.class.getResource(
                    String.format("/%s/log4j.xml", MsgBrokerConst.SVR_TYPE)));
            InputStream is = MsgBrokerMain.class.getResourceAsStream(
                    String.format("/%s/msg.properties", MsgBrokerConst.SVR_TYPE));
            MsgCommon.msgProps.load(is);
            ActiveMQManager amq = new ActiveMQManager( args[0] );
            if( args[0].equals("-lc") || args[0].equals("-llc") ) {
                amq.listAll( "C" );
            }
            else if( args[0].equals("-lp") ) {
                amq.listAll( "P" );
            }
            else if(args[0].equals("-rc") || args[0].equals("-lrc") ) {
                if( args[1].equals("ALL") )
                    amq.removeAll( "C" );
                else
                    amq.remove( "C", args[1] );
            }
            else if(args[0].equals("-bc") || args[0].equals("-lbc") ) {
                amq.browse( "C", args[1] );
            }
            else if(args[0].equals("-bp") ) {
                amq.browse( "P", args[1] );
            }
        }
        catch( Exception e ) {
            logger.error(e.getMessage());
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
    }
}