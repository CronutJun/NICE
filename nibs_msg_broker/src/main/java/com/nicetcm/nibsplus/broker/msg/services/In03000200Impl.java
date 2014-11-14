package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnInoutStatMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnAtmsCashPlan;
import com.nicetcm.nibsplus.broker.msg.model.TFnAtmsCashPlanSpec;
import com.nicetcm.nibsplus.broker.msg.model.TFnInoutStat;
import com.nicetcm.nibsplus.broker.msg.model.TFnInoutStatSpec;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

/**
 *
 * 0200 입출금내역 통보
 * <pre>
 * MngCM_SaveInOutList( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03000200")
public class In03000200Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03000200Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnInoutStatMapper tFnInoutStatMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TMacInfo macInfo = new TMacInfo();
        macInfo.setOrgCd( parsed.getString("CM.org_cd") );
        macInfo.setBranchCd( parsed.getString("brch_cd") );
        macInfo.setMacNo( parsed.getString("mac_no") );

        comPack.getMacInfo(macInfo);

        logger.warn("기관[{}] 지점[{}] 기번[{}] 기기명[{}] 부서[{}] 사무소[{}] 지소[{}]",
                        macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo(),
                        macInfo.getMacNm(), macInfo.getDeptCd(), macInfo.getOfficeCd(), macInfo.getTeamCd() );

        boolean isDbDupData = false;

        try
        {
            TFnInoutStat tFnInoutStat = new TFnInoutStat();
            tFnInoutStat.setOrgCd     (parsed.getString("CM.org_cd"));
            tFnInoutStat.setBranchCd  (parsed.getString("brch_cd"));
            tFnInoutStat.setSiteCd    (macInfo.getSiteCd());
            tFnInoutStat.setMacNo     (parsed.getString("mac_no"));
            tFnInoutStat.setTradeDate (MsgBrokerLib.rtrim(parsed.getString("trade_date")));
            tFnInoutStat.setKjGb      (String.valueOf(parsed.getLong("kj_gb")));
            tFnInoutStat.setIn01      (parsed.getLong("in_01" ));
            tFnInoutStat.setOut01     (parsed.getLong("out_01"));
            tFnInoutStat.setIn02      (parsed.getLong("in_02" ));
            tFnInoutStat.setOut02     (parsed.getLong("out_02"));
            tFnInoutStat.setIn03      (parsed.getLong("in_03" ));
            tFnInoutStat.setOut03     (parsed.getLong("out_03"));
            tFnInoutStat.setIn04      (parsed.getLong("in_04" ));
            tFnInoutStat.setOut04     (parsed.getLong("out_04"));
            tFnInoutStat.setIn05      (parsed.getLong("in_05" ));
            tFnInoutStat.setOut05     (parsed.getLong("out_05"));
            tFnInoutStat.setIn06      (parsed.getLong("in_06" ));
            tFnInoutStat.setOut06     (parsed.getLong("out_06"));
            tFnInoutStat.setIn07      (parsed.getLong("in_07" ));
            tFnInoutStat.setOut07     (parsed.getLong("out_07"));
            tFnInoutStat.setIn08      (parsed.getLong("in_08" ));
            tFnInoutStat.setOut08     (parsed.getLong("out_08"));
            tFnInoutStat.setIn09      (parsed.getLong("in_09" ));
            tFnInoutStat.setOut09     (parsed.getLong("out_09"));
            tFnInoutStat.setIn10      (parsed.getLong("in_10" ));
            tFnInoutStat.setOut10     (parsed.getLong("out_10"));
            tFnInoutStat.setIn11      (parsed.getLong("in_11" ));
            tFnInoutStat.setOut11     (parsed.getLong("out_11"));
            tFnInoutStat.setIn12      (parsed.getLong("in_12" ));
            tFnInoutStat.setOut12     (parsed.getLong("out_12"));
            tFnInoutStat.setIn13      (parsed.getLong("in_13" ));
            tFnInoutStat.setOut13     (parsed.getLong("out_13"));
            tFnInoutStat.setIn14      (parsed.getLong("in_14" ));
            tFnInoutStat.setOut14     (parsed.getLong("out_14"));
            tFnInoutStat.setIn15      (parsed.getLong("in_15" ));
            tFnInoutStat.setOut15     (parsed.getLong("out_15"));
            tFnInoutStat.setIn16      (parsed.getLong("in_16" ));
            tFnInoutStat.setOut16     (parsed.getLong("out_16"));
            tFnInoutStat.setIn17      (parsed.getLong("in_17" ));
            tFnInoutStat.setOut17     (parsed.getLong("out_17"));
            tFnInoutStat.setIn18      (parsed.getLong("in_18" ));
            tFnInoutStat.setOut18     (parsed.getLong("out_18"));
            tFnInoutStat.setIn19      (parsed.getLong("in_19" ));
            tFnInoutStat.setOut19     (parsed.getLong("out_19"));
            tFnInoutStat.setIn20      (parsed.getLong("in_20" ));
            tFnInoutStat.setOut20     (parsed.getLong("out_20"));
            tFnInoutStat.setIn21      (parsed.getLong("in_21" ));
            tFnInoutStat.setOut21     (parsed.getLong("out_21"));
            tFnInoutStat.setIn22      (parsed.getLong("in_22" ));
            tFnInoutStat.setOut22     (parsed.getLong("out_22"));
            tFnInoutStat.setIn23      (parsed.getLong("in_23" ));
            tFnInoutStat.setOut23     (parsed.getLong("out_23"));
            tFnInoutStat.setIn24      (parsed.getLong("in_24" ));
            tFnInoutStat.setOut24     (parsed.getLong("out_24"));
            tFnInoutStat.setUpdateDate(safeData.getDSysDate());

            tFnInoutStatMapper.insertSelective(tFnInoutStat);

        } catch( org.springframework.dao.DataIntegrityViolationException e ) {

            isDbDupData = true;

        } catch (Exception e)
        {
            logger.warn(">>> [fnDBInsertUpdateFundsInOut] (T_FN_INOUT_STAT) INSERT ERROR {}", e.getMessage());
            throw e;
        }

        if(isDbDupData) {
            logger.warn("...중복요청건...");

            try
            {
                TFnInoutStat tFnInoutStat = new TFnInoutStat();
                tFnInoutStat.setSiteCd    (macInfo.getSiteCd());
                tFnInoutStat.setTradeDate (MsgBrokerLib.rtrim(parsed.getString("trade_date")));
                tFnInoutStat.setIn01      (parsed.getLong("in_01" ));
                tFnInoutStat.setOut01     (parsed.getLong("out_01"));
                tFnInoutStat.setIn02      (parsed.getLong("in_02" ));
                tFnInoutStat.setOut02     (parsed.getLong("out_02"));
                tFnInoutStat.setIn03      (parsed.getLong("in_03" ));
                tFnInoutStat.setOut03     (parsed.getLong("out_03"));
                tFnInoutStat.setIn04      (parsed.getLong("in_04" ));
                tFnInoutStat.setOut04     (parsed.getLong("out_04"));
                tFnInoutStat.setIn05      (parsed.getLong("in_05" ));
                tFnInoutStat.setOut05     (parsed.getLong("out_05"));
                tFnInoutStat.setIn06      (parsed.getLong("in_06" ));
                tFnInoutStat.setOut06     (parsed.getLong("out_06"));
                tFnInoutStat.setIn07      (parsed.getLong("in_07" ));
                tFnInoutStat.setOut07     (parsed.getLong("out_07"));
                tFnInoutStat.setIn08      (parsed.getLong("in_08" ));
                tFnInoutStat.setOut08     (parsed.getLong("out_08"));
                tFnInoutStat.setIn09      (parsed.getLong("in_09" ));
                tFnInoutStat.setOut09     (parsed.getLong("out_09"));
                tFnInoutStat.setIn10      (parsed.getLong("in_10" ));
                tFnInoutStat.setOut10     (parsed.getLong("out_10"));
                tFnInoutStat.setIn11      (parsed.getLong("in_11" ));
                tFnInoutStat.setOut11     (parsed.getLong("out_11"));
                tFnInoutStat.setIn12      (parsed.getLong("in_12" ));
                tFnInoutStat.setOut12     (parsed.getLong("ou_t12"));
                tFnInoutStat.setIn13      (parsed.getLong("in_13" ));
                tFnInoutStat.setOut13     (parsed.getLong("out_13"));
                tFnInoutStat.setIn14      (parsed.getLong("in_14" ));
                tFnInoutStat.setOut14     (parsed.getLong("out_14"));
                tFnInoutStat.setIn15      (parsed.getLong("in_15" ));
                tFnInoutStat.setOut15     (parsed.getLong("out_15"));
                tFnInoutStat.setIn16      (parsed.getLong("in_16" ));
                tFnInoutStat.setOut16     (parsed.getLong("out_16"));
                tFnInoutStat.setIn17      (parsed.getLong("in_17" ));
                tFnInoutStat.setOut17     (parsed.getLong("out_17"));
                tFnInoutStat.setIn18      (parsed.getLong("in_18" ));
                tFnInoutStat.setOut18     (parsed.getLong("out_18"));
                tFnInoutStat.setIn19      (parsed.getLong("in_19" ));
                tFnInoutStat.setOut19     (parsed.getLong("out_19"));
                tFnInoutStat.setIn20      (parsed.getLong("in_20" ));
                tFnInoutStat.setOut20     (parsed.getLong("out_20"));
                tFnInoutStat.setIn21      (parsed.getLong("in_21" ));
                tFnInoutStat.setOut21     (parsed.getLong("out_21"));
                tFnInoutStat.setIn22      (parsed.getLong("in_22" ));
                tFnInoutStat.setOut22     (parsed.getLong("out_22"));
                tFnInoutStat.setIn23      (parsed.getLong("in_23" ));
                tFnInoutStat.setOut23     (parsed.getLong("out_23"));
                tFnInoutStat.setIn24      (parsed.getLong("in_24" ));
                tFnInoutStat.setOut24     (parsed.getLong("out_24"));
                tFnInoutStat.setUpdateDate(safeData.getDSysDate());

                TFnInoutStatSpec tFnInoutStatSpec = new TFnInoutStatSpec();
                tFnInoutStatSpec.createCriteria()
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andMacNoEqualTo(parsed.getString("mac_no"))
                .andTradeDateEqualTo(parsed.getString("trade_date"))
                .andKjGbEqualTo(String.valueOf(parsed.getLong("kj_gb")));

                tFnInoutStatMapper.updateBySpecSelective(tFnInoutStat, tFnInoutStatSpec);


            } catch (Exception e)
            {
                logger.warn(">>> [fnDBInsertUpdateFundsInOut] (T_FN_INOUT_STAT) UPDATE ERROR {}", e.getMessage());
                throw e;
            }

            logger.warn("!!!처리완료(UPDATE)!!!");

        }

        logger.warn("!!!처리완료(INSERT)!!!");

    }
}
