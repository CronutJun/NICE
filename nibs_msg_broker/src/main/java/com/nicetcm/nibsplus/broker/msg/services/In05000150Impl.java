package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * KIOSK 장애 상태 ( 홈플러스 )
 *
 * <pre>
 * MngES_SaveKioskState
 * </pre>
 *
 *           2014. 10. 08    K.D.J.
 */


import java.util.List;
import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nicetcm.nibsplus.broker.msg.MsgBrokerLib.lpad;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtUnfinishMapper;

import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCall;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorNoti;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorRcpt;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxn;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.TCtUnfinish;
import com.nicetcm.nibsplus.broker.msg.model.ErrorState;

@Service("in05000150")
public class In05000150Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In05000150Impl.class);

    @Autowired private StoredProcMapper  splMap;
    @Autowired private TCtUnfinishMapper unfMap;

    private class BillState {
        /*
         * 발행사
         */
        private String ticketCd;
        /*
         * 권종구분코드
         */
        private String billClassifyCd;
        /*
         * '0':good, '1':NeerEnd, '2':Empty, '9':사용안함
         */
        private String state;

        public String getTicketCd() {
            return ticketCd;
        }

        public void setTicketCd(String ticketCd) {
            this.ticketCd = ticketCd;
        }

        public String getBillClassifyCd() {
            return billClassifyCd;
        }

        public void setBillClassifyCd(String billClassifyCd) {
            this.billClassifyCd = billClassifyCd;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TMacInfo macInfo = new TMacInfo();
        TCtErrorBasic errBasic = new TCtErrorBasic();
        TCtErrorRcpt errRcpt = new TCtErrorRcpt();
        TCtErrorNoti errNoti = new TCtErrorNoti();
        TCtErrorCall errCall = new TCtErrorCall();
        TCtErrorTxn  errTxn  = new TCtErrorTxn();

        ArrayList<String> errList = new ArrayList<String>();
        ArrayList<BillState> billStates = new ArrayList<BillState>();

        String kioskHWType[] = { "3", "8", "5", "4", "2" };


        macInfo.setOrgCd( parsed.getString("CM.org_cd") );
        macInfo.setBranchCd( parsed.getString("brch_cd") );
        macInfo.setMacNo( parsed.getString("mac_no") );
        /**
         * 지점코드, 기번 길이 검증
         */
        try { comPack.checkBranchMacLength( macInfo ); } catch( Exception e ) {}

        try {
            /**
             * 기기정보 취득
             */
            comPack.getMacInfo( macInfo );
        }
        catch (Exception e) {
            logger.debug("GetMacInfo Error");
            logger.warn( "[01003100] 기기정보 검색 실패 기관[{}] 지점[{}] 기번[{}]",
                    macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo() );
            /**
             * SMS 전송
             */
            if( parsed.getString("CM.org_cd").equals(MsgBrokerConst.SHATMS_CODE) )
                splMap.SendSMSMacInfo( macInfo );
            throw e;
        }

        logger.warn("기관[{}] 지점[{}] 기번[{}] 기기명[{}] 부서[{}] 사무소[{}] 지소[{}]",
                macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo(), macInfo.getMacNm(),
                macInfo.getDeptCd(),macInfo.getOfficeCd(), macInfo.getTeamCd());

        /**
         *  macInfo의 값을 errMng로 일괄 복사
         */
        BeanUtils.copyProperties( errBasic, macInfo );

        errBasic.setCreateDate( parsed.getString("create_date") );
        errBasic.setCreateTime( parsed.getString("create_time") );
        errBasic.setTransDate ( errBasic.getCreateTime() );

        /*
         * 업무구분 '11'-장애관리전문
         */
        errBasic.setFormatType( "11" );

        /*
         *  개국전문은 개국테이블에
         *  개국전문 수신시 미개국 장애 복구처리
         */
        if( parsed.getString("open_yn").equals("1") )  {
            comPack.insertUpdateMacOpen( safeData, macInfo, errBasic ); /* 개시관리에 insert */
        }

        List<TCtUnfinish> unfs = null;
        try {
            unfs = unfMap.selectByCond8( errBasic );
        }
        catch( Exception e ) {
            logger.warn( ">>>Cursor Open Error [%.200s]\n", e.getLocalizedMessage() );
            throw e;
        }
        for( TCtUnfinish unf: unfs ) {
            errList.add( unf.getErrorCd() );
        }

        errBasic.setErrorCd( "SVR000" );

        /*
         *  슈퍼바이져 모드 일경우 처리
         */
        if( parsed.getString("mode_type").equals("1") ) {
            /*
             *  계원모드 발생 처리
             */
            insertKioskErrBasic( safeData, errList, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );
        }
        else {
            /*
             *  슈퍼바이져 모드 아닐경우 계원모드 복구 처리
             */
            updateKioskErrBasic( safeData, errList, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );

            /********************
             * 상태 처리
             */
            int boxInfoSize = parsed.getField(parsed.getCurrentThrData().msgDatMap, "BI").iteration;
            for( int i = 0; i < boxInfoSize; i++ ) {
                if( !parsed.getString(String.format("BI[%d].state", i)).equals("9") ) {
                    for( BillState bs: billStates ) {
                        /*
                         * 다수의 카세트에 같은종류의 현금 혹은 상품권을 넣을 수 있으므로
                         * 동일 권종일 경우 카세트 하나라도 정상 이라면 정상 처리를 하기 위함.
                         */
                        if( bs.getTicketCd().equals(parsed.getString(String.format("BI[%d].ticket_cd", i)))
                        &&  bs.getBillClassifyCd().equals(parsed.getString(String.format("BI[%d].bill_class_cd", i))) ) {
                            if( parsed.getString(String.format("BI[%d].state", i)).equals("0")
                            ||  (bs.getState().equals("2") && parsed.getString(String.format("BI[%d].state", i)).equals("1")) ) {
                                bs.setState(parsed.getString(String.format("BI[%d].state", i)));
                            }
                        }
                    }

                    BillState billState = new BillState();
                    billState.setTicketCd      ( parsed.getString(String.format("BI[%d].ticket_cd", i)) );
                    billState.setBillClassifyCd( parsed.getString(String.format("BI[%d].bill_class_cd", i)) );
                    billState.setState         ( parsed.getString(String.format("BI[%d].state", i)) );

                    billStates.add( billState );
                }
            }
            /*
             *  장애 코드 규칙
             *
             *  첫번째 자리
             *   - S :부족 과같은 상태 장애
             *   - H : H/W 장애
             *  두번째 자리 이후
             *   - 상태장애 일 경우 : C(현금)/G(상품권)/J(명세표) 1자리 + 상태 1자리(1-예보, 2-장애) + 권종코드 2자리
             *   - HW 장애 일경우 : HW장애코드 5자리 (장애코드 5자리중 첫번째 자리는 각 HW 부위를 나타냄 - 전문사양서 참고
             */
            for( BillState bs: billStates ) {
                /*
                 * 정상(0) => 장애(2)와 예보(1) 모두 복구 처리
                 */
                if( bs.getState().equals("0") ) {
                    /*
                     *  현금 장애
                     */
                    if( bs.getTicketCd().equals("CAS") || bs.getTicketCd().equals("CON") ) {
                        errBasic.setErrorCd( String.format("SC1%s", bs.getBillClassifyCd()) );
                        updateKioskErrBasic( safeData, errList, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );

                        errBasic.setErrorCd( String.format("SC2%s", bs.getBillClassifyCd()) );
                        updateKioskErrBasic( safeData, errList, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );
                    }
                    /*
                     *  상품권 장애
                     */
                    else {
                        errBasic.setErrorCd( String.format("SG1%s", bs.getBillClassifyCd()) );
                        updateKioskErrBasic( safeData, errList, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );

                        errBasic.setErrorCd( String.format("SG2%s", bs.getBillClassifyCd()) );
                        updateKioskErrBasic( safeData, errList, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );
                    }
                }
                /*
                 *  예보(1) => 장애(2) 복구, 예보(1) 발생 처리
                 */
                else if( bs.getState().equals("1") ) {
                    /*
                     *  현금 장애
                     */
                    if( bs.getTicketCd().equals("CAS") || bs.getTicketCd().equals("CON") ) {
                        errBasic.setErrorCd( String.format("SC1%s", bs.getBillClassifyCd()) );
                        insertKioskErrBasic( safeData, errList, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );

                        errBasic.setErrorCd( String.format("SC2%s", bs.getBillClassifyCd()) );
                        updateKioskErrBasic( safeData, errList, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );
                    }
                    /*
                     *  상품권 장애
                     */
                    else {
                        errBasic.setErrorCd( String.format("SG1%s", bs.getBillClassifyCd()) );
                        insertKioskErrBasic( safeData, errList, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );

                        errBasic.setErrorCd( String.format("SG2%s", bs.getBillClassifyCd()) );
                        updateKioskErrBasic( safeData, errList, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );
                    }
                }
                /*
                 * 장애(2) => 장애(2) 발생 처리
                 */
                else  {
                    /*
                     *  현금 장애
                     */
                    if( bs.getTicketCd().equals("CAS") || bs.getTicketCd().equals("CON") ) {
                        errBasic.setErrorCd( String.format("SC2%s", bs.getBillClassifyCd()) );
                        insertKioskErrBasic( safeData, errList, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );
                    }
                    /*
                     *  상품권 장애
                     */
                    else {
                        errBasic.setErrorCd( String.format("SG2%s", bs.getBillClassifyCd()) );
                        insertKioskErrBasic( safeData, errList, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );
                    }
                }
            }
            /*
             *  명세표 상태
             */
            if( parsed.getString("deal_list_state").equals("0") ) {
                errBasic.setErrorCd( "SJ100" );
                updateKioskErrBasic( safeData, errList, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );

                errBasic.setErrorCd( "SJ200" );
                updateKioskErrBasic( safeData, errList, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );
            }
            else if( parsed.getString("deal_list_state").equals("1") ) {
                errBasic.setErrorCd( "SJ100" );
                insertKioskErrBasic( safeData, errList, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );

                errBasic.setErrorCd( "SJ200" );
                updateKioskErrBasic( safeData, errList, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );
            }
            else if( parsed.getString("deal_list_state").equals("2") ) {
                errBasic.setErrorCd( "SJ200" );
                insertKioskErrBasic( safeData, errList, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );
            }

            /**
             * H/W 장애 처리
             */
            int hwInfoSize = parsed.getField(parsed.getCurrentThrData().msgDatMap, "KH").iteration;
            for( int i = 0; i < hwInfoSize; i ++ ) {
                /*
                 * 정상
                 */
                if( parsed.getString(String.format("KH[%d].state", i)).equals("0") ) {
                    errBasic.setErrorCd( String.format("H%s", kioskHWType[i]) );
                    updateKioskErrBasic( safeData, errList, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_HW_ALL_CLEAR, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );
                }
                /*
                 * 장애
                 */
                else if( parsed.getString(String.format("KH[%d].state", i)).equals("1") ) {
                    errBasic.setErrorCd( String.format("H%s%s", parsed.getString(String.format("KH[%d].hw_type", i)), parsed.getString(String.format("KH[%d].hw_err_cd", i))) );
                    insertKioskErrBasic( safeData, errList, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo );
                }
            }
        }

    }

    private void insertKioskErrBasic( MsgBrokerData safeData, ArrayList<String> errList,  TCtErrorBasic errBasic, TCtErrorRcpt errRcpt, TCtErrorNoti errNoti, TCtErrorCall errCall,
            TCtErrorTxn errTxn, TMacInfo macInfo ) throws Exception {
        boolean isExists = false;
        for( String err: errList ) {
            if( errBasic.getErrorCd().equals(err) ) {
                isExists = true;
                break;
            }
        }
        if( !isExists ) {
            comPack.insertErrBasic( safeData, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, "" );
        }
    }

    private void updateKioskErrBasic( MsgBrokerData safeData, ArrayList<String> errList, int iWorkType, String dbMode, TCtErrorBasic errBasic, TCtErrorRcpt errRcpt, TCtErrorNoti errNoti, TCtErrorCall errCall,
            TCtErrorTxn errTxn, TMacInfo macInfo ) throws Exception {
        boolean isExists = false;
        for( String err: errList ) {
            if( errBasic.getErrorCd().equals(err) ) {
                isExists = true;
                break;
            }
        }
        if( isExists ) {
            comPack.updateErrBasic( safeData, iWorkType, dbMode, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, null );
        }
    }
}
