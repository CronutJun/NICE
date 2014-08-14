package com.nicetcm.nibsplus.broker.msg.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmCommonMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmCommon;
import com.nicetcm.nibsplus.broker.msg.model.TCmCommonSpec;

/**
 *
 * 경비해제수신
 * <pre>
 * MngEM_SaveUnLockTime( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01000161")
public class In01000161Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCmCommonMapper tCmCommonMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        /* 도착통보 기관전송에 대한 응답을 받은경우 아무 처리 하지 않고 응답없음으로 return */
        if(MsgBrokerConst.EM_ANS.equals(parsed.getString("CM.msg_type"))) {
            throw new MsgBrokerException(-99);
        }

        String horg_cq_code = MsgBrokerConst.CQ_CODE;
        String horg_cp_code = MsgBrokerConst.CP_CODE;

        TCmCommonSpec tCmCommonSpec = new TCmCommonSpec();
        tCmCommonSpec.createCriteria()
        .andLargeCdEqualTo("2300")
        .andCdNm4EqualTo(parsed.getString("CM.org_cd").equals(horg_cq_code) ? horg_cp_code : parsed.getString("CM.org_cd"));

        String errMsg = "";
        List<TCmCommon> tCmCommonList = null;

        try
        {
            tCmCommonList = tCmCommonMapper.selectBySpec(tCmCommonSpec);
        } catch (Exception e)
        {
            errMsg = e.getMessage();
        }

        if(errMsg.equals("") == false || tCmCommonList == null || tCmCommonList.size() == 0) {
            logger.info(String.format(">>> [SaveUnLockTime] (T_CM_COMMON) 경비사 코드[%s] 검색실패 [%.200s]", parsed.getString("CM.org_cd"), errMsg));
            throw new Exception(String.format(">>> [SaveUnLockTime] (T_CM_COMMON) 경비사 코드[%s] 검색실패 [%.200s]", parsed.getString("CM.org_cd"), errMsg));
        }

        /*===> 대구은행 경비사 사이트 등록 완료시 삭제 필요 2013.11.02 */
        if(MsgBrokerConst.DGB_CODE.equals(parsed.getString("CM.org_cd")) && parsed.getString("brch_cd").length() == 3) {
            String szJijumCd = null;
            String szSiteCd = null;

            szJijumCd = "0" + parsed.getString("brch_cd");
            try
            {
                szSiteCd = comPack.fcGetMapSiteCd("0", "031", parsed.getString("brch_cd"), parsed.getString("site_cd"));


            } catch (Exception e)
            {
                logger.info( ">>> [MngEM_SaveUnLockTime] 코너정보 mapping실패 [{}]", e.getMessage());
            }

            if(szSiteCd.equals("")) {
                logger.info( ">>> [MngEM_SaveUnLockTime] mapping 테이블 데이터 없음 grd.jijum_cd[{}], grd.site_cd[{}]", parsed.getString("brch_cd"), parsed.getString("site_cd"));
            } else {
                logger.info( ">>> [MngEM_SaveUnLockTime] 기초정보 mapping jijum_cd[{}->{}], site_cd[{}->{}]", parsed.getString("brch_cd"), szJijumCd, parsed.getString("site_cd"), szSiteCd );
            }
        }

        /*대구은행 경비사 사이트 등록 완료시 삭제 필요 2013.11.02 <===*/

        //TODO
        //모듈생성후 코딩하기
        //TCtSiteLockHistory TCtSiteLockHistory = new TCtSiteLockHistory();

        /* 현장  부가업무 연동              */
        /* 세트 해제일 경우, 아래 루트를 수행한다.  */
        if(parsed.getString("inout_type").startsWith("2")) {
            /* 해당테이블의 대상 Data를 검색하여 Update 치고 push 테이블에도 insert 처리 */

            //TODO
            //모듈생성후 코딩하기
            //TCmCheckMaster TCmCheckMaster = new TCmCheckMaster();



        }
















    }//end method
}