package com.nicetcm.nibsplus.broker.ams;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * AMSBrokerMain
 *
 *  AMSBroker의 시작을 관리
 *
 *
 * @author  K.D.J
 * @since   2014.06.29
 */


import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.ams.jmx.AMSBrokerManager;
import com.nicetcm.nibsplus.broker.ams.services.InitScheduler;
import com.nicetcm.nibsplus.broker.common.MsgCommon;


public class AMSBrokerMain extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerMain.class);

    private static AMSBrokerServer svr;
    private static AMSBrokerRMIServer rmi;
    private static Scheduler sched;
    private static JMXConnectorServer cs;

    private String jobName;

    public static String serverNo = "";

    public static AMSBrokerRMIServer getRMI() {
        return rmi;
    }

    public static Scheduler getScheduler() {
        return sched;
    }

    public AMSBrokerMain(String job) {

        this.jobName = job;
        this.setName(job);
        if( jobName.equals("SERVER") ) {
            logger.warn("properties = " + MsgCommon.msgProps.getProperty("schema_path"));
            svr = AMSBrokerServer.getServer(Integer.parseInt(MsgCommon.msgProps.getProperty("ams.port")));
        }
        else if( jobName.equals("RMI") ) {
            rmi = new AMSBrokerRMIServer();
        }
        else if( jobName.equals("SCHEDULE") ) {
            try {
                sched = new StdSchedulerFactory().getScheduler();
            }
            catch( SchedulerException se ) {
                logger.warn( "Schedule Exception: {}", se.getMessage() );
            }
        }
    }

    public void run() {

        if( jobName.equals("SERVER") ) {
            try {
                registerMBean();
                svr.run();
                logger.warn("Soket server going to stop..");
            }
            catch( Exception err) {
                err.printStackTrace();
            }
        }
        else if( jobName.equals("RMI") ) {
            try {
                rmi.bind();
            }
            catch( Exception err) {
                err.printStackTrace();
            }
        }
        else if( jobName.equals("SCHEDULE") ) {
            try {
                sched.start();
                initSchedule();
            }
            catch( Exception err) {
                err.printStackTrace();
            }
        }
    }

    private void registerMBean() throws Exception {

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        ObjectName mbeanName = new ObjectName("com.nicetcm.nibsplus.broker.ams.jmx:type=AMSBrokerManager");
        AMSBrokerManager mbean = new AMSBrokerManager();

        mbs.registerMBean(mbean, mbeanName);

    }

    private void initSchedule() {

        AMSBrokerData safeData = new AMSBrokerData();
        InitScheduler initSched = (InitScheduler)AMSBrokerSpringMain.sprCtx.getBean("initScheduler");
        try {
            initSched.initSchedule(safeData, "A");
        }
        catch( Exception e ) {
            logger.error("Error raised. Message = {}", e.getMessage() );
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
    }

    public static void main(String[] args) {

        try {
            Runtime.getRuntime().addShutdownHook( new AMSBrokerShutdown() );

            InputStream is = AMSBrokerMain.class.getResourceAsStream(
                    String.format("/%s/ams.properties", AMSBrokerConst.SVR_TYPE));
            MsgCommon.msgProps.load(is);
            /**
             * 서버번호 Setting
             */
            AMSBrokerMain.serverNo = MsgCommon.msgProps.getProperty("server.number", "0");

            org.apache.log4j.xml.DOMConfigurator.configure(AMSBrokerMain.class.getResource(
                    String.format("/%s/log4j.xml", AMSBrokerConst.SVR_TYPE)));

            MsgCommon.READ_BUF_SIZE = Integer.parseInt(MsgCommon.msgProps.getProperty("read_buf_size"));
            AMSBrokerLib.ROOT_FILE_PATH  = MsgCommon.msgProps.getProperty("root.file.path", "");
            AMSBrokerLib.TEMP_FILE_PATH  = MsgCommon.msgProps.getProperty("temp.file.path", "");
            AMSBrokerLib.FILE_RETRY_COUNT = Integer.parseInt(MsgCommon.msgProps.getProperty("file.retry.count", "3"));
            AMSBrokerLib.FILE_MD5_RETRY_COUNT = Integer.parseInt(MsgCommon.msgProps.getProperty("file.md5.retry.count", "3"));

            startJMXConnectorServer();
        }
        catch (Exception err) {
            err.printStackTrace();
        }

        AMSBrokerMain svrT   = new AMSBrokerMain("SERVER");
        AMSBrokerMain rmiT   = new AMSBrokerMain("RMI");
        AMSBrokerMain schedT = new AMSBrokerMain("SCHEDULE");

        AMSBrokerSpringMain.sprCtx.register(AMSBrokerAppConfig.class);
        //AMSBrokerSpringMain.sprCtx.scan("com.nicetcm.nibsplus.broker.ams.services"); AppConfig @ComponentScan으로 대체
        AMSBrokerSpringMain.sprCtx.refresh();

        svrT.start();
        rmiT.start();
        schedT.start();
    }

    private static void startJMXConnectorServer() throws Exception {

        final int rmiRegistryPort = Integer.parseInt(System.getProperty("com.nicetcm.nibsplus.broker.ams.jmxremote.port","10999"));
        final int rmiServerPort   = rmiRegistryPort - 1;
        final String hostname     = System.getProperty("java.rmi.server.hostname","localhost");

        logger.warn("registry port  = {}", rmiRegistryPort);
        logger.warn("rmiServer port = {} ", rmiServerPort);

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

        logger.warn("Local Connection URL: {}", url);
        logger.warn("Creating RMI connector server");
        cs = JMXConnectorServerFactory.newJMXConnectorServer(url, env, mbs);
        cs.start();

    }

    public static void stopJMX() throws Exception {
        cs.stop();
    }


}
