package com.nicetcm.nibsplus.broker.ams.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmFileMapper;
import com.nicetcm.nibsplus.broker.ams.model.TRmFile;

@Service("in12005012")
public class In12005012Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In12005012Impl.class);

    @Autowired private   CommonPack                   comPack;
    @Autowired private   TRmFileMapper                fileMap;

    @Override
    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

        TRmFile rmFile = new TRmFile();

        rmFile.setCreateDate( reqJob.getFileCreateDate() );
        rmFile.setFileSeq   ( reqJob.getFileSeq()        );
        rmFile.setInsertDate( safeData.getSysDate()      );
        rmFile.setInsertUid ( reqJob.getTrxUid()         );
        rmFile.setUpdateDate( safeData.getSysDate()      );
        rmFile.setUpdateUid ( reqJob.getTrxUid()         );
        rmFile.setMacNo     ( reqJob.getMacNo()          );

        comPack.insUpdFile( safeData, rmFile, "810" );
    }

}
