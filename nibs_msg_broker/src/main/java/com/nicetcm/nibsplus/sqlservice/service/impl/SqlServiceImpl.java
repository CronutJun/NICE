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
		sqlServiceMapper.sp_cm_fax();
	}

	@Override
	public void spCmMacRank(Logger logger) {
		sqlServiceMapper.sp_cm_mac_rank();
	}

	@Override
	public void spCmPersonnel(Logger logger) {
		sqlServiceMapper.sp_cm_personnel();
	}

	@Override
	public void spCmUpdatePlanFocus(Logger logger) {
		sqlServiceMapper.sp_cm_update_plan_focus();
	}

	@Override
	public void spCtCheck0elAlert(Logger logger) {
		sqlServiceMapper.sp_ct_check_0EL_alert();
	}

	@Override
	public void spCtCheckAlert039(Logger logger) {
		sqlServiceMapper.sp_ct_check_alert_039();
	}

	@Override
	public void spCtCheckAlertNhFile(Logger logger) {
		sqlServiceMapper.sp_ct_check_alert_nh_file();
	}

	@Override
	public void spCtCheckAlert(Logger logger) {
		sqlServiceMapper.sp_ct_check_alert();
	}

	@Override
	public void spCtDeleteDay(Logger logger) {
		sqlServiceMapper.sp_ct_delete_day();
	}

	@Override
	public void spCtReportDay2007(Logger logger) {
		sqlServiceMapper.sp_ct_report_day_2007_1();
		sqlServiceMapper.sp_ct_report_day_2007_2();
	}

	@Override
	public void spFnBscenter(Logger logger) {
		sqlServiceMapper.sp_fn_bscenter();
	}

	@Override
	public void spFnCashflowEndamt(Logger logger) {
		sqlServiceMapper.sp_fn_cashflow_endamt();
	}

	@Override
	public void spFnCenter(Logger logger) {
		sqlServiceMapper.sp_fn_center();
	}

	@Override
	public void spFnCloseSendWc(Logger logger) {
		sqlServiceMapper.sp_fn_close_send_wc();
	}

	@Override
	public void spFnCtcenter(Logger logger) {
		sqlServiceMapper.sp_fn_ctcenter();
	}

	@Override
	public void spFnDayCloseKtis(Logger logger) {
		sqlServiceMapper.sp_fn_day_close_ktis();
	}

	@Override
	public void spFnDealForecast(Logger logger) {
		sqlServiceMapper.sp_fn_deal_forecast();
	}

	@Override
	public void spFnDealcount(Logger logger) {
		sqlServiceMapper.sp_fn_dealcount();
	}

	@Override
	public void spFnDgbcenter(Logger logger) {
		sqlServiceMapper.sp_fn_dgbcenter();
	}

	@Override
	public void spFnElandAmt(Logger logger) {
		sqlServiceMapper.sp_fn_eland_amt();
	}

	@Override
	public void spFnErrTrade(Logger logger) {
		sqlServiceMapper.sp_fn_err_trade();
	}

	@Override
	public void spFnEtcMsum(Logger logger) {
		sqlServiceMapper.sp_fn_etc_msum_1();
		sqlServiceMapper.sp_fn_etc_msum_2();
		sqlServiceMapper.sp_fn_etc_msum_3();
	}

	@Override
	public void spFnForcastAddsendCenter(Logger logger) {
		sqlServiceMapper.sp_fn_forcast_addsend_center();
	}

	@Override
	public void spFnForcastDataDsumAtm(Logger logger) {
		sqlServiceMapper.sp_fn_forcast_data_dsum_atm();
	}

	@Override
	public void spFnForcastDataDsum(Logger logger) {
		sqlServiceMapper.sp_fn_forcast_data_dsum();
	}

	@Override
	public void spFnHncenter(Logger logger) {
		sqlServiceMapper.sp_fn_hncenter();
	}

	@Override
	public void spFnImsiJan(Logger logger) {
		sqlServiceMapper.sp_fn_imsi_jan();
	}

	@Override
	public void spFnJjcenter(Logger logger) {
		sqlServiceMapper.sp_fn_jjcenter();
	}

	@Override
	public void spFnKbcenter(Logger logger) {
		sqlServiceMapper.sp_fn_kbcenter();
	}

	@Override
	public void spFnKebcenter(Logger logger) {
		sqlServiceMapper.sp_fn_kebcenter();
	}

	@Override
	public void spFnKncenter(Logger logger) {
		sqlServiceMapper.sp_fn_kncenter();
	}

	@Override
	public void spFnKtiscenter(Logger logger) {
		sqlServiceMapper.sp_fn_ktiscenter();
	}

	@Override
	public void spFnKucenter(Logger logger) {
		sqlServiceMapper.sp_fn_kucenter();
	}

	@Override
	public void spFnLargemonth(Logger logger) {
		sqlServiceMapper.sp_fn_largemonth_1();
		sqlServiceMapper.sp_fn_largemonth_2();
	}

	@Override
	public void spFnMacStockAmt(Logger logger) {
		sqlServiceMapper.sp_fn_mac_stock_amt();
	}

	@Override
	public void spFnMaccount(Logger logger) {
		sqlServiceMapper.sp_fn_maccount();
	}

	@Override
	public void spFnMan(Logger logger) {
		sqlServiceMapper.sp_fn_man_1();
		sqlServiceMapper.sp_fn_man_2();
	}

	@Override
	public void spFnNhcenter(Logger logger) {
		sqlServiceMapper.sp_fn_nhcenter();
	}

	@Override
	public void spFnNiceDealByday(Logger logger) {
		sqlServiceMapper.sp_fn_nice_deal_byday();
	}

	@Override
	public void spFnNiceDealByhour(Logger logger) {
		sqlServiceMapper.sp_fn_nice_deal_byhour_1();
		sqlServiceMapper.sp_fn_nice_deal_byhour_2();
	}

	@Override
	public void spFnNiceDsumBc(Logger logger) {
		sqlServiceMapper.sp_fn_nice_dsum_bc_1();
		sqlServiceMapper.sp_fn_nice_dsum_bc_2();
		sqlServiceMapper.sp_fn_nice_dsum_bc_3();
		sqlServiceMapper.sp_fn_nice_dsum_bc_4();
		sqlServiceMapper.sp_fn_nice_dsum_bc_5();
		sqlServiceMapper.sp_fn_nice_dsum_bc_6();
		sqlServiceMapper.sp_fn_nice_dsum_bc_7();
	}

	@Override
	public void spFnNiceDsum(Logger logger) {
		sqlServiceMapper.sp_fn_nice_dsum();
	}

	@Override
	public void spFnNiceEndamt(Logger logger) {
		sqlServiceMapper.sp_fn_nice_endamt();
	}

	@Override
	public void spFnNiceEveryInsert(Logger logger) {
		sqlServiceMapper.sp_fn_nice_every_insert();
	}

	@Override
	public void spFnNiceTicketCnt(Logger logger) {
		sqlServiceMapper.sp_fn_nice_ticket_cnt();
	}

	@Override
	public void spFnNiceTranGiftDsum(Logger logger) {
		sqlServiceMapper.sp_fn_nice_tran_gift_dsum();
	}

	@Override
	public void spFnNiceTranTotal(Logger logger) {
		sqlServiceMapper.sp_fn_nice_tran_total();
	}

	@Override
	public void spFnNotcloseCancel(Logger logger) {
		sqlServiceMapper.sp_fn_notclose_cancel();
	}

	@Override
	public void spFnNotendNice(Logger logger) {
		sqlServiceMapper.sp_fn_notend_nice();
	}

	@Override
	public void spFnOfficeclose(Logger logger) {
		sqlServiceMapper.sp_fn_officeclose();
	}

	@Override
	public void spFnOnlycashdatamacclose(Logger logger) {
		sqlServiceMapper.sp_fn_onlycashdatamacclose();
	}

	@Override
	public void spFnOperfundssh(Logger logger) {
		sqlServiceMapper.sp_fn_operfundssh();
	}

	@Override
	public void spFnRecvAmtBs(Logger logger) {
		sqlServiceMapper.sp_fn_recv_amt_bs();
	}

	@Override
	public void spFnSendplanConfirmBs(Logger logger) {
		sqlServiceMapper.sp_fn_sendplan_confirm_bs();
	}

	@Override
	public void spFnSendplanConfirmSh(Logger logger) {
		sqlServiceMapper.sp_fn_sendplan_confirm_sh();
	}

	@Override
	public void spFnSendplanConfirm(Logger logger) {
		sqlServiceMapper.sp_fn_sendplan_confirm();
	}

	@Override
	public void spFnSendplanReform(Logger logger) {
		sqlServiceMapper.sp_fn_sendplan_reform();
	}

	@Override
	public void spFnSendplanUploadSh(Logger logger) {
		sqlServiceMapper.sp_fn_sendplan_upload_sh();
	}

	@Override
	public void spFnServicesend(Logger logger) {
		sqlServiceMapper.sp_fn_servicesend();
	}

	@Override
	public void spFnShOpcenter(Logger logger) {
		sqlServiceMapper.sp_fn_sh_opcenter();
	}

	@Override
	public void spFnShcenter(Logger logger) {
		sqlServiceMapper.sp_fn_shcenter();
	}

	@Override
	public void spFnSitecount(Logger logger) {
		sqlServiceMapper.sp_fn_sitecount();
	}

	@Override
	public void spFnSmcenter(Logger logger) {
		sqlServiceMapper.sp_fn_smcenter();
	}

	@Override
	public void spFnWccenter(Logger logger) {
		sqlServiceMapper.sp_fn_wccenter();
	}

	@Override
	public void spFnWrVanDemand(Logger logger) {
		sqlServiceMapper.sp_fn_wr_van_demand();
	}

	@Override
	public void spFnWrcenter(Logger logger) {
		sqlServiceMapper.sp_fn_wrcenter();
	}

	@Override
	public void spIfSendsmscashlack(Logger logger) {
		sqlServiceMapper.sp_if_SendSMSCashLack_1();
		sqlServiceMapper.sp_if_SendSMSCashLack_2();
	}

	@Override
	public void spJobNiceShortCashConfig(Logger logger) {
		sqlServiceMapper.sp_job_nice_short_cash_config();
	}

	@Override
	public void spPhsPushMessages(Logger logger) {
		sqlServiceMapper.sp_phs_push_messages();
	}

	@Override
	public void spSuMultireportAll(Logger logger) {
		sqlServiceMapper.sp_su_multireport_all_1();
		sqlServiceMapper.sp_su_multireport_all_2();
		sqlServiceMapper.sp_su_multireport_all_3();
		sqlServiceMapper.sp_su_multireport_all_4();
		sqlServiceMapper.sp_su_multireport_all_5();
		sqlServiceMapper.sp_su_multireport_all_6();
	}

	@Override
	public void spUpdateSite(Logger logger) {
		sqlServiceMapper.sp_update_site();
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
