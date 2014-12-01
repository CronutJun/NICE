package com.nicetcm.nibsplus.sqlservice.service.impl;

import java.util.HashMap;

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
		try {
			sqlServiceMapper.sp_cm_fax();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spCmMacRank(Logger logger) {
		try {
			sqlServiceMapper.sp_cm_mac_rank();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spCmPersonnel(Logger logger) {
		try {
			sqlServiceMapper.sp_cm_personnel();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spCmUpdatePlanFocus(Logger logger) {
		try {
			sqlServiceMapper.sp_cm_update_plan_focus();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spCtCheck0elAlert(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_check_0EL_alert();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spCtCheckAlert039(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_check_alert_039();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spCtCheckAlertNhFile(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_check_alert_nh_file();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spCtCheckAlert(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_check_alert();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spCtDeleteDay(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_delete_day();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spCtReportDay2012(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_report_day_2012_1();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
		sqlServiceMapper.sp_ct_report_day_2012_2();
	}

	@Override
	public void spFnBscenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_bscenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnCashflowEndamt(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_cashflow_endamt();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnCenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_center();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnCloseSendWc(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_close_send_wc();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnCtcenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_ctcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnDayCloseKtis(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_day_close_ktis();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnDealForecast(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_deal_forecast(new HashMap<String, String>());
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnDealcount(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_dealcount();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnDgbcenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_dgbcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnElandAmt(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_eland_amt();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnErrTrade(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_err_trade();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnEtcMsum(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_etc_msum_1();
			sqlServiceMapper.sp_fn_etc_msum_2();
			sqlServiceMapper.sp_fn_etc_msum_3();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnForcastAddsendCenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_forcast_addsend_center();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnForcastDataDsumAtm(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_forcast_data_dsum_atm();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnHncenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_hncenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnImsiJan(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_imsi_jan();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnJjcenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_jjcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnKbcenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_kbcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnKebcenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_kebcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnKncenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_kncenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnKtiscenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_ktiscenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnKucenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_kucenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnLargemonth(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_largemonth_1();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
		sqlServiceMapper.sp_fn_largemonth_2();
	}

	@Override
	public void spFnMacStockAmt(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_mac_stock_amt(new HashMap<String, String>());
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnMaccount(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_maccount();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnMan(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_man_1();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
		sqlServiceMapper.sp_fn_man_2();
	}

	@Override
	public void spFnNhcenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nhcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnNiceDealByday(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_deal_byday();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnNiceDealByhour(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_deal_byhour_1();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
		sqlServiceMapper.sp_fn_nice_deal_byhour_2();
	}

	@Override
	public void spFnNiceDsum(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_dsum_1();
			sqlServiceMapper.sp_fn_nice_dsum_2();
			sqlServiceMapper.sp_fn_nice_dsum_3();
			sqlServiceMapper.sp_fn_nice_dsum_4(new HashMap<String, String>());
			sqlServiceMapper.sp_fn_nice_dsum_5(new HashMap<String, String>());
			sqlServiceMapper.sp_fn_nice_dsum_6(new HashMap<String, String>());
			sqlServiceMapper.sp_fn_nice_dsum_7();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnNiceDsumBc(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_dsum_0bc();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnNiceEndamt(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_endamt();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnNiceEveryInsert(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_every_insert();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnNiceTicketCnt(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_ticket_cnt();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnNiceTranGiftDsum(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_tran_gift_dsum();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnNiceTranTotal(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_tran_total();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnNotcloseCancel(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_notclose_cancel();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnNotendNice(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_notend_nice();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnOfficeclose(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_officeclose();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnOnlycashdatamacclose(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_onlycashdatamacclose();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnOperfundssh(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_operfundssh();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnRecvAmtBs(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_recv_amt_bs();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnSendplanConfirmBs(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_sendplan_confirm_bs();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnSendplanConfirmSh(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_sendplan_confirm_sh(new HashMap<String, String>());
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnSendplanConfirm(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_sendplan_confirm(new HashMap<String, String>());
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnSendplanReform(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_sendplan_reform();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnSendplanUploadSh(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_sendplan_upload_sh(new HashMap<String, String>());
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnServicesend(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_servicesend();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnShOpcenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_sh_opcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnShcenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_shcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnSitecount(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_sitecount();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnSmcenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_smcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnWccenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_wccenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnWrVanDemand(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_wr_van_demand();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnWrcenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_wrcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spIfSendsmscashlack(Logger logger) {
		try {
			sqlServiceMapper.sp_if_SendSMSCashLack_1();
			sqlServiceMapper.sp_if_SendSMSCashLack_2();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spJobNiceShortCashConfig(Logger logger) {
		try {
			sqlServiceMapper.sp_job_nice_short_cash_config();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spPhsPushMessages(Logger logger) {
		try {
			sqlServiceMapper.sp_phs_push_messages();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spSuMultireportAll(Logger logger) {
		try {
			sqlServiceMapper.sp_su_multireport_all_1();
			sqlServiceMapper.sp_su_multireport_all_2();
			sqlServiceMapper.sp_su_multireport_all_3();
			sqlServiceMapper.sp_su_multireport_all_4();
			sqlServiceMapper.sp_su_multireport_all_5();
			sqlServiceMapper.sp_su_multireport_all_6();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spUpdateSite(Logger logger) {
		try {
			sqlServiceMapper.sp_update_site();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spCmPasswordChange(Logger logger) {
		try {
    		sqlServiceMapper.sp_cm_password_change_1();
    		sqlServiceMapper.sp_cm_password_change_2();
    		sqlServiceMapper.sp_cm_password_change_3();
    		sqlServiceMapper.sp_cm_password_change_4();
    		sqlServiceMapper.sp_cm_password_change_5();
    		sqlServiceMapper.sp_cm_password_change_6();
    	} catch(RuntimeException e) {
    		logger.error(e.getMessage());
    		throw e;
    	}
	}

	@Override
	public void spCtMonthUseStatus003(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_month_use_status_003_1();
			sqlServiceMapper.sp_ct_month_use_status_003_2();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
		
    	new NibsScheduleExecuter(new String[]{"ManualOrgSendService", "MONTH_USE_STATUS", "003"});
	}

	@Override
	public void tCtErrorMng003BrandTemp(Logger logger) {
		try {
			sqlServiceMapper.t_ct_error_mng_003_brand_temp();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	// OracleJob 추가
	@Override
	public void spBatchErrorEtcDelete(Logger logger) {
		try {
			sqlServiceMapper.sp_batch_error_etc_delete();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spCheckPhotoCarry(Logger logger) {
		try {
			sqlServiceMapper.sp_check_photo_carry();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spCmCashWcInsert(Logger logger) {
		try {
			sqlServiceMapper.sp_cm_cash_wc_insert();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spCmElecsignRetire(Logger logger) {
		try {
			sqlServiceMapper.sp_cm_elecsign_retire();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spCmPkgDay(Logger logger) {
		try {
			sqlServiceMapper.sp_cm_pkg_day();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spCtLinefaultStatus(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_linefault_status();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spCtLockHistoyBackup(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_lock_histoy_backup();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spCtLockserverVerify(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_lockserver_verify();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spCtTCtTradeConfirmInit(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_t_ct_trade_confirm_init();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spFnAdjustmentCarry(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_adjustment_carry();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spFnCreateNotendMember(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_create_notend_member();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spFnEmartTicket(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_emart_ticket();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spFnMakeNodealResultHis(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_make_nodeal_result_his();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spFnNotendFee(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_notend_fee();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spFnOnnuriDsum(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_onnuri_dsum();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spFnPresafeHistory(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_presafe_history();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spIfSendsmsktiserror(Logger logger) {
		try {
			sqlServiceMapper.sp_if_sendsmsktiserror();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobArrivalEstTime(Logger logger) {
		try {
			sqlServiceMapper.sp_job_arrival_est_time();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobAutosendPush(Logger logger) {
		try {
			sqlServiceMapper.sp_job_autosend_push();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobBoardListNotice(Logger logger) {
		try {
			sqlServiceMapper.sp_job_board_list_notice();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobFnSendplanUpdate0ki(Logger logger) {
		try {
			sqlServiceMapper.sp_job_fn_sendplan_update_0ki();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobScheduleMngNotice(Logger logger) {
		try {
			sqlServiceMapper.sp_job_schedule_mng_notice();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobTCtAsUnfinish(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_ct_as_unfinish();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobTCtAutosendMac(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_ct_autosend_mac();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobTCtDelayMonitoring(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_ct_delay_monitoring();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobTCtErrorMng(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_ct_error_mng();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobTCtErrorMngGuard(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_ct_error_mng_guard();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobTCtErrorOpenCheck(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_ct_error_open_check();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobTCvPosition(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_cv_position();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobTEmartMonitoring(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_emart_monitoring();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobTFnSendplanSchedule(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_fn_sendplan_schedule();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spJobTShortCashMng(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_short_cash_mng();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void spRcInfoDelete(Logger logger) {
		try {
			sqlServiceMapper.sp_rc_info_delete();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	

	@Override
	public void spFnForcastDataDsumBase(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_forcast_data_dsum_base_1();
			sqlServiceMapper.sp_fn_forcast_data_dsum_base_2();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnForcastDataDsum(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_forcast_data_dsum();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnForcastInoutCenter(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_forcast_inout_center();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	


	@Override
	public void spCtCheckAlertEmartpos(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_check_alert_emartpos();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnDayCloseKiosk(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_day_close_kiosk();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnKbVanDemand(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_kb_van_demand();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnVoucherMacstock(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_voucher_macstock();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spFnVoucherOfficestock(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_voucher_officestock();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spInsertNiceLockTime(Logger logger) {
		try {
			sqlServiceMapper.sp_insert_nice_lock_time();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void spPhsPushSvcUser(Logger logger) {
		try {
			sqlServiceMapper.sp_job_tb_phs_push_svc_user();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

}
