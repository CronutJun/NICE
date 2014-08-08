package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;

/**
 *
 * 1140 기기점검결과
 * <pre>
 * MngIQ_AP_SaveMacCheckRslt( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04101140")
public class In04101140Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        /*====> 엘지에서 기기사로부터 받아 은행으로 바로 전송하므로 사용하지 않음 20090212
        EXEC SQL UPDATE T_CT_MAC_CHECK_RSLT
            SET
                    ORG_SEND_YN = '1',
                    ORG_SEND_DATE = SYSDATE,
                    UPDATE_UID = 'APmng'
        WHERE ORG_CD        = :suHead.org_cd
            AND JIJUM_CD    = f_get_nice_jijum_cd(rtrim(:suHead.org_cd), rtrim(:suBody.jijum_cd), '', rtrim(:suBody.mac_no))
            AND MAC_NO      = :suBody.mac_no
            AND CHECK_DATE  = :suBody.check_date
            AND SEQ         = to_number(:suBody.serial_no);

        if ( sqlca.sqlcode )
        {
            logger( "[T_CT_MAC_CHECK_RSLT] Update Error [%.200s]\n", SqlErrMsg );
            EXEC SQL ROLLBACK;
            return -1;
        }

        EXEC SQL COMMIT WORK;
        logger( "[T_CT_MAC_CHECK_RSLT] Update OK\n" );
        */


    }
}