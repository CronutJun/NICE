package com.nicetcm.nibsplus.sqlservice.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
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
	public void SP_CM_FAX(Logger logger) {
		try {
			sqlServiceMapper.sp_cm_fax();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_CM_MAC_RANK(Logger logger) {
		try {
			sqlServiceMapper.sp_cm_mac_rank();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_CM_PERSONNEL(Logger logger) {
		try {
			sqlServiceMapper.sp_cm_personnel();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_CM_UPDATE_PLAN_FOCUS(Logger logger) {
		try {
			sqlServiceMapper.sp_cm_update_plan_focus();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_CT_CHECK0EL_ALERT(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_check_0EL_alert();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_CT_CHECK_ALERT_039(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_check_alert_039();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_CT_CHECK_ALERT_NH_FILE(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_check_alert_nh_file();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_CT_CHECK_ALERT(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_check_alert();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_CT_DELETE_DAY(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_delete_day();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_CT_REPORT_DAY_2012(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_report_day_2012_1();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
		sqlServiceMapper.sp_ct_report_day_2012_2();
	}

	@Override
	public void SP_FN_BSCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_bscenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_CASHFLOW_ENDAMT(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_cashflow_endamt();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_CENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_center();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_CLOSE_SEND_WC(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_close_send_wc();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_CTCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_ctcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_DAY_CLOSE_KTIS(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_day_close_ktis();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_DEAL_FORECAST(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_deal_forecast(new HashMap<String, String>());
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_DEALCOUNT(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_dealcount();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_DGBCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_dgbcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_ELAND_AMT(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_eland_amt();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_ERR_TRADE(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_err_trade();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_ETC_MSUM(Logger logger) {
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
	public void SP_FN_FORCAST_ADDSEND_CENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_forcast_addsend_center();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_FORCAST_DATA_DSUM_ATM(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_forcast_data_dsum_atm();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_HNCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_hncenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_IMSI_JAN(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_imsi_jan();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_JJCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_jjcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_KBCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_kbcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_KEBCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_kebcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_KNCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_kncenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_KTISCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_ktiscenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_KUCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_kucenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_LARGEMONTH(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_largemonth_1();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
		sqlServiceMapper.sp_fn_largemonth_2();
	}

	@Override
	public void SP_FN_MAC_STOCK_AMT(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_mac_stock_amt(new HashMap<String, String>());
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_MACCOUNT(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_maccount();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_MAN(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_man_1();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
		sqlServiceMapper.sp_fn_man_2();
	}

	@Override
	public void SP_FN_NHCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nhcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_NICE_DEAL_BYDAY(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_deal_byday();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_NICE_DEAL_BYHOUR(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_deal_byhour_1();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
		sqlServiceMapper.sp_fn_nice_deal_byhour_2();
	}

	@Override
	public void SP_FN_NICE_DSUM(Logger logger) {
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
	public void SP_FN_NICE_DSUM_BC(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_dsum_0bc();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_NICE_ENDAMT(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_endamt();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_NICE_EVERY_INSERT(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_every_insert();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_NICE_TICKET_CNT(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_ticket_cnt();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_NICE_TRAN_GIFT_DSUM(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_tran_gift_dsum();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_NICE_TRAN_TOTAL(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_tran_total();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_NOTCLOSE_CANCEL(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_notclose_cancel();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_NOTEND_NICE(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_notend_nice();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_OFFICECLOSE(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_officeclose();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_ONLYCASHDATAMACCLOSE(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_onlycashdatamacclose();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_OPERFUNDSSH(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_operfundssh();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_RECV_AMT_BS(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_recv_amt_bs();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_SENDPLAN_CONFIRM_BS(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_sendplan_confirm_bs();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_SENDPLAN_CONFIRM_SH(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_sendplan_confirm_sh(new HashMap<String, String>());
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_SENDPLAN_CONFIRM(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_sendplan_confirm(new HashMap<String, String>());
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_SENDPLAN_REFORM(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_sendplan_reform();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_SENDPLAN_UPLOAD_SH(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_sendplan_upload_sh(new HashMap<String, String>());
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_SERVICESEND(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_servicesend();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_SH_OPCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_sh_opcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_SHCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_shcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_SITECOUNT(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_sitecount();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_SMCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_smcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_WCCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_wccenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_WR_VAN_DEMAND(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_wr_van_demand();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_WRCENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_wrcenter();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_IF_SENDSMSCASHLACK(Logger logger) {
		try {
			sqlServiceMapper.sp_if_SendSMSCashLack_1();
			sqlServiceMapper.sp_if_SendSMSCashLack_2();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_JOB_NICE_SHORT_CASH_CONFIG(Logger logger) {
		try {
			sqlServiceMapper.sp_job_nice_short_cash_config();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_PHS_PUSH_MESSAGES(Logger logger) {
		try {
			sqlServiceMapper.sp_phs_push_messages();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_SU_MULTIREPORT_ALL(Logger logger) {
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
	public void SP_UPDATE_SITE(Logger logger) {
		try {
			sqlServiceMapper.sp_update_site();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_NICE_TS_INSERT(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_nice_ts_insert();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_CM_PASSWORD_CHANGE(Logger logger) {
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
	public void SP_CT_MONTH_USE_STATUS_003(Logger logger) {
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
	public void T_CT_ERROR_MNG_003_BRAND_TEMP(Logger logger) {
		try {
			sqlServiceMapper.t_ct_error_mng_003_brand_temp();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	// OracleJob 추가
	@Override
	public void SP_BATCH_ERROR_ETC_DELETE(Logger logger) {
		try {
			sqlServiceMapper.sp_batch_error_etc_delete();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_CHECK_PHOTO_CARRY(Logger logger) {
		try {
			sqlServiceMapper.sp_check_photo_carry();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_CM_CASH_WC_INSERT(Logger logger) {
		try {
			sqlServiceMapper.sp_cm_cash_wc_insert();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_CM_ELECSIGN_RETIRE(Logger logger) {
		try {
			sqlServiceMapper.sp_cm_elecsign_retire();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_CM_PKG_DAY(Logger logger) {
		try {
			sqlServiceMapper.sp_cm_pkg_day();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_CT_LINEFAULT_STATUS(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_linefault_status();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_CT_LOCK_HISTOY_BACKUP(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_lock_histoy_backup();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_CT_LOCKSERVER_VERIFY(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_lockserver_verify();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_CT_T_CT_TRADE_CONFIRM_INIT(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_t_ct_trade_confirm_init();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_FN_ADJUSTMENT_CARRY(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_adjustment_carry();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_FN_CREATE_NOTEND_MEMBER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_create_notend_member();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_FN_EMART_TICKET(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_emart_ticket();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_FN_MAKE_NODEAL_RESULT_HIS(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_make_nodeal_result_his();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_FN_NOTEND_FEE(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_notend_fee();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_FN_ONNURI_DSUM(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_onnuri_dsum();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_FN_PRESAFE_HISTORY(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_presafe_history();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_IF_SENDSMSKTISERROR(Logger logger) {
		try {
			sqlServiceMapper.sp_if_sendsmsktiserror();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_ARRIVAL_EST_TIME(Logger logger) {
		try {
			sqlServiceMapper.sp_job_arrival_est_time();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_AUTOSEND_PUSH(Logger logger) {
		try {
			sqlServiceMapper.sp_job_autosend_push();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_BOARD_LIST_NOTICE(Logger logger) {
		try {
			sqlServiceMapper.sp_job_board_list_notice();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_FN_SENDPLAN_UPDATE_0KI(Logger logger) {
		try {
			sqlServiceMapper.sp_job_fn_sendplan_update_0ki();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_SCHEDULE_MNG_NOTICE(Logger logger) {
		try {
			sqlServiceMapper.sp_job_schedule_mng_notice();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_T_CT_AS_UNFINISH(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_ct_as_unfinish();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_T_CT_AUTOSEND_MAC(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_ct_autosend_mac();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_T_CT_DELAY_MONITORING(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_ct_delay_monitoring();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_T_CT_ERROR_MNG(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_ct_error_mng();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_T_CT_ERROR_MNG_GUARD(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_ct_error_mng_guard();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_T_CT_ERROR_OPEN_CHECK(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_ct_error_open_check();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_T_CV_POSITION(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_cv_position();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_T_EMART_MONITORING(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_emart_monitoring();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_T_FN_SENDPLAN_SCHEDULE(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_fn_sendplan_schedule();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_JOB_T_SHORT_CASH_MNG(Logger logger) {
		try {
			sqlServiceMapper.sp_job_t_short_cash_mng();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void SP_RC_INFO_DELETE(Logger logger) {
		try {
			sqlServiceMapper.sp_rc_info_delete();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	

	@Override
	public void SP_FN_FORCAST_DATA_DSUM_BASE(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_forcast_data_dsum_base_1();
			sqlServiceMapper.sp_fn_forcast_data_dsum_base_2();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_FORCAST_DATA_DSUM(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_forcast_data_dsum();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_FORCAST_INOUT_CENTER(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_forcast_inout_center();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	


	@Override
	public void SP_CT_CHECK_ALERT_EMARTPOS(Logger logger) {
		try {
			sqlServiceMapper.sp_ct_check_alert_emartpos();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_DAY_CLOSE_KIOSK(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_day_close_kiosk();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_KB_VAN_DEMAND(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_kb_van_demand();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_VOUCHER_MACSTOCK(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_voucher_macstock();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_FN_VOUCHER_OFFICESTOCK(Logger logger) {
		try {
			sqlServiceMapper.sp_fn_voucher_officestock();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_INSERT_NICE_LOCK_TIME(Logger logger) {
		try {
			sqlServiceMapper.sp_insert_nice_lock_time();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public void SP_PHS_PUSH_SVC_USER(Logger logger) {
		try {
			sqlServiceMapper.sp_job_tb_phs_push_svc_user();
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public String SP_FN_KTIS_CLOSE(Logger logger, String param) {
		Map<String, Object> paramObj = new HashMap<String, Object>();
		String[] arrParam = param.split("\\^");
		String ret = null;
		int pCnt = 0;
		
		try {
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
		} catch(RuntimeException e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

}
