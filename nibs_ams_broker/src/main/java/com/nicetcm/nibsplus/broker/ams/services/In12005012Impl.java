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

        if( "0".equals(reqJob.getDownCmdType()) ) {
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

        if( "0".equals(reqJob.getDownCmdType()) || "2".equals(reqJob.getDownCmdType()) ) {
            if( !parsed.getString("_AOCDownFileMD5").equals(reqJob.getDownMD5Checksum()) ) {
                reqJob.setDownCmdType("1");
            }
            else {
                reqJob.setDownCmdType("0");
            }
        }
        else if( "1".equals(reqJob.getDownCmdType()) ) {
            reqJob.setDownWritePos( parsed.getLong("_AOCCurDownFileSize") );
            reqJob.setDownCmdType("2");
        }
    }

}
