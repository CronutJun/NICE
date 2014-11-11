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
    
	public void sp_ct_month_use_status_003_1();

	public void sp_ct_month_use_status_003_2();

	public void sp_cm_password_change_1();

	public void sp_cm_password_change_2();

	public void sp_cm_password_change_3();

	public void sp_cm_password_change_4();

	public void sp_cm_password_change_5();

	public void sp_cm_password_change_6();

    public void t_ct_error_mng_003_brand_temp();
}
