package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnDayCloseKtisMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnDayCloseMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnDayClose;
import com.nicetcm.nibsplus.broker.msg.model.TFnDayCloseKtis;
import com.nicetcm.nibsplus.broker.msg.model.TFnDayCloseKtisSpec;
import com.nicetcm.nibsplus.broker.msg.model.TFnDayCloseSpec;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

/**
 *
 * 0320 일마감 통보   ===> in03100320 과 내용이 같음 (K.D.J)
 * <pre>
 * MngCM_SaveDayClose( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03100320")
public class In03100320Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03100320Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnDayCloseKtisMapper tFnDayCloseKtisMapper;

    @Autowired private TFnDayCloseMapper tFnDayCloseMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TMacInfo macInfo = new TMacInfo();
        macInfo.setOrgCd( parsed.getString("CM.org_cd") );
        macInfo.setBranchCd( parsed.getString("brch_cd") );
        macInfo.setMacNo( parsed.getString("mac_no") );

        comPack.getMacInfo(macInfo);

        if( MsgBrokerConst.YK_CODE.equals(parsed.getString("CM.org_cd")) ) {
            parsed.setInt("cash_out_cnt", 0);
            parsed.setInt("cash_rtn_cnt", 0);
            parsed.setLong("cash_out_amt", 0);
            parsed.setLong("cash_rtn_amt", 0);

            parsed.setLong("cash_out_amt",
                parsed.getLong("out_100000_cnt"  ) * 100000  +
                parsed.getLong("out_50000_cnt"   ) * 50000   +
                parsed.getLong("out_10000_cnt"   ) * 10000   +
                parsed.getLong("out_5000_cnt"    ) * 5000    +
                parsed.getLong("out_1000_cnt"    ) * 1000    +
                parsed.getLong("out_500_cnt"     ) * 500     +
                parsed.getLong("out_100_cnt"     ) * 100     +
                parsed.getLong("out_50_cnt"      ) * 50      +
                parsed.getLong("out_10_cnt"      ) * 10
            );

            parsed.setInt("cash_rtn_amt",
                parsed.getInt("in_100000_cnt"   ) * 100000  +
                parsed.getInt("in_50000_cnt"    ) * 50000   +
                parsed.getInt("in_10000_cnt"    ) * 10000   +
                parsed.getInt("in_5000_cnt"     ) * 5000    +
                parsed.getInt("in_1000_cnt"     ) * 1000    +
                parsed.getInt("in_500_cnt"      ) * 500     +
                parsed.getInt("in_100_cnt"      ) * 100     +
                parsed.getInt("in_50_cnt"       ) * 50      +
                parsed.getInt("in_10_cnt"       ) * 10
            );
        }

        boolean isDbDupData = false;

        if( MsgBrokerConst.TAXRF_CODE.equals(parsed.getString("CM.org_cd")) ) {

            TFnDayCloseKtis tFnDayCloseKtis = new TFnDayCloseKtis();
            tFnDayCloseKtis.setOrgCd      (parsed.getString("CM.org_cd"));
            tFnDayCloseKtis.setBranchCd    (parsed.getString("brch_cd"));
            tFnDayCloseKtis.setMacNo      (parsed.getString("mac_no"));
            tFnDayCloseKtis.setDeptCd     (macInfo.getFdeptCd());
            tFnDayCloseKtis.setOfficeCd   (macInfo.getFofficeCd());
            tFnDayCloseKtis.setCloseType  ("ORG");
            tFnDayCloseKtis.setCloseDate  (parsed.getString("close_date"));
            tFnDayCloseKtis.setCloseTime  ( (parsed.getString("close_time").equals("") ? "000000" : parsed.getString("close_time")) );
            tFnDayCloseKtis.setCashOutAmt (parsed.getLong("cash_out_amt"));
            tFnDayCloseKtis.setUpdateUid  ("ETC_In");
            tFnDayCloseKtis.setUpdateDate (safeData.getDSysDate());

            try
            {
                tFnDayCloseKtisMapper.insertSelective(tFnDayCloseKtis);

            } catch( org.springframework.dao.DataIntegrityViolationException e ) {
                isDbDupData = true;

            } catch (Exception e)
            {
                logger.warn(">>> [T_FN_DAY_CLOSE_KTIS] INSERT ERROR {}", e.getMessage());
                throw e;
            }

        } else {

            TFnDayClose tFnDayClose = new TFnDayClose();
            tFnDayClose.setOrgCd     (parsed.getString("CM.org_cd"));
            tFnDayClose.setBranchCd  (parsed.getString("brch_cd"));
            tFnDayClose.setMacNo     (parsed.getString("mac_no"));
            tFnDayClose.setCloseDate (parsed.getString("close_date"));
            tFnDayClose.setCloseTime (parsed.getString("close_time"));
            tFnDayClose.setCashOutCnt(parsed.getInt("cash_out_cnt"));
            tFnDayClose.setCashOutAmt(parsed.getLong("cash_out_amt"));
            tFnDayClose.setCashRtnCnt(parsed.getInt("cash_rtn_cnt"));
            tFnDayClose.setCashRtnAmt(parsed.getLong("cash_rtn_amt"));
            tFnDayClose.setUpdateUid (parsed.getString("ETC_In"));
            tFnDayClose.setUpdateDate(safeData.getDSysDate());

            try
            {
                tFnDayCloseMapper.insertSelective(tFnDayClose);

            } catch( org.springframework.dao.DataIntegrityViolationException e ) {
                isDbDupData = true;

            } catch (Exception e)
            {
                logger.warn(">>> [T_FN_DAY_CLOSE] INSERT ERROR {}", e.getMessage());
                throw e;
            }
        }

        if(isDbDupData) {
            if( MsgBrokerConst.TAXRF_CODE.equals(parsed.getString("CM.org_cd")) ) {
                TFnDayCloseKtis tFnDayCloseKtis = new TFnDayCloseKtis();
                tFnDayCloseKtis.setCashOutAmt (parsed.getLong("cash_out_amt"));
                tFnDayCloseKtis.setUpdateUid  ("ETC_Up");
                tFnDayCloseKtis.setUpdateDate (safeData.getDSysDate());

                TFnDayCloseKtisSpec tFnDayCloseKtisSpec = new TFnDayCloseKtisSpec();
                tFnDayCloseKtisSpec.createCriteria()
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andMacNoEqualTo(parsed.getString("mac_no"))
                .andCloseTypeEqualTo("ORG")
                .andCloseDateEqualTo(parsed.getString("close_date"))
                .andCloseTimeEqualTo( (parsed.getString("close_time").equals("") ? "000000" : parsed.getString("close_time")) );

                try
                {
                    tFnDayCloseKtisMapper.updateBySpecSelective(tFnDayCloseKtis, tFnDayCloseKtisSpec);
                } catch (Exception e)
                {
                    logger.warn(">>> [T_FN_DAY_CLOSE_KTIS] UPDATE ERROR {}", e.getMessage());
                    throw e;
                }

            } else {
                TFnDayClose tFnDayClose = new TFnDayClose();
                tFnDayClose.setCashOutCnt(parsed.getInt("cash_out_cnt"));
                tFnDayClose.setCashOutAmt(parsed.getLong("cash_out_amt"));
                tFnDayClose.setCashRtnCnt(parsed.getInt("cash_rtn_cnt"));
                tFnDayClose.setCashRtnAmt(parsed.getLong("cash_rtn_amt"));
                tFnDayClose.setUpdateUid (parsed.getString("ETC_Up"));
                tFnDayClose.setUpdateDate(safeData.getDSysDate());

                TFnDayCloseSpec tFnDayCloseSpec = new TFnDayCloseSpec();
                tFnDayCloseSpec.createCriteria()
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andMacNoEqualTo(parsed.getString("mac_no"))
                .andCloseDateEqualTo(parsed.getString("close_date"))
                .andCloseTimeEqualTo(parsed.getString("close_time"));

                try
                {
                    tFnDayCloseMapper.updateBySpecSelective(tFnDayClose, tFnDayCloseSpec);
                } catch (Exception e)
                {
                    logger.warn(">>> [T_FN_DAY_CLOSE] UPDATE ERROR {}", e.getMessage());
                    throw e;
                }
            }
        }//endif

    }//end method
}
