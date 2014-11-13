/** 
 * com.nicetcm.nibsplus.sqlservice.service.SqlService
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 11. 13.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.sqlservice.service;

import org.slf4j.Logger;

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
	public void spCmFax(Logger logger);
	public void spCmMacRank(Logger logger);
	public void spCmPersonnel(Logger logger);
	public void spCmUpdatePlanFocus(Logger logger);
	public void spCtCheck0elAlert(Logger logger);
	public void spCtCheckAlert039(Logger logger);
	public void spCtCheckAlertNhFile(Logger logger);
	public void spCtCheckAlert(Logger logger);
	public void spCtDeleteDay(Logger logger);
	public void spCtReportDay2007(Logger logger);
	public void spFnBscenter(Logger logger);
	public void spFnCashflowEndamt(Logger logger);
	public void spFnCenter(Logger logger);
	public void spFnCloseSendWc(Logger logger);
	public void spFnCtcenter(Logger logger);
	public void spFnDayCloseKtis(Logger logger);
	public void spFnDealForecast(Logger logger);
	public void spFnDealcount(Logger logger);
	public void spFnDgbcenter(Logger logger);
	public void spFnElandAmt(Logger logger);
	public void spFnErrTrade(Logger logger);
	public void spFnEtcMsum(Logger logger);
	public void spFnForcastAddsendCenter(Logger logger);
	public void spFnForcastDataDsumAtm(Logger logger);
	public void spFnForcastDataDsum(Logger logger);
	public void spFnHncenter(Logger logger);
	public void spFnImsiJan(Logger logger);
	public void spFnJjcenter(Logger logger);
	public void spFnKbcenter(Logger logger);
	public void spFnKebcenter(Logger logger);
	public void spFnKncenter(Logger logger);
	public void spFnKtiscenter(Logger logger);
	public void spFnKucenter(Logger logger);
	public void spFnLargemonth(Logger logger);
	public void spFnMacStockAmt(Logger logger);
	public void spFnMaccount(Logger logger);
	public void spFnMan(Logger logger);
	public void spFnNhcenter(Logger logger);
	public void spFnNiceDealByday(Logger logger);
	public void spFnNiceDealByhour(Logger logger);
	public void spFnNiceDsumBc(Logger logger);
	public void spFnNiceDsum(Logger logger);
	public void spFnNiceEndamt(Logger logger);
	public void spFnNiceEveryInsert(Logger logger);
	public void spFnNiceTicketCnt(Logger logger);
	public void spFnNiceTranGiftDsum(Logger logger);
	public void spFnNiceTranTotal(Logger logger);
	public void spFnNotcloseCancel(Logger logger);
	public void spFnNotendNice(Logger logger);
	public void spFnOfficeclose(Logger logger);
	public void spFnOnlycashdatamacclose(Logger logger);
	public void spFnOperfundssh(Logger logger);
	public void spFnRecvAmtBs(Logger logger);
	public void spFnSendplanConfirmBs(Logger logger);
	public void spFnSendplanConfirmSh(Logger logger);
	public void spFnSendplanConfirm(Logger logger);
	public void spFnSendplanReform(Logger logger);
	public void spFnSendplanUploadSh(Logger logger);
	public void spFnServicesend(Logger logger);
	public void spFnShOpcenter(Logger logger);
	public void spFnShcenter(Logger logger);
	public void spFnSitecount(Logger logger);
	public void spFnSmcenter(Logger logger);
	public void spFnWccenter(Logger logger);
	public void spFnWrVanDemand(Logger logger);
	public void spFnWrcenter(Logger logger);
	public void spIfSendsmscashlack(Logger logger);
	public void spJobNiceShortCashConfig(Logger logger);
	public void spPhsPushMessages(Logger logger);
	public void spSuMultireportAll(Logger logger);
	public void spUpdateSite(Logger logger);
	public void spCmPasswordChange(Logger logger);
	public void spCtMonthUseStatus003(Logger logger);
	public void tCtErrorMng003BrandTemp(Logger logger);
}
