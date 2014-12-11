package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 기기위치 정보
 *
 * <pre>
 * MngNQ_NiceMacLocation
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
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnNiceTranMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranJoin;

@Service("inN5000410")
public class InN5000410Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN5000410Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnNiceTranMapper fnNiceTranMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        safeData.setKeepResData(false);

        TFnNiceTranJoin cond = new TFnNiceTranJoin();
        TFnNiceTranJoin rslt = null;

        cond.setOrgCd( parsed.getString("CM.org_cd") );
        cond.setDealYear( parsed.getString("deal_date").substring(0,4) );
        cond.setDealDate( parsed.getString("deal_date") );
        cond.setDealNo( parsed.getString("deal_no") );

        try {
            rslt = fnNiceTranMap.selectByJoin1( cond );
            if( rslt == null ) {
                parsed.setString( "CM.ret_cd", "91" );
                logger.warn( String.format("...해당 거래내역 없음 DEAL_DATE[%s] DEAL_NO[%s]",
                        parsed.getString("deal_date"), parsed.getString("deal_no")) );
                throw new MsgBrokerException( String.format("...해당 거래내역 없음 DEAL_DATE[%s] DEAL_NO[%s]",
                        parsed.getString("deal_date"), parsed.getString("deal_no")), -91 );
            }
            parsed.setString("mac_gb",   rslt.getMacCl()   ); /*  기기구분(제휴/브랜드/결합부스)   */
            parsed.setString("tel_no",   rslt.getTelNo()   ); /*  관리전화번호                     */
            parsed.setString("site_nm",  rslt.getSiteNm()  ); /*  사이트명                         */
            parsed.setString("set_addr", rslt.getSetAddr() ); /*  사이트주소                       */
        }
        catch( Exception e ) {
            parsed.setString( "CM.ret_cd", "91" );
            logger.warn( String.format("...해당 거래내역 없음 DEAL_DATE[%s] DEAL_NO[%s][errcd-%s]",
                    parsed.getString("deal_date"), parsed.getString("deal_no"), e.getLocalizedMessage()) );
            throw e;
        }
        safeData.setKeepResData( false );
    }
}
