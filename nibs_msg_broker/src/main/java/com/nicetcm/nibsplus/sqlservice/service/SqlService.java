/** 
 * com.nicetcm.nibsplus.sqlservice.service.SqlService
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 11. 13.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.sqlservice.service;

import org.apache.log4j.Logger;

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
	public void SP_CM_FAX(Logger logger);
	public void SP_CM_MAC_RANK(Logger logger);
	public void SP_CM_PERSONNEL(Logger logger);
	public void SP_CM_UPDATE_PLAN_FOCUS(Logger logger);
	public void SP_CT_CHECK_0EL_ALERT(Logger logger);
	public void SP_CT_CHECK_ALERT_039(Logger logger);
	public void SP_CT_CHECK_ALERT_NH_FILE(Logger logger);
	public void SP_CT_CHECK_ALERT(Logger logger);
	public void SP_CT_DELETE_DAY(Logger logger);
	public void SP_CT_REPORT_DAY_2012(Logger logger);
	public void SP_FN_BSCENTER(Logger logger);
	public void SP_FN_CASHFLOW_ENDAMT(Logger logger);
	public void SP_FN_CENTER(Logger logger);
	public void SP_FN_CLOSE_SEND_WC(Logger logger);
	public void SP_FN_CTCENTER(Logger logger);
	public void SP_FN_DAY_CLOSE_KTIS(Logger logger);
	public void SP_FN_DEAL_FORECAST(Logger logger);
	public void SP_FN_DEALCOUNT(Logger logger);
	public void SP_FN_DGBCENTER(Logger logger);
	public void SP_FN_ELAND_AMT(Logger logger);
	public void SP_FN_ERR_TRADE(Logger logger);
	public void SP_FN_ETC_MSUM(Logger logger);
	public void SP_FN_FORCAST_ADDSEND_CENTER(Logger logger);
	public void SP_FN_FORCAST_DATA_DSUM_ATM(Logger logger);
	public void SP_FN_HNCENTER(Logger logger);
	public void SP_FN_IMSI_JAN(Logger logger);
	public void SP_FN_JJCENTER(Logger logger);
	public void SP_FN_KBCENTER(Logger logger);
	public void SP_FN_KEBCENTER(Logger logger);
	public void SP_FN_KNCENTER(Logger logger);
	public void SP_FN_KTISCENTER(Logger logger);
	public void SP_FN_KUCENTER(Logger logger);
	public void SP_FN_LARGEMONTH(Logger logger);
	public void SP_FN_MAC_STOCK_AMT(Logger logger);
	public void SP_FN_MACCOUNT(Logger logger);
	public void SP_FN_MAN(Logger logger);
	public void SP_FN_NHCENTER(Logger logger);
	public void SP_FN_NICE_DEAL_BYDAY(Logger logger);
	public void SP_FN_NICE_DEAL_BYHOUR(Logger logger);
	public void SP_FN_NICE_DSUM(Logger logger);
	public void SP_FN_NICE_DSUM_BC(Logger logger);
	public void SP_FN_NICE_ENDAMT(Logger logger);
	public void SP_FN_NICE_EVERY_INSERT(Logger logger);
	public void SP_FN_NICE_TICKET_CNT(Logger logger);
	public void SP_FN_NICE_TRAN_GIFT_DSUM(Logger logger);
	public void SP_FN_NICE_TRAN_TOTAL(Logger logger);
	public void SP_FN_NOTCLOSE_CANCEL(Logger logger);
	public void SP_FN_NOTEND_NICE(Logger logger);
	public void SP_FN_OFFICECLOSE(Logger logger);
	public void SP_FN_ONLYCASHDATAMACCLOSE(Logger logger);
	public void SP_FN_OPERFUNDSSH(Logger logger);
	public void SP_FN_RECV_AMT_BS(Logger logger);
	public void SP_FN_SENDPLAN_CONFIRM_BS(Logger logger);
	public void SP_FN_SENDPLAN_CONFIRM_SH(Logger logger);
	public void SP_FN_SENDPLAN_CONFIRM(Logger logger);
	public void SP_FN_SENDPLAN_REFORM(Logger logger);
	public void SP_FN_SENDPLAN_UPLOAD_SH(Logger logger);
	public void SP_FN_SERVICESEND(Logger logger);
	public void SP_FN_SH_OPCENTER(Logger logger);
	public void SP_FN_SHCENTER(Logger logger);
	public void SP_FN_SITECOUNT(Logger logger);
	public void SP_FN_SMCENTER(Logger logger);
	public void SP_FN_WCCENTER(Logger logger);
	public void SP_FN_WR_VAN_DEMAND(Logger logger);
	public void SP_FN_WRCENTER(Logger logger);
	public void SP_IF_SENDSMSCASHLACK(Logger logger);
	public void SP_JOB_NICE_SHORT_CASH_CONFIG(Logger logger);
	public void SP_PHS_PUSH_MESSAGES(Logger logger);
	public void SP_SU_MULTIREPORT_ALL(Logger logger);
	public void SP_UPDATE_SITE(Logger logger);
	public void SP_FN_NICE_TS_INSERT(Logger logger);
	public void SP_CM_PASSWORD_CHANGE(Logger logger);
	public void SP_CT_MONTH_USE_STATUS_003(Logger logger);
	public void T_CT_ERROR_MNG_003_BRAND_TEMP(Logger logger);
	public void SP_BATCH_ERROR_ETC_DELETE(Logger logger);
	
	// OracleJob 추가
	public void SP_CHECK_PHOTO_CARRY(Logger logger);
	public void SP_CM_CASH_WC_INSERT(Logger logger);
	public void SP_CM_ELECSIGN_RETIRE(Logger logger);
	public void SP_CM_PKG_DAY(Logger logger);
	public void SP_CT_LINEFAULT_STATUS(Logger logger);
	public void SP_CT_LOCK_HISTOY_BACKUP(Logger logger);
	public void SP_CT_LOCKSERVER_VERIFY(Logger logger);
	public void SP_CT_T_CT_TRADE_CONFIRM_INIT(Logger logger);
	public void SP_FN_ADJUSTMENT_CARRY(Logger logger);
	public void SP_FN_CREATE_NOTEND_MEMBER(Logger logger);
	public void SP_FN_EMART_TICKET(Logger logger);
	public void SP_FN_MAKE_NODEAL_RESULT_HIS(Logger logger);
	public void SP_FN_NOTEND_FEE(Logger logger);
	public void SP_FN_ONNURI_DSUM(Logger logger);
	public void SP_FN_PRESAFE_HISTORY(Logger logger);
	public void SP_IF_SENDSMSKTISERROR(Logger logger);
	public void SP_JOB_ARRIVAL_EST_TIME(Logger logger);
	public void SP_JOB_AUTOSEND_PUSH(Logger logger);
	public void SP_JOB_BOARD_LIST_NOTICE(Logger logger);
	public void SP_JOB_FN_SENDPLAN_UPDATE_0KI(Logger logger);
	public void SP_JOB_SCHEDULE_MNG_NOTICE(Logger logger);
	public void SP_JOB_T_CT_AS_UNFINISH(Logger logger);
	public void SP_JOB_T_CT_AUTOSEND_MAC(Logger logger);
	public void SP_JOB_T_CT_DELAY_MONITORING(Logger logger);
	public void SP_JOB_T_CT_ERROR_MNG(Logger logger);
	public void SP_JOB_T_CT_ERROR_MNG_GUARD(Logger logger);
	public void SP_JOB_T_CT_ERROR_OPEN_CHECK(Logger logger);
	public void SP_JOB_T_CV_POSITION(Logger logger);
	public void SP_JOB_T_EMART_MONITORING(Logger logger);
	public void SP_JOB_T_FN_SENDPLAN_SCHEDULE(Logger logger);
	public void SP_JOB_T_SHORT_CASH_MNG(Logger logger);
	public void SP_RC_INFO_DELETE(Logger logger);
	public void SP_FN_FORCAST_DATA_DSUM_BASE(Logger logger);
	
	// 누락분
	public void SP_FN_FORCAST_DATA_DSUM(Logger logger);
	public void SP_FN_FORCAST_INOUT_CENTER(Logger logger);
	public void SP_CT_CHECK_ALERT_EMARTPOS(Logger logger);
	public void SP_FN_DAY_CLOSE_KIOSK(Logger logger);
	public void SP_FN_KB_VAN_DEMAND(Logger logger);
	public void SP_FN_VOUCHER_MACSTOCK(Logger logger);
	public void SP_FN_VOUCHER_OFFICESTOCK(Logger logger);
	public void SP_INSERT_NICE_LOCK_TIME(Logger logger);
	public void SP_PHS_PUSH_SVC_USER(Logger logger);
	
	// 추가
	public String SP_FN_KTIS_CLOSE(Logger logger, String param);
	
	public void SP_IF_GET_LOST_NO(Logger logger);
}
