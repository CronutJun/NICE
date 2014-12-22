package com.nicetcm.nibsplus.sqlservice.service.impl;

import java.util.HashMap;
import java.util.Map;

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
	public void SP_CM_FAX() {
		sqlServiceMapper.sp_cm_fax();
	}

	@Override
	public void SP_CM_MAC_RANK() {
		sqlServiceMapper.sp_cm_mac_rank();
	}

	@Override
	public void SP_CM_PERSONNEL() {
		sqlServiceMapper.sp_cm_personnel();
	}

	@Override
	public void SP_CM_UPDATE_PLAN_FOCUS() {
		sqlServiceMapper.sp_cm_update_plan_focus();
	}

	@Override
	public void SP_CT_CHECK_0EL_ALERT() {
		sqlServiceMapper.sp_ct_check_0EL_alert();
	}

	@Override
	public void SP_CT_CHECK_ALERT_039() {
		sqlServiceMapper.sp_ct_check_alert_039();
	}

	@Override
	public void SP_CT_CHECK_ALERT_NH_FILE() {
		sqlServiceMapper.sp_ct_check_alert_nh_file();
	}

	@Override
	public void SP_CT_CHECK_ALERT() {
		sqlServiceMapper.sp_ct_check_alert();
	}

	@Override
	public void SP_CT_DELETE_DAY() {
		sqlServiceMapper.sp_ct_delete_day();
	}

	@Override
	public void SP_CT_REPORT_DAY_2012() {
		sqlServiceMapper.sp_ct_report_day_2012_1();
		sqlServiceMapper.sp_ct_report_day_2012_2();
	}

	@Override
	public void SP_FN_BSCENTER() {
		sqlServiceMapper.sp_fn_bscenter();
	}

	@Override
	public void SP_FN_CASHFLOW_ENDAMT() {
		sqlServiceMapper.sp_fn_cashflow_endamt();
	}

	@Override
	public void SP_FN_CENTER() {
		sqlServiceMapper.sp_fn_center();
	}

	@Override
	public void SP_FN_CLOSE_SEND_WC() {
		sqlServiceMapper.sp_fn_close_send_wc();
	}

	@Override
	public void SP_FN_CTCENTER() {
		sqlServiceMapper.sp_fn_ctcenter();
	}

	@Override
	public void SP_FN_DAY_CLOSE_KTIS() {
		sqlServiceMapper.sp_fn_day_close_ktis();
	}

	@Override
	public void SP_FN_DEAL_FORECAST() {
		sqlServiceMapper.sp_fn_deal_forecast(new HashMap<String, String>());
	}

	@Override
	public void SP_FN_DEALCOUNT() {
		sqlServiceMapper.sp_fn_dealcount();
	}

	@Override
	public void SP_FN_DGBCENTER() {
		sqlServiceMapper.sp_fn_dgbcenter();
	}

	@Override
	public void SP_FN_ELAND_AMT() {
		sqlServiceMapper.sp_fn_eland_amt();
	}

	@Override
	public void SP_FN_ERR_TRADE() {
		sqlServiceMapper.sp_fn_err_trade();
	}

	@Override
	public void SP_FN_ETC_MSUM() {
		sqlServiceMapper.sp_fn_etc_msum_1();
		sqlServiceMapper.sp_fn_etc_msum_2();
		sqlServiceMapper.sp_fn_etc_msum_3();
	}

	@Override
	public void SP_FN_FORCAST_ADDSEND_CENTER() {
		sqlServiceMapper.sp_fn_forcast_addsend_center();
	}

	@Override
	public void SP_FN_FORCAST_DATA_DSUM_ATM() {
		sqlServiceMapper.sp_fn_forcast_data_dsum_atm();
	}

	@Override
	public void SP_FN_HNCENTER() {
		sqlServiceMapper.sp_fn_hncenter();
	}

	@Override
	public void SP_FN_IMSI_JAN() {
		sqlServiceMapper.sp_fn_imsi_jan();
	}

	@Override
	public void SP_FN_JJCENTER() {
		sqlServiceMapper.sp_fn_jjcenter();
	}

	@Override
	public void SP_FN_KBCENTER() {
		sqlServiceMapper.sp_fn_kbcenter();
	}

	@Override
	public void SP_FN_KEBCENTER() {
		sqlServiceMapper.sp_fn_kebcenter();
	}

	@Override
	public void SP_FN_KNCENTER() {
		sqlServiceMapper.sp_fn_kncenter();
	}

	@Override
	public void SP_FN_KTISCENTER() {
		sqlServiceMapper.sp_fn_ktiscenter();
	}

	@Override
	public void SP_FN_KUCENTER() {
		sqlServiceMapper.sp_fn_kucenter();
	}

	@Override
	public void SP_FN_LARGEMONTH() {
		sqlServiceMapper.sp_fn_largemonth_1();
		sqlServiceMapper.sp_fn_largemonth_2();
	}

	@Override
	public void SP_FN_MAC_STOCK_AMT() {
		sqlServiceMapper.sp_fn_mac_stock_amt(new HashMap<String, String>());
	}

	@Override
	public void SP_FN_MACCOUNT() {
		sqlServiceMapper.sp_fn_maccount();
	}

	@Override
	public void SP_FN_MAN() {
		sqlServiceMapper.sp_fn_man_1();
		sqlServiceMapper.sp_fn_man_2();
	}

	@Override
	public void SP_FN_NHCENTER() {
		sqlServiceMapper.sp_fn_nhcenter();
	}

	@Override
	public void SP_FN_NICE_DEAL_BYDAY() {
		sqlServiceMapper.sp_fn_nice_deal_byday();
	}

	@Override
	public void SP_FN_NICE_DEAL_BYHOUR() {
		sqlServiceMapper.sp_fn_nice_deal_byhour_1();
		sqlServiceMapper.sp_fn_nice_deal_byhour_2();
	}

	@Override
	public void SP_FN_NICE_DSUM() {
		sqlServiceMapper.sp_fn_nice_dsum_1();
		sqlServiceMapper.sp_fn_nice_dsum_2();
		sqlServiceMapper.sp_fn_nice_dsum_3();
		sqlServiceMapper.sp_fn_nice_dsum_4(new HashMap<String, String>());
		sqlServiceMapper.sp_fn_nice_dsum_5(new HashMap<String, String>());
		sqlServiceMapper.sp_fn_nice_dsum_6(new HashMap<String, String>());
		sqlServiceMapper.sp_fn_nice_dsum_7();
	}

	@Override
	public void SP_FN_NICE_DSUM_BC() {
		sqlServiceMapper.sp_fn_nice_dsum_0bc();
	}

	@Override
	public void SP_FN_NICE_ENDAMT() {
		sqlServiceMapper.sp_fn_nice_endamt();
	}

	@Override
	public void SP_FN_NICE_EVERY_INSERT() {
		sqlServiceMapper.sp_fn_nice_every_insert();
	}

	@Override
	public void SP_FN_NICE_TICKET_CNT() {
		sqlServiceMapper.sp_fn_nice_ticket_cnt();
	}

	@Override
	public void SP_FN_NICE_TRAN_GIFT_DSUM() {
		sqlServiceMapper.sp_fn_nice_tran_gift_dsum();
	}

	@Override
	public void SP_FN_NICE_TRAN_TOTAL() {
		sqlServiceMapper.sp_fn_nice_tran_total();
	}

	@Override
	public void SP_FN_NOTCLOSE_CANCEL() {
		sqlServiceMapper.sp_fn_notclose_cancel();
	}

	@Override
	public void SP_FN_NOTEND_NICE() {
		sqlServiceMapper.sp_fn_notend_nice();
	}

	@Override
	public void SP_FN_OFFICECLOSE() {
		sqlServiceMapper.sp_fn_officeclose();
	}

	@Override
	public void SP_FN_ONLYCASHDATAMACCLOSE() {
		sqlServiceMapper.sp_fn_onlycashdatamacclose();
	}

	@Override
	public void SP_FN_OPERFUNDSSH() {
		sqlServiceMapper.sp_fn_operfundssh();
	}

	@Override
	public void SP_FN_RECV_AMT_BS() {
		sqlServiceMapper.sp_fn_recv_amt_bs();
	}

	@Override
	public void SP_FN_SENDPLAN_CONFIRM_BS() {
		sqlServiceMapper.sp_fn_sendplan_confirm_bs();
	}

	@Override
	public void SP_FN_SENDPLAN_CONFIRM_SH() {
		sqlServiceMapper.sp_fn_sendplan_confirm_sh(new HashMap<String, String>());
	}

	@Override
	public void SP_FN_SENDPLAN_CONFIRM() {
		sqlServiceMapper.sp_fn_sendplan_confirm(new HashMap<String, String>());
	}

	@Override
	public void SP_FN_SENDPLAN_REFORM() {
		sqlServiceMapper.sp_fn_sendplan_reform();
	}

	@Override
	public void SP_FN_SENDPLAN_UPLOAD_SH() {
		sqlServiceMapper.sp_fn_sendplan_upload_sh(new HashMap<String, String>());
	}

	@Override
	public void SP_FN_SERVICESEND() {
		sqlServiceMapper.sp_fn_servicesend();
	}

	@Override
	public void SP_FN_SH_OPCENTER() {
		sqlServiceMapper.sp_fn_sh_opcenter();
	}

	@Override
	public void SP_FN_SHCENTER() {
		sqlServiceMapper.sp_fn_shcenter();
	}

	@Override
	public void SP_FN_SITECOUNT() {
		sqlServiceMapper.sp_fn_sitecount();
	}

	@Override
	public void SP_FN_SMCENTER() {
		sqlServiceMapper.sp_fn_smcenter();
	}

	@Override
	public void SP_FN_WCCENTER() {
		sqlServiceMapper.sp_fn_wccenter();
	}

	@Override
	public void SP_FN_WR_VAN_DEMAND() {
		sqlServiceMapper.sp_fn_wr_van_demand();
	}

	@Override
	public void SP_FN_WRCENTER() {
		sqlServiceMapper.sp_fn_wrcenter();
	}

	@Override
	public void SP_IF_SENDSMSCASHLACK() {
		sqlServiceMapper.sp_if_SendSMSCashLack_1();
		sqlServiceMapper.sp_if_SendSMSCashLack_2();
	}

	@Override
	public void SP_JOB_NICE_SHORT_CASH_CONFIG() {
		sqlServiceMapper.sp_job_nice_short_cash_config();
	}

	@Override
	public void SP_PHS_PUSH_MESSAGES() {
		sqlServiceMapper.sp_phs_push_messages();
	}

	@Override
	public void SP_SU_MULTIREPORT_ALL() {
		sqlServiceMapper.sp_su_multireport_all_1();
		sqlServiceMapper.sp_su_multireport_all_2();
		sqlServiceMapper.sp_su_multireport_all_3();
		sqlServiceMapper.sp_su_multireport_all_4();
		sqlServiceMapper.sp_su_multireport_all_5();
		sqlServiceMapper.sp_su_multireport_all_6();
	}

	@Override
	public void SP_UPDATE_SITE() {
		sqlServiceMapper.sp_update_site();
	}

	@Override
	public void SP_FN_NICE_TS_INSERT() {
		sqlServiceMapper.sp_fn_nice_ts_insert();
	}

	@Override
	public void SP_CM_PASSWORD_CHANGE() {
    		sqlServiceMapper.sp_cm_password_change_1();
    		sqlServiceMapper.sp_cm_password_change_2();
    		sqlServiceMapper.sp_cm_password_change_3();
    		sqlServiceMapper.sp_cm_password_change_4();
    		sqlServiceMapper.sp_cm_password_change_5();
    		sqlServiceMapper.sp_cm_password_change_6();
	}

	@Override
	public void SP_CT_MONTH_USE_STATUS_003() {
		sqlServiceMapper.sp_ct_month_use_status_003_1();
		sqlServiceMapper.sp_ct_month_use_status_003_2();
		
    	new NibsScheduleExecuter(new String[]{"OrgSend", "MONTH_USE_STATUS", "003"});
	}

	@Override
	public void T_CT_ERROR_MNG_003_BRAND_TEMP() {
		sqlServiceMapper.t_ct_error_mng_003_brand_temp();
	}
	
	// OracleJob 추가
	@Override
	public void SP_BATCH_ERROR_ETC_DELETE() {
		sqlServiceMapper.sp_batch_error_etc_delete();
	}
	
	@Override
	public void SP_CHECK_PHOTO_CARRY() {
		sqlServiceMapper.sp_check_photo_carry();
	}
	
	@Override
	public void SP_CM_CASH_WC_INSERT() {
		sqlServiceMapper.sp_cm_cash_wc_insert();
	}
	
	@Override
	public void SP_CM_ELECSIGN_RETIRE() {
		sqlServiceMapper.sp_cm_elecsign_retire();
	}
	
	@Override
	public void SP_CM_PKG_DAY() {
		sqlServiceMapper.sp_cm_pkg_day();
	}
	
	@Override
	public void SP_CT_LINEFAULT_STATUS() {
		sqlServiceMapper.sp_ct_linefault_status();
	}
	
	@Override
	public void SP_CT_LOCK_HISTOY_BACKUP() {
		sqlServiceMapper.sp_ct_lock_histoy_backup();
	}
	
	@Override
	public void SP_CT_LOCKSERVER_VERIFY() {
		sqlServiceMapper.sp_ct_lockserver_verify();
	}
	
	@Override
	public void SP_CT_T_CT_TRADE_CONFIRM_INIT() {
		sqlServiceMapper.sp_ct_t_ct_trade_confirm_init();
	}
	
	@Override
	public void SP_FN_ADJUSTMENT_CARRY() {
		sqlServiceMapper.sp_fn_adjustment_carry();
	}
	
	@Override
	public void SP_FN_CREATE_NOTEND_MEMBER() {
		sqlServiceMapper.sp_fn_create_notend_member();
	}
	
	@Override
	public void SP_FN_EMART_TICKET() {
		sqlServiceMapper.sp_fn_emart_ticket();
	}
	
	@Override
	public void SP_FN_MAKE_NODEAL_RESULT_HIS() {
		sqlServiceMapper.sp_fn_make_nodeal_result_his();
	}
	
	@Override
	public void SP_FN_NOTEND_FEE() {
		sqlServiceMapper.sp_fn_notend_fee();
	}
	
	@Override
	public void SP_FN_ONNURI_DSUM() {
		sqlServiceMapper.sp_fn_onnuri_dsum();
	}
	
	@Override
	public void SP_FN_PRESAFE_HISTORY() {
		sqlServiceMapper.sp_fn_presafe_history();
	}
	
	@Override
	public void SP_IF_SENDSMSKTISERROR() {
		sqlServiceMapper.sp_if_sendsmsktiserror();
	}
	
	@Override
	public void SP_JOB_ARRIVAL_EST_TIME() {
		sqlServiceMapper.sp_job_arrival_est_time();
	}
	
	@Override
	public void SP_JOB_AUTOSEND_PUSH() {
		sqlServiceMapper.sp_job_autosend_push();
	}
	
	@Override
	public void SP_JOB_BOARD_LIST_NOTICE() {
		sqlServiceMapper.sp_job_board_list_notice();
	}
	
	@Override
	public void SP_JOB_FN_SENDPLAN_UPDATE_0KI() {
		sqlServiceMapper.sp_job_fn_sendplan_update_0ki();
	}
	
	@Override
	public void SP_JOB_SCHEDULE_MNG_NOTICE() {
		sqlServiceMapper.sp_job_schedule_mng_notice();
	}
	
	@Override
	public void SP_JOB_T_CT_AS_UNFINISH() {
		sqlServiceMapper.sp_job_t_ct_as_unfinish();
	}
	
	@Override
	public void SP_JOB_T_CT_AUTOSEND_MAC() {
		sqlServiceMapper.sp_job_t_ct_autosend_mac();
	}
	
	@Override
	public void SP_JOB_T_CT_DELAY_MONITORING() {
		sqlServiceMapper.sp_job_t_ct_delay_monitoring();
	}
	
	@Override
	public void SP_JOB_T_CT_ERROR_MNG() {
		sqlServiceMapper.sp_job_t_ct_error_mng();
	}
	
	@Override
	public void SP_JOB_T_CT_ERROR_MNG_GUARD() {
		sqlServiceMapper.sp_job_t_ct_error_mng_guard();
	}
	
	@Override
	public void SP_JOB_T_CT_ERROR_OPEN_CHECK() {
		sqlServiceMapper.sp_job_t_ct_error_open_check();
	}
	
	@Override
	public void SP_JOB_T_CV_POSITION() {
		sqlServiceMapper.sp_job_t_cv_position();
	}
	
	@Override
	public void SP_JOB_T_EMART_MONITORING() {
		sqlServiceMapper.sp_job_t_emart_monitoring();
	}
	
	@Override
	public void SP_JOB_T_FN_SENDPLAN_SCHEDULE() {
		sqlServiceMapper.sp_job_t_fn_sendplan_schedule();
	}
	
	@Override
	public void SP_JOB_T_SHORT_CASH_MNG() {
		sqlServiceMapper.sp_job_t_short_cash_mng();
	}
	
	@Override
	public void SP_RC_INFO_DELETE() {
		sqlServiceMapper.sp_rc_info_delete();
	}
	

	@Override
	public void SP_FN_FORCAST_DATA_DSUM_BASE() {
		sqlServiceMapper.sp_fn_forcast_data_dsum_base_1();
		sqlServiceMapper.sp_fn_forcast_data_dsum_base_2();
	}

	@Override
	public void SP_FN_FORCAST_DATA_DSUM() {
		sqlServiceMapper.sp_fn_forcast_data_dsum();
	}

	@Override
	public void SP_FN_FORCAST_INOUT_CENTER() {
		sqlServiceMapper.sp_fn_forcast_inout_center();
	}
	
	


	@Override
	public void SP_CT_CHECK_ALERT_EMARTPOS() {
		sqlServiceMapper.sp_ct_check_alert_emartpos();
	}

	@Override
	public void SP_FN_DAY_CLOSE_KIOSK() {
		sqlServiceMapper.sp_fn_day_close_kiosk();
	}

	@Override
	public void SP_FN_KB_VAN_DEMAND() {
		sqlServiceMapper.sp_fn_kb_van_demand();
	}

	@Override
	public void SP_FN_VOUCHER_MACSTOCK() {
		sqlServiceMapper.sp_fn_voucher_macstock();
	}

	@Override
	public void SP_FN_VOUCHER_OFFICESTOCK() {
		sqlServiceMapper.sp_fn_voucher_officestock();
	}

	@Override
	public void SP_INSERT_NICE_LOCK_TIME() {
		sqlServiceMapper.sp_insert_nice_lock_time();
	}

	@Override
	public void SP_PHS_PUSH_SVC_USER() {
		sqlServiceMapper.sp_job_tb_phs_push_svc_user();
	}

	@Override
	public String SP_FN_KTIS_CLOSE(String param) {
		Map<String, Object> paramObj = new HashMap<String, Object>();
		String[] arrParam = param.split("\\^");
		String ret = null;
		int pCnt = 0;
		
		for (String p : arrParam) {
			paramObj.put("cond" + pCnt++, p);
		}
		
		sqlServiceMapper.sp_fn_ktis_close(paramObj);
		
		ret = (String)paramObj.get("v_RtnOut");
		
		if (ret.length() > 2) {
			return ret.substring(1, 2);
		} else {
			return "0";
		}
	}

	@Override
	public void SP_IF_GET_LOST_NO() {
		sqlServiceMapper.sp_if_get_lost_no();
	}

}
