package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

/**
 *
 * 1150 미처리금액 보고서
 * <pre>
 * MngCM_AP_SaveNotMng( pRecvData, nLen )
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101150")
public class In03101150Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101150Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TMiscMapper tMiscMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        comPack.checkBranchMacLength( parsed );

        TMacInfo tMacInfo = new TMacInfo();
        tMacInfo.setOrgCd( parsed.getString("CM.org_cd") );
        tMacInfo.setBranchCd( parsed.getString("brch_cd") );
        tMacInfo.setMacNo( parsed.getString("mac_no") );
        tMacInfo.setSerialNo(parsed.getString("serial_no"));
        tMacInfo.setTradeDate(parsed.getString("trade_date"));

        try
        {
            tMiscMapper.updateFnAtmsReport(tMacInfo);
        } catch (Exception e)
        {
            logger.warn( "[T_FN_ATMS_REPORT] Update Error {}", e.getMessage() );
            throw e;
        }
    }
}
