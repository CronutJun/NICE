package com.nicetcm.nibsplus.broker.msg.model;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * T_CT_ERROR 테이블 정규쿼리 이외의 조건값들을 처리하는 모델 클래스
 * 
 * 
 *           2014. 06. 24
 *           
 *           @author KDJ
 */
public class TCtErrorCond 
{
    private String orgCd;
    private String errorCd;
    private String brandOrgCd;
    private String macGrade;
    
    public String getOrgCd() {
        return orgCd;
    }
    
    public void setOrgCd(String orgCd) {
        this.orgCd = orgCd;
    }

    public String getErrorCd() {
        return errorCd;
    }
    
    public void setErrorCd(String errorCd) {
        this.errorCd = errorCd;
    }

    public String getBrandOrgCd() {
        return brandOrgCd;
    }
    
    public void setBrandOrgCd(String brandOrgCd) {
        this.brandOrgCd = brandOrgCd;
    }

    public String getMacGrade() {
        return macGrade;
    }
    
    public void setMacGrade(String macGrade) {
        this.macGrade = macGrade;
    }

}
