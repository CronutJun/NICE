/** 
 * com.nicetcm.nibsplus.filemng.dao.FileSendMapper
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 10. 10.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.filemng.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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
public interface FileSendMapper {
	public String pickupGetCNTranData(String pTransDate);
	public String pickupGetCNMacData(String pTransDate);
	public String pickupGetCNMacDataPrev(String pTransDate);
	public String pickupGetBCTranData(String pTransDate);
	public String pickupGetSHTranData(String pTransDate);
	public String pickupGetSCTranData(String pTransDate);
	public String pickupGetHNetTranData(String pTransDate);
	public String pickupGetHNetNewTranData(String pTransDate);
	public String pickupGetCUTranData(String pTransDate);
	public String pickupGetCityTranData(String pTransDate);
	public String pickupGetCityTranData2(String pTransDate);
	public String pickupGetCityTranData3(String szDate);
	public String pickupGetLGTranData(String pTransDate);
	public String pickupGetLGTranData2(String pTransDate);
	public String pickupGetLGTranData3(String szDate);
	public String pickupGetLCTranData(String pTransDate);
	public String pickupGetNongHTranData(String pTransDate);
	public String pickupGetNongHTranData2(String pTransDate);
	public String pickupGetNongHTranData3(String szDate);
	public String pickupGetNongHTranData_dasi(String pTransDate);
	public String pickupGetNongHTranData_dasi2(String pTransDate);
	public String pickupGetNongHTranData_dasi3(String szDate);
	public String pickupGetNongHTranData_NEW(String pTransDate);
	public String pickupGetNongHTranData_NEW2(String pTransDate);
	public String pickupGetNongHTranData_NEW3(String szDate);
	public String pickupGetNongHMacData(String pTransDate);
	public String pickupGetNongHBrandMacData(String pTransDate);
	public String pickupGetNongHBrandMacData2(String pTransDate);
	public String pickupGetCommonTranData(String pTransDate);
	public String pickupGetCommonTranData2(String pTransDate);
	public String pickupGetCommonTranData3(String pTransDate);
	public String pickupGetKiupTranData(String pTransDate);
	public String pickupGetKBSTTranData(String pTransDate);
	public String pickupGetCommonImsiData(String pTransDate);
	public String pickupGetCommonImsiData2(String szDate);
	public String pickupGetKETranData(String pTransDate);
	public String pickupGetKETranData2(String pTransDate);
	public String pickupGetKETranData3(String szDate);
	public String pickupGetSmartTranData(String pTransDate);
	public String pickupGetSmartImsiTranData(String pTransDate);
	public String pickupGetSmartImsiTranData2(String szDate);
	public String pickupGetHITranData(String pTransDate);
	public String pickupGetCHTranData(String pTransDate);
	public String pickupGetCHTranData2(String pTransDate);
	public String pickupGetSuHTranData(String pTransDate);
	public String pickupGetSuHTranData2(String szDate);
	public String pickupGetSuHMacData(String pTransDate);
	public String pickupGetKFTCTranData(String pTransDate);
	public String pickupGetKFTCTranData2(String pTransDate);
	public String pickupGetKFTCTranData3(String szDate);
	public String pickupGetGiftCardInfoData();

	public List<LinkedHashMap<String, Object>> selectGetCNTranData(String pTransDate);
	public List<LinkedHashMap<String, Object>> selectGetCNMacData(@Param("hPreActDate") String hPreActDate, @Param("hPrePreActDate") String hPrePreActDate);
	public List<LinkedHashMap<String, Object>> selectGetBCTranData(String hPreDate);
	public List<LinkedHashMap<String, Object>> selectGetBCMacData(String hTransDate);
	public List<LinkedHashMap<String, Object>> selectGetSHTranData(@Param("pDealType") String pDealType, @Param("hPreDate") String hPreDate);
	public List<LinkedHashMap<String, Object>> selectGetSCTranData(String hPreDate);
	public List<LinkedHashMap<String, Object>> selectGetHNetTranData(String hPreDate);
	public List<LinkedHashMap<String, Object>> selectGetHNetNewTranData(String hPreDate);
	public List<LinkedHashMap<String, Object>> selectGetCUTranData(String hPreDate);
	public List<LinkedHashMap<String, Object>> selectGetCityTranData(String szDate);
	public List<LinkedHashMap<String, Object>> selectGetLGTranData(String szDate);
	public List<LinkedHashMap<String, Object>> selectGetLCTranData(String szDate);
	public List<LinkedHashMap<String, Object>> selectGetNongHTranData(String szDate);
	public List<LinkedHashMap<String, Object>> selectGetNongHTranData_dasi(String szDate);
	public List<LinkedHashMap<String, Object>> selectGetNongHTranData_NEW(String szDate);
	public List<LinkedHashMap<String, Object>> selectGetNongHMacData(String pTransDate);
	public List<LinkedHashMap<String, Object>> selectGetNongHBrandMacData(String hPreActDate);
	public List<LinkedHashMap<String, Object>> selectGetCommonTranData(@Param("szDate") String szDate, @Param("szOrgCd") String szOrgCd);
	public List<LinkedHashMap<String, Object>> selectGetCommonTranData2(@Param("szDate") String szDate, @Param("szOrgCd") String szOrgCd);
	public List<LinkedHashMap<String, Object>> selectGetKiupTranData(@Param("szDate") String szDate, @Param("szOrgCd") String szOrgCd);
	public List<LinkedHashMap<String, Object>> selectGetKiupTranData2(@Param("szDate") String szDate, @Param("szOrgCd") String szOrgCd);
	public List<LinkedHashMap<String, Object>> selectGetKBSTTranData(@Param("szDate") String szDate, @Param("szOrgCd") String szOrgCd);
	public List<LinkedHashMap<String, Object>> selectGetKBSTTranData2(@Param("szDate") String szDate, @Param("szOrgCd") String szOrgCd);
	public List<LinkedHashMap<String, Object>> selectGetCommonImsiData(@Param("szDate") String szDate, @Param("szOrgCd") String szOrgCd);
	public List<LinkedHashMap<String, Object>> selectGetCommonImsiData2(@Param("szDate") String szDate, @Param("szOrgCd") String szOrgCd);
	public List<LinkedHashMap<String, Object>> selectGetKETranData(@Param("szDate") String szDate, @Param("szOrgCd") String szOrgCd);
	public List<LinkedHashMap<String, Object>> selectGetKETranData2(@Param("szDate") String szDate, @Param("szOrgCd") String szOrgCd);
	public List<LinkedHashMap<String, Object>> selectGetSmartTranData(String szDate);
	public List<LinkedHashMap<String, Object>> selectGetSmartImsiTranData(String szDate);
	public List<LinkedHashMap<String, Object>> selectGetHITranData(String hPreDate);
	public List<LinkedHashMap<String, Object>> selectGetCHTranData(String szDate);
	public List<LinkedHashMap<String, Object>> selectGetSuHTranData(String szDate);
	public List<LinkedHashMap<String, Object>> selectGetSuHMacData(String pTransDate);
	public List<LinkedHashMap<String, Object>> selectGetKFTCTranData(String pTransDate);
	public List<LinkedHashMap<String, Object>> selectGetGiftCardInfoData();
	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param szFileName
	 * @param ftpsuccess
	 * @return
	 */
	public int updatePutOrgTranFile(String szFileName, int ftpsuccess);
	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param szFileName
	 * @param ftpsuccess
	 */
	public void insertPutOrgTranFile(String szFileName, int ftpsuccess);
}
