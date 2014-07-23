package com.nicetcm.nibsplus.broker.msg.services;

import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtInputCheckListMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtInputCheckList;
import com.nicetcm.nibsplus.broker.msg.model.TCtInputCheckListSpec;

/**
 *
 * 1112 입금수표명세조회(다중응답)
 * <pre>
 * MngCM_AP_SaveInqCheckMulti( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101112")
public class In03101112Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101112Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TCtInputCheckListMapper tCtInputCheckListMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TCtInputCheckList insertTCtInputCheckList = new TCtInputCheckList();
        insertTCtInputCheckList.setFromDate      (parsed.getString("from_date"));
        insertTCtInputCheckList.setToDate        (parsed.getString("to_date"));
        insertTCtInputCheckList.setOrgCd         (parsed.getString("CM.org_cd"));
        insertTCtInputCheckList.setBranchCd      (parsed.getString("brch_cd"));
        insertTCtInputCheckList.setMacNo         (parsed.getString("mac_no"));
        insertTCtInputCheckList.setOwnCheckCnt   (parsed.getLong("own_check_cnt"));
        insertTCtInputCheckList.setOwnCheckAmt   (parsed.getLong("own_check_amt"));
        insertTCtInputCheckList.setOtherCheckCnt (parsed.getLong("other_check_cnt"));
        insertTCtInputCheckList.setOtherCheckAmt (parsed.getLong("other_check_amt"));
        insertTCtInputCheckList.setTotCnt        (parsed.getLong("tot_cnt"));
        insertTCtInputCheckList.setTotAmt        (parsed.getLong("tot_amt"));
        insertTCtInputCheckList.setInsertDate(safeData.getDSysDate());
        insertTCtInputCheckList.setUpdateDate(safeData.getDSysDate());

        try
        {
            tCtInputCheckListMapper.insertSelective(insertTCtInputCheckList);

        } catch( org.springframework.dao.DataIntegrityViolationException e ) {

            TCtInputCheckList updateTCtInputCheckList = new TCtInputCheckList();
            updateTCtInputCheckList.setOwnCheckCnt   (parsed.getLong("own_check_cnt"));
            updateTCtInputCheckList.setOwnCheckAmt   (parsed.getLong("own_check_amt"));
            updateTCtInputCheckList.setOtherCheckCnt (parsed.getLong("other_check_cnt"));
            updateTCtInputCheckList.setOtherCheckAmt (parsed.getLong("other_check_amt"));
            updateTCtInputCheckList.setTotCnt        (parsed.getLong("tot_cnt"));
            updateTCtInputCheckList.setTotAmt        (parsed.getLong("tot_amt"));

            updateTCtInputCheckList.setUpdateDate(new GregorianCalendar().getTime());



            TCtInputCheckListSpec tCtInputCheckListSpec = new TCtInputCheckListSpec();
            tCtInputCheckListSpec.createCriteria().andFromDateEqualTo(parsed.getString("from_date"))
                                                    .andToDateEqualTo(parsed.getString("to_date"))
                                                    .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                                                    .andBranchCdEqualTo(parsed.getString("brch_cd"))
                                                    .andMacNoEqualTo(parsed.getString("mac_no"));

            try
            {
                tCtInputCheckListMapper.updateBySpecSelective(updateTCtInputCheckList, tCtInputCheckListSpec);

            } catch (Exception e1)
            {
                logger.info(String.format("[T_CT_INPUT_CHECK_LIST] UPDATE Err [%s-%s][%.200s]", parsed.getString("msg_cnt"), parsed.getString("msg_index"), e1.getMessage()));
                throw e1;
            }
        } catch (Exception e)
        {
            logger.info(String.format("[T_CT_INPUT_CHECK_LIST] INSERT Err [%s-%s][%.200s]", parsed.getString("msg_cnt"), parsed.getString("msg_index"), e.getMessage()));
            throw e;
        }

        logger.info(String.format("[T_CT_INPUT_CHECK_LIST] [%s-%s]Save OK\n", parsed.getString("msg_cnt"), parsed.getString("msg_index")));


    }
}
