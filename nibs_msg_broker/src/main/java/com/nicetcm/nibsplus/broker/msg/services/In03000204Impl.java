package com.nicetcm.nibsplus.broker.msg.services;

import org.apache.commons.lang.StringUtils;
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
import com.nicetcm.nibsplus.broker.msg.mapper.TFnTicketDealMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnBoxOrg;
import com.nicetcm.nibsplus.broker.msg.model.TFnTicketDeal;
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

    @Autowired private TFnTicketDealMapper tFnTicketDealMapper;

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
            logger.warn( String.format("원거래 데이터 없음 [%s][%s][%s]", parsed.getString("mac_no"), parsed.getString("own_trade_date"), parsed.getString("own_seq_no")) );
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
                tFnBoxOrg.setUpdateUid ("online");

                try
                {
                    tFnBoxOrgMapper.insertSelective(tFnBoxOrg);
                } catch (Exception e)
                {
                    logger.warn( ">>> [MngCM_SaveCalcBoxTicket_부자재] (T_FN_INOUT) INSERT ERROR [{}]", e.getMessage());
                    throw new MsgBrokerException(-1);
                }

                logger.warn( "!!!처리완료_부자재(INSERT)!!!" );
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

                String[] ticketCnt = {
                    "ticket_cnt_1000"
                    ,"ticket_cnt_2000"
                    ,"ticket_cnt_3000"
                    ,"ticket_cnt_5000"
                    ,"ticket_cnt_10000"
                    ,"ticket_cnt_30000"
                    ,"ticket_cnt_50000"
                    ,"ticket_cnt_70000"
                    ,"ticket_cnt_100000"
                    ,"ticket_cnt_300000"
                    ,"ticket_cnt_500000"
                    ,"ticket_cnt_1000000"
                };

                for(int i = 0; i < 12; i++) {
                    int szCnt = parsed.getInt(ticketCnt[i]);
                    long szAmt = szCnt * nAryKjAmt[i];

                    if(szCnt > 0) {
                        /* BOX 데이터가 RETRY 되어 중복 수신되었을 경우 저장 하지 않고 정상으로 RETURN 처리*/
                        TMisc tMisc2 = new TMisc();
                        tMisc2.setOrgCd(parsed.getString("CM.org_cd"));
                        tMisc2.setBranchCd(parsed.getString("brch_cd"));
                        tMisc2.setMacNo(parsed.getString("mac_no"));
                        tMisc2.setDealDate(parsed.getString("own_trade_date"));
                        tMisc2.setSeq(parsed.getString("own_seq_no"));

                        tMisc2.setBoxGubun1("0004");
                        tMisc2.setBoxGubun2(parsed.getString("ticket_comp"));
                        tMisc2.setKjGubun(szAryKjGb[i]);

                        if(comPack.getBoxRecvYN(tMisc2) > 0) {
                            continue;
                        }

                        TFnBoxOrg tFnBoxOrg = tMiscMapper.generateSeqBox();
                        tFnBoxOrg.setOrgCd     (parsed.getString("CM.org_cd"));
                        tFnBoxOrg.setBranchCd  (parsed.getString("brch_cd"));
                        tFnBoxOrg.setMacNo     (parsed.getString("mac_no"));
                        tFnBoxOrg.setDealDate  (parsed.getString("own_trade_date"));
                        tFnBoxOrg.setSeq       (parsed.getString("own_seq_no"));
                        //tFnBoxOrg.setBoxSeq    (parsed.getString(""));TFnBoxOrg 생성시 시퀀스에서 가져옴
                        tFnBoxOrg.setBoxGubun1 (parsed.getString("0004"));
                        tFnBoxOrg.setBoxGubun2 (StringUtils.leftPad(parsed.getString("ticket_comp"), 4, "0"));
                        tFnBoxOrg.setKjGubun   (szAryKjGb[i]);
                        tFnBoxOrg.setKjCnt     (szCnt);
                        tFnBoxOrg.setKjAmt     (szAmt);
                        tFnBoxOrg.setUpdateDate(safeData.getDSysDate());
                        tFnBoxOrg.setUpdateUid ("online");

                        try
                        {
                            tFnBoxOrgMapper.insertSelective(tFnBoxOrg);
                        } catch (Exception e)
                        {
                            logger.warn( ">>> [MngCM_SaveCalcBoxTicket] (T_FN_INOUT) INSERT ERROR [{}]", e.getMessage());
                            throw new MsgBrokerException(-1);
                        }

                        logger.warn( "!!!처리완료(INSERT)!!!" );
                    }
                }//end for

            }

        } else if(parsed.getString("input_type").equals("1")) {
            /* 기기입금(1)일 경우  */



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

            String[] ticketCnt = {
                "ticket_cnt_1000"
                ,"ticket_cnt_2000"
                ,"ticket_cnt_3000"
                ,"ticket_cnt_5000"
                ,"ticket_cnt_10000"
                ,"ticket_cnt_30000"
                ,"ticket_cnt_50000"
                ,"ticket_cnt_70000"
                ,"ticket_cnt_100000"
                ,"ticket_cnt_300000"
                ,"ticket_cnt_500000"
                ,"ticket_cnt_1000000"
            };

            for(int i = 0; i < 12; i++) {
                int szCnt = parsed.getInt(ticketCnt[i]);
                long szAmt = szCnt * nAryKjAmt[i];

                if(szCnt > 0) {
                    /* BOX 데이터가 RETRY 되어 중복 수신되었을 경우 저장 하지 않고 정상으로 RETURN 처리*/
                    TMisc tMisc3 = new TMisc();
                    tMisc3.setOrgCd(parsed.getString("CM.org_cd"));
                    tMisc3.setBranchCd(parsed.getString("brch_cd"));
                    tMisc3.setMacNo(parsed.getString("mac_no"));
                    tMisc3.setDealDate(parsed.getString("own_trade_date"));
                    tMisc3.setSeq(parsed.getString("own_seq_no"));

                    tMisc3.setTicketCd(parsed.getString("ticket_comp"));
                    tMisc3.setKjGubun(szAryKjGb[i]);

                    if(comPack.getTicketDealRecvYN(tMisc3) > 0) {
                        continue;
                    }

                    TFnTicketDeal tFnTicketDeal = tMiscMapper.generateSeqTicketDeal();
                    //tFnTicketDeal.setTicketDealId(parsed.getString("")); TFnTicketDeal 생성시 시퀀스에서 가져옴
                    tFnTicketDeal.setOrgCd       (parsed.getString("CM.org_cd"));
                    tFnTicketDeal.setBranchCd    (parsed.getString("brch_cd"));
                    tFnTicketDeal.setMacNo       (parsed.getString("mac_no"));
                    tFnTicketDeal.setDealDate    (parsed.getString("own_trade_date"));
                    tFnTicketDeal.setSeq         (parsed.getString("own_seq_no"));
                    tFnTicketDeal.setTicketCd    (StringUtils.leftPad(parsed.getString("ticket_comp"), 4, "0"));
                    tFnTicketDeal.setKjGubun     (szAryKjGb[i]);
                    tFnTicketDeal.setKjCnt       (szCnt);
                    tFnTicketDeal.setKjAmt       (String.valueOf(szAmt));
                    tFnTicketDeal.setUpdateDate  (safeData.getDSysDate());
                    tFnTicketDeal.setUpdateUid   ("online");

                    try
                    {
                        tFnTicketDealMapper.insertSelective(tFnTicketDeal);
                    } catch (Exception e)
                    {
                        logger.warn( ">>> [MngCM_SaveCalcBoxTicket] (T_FN_TICKET_DEAL) INSERT ERROR [{}]", e.getMessage());
                        throw new MsgBrokerException(-1);
                    }

                    logger.warn( "!!!처리완료(INSERT)!!!" );
                }
            }//end for
        }

    }//end method
}
