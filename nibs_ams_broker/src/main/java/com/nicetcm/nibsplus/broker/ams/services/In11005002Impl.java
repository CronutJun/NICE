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

        // 제조사,기종 정보를 따고,
        // parsed.getString("_APVersion") 정보 이후의 DB를 검색하여 기기버전 이후의 최초버전을 선택하여 내려보낸다.
        // 내려보낼 수 없는 상황이면.. reqJob.setFileName("N/A")를 셋하고 out22005002Impl에서 응답처리토록 한다.
        reqJob.setFileName("Test.zip");
        logger.debug("reqJob FileName = {}", reqJob.getFileName());
    }
}
