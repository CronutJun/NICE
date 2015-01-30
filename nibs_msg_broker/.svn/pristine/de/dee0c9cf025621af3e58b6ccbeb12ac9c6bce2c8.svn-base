package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnRefundAmtMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnRefundAmt;

/**
 *
 * 1340 자금반환청구
 * <pre>
 * 
 * </pre>
 *
 * @author BHJ
 * @version 1.0
 * @see
 */
@Service("in03101340")
public class In03101340Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101340Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TMiscMapper tMiscMapper;
    

    @Autowired private TFnRefundAmtMapper tFnRefundAmtMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {


        try{ comPack.checkBranchMacLength( parsed ); } catch( Exception e) {}
        
        TFnRefundAmt tFnRefundAmt = new TFnRefundAmt();
        
        tFnRefundAmt.setOrgCd( parsed.getString("CM.org_cd") );
        tFnRefundAmt.setReqDate( parsed.getString("req_date") );
        tFnRefundAmt.setReqTime( parsed.getString("req_time") );
        tFnRefundAmt.setSeqNo( parsed.getString("seq_no") );
        tFnRefundAmt.setResultYn( parsed.getString("result_yn") );
        tFnRefundAmt.setOrgSendYn( parsed.getString("req_type") );
        
        try
        {
            tFnRefundAmtMapper.updateByPrimaryKey(tFnRefundAmt);
        } catch (Exception e)
        {
            logger.warn( "[T_FN_REFUND_AMT] Update Error {}", e.getMessage() );
            throw e;
        }
    }
}
