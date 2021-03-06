/** 
 * com.nicetcm.nibsplus.sqlservice.service.SqlService
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 11. 13.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.sqlservice.service;


/**
 * 여기에 클래스(한글)명.
 * <pre>
 * 여기에 클래스 설명 및 변경 이력을 기술하십시오.
 * </pre>
 * 
 * @author 박상철
 * @version 1.0
 * @see
 */
public interface SqlService {
	public void SP_CM_FAX();
	public void SP_CM_MAC_RANK();
	public void SP_CM_PERSONNEL();
	public void SP_CM_UPDATE_PLAN_FOCUS();
	public void SP_CT_CHECK_0EL_ALERT();
	public void SP_CT_CHECK_ALERT_039();
	public void SP_CT_CHECK_ALERT_NH_FILE();
	public void SP_CT_CHECK_ALERT();
	public void SP_CT_DELETE_DAY();
	public void SP_CT_REPORT_DAY_2012();
	public void SP_FN_BSCENTER();
	public void SP_FN_CASHFLOW_ENDAMT();
	public void SP_FN_CENTER();
	public void SP_FN_CLOSE_SEND_WC();
	public void SP_FN_CTCENTER();
	public void SP_FN_DAY_CLOSE_KTIS();
	public void SP_FN_DEAL_FORECAST();
	public void SP_FN_DEALCOUNT();
	public void SP_FN_DGBCENTER();
	public void SP_FN_ELAND_AMT();
	public void SP_FN_ERR_TRADE();
	public void SP_FN_ETC_MSUM();
	public void SP_FN_FORCAST_ADDSEND_CENTER();
	public void SP_FN_FORCAST_DATA_DSUM_ATM();
	public void SP_FN_HNCENTER();
	public void SP_FN_IMSI_JAN();
	public void SP_FN_JJCENTER();
	public void SP_FN_KBCENTER();
	public void SP_FN_KEBCENTER();
	public void SP_FN_KNCENTER();
	public void SP_FN_KTISCENTER();
	public void SP_FN_KUCENTER();
	public void SP_FN_LARGEMONTH();
	public void SP_FN_MAC_STOCK_AMT();
	public void SP_FN_MACCOUNT();
	public void SP_FN_MAN();
	public void SP_FN_NHCENTER();
	public void SP_FN_NICE_DEAL_BYDAY();
	public void SP_FN_NICE_DEAL_BYHOUR();
	public void SP_FN_NICE_DSUM();
	public void SP_FN_NICE_DSUM_BC();
	public void SP_FN_NICE_ENDAMT();
	public void SP_FN_NICE_EVERY_INSERT();
	public void SP_FN_NICE_TICKET_CNT();
	public void SP_FN_NICE_TRAN_GIFT_DSUM();
	public void SP_FN_NICE_TRAN_TOTAL();
	public void SP_FN_NOTCLOSE_CANCEL();
	public void SP_FN_NOTEND_NICE();
	public void SP_FN_OFFICECLOSE();
	public void SP_FN_ONLYCASHDATAMACCLOSE();
	public void SP_FN_OPERFUNDSSH();
	public void SP_FN_RECV_AMT_BS();
	public void SP_FN_SENDPLAN_CONFIRM_BS();
	public void SP_FN_SENDPLAN_CONFIRM_SH();
	public void SP_FN_SENDPLAN_CONFIRM();
	public void SP_FN_SENDPLAN_REFORM();
	public void SP_FN_SENDPLAN_UPLOAD_SH();
	public void SP_FN_SERVICESEND();
	public void SP_FN_SH_OPCENTER();
	public void SP_FN_SHCENTER();
	public void SP_FN_SITECOUNT();
	public void SP_FN_SMCENTER();
	public void SP_FN_WCCENTER();
	public void SP_FN_WR_VAN_DEMAND();
	public void SP_FN_WRCENTER();
	public void SP_IF_SENDSMSCASHLACK();
	public void SP_JOB_NICE_SHORT_CASH_CONFIG();
	public void SP_PHS_PUSH_MESSAGES();
	public void SP_SU_MULTIREPORT_ALL();
	public void SP_UPDATE_SITE();
	public void SP_FN_NICE_TS_INSERT();
	public void SP_CM_PASSWORD_CHANGE();
	public void SP_CT_MONTH_USE_STATUS_003();
	public void T_CT_ERROR_MNG_003_BRAND_TEMP();
	public void SP_BATCH_ERROR_ETC_DELETE();
	
	// OracleJob 추가
	public void SP_CHECK_PHOTO_CARRY();
	public void SP_CM_CASH_WC_INSERT();
	public void SP_CM_ELECSIGN_RETIRE();
	public void SP_CM_PKG_DAY();
	public void SP_CT_LINEFAULT_STATUS();
	public void SP_CT_LOCK_HISTOY_BACKUP();
	public void SP_CT_LOCKSERVER_VERIFY();
	public void SP_CT_T_CT_TRADE_CONFIRM_INIT();
	public void SP_FN_ADJUSTMENT_CARRY();
	public void SP_FN_CREATE_NOTEND_MEMBER();
	public void SP_FN_EMART_TICKET();
	public void SP_FN_MAKE_NODEAL_RESULT_HIS();
	public void SP_FN_NOTEND_FEE();
	public void SP_FN_ONNURI_DSUM();
	public void SP_FN_PRESAFE_HISTORY();
	public void SP_IF_SENDSMSKTISERROR();
	public void SP_JOB_ARRIVAL_EST_TIME();
	public void SP_JOB_AUTOSEND_PUSH();
	public void SP_JOB_BOARD_LIST_NOTICE();
	public void SP_JOB_FN_SENDPLAN_UPDATE_0KI();
	public void SP_JOB_SCHEDULE_MNG_NOTICE();
	public void SP_JOB_T_CT_AS_UNFINISH();
	public void SP_JOB_T_CT_AUTOSEND_MAC();
	public void SP_JOB_T_CT_DELAY_MONITORING();
	public void SP_JOB_T_CT_ERROR_MNG();
	public void SP_JOB_T_CT_ERROR_MNG_GUARD();
	public void SP_JOB_T_CT_ERROR_OPEN_CHECK();
	public void SP_JOB_T_CV_POSITION();
	public void SP_JOB_T_EMART_MONITORING();
	public void SP_JOB_T_FN_SENDPLAN_SCHEDULE();
	public void SP_JOB_T_SHORT_CASH_MNG();
	public void SP_RC_INFO_DELETE();
	public void SP_FN_FORCAST_DATA_DSUM_BASE();
	
	// 누락분
	public void SP_FN_FORCAST_DATA_DSUM();
	public void SP_FN_FORCAST_INOUT_CENTER();
	public void SP_CT_CHECK_ALERT_EMARTPOS();
	public void SP_FN_DAY_CLOSE_KIOSK();
	public void SP_FN_KB_VAN_DEMAND();
	public void SP_FN_VOUCHER_MACSTOCK();
	public void SP_FN_VOUCHER_OFFICESTOCK();
	public void SP_INSERT_NICE_LOCK_TIME();
	public void SP_PHS_PUSH_SVC_USER();
	
	// 추가
	public String SP_FN_KTIS_CLOSE(String param);
	
	public void SP_IF_GET_LOST_NO();
	public void SP_FN_SENDPLAN_UPDATE_0KI();
}
