package com.nicetcm.nibsplus.broker.msg;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;

@RunWith(Suite.class)
@SuiteClasses({  })
public class CmAllTestSuite
{

    protected String getMsg() {return (String)null;}

    public MsgParser getMsgParser(String testMsg) throws Exception {

        //byte[] msg = "020B    HOST   0590201405090000239296180         EM01000130          205099296180         20140509000023201405082359410   23594120186   98          01    107     3   1   WR901_G2     자동출동:오만원부족(현금출금부)                                                                                                                                                                                               0011                                                                                                                     02                                                                                                                                                        "
        //.getBytes();

        byte[] msg = testMsg.getBytes();

        ByteBuffer buf;

        byte[] bMsgType = new byte[4];
        byte[] bWrkType = new byte[4];
        String inQNm;

        buf = ByteBuffer.allocateDirect(msg.length);
        buf.put(msg);
        buf.position(51);
        buf.get(bMsgType);
        buf.get(bWrkType);
        buf.position(0);

        /*
         * 응답 전문의 경우에 스키마 파일은 원본 요청 전문에 해당하는 스키마를 읽도록 한다.
         */
        if( bMsgType[2] == '1') {
            bMsgType[2] = '0';
        }

        inQNm = MsgCommon.msgProps.getProperty("schema_path") + new String(bMsgType) + new String(bWrkType) + ".json";

        return MsgParser.getInstance(inQNm).parseMessage(buf);
    }
}
