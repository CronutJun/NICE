package com.nicetcm.nibsplus.broker.msg;

import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.msg.jmx.MsgBrokerManager;

public class MsgBrokerMain {

    private static MsgBrokerRMIServer rmi;
    private static JMXConnectorServer cs;
    private static MsgBrokerManager   mbean;
    private static ServerSocket       isRun;

    public static final Logger logger = LoggerFactory.getLogger(MsgBrokerMain.class);
    public static String serverNo = "";

    public static MsgBrokerRMIServer getRMI() {
        return rmi;
    }

    public MsgBrokerMain() {
        try {
            /**
             * Lost msg logging
             */
            if( MsgCommon.msgProps.getProperty("file.name.lost", "").length() > 0 ) {
                MsgBrokerLib.lostLogFile = MsgCommon.msgProps.getProperty("file.name.lost");
                MsgBrokerLib.isLostLogging = true;
            }

            /*
             * Spring Component 초기화
             */
            logger.debug("Going to scanning");
            MsgBrokerSpringMain.sprCtx.register(MsgBrokerAppConfig.class);
            //MsgBrokerSpringMain.sprCtx.scan("com.nicetcm.nibsplus.broker.msg.services"); @ComponentScan으로 대체
            MsgBrokerSpringMain.sprCtx.refresh();


            /*
             *  ActiveMQ 초기화
             */
            MsgBrokerQInitializer.getInstance("qconfig.json")
                                 .initProducers()
                                 .initConsumers();
            /*
             * Thread Executor 초기화
             */
            MsgBrokerWorkGroup.getInstance();

//            while( !Thread.interrupted() ) {
//                logger.debug("Going to sleep");
//                Thread.sleep(1000 * 60 * 60);
//            }
//            logger.debug("Out of loop");
            /*
             * RMI Server 초기화
             */
            rmi = new MsgBrokerRMIServer();
            rmi.bind();

            registerMBean();

        }
        catch (Exception e) {
            logger.error("Error raised. Message = {}", e.getMessage() );
            logger.error("              Class = {}", e.getClass().getName() );
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
            new MsgBrokerShutdown().run();
        }
    }

    private void registerMBean() throws Exception {

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName mbeanName = new ObjectName("com.nicetcm.nibsplus.broker.msg.jmx:type=MsgBrokerManager");
        MsgBrokerManager mbean = new MsgBrokerManager();

        mbs.registerMBean(mbean, mbeanName);

    }

    public static void main(String[] args) {

        try {
            Runtime.getRuntime().addShutdownHook( new MsgBrokerShutdown() );

            InputStream is = MsgBrokerMain.class.getResourceAsStream(
                    String.format("/%s/msg.properties", MsgBrokerConst.SVR_TYPE));
            MsgCommon.msgProps.load(is);
            /**
             * 서버번호 Setting
             */
            MsgBrokerMain.serverNo = MsgCommon.msgProps.getProperty("server.number", "0");

            org.apache.log4j.xml.DOMConfigurator.configure(MsgBrokerMain.class.getResource(
                    String.format("/%s/log4j.xml", MsgBrokerConst.SVR_TYPE)));

            /**
             * 중복 검증
             */
            try {
                isRun = new ServerSocket( 10799, 1, InetAddress.getLocalHost() );
            }
            catch( BindException be ) {
                System.out.println("MessageBroker is already running.");
                logger.error("Detect duplicate running..");
                System.exit(0);
            }
            logger.warn("===================== START MSG BROKER =====================");
            //RMISocketFactory.setSocketFactory(new MsgBrokerRMISocketFactory());
            startJMXConnectorServer();

            new MsgBrokerMain();
        }
        catch (Exception err) {
            err.printStackTrace();
        }

    }

    private static void startJMXConnectorServer() throws Exception {

        final int rmiRegistryPort = Integer.parseInt(System.getProperty("com.nicetcm.nibsplus.broker.msg.jmxremote.port","10899"));
        final int rmiServerPort   = rmiRegistryPort - 1;
        final String hostname     = System.getProperty("java.rmi.server.hostname","localhost");

        logger.debug("registry port  = {}", rmiRegistryPort);
        logger.debug("rmiServer port = {} ", rmiServerPort);

        LocateRegistry.createRegistry(rmiRegistryPort);

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        Map<String, Object> env = new HashMap<String, Object>();

        // SSL 사용한다면 아래4라인 사용해야 함
        //SslRMIClientSocketFactory csf = new SslRMIClientSocketFactory();
        //SslRMIServerSocketFactory ssf = new SslRMIServerSocketFactory();
        //env.put(RMIConnectorServer.RMI_CLIENT_SOCKET_FACTORY_ATTRIBUTE, csf);
        //env.put(RMIConnectorServer.RMI_SERVER_SOCKET_FACTORY_ATTRIBUTE, ssf);

        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi://" + hostname + ":" +
                                                rmiServerPort + "/jndi/rmi://" + hostname + ":" +
                                                rmiRegistryPort + "/jmxrmi");

        logger.debug("Local Connection URL: {}", url);
        logger.debug("Creating RMI connector server");
        cs = JMXConnectorServerFactory.newJMXConnectorServer(url, env, mbs);
        cs.start();

    }

    public static void stopJMX() throws Exception {
        cs.stop();
    }

    public static JMXConnectorServer getJMXConnectorServer() {
        return cs;
    }

    public static MsgBrokerManager getMBean() {
        return mbean;
    }

    public static void setMBean(MsgBrokerManager src) {
        mbean = src;
    }

}
