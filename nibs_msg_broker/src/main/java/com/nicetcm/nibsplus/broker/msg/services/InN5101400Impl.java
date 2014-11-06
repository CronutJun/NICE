package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker CD-VAN 기관 처리결과(거래계좌번호)조회
 *
 * <pre>
 * MngNQ_AP_SaveNiceRslt
 * </pre>
 *
 *           2014. 08. 04    K.D.J.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnNiceTranMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTran;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranSpec;

@Service("inN5101400")
public class InN5101400Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN5101400Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnNiceTranMapper fnNiceTranMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        /*
         *  기업은행 브랜드 제휴 거래처리결과조회 일경우
         *  나이스에 미완료건으로 남아 있는 거래가 정상처리 건으로 들어오면
         *  UPDATE 하도록 한다 20090325
         *  제일은행 추가 정상건은 '00'으로수신 20091113
         */
        if( (parsed.getString("org_cd").equals(MsgBrokerConst.BK_CODE)
          && parsed.getString("deal_status").equals("A"))
        ||  (parsed.getString("org_cd").equals(MsgBrokerConst.JEIL_CODE)
          && parsed.getString("deal_status").equals("00")) ) {
            TFnNiceTran fnNiceTran = new TFnNiceTran();
            TFnNiceTranSpec fnNiceTranSpec = new TFnNiceTranSpec();
            fnNiceTran.setDealStatus( "0" );
            fnNiceTranSpec.createCriteria().andDealYearEqualTo( parsed.getString("deal_date").substring(0, 4) )
                                           .andDealDateEqualTo( parsed.getString("deal_date") )
                                           .andDealNoEqualTo( parsed.getString("deal_no") )
                                           .andDealStatusEqualTo( "2" );
            try {
                fnNiceTranMap.updateBySpecSelective( fnNiceTran, fnNiceTranSpec );
            }
            catch( Exception e ) {
                logger.warn( "T_FN_NICE_TRAN UPDATE Error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
    }
}
