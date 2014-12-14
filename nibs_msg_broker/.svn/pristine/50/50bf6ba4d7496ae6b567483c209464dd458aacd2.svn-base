package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker NICE 제휴기관 거래번호 통보
 * 
 * <pre>
 * MngNC_NiceOrgDealNo
 * </pre>
 * 
 *           2014. 07. 31    K.D.J. 
 */


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnNiceTranMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTran;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranSpec;

@Service("inN3000800")
public class InN3000800Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN3000800Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnNiceTranMapper fnNiceTranMap;
    
    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TFnNiceTranSpec fnNiceTranSpec = new TFnNiceTranSpec();
        fnNiceTranSpec.createCriteria().andDealYearEqualTo( parsed.getString("deal_year") )
                                       .andDealNoEqualTo( parsed.getString("deal_no") );
        List<TFnNiceTran> rslt = null;
        TFnNiceTran updFnNiceTran = new TFnNiceTran();
        try {
            rslt = fnNiceTranMap.selectBySpec( fnNiceTranSpec );
        }
        catch( Exception e ) {
            logger.warn("MngNC_NiceOrgDealNo [T_FN_NICE_TRAN] Update Error.. {}", e.getLocalizedMessage());
            throw e;
        }
        for( TFnNiceTran fnNiceTran: rslt ) {
            updFnNiceTran.setDealDate( fnNiceTran.getDealDate() );
            updFnNiceTran.setMacNo( fnNiceTran.getMacNo() );
            updFnNiceTran.setAtmDealNo( fnNiceTran.getAtmDealNo() );
            updFnNiceTran.setJoinOrgDealNo( parsed.getString("org_deal_no") ); //제휴번호
            try {
                fnNiceTranMap.updateByPrimaryKeySelective( updFnNiceTran );
            }
            catch( Exception e ) {
                logger.warn( "[T_FN_NICE_TRAN] Update Error..{}", e.getLocalizedMessage() );
                throw e;
            }
        }
    }
}
