package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * Out21005012Impl
 *
 *   특정파일 다운로드 요청 (프로그램)
 *
 *
 * @author  K.D.J
 * @since   2014.08.28
 */

import static com.nicetcm.nibsplus.broker.ams.AMSBrokerLib.ROOT_FILE_PATH;

import java.io.FileInputStream;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqInfo;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmFileMapper;
import com.nicetcm.nibsplus.broker.ams.model.TRmFile;
import com.nicetcm.nibsplus.broker.ams.model.TRmFileKey;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;

@Service("out21005004")
public class Out21005004Impl implements OutMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(Out21005004Impl.class);

    @Autowired private TRmFileMapper                  fileMap;

    @Override
    public void outMsgHandle(MsgParser outMsg, AMSBrokerData safeData, AMSBrokerReqJob reqJob, AMSBrokerReqInfo reqInfo, TRmMsg msg) throws Exception {

        TRmFileKey fileKey = new TRmFileKey();
        TRmFile rmFile = null;

        fileKey.setCreateDate( reqJob.getFileCreateDate() );
        fileKey.setFileSeq   ( reqJob.getFileSeq()        );

        try {
            rmFile = fileMap.selectByPrimaryKey( fileKey );
            if( rmFile == null ) {
                throw new Exception( String.format("T_RM_FILE no data found. createDate = %s, fileSeq = %s", reqJob.getFileCreateDate(), reqJob.getFileSeq()) );
            }
        }
        catch( Exception e ) {
            logger.debug( "T_RM_FILE select error : {}", e.getLocalizedMessage() );
            throw e;
        }
        //File f = new File("D:\\CronutWorks\\NICE\\Documents\\Design\\05. AMS\\ams_server_src.zip");
        File f = new File(String.format("%s%s%s", ROOT_FILE_PATH, rmFile.getFilePath(), rmFile.getFileNm()));
        FileInputStream fIn = new FileInputStream(f);

        reqInfo.getMsg().position(0);
        outMsg.setString( "CM._AOCMsgCode",           msg.getMsgCd())
              .setString( "CM._AOCServiceCode",       msg.getSvcCd())
              .setString( "CM._AOCMsgSendDate",       safeData.getMsgDate() )
              .setString( "CM._AOCMsgSendTime",       safeData.getMsgTime() )
              .setLong  ( "CM._AOCMsgLen",            (f.length() + outMsg.getMessageLength()) - 9 )
              .setString( "CM._AOCTranNo",            msg.getMsgSeq() )
              .setString( "_AOCDownFileType",         reqJob.getFileType() == null ? "9" : reqJob.getFileType() )
              .setString( "_AOCDownFileName",         reqJob.getFileName() == null ? rmFile.getOrgFileNm() : reqJob.getFileName() )
              .setLong  ( "_AOCDownFileSize",         f.length()  )
              .syncMessage();

        reqInfo.setStrm( fIn );
    }

}
