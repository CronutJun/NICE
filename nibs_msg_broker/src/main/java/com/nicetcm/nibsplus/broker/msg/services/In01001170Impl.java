package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;

/**
 *
 * 조치결과통보
 * <pre>
 * MngEM_SaveMngResult( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01001170")
public class In01001170Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        if(MsgBrokerConst.SHATMS_CODE.equals(parsed.getString("CM.org_cd"))) {
            /* 신한은행 부분관리의 경우 무조건 완료로 처리 */
/* 20140811 여기까지 코딩 */
/*
            TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
            updateTCtErrorMng.setFinishDate (parsed.getString(""));
            updateTCtErrorMng.setFinishTime (parsed.getString(""));
            updateTCtErrorMng.setFinishType (parsed.getString(""));
            updateTCtErrorMng.setErrorStatus(parsed.getString(""));
            updateTCtErrorMng.setFinishNm   (parsed.getString(""));
            updateTCtErrorMng.setRecvTeleNo (parsed.getString(""));
            updateTCtErrorMng.setWorkStatus (parsed.getString(""));
            updateTCtErrorMng.setMsg        (parsed.getString(""));
            updateTCtErrorMng.setOrgSendYn  (parsed.getString(""));
            updateTCtErrorMng.setUpdateDate (parsed.getString(""));
            updateTCtErrorMng.setUpdateUid  (parsed.getString(""));
            */
        }
    }
}
