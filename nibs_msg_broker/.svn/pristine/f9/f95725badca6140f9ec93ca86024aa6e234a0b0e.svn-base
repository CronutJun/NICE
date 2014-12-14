package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;

/**
 *
 * 1230 관리자 메세지
 * <pre>
 * MngIQ_SaveAdminMessage( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04001230")
public class In04001230Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In04001230Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        logger.warn( String.format(">>> 관리자 메시지 수신 message_gb[%.*s] message[%.*s] 작성자명[%.*s] 연락처[%.*s]", parsed.getString("message_gb"), parsed.getString("message"), parsed.getString("write_nm"), parsed.getString("write_tel_no")) );
    }
}
