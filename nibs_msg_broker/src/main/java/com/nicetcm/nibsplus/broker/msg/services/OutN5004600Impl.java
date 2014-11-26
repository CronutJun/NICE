package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;

@Service("outN5004600")
public class OutN5004600Impl extends OutMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(OutN5004600Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void outMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        if( !parsed.getString("CM.org_cd").equals(MsgBrokerConst.KFTC_CODE) ) {
            safeData.setNoOutData( true );
        }

        if( parsed.getString("CM.org_cd").equals(MsgBrokerConst.ELAND_CODE) ) {

        }
        else if( parsed.getString("CM.org_cd").equals(MsgBrokerConst.KFTC_CODE) ) {

        }
    }
}
