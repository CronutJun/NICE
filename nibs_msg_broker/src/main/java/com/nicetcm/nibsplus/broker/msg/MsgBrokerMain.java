package com.nicetcm.nibsplus.broker.msg;

import java.io.InputStream;
import java.net.DatagramSocket;
import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.msg.jmx.MsgBrokerManager;
import com.nicetcm.nibsplus.broker.common.MsgCommon;

public class MsgBrokerMain {

    private static MsgBrokerRMIServer rmi;
    private DatagramSocket isRun;

    public static final Logger logger = LoggerFactory.getLogger(MsgBrokerMain.class);

    public static MsgBrokerRMIServer getRMI() {
        return rmi;
    }

    public MsgBrokerMain() {
        try {
            /**
             * 중복 검증
             */
            try {
                isRun = new DatagramSocket(10799);
            }
            catch( Exception e ) {
                logger.error("Detect duplicate running..");
                throw e;
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
            logger.error(e.getMessage());
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
            org.apache.log4j.xml.DOMConfigurator.configure(MsgBrokerMain.class.getResource(
                    String.format("/%s/log4j.xml", MsgBrokerConst.SVR_TYPE)));
            InputStream is = MsgBrokerMain.class.getResourceAsStream(
                    String.format("/%s/msg.properties", MsgBrokerConst.SVR_TYPE));
            MsgCommon.msgProps.load(is);
            new MsgBrokerMain();
        }
        catch (Exception err) {
            err.printStackTrace();
        }

    }

}
