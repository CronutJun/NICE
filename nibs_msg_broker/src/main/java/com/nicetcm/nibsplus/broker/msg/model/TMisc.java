package com.nicetcm.nibsplus.broker.msg.model;

/**
 * Copyright 2014 The NIBS+ Project
 * 
 * 특정 테이블에 종속 되지 않은 Query들을 모아둔 클래스
 * 
 * @author KDJ on 2014.07.02
 *
 */
public class TMisc {

    /*
     * holiday 값 F_GET_HOLIDAY StoredFunction을 통해 취득 
     */
    private String holiday;
    /*
     * F_GET_NICE_BRANCH_CD StoredFunction을 통해 branchCd 취득 (삼성생명?)
     */
    private String branchCd;
    /*
     * 장애관리 Key 
     */
    private String errorNo;
    
    
    public String getHoliday() {
        return holiday;
    }
    
    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }
    
    public String getBranchCd() {
        return branchCd;
    }
    
    public void setBranchCd(String branchCd) {
        this.branchCd = branchCd;
    }
    
    public String getErrorNo() {
        return errorNo;
    }
    
    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }
    
}