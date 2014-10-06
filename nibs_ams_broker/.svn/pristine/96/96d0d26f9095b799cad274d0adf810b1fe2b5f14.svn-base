package com.nicetcm.nibsplus.broker.ams.services;

/*
 * Copyright 2014 The NIBS Project
 *
 * AMS 기기관리시스템 - FileSaverImpl
 *
 * 기기로 부터 수취한 파일의 임시저장 내역을 기기별 분류하여 저장
 *
 * @author  K.D.J
 * @since   2014.09.01
 */

import static com.nicetcm.nibsplus.broker.ams.AMSBrokerLib.ROOT_FILE_PATH;
import static com.nicetcm.nibsplus.broker.ams.AMSBrokerConst.*;

import java.io.File;

import org.apache.ibatis.session.SqlSession;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerLib;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerTransaction;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmFileMapper;
import com.nicetcm.nibsplus.broker.ams.model.TRmFile;
import com.nicetcm.nibsplus.broker.common.MsgParser;

@Service("fileSaver")
public class FileSaverImpl implements FileSaver {

    private static final Logger logger = LoggerFactory.getLogger(FileSaverImpl.class);

    @Autowired protected SqlSession sqlSession;
    @Autowired protected DataSourceTransactionManager amsTX;

    @Autowired private   CommonPack                   comPack;
    @Autowired private   TRmFileMapper                fileMap;

    @Override
    public String tempFileToClassify(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

        safeData.setSysDate( AMSBrokerLib.getSysDate() );

        safeData.setTXS( amsTX.getTransaction( AMSBrokerTransaction.defAMSTX ));
        safeData.setMsgDate( AMSBrokerLib.getMsgDate(safeData.getSysDate()) );
        safeData.setMsgTime( AMSBrokerLib.getMsgTime(safeData.getSysDate()) );

        String fileName = null;

        try {
            logger.debug( "Service Code = {}", parsed.getString("CM._AOCServiceCode") );
            /**
             * 특정파일 업로드
             */
            if( parsed.getString("CM._AOCServiceCode").equals("5003") ) {
                TRmFile file = new TRmFile();

                file.setCreateDate( safeData.getMsgDate() );
                file.setFileSeq   ( fileMap.generateKey() );
                file.setInsertDate( safeData.getSysDate() );
                file.setInsertUid ( parsed.getString("CM._SSTNo").substring(2) );
                file.setUpdateDate( safeData.getSysDate() );
                file.setUpdateUid ( file.getInsertUid() );
                file.setMacNo     ( file.getInsertUid() );
                file.setComprssYn ( parsed.getString("_AOCUpFileCompress").equals("1") ? "Y" : "N" );
                file.setFileCl    ( parsed.getString("_AOCUpFileType")  );
                file.setOrgFileNm ( parsed.getString("_AOCUpFileName") );
                file.setFilePath  ( String.format("%s%s/%s/%s/", SPECIFIC_FILE_PATH, file.getCreateDate(), file.getInsertUid(), file.getOrgFileNm()) );
                file.setFileNm    ( String.format("%s.%s", file.getOrgFileNm(), file.getFileSeq()) );
                if( file.getOrgFileNm().indexOf(".") >= 0 )
                    file.setFileExt( file.getOrgFileNm().substring(file.getOrgFileNm().indexOf(".")) );

                comPack.insUpdFile( safeData, file, "800" );
                File d = new File( ROOT_FILE_PATH + file.getFilePath() );
                FileUtils.forceMkdir(d);

                fileName = ROOT_FILE_PATH + file.getFilePath() + file.getFileNm();
                File srcFile  = new File( fileLoc );
                File destFile = new File( fileName );

                FileUtils.copyFile( srcFile, destFile );
            }
            /**
             * 일반파일 업로드
             */
            else if( parsed.getString("CM._AOCServiceCode").equals("5011") ) {
                TRmFile file = new TRmFile();

                file.setCreateDate( safeData.getMsgDate() );
                file.setFileSeq   ( fileMap.generateKey() );
                file.setInsertDate( safeData.getSysDate() );
                file.setInsertUid ( parsed.getString("CM._SSTNo").substring(2) );
                file.setUpdateDate( safeData.getSysDate() );
                file.setUpdateUid ( file.getInsertUid() );
                file.setMacNo     ( file.getInsertUid() );
                file.setComprssYn ( "N" );
                file.setFileCl    ( "0" );
                file.setOrgFileNm ( parsed.getString("_AOCUpFileName") );
                file.setFilePath  ( String.format("%s%s/%s/%s/", GENERAL_FILE_PATH, file.getCreateDate(), file.getInsertUid(), file.getOrgFileNm()) );
                file.setFileNm    ( String.format("%s.%s", file.getOrgFileNm(), file.getFileSeq()) );
                if( file.getOrgFileNm().indexOf(".") >= 0 )
                    file.setFileExt( file.getOrgFileNm().substring(file.getOrgFileNm().indexOf(".")) );

                comPack.insUpdFile( safeData, file, "800" );
                File d = new File( ROOT_FILE_PATH + file.getFilePath() );
                FileUtils.forceMkdir(d);

                fileName = ROOT_FILE_PATH + file.getFilePath() + file.getFileNm();
                File srcFile  = new File( fileLoc );
                File destFile = new File( fileName );

                FileUtils.copyFile( srcFile, destFile );
            }

            amsTX.commit(safeData.getTXS());

            return fileName;
        }
        catch( Exception e ) {
            logger.debug("fileSaver has error [{}]", e.getMessage() );
            amsTX.rollback(safeData.getTXS());
            throw e;
        }
    }

}
