package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * 경비사 현장직원 콜 수신확인
 *
 * <pre>
 * MngEM_SaveCallRecvConf
 * </pre>
 *
 *           2014. 10. 08    K.D.J.
 */

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nicetcm.nibsplus.broker.msg.MsgBrokerLib.nstr;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;

import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorBasicMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorCallMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorNotiMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorTxnMapper;

import com.nicetcm.nibsplus.broker.msg.model.ErrorState;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasicSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCall;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCallSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorNoti;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorNotiSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxn;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxnSpec;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

@Service("in01000151")
public class In01000151Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private StoredProcMapper splMap;

    @Autowired private TCtErrorBasicMapper errBasicMap;
    @Autowired private TCtErrorCallMapper  errCallMap;
    @Autowired private TCtErrorNotiMapper  errNotiMap;
    @Autowired private TCtErrorTxnMapper   errTxnMap;


    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TCtErrorBasicSpec errBasicSpec = new TCtErrorBasicSpec();
        TCtErrorCallSpec  errCallSpec = new TCtErrorCallSpec();
        TCtErrorNotiSpec  errNotiSpec = new TCtErrorNotiSpec();
        TCtErrorTxnSpec   errTxnSpec = new TCtErrorTxnSpec();

        TCtErrorBasic     errBasic = new TCtErrorBasic();
        TCtErrorNoti      errNoti  = new TCtErrorNoti();
        TCtErrorTxn       errTxn   = new TCtErrorTxn();


        errBasicSpec.createCriteria().andCreateDateEqualTo( parsed.getString("trans1_date") )
                                     .andErrorNoEqualTo   ( parsed.getString("trans1_seq") );
        List<TCtErrorBasic> errBasics = null;
        try {
            errBasics = errBasicMap.selectBySpec( errBasicSpec );
            if( errBasics.size() == 0 ) {
                throw new Exception( String.format("ErrBasic - 해당데이터가 없습니다. create_date[%s], error_no[%s]",
                        parsed.getInt("trans1_date"), parsed.getString("trans1_seq")) );
            }
        }
        catch( Exception e ) {
            logger.warn(">>> ErrorBasic 장애건검색실패 create_date[{}], error_no[{}] {}]",
                    parsed.getInt("trans1_date"), parsed.getString("trans1_seq"), e.getLocalizedMessage() );
            throw e;
        }

        errCallSpec.createCriteria().andCreateDateEqualTo( parsed.getString("trans1_date") )
                                    .andErrorNoEqualTo   ( parsed.getString("trans1_seq") );
        List<TCtErrorCall> errCalls = null;
        try {
            errCalls = errCallMap.selectBySpec( errCallSpec );
            if( errCalls.size() == 0 ) {
                throw new Exception( String.format("ErrCall - 해당데이터가 없습니다. create_date[%s], error_no[%s]",
                        parsed.getInt("trans1_date"), parsed.getString("trans1_seq")) );
            }
        }
        catch( Exception e ) {
            logger.warn(">>> ErrorCall 장애건검색실패 create_date[{}], error_no[{}] {}]",
                    parsed.getInt("trans1_date"), parsed.getString("trans1_seq"), e.getLocalizedMessage() );
            throw e;
        }

        errNotiSpec.createCriteria().andCreateDateEqualTo( parsed.getString("trans1_date") )
                                    .andErrorNoEqualTo   ( parsed.getString("trans1_seq") );
        List<TCtErrorNoti> errNotis = null;
        try {
            errNotis = errNotiMap.selectBySpec( errNotiSpec );
            if( errNotis.size() == 0 ) {
                throw new Exception( String.format("ErrNoti - 해당데이터가 없습니다. create_date[%s], error_no[%s]",
                        parsed.getInt("trans1_date"), parsed.getString("trans1_seq")) );
            }
        }
        catch( Exception e ) {
            logger.warn(">>> ErrorNoti 장애건검색실패 create_date[{}], error_no[{}] {}]",
                    parsed.getInt("trans1_date"), parsed.getString("trans1_seq"), e.getLocalizedMessage() );
            throw e;
        }

        errTxnSpec.createCriteria().andCreateDateEqualTo( parsed.getString("trans1_date") )
                                   .andErrorNoEqualTo   ( parsed.getString("trans1_seq") );
        List<TCtErrorTxn> errTxns = null;
        try {
            errTxns = errTxnMap.selectBySpec( errTxnSpec );
            if( errTxns.size() == 0 ) {
                throw new Exception( String.format("ErrTxn - 해당데이터가 없습니다. create_date[%s], error_no[%s]",
                        parsed.getString("trans1_date"), parsed.getString("trans1_seq")) );
            }
        }
        catch( Exception e ) {
            logger.warn(">>> ErrorTxn 장애건검색실패 create_date[{}], error_no[{}] {}]",
                    parsed.getString("trans1_date"), parsed.getString("trans1_seq"), e.getLocalizedMessage() );
            throw e;
        }

        /*
         *  이미 완료된 건은 update 하지 않도록 한다.
         */
        if( nstr(errBasics.get(0).getErrorStatus()).equals("7000") )  {
            logger.warn(">>> 완료장애에 대한 콜수신확인 수신 create_date[{}], error_no[{}]",
                    parsed.getInt("trans1_date"), parsed.getString("trans1_seq") );
            throw new MsgBrokerException(String.format(">>> 완료장애에 대한 콜수신확인 수신 create_date[%s], error_no[%s]",
                    parsed.getString("trans1_date"), parsed.getString("trans1_seq")), -2);
        }

        parsed.setString("recv_tele_no", parsed.getString("recv_tele_no").replaceAll("-", ""));

        /*
         *  경비사 전문에서 관리지사명 필드에 출동자 사번을 설정하도록 함
         *  출동자명이 없을 경우에는 경비사 default값 9999999을 넣도록 수정 20090302
         */
        errNoti.setRecvDate      ( parsed.getString("confirm_date") );
        errNoti.setRecvTime      ( parsed.getString("confirm_time").substring(0, 4) );
        errNoti.setRecvUserUid   ( parsed.getString("recv_user_uid").length() == 0 ? "9999999" : parsed.getString("recv_user_uid") );
        errNoti.setRecvUserNm    ( parsed.getString("recv_user_nm") );
        errNoti.setRecvTeleNo    ( parsed.getString("recv_tele_no") );
        errNoti.setSendStatus    ( "2" );
        errNoti.setSendSmsStatus ( "6040" );
        errNoti.setUpdateDate    ( safeData.getDSysDate() );
        errNoti.setUpdateUid     ( "ERRmngR" );

        errBasic.setWorkStatus   ( "6050" );
        errBasic.setUpdateDate    ( safeData.getDSysDate() );
        errBasic.setUpdateUid     ( "ERRmngR" );

        errTxn.setUpdateDate    ( safeData.getDSysDate() );
        errTxn.setUpdateUid     ( "ERRmngR" );
        try {
            errBasicMap.updateBySpecSelective( errBasic, errBasicSpec );
        }
        catch( Exception e ) {
            logger.warn( "[T_CT_ERROR_BASIC] Update Err [{}]", e.getLocalizedMessage() );
            throw e;
        }

        try {
            errNotiMap.updateBySpecSelective( errNoti, errNotiSpec );
        }
        catch( Exception e ) {
            logger.warn( "[T_CT_ERROR_NOTI] Update Err [{}]", e.getLocalizedMessage() );
            throw e;
        }

        try {
            errTxnMap.updateBySpecSelective( errTxn, errTxnSpec );
        }
        catch( Exception e ) {
            logger.warn( "[T_CT_ERROR_TXN] Update Err [{}]", e.getLocalizedMessage() );
            throw e;
        }

    }//end inMsgBizProc
}
