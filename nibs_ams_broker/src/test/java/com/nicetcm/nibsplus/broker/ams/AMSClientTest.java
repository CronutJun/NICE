package com.nicetcm.nibsplus.broker.ams;

import java.io.InputStream;
import java.nio.ByteBuffer;

import com.nicetcm.nibsplus.broker.common.*;
import com.nicetcm.nibsplus.broker.ams.*;

public class AMSClientTest {

    public AMSClientTest() {
        try {
            InputStream is = AMSBrokerMain.class.getResourceAsStream(
                    String.format("/%s/ams.properties", AMSBrokerConst.SVR_TYPE));
            MsgCommon.msgProps.load(is);
            MsgCommon.READ_BUF_SIZE = Integer.parseInt(MsgCommon.msgProps.getProperty("read_buf_size"));
        }
        catch( Exception e ) {
            MsgCommon.READ_BUF_SIZE = 2000;
        }

    }

    public void sendOpenMacToSvr() throws Exception {
        java.util.Date dt = new java.util.Date();

        ByteBuffer buf = ByteBuffer.allocate(MsgCommon.READ_BUF_SIZE);
        MsgParser msgPsr = MsgParser.getInstance(String.format("%s%s%s.json", MsgCommon.msgProps.getProperty("schema_path"), "1100", "1001"))
                                    .newMessage(buf);
        try {
            System.out.println("Message Length = "  + msgPsr.getMessageLength());
            System.out.println("Last Position = "   + msgPsr.lastPosition());
            buf.limit(msgPsr.lastPosition());
            msgPsr.setInt   ( "CM._AOCMsgLen",        msgPsr.getMessageLength() - 9 )
                  .setString( "CM._AOCMsgCode",       "1100" )
                  .setString( "CM._AOCServiceCode",   "1001" )
                  .setString( "CM._AOCMsgSendDate",   AMSBrokerLib.getMsgDate(dt) )
                  .setString( "CM._AOCMsgSendTime",   AMSBrokerLib.getMsgTime(dt)  )
                  .setString( "CM._AOCMsgSerialNo",   "000001" )
                  .setString( "CM._SSTNo",            "960202" )
                  .setString( "CM._SSTSerialNo",      "12345678" )
                  .setString( "CM._BranchCode",       "9600" )
                  .setString( "CM._BankCode",         "096" )
                  .setString( "NT._SSTStatus",        "0"   )
                  .setString( "NT._HCCstDevCode",     " "   )
                  .setString( "NT._ACCstDevCode",     " "   )
                  .setString( "NT._APVersion",        " "   )
                  .setString( "NT._SSTType",          " "   )
                  .setString( "NT._SSTMakerCode",     " "   )
                  .setString( "NT._SSTMaufactureNo",  " "   )
                  .setString( "NT._SSTIp",            " "   )
                  .setString( "NT._SSTSubIp",         " "   )
                  .syncMessage();


            byte[] read = new byte[buf.limit()];
            buf.position(0);
            buf.get(read);
            System.out.println(new String(read));

            AMSBrokerClient client = AMSBrokerClient.getInstance( "10.3.28.180", 33001, null );
            ByteBuffer rslt = client.outboundCall( buf, null, 10 );
        }
        finally {
            msgPsr.clearMessage();
        }
    }

    public static void main(String args[]) {
        try {
            new AMSClientTest().sendOpenMacToSvr();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
