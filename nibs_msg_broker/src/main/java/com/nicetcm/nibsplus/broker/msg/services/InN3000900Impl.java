package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker HOST 전일자 데이터 건수 통보
 * 
 * <pre>
 * MngNC_AP_SaveEnvChk
 * </pre>
 * 
 *           2014. 07. 31    K.D.J. 
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnHostTranCntMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnNiceTranMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnHostTranCnt;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTran;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranSpec;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

@Service("inN3000900")
public class InN3000900Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN3000900Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnHostTranCntMapper fnHostTranCntMap;
    @Autowired private TFnNiceTranMapper fnNiceTranMap;;
    
    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        
        TFnHostTranCnt fnHostTranCnt = new TFnHostTranCnt();
        
        fnHostTranCnt.setDealDate   ( parsed.getString("deal_date"  ) );
        fnHostTranCnt.setNiceDealCnt( parsed.getInt   ("deal_no_cnt") );
        
        try {
            fnHostTranCntMap.insert( fnHostTranCnt );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                fnHostTranCntMap.updateByPrimaryKey( fnHostTranCnt );
            }
            catch( Exception e ) {
                logger.warn( "[T_FN_HOST_TRAN_CNT] UPDATE Err [{}-{}][{}]",
                        parsed.getString("deal_date"), parsed.getString("deal_no_cnt"), e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.warn( "[T_FN_HOST_TRAN_CNT] INSERT Err [{}-{}][{}]",
                    parsed.getString("deal_date"), parsed.getString("deal_no_cnt"), e.getLocalizedMessage() );
            throw e;
        }
        TFnNiceTranSpec fnNiceTranSpec = new TFnNiceTranSpec();
        fnNiceTranSpec.createCriteria().andDealDateEqualTo( parsed.getString("deal_date") );
        int iCnt = fnNiceTranMap.countBySpec( fnNiceTranSpec );
        if( iCnt != parsed.getInt("deal_no_cnt") ) {
            /*
             *  거래건수 불일치 문자 전송
             */
            TMisc cond = new TMisc();
            cond.setSendMsg( String.format("[나이스거래누락] [%s] : HOST-%d건 NIBS-%d건", 
                                  parsed.getString("deal_date"), parsed.getInt("deal_no_cnt"), iCnt) );
            cond.setSendMode( 0 );
            try {
                splMap.spIfSendSMSTranCntMismatch( cond );
            }
            catch( Exception e ) {
                logger.warn("Call spIfSendSMSTranCntMismatch error [{}]", e.getLocalizedMessage());
            }
        }
        else {
            logger.warn("거래건수 일치");
        }
    }
}
