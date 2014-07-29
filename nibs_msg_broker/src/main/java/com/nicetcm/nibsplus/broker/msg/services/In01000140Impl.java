package com.nicetcm.nibsplus.broker.msg.services;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorMngMadeComMapper;
import com.nicetcm.nibsplus.broker.msg.model.ErrorState;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCall;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngMadeCom;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngMadeComSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorNoti;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorRcpt;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxn;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

/**
 *
 * 장애출동취소
 * <pre>
 * MngEM_SaveErrCallCancel( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01000140")
public class In01000140Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private StoredProcMapper splMap;

    @Autowired private TCtErrorMngMadeComMapper tCtErrorMngMadeComMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        comPack.checkBranchMacLength( parsed );

        TMacInfo macInfo = new TMacInfo();
        macInfo.setOrgCd( parsed.getString("CM.org_cd") );
        macInfo.setBranchCd( parsed.getString("brch_cd") );
        macInfo.setMacNo( parsed.getString("mac_no") );
        macInfo.setOrgSiteCd( parsed.getString("org_site_cd") );

        if( macInfo.getMacNo().equals("0000")
        ||  macInfo.getMacNo().trim().length() == 0
        || ( macInfo.getOrgCd().equals(  MsgBrokerConst.KEB_CODE ) && macInfo.getMacNo().equals("999") )) {
            comPack.getMacNoIntoSite( macInfo );
            logger.debug("SiteCd = {}, MacNo = {}", macInfo.getSiteCd(), macInfo.getMacNo());
        }

        try {
            /**
             * 기기정보 취득
             */
            comPack.getMacInfo( macInfo );
        }
        catch (Exception e) {
            throw new MsgBrokerException( String.format("[01003100] 기기정보 검색 실패 기관[%s] 지점[%s] 기번[%s]",
                                            macInfo.getOrgCd(), macInfo.getMacNo()), -7 );
        }

        logger.info("기관[{}] 지점[{}] 기번[{}] 기기명[{}] 부서[{}] 사무소[{}] 지소[{}]",
                    macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo(),
                    macInfo.getMacNm(), macInfo.getDeptCd(), macInfo.getOfficeCd(), macInfo.getTeamCd() );


        if( macInfo.getOrgCd().equals(MsgBrokerConst.SHATMS_CODE)
        &&  parsed.getString("call_cnt_type").equals("2")
        &&  !parsed.getString("call_type").equals("3") ) {

            TCtErrorMngMadeCom tCtErrorMngMadeCom = new TCtErrorMngMadeCom();
            tCtErrorMngMadeCom.setCancelDate    (parsed.getString("cancel_date"));
            tCtErrorMngMadeCom.setCancelTime    (parsed.getString("cancel_time"));
            tCtErrorMngMadeCom.setCallCancelType(parsed.getString("cancel_type"));
            tCtErrorMngMadeCom.setOrgSendYn     ("D");
            tCtErrorMngMadeCom.setUpdateDate    (safeData.getDSysDate());
            tCtErrorMngMadeCom.setUpdateUid     ("ERRmng");

            TCtErrorMngMadeComSpec tCtErrorMngMadeComSpec = new TCtErrorMngMadeComSpec();
            tCtErrorMngMadeComSpec.createCriteria()
            .andTransDateEqualTo(parsed.getString("trans1_date"))
            .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"))
            .andOrgCallCntEqualTo(parsed.getShort("org_call_cnt"));

            try
            {
                tCtErrorMngMadeComMapper.updateBySpecSelective(tCtErrorMngMadeCom, tCtErrorMngMadeComSpec);
            } catch (Exception e)
            {
                logger.info(">>> [ErrCallCancel] (T_CT_ERROR_MNG_MADE_COM) UPDATE ERROR [{}]", e.getMessage());
                throw new MsgBrokerException(-1);
            }

            return;
        }//endif

        TCtErrorBasic errBasic = new TCtErrorBasic();
        /**
         *  macInfo의 값을 errMng로 일괄 복사
         */
        BeanUtils.copyProperties( errBasic, macInfo );

        errBasic.setCreateDate(parsed.getInt("cancel_date"));
        errBasic.setCreateTime(parsed.getString("cancel_time"));
        errBasic.setOrgMsgNo  (parsed.getString("trans1_seq"));
        errBasic.setTransDate (parsed.getString("trans1_date"));
        errBasic.setErrorCd   (parsed.getString("error_cd"));
        errBasic.setMadeErrCd (parsed.getString("error_mtc_cd"));
        errBasic.setOrgMsg    (parsed.getString("memo"));
        errBasic.setFormatType("9" + parsed.getString("cancel_type")); /*업무구분 '9?'-출동취소요청*/

        /*****************************************************************************************
         중복 장애 발생 여부 확인
         국민, 기업, 외환등  같이  기관에서 1차통지 전문 일련번호를 관리 할 경우 1차통지 전문 일련번호로 중복 체크
         그 이외에는 같은 기기의 장애 코드로 만 체크. 단, 신한은 발생 시간으로 일련번호를 대신하므로 추가
        *****************************************************************************************/

        if( macInfo.getOrgCd().equals(MsgBrokerConst.SHATMS_CODE)) {
            /************************************************************************************
             신한은행의 경우 전일 복구신호를 당일 출동요청건에 대해 보내 주기도 하므로 중복 체크 필요
            ************************************************************************************/
            if(comPack.getDupErrorMng(errBasic, 1) == false) {
                logger.info( String.format("전문번호 [%s-%s] 의 미완료 출동요청 장애 없음", errBasic.getTransDate(), errBasic.getOrgMsgNo() ));
                throw new MsgBrokerException(-1);
            }
        }

        /*****************************************************************************************
        ==========>
        출동취소에서는 쓰지 않으므로 는 임시로
        *****************************************************************************************/
        TCtErrorRcpt errRcpt = new TCtErrorRcpt();
        TCtErrorNoti errNoti = new TCtErrorNoti();
        TCtErrorCall errCall = new TCtErrorCall();
        TCtErrorTxn  errTxn  = new TCtErrorTxn();

        ErrorState errState = new ErrorState();
        errState.setMacType( MsgBrokerConst.CURERR_ATM );
        errState.setOrgCd( errBasic.getOrgCd() );
        errState.setMacNo( errBasic.getMacNo() );
        byte[] curErrList = comPack.getCurrentErrorState( errState );

        //DBUpdateErrMng( DB_UPDATE_CANCEL_MNG, &suDBErr, &suCurErrList );
        comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_CANCEL_MNG, MsgBrokerConst.MODE_UPDATE_HW_ONE_CLEAR, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, curErrList );
    }//end method
}
