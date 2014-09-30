package com.nicetcm.nibsplus.filemng.model;
/*
======================================================================
 일괄전송 농협 FTP 수신 FILE FORMAT   HEADER
======================================================================
struct FTP_NH_TRAN_HEADER {
    char    work_nm         [6 +1];  업무명          "VC0001"           1-6
    char    data_type       [2 +1];  데이터구분        "11"             7-8
    char    data_seq        [7 +1];  데이터일련번호  "0000000"          9-15
    char    org_cd          [3 +1];  은행코드         "011"             16-18
    char    van_cd          [3 +1];  기관코드         VAN구분코드+SPACE 19-21
    char    tot_record_cnt  [7 +1];  총 Data Record 수                  22-28
    char    deal_date       [8 +1];  거래일자                           29-36
    char    filler          [64+1];  여분                               37-100
};
*/
public class TFnNiceTranNhHeaderVO
{
    private String workNm;
    private String dataType;
    private String dataSeq;
    private String orgCd;
    private String vanCd;
    private String totRecordCnt;
    private String dealDate;
    private String filler;
    /**
     * @return the workNm
     */
    public String getWorkNm()
    {
        return workNm;
    }
    /**
     * @param workNm the workNm to set
     */
    public void setWorkNm(String workNm)
    {
        this.workNm = workNm;
    }
    /**
     * @return the dataType
     */
    public String getDataType()
    {
        return dataType;
    }
    /**
     * @param dataType the dataType to set
     */
    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }
    /**
     * @return the dataSeq
     */
    public String getDataSeq()
    {
        return dataSeq;
    }
    /**
     * @param dataSeq the dataSeq to set
     */
    public void setDataSeq(String dataSeq)
    {
        this.dataSeq = dataSeq;
    }
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
     * @return the vanCd
     */
    public String getVanCd()
    {
        return vanCd;
    }
    /**
     * @param vanCd the vanCd to set
     */
    public void setVanCd(String vanCd)
    {
        this.vanCd = vanCd;
    }
    /**
     * @return the totRecordCnt
     */
    public String getTotRecordCnt()
    {
        return totRecordCnt;
    }
    /**
     * @param totRecordCnt the totRecordCnt to set
     */
    public void setTotRecordCnt(String totRecordCnt)
    {
        this.totRecordCnt = totRecordCnt;
    }
    /**
     * @return the dealDate
     */
    public String getDealDate()
    {
        return dealDate;
    }
    /**
     * @param dealDate the dealDate to set
     */
    public void setDealDate(String dealDate)
    {
        this.dealDate = dealDate;
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
}
