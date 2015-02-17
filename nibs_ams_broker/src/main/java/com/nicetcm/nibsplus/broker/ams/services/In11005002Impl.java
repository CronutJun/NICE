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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerConst;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
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
import com.nicetcm.nibsplus.broker.ams.model.TRmTrx;
import com.nicetcm.nibsplus.broker.common.MsgParser;

@Service("in11005002")
public class In11005002Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In11005002Impl.class);

    @Autowired private TRmFileMapper                  fileMap;
    @Autowired private TRmMacEnvMapper                macEnvMap;
    @Autowired private TPmPgmVerMapper                pgmVerMap;

    @Override
    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

        // 제조사,기종 정보를 따고,
        // parsed.getString("_APVersion") 정보 이후의 DB를 검색하여 기기버전 이후의 최초버전을 선택하여 내려보낸다.
        // 내려보낼 수 없는 상황이면.. reqJob.setFileName("N/A")를 셋하고 out22005002Impl에서 응답처리토록 한다.
        /**
         * Version 검증
         */
        TRmMacEnv    env = null;
        TPmPgmVer    ver = null;
        TRmMacEnvKey envKey = new TRmMacEnvKey();
        envKey.setBranchCd( AMSBrokerConst.NICE_BR_CD );
        envKey.setOrgCd   ( AMSBrokerConst.NICE_ORG_CD );
        envKey.setMacNo   ( parsed.getString( "CM._SSTNo").substring(2) );
        try {
            env = macEnvMap.selectByPrimaryKey( envKey );
            if( env == null ) {
                throw new Exception("기기정보가 없습니다.");
            }
            if( "9".equals(env.getSts()) ) {
                throw new Exception("기기가 폐국 상태입니다.");
            }

            TPmPgmVerSpec pgmVerSpec = new TPmPgmVerSpec();
            pgmVerSpec.createCriteria().andMkrCdEqualTo             ( env.getMkrCd() )
                                       .andModelCdEqualTo           ( env.getModelCd() )
                                       .andVerIdGreaterThan         ( parsed.getString("_APVersion") );
            pgmVerSpec.setOrderByClause( "VER_ID DESC, SORT_ID DESC" );
            List<TPmPgmVer> rslt = pgmVerMap.selectBySpec( pgmVerSpec );
            if( rslt.size() == 0 ) {
                reqJob.setFileName("N/A");
                return;
            }
            ver = rslt.get(0);
            for( TPmPgmVer ver1: rslt ) {
                logger.warn("[VERSION INFO] Version = {}, MasterYN = {}, DependencyVer = {}", ver.getVerId(), ver.getMasterYn(), ver.getDepVerId());
                if( "Y".equals(ver1.getMasterYn()) ) {
                    ver = ver1;
                    break;
                }
            }
        }
        catch( Exception e ) {
            throw e;
        }

        TRmFileKey fileKey = new TRmFileKey();
        TRmFile rmFile = null;

        fileKey.setCreateDate( ver.getCreateDate() );
        fileKey.setFileSeq   ( ver.getFileSeq()    );

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

        reqJob.setFilePath   ( rmFile.getFilePath() );
        reqJob.setFileName   ( rmFile.getFileNm() );
        reqJob.setFileOrgName( rmFile.getOrgFileNm() );
        logger.warn("reqJob FileName = {}", reqJob.getFileName());
    }
}
