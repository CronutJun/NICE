package com.nicetcm.nibsplus.broker.msg.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtManyErrorMngMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtManyErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtManyErrorMngSpec;

/**
 *
 * 동시다발장애 조치결과통보_AP
 * <pre>
 * MngEM_SaveManyErrRslt( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01001490")
public class In01001490Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCtManyErrorMngMapper tCtManyErrorMngMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCtManyErrorMngSpec tCtManyErrorMngSpec = new TCtManyErrorMngSpec();
        tCtManyErrorMngSpec.createCriteria()
        .andCallDateEqualTo(parsed.getString("call_date"))
        .andCallNoEqualTo(parsed.getString("call_no"));

        List<TCtManyErrorMng> tCtManyErrorMngList = null;
        try
        {
            tCtManyErrorMngList = tCtManyErrorMngMapper.selectBySpec(tCtManyErrorMngSpec);
        } catch (Exception e)
        {
            logger.info("[T_CT_MANY_ERROR_MNG_FINISH] 장애건검색실패 call_date[{}], call_no[{}] [{}]", parsed.getString("call_date"), parsed.getString("call_no"), e.getMessage());
            throw new Exception("[T_CT_MANY_ERROR_MNG_FINISH] 장애건검색실패 call_date[" + parsed.getString("call_date") + "], call_no[" + parsed.getString("call_no") + "] [" + e.getMessage() + "]");
        }

        if(tCtManyErrorMngList == null || tCtManyErrorMngList.size() == 0) {
            logger.info("[T_CT_MANY_ERROR_MNG_FINISH] 해당데이터가 없습니다. call_date[{}], call_no[{}]", parsed.getString("call_date"), parsed.getString("call_no"));
            throw new Exception("[T_CT_MANY_ERROR_MNG_FINISH] 해당데이터가 없습니다. call_date[" + parsed.getString("call_date") + "], call_no[" + parsed.getString("call_no") + "]");
        }

        TCtManyErrorMng tCtManyErrorMng = tCtManyErrorMngList.get(0);

        String htime_conf = Integer.parseInt(tCtManyErrorMng.getCallDate() + tCtManyErrorMng.getCallTime()) > Integer.parseInt(parsed.getString("finish_date") + parsed.getString("finish_time")) ? "0" : "1";

        /* 이미 완료된 건은 update 하지 않도록 한다. */
        if(tCtManyErrorMng.getFinishTime().length() > 0) {
            throw new MsgBrokerException("[T_CT_MANY_ERROR_MNG_FINISH] 해당데이터가 없습니다. call_date[" + parsed.getString("call_date") + "], call_no[" + parsed.getString("call_no") + "]", -2);
        }

        TCtManyErrorMng updateTCtManyErrorMng = new TCtManyErrorMng();
        updateTCtManyErrorMng.setFinishDate(parsed.getString("finish_date"));
        updateTCtManyErrorMng.setFinishTime(parsed.getString("finish_time"));
        updateTCtManyErrorMng.setFinishNm(parsed.getString("finish_nm"));
        updateTCtManyErrorMng.setRemark(parsed.getString("bigo"));

        updateTCtManyErrorMng.setUpdateDate (safeData.getDSysDate());
        updateTCtManyErrorMng.setUpdateUid  ("ERRmng");

        TCtManyErrorMngSpec tCtManyErrorMngSpec2 = new TCtManyErrorMngSpec();
        tCtManyErrorMngSpec.createCriteria()
        .andCallDateEqualTo(parsed.getString("call_date"))
        .andCallNoEqualTo(parsed.getString("call_no"));

        try
        {
            tCtManyErrorMngMapper.updateBySpecSelective(updateTCtManyErrorMng, tCtManyErrorMngSpec2);
        } catch (Exception e)
        {
            logger.info("[T_CT_MANY_ERROR_MNG-FINISH] Update Err [{}]", e.getMessage());
            throw new Exception("[T_CT_MANY_ERROR_MNG-FINISH] Update Err [" + e.getMessage() + "]");
        }

        logger.info( "[T_CT_MANY_ERROR_MNG] Update OK" );

    }//end method
}