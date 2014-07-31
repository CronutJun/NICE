package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;

/**
 *
 * 도착예정
 * <pre>
 * MngEM_SaveArrivalSchdule( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01001150")
public class In01001150Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        if(MsgBrokerConst.SHATMS_CODE.equals(parsed.getString("CM.org_cd"))) {
/*
            EXEC SQL update T_CT_ERROR_MNG
            set     RECV_DATE   = TO_CHAR(SYSDATE, 'YYYYMMDD'),          --T_CT_ERROR_NOTI
                    RECV_TIME   = TO_CHAR(SYSDATE, 'HH24MI'),            --T_CT_ERROR_NOTI
                    ARRIVAL_EST_DATE = rtrim(:suBody.schdule_date),      --T_CT_ERROR_NOTI
                    ARRIVAL_EST_TIME = rtrim(:suBody.schdule_time),      --T_CT_ERROR_NOTI
                    RECV_USER_NM = '부분관리',                           --T_CT_ERROR_NOTI
                    SEND_STATUS = '2',                                   --T_CT_ERROR_NOTI
                    SEND_SMS_STATUS = '6040',                            --T_CT_ERROR_NOTI
                    WORK_STATUS = '6050',                                --T_CT_ERROR_BASIC
                    UPDATE_DATE = SYSDATE,
                    UPDATE_UID  = 'ERRmng'

            where   TRANS_DATE  = rtrim(:suBody.trans1_date)
            and     CREATE_DATE > TO_NUMBER(TO_CHAR( SYSDATE - 10, 'YYYYMMDD' ))
            and     ORG_MSG_NO  = rtrim(:suBody.trans1_seq)
            and     ORG_CD      = rtrim(:suHead.org_cd)
            and     JIJUM_CD    = rtrim(:suBody.jijum_cd)
            and     MAC_NO      = rtrim(:suBody.mac_no);
*/
        }
    }
}