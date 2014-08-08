package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorMngGuardMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngGuard;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngGuardSpec;

/**
 *
 * 경비사 출동요청 정보 변경 (고객대기 )
 * <pre>
 * MngEM_AP_SaveGuardChngMsg( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01100180")
public class In01100180Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCtErrorMngGuardMapper tCtErrorMngGuardMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        String hSEND_YN;

        if(parsed.getString("CM.ret_cd").equals("0000")) {
            /* 고객대기 장애는 미완료 장애중 출동요청 전문이 성공한 */
            /* 경우만 송신하여 정상응답을 수신 받으면    */
            /* GUARD_SEND_YN = 'A' 로 설정한다                                   */
            hSEND_YN = "A";
        } else {
            if(parsed.getString("CM.ret_cd_src").equals("B")) {
                hSEND_YN = "D";
            } else {
                /* HOST 오류 메시지를 받은 경우는 다음에 다시 송신 할 수 있도록    */
                /* GUARD_SEND_YN를 바꾸지 않도록 한다.                           */
                throw new MsgBrokerException(-1);
            }
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

        logger.info( "[T_CT_ERROR_MNG_GUARD] Update OK" );
    }
}