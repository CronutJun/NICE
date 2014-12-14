package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtManyErrorMngMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtManyErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

/**
 *
 * 동시다발장애출동요청
 * <pre>
 * MngEM_SaveManyErrCall( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01001480")
public class In01001480Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TMiscMapper tMiscMapper;

    @Autowired private TCtManyErrorMngMapper tCtManyErrorMngMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TMisc tMisc = tMiscMapper.getMadeComCd(parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"));

        if(tMisc == null) {
            logger.warn( String.format("[MngEM_SaveManyErrCall] 기기제조사 정보  검색 실패 기관[%s] 지점[%s] 기번[%s]", parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no") ));
            throw new MsgBrokerException(-1);
        }

        TCtManyErrorMng tCtManyErrorMng = new TCtManyErrorMng();
        tCtManyErrorMng.setCallDate   (parsed.getString("call_date"));
        tCtManyErrorMng.setCallNo     (parsed.getString("call_no"));
        tCtManyErrorMng.setMadeComCd  (tMisc.getMadeComCd());
        tCtManyErrorMng.setCallTime   (parsed.getString("call_time"));
        tCtManyErrorMng.setBaseDayCnt (parsed.getShort ("base_day_cnt"));
        tCtManyErrorMng.setBaseLimit  (parsed.getShort ("base_limit"));
        tCtManyErrorMng.setErrCnt     (parsed.getShort ("err_cnt"));
        tCtManyErrorMng.setOrgCd      (parsed.getString("org_cd"));
        tCtManyErrorMng.setBranchCd   (parsed.getString("brch_cd"));
        tCtManyErrorMng.setMacNo      (parsed.getString("mac_no"));
        tCtManyErrorMng.setSiteCd     (parsed.getString("site_cd"));
        tCtManyErrorMng.setMacModel   (parsed.getString("mac_model"));
        tCtManyErrorMng.setSiteNm     (parsed.getString("site_nm"));
        tCtManyErrorMng.setMsg        (parsed.getString("msg"));
        tCtManyErrorMng.setAsMedia    (parsed.getString("as_media"));
        tCtManyErrorMng.setManyErrGb  (parsed.getString("many_err_gb"));
        tCtManyErrorMng.setErrorCd    (parsed.getString("error_cd"));
        tCtManyErrorMng.setLcCd       (parsed.getString("lc_cd"));
        tCtManyErrorMng.setMtcCd      (parsed.getString("mtc_cd"));
        tCtManyErrorMng.setOrgSendYn  ("0");
        tCtManyErrorMng.setUpdateDate (safeData.getDSysDate());
        tCtManyErrorMng.setUpdateUid  ("online");

        try
        {
            tCtManyErrorMngMapper.insertSelective(tCtManyErrorMng);
        } catch( org.springframework.dao.DataIntegrityViolationException e ) {
            logger.warn(">>> [MngEM_SaveManyErrCall] T_CT_MANY_ERROR_MNG 같은요청전문 수신");
            throw new Exception(">>> [MngEM_SaveManyErrCall] T_CT_MANY_ERROR_MNG 같은요청전문 수신");

        } catch (Exception e)
        {
            logger.warn(">>> [MngEM_SaveManyErrCall]  T_CT_MANY_ERROR_MNG INSERT ERROR [{}]", e.getMessage());
            throw new Exception(">>> [MngEM_SaveManyErrCall]  T_CT_MANY_ERROR_MNG INSERT ERROR");
        }

    }
}