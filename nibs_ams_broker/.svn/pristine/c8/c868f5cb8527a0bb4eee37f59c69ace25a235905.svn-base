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
import com.nicetcm.nibsplus.broker.ams.mapper.TRcRegInfMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRcRegInfHisMapper;
import com.nicetcm.nibsplus.broker.ams.model.TRcRegInf;
import com.nicetcm.nibsplus.broker.ams.model.TRcRegInfHis;

@Service("in12002011")
public class In12002011Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In12002011Impl.class);

    @Autowired private TRcRegInfMapper    rcRegInfMap;
    @Autowired private TRcRegInfHisMapper rcRegInfHisMap;

    @Override
    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

        TRcRegInf rcRegInf       = new TRcRegInf();
        TRcRegInfHis rcRegInfHis = new TRcRegInfHis();

        rcRegInf.setOrgCd     ( AMSBrokerConst.NICE_ORG_CD         );
        rcRegInf.setBranchCd  ( AMSBrokerConst.NICE_BR_CD          );
        rcRegInf.setMacNo     ( parsed.getString("CM._SSTNo").substring(2) );
        rcRegInf.setUpdateUid ( reqJob.getTrxCd()                  );
        rcRegInf.setUpdateDate( safeData.getSysDate()              );

        rcRegInf.setRegBaseKey( parsed.getString("_AocReqRegBaseKey")  );
        rcRegInf.setRegKeyPath( parsed.getString("_AocReqRegSubKey")   );
        rcRegInf.setRegKeyNm  ( parsed.getString("_AocReqRegValueKey") );
        rcRegInf.setRegVal    ( parsed.getString("_AocReqRegValue")    );

        try {
            if( rcRegInfMap.updateByPrimaryKeySelective( rcRegInf ) == 0 )
            try {
                rcRegInf.setInsertUid ( reqJob.getTrxUid()                 );
                rcRegInf.setInsertDate( safeData.getSysDate()              );

                rcRegInfMap.insert( rcRegInf );
            }
            catch ( Exception e ) {
                logger.info("T_RC_REG_INF Insert error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.info("T_RC_REG_INF Update error [{}]", e.getLocalizedMessage() );
            throw e;
        }

        BeanUtils.copyProperties( rcRegInfHis, rcRegInf );

        rcRegInfHis.setInsertUid ( reqJob.getTrxUid()                 );
        rcRegInfHis.setInsertDate( safeData.getSysDate()              );
        rcRegInfHis.setTrxDate   ( reqJob.getTrxDate()                );
        rcRegInfHis.setTrxNo     ( reqJob.getTrxNo()                  );

        try {
            rcRegInfHisMap.insert( rcRegInfHis );
        }
        catch( Exception e ) {
            logger.info("T_RC_REG_INF_HIS Insert error [{}]", e.getLocalizedMessage() );
            throw e;
        }
    }

}
