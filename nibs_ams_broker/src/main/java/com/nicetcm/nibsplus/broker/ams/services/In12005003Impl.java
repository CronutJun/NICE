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
            logger.debug("File Location = {}", fileLoc);
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

            logger.debug("Journal Path = {}, Thread id = {}", journalPath, Thread.currentThread().getId());
            String extractDirNm = String.format( "tmp_%s_ext", Thread.currentThread().getId());
            String extractDir   = String.format( "%s%s", TEMP_FILE_PATH, extractDirNm );
            logger.debug("extract Loc = {}", extractDir);
            try {
                AMSBrokerLib.unZip(fileLoc, extractDir);
                File[] paths = new File(extractDir).listFiles();
                File dirImg  = new File(String.format("%simages",journalPath));
                File dirJnl  = new File(String.format("%s",      journalPath));
                for( File path: paths ) {
                    logger.debug("Extracted file: {}", path.getName());
                    /**
                     * JPG파일은 저널폴더로 이동
                     */
                    if( path.isFile() && path.getName().matches("(?i).*\\.jpg") ) {
                        File exist = new File(String.format("%s/%s", dirImg, path.getName()));
                        if( exist.exists())
                            FileUtils.forceDelete(exist);
                        FileUtils.moveFileToDirectory(path, dirImg, true);
                        TJmFile jmFile = new TJmFile();
                        jmFile.setMacTrxDate( parsed.getString("_AOCUpFileDate") );
                        jmFile.setMacNo     ( parsed.getString("CM._SSTNo").substring(2) );
                        jmFile.setOrgCd     ( NICE_ORG_CD );
                        jmFile.setBranchCd  ( NICE_BR_CD );
                        jmFile.setFileName  ( path.getName() );
                        jmFile.setInsertDate( safeData.getSysDate() );
                        jmFile.setInsertUid ( "BROKER" );
                        jmFile.setFilePath  ( String.format("%simages/", jnlPath) );
                        try {
                            if( fileMap.updateByPrimaryKey( jmFile ) == 0 ) {
                                try {
                                    fileMap.insert( jmFile );
                                }
                                catch( Exception e ) {
                                    logger.debug("T_JM_FILE INSERT ERROR: {}", e.getLocalizedMessage() );
                                    throw e;
                                }
                            }
                        }
                        catch( Exception e ) {
                            logger.debug("T_JM_FILE UPDATE ERROR: {}", e.getLocalizedMessage() );
                            throw e;
                        }
                    }
                    /**
                     * 저널파일은 저널폴더로 이동
                     */
                    if( path.isFile() && path.getName().matches("(?i).*\\.jnl") ) {
                        File exist = new File(String.format("%s/%s", dirJnl, path.getName()));
                        logger.debug("jnl File = {}", exist );
                        if( exist.exists() )
                            FileUtils.forceDelete(exist);
                        FileUtils.moveFileToDirectory(path, dirJnl, true);
                        TJmFile jmFile = new TJmFile();
                        jmFile.setMacTrxDate( parsed.getString("_AOCUpFileDate") );
                        jmFile.setMacNo     ( parsed.getString("CM._SSTNo").substring(2) );
                        jmFile.setOrgCd     ( NICE_ORG_CD );
                        jmFile.setBranchCd  ( NICE_BR_CD );
                        jmFile.setFileName  ( path.getName() );
                        jmFile.setInsertDate( safeData.getSysDate() );
                        jmFile.setInsertUid ( "BROKER" );
                        jmFile.setFilePath  ( String.format("%s", jnlPath) );
                        try {
                            if( fileMap.updateByPrimaryKey( jmFile ) == 0 ) {
                                try {
                                    fileMap.insert( jmFile );
                                }
                                catch( Exception e ) {
                                    logger.debug("T_JM_FILE INSERT ERROR: {}", e.getLocalizedMessage() );
                                    throw e;
                                }
                            }
                        }
                        catch( Exception e ) {
                            logger.debug("T_JM_FILE UPDATE ERROR: {}", e.getLocalizedMessage() );
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
            logger.debug("In12005003Impl has error :  {} ", e.getMessage());
            throw e;
        }
    }
}