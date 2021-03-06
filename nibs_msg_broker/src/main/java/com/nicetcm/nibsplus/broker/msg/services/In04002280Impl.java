package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;

/**
 *
 * 2280 CRT번호등록
 * <pre>
 * MngIQ_SendRegCRTNo( pRecvData, pSendBuf, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04002280")
public class In04002280Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In04002280Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        if(MsgBrokerConst.SHATMS_CODE.equals(parsed.getString("CM.org_cd"))
           || MsgBrokerConst.SHB_CODE.equals(parsed.getString("CM.org_cd"))
        ) {
            parsed.setString("CM.org_cd", MsgBrokerConst.SHATMS_CODE);

            String branchCd = null;
            String siteCd = null;

            try
            {
                branchCd = comPack.fGetOrgBranchCd(parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("site_cd"), parsed.getString("mac_no"));
                siteCd = comPack.fGetOrgSiteCd(parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("site_cd"), parsed.getString("mac_no"));

                if(branchCd.equals("") || siteCd.equals("")) {
                    throw new Exception("branchCd or siteCd is null");
                }
            } catch (Exception e)
            {
                logger.warn(">>> [f_get_org_site_cd] 기관 코너코드 파악 실패 [{}]", e.getMessage());
                throw e;
            }

            parsed.setString("brch_cd", branchCd);
            parsed.setString("site_cd", siteCd);

            comPack.msgSnd(parsed);

        }
    }
}
