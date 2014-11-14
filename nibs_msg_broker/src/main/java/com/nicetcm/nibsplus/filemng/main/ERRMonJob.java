/** 
 * com.nicetcm.nibsplus.errmon.service.impl.ERRMonServiceImpl
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 10. 7.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.filemng.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.context.ApplicationContext;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.model.MsgBrokerConf;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerCallBack;
import com.nicetcm.nibsplus.filemng.dao.ERRMonMapper;
import com.nicetcm.nibsplus.filemng.rmi.MsgBrokerCallAgent;

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
@DisallowConcurrentExecution
public class ERRMonJob implements Job {
	
	private static int checkCnt = 0;
	private static final String hOrgCd = "096";
	private static final String hBranchCd = "9600";
	
	private ERRMonMapper errMonMapper;

	@Override
	public void execute(JobExecutionContext context) {
		ApplicationContext applicationContext = (ApplicationContext)context.getMergedJobDataMap().get("applicationContext");

        if (MsgBrokerCallAgent.msgBrokerConfig == null) {
        	MsgBrokerCallAgent.msgBrokerConfig = (Properties)applicationContext.getBean("msgBrokerConfig");
        }
        
		errMonMapper = applicationContext.getBean("errMonMapper", ERRMonMapper.class);
		errMonMapper.mainWhileDelete1();
		errMonMapper.mainWhileDelete2();
		
		if (checkCnt % 3 == 0) exec3Min();
		if (checkCnt % 5 == 0) exec5Min();
		
		checkCnt++;
		
		if (checkCnt == 15) checkCnt = 0;
	}
	
	private void exec3Min() {
		logger("checkCnt 1 : " + checkCnt + "\n");
		
		if (niceDoorCheckErrorProc() < 0) {
			logger( "NiceDoorCheckErrorProc Error !!!\n" );
			fnCleanup(-1);
		} else {
			logger( "NiceDoorCheckErrorProc Ended !!!\n" );
		}
		
		if (niceCashLackRepairProc() < 0) {
			logger( "NiceCashLackRepairProc Error !!!\n" );
			fnCleanup(-1);
		} else {
			logger( "NiceDoorCheckErrorProc Ended !!!\n" );
		}
	}

	private void exec5Min() {
		logger("checkCnt 2 : " + checkCnt + "\n");
		
		SimpleDateFormat dateFmt = new SimpleDateFormat("HHmmss");
		int lCurrTime = Integer.parseInt(dateFmt.format(Calendar.getInstance().getTime()));
		String hRealHolyYn = null;

		if ( niceTranPickErrorProc() < 0 ) {
			logger( "NiceTranPickErrorProc Error !!!\n" );
			fnCleanup(-1);
		} else {
			logger( "NiceTranPickErrorProc Ended !!!\n\n" );
		}
		
		if ( niceTranNotranErrorProc() < 0 ) {
			logger( 0, "NiceTranNotranErrorProc Error !!!\n" );
			fnCleanup(-1);
		} else {
			logger( "NiceTranNotranErrorProc Ended !!!\n\n" );
		}

		if ( niceTranRepairErrorProc() < 0 ) {
			logger( 0, "NiceTranRepairErrorProc Error !!!\n" );
			fnCleanup(-1);
		} else {
			logger( "NiceTranRepairErrorProc Ended !!!\n\n" );
		}
		
		if ( niceCashLackErrorProc() < 0 ) {
			logger( "NiceCashLackErrorProc Error !!!\n" );
			fnCleanup(-1);
		} else {
			logger( "NiceCashLackErrorProc Ended !!!\n\n" );
		}
		
		if ( niceInqRemAmtErrorProc() < 0 ) {
			logger( 0, "NiceInqRemAmtErrorProc Error !!!\n" );
			fnCleanup(-1);
		} else {
			logger( "NiceInqRemAmtErrorProc Ended !!!\n\n" );
		}

		if ( shCashLackErrorProc() < 0 ) {
			logger( 0, "SHCashLackErrorProc Error !!!\n" );
			fnCleanup(-1);
		} else {
			logger( "SHCashLackErrorProc Ended !!!\n\n" );
		}
		
		if( (lCurrTime >= 11500 && lCurrTime < 12000 ) ||
			(lCurrTime >= 21500 && lCurrTime < 22000 ) ||
			(lCurrTime >= 31500 && lCurrTime < 32000 ) ||
			(lCurrTime >= 41500 && lCurrTime < 42000 ) ||
			(lCurrTime >= 51500 && lCurrTime < 52000 ) ||
			(lCurrTime >= 61500 && lCurrTime < 62000 ) ||
			(lCurrTime >= 71500 && lCurrTime < 72000 ) ||
			(lCurrTime >= 81500 && lCurrTime < 82000 ) ||
			(lCurrTime >= 91500 && lCurrTime < 92000 ) ||
			(lCurrTime >= 101500 && lCurrTime < 102000 ) ) {
			hRealHolyYn = errMonMapper.pickupRealHolyYN();
			
			if (hRealHolyYn != null) {
				/* 20120103 운총 조규석 요청 농협은 01시~10시까지 미개국 발생 */
				if ( dgNotOpenErrorProc("011") < 0 ) {
					logger( 0, "DGNotOpenErrorProc-011 Error !!!\n" );
					fnCleanup(-1);
				}
				
				if( lCurrTime >= 61500 ) {
					/* 6시 시점은 제주 은행만 */
					if ( dgNotOpenErrorProc("035") < 0 )
					{
						logger( 0, "DGNotOpenErrorProc-035 Error !!!\n" );
						fnCleanup(-1);
					}
				
					if( lCurrTime >= 71500 )
					{
						if ( dgNotOpenErrorProc("0DJ") < 0 )
						{
							logger( 0, "DGNotOpenErrorProc-0DJ Error !!!\n" );
							fnCleanup(-1);
						}
						
						if ( dgNotOpenErrorProc("0HD") < 0 )
						{
							logger( 0, "DGNotOpenErrorProc-0HD Error !!!\n" );
							fnCleanup(-1);
						}
						
						/* 대구은행일 경우 일요일과 공휴일의 경우 7:30분 미개국은 발생시키지 않도록 한다.	*/
						if( "0".equals(hRealHolyYn) || ("1".equals(hRealHolyYn) && lCurrTime >= 83000 )) {
							if ( dgNotOpenErrorProc("031") < 0 ) {
								logger( 0, "DGNotOpenErrorProc-031 Error !!!\n" );
								fnCleanup(-1);
							}
						}
						
						logger( "DGNotOpenErrorProc Ended !!!\n\n" );
					}
				}
			}
		}
		/* 매일 23시경 마사회 경륜 미운영 사이트 엠티센서장애 강제 완료 처리 */
		else if( (lCurrTime >= 230000 && lCurrTime < 230500 ) ) {
			if ( niceEemptySensorRepairProc() < 0 ) {
				logger( "NiceEemptySensorRepairProc Error !!!\n" );
				fnCleanup(-1);
			}
			
			logger( "NiceEemptySensorRepairProc Ended !!!\n" );
		}
		/* 매주 월요일 10시경 전주 CD-VNA 기기의 IC 무거래 기기 장애 통보 */
		else if( (lCurrTime >= 100000 && lCurrTime < 100500 ) ) {
			short nRtn = niceTranICNotranErrorProc();
			/* -9: 월요일이 아님... 정상처리 */
			if ( nRtn < 0 && nRtn != -9) {
				logger( "NiceTranICNotranErrorProc Error !!!\n" );
				fnCleanup(-1);
			}
			
			logger( "NiceTranICNotranErrorProc Ended !!!\n" );
		}
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @return
	 */
	private short niceTranICNotranErrorProc() {
		int hWeek = errMonMapper.pickupWeekDay();

		if( hWeek != 2 ) {
			logger( ">>> 월요일에만 실행 \n"  );
			return (-9);
		}
		
		List<HashMap<String, Object>> list = errMonMapper.selectNiceTranICNotranErrorProc();
		
		for (HashMap<String, Object> obj : list) {
			try {
				niceTranErrorSendProc( 10, (String)obj.get("MAC_NO"));
			} catch(Exception e) {
				logger( "NiceTranICNotranErrorProc Error !!!\n" );
				fnCleanup(-1);
			}
		}
		
		return 0;
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @return
	 */
	private int niceEemptySensorRepairProc() {
		List<HashMap<String, Object>> list = errMonMapper.selectNiceEemptySensorRepairProc();
		
		for (HashMap<String, Object> obj : list) {
			errMonMapper.updateNiceEemptySensorRepairProc(obj);
			errMonMapper.updateNiceEemptySensorRepairProc2(obj);
		}
		
		return 0;
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 */
	private int niceCashLackRepairProc() {
		List<HashMap<String, Object>> list = errMonMapper.selectNiceCashLackRepairProc();

		for (HashMap<String, Object> obj : list) {
			errMonMapper.updateNiceCashLackRepairProc(obj);
			errMonMapper.updateNiceCashLackRepairProc2(obj);
		}
		
		return 0;
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 */
	private int niceDoorCheckErrorProc() {
		List<String> list = errMonMapper.selectNiceDoorCheckErrorProc();
		
		for (String mapNo : list) {
			try {
				niceTranErrorSendProc( 15, mapNo );
			} catch(Exception e) {
				logger( "N05 NiceDoorCheckErrorProc Error !!!\n" );
				fnCleanup(-1);
			}
		}
		
		return 0;
	}
	
	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
		01. FUNCTION ID      : {SHCashLackErrorProc}							
		02. FUNCTION NAME    : {현재시재 테이블중 1000만원 미만인 장애 발생}	
		03. 작  성  자       : {방혜진}											
		04. 작 성 일 자      : {2008/10/27}										
		05. 입력인자         : {Data 버퍼}										
		06. 출력인자         : {}												
		07. RETURN 값        : {short(0:success, <0:error)} 		            
		08. REMARKS 사항     : {20081027 김희천 대리 요청}						
	 * </pre>
	 * 
	 */
	private int shCashLackErrorProc() {
		List<HashMap<String, Object>> list;
		String orgCd, branchCd, macNo, szMsg;

		/* 배치에서 기관의 현재시재 화일을 테이블에 일괄 넣어 놓은 사항을 읽어
			1000만원 미만의 기기목록을 구하여 장애발생전문을 만든다.	*/
		{
			list = errMonMapper.selectSHCashLackErrorProc();
			
			for (HashMap<String, Object> obj : list) {
				orgCd = obj.get("ORG_CD").toString();
				branchCd = obj.get("BRANCH_CD").toString();
				macNo = obj.get("MAC_CD").toString();
				szMsg = "[현재시재-" + obj.get("REMAIN_AMT").toString() + "원]";
				
				try {
					atmESErrorSendProc( orgCd, branchCd, macNo, "NE999", szMsg, "1" );
				} catch(Exception e) {
					logger( "N05 NiceTranErrorSendProc Error !!!\n" );
					fnCleanup(-1);
				}
			}
		}

		/* 배치에서 기관의 현재시재 화일을 테이블에 일괄 넣어 놓은 사항을 읽어
			1000만원 이상의 기기목록을 구하여 장애복구전문을 만든다.	*/
		{
			list = errMonMapper.selectSHCashLackErrorProc2();
			
			for (HashMap<String, Object> obj : list) {
				orgCd = obj.get("ORG_CD").toString();
				branchCd = obj.get("BRANCH_CD").toString();
				macNo = obj.get("MAC_CD").toString();
				szMsg = obj.get("ORG_MSG").toString() + "[현재시재-" + obj.get("REMAIN_AMT").toString() + "원]";

				try {
					atmESErrorSendProc( orgCd, branchCd, macNo, "NE999", szMsg, "1" );
				} catch(Exception e) {
					logger( "N05 NiceTranErrorSendProc Error !!!\n" );
					fnCleanup(-1);
				}
			}
		}

		return 0;
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
		01. FUNCTION ID      : {NiceInqRemAmtErrorProc}							
		02. FUNCTION NAME    : {현금부족이 아닌 상태에서  연속 7회 이상 		
								조회 업무만 올라오는 경우 잔액조회 장애 발생 처리
		03. 작  성  자       : {방혜진}											
		04. 작 성 일 자      : {2008/03/06}										
		05. 입력인자         : {Data 버퍼}										
		06. 출력인자         : {}												
		07. RETURN 값        : {short(0:success, <0:error)} 		            
		08. REMARKS 사항     : {20080306 김희천 대리 요청}						
	 * </pre>
	 * 
	 */
	private int niceInqRemAmtErrorProc() {
		List<HashMap<String, Object>> list = errMonMapper.selectNiceInqRemAmtErrorProc(hOrgCd, hBranchCd);
		String prevMacNo = null, n05Str = null, atmDealNo = null, outAtmAtmDealNo = null;
		String macNo, dealType;
		
		for (HashMap<String, Object> obj : list) {
			macNo = obj.get("MAC_NO").toString();
			dealType = obj.get("DEAL_TYPE").toString();
			atmDealNo = obj.get("ATM_DEAL_NO").toString();
			
			if (prevMacNo != null && prevMacNo.equals(macNo)) {
				/* 새로운 기기 list로 넘어가면 이전 기기의 마지막 정상출금 atm_deal_no 혹은	*/
				/* 장애를 발생시켰던 ATM_DEAL_NO를 테이블에 저장 							*/
				
				if (n05Str != null && n05Str.length() >= 7) {
					try {
						niceTranErrorSendProc( 5, prevMacNo );
					} catch(Exception e) {
						logger( "N05 NiceTranErrorSendProc Error !!!\n" );
						fnCleanup(-1);
					}
					
					n05Str = null;
					outAtmAtmDealNo = atmDealNo;
				}
				
				if (outAtmAtmDealNo != null && outAtmAtmDealNo.length() > 0) {
					try {
						niceInqRemAmtErrorUpdateProc( prevMacNo, outAtmAtmDealNo );
					} catch(Exception e) {
						logger( "NiceInqRemAmtErrorUpdateProc Error !!!\n" );
						fnCleanup(-1);
					}
				}
				
				n05Str = null;
				outAtmAtmDealNo = null;
			}
			
			// 조회
			if ("3".equals(dealType)) {
				n05Str = dealType;
			} else {
				n05Str = null;
				outAtmAtmDealNo = atmDealNo;
			}
			
			prevMacNo = macNo;
		}
		
		if (prevMacNo != null) {
			if (outAtmAtmDealNo.length() > 0) {
				if (n05Str.length() >= 7) {
					try {
						niceTranErrorSendProc( 5, prevMacNo );
					} catch(Exception e) {
						logger( "NiceInqRemAmtErrorUpdateProc Error !!!\n" );
						fnCleanup(-1);
					}
					
					n05Str = null;
					outAtmAtmDealNo = null;
					
					outAtmAtmDealNo = atmDealNo;
				}
				
				try {
					niceInqRemAmtErrorUpdateProc(prevMacNo, outAtmAtmDealNo);
				} catch(Exception e) {
					logger( "NiceInqRemAmtErrorUpdateProc Error <<!!!\n" );
					fnCleanup(-1);
				}
			}
		}

		return 0;
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
		01. FUNCTION ID     : {NiceCashLackErrorProc}					
		02. FUNCTION NAME   : {현금부족 ROUTINE을 CALL하는 MAIN 함수 }	
		03. 작  성  자		: {방혜진}									
		04. 작 성 일 자      	: {2007/04/05}							
		05. 입력인자         	: {}									
		06. 출력인자         	: {}									
		07. RETURN 값       	: {short(0:success, <0:error)} 		     
		08. REMARKS 사항    	: {실시간 현금부족 감시, 장애 발생 처리}
	 * </pre>
	 * 
	 */
	private int niceCashLackErrorProc() {
		List<HashMap<String, Object>> list = errMonMapper.selectNiceCashLackErrorProc();
		String macNo;
		int shortCashStatus;
		
		for (HashMap<String, Object> obj : list) {
			macNo = obj.get("MAC_NO").toString();
			shortCashStatus = (Integer)obj.get("SHORT_CASH_STATUS");

			if ( shortCashStatus == -1 ) {
				/* 기준금액 없음 장애 발생 */
				try {
					niceTranErrorSendProc( 13, macNo );
				} catch(Exception e) {
					logger( "N08 NiceTranErrorSendProc Error !!!\n" );
					fnCleanup(-1);
				}
			} else if ( shortCashStatus == -2 ) {
				/* 현금부족(기준금액) 장애 발생 */
				try {
					niceTranErrorSendProc( 12, macNo );
				} catch(Exception e) {
					logger( "N07 NiceTranErrorSendProc Error !!!\n" );
					fnCleanup(-1);
				}
			} else if ( shortCashStatus == -3 ) {
				/* 현금부족예보(기준금액) 장애 발생 */
				try {
					niceTranErrorSendProc( 14, macNo );
				} catch(Exception e) {
					logger( "N07 NiceTranErrorSendProc Error !!!\n" );
					fnCleanup(-1);
				}
			}
		}

		return 0;
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 */
	private int niceTranRepairErrorProc() {
		List<HashMap<String, Object>> list = errMonMapper.selectNiceTranRepairErrorProc(hOrgCd, hBranchCd, "NI906");
		String prevMacNo = null, prevAtmDealNo = null;
		String macNo, dealType, atmDealNo;
		
		for(HashMap<String, Object> obj : list) {
			macNo = obj.get("mac_no").toString();
			dealType = obj.get("deal_type").toString();
			atmDealNo = obj.get("atm_deal_no").toString();
			
			if (prevMacNo != null && prevMacNo.equals(macNo)) {
				if (dealType.length() >= 3) {
					try {
						niceTranRepairErrorUpdateProc( prevMacNo, prevAtmDealNo);
					} catch(Exception e) {
						logger( "NiceTranRepairErrorUpdateProc Error !!!\n" );
						fnCleanup(-1);
					}
				}
				
				// if ( strstr( deal_type, "333" ) )
				if (dealType.indexOf("333") != -1) {
					try {
						niceTranErrorSendProc( 6, prevMacNo );
					} catch(Exception e) {
						logger( "N06 NiceTranErrorSendProc Error !!!\n" );
						fnCleanup(-1);
					}
					
					prevAtmDealNo = atmDealNo;
				}

				dealType = null;
			}
			
			if (!"3".equals(dealType)) {
				prevAtmDealNo = atmDealNo;
				prevMacNo = macNo;
			}
		}

		return 0;
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 */
	private int niceTranNotranErrorProc() {
		List<HashMap<String, Object>> list = errMonMapper.selectNiceTranNotranErrorProc();
		String macNo;
		int[] naryWeek = new int[7];
		int toDayWeek, totNoTrade, oneOperTime, noTradeBase;
		int nIdx;
		int isSkip = 0;
		
		for (HashMap<String, Object> obj : list) {
			macNo = obj.get("MAC_NO").toString();
			toDayWeek = Integer.parseInt(obj.get("TO_DAY_WEEK").toString());
			naryWeek[0] = Integer.parseInt(obj.get("NO_TRADE_SUN").toString());
			naryWeek[1] = Integer.parseInt(obj.get("NO_TRADE_MON").toString());
			naryWeek[2] = Integer.parseInt(obj.get("NO_TRADE_TUE").toString());
			naryWeek[3] = Integer.parseInt(obj.get("NO_TRADE_WED").toString());
			naryWeek[4] = Integer.parseInt(obj.get("NO_TRADE_THU").toString());
			naryWeek[5] = Integer.parseInt(obj.get("NO_TRADE_FRI").toString());
			naryWeek[6] = Integer.parseInt(obj.get("NO_TRADE_SAT").toString());
			noTradeBase = Integer.parseInt(obj.get("NO_TRADE_BASE").toString());
			totNoTrade = Integer.parseInt(obj.get("TOT_NO_TRADE").toString());
			oneOperTime = Integer.parseInt(obj.get("ONE_OPER_TIME").toString());
			
			for (int i=0; i<7; i++) {
				/* 전체 무거래 시간이 하루 운영 시간을 초과할경우
					전일(요일) 운영 기기인지 체크하여 해당 요일 운영하지 않는 기기라면
					운영시간을 빼서 무거래 반영 하도록 20110414 */
				if (totNoTrade > oneOperTime) {
					nIdx = (toDayWeek - 1) - (i + 1);
					
					if( nIdx < 0 ) {
						nIdx += 7 ;
					}
					
					if( naryWeek[ nIdx ] == 0 ) {
						totNoTrade -= oneOperTime;
						
						if( totNoTrade < noTradeBase ) {
							isSkip = 1;
							break;
						}
					}					
				}
			}
			
			if (totNoTrade >= noTradeBase) {
				isSkip = 0;
			}
			
			if (isSkip == 0) {
				try {
					niceTranErrorSendProc( 4, macNo);
				} catch(Exception e) {
					logger( "NiceTranNotranErrorProc Error !!!\n" );
					fnCleanup(-1);
				}
			}
			
			isSkip = 0;
		}

		return 0;
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
		01. FUNCTION ID      : {NiceTranPickErrorProc}							
		02. FUNCTION NAME    : {집계처리 ROUTINE을 CALL하는 MAIN 함수 }			
		03. 작  성  자       : {씨에스 테크놀로지/채정엽}						
		04. 작 성 일 자      : {2003/04/01}										
		05. 입력인자         : {Data 버퍼}										
		06. 출력인자         : {}												
		07. RETURN 값        : {short(0:success, <0:error)} 		            
		08. REMARKS 사항     : {집계 Data 처리}									
	 * </pre>
	 * 
	 */
	private int niceTranPickErrorProc() {
		List<HashMap<String, Object>> list = errMonMapper.selectNiceTranPickErrorProc(hOrgCd, hBranchCd);
		String prevMacNo = null, prevAtmDealNo = null;
		String macNo, dealStatus, atmDealNo;
		StringBuilder strN02 = new StringBuilder();
		StringBuilder strN03 = new StringBuilder();
		StringBuilder strDealStatus = new StringBuilder();
		
		for (HashMap<String, Object> obj : list) {
			macNo = obj.get("MAC_NO").toString();
			dealStatus = obj.get("DEAL_STATUS").toString();
			atmDealNo = obj.get("ATM_DEAL_NO").toString();
			
			if (prevMacNo != null && prevMacNo.equals(macNo)) {
				/*
				logger( ">>>>mac_no[%s][%s] deal_no[%s][%s] deal_status[%s]\n",
				hMAC_NO, prev_mac_no, hATM_DEAL_NO, prev_atm_deal_no, deal_status );
				*/
				if ( strDealStatus.length() >= 3 ) {
					try {
						niceTranErrorUpdateProc( prevMacNo, prevAtmDealNo);
					} catch(Exception e) {
						logger( "NiceTranErrorProc Error !!!\n" );
						fnCleanup(-1);
					}
				}
				
				strN02 = new StringBuilder();
				strN03 = new StringBuilder();
				strDealStatus = new StringBuilder();
				prevAtmDealNo = null;
				// sprintf( prev_atm_deal_no,	"%05d", atoi(hATM_DEAL_NO)-1 );
				prevAtmDealNo = StringUtils.leftPad(String.valueOf(Integer.parseInt(atmDealNo) - 1), 5, '0');
			}
			
			if ("1".equals(dealStatus)) {
				strN02.append(dealStatus);
				
				// if ( strcmp( N03str, "1" ) ) strcat( N03str, hDEAL_STATUS );
				if (strN03.indexOf("1") != -1) strN03.append(dealStatus);
				
				// if ( ! strncmp( N02str, "11111", 5 ) ) {
				if (!"11111".equals(strN02.substring(0, 5))) {
					try {
						niceTranErrorSendProc( 2, macNo );
					} catch(Exception e) {
						logger( "N02 NiceTranErrorSendProc Error !!!\n" );
						fnCleanup(-1);
					}
					
					strN02 = new StringBuilder();
					prevAtmDealNo = atmDealNo;
				}

				// if ( ! strncmp( N03str, "101", 3 ) ) {
				if (!"101".equals(strN03.substring(0, 3))) {
					try {
						niceTranErrorSendProc( 3, macNo);
					} catch(Exception e) {
						logger( "N03 NiceTranErrorSendProc Error !!!\n" );
						fnCleanup(-1);
					}
					
					strN03 = new StringBuilder();
					/* 그다음에 1111 이들어오면 N02에러를 발생시키지 못하기 때문에 
									sprintf( prev_atm_deal_no,	"%s", hATM_DEAL_NO );
					*/
				}
			} else {
				strN02 = new StringBuilder();
				
				if ( !"1".equals(strN03.substring(0, 1))) {
					strN03.append(dealStatus);
				} else {
					strN03 = new StringBuilder();
				}
			}
			
			if ( !"1".equals(dealStatus) ) {
				prevAtmDealNo = atmDealNo;
			}

			prevMacNo = macNo;
			strDealStatus.append(dealStatus);
		}
		
		return 0;
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
		01. FUNCTION ID      : {DGNotOpenErrorProc}								
		02. FUNCTION NAME    : {미개국기기 장애 발생}							
		03. 작  성  자       : {방혜진}											
		04. 작 성 일 자      : {2009/11/17}										
		05. 입력인자         : {Data 버퍼}										
		06. 출력인자         : {}												
		07. RETURN 값        : {short(0:success, <0:error)} 		            
		08. REMARKS 사항     : {20091117 운영중이면서 A/S접수가 없는기기중 		
							 : 개국이 안들어온 기기에대해 미개국 장애 발생시킴}	
	 * </pre>
	 * 
	 * @param string
	 * @return
	 */
	private int dgNotOpenErrorProc(String orgCd) {
		List<HashMap<String, Object>> list = errMonMapper.selectDGNotOpenErrorProc(orgCd);
		String hOrgCd, hBranchCd, hMacNo;
		
		for (HashMap<String, Object> obj : list) {
			hOrgCd = obj.get("org_cd").toString();
			hBranchCd = obj.get("branch_cd").toString();
			hMacNo = obj.get("mac_no").toString();
			
			try {
				atmESErrorSendProc(hOrgCd, hBranchCd, hMacNo, "USR01", "", "1");
			} catch(Exception e) {
				logger( "USR01 ATM_ESErrorSendProc Error !!!\n" );
				fnCleanup(-1);
			}
		}
		
		return 0;
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param orgCd
	 * @param branchCd
	 * @param macNo
	 * @param string
	 * @param string2
	 * @param string3
	 * @return
	 * @throws Exception 
	 */
	private void atmESErrorSendProc(String orgCd, final String branchCd, final String macNo, final String errorCd, final String msg, final String szCreateYN) throws Exception {

		// format_type : ES
		// msg_type : ES_REQ				"0500"
		// work_type : IDX_ES_ERR_STATE "0110"

        //전문의 필수값 설정
        //MsgBrokerConf msgBrokerConf = new MsgBrokerConf(ORG_CD, "FORMAT_TYPE", "MSG_TYPE", "WORK_TYPE");
        MsgBrokerConf msgBrokerConf = new MsgBrokerConf(orgCd, "ES", "0500", "0110");
        
        //콜백 인터페이스 구현
        MsgBrokerCallBack<Object> callback = new MsgBrokerCallBack<Object>()
        {
            //전문 호출전
            //전문에 Message 셋팅
            @Override
            public void doPreCallBroker(MsgParser parsed, Object empty) throws Exception {
            	SimpleDateFormat dateFmt = new SimpleDateFormat("yyyyMMddHHmmss");
            	String curDate = dateFmt.format(Calendar.getInstance().getTime());

            	// memcpy( suSendHead.org_cd		, org_cd , LEN_ORG_CD );
            	// memcpy( suSendHead.ret_cd_src	, " " , 1 );
            	parsed.setString("CM.msg_id", "sSERVER");
            	// sprintf( suSendHead.body_len, "%0*d", LEN_BODY_LEN, EM_ERR_STATE_LEN );
            	// sprintf( suSendHead.trans_date, "%08ld", GetCurDate() );
            	// sprintf( suSendHead.trans_time, "%06ld", GetCurTime() );
            	// memcpy( suSendHead.format_type	, "ES" , LEN_FORMAT_TYPE );					
            	// memcpy( suSendHead.msg_type     , ES_REQ , LEN_MSG_TYPE	);
            	// memcpy( suSendHead.work_type    , WORK_TYPE_ES[IDX_ES_ERR_STATE] , LEN_WORK_TYPE );

            	parsed.setString("create_date", curDate.substring(0, 8));
            	parsed.setString("create_time", curDate.substring(8));
            	parsed.setString("brch_cd", branchCd);
            	parsed.setString("mac_no", macNo);
            	parsed.setString("atm_state", "9");
            	parsed.setString("error_hw_yn", szCreateYN);
            	parsed.setString("error_cd", errorCd);
            	parsed.setString("memo", msg);

            }

            //전문 호출후
            //결과값 얻기
            @Override
            public void doPostCallBroker(MsgParser parsed, Object params) throws Exception {}
        };

        //전문호출 객체 생성
        MsgBrokerCallAgent<Object> agent = new MsgBrokerCallAgent<Object>(msgBrokerConf, null, callback);
        
        agent.callBrokerAync();
        
        errMonMapper.updateATMESErrorSendProc(szCreateYN, orgCd, branchCd, macNo);
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param i
	 * @param object
	 * @return
	 * @throws Exception 
	 */
	private void niceTranErrorSendProc(final int errNo, String macNo) throws Exception {
		// org_cd : 096
		// format_type : NS
		// msg_type : N200
		// work_type : 0120

        //전문의 필수값 설정
        //MsgBrokerConf msgBrokerConf = new MsgBrokerConf(ORG_CD, "FORMAT_TYPE", "MSG_TYPE", "WORK_TYPE");
        MsgBrokerConf msgBrokerConf = new MsgBrokerConf("096", "NS", "N200", "0120");
        
        //콜백 인터페이스 구현
        MsgBrokerCallBack<String> callback = new MsgBrokerCallBack<String>()
        {
            //전문 호출전
            //전문에 Message 셋팅
            @Override
            public void doPreCallBroker(MsgParser parsed, String params) throws Exception {
            	SimpleDateFormat dateFmt = new SimpleDateFormat("yyyyMMddHHmmss");
            	String curDate = dateFmt.format(Calendar.getInstance().getTime());
            	
            	/*
            	memcpy( suSendHead.org_cd		, "096" , 3 );
            	memcpy( suSendHead.ret_cd_src	, " " , 1 );
            	*/
            	parsed.setString("CM.msg_id" , "sSERVER");
            	
            	/*
            	sprintf( suSendHead.body_len, "%0*d", LEN_BODY_LEN, EM_ERR_STATE_LEN );
            	sprintf( suSendHead.trans_date, "%08ld", GetCurDate() );
            	sprintf( suSendHead.trans_time, "%06ld", GetCurTime() );
            	memcpy( suSendHead.format_type	, "ES" , LEN_FORMAT_TYPE );					
            	memcpy( suSendHead.msg_type     , ES_REQ , LEN_MSG_TYPE	);
            	memcpy( suSendHead.work_type    , WORK_TYPE_ES[IDX_ES_ERR_STATE] , LEN_WORK_TYPE );
            	*/
            	
            	parsed.setString("create_date", curDate.substring(0, 8));
            	parsed.setString("create_time",	curDate.substring(8));
            	parsed.setString("brch_cd", "9600");

                parsed.setString("mac_no", params);
                parsed.setString("network_info", "301" );
                                                      
                parsed.setString("atm_off_day", "00000000" );
                parsed.setString("atm_off_time", "000000" );
                parsed.setString("atm_state", "0" );
                parsed.setString("atm_cash", "000000000" );
                parsed.setString("atm_dummy", "00000000000000000000" );
                parsed.setString("user_made_err", String.valueOf(errNo));
                
                for(int i=0; i<29; i++ ) {
                      parsed.setString("atm_hw_error[" + i + "]", "000" );
                }
                
                parsed.setString("atm_monitor", "0000000000000000000000000000000000" );
                /*
            	sprintf( suNiceErr.pgm_version,	"%*s",	8,	" " );
            	sprintf( suNiceErr.serial_no,	"%*s",	20,	" " );
            	sprintf( suNiceErr.filler, 		"%*s", 191, " ");
            	*/
            }

            //전문 호출후
            //결과값 얻기
            @Override
            public void doPostCallBroker(MsgParser parsed, String params) throws Exception {}
        };

        //전문호출 객체 생성
        MsgBrokerCallAgent<String> agent = new MsgBrokerCallAgent<String>(msgBrokerConf, macNo, callback);
        
        agent.callBrokerAync();
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param prevMacNo
	 * @param outAtmAtmDealNo
	 */
	private void niceInqRemAmtErrorUpdateProc(String macNo, String atmDealNo) {
		if (errMonMapper.updateNiceInqRemAmtErrorUpdateProc(macNo, atmDealNo) == 0) {
			errMonMapper.insertNiceInqRemAmtErrorUpdateProc(macNo, atmDealNo);
		}
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param prevMacNo
	 * @param prevAtmDealNo
	 */
	private void niceTranRepairErrorUpdateProc(String macNo, String atmDealNo) {
		if (errMonMapper.updateNiceTranRepairErrorUpdateProc(macNo, atmDealNo) == 0) {
			errMonMapper.insertNiceTranRepairErrorUpdateProc(macNo, atmDealNo);
		}
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param prevMacNo
	 * @param prevAtmDealNo
	 */
	private void niceTranErrorUpdateProc(String macNo, String atmDealNo) {
		if (errMonMapper.updateNiceTranErrorUpdateProc(macNo, atmDealNo) == 0) {
			errMonMapper.insertNiceTranErrorUpdateProc(macNo, atmDealNo);
		}
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param i
	 * @param msg
	 */
	private void logger(int i, String msg) {
		System.out.print(msg);
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param msg
	 */
	private void logger(String msg) {
		System.out.print(msg);
	}

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param i
	 */
	private void fnCleanup(int Sig) {
		logger( "ERRMon Main Process fnCleanup Start...Signal=[" + Sig + "]\n" );

		System.exit(0);
	}

}
