/** 
 * com.nicetcm.nibsplus.filemng.service.impl.FileSendTest
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 10. 14.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.filemng.service.impl;

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
public class FileSendTest {
	public static void main(String[] args) {
		// System.out.print("%.*s%.*s%.*s%.*s".replaceAll("%.", "%").replaceFirst("\\*", "15").replaceFirst("\\*", "20").replaceFirst("\\*", "25").replaceFirst("\\*", "30"));
		// System.out.println("%10D".replaceFirst("%.?\\d*\\w", "15"));
		// System.out.println(String.format("%010d", 15));
		// System.out.println(String.format("%02.02f", 19.6543));

		System.out.println("2342343ㅁㄴㅇㄻㄴㅇㄹasdfasf".replaceAll("[^ㄱ-핳]", ""));
		
		/*
		System.out.println(String.format("S%s%3s", " ", " "));
		System.out.println(String.format("%s/%s", " ", " "));
		System.out.println(String.format("File 생성 Start [%s]-TransDate[%s], OrgCd[%s]\n", " ", " ", " "));
		System.out.println(String.format("해당 기관없음 [%s]\n", " "));
		System.out.println(String.format("file 생성 실패 [%s]\n", " "));
		System.out.println(String.format("file 생성 OK !!! [%s]\n", " "));
		System.out.println(String.format("%s/ftpscript", " "));
		System.out.println(String.format("%s/ftpscriptchk", " "));
		System.out.println(String.format("ftp 전송 스크립트 실패 HOST[%s] ID[%s] DestPath[%s] SrcPath[%s] FileName[%s]\n", " ", " ", " ", " ", " "));
		System.out.println(String.format("ftp 전송 스크립트 완료 HOST[%s] ID[%s] DestPath[%s] SrcPath[%s] FileName[%s]\n", " ", " ", " ", " ", " "));
		System.out.println(String.format("ftp 확인 스크립트 실패 HOST[%s] ID[%s] DestPath[%s] SrcPath[%s] FileName[%s]\n", " ", " ", " ", " ", " "));
		System.out.println(String.format("ftp 확인 스크립트 완료 HOST[%s] ID[%s] DestPath[%s] SrcPath[%s] FileName[%s]\n", " ", " ", " ", " ", " "));
		System.out.println(String.format("1%sKMC%58s\n", " ", " "));
		System.out.println(String.format("%1s%8s%6s%16s%8s%10s%1s%12s%8s\n", " ", " ", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("3%08d%013d%48s\n", 0, 0, " " ));
		System.out.println(String.format(">>> [GetCityTranData] 전영업일 없음. \n"));
		System.out.println(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 않음 \n", " ", " "));
		System.out.println(String.format(">>> [GetCityTranData] 전전영업일 없음. \n"));
		System.out.println(String.format("H%8s%191s\n", " ", " " ));
		System.out.println(String.format("D%2s96%8s%8s%8s%8s%8s%6s%60s%30s%20s%1s%2s%36s\n", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("T%8s%010d%181s\n", " ", 0, " " ));
		System.out.println(String.format(">>> [GetCityTranData] 전영업일 없음. \n"));
		System.out.println(String.format("1%sNIC000000%52s\n", " ", " " ));
		System.out.println(String.format("%1s%8s%6s%16s%4s%7s%7s%1s%12s%8s\n", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("3%08d%013d%48s\n", 0, 0, " " ));
		System.out.println(String.format("BHATM-INFO%8s%232s\n", " ", " " ));
		System.out.println(String.format("BD03102%16s%50s%8s080022 8252%6s%100s%52s\n", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("BT%08d%240s\n", 0, " " ));
		System.out.println(String.format("JH0001110000000%7s%s%70s\n", " ", " ", " " ));
		System.out.println(String.format("JH0001%2s%07d%4s%12s%6s%12s%1s0%10s%3s%17s%1s%8s%10s\n", " ", 0, " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("JH000133999999900000000000000000000%08d%012d%08d%012d%25s\n", 0, 0, 0, 0, " " ));
		System.out.println(String.format("HEADER  96        %s%4s\n", " ", " " ));
		System.out.println(String.format("%8s%6s%19s%18s%12s\n", " ", " ", " ", " ", " "));
		System.out.println(String.format("TRAILER 96        %012d\n", 0));
		System.out.println(String.format(">>> [GetHNetTranData] FileOpenError [%s]\n", " "));
		System.out.println(String.format("HB00011100000009A0000000%8s%28s\n", " ", " " ));
		System.out.println(String.format("%6s%2s%07d%6s%12s%6s%12s%1s%8s\n", " ", " ", 0, " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("HB00013399999999A%07d%36s\n", 0, " " ));
		System.out.println(String.format("%07d", 0));
		System.out.println(String.format("%7s", " "));
		System.out.println(String.format("HB0001110000000J960000000%8s%27s\n", " ", " " ));
		System.out.println(String.format("%6s%2s%07d%6s%13s%6s%12s%1s%7s\n", " ", " ", 0, " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("HB0001339999999J96%07d%35s\n", 0, " " ));
		System.out.println(String.format("%07d", 0));
		System.out.println(String.format("%7s", " "));
		System.out.println(String.format("H%sATM%6s6%83s\n", " ", "NICE", " " ));
		System.out.println(String.format("%1s%6s%6s%19s%8s%7s%6s%1s%1s%12s%8s%25s\n", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("T%06d%013d%80s\n", 0, 0, " " ));
		System.out.println(String.format(">>> [GetCityTranData] 전영업일 없음. \n"));
		System.out.println(String.format(">>> [GetCityTranData] 휴일 전송 안함 [%s]\n", " "));
		System.out.println(String.format(">>> [GetCityTranData] 전일 없음. \n"));
		System.out.println(String.format("1NIC%s000000%52s\n", " ", " " ));
		System.out.println(String.format("%1s%8s%6s%16s%4s%7s%7s%1s%12s%8s\n", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("DealDate[%s]-PreDate[%s]-preActDate[%s]\n", " ", " ", " "));
		System.out.println(String.format("3%08d%013d%48s\n", 0, 0, " " ));
		System.out.println(String.format(">>> [GetLGTranData] 전영업일 없음. \n"));
		System.out.println(String.format(">>> [GetLGTranData] 휴일 전송 안함 [%s]\n", " "));
		System.out.println(String.format(">>> [GetLGTranData] 전일 없음. \n"));
		System.out.println(String.format("H%8s%8s%8s96%43s\n", " ", " ", " ", " " ));
		System.out.println(String.format("%1s%2s%8s%6s%12s%16s%7s%4s%14s\n", " ", " ", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("T%06d%013d%50s\n", 0, 0, " " ));
		System.out.println(String.format(">>> [GetLGTranData] 전일 없음.\n"));
		System.out.println(String.format("HD%8s%8s89%180s\n", " ", " ", " " ));
		System.out.println(String.format("%2s89%12s%16s%8s%6s%13s%1s00  %6s%2s%20s%1s%107s\n", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("TR89%010d%013d%010d%013d%010d%013d%010d%013d%010d%013d%81s\n", 0,0,0,0, 0,0,0,0, 0,0, " " ));
		System.out.println(String.format(">>> [GetNongHTranData] 전영업일 없음. \n"));
		System.out.println(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 않음 \n", " ", " "));
		System.out.println(String.format(">>> [GetNongHTranData] 전일 없음. \n"));
		System.out.println(String.format(">>> [GetNongHTranData] FileOpenError [%s]\n", " "));
		System.out.println(String.format("ATM03311000000011960000000%6s%138s\n", " ", " " ));
		System.out.println(String.format("ATM03322%07d", 0));
		System.out.println(String.format("%8s%6s%12s%1s%5s%2s%4s%6s%4s%2s%2s%17s%12s%5s%2s%14s%2s%51s\n", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("ATM0333399999991196%07d%8s%136s\n", 0, " ", " " ));
		System.out.println(String.format("%07d", 0));
		System.out.println(String.format("%7s", " "));
		System.out.println(String.format(">>> [GetNongHTranData] 전영업일 없음. \n"));
		System.out.println(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 않음 \n", " ", " "));
		System.out.println(String.format(">>> [GetNongHTranData] 전일 없음. \n"));
		System.out.println(String.format(">>> [GetNongHTranData] FileOpenError [%s]\n", " "));
		System.out.println(String.format("ATM03311000000011960000000%6s%138s\n", " ", " " ));
		System.out.println(String.format("ATM03322%07d", 0));
		System.out.println(String.format("%8s%6s%12s%1s%5s%2s%4s%6s%4s%2s%2s%17s%12s%5s%2s%14s%2s%51s\n", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("ATM0333399999991196%07d%8s%136s\n", 0, " ", " " ));
		System.out.println(String.format("%07d", 0));
		System.out.println(String.format("%7s", " "));
		System.out.println(String.format(">>> [GetNongHTranData] 전영업일 없음. \n"));
		System.out.println(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 않음 \n", " ", " "));
		System.out.println(String.format(">>> [GetNongHTranData] 전일 없음. \n"));
		System.out.println(String.format("VC0033110000000011NC 0000000%8s%134s\n", " ", " " ));
		System.out.println(String.format("VC003322%07d", 0));
		System.out.println(String.format("%8s%6s%13s%6s%3s%4s%6s%4s%2s%3s%17s%12s%5s%3s%14s%2s%47s\n", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("VC0033339999999011NC %07d%8s%134s\n", 0, " ", " " ));
		System.out.println(String.format("%07d", 0));
		System.out.println(String.format("%7s", " "));
		System.out.println(String.format(">>> [GetNongHTranData] 익영업일 없음. \n"));
		System.out.println(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 매달 첫 영업일에만 전송 \n", " ", " "));
		System.out.println(String.format("VC0002110000000011NC 0000000%8s%164s\n", " ", " " ));
		System.out.println(String.format("VC000222%07dI", 0));
		System.out.println(String.format("%3s%4s%30s%100s%15s%6s%6s%20s\n", " ", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("VC0002339999999011NC %07d%8s%164s\n", 0, " ", " " ));
		System.out.println(String.format("%07d", 0));
		System.out.println(String.format("%7s", " "));
		System.out.println(String.format(">>> [GetNongHTranData] 익영업일 없음. \n"));
		System.out.println(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 매달 첫 영업일에만 전송 \n", " ", " "));
		System.out.println(String.format("VC000111NCI*******%6s%26s\n", " ", " " ));
		System.out.println(String.format("VC111122%07d%6sNCI", 0, " "));
		System.out.println(String.format("%4s%1s%8s1000000000000\n", " ", " ", " "));
		System.out.println(String.format("%07d", 0));
		System.out.println(String.format("%7s", " "));
		System.out.println(String.format(">>> [GetCommonTranData-%s] 전영업일 없음.\n", " "));
		System.out.println(String.format(">>> [GetCommonTranData-%s] 휴일 전송 안함 [%s]\n", " ",  " "));
		System.out.println(String.format(">>> [GetCommonTranData-%s] 전일 없음.\n", " "));
		System.out.println(String.format("ATM001110000000%2s030000000%6s%138s\n", " ", " ", " " ));
		System.out.println(String.format("ATM00122%07d%2s03 012200110096%4s%6s%6s%6s%116s\n", 0, " ", " ", " ", " ", " ", " " ));
		// System.out.println(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", 0, " ", " ", " ", 0 + " ", " ", " ", " ", " ", " ", " " , " ", " ", 0, 0, " ", " ", " ", " "," "));
		// System.out.println(String.format("ATM00122%07d%2s03 01220033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", 0, " " , " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ));
		System.out.println(String.format("ATM00122%07d%2s03 012300110096%4s%6s%6s%6s%116s\n", 0, " ", " ", " ", " ", " ", " " ));
		// System.out.println(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", 0, " ", " ", " ", 0 + " ", " ", " ", " ", " ", " ", " " , " ", " ", 0, 0, " ", " ", " ", " "," "));
		// System.out.println(String.format("ATM00122%07d%2s03 01230033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", 0, " " , " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ));
		System.out.println(String.format("ATM001339999999%2s03%07d%144s\n", " ", 0, " " ));
		System.out.println(String.format("%07d", 0));
		System.out.println(String.format("%7s", " "));
		System.out.println(String.format(">>> [GetCommonTranData-%s] 전일 없음.\n", " "));
		System.out.println(String.format("ATM001110000000%2s030000000%6s%138s\n", " ", " ", " " ));
		System.out.println(String.format("ATM00122%07d%2s03 012200110096%4s%6s%6s%6s%116s\n", 0, " ", " ", " ", " ", " ", " " ));
		// System.out.println(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", 0, " ", " ", " ", 0 + " ", " ", " ", " ", " ", " ", " " , " ", " ", 0, 0, " ", " ", " ", " "," "));
		// System.out.println(String.format("ATM00122%07d%2s03 01220033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", 0, " " , " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ));
		System.out.println(String.format("ATM00122%07d%2s03 012300110096%4s%6s%6s%6s%116s\n", 0, " ", " ", " ", " ", " ", " " ));
		// System.out.println(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", 0, " ", " ", " ", 0 + " ", " ", " ", " ", " ", " ", " " , " ", " ", 0, 0, " ", " ", " ", " "," "));
		// System.out.println(String.format("ATM00122%07d%2s03 01230033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", 0, " " , " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ));
		System.out.println(String.format("ATM001339999999%2s03%07d%144s\n", " ", 0, " " ));
		System.out.println(String.format("%07d", 0));
		System.out.println(String.format("%7s", " "));
		System.out.println(String.format(">>> [GetKBSTTranData-%s] 전일 없음.\n", " "));
		System.out.println(String.format("ATM001110000000%2s030000000%6s%138s\n", " ", " ", " " ));
		System.out.println(String.format("ATM00122%07d%2s03 012200110096%4s%6s%6s%6s%116s\n", 0, " ", " ", " ", " ", " ", " " ));
		// System.out.println(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%s\n", 0, " ", " ", " ", 0 + " ", " ", " ", " ", " ", " ", " " , " ", " ", 0, 0, " ", " ", " ", " ", " "));
		// System.out.println(String.format("ATM00122%07d%2s03 01220033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", 0, " " , " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ));
		System.out.println(String.format("ATM00122%07d%2s03 012300110096%4s%6s%6s%6s%116s\n", 0, " ", " ", " ", " ", " ", " " ));
		// System.out.println(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%s\n", 0, " ", " ", " ", 0 + " ", " ", " ", " ", " ", " ", " " , " ", " ", 0, 0, " ", " ", " ", " ", " "));
		// System.out.println(String.format("ATM00122%07d%2s03 01230033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", 0, " " , " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ));
		System.out.println(String.format("ATM001339999999%2s03%07d%144s\n", " ", 0, " " ));
		System.out.println(String.format("%07d", 0));
		System.out.println(String.format("%7s", " "));
		System.out.println(String.format(">>> [GetCommonTranData-%s] 휴일 전송 안함 [%s]\n", " ",  " "));
		System.out.println(String.format(">>> [GetCommonTranData-%s] 전일 없음.\n", " "));
		System.out.println(String.format(">>> [GetCommonTranData-%s] FileOpenError [%s]\n", " ", " "));
		System.out.println(String.format("ATM001110000000%2s030000000%6s%138s\n", " ", " ", " " ));
		System.out.println(String.format("ATM00122%07d%2s03 012200110096%4s%6s%6s%6s%116s\n", 0, " ", " ", " ", " ", " ", " " ));
		// System.out.println(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", 0, " ", " ", " ", 0 + " ", " ", " ", " ", " ", " ", " " , " ", " ", 0, 0, " ", " ", " ", " "," "));
		// System.out.println(String.format("ATM00122%07d%2s03 01220033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", 0, " " , " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ));
		System.out.println(String.format("ATM00122%07d%2s03 012300110096%4s%6s%6s%6s%116s\n", 0, " ", " ", " ", " ", " ", " " ));
		// System.out.println(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", 0, " ", " ", " ", 0 + " ", " ", " ", " ", " ", " ", " " , " ", " ", 0, 0, " ", " ", " ", " "," "));
		// System.out.println(String.format("ATM00122%07d%2s03 01230033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", 0, " " , " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ));
		System.out.println(String.format("ATM001339999999%2s03%07d%144s\n", " ", 0, " " ));
		System.out.println(String.format("%07d", 0));
		System.out.println(String.format("%7s", " "));
		System.out.println(String.format(">>> [GetCommonTranData-%s] 전영업일 없음.\n", " "));
		System.out.println(String.format(">>> [GetCommonTranData-%s] 휴일 전송 안함 [%s]\n", " ",  " "));
		System.out.println(String.format(">>> [GetCommonTranData-%s] 전일 없음.\n", " "));
		System.out.println(String.format("012200110096%4s%6s%6s%6s%116s\n", " ", " ", " ", " ", " " ));
		System.out.println(String.format("%s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", " ", " ", 0, " ", " ", " ", " ", " ", " " , " ", " ", 0, 0, " ", " ", " ", " "," "));
		// System.out.println(String.format("01220033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ));
		System.out.println(String.format("012300110096%4s%6s%6s%6s%116s\n", " ", " ", " ", " ", " " ));
		System.out.println(String.format("%s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", " ", " ", 0, " ", " ", " ", " ", " ", " " , " ", " ", 0, 0, " ", " ", " ", " "," "));
		// System.out.println(String.format("01230033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " ));
		System.out.println(String.format("%s/S%sDKS", " ", " "));
		System.out.println(String.format(">>> [GetSmartTranData] 전일 없음. \n"));
		System.out.println(String.format(">>> [GetSmartTranData] FileOpenError [%s]\n", " "));
		System.out.println(String.format("MDH01%57s%076d%8s%8s%06d%40s\n", " ", 0, " ", " ", 0, " " ));
		System.out.println(String.format(">>> [fnDBInsertUpTransDate] SmartFile Read Error [%s]\n", " "));
		System.out.println(String.format("%010d", 0));
		System.out.println(String.format("%2s%1s%07d%2s%16s%16s%2s%4s%14s%14s%10s%1s%10s%10s%10s%10s%010d%010d%1s%10s%8s%32s\n" , " ", " ", 0 , " ", " ", " ", " ", " ", " ", " ", " ", " ", " " , " ", " ", " ", 0  , 0  , " "  , " ", " ", " " ));
		System.out.println(String.format("MDT%197s\n", " "));
		System.out.println(String.format("%07d%07d%013d%010d%07d%013d", 0, 0, 0, 0, 0, 0));
		System.out.println(String.format("%s", " "));
		System.out.println(String.format(">>> [GetSmartTranData] 전일 없음. \n"));
		System.out.println(String.format(">>> [GetSmartTranData] FileOpenError [%s]\n", " "));
		System.out.println(String.format("MDH01%57s%076d%8s%8s%06d%40s\n", " ", 0, 0, " ", 0, " " ));
		System.out.println(String.format("%s/S%sDKS", " ", " "));
		System.out.println(String.format(">>> [fnDBInsertUpTransDate] SmartFile Read Error [%s]\n", " "));
		System.out.println(String.format("%010d", 0 ));
		// System.out.println(String.format("%2s%1s%07d%2s%16s%16s%2s%4s%14s%14s%10s%1s%10s%10s%10s%10s%010d%010d%1s%10s%8s%32s\n" , " ", " ", 0 , " ", " ", " ", " ", " ", " ", " ", " ", " ", " "  , " ", " ", " ", 0  , 0  , " "  , " ", " ", " " ));
		System.out.println(String.format("MDT%197s\n", " "));
		System.out.println(String.format("%07d%07d%013d%010d%07d%013d", 0, 0, 0, 0, 0, 0));
		System.out.println(String.format("%s", " "));
		System.out.println(String.format("Deal_Date[%s]\n", " "));
		System.out.println(String.format("V01HPS110000000V01%8s%8s%116s\n", " ", " ", " " ));
		System.out.println(String.format("%6s%2s%07d%16s%8s11V01%4s%4s%16s%13s%8s%1s%9s%16s%10s%8s%17s\n", " ", " ", 0, " ", " ", " ", " ", " ", " ", " ", " "," ", " ", " ", " ", " "));
		System.out.println(String.format("V01HPS339999999V01%07d%015d%110s\n", 0, 0, " " ));
		System.out.println(String.format(">>> [GetNongHTranData] 전영업일 없음. \n"));
		System.out.println(String.format(">>> [GetNongHTranData] 전일 없음. \n"));
		System.out.println(String.format("H96%8s%6d%133s\n", " ", 0, " " ));
		System.out.println(String.format("D%4s013008%16s%11s%12s%6s%8s%6s0000%2s%16s410%1s%54s\n", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("T%08d%08d%08d%08d%08d%08d%101s\n", 0, 0, 0, 0, 0, 0, " "));
		System.out.println(String.format(">>> [GetSuHTranData] 전일 없음. \n"));
		System.out.println(String.format("NI00011100000000070960000000%8s%164s\n", " ", " " ));
		System.out.println(String.format("NI000122%07d", 0));
		System.out.println(String.format("%8s%6s%13s%3s%4s%6s%4s%2s%3s%16s%12s%5s%3s%14s%86s\n", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("NI0001339999999007096%07d%8s%164s\n", 0, " ", " " ));
		System.out.println(String.format("%07d", 0));
		System.out.println(String.format("%7s", " "));
		System.out.println(String.format(">>> [GetSuHMacData] 익영업일 없음. \n"));
		System.out.println(String.format(">>> [GetSuHMacData][%s]-[%s] 휴일 전송 하지 매달 첫 영업일에만 전송 \n", " ", " "));
		System.out.println(String.format("NI00021100000000070960000000%8s%164s\n", " ", " " ));
		System.out.println(String.format("NI000222%07d096", 0));
		System.out.println(String.format("%4s%1s%30s%100s%15s%6s%26s\n", " ", " ", " ", " ", " ", " ", " "));
		System.out.println(String.format("NI0002339999999007096%07d%8s%164s\n", 0, " ", " " ));
		System.out.println(String.format("%07d", 0));
		System.out.println(String.format("%7s", " "));
		System.out.println(String.format(">>> [GetKFTCTranData] 영업일 없음.\n"));
		System.out.println(String.format(">>> [GetKFTCTranData][%s]-[%s] 휴일 전송 하지 영업일에만 전송 \n", " ", " "));
		System.out.println(String.format(">>> [GetKFTCTranData] 전일 없음.\n"));
		System.out.println(String.format("CD111911%07d096*******%8s%67s\n", 0, " ", " " ));
		System.out.println(String.format("CD111922%07d%s%s%015d%66s\n", 0, " "," ", 0, " " ));
		System.out.println(String.format("CD1119339999999096%07d%020d%020d%35s\n", 0, 0, 0, " " ));
		System.out.println(String.format("%07d", 0));
		System.out.println(String.format("%7s", " "));
		System.out.println(String.format("gvinfo_%s.txt", " "));
		System.out.println(String.format("%s/%s", " ", " "));
		System.out.println(String.format("1       0%141s\n", " "));
		System.out.println(String.format("0%1s%-3s%-4s%-2s%-20s%-80s%39s\n", "1"," "," "," "," "," ", " " ));
		System.out.println(String.format("00%148s\n", " "));
		System.out.println(String.format("1       0%141s\n", " "));
		System.out.println(String.format("%02.02f", 0f ));
		System.out.println(String.format("%02.02f", 0f ));
		System.out.println(String.format("1%3s%-4s%-1s%-2s%-20s%07d%5s%5s%102s\n"," "," ", "1"," "," ", 0, " ", " ", " " ));
		System.out.println(String.format("1       0%141s\n", " "));
		System.out.println(String.format("1       0%141s\n", " "));
		System.out.println(String.format("00%148s\n", " "));
		System.out.println(String.format("1       0%141s\n", " "));
		*/
	}
}
