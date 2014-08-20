package com.nicetcm.nibsplus.broker.ams.services;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerConst;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.mapper.TRcIniInfMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRcIniInfHisMapper;
import com.nicetcm.nibsplus.broker.ams.model.TRcIniInf;
import com.nicetcm.nibsplus.broker.ams.model.TRcIniInfHis;

@Service("in12002012")
public class In12002012Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In12002012Impl.class);

    @Autowired private TRcIniInfMapper    rcIniInfMap;
    @Autowired private TRcIniInfHisMapper rcIniInfHisMap;

    @Override
    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

        TRcIniInf rcIniInf       = new TRcIniInf();
        TRcIniInfHis rcIniInfHis = new TRcIniInfHis();

        rcIniInf.setOrgCd     ( AMSBrokerConst.NICE_ORG_CD         );
        rcIniInf.setBranchCd  ( AMSBrokerConst.NICE_BR_CD          );
        rcIniInf.setMacNo     ( parsed.getString("CM._SSTNo").substring(2) );
        rcIniInf.setUpdateUid ( reqJob.getTrxCd()                  );
        rcIniInf.setUpdateDate( safeData.getSysDate()              );

        rcIniInf.setIniFileNm ( parsed.getString("_AOCReqIniPathName") );
        rcIniInf.setIniSectNm ( parsed.getString("_AOCReqIniSection")  );
        rcIniInf.setIniKeyNm  ( parsed.getString("_AOCReqIniKey")      );
        rcIniInf.setIniVal    ( parsed.getString("_AOCReqIniValue")    );

        try {
            if( rcIniInfMap.updateByPrimaryKeySelective( rcIniInf ) == 0 )
            try {
                rcIniInf.setInsertUid ( reqJob.getTrxUid()                 );
                rcIniInf.setInsertDate( safeData.getSysDate()              );

                rcIniInfMap.insert( rcIniInf );
            }
            catch ( Exception e ) {
                logger.info("T_RC_INI_INF Insert error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.info("T_RC_INI_INF Update error [{}]", e.getLocalizedMessage() );
            throw e;
        }

        BeanUtils.copyProperties( rcIniInfHis, rcIniInf );

        rcIniInfHis.setInsertUid ( reqJob.getTrxUid()                 );
        rcIniInfHis.setInsertDate( safeData.getSysDate()              );
        rcIniInfHis.setTrxDate   ( reqJob.getTrxDate()                );
        rcIniInfHis.setTrxNo     ( reqJob.getTrxNo()                  );

        try {
            rcIniInfHisMap.insert( rcIniInfHis );
        }
        catch( Exception e ) {
            logger.info("T_RC_INI_INF_HIS Insert error [{}]", e.getLocalizedMessage() );
            throw e;
        }
    }

}
