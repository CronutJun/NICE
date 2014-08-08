package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 나이스 요청전문 에대한 응답일 경우 - 경비사
 * 
 * 
 *           2014. 07. 07    K.D.J.
 */

import java.util.Date;
import java.util.List;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerTransaction;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.*;
import com.nicetcm.nibsplus.broker.msg.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;

@Service("in01100130")
public class In01100130Impl implements InMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(In01100130Impl.class);
    
    @Autowired private SqlSession sqlSession;
    @Autowired private DataSourceTransactionManager msgTX;
    
    @Autowired private CommonPack comPack;
    @Autowired private StoredProcMapper splMap;

    @Autowired private TCtErrorBasicMapper errBasicMap;
    @Autowired private TCtErrorMngMadeComMapper errMngMadeComMap;
    @Autowired private TCtErrorNotiMapper errNotiMap;
    @Autowired private TCtErrorMngGuardMapper errMngGuardMap;

    @Override
    public void inMsgHandle(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        
        TransactionStatus status = msgTX.getTransaction( MsgBrokerTransaction.defMSGTX );
        try {
            String sSysDate = MsgBrokerLib.SysDate();
            String sNSysDate = MsgBrokerLib.SysDate(1);
            String sSysTime = MsgBrokerLib.SysTime();
            Date   dSysDate = MsgBrokerLib.SysDateD(0);
            
            TCtErrorBasic errBasic = new TCtErrorBasic();
            TCtErrorNoti errNoti = new TCtErrorNoti();
            TCtErrorMngMadeCom errMngMadeCom = new TCtErrorMngMadeCom();
            TCtErrorMngGuard errMngGuard = new TCtErrorMngGuard();
            
            if( parsed.getString("CM.ret_cd").equals("0000") 
            || ( parsed.getString("CM.org_cd").equals(MsgBrokerConst.TAXRF_CODE) 
              && parsed.getString("CM.ret_cd").equals("00") )) {
                errBasic.setSendYn("Y");
                errBasic.setWorkStatus("6040");
                errNoti.setSendStatus("2");
                errNoti.setSendSmsStatus("6040");
                errNoti.setSendTool("COM");
            }
            else {
                if( parsed.getString("CM.ret_cd_src").equals("B") )
                    errBasic.setSendYn("B");
                else
                    errBasic.setSendYn("H");
                errBasic.setWorkStatus("6030");
                errNoti.setSendStatus("3");
                errNoti.setSendSmsStatus("6030");
            }
    
            try {
                /*
                 * 기기사에서 수신 받은 전문은 2차 출동요청 테이블에서 처리
                 */
                if( parsed.getString("CM.org_cd").equals(MsgBrokerConst.ATM_HY_CODE) 
                ||  parsed.getString("CM.org_cd").equals(MsgBrokerConst.ATM_CH_CODE)
                ||  parsed.getString("CM.org_cd").equals(MsgBrokerConst.ATM_LG_CODE) ) {
                    /*
                     * 현재 기기사와 전문 연결하여 요청하는 기관은 신한은행만 이므로
                     * 기관코드를 임시로 '088'로 넣어 주도록 한다.
                     * - 전기관 확대시 기기사와의 전문 사양도 수정 필요
                     */
                    TCtErrorMngMadeComSpec errMngMadeComSpec = new TCtErrorMngMadeComSpec();
                    errMngMadeComSpec.createCriteria().andAsAcptDateEqualTo( parsed.getString("trans1_date") )
                                                      .andOrgCdEqualTo( MsgBrokerConst.SHATMS_CODE )
                                                      .andBranchCdEqualTo( parsed.getString("brch_cd") )
                                                      .andMacNoEqualTo( parsed.getString("mac_no") )
                                                      .andOrgCallCntEqualTo( Short.parseShort(parsed.getString("org_call_cnt")) );
                    errMngMadeCom.setOrgSendYn("1");
                    errMngMadeCom.setUpdateDate( dSysDate );
                    errMngMadeCom.setUpdateUid( "APmngEM" );
                
                    errMngMadeComMap.updateBySpecSelective( errMngMadeCom, errMngMadeComSpec );
                }
                else {
                    TCtErrorBasicSpec errBasicSpec = new TCtErrorBasicSpec();
                    errBasicSpec.createCriteria().andCreateDateEqualTo( parsed.getInt("trans1_date") )
                                               .andErrorNoEqualTo( parsed.getString("trans1_seq") );
                    TCtErrorNotiSpec errNotiSpec = new TCtErrorNotiSpec();
                    errNotiSpec.createCriteria().andCreateDateEqualTo( parsed.getInt("trans1_date") )
                                                .andErrorNoEqualTo( parsed.getString("trans1_seq") );
                    errBasic.setUpdateDate( dSysDate );
                    errBasic.setUpdateUid( "APmngEM" );
                    errNoti.setUpdateDate( dSysDate );
                    errNoti.setUpdateUid( "APmngEM" );
                    
                    errBasicMap.updateBySpecSelective( errBasic, errBasicSpec );
                    errNotiMap.updateBySpecSelective( errNoti, errNotiSpec );
                }
                /*
                 * 20100526 출동요청전문 전송 전에 고객대기건이 접수 된경우 혹은 장애접수시 고객대기로 접수될경우
                 * GUARD_SEND_YN = 'E' 로저장하여 고객대기 전문전에 출동요청전문이 전송될수 있도록 수정
                 * 'E'였던 출동요청의 응답을 수신받으면 다시 고객대기 전문이 송신 될 수 있도록 'C'로 바꾼다.
                 */
                TCtErrorMngGuardSpec errMngGuardSpec = new TCtErrorMngGuardSpec();
                errMngGuardSpec.createCriteria().andCreateDateEqualTo( parsed.getInt("trans1_date") )
                                                .andErrorNoEqualTo( parsed.getString("trans1_seq") );
                List<TCtErrorMngGuard> ret = errMngGuardMap.selectBySpec( errMngGuardSpec );
                if( ret.size() > 0 ) {
                    if( errBasic.getSendYn().equals("Y") )
                        if( ret.get(0).getGuardSendYn().equals("E") )
                            errMngGuard.setGuardSendYn("C");
                        else
                            errMngGuard.setGuardSendYn("Y");
                    else
                        errMngGuard.setGuardSendYn(errBasic.getSendYn());
                    errMngGuard.setUpdateDate( dSysDate );
                    errMngGuard.setUpdateUid( "APmngEM" );
                    
                    errMngGuardMap.updateBySpecSelective( errMngGuard,  errMngGuardSpec );
                }
            }
            catch ( Exception e ) {
                logger.info("Error raised message = [{}][{}]", e.getMessage(), e.getStackTrace().toString() );
                throw e;
            }

            msgTX.commit(status);
        }
        catch( Exception e ) {
            e.printStackTrace();
            logger.error(e.getMessage());
            msgTX.rollback(status);
            throw e;
        }
    }

}
