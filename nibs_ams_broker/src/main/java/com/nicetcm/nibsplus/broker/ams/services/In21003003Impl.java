package com.nicetcm.nibsplus.broker.ams.services;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.mapper.*;
import com.nicetcm.nibsplus.broker.ams.model.*;
import com.nicetcm.nibsplus.broker.common.MsgParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("in21003003")
public class In21003003Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In21003003Impl.class);

    @Override
    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

        logger.debug("file_loc = " + fileLoc);

        //TJnTranMapper map = sqlSession.getMapper(TJnTranMapper.class);
        //TJnTran jn = new TJnTran();
        //map.insert(jn);

    }
}
