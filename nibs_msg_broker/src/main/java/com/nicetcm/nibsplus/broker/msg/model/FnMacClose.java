package com.nicetcm.nibsplus.broker.msg.model;

/**
*
* FnMacClose
* <pre>
* sp_fn_macClose_nh call input VO
* </pre>
*
* @author s7760ker@gmail.com
* @version 1.0
* @see
*/
public class FnMacClose {
   /*
   pCloseDate      IN        t_fn_close.close_date%TYPE,
   pOrgCode        IN        t_fn_close.org_cd%TYPE,
   pJijumCode      IN        t_fn_close.jijum_cd%TYPE,
   pMacNo          IN        t_fn_close.mac_no%TYPE,
   pUserId         IN        t_fn_close.insert_uid%TYPE,
   vResult         OUT       VARCHAR2
   */

   private String closeDate   ;
   private String orgCode     ;
   private String jijumCode   ;
   private String macNo       ;
   private String closeTime   ;
   private String inTime      ;
   private String sendType    ;
   private String notSend     ;
   private String endType     ;
   private Long   moveAmt     ;
   private String userId      ;
   private String result      ;
   /**
    * @return the closeDate
    */
   public String getCloseDate()
   {
       return closeDate;
   }
   /**
    * @param closeDate the closeDate to set
    */
   public void setCloseDate(String closeDate)
   {
       this.closeDate = closeDate;
   }
   /**
    * @return the orgCode
    */
   public String getOrgCode()
   {
       return orgCode;
   }
   /**
    * @param orgCode the orgCode to set
    */
   public void setOrgCode(String orgCode)
   {
       this.orgCode = orgCode;
   }
   /**
    * @return the jijumCode
    */
   public String getJijumCode()
   {
       return jijumCode;
   }
   /**
    * @param jijumCode the jijumCode to set
    */
   public void setJijumCode(String jijumCode)
   {
       this.jijumCode = jijumCode;
   }
   /**
    * @return the macNo
    */
   public String getMacNo()
   {
       return macNo;
   }
   /**
    * @param macNo the macNo to set
    */
   public void setMacNo(String macNo)
   {
       this.macNo = macNo;
   }
   /**
    * @return the userId
    */
   public String getUserId()
   {
       return userId;
   }
   /**
    * @param userId the userId to set
    */
   public void setUserId(String userId)
   {
       this.userId = userId;
   }
   /**
    * @return the result
    */
   public String getResult()
   {
       return result;
   }
   /**
    * @param result the result to set
    */
   public void setResult(String result)
   {
       this.result = result;
   }
   /**
    * @return the closeTime
    */
   public String getCloseTime()
   {
       return closeTime;
   }
   /**
    * @param closeTime the closeTime to set
    */
   public void setCloseTime(String closeTime)
   {
       this.closeTime = closeTime;
   }

   public String getInTime() {
       return inTime;
   }
   public void setInTime(String inTime) {
       this.inTime = inTime;
   }
   public String getSendType() {
       return sendType;
   }
   public void setSendType(String sendType) {
       this.sendType = sendType;
   }
   public String getNotSend() {
       return notSend;
   }
   public void setNotSend(String notSend) {
       this.notSend = notSend;
   }
   public String getEndType() {
       return endType;
   }
   public void setEndType(String endType) {
       this.endType = endType;
   }
   public Long getMoveAmt() {
       return moveAmt;
   }
   public void setMoveAmt(Long moveAmt) {
       this.moveAmt = moveAmt;
   }


}