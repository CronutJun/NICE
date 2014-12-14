package com.nicetcm.nibsplus.broker.msg.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;

@Service("out03001130")
public class Out03001130Impl extends OutMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(Out03001130Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void outMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        String command = "";

        if( parsed.getString("CM.org_cd").equals(MsgBrokerConst.TAXRF_CODE)
        &&  parsed.getString("close_type").equals("2") ) {
            command = String.format("ivkAutoSend SqlService SP_FN_KTIS_CLOSE SH %s^%s^%s^%s^%s",
                    parsed.getString("inq_date"), parsed.getString("CM.org_cd"),
                    parsed.getString("brch_cd"),  parsed.getString("mac_no"),
                    parsed.getString("close_type") );
        }
        else return;

        /**
         * Command 실행
         */
        if( command.length() > 0 ) {
            if( !MsgBrokerLib.execUnixCommand( command ) )
                throw new Exception(String.format("명령 [%s] 실행 오류", command));
        }

        safeData.setNoOutData( true );

        /*
         * Client 응답, (대외기관 요청하지 않고 통신서버에서 응답처리)
         */
        parsed.setString("CM.ret_cd_src", "S");
        parsed.setString("CM.ret_cd",     "00");
        ByteBuffer buf = parsed.getMessage();
        buf.position(0);
        byte[] msg = new byte[buf.limit()];
        buf.get(msg);
        safeData.setRespMsg(msg);
    }
}
