package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtPenaltyListMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtPenaltyList;
import com.nicetcm.nibsplus.broker.msg.model.TCtPenaltyListSpec;

/**
 *
 * 0220 패널티 적용 명세 (Outbound 응답 K.D.J - 2014/11/20)
 * <pre>
 * MngIQ_SavePenaltyList( pRecvData, nLen );
 * </pre>
 *
 * @author K.D.J
 * @version 1.0
 * @see
 */
@Service("in04100220")
public class In04100220Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In04100220Impl.class);

    @Autowired private In04000220Impl in04000220;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        in04000220.inMsgBizProc(safeData, parsed);

    }//end method
}
