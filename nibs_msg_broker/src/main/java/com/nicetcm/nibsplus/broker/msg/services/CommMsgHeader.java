package com.nicetcm.nibsplus.broker.msg.services;

/**
 *
 * comm_msg_header.json VO
 * <pre>
 * {
 *   "name" : "comm_msg_header",        "kname" : "전문헤더",
 *   "columns" : [
 *      { "name" : "org_cd",            "kname" : "기관코드",          "type" : "AN",       "length" : 3  },
 *      { "name" : "ret_cd_src",        "kname" : "응답코드출처",      "type" : "AN",       "length" : 1  },
 *      { "name" : "ret_cd",            "kname" : "응답코드",          "type" : "AN",       "length" : 4  },
 *      { "name" : "msg_id",            "kname" : "전문출처",          "type" : "AN",       "length" : 7  },
 *      { "name" : "body_len",          "kname" : "전문 Body 길이",    "type" : "AN",       "length" : 4  },
 *      { "name" : "trans_date",        "kname" : "전문송신일자",      "type" : "AN",       "length" : 8  },
 *      { "name" : "trans_time",        "kname" : "전문송신시간",      "type" : "AN",       "length" : 6  },
 *      { "name" : "trans_seq_no",      "kname" : "전문일련번호",      "type" : "AN",       "length" : 7  },
 *      { "name" : "ap_class_no",       "kname" : "AP전문구분번호",    "type" : "AN",       "length" : 9  },
 *      { "name" : "format_type",       "kname" : "전문형태",          "type" : "AN",       "length" : 2  },
 *      { "name" : "msg_type",          "kname" : "전문구분",          "type" : "AN",       "length" : 4  },
 *      { "name" : "work_type",         "kname" : "업무구분",          "type" : "AN",       "length" : 4  },
 *      { "name" : "seq_no2",           "kname" : "전문일련번호2",     "type" : "AN",       "length" : 7  },
 *      { "name" : "re_send_yn",        "kname" : "재전송여부",        "type" : "AN",       "length" : 1  },
 *      { "name" : "host_seq",          "kname" : "HOST전문일련번호",  "type" : "AN",       "length" : 14 },
 *      { "name" : "filler",            "kname" : "여유공간",          "type" : "AN",       "length" : 8  },
 *      { "name" : "service_gb",        "kname" : "서비스구분",        "type" : "AN",       "length" : 1  }
 *   ]
 * }
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public class CommMsgHeader
{
    private String orgCd      ;
    private String retCdSrc   ;
    private String retCd      ;
    private String msgId      ;
    private String bodyLen    ;
    private String transDate  ;
    private String transTime  ;
    private String transSeqNo ;
    private String apClassNo  ;
    private String formatType ;
    private String msgType    ;
    private String workType   ;
    private String seqNo2     ;
    private String reSendYn   ;
    private String hostSeq    ;
    private String filler     ;
    private String serviceGb  ;
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
     * @return the msgId
     */
    public String getMsgId()
    {
        return msgId;
    }
    /**
     * @param msgId the msgId to set
     */
    public void setMsgId(String msgId)
    {
        this.msgId = msgId;
    }
    /**
     * @return the bodyLen
     */
    public String getBodyLen()
    {
        return bodyLen;
    }
    /**
     * @param bodyLen the bodyLen to set
     */
    public void setBodyLen(String bodyLen)
    {
        this.bodyLen = bodyLen;
    }
    /**
     * @return the transDate
     */
    public String getTransDate()
    {
        return transDate;
    }
    /**
     * @param transDate the transDate to set
     */
    public void setTransDate(String transDate)
    {
        this.transDate = transDate;
    }
    /**
     * @return the transTime
     */
    public String getTransTime()
    {
        return transTime;
    }
    /**
     * @param transTime the transTime to set
     */
    public void setTransTime(String transTime)
    {
        this.transTime = transTime;
    }
    /**
     * @return the transSeqNo
     */
    public String getTransSeqNo()
    {
        return transSeqNo;
    }
    /**
     * @param transSeqNo the transSeqNo to set
     */
    public void setTransSeqNo(String transSeqNo)
    {
        this.transSeqNo = transSeqNo;
    }
    /**
     * @return the apClassNo
     */
    public String getApClassNo()
    {
        return apClassNo;
    }
    /**
     * @param apClassNo the apClassNo to set
     */
    public void setApClassNo(String apClassNo)
    {
        this.apClassNo = apClassNo;
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
     * @return the seqNo2
     */
    public String getSeqNo2()
    {
        return seqNo2;
    }
    /**
     * @param seqNo2 the seqNo2 to set
     */
    public void setSeqNo2(String seqNo2)
    {
        this.seqNo2 = seqNo2;
    }
    /**
     * @return the reSendYn
     */
    public String getReSendYn()
    {
        return reSendYn;
    }
    /**
     * @param reSendYn the reSendYn to set
     */
    public void setReSendYn(String reSendYn)
    {
        this.reSendYn = reSendYn;
    }
    /**
     * @return the hostSeq
     */
    public String getHostSeq()
    {
        return hostSeq;
    }
    /**
     * @param hostSeq the hostSeq to set
     */
    public void setHostSeq(String hostSeq)
    {
        this.hostSeq = hostSeq;
    }
    /**
     * @return the filler
     */
    public String getFiller()
    {
        return filler;
    }
    /**
     * @param filler the filler to set
     */
    public void setFiller(String filler)
    {
        this.filler = filler;
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
