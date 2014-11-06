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
 * 1140 회수자금 결과 통보
 * <pre>
 * MngCM_AP_SaveCollect( char *pRecvData, int nDataLen )
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101140")
public class In03101140Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101140Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TMiscMapper tMiscMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {


        comPack.checkBranchMacLength( parsed );

        TMacInfo tMacInfo = new TMacInfo();
        tMacInfo.setOrgCd( parsed.getString("CM.org_cd") );
        tMacInfo.setBranchCd( parsed.getString("brch_cd") );
        tMacInfo.setMacNo( parsed.getString("mac_no") );
        tMacInfo.setCloseDate(parsed.getString("close_date"));

        try
        {
            tMiscMapper.updateFnAtmsCollect(tMacInfo);
        } catch (Exception e)
        {
            logger.warn( "[T_FN_ATMS_COLLECT] Update Error {}", e.getMessage() );
            throw e;
        }
    }
}
