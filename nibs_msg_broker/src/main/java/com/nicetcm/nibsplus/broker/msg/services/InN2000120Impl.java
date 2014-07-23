package com.nicetcm.nibsplus.broker.msg.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 나이스 장애처리
 * 
 * <pre>
 * MngNS_NiceSaveErrState
 * </pre>
 * 
 * @author   K.D.J
 * @since    2014.07.23
 * 
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtNiceMngMapper;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TCtNiceMng;


@Service("inN2000120")
public class InN2000120Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN2000120Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    @Autowired private TCtNiceMngMapper niceMngMap;
    
    @Override
    public void inMsgBizProc( MsgBrokerData safeData, MsgParser parsed ) throws Exception {
        
        TMacInfo macInfo = new TMacInfo();
        /*
         * NICE ATM H/W 장애 
         */
        String[] saNiceErrState = {
                "",
                MsgBrokerConst.NICE_ERROR_CASHOUT_ERROR   , /* "121" 지폐방출기                  */
                MsgBrokerConst.NICE_ERROR_CARDREAD_ERROR  , /* "122" 카드판독기                  */
                MsgBrokerConst.NICE_ERROR_SPECS_ERROR     , /* "123" 명세표                        */
                "",
                MsgBrokerConst.NICE_ERROR_INBOX_ERROR     , /* "126" 입금함 2005.11.25 추가  */
                "",
                MsgBrokerConst.NICE_ERROR_BANKBOOK_ERROR  , /* "128" 통장정리부 2005.12.13 추가 */
                "",
                MsgBrokerConst.NICE_ERROR_DEAL_LIST_ERROR , /* "129" 거래내역출력부 2008.12.16 추가 */
                "",
                MsgBrokerConst.NICE_ERROR_CASHIN_ERROR    , /* "131" 지폐미수취              */
                "",
                MsgBrokerConst.NICE_ERROR_DONGUL_ERROR    , /* "133" 동글이                    */
                MsgBrokerConst.NICE_ERROR_DVR_ERROR       , /* "134" DVR 2003.11.13 추가      */
                MsgBrokerConst.NICE_ERROR_INPUT_CHECK     , /* "135" 수표입금부                  */
                MsgBrokerConst.NICE_ERROR_OUT_CHECK       , /* "136" 수표출금부              */
                MsgBrokerConst.NICE_ERROR_INPUT_BOX_50000 , /* "137" 오만원권입금부            */
                MsgBrokerConst.NICE_ERROR_INPUT_BOX_100000, /* "138" 십만원권입금부            */
                "",
                MsgBrokerConst.NICE_ERROR_REMAIN_MONEY
        };
        macInfo.setOrgCd( parsed.getString("CM.org_cd") );
        macInfo.setBranchCd( parsed.getString("brch_cd") );
        macInfo.setMacNo( parsed.getString("mac_no") );

        /*
         *  픽에러, 픽이상, 무거래, 잔액조회, 지폐방출후잔액조회
         *  --> 위의 관련에러는 T_CT_NICE_MNG에 insert하지 않는다.
         */
        if( parsed.getString("user_made_err").equals("0") ) {
            insertNiceMng( parsed );
        }
        
    }
    
    /**
     * NICE-장애전문 모니터링을 위한 데이터 저장
     * 
     * @author KDJ originated by 방혜진
     * @since 2008/12/02
     * @param parsed  파싱된 전문
     * @throws Exception
     */

    private void insertNiceMng( MsgParser parsed ) throws Exception {
        
        TCtNiceMng niceMng = new TCtNiceMng();
        
        niceMng.setjDate( parsed.getString("create_date") );      /* 일자 */
        niceMng.setjTime( parsed.getString("create_time") );      /* 시간 */
        niceMng.setMacNo( parsed.getString("mac_no") );           /* 기번 */
        niceMng.setNetS( parsed.getString("network_info") );      /* 통신망 */
        niceMng.setStat( parsed.getString("atm_state") );         /* 상태 */
        
        if( niceMng.getNetS().equals(MsgBrokerConst.NICE_STATE_LINE_ERR) ) {
            TCtNiceMng rsltNiceMng;
            try {
                rsltNiceMng = niceMngMap.selectByCond1( niceMng );
                if( rsltNiceMng == null ) {
                    rsltNiceMng = new TCtNiceMng();
                    rsltNiceMng.setNetS("");
                }
            }
            catch ( Exception e ) {
                rsltNiceMng = new TCtNiceMng();
                rsltNiceMng.setNetS("");
            }
            if( rsltNiceMng.getNetS().equals(MsgBrokerConst.NICE_STATE_LINE_ERR) )
                return;
        }
    }
}
