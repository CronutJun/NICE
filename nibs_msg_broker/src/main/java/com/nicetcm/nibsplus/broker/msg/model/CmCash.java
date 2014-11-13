package com.nicetcm.nibsplus.broker.msg.model;

public class CmCash {

    private String inqDate;
    private String orgCd;
    private String branchCd;
    private String macNo;

    private long hisClose;

    public String getInqDate() {
        return inqDate;
    }

    public void setInqDate(String inqDate) {
        this.inqDate = inqDate;
    }

    public String getOrgCd() {
        return orgCd;
    }

    public void setOrgCd(String orgCd) {
        this.orgCd = orgCd;
    }

    public String getBranchCd() {
        return branchCd;
    }

    public void setBranchCd(String branchCd) {
        this.branchCd = branchCd;
    }

    public String getMacNo() {
        return macNo;
    }

    public void setMacNo(String macNo) {
        this.macNo = macNo;
    }

    public long getHisClose() {
        return hisClose;
    }

    public void setHisClose(long hisClose) {
        this.hisClose = hisClose;
    }


}
