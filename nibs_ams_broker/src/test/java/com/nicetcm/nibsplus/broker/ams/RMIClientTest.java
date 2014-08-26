package com.nicetcm.nibsplus.broker.ams;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Calendar;
import java.io.*;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;

import javax.sql.DataSource;
import java.util.ArrayList;

import com.nicetcm.nibsplus.broker.ams.rmi.*;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmTrxMapper;

public class RMIClientTest {

    private DataSource ds;
    private TransactionFactory trxFactory;
    private Environment env;
    private Configuration config;
    private TypeAliasRegistry aliases;
    private SqlSessionFactory factory;
    private SqlSession session;

    public RMIClientTest() {
        ds = RMIClientTest.getDataSource();
        trxFactory = new JdbcTransactionFactory();
        env = new Environment("dev", trxFactory, ds);
        config = new Configuration(env);
        aliases = config.getTypeAliasRegistry();

        config.addMapper(TRmTrxMapper.class);

        factory = new SqlSessionFactoryBuilder().build(config);
        session = factory.openSession();
    }

    public void threadTest() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");
        Calendar c = Calendar.getInstance();
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("HH:mm:ss.SSS");
        String tm = fmt.format(c.getTime());
        System.out.println(remoteObj.threadTest(tm));
        System.out.println(remoteObj.threadTest(tm));
    }

    public void dataUpload() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        FileInputStream file = new FileInputStream("D:/CronutWorks/NICE/Documents/Design/07. �������/����/P140217_DS 06 ȭ�����Ǽ�_AMS v1.1.pptx");
        boolean isFirst = true;
        boolean hasNext = false;
        while ( file.available() > 0) {
            byte[] data = file.available() > 262144 ? new byte[262144] : new byte[file.available()];
            file.read(data);
            hasNext = file.available() > 0;
            remoteObj.dataUploadToBroker(data, isFirst, hasNext);
            isFirst = false;
        }
        System.out.println("Upload complete");
    }

    public void reqEnvInfToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        TRmTrxMapper trxMap = session.getMapper(TRmTrxMapper.class);

        String key = trxMap.generateKey();

        remoteObj.reqEnvInfToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), key, "720103", "500", "test", "0202", 0 );
    }

    public void reqRegInfToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        TRmTrxMapper trxMap = session.getMapper(TRmTrxMapper.class);

        RMIReqRegInfo reqRegInfo = new RMIReqRegInfo();
        reqRegInfo.setMacNo( "0202" );
        reqRegInfo.setBaseKey( "HKEY_CURRENT_USER" );
        reqRegInfo.setKeyPath( "Software\\ANGARA" );
        reqRegInfo.setKeyName( "TraceFileName" );

        String key = trxMap.generateKey();

        remoteObj.reqRegInfToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), key, "2000", "1000", "test", reqRegInfo, 0 );
    }

    public void reqIniInfToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        TRmTrxMapper trxMap = session.getMapper(TRmTrxMapper.class);

        RMIReqIniInfo reqIniInfo = new RMIReqIniInfo();
        reqIniInfo.setMacNo( "0202" );
        reqIniInfo.setPathName( "C:\\ANGARA\\DATA\\BankName.ini" );
        reqIniInfo.setSection( "BANK" );
        reqIniInfo.setKeyName( "081"  );

        String key = trxMap.generateKey();

        remoteObj.reqIniInfToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), key, "3000", "1000", "test", reqIniInfo, 0 );
    }

    public void reqEnvChgToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        TRmTrxMapper trxMap = session.getMapper(TRmTrxMapper.class);

        ArrayList<RMIEnvValue> envValues = new ArrayList<RMIEnvValue>();

        RMIEnvValue envValue = new RMIEnvValue();
        envValue.setEnvId   ("006");
        envValue.setEnvValue("0");
        envValues.add(envValue);

        envValue = new RMIEnvValue();
        envValue.setEnvId   ("013");
        envValue.setEnvValue("230001");
        envValues.add(envValue);

        String key = trxMap.generateKey();

        remoteObj.reqEnvChgToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), key, "4000", "1000", "test", "0202", envValues );

    }

    public void reqRegChgToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        TRmTrxMapper trxMap = session.getMapper(TRmTrxMapper.class);

        RMIReqRegInfo reqRegInfo = new RMIReqRegInfo();
        reqRegInfo.setMacNo( "0202" );
        reqRegInfo.setBaseKey( "HKEY_CURRENT_USER" );
        reqRegInfo.setKeyPath( "Software\\ANGARAA" );
        reqRegInfo.setKeyName( "TraceFileName" );
        reqRegInfo.setValue( "test.log" );

        String key = trxMap.generateKey();

        remoteObj.reqRegChgToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), key, "5000", "1000", "test", reqRegInfo );
    }

    public void reqIniChgToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        TRmTrxMapper trxMap = session.getMapper(TRmTrxMapper.class);

        RMIReqIniInfo reqIniInfo = new RMIReqIniInfo();
        reqIniInfo.setMacNo( "0202" );
        reqIniInfo.setPathName( "C:\\ANGARA\\DATA\\BankName.ini" );
        reqIniInfo.setSection( "BANK" );
        reqIniInfo.setKeyName( "081"  );
        reqIniInfo.setValue( "김동준" );

        String key = trxMap.generateKey();

        remoteObj.reqIniChgToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), key, "6000", "1000", "test", reqIniInfo );
    }

    public void reqRebootToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        TRmTrxMapper trxMap = session.getMapper(TRmTrxMapper.class);

        String key = trxMap.generateKey();

        remoteObj.reqRebootToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), key, "7000", "501", "test", "0202" );
    }

    public void reqPwroffToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        TRmTrxMapper trxMap = session.getMapper(TRmTrxMapper.class);

        String key = trxMap.generateKey();

        remoteObj.reqPwroffToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), key, "7000", "502", "test", "0202" );
    }

    public void reqDevResetToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        TRmTrxMapper trxMap = session.getMapper(TRmTrxMapper.class);

        String key = trxMap.generateKey();

        remoteObj.reqDevResetToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), key, "7000", "503", "test", "0202", "101" );
    }

    public void reqDevCollectToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        TRmTrxMapper trxMap = session.getMapper(TRmTrxMapper.class);

        String key = trxMap.generateKey();

        remoteObj.reqDevResetToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), key, "7000", "504", "test", "0202", "101" );
    }

    public void reqDevReturnToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        TRmTrxMapper trxMap = session.getMapper(TRmTrxMapper.class);

        String key = trxMap.generateKey();

        remoteObj.reqDevResetToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), key, "7000", "505", "test", "0202", "101" );
    }

    public void reqCallNoticeToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        TRmTrxMapper trxMap = session.getMapper(TRmTrxMapper.class);

        String key = trxMap.generateKey();

        remoteObj.reqCallNoticeToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), key, "7001", "500", "test", "0202", "030101", "01074564016", "233321", 5 );
    }

    public void reqSFileDownToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        TRmTrxMapper trxMap = session.getMapper(TRmTrxMapper.class);

        String key = trxMap.generateKey();

        remoteObj.reqSFileDownToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), key, "7001", "500", "test", "0202", "C:\\", "test.zip" );
    }

    public static void main(String[] args) {
        try {
            new RMIClientTest().reqEnvInfToMac();
            new RMIClientTest().reqRegInfToMac();
            new RMIClientTest().reqIniInfToMac();
            new RMIClientTest().reqEnvChgToMac();
            new RMIClientTest().reqRegChgToMac();
            new RMIClientTest().reqIniChgToMac();
            new RMIClientTest().reqRebootToMac();
            new RMIClientTest().reqPwroffToMac();
            new RMIClientTest().reqDevResetToMac();
            new RMIClientTest().reqDevCollectToMac();
            new RMIClientTest().reqDevReturnToMac();
            new RMIClientTest().reqCallNoticeToMac();
            //new RMIClientTest().reqSFileDownToMac();
        }
        catch( java.rmi.RemoteException e ) {
            System.out.println("Something has gone wrong during remote method call...");
            e.printStackTrace();
        }
        catch( java.rmi.NotBoundException e ) {
            System.out.println("Could't bound...");
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
        dataSource.setUrl("jdbc:db2://10.3.28.63:4892/infodb");
        dataSource.setUsername("nadm");
        dataSource.setPassword("nadm");

        return dataSource;
    }
}
