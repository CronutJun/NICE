package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;

@Service("respAckNak")
public class RespAckNakImpl implements RespAckNakHandler {

    private static final Logger logger = LoggerFactory.getLogger(RespAckNakImpl.class);
    
    @Override
    public void procAckNak(MsgBrokerData safeData, MsgParser parsed, int errorCode) throws Exception {
        
        String sMsgType = parsed.getString("CM.msg_type");
        
        if( parsed.getResponseInfo().getType().equals("COMMON") ) {
            logger.debug("resonse is COMMON");
        }
        parsed.setString( "CM.msg_type",   sMsgType.substring(0, 2) + MsgBrokerConst.ANS_CODE );
        parsed.setString( "CM.ret_cd_src", "S");
        switch (errorCode) {
            case 0: 
                parsed.setString( "CM.ret_cd",     MsgBrokerConst.ERROR_NORMAL);
                break;
            case -2:
                parsed.setString( "CM.ret_cd",     MsgBrokerConst.ERROR_FINISH_ERRDATA );
                break;
            case -3:
                parsed.setString( "CM.ret_cd",     MsgBrokerConst.ERROR_NO_MACINFO );
                break;
            case -11:
                parsed.setString( "CM.ret_cd",     MsgBrokerConst.ERROR_DATA_LENGTH );
                break;
            case -8:
                parsed.setString( "CM.ret_cd",     MsgBrokerConst.ERROR_DELETE );
                break;
            case -12:
                parsed.setString( "CM.ret_cd",     MsgBrokerConst.ERROR_DUP_ERROR );
                break;
            case -91:
                parsed.setString( "CM.ret_cd",     MsgBrokerConst.ERROR_NO_DATA );
                break;
            case -7:
                parsed.setString( "CM.ret_cd",     MsgBrokerConst.ERROR_MISMATCH_FILECNT );
                break;
            case -85:
                parsed.setString( "CM.ret_cd",     MsgBrokerConst.ERROR_ILL_BRANCH );
                break;
            default:
                parsed.setString( "CM.ret_cd",     MsgBrokerConst.ERROR_ETC );
                break;
        }
    }
}
