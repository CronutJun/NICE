package com.nicetcm.nibsplus.filemng.rmi;

import java.nio.ByteBuffer;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.model.MsgBrokerConf;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerCallBack;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerRMI;

public class MsgBrokerCallAgent <PT> {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerCallAgent.class);
    private final MsgBrokerConf conf;
    private final String msgId;
    private final String msgId2 = "ERRMON";
    private PT params;
    private MsgBrokerCallBack<PT> callBack;

    public static Properties msgBrokerConfig = null; // NibsScheduleExecuter, NibsQuartzSchedulerMain 에서 주입

    public MsgBrokerCallAgent( MsgBrokerConf conf, PT params, MsgBrokerCallBack<PT> callBack ) {
        this.conf = conf;
        this.params = params;
        this.callBack = callBack;

        if(conf.getMsgType().substring(2, 3).equals("1")) {
            msgId = conf.getMsgType().substring(0, 2) + "0" + conf.getMsgType().substring(3, 4) + conf.getWorkType();
        } else {
            msgId = conf.getMsgType() + conf.getWorkType();
        }
    }

    private void initMsgPsr(MsgParser msgPsr) throws Exception {
        msgPsr.setString("CM.org_cd", conf.getOrgCd() )
              .setString("CM.ret_cd_src", "S" )
              .setString("CM.msg_id", msgId2 ) // AUTOSEND : AUTOSND, ERRMon : ERRMON
              .setString("CM.format_type", conf.getFormatType() )
              .setString("CM.msg_type", msgId.substring(0, 4) )
              .setString("CM.work_type", conf.getWorkType() )

              .setString("CM.trans_seq_no", conf.getWorkType() )
              .setInt   ("CM.body_len", msgPsr.getMessageLength() - 90 )
              .setString("CM.trans_date", MsgBrokerLib.SysDate() )
              .setString("CM.trans_time", MsgBrokerLib.SysTime() )
              .setString("CM.service_gb", conf.getServiceGb() );
    }

    public void callBrokerSync(int timeout) throws Exception {

        MsgParser msgPsr = MsgParser.getInstance(msgBrokerConfig.getProperty("schema_path")  + this.msgId + ".json");
        ByteBuffer msg = ByteBuffer.allocateDirect( msgPsr.getSchemaLength() );
        msgPsr.newMessage( msg );
        msg.position(0);
        try {
            initMsgPsr(msgPsr); //기본적인 전문에 대한 Setting

            callBack.doPreCallBroker(msgPsr, params);
            logger.debug("After call doPreCallProker === ORG_CD = {}", msgPsr.getString("CM.org_cd"));

            msgPsr.syncMessage();
            byte[] read = new byte[msg.limit()];

            msg.position(0);
            msg.get(read);

            Registry registry = LocateRegistry.getRegistry(msgBrokerConfig.getProperty("msgbroker.server.ip"), Integer.parseInt(msgBrokerConfig.getProperty("msgbroker.server.port")));
            MsgBrokerRMI remoteObj = (MsgBrokerRMI)registry.lookup(msgBrokerConfig.getProperty("msgbroker.lookup.name"));

            byte[] rsltMsg = remoteObj.callBrokerSync(read, timeout);

            msg = ByteBuffer.allocateDirect( rsltMsg.length );
            msg.position(0);
            msg.put(rsltMsg);
            msgPsr.parseMessage(msg);

            callBack.doPostCallBroker(msgPsr, params);
        }
        finally {
            msgPsr.clearMessage();
        }
    }

    public void callBrokerAync() throws Exception {

        //MsgParser msgPsr = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path")  + this.msgId + ".json");
        MsgParser msgPsr = MsgParser.getInstance(msgBrokerConfig.getProperty("schema_path")  + this.msgId + ".json");
        ByteBuffer msg = ByteBuffer.allocateDirect( msgPsr.getSchemaLength() );
        msgPsr.newMessage( msg );
        msg.position(0);
        try {
            initMsgPsr(msgPsr); //기본적인 전문에 대한 Setting

            callBack.doPreCallBroker(msgPsr, params);
            logger.debug("After call doPreCallProker === ORG_CD = {}", msgPsr.getString("CM.org_cd"));

            msgPsr.syncMessage();
            byte[] read = new byte[msg.limit()];

            msg.position(0);
            msg.get(read);

            Registry registry = LocateRegistry.getRegistry(msgBrokerConfig.getProperty("msgbroker.server.ip"), Integer.parseInt(msgBrokerConfig.getProperty("msgbroker.server.port")));
            MsgBrokerRMI remoteObj = (MsgBrokerRMI)registry.lookup(msgBrokerConfig.getProperty("msgbroker.lookup.name"));

            remoteObj.callBrokerAsync(read);
        }
        finally {
            msgPsr.clearMessage();
        }
    }
}
