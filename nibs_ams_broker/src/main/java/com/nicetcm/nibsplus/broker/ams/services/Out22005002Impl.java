package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * Out22005002Impl
 *
 *   프로그램파일 다운로드 요청
 *
 *
 * @author  K.D.J
 * @since   2014.09.04
 */

import java.io.FileInputStream;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.nicetcm.nibsplus.broker.ams.AMSBrokerLib.ROOT_FILE_PATH;
import static com.nicetcm.nibsplus.broker.ams.AMSBrokerLib.TEMP_FILE_PATH;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqInfo;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;

@Service("out22005002")
public class Out22005002Impl implements OutMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(Out22005002Impl.class);

    @Override
    public void outMsgHandle(MsgParser outMsg, AMSBrokerData safeData, AMSBrokerReqJob reqJob, AMSBrokerReqInfo reqInfo, TRmMsg msg) throws Exception {

        if( "N/A".equals(reqJob.getFileName()) ) {
            throw new Exception("다운로드할 버전 없슴.");
        }
        File f = new File(String.format("%s%s%s", ROOT_FILE_PATH, reqJob.getFilePath(), reqJob.getFileName()) );
        FileInputStream fIn = new FileInputStream(f);

        logger.warn("reqJob = {}", reqJob.getFileName() );
        reqInfo.getMsg().position(0);
        outMsg.setString( "CM._AOCMsgCode",           msg.getMsgCd())
              .setString( "CM._AOCServiceCode",       msg.getSvcCd())
              .setString( "CM._AOCMsgSendDate",       safeData.getMsgDate() )
              .setString( "CM._AOCMsgSendTime",       safeData.getMsgTime() )
              .setLong  ( "CM._AOCMsgLen",            (outMsg.getMessageLength() + f.length()) - 9 )
              .setString( "CM._AOCTranNo",            msg.getMsgSeq() )
              .setString( "_AOCDownFileType",         "9"                     )
              .setString( "_AOCDownFileName",         reqJob.getFileOrgName() )
              .setLong  ( "_AOCDownFileSize",         f.length()  )
              .syncMessage();

        reqInfo.setStrm( fIn );
    }

}
