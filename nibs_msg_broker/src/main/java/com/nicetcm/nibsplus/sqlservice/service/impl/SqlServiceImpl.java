package com.nicetcm.nibsplus.sqlservice.service.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.scheduler.main.NibsScheduleExecuter;
import com.nicetcm.nibsplus.sqlservice.dao.SqlServiceMapper;
import com.nicetcm.nibsplus.sqlservice.service.SqlService;

/**
 * @author mulpats@gmail.com
 * @version 1.0
 * @see
 */
@Service
public class SqlServiceImpl implements SqlService {

	@Autowired
	private SqlServiceMapper sqlServiceMapper;

	@Override
	public void spCmFax(Logger logger) {
		sqlServiceMapper.executeProcedure("PKG_CM_FAXMASTER.SP_BATCHPROCESS(TO_CHAR(SYSDATE,'YYYYMMDD'),'')");
	}

	@Override
	public void spCmMacRank(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_CM_MAC_RANK(null)");
	}

	@Override
	public void spCmPersonnel(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_cm_personnel(null, null, null)");
	}

	@Override
	public void spCmUpdatePlanFocus(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_CM_UPDATE_PLAN_FOCUS(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
	}

	@Override
	public void spCtCheck0elAlert(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_ct_check_0EL_alert('0EL', null)");
	}

	@Override
	public void spCtCheckAlert039(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_ct_check_alert(null)");
	}

	@Override
	public void spCtCheckAlertNhFile(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_ct_check_alert_039(null)");
	}

	@Override
	public void spCtCheckAlert(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_ct_check_alert_nh_file(null)");
	}

	@Override
	public void spCtDeleteDay(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_CT_DELETE_DAY(null)");
	}

	@Override
	public void spCtReportDay2007(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_ct_report_day_2012(null, null)");
		sqlServiceMapper.executeProcedure("sp_ct_reportoper_modify(null)");
	}

	@Override
	public void spFnBscenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_BsAtms_DemandManager.sp_BsAtms_batchProcess(null, null, null)");
	}

	@Override
	public void spFnCashflowEndamt(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_cashflow_endamt(TO_CHAR(SYSDATE,'YYYYMMDD'))");
	}

	@Override
	public void spFnCenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_DemandManager.sp_batchProcess(null, null, null)");
	}

	@Override
	public void spFnCloseSendWc(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_close_send_wc(null)");
	}

	@Override
	public void spFnCtcenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_CtAtms_DemandManager.sp_CtAtms_batchProcess(null, null, null)");
	}

	@Override
	public void spFnDayCloseKtis(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_DAY_CLOSE_KTIS(null, null)");
	}

	@Override
	public void spFnDealForecast(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_DEAL_FORECAST(TO_CHAR(SYSDATE,'YYYYMMDD'),null)");
	}

	@Override
	public void spFnDealcount(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_dealCount(null)");
	}

	@Override
	public void spFnDgbcenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_DGBAtms_DemandManager.sp_DGBAtms_batchProcess(null, null, null)");
	}

	@Override
	public void spFnElandAmt(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_eland_amt(null)");
	}

	@Override
	public void spFnErrTrade(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_err_trade(null)");
	}

	@Override
	public void spFnEtcMsum(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_lease_msum(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
		sqlServiceMapper.executeProcedure("sp_fn_cms_close_msum(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
		sqlServiceMapper.executeProcedure("sp_fn_eis_msum(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
	}

	@Override
	public void spFnForcastAddsendCenter(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_forcast_addsend_center(null)");
	}

	@Override
	public void spFnForcastDataDsumAtm(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_forcast_data_dsum(TO_CHAR(SYSDATE,'YYYYMMDD'))");
	}

	@Override
	public void spFnForcastDataDsum(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_forcast_data_dsum_atm(TO_CHAR(SYSDATE,'YYYYMMDD'))");
	}

	@Override
	public void spFnHncenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_HnAtms_DemandManager.sp_HnAtms_batchProcess(null, null, null)");
	}

	@Override
	public void spFnImsiJan(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_IMSI_JAN(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
	}

	@Override
	public void spFnJjcenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_JjAtms_DemandManager.sp_JjAtms_batchProcess(null, null, null)");
	}

	@Override
	public void spFnKbcenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_KbAtms_DemandManager.sp_KbAtms_batchProcess(null, null, null)");
	}

	@Override
	public void spFnKebcenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_KebAtms_DemandManager.sp_KebAtms_batchProcess(null, null, null)");
	}

	@Override
	public void spFnKncenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_KNAtms_DemandManager.sp_KNAtms_batchProcess(null, null, null)");
	}

	@Override
	public void spFnKtiscenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_KTISAtms_DemandManager.sp_KTISAtms_batchProcess(null, null, null)");
	}

	@Override
	public void spFnKucenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_KuAtms_DemandManager.sp_KuAtms_batchProcess(null, null, null)");
	}

	@Override
	public void spFnLargemonth(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_LARGEMONTH()");
		sqlServiceMapper.executeProcedure("sp_fn_risk_month(TO_CHAR(SYSDATE-1,'YYYYMM'))");
	}

	@Override
	public void spFnMacStockAmt(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_MAC_STOCK_AMT(TO_CHAR(SYSDATE-1,'YYYYMMDD'),null)");
	}

	@Override
	public void spFnMaccount(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_MACCOUNT(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
	}

	@Override
	public void spFnMan(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_MAN(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
		sqlServiceMapper.executeProcedure("sp_fn_risk_day(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
	}

	@Override
	public void spFnNhcenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_NhAtms_DemandManager.sp_NhAtms_batchProcess(null, null, null)}");
	}

	@Override
	public void spFnNiceDealByday(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_nice_deal_byday(null)");
	}

	@Override
	public void spFnNiceDealByhour(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_nice_deal_byhour(null)");
		sqlServiceMapper.executeProcedure("sp_fn_nice_deal_byhour_amt(null)");
	}

	@Override
	public void spFnNiceDsumBc(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_update_tran_nh(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
		sqlServiceMapper.executeProcedure("sp_fn_nice_dsum_demand(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
		sqlServiceMapper.executeProcedure("SP_FN_NICE_DSUM(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
		sqlServiceMapper.executeProcedure("SP_FN_MAC_DEALTYPE_DSUM(TO_CHAR(SYSDATE-1,'YYYYMMDD'),null)");
		sqlServiceMapper.executeProcedure("SP_FN_FORECAST_BASE_NICE(TO_CHAR(SYSDATE-1,'YYYYMMDD'),null)");
		sqlServiceMapper.executeProcedure("SP_FN_FORECAST_BASE_CLOSE(TO_CHAR(SYSDATE-1,'YYYYMMDD'),null)");
		sqlServiceMapper.executeProcedure("SP_FN_CARRY_FEE_DSUM(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
	}

	@Override
	public void spFnNiceDsum(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_nice_dsum_0bc(null)");
	}

	@Override
	public void spFnNiceEndamt(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_NICE_ENDAMT(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
	}

	@Override
	public void spFnNiceEveryInsert(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_nice_every_insert(null)");
	}

	@Override
	public void spFnNiceTicketCnt(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_nice_ticket_cnt(null)");
	}

	@Override
	public void spFnNiceTranGiftDsum(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_NICE_TRAN_GIFT_DSUM(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
	}

	@Override
	public void spFnNiceTranTotal(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_nice_tran_total(null, null)");
	}

	@Override
	public void spFnNotcloseCancel(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_notclose_cancel(null)");
	}

	@Override
	public void spFnNotendNice(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_NOTEND_NICE(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
	}

	@Override
	public void spFnOfficeclose(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_OFFICECLOSE()");
	}

	@Override
	public void spFnOnlycashdatamacclose(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_onlyCashDataMacClose()");
	}

	@Override
	public void spFnOperfundssh(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_OperfundsSh(null)");
	}

	@Override
	public void spFnRecvAmtBs(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_recv_amt_bs(TO_CHAR(SYSDATE,'YYYYMMDD'))");
	}

	@Override
	public void spFnSendplanConfirmBs(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_SENDPLAN_CONFIRM(TO_CHAR(SYSDATE,'YYYYMMDD'),null)");
	}

	@Override
	public void spFnSendplanConfirmSh(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_SENDPLAN_CONFIRM_BS(TO_CHAR(SYSDATE,'YYYYMMDD'))");
	}

	@Override
	public void spFnSendplanConfirm(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_sendplan_confirm_sh(TO_CHAR(SYSDATE,'YYYYMMDD'),null)");
	}

	@Override
	public void spFnSendplanReform(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_sendplan_reform(TO_CHAR(SYSDATE,'YYYYMMDD'))");
	}

	@Override
	public void spFnSendplanUploadSh(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_sendplan_upload_sh(TO_CHAR(SYSDATE,'YYYYMMDD'),null)");
	}

	@Override
	public void spFnServicesend(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_SERVICESEND(TO_CHAR(SYSDATE-1,'YYYYMMDD'))");
	}

	@Override
	public void spFnShOpcenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_ShAtms_OpDemandManager.sp_ShAtms_batchProcess(null)");
	}

	@Override
	public void spFnShcenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_ShAtms_DemandManager.sp_ShAtms_batchProcess(null)");
	}

	@Override
	public void spFnSitecount(Logger logger) {
		sqlServiceMapper.executeProcedure("SP_FN_SITECOUNT(TO_CHAR(SYSDATE-1,'YYYYMMDD'))}");
	}

	@Override
	public void spFnSmcenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_SmAtms_DemandManager.sp_SMAtms_batchProcess(null)");
	}

	@Override
	public void spFnWccenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_WCAtms_DemandManager.sp_WCAtms_batchProcess(null)");
	}

	@Override
	public void spFnWrVanDemand(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_fn_wr_van_demand(null)");
	}

	@Override
	public void spFnWrcenter(Logger logger) {
		sqlServiceMapper.executeProcedure("pkg_fn_WrAtms_DemandManager.sp_WrAtms_batchProcess(null, null, null)");
	}

	@Override
	public void spIfSendsmscashlack(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_if_SendSMSCashLack('088')");
		sqlServiceMapper.executeProcedure("sp_if_SendSMSCashLack('004')");
	}

	@Override
	public void spJobNiceShortCashConfig(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_job_nice_short_cash_config()");
	}

	@Override
	public void spPhsPushMessages(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_phs_push_messages(null)");
	}

	@Override
	public void spSuMultireportAll(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_su_multireport_day_01(null)");
		sqlServiceMapper.executeProcedure("sp_su_multireport_day_02(null)");
		sqlServiceMapper.executeProcedure("sp_su_multireport_day_03(null)");
		sqlServiceMapper.executeProcedure("sp_su_multireport_day_04(null)");
		sqlServiceMapper.executeProcedure("sp_su_multireport_day_05(null)");
		sqlServiceMapper.executeProcedure("sp_su_multireport_day_06(null)");
	}

	@Override
	public void spUpdateSite(Logger logger) {
		sqlServiceMapper.executeProcedure("sp_update_site()");
	}

	@Override
	public void spCmPasswordChange(Logger logger) {
    	sqlServiceMapper.sp_cm_password_change_1();
    	sqlServiceMapper.sp_cm_password_change_2();
    	sqlServiceMapper.sp_cm_password_change_3();
    	sqlServiceMapper.sp_cm_password_change_4();
    	sqlServiceMapper.sp_cm_password_change_5();
    	sqlServiceMapper.sp_cm_password_change_6();
	}

	@Override
	public void spCtMonthUseStatus003(Logger logger) {
		sqlServiceMapper.sp_ct_month_use_status_003_1();
		sqlServiceMapper.sp_ct_month_use_status_003_2();
		
    	new NibsScheduleExecuter(new String[]{"ManualOrgSendService", "MONTH_USE_STATUS", "003"});
	}

	@Override
	public void tCtErrorMng003BrandTemp(Logger logger) {
		sqlServiceMapper.t_ct_error_mng_003_brand_temp();
	}
}
