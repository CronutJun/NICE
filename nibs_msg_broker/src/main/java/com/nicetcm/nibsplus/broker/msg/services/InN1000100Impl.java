package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 나이스 실적
 * 
 * 
 *           2014. 07. 08    K.D.J.
 */

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;

import javax.jms.BytesMessage;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerProducer;
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

@Service("inN1000100")
public class InN1000100Impl implements InMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(InN1000100Impl.class);
    
    @Autowired private SqlSession sqlSession;
    @Autowired private DataSourceTransactionManager msgTX;
    
    @Autowired private CommonPack comPack;
    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnRcInfoMapper fnRCInfoMap;
    @Autowired private TFnNiceTranMapper fnNiceTranMap;
    @Autowired private TFnNiceTranCuponMapper fnNiceTranCuponMap;
    @Autowired private TFnNiceTranGiftMapper fnNiceTranGiftMap;
    @Autowired private TFnMacMapper fnMacMap;
    
    private String sSysDate;
    private String sNSysDate;
    private String sSysTime;
    private Date   dSysDate;
    
    private enum HW_MODULE_ERR {
        HW_LINE_ERR,        /* 0 - 통신장애         */
        HW_CASH_OUT,        /* 1 - 지폐방출기        */
        HW_CARD_READ,       /* 2 - 카드판독기        */
        HW_SPECS,           /* 3 - 명세표              */
        HW_JOURNAL,         /* 4 - 감사용지         */
        HW_INPUT_BOX,       /* 5 - 입금함              */
        HW_SYS_DISK,        /* 6 - SYSTEM DISK      */
        HW_BANK_BOOK,       /* 7 - 통장정리부        */
        HW_SYS_CONTROLOR,   /* 8 - SYSTEM 제어부       */
        HW_DEAL_LIST,       /* 9 - 거래내역출력부  */
        HW_ENCODE_MAC,      /* 10- 암호화장비        */
        HW_CASHIN,          /* 11- 지폐미수취        */
        HW_T_MONEY,         /* 12- T-Money 모듈상태 */
        HW_DONGGLEI,        /* 13- 동글이              */
        HW_DVR_ERR,         /* 14- 화상카메라        */
        HW_INPUT_CHECK,     /* 15- 수표입금부        */
        HW_OUT_CHECK,       /* 16- 수표출금부        */
        HW_INPUT_BOX_50000, /* 17- 오만원입금함       */
        HW_INPUT_BOX_100000,/* 18- 십만원입금함       */
        HW_IMSI,            /* 19- 임시               */
        HW_REMAIN_MONEY;    /* 20- 지폐잔류         */
    }
    
    @Override
    public void inMsgHandle(MsgParser parsed) throws Exception {
        TransactionStatus status = msgTX.getTransaction( MsgBrokerTransaction.defMSGTX );
        try {
            sSysDate  = MsgBrokerLib.SysDate();
            sNSysDate = MsgBrokerLib.SysDate(1);
            sSysTime  = MsgBrokerLib.SysTime();
            dSysDate  = MsgBrokerLib.SysDateD(0);
            
            /*
             * 20110719 러시앤캐시 관련 추가
             * 러시앤캐시 대출상담대기 거래는 t_fn_nice_tran에 넣지 않고 다른테이블에 넣은 후 return
             */
            if( parsed.getString("deal_type").equals("36000") ) {
                insertUpdateRCInfo( parsed );
                return;
            }
            /*
             * 20120116 온누리 상품건 관련 거래는 별도의 테이블에 저장후 TRAN 데이터 처리
             * 기업은행 온누리 상품권의 경우 나이스 기기로 등록하여야만
             * HOST에서 기기 개시 및 이체 처리가 가능하므로 나이스 기기로 등록 하지만
             * 자금 웹 화면에서는 일괄 처럼 처리 해야 하므로 기기등록을 096과 0KK 두개로 등록 하도록 함.
             * (송호석,정희성,이재원,방혜진 협의 완료 2012.02.14)
             * 또한 이기기의 거래는 이체만 트란에 쌓아두고 별도 테이블에 이체및 입,출금 데이터를 저장 후
             * '0KK'로 잔액 계산을 하도록 한다.
             * 20120323 정희성,이재원협의 -> 온누리상품권 관련 모든 데이터 트란에 넣도록 수정
             */
            else if( parsed.getString("deal_type").equals("55500")
                  ||  parsed.getString("deal_type").equals("40001") ) {
                try {
                    insertUpdateCuponTran( parsed );
                }
                catch ( Exception e ) {
                    logger.info("insertUpdateCuponTran error {}", e.getMessage() );
                }
                try {
                    updateFNMacProc( parsed, "", 0, 1);
                }
                catch ( Exception e ) {
                    logger.info("MacProc Error.>> 1 <<" );
                }
            }
            
            /*
             * 전자상품권관련 거래 일 경우 별도의 테이블에 저장 후 트란에 저장
             * 지류상품권관련 거래 일경우도 같은 테이블에 저장
             */
            if( parsed.getString("st_org_cd").equals(MsgBrokerConst.GV_CODE)
            ||  parsed.getString("deal_type").equals("55810") ) {
                try {
                    insertUpdateGiftCardTran( parsed );
                }
                catch ( Exception e ) {
                    logger.info("insertUpdateGiftCardTran error {}", e.getMessage() );
                }
            }
            NiceTranReturn ntRet = new NiceTranReturn();
            try {
                insertUpdateNiceTran( parsed, ntRet );
            }
            catch ( MsgBrokerException me ) {
                if( me.getErrorCode() == -999 || me.getErrorCode() == -998 )
                    return;
                logger.info("NiceTranProc Error.");
                throw new Exception("NiceTranProc Error.");
            }
            
            /*
             * t_fn_mac 에 데이터 수정 중 error 에 대해서는 host에 정상응답으로 처리해 준다
             * 잔액조회도 실거래로 인식하도록(무거래에 포함되지 않도록) 한다. 2008.06.23 김희천 대리 요청
             */
            if( parsed.getString("deal_type").equals("01001") 
            ||  parsed.getString("deal_type").substring(0, 1).equals("1")
            /*
             * 잔액조회
             */
            ||  parsed.getString("deal_type").equals("31005") ) {
                logger.info("deal_type : {}", parsed.getString("deal_type") );
                try {
                    updateFNMacProc( parsed, ntRet.prevDealStatus, ntRet.UpInFlag, 0 );
                }
                catch ( Exception e ) {
                    logger.info("MacProc Error.>> 1 <<" );
                }
            }
            /*
             * 농협 ORG_RESPONSE_CD가 A6, A7일 경우, 담당자에게 즉시 문자통보 20121018->20140422
             * 입금거래 일 경우만 적용
             */
            if( (parsed.getString("st_org_cd").equals("017") || parsed.getString("st_org_cd").equals("018"))
            &&  parsed.getString("deal_type").substring(0,1).equals("1") ) {
                if( parsed.getString("org_response_cd").equals("A6")
                ||  parsed.getString("org_response_cd").equals("A7") ) {
                    
                }
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

    
    /**
     * 러쉬앤캐쉬 정보 등록
     * 
     * @author KDJ, 2014/07/08
     * @param parsed  파싱된 전문
     * @throws Exception
     */
    private void insertUpdateRCInfo( MsgParser parsed ) throws Exception {
        
        TFnRcInfo fnRcInfoRec = new TFnRcInfo();
        
        fnRcInfoRec.setDealDate( parsed.getString("deal_date") );
        fnRcInfoRec.setDealNo( parsed.getString("deal_no") );
        fnRcInfoRec.setMacNo( parsed.getString("mac_no") );
        fnRcInfoRec.setDealTime( parsed.getString("deal_time") );
        fnRcInfoRec.setDealType( parsed.getString("deal_type").substring(0,1) );
        fnRcInfoRec.setDealClass( parsed.getString("deal_type").substring(1) );
        fnRcInfoRec.setCompGb( MsgBrokerLib.lpad( parsed.getString("inst_org_cd"), 4, "0") );
        fnRcInfoRec.setHpNo( parsed.getString("account_no") );
        fnRcInfoRec.setInsertDate( dSysDate );
        fnRcInfoRec.setUpdateDate( dSysDate );
        try {
            fnRCInfoMap.insert( fnRcInfoRec );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                fnRcInfoRec.setInsertDate( null );
                fnRCInfoMap.updateByPrimaryKeySelective( fnRcInfoRec );
            }
            catch ( Exception e ) {
                logger.info( ">>> [insertUpdateRCInfo] (T_FN_RC_INFO) UPDATE ERROR [{}]", e.getMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.info( ">>> [insertUpdateRCInfo] (T_FN_RC_INFO) INSERT ERROR [{}]", e.getMessage() );
            throw e;
        }
        
    }
    
    /**
     * 온누리상품권 테이블 저장처리
     *  
     * @author KDJ, 2014/07/08
     * @param parsed
     * @throws Exception
     */
    private void insertUpdateCuponTran( MsgParser parsed ) throws Exception {
        
        TFnNiceTranCupon  fnNTCRec =  new TFnNiceTranCupon();
        
        fnNTCRec.setDealDate( parsed.getString("deal_date") );
        fnNTCRec.setMacNo( parsed.getString("mac_no") );
        fnNTCRec.setAtmDealNo( parsed.getString("atm_deal_no") );
        fnNTCRec.setDealType( parsed.getString("deal_type").substring(0, 1) );
        fnNTCRec.setDealClss( parsed.getString("deal_type").substring(1) );
        fnNTCRec.setInstBranchCd( MsgBrokerLib.rpad(parsed.getString("account_no").substring(0, 4), 4, " ") );
        fnNTCRec.setAccountNo( MsgBrokerLib.rpad(parsed.getString("account_no").substring(4), 20, " ") );
        fnNTCRec.setRealAccountNo( MsgBrokerLib.rpad(parsed.getString("real_account_no"), 16, " ") );
        fnNTCRec.setDealAmt( parsed.getLong("deal_amt") );
        fnNTCRec.setOrgCd( parsed.getString("st_org_cd") );
        fnNTCRec.setDealYear( fnNTCRec.getDealDate().substring(0, 4) );
        fnNTCRec.setDealMmdd( fnNTCRec.getDealDate().substring(4) );
        fnNTCRec.setDealNo( parsed.getString("deal_no") );
        fnNTCRec.setDealStatus( parsed.getString("deal_status") );
        fnNTCRec.setDealTime( parsed.getString("deal_time") );
        fnNTCRec.setDealHour( fnNTCRec.getDealTime().substring(0, 2) );
        fnNTCRec.setDealMi( fnNTCRec.getDealTime().substring(2, 4) );
        fnNTCRec.setDealSec( fnNTCRec.getDealTime().substring(4) );
        fnNTCRec.setDealInAmtCw15( parsed.getLong("cash_cnt_100000") );
        fnNTCRec.setDealInAmtCw54( parsed.getLong("cash_cnt_50000") );
        fnNTCRec.setDealInAmtCw14( parsed.getLong("cash_cnt_10000") );
        fnNTCRec.setDealInAmtCw53( parsed.getLong("cash_cnt_5000") );
        fnNTCRec.setDealInAmtCw13( parsed.getLong("cash_cnt_1000") );
        fnNTCRec.setDealOutAmtCw54( parsed.getLong("out_cnt_50000") );
        fnNTCRec.setDealOutAmtCw14( parsed.getLong("out_cnt_10000") );
        fnNTCRec.setDealOutAmtCw53( parsed.getLong("out_cnt_5000") );
        fnNTCRec.setDealOutAmtCw13( parsed.getLong("out_cnt_1000") );
        fnNTCRec.setDealOutAmtCw52( parsed.getLong("out_cnt_500") );
        fnNTCRec.setDealOutAmtCw12( parsed.getLong("out_cnt_100") );
        fnNTCRec.setDealOutAmtCw51( parsed.getLong("out_cnt_50") );
        fnNTCRec.setDealOutAmtCw11( parsed.getLong("out_cnt_10") );
        fnNTCRec.setInsertDate( dSysDate );
        try {
            fnNiceTranCuponMap.insert( fnNTCRec );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                fnNiceTranCuponMap.updateByPrimaryKeySelective( fnNTCRec );
            }
            catch ( Exception e ) {
                logger.info( ">>> [InsertUpdateCuponTran] (T_FN_NICE_TRAN_CUPON) UPDATE ERROR [{}]", e.getMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.info( ">>> [InsertUpdateCuponTran] (T_FN_NICE_TRAN_CUPON) INSERT ERROR [{}]", e.getMessage() );
            throw e;
        }
        
    }
    
    /*
     * 전자상품권  테이블 저장처리
     */
    private void insertUpdateGiftCardTran( MsgParser parsed ) throws Exception {
        
        TFnNiceTranGift fnNTGRec = new TFnNiceTranGift();
        
        fnNTGRec.setDealDate( parsed.getString("deal_date") );
        fnNTGRec.setMacNo( parsed.getString("mac_no") );
        fnNTGRec.setAtmDealNo( parsed.getString("atm_deal_no") );
        fnNTGRec.setDealType( parsed.getString("deal_type").substring(0, 1) );
        fnNTGRec.setDealClss( parsed.getString("deal_type").substring(1) );
        fnNTGRec.setTrackNo( parsed.getString("track_no") );
        fnNTGRec.setInstOrgCd( parsed.getString("inst_org_cd"));
        fnNTGRec.setInstBranchCd( MsgBrokerLib.rpad(parsed.getString("account_no").substring(0, 4), 4, " ") );
        fnNTGRec.setAccountNo( MsgBrokerLib.rpad(parsed.getString("account_no").substring(4), 20, " ") );
        fnNTGRec.setRealAccountNo( MsgBrokerLib.rpad(parsed.getString("real_account_no"), 16, " ") );
        fnNTGRec.setDealAmt( parsed.getLong("deal_amt") );
        fnNTGRec.setCustFee( parsed.getInt("cust_fee") );
        fnNTGRec.setBankFee( parsed.getInt("bank_fee") );
        fnNTGRec.setResponseCd( parsed.getString("response_cd") );
        fnNTGRec.setRefuseCd( parsed.getString("refuse_cd") );
        fnNTGRec.setOrgResponseCd( parsed.getString("org_response_cd") );
        fnNTGRec.setDealYear( fnNTGRec.getDealDate().substring(0, 4) );
        fnNTGRec.setDealMmdd( fnNTGRec.getDealDate().substring(4) );
        fnNTGRec.setDealNo( parsed.getString("deal_no") );
        fnNTGRec.setAdmisOrg( parsed.getString("admis_org") );
        fnNTGRec.setDealStatus( parsed.getString("deal_status") );
        fnNTGRec.setDealHour( parsed.getString("deal_time").substring(0, 2) );
        fnNTGRec.setDealMi( parsed.getString("deal_time").substring(2, 4) );
        fnNTGRec.setDealSec( parsed.getString("deal_time").substring(4) );
        fnNTGRec.setErrorStatus( parsed.getString("error_status") );
        fnNTGRec.setDealTimeType( parsed.getString("deal_time_type") );
        fnNTGRec.setJoinOrgDealNo( parsed.getString("join_org_deal_no") );
        fnNTGRec.setNetOrgCd( parsed.getString("net_org_cd") );
        fnNTGRec.setDealAmtCw54( parsed.getLong("cash_cnt_50000") * 50000);
        fnNTGRec.setDealAmtCw15( parsed.getLong("cash_cnt_100000") * 100000);
        fnNTGRec.setDealAmtCw13( parsed.getLong("cash_cnt_1000") * 1000);
        fnNTGRec.setDealAmtCw53( parsed.getLong("cash_cnt_5000") * 5000);
        fnNTGRec.setInsertDate( dSysDate );
        fnNTGRec.setUpdateDate( dSysDate );
        fnNTGRec.setBrandOrgCd( parsed.getString("brand_org_cd") );
        fnNTGRec.setUserTelNo( parsed.getString("user_tel_no") );
        fnNTGRec.setGiftInitial( parsed.getString("gift_initial") );
        fnNTGRec.setGiftJumCd( parsed.getString("gift_brch_cd") );
        fnNTGRec.setGiftSubCd( parsed.getString("gift_sub_cd") );
        fnNTGRec.setGiftSeqNo( parsed.getString("gift_seq_no") );
        fnNTGRec.setOriginDealDate( parsed.getString("origin_deal_date") );
        fnNTGRec.setOriginDealNo( parsed.getString("origin_deal_no") );
        
        try {
            fnNiceTranGiftMap.insert( fnNTGRec );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                fnNiceTranGiftMap.updateByPrimaryKeySelective( fnNTGRec );
            }
            catch ( Exception e ) {
                logger.info( ">>> [InsertUpdateGiftTran] (T_FN_NICE_TRAN_GIFT) UPDATE ERROR [{}]", e.getMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.info( ">>> [InsertUpdateGiftTran] (T_FN_NICE_TRAN_GIFT) INSERT ERROR [{}]", e.getMessage() );
            throw e;
        }
        
    }

    /*
     * insertUpdateNiceTran 메소드로 부터 넘겨받을 리턴값을 보관하는 inner class
     */
    private class NiceTranReturn {
        
        String prevDealStatus;
        int   UpInFlag;
    
    }
    
    public void insertUpdateNiceTran( MsgParser parsed, NiceTranReturn ret ) throws Exception {
        
        TFnNiceTran fnNiceTranRec = new TFnNiceTran();
        
        ret.prevDealStatus = " ";
        
        fnNiceTranRec.setDealDate( parsed.getString("deal_date") );
        fnNiceTranRec.setMacNo( parsed.getString("mac_no") );
        fnNiceTranRec.setAtmDealNo( parsed.getString("atm_deal_no") );
        fnNiceTranRec.setDealType( parsed.getString("deal_type").substring(0, 1) );
        fnNiceTranRec.setDealClss( parsed.getString("deal_type").substring(1) );
        fnNiceTranRec.setTrackNo( parsed.getString("track_no") );
        fnNiceTranRec.setInstOrgCd( parsed.getString("inst_org_cd"));
        fnNiceTranRec.setInstBranchCd( MsgBrokerLib.rpad(parsed.getString("account_no").substring(0, 4), 4, " ") );
        fnNiceTranRec.setAccountNo( MsgBrokerLib.rpad(parsed.getString("account_no").substring(4), 20, " ") );
        fnNiceTranRec.setRealAccountNo( MsgBrokerLib.rpad(parsed.getString("real_account_no"), 16, " ") );
        fnNiceTranRec.setDealAmt( parsed.getLong("deal_amt") );
        fnNiceTranRec.setCustFee( parsed.getInt("cust_fee") );
        fnNiceTranRec.setBankFee( parsed.getInt("bank_fee") );
        fnNiceTranRec.setTransOrgCd( parsed.getString("trans_org_cd") );
        fnNiceTranRec.setTransBranchCd( MsgBrokerLib.rpad(parsed.getString("account_no").substring(0, 4), 4, " ") );
        fnNiceTranRec.setTransAccountNo( MsgBrokerLib.rpad(parsed.getString("account_no").substring(4), 20, " ") );
        fnNiceTranRec.setTransDepositor( parsed.getString("trans_depositor") );
        fnNiceTranRec.setResponseCd( parsed.getString("response_cd") );
        fnNiceTranRec.setRefuseCd( parsed.getString("refuse_cd") );
        fnNiceTranRec.setOrgResponseCd( parsed.getString("org_response_cd") );
        fnNiceTranRec.setOrgCd( parsed.getString("st_org_cd") );
        fnNiceTranRec.setDealYear( fnNiceTranRec.getDealDate().substring(0, 4) );
        fnNiceTranRec.setDealMmdd( fnNiceTranRec.getDealDate().substring(4) );
        fnNiceTranRec.setDealNo( parsed.getString("deal_no") );
        fnNiceTranRec.setAdmisOrg( parsed.getString("admis_org") );
        fnNiceTranRec.setDealStatus( parsed.getString("deal_status") );
        fnNiceTranRec.setDealHour( parsed.getString("deal_time").substring(0, 2) );
        fnNiceTranRec.setDealMi( parsed.getString("deal_time").substring(2, 4) );
        fnNiceTranRec.setDealSec( parsed.getString("deal_time").substring(4) );
        fnNiceTranRec.setErrorStatus( parsed.getString("error_status") );
        fnNiceTranRec.setDealTimeType( parsed.getString("deal_time_type") );
        fnNiceTranRec.setJoinOrgDealNo( parsed.getString("join_org_deal_no") );
        fnNiceTranRec.setInsertDate( dSysDate );
        fnNiceTranRec.setUpdateDate( dSysDate );
        fnNiceTranRec.setNetOrgCd( parsed.getString("net_org_cd") );
        fnNiceTranRec.setDealAmt10000( parsed.getLong("cash_cnt_10000") * 10000);
        fnNiceTranRec.setDealAmt50000( parsed.getLong("cash_cnt_50000") * 50000);
        fnNiceTranRec.setDealAmt100000( parsed.getLong("cash_cnt_100000") * 100000);
        fnNiceTranRec.setDealAmt1000( parsed.getLong("cash_cnt_1000") * 1000);
        fnNiceTranRec.setDealAmt5000( parsed.getLong("cash_cnt_5000") * 5000);
        fnNiceTranRec.setCheckAmt( parsed.getLong("check_amt") );
        fnNiceTranRec.setBrandOrgCd( parsed.getString("brand_org_cd") );
        fnNiceTranRec.setUserTelNo( parsed.getString("user_tel_no") );
        fnNiceTranRec.setGiftInitial( parsed.getString("gift_initial") );
        fnNiceTranRec.setGiftJumCd( parsed.getString("gift_brch_cd") );
        fnNiceTranRec.setGiftSubCd( parsed.getString("gift_sub_cd") );
        fnNiceTranRec.setGiftSeqNo( parsed.getString("gift_seq_no") );
        fnNiceTranRec.setOriginDealDate( parsed.getString("origin_deal_date") );
        fnNiceTranRec.setOriginDealNo( parsed.getString("origin_deal_no") );
        fnNiceTranRec.setCalcDate( parsed.getString("calc_date") );
        
        /*
         * 입금시 취소 나 미완료가 들어왔을경우 오류처리(입금은 정상과 거절만 있다. 20051201 BY B.H.J
         */
        if( fnNiceTranRec.getDealTimeType().equals("1") ) {
            if( fnNiceTranRec.getDealStatus().equals("1") || fnNiceTranRec.getDealStatus().equals("2") ) {
                logger.info("입금업무에 취소/미완료가 들어옴[{}][{}]", 
                        ret.prevDealStatus, fnNiceTranRec.getDealStatus() );
                throw new MsgBrokerException( String.format("입금업무에 취소/미완료가 들어옴 [%s][%s]",    
                                                              ret.prevDealStatus, fnNiceTranRec.getDealStatus()),
                                                -998);
            }
        }

        try {
            fnNiceTranMap.insert( fnNiceTranRec );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                fnNiceTranMap.updateByPrimaryKeySelective( fnNiceTranRec );
            }
            catch ( Exception e ) {
                logger.info( ">>> [InsertUpdateNiceTran] (T_FN_NICE_TRAN) UPDATE ERROR [{}]", e.getMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.info( ">>> [InsertUpdateNiceTran] (T_FN_NICE_TRAN) INSERT ERROR [{}]", e.getMessage() );
            throw e;
        }
    }

    private void updateFNMacProc( MsgParser parsed, String prevDealStatus, int upInFlag, int n0KKyn ) throws Exception {
        
        TFnMacKey fnMacKey = new TFnMacKey();
        TFnMac fnMac, fnMacUpd;
        Date dtLastDealTime;
        
        fnMacKey.setOrgCd( MsgBrokerConst.NICE_CODE );
        fnMacKey.setBranchCd( MsgBrokerConst.NICE_BRCH_CD );
        fnMacKey.setMacNo( parsed.getString("mac_no") );
        
        try {
            if( n0KKyn == 0 ) {
                fnMac = fnMacMap.selectByJoin1( fnMacKey );
            }
            else {
                fnMac = fnMacMap.selectByJoin2( fnMacKey );
            }
            if( fnMac == null ) {
                fnMac = new TFnMac();
                fnMac.setSvcRelayYn("");
                fnMac.setInMacAmt( new Long(0) );
                fnMac.setInMacAmtCw14(  new Long(0) );
                fnMac.setInMacAmtCw54(  new Long(0) );
                fnMac.setInMacAmtCw15(  new Long(0) );
            }
        }
        catch ( Exception e ) {
            logger.info("[updateFNMacProc] SVC_RELAY_YN Select Error.. {}", e.getMessage() );
            throw e;
        }
        if( fnMac.getSvcRelayYn().equals("1") ) {
            logger.info("SVC_RELAY_YN value [{}]", fnMac.getSvcRelayYn() );
            return;
        }
        
        dtLastDealTime = MsgBrokerLib.toDate( parsed.getString("deal_date") 
                       + " " + parsed.getString("deal_time"), "yyyyMMdd HHmmss" );
        if( (parsed.getString("refuse_cd").equals("306") && parsed.getString("deal_status").equals("3"))
        ||  (parsed.getString("refuse_cd").equals("401") && parsed.getString("deal_status").equals("3"))
        ||  (!parsed.getString("refuse_cd").equals("306") && !parsed.getString("refuse_cd").equals("401")
          && !parsed.getString("deal_status").equals("3") ) ) {
            fnMacUpd = new TFnMac();
            fnMacUpd.setOrgCd( fnMac.getOrgCd() );
            fnMacUpd.setBranchCd( fnMac.getBranchCd() );
            fnMacUpd.setMacNo( fnMac.getMacNo() );
            fnMacUpd.setAtmDealNo( parsed.getString("atm_deal_no") );
            fnMacUpd.setLastDealTime( fnMac.getLastDealTime() );
            if( fnMac.getLastDealTime().compareTo(dtLastDealTime) < 0 )
                fnMacUpd.setLastDealTime( dtLastDealTime );
            fnMacUpd.setUpdateUid( "TRANmng");
            fnMacUpd.setUpdateDate( dSysDate );
            try {
                fnMacMap.updateByPrimaryKeySelective( fnMacUpd );
            }
            catch ( Exception e ) {
                logger.info( "[T_FN_MAC] Last Time Update Error[{}].. {}",
                        fnMacUpd.getLastDealTime(), e.getMessage() );
                throw e;
            }
            sendNICERepairMsg( fnMacUpd.getBranchCd(), fnMacUpd.getMacNo(), "4", null );
            sendNICERepairMsg( fnMacUpd.getBranchCd(), fnMacUpd.getMacNo(), "7", null );
            sendNICERepairMsg( fnMacUpd.getBranchCd(), fnMacUpd.getMacNo(), "8", null );
            
            byte[] byteAtmHwError = new byte[HW_MODULE_ERR.values().length * 4];
            /*
             *  카드판독기이상 (NI122), 명세표부이상(NI123) 만 복구
             */
            for( HW_MODULE_ERR enumAtmHwError: HW_MODULE_ERR.values()){
                if( enumAtmHwError.name().equals("HW_CARD_READ")
                ||  enumAtmHwError.name().equals("HW_SPECS") )
                    System.arraycopy( new byte[]{'0','\0','\0','\0'}, 0, byteAtmHwError, enumAtmHwError.ordinal() * 4, 4 );
                else
                    System.arraycopy( new byte[]{'9','\0','\0','\0'}, 0, byteAtmHwError, enumAtmHwError.ordinal() * 4, 4 );
            }
            sendNICERepairMsg( fnMacUpd.getBranchCd(), fnMacUpd.getMacNo(), "", byteAtmHwError );
        }
        
        /*
         * 잔액조회도 실거래에 포함되도록 수정 2008.07.02
         */
        if( parsed.getString("deal_type").equals("31000") ){
            /*
             * 잔액조회
             */
            fnMacUpd = new TFnMac();
            fnMacUpd.setOrgCd( fnMac.getOrgCd() );
            fnMacUpd.setBranchCd( fnMac.getBranchCd() );
            fnMacUpd.setMacNo( fnMac.getMacNo() );
            fnMacUpd.setAtmDealNo( parsed.getString("atm_deal_no") );
            fnMacUpd.setLastDealTime( fnMac.getLastDealTime() );
            if( fnMac.getLastDealTime().compareTo(dtLastDealTime) < 0 )
                fnMacUpd.setLastDealTime( dtLastDealTime );
            fnMacUpd.setUpdateUid( "TRANmng");
            fnMacUpd.setUpdateDate( dSysDate );
            try {
                fnMacMap.updateByPrimaryKeySelective( fnMacUpd );
            }
            catch ( Exception e ) {
                logger.info( "[T_FN_MAC] Last Time Update Error[{}].. {}",
                        fnMacUpd.getLastDealTime(), e.getMessage() );
                throw e;
            }
            return;
        }
        
        /*
         * 2009.10.29 T_FN_MAC의 IN_MAC_AMT 에는 기타권종 (1000,5000)은 반영하지 않도록 한다.
         */
        long lInMacAmt = parsed.getLong("deal_amt");
        lInMacAmt = lInMacAmt - ( parsed.getLong("cash_cnt_1000") * 1000 ) - ( parsed.getLong("cash_cnt_5000") * 5000 ); 
                
        fnMacUpd = new TFnMac();
        fnMacUpd.setInMacAmt( fnMac.getInMacAmt() );
        fnMacUpd.setInMacAmtCw14( fnMac.getInMacAmtCw14() );
        fnMacUpd.setInMacAmtCw54( fnMac.getInMacAmtCw54() );
        fnMacUpd.setInMacAmtCw15( fnMac.getInMacAmtCw15() );
        if( upInFlag == MsgBrokerConst.TRAN_INSERT_STATE
        &&  !parsed.getString("deal_status").equals("0") ) {
            logger.info( "UpdateFNMacProc : 취소,미완료,거절 Data가 처음으로 들어옴" );
            return;
        }
        else if( upInFlag == MsgBrokerConst.TRAN_UPDATE_STATE
                &&  !prevDealStatus.equals("0") ) {
            logger.info( "UpdateFNMacProc : [{}] => [{}]", prevDealStatus, parsed.getString("deal_status") );
            return;
        }
        /*
         * 입금일 경우 정상 데이터가 들어 왔다 이후에 거절 되는 경우가 있으므로 이경우는 입금을 차감 한다.
         *             2013.08.21 by B.H.J
         */
        else if ( upInFlag == MsgBrokerConst.TRAN_UPDATE_STATE 
              && prevDealStatus.equals("0")
              &&  !parsed.getString("deal_status").equals("0")
              &&  parsed.getString("deal_type").equals("1") ) {
            lInMacAmt *= -1;
            fnMacUpd.setInMacAmt( fnMacUpd.getInMacAmt() + lInMacAmt );
            fnMacUpd.setInMacAmtCw14( fnMacUpd.getInMacAmtCw14() - (parsed.getLong("cash_cnt_10000") * 10000) );
            fnMacUpd.setInMacAmtCw54( fnMacUpd.getInMacAmtCw54() - (parsed.getLong("cash_cnt_50000") * 50000) );
            fnMacUpd.setInMacAmtCw15( fnMacUpd.getInMacAmtCw15() - (parsed.getLong("cash_cnt_100000") * 100000) );
        }
        else {
            /*
             * 2005.12.08 자금팀 요청, 현재고를 지급일때 빼주고 입금일때 더해준다   
             */
            if( parsed.getString("deal_type").equals("0") && parsed.getString("deal_status").equals("0") ) {
                lInMacAmt *= -1;
                fnMacUpd.setInMacAmt( fnMacUpd.getInMacAmt() + lInMacAmt );
                fnMacUpd.setInMacAmtCw14( fnMacUpd.getInMacAmtCw14() - (parsed.getLong("cash_cnt_10000") * 10000) );
                fnMacUpd.setInMacAmtCw54( fnMacUpd.getInMacAmtCw54() - (parsed.getLong("cash_cnt_50000") * 50000) );
                fnMacUpd.setInMacAmtCw15( fnMacUpd.getInMacAmtCw15() - (parsed.getLong("cash_cnt_100000") * 100000) );
            }
            else if( parsed.getString("deal_type").equals("0") && parsed.getString("deal_status").equals("0") ) {
                /*
                 * 2010.01.29 hREFLUX_5_ALLOW_YN 항목이 0(오만원권 환류 허용안함 ) 일경우 현재고에
                 * 5만원권 입금 거래 내역은 반영하지 않도록 한다.
                 * ==> 모든기기가 5만원 환류 가능기기로 바뀌었으므로 필요 없음 20110719 금대리 확인
                 */
                fnMacUpd.setInMacAmt( fnMacUpd.getInMacAmt() + lInMacAmt );
                fnMacUpd.setInMacAmtCw14( fnMacUpd.getInMacAmtCw14() + (parsed.getLong("cash_cnt_10000") * 10000) );
                fnMacUpd.setInMacAmtCw54( fnMacUpd.getInMacAmtCw54() + (parsed.getLong("cash_cnt_50000") * 50000) );
                fnMacUpd.setInMacAmtCw15( fnMacUpd.getInMacAmtCw15() + (parsed.getLong("cash_cnt_100000") * 100000) );
            }
        }
        fnMacUpd.setOrgCd( fnMac.getOrgCd() );
        fnMacUpd.setBranchCd( fnMac.getBranchCd() );
        fnMacUpd.setMacNo( fnMac.getMacNo() );
        fnMacUpd.setAtmDealNo( parsed.getString("atm_deal_no") );
        fnMacUpd.setLastDealTime( dtLastDealTime );
        fnMacUpd.setUpdateUid( "TRANmng");
        fnMacUpd.setUpdateDate( dSysDate );
        if( fnMac.getFirstDealDate() == null || fnMac.getFirstDealDate().length() == 0)
            fnMacUpd.setFirstDealDate( parsed.getString("deal_date") );
        if( fnMac.getFirstAtmDealNo() == null || fnMac.getFirstAtmDealNo().length() == 0)
            fnMacUpd.setFirstAtmDealNo( parsed.getString("deal_date") );
        if( n0KKyn != 0 ) {
            fnMacUpd.setOrgCd( "0KK" );
        }
        
        try {
            fnMacMap.updateByPrimaryKeySelective( fnMacUpd );
        }
        catch ( Exception e ) {
            logger.info( "[T_FN_MAC] Update Error.. {}", e.getCause().getMessage() );
             throw e;
        }
        
        /*
         * 잔액 변화가 있을경우
         * 무거래(NI904), 카드판독기 이상(NI122), 명세표부 이상(NI123),
         * 무거래(특별)NI907, 무거래(일반)NI908 - 복구
         * NI9**는 user_made_err 에 설정 이외는 H/W 장애
         */
        
        /*
         * 잔액변화 뿐아니라 불능사유가 예금잔액부족이나 비밀번호 오류
         * 그이외의 정상 건에 발생시 복구하도록 수정 20090205 by BHJ
         */
        
        /*
         * 출금이 있어 잔액이 줄어들면 잔액조회(NI905) 장애를 복구 처리 
         * 지폐방출기장애(NI121) 는 복구 오류로 인해 제외 2008.07.04
         * 지폐방출후 잔액조회(NI906), 픽에러(EMPTY)(NI911) 복구추가 20090205
         * 픽에러(NI902) 와 지폐방출기장애(NI121)일 경우 취소가 반복될 경우
         * 발생시키는데 취소 거래는
         * 정상거래전문 수신 후 수분 후에 취소전문이 다시 올라옴에 따라
         * 실제 장애가 복구 처리될 수 있다 따라서 복구 시키지 않음 20090330   
         */
        if( lInMacAmt < 0 ) {
            sendNICERepairMsg( fnMacUpd.getBranchCd(), fnMacUpd.getMacNo(), "5", null );
            sendNICERepairMsg( fnMacUpd.getBranchCd(), fnMacUpd.getMacNo(), "6", null );
            sendNICERepairMsg( fnMacUpd.getBranchCd(), fnMacUpd.getMacNo(), "b", null );
        }
        
        /*
         * 입금일경우 뿐아니라 거래가 있다면 복구 T_FN_MAC의 잔액에서 복구 절차를 밟기 위해...
         * 트리거가 아니라 마감 했을경우 바로 복구 시킬수가 없다.
         * 20090701 고액권 적용후에도 현금부족 및 예보의 경우  총금액을 기준으로 가므로 수정필요 없음
         */
        if( fnMac.getShortCash() > 0 && fnMac.getShortCashNotice() > 0 ) {
            /*
             * 잔액이 현금부족 예보 기준금액 보다 많아 진다면
             * 현금부족예보(기준금액)(NI914) & 현금부족(기준금액)(NI912) 복구
             */
            logger.info("OLD_IN_MAC_AMT[{}], IN_MAC_AMT[{}], SHORT_CASH_NOTICE[{}], SHORT_CASH[{}]",
                    fnMac.getInMacAmt(), lInMacAmt, fnMac.getShortCashNotice(), fnMac.getShortCash() );
            if( fnMac.getInMacAmt() + lInMacAmt > fnMac.getShortCashNotice() ) {
                sendNICERepairMsg( fnMacUpd.getBranchCd(), fnMacUpd.getMacNo(), "c", null ); /*현금부족 (기준금액)     */
                sendNICERepairMsg( fnMacUpd.getBranchCd(), fnMacUpd.getMacNo(), "e", null ); /*현금부족 예보 (기준금액)*/
            }
            /*
             * 잔액이 현금부족 기준금액 보다 많아 진다면
             * 현금부족(기준금액)(NI912) 복구 
             */
            else if( fnMac.getInMacAmt() + lInMacAmt > fnMac.getShortCash() ) {
                sendNICERepairMsg( fnMacUpd.getBranchCd(), fnMacUpd.getMacNo(), "c", null ); /*현금부족 (기준금액)     */
            }
        }
    }
        
    
    private void sendNICERepairMsg( String branchCd, String macNo, String userMadeErr, byte[] errState ) {
        
        /*
         * 거래 금액의 변화가 있을 경우 나이스 발생 장애 복구 시킴
         * 트리거에서의 부하로 인해 장애 큐로 넘겨 처리 하도록 수정
         * 20090123 by B.H.J
         */
        
        try {
            MsgParser msgPsr = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path") 
                             + MsgBrokerConst.NS_Q_NAME.replace(".", "" ) + ".json");
            ByteBuffer msg = ByteBuffer.allocateDirect( msgPsr.getSchemaLength() );
            msgPsr.newMessage( msg );
            msg.position(0);
            msgPsr.setString( "CM.org_cd", MsgBrokerConst.NICE_CODE )
                  .setString( "CM.ret_cd_src", "S" )
                  .setString( "CM.msg_id", "TRANRPR" )
                  .setInt   ( "CM.body_len", msgPsr.getMessageLength() - MsgBrokerConst.HEADER_LEN )
                  .setString( "CM.trans_date", sSysDate )
                  .setString( "CM.trans_time", sSysTime )
                  .setString( "CM.format_type", MsgBrokerConst.NS_CODE )
                  .setString( "CM.msg_type", MsgBrokerConst.NS_REQ )
                  .setString( "CM.work_type", MsgBrokerConst.NS_ERR_STATE )
                  .setString( "network_info", MsgBrokerConst.NICE_USER_ERR_REPAIR )
                  .setString( "create_date", sSysDate )
                  .setString( "create_time", sSysTime )
                  .setString( "brch_cd", branchCd )
                  .setString( "mac_no", macNo )
                  .setString( "user_made_err", userMadeErr );
            
            if( errState != null )
                msgPsr.setBytes( "atm_hw_error", errState );
            
            MsgBrokerProducer prd = MsgBrokerProducer.producers.get( MsgBrokerConst.NS_Q_NAME );
            BytesMessage nsData = prd.getBytesMessage();

            byte[] read = new byte[msg.limit()];
            msg.position(0);
            msg.get(read);
            logger.info("NS State Data = {}", new String(read) );
            nsData.writeBytes(read);
            prd.produce( nsData );

        }
        catch ( Exception e ) {
            logger.debug("[sendNICERepairMsg] Error raised..{}", e.getMessage() );
        }
    }
}
