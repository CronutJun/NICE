package com.nicetcm.nibsplus.broker.msg.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnVanDemandDetailMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnWrVanDemandMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TIfDataLogMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnVanDemandDetail;
import com.nicetcm.nibsplus.broker.msg.model.TFnVanDemandDetailSpec;
import com.nicetcm.nibsplus.broker.msg.model.TFnWrVanDemand;
import com.nicetcm.nibsplus.broker.msg.model.TFnWrVanDemandSpec;
import com.nicetcm.nibsplus.broker.msg.model.TIfDataLog;
import com.nicetcm.nibsplus.broker.msg.model.TIfDataLogSpec;

/**
 *
 * 2110 결번요청
 * <pre>
 * MngIQ_SaveReqLostNo( pRecvData, pSendBuf, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04002110")
public class In04002110Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In04002110Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnWrVanDemandMapper tFnWrVanDemandMapper;
    @Autowired private TFnVanDemandDetailMapper tFnVanDemandDetailMapper;
    @Autowired private TMiscMapper tMiscMapper;
    @Autowired private TIfDataLogMapper tIfDataLogMapper;


    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        safeData.setKeepResData(false);

        /***************************************************************************
         [기업은행] 브랜드제휴 재전송 - 일련번호 상관 없이, 요청일자 운영자금, 상세 전송
        ***************************************************************************/
        if(MsgBrokerConst.KIUP_CODE.equals(parsed.getString("CM.org_cd")) && parsed.getString("CM.service_gb").equals("1")) {
            /**************************************************************************
             1. 요청일자 운영자금청구 전문 생성, 전송
            **************************************************************************/

            TFnWrVanDemandSpec tFnWrVanDemandSpec = new TFnWrVanDemandSpec();
            tFnWrVanDemandSpec.createCriteria()
            .andDemandDateEqualTo(parsed.getString("req_date"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"));

            CommMsgHeader suSendHead = new CommMsgHeader();

            List<TFnWrVanDemand> tFnWrVanDemandList = null;
            try
            {
                tFnWrVanDemandList = tFnWrVanDemandMapper.selectBySpec(tFnWrVanDemandSpec);
            } catch (Exception e)
            {
                /* 응답코드 '99' */
                suSendHead.setRetCd("91");
                logger.warn(">>>  운영자금 파악 실패[{}]", e.getMessage());
            }

            if(tFnWrVanDemandList == null || tFnWrVanDemandList.size() == 0) {
                suSendHead.setRetCd("91");
                logger.warn( "...해당 운영자금데이터 없음");
            }

            /*  여기까지, 운영자금 데이터를 SendBody에 넣었으므로, 다음은 전문형태를 갖춘다. */
            //memcpy( suSendHead.org_cd   , suHead.org_cd,        LEN_ORG_CD  );
            //memcpy( suSendHead.ret_cd_src, "S",     LEN_RET_CD_SRC );
            //memcpy( suSendHead.format_type  , "CM"                  ,       LEN_FORMAT_TYPE     );
            //memcpy( suSendHead.msg_type     , "0300"                ,       LEN_MSG_TYPE    );
            //memcpy( suSendHead.work_type    , "1193"                ,       LEN_WORK_TYPE   );
            //memcpy( suSendHead.service_gb   , "1"                   ,       LEN_SERVICE_GB  );

            suSendHead.setOrgCd(parsed.getString("CM.org_cd"));
            suSendHead.setRetCdSrc("S");
            suSendHead.setFormatType("CM");
            suSendHead.setMsgType("0300");
            suSendHead.setWorkType("1193");
            suSendHead.setServiceGb("1");

            comPack.msgSnd(safeData, suSendHead, null, "P");

            /**************************************************************************
                2. 요청일자 운영자금청구상세 전문 생성, 전송
            **************************************************************************/

            TFnVanDemandDetailSpec tFnVanDemandDetailSpec = new TFnVanDemandDetailSpec();

            tFnVanDemandDetailSpec.createCriteria()
            .andDemandDateEqualTo(parsed.getString("req_date"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"));

            CommMsgHeader suSendHead2 = new CommMsgHeader();

            List<TFnVanDemandDetail> tFnVanDemandDetailList = null;
            try
            {
                tFnVanDemandDetailList = tFnVanDemandDetailMapper.selectBySpec(tFnVanDemandDetailSpec);
            } catch (Exception e)
            {
                /* 응답코드 '99' */
                suSendHead2.setRetCd("91");
                logger.warn(">>>  운영자금 파악 실패[{}]", e.getMessage());
            }

            if(tFnVanDemandDetailList == null || tFnVanDemandDetailList.size() == 0) {
                /* 모두 0을 채워 보낸다. 응답코드 '91' */
                suSendHead2.setRetCd("91");
                logger.warn( "...해당 운영자금데이터 없음");
            }

            /*  여기까지, 운영자금 데이터를 SendBody에 넣었으므로, 다음은 전문형태를 갖춘다. */
            //memcpy( suSendHead2.org_cd  , suHead.org_cd,        LEN_ORG_CD  );
            //memcpy( suSendHead2.ret_cd_src, "S",        LEN_RET_CD_SRC );
            //memcpy( suSendHead2.format_type , "CM"                  ,       LEN_FORMAT_TYPE     );
            //memcpy( suSendHead2.msg_type        , "0300"                ,       LEN_MSG_TYPE    );
            //memcpy( suSendHead2.work_type   , "1194"                ,       LEN_WORK_TYPE   );
            //memcpy( suSendHead2.service_gb  , "1"                   ,       LEN_SERVICE_GB  );
            suSendHead2.setOrgCd(parsed.getString("CM.org_cd"));
            suSendHead2.setRetCdSrc("S");
            suSendHead2.setFormatType("CM");
            suSendHead2.setMsgType("0300");
            suSendHead2.setWorkType("1194");
            suSendHead2.setServiceGb("1");

            comPack.msgSnd(safeData, suSendHead2, null, "P");

        //end of 기업은행
        } else if(parsed.getString("req_seq").length() == 0) {
            /**************************************************************************
             요청일련번호가 공백으로 오면 최종 번호 설정하여 송신
            **************************************************************************/
            String maxTransSeqNo = null;
            try
            {
                maxTransSeqNo = tMiscMapper.getMaxTransSeqNo(parsed.getString("CM.org_cd"), parsed.getString("req_date"));

                if(maxTransSeqNo == null) {
                    logger.warn(">>> [T_CT_SEND_ORG_MSG] 결번 데이타 파악 실패");
                    throw new Exception(">>> [T_CT_SEND_ORG_MSG] 결번 데이타 파악 실패");
                } else {
                    parsed.setString("req_seq", maxTransSeqNo);
                    return;
                }
            } catch (Exception e)
            {
                logger.warn(">>> [T_CT_SEND_ORG_MSG] 결번 데이타 파악 실패 [{}]", e.getMessage());
                throw e;
            }

        } else {
            TIfDataLogSpec tIfDataLogSpec = new TIfDataLogSpec();
            tIfDataLogSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andTransDateEqualTo(parsed.getString("req_date"))
            .andTransSeqNoEqualTo(parsed.getLong("req_seq"))
            .andTransTypeEqualTo("QS");

            List<TIfDataLog> tIfDataLogList = null;

            try
            {
                tIfDataLogList = tIfDataLogMapper.selectBySpec(tIfDataLogSpec);

                if(tIfDataLogList == null || tIfDataLogList.size() == 0) {
                    logger.warn("...결번 데이타 없음");
                    throw new Exception("...결번 데이타 없음");
                } else {
                    comPack.msgSnd(tIfDataLogList.get(0).getTransData().getBytes());
                }
            } catch (Exception e)
            {
                logger.warn(">>> [T_CT_SEND_ORG_MSG] 결번 데이타 파악 실패 [{}]", e.getMessage());
                throw e;
            }

        }

    }//end method
}
