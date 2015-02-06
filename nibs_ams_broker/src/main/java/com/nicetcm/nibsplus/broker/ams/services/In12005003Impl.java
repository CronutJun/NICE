package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * In12005003Impl
 *
 *  특정파일 업로드 처리
 *
 *
 * @author  K.D.J
 * @since   2014.09.02
 */

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nicetcm.nibsplus.broker.ams.AMSBrokerConst.JOURNAL_FILE_PATH;
import static com.nicetcm.nibsplus.broker.ams.AMSBrokerConst.NICE_ORG_CD;
import static com.nicetcm.nibsplus.broker.ams.AMSBrokerConst.NICE_BR_CD;
import static com.nicetcm.nibsplus.broker.ams.AMSBrokerLib.ROOT_FILE_PATH;
import static com.nicetcm.nibsplus.broker.ams.AMSBrokerLib.TEMP_FILE_PATH;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerLib;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.mapper.TJmFileMapper;
import com.nicetcm.nibsplus.broker.ams.model.TJmFile;

@Service("in12005003")
public class In12005003Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In12005003Impl.class);

    @Autowired private   TJmFileMapper                fileMap;

    @Override
    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

        try {
            if( fileLoc == null || fileLoc.length() == 0 ) {
                logger.warn("There's no journal data. skip file processing..");
                return;
            }
            File zipFile = new File(fileLoc);
            if( !zipFile.exists() ) {
                logger.warn("There's no journal data. skip file processing..");
                return;
            }
            if( !parsed.getString("_AOCUpFileMD5").equals(reqJob.getUpMD5Checksum()) ) {
                logger.warn("ATM MD5 = {}, AOC MD5 = {} is not same", parsed.getString("_AOCUpFileMD5"), reqJob.getUpMD5Checksum() );
                reqJob.setUpMD5Result(false);
                return;
            }
            else {
                logger.warn("ATM MD5 = {}, AOC MD5 = {} is same", parsed.getString("_AOCUpFileMD5"), reqJob.getUpMD5Checksum() );
            }
            TJmFile jmFileOrg = new TJmFile();
            jmFileOrg.setMacTrxDate ( parsed.getString("_AOCUpFileDate") );
            jmFileOrg.setMacNo      ( parsed.getString("CM._SSTNo").substring(2) );
            jmFileOrg.setOrgCd      ( NICE_ORG_CD );
            jmFileOrg.setBranchCd   ( NICE_BR_CD );
            jmFileOrg.setFileName   ( zipFile.getName() );
            jmFileOrg.setInsertDate ( null );
            jmFileOrg.setInsertUid  ( null );
            jmFileOrg.setUpdateDate ( safeData.getSysDate() );
            jmFileOrg.setUpdateUid  ( "BROKER" );
            jmFileOrg.setFileCl     ( parsed.getString("_AOCUpFileType") );
            jmFileOrg.setZipFileName( "ORG" );
            jmFileOrg.setFilePath   ( zipFile.getPath().substring(ROOT_FILE_PATH.length(), zipFile.getPath().length() - zipFile.getName().length()) );
            try {
                if( fileMap.updateByPrimaryKeySelective( jmFileOrg ) == 0 ) {
                    try {
                        jmFileOrg.setInsertDate ( safeData.getSysDate() );
                        jmFileOrg.setInsertUid  ( "BROKER" );
                        fileMap.insertSelective( jmFileOrg );
                    }
                    catch( Exception e ) {
                        logger.warn("T_JM_FILE ORG INSERT ERROR: {}", e.getLocalizedMessage() );
                        throw e;
                    }
                }
            }
            catch( Exception e ) {
                logger.warn("T_JM_FILE ORG UPDATE ERROR: {}", e.getLocalizedMessage() );
                throw e;
            }
            logger.warn("File Location = {}", fileLoc);
            String journalPath = String.format("%s%s%s/%s/",
                                               ROOT_FILE_PATH,
                                               JOURNAL_FILE_PATH,
                                               parsed.getString("_AOCUpFileDate"),
                                               parsed.getString("CM._SSTNo").substring(2)
                                              );
            String jnlPath = String.format("%s%s/%s/",
                                           JOURNAL_FILE_PATH,
                                           parsed.getString("_AOCUpFileDate"),
                                           parsed.getString("CM._SSTNo").substring(2)
                                          );

            logger.warn("Journal Path = {}, Thread id = {}", journalPath, Thread.currentThread().getId());
            String extractDirNm = String.format( "tmp_%s_ext", Thread.currentThread().getId());
            String extractDir   = String.format( "%s%s", TEMP_FILE_PATH, extractDirNm );
            logger.warn("extract Loc = {}", extractDir);
            try {
                AMSBrokerLib.unZip(fileLoc, extractDir);
                File[] paths = new File(extractDir).listFiles();
                File dirImg  = new File(String.format("%simages",journalPath));
                File dirJnl  = new File(String.format("%s",      journalPath));
                for( File path: paths ) {
                    logger.warn("Extracted file: {}", path.getName());
                    /**
                     * JPG파일은 저널폴더로 이동
                     */
                    if( path.isFile() && path.getName().matches("(?i).*\\.jpg") ) {
                        File exist = new File(String.format("%s/%s", dirImg, path.getName()));
                        if( exist.exists())
                            FileUtils.forceDelete(exist);
                        FileUtils.moveFileToDirectory(path, dirImg, true);
                        TJmFile jmFile = new TJmFile();
                        jmFile.setMacTrxDate ( parsed.getString("_AOCUpFileDate") );
                        jmFile.setMacNo      ( parsed.getString("CM._SSTNo").substring(2) );
                        jmFile.setOrgCd      ( NICE_ORG_CD );
                        jmFile.setBranchCd   ( NICE_BR_CD );
                        jmFile.setFileName   ( path.getName() );
                        jmFile.setInsertDate ( null );
                        jmFile.setInsertUid  ( null );
                        jmFile.setUpdateDate ( safeData.getSysDate() );
                        jmFile.setUpdateUid  ( "BROKER" );
                        jmFile.setFileCl     ( parsed.getString("_AOCUpFileType") );
                        jmFile.setZipFileName( jmFileOrg.getFileName() );
                        jmFile.setFilePath   ( String.format("%simages/", jnlPath) );
                        try {
                            if( fileMap.updateByPrimaryKeySelective( jmFile ) == 0 ) {
                                try {
                                    jmFile.setInsertDate ( safeData.getSysDate() );
                                    jmFile.setInsertUid  ( "BROKER" );
                                    fileMap.insertSelective( jmFile );
                                }
                                catch( Exception e ) {
                                    logger.warn("T_JM_FILE INSERT ERROR: {}", e.getLocalizedMessage() );
                                    throw e;
                                }
                            }
                        }
                        catch( Exception e ) {
                            logger.warn("T_JM_FILE UPDATE ERROR: {}", e.getLocalizedMessage() );
                            throw e;
                        }
                    }
                    /**
                     * 저널파일은 저널폴더로 이동
                     */
                    if( path.isFile() && path.getName().matches("(?i).*\\.jnl") ) {
                        File exist = new File(String.format("%s/%s", dirJnl, path.getName()));
                        logger.warn("jnl File = {}", exist );
                        if( exist.exists() )
                            FileUtils.forceDelete(exist);
                        FileUtils.moveFileToDirectory(path, dirJnl, true);
                        TJmFile jmFile = new TJmFile();
                        jmFile.setMacTrxDate ( parsed.getString("_AOCUpFileDate") );
                        jmFile.setMacNo      ( parsed.getString("CM._SSTNo").substring(2) );
                        jmFile.setOrgCd      ( NICE_ORG_CD );
                        jmFile.setBranchCd   ( NICE_BR_CD );
                        jmFile.setFileName   ( path.getName() );
                        jmFile.setInsertDate ( null );
                        jmFile.setInsertUid  ( null );
                        jmFile.setUpdateDate ( safeData.getSysDate() );
                        jmFile.setUpdateUid  ( "BROKER" );
                        jmFile.setFileCl     ( parsed.getString("_AOCUpFileType") );
                        jmFile.setZipFileName( jmFileOrg.getFileName() );
                        jmFile.setFilePath   ( String.format("%s", jnlPath) );
                        try {
                            if( fileMap.updateByPrimaryKeySelective( jmFile ) == 0 ) {
                                try {
                                    jmFile.setInsertDate ( safeData.getSysDate() );
                                    jmFile.setInsertUid  ( "BROKER" );
                                    fileMap.insertSelective( jmFile );
                                }
                                catch( Exception e ) {
                                    logger.warn("T_JM_FILE INSERT ERROR: {}", e.getLocalizedMessage() );
                                    throw e;
                                }
                            }
                        }
                        catch( Exception e ) {
                            logger.warn("T_JM_FILE UPDATE ERROR: {}", e.getLocalizedMessage() );
                            throw e;
                        }
                    }
                    /**
                     * CSV파일은 파싱하여 DB저장
                     */
                    if( path.isFile() && path.getName().matches(String.format("(?i)%s_%s.csv",
                                                                        parsed.getString("_AOCUpFileDate"),
                                                                        parsed.getString("CM._SSTNo")
                                                                              )) ) {
                        comPack.parseCSV( safeData, parsed, path );
                    }
                }
            }
            finally {
                FileUtils.forceDelete( new File(extractDir) );
            }
        }
        catch( Exception e ) {
            logger.warn("In12005003Impl has error :  {} ", e.getMessage());
            throw e;
        }
    }
}
