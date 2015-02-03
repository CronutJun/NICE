package com.nicetcm.nibsplus.broker.ams.services;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerConst;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.mapper.TRcIniInfMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRcIniInfHisMapper;
import com.nicetcm.nibsplus.broker.ams.model.TRcIniInf;
import com.nicetcm.nibsplus.broker.ams.model.TRcIniInfHis;

@Service("in12004003")
public class In12004003Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In12004003Impl.class);

    @Override
    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

    }

}