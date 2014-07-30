package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorMngGuardMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorMngMadeComMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngGuard;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngGuardSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngMadeCom;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngMadeComSpec;

/**
*
* 장애출동취소
* <pre>
* MngEM_AP_SaveGuardErrCancel( pRecvData, nLen );
* </pre>
*
* @author s7760ker@gmail.com
* @version 1.0
* @see
*/
@Service("in01100140")
public class In01100140Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCtErrorMngMadeComMapper tCtErrorMngMadeComMapper;

    @Autowired private TCtErrorMngGuardMapper tCtErrorMngGuardMapper;



    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        if(MsgBrokerConst.ATM_HY_CODE.equals(parsed.getString("CM.org_cd"))
            || MsgBrokerConst.ATM_CH_CODE.equals(parsed.getString("CM.org_cd"))
            || MsgBrokerConst.ATM_LG_CODE.equals(parsed.getString("CM.org_cd"))
        ) {
            /* 기기사에서 수신 받은 전문은 2차 출동요청 테이블에서 처리 */
            TCtErrorMngMadeCom tCtErrorMngMadeCom = new TCtErrorMngMadeCom();
            tCtErrorMngMadeCom.setOrgSendYn("Q");

            TCtErrorMngMadeComSpec tCtErrorMngMadeComSpec = new TCtErrorMngMadeComSpec();
            tCtErrorMngMadeComSpec.createCriteria()
            .andTransDateEqualTo(parsed.getString("trans1_date"))
            .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"))
            .andOrgCallCntEqualTo(parsed.getShort("mac_model"));

            try
            {
                tCtErrorMngMadeComMapper.updateBySpecSelective(tCtErrorMngMadeCom, tCtErrorMngMadeComSpec);
            } catch (Exception e)
            {
                logger.info( "[t_ct_error_mng_made_com] Delete Err [{}]", e.getMessage() );
            }

            return;

        }

        if(parsed.getString("CM.ret_cd").equals("0000")) {
            TCtErrorMngGuardSpec tCtErrorMngGuardSpec = new TCtErrorMngGuardSpec();
            tCtErrorMngGuardSpec.createCriteria()
            .andCreateDateEqualTo(parsed.getInt("trans1_date"))
            .andErrorNoEqualTo(parsed.getString("trans1_seq"));

            try
            {
                tCtErrorMngGuardMapper.deleteBySpec(tCtErrorMngGuardSpec);
            } catch (Exception e)
            {
                logger.info( "[T_CT_ERROR_MNG_GUARD] Delete Err [{}]", e.getMessage() );
            }

            logger.info( "[T_CT_ERROR_MNG_GUARD] Delete OK" );
        } else {

            String hSEND_YN;

            if(parsed.getString("CM.ret_cd_src").equals("B")) {
                hSEND_YN = "B";
            } else {
                hSEND_YN = "H";
            }

            TCtErrorMngGuard tCtErrorMngGuard = new TCtErrorMngGuard();
            tCtErrorMngGuard.setGuardSendYn(hSEND_YN);
            tCtErrorMngGuard.setUpdateDate(safeData.getDSysDate());
            tCtErrorMngGuard.setUpdateUid("APmngEM");

            TCtErrorMngGuardSpec tCtErrorMngGuardSpec = new TCtErrorMngGuardSpec();
            tCtErrorMngGuardSpec.createCriteria()
            .andCreateDateEqualTo(parsed.getInt("trans1_date"))
            .andErrorNoEqualTo(parsed.getString("trans1_seq"));

            try
            {
                tCtErrorMngGuardMapper.updateBySpecSelective(tCtErrorMngGuard, tCtErrorMngGuardSpec);
            } catch (Exception e)
            {
                logger.info("[T_CT_ERROR_MNG_GUARD] Update Err [{}]", e.getMessage());
                throw e;
            }
        }

        logger.info( "[T_CT_ERROR_MNG_GUARD] Update OK" );

    }//end method
}