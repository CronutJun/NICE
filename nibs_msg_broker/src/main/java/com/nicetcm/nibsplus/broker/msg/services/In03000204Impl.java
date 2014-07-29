package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnBoxOrgMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnBoxOrg;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

/**
 *
 * 0204 정산기 봉투입금-상품권
 * <pre>
 * MngCM_SaveCalcBoxTicket( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03000204")
public class In03000204Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03000204Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TMiscMapper tMiscMapper;

    @Autowired private TFnBoxOrgMapper tFnBoxOrgMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TMisc tMisc = new TMisc();
        tMisc.setOrgCd(parsed.getString("CM.org_cd"));
        tMisc.setBranchCd(parsed.getString("brch_cd"));
        tMisc.setMacNo(parsed.getString("mac_no"));
        tMisc.setDealDate(parsed.getString("own_trade_date"));
        tMisc.setSeq(parsed.getString("own_seq_no"));

        tMisc.setBoxGubun1("0008");
        tMisc.setBoxGubun2(parsed.getString("good_cd"));
        tMisc.setKjGubun("");

        int nRtn;

        /* 원거래 데이터가 존재하는지 체크 */
        nRtn = comPack.getOwnTradeSeqYN(tMisc);

        /* 이마트 일경우에는 원거래 데이터 체크 하나 이랜드는 안한다 */
        if(MsgBrokerConst.EMART_CODE.equals(parsed.getString("CM.org_cd")) && nRtn < 0) {
            logger.info( String.format("원거래 데이터 없음 [%s][%s][%s]", parsed.getString("mac_no"), parsed.getString("own_trade_date"), parsed.getString("own_seq_no")) );
            throw new MsgBrokerException(-1);
        }


        if(parsed.getString("input_type").equals("0")) {
            /* 봉투입금(0)일 경우  */

            if(parsed.getString("in_type").equals("3")) {
                /* 부자재 내역 일 경우 */

                /* BOX 데이터가 RETRY 되어 중복 수신되었을 경우 저장 하지 않고 정상으로 RETURN 처리*/
                if(comPack.getBoxRecvYN(tMisc) > 0) {
                    return;
                }

                TFnBoxOrg tFnBoxOrg = tMiscMapper.generateSeqBox();
                tFnBoxOrg.setOrgCd     (parsed.getString("CM.org_cd"));
                tFnBoxOrg.setBranchCd  (parsed.getString("brch_cd"));
                tFnBoxOrg.setMacNo     (parsed.getString("mac_no"));
                tFnBoxOrg.setDealDate  (parsed.getString("own_trade_date"));
                tFnBoxOrg.setSeq       (parsed.getString("own_seq_no"));
                //tFnBoxOrg.setBoxSeq    (parsed.getString(""));생성시 시퀀스에서 가져옴
                tFnBoxOrg.setBoxGubun1 (parsed.getString("0008"));
                tFnBoxOrg.setBoxGubun2 (parsed.getString("good_cd"));
                tFnBoxOrg.setKjCnt     (parsed.getInt("tot_ticket_cnt"));
                tFnBoxOrg.setKjAmt     (parsed.getLong("tot_ticket_amt"));
                tFnBoxOrg.setUpdateDate(safeData.getDSysDate());
                tFnBoxOrg.setUpdateUid (parsed.getString("online"));

                try
                {
                    tFnBoxOrgMapper.insertSelective(tFnBoxOrg);
                } catch (Exception e)
                {
                    logger.info( ">>> [MngCM_SaveCalcBoxTicket_부자재] (T_FN_INOUT) INSERT ERROR [{}]", e.getMessage());
                    throw new MsgBrokerException(-1);
                }

                logger.info( "!!!처리완료_부자재(INSERT)!!!" );
            } else {
                String[] szAryKjGb = {
                     "0001"
                    ,"0002"
                    ,"0016"
                    ,"0003"
                    ,"0004"
                    ,"0005"
                    ,"0006"
                    ,"0007"
                    ,"0008"
                    ,"0009"
                    ,"0010"
                    ,"0011"
                };

                int[] nAryKjAmt = {
                     1000
                    ,2000
                    ,3000
                    ,5000
                    ,10000
                    ,30000
                    ,50000
                    ,70000
                    ,100000
                    ,300000
                    ,500000
                    ,1000000
                };

                for(int i = 0; i < 12; i++) {

                }

            }

        }




    }//end method
}
