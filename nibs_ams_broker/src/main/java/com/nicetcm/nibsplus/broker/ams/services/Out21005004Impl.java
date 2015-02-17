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

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerConst;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqInfo;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.mapper.TPmPgmVerMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmFileMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMacEnvMapper;
import com.nicetcm.nibsplus.broker.ams.model.TPmPgmVer;
import com.nicetcm.nibsplus.broker.ams.model.TPmPgmVerSpec;
import com.nicetcm.nibsplus.broker.ams.model.TRmFile;
import com.nicetcm.nibsplus.broker.ams.model.TRmFileKey;
import com.nicetcm.nibsplus.broker.ams.model.TRmMacEnv;
import com.nicetcm.nibsplus.broker.ams.model.TRmMacEnvKey;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;
import com.nicetcm.nibsplus.broker.common.MsgParser;

@Service("out21005004")
public class Out21005004Impl implements OutMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(Out21005004Impl.class);

    @Autowired private TRmFileMapper                  fileMap;
    @Autowired private TRmMacEnvMapper                macEnvMap;
    @Autowired private TPmPgmVerMapper                pgmVerMap;

    @Override
    public void outMsgHandle(MsgParser outMsg, AMSBrokerData safeData, AMSBrokerReqJob reqJob, AMSBrokerReqInfo reqInfo, TRmMsg msg) throws Exception {

        /**
         * Version 검증
         */
        TRmMacEnv    env = null;
        if( reqJob.getFileType() == null || reqJob.getFileType().equals("9") ) {
            TRmMacEnvKey envKey = new TRmMacEnvKey();
            envKey.setBranchCd( AMSBrokerConst.NICE_BR_CD );
            envKey.setOrgCd   ( AMSBrokerConst.NICE_ORG_CD );
            envKey.setMacNo   ( reqJob.getMacNo() );
            try {
                env = macEnvMap.selectByPrimaryKey( envKey );
                if( env == null ) {
                    throw new Exception("기기정보가 없습니다.");
                }
                if( "9".equals(env.getSts()) ) {
                    throw new Exception("기기가 폐국 상태입니다.");
                }
                if( env.getApVer().compareTo( reqJob.getFileVersion() ) >= 0 ) {
                    throw new Exception("[VER]이미 설치 되었습니다.");
                }
                TPmPgmVerSpec pgmVerSpec = new TPmPgmVerSpec();
                pgmVerSpec.createCriteria().andMkrCdEqualTo             ( env.getMkrCd() )
                                           .andModelCdEqualTo           ( env.getModelCd() )
                                           .andVerIdLessThanOrEqualTo   ( reqJob.getFileVersion() )
                                           .andVerIdGreaterThanOrEqualTo( env.getApVer() );
                pgmVerSpec.setOrderByClause( "VER_ID DESC, SORT_ID DESC" );
                List<TPmPgmVer> rslt = pgmVerMap.selectBySpec( pgmVerSpec );
                if( !rslt.get(0).getVerId().equals(reqJob.getFileVersion()) ) {
                    throw new Exception("[VER]다운로드 버전 미등록");
                }
//                for( TPmPgmVer ver: rslt ) {
//                    logger.warn("[VERSION INFO] Version = {}, MasterYN = {}, DependencyVer = {}", ver.getVerId(), ver.getMasterYn(), ver.getDepVerId());
//                    if( ver.getDepVerId() != null && env.getApVer().compareTo( ver.getDepVerId() ) < 0 ) {
//                        throw new Exception("[VER]종속 버전 설치 후 진행 [" + ver.getDepVerId() + "]");
//                    }
//                }
            }
            catch( Exception e ) {
                throw e;
            }
        }

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
            logger.warn( "T_RM_FILE select error : {}", e.getLocalizedMessage() );
            throw e;
        }

        if( "0".equals(reqJob.getDownCmdType()) || "2".equals(reqJob.getDownCmdType()) ) {
            File f = new File(String.format("%s%s%s", ROOT_FILE_PATH, rmFile.getFilePath(), rmFile.getFileNm()));
            FileInputStream fIn = new FileInputStream(f);

            reqInfo.getMsg().position(0);
            outMsg.setString( "CM._AOCMsgCode",           msg.getMsgCd() )
                  .setString( "CM._AOCServiceCode",       msg.getSvcCd() )
                  .setString( "CM._AOCMsgSendDate",       safeData.getMsgDate() )
                  .setString( "CM._AOCMsgSendTime",       safeData.getMsgTime() )
                  .setLong  ( "CM._AOCMsgLen",            (f.length() + outMsg.getMessageLength()) - 9 )
                  .setString( "CM._AOCTranNo",            msg.getMsgSeq() )
                  .setString( "_AOCDownCmdType",          "0" )
                  .setString( "_AOCDownFileType",         reqJob.getFileType() == null ? "9" : reqJob.getFileType() )
                  .setString( "_AOCDownFileName",         reqJob.getFileName() == null ? rmFile.getOrgFileNm() : reqJob.getFileName() )
                  .setLong  ( "_AOCDownFileSize",         f.length()  )
                  .syncMessage();

            reqInfo.setStrm( fIn );
        }
        else {
            reqInfo.getMsg().position(0);
            outMsg.setString( "CM._AOCMsgCode",           msg.getMsgCd() )
                  .setString( "CM._AOCServiceCode",       msg.getSvcCd() )
                  .setString( "CM._AOCMsgSendDate",       safeData.getMsgDate() )
                  .setString( "CM._AOCMsgSendTime",       safeData.getMsgTime() )
                  .setLong  ( "CM._AOCMsgLen",            outMsg.getMessageLength() - 9 )
                  .setString( "CM._AOCTranNo",            msg.getMsgSeq() )
                  .setString( "_AOCDownCmdType",          "1" )
                  .setString( "_AOCDownFileType",         reqJob.getFileType() == null ? "9" : reqJob.getFileType() )
                  .setString( "_AOCDownFileName",         reqJob.getFileName() == null ? rmFile.getOrgFileNm() : reqJob.getFileName() )
                  .setLong  ( "_AOCDownFileSize",         0  )
                  .syncMessage();

            reqInfo.setStrm( null );
        }
    }

}
