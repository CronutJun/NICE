package com.nicetcm.nibsplus.broker.msg.services;

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

@Service("outN5004600")
public class OutN5004600Impl extends OutMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(OutN5004600Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void outMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        String command = "";

        if( parsed.getString("CM.org_cd").equals(MsgBrokerConst.ELAND_CODE) ) {
            /*
             * File처리 ...
             *
             */
            if( parsed.getString("work_cd").equals(MsgBrokerConst.WORKCD_COUPON_ELAND)
            &&  parsed.getString("proc_cd").equals(MsgBrokerConst.PROCCD_AUTOSEND) ) {
                command = "ivkAutoSend OrgSend CLOSE_COUPON 0EL";
            }

        }
        else if( parsed.getString("CM.org_cd").equals(MsgBrokerConst.KFTC_CODE) ) {
            if( parsed.getString("work_cd").equals(MsgBrokerConst.WORKCD_DIFF_KFTC)
            &&  parsed.getString("proc_cd").equals(MsgBrokerConst.PROCCD_FILESEND) ) {
                command = "ivkAutoSend FilemngService FILE_SEND_0GV SH 000";
            }
        }

        /**
         * Command 실행
         */
        if( command.length() > 0 ) {
            if( !MsgBrokerLib.execUnixCommand( command ) )
                throw new Exception(String.format("명령 [%s] 실행 오류", command));
        }

        /**
         *  금결원을 제외한 기관은 WEB으로 바로 응답
         */
        if( !parsed.getString("CM.org_cd").equals(MsgBrokerConst.KFTC_CODE) ) {
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
}
