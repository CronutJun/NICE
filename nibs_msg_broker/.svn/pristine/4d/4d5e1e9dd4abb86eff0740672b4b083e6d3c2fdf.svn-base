package com.nicetcm.nibsplus.broker.msg;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
 

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
 
/*
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
*/
 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Programmatic JMX example
 * @author bsnyder 
 */
public class ActiveMQManager /* extends TestCase */{
    private static final Logger logger = LoggerFactory.getLogger(ActiveMQManager.class);

 
    public String amqJmxUrl = "service:jmx:rmi:///jndi/rmi://10.3.28.62:1099/jmxrmi";
 
    /**
     * Create the test case
     * 
     * @param testName name of the test case
     */
    public ActiveMQManager(String testName) {
        //super(testName);
    }
 
    /**
     * @return the suite of tests being tested
    public static Test suite() {
        return new TestSuite(JmxExampleTest.class);
    }
     */
 
    public void testApp() {
        MBeanServerConnection connection;
        
        String clientServiceName = "org.apache.activemq:brokerName=localhost,type=Broker";
        
        String topicName = "Test.Msg";
        
        try {
            // Acquire a connection to the MBean server
            connection = connect();
            
            // How many MBeans are running? 
            count(connection);
            
            // Query for a single MBean 
            logger.debug("Query single");
            query(connection, clientServiceName);
            
            Set mbeans = queryForQueue(connection, "*");
            logger.debug(String.valueOf(mbeans.size()));
            for(Iterator iter = mbeans.iterator(); iter.hasNext(); ) {
                ObjectInstance que = (ObjectInstance) iter.next();
                logger.info("+ " + que.getObjectName().getKeyProperty("destinationName"));
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
            
            logger.info("Located [" + mbeans.size() + "] MBeans");
            
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public MBeanServerConnection connect()  throws IOException {
        JMXConnector connector = null;
        MBeanServerConnection connection = null;
 
        String username = "";
 
        String password = "";
 
        Map env = new HashMap();
        String[] credentials = new String[] { username, password };
        env.put(JMXConnector.CREDENTIALS, credentials);
 
        try {
            connector = JMXConnectorFactory.newJMXConnector(new JMXServiceURL(amqJmxUrl), env);
            connector.connect();
            connection = connector.getMBeanServerConnection();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return connection; 
    }
    
    public void count(MBeanServerConnection conn) throws IOException {
        int numberOfMBeans = conn.getMBeanCount().intValue();
        logger.info("Number of MBeans currently running: " + numberOfMBeans);
    }
    
    public void query(MBeanServerConnection conn, String query)
        throws IOException {
        if (conn != null && query.length() > 0) {
            listMBeans(conn, query);
        } else if (conn != null && query.length() == 0) {
            listAllMBeanNames(conn);
        } else {
            logger.error("Unable to connect to ServiceMix");
        }
    }
    
    public void listMBeans(MBeanServerConnection conn, String query) throws IOException {
        ObjectName name;
        Set names = null; 
        try {
            name = new ObjectName(query);
            names = conn.queryMBeans(name, name);
            logger.debug("listMBeans = " + names.size());
        } catch (MalformedObjectNameException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        for(Iterator iter = names.iterator(); iter.hasNext(); ) {
            ObjectInstance obj = (ObjectInstance) iter.next();
            logger.info("+ " + obj.getClassName());
        }
    }
    
    public void listAllMBeanNames(MBeanServerConnection conn) throws IOException {
        Set names = getAllMBeanNames(conn);
 
        for (Iterator iter = names.iterator(); iter.hasNext();) {
            ObjectName objName = (ObjectName) iter.next();
            logger.info("+ " + objName + ", Domain = " + objName.getDomain());
        }
    }
    
    public void listMBeanAttrs(MBeanServerConnection conn, String query) throws IOException {
        ObjectName objName = null;
        try {
            objName = new ObjectName(query);
            logger.info("+ " + objName.getCanonicalName()); 
            
            MBeanInfo info = getMBeanInfo(conn, objName); 
            MBeanAttributeInfo[] attrs = info.getAttributes(); 
            
            for(int i = 0; i < attrs.length; ++i) {
                Object obj = conn.getAttribute(objName, attrs[i].getName());
                logger.info("  - " + attrs[i].getName() + obj);
            }
        } catch (MalformedObjectNameException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (AttributeNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstanceNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MBeanException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ReflectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void createTopic(MBeanServerConnection conn, String topicName)
        throws IOException {
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
            logger.info( verb + "ing new topic: [" + topicName + "]");
            brokerObjName = new ObjectName(brokerNameQuery); 
            conn.invoke(brokerObjName, operationName, params, sig);
            logger.info("Topic [" + topicName + "] has been " + verb);
        } catch (MalformedObjectNameException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstanceNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MBeanException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ReflectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
 
    private void printMBeans(String topicName, Set mbeans) {
        if (!mbeans.isEmpty()) {
            for (Iterator iter = mbeans.iterator(); iter.hasNext();) {
                ObjectInstance mbean = (ObjectInstance) iter.next();
                logger.info("+ " + mbean.getClassName());
            }
        } 
        else {
            logger.info("Unable to locate MBean for " + topicName);
        }
    }
 
    public Set queryForTopic(MBeanServerConnection conn, String topicName) throws IOException {
        // Was the topic created?  
        String topicsQuery = "org.apache.activemq:BrokerName=localhost,Type=Topic,*";
        // listMBeans(conn, topicsQuery);
 
        // Use JMX query expressions
        QueryExp queryExp = Query.eq(Query.attr("name"), Query.value(topicName)); 
 
        ObjectName objName;
        Set mbeans = null; 
        try {
            objName = new ObjectName(topicsQuery);
            logger.info("Querying for " + topicName);
            mbeans = conn.queryMBeans(objName, queryExp);       
        } catch (MalformedObjectNameException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return mbeans;
    }
 
    public Set queryForQueue(MBeanServerConnection conn, String queueName) throws IOException {
        // Was the topic created?  
        String topicsQuery = "org.apache.activemq:brokerName=localhost,type=Broker,destinationType=Queue,destinationName=";
        //listMBeans(conn, topicsQuery);
 
        // Use JMX query expressions
        //QueryExp queryExp = Query.eq(Query.attr("Name"), Query.value(queueName)); 
 
        ObjectName objName;
        Set mbeans = null; 
        try {
            objName = new ObjectName(topicsQuery + queueName);
            logger.info("Querying for " + queueName);
            //mbeans = conn.queryMBeans(objName, queryExp);       
            mbeans = conn.queryMBeans(objName, objName);       
        } catch (MalformedObjectNameException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return mbeans;
    }
 
    public void listAllMBeanAttrs(MBeanServerConnection conn, Set names) throws IOException {
 
        for (Iterator iter = names.iterator(); iter.hasNext();) {
            ObjectName objName = (ObjectName) iter.next();
            logger.info("+ " + objName);
 
            MBeanInfo info = getMBeanInfo(conn, objName);
            
            MBeanAttributeInfo[] attrs = info.getAttributes();
 
            if (attrs == null)
                continue;
 
            for (int i = 0; i < attrs.length; ++i) {
                try {
                    Object obj = conn.getAttribute(objName, attrs[i].getName());
                    logger.info(" - " + attrs[i].getName() + " = " + obj);
                } catch (NullPointerException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (AttributeNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InstanceNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (MBeanException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ReflectionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void query(MBeanServerConnection conn, QueryExp queryExp) throws IOException {
        logger.info("Not yet implemented"); 
    }
    
    // Private ----------------------------------------------------------------
 
    private MBeanInfo getMBeanInfo(MBeanServerConnection conn, ObjectName objName) throws IOException {
        MBeanInfo info = null;
        
        try {
            info = conn.getMBeanInfo((ObjectName) objName);
        } catch (InstanceNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IntrospectionException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ReflectionException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        return info;
    }
    
    private Set getAllMBeanNames(MBeanServerConnection conn) throws IOException {
        return conn.queryNames(null, null);
    }
    
    public static void main(String[] args) {
        ActiveMQManager aqm = new ActiveMQManager("test");
        aqm.testApp();
    }
}