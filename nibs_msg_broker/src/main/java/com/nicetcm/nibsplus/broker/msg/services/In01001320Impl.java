package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngSpec;

/**
 *
 * 후처리 조치예정_완료
 * <pre>
 * MngEM_AP_SaveAfterMng( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01001320")
public class In01001320Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        String szOrgMsgNo = null;
        String szOrgSendYn = null;

        if(MsgBrokerConst.KIUP_CODE.equals(parsed.getString("CM.org_cd"))) {
            /* 기업은행  */
            szOrgSendYn = "1";

        } else {
            if(parsed.getString("mng_gb").startsWith("2")) {
                szOrgSendYn = "1";
            } else if(parsed.getString("mng_gb").startsWith("3")) {
                szOrgSendYn = "2";
            } else {
                throw new Exception("[MngEM_AP_SaveAfterMng]잘못된 조치상태수신[" + parsed.getString("mng_gb") + "]");
            }
        }

        if(MsgBrokerConst.KIUP_CODE.equals(parsed.getString("CM.org_cd"))) {
            /* 기업은행  */
            szOrgMsgNo = parsed.getString("trans1_seq_long");

        } else {
            szOrgMsgNo = parsed.getString("trans1_seq");

        }

        TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
        updateTCtErrorMng.setOrgSendYn(szOrgSendYn);
        updateTCtErrorMng.setUpdateDate(safeData.getDSysDate());
        updateTCtErrorMng.setUpdateUid("APmng");

        TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
        tCtErrorMngSpec.createCriteria()
        .andCreateDateGreaterThan(Integer.parseInt(MsgBrokerLib.SysDate(-10)))
        .andOrgMsgNoEqualTo(szOrgMsgNo)
        .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
        .andBranchCdEqualTo(parsed.getString("brch_cd"))
        .andMacNoEqualTo(parsed.getString("mac_no"))
        .andErrorCdEqualTo("AFTMNG");

        try
        {
            comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMngSpec);
        } catch (Exception e)
        {
            logger.warn("[MngEM_AP_SaveAfterMng] Update Err[{}]", e.getMessage());
            throw e;
        }

        logger.warn( "[MngEM_AP_SaveAfterMng] Update OK" );

    }//end method
}