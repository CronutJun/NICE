package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnAtmsCloseSendMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnAtmsCloseSend;
import com.nicetcm.nibsplus.broker.msg.model.TFnAtmsCloseSendSpec;

/**
 *
 * 1132 마감내역통보
 * <pre>
 * MngCM_SaveCloseNotice( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101134")
public class In03101134Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101134Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnAtmsCloseSendMapper tFnAtmsCloseSendMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TFnAtmsCloseSend tFnAtmsCloseSend = new TFnAtmsCloseSend();
        tFnAtmsCloseSend.setOrgSendYn("1");
        tFnAtmsCloseSend.setUpdateUid("APmng");
        tFnAtmsCloseSend.setUpdateDate(safeData.getDSysDate());

        TFnAtmsCloseSendSpec tFnAtmsCloseSendSpec = new TFnAtmsCloseSendSpec();

        tFnAtmsCloseSendSpec.createCriteria().andCloseDateEqualTo(parsed.getString("close_date"))
        .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
        .andBranchCdEqualTo(parsed.getString("brch_cd"))
        .andMacNoEqualTo(parsed.getString("mac_no"));

        try
        {
            tFnAtmsCloseSendMapper.updateBySpecSelective(tFnAtmsCloseSend, tFnAtmsCloseSendSpec);
        } catch (Exception e)
        {
            logger.warn( "[T_FN_ATMS_CLOSE_SEND] Update Error {}", e.getMessage() );
            throw e;
        }

        logger.warn( "[T_FN_ATMS_CLOSE_SEND] Update OK" );
    }
}
