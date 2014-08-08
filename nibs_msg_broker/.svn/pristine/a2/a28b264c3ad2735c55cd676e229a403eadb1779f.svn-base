package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;

/**
 *
 * 0120 보수요원관리
 * <pre>
 * MngIQ_SaveAnsStaffInfo( pRecvData, pSendBuf, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04000120")
public class In04000120Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In04000120Impl.class);

    @Autowired private TMiscMapper tMiscMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        try
        {
            String mbrYn = tMiscMapper.getCmMemberYn(parsed.getString("mbr_idno"), parsed.getString("mbr_nm"));

            if(mbrYn == null) {
                logger.info("...보수요원 데이타 없음");
                parsed.setString("mbr_yn", "2");
            } else {
                parsed.setString("mbr_yn", mbrYn);
            }


        } catch (Exception e)
        {
            logger.info(">>> [fnDBGetStaff] 보수요원 파악 실패 [{}]", e.getMessage());
            throw new MsgBrokerException(-1);
        }
    }
}
