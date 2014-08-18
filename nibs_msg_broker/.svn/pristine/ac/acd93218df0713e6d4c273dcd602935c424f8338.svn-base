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
import com.nicetcm.nibsplus.broker.msg.mapper.TFnWrVanDemandMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnWrVanDemand;
import com.nicetcm.nibsplus.broker.msg.model.TFnWrVanDemandSpec;

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



    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
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

            SuSendHead suSendHead = new SuSendHead();

            List<TFnWrVanDemand> tFnWrVanDemandList = null;
            try
            {
                tFnWrVanDemandList = tFnWrVanDemandMapper.selectBySpec(tFnWrVanDemandSpec);
            } catch (Exception e)
            {
                /* 응답코드 '99' */
                suSendHead.setRetCd("91");
                logger.info(">>>  운영자금 파악 실패[{}]", e.getMessage());
            }

            if(tFnWrVanDemandList == null || tFnWrVanDemandList.size() == 0) {
                suSendHead.setRetCd("91");
                logger.info( "...해당 운영자금데이터 없음");
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
            
            
            
            
        }

    }

    private class SuSendHead {
        private String orgCd        ;
        private String retCd        ;
        private String retCdSrc     ;
        private String formatType   ;
        private String msgType      ;
        private String workType     ;
        private String serviceGb    ;
        /**
         * @return the orgCd
         */
        public String getOrgCd()
        {
            return orgCd;
        }
        /**
         * @param orgCd the orgCd to set
         */
        public void setOrgCd(String orgCd)
        {
            this.orgCd = orgCd;
        }
        /**
         * @return the retCd
         */
        public String getRetCd()
        {
            return retCd;
        }
        /**
         * @param retCd the retCd to set
         */
        public void setRetCd(String retCd)
        {
            this.retCd = retCd;
        }
        /**
         * @return the retCdSrc
         */
        public String getRetCdSrc()
        {
            return retCdSrc;
        }
        /**
         * @param retCdSrc the retCdSrc to set
         */
        public void setRetCdSrc(String retCdSrc)
        {
            this.retCdSrc = retCdSrc;
        }
        /**
         * @return the formatType
         */
        public String getFormatType()
        {
            return formatType;
        }
        /**
         * @param formatType the formatType to set
         */
        public void setFormatType(String formatType)
        {
            this.formatType = formatType;
        }
        /**
         * @return the msgType
         */
        public String getMsgType()
        {
            return msgType;
        }
        /**
         * @param msgType the msgType to set
         */
        public void setMsgType(String msgType)
        {
            this.msgType = msgType;
        }
        /**
         * @return the workType
         */
        public String getWorkType()
        {
            return workType;
        }
        /**
         * @param workType the workType to set
         */
        public void setWorkType(String workType)
        {
            this.workType = workType;
        }
        /**
         * @return the serviceGb
         */
        public String getServiceGb()
        {
            return serviceGb;
        }
        /**
         * @param serviceGb the serviceGb to set
         */
        public void setServiceGb(String serviceGb)
        {
            this.serviceGb = serviceGb;
        }
    }
}
