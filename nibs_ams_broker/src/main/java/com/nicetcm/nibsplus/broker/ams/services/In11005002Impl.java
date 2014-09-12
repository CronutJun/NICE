package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * In11005002Impl
 *
 *  프로그램 파일 다운로드 요청
 *
 *
 * @author  K.D.J
 * @since   2014.09.04
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.model.TRmTrx;
import com.nicetcm.nibsplus.broker.common.MsgParser;

@Service("in11005002")
public class In11005002Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In11005002Impl.class);

    @Override
    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

        // 여기서 버전정보와 DB버전을 비교하여 내려보낼지 말지를 처리한다.
        reqJob.setFileName("Test.zip");
        logger.debug("reqJob FileName = {}", reqJob.getFileName());
    }
}
