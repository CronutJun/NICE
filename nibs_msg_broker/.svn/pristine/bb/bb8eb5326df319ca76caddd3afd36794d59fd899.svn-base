package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnVanDemandDetailMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnVanDemandDetail;

@Service("in03101194")
public class In03101194Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101194Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnVanDemandDetailMapper tFnVanDemandDetailMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TFnVanDemandDetail tFnVanDemandDetail = new TFnVanDemandDetail();
        tFnVanDemandDetail.setOrgSendYn("1");
        tFnVanDemandDetail.setDemandDate( parsed.getString("demand_date") );
        tFnVanDemandDetail.setOrgCd     ( parsed.getString("CM.org_cd")   );

        try {
            tFnVanDemandDetailMap.updateByPrimaryKeySelective( tFnVanDemandDetail );
        }
        catch( Exception e ) {
            logger.warn("[T_FN_VAN_DEMAND_DETAIL] Update Err!! Demand_date[{}] OrgCd[{}][{}]", parsed.getString("demand_date"), parsed.getString("CM.org_cd"), e.getLocalizedMessage() );
            throw e;
        }
        logger.warn("[T_FN_VAN_DEMAND_DETAIL] Update Success!! Demand_date[{}] OrgCd[{}]", parsed.getString("demand_date"), parsed.getString("CM.org_cd"));
    }
}
