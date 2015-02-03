package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * In12005011Impl
 *
 *  일반파일 업로드 처리
 *
 *
 * @author  K.D.J
 * @since   2015.02.02
 */


import java.io.File;

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

@Service("in12005011")
public class In12005011Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In12005011Impl.class);

    @Override
    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {
        try {
            if( fileLoc == null || fileLoc.length() == 0 ) {
                logger.warn("There's no general file. skip file processing..");
                return;
            }
            File genFile = new File(fileLoc);
            if( !genFile.exists() ) {
                logger.warn("There's no general file. skip file processing..");
                return;
            }
            if( !parsed.getString("_AOCUpFileMD5").equals(reqJob.getUpMD5Checksum()) ) {
                logger.warn("ATM MD5 = {}, AOC MD5 = {} is not equal", parsed.getString("_AOCUpFileMD5"), reqJob.getUpMD5Checksum() );
                reqJob.setUpMD5Result(false);
                return;
            }
        }
        catch( Exception e ) {
            logger.warn("In12005011Impl has error :  {} ", e.getMessage());
            throw e;
        }
    }

}
