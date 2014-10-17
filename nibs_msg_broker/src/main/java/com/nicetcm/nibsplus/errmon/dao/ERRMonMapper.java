package com.nicetcm.nibsplus.errmon.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ERRMonMapper {

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 */
	public int mainWhileDelete1();

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 */
	public int mainWhileDelete2();

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @return
	 */
	public String pickupRealHolyYN();

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @return
	 */
	public int pickupWeekDay();

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @return
	 */
	public List<HashMap<String, Object>> selectNiceTranICNotranErrorProc();

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @return
	 */
	public List<HashMap<String, Object>> selectNiceCashLackRepairProc();

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param obj
	 */
	public void updateNiceCashLackRepairProc(HashMap<String, Object> obj);
	public void updateNiceCashLackRepairProc2(HashMap<String, Object> obj);

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param orgCd
	 * @return
	 */
	public List<HashMap<String, Object>> selectDGNotOpenErrorProc(String orgCd);

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @return
	 */
	public List<HashMap<String, Object>> selectNiceEemptySensorRepairProc();

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param obj
	 */
	public void updateNiceEemptySensorRepairProc(HashMap<String, Object> obj);
	public void updateNiceEemptySensorRepairProc2(HashMap<String, Object> obj);

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @return
	 */
	public List<HashMap<String, Object>> selectSHCashLackErrorProc();

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @return
	 */
	public List<HashMap<String, Object>> selectSHCashLackErrorProc2();

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param horgcd
	 * @param hbranchcd
	 * @return
	 */
	public List<HashMap<String, Object>> selectNiceInqRemAmtErrorProc(@Param("orgCd") String horgcd, @Param("branchCd") String hbranchcd);

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @return
	 */
	public List<HashMap<String, Object>> selectNiceCashLackErrorProc();

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param horgcd
	 * @param hbranchcd
	 * @param string
	 * @return
	 */
	public List<HashMap<String, Object>> selectNiceTranRepairErrorProc(@Param("orgCd") String horgcd, @Param("branchCd") String hbranchcd, @Param("cashOut")String cashOut);
	
	public List<String> selectNiceDoorCheckErrorProc();

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @return
	 */
	public List<HashMap<String, Object>> selectNiceTranNotranErrorProc();

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param horgcd
	 * @param hbranchcd
	 * @return
	 */
	public List<HashMap<String, Object>> selectNiceTranPickErrorProc(@Param("orgCd") String horgcd, @Param("branchCd") String hbranchcd);

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param szCreateYN
	 * @param orgCd
	 * @param branchCd
	 * @param macNo
	 */
	public void updateATMESErrorSendProc(@Param("szCreateYN") String szCreateYN, @Param("orgCd") String orgCd, @Param("branchCd") String branchCd, @Param("macNo") String macNo);

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param macNo
	 * @param atmDealNo
	 */
	public int updateNiceInqRemAmtErrorUpdateProc(@Param("macNo") String macNo, @Param("atmDealNo") String atmDealNo);

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param macNo
	 * @param atmDealNo
	 */
	public void insertNiceInqRemAmtErrorUpdateProc(@Param("macNo") String macNo, @Param("atmDealNo") String atmDealNo);

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param macNo
	 * @param atmDealNo
	 * @return
	 */
	public int updateNiceTranRepairErrorUpdateProc(@Param("macNo") String macNo, @Param("atmDealNo") String atmDealNo);

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param macNo
	 * @param atmDealNo
	 */
	public void insertNiceTranRepairErrorUpdateProc(@Param("macNo") String macNo, @Param("atmDealNo") String atmDealNo);

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param macNo
	 * @param atmDealNo
	 * @return
	 */
	public int updateNiceTranErrorUpdateProc(@Param("macNo") String macNo, @Param("atmDealNo") String atmDealNo);

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param macNo
	 * @param atmDealNo
	 */
	public void insertNiceTranErrorUpdateProc(@Param("macNo") String macNo, @Param("atmDealNo") String atmDealNo);

}
