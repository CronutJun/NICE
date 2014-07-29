package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 기업은행 브랜드제휴 장애, 개국, 상태
 * 
 * <pre>
 * MngES_SaveIBKBrandErrState
 * </pre>
 * 
 *           2014. 07. 29    K.D.J.
 */

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorBasicMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasicSpec;

@Service("in05001130")
public class In05001130Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In05001130Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    @Autowired private TCtErrorBasicMapper errBasicMap;
    
    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCtErrorBasicSpec errBasicSpec = new TCtErrorBasicSpec();
        /*
         * 전송된 장애의 경우, org_send_yn = '1' 로 설정한다.
         * 정상 및 개국신호의 경우, org_send_yn = '3' 으로 설정한다.
         */
        if( parsed.getString("cl_cd").equals("2") ) {  // 중지중(장애)
            errBasicSpec.createCriteria().andCreateDateEqualTo( parsed.getInt("create_date") )
                                         .andCreateTimeEqualTo( parsed.getString("create_time") )
                                         .andOrgCdEqualTo( "096" )
                                         .andBranchCdEqualTo( "9600" )
                                         .andMacNoEqualTo( parsed.getString("mac_no") )
                                         .andErrorCdEqualTo( parsed.getString("mac_error_cd") );
            List<TCtErrorBasic> rslt = null;
            try {
                rslt = errBasicMap.selectBySpec( errBasicSpec );
                if( rslt.size() == 0 ) {
                    logger.info( String.format("[SaveIBKBrandErrState][ERR][Pri] NO_DATA: CREATE_DATE[%s] CREATE_TIME[%s] MAC_NO[%s] ERROR_CD[%s] UPDATE Error [%s]", 
                            parsed.getString("create_date"), parsed.getString("create_time"), 
                            parsed.getString("mac_no"), parsed.getString("mac_error_cd")) );
                    throw new Exception( String.format("[SaveIBKBrandErrState][ERR][Pri] NO_DATA: CREATE_DATE[%s] CREATE_TIME[%s] MAC_NO[%s] ERROR_CD[%s] UPDATE Error [%s]", 
                            parsed.getString("create_date"), parsed.getString("create_time"), 
                            parsed.getString("mac_no"), parsed.getString("mac_error_cd")) );
                }
            }
            catch( Exception e) {
                logger.info( String.format("[SaveIBKBrandErrState][ERR][Pri] CREATE_DATE[%s] CREATE_TIME[%s] MAC_NO[%s] ERROR_CD[%s] UPDATE Error [%s]", 
                        parsed.getString("create_date"), parsed.getString("create_time"), 
                        parsed.getString("mac_no"), parsed.getString("mac_error_cd"), e.getLocalizedMessage()) );
                throw new Exception( String.format("[SaveIBKBrandErrState][ERR][Pri] CREATE_DATE[%s] CREATE_TIME[%s] MAC_NO[%s] ERROR_CD[%s] UPDATE Error [%s]", 
                        parsed.getString("create_date"), parsed.getString("create_time"), 
                        parsed.getString("mac_no"), parsed.getString("mac_error_cd"), e.getLocalizedMessage()) );
            }
            
            TCtErrorBasic updBasic = new TCtErrorBasic();
            for( TCtErrorBasic errBasic: rslt ) {
                updBasic.setOrgSendYn( "1" );
                updBasic.setUpdateDate( safeData.getDSysDate() );
                updBasic.setUpdateUid( "online" );
                updBasic.setErrorNo( errBasic.getErrorNo() );
                updBasic.setCreateDate( errBasic.getCreateDate() );
                updBasic.setCreateTime( errBasic.getCreateTime() );
                try {
                    errBasicMap.updateByPrimaryKeySelective( updBasic );
                }
                catch( Exception e ) {
                    logger.info( String.format("[SaveIBKBrandErrState][ERR][Pri] CREATE_DATE[%s] CREATE_TIME[%s] MAC_NO[%s] ERROR_CD[%s] UPDATE Error [%s]", 
                            parsed.getString("create_date"), parsed.getString("create_time"), 
                            parsed.getString("mac_no"), parsed.getString("mac_error_cd"), e.getLocalizedMessage()) );
                    throw new Exception( String.format("[SaveIBKBrandErrState][ERR][Pri] CREATE_DATE[%s] CREATE_TIME[%s] MAC_NO[%s] ERROR_CD[%s] UPDATE Error [%s]", 
                            parsed.getString("create_date"), parsed.getString("create_time"), 
                            parsed.getString("mac_no"), parsed.getString("mac_error_cd"), e.getLocalizedMessage()) );
                }
            }
        }
    }
}
