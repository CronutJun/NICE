/** 
 * com.nicetcm.nibsplus.filesend.service.impl.FileSendServiceImpl
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 10. 10.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.filemng.common.FileMngException;
import com.nicetcm.nibsplus.filemng.dao.FileSendMapper;
import com.nicetcm.nibsplus.filemng.model.TransferVO;
import com.nicetcm.nibsplus.filemng.service.FileSendService;
import com.nicetcm.nibsplus.util.NibsBatchUtil;

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
@Service("fileSendService")
public class FileSendServiceImpl implements FileSendService {
	
	@Autowired
	private FileSendMapper fileSendMapper;
	
	@Autowired
	private Properties config;

	public void execute(String... argv) throws Exception {
		String szTransDate = null;
		String szOrgCd = null;
		String[] szaryOrg= {
			"0BC",
			"1BC",
			"0CN",
			"0CU",
			"0BJ",
			"0HM",
			"0LG",
			"0LC",
			"B11",
			"181",
			"003",
			"044",
			"031",
			"020",
			"023",
			"011",
			"012",
			"004",
			"0HI",
			"A11",
			"Z11",
			"1CN",
			"0CH",
			"088",
			"488",
			"B88",
			// "0SC", // 20141217 담당자 요청으로 제거
			"A07",
			"007",
			"000",
			"0GV",
			"2CN",
			"23_0511",
			"23_0521",
			"23_0571",
			"23_05F1"
		};
		int MAX_ORG_CNT = szaryOrg.length;

		if ( argv.length != 1 ) {
			szTransDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		} else {
			if (argv[0].length() == 3) {
				szTransDate = NibsBatchUtil.SysDate();
				szOrgCd = argv[0];
			} else if (argv[0].length() == 8) {
				szTransDate = argv[0];
			} else if (argv[0].length() == 11) {
				szTransDate = argv[0].substring(0, 8);

				if( (argv[0].length() - 8) == 3 ) {
					szOrgCd = argv[0].substring(8);
				}
			} else {
				System.out.print( "Usage : MakeOrgTranFile [calc_date(8)+orgcd(3)]\n" );
			}
		}

		if (szOrgCd == null) {
			for(int i = 0 ; i < MAX_ORG_CNT; i++ ) {
				szOrgCd = szaryOrg[i];

				// 전체실행 예외기관 부분 														 
				// 금융결제원(KFTC)의 경우, 전체실행 시간인 01:30에 데이터가 없으므로, 제외한다. 
				if ("000".equals(szOrgCd)) {
					continue;
				} else if ("2CN".equals(szOrgCd)) { // 7:50 분 별도로 스케쥴 지정
					continue;
				}

				try {
					putFtp(PutOrgTranFile(szTransDate, szOrgCd));
				} catch(Exception e) {
					System.err.println(e.getMessage());
				}
			}
		} else {
			try {
				putFtp(PutOrgTranFile(szTransDate, szOrgCd));
			} catch(Exception e) {
				System.err.println(e.getMessage());
			}
		}
		
		System.out.print(">>> [main] 작업완료\n");
	}
	
	private void putFtp(File file) throws FileMngException, IOException {
        TransferVO transferVO = new TransferVO();
        transferVO.setHost((String)config.get("FILESend.host"));
        transferVO.setAvailableServerPort(Integer.parseInt((String)config.get("FILESend.availableServerPort")));
        transferVO.setUserId((String)config.get("FILESend.userid"));
        transferVO.setPassword((String)config.get("FILESend.password"));
        transferVO.setRemotePath((String)config.get("FILESend.remote.path"));
        transferVO.setLocalPath((String)config.get("FILESend.local.path"));
        transferVO.setFileName(file.getName());
        
        try {
        	// SFtpTransfer.putFile(transferVO);
        	FtpTransfer.putFile(transferVO);

    		try {
    			if (fileSendMapper.updatePutOrgTranFile(file.getName(), 0) == 0) {
    				fileSendMapper.insertPutOrgTranFile(file.getName(), 0);
    			}
    		} catch(Exception er) {}
        } catch(FileMngException e) {
    		try {
    			if (fileSendMapper.updatePutOrgTranFile(file.getName(), 1) == 0) {
    				fileSendMapper.insertPutOrgTranFile(file.getName(), 1);
    			}
    		} catch(Exception er) {}
    		
    		throw e;
        }
	}
	
	private File PutOrgTranFile(String pDate, String pOrgCd) throws Exception {
		String szSrcPath = (String)config.get("FILESend.local.path");
		String szFileName = String.format("S%s%3s", pDate, pOrgCd);
		String szFilePath = String.format("%s/%s", szSrcPath, szFileName);
		int		nRtn = -1;

		new File(szSrcPath).mkdirs();

		System.out.print(String.format("File 생성 Start [%s]-TransDate[%s], OrgCd[%s]\n", szFileName, pDate, pOrgCd));

		if( pOrgCd.equals("0BC") ) {
			nRtn = GetBCTranData(pDate, szFilePath);
		} else if( pOrgCd.equals("1BC") ) {
			nRtn = GetBCMacData(pDate, szFilePath);
		} else if( pOrgCd.equals("0CN") ) {
			nRtn = GetCNTranData(pDate, szFilePath);
		} else if( pOrgCd.equals("1CN") ) {
			nRtn = GetCNMacData(pDate, szFilePath);
		} else if( pOrgCd.equals("0CU") ) {
			nRtn = GetCUTranData(pDate, szFilePath);
		} else if( pOrgCd.equals("0BJ") ) {
			nRtn = GetBJTranData(pDate, szFilePath);
		} else if( pOrgCd.equals("0HM") ) {
			nRtn = GetCityTranData(pDate, szFilePath);
		} else if( pOrgCd.equals("0LG") ) {
			nRtn = GetLGTranData(pDate, szFilePath);
		} else if( pOrgCd.equals("0LC") ) {
			nRtn = GetLCTranData(pDate, szFilePath);
		} else if( pOrgCd.equals("B11") ) {
			nRtn = GetNongHTranData_NEW(pDate, szFilePath);
		} else if( pOrgCd.equals("A11") ) {
			nRtn = GetNongHMacData(pDate, szFilePath);
		} else if( pOrgCd.equals("Z11") ) {
			nRtn = GetNongHBrandMacData(pDate, szFilePath);
		} else if( pOrgCd.equals("181") ) {
			nRtn = GetHNetNewTranData(pDate, szFilePath);
		} else if( pOrgCd.equals("044") ) {
			nRtn = GetKETranData(pDate, pOrgCd, szFilePath);
		} else if( pOrgCd.equals("004") ) {
			nRtn = GetKBSTTranData(pDate, pOrgCd, szFilePath);
		} else if( pOrgCd.equals("003") ) {
			nRtn = GetKiupTranData(pDate, pOrgCd, szFilePath);
		} else if( pOrgCd.equals("031") ||
				 pOrgCd.equals("020") ||
				 pOrgCd.equals("023") ||
				 pOrgCd.equals("011") ||
				 pOrgCd.equals("012") ) {
			nRtn = GetCommonTranData(pDate, pOrgCd, szFilePath);
		} else if( pOrgCd.equals("0HI") ) {
			nRtn = GetHITranData(pDate, szFilePath);
		} else if( pOrgCd.equals("0CH") ) { /* 농협카드 */
			nRtn = GetCHTranData(pDate, szFilePath);
		} else if( pOrgCd.equals("088") ) { /* 신한은행 출금File*/
			nRtn = GetSHTranData(pDate, szFilePath, "0");	/* 출금File 생성 */
		} else if( pOrgCd.equals("488") ) { /* 신한은행 이체File*/
			nRtn = GetSHTranData(pDate, szFilePath, "4");	/* 이체File 생성 */
		} else if( pOrgCd.equals("B88") ) { /* 신한은행 이체File*/
			nRtn = GetSHTranData(pDate, szFilePath, "B");	/* 이체File 생성 */
		} else if( pOrgCd.equals("0SC") ) { /* 삼성카드 */
			nRtn = GetSCTranData(pDate, szFilePath);
		} else if( pOrgCd.equals("007") ) { /* 수협 거래실적 */
			nRtn = GetSuHTranData(pDate, szFilePath);
		} else if( pOrgCd.equals("A07") ) { /* 수협 기기정보 */
			nRtn = GetSuHMacData(pDate, szFilePath);
		} else if( pOrgCd.equals("000") ) { /* 금융결제원(KFTC) */
			nRtn = GetKFTCTranData(pDate, szFilePath);
		} else if( pOrgCd.equals("0GV") ) { /* 전자상품권  */
			szFilePath = String.format("%s/%s", szSrcPath, String.format("gvinfo_%s.txt", fileSendMapper.pickupGetGiftCardInfoData()));
			
			nRtn = GetGiftCardInfoData(pDate, szFilePath);
		} else if( pOrgCd.equals("2CN") ) { /* 금융결제원(KFTC) */
			nRtn = GetCNOperData(pDate, szFilePath);
		} else if( pOrgCd.equals("23_0511") ) {
			szFilePath = String.format("%s/%s", szSrcPath, String.format("%s_%s", pDate.substring(0, 6), pOrgCd));
			
			nRtn = shb230511(pDate, szFilePath);
		} else if( pOrgCd.equals("23_0521") ) {
			szFilePath = String.format("%s/%s", szSrcPath, String.format("%s_%s", pDate.substring(0, 6), pOrgCd));
			
			nRtn = shb230521(pDate, szFilePath);
		} else if( pOrgCd.equals("23_0571") ) {
			szFilePath = String.format("%s/%s", szSrcPath, String.format("%s_%s", pDate.substring(0, 6), pOrgCd));
			
			nRtn = shb230571(pDate, szFilePath);
		} else if( pOrgCd.equals("23_05F1") ) {
			szFilePath = String.format("%s/%s", szSrcPath, String.format("%s_%s", pDate.substring(0, 6), pOrgCd));
			
			nRtn = shb2305F1(pDate, szFilePath);
		} else {
			throw new RuntimeException(String.format("해당 기관없음 [%s]\n", pOrgCd));
		}

		if( nRtn < 0 ) {
			throw new RuntimeException(String.format("file 생성 실패 [%s]\n", szFileName));
		} else {
			System.out.print(String.format("file 생성 OK !!! [%s]\n", szFileName));
		}
		
		return new File(szFilePath);
	}
	
	private int GetCNTranData(String pTransDate, String pFileName) throws IOException {
		String hDataType;
		String hDealDate;
		String hDealTime;
		String hAccountNo;
		String hMacNo;
		String hDealAmt;
		String hDealStatus;
		String hDealNo;
		String hPreDate;

		int		ncount = 0;
		long	lnTotalAmt = 0;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));
		
		hPreDate = fileSendMapper.pickupGetCNTranData(pTransDate);

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetCNTranData(hPreDate);
		Object[] rowValues;
		
		// fprintf( pfTran, "1%sKMC%*s\n", hPreDate, 58, " " );
		fileWriter.write(String.format("1%sKMC%58s", hPreDate, " "));
		// fileWriter.newLine();
		
		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hDataType = rowValues[0] != null ? rowValues[0].toString() : "";
			hDealDate = rowValues[1] != null ? rowValues[1].toString() : "";
			hDealTime = rowValues[2] != null ? rowValues[2].toString() : "";
			hAccountNo = rowValues[3] != null ? rowValues[3].toString() : "";
			hMacNo = rowValues[4] != null ? rowValues[4].toString() : "";
			hDealAmt = rowValues[5] != null ? rowValues[5].toString() : "";
			hDealStatus = rowValues[6] != null ? rowValues[6].toString() : "";
			hDealNo = rowValues[7] != null ? rowValues[7].toString() : "";

			// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 1, hDataType, 8, hDealDate, 6, hDealTime, 16, hAccountNo, 8, hMacNo, 10, hDealAmt, 1, hDealStatus, 12, hDealNo, 8, " ");
			fileWriter.write(String.format("%1s%8s%6s%-16s%-8s%10s%1s%12s%8s", hDataType, hDealDate, hDealTime, hAccountNo.trim(), hMacNo, hDealAmt, hDealStatus, hDealNo, " "));
			// fileWriter.newLine();

			lnTotalAmt += Long.parseLong(hDealAmt);
			ncount++;
		}

		// fprintf( fileWriter, "3%08d%013ld%*s\n", ncount, lnTotalAmt, 48, " " );
		fileWriter.write(String.format("3%08d%013d%48s", ncount, lnTotalAmt, " " ));
		// fileWriter.newLine();

		fileWriter.close();
		
		return 0;
	}
	
	private int GetCNMacData(String pTransDate, String pFileName) throws IOException {
		String hSetYn;
		String hMacNo;
		String hSetDate;
		String hOpenDate;
		String hUpdateDate;
		String hCloseDate;
		String hZipNo;
		String hSetPlace;
		String hSetAddr;
		String hInterPhone;
		String hPicYn;
		String hCDType;

		int		ncount;
		String hPreActDate;
		String hPrePreActDate;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		/* 전송일이 휴일면 전송일을 전송일이 영업일이면 전 영업일을 return */
		hPreActDate = fileSendMapper.pickupGetCNMacData(pTransDate);

		if(hPreActDate == null) {
			throw new RuntimeException(">>> [GetCityTranData] 전영업일 없음. \n");
		}

		/* 휴일에는 전송하지 않는다. */
		if( pTransDate.equals(hPreActDate)) {
			throw new RuntimeException(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 않음 \n", pTransDate, hPreActDate));
		}

		/* 전전 영업일을 return */
		hPrePreActDate = fileSendMapper.pickupGetCNMacDataPrev(pTransDate);

		if(hPrePreActDate == null) {
			throw new RuntimeException(">>> [GetCityTranData] 전전영업일 없음. \n");
		}

		ncount = 0;

		// fprintf( fileWriter, "H%8s%*s\n", pTransDate, 191, " " );
		fileWriter.write(String.format("H%8s%191s", pTransDate, " " ));
		// fileWriter.newLine();

		/* 변경분만 전송 */
		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetCNMacData(hPreActDate, hPrePreActDate);
		Object[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hSetYn = rowValues[0] != null ? rowValues[0].toString() : "";
			hMacNo = rowValues[1] != null ? rowValues[1].toString() : "";
			hSetDate = rowValues[2] != null ? rowValues[2].toString() : "";
			hOpenDate = rowValues[3] != null ? rowValues[3].toString() : "";
			hUpdateDate = rowValues[4] != null ? rowValues[4].toString() : "";
			hCloseDate = rowValues[5] != null ? rowValues[5].toString() : "";
			hZipNo = rowValues[6] != null ? rowValues[6].toString() : "";
			hSetPlace = rowValues[7] != null ? rowValues[7].toString() : "";
			hSetAddr = rowValues[8] != null ? rowValues[8].toString() : "";
			hInterPhone = rowValues[9] != null ? rowValues[9].toString() : "";
			hPicYn = rowValues[10] != null ? rowValues[10].toString() : "";
			hCDType = rowValues[11] != null ? rowValues[11].toString() : "";

			// fprintf( fileWriter, "D%.*s96%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 2, hSetYn, 8, hMacNo, 8, hSetDate, 8, hOpenDate, 8, hUpdateDate, 8, hCloseDate, 6, hZipNo, 60, hSetPlace, 30, hSetAddr, 20, hInterPhone, 1, hPicYn, 2, hCDType, 36, " ");
			// fileWriter.write(String.format("D%2s96%8s%8s%8s%8s%8s%6s%60s%30s%20s%1s%2s%36s\n", hSetYn, hMacNo, hSetDate, hOpenDate, hUpdateDate, hCloseDate, hZipNo, hSetPlace, hSetAddr, hInterPhone, hPicYn, hCDType, " "));
			fileWriter.write(String.format("D%2s96%8s%8s%8s%8s%8s%6s%-"+minHanCount(60, hSetPlace)+"s%-"+minHanCount(30, hSetAddr)+"s%-20s%1s%2s%36s", hSetYn, hMacNo, hSetDate, hOpenDate, hUpdateDate, hCloseDate, hZipNo, hSetPlace.trim(), hSetAddr.trim(), hInterPhone, hPicYn, hCDType, " "));
			// fileWriter.newLine();

			ncount++;
	    }

		// fprintf( fileWriter, "T%.8s%010ld%*s\n", pTransDate, ncount, 181, " " );
		fileWriter.write(String.format("T%8s%010d%181s", pTransDate, ncount, " " ));
		// fileWriter.newLine();

		fileWriter.close();
		
		return 0;
	}

	private int GetBCTranData(String pTransDate, String pFileName) throws IOException {
		String hDataType;
		String hDealDate;
		String hDealTime;
		String hAccountNo;
		String hMacNo;
		String hDealAmt;
		String hNiceDealAmt;
		String hDealStatus;
		String hDealNo;
		String hPreDate;

		int		ncount = 0;
		long	lnTotalAmt = 0;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		hPreDate = fileSendMapper.pickupGetBCTranData(pTransDate);

		if(hPreDate == null) {
			throw new RuntimeException(">>> [GetCityTranData] 전영업일 없음. \n");
		}

		/* 전일자 실적 전송 */
		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetBCTranData(hPreDate);
		Object[] rowValues;
		
		// fprintf( fileWriter, "1%sNIC000000%*s\n", hPreDate, 52, " " );
		fileWriter.write(String.format("1%sNIC000000%52s", hPreDate, " " ));
		// fileWriter.newLine();

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hDataType = rowValues[0] != null ? rowValues[0].toString() : "";
			hDealDate = rowValues[1] != null ? rowValues[1].toString() : "";
			hDealTime = rowValues[2] != null ? rowValues[2].toString() : "";
			hAccountNo = rowValues[3] != null ? rowValues[3].toString() : "";
			hMacNo = rowValues[4] != null ? rowValues[4].toString() : "";
			hDealAmt = rowValues[5] != null ? rowValues[5].toString() : "";
			hNiceDealAmt = rowValues[6] != null ? rowValues[6].toString() : "";
			hDealStatus = rowValues[7] != null ? rowValues[7].toString() : "";
			hDealNo = rowValues[8] != null ? rowValues[8].toString() : "";

			// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 1, hDataType, 8, hDealDate, 6, hDealTime, 16, hAccountNo, 4, hMacNo, 7, hDealAmt, 7, hNiceDealAmt, 1, hDealStatus, 12, hDealNo, 8, " ");
			fileWriter.write(String.format("%1s%8s%6s%16s%4s%7s%7s%1s%12s%8s", hDataType, hDealDate, hDealTime, hAccountNo.trim(), hMacNo, hDealAmt, hNiceDealAmt, hDealStatus, hDealNo, " "));
			// fileWriter.newLine();

			lnTotalAmt += Long.parseLong(hDealAmt);

			ncount++;
	    }

		// fprintf( fileWriter, "3%08d%013ld%*s\n", ncount, lnTotalAmt, 48, " " );
		fileWriter.write(String.format("3%08d%013d%48s", ncount, lnTotalAmt, " " ));
		// fileWriter.newLine();

		fileWriter.close();
		
		return 0;
	}
	
	private int GetBCMacData(String pTransDate, String pFileName) throws IOException {
		String hMacNo;
		String hOpenDate;
		String hZipNo;
		String hSetPlace;
		String hSetAddr;

		int		ncount;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		if( !"01".equals(pTransDate.substring(6)) ) {
			throw new RuntimeException("매월 01일에만 전송 하도록 함.\n" );
		}

		ncount = 0;

		// fprintf( fileWriter, "BHATM-INFO%8s%*s\n", pTransDate, 232, " " );
		fileWriter.write(String.format("BHATM-INFO%8s%232s", pTransDate, " " ));
		// fileWriter.newLine();

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetBCMacData(pTransDate);
		Object[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hMacNo = rowValues[0] != null ? rowValues[0].toString() : "";
			hSetPlace = rowValues[1] != null ? rowValues[1].toString() : "";
			hOpenDate = rowValues[2] != null ? rowValues[2].toString() : "";
			hZipNo = rowValues[3] != null ? rowValues[3].toString() : "";
			hSetAddr = rowValues[4] != null ? rowValues[4].toString() : "";

			// fprintf( fileWriter, "BD03102%.*s%.*s%.*s080022 8252%.*s%.*s%*s\n", 16, hMacNo, 50, hSetPlace, 8, hOpenDate, 6, hZipNo, 100, hSetAddr, 52, " ");
			// fileWriter.write(String.format("BD03102%16s%50s%8s080022 8252%6s%100s%52s\n", hMacNo, hSetPlace, hOpenDate, hZipNo, hSetAddr, " "));
			fileWriter.write(String.format("BD03102%16s%-"+minHanCount(50, hSetPlace)+"s%8s080022 8252%6s%-"+minHanCount(100, hSetAddr)+"s%52s", hMacNo, hSetPlace.trim(), hOpenDate, hZipNo, hSetAddr.trim(), " "));
			// fileWriter.newLine();

			ncount++;
	    }

		// fprintf( fileWriter, "BT%08ld%*s\n", ncount, 240, " " );
		fileWriter.write(String.format("BT%08d%240s", ncount, " " ));
		// fileWriter.newLine();

		fileWriter.close();
		
		return 0;
	}

	/* 신한은행 */
	@SuppressWarnings("unused")
	private int GetSHTranData(String pTransDate, String pFileName, String  pDealType) throws IOException {
		String hDealType;
		String hDataType;
		String hDealDate;
		String hDealTime;
		String hMacNo;
		String hDealAmt;
		String hDealStatus;
		String hDealNo;
		String hPreDate;
		String hOrgCd;
		String hMsgType;
		String hNotendInOrgCd;		/* 입금미완료 은행코드 		*/
		String hNotendInAccountNo;	/* 입금미완료 계좌정보		*/
		String hNotendOutFlag;		/* 출금미완료 Flag			*/
		String hNotendOutDealdate;	/* 출금미완료 거래일자		*/
	
		int		ncount = 1;
		long	lnTotalAmt = 0;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));
	
		hNotendOutFlag = "1";			/* 출금미완료 FLAG		*/
	
		hPreDate = fileSendMapper.pickupGetSHTranData(pTransDate);
	
		if( "0".equals(pDealType)) { /* 지급 */
			hOrgCd = "NICEBNK";
		} else if ("4".equals(pDealType)) {
			hOrgCd = "P001226";
		} else {
			hOrgCd = "BJNICE1";
		}
	
		/* 출금일경우 거래고유번호 앞 두자리를 '00'으로
			이체일 경우 거래고유번호 앞 6자리를 '000000'으로 20100811 이재원과장 요청 */
		/* 이체일 경우 입금거래 포함하도록 수정 20101111 김재환요청
		   => 입금일경우 st_org_cd(org_cd) = '0'으로 저장됨 */
		/* 입금거래 시 org_cd = '0' 이던것을 각 입금 기관코드를 표시하도록 TRAN 데이터가 바뀜 (2011.11.22 이재원과장)
		   따라서 출금 일 경우 개별망(088)과 공동망(099) 을 모두 사용하므로 net_org_cd, org_cd를 모두
		   참조하도록 (=> 공동망을 탈경우 전 기관의 거래가 들어 가므로 org_cd를 통해 신한은행 거래만 거름)
		   이체와 입금 거래는 개별망을 타므로 NET_ORG_CD 만으로 신한은행 거래인지 구분 가능 */
		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetSHTranData(pDealType, hPreDate);
		Object[] rowValues;
	
		/* 요청에 의해 필러 1바이트 늘림 */
		// fprintf( fileWriter, "JH0001110000000%.*s%s%*s\n", 7, hOrgCd, hPreDate, 70, " " );
		fileWriter.write(String.format("JH0001110000000%7s%s%70s", hOrgCd, hPreDate, " " ));
		// fileWriter.newLine();

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hDataType = rowValues[0] != null ? rowValues[0].toString() : "";
			hDealDate = rowValues[1] != null ? rowValues[1].toString() : "";
			hDealTime = rowValues[2] != null ? rowValues[2].toString() : "";
			hMacNo = rowValues[3] != null ? rowValues[3].toString() : "";
			hDealAmt = rowValues[4] != null ? rowValues[4].toString() : "";
			hDealStatus = rowValues[5] != null ? rowValues[5].toString() : "";
			hDealNo = rowValues[6] != null ? rowValues[6].toString() : "";
			hDealType = rowValues[7] != null ? rowValues[7].toString() : "";
			hNotendInOrgCd = rowValues[8] != null ? rowValues[8].toString() : "";
			hNotendInAccountNo = rowValues[9] != null ? rowValues[9].toString() : "";
			hNotendOutFlag = rowValues[10] != null ? rowValues[10].toString() : "";
			hNotendOutDealdate = rowValues[11] != null ? rowValues[11].toString() : "";
			
			if( "0".equals(hDealType)){ /* 지급 */
				hMsgType = "0770";
			} else if( "4".equals(hDealType)){ /* 이체 */
				hMsgType = "0780";
			} else if( "1".equals(hDealType)){ /* 입금 */
				hMsgType = "0760";
			} else {
				hMsgType = "";
			}
	
			/* 요청에 의해 필러 1바이트 늘림 */
			// fprintf( fileWriter, "JH0001%.*s%07d%.*s%.*s%.*s%.*s%.*s0%.*s%.*s%.*s%.*s%.*s%*s\n", 2, hDataType, ncount, 4, hMsgType, 12, hDealNo, 6, hDealTime, 12, hDealAmt, 1, hDealStatus, 10, hMacNo, 3, hNotendInOrgCd, 17, hNotendInAccountNo, 1, hNotendOutFlag, 8, hNotendOutDealdate, 10, " ");
			fileWriter.write(String.format("JH0001%2s%07d%4s%12s%6s%12s%1s0%-10s%3s%17s%1s%8s%10s", hDataType, ncount, hMsgType, hDealNo, hDealTime, hDealAmt, hDealStatus, hMacNo, hNotendInOrgCd, hNotendInAccountNo, hNotendOutFlag, hNotendOutDealdate, " "));
			// fileWriter.newLine();
	
			lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);
	
			ncount++;
	    }
	
		/* 요청에 의해 필러 1바이트 늘림 */
		// fprintf( fileWriter, "JH000133999999900000000000000000000%08d%012lu%08d%012lu%*s\n", ncount-1, lnTotalAmt, ncount-1, lnTotalAmt, 25, " " );
		fileWriter.write(String.format("JH000133999999900000000000000000000%08d%012d%08d%012d%25s", ncount-1, lnTotalAmt, ncount-1, lnTotalAmt, " " ));
		// fileWriter.newLine();

		fileWriter.close();
	
		return 0;
	}
	
	/* 삼성해외카드 */
	private int GetSCTranData(String pTransDate, String pFileName) throws IOException {
		String hDealDate;
		String hDealTime;
		String hAccountNo;
		String hDealAmt;
		String hDealNo;
		String hPreDate;
	
		int		ncount;
		long	lnTotalAmt;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));
	
		hPreDate = fileSendMapper.pickupGetSCTranData(pTransDate);
	
		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetSCTranData(hPreDate);
		Object[] rowValues;

		ncount = 0;
		lnTotalAmt = 0;
	
		// fprintf( fileWriter, "HEADER  96        %s%*s\n", hPreDate, 4, " " );
		fileWriter.write(String.format("HEADER  96        %s%4s", hPreDate, " " ));
		// fileWriter.newLine();

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hDealDate = rowValues[0] != null ? rowValues[0].toString() : "";
			hDealTime = rowValues[1] != null ? rowValues[1].toString() : "";
			hAccountNo = rowValues[2] != null ? rowValues[2].toString() : "";
			hDealAmt = rowValues[3] != null ? rowValues[3].toString() : "";
			hDealNo = rowValues[4] != null ? rowValues[4].toString() : "";
			
			if (hAccountNo.length() > 19) hAccountNo = hAccountNo.substring(0, 19);

			// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s\n", 8, hDealDate, 6, hDealTime, 19, hAccountNo, 18, hDealAmt, 12, hDealNo);
			fileWriter.write(String.format("%8s%6s%-19s%18s%12s%17s", hDealDate, hDealTime, hAccountNo.trim(), hDealAmt, hDealNo, " "));
			// fileWriter.newLine();
	
			lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);
	
			ncount++;
	    }
	
		// fprintf( fileWriter, "TRAILER 96        %012d\n", ncount);
		fileWriter.write(String.format("TRAILER 96        %012d", ncount));
		// fileWriter.newLine();
	
		fileWriter.close();
	
		return 0;
	}
	
	/* 하나은행 개별망 * /
	private int GetHNetTranData(String pTransDate, String pFileName) {
		String hWorkType;
		String hDataType;
		String hDealType;
		String hDealNo;
		String hDealTime;
		String hDealAmt;
		String hDealStatus;
		String hPreDate;
	
		int		ncount;
		String szCnt;
	
		long	lnTotalAmt;
	
		File	fileWriter;
	
		hPreDate = fileSendMapper.pickupGetHNetTranData(pTransDate);
	
		if(( fileWriter = pFileName) == null ) {
			System.out.print( String.format(">>> [GetHNetTranData] FileOpenError [%s]\n", pFileName) );
			return -1;
		}
	
		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetHNetTranData(hPreDate);
		Object[] rowValues;
	
		ncount = 0;
		lnTotalAmt = 0;
	
		// fprintf( fileWriter, "HB00011100000009A0000000%8s%*s\n", hPreDate, 28, " " );
		fileWriter.write(String.format("HB00011100000009A0000000%8s%28s\n", hPreDate, " " ));
	
		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hWorkType = rowValues[0] != null ? rowValues[0].toString() : "";
			hDataType = rowValues[1] != null ? rowValues[1].toString() : "";
			hDealType = rowValues[2] != null ? rowValues[2].toString() : "";
			hDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
			hDealTime = rowValues[4] != null ? rowValues[4].toString() : "";
			hDealAmt = rowValues[5] != null ? rowValues[5].toString() : "";
			hDealStatus = rowValues[6] != null ? rowValues[6].toString() : "";

			// fprintf( fileWriter, "%.*s%.*s%07ld%.*s%.*s%.*s%.*s%.*s%*s\n", 6, hWorkType, 2, hDataType, ncount+1, 6, hDealType, 12, hDealNo, 6, hDealTime, 12, hDealAmt, 1, hDealStatus, 8, " ");
			fileWriter.write(String.format("%6s%2s%07d%6s%12s%6s%12s%1s%8s\n", hWorkType, hDataType, ncount+1, hDealType, hDealNo, hDealTime, hDealAmt, hDealStatus, " "));
	
			lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);
	
			ncount++;
	    }
	
		// fprintf( fileWriter, "HB00013399999999A%07ld%*s\n", ncount, 36, " " );
		fileWriter.write(String.format("HB00013399999999A%07d%36s\n", ncount, " " ));
	
		szCnt = String.format("%07d", ncount);
		fseek (fileWriter, 6+2+7+2, 0 );
		// fprintf( fileWriter, "%7s", szCnt );
		fileWriter.write(String.format("%7s", szCnt ));
	
		fileWriter.close();
	
		return 0;
	}
	*/
	
	/* 하나은행 신규 20090302 */
	private int GetHNetNewTranData(String pTransDate, String pFileName) throws IOException {
		String hWorkType;
		String hDataType;
		String hDealType;
		String hDealNo;
		String hDealTime;
		String hDealAmt;
		String hDealStatus;
		String hPreDate;
	
		int		ncount;
		long	lnTotalAmt;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));
	
		hPreDate = fileSendMapper.pickupGetHNetNewTranData(pTransDate);
	
		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetHNetNewTranData(hPreDate);
		Object[] rowValues;
	
		ncount = 0;
		lnTotalAmt = 0;
	
		// fprintf( fileWriter, "HB0001110000000J960000000%8s%*s\n", hPreDate, 27, " " );
		fileWriter.write(String.format("HB0001110000000J960000000%8s%27s", hPreDate, " " ));
		// fileWriter.newLine();

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hWorkType = rowValues[0] != null ? rowValues[0].toString() : "";
			hDataType = rowValues[1] != null ? rowValues[1].toString() : "";
			hDealType = rowValues[2] != null ? rowValues[2].toString() : "";
			hDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
			hDealTime = rowValues[4] != null ? rowValues[4].toString() : "";
			hDealAmt = rowValues[5] != null ? rowValues[5].toString() : "";
			hDealStatus = rowValues[6] != null ? rowValues[6].toString() : "";
			
			// fprintf( fileWriter, "%.*s%.*s%07ld%.*s%.*s%.*s%.*s%.*s%*s\n", 6, hWorkType, 2, hDataType, ncount+1, 6, hDealType, 13, hDealNo, 6, hDealTime, 12, hDealAmt, 1, hDealStatus, 7, " ");
			fileWriter.write(String.format("%6s%2s%07d%6s%13s%6s%12s%1s%7s", hWorkType, hDataType, ncount+1, hDealType, hDealNo, hDealTime, hDealAmt, hDealStatus, " "));
			// fileWriter.newLine();
	
			lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);
	
			ncount++;
	    }
	
		// fprintf( fileWriter, "HB0001339999999J96%07ld%*s\n", ncount, 35, " " );
		fileWriter.write(String.format("HB0001339999999J96%07d%35s", ncount, " " ));
		// fileWriter.newLine();

		fileWriter.close();

		// fseek (fileWriter, 6+2+7+3, 0 );
		// fprintf( fileWriter, "%7s", szCnt );
		RandomAccessFile raf = new RandomAccessFile(pFileName, "rw");
		raf.seek(6+2+7+3);
		raf.writeBytes(String.format("%07d", ncount ));
		raf.close();
	
		return 0;
	}

	/* 중국은련카드 */
	private int GetCUTranData(String pTransDate, String pFileName) throws IOException {
		String hDataType;
		String hDealDate;
		String hDealTime;
		String hAccountNo;
		String hMacNo;
		String hDealAmt;
		String hDeal2Amt;
		String hDealStatus;
		String hDealNo;
		String hBrandNm;
		String hDealDate2;
		String hPreDate;

		String szDate;
		int		ncount;
		long	lnTotalAmt;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		hPreDate = fileSendMapper.pickupGetCUTranData(pTransDate);

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetCUTranData(hPreDate);
		Object[] rowValues;

		ncount = 0;
		lnTotalAmt = 0;

		szDate = hPreDate.substring(2);

		// fprintf( fileWriter, "H%sATM%6s6%*s\n", szDate, "NICE", 83, " " );
		fileWriter.write(String.format("H%sATM%6s6%83s", szDate, "NICE", " " ));
		// // fileWriter.newLine();

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hDataType = rowValues[0] != null ? rowValues[0].toString() : "";
			hDealDate = rowValues[1] != null ? rowValues[1].toString() : "";
			hDealTime = rowValues[2] != null ? rowValues[2].toString() : "";
			hAccountNo = rowValues[3] != null ? rowValues[3].toString() : "";
			hMacNo = rowValues[4] != null ? rowValues[4].toString() : "";
			hDealAmt = rowValues[5] != null ? rowValues[5].toString() : "";
			hDeal2Amt = rowValues[6] != null ? rowValues[6].toString() : "";
			hDealStatus = rowValues[7] != null ? rowValues[7].toString() : "";
			hBrandNm = rowValues[8] != null ? rowValues[8].toString() : "";
			hDealNo = rowValues[9] != null ? rowValues[9].toString() : "";
			hDealDate2 = rowValues[10] != null ? rowValues[10].toString() : "";

			// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 1, hDataType, 6, hDealDate, 6, hDealTime, 19, hAccountNo, 8, hMacNo, 7, hDealAmt, 6, hDeal2Amt, 1, hDealStatus, 1, hBrandNm, 12, hDealNo, 8, hDealDate2, 25, " ");
			fileWriter.write(String.format("%1s%6s%6s%-19s%-8s%7s%6s%1s%1s%12s%8s%25s", hDataType, hDealDate, hDealTime, hAccountNo.trim(), hMacNo, hDealAmt, hDeal2Amt, hDealStatus, hBrandNm, hDealNo, hDealDate2, " "));
			// // fileWriter.newLine();

			lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);

			ncount++;
	    }

		// fprintf( fileWriter, "T%06d%013ld%*s\n", ncount, lnTotalAmt, 80, " " );
		fileWriter.write(String.format("T%06d%013d%80s", ncount, lnTotalAmt, " " ));
		// fileWriter.newLine();

		fileWriter.close();

		return 0;
	}

	private int GetBJTranData(String pTransDate, String pFileName) throws IOException {
		String hDataType;
		String hDealDate;
		String hDealTime;
		String hAccountNo;
		String hMacNo;
		String hDealAmt;
		String hDeal2Amt;
		String hDealStatus;
		String hDealNo;
		String hBrandNm;
		String hDealDate2;
		String hPreDate;

		String szDate;
		int		ncount;
		long	lnTotalAmt;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		hPreDate = fileSendMapper.pickupGetCUTranData(pTransDate);

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetBJTranData(hPreDate);
		Object[] rowValues;

		ncount = 0;
		lnTotalAmt = 0;

		szDate = hPreDate.substring(2);

		// fprintf( pfTran, "H%sATM%6s6%*s\n", szDate, "NICE", 83, " " );
		fileWriter.write(String.format("H%sATM%6s6%83s", szDate, "NICE", " "));
		// // fileWriter.newLine();

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);

			hDataType = rowValues[0] != null ? rowValues[0].toString() : "";
			hDealDate = rowValues[1] != null ? rowValues[1].toString() : "";
			hDealTime = rowValues[2] != null ? rowValues[2].toString() : "";
			hAccountNo = rowValues[3] != null ? rowValues[3].toString() : "";
			hMacNo = rowValues[4] != null ? rowValues[4].toString() : "";
			hDealAmt = rowValues[5] != null ? rowValues[5].toString() : "";
			hDeal2Amt = rowValues[6] != null ? rowValues[6].toString() : "";
			hDealStatus = rowValues[7] != null ? rowValues[7].toString() : "";
			hBrandNm = rowValues[8] != null ? rowValues[8].toString() : "";
			hDealNo = rowValues[9] != null ? rowValues[9].toString() : "";
			hDealDate2 = rowValues[10] != null ? rowValues[10].toString() : "";

			// fprintf( pfTran, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 1, hDataType, 6, hDealDate,	6, hDealTime, 19, hAccountNo, 8, hMacNo, 7, hDealAmt, 6, hDeal2Amt, 1, hDealStatus, 1, hBrandNm, 12, hDealNo, 8, hDealDate2, 25, " ");
			fileWriter.write(String.format("%1s%6s%6s%-19s%-8s%7s%6s%1s%1s%12s%8s%25s", hDataType, hDealDate, hDealTime, hAccountNo.trim(), hMacNo, hDealAmt, hDeal2Amt, hDealStatus, hBrandNm, hDealNo, hDealDate2, " "));
			// // fileWriter.newLine();

			lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);

			ncount++;
	    }

		// fprintf( pfTran, "T%06d%013ld%*s\n", ncount, lnTotalAmt, 80, " " );
		fileWriter.write(String.format("T%06d%013d%80s", ncount, lnTotalAmt, " " ));
		// // fileWriter.newLine();

		fileWriter.close();

		return 0;
	}
	
	private int GetCityTranData(String pTransDate, String pFileName) throws IOException {
		String hDataType;
		String hDealDate;
		String hDealTime;
		String hAccountNo;
		String hMacNo;
		String hDealAmt;
		String hDeal2Amt;
		String hDealStatus;
		String hDealNo;
		String hPreActDate;
		String hPreDate;

		String szDate;
		int		ncount;
		long	lnTotalAmt;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		/* 전송일이 휴일면 전송일을 전송일이 영업일이면 전 영업일을 return */
		hPreActDate = fileSendMapper.pickupGetCityTranData(pTransDate);

		if(hPreActDate == null) {
			throw new RuntimeException(">>> [GetCityTranData] 전영업일 없음. \n");
		}

		/* 씨티카드 휴일에는 전송하지 않는다. */
		if( pTransDate.equals(hPreActDate)) {
			throw new RuntimeException(String.format(">>> [GetCityTranData] 휴일 전송 안함 [%s]\n", pTransDate));
		}

		hPreDate = fileSendMapper.pickupGetCityTranData2(pTransDate);

		if(hPreDate == null) {
			throw new RuntimeException(">>> [GetCityTranData] 전일 없음. \n");
		}

		szDate = hPreDate;

		ncount = 0;
		lnTotalAmt = 0;

		// fprintf( fileWriter, "1NIC%s000000%*s\n", pTransDate, 52, " " );
		fileWriter.write(String.format("1NIC%s000000%52s", pTransDate, " " ));
		// fileWriter.newLine();

		while(true) {
			List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetCityTranData(szDate);
			Object[] rowValues;

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new Object[obj.size()]);
				
				hDataType = rowValues[0] != null ? rowValues[0].toString() : "";
				hDealDate = rowValues[1] != null ? rowValues[1].toString() : "";
				hDealTime = rowValues[2] != null ? rowValues[2].toString() : "";
				hAccountNo = rowValues[3] != null ? rowValues[3].toString() : "";
				hMacNo = rowValues[4] != null ? rowValues[4].toString() : "";
				hDealAmt = rowValues[5] != null ? rowValues[5].toString() : "";
				hDeal2Amt = rowValues[6] != null ? rowValues[6].toString() : "";
				hDealStatus = rowValues[7] != null ? rowValues[7].toString() : "";
				hDealNo = rowValues[8] != null ? rowValues[8].toString() : "";

				// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 1, hDataType, 8, hDealDate, 6, hDealTime, 16, hAccountNo, 4, hMacNo, 7, hDealAmt, 7, hDeal2Amt, 1, hDealStatus, 12, hDealNo, 8, " ");
				fileWriter.write(String.format("%1s%8s%6s%16s%4s%7s%7s%1s%12s%8s", hDataType, hDealDate, hDealTime, hAccountNo.trim(), hMacNo, hDealAmt, hDeal2Amt, hDealStatus, hDealNo, " "));
				// fileWriter.newLine();

				lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);

				ncount++;
		    }

			hPreDate = fileSendMapper.pickupGetCityTranData3(szDate);

			// System.out.print(String.format("DealDate[%s]-PreDate[%s]-preActDate[%s]\n", szDate, hPreDate, hPreActDate));

			if( Long.parseLong(hPreDate) < Long.parseLong(hPreActDate) ) {
				break;
			}

			szDate = hPreDate;
		}
		// fprintf( fileWriter, "3%08d%013ld%*s\n", ncount, lnTotalAmt, 48, " " );
		fileWriter.write(String.format("3%08d%013d%48s", ncount, lnTotalAmt, " " ));
		// fileWriter.newLine();

		fileWriter.close();

		return 0;
	}

	/* LG카드 */
	
	private int GetLGTranData(String pTransDate, String pFileName) throws IOException {
		String hDataType;
		String hDealDate;
		String hDealTime;
		String hAccountNo;
		String hMacNo;
		String hDealAmt;
		String hDealNo;
		String hPreActDate;
		String hPreDate;
		String hTradeType;

		String szDate;
		int		ncount;
		long	lnTotalAmt;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		/* 전송일이 휴일면 전송일을 전송일이 영업일이면 전 영업일을 return */
		hPreActDate = fileSendMapper.pickupGetLGTranData(pTransDate);

		if(hPreActDate == null) {
			throw new RuntimeException(">>> [GetLGTranData] 전영업일 없음. \n");
		}

		/* LG카드 휴일에는 전송하지 않는다. */
		if( pTransDate.equals(hPreActDate)) {
			throw new RuntimeException(String.format(">>> [GetLGTranData] 휴일 전송 안함 [%s]\n", pTransDate));
		}

		hPreDate = fileSendMapper.pickupGetLGTranData2(pTransDate);

		if(hPreDate == null) {
			throw new RuntimeException(">>> [GetLGTranData] 전일 없음. \n");
		}

		szDate = hPreDate;

		ncount = 0;
		lnTotalAmt = 0;

		// fprintf( fileWriter, "H%8s%8s%8s96%*s\n", pTransDate, hPreActDate, szDate, 43, " " );
		fileWriter.write(String.format("H%8s%8s%8s96%43s", pTransDate, hPreActDate, szDate, " " ));
		// fileWriter.newLine();

		while(true) {
			List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetLGTranData(szDate);
			Object[] rowValues;

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new Object[obj.size()]);
				
				hDataType = rowValues[0] != null ? rowValues[0].toString() : "";
				hTradeType = rowValues[1] != null ? rowValues[1].toString() : "";
				hDealDate = rowValues[2] != null ? rowValues[2].toString() : "";
				hDealTime = rowValues[3] != null ? rowValues[3].toString() : "";
				hDealNo = rowValues[4] != null ? rowValues[4].toString() : "";
				hAccountNo = rowValues[5] != null ? rowValues[5].toString() : "";
				hDealAmt = rowValues[6] != null ? rowValues[6].toString() : "";
				hMacNo = rowValues[7] != null ? rowValues[7].toString() : "";

				// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 1, hDataType, 2, hTradeType, 8, hDealDate, 6, hDealTime, 12, hDealNo, 16, hAccountNo, 7, hDealAmt, 4, hMacNo, 14, " ");
				fileWriter.write(String.format("%1s%2s%8s%6s%12s%-16s%7s%4s%14s", hDataType, hTradeType, hDealDate, hDealTime, hDealNo, hAccountNo.trim(), hDealAmt, hMacNo, " "));
				// fileWriter.newLine();

				lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);

				ncount++;
		    }

			hPreDate = fileSendMapper.pickupGetLGTranData3(szDate);

			if( Long.parseLong(hPreDate) < Long.parseLong(hPreActDate) ) {
				break;
			}

			szDate = hPreDate;
		}

		// fprintf( fileWriter, "T%06d%013ld%*s\n", ncount, lnTotalAmt, 50, " " );
		fileWriter.write(String.format("T%06d%013d%50s", ncount, lnTotalAmt, " " ));
		// fileWriter.newLine();

		fileWriter.close();

		return 0;
	}

	/* 신한카드론 */
	private int GetLCTranData(String pTransDate, String pFileName) throws IOException {
		String hDataType;
		String hDealNo;
		String hAccountNo;
		String hDealDate;
		String hDealTime;
		String hDealAmt;
		String hTradeType;
		String hTransOrgCd;
		String hTransAccountNo;
		String hDealStatus;
		String hPreDate;

		String szDate;

		long	lnTotalAmt;
		long	lnTotalCnt;
		long	lnNormalOutCnt;
		long	lnNormalOutAmt;
		long	lnNormalTransCnt;
		long	lnNormalTransAmt;
		long	lnEtcCnt;
		long	lnEtcAmt;
		long	lnErrCnt;
		long	lnErrAmt;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		hPreDate = fileSendMapper.pickupGetLCTranData(pTransDate);

		if(hPreDate == null) {
			throw new RuntimeException(">>> [GetLGTranData] 전일 없음.\n");
		}

		szDate = hPreDate;

		lnTotalAmt       = 0;
		lnTotalCnt       = 0;
		lnNormalOutCnt   = 0;
		lnNormalOutAmt   = 0;
		lnNormalTransCnt = 0;
		lnNormalTransAmt = 0;
		lnEtcCnt = 0;
		lnEtcAmt = 0;
		lnErrCnt         = 0;
		lnErrAmt         = 0;

		// fprintf( fileWriter, "HD%8s%8s89%*s\n", hPreDate, pTransDate, 180, " " );
		fileWriter.write(String.format("HD%8s%8s89%180s", hPreDate, pTransDate, " " ));
		// fileWriter.newLine();

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetLCTranData(szDate);
		Object[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hDataType = rowValues[0] != null ? rowValues[0].toString() : "";
			hDealNo = rowValues[1] != null ? rowValues[1].toString() : "";
			hAccountNo = rowValues[2] != null ? rowValues[2].toString() : "";
			hDealDate = rowValues[3] != null ? rowValues[3].toString() : "";
			hDealTime = rowValues[4] != null ? rowValues[4].toString() : "";
			hDealAmt = rowValues[5] != null ? rowValues[5].toString() : "";
			hTradeType = rowValues[6] != null ? rowValues[6].toString() : "";
			hTransOrgCd = rowValues[7] != null ? rowValues[7].toString() : "";
			hTransAccountNo = rowValues[8] != null ? rowValues[8].toString() : "";
			hDealStatus = rowValues[9] != null ? rowValues[9].toString() : "";
			
			if (hAccountNo.length() > 16) hAccountNo = hAccountNo.substring(0, 16);
			if (hTransAccountNo.length() > 20) hTransAccountNo = hTransAccountNo.substring(0, 20);

			// fprintf( fileWriter, "%.*s89%.*s%.*s%.*s%.*s%.*s%.*s00  %.*s%.*s%.*s%.*s%*s\n", 2, hDataType, 12, hDealNo, 16, hAccountNo, 8, hDealDate, 6, hDealTime, 13, hDealAmt, 1, hTradeType, 6, hDealTime, 2, hTransOrgCd, 20, hTransAccountNo, 1, hDealStatus, 107, " ");
			fileWriter.write(String.format("%2s89%12s%-16s%8s%6s%13s%1s00  %6s%2s%-20s%1s%107s", hDataType, hDealNo, hAccountNo.trim(), hDealDate, hDealTime, hDealAmt, hTradeType, hDealTime, hTransOrgCd, hTransAccountNo, hDealStatus, " "));
			// fileWriter.newLine();

			lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);
			lnTotalCnt++;

			/* 정상건 */
			if( "N".equals(hDealStatus)) {
				/* 지급건 */
				if( "1".equals(hTradeType)) {
					lnNormalOutAmt = lnNormalOutAmt + Long.parseLong(hDealAmt);
					lnNormalOutCnt++;
				} else if( "4".equals(hTradeType)) { /* 이체건 */
					lnNormalTransAmt = lnNormalTransAmt + Long.parseLong(hDealAmt);
					lnNormalTransCnt++;
				} else { /* 약정 */
					lnEtcAmt = lnEtcAmt + Long.parseLong(hDealAmt);
					lnEtcCnt++;
				}
			} else { /* 오류건 */
				lnErrAmt = lnErrAmt + Long.parseLong(hDealAmt);
				lnErrCnt++;
			}
	    }

		// fprintf( fileWriter, "TR89%010d%013d%010d%013d%010d%013d%010d%013d%010d%013d%*s\n", lnTotalCnt ,lnTotalAmt ,lnNormalOutCnt ,lnNormalOutAmt , lnNormalTransCnt ,lnNormalTransAmt ,lnEtcCnt ,lnEtcAmt , lnErrCnt ,lnErrAmt, 81, " " );
		fileWriter.write(String.format("TR89%010d%013d%010d%013d%010d%013d%010d%013d%010d%013d%81s", lnTotalCnt ,lnTotalAmt ,lnNormalOutCnt ,lnNormalOutAmt , lnNormalTransCnt ,lnNormalTransAmt ,lnEtcCnt ,lnEtcAmt , lnErrCnt ,lnErrAmt, " " ));
		// fileWriter.newLine();

		fileWriter.close();

		return 0;
	}

	/* 농협 * /
	private int GetNongHTranData(String pTransDate, String pFileName) {
		String hDealDate;
		String hDealTime;
		String hAccountNo;
		String hMacNo;
		String hDealAmt;
		String hDealNo;
		String hPreActDate;
		String hPreDate;
		String hTradeType;
		String hJoinType;
		String hJoinMojum;
		String hJoinCd;
		String hLastType;
		String hOrgRespCd;
		String hOrgCd;
		String hFee;
		String hTransOrgCd;
		String hTransAccountNo;
		String hAtmType;

		int		ncount;
		String szDate;
		String szCnt;
		String hTransDate6;

		long	lnTotalAmt;

		File fileWriter;

		// 전송일이 휴일면 전송일을.. 전송일이 영업일이면 전 영업일을 return
		hPreActDate = fileSendMapper.pickupGetNongHTranData(pTransDate);

		if(hPreActDate == null) {
			System.out.print(String.format(">>> [GetNongHTranData] 전영업일 없음. \n"));
			return -1;
		}

		// 휴일에는 전송하지 않는다.
		if( pTransDate.equals(hPreActDate)) {
			System.out.print(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 않음 \n", pTransDate, hPreActDate));
			return -2;
		}

		hPreDate = fileSendMapper.pickupGetNongHTranData2(pTransDate);

		if(hPreDate == null) {
			System.out.print(String.format(">>> [GetNongHTranData] 전일 없음. \n"));
			return -1;
		}

	 	if(( fileWriter = pFileName) == null ) {
			System.out.print(String.format(">>> [GetNongHTranData] FileOpenError [%s]\n", pFileName));
			return -1;
		}

		szDate = hPreDate;

		ncount = 0;
		lnTotalAmt = 0;

		hTransDate6 = pTransDate.substring(2);

		// fprintf( fileWriter, "ATM03311000000011960000000%6s%*s\n", hTransDate6, 138, " " );
		fileWriter.write(String.format("ATM03311000000011960000000%6s%138s\n", hTransDate6, " " ));

		while(true) {
			List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetNongHTranData(szDate);
			Object[] rowValues;

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new Object[obj.size()]);
				
				hDealDate = rowValues[0] != null ? rowValues[0].toString() : "";
				hDealTime = rowValues[1] != null ? rowValues[1].toString() : "";
				hDealNo = rowValues[2] != null ? rowValues[2].toString() : "";
				hJoinType = rowValues[3] != null ? rowValues[3].toString() : "";
				hJoinMojum = rowValues[4] != null ? rowValues[4].toString() : "";
				hJoinCd = rowValues[5] != null ? rowValues[5].toString() : "";
				hMacNo = rowValues[6] != null ? rowValues[6].toString() : "";
				hTradeType = rowValues[7] != null ? rowValues[7].toString() : "";
				hLastType = rowValues[8] != null ? rowValues[8].toString() : "";
				hOrgRespCd = rowValues[9] != null ? rowValues[9].toString() : "";
				hOrgCd = rowValues[10] != null ? rowValues[10].toString() : "";
				hAccountNo = rowValues[11] != null ? rowValues[11].toString() : "";
				hDealAmt = rowValues[12] != null ? rowValues[12].toString() : "";
				hFee = rowValues[13] != null ? rowValues[13].toString() : "";
				hTransOrgCd = rowValues[14] != null ? rowValues[14].toString() : "";
				hTransAccountNo = rowValues[15] != null ? rowValues[15].toString() : "";
				hAtmType = rowValues[16] != null ? rowValues[16].toString() : "";

				// fprintf( fileWriter, "ATM03322%07ld", ncount+1 );
				fileWriter.write(String.format("ATM03322%07d", ncount+1 ));
				// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 8, hDealDate, 6, hDealTime, 12, hDealNo, 1, hJoinType, 5, hJoinMojum, 2, hJoinCd, 4, hMacNo, 6, hTradeType, 4, hLastType, 2, hOrgRespCd, 2, hOrgCd, 17, hAccountNo, 12, hDealAmt, 5, hFee, 2, hTransOrgCd, 14, hTransAccountNo, 2, hAtmType, 51, " ");
				fileWriter.write(String.format("%8s%6s%12s%1s%5s%2s%4s%6s%4s%2s%2s%17s%12s%5s%2s%14s%2s%51s\n", hDealDate, hDealTime, hDealNo, hJoinType, hJoinMojum, hJoinCd, hMacNo, hTradeType, hLastType, hOrgRespCd, hOrgCd, hAccountNo, hDealAmt, hFee, hTransOrgCd, hTransAccountNo, hAtmType, " "));

				lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);

				ncount++;
		    }

			hPreDate = fileSendMapper.pickupGetNongHTranData3(szDate);

			if( Long.parseLong(hPreDate) < Long.parseLong(hPreActDate) ) {
				break;
			}

			szDate = hPreDate;
		}

		// fprintf( fileWriter, "ATM0333399999991196%07ld%.8s%*s\n", ncount, pTransDate, 136, " " );
		fileWriter.write(String.format("ATM0333399999991196%07d%8s%136s\n", ncount, pTransDate, " " ));

		szCnt = String.format("%07d", ncount );
		fseek (fileWriter, 6+2+7+2+2, 0 );
		// fprintf( fileWriter, "%7s", szCnt );
		fileWriter.write(String.format("%7s", szCnt ));

		fileWriter.close();

		return 0;
	}
	*/

	/* 농협 * /
	private int GetNongHTranData_dasi(String pTransDate, String pFileName) {
		String hDealDate;
		String hDealTime;
		String hAccountNo;
		String hMacNo;
		String hDealAmt;
		String hDealStatus;
		String hDealNo;
		String hPreActDate;
		String hPreDate;
		String hTradeType;
		String hJoinType;
		String hJoinMojum;
		String hJoinCd;
		String hLastType;
		String hOrgRespCd;
		String hOrgCd;
		String hFee;
		String hTransOrgCd;
		String hTransAccountNo;
		String hAtmType;

		int		ncount;
		String szContent;
		String szDate;
		String szCnt;
		String hFirstDate;
		String hTransDate6;

		long	lnTotalAmt;

		File fileWriter;

		// 전송일이 휴일면 전송일을.. 전송일이 영업일이면 전 영업일을 return
		hPreActDate = fileSendMapper.pickupGetNongHTranData_dasi(pTransDate);

		if(hPreActDate == null) {
			System.out.print(String.format(">>> [GetNongHTranData] 전영업일 없음. \n"));
			return -1;
		}

		// 휴일에는 전송하지 않는다.
		if( pTransDate.equals(hPreActDate)) {
			System.out.print(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 않음 \n", pTransDate, hPreActDate));
			return -2;
		}

		hPreDate = fileSendMapper.pickupGetNongHTranData_dasi2(pTransDate);

		if(hPreDate == null) {
			System.out.print(String.format(">>> [GetNongHTranData] 전일 없음. \n"));
			return -1;
		}

	 	if(( fileWriter = pFileName) == null ) {
			System.out.print(String.format(">>> [GetNongHTranData] FileOpenError [%s]\n", pFileName));
			return -1;
		}

		szDate = hPreDate;

		ncount = 0;
		lnTotalAmt = 0;
		
		hTransDate6 = pTransDate.substring(2);

		// fprintf( fileWriter, "ATM03311000000011960000000%6s%*s\n", hTransDate6, 138, " " );
		fileWriter.write(String.format("ATM03311000000011960000000%6s%138s\n", hTransDate6, " " ));

		while(true) {
			List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetNongHTranData_dasi(szDate);
			Object[] rowValues;

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new Object[obj.size()]);
				
				hDealDate = rowValues[0] != null ? rowValues[0].toString() : "";
				hDealTime = rowValues[1] != null ? rowValues[1].toString() : "";
				hDealNo = rowValues[2] != null ? rowValues[2].toString() : "";
				hJoinType = rowValues[3] != null ? rowValues[3].toString() : "";
				hJoinMojum = rowValues[4] != null ? rowValues[4].toString() : "";
				hJoinCd = rowValues[5] != null ? rowValues[5].toString() : "";
				hMacNo = rowValues[6] != null ? rowValues[6].toString() : "";
				hTradeType = rowValues[7] != null ? rowValues[7].toString() : "";
				hLastType = rowValues[8] != null ? rowValues[8].toString() : "";
				hOrgRespCd = rowValues[9] != null ? rowValues[9].toString() : "";
				hOrgCd = rowValues[10] != null ? rowValues[10].toString() : "";
				hAccountNo = rowValues[11] != null ? rowValues[11].toString() : "";
				hDealAmt = rowValues[12] != null ? rowValues[12].toString() : "";
				hFee = rowValues[13] != null ? rowValues[13].toString() : "";
				hTransOrgCd = rowValues[14] != null ? rowValues[14].toString() : "";
				hTransAccountNo = rowValues[15] != null ? rowValues[15].toString() : "";
				hAtmType = rowValues[16] != null ? rowValues[16].toString() : "";

				// fprintf( fileWriter, "ATM03322%07ld", ncount+1 );
				fileWriter.write(String.format("ATM03322%07d", ncount+1 ));
				// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 8, hDealDate, 6, hDealTime, 12, hDealNo, 1, hJoinType, 5, hJoinMojum, 2, hJoinCd, 4, hMacNo, 6, hTradeType, 4, hLastType, 2, hOrgRespCd, 2, hOrgCd, 17, hAccountNo, 12, hDealAmt, 5, hFee, 2, hTransOrgCd, 14, hTransAccountNo, 2, hAtmType, 51, " ");
				fileWriter.write(String.format("%8s%6s%12s%1s%5s%2s%4s%6s%4s%2s%2s%17s%12s%5s%2s%14s%2s%51s\n", hDealDate, hDealTime, hDealNo, hJoinType, hJoinMojum, hJoinCd, hMacNo, hTradeType, hLastType, hOrgRespCd, hOrgCd, hAccountNo, hDealAmt, hFee, hTransOrgCd, hTransAccountNo, hAtmType, " "));

				lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);

				ncount++;
		    }

			hPreDate = fileSendMapper.pickupGetNongHTranData_dasi3(szDate);

			if( Long.parseLong(hPreDate) < Long.parseLong(hPreActDate) ) {
				break;
			}

			szDate = hPreDate;
		}
		// fprintf( fileWriter, "ATM0333399999991196%07ld%.8s%*s\n", ncount, pTransDate, 136, " " );
		fileWriter.write(String.format("ATM0333399999991196%07d%8s%136s\n", ncount, pTransDate, " " ));

		szCnt = String.format("%07d", ncount );
		fseek (fileWriter, 6+2+7+2+2, 0 );
		// fprintf( fileWriter, "%7s", szCnt );
		fileWriter.write(String.format("%7s", szCnt ));

		fileWriter.close();

		return 0;
	}
	*/

	/* 농협 */
	private int GetNongHTranData_NEW(String pTransDate, String pFileName) throws IOException {
		String hDealDate;
		String hDealTime;
		String hAccountNo;
		String hMacNo;
		String hDealAmt;
		String hDealNo;
		String hPreActDate;
		String hPreDate;
		String hTradeType;
		String hJoinMojum;
		String hJoinCd;
		String hLastType;
		String hOrgRespCd;
		String hOrgCd;
		String hFee;
		String hTransOrgCd;
		String hTransAccountNo;
		String hAtmType;

		String szDate;
		int		ncount;
		long	lnTotalAmt;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		/* 전송일이 휴일면 전송일을.. 전송일이 영업일이면 전 영업일을 return */
		hPreActDate = fileSendMapper.pickupGetNongHTranData_NEW(pTransDate);

		if(hPreActDate == null) {
			throw new RuntimeException(">>> [GetNongHTranData] 전영업일 없음. \n");
		}

		/* 휴일에는 전송하지 않는다. */
		if( pTransDate.equals(hPreActDate)) {
			throw new RuntimeException(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 않음 \n", pTransDate, hPreActDate));
		}

		hPreDate = fileSendMapper.pickupGetNongHTranData_NEW2(pTransDate);

		if(hPreDate == null) {
			throw new RuntimeException(">>> [GetNongHTranData] 전일 없음. \n");
		}

		szDate = hPreDate;

		ncount = 0;
		lnTotalAmt = 0;

		// fprintf( fileWriter, "VC0033110000000011NC 0000000%8s%*s\n", hPreDate, 134, " " );
		fileWriter.write(String.format("VC0033110000000011NC 0000000%8s%134s", hPreDate, " " ));
		// fileWriter.newLine();

		/* 20110609 기관응답코드를 N0, N1,N2 에 대해서도 정상으로 변환처리 */
		while(true) {
			List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetNongHTranData_NEW(szDate);
			Object[] rowValues;

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new Object[obj.size()]);
				
				hDealDate = rowValues[0] != null ? rowValues[0].toString() : "";
				hDealTime = rowValues[1] != null ? rowValues[1].toString() : "";
				hDealNo = rowValues[2] != null ? rowValues[2].toString() : "";
				hJoinMojum = rowValues[3] != null ? rowValues[3].toString() : "";
				hJoinCd = rowValues[4] != null ? rowValues[4].toString() : "";
				hMacNo = rowValues[5] != null ? rowValues[5].toString() : "";
				hTradeType = rowValues[6] != null ? rowValues[6].toString() : "";
				hLastType = rowValues[7] != null ? rowValues[7].toString() : "";
				hOrgRespCd = rowValues[8] != null ? rowValues[8].toString() : "";
				hOrgCd = rowValues[9] != null ? rowValues[9].toString() : "";
				hAccountNo = rowValues[10] != null ? rowValues[10].toString() : "";
				hDealAmt = rowValues[11] != null ? rowValues[11].toString() : "";
				hFee = rowValues[12] != null ? rowValues[12].toString() : "";
				hTransOrgCd = rowValues[13] != null ? rowValues[13].toString() : "";
				hTransAccountNo = rowValues[14] != null ? rowValues[14].toString() : "";
				hAtmType = rowValues[15] != null ? rowValues[15].toString() : "";
				
				if (hTransAccountNo.length() > 14) hTransAccountNo = hTransAccountNo.substring(0, 14);
				if (hAccountNo.length() > 17) hAccountNo = hAccountNo.substring(0, 17);

				// fprintf( fileWriter, "VC003322%07ld", ncount+1 );
				// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 8, hDealDate, 6, hDealTime, 13, hDealNo, 6, hJoinMojum, 3, hJoinCd, 4, hMacNo, 6, hTradeType, 4, hLastType, 2, hOrgRespCd, 3, hOrgCd, 17, hAccountNo, 12, hDealAmt, 5, hFee, 3, hTransOrgCd, 14, hTransAccountNo, 2, hAtmType, 47, " ");
				fileWriter.write(String.format("VC003322%07d%8s%6s%13s%6s%3s%4s%6s%4s%2s%3s%-17s%12s%5s%3s%-14s%2s%47s", ncount+1, hDealDate, hDealTime, hDealNo, hJoinMojum, hJoinCd, hMacNo, hTradeType, hLastType, hOrgRespCd, hOrgCd, hAccountNo.trim(), hDealAmt, hFee, hTransOrgCd, hTransAccountNo, hAtmType, " "));
				// fileWriter.newLine();

				lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);

				ncount++;
		    }

			hPreDate = fileSendMapper.pickupGetNongHTranData_NEW3(szDate);

			if( Long.parseLong(hPreDate) < Long.parseLong(hPreActDate) ) {
				break;
			}

			szDate = hPreDate;
		}
		
		// fprintf( fileWriter, "VC0033339999999011NC %07ld%.8s%*s\n", ncount, hPreDate, 134, " " );
		fileWriter.write(String.format("VC0033339999999011NC %07d%8s%134s", ncount, hPreDate, " " ));
		// fileWriter.newLine();

		fileWriter.close();
		
		// fseek (fileWriter, 6+2+7+3+3, 0 );
		// fprintf( fileWriter, "%7s", szCnt );
		RandomAccessFile raf = new RandomAccessFile(pFileName, "rw");
		raf.seek(6+2+7+3+3);
		raf.writeBytes(String.format("%07d", ncount ));
		raf.close();

		return 0;
	}

	/* 농협 */
	private int GetNongHMacData(String pTransDate, String pFileName) throws IOException {
		String hMacNo;
		String hType;
		String hSetPlace;
		String hSetAddr;
		String hInterphoneNo;
		String hZipNo;
		String hJoinMojumCd;

		String hActDate;
		int		ncount;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		/* 매월 첫일이 휴일면 익영업일을.. 그렇지 않으면 전송일을 return */
		hActDate = fileSendMapper.pickupGetNongHMacData(pTransDate);

		if(hActDate == null) {
			throw new RuntimeException(">>> [GetNongHTranData] 익영업일 없음. \n");
		}

		/* 휴일에는 전송하지 않는다. */
		if( pTransDate.equals(hActDate)) {
			throw new RuntimeException(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 않음, 매달 첫 영업일에만 전송 \n", pTransDate, hActDate));
		}

		ncount = 0;

		// fprintf( fileWriter, "VC0002110000000011NC 0000000%8s%*s\n", pTransDate, 164, " " );
		fileWriter.write(String.format("VC0002110000000011NC 0000000%8s%164s", pTransDate, " " ));
		// fileWriter.newLine();

		/* 브랜드 제휴가 아닌 일반 CD VAN 일 경우 '096'->'NCJ'로 변경
			NCJ 와 별개로 브랜드 제휴가 아닌 기기를 '096'으로 아래 추가 20120703 BY 이재원 */
		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetNongHMacData(pTransDate);
		Object[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hType = rowValues[0] != null ? rowValues[0].toString() : "";
			hMacNo = rowValues[1] != null ? rowValues[1].toString() : "";
			hSetPlace = rowValues[2] != null ? rowValues[2].toString() : "";
			hSetAddr = rowValues[3] != null ? rowValues[3].toString() : "";
			hInterphoneNo = rowValues[4] != null ? rowValues[4].toString() : "";
			hZipNo = rowValues[5] != null ? rowValues[5].toString() : "";
			hJoinMojumCd = rowValues[6] != null ? rowValues[6].toString() : "";

			// fprintf( fileWriter, "VC000222%07ldI", ncount+1 );
			fileWriter.write(String.format("VC000222%07dI", ncount+1 ));
			// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 3, hType, 4, hMacNo, 30, hSetPlace, 100, hSetAddr, 15, hInterphoneNo, 6, hZipNo, 6, hJoinMojumCd, 20, " ");
			// fileWriter.write(String.format("%3s%4s%30s%100s%15s%6s%6s%20s\n", hType, hMacNo, hSetPlace, hSetAddr, hInterphoneNo, hZipNo, hJoinMojumCd, " "));
			fileWriter.write(String.format("%3s%4s%-"+minHanCount(30, hSetPlace)+"s%-"+minHanCount(100, hSetAddr)+"s%15s%6s%6s%20s", hType, hMacNo, hSetPlace.trim(), hSetAddr.trim(), hInterphoneNo, hZipNo, hJoinMojumCd, " "));
			// fileWriter.newLine();

			ncount++;
	    }

		// fprintf( fileWriter, "VC0002339999999011NC %07ld%.8s%*s\n", ncount, pTransDate, 164, " " );
		fileWriter.write(String.format("VC0002339999999011NC %07d%8s%164s", ncount, pTransDate, " " ));
		// fileWriter.newLine();

		fileWriter.close();
		
		// fseek (fileWriter, 6+2+7+3+3, 0 );
		// fprintf( fileWriter, "%7s", szCnt );
		RandomAccessFile raf = new RandomAccessFile(pFileName, "rw");
		raf.seek(6+2+7+3+3);
		raf.writeBytes(String.format("%07d", ncount ));
		raf.close();

		return 0;
	}

	/* 농협 브랜드제휴 기기정보(마사회제외) */
	
	private int GetNongHBrandMacData(String pTransDate, String pFileName) throws IOException {
		String hMacNo;
		String hMacModel;
		String hOpenDate;

		String hActDate;
		String hPreActDate;
		String hPreActYM;
		int		ncount;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		/* 매월 첫일이 휴일면 익영업일을.. 그렇지 않으면 전송일을 return */
		hActDate = fileSendMapper.pickupGetNongHBrandMacData(pTransDate);

		if(hActDate == null) {
			throw new RuntimeException(">>> [GetNongHTranData] 익영업일 없음. \n");
		}

		/* 휴일에는 전송하지 않는다. */
		if( pTransDate.equals(hActDate)) {
			throw new RuntimeException(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 않음, 매달 첫 영업일에만 전송 \n", pTransDate, hActDate));
		}

		hPreActDate = fileSendMapper.pickupGetNongHBrandMacData2(pTransDate);

		hPreActYM = hPreActDate.substring(0, 6);

		ncount = 0;

		// fprintf( fileWriter, "VC000111NCI*******%6s%*s\n", hPreActYM, 26, " " );
		fileWriter.write(String.format("VC000111NCI*******%6s%26s", hPreActYM, " " ));
		// fileWriter.newLine();

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetNongHBrandMacData(hPreActDate);
		Object[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hMacNo = rowValues[0] != null ? rowValues[0].toString() : "";
			hMacModel = rowValues[1] != null ? rowValues[1].toString() : "";
			hOpenDate = rowValues[2] != null ? rowValues[2].toString() : "";

			// fprintf( fileWriter, "VC111122%07ld%6sNCI", ncount+1, hPreActYM );
			fileWriter.write(String.format("VC111122%07d%6sNCI", ncount+1, hPreActYM ));
			// fprintf( fileWriter, "%.*s%.*s%.*s1000000000000\n", 4, hMacNo, 1, hMacModel, 8, hOpenDate);
			fileWriter.write(String.format("%4s%1s%8s1000000000000", hMacNo, hMacModel, hOpenDate));
			// fileWriter.newLine();

			ncount++;
	    }

		fileWriter.close();
		
		// fseek (fileWriter, 6+2+3, 0 );
		// fprintf( fileWriter, "%7s", szCnt );
		RandomAccessFile raf = new RandomAccessFile(pFileName, "rw");
		raf.seek(6+2+3);
		raf.writeBytes(String.format("%07d", ncount ));
		raf.close();

		return 0;
	}

	/* 은행 공통 - 대구, 우리, 제일, 농협중앙회, 농협단위조합, 하나(공동망), 외환
	카드 */
	private int GetCommonTranData(String pTransDate, String pOrgCd, String pFileName) throws IOException {
		String hWorkType;
		String hDataType;
		String hDealDate;
		String hAtmDealNo;
		String hInsetOrgCd;
		String hAccountNo;
		String hDealAmt;
		String hCustFee;
		String hBankFee;
		String hOrgRespCd;
		String hDealNo;
		String hDealStatus;
		String hPreActDate;
		String hPreDate;

		String szDate;
		String szOrgCd;
		int		ncount;
		int		nDataCnt;

		int		nTotAcptCnt;
		long	lnTotAcptAmt;
		long	lnTotCustAmt;
		long	lnTotBankAmt;
		int		nTotCancelCnt;
		long	lnTotCancelAmt;
		int		nTotNotMngCnt;
		long	lnTotNotMngAmg;
		int		nTotRejCnt;
		long	lnTotRejAmt;
		
		List<LinkedHashMap<String, Object>> list;
		Object[] rowValues;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		szOrgCd = pOrgCd.substring(1);

		/* 전송일이 휴일이면 전송일을 전송일이 영업일이면 전 영업일을 return */
		hPreActDate = fileSendMapper.pickupGetCommonTranData(pTransDate);

		if(hPreActDate == null) {
			throw new RuntimeException(String.format(">>> [GetCommonTranData-%s] 전영업일 없음.\n", pOrgCd));
		}

		/* 휴일에는 전송하지 않는다. */
		if( pTransDate.equals(hPreActDate)) {
			throw new RuntimeException(String.format(">>> [GetCommonTranData-%s] 휴일 전송 안함 [%s]\n", szOrgCd,  pTransDate));
		}

		hPreDate = fileSendMapper.pickupGetCommonTranData2(pTransDate);

		if(hPreDate == null) {
			throw new RuntimeException(String.format(">>> [GetCommonTranData-%s] 전일 없음.\n", pOrgCd));
		}

		szDate = hPreDate;

		ncount = 0;

		/* 전체 Header */
		// fprintf( fileWriter, "ATM001110000000%2s030000000%.*s%*s\n", szOrgCd, 6, pTransDate.substring(2), 138, " " );
		fileWriter.write(String.format("ATM001110000000%2s030000000%6s%138s", szOrgCd, pTransDate.substring(2), " " ));
		// fileWriter.newLine();

		while(true) {
			/* 영업내 Data부 Header */
			// fprintf( fileWriter, "ATM00122%07ld%2s03 012200110096%4s%6s%6s%6s%*s\n", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), 116, " " );
			fileWriter.write(String.format("ATM00122%07d%2s03 01220011009600%2s%6s%6s%6s%116s", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));
			// fileWriter.newLine();

			/* Header 와 Trailer도 count에 포함 */
			ncount++;

			nDataCnt = 0;
			nTotAcptCnt     = 0;
			lnTotAcptAmt    = 0;
			lnTotCustAmt    = 0;
			lnTotBankAmt    = 0;
			nTotCancelCnt   = 0;
			lnTotCancelAmt  = 0;
			nTotNotMngCnt   = 0;
			lnTotNotMngAmg  = 0;
			nTotRejCnt      = 0;
			lnTotRejAmt     = 0;

			list = fileSendMapper.selectGetCommonTranData(szDate, szOrgCd);
			
			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new Object[obj.size()]);
				
				hWorkType = rowValues[0] != null ? rowValues[0].toString() : "";
				hDataType = rowValues[1] != null ? rowValues[1].toString() : "";
				hDealDate = rowValues[2] != null ? rowValues[2].toString() : "";
				hAtmDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
				hInsetOrgCd = rowValues[4] != null ? rowValues[4].toString() : "";
				hAccountNo = rowValues[5] != null ? rowValues[5].toString() : "";
				hDealAmt = rowValues[6] != null ? rowValues[6].toString() : "";
				hCustFee = rowValues[7] != null ? rowValues[7].toString() : "";
				hBankFee = rowValues[8] != null ? rowValues[8].toString() : "";
				hOrgRespCd = rowValues[9] != null ? rowValues[9].toString() : "";
				hDealNo = rowValues[10] != null ? rowValues[10].toString() : "";
				hDealStatus = rowValues[11] != null ? rowValues[11].toString() : "";

				if(  pOrgCd.equals("020") ) {
					// memset(hAccountNo.substring(7), '*', strlen(hAccountNo)-7);
					hAccountNo = StringUtils.rightPad(hAccountNo.substring(0, 7), hAccountNo.length(), "*");
				}
				
				if (hAccountNo.length() > 22) hAccountNo = hAccountNo.substring(0, 22);

				// fprintf( fileWriter, "ATM00122%07ld%2s03 %s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%*s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, 1 ," ");
				fileWriter.write(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo, " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus ," "));
				// fileWriter.newLine();

				/* 승인건수, 금액 */
				if("0".equals(hDealStatus)) {
					nTotAcptCnt++;
					lnTotAcptAmt    += Long.parseLong(hDealAmt);
					lnTotCustAmt    += Long.parseLong(hCustFee);
					lnTotBankAmt    += Long.parseLong(hBankFee);
				} else if("1".equals(hDealStatus)) { /* 취소 */
					nTotCancelCnt++;
					lnTotCancelAmt  += Long.parseLong(hDealAmt);
				} else if("2".equals(hDealStatus)) { /* 미완료 */
					nTotNotMngCnt ++;
					lnTotNotMngAmg  += Long.parseLong(hDealAmt);
				} else if("3".equals(hDealStatus)) { /* 거절 */
					nTotRejCnt ++;
					lnTotRejAmt    += Long.parseLong(hDealAmt);
				}

				nDataCnt++;
				ncount++;
		    }

			/* 영업내 Data부 Trailer */
			// fprintf( fileWriter, "ATM00122%07ld%2s03 01220033  %06d%014ld%010ld%010ld%06ld%014ld%06ld%014ld%06ld%014ld%*s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, 40, " " );
			fileWriter.write(String.format("ATM00122%07d%2s03 01220033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));
			// fileWriter.newLine();
			ncount++;

			/* 영업외 Data부 Header */
			// fprintf( fileWriter, "ATM00122%07ld%2s03 012300110096%4s%6s%6s%6s%*s\n", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), 116, " " );
			fileWriter.write(String.format("ATM00122%07d%2s03 01230011009600%2s%6s%6s%6s%116s", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));
			// fileWriter.newLine();
			ncount++;

			nDataCnt = 0;

			nTotAcptCnt     = 0;
			lnTotAcptAmt    = 0;
			lnTotCustAmt    = 0;
			lnTotBankAmt    = 0;
			nTotCancelCnt   = 0;
			lnTotCancelAmt  = 0;
			nTotNotMngCnt   = 0;
			lnTotNotMngAmg  = 0;
			nTotRejCnt      = 0;
			lnTotRejAmt     = 0;

			list = fileSendMapper.selectGetCommonTranData2(szDate, szOrgCd);
			
			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new Object[obj.size()]);
				
				hWorkType = rowValues[0] != null ? rowValues[0].toString() : "";
				hDataType = rowValues[1] != null ? rowValues[1].toString() : "";
				hDealDate = rowValues[2] != null ? rowValues[2].toString() : "";
				hAtmDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
				hInsetOrgCd = rowValues[4] != null ? rowValues[4].toString() : "";
				hAccountNo = rowValues[5] != null ? rowValues[5].toString() : "";
				hDealAmt = rowValues[6] != null ? rowValues[6].toString() : "";
				hCustFee = rowValues[7] != null ? rowValues[7].toString() : "";
				hBankFee = rowValues[8] != null ? rowValues[8].toString() : "";
				hOrgRespCd = rowValues[9] != null ? rowValues[9].toString() : "";
				hDealNo = rowValues[10] != null ? rowValues[10].toString() : "";
				hDealStatus = rowValues[11] != null ? rowValues[11].toString() : "";

				if(  pOrgCd.equals("020") ) {
					// memset(hAccountNo.substring(7), '*', strlen(hAccountNo)-7);
					hAccountNo = StringUtils.rightPad(hAccountNo.substring(0, 7), hAccountNo.length(), "*");
				}

				if (hAccountNo.length() > 22) hAccountNo = hAccountNo.substring(0, 22);

				// fprintf( fileWriter, "ATM00122%07ld%2s03 %s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%*s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, 1 ," ");
				fileWriter.write(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo.trim(), " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus ," "));
				// fileWriter.newLine();

				/* 승인건수, 금액 */
				if("0".equals(hDealStatus)) {
					nTotAcptCnt++;
					lnTotAcptAmt    += Long.parseLong(hDealAmt);
					lnTotCustAmt    += Long.parseLong(hCustFee);
					lnTotBankAmt    += Long.parseLong(hBankFee);
				} else if("1".equals(hDealStatus)) { /* 취소 */
					nTotCancelCnt++;
					lnTotCancelAmt  += Long.parseLong(hDealAmt);
				} else if("2".equals(hDealStatus)) { /* 미완료 */
					nTotNotMngCnt ++;
					lnTotNotMngAmg  += Long.parseLong(hDealAmt);
				} else if("3".equals(hDealStatus)) { /* 거절 */
					nTotRejCnt ++;
					lnTotRejAmt    += Long.parseLong(hDealAmt);
				}

				nDataCnt++;
				ncount++;
		    }

			/* 영업외 Data부 Trailer */
			// fprintf( fileWriter, "ATM00122%07ld%2s03 01230033  %06d%014ld%010ld%010ld%06ld%014ld%06ld%014ld%06ld%014ld%*s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, 40, " " );
			fileWriter.write(String.format("ATM00122%07d%2s03 01230033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));
			// fileWriter.newLine();

			ncount++;

			hPreDate = fileSendMapper.pickupGetCommonTranData3(szDate);

			if( Long.parseLong(hPreDate) < Long.parseLong(hPreActDate) ) {
				break;
			}

			szDate = hPreDate;
		}

		// fprintf( fileWriter, "ATM001339999999%2s03%07ld%*s\n", szOrgCd, ncount, 144, " " );
		fileWriter.write(String.format("ATM001339999999%2s03%07d%144s", szOrgCd, ncount, " " ));
		// fileWriter.newLine();

		fileWriter.close();

		// fseek (fileWriter, 6+2+7+2+2, 0 );
		// fprintf( fileWriter, "%7s", szCnt );
		RandomAccessFile raf = new RandomAccessFile(pFileName, "rw");
		raf.seek(6+2+7+2+2);
		raf.writeBytes(String.format("%07d", ncount ));
		raf.close();

		return 0;
	}
	
	private int GetKiupTranData(String pTransDate, String pOrgCd, String pFileName) throws IOException {
		String hWorkType;
		String hDataType;
		String hDealDate;
		String hAtmDealNo;
		String hInsetOrgCd;
		String hAccountNo;
		String hDealAmt;
		String hCustFee;
		String hBankFee;
		String hOrgRespCd;
		String hDealNo;
		String hDealStatus;
		String hPreActDate;
		String hPreDate;

		int		ncount;
		int		nDataCnt;
		String szDate;
		String szOrgCd;

		int		nTotAcptCnt;
		long	lnTotAcptAmt;
		long	lnTotCustAmt;
		long	lnTotBankAmt;
		int		nTotCancelCnt;
		long	lnTotCancelAmt;
		int		nTotNotMngCnt;
		long	lnTotNotMngAmg;
		int		nTotRejCnt;
		long	lnTotRejAmt;

		List<LinkedHashMap<String, Object>> list;
		Object[] rowValues;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		szOrgCd = pOrgCd.substring(1);

		hPreDate = fileSendMapper.pickupGetKiupTranData(pTransDate);

		hPreActDate = hPreDate;

		if(hPreDate == null) {
			throw new RuntimeException(String.format(">>> [GetCommonTranData-%s] 전일 없음.\n", pOrgCd));
		}

		szDate = hPreDate;

		ncount = 0;

		/* 전체 Header */
		// fprintf( fileWriter, "ATM001110000000%2s030000000%.*s%*s\n", szOrgCd, 6, pTransDate.substring(2), 138, " " );
		fileWriter.write(String.format("ATM001110000000%2s030000000%6s%138s", szOrgCd, pTransDate.substring(2), " " ));
		// fileWriter.newLine();

		/* 영업내 Data부 Header */
		// fprintf( fileWriter, "ATM00122%07ld%2s03 012200110096%4s%6s%6s%6s%*s\n", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), 116, " " );
		fileWriter.write(String.format("ATM00122%07d%2s03 01220011009600%2s%6s%6s%6s%116s", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));
		// fileWriter.newLine();

		/* Header 와 Trailer도 count에 포함 */
		ncount++;

		/* 시간내 데이터부 */
		/* 하나은행 공동망 일 경우  net_org_cd <> '81' 추가 */
		nDataCnt = 0;
		nTotAcptCnt     = 0;
		lnTotAcptAmt    = 0;
		lnTotCustAmt    = 0;
		lnTotBankAmt    = 0;
		nTotCancelCnt   = 0;
		lnTotCancelAmt  = 0;
		nTotNotMngCnt   = 0;
		lnTotNotMngAmg  = 0;
		nTotRejCnt      = 0;
		lnTotRejAmt     = 0;

		list = fileSendMapper.selectGetKiupTranData(szDate, szOrgCd);
		
		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hWorkType = rowValues[0] != null ? rowValues[0].toString() : "";
			hDataType = rowValues[1] != null ? rowValues[1].toString() : "";
			hDealDate = rowValues[2] != null ? rowValues[2].toString() : "";
			hAtmDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
			hInsetOrgCd = rowValues[4] != null ? rowValues[4].toString() : "";
			hAccountNo = rowValues[5] != null ? rowValues[5].toString() : "";
			hDealAmt = rowValues[6] != null ? rowValues[6].toString() : "";
			hCustFee = rowValues[7] != null ? rowValues[7].toString() : "";
			hBankFee = rowValues[8] != null ? rowValues[8].toString() : "";
			hOrgRespCd = rowValues[9] != null ? rowValues[9].toString() : "";
			hDealNo = rowValues[10] != null ? rowValues[10].toString() : "";
			hDealStatus = rowValues[11] != null ? rowValues[11].toString() : "";

			// fprintf( fileWriter, "ATM00122%07ld%2s03 %s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%*s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, 1 ," ");
			fileWriter.write(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo.trim() , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus ," "));
			// fileWriter.newLine();

			/* 승인건수, 금액 */
			if("0".equals(hDealStatus)) {
				nTotAcptCnt++;
				lnTotAcptAmt    += Long.parseLong(hDealAmt);
				lnTotCustAmt    += Long.parseLong(hCustFee);
				lnTotBankAmt    += Long.parseLong(hBankFee);
			} else if("1".equals(hDealStatus)) { /* 취소 */
				nTotCancelCnt++;
				lnTotCancelAmt  += Long.parseLong(hDealAmt);
			} else if("2".equals(hDealStatus)) { /* 미완료 */
				nTotNotMngCnt ++;
				lnTotNotMngAmg  += Long.parseLong(hDealAmt);
			} else if("3".equals(hDealStatus)) { /* 거절 */
				nTotRejCnt ++;
				lnTotRejAmt    += Long.parseLong(hDealAmt);
			}

			nDataCnt++;
			ncount++;
	    }

		/* 영업내 Data부 Trailer */
		// fprintf( fileWriter, "ATM00122%07ld%2s03 01220033  %06d%014ld%010ld%010ld%06ld%014ld%06ld%014ld%06ld%014ld%*s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, 40, " " );
		fileWriter.write(String.format("ATM00122%07d%2s03 01220033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));
		// fileWriter.newLine();
		ncount++;

		/* 영업외 Data부 Header */
		// fprintf( fileWriter, "ATM00122%07ld%2s03 012300110096%4s%6s%6s%6s%*s\n", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), 116, " " );
		fileWriter.write(String.format("ATM00122%07d%2s03 01230011009600%2s%6s%6s%6s%116s", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));
		// fileWriter.newLine();
		ncount++;

		nDataCnt = 0;

		nTotAcptCnt     = 0;
		lnTotAcptAmt    = 0;
		lnTotCustAmt    = 0;
		lnTotBankAmt    = 0;
		nTotCancelCnt   = 0;
		lnTotCancelAmt  = 0;
		nTotNotMngCnt   = 0;
		lnTotNotMngAmg  = 0;
		nTotRejCnt      = 0;
		lnTotRejAmt     = 0;

		list = fileSendMapper.selectGetKiupTranData2(szDate, szOrgCd);

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hWorkType = rowValues[0] != null ? rowValues[0].toString() : "";
			hDataType = rowValues[1] != null ? rowValues[1].toString() : "";
			hDealDate = rowValues[2] != null ? rowValues[2].toString() : "";
			hAtmDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
			hInsetOrgCd = rowValues[4] != null ? rowValues[4].toString() : "";
			hAccountNo = rowValues[5] != null ? rowValues[5].toString() : "";
			hDealAmt = rowValues[6] != null ? rowValues[6].toString() : "";
			hCustFee = rowValues[7] != null ? rowValues[7].toString() : "";
			hBankFee = rowValues[8] != null ? rowValues[8].toString() : "";
			hOrgRespCd = rowValues[9] != null ? rowValues[9].toString() : "";
			hDealNo = rowValues[10] != null ? rowValues[10].toString() : "";
			hDealStatus = rowValues[11] != null ? rowValues[11].toString() : "";

			// fprintf( fileWriter, "ATM00122%07ld%2s03 %s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%*s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, 1 ," ");
			fileWriter.write(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo.trim() , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus ," "));
			// fileWriter.newLine();

			/* 승인건수, 금액 */
			if("0".equals(hDealStatus)) {
				nTotAcptCnt++;
				lnTotAcptAmt    += Long.parseLong(hDealAmt);
				lnTotCustAmt    += Long.parseLong(hCustFee);
				lnTotBankAmt    += Long.parseLong(hBankFee);
			} else if("1".equals(hDealStatus)) { /* 취소 */
				nTotCancelCnt++;
				lnTotCancelAmt  += Long.parseLong(hDealAmt);
			} else if("2".equals(hDealStatus)) { /* 미완료 */
				nTotNotMngCnt ++;
				lnTotNotMngAmg  += Long.parseLong(hDealAmt);
			} else if("3".equals(hDealStatus)) { /* 거절 */
				nTotRejCnt ++;
				lnTotRejAmt    += Long.parseLong(hDealAmt);
			}

			nDataCnt++;
			ncount++;
	    }

		/* 영업외 Data부 Trailer */
		// fprintf( fileWriter, "ATM00122%07ld%2s03 01230033  %06d%014ld%010ld%010ld%06ld%014ld%06ld%014ld%06ld%014ld%*s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, 40, " " );
		fileWriter.write(String.format("ATM00122%07d%2s03 01230033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));
		// fileWriter.newLine();

		ncount++;

		// fprintf( fileWriter, "ATM001339999999%2s03%07ld%*s\n", szOrgCd, ncount, 144, " " );
		fileWriter.write(String.format("ATM001339999999%2s03%07d%144s", szOrgCd, ncount, " " ));
		// fileWriter.newLine();

		fileWriter.close();

		// fseek (fileWriter, 6+2+7+2+2, 0 );
		// fprintf( fileWriter, "%7s", szCnt );
		RandomAccessFile raf = new RandomAccessFile(pFileName, "rw");
		raf.seek(6+2+7+2+2);
		raf.writeBytes(String.format("%07d", ncount ));
		raf.close();

		return 0;
	}
	
	private int GetKBSTTranData(String pTransDate, String pOrgCd, String pFileName) throws IOException {
		String hWorkType;
		String hDataType;
		String hDealDate;
		String hAtmDealNo;
		String hInsetOrgCd;
		String hAccountNo;
		String hDealAmt;
		String hCustFee;
		String hBankFee;
		String hOrgRespCd;
		String hDealNo;
		String hDealStatus;
		String hPreActDate;
		String hPreDate;
		String hSpace;

		String szDate;
		String szOrgCd;
		int		ncount;
		int		nDataCnt;

		int		nTotAcptCnt;
		long	lnTotAcptAmt;
		long	lnTotCustAmt;
		long	lnTotBankAmt;
		int		nTotCancelCnt;
		long	lnTotCancelAmt;
		int		nTotNotMngCnt;
		long	lnTotNotMngAmg;
		int		nTotRejCnt;
		long	lnTotRejAmt;

		List<LinkedHashMap<String, Object>> list;
		Object[] rowValues;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		szOrgCd = pOrgCd.substring(1);

		hPreDate = fileSendMapper.pickupGetKBSTTranData(pTransDate);

		hPreActDate = hPreDate;

		if(hPreDate == null) {
			throw new RuntimeException(String.format(">>> [GetKBSTTranData-%s] 전일 없음.\n", pOrgCd));
		}

		szDate = hPreDate;

		ncount = 0;

		/* 전체 Header */
		// fprintf( fileWriter, "ATM001110000000%2s030000000%.*s%*s\n", szOrgCd, 6, pTransDate.substring(2), 138, " " );
		fileWriter.write(String.format("ATM001110000000%2s030000000%6s%138s", szOrgCd, pTransDate.substring(2), " " ));
		// fileWriter.newLine();

		/* 영업내 Data부 Header */
		// fprintf( fileWriter, "ATM00122%07ld%2s03 012200110096%4s%6s%6s%6s%*s\n",
		fileWriter.write(String.format("ATM00122%07d%2s03 01220011009600%2s%6s%6s%6s%116s", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));
		// fileWriter.newLine();

		/* Header 와 Trailer도 count에 포함 */
		ncount++;

		nDataCnt = 0;
		nTotAcptCnt     = 0;
		lnTotAcptAmt    = 0;
		lnTotCustAmt    = 0;
		lnTotBankAmt    = 0;
		nTotCancelCnt   = 0;
		lnTotCancelAmt  = 0;
		nTotNotMngCnt   = 0;
		lnTotNotMngAmg  = 0;
		nTotRejCnt      = 0;
		lnTotRejAmt     = 0;

		list = fileSendMapper.selectGetKBSTTranData(szDate, szOrgCd);
				
		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hWorkType = rowValues[0] != null ? rowValues[0].toString() : "";
			hDataType = rowValues[1] != null ? rowValues[1].toString() : "";
			hDealDate = rowValues[2] != null ? rowValues[2].toString() : "";
			hAtmDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
			hInsetOrgCd = rowValues[4] != null ? rowValues[4].toString() : "";
			hAccountNo = rowValues[5] != null ? rowValues[5].toString() : "";
			hDealAmt = rowValues[6] != null ? rowValues[6].toString() : "";
			hCustFee = rowValues[7] != null ? rowValues[7].toString() : "";
			hBankFee = rowValues[8] != null ? rowValues[8].toString() : "";
			hOrgRespCd = rowValues[9] != null ? rowValues[9].toString() : "";
			hDealNo = rowValues[10] != null ? rowValues[10].toString() : "";
			hDealStatus = rowValues[11] != null ? rowValues[11].toString() : "";
			hSpace = rowValues[12] != null ? rowValues[12].toString() : "";

			// fprintf( fileWriter, "ATM00122%07ld%2s03 %s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, hSpace);
			fileWriter.write(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%-22s%4s%4s%s%06d%06d%30s%s%s%s%s", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo.trim() , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus, hSpace));
			// fileWriter.newLine();

			/* 승인건수, 금액 */
			if("0".equals(hDealStatus)) {
				nTotAcptCnt++;
				lnTotAcptAmt    += Long.parseLong(hDealAmt);
				lnTotCustAmt    += Long.parseLong(hCustFee);
				lnTotBankAmt    += Long.parseLong(hBankFee);
			} else if("1".equals(hDealStatus)) { /* 취소 */
				nTotCancelCnt++;
				lnTotCancelAmt  += Long.parseLong(hDealAmt);
			} else if("2".equals(hDealStatus)) { /* 미완료 */
				nTotNotMngCnt ++;
				lnTotNotMngAmg  += Long.parseLong(hDealAmt);
			} else if("3".equals(hDealStatus)) { /* 거절 */
				nTotRejCnt ++;
				lnTotRejAmt    += Long.parseLong(hDealAmt);
			}

			nDataCnt++;
			ncount++;
	    }

		/* 영업내 Data부 Trailer */
		// fprintf( fileWriter, "ATM00122%07ld%2s03 01220033  %06d%014ld%010ld%010ld%06ld%014ld%06ld%014ld%06ld%014ld%*s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, 40, " " );
		fileWriter.write(String.format("ATM00122%07d%2s03 01220033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));
		// fileWriter.newLine();
		ncount++;

		/* 영업외 Data부 Header */
		// fprintf( fileWriter, "ATM00122%07ld%2s03 012300110096%4s%6s%6s%6s%*s\n",
		fileWriter.write(String.format("ATM00122%07d%2s03 01230011009600%2s%6s%6s%6s%116s", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));
		// fileWriter.newLine();
		ncount++;

		nDataCnt = 0;

		nTotAcptCnt     = 0;
		lnTotAcptAmt    = 0;
		lnTotCustAmt    = 0;
		lnTotBankAmt    = 0;
		nTotCancelCnt   = 0;
		lnTotCancelAmt  = 0;
		nTotNotMngCnt   = 0;
		lnTotNotMngAmg  = 0;
		nTotRejCnt      = 0;
		lnTotRejAmt     = 0;

		list = fileSendMapper.selectGetKBSTTranData2(szDate, szOrgCd);
		
		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hWorkType = rowValues[0] != null ? rowValues[0].toString() : "";
			hDataType = rowValues[1] != null ? rowValues[1].toString() : "";
			hDealDate = rowValues[2] != null ? rowValues[2].toString() : "";
			hAtmDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
			hInsetOrgCd = rowValues[4] != null ? rowValues[4].toString() : "";
			hAccountNo = rowValues[5] != null ? rowValues[5].toString() : "";
			hDealAmt = rowValues[6] != null ? rowValues[6].toString() : "";
			hCustFee = rowValues[7] != null ? rowValues[7].toString() : "";
			hBankFee = rowValues[8] != null ? rowValues[8].toString() : "";
			hOrgRespCd = rowValues[9] != null ? rowValues[9].toString() : "";
			hDealNo = rowValues[10] != null ? rowValues[10].toString() : "";
			hDealStatus = rowValues[11] != null ? rowValues[11].toString() : "";
			hSpace = rowValues[12] != null ? rowValues[12].toString() : "";

			// fprintf( fileWriter, "ATM00122%07ld%2s03 %s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, hSpace );
			fileWriter.write(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%-22s%4s%4s%s%06d%06d%30s%s%s%s%s", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo.trim() , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus, hSpace ));
			// fileWriter.newLine();

			/* 승인건수, 금액 */
			if("0".equals(hDealStatus)) {
				nTotAcptCnt++;
				lnTotAcptAmt    += Long.parseLong(hDealAmt);
				lnTotCustAmt    += Long.parseLong(hCustFee);
				lnTotBankAmt    += Long.parseLong(hBankFee);
			} else if("1".equals(hDealStatus)) { /* 취소 */
				nTotCancelCnt++;
				lnTotCancelAmt  += Long.parseLong(hDealAmt);
			} else if("2".equals(hDealStatus)) { /* 미완료 */
				nTotNotMngCnt ++;
				lnTotNotMngAmg  += Long.parseLong(hDealAmt);
			} else if("3".equals(hDealStatus)) { /* 거절 */
				nTotRejCnt ++;
				lnTotRejAmt    += Long.parseLong(hDealAmt);
			}

			nDataCnt++;
			ncount++;
	    }

		/* 영업외 Data부 Trailer */
		// fprintf( fileWriter, "ATM00122%07ld%2s03 01230033  %06d%014ld%010ld%010ld%06ld%014ld%06ld%014ld%06ld%014ld%*s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, 40, " " );
		fileWriter.write(String.format("ATM00122%07d%2s03 01230033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));
		// fileWriter.newLine();

		ncount++;

		// fprintf( fileWriter, "ATM001339999999%2s03%07ld%*s\n", szOrgCd, ncount, 144, " " );
		fileWriter.write(String.format("ATM001339999999%2s03%07d%144s", szOrgCd, ncount, " " ));
		// fileWriter.newLine();

		fileWriter.close();

		// fseek (fileWriter, 6+2+7+2+2, 0 );
		// fprintf( fileWriter, "%7s", szCnt );
		RandomAccessFile raf = new RandomAccessFile(pFileName, "rw");
		raf.seek(6+2+7+2+2);
		raf.writeBytes(String.format("%07d", ncount ));
		raf.close();

		return 0;
	}
	
	/*
	private int GetCommonImsiData(String pTransDate, String pOrgCd, String pFileName) {
		String hWorkType;
		String hDataType;
		String hDealDate;
		String hAtmDealNo;
		String hInsetOrgCd;
		String hAccountNo;
		String hDealAmt;
		String hCustFee;
		String hBankFee;
		String hOrgRespCd;
		String hDealNo;
		String hDealStatus;
		String hPreActDate;
		String hPreDate;

		String szDate;
		String szOrgCd;
		String szCnt;
		int		ncount;
		int		nDataCnt;

		int		nTotAcptCnt;
		long	lnTotAcptAmt;
		long	lnTotCustAmt;
		long	lnTotBankAmt;
		int		nTotCancelCnt;
		long	lnTotCancelAmt;
		int		nTotNotMngCnt;
		long	lnTotNotMngAmg;
		int		nTotRejCnt;
		long	lnTotRejAmt;

		List<LinkedHashMap<String, Object>> list;
		Object[] rowValues;
		
		File fileWriter;

		szOrgCd = pOrgCd.substring(1);

		// 임시.. 전송일 10일 거래일 8,9일 데이터 요청 
		hPreActDate = "20061008";

		// 휴일에는 전송하지 않는다.
		if( pTransDate.equals(hPreActDate)) {
			System.out.print(String.format(">>> [GetCommonTranData-%s] 휴일 전송 안함 [%s]\n", szOrgCd,  pTransDate));
			return -2;
		}

		hPreDate = fileSendMapper.pickupGetCommonImsiData(pTransDate);

		if(hPreDate == null) {
			System.out.print(String.format(">>> [GetCommonTranData-%s] 전일 없음.\n", pOrgCd));
			return -1;
		}

	 	if(( fileWriter = pFileName) == null ) {
			System.out.print(String.format(">>> [GetCommonTranData-%s] FileOpenError [%s]\n", pOrgCd, pFileName));
			return -1;
		}

		szDate = hPreDate;

		ncount = 0;

		// 전체 Header
		// fprintf( fileWriter, "ATM001110000000%2s030000000%.*s%*s\n", szOrgCd, 6, pTransDate.substring(2), 138, " " );
		fileWriter.write(String.format("ATM001110000000%2s030000000%6s%138s\n", szOrgCd, pTransDate.substring(2), " " ));

		while(true) {
			// 영업내 Data부 Header
			// fprintf( fileWriter, "ATM00122%07ld%2s03 012200110096%4s%6s%6s%6s%*s\n", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), 116, " " );
			fileWriter.write(String.format("ATM00122%07d%2s03 012200110096%4s%6s%6s%6s%116s\n", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));

			// Header 와 Trailer도 count에 포함 
			ncount++;

			nDataCnt = 0;
			nTotAcptCnt     = 0;
			lnTotAcptAmt    = 0;
			lnTotCustAmt    = 0;
			lnTotBankAmt    = 0;
			nTotCancelCnt   = 0;
			lnTotCancelAmt  = 0;
			nTotNotMngCnt   = 0;
			lnTotNotMngAmg  = 0;
			nTotRejCnt      = 0;
			lnTotRejAmt     = 0;

			list = fileSendMapper.selectGetCommonImsiData(szDate, szOrgCd);
			
			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new Object[obj.size()]);
				
				hWorkType = rowValues[0] != null ? rowValues[0].toString() : "";
				hDataType = rowValues[1] != null ? rowValues[1].toString() : "";
				hDealDate = rowValues[2] != null ? rowValues[2].toString() : "";
				hAtmDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
				hInsetOrgCd = rowValues[4] != null ? rowValues[4].toString() : "";
				hAccountNo = rowValues[5] != null ? rowValues[5].toString() : "";
				hDealAmt = rowValues[6] != null ? rowValues[6].toString() : "";
				hCustFee = rowValues[7] != null ? rowValues[7].toString() : "";
				hBankFee = rowValues[8] != null ? rowValues[8].toString() : "";
				hOrgRespCd = rowValues[9] != null ? rowValues[9].toString() : "";
				hDealNo = rowValues[10] != null ? rowValues[10].toString() : "";
				hDealStatus = rowValues[11] != null ? rowValues[11].toString() : "";

				// fprintf( fileWriter, "ATM00122%07ld%2s03 %s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%*s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, 1 ," ");
				fileWriter.write(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus ," "));

				// 승인건수, 금액 
				if("0".equals(hDealStatus)) {
					nTotAcptCnt++;
					lnTotAcptAmt    += Long.parseLong(hDealAmt);
					lnTotCustAmt    += Long.parseLong(hCustFee);
					lnTotBankAmt    += Long.parseLong(hBankFee);
				} else if("1".equals(hDealStatus)) { // 취소 
					nTotCancelCnt++;
					lnTotCancelAmt  += Long.parseLong(hDealAmt);
				} else if("2".equals(hDealStatus)) { // 미완료
					nTotNotMngCnt ++;
					lnTotNotMngAmg  += Long.parseLong(hDealAmt);
				} else if("3".equals(hDealStatus)) { // 거절
					nTotRejCnt ++;
					lnTotRejAmt    += Long.parseLong(hDealAmt);
				}

				nDataCnt++;
				ncount++;
		    }

			// 영업내 Data부 Trailer
			// fprintf( fileWriter, "ATM00122%07ld%2s03 01220033  %06d%014ld%010ld%010ld%06ld%014ld%06ld%014ld%06ld%014ld%*s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, 40, " " );
			fileWriter.write(String.format("ATM00122%07d%2s03 01220033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));
			ncount++;

			// 영업외 Data부 Header
			// fprintf( fileWriter, "ATM00122%07ld%2s03 012300110096%4s%6s%6s%6s%*s\n", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), 116, " " );
			fileWriter.write(String.format("ATM00122%07d%2s03 012300110096%4s%6s%6s%6s%116s\n", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));
			ncount++;

			nDataCnt = 0;

			nTotAcptCnt     = 0;
			lnTotAcptAmt    = 0;
			lnTotCustAmt    = 0;
			lnTotBankAmt    = 0;
			nTotCancelCnt   = 0;
			lnTotCancelAmt  = 0;
			nTotNotMngCnt   = 0;
			lnTotNotMngAmg  = 0;
			nTotRejCnt      = 0;
			lnTotRejAmt     = 0;

			list = fileSendMapper.selectGetCommonImsiData2(szDate, szOrgCd);
			
			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new Object[obj.size()]);
				
				hWorkType = rowValues[0] != null ? rowValues[0].toString() : "";
				hDataType = rowValues[1] != null ? rowValues[1].toString() : "";
				hDealDate = rowValues[2] != null ? rowValues[2].toString() : "";
				hAtmDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
				hInsetOrgCd = rowValues[4] != null ? rowValues[4].toString() : "";
				hAccountNo = rowValues[5] != null ? rowValues[5].toString() : "";
				hDealAmt = rowValues[6] != null ? rowValues[6].toString() : "";
				hCustFee = rowValues[7] != null ? rowValues[7].toString() : "";
				hBankFee = rowValues[8] != null ? rowValues[8].toString() : "";
				hOrgRespCd = rowValues[9] != null ? rowValues[9].toString() : "";
				hDealNo = rowValues[10] != null ? rowValues[10].toString() : "";
				hDealStatus = rowValues[11] != null ? rowValues[11].toString() : "";

				// fprintf( fileWriter, "ATM00122%07ld%2s03 %s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%*s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, 1 ," ");
				fileWriter.write(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus ," "));
				// 승인건수, 금액
				if("0".equals(hDealStatus)) {
					nTotAcptCnt++;
					lnTotAcptAmt    += Long.parseLong(hDealAmt);
					lnTotCustAmt    += Long.parseLong(hCustFee);
					lnTotBankAmt    += Long.parseLong(hBankFee);
				} else if("1".equals(hDealStatus)) { // 취소 
					nTotCancelCnt++;
					lnTotCancelAmt  += Long.parseLong(hDealAmt);
				} else if("2".equals(hDealStatus)) { // 미완료 
					nTotNotMngCnt ++;
					lnTotNotMngAmg  += Long.parseLong(hDealAmt);
				} else if("3".equals(hDealStatus)) { // 거절 
					nTotRejCnt ++;
					lnTotRejAmt    += Long.parseLong(hDealAmt);
				}

				nDataCnt++;
				ncount++;
		    }

			// 영업외 Data부 Trailer
			// fprintf( fileWriter, "ATM00122%07ld%2s03 01230033  %06d%014ld%010ld%010ld%06ld%014ld%06ld%014ld%06ld%014ld%*s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, 40, " " );
			fileWriter.write(String.format("ATM00122%07d%2s03 01230033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));

			ncount++;

			hPreDate = fileSendMapper.pickupGetCommonImsiData2(szDate);

			if( Long.parseLong(hPreDate) < Long.parseLong(hPreActDate) ) {
				break;
			}

			szDate = hPreDate;
		}

		// fprintf( fileWriter, "ATM001339999999%2s03%07ld%*s\n", szOrgCd, ncount, 144, " " );
		fileWriter.write(String.format("ATM001339999999%2s03%07d%144s\n", szOrgCd, ncount, " " ));

		szCnt = String.format("%07d", ncount );
		fseek (fileWriter, 6+2+7+2+2, 0 );
		// fprintf( fileWriter, "%7s", szCnt );
		fileWriter.write(String.format("%7s", szCnt ));

		fileWriter.close();

		return 0;
	}
	*/
	
	private int GetKETranData(String pTransDate, String pOrgCd, String pFileName) throws IOException {
		String hWorkType;
		String hDataType;
		String hDealDate;
		String hAtmDealNo;
		String hInsetOrgCd;
		String hAccountNo;
		String hDealAmt;
		String hCustFee;
		String hBankFee;
		String hOrgRespCd;
		String hDealNo;
		String hDealStatus;
		String hPreActDate;
		String hPreDate;

		String szDate;
		String szOrgCd;
		int		nDataCnt;

		int		nTotAcptCnt;
		long	lnTotAcptAmt;
		long	lnTotCustAmt;
		long	lnTotBankAmt;
		int		nTotCancelCnt;
		long	lnTotCancelAmt;
		int		nTotNotMngCnt;
		long	lnTotNotMngAmg;
		int		nTotRejCnt;
		long	lnTotRejAmt;

		List<LinkedHashMap<String, Object>> list;
		Object[] rowValues;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		szOrgCd = pOrgCd.substring(1);

		/* 전송일이 휴일이면 전송일을 전송일이 영업일이면 전 영업일을 return */
		hPreActDate = fileSendMapper.pickupGetKETranData(pTransDate);

		if(hPreActDate == null) {
			throw new RuntimeException(String.format(">>> [GetCommonTranData-%s] 전영업일 없음.\n", pOrgCd));
		}

		/* 휴일에는 전송하지 않는다. */ 
		if( pTransDate.equals(hPreActDate)) {
			throw new RuntimeException(String.format(">>> [GetCommonTranData-%s] 휴일 전송 안함 [%s]\n", szOrgCd,  pTransDate));
		}

		hPreDate = fileSendMapper.pickupGetKETranData2(pTransDate);

		if(hPreDate == null) {
			throw new RuntimeException(String.format(">>> [GetCommonTranData-%s] 전일 없음.\n", pOrgCd));
		}

		szDate = hPreDate;

		while(true) {
			/* 영업내 Data부 Header */
			// fprintf( fileWriter, "012200110096%4s%6s%6s%6s%*s\n", szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), 116, " " );
			fileWriter.write(String.format("01220011009600%2s%6s%6s%6s%116s", szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));
			// fileWriter.newLine();

			nDataCnt = 0;
			nTotAcptCnt     = 0;
			lnTotAcptAmt    = 0;
			lnTotCustAmt    = 0;
			lnTotBankAmt    = 0;
			nTotCancelCnt   = 0;
			lnTotCancelAmt  = 0;
			nTotNotMngCnt   = 0;
			lnTotNotMngAmg  = 0;
			nTotRejCnt      = 0;
			lnTotRejAmt     = 0;

			list = fileSendMapper.selectGetKETranData(szDate, szOrgCd);
			
			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new Object[obj.size()]);
				
				hWorkType = rowValues[0] != null ? rowValues[0].toString() : "";
				hDataType = rowValues[1] != null ? rowValues[1].toString() : "";
				hDealDate = rowValues[2] != null ? rowValues[2].toString() : "";
				hAtmDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
				hInsetOrgCd = rowValues[4] != null ? rowValues[4].toString() : "";
				hAccountNo = rowValues[5] != null ? rowValues[5].toString() : "";
				hDealAmt = rowValues[6] != null ? rowValues[6].toString() : "";
				hCustFee = rowValues[7] != null ? rowValues[7].toString() : "";
				hBankFee = rowValues[8] != null ? rowValues[8].toString() : "";
				hOrgRespCd = rowValues[9] != null ? rowValues[9].toString() : "";
				hDealNo = rowValues[10] != null ? rowValues[10].toString() : "";
				hDealStatus = rowValues[11] != null ? rowValues[11].toString() : "";

				// fprintf( fileWriter, "%s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%*s\n", hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, 1 ," ");
				fileWriter.write(String.format("%s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s", hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo.trim() , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus ," "));
				// fileWriter.newLine();

				/* 승인건수, 금액 */
				if("0".equals(hDealStatus)) {
					nTotAcptCnt++;
					lnTotAcptAmt    += Long.parseLong(hDealAmt);
					lnTotCustAmt    += Long.parseLong(hCustFee);
					lnTotBankAmt    += Long.parseLong(hBankFee);
				} else if("1".equals(hDealStatus)) { /* 취소 */
					nTotCancelCnt++;
					lnTotCancelAmt  += Long.parseLong(hDealAmt);
				} else if("2".equals(hDealStatus)) { /* 미완료 */
					nTotNotMngCnt ++;
					lnTotNotMngAmg  += Long.parseLong(hDealAmt);
				} else if("3".equals(hDealStatus)) { /* 거절 */
					nTotRejCnt ++;
					lnTotRejAmt    += Long.parseLong(hDealAmt);
				}

				nDataCnt++;
		    }

			/* 영업내 Data부 Trailer */
			// fprintf( fileWriter, "01220033  %06d%014ld%010ld%010ld%06ld%014ld%06ld%014ld%06ld%014ld%*s\n", nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, 40, " " );
			fileWriter.write(String.format("01220033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s", nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));
			// fileWriter.newLine();

			/* 영업외 Data부 Header */
			// fprintf( fileWriter, "012300110096%4s%6s%6s%6s%*s\n", szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), 116, " " );
			fileWriter.write(String.format("01230011009600%2s%6s%6s%6s%116s", szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));
			// fileWriter.newLine();

			nDataCnt = 0;

			nTotAcptCnt     = 0;
			lnTotAcptAmt    = 0;
			lnTotCustAmt    = 0;
			lnTotBankAmt    = 0;
			nTotCancelCnt   = 0;
			lnTotCancelAmt  = 0;
			nTotNotMngCnt   = 0;
			lnTotNotMngAmg  = 0;
			nTotRejCnt      = 0;
			lnTotRejAmt     = 0;

			list = fileSendMapper.selectGetKETranData2(szDate, szOrgCd);
			
			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new Object[obj.size()]);
				
				hWorkType = rowValues[0] != null ? rowValues[0].toString() : "";
				hDataType = rowValues[1] != null ? rowValues[1].toString() : "";
				hDealDate = rowValues[2] != null ? rowValues[2].toString() : "";
				hAtmDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
				hInsetOrgCd = rowValues[4] != null ? rowValues[4].toString() : "";
				hAccountNo = rowValues[5] != null ? rowValues[5].toString() : "";
				hDealAmt = rowValues[6] != null ? rowValues[6].toString() : "";
				hCustFee = rowValues[7] != null ? rowValues[7].toString() : "";
				hBankFee = rowValues[8] != null ? rowValues[8].toString() : "";
				hOrgRespCd = rowValues[9] != null ? rowValues[9].toString() : "";
				hDealNo = rowValues[10] != null ? rowValues[10].toString() : "";
				hDealStatus = rowValues[11] != null ? rowValues[11].toString() : "";

				// fprintf( fileWriter, "%s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%*s\n", hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, 1 ," ");
				fileWriter.write(String.format("%s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s", hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo.trim() , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus ," "));
				// fileWriter.newLine();
				/* 승인건수, 금액 */
				if("0".equals(hDealStatus)) {
					nTotAcptCnt++;
					lnTotAcptAmt    += Long.parseLong(hDealAmt);
					lnTotCustAmt    += Long.parseLong(hCustFee);
					lnTotBankAmt    += Long.parseLong(hBankFee);
				} else if("1".equals(hDealStatus)) { /* 취소 */
					nTotCancelCnt++;
					lnTotCancelAmt  += Long.parseLong(hDealAmt);
				} else if("2".equals(hDealStatus)) { /* 미완료 */
					nTotNotMngCnt ++;
					lnTotNotMngAmg  += Long.parseLong(hDealAmt);
				} else if("3".equals(hDealStatus)) { /* 거절 */
					nTotRejCnt ++;
					lnTotRejAmt    += Long.parseLong(hDealAmt);
				}

				nDataCnt++;
		    }

			/* 영업외 Data부 Trailer */
			// fprintf( fileWriter, "01230033  %06d%014ld%010ld%010ld%06ld%014ld%06ld%014ld%06ld%014ld%*s\n", nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, 40, " " );
			fileWriter.write(String.format("01230033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s", nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));
			// fileWriter.newLine();

			hPreDate = fileSendMapper.pickupGetKETranData3(szDate);

			if( Long.parseLong(hPreDate) < Long.parseLong(hPreActDate) ) {
				break;
			}

			szDate = hPreDate;
		}

		fileWriter.close();

		return 0;
	}

	/* 스마트카드 * /
	private int GetSmartTranData(String pTransDate, String pFileName) {
		String hDataType;
		String hRecordType;
		String hOrgCd;
		String hSmDealNo;
		String hAccountNo;
		String hStatusCd;
		String hDealTime1;
		String hDealTime2;
		String hDealStatus;
		String hDealAmt;
		String hCustFee;
		String hMacNo;
		String hPreActDate;
		String hPreDate;
		String hDealNo;
		String szAftAmt;
		String szDate;
		String szCnt;
		String szPath;
		String szFileName;

		int		ncount;
		int		nTotalCnt;
		long	lnTotalAmt;
		long	lnTotCustFee;
		int		nTotalCancelCnt;
		long	lnTotalCancelAmt;

		HashMap<String, Object> suFileRead;

		File fileWriter;

		szPath = (String)config.get("smart.local.path");

		szFileName = String.format("%s/S%sDKS", szPath, pTransDate);

		hPreDate = fileSendMapper.pickupGetSmartTranData(pTransDate);

		if(hPreDate == null) {
			System.out.print(String.format(">>> [GetSmartTranData] 전일 없음. \n"));
			return -1;
		}

	 	if(( fileWriter = pFileName) == null ) {
			System.out.print(String.format(">>> [GetSmartTranData] FileOpenError [%s]\n", pFileName));
			return -1;
		}

		szDate = hPreDate;

		ncount			= 0;
		nTotalCnt       = 0;
		lnTotalAmt      = 0;
		lnTotCustFee       = 0;
		nTotalCancelCnt = 0;
		lnTotalCancelAmt = 0;

		// fprintf( fileWriter, "MDH01%*s%0*d%8s%8s%06ld%*s\n", 7+7+13+10+7+13, " ", 7+13+10+13+13+7+13, 0, szDate, pTransDate, GetCurTime(), 40, " " );
		fileWriter.write(String.format("MDH01%57s%076d%8s%8s%06d%40s\n", " ", 0, szDate, pTransDate, GetCurTime(), " " ));

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetSmartTranData(szDate);
		Object[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hDataType = rowValues[0] != null ? rowValues[0].toString() : "";
			hRecordType = rowValues[1] != null ? rowValues[1].toString() : "";
			hOrgCd = rowValues[2] != null ? rowValues[2].toString() : "";
			hSmDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
			hAccountNo = rowValues[4] != null ? rowValues[4].toString() : "";
			hStatusCd = rowValues[5] != null ? rowValues[5].toString() : "";
			hDealTime1 = rowValues[6] != null ? rowValues[6].toString() : "";
			hDealTime2 = rowValues[7] != null ? rowValues[7].toString() : "";
			hDealStatus = rowValues[8] != null ? rowValues[8].toString() : "";
			hDealAmt = rowValues[9] != null ? rowValues[9].toString() : "";
			hCustFee = rowValues[10] != null ? rowValues[10].toString() : "";
			hMacNo = rowValues[11] != null ? rowValues[11].toString() : "";
			hDealNo = rowValues[12] != null ? rowValues[12].toString() : "";

			suFileRead = new HashMap<String, Object>();
			suFileRead.put("szDealDate", szDate);
			suFileRead.put("szDealNo", hDealNo);

			if( ReadSmartFileData(szFileName, suFileRead) < 0 ) {
				System.out.print(String.format(">>> [fnDBInsertUpTransDate] SmartFile Read Error [%s]\n", szFileName));
				return -1;
			}

			if( "5".equals(hDealStatus)) {
				szAftAmt = String.format("%010d", Long.parseLong(suFileRead.get("szPreAmt").toString()) + Long.parseLong(hDealAmt));
				lnTotalAmt = lnTotalAmt + Long.parseLong( hDealAmt );
				lnTotCustFee = lnTotCustFee + Long.parseLong(hCustFee);
				nTotalCnt++;
			} else {
				szAftAmt = suFileRead.get("szPreAmt").toString();
				lnTotalCancelAmt = lnTotalCancelAmt + Long.parseLong( hDealAmt );
				nTotalCancelCnt++;
			}

			// fprintf( fileWriter, "%.*s%.*s%07ld%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%0*d%0*d%*s%.*s%.*s%*s\n", 2 , hDataType, 1 , hRecordType , ncount+1 , 2 , hOrgCd , 16 , hSmDealNo, 16 , hAccountNo, 2 , suFileRead.get("szOwnerType").toString(), 4 , hStatusCd , 14 , hDealTime1, 14 , hDealTime2, 10 , suFileRead.get("szDealSeqNo").toString(), 1 , hDealStatus , 10 , suFileRead.get("szPreAmt").toString(), 10 , hDealAmt, 10 , szAftAmt , 10 , hCustFee, 10 , 0 , 10 , 0 , 1 , " " , 10 , hMacNo , 8 , suFileRead.get("szConfirmNm").toString(), 32 , " " );
			fileWriter.write(String.format("%2s%1s%07d%2s%16s%16s%2s%4s%14s%14s%10s%1s%10s%10s%10s%10s%010d%010d%1s%10s%8s%32s\n" , hDataType , hRecordType , ncount+1  , hOrgCd  , hSmDealNo , hAccountNo , suFileRead.get("szOwnerType").toString() , hStatusCd  , hDealTime1 , hDealTime2 , suFileRead.get("szDealSeqNo").toString() , hDealStatus  , suFileRead.get("szPreAmt").toString() , hDealAmt , szAftAmt  , hCustFee , 0  , 0  , " "  , hMacNo  , suFileRead.get("szConfirmNm").toString() , " " ));
			ncount++;
	    }

		// fprintf( fileWriter, "MDT%*s\n", 197, " ");
		fileWriter.write(String.format("MDT%197s\n", " "));

		szCnt = String.format("%07d%07d%013d%010d%07d%013d", ncount, nTotalCnt, lnTotalAmt, lnTotCustFee, nTotalCancelCnt, lnTotalCancelAmt);

		fseek (fileWriter, 2+1+2, 0 );
		// // fprintf( fileWriter, "%.*s", strlen(szCnt), szCnt );
		fileWriter.write(String.format("%" + szCnt.length() + "s", szCnt ));
		// fprintf( fileWriter, "%s", szCnt );
		fileWriter.write(String.format("%s", szCnt ));

		fileWriter.close();

		return 0;
	}
	*/

	/* 스마트카드 임시 (몇일단위 묶어 일괄처리)* /
	private int GetSmartImsiTranData(String pTransDate, String pFileName) {
		String hDataType;
		String hRecordType;
		String hOrgCd;
		String hSmDealNo;
		String hAccountNo;
		String hStatusCd;
		String hDealTime1;
		String hDealTime2;
		String hDealStatus;
		String hDealAmt;
		String hCustFee;
		String hMacNo;
		String hPreActDate;
		String hPreDate;
		String hDealNo;
		String szAftAmt;
		String szDate;
		String szFileDate;
		String szCnt;
		String szPath;
		String szFileName;

		int		ncount;
		int		nTotalCnt;
		long	lnTotalAmt;
		long	lnTotCustFee;
		int		nTotalCancelCnt;
		long	lnTotalCancelAmt;

		HashMap<String, Object> suFileRead;

		File fileWriter;

		szPath = (String)config.get("casher.local.path");

		// 임시.. 전송일 13일 거래일 11,12일 데이터 요청 
		hPreActDate = "20061111";

		hPreDate = fileSendMapper.pickupGetSmartImsiTranData(pTransDate);

		if(hPreDate == null) {
			System.out.print(String.format(">>> [GetSmartTranData] 전일 없음. \n"));
			return -1;
		}

	 	if(( fileWriter = pFileName) == null ) {
			System.out.print(String.format(">>> [GetSmartTranData] FileOpenError [%s]\n", pFileName));
			return -1;
		}

		szDate = hPreDate;

		szFileDate = pTransDate;

		ncount			= 0;
		nTotalCnt       = 0;
		lnTotalAmt      = 0;
		lnTotCustFee       = 0;
		nTotalCancelCnt = 0;
		lnTotalCancelAmt = 0;

		// fprintf( fileWriter, "MDH01%*s%0*d%8s%8s%06ld%*s\n", 7+7+13+10+7+13, " ", 7+13+10+13+13+7+13, 0, szDate, pTransDate, GetCurTime(), 40, " " );
		fileWriter.write(String.format("MDH01%57s%076d%8s%8s%06d%40s\n", " ", 0, szDate, pTransDate, GetCurTime(), " " ));

		while(true) {
			List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetSmartImsiTranData(szDate);
			Object[] rowValues;

			szFileName = String.format("%s/S%sDKS", szPath, szFileDate);

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new Object[obj.size()]);
				
				hDataType = rowValues[0] != null ? rowValues[0].toString() : "";
				hRecordType = rowValues[1] != null ? rowValues[1].toString() : "";
				hOrgCd = rowValues[2] != null ? rowValues[2].toString() : "";
				hSmDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
				hAccountNo = rowValues[4] != null ? rowValues[4].toString() : "";
				hStatusCd = rowValues[5] != null ? rowValues[5].toString() : "";
				hDealTime1 = rowValues[6] != null ? rowValues[6].toString() : "";
				hDealTime2 = rowValues[7] != null ? rowValues[7].toString() : "";
				hDealStatus = rowValues[8] != null ? rowValues[8].toString() : "";
				hDealAmt = rowValues[9] != null ? rowValues[9].toString() : "";
				hCustFee = rowValues[10] != null ? rowValues[10].toString() : "";
				hMacNo = rowValues[11] != null ? rowValues[11].toString() : "";
				hDealNo = rowValues[12] != null ? rowValues[12].toString() : "";

				suFileRead = new HashMap<String, Object>();
				suFileRead.put("szDealDate", szDate);
				suFileRead.put("szDealNo", hDealNo);

				if( ReadSmartFileData(szFileName, suFileRead) < 0 ) {
					System.out.print(String.format(">>> [fnDBInsertUpTransDate] SmartFile Read Error [%s]\n", szFileName));
					return -1;
				}

				if( "5".equals(hDealStatus)) {
					szAftAmt = String.format("%010d", Long.parseLong(suFileRead.get("szPreAmt").toString()) + Long.parseLong(hDealAmt) );
					lnTotalAmt = lnTotalAmt + Long.parseLong( hDealAmt );
					lnTotCustFee = lnTotCustFee + Long.parseLong(hCustFee);
					nTotalCnt++;
				} else {
					szAftAmt = suFileRead.get("szPreAmt").toString();
					lnTotalCancelAmt = lnTotalCancelAmt + Long.parseLong( hDealAmt );
					nTotalCancelCnt++;
				}

				// fprintf( fileWriter, "%.*s%.*s%07ld%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%0*d%0*d%*s%.*s%.*s%*s\n", 2 , hDataType, 1 , hRecordType , ncount+1 , 2 , hOrgCd , 16 , hSmDealNo, 16 , hAccountNo, 2 , suFileRead.get("szOwnerType").toString() , 4 , hStatusCd , 14 , hDealTime1, 14 , hDealTime2, 10 , suFileRead.get("szDealSeqNo").toString() , 1 , hDealStatus , 10 , suFileRead.get("szPreAmt").toString() , 10 , hDealAmt, 10 , szAftAmt , 10 , hCustFee, 10 , 0 , 10 , 0 , 1 , " " , 10 , hMacNo , 8 , suFileRead.get("szConfirmNm").toString() , 32 , " " );
				fileWriter.write(String.format("%2s%1s%07d%2s%16s%16s%2s%4s%14s%14s%10s%1s%10s%10s%10s%10s%010d%010d%1s%10s%8s%32s\n" , hDataType , hRecordType , ncount+1  , hOrgCd  , hSmDealNo , hAccountNo , suFileRead.get("szOwnerType").toString()  , hStatusCd  , hDealTime1 , hDealTime2 , suFileRead.get("szDealSeqNo").toString()  , hDealStatus  , suFileRead.get("szPreAmt").toString()  , hDealAmt , szAftAmt  , hCustFee , 0  , 0  , " "  , hMacNo  , suFileRead.get("szConfirmNm").toString()  , " " ));
				ncount++;
		    }

			hPreDate = fileSendMapper.pickupGetSmartImsiTranData2(szDate);

			if( Long.parseLong(hPreDate) < Long.parseLong(hPreActDate) ) {
				break;
			}

			szFileDate = szDate;

			szDate = hPreDate;
		}

		// fprintf( fileWriter, "MDT%*s\n", 197, " ");
		fileWriter.write(String.format("MDT%197s\n", " "));

		szCnt = String.format("%07d%07d%013d%010d%07d%013d", ncount, nTotalCnt, lnTotalAmt, lnTotCustFee, nTotalCancelCnt, lnTotalCancelAmt);

		fseek (fileWriter, 2+1+2, 0 );
		// fprintf( fileWriter, "%s", szCnt );
		fileWriter.write(String.format("%s", szCnt ));

		fileWriter.close();

		return 0;
	}
	*/

	/* 하이패스 */
	private int GetHITranData(String pTransDate, String pFileName) throws IOException {
		String hWorkType;
		String hDataType;
		String hJijumCd;
		String hMacNo;
		String hMacNm;
		String hDealNo;
		String hDealDate;
		String hAccountNo;
		String hDealAmt;
		String hDealStatus;
		String hPreDate;
		String hCustFee;
		String hAccountNo2;
		String hJoinsNo;

		int		ncount;
		long	lnTotalAmt;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		hPreDate = fileSendMapper.pickupGetHITranData(pTransDate);

		/* 하이패스의 경우 바로 전 거래( 이체요청=>기관코드 'NI' )의
		   카드번호, 승인번호(제휴기관거래번호 항목을 사용), 승인일자를 같이 보낸다. */
		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetHITranData(hPreDate);
		Object[] rowValues;

		ncount = 0;
		lnTotalAmt = 0;

		// fprintf( fileWriter, "V01HPS110000000V01%8s%8s%*s\n", pTransDate, hPreDate, 116, " " );
		fileWriter.write(String.format("V01HPS110000000V01%8s%8s%116s", pTransDate, hPreDate, " " ));
		// fileWriter.newLine();

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hWorkType = rowValues[0] != null ? rowValues[0].toString() : "";
			hDataType = rowValues[1] != null ? rowValues[1].toString() : "";
			hAccountNo = rowValues[2] != null ? rowValues[2].toString() : "";
			hDealDate = rowValues[3] != null ? rowValues[3].toString() : "";
			hJijumCd = rowValues[4] != null ? rowValues[4].toString() : "";
			hMacNo = rowValues[5] != null ? rowValues[5].toString() : "";
			hDealNo = rowValues[6] != null ? rowValues[6].toString() : "";
			hDealAmt = rowValues[7] != null ? rowValues[7].toString() : "";
			hMacNm = rowValues[8] != null ? rowValues[8].toString() : "";
			hDealStatus = rowValues[9] != null ? rowValues[9].toString() : "";
			hCustFee = rowValues[10] != null ? rowValues[10].toString() : "";
			hAccountNo2 = rowValues[11] != null ? rowValues[11].toString() : "";
			hJoinsNo = rowValues[12] != null ? rowValues[12].toString() : "";

			// fprintf( fileWriter, "%.*s%.*s%07ld%.*s%.*s11V01%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 6, hWorkType, 2, hDataType, ncount+1, 16, hAccountNo, 8, hDealDate, 4, hJijumCd, 4, hMacNo, 16, hDealNo, 13, hDealAmt, 8, hMacNm, 1, hDealStatus, 9,hCustFee, 16, hAccountNo2, 10, hJoinsNo, 8, hDealDate, 17, " ");
			// fileWriter.write(String.format("%6s%2s%07d%16s%8s11V01%4s%4s%16s%13s%8s%1s%9s%16s%10s%8s%17s\n", hWorkType, hDataType, ncount+1, hAccountNo, hDealDate, hJijumCd, hMacNo, hDealNo, hDealAmt, hMacNm, hDealStatus,hCustFee, hAccountNo2, hJoinsNo, hDealDate, " "));
			fileWriter.write(String.format("%6s%2s%07d%16s%8s11V01%4s%4s%16s%13s%-"+minHanCount(8, hMacNm)+"s%1s%9s%16s%10s%8s%17s", hWorkType, hDataType, ncount+1, hAccountNo.trim(), hDealDate, hJijumCd, hMacNo, hDealNo, hDealAmt, hMacNm.trim(), hDealStatus,hCustFee, hAccountNo2.trim(), hJoinsNo, hDealDate, " "));
			// fileWriter.newLine();

			if( "1".equals(hDealStatus)) {
				lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);

				ncount++;
			}
	    }

		// fprintf( fileWriter, "V01HPS339999999V01%07ld%015ld%*s\n", ncount, lnTotalAmt, 110, " " );
		fileWriter.write(String.format("V01HPS339999999V01%07d%015d%110s", ncount, lnTotalAmt, " " ));
		// fileWriter.newLine();

		fileWriter.close();

		return 0;
	}

	/* 농협카드 */
	private int GetCHTranData(String pTransDate, String pFileName) throws IOException {
		String hDealDate;
		String hDealTime;
		String hAccountNo;
		String hMacNo;
		String hDealAmt;
		String hDealStatus;
		String hDealNo;
		String hDealNo2;
		String hPreActDate;
		String hPreDate;
		String hTradeType;
		String hOrgRespCd;
		String szDate;

		int		nTotalDealCnt;
		long	lnTotalDealAmt;
		long	lnTotalRejectAmt;
		long	nTotalRejectCnt;
		long	lnTotalCancelAmt;
		long	nTotalCancelCnt;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		/* 전송일이 휴일면 전송일을.. 전송일이 영업일이면 전 영업일을 return */
		hPreActDate = fileSendMapper.pickupGetCHTranData(pTransDate);

		if(hPreActDate == null) {
			throw new RuntimeException(">>> [GetNongHTranData] 전영업일 없음. \n");
		}

		hPreDate = fileSendMapper.pickupGetCHTranData2(pTransDate);

		if(hPreDate == null) {
			throw new RuntimeException(">>> [GetNongHTranData] 전일 없음. \n");
		}

		szDate = hPreDate;

		nTotalDealCnt = 0;
		lnTotalDealAmt = 0;
		nTotalRejectCnt = 0;
		lnTotalRejectAmt = 0;
		nTotalCancelCnt = 0;
		lnTotalCancelAmt = 0;

		// fprintf( fileWriter, "H96%8s%6ld%*s\n", pTransDate, GetCurTime(), 133, " " );
		fileWriter.write(String.format("H96%8s%6s%133s", pTransDate, GetCurTime(), " " ));
		// fileWriter.newLine();

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetCHTranData(szDate);
		Object[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hTradeType = rowValues[0] != null ? rowValues[0].toString() : "";
			hAccountNo = rowValues[1] != null ? rowValues[1].toString() : "";
			hDealAmt = rowValues[2] != null ? rowValues[2].toString() : "";
			hDealNo = rowValues[3] != null ? rowValues[3].toString() : "";
			hDealNo2 = rowValues[4] != null ? rowValues[4].toString() : "";
			hDealDate = rowValues[5] != null ? rowValues[5].toString() : "";
			hDealTime = rowValues[6] != null ? rowValues[6].toString() : "";
			hOrgRespCd = rowValues[7] != null ? rowValues[7].toString() : "";
			hMacNo = rowValues[8] != null ? rowValues[8].toString() : "";
			hDealStatus = rowValues[9] != null ? rowValues[9].toString() : "";

			// fprintf( fileWriter, "D%.*s013008%.*s%.*s%.*s%.*s%.*s%.*s0000%.*s%.*s410%.*s%*s\n", 4, hTradeType, 16, hAccountNo, 11, hDealAmt, 12, hDealNo, 6, hDealNo2, 8, hDealDate, 6, hDealTime, 2, hOrgRespCd, 16, hMacNo, 1, hDealStatus, 54, " ");
			fileWriter.write(String.format("D%4s013008%16s%11s%12s%6s%8s%6s0000%2s%16s410%1s%54s", hTradeType, hAccountNo.trim(), hDealAmt, hDealNo, hDealNo2, hDealDate, hDealTime, hOrgRespCd, hMacNo, hDealStatus, " "));
			// fileWriter.newLine();

			if(  hDealStatus.equals("0") ) {
				lnTotalDealAmt = lnTotalDealAmt + Long.parseLong(hDealAmt);

				nTotalDealCnt++;
			} else if(  hDealStatus.equals("1") ) {
				lnTotalCancelAmt = lnTotalCancelAmt + Long.parseLong(hDealAmt);

				nTotalCancelCnt++;
			} else if(  hDealStatus.equals("3") ) {
				lnTotalRejectAmt = lnTotalRejectAmt + Long.parseLong(hDealAmt);

				nTotalRejectCnt++;
			}

	    }

		lnTotalDealAmt /= 10000; /* 만원단위 */
		lnTotalCancelAmt /= 10000; /* 만원단위 */
		lnTotalRejectAmt /= 10000; /* 만원단위 */
		// fprintf( fileWriter, "T%08d%08d%08d%08d%08d%08d%*s\n",
		fileWriter.write(String.format("T%08d%08d%08d%08d%08d%08d%101s", nTotalDealCnt, lnTotalDealAmt, nTotalRejectCnt, lnTotalRejectAmt, nTotalCancelCnt, lnTotalCancelAmt, " "));
		// fileWriter.newLine();

		fileWriter.close();

		return 0;
	}

	/* 수협 실적 */
	private int GetSuHTranData(String pTransDate, String pFileName) throws IOException {
		String hDealDate;
		String hDealTime;
		String hAccountNo;
		String hMacNo;
		String hDealAmt;
		String hDealNo;
		String hPreActDate;
		String hPreDate;
		String hTradeType;
		String hJijum;
		String hLastType;
		String hOrgRespCd;
		String hOrgCd;
		String hFee;
		String hTransOrgCd;
		String hTransAccountNo;

		int		ncount;
		String szDate;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		hPreDate = fileSendMapper.pickupGetSuHTranData(pTransDate);

		hPreActDate = hPreDate;

		if(hPreDate == null) {
			throw new RuntimeException(">>> [GetSuHTranData] 전일 없음. \n");
		}

		szDate = hPreDate;

		ncount = 0;

		/* 전체 Header */
		// fprintf( fileWriter, "NI00011100000000070960000000%8s%*s\n", hPreDate, 164, " " );
		fileWriter.write(String.format("NI00011100000000070960000000%8s%164s", hPreDate, " " ));
		// fileWriter.newLine();

		while(true) {
			List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetSuHTranData(szDate);
			Object[] rowValues;

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new Object[obj.size()]);
				
				hDealDate = rowValues[0] != null ? rowValues[0].toString() : "";
				hDealTime = rowValues[1] != null ? rowValues[1].toString() : "";
				hDealNo = rowValues[2] != null ? rowValues[2].toString() : "";
				hJijum = rowValues[3] != null ? rowValues[3].toString() : "";
				hMacNo = rowValues[4] != null ? rowValues[4].toString() : "";
				hTradeType = rowValues[5] != null ? rowValues[5].toString() : "";
				hLastType = rowValues[6] != null ? rowValues[6].toString() : "";
				hOrgRespCd = rowValues[7] != null ? rowValues[7].toString() : "";
				hOrgCd = rowValues[8] != null ? rowValues[8].toString() : "";
				hAccountNo = rowValues[9] != null ? rowValues[9].toString() : "";
				hDealAmt = rowValues[10] != null ? rowValues[10].toString() : "";
				hFee = rowValues[11] != null ? rowValues[11].toString() : "";
				hTransOrgCd = rowValues[12] != null ? rowValues[12].toString() : "";
				hTransAccountNo = rowValues[13] != null ? rowValues[13].toString() : "";
				
				if (hTransAccountNo.length() > 14) hTransAccountNo = hTransAccountNo.substring(0, 14);

				// fprintf( fileWriter, "NI000122%07ld", ncount+1 );
				// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 8, hDealDate, 6, hDealTime, 13, hDealNo, 3, hJijum, 4, hMacNo, 6, hTradeType, 4, hLastType, 2, hOrgRespCd, 3, hOrgCd, 16, hAccountNo, 12, hDealAmt, 5, hFee, 3, hTransOrgCd, 14, hTransAccountNo, 86, " ");
				fileWriter.write(String.format("NI000122%07d%8s%6s%13s%3s%4s%6s%4s%2s%3s%-16s%12s%5s%3s%-14s%86s", ncount+1, hDealDate, hDealTime, hDealNo, hJijum, hMacNo, hTradeType, hLastType, hOrgRespCd, hOrgCd, hAccountNo.trim(), hDealAmt, hFee, hTransOrgCd, hTransAccountNo, " "));
				// fileWriter.newLine();

				ncount++;
		    }

			hPreDate = fileSendMapper.pickupGetSuHTranData2(szDate);

			if( Long.parseLong(hPreDate) < Long.parseLong(hPreActDate) ) {
				break;
			}

			szDate = hPreDate;
		}

		// fprintf( fileWriter, "NI0001339999999007096%07ld%*s%*s\n", ncount, 8, pTransDate, 164, " " );
		fileWriter.write(String.format("NI0001339999999007096%07d%8s%164s", ncount, pTransDate, " " ));
		// fileWriter.newLine();

		fileWriter.close();

		// fseek (fileWriter, 6+2+7+3+3, 0 );
		// fprintf( fileWriter, "%7s", szCnt );
		RandomAccessFile raf = new RandomAccessFile(pFileName, "rw");
		raf.seek(6+2+7+3+3);
		raf.writeBytes(String.format("%07d", ncount ));
		raf.close();

		return 0;
	}

	/* 수협 기기정보 */
	
	private int GetSuHMacData(String pTransDate, String pFileName) throws IOException {
		String hMacNo;
		String hType;
		String hSetPlace;
		String hSetAddr;
		String hInterphoneNo;
		String hZipNo;

		String hActDate;
		int		ncount;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		/* 매월 첫일이 휴일면 익영업일을.. 그렇지 않으면 전송일을 return */
		hActDate = fileSendMapper.pickupGetSuHMacData(pTransDate);

		if(hActDate == null) {
			throw new RuntimeException(">>> [GetSuHMacData] 익영업일 없음. \n");
		}

		/* 휴일에는 전송하지 않는다. */
		if( pTransDate.equals(hActDate) ) {
			throw new RuntimeException(String.format(">>> [GetSuHMacData][%s]-[%s] 휴일 전송 하지 않음, 매달 첫 영업일에만 전송 \n", pTransDate, hActDate));
		}

		ncount = 0;

		// fprintf( fileWriter, "NI00021100000000070960000000%8s%*s\n", pTransDate, 164, " " );
		fileWriter.write(String.format("NI00021100000000070960000000%8s%164s", pTransDate, " " ));
		// fileWriter.newLine();

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetSuHMacData(pTransDate);
		Object[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hMacNo = rowValues[0] != null ? rowValues[0].toString() : "";
			hType = rowValues[1] != null ? rowValues[1].toString() : "";
			hSetPlace = rowValues[2] != null ? rowValues[2].toString() : "";
			hSetAddr = rowValues[3] != null ? rowValues[3].toString() : "";
			hInterphoneNo = rowValues[4] != null ? rowValues[4].toString() : "";
			hZipNo = rowValues[5] != null ? rowValues[5].toString() : "";

			// fprintf( fileWriter, "NI000222%07ld096", ncount+1 );
			fileWriter.write(String.format("NI000222%07d096", ncount+1 ));
			// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 4, hMacNo, 1, hType, 30, hSetPlace, 100, hSetAddr, 15, hInterphoneNo, 6, hZipNo, 26, " ");
			// fileWriter.write(String.format("%4s%1s%30s%100s%15s%6s%26s\n", hMacNo, hType, hSetPlace, hSetAddr, hInterphoneNo, hZipNo, " "));
			fileWriter.write(String.format("%4s%1s%-"+minHanCount(30, hSetPlace)+"s%-"+minHanCount(100, hSetAddr)+"s%-15s%6s%26s", hMacNo, hType, hSetPlace.trim(), hSetAddr.trim(), hInterphoneNo, hZipNo, " "));
			// fileWriter.newLine();

			ncount++;
	    }

		// fprintf( fileWriter, "NI0002339999999007096%07ld%.8s%*s\n", ncount, pTransDate, 164, " " );
		fileWriter.write(String.format("NI0002339999999007096%07d%8s%164s", ncount, pTransDate, " " ));
		// fileWriter.newLine();

		fileWriter.close();

		// fseek (fileWriter, 6+2+7+3+3, 0 );
		// fprintf( fileWriter, "%7s", szCnt );
		RandomAccessFile raf = new RandomAccessFile(pFileName, "rw");
		raf.seek(6+2+7+3+3);
		raf.writeBytes(String.format("%07d", ncount ));
		raf.close();

		return 0;
	}

	/* 금융결제원(KFTC) 실적 */
	private int GetKFTCTranData(String pTransDate, String pFileName) throws IOException {
		String hDealAmt;
		String hActDate;
		String hPreActDate;
		String hPreDate;
		String hDebitCd;
		String hOrgCd;

		String szDate;
		int		ncount;

		long	nTotalDebitAmt = 0;
		long	nTotalCreditAmt = 0;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		/* 영업일엔 영업일을 반환, 휴일에는 최종영업일을 반환 				*/
		hActDate = fileSendMapper.pickupGetKFTCTranData(pTransDate);

	    if ( hActDate == null ) {
	    	throw new RuntimeException(">>> [GetKFTCTranData] 영업일 없음.\n");
	    }

		/* 휴일에는 전송하지 않는다.					 */
		/* 값이 다를 경우, 함수 종료.					*/
		if( !hActDate.equals(pTransDate) ) {
			throw new RuntimeException(String.format(">>> [GetKFTCTranData][%s]-[%s] 휴일 전송 하지 않음, 영업일에만 전송 \n", pTransDate, hActDate));
		}

		hPreDate = fileSendMapper.pickupGetKFTCTranData2(pTransDate);

		hPreActDate = hPreDate;

		if(hPreDate == null) {
			throw new RuntimeException(">>> [GetKFTCTranData] 전일 없음.\n");
		}

		szDate = hPreDate;

		ncount = 0;

		/* 전체 Header */
		// fprintf( fileWriter, "CD111911%07d096*******%8s%*s\n", ncount, pTransDate, 67, " " );
		fileWriter.write(String.format("CD111911%07d096*******%8s%67s", ncount, pTransDate, " " ));
		// fileWriter.newLine();

		while(true) {
			List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetKFTCTranData(pTransDate);
			Object[] rowValues;

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new Object[obj.size()]);
				
				hOrgCd = rowValues[0] != null ? rowValues[0].toString() : "";
				hDebitCd = rowValues[1] != null ? rowValues[1].toString() : "";
				hDealAmt = rowValues[2] != null ? rowValues[2].toString() : "";

				// fprintf( fileWriter, "CD111922%07d%s%s%015ld%*s\n", ncount+1, hOrgCd ,hDebitCd, Long.parseLong(hDealAmt), 66, " " );
				fileWriter.write(String.format("CD111922%07d%s%s%015d%66s", ncount+1, hOrgCd ,hDebitCd, Long.parseLong(hDealAmt), " " ));
				// fileWriter.newLine();

				/* 차변과 대변 각각 총합을 구함 */
				if ( "D".equals(hDebitCd)) { 					/* 차변일 경우 */
					nTotalDebitAmt = nTotalDebitAmt + Long.parseLong(hDealAmt);
				} else {/*	if ( "C".equals(hDebitCD))	*/ /* 대변일 경우 */
					nTotalCreditAmt = nTotalCreditAmt + Long.parseLong(hDealAmt);
				}

				ncount++;
		    }

			hPreDate = fileSendMapper.pickupGetKFTCTranData3(szDate);

			if( Long.parseLong(hPreDate) < Long.parseLong(hPreActDate) ) {
				break;
			}

			szDate = hPreDate;
		}

		// fprintf( fileWriter, "CD1119339999999096%07d%020lu%020lu%*s\n", ncount, nTotalDebitAmt, nTotalCreditAmt, 35, " " );
		fileWriter.write(String.format("CD1119339999999096%07d%020d%020d%35s", ncount, Math.abs(nTotalDebitAmt), Math.abs(nTotalCreditAmt), " " ));
		// fileWriter.newLine();

		fileWriter.close();

		// fseek (fileWriter, 6+2+7+3, 0 );
		// fprintf( fileWriter, "%7s", szCnt );
		RandomAccessFile raf = new RandomAccessFile(pFileName, "rw");
		raf.seek(6+2+7+3);
		raf.writeBytes(String.format("%07d", ncount ));
		raf.close();

		return 0;
	}

	int MAX_MAIN_INDEX = 8;
	int MAX_SUB_INDEX = 8;
	/* 전자상품권 코드정보 */
	
	private int GetGiftCardInfoData(String pTransDate, String pFileName) throws IOException {
		String hInfoType;
		String hCol_2;
		String hCol_3;
		String hCol_4;
		String hCol_5;
		String hCol_6;
		String hCol_7;
		String hCol_8;
		String hCol_9;
		String hCol_10;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetGiftCardInfoData();
		Object[] rowValues;

		int nMainIdex = 0;
		int	nSubIdex = 0;
		int i;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			hInfoType = rowValues[0] != null ? rowValues[0].toString() : "";
			hCol_2 = rowValues[1] != null ? rowValues[1].toString() : "";
			hCol_3 = rowValues[2] != null ? rowValues[2].toString() : "";
			hCol_4 = rowValues[3] != null ? rowValues[3].toString() : "";
			hCol_5 = rowValues[4] != null ? rowValues[4].toString() : "";
			hCol_6 = rowValues[5] != null ? rowValues[5].toString() : "";
			hCol_7 = rowValues[6] != null ? rowValues[6].toString() : "";
			hCol_8 = rowValues[7] != null ? rowValues[7].toString() : "";
			hCol_9 = rowValues[8] != null ? rowValues[8].toString() : "";
			hCol_10 = rowValues[9] != null ? rowValues[9].toString() : "";

			/*
			spc_chk0( strlen( hInfoType ), hInfoType );
			spc_chk0( strlen( hCol_2 		), hCol_2 	 );
			spc_chk0( strlen( hCol_3 		), hCol_3 	 );
			spc_chk0( strlen( hCol_4 		), hCol_4 	 );
			spc_chk0( strlen( hCol_5 		), hCol_5 	 );
			spc_chk0( strlen( hCol_6 		), hCol_6 	 );
			spc_chk0( strlen( hCol_7 		), hCol_7 	 );
			spc_chk0( strlen( hCol_8 		), hCol_8 	 );
			spc_chk0( strlen( hCol_9 		), hCol_9 	 );
			spc_chk0( strlen( hCol_10 		), hCol_10 	 );
			*/

			int nQryIndex = Integer.parseInt(hCol_10) - 1;

			/* 대분류 일경우 포맷 */
			if( "0".equals(hInfoType)) {
				/* 세분류가 8개이하로 정의 되어 있다면 빈칸을 넣어주기 위한 작업 */
				if( nMainIdex > 0 && nSubIdex < MAX_SUB_INDEX ) {
					for( i = nSubIdex; i < MAX_SUB_INDEX ; i++ ) {
						// fprintf( fileWriter, "1       0%*s\n", 141, " ");
						fileWriter.write(String.format("1       0%141s", " "));
						// fileWriter.newLine();
					}
				}

				if( nMainIdex  <= nQryIndex ) {
					while(true) {
						if( nMainIdex == nQryIndex ) {
							// fprintf( fileWriter, "0%-*s%-*s%-*s%-*s%-*s%-*s%*s\n",1, "1", 3,hCol_3, 4,hCol_4, 2,hCol_5, 20,hCol_6, 80,hCol_7, 39, " " );
							// fileWriter.write(String.format("0%1s%-3s%-4s%-2s%-20s%-80s%39s\n", "1",hCol_3,hCol_4,hCol_5,hCol_6,hCol_7, " " ));
							fileWriter.write(String.format("0%1s%-3s%-4s%-2s%-"+minHanCount(20, hCol_6)+"s%-"+minHanCount(80, hCol_7)+"s%39s", "1",hCol_3,hCol_4,hCol_5,hCol_6.trim(),hCol_7.trim(), " " ));
							// fileWriter.newLine();
							
							break;
						} else {
							// fprintf( fileWriter, "00%*s\n", 148, " ");
							fileWriter.write(String.format("00%148s", " "));
							// fileWriter.newLine();

							for( i = 0; i < MAX_SUB_INDEX; i++ ) {
								// fprintf( fileWriter, "1       0%*s\n", 141, " ");
								fileWriter.write(String.format("1       0%141s", " "));
								// fileWriter.newLine();
							}

							nMainIdex++;

							if( nMainIdex == MAX_MAIN_INDEX ) break;
						}

					}
				}

				nMainIdex++;

				nSubIdex = 0;

			} else { /* 세부분류 일경우 포맷 */
				String szImsi1, szImsi2;
				szImsi1 = String.format("%05.02f", Float.parseFloat(hCol_8) );
				szImsi2 = String.format("%05.02f", Float.parseFloat(hCol_9) );

				if( nSubIdex  <= nQryIndex ) {
					while(true) {
						if( nSubIdex == nQryIndex ) {
							// fprintf( fileWriter, "1%-*s%-*s%-*s%-*s%-*s%07d%0*s%0*s%*s\n",3,hCol_2, 4,hCol_3, 1, "1", 2,hCol_5, 20,hCol_6, Integer.parseInt(hCol_7), 5, szImsi1, 5, szImsi2, 102, " " );
							// fileWriter.write(String.format("1%3s%-4s%-1s%-2s%-20s%07d%5s%5s%102s\n",hCol_2,hCol_3, "1",hCol_5,hCol_6, Integer.parseInt(hCol_7), szImsi1, szImsi2, " " ));
							fileWriter.write(String.format("1%3s%-4s%-1s%-2s%-"+minHanCount(20, hCol_6)+"s%07d%5s%5s%102s",hCol_2,hCol_3, "1",hCol_5,hCol_6.trim(), Integer.parseInt(hCol_7), szImsi1, szImsi2, " " ));
							// fileWriter.newLine();
							break;
						} else {
							// fprintf( fileWriter, "1       0%*s\n", 141, " ");
							fileWriter.write(String.format("1       0%141s", " "));
							// fileWriter.newLine();

							nSubIdex++;
						}

						if( nSubIdex == MAX_SUB_INDEX ) break;
					}
				}

				nSubIdex++;
			}

		}

		/* 쿼리 종료후 채워지지않은 필드 채우기 */
		if( nSubIdex < MAX_SUB_INDEX ) {
			for( i = nSubIdex; i < MAX_SUB_INDEX ; i++ ) {
				// fprintf( fileWriter, "1       0%*s\n", 141, " ");
				fileWriter.write(String.format("1       0%141s", " "));
				// fileWriter.newLine();
			}
		}

		if( nMainIdex < MAX_MAIN_INDEX ) {
			while( nMainIdex < MAX_MAIN_INDEX ) {
				// fprintf( fileWriter, "00%*s\n", 148, " ");
				fileWriter.write(String.format("00%148s", " "));
				// fileWriter.newLine();
				for( i = 0; i < MAX_SUB_INDEX; i++ ) {
					// fprintf( fileWriter, "1       0%*s\n", 141, " ");
					fileWriter.write(String.format("1       0%141s", " "));
					// fileWriter.newLine();
				}
				nMainIdex ++;
			}
		}

		fileWriter.close();

		return 0;
	}

	private int GetCNOperData(String pTransDate, String pFileName) throws IOException {
		String hOrgType;
		String hDealDate;
		String hCardType;
		String hTimeDealAmt;
		String hOtDealAmt;
		String hTotalAmt;
		String hPlusType;
		String hDemandAmt;

		int		ncount;

		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetCNOperData(pTransDate);
		Object[] rowValues;

		ncount = 0;

		// fprintf( pfTran, "H 06 %s%*s\n", pTransDate, 87, " " );
		fileWriter.write(String.format("H 06 %s%87s", pTransDate, " "));
		// fileWriter.newLine();

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);

			hOrgType = rowValues[0] != null ? rowValues[0].toString() : "";
			hDealDate = rowValues[1] != null ? rowValues[1].toString() : "";
			hCardType = rowValues[2] != null ? rowValues[2].toString() : "";
			hTimeDealAmt = rowValues[3] != null ? rowValues[3].toString() : "";
			hOtDealAmt = rowValues[4] != null ? rowValues[4].toString() : "";
			hTotalAmt = rowValues[5] != null ? rowValues[5].toString() : "";
			hPlusType = rowValues[6] != null ? rowValues[6].toString() : "";
			hDemandAmt = rowValues[7] != null ? rowValues[7].toString() : "";

			// fprintf( pfTran, "D %.*s %.*s %.*s %015d %015d %015d %.*s%015d%*s\n", 2 , hOrgType	, 8 ,hDealDate	, 1 , hCardType	, hTimeDealAmt, hOtDealAmt	, hTotalAmt	, 1 , hPlusType	, hDemandAmt	, 20, " ");
			fileWriter.write(String.format("D %2s %-8s %1s %015d %015d %015d %s%015d%20s", hOrgType, hDealDate, hCardType, Integer.parseInt(hTimeDealAmt), Integer.parseInt(hOtDealAmt), Integer.parseInt(hTotalAmt), hPlusType, Integer.parseInt(hDemandAmt), " "));
			// fileWriter.newLine();
	
			ncount++;
	    }
		
		// fprintf( pfTran, "T 06 %s %015d%*s\n", pTransDate, ncount, 71, " " );
		fileWriter.write(String.format("T 06 %s %015d%71s", pTransDate, ncount, " " ));
		// fileWriter.newLine();

		fileWriter.close();

		return 0;
	}

	private int shb230511(String pTransDate, String pFileName) throws IOException {
		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		if( !"01".equals(pTransDate.substring(6)) ) {
			throw new RuntimeException("매월 01일에만 전송 하도록 함.\n" );
		}

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectShb230511(pTransDate.substring(0, 6));
		Object[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			fileWriter.write((String)rowValues[0]);
			fileWriter.newLine();
	    }

		fileWriter.close();
		
		return 0;
	}

	private int shb230521(String pTransDate, String pFileName) throws IOException {
		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		if( !"01".equals(pTransDate.substring(6)) ) {
			throw new RuntimeException("매월 01일에만 전송 하도록 함.\n" );
		}

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectShb230521(pTransDate.substring(0, 6));
		Object[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			fileWriter.write((String)rowValues[0]);
			fileWriter.newLine();
	    }

		fileWriter.close();
		
		return 0;
	}

	private int shb230571(String pTransDate, String pFileName) throws IOException {
		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		if( !"01".equals(pTransDate.substring(6)) ) {
			throw new RuntimeException("매월 01일에만 전송 하도록 함.\n" );
		}

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectShb230571(pTransDate.substring(0, 6));
		Object[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			fileWriter.write((String)rowValues[0]);
			fileWriter.newLine();
	    }

		fileWriter.close();
		
		return 0;
	}

	private int shb2305F1(String pTransDate, String pFileName) throws IOException {
		new File(pFileName).createNewFile();
		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFileName), "KSC5601"));

		if( !"01".equals(pTransDate.substring(6)) ) {
			throw new RuntimeException("매월 01일에만 전송 하도록 함.\n" );
		}

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectShb2305F1(pTransDate.substring(0, 6));
		Object[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new Object[obj.size()]);
			
			fileWriter.write((String)rowValues[0]);
			fileWriter.newLine();
	    }

		fileWriter.close();
		
		return 0;
	}
	
	private Object GetCurTime() {
		return new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
	}
	
	private int minHanCount(int cnt, String str) {
		return cnt - str.replaceAll("[^ㄱ-힣]", "").length();
	}

}