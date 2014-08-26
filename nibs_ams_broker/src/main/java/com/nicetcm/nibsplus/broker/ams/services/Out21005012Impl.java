package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * Out21005012Impl
 *
 *   특정파일 다운로드 요청
 *
 *
 * @author  K.D.J
 * @since   2014.08.21
 */

import java.io.FileInputStream;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqInfo;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;

@Service("out21005012")
public class Out21005012Impl implements OutMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(Out21005012Impl.class);

    @Override
    public void outMsgHandle(MsgParser outMsg, AMSBrokerData safeData, AMSBrokerReqJob reqJob, AMSBrokerReqInfo reqInfo, TRmMsg msg) throws Exception {
        File f = new File("D:\\CronutWorks\\NICE\\Documents\\Design\\05. AMS\\ams_server_src.zip");
        FileInputStream fIn = new FileInputStream(f);

        reqInfo.getMsg().position(0);
        outMsg.setString( "CM._AOCMsgCode",           msg.getMsgCd())
              .setString( "CM._AOCServiceCode",       msg.getSvcCd())
              .setString( "CM._AOCMsgSendDate",       safeData.getMsgDate() )
              .setString( "CM._AOCMsgSendTime",       safeData.getMsgTime() )
              .setLong  ( "CM._AOCMsgLen",            outMsg.getMessageLength() - 9 )
              .setString( "CM._AOCTranNo",            msg.getMsgSeq() )
              .setString( "_AOCDownFileSavePath",     reqJob.getFilePath() )
              .setString( "_AOCDownFileName",         reqJob.getFileName() )
              .setLong  ( "_AOCDownFileSize",         f.length()  )
              .syncMessage();

        reqInfo.setStrm( fIn );
    }

}
