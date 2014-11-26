package com.nicetcm.nibsplus.sqlservice.dao;

/**
 * DB에서 스케쥴정보 조회
 * <pre>
 *
 * </pre>
 *
 * @author 박상철
 * @version 1.0
 * @see
 */
public interface SqlServiceMapper {
    public void executeProcedure(String procedure);
    
    public void sp_cm_fax();

    public void sp_cm_mac_rank();

    public void sp_cm_personnel();

    public void sp_cm_update_plan_focus();

    public void sp_ct_check_0EL_alert();

    public void sp_ct_check_alert_039();

    public void sp_ct_check_alert_nh_file();

    public void sp_ct_check_alert();

    public void sp_ct_delete_day();

    public void sp_ct_report_day_2007_1();
    public void sp_ct_report_day_2007_2();

    public void sp_fn_bscenter();

    public void sp_fn_cashflow_endamt();

    public void sp_fn_center();

    public void sp_fn_close_send_wc();

    public void sp_fn_ctcenter();

    public void sp_fn_day_close_ktis();

    public void sp_fn_deal_forecast();

    public void sp_fn_dealcount();

    public void sp_fn_dgbcenter();

    public void sp_fn_eland_amt();

    public void sp_fn_err_trade();

    public void sp_fn_etc_msum_1();
    public void sp_fn_etc_msum_2();
    public void sp_fn_etc_msum_3();

    public void sp_fn_forcast_addsend_center();

    public void sp_fn_forcast_data_dsum_atm();

    public void sp_fn_forcast_data_dsum();

    public void sp_fn_hncenter();

    public void sp_fn_imsi_jan();

    public void sp_fn_jjcenter();

    public void sp_fn_kbcenter();

    public void sp_fn_kebcenter();

    public void sp_fn_kncenter();

    public void sp_fn_ktiscenter();

    public void sp_fn_kucenter();

    public void sp_fn_largemonth_1();
    public void sp_fn_largemonth_2();

    public void sp_fn_mac_stock_amt();

    public void sp_fn_maccount();

    public void sp_fn_man_1();
    public void sp_fn_man_2();

    public void sp_fn_nhcenter();

    public void sp_fn_nice_deal_byday();

    public void sp_fn_nice_deal_byhour_1();
    public void sp_fn_nice_deal_byhour_2();

    public void sp_fn_nice_dsum_bc_1();
    public void sp_fn_nice_dsum_bc_2();
    public void sp_fn_nice_dsum_bc_3();
    public void sp_fn_nice_dsum_bc_4();
    public void sp_fn_nice_dsum_bc_5();
    public void sp_fn_nice_dsum_bc_6();
    public void sp_fn_nice_dsum_bc_7();

    public void sp_fn_nice_dsum();

    public void sp_fn_nice_endamt();

    public void sp_fn_nice_every_insert();

    public void sp_fn_nice_ticket_cnt();

    public void sp_fn_nice_tran_gift_dsum();

    public void sp_fn_nice_tran_total();

    public void sp_fn_notclose_cancel();

    public void sp_fn_notend_nice();

    public void sp_fn_officeclose();

    public void sp_fn_onlycashdatamacclose();

    public void sp_fn_operfundssh();

    public void sp_fn_recv_amt_bs();

    public void sp_fn_sendplan_confirm_bs();

    public void sp_fn_sendplan_confirm_sh();

    public void sp_fn_sendplan_confirm();

    public void sp_fn_sendplan_reform();

    public void sp_fn_sendplan_upload_sh();

    public void sp_fn_servicesend();

    public void sp_fn_sh_opcenter();

    public void sp_fn_shcenter();

    public void sp_fn_sitecount();

    public void sp_fn_smcenter();

    public void sp_fn_wccenter();

    public void sp_fn_wr_van_demand();

    public void sp_fn_wrcenter();

    public void sp_if_SendSMSCashLack_1();
    public void sp_if_SendSMSCashLack_2();

    public void sp_job_nice_short_cash_config();

    public void sp_phs_push_messages();

    public void sp_su_multireport_all_1();
    public void sp_su_multireport_all_2();
    public void sp_su_multireport_all_3();
    public void sp_su_multireport_all_4();
    public void sp_su_multireport_all_5();
    public void sp_su_multireport_all_6();

    public void sp_update_site();
    
	public void sp_cm_password_change_1();

	public void sp_cm_password_change_2();

	public void sp_cm_password_change_3();

	public void sp_cm_password_change_4();

	public void sp_cm_password_change_5();

	public void sp_cm_password_change_6();

	public void sp_ct_month_use_status_003_1();

	public void sp_ct_month_use_status_003_2();

    public void t_ct_error_mng_003_brand_temp();
}
