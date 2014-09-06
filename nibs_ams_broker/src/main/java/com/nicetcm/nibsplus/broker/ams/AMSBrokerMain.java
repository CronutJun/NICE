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


import java.io.*;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.nicetcm.nibsplus.broker.common.*;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerConst;
import com.nicetcm.nibsplus.broker.ams.jmx.AMSBrokerManager;
import com.nicetcm.nibsplus.broker.ams.services.InitScheduler;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AMSBrokerMain extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerMain.class);

    private static AMSBrokerServer svr;
    private static AMSBrokerRMIServer rmi;
    private static Scheduler sched;

    private String jobName;

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
            logger.debug("properties = " + MsgCommon.msgProps.getProperty("schema_path"));
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
                logger.debug( "Schedule Exception: {}", se.getMessage() );
            }
        }
    }

    public void run() {

        if( jobName.equals("SERVER") ) {
            try {
                registerMBean();
                svr.run();
                logger.debug("Soket server going to stop..");
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
            initSched.initSchedule(safeData);
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            //FileInputStream file = new FileInputStream( sys_prop_loc + "ams.properties");
            org.apache.log4j.xml.DOMConfigurator.configure(AMSBrokerMain.class.getResource(
                    String.format("/%s/log4j.xml", AMSBrokerConst.SVR_TYPE)));
            InputStream is = AMSBrokerMain.class.getResourceAsStream(
                    String.format("/%s/ams.properties", AMSBrokerConst.SVR_TYPE));
            MsgCommon.msgProps.load(is);
            MsgCommon.READ_BUF_SIZE = Integer.parseInt(MsgCommon.msgProps.getProperty("read_buf_size"));
            AMSBrokerLib.ROOT_FILE_PATH  = MsgCommon.msgProps.getProperty("root.file.path", "");
            AMSBrokerLib.TEMP_FILE_PATH  = MsgCommon.msgProps.getProperty("temp.file.path", "");
        }
        catch (Exception err) {
            err.printStackTrace();
        }

        AMSBrokerMain svr   = new AMSBrokerMain("SERVER");
        AMSBrokerMain rmi   = new AMSBrokerMain("RMI");
        AMSBrokerMain sched = new AMSBrokerMain("SCHEDULE");

        AMSBrokerSpringMain.sprCtx.register(AMSBrokerAppConfig.class);
        //AMSBrokerSpringMain.sprCtx.scan("com.nicetcm.nibsplus.broker.ams.services"); AppConfig @ComponentScan으로 대체
        AMSBrokerSpringMain.sprCtx.refresh();

        svr.start();
        rmi.start();
        sched.start();
    }

}
