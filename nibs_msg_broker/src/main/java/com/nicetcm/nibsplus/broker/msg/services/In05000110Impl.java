package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker ATM 상태전문 수신처리
 * 
 * <pre>
 * MngES_SaveErrState
 * </pre>
 * 
 *           2014. 07. 16    K.D.J.
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

@Service("in05000110")
public class In05000110Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In05000110Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    
    @Override
    public void inMsgBizProc(MsgParser parsed) throws Exception {
        
        TMacInfo macInfo = new TMacInfo();
        macInfo.setOrgCd( parsed.getString("CM.org_cd") );
        macInfo.setBranchCd( parsed.getString("brch_cd") );
        macInfo.setMacNo( parsed.getString("mac_no") );
        /**
         * 지점코드, 기번 길이 검증
         */
        comPack.checkBranchMacLength( macInfo );
        
        /*
         * 출동알림은 코너정보만 들어오므로 대표기번을 따야 한다. 
         */
        if( parsed.getString("CM.msg_id").equals("ALARM") ) {
            /*
             * 출동알림은 기번자리에 코너코드를 받는다 
             */
            comPack.getMacNoIntoSite( macInfo );
            parsed.setString("mac_no", macInfo.getMacNo());
        }
        try {
            /**
             * 기기정보 취득
             */
            comPack.getMacInfo( macInfo );
        }
        catch (Exception e) {
            logger.debug("GetMacInfo Error");
            logger.info( "[01003100] 기기정보 검색 실패 기관[{}] 지점[{}] 기번[{}]",
                    macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo() );
            /**
             * SMS 전송
             */
            splMap.SendSMSMacInfo( macInfo );
            throw e;
        }
        
        logger.info("기관[{}] 지점[{}] 기번[{}] 기기명[{}] 부서[{}] 사무소[{}] 지소[{}] 보수모드[{}] 중지여부[{}]",
                macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo(), macInfo.getMacNm(),
                macInfo.getDeptCd(),macInfo.getOfficeCd(), macInfo.getTeamCd(), parsed.getString("state_term_mode"),
                parsed.getString("state_off_yn"));

        /*
         * 상태전문에 대한 응답을 보내지 않는 기관
         * - 기업은행 상관없이 걍 보냄
         */
        
        /*
         * 부산은행일 경우 발생일자 발생시간을 1차통지 거래일자, 및 일련번호로 설정 
         * 20080624 by BHJ 
         * 부산은행일 경우 발생일자 발생시간은 접수일 접수시간으로 20110601 채정아 요청 
         * 현대증권일 경우 발생일자 발생시간은 접수일 접수시간으로 20120621 조규석 요청 
         */

    }
}
