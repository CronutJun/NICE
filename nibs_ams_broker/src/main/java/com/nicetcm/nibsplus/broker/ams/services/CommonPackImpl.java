package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * CommonPackImpl
 *
 *  전문관련 DB처리 공통
 *
 *
 * @author  K.D.J
 * @since   2014.08.18
 */

import org.apache.commons.beanutils.BeanUtils;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerConst;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.mapper.TCmMacMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TCmSiteMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMsgMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMsgHisMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMacEnvMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMacEnvHisMapper;
import com.nicetcm.nibsplus.broker.ams.model.TCmMacKey;
import com.nicetcm.nibsplus.broker.ams.model.TCmMac;
import com.nicetcm.nibsplus.broker.ams.model.TCmSiteKey;
import com.nicetcm.nibsplus.broker.ams.model.TCmSite;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsgHis;
import com.nicetcm.nibsplus.broker.ams.model.TRmMacEnv;
import com.nicetcm.nibsplus.broker.ams.model.TRmMacEnvHis;
import com.nicetcm.nibsplus.broker.common.MsgParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Component
public class CommonPackImpl implements CommonPack {

    private static final Logger logger = LoggerFactory.getLogger(CommonPackImpl.class);

    @Autowired protected DataSourceTransactionManager msgTX;

    @Autowired private TCmMacMapper                   cmMacMap;
    @Autowired private TCmSiteMapper                  cmSiteMap;
    @Autowired private TRmMsgMapper                   msgMap;
    @Autowired private TRmMsgHisMapper                msgHisMap;
    @Autowired private TRmMacEnvMapper                macEnvMap;
    @Autowired private TRmMacEnvHisMapper             macEnvHisMap;

    @Override
    public void insUpdMsg(AMSBrokerData safeData, AMSBrokerReqJob reqJob, TRmMsg msg, TRmMsgHis msgHis) throws Exception {

        if( msg.getCreateDate() == null )
            msg.setCreateDate( safeData.getMsgDate() );
        if( msg.getCreateTime() == null )
            msg.setCreateTime( safeData.getMsgTime() );
        if( msg.getMacNo() == null )
            msg.setMacNo( reqJob.getMacNo() );

        msg.setInsertDate( safeData.getSysDate() );
        msg.setInsertUid( reqJob.getTrxUid() );
        msg.setUpdateDate( safeData.getSysDate() );
        msg.setUpdateUid( reqJob.getTrxUid() );
        msg.setTrxDate( reqJob.getTrxDate() );
        msg.setTrxNo( reqJob.getTrxNo() );

        BeanUtils.copyProperties(msgHis, msg);

        try {
            msgMap.insert( msg );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                msg.setInsertUid ( null );
                msg.setInsertDate( null );
                msgMap.updateByPrimaryKeySelective( msg );
            }
            catch( Exception e ) {
                logger.info( String.format("T_RM_MSG update error, CreateDate = %s, MsgSeq = %s, error = %s",
                                             msg.getCreateDate(), msg.getMsgSeq(), e.getLocalizedMessage()) );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.info( String.format("T_RM_MSG insert error, CreateDate = %s, MsgSeq = %s, error = %s",
                    msg.getCreateDate(), msg.getMsgSeq(), e.getLocalizedMessage()) );
            throw e;
        }

        msgHis.setHisSeq( msgHisMap.generateKey(msgHis) );
        try {
            msgHisMap.insert( msgHis );
        }
        catch( Exception e ) {
            logger.info( String.format("T_RM_MSG_HIS insert error, CreateDate = %s, MsgSeq = %s, error = %s",
                    msg.getCreateDate(), msg.getMsgSeq(), e.getLocalizedMessage()) );
        }

    }

    /**
     *
     *  기기환경정보 응답 또는 환경설정 응답의 결과를 DB처리한다.
     *
     * @param safeData  Thread Safe 데이터
     * @param parsed   응답전문을 파싱한 결과
     * @param reqJob   요청정보
     * @throws Exception
     *
     */
    @Override
    public void insUpdMacEnv(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob) throws Exception {

        TRmMacEnv macEnv = new TRmMacEnv();
        TCmMacKey cmMacKey = new TCmMacKey();

        macEnv.setOrgCd     ( AMSBrokerConst.NICE_ORG_CD         );
        macEnv.setBranchCd  ( AMSBrokerConst.NICE_BR_CD          );
        macEnv.setMacNo     ( parsed.getString("CM._SSTNo").substring(2) );
        macEnv.setInsertUid ( reqJob.getTrxUid()                 );
        macEnv.setInsertDate( safeData.getSysDate()              );
        macEnv.setUpdateUid ( reqJob.getTrxCd()                  );
        macEnv.setUpdateDate( safeData.getSysDate()              );
        macEnv.setSts       ( "0"                                );

        cmMacKey.setOrgCd   ( macEnv.getOrgCd()                  );
        cmMacKey.setBranchCd( macEnv.getBranchCd()               );
        cmMacKey.setMacNo   ( macEnv.getMacNo()                  );
        try {
            TCmMac cmMac = cmMacMap.selectByPrimaryKey( cmMacKey );
            if( cmMac != null ) {
                macEnv.setSiteCd( cmMac.getSiteCd() );
                TCmSiteKey cmSiteKey = new TCmSiteKey();
                cmSiteKey.setOrgCd   ( cmMac.getOrgCd()    );
                cmSiteKey.setBranchCd( cmMac.getBranchCd() );
                cmSiteKey.setSiteCd  ( cmMac.getSiteCd()   );
                try {
                    TCmSite cmSite = cmSiteMap.selectByPrimaryKey( cmSiteKey );
                    if( cmSite != null ) {
                        macEnv.setDeptCd  ( cmSite.getDeptCd()   );
                        macEnv.setOfficeCd( cmSite.getOfficeCd() );
                        macEnv.setTeamCd  ( cmSite.getTeamCd()   );
                    }
                }
                catch( Exception e ) {
                    logger.info("T_CM_SITE Select error. [{}]", e.getLocalizedMessage() );
                    throw e;
                }
            }
            else {
                macEnv.setSiteCd( "000000" );
            }
        }
        catch( Exception e ) {
            logger.info("T_CM_MAC Select error. [{}]", e.getLocalizedMessage() );
            throw e;
        }
        int fCnt = parsed.getInt("FieldCount");
        String fID;
        for( int i = 0; i < fCnt; i++ ) {
            fID   = parsed.getString(String.format("FD[%d].FieldID", i));
            if( fID.equals("003") ) {         // 기기제조번호
                macEnv.setPrdcNo( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("004") ) {   // 기기제조사코드
                macEnv.setMkrCd( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("005") ) {   // 기기종류코드
                macEnv.setModelCd( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("006") ) {   // 기기설치장소
                macEnv.setSetPlace( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("007") ) {   // ATM IP 주소1
                macEnv.setPriIpAddr( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("008") ) {   // ATM IP 주소2
                macEnv.setPubIpAddr( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("009") ) {   // Host IP 주소
                macEnv.setHostIpAddr( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("010") ) {   // Host IP 포트
                macEnv.setHostIpPort( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("011") ) {   // AOC IP 주소
                macEnv.setAocIpAddr( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("012") ) {   // AOC IP 포트
                macEnv.setAocIpPort( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("013") ) {   // 리부팅 예약시간
                macEnv.setRebootTime( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("014") ) {   // ATM IP 포트
                macEnv.setIpPort( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("100") ) {   // 자행최대출금가능금액
                macEnv.setOurMaxWdrAmt( parsed.getLong(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("101") ) {   // 타행최대출금가능금액
                macEnv.setThrMaxWdrAmt( parsed.getLong(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("102") ) {   // 자행최대입금가능금액
                macEnv.setOurMaxDpsAmt( parsed.getLong(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("103") ) {   // 타행최대입금가능금액
                macEnv.setThrMaxDpsAmt( parsed.getLong(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("104") ) {   // 자행최대이체가능금액
                macEnv.setOurMaxTsfAmt( parsed.getLong(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("105") ) {   // 타행최대이체가능금액
                macEnv.setThrMaxTsfAmt( parsed.getLong(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("106") ) {   // 최대서비스출금가능금액
                macEnv.setMaxSvcWdrAmt( parsed.getLong(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("107") ) {   // 최대출금가능현금매수
                macEnv.setMaxCashWdrCnt( parsed.getShort(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("108") ) {   // 최대입금가능현금매수
                macEnv.setMaxCashDpsCnt( parsed.getShort(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("109") ) {   // 최대서비스출금가능현금매수
                macEnv.setMaxCashSvcWdrCnt( parsed.getShort(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("200") ) {   // 모뎀릴레이 사용여부
                macEnv.setModemRelayYn( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("201") ) {   // RPC 사용여부
                macEnv.setRpcYn( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("202") ) {   // 신용카드 거래사용 설정
                macEnv.setCreditCardYn( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("203") ) {   // 카드론 거래사용 설정
                macEnv.setCardLoanYn( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("204") ) {   // 하이패스 충전사용 설정
                macEnv.setChrgHipassYn( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("205") ) {   // 해외카드 설정
                macEnv.setFrgnTranType( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("300") ) {   // OS
                macEnv.setMacOs( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("301") ) {   // CPU
                macEnv.setMacCpu( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("302") ) {   // Memory
                macEnv.setMacMem( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
            else if( fID.equals("303") ) {   // HDD
                macEnv.setMacHdd( parsed.getString(String.format("FD[%d].FieldData", i)) );
            }
        }

        if( macEnv.getModemRelayYn() != null ) {
            if( macEnv.getModemRelayYn().equals("0") )
                macEnv.setModemRelayYn("N");
            else if( macEnv.getModemRelayYn().equals("1") )
                macEnv.setModemRelayYn("Y");
        }
        if( macEnv.getRpcYn() != null ) {
            if( macEnv.getRpcYn().equals("0") )
                macEnv.setRpcYn("N");
            else if( macEnv.getRpcYn().equals("1") )
                macEnv.setRpcYn("Y");
        }
        if( macEnv.getCreditCardYn() != null ) {
            if( macEnv.getCreditCardYn().equals("0") )
                macEnv.setCreditCardYn("N");
            else if( macEnv.getCreditCardYn().equals("1") )
                macEnv.setCreditCardYn("Y");
        }
        if( macEnv.getCardLoanYn() != null ) {
            if( macEnv.getCardLoanYn().equals("0") )
                macEnv.setCardLoanYn("N");
            else if( macEnv.getCardLoanYn().equals("1") )
                macEnv.setCardLoanYn("Y");
        }
        if( macEnv.getChrgHipassYn() != null ) {
            if( macEnv.getChrgHipassYn().equals("0") )
                macEnv.setChrgHipassYn("N");
            else if( macEnv.getChrgHipassYn().equals("1") )
                macEnv.setChrgHipassYn("Y");
        }

        TRmMacEnvHis macEnvHis = new TRmMacEnvHis();

        BeanUtils.copyProperties( macEnvHis, macEnv );

        try {
            macEnvMap.insertSelective( macEnv );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            macEnv.setInsertDate( null );
            macEnv.setInsertUid ( null );
            macEnv.setSts       ( null );
            try {
                macEnvMap.updateByPrimaryKeySelective( macEnv );
            }
            catch( Exception e ) {
                logger.info("T_RM_MAC_ENV Update error {}", e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.info("T_RM_MAC_ENV Insert error {}", e.getLocalizedMessage() );
            throw e;
        }

        macEnvHis.setTrxDate( reqJob.getTrxDate() );
        macEnvHis.setTrxNo  ( reqJob.getTrxNo()   );

        try {
            macEnvHisMap.insertSelective( macEnvHis );
        }
        catch( Exception e ) {
            logger.info("T_RM_MAC_ENV_HIS Insert error {}", e.getLocalizedMessage() );
            throw e;
        }

    }

}
