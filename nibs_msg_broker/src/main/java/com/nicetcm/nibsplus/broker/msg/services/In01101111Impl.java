package com.nicetcm.nibsplus.broker.msg.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorMngMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngSpec;

/**
 * 나이스접수장애통보
 * <pre>
 * MngEM_AP_SaveNiceAcptErr( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01101111")
public class In01101111Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCtErrorMngMapper tCtErrorMngMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        /********************************************************************/
        /* 부산은행 ATMS의 경우 응답전문의 은행 장애 번호와 출동요청 사유 등을 저장 하도록 한다. */
        if(MsgBrokerConst.BUATMS_CODE.equals(parsed.getString("CM.org_cd"))) {

            TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
            tCtErrorMngSpec.createCriteria()
            .andCreateDateEqualTo(parsed.getInt("create_date"))
            .andErrorNoEqualTo(parsed.getString("error_no"));

            List<TCtErrorMng> tCtErrorMngList = tCtErrorMngMapper.selectBySpec(tCtErrorMngSpec);

            if(tCtErrorMngList != null && tCtErrorMngList.size() > 0) {

                for(TCtErrorMng tCtErrorMng : tCtErrorMngList) {
                    TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
                    updateTCtErrorMng.setOrgSendYn  ("0");
                    updateTCtErrorMng.setOrgMsgNo(parsed.getString("org_msg_no"));
                    updateTCtErrorMng.setTransDate(String.valueOf(tCtErrorMng.getCreateDate()));
                    updateTCtErrorMng.setSec(parsed.getString("reason_cd"));
                    updateTCtErrorMng.setUpdateDate (safeData.getDSysDate());
                    updateTCtErrorMng.setUpdateUid  ("APmng");

                    try
                    {
                        comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMng);
                    } catch (Exception e)
                    {
                        logger.warn( "[T_CT_ERROR_MNG] Update Err [{}]", e.getMessage());
                        throw e;
                    }
                }

            }


        } else if(MsgBrokerConst.DGB_CODE.equals(parsed.getString("CM.org_cd"))) {

            TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
            updateTCtErrorMng.setOrgSendYn("0");
            updateTCtErrorMng.setOrgMsgNo(parsed.getString("org_msg_no_long"));
            updateTCtErrorMng.setTransDate(parsed.getString("create_date"));
            updateTCtErrorMng.setUpdateDate(safeData.getDSysDate());
            updateTCtErrorMng.setUpdateUid("APmng");

            TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
            tCtErrorMngSpec.createCriteria()
            .andCreateDateEqualTo(parsed.getInt("create_date"))
            .andCreateTimeEqualTo(parsed.getString("create_time"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"));

            try
            {
                comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMngSpec);
            } catch (Exception e)
            {
                logger.warn( "[T_CT_ERROR_MNG] Update Err [{}]", e.getMessage());
                throw e;
            }

        } else {

            TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
            updateTCtErrorMng.setOrgSendYn("0");
            updateTCtErrorMng.setUpdateDate(safeData.getDSysDate());
            updateTCtErrorMng.setUpdateUid("APmng");

            TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
            tCtErrorMngSpec.createCriteria()
            .andCreateDateEqualTo(parsed.getInt("create_date"))
            .andCreateTimeEqualTo(parsed.getString("create_time"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"))
            .andErrorCdEqualTo(parsed.getString("error_cd"));

            try
            {
                comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMngSpec);
            } catch (Exception e)
            {
                logger.warn( "[T_CT_ERROR_MNG] Update Err [{}]", e.getMessage());
                throw e;
            }

        }

        logger.warn( "[T_CT_ERROR_MNG] Update OK" );

    }//end method
}