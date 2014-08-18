package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorMngMadeComMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngMadeCom;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngSpec;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

/**
 *
 * 고객대기정보통보 - 기관으로부터 온 요청 처리
 * <pre>
 * MngEM_SaveWaitCustomer( char * pRecvData, int nRecvLen )
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01000180")
public class In01000180Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCtErrorMngMadeComMapper tCtErrorMngMadeComMapper;

    @Autowired private TMiscMapper tMiscMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        comPack.checkBranchMacLength(parsed);

        if(parsed.getString("mac_no").equals("0000") || parsed.getString("mac_no").equals("    ") || parsed.getString("mac_no").equals("")
           || (MsgBrokerConst.KEB_CODE.equals(parsed.getString("CM.org_cd")) && parsed.getString("mac_no").equals("999"))
        ) {

            TMacInfo macInfo = new TMacInfo();
            macInfo.setOrgCd( parsed.getString("CM.org_cd") );
            macInfo.setBranchCd( parsed.getString("brch_cd") );
            macInfo.setMacNo( parsed.getString("mac_no") );
            macInfo.setOrgSiteCd( parsed.getString("org_site_cd") );

            try
            {
                comPack.getMacNoIntoSite( macInfo );
                parsed.setString("mac_no", macInfo.getMacNo());
                parsed.setString("site_cd", macInfo.getSiteCd());

            } catch (Exception e)
            {
                logger.info(String.format("기번정보 없음(코너 대표기번 검색 실패),org_cd[%s],jijum_cd[%s], org_site_cd[%s]", parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("org_site_cd")) );
                throw new Exception(String.format("기번정보 없음(코너 대표기번 검색 실패),org_cd[%s],jijum_cd[%s], org_site_cd[%s]", parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("org_site_cd")));
            }

        }

        logger.info("...고객대기정보 발생껀...");

        /* 신한은행 2차출동의 경우 별도 테이블에 */
        if(MsgBrokerConst.SHATMS_CODE.equals(parsed.getString("CM.org_cd")) && parsed.getString("call_cnt_type").equals("2")) {
            TCtErrorMngMadeCom tCtErrorMngMadeCom = new TCtErrorMngMadeCom();
            tCtErrorMngMadeCom.setOrgMsg(parsed.getString("memo"));
            tCtErrorMngMadeCom.setTransDate(parsed.getString("trans1_date"));
            tCtErrorMngMadeCom.setOrgMsgNo(parsed.getString("trans1_seq"));
            tCtErrorMngMadeCom.setOrgCallCnt(parsed.getShort("call_cnt"));

            try
            {
                tMiscMapper.updateCtErrorMngMadeCom4(tCtErrorMngMadeCom);
            } catch (Exception e)
            {
                logger.info( ">>> [CallIVR_WaitCustomer] (T_CT_ERROR_MNG_MADE_COM) UPDATE ERROR [{}]", e.getMessage());
                throw e;
            }

            return;
        }

        TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
        TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();

        if(parsed.getString("type_cd").equals("1")) {
            /**********************************************************************************
             고객대기 건이라면 특이사항과 고객대기 여부 모두 수정
            ***********************************************************************************/

            updateTCtErrorMng.setOrgCustMsg(parsed.getString("memo"));
            updateTCtErrorMng.setOrgCustRecvYn("Y");
            updateTCtErrorMng.setUpdateDate(safeData.getDSysDate());
            updateTCtErrorMng.setUpdateUid("ERRmng");


            tCtErrorMngSpec.createCriteria()
            .andCreateDateGreaterThan(Integer.parseInt(parsed.getString("call_date").equals("") ? parsed.getString("trans1_date") : parsed.getString("call_date")))
            .andTransDateEqualTo(parsed.getString("trans1_date"))
            .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"));

        } else {
            /**********************************************************************************
             추가 특이사항('2') 건이라면 기관특이사항만 수정
            ***********************************************************************************/

            updateTCtErrorMng.setOrgMsg(parsed.getString("memo"));
            updateTCtErrorMng.setUpdateDate(safeData.getDSysDate());
            updateTCtErrorMng.setUpdateUid("ERRmng");

            tCtErrorMngSpec.createCriteria()
            .andCreateDateGreaterThan(Integer.parseInt(parsed.getString("call_date").equals("") ? parsed.getString("trans1_date") : parsed.getString("call_date")))
            .andTransDateEqualTo(parsed.getString("trans1_date"))
            .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"));

        }

        try
        {
            comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMngSpec);
        } catch (Exception e)
        {
            logger.info( ">>> [CallIVR_WaitCustomer] (T_CT_ERROR_MNG) UPDATE ERROR [{}]", e.getMessage());
            throw e;
        }

        logger.info("!!!고객대기정보 처리완료(T_CT_ERROR_MNG UPDATE)!!!");

    }//end method
}