package com.nicetcm.nibsplus.broker.ams;

import java.io.*;

import com.nicetcm.nibsplus.broker.common.*;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerConst;

public class AMSBrokerMain extends Thread {

    private String jobName;
    private AMSBrokerServer svr;
    private AMSBrokerRMIServer rmi;

    public AMSBrokerMain(String job) {

        System.out.println("properties = " + MsgCommon.msgProps.getProperty("schema_path"));
        this.jobName = job;
        if( jobName.equals("SERVER") ) {
            svr = new AMSBrokerServer(8080);
        }
        else if( jobName.equals("RMI") ) {
            rmi = new AMSBrokerRMIServer();
        }
    }

    public void run() {

        if( jobName.equals("SERVER") ) {
            try {
                svr.run();
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
        }
        catch (Exception err) {
            err.printStackTrace();
        }

        AMSBrokerMain svr = new AMSBrokerMain("SERVER");
        AMSBrokerMain rmi = new AMSBrokerMain("RMI");

        AMSBrokerSpringMain.sprCtx.register(AMSBrokerAppConfig.class);
        //AMSBrokerSpringMain.sprCtx.scan("com.nicetcm.nibsplus.broker.ams.services");
        AMSBrokerSpringMain.sprCtx.refresh();

        svr.start();
        rmi.start();
    }

}
