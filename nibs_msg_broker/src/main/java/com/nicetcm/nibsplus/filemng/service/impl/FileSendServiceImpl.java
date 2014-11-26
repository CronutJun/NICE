/** 
 * com.nicetcm.nibsplus.filesend.service.impl.FileSendServiceImpl
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 10. 10.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.File;
import java.io.FileInputStream;
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

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3FileHandle;

import com.nicetcm.nibsplus.filemng.common.FileMngException;
import com.nicetcm.nibsplus.filemng.dao.FileSendMapper;
import com.nicetcm.nibsplus.filemng.service.FileSendService;
import com.nicetcm.nibsplus.orgsend.constant.ExceptionType;
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

    private Connection connection;
    private SFTPv3Client sftp = null;
    
	public void execute(String... argv) throws Exception {
		String szTransDate = null;
		String szOrgCd = null;
		String[] szaryOrg= {
			"0BC",
			"1BC",
			"0CN",
			"0CU",
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
			"0SC",
			"A07",
			"007",
			"000",
			"0GV"
		};
		int MAX_ORG_CNT = szaryOrg.length;

		if ( argv.length != 1 ) {
			szTransDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		} else {
			if (argv[0].length() == 3) {
				szTransDate = NibsBatchUtil.SysDate();
				szOrgCd = argv[0];
			} else if (argv[0].length() == 11) {
				szTransDate = argv[0].substring(0, 8);

				if( (argv[0].length() - 8) == 3 ) {
					szOrgCd = argv[0].substring(8);
				}
			} else {
				System.out.print( "Usage : MakeOrgTranFile [calc_date(8)+orgcd(3)]\n" );
			}
		}

		try {
			connectFtp();
			
			if (szOrgCd == null) {
				for(int i = 0 ; i < MAX_ORG_CNT; i++ ) {
					szOrgCd = szaryOrg[i];
	
					/* 전체실행 예외기관 부분 														 */
					/* 금융결제원(KFTC)의 경우, 전체실행 시간인 01:30에 데이터가 없으므로, 제외한다. */
					if ("000".equals(szOrgCd)) {
						continue;
					}
	
					try {
						putFtp(PutOrgTranFile(szTransDate, szOrgCd));
					} catch(RuntimeException e) {
						System.out.println(e.getMessage());
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					putFtp(PutOrgTranFile(szTransDate, szOrgCd));
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		} finally {
			closeFtp();
		}
		
		System.out.print(">>> [main] 작업완료\n");
	}
	
	private void connectFtp() throws FileMngException, IOException {
		String szHost = (String)config.get("host.host");;
		String szID = (String)config.get("host.userid");;
		String szPwd = (String)config.get("host.password");
		
    	connection = new Connection(szHost);
        connection.connect();

        if (!connection.authenticateWithPassword(szID, szPwd)) {
            throw new FileMngException(ExceptionType.NETWORK, "ftp 서버에 로그인하지 못했습니다.");
        }

        sftp = new SFTPv3Client(connection);
	}

	private void putFtp(File file) throws FileMngException, IOException {
		String szDestPath = (String)config.get("host.remote.path");
		SFTPv3FileHandle rfile = null;
		FileInputStream fis = null;
    	long fileOffset = 0;
    	byte[] readBuf = new byte[32768];
    	int readCnt=0;
    	
        try {
        	rfile = sftp.createFile(szDestPath + "/" + file.getName());
        	fis = new FileInputStream(file);
        	
            while ((readCnt = fis.read(readBuf)) != -1) {
            	sftp.write(rfile, fileOffset, readBuf, 0, readCnt);
            	
            	fileOffset += readCnt;
            }
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException ex) {}
            if (sftp != null && rfile != null) {
                try {
                	if (rfile != null) sftp.closeFile(rfile);
                } catch (IOException e) {}
            }
        }
	}
	
	private void closeFtp() {
        if (sftp != null && sftp.isConnected()) {
        	sftp.close();
        	connection.close();
        }
	}
	
	private File PutOrgTranFile(String pDate, String pOrgCd) throws Exception {
		String szSrcPath = (String)config.get("host.local.path");
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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));
		
		hPreDate = fileSendMapper.pickupGetCNTranData(pTransDate);

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetCNTranData(pTransDate);
		String[] rowValues;
		
		fileWriter.write(String.format("1%sKMC%58s\n", hPreDate, " "));
		
		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hDataType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hDealDate = StringUtils.defaultIfEmpty(rowValues[1], "");
			hDealTime = StringUtils.defaultIfEmpty(rowValues[2], "");
			hAccountNo = StringUtils.defaultIfEmpty(rowValues[3], "");
			hMacNo = StringUtils.defaultIfEmpty(rowValues[4], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[5], "");
			hDealStatus = StringUtils.defaultIfEmpty(rowValues[6], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[7], "");

			// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 1, hDataType, 8, hDealDate, 6, hDealTime, 16, hAccountNo, 8, hMacNo, 10, hDealAmt, 1, hDealStatus, 12, hDealNo, 8, " ");
			fileWriter.write(String.format("%1s%8s%6s%16s%8s%10s%1s%12s%8s\n", hDataType, hDealDate, hDealTime, hAccountNo, hMacNo, hDealAmt, hDealStatus, hDealNo, " "));

			lnTotalAmt += Long.parseLong(hDealAmt);
			ncount++;
		}

		// fprintf( fileWriter, "3%08d%013ld%*s\n", ncount, lnTotalAmt, 48, " " );
		fileWriter.write(String.format("3%08d%013d%48s\n", ncount, lnTotalAmt, " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		/* 전송일이 휴일면 전송일을 전송일이 영업일이면 전 영업일을 return */
		hPreActDate = fileSendMapper.pickupGetCNMacData(pTransDate);

		if(hPreActDate == null) {
			System.out.print(">>> [GetCityTranData] 전영업일 없음. \n");
			return -1;
		}

		/* 휴일에는 전송하지 않는다. */
		if( pTransDate.equals(hPreActDate)) {
			System.out.print(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 않음 \n", pTransDate, hPreActDate));
			return -2;
		}

		/* 전전 영업일을 return */
		hPrePreActDate = fileSendMapper.pickupGetCNMacDataPrev(pTransDate);

		if(hPrePreActDate == null) {
			System.out.print(">>> [GetCityTranData] 전전영업일 없음. \n");
			return -1;
		}

		ncount = 0;

		// fprintf( fileWriter, "H%8s%*s\n", pTransDate, 191, " " );
		fileWriter.write(String.format("H%8s%191s\n", pTransDate, " " ));

		/* 변경분만 전송 */
		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetCNMacData(hPreActDate, hPrePreActDate);
		String[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hSetYn = StringUtils.defaultIfEmpty(rowValues[0], "");
			hMacNo = StringUtils.defaultIfEmpty(rowValues[1], "");
			hSetDate = StringUtils.defaultIfEmpty(rowValues[2], "");
			hOpenDate = StringUtils.defaultIfEmpty(rowValues[3], "");
			hUpdateDate = StringUtils.defaultIfEmpty(rowValues[4], "");
			hCloseDate = StringUtils.defaultIfEmpty(rowValues[5], "");
			hZipNo = StringUtils.defaultIfEmpty(rowValues[6], "");
			hSetPlace = StringUtils.defaultIfEmpty(rowValues[7], "");
			hSetAddr = StringUtils.defaultIfEmpty(rowValues[8], "");
			hInterPhone = StringUtils.defaultIfEmpty(rowValues[9], "");
			hPicYn = StringUtils.defaultIfEmpty(rowValues[10], "");
			hCDType = StringUtils.defaultIfEmpty(rowValues[11], "");

			// fprintf( fileWriter, "D%.*s96%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 2, hSetYn, 8, hMacNo, 8, hSetDate, 8, hOpenDate, 8, hUpdateDate, 8, hCloseDate, 6, hZipNo, 60, hSetPlace, 30, hSetAddr, 20, hInterPhone, 1, hPicYn, 2, hCDType, 36, " ");
			fileWriter.write(String.format("D%2s96%8s%8s%8s%8s%8s%6s%60s%30s%20s%1s%2s%36s\n", hSetYn, hMacNo, hSetDate, hOpenDate, hUpdateDate, hCloseDate, hZipNo, hSetPlace, hSetAddr, hInterPhone, hPicYn, hCDType, " "));

			ncount++;
	    }

		// fprintf( fileWriter, "T%.8s%010ld%*s\n", pTransDate, ncount, 181, " " );
		fileWriter.write(String.format("T%8s%010d%181s\n", pTransDate, ncount, " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		hPreDate = fileSendMapper.pickupGetBCTranData(pTransDate);

		if(hPreDate == null) {
			System.out.print(">>> [GetCityTranData] 전영업일 없음. \n");
			return -1;
		}

		/* 전일자 실적 전송 */
		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetBCTranData(hPreDate);
		String[] rowValues;
		
		// fprintf( fileWriter, "1%sNIC000000%*s\n", hPreDate, 52, " " );
		fileWriter.write(String.format("1%sNIC000000%52s\n", hPreDate, " " ));

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hDataType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hDealDate = StringUtils.defaultIfEmpty(rowValues[1], "");
			hDealTime = StringUtils.defaultIfEmpty(rowValues[2], "");
			hAccountNo = StringUtils.defaultIfEmpty(rowValues[3], "");
			hMacNo = StringUtils.defaultIfEmpty(rowValues[4], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[5], "");
			hNiceDealAmt = StringUtils.defaultIfEmpty(rowValues[6], "");
			hDealStatus = StringUtils.defaultIfEmpty(rowValues[7], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[8], "");

			// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 1, hDataType, 8, hDealDate, 6, hDealTime, 16, hAccountNo, 4, hMacNo, 7, hDealAmt, 7, hNiceDealAmt, 1, hDealStatus, 12, hDealNo, 8, " ");
			fileWriter.write(String.format("%1s%8s%6s%16s%4s%7s%7s%1s%12s%8s\n", hDataType, hDealDate, hDealTime, hAccountNo, hMacNo, hDealAmt, hNiceDealAmt, hDealStatus, hDealNo, " "));

			lnTotalAmt += Long.parseLong(hDealAmt);

			ncount++;
	    }

		// fprintf( fileWriter, "3%08d%013ld%*s\n", ncount, lnTotalAmt, 48, " " );
		fileWriter.write(String.format("3%08d%013d%48s\n", ncount, lnTotalAmt, " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		if( !"01".equals(pTransDate.substring(6)) ) {
			System.out.print("매월 01일에만 전송 하도록 함.\n" );
			return -1;
		}

		ncount = 0;

		// fprintf( fileWriter, "BHATM-INFO%8s%*s\n", pTransDate, 232, " " );
		fileWriter.write(String.format("BHATM-INFO%8s%232s\n", pTransDate, " " ));

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetBCMacData(pTransDate);
		String[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hMacNo = StringUtils.defaultIfEmpty(rowValues[0], "");
			hSetPlace = StringUtils.defaultIfEmpty(rowValues[1], "");
			hOpenDate = StringUtils.defaultIfEmpty(rowValues[2], "");
			hZipNo = StringUtils.defaultIfEmpty(rowValues[3], "");
			hSetAddr = StringUtils.defaultIfEmpty(rowValues[4], "");

			// fprintf( fileWriter, "BD03102%.*s%.*s%.*s080022 8252%.*s%.*s%*s\n", 16, hMacNo, 50, hSetPlace, 8, hOpenDate, 6, hZipNo, 100, hSetAddr, 52, " ");
			fileWriter.write(String.format("BD03102%16s%50s%8s080022 8252%6s%100s%52s\n", hMacNo, hSetPlace, hOpenDate, hZipNo, hSetAddr, " "));

			ncount++;
	    }

		// fprintf( fileWriter, "BT%08ld%*s\n", ncount, 240, " " );
		fileWriter.write(String.format("BT%08d%240s\n", ncount, " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));
	
		hNotendOutFlag = "1";			/* 출금미완료 FLAG		*/
	
		hPreDate = fileSendMapper.pickupGetSHTranData(pTransDate);
	
		if( "0".equals(pDealType)) { /* 지급 */
			hOrgCd = "NICEBNK";
		} else {
			hOrgCd = "P001226";
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
		String[] rowValues;
	
		/* 요청에 의해 필러 1바이트 늘림 */
		// fprintf( fileWriter, "JH0001110000000%.*s%s%*s\n", 7, hOrgCd, hPreDate, 70, " " );
		fileWriter.write(String.format("JH0001110000000%7s%s%70s\n", hOrgCd, hPreDate, " " ));

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hDataType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hDealDate = StringUtils.defaultIfEmpty(rowValues[1], "");
			hDealTime = StringUtils.defaultIfEmpty(rowValues[2], "");
			hMacNo = StringUtils.defaultIfEmpty(rowValues[3], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[4], "");
			hDealStatus = StringUtils.defaultIfEmpty(rowValues[5], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[6], "");
			hDealType = StringUtils.defaultIfEmpty(rowValues[7], "");
			hNotendInOrgCd = StringUtils.defaultIfEmpty(rowValues[8], "");
			hNotendInAccountNo = StringUtils.defaultIfEmpty(rowValues[9], "");
			hNotendOutFlag = StringUtils.defaultIfEmpty(rowValues[10], "");
			hNotendOutDealdate = StringUtils.defaultIfEmpty(rowValues[11], "");
			
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
			fileWriter.write(String.format("JH0001%2s%07d%4s%12s%6s%12s%1s0%10s%3s%17s%1s%8s%10s\n", hDataType, ncount, hMsgType, hDealNo, hDealTime, hDealAmt, hDealStatus, hMacNo, hNotendInOrgCd, hNotendInAccountNo, hNotendOutFlag, hNotendOutDealdate, " "));
	
			lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);
	
			ncount++;
	    }
	
		/* 요청에 의해 필러 1바이트 늘림 */
		// fprintf( fileWriter, "JH000133999999900000000000000000000%08d%012lu%08d%012lu%*s\n", ncount-1, lnTotalAmt, ncount-1, lnTotalAmt, 25, " " );
		fileWriter.write(String.format("JH000133999999900000000000000000000%08d%012d%08d%012d%25s\n", ncount-1, lnTotalAmt, ncount-1, lnTotalAmt, " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));
	
		hPreDate = fileSendMapper.pickupGetSCTranData(pTransDate);
	
		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetSCTranData(hPreDate);
		String[] rowValues;

		ncount = 0;
		lnTotalAmt = 0;
	
		// fprintf( fileWriter, "HEADER  96        %s%*s\n", hPreDate, 4, " " );
		fileWriter.write(String.format("HEADER  96        %s%4s\n", hPreDate, " " ));

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hDealDate = StringUtils.defaultIfEmpty(rowValues[0], "");
			hDealTime = StringUtils.defaultIfEmpty(rowValues[1], "");
			hAccountNo = StringUtils.defaultIfEmpty(rowValues[2], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[3], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[4], "");

			// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s\n", 8, hDealDate, 6, hDealTime, 19, hAccountNo, 18, hDealAmt, 12, hDealNo);
			fileWriter.write(String.format("%8s%6s%19s%18s%12s\n", hDealDate, hDealTime, hAccountNo, hDealAmt, hDealNo));
	
			lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);
	
			ncount++;
	    }
	
		// fprintf( fileWriter, "TRAILER 96        %012d\n", ncount);
		fileWriter.write(String.format("TRAILER 96        %012d\n", ncount));
	
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
		String[] rowValues;
	
		ncount = 0;
		lnTotalAmt = 0;
	
		// fprintf( fileWriter, "HB00011100000009A0000000%8s%*s\n", hPreDate, 28, " " );
		fileWriter.write(String.format("HB00011100000009A0000000%8s%28s\n", hPreDate, " " ));
	
		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hWorkType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hDataType = StringUtils.defaultIfEmpty(rowValues[1], "");
			hDealType = StringUtils.defaultIfEmpty(rowValues[2], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
			hDealTime = StringUtils.defaultIfEmpty(rowValues[4], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[5], "");
			hDealStatus = StringUtils.defaultIfEmpty(rowValues[6], "");

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));
	
		hPreDate = fileSendMapper.pickupGetHNetNewTranData(pTransDate);
	
		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetHNetNewTranData(hPreDate);
		String[] rowValues;
	
		ncount = 0;
		lnTotalAmt = 0;
	
		// fprintf( fileWriter, "HB0001110000000J960000000%8s%*s\n", hPreDate, 27, " " );
		fileWriter.write(String.format("HB0001110000000J960000000%8s%27s\n", hPreDate, " " ));

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hWorkType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hDataType = StringUtils.defaultIfEmpty(rowValues[1], "");
			hDealType = StringUtils.defaultIfEmpty(rowValues[2], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
			hDealTime = StringUtils.defaultIfEmpty(rowValues[4], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[5], "");
			hDealStatus = StringUtils.defaultIfEmpty(rowValues[6], "");
			
			// fprintf( fileWriter, "%.*s%.*s%07ld%.*s%.*s%.*s%.*s%.*s%*s\n", 6, hWorkType, 2, hDataType, ncount+1, 6, hDealType, 13, hDealNo, 6, hDealTime, 12, hDealAmt, 1, hDealStatus, 7, " ");
			fileWriter.write(String.format("%6s%2s%07d%6s%13s%6s%12s%1s%7s\n", hWorkType, hDataType, ncount+1, hDealType, hDealNo, hDealTime, hDealAmt, hDealStatus, " "));
	
			lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);
	
			ncount++;
	    }
	
		// fprintf( fileWriter, "HB0001339999999J96%07ld%*s\n", ncount, 35, " " );
		fileWriter.write(String.format("HB0001339999999J96%07d%35s\n", ncount, " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		hPreDate = fileSendMapper.pickupGetCUTranData(pTransDate);

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetCUTranData(hPreDate);
		String[] rowValues;

		ncount = 0;
		lnTotalAmt = 0;

		szDate = hPreDate.substring(2);

		// fprintf( fileWriter, "H%sATM%6s6%*s\n", szDate, "NICE", 83, " " );
		fileWriter.write(String.format("H%sATM%6s6%83s\n", szDate, "NICE", " " ));

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hDataType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hDealDate = StringUtils.defaultIfEmpty(rowValues[1], "");
			hDealTime = StringUtils.defaultIfEmpty(rowValues[2], "");
			hAccountNo = StringUtils.defaultIfEmpty(rowValues[3], "");
			hMacNo = StringUtils.defaultIfEmpty(rowValues[4], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[5], "");
			hDeal2Amt = StringUtils.defaultIfEmpty(rowValues[6], "");
			hDealStatus = StringUtils.defaultIfEmpty(rowValues[7], "");
			hBrandNm = StringUtils.defaultIfEmpty(rowValues[8], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[9], "");
			hDealDate2 = StringUtils.defaultIfEmpty(rowValues[10], "");

			// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 1, hDataType, 6, hDealDate, 6, hDealTime, 19, hAccountNo, 8, hMacNo, 7, hDealAmt, 6, hDeal2Amt, 1, hDealStatus, 1, hBrandNm, 12, hDealNo, 8, hDealDate2, 25, " ");
			fileWriter.write(String.format("%1s%6s%6s%19s%8s%7s%6s%1s%1s%12s%8s%25s\n", hDataType, hDealDate, hDealTime, hAccountNo, hMacNo, hDealAmt, hDeal2Amt, hDealStatus, hBrandNm, hDealNo, hDealDate2, " "));

			lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);

			ncount++;
	    }

		// fprintf( fileWriter, "T%06d%013ld%*s\n", ncount, lnTotalAmt, 80, " " );
		fileWriter.write(String.format("T%06d%013d%80s\n", ncount, lnTotalAmt, " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		/* 전송일이 휴일면 전송일을 전송일이 영업일이면 전 영업일을 return */
		hPreActDate = fileSendMapper.pickupGetCityTranData(pTransDate);

		if(hPreActDate == null) {
			System.out.print(">>> [GetCityTranData] 전영업일 없음. \n");
			return -1;
		}

		/* 씨티카드 휴일에는 전송하지 않는다. */
		if( !pTransDate.equals(hPreActDate)) {
			System.out.print(String.format(">>> [GetCityTranData] 휴일 전송 안함 [%s]\n", pTransDate));
			return -2;
		}

		hPreDate = fileSendMapper.pickupGetCityTranData2(pTransDate);

		if(hPreDate == null) {
			System.out.print(">>> [GetCityTranData] 전일 없음. \n");
			return -1;
		}

		szDate = hPreDate;

		ncount = 0;
		lnTotalAmt = 0;

		// fprintf( fileWriter, "1NIC%s000000%*s\n", pTransDate, 52, " " );
		fileWriter.write(String.format("1NIC%s000000%52s\n", pTransDate, " " ));

		while(true) {
			List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetCityTranData(szDate);
			String[] rowValues;

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new String[obj.size()]);
				
				hDataType = StringUtils.defaultIfEmpty(rowValues[0], "");
				hDealDate = StringUtils.defaultIfEmpty(rowValues[1], "");
				hDealTime = StringUtils.defaultIfEmpty(rowValues[2], "");
				hAccountNo = StringUtils.defaultIfEmpty(rowValues[3], "");
				hMacNo = StringUtils.defaultIfEmpty(rowValues[4], "");
				hDealAmt = StringUtils.defaultIfEmpty(rowValues[5], "");
				hDeal2Amt = StringUtils.defaultIfEmpty(rowValues[6], "");
				hDealStatus = StringUtils.defaultIfEmpty(rowValues[7], "");
				hDealNo = StringUtils.defaultIfEmpty(rowValues[8], "");

				// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 1, hDataType, 8, hDealDate, 6, hDealTime, 16, hAccountNo, 4, hMacNo, 7, hDealAmt, 7, hDeal2Amt, 1, hDealStatus, 12, hDealNo, 8, " ");
				fileWriter.write(String.format("%1s%8s%6s%16s%4s%7s%7s%1s%12s%8s\n", hDataType, hDealDate, hDealTime, hAccountNo, hMacNo, hDealAmt, hDeal2Amt, hDealStatus, hDealNo, " "));

				lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);

				ncount++;
		    }

			hPreDate = fileSendMapper.pickupGetCityTranData3(szDate);

			System.out.print(String.format("DealDate[%s]-PreDate[%s]-preActDate[%s]\n", szDate, hPreDate, hPreActDate));

			if( Long.parseLong(hPreDate) < Long.parseLong(hPreActDate) ) {
				break;
			}

			szDate = hPreDate;
		}
		// fprintf( fileWriter, "3%08d%013ld%*s\n", ncount, lnTotalAmt, 48, " " );
		fileWriter.write(String.format("3%08d%013d%48s\n", ncount, lnTotalAmt, " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		/* 전송일이 휴일면 전송일을 전송일이 영업일이면 전 영업일을 return */
		hPreActDate = fileSendMapper.pickupGetLGTranData(pTransDate);

		if(hPreActDate == null) {
			System.out.print(">>> [GetLGTranData] 전영업일 없음. \n");
			return -1;
		}

		/* LG카드 휴일에는 전송하지 않는다. */
		if( !pTransDate.equals(hPreActDate)) {
			System.out.print(String.format(">>> [GetLGTranData] 휴일 전송 안함 [%s]\n", pTransDate));
			return -2;
		}

		hPreDate = fileSendMapper.pickupGetLGTranData2(pTransDate);

		if(hPreDate == null) {
			System.out.print(">>> [GetLGTranData] 전일 없음. \n");
			return -1;
		}

		szDate = hPreDate;

		ncount = 0;
		lnTotalAmt = 0;

		// fprintf( fileWriter, "H%8s%8s%8s96%*s\n", pTransDate, hPreActDate, szDate, 43, " " );
		fileWriter.write(String.format("H%8s%8s%8s96%43s\n", pTransDate, hPreActDate, szDate, " " ));

		while(true) {
			List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetLGTranData(szDate);
			String[] rowValues;

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new String[obj.size()]);
				
				hDataType = StringUtils.defaultIfEmpty(rowValues[0], "");
				hTradeType = StringUtils.defaultIfEmpty(rowValues[1], "");
				hDealDate = StringUtils.defaultIfEmpty(rowValues[2], "");
				hDealTime = StringUtils.defaultIfEmpty(rowValues[3], "");
				hDealNo = StringUtils.defaultIfEmpty(rowValues[4], "");
				hAccountNo = StringUtils.defaultIfEmpty(rowValues[5], "");
				hDealAmt = StringUtils.defaultIfEmpty(rowValues[6], "");
				hMacNo = StringUtils.defaultIfEmpty(rowValues[7], "");

				// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 1, hDataType, 2, hTradeType, 8, hDealDate, 6, hDealTime, 12, hDealNo, 16, hAccountNo, 7, hDealAmt, 4, hMacNo, 14, " ");
				fileWriter.write(String.format("%1s%2s%8s%6s%12s%16s%7s%4s%14s\n", hDataType, hTradeType, hDealDate, hDealTime, hDealNo, hAccountNo, hDealAmt, hMacNo, " "));

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
		fileWriter.write(String.format("T%06d%013d%50s\n", ncount, lnTotalAmt, " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		hPreDate = fileSendMapper.pickupGetLCTranData(pTransDate);

		if(hPreDate == null) {
			System.out.print(">>> [GetLGTranData] 전일 없음.\n");
			return -1;
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
		fileWriter.write(String.format("HD%8s%8s89%180s\n", hPreDate, pTransDate, " " ));

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetLCTranData(szDate);
		String[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hDataType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[1], "");
			hAccountNo = StringUtils.defaultIfEmpty(rowValues[2], "");
			hDealDate = StringUtils.defaultIfEmpty(rowValues[3], "");
			hDealTime = StringUtils.defaultIfEmpty(rowValues[4], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[5], "");
			hTradeType = StringUtils.defaultIfEmpty(rowValues[6], "");
			hTransOrgCd = StringUtils.defaultIfEmpty(rowValues[7], "");
			hTransAccountNo = StringUtils.defaultIfEmpty(rowValues[8], "");
			hDealStatus = StringUtils.defaultIfEmpty(rowValues[9], "");

			// fprintf( fileWriter, "%.*s89%.*s%.*s%.*s%.*s%.*s%.*s00  %.*s%.*s%.*s%.*s%*s\n", 2, hDataType, 12, hDealNo, 16, hAccountNo, 8, hDealDate, 6, hDealTime, 13, hDealAmt, 1, hTradeType, 6, hDealTime, 2, hTransOrgCd, 20, hTransAccountNo, 1, hDealStatus, 107, " ");
			fileWriter.write(String.format("%2s89%12s%16s%8s%6s%13s%1s00  %6s%2s%20s%1s%107s\n", hDataType, hDealNo, hAccountNo, hDealDate, hDealTime, hDealAmt, hTradeType, hDealTime, hTransOrgCd, hTransAccountNo, hDealStatus, " "));

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
		fileWriter.write(String.format("TR89%010d%013d%010d%013d%010d%013d%010d%013d%010d%013d%81s\n", lnTotalCnt ,lnTotalAmt ,lnNormalOutCnt ,lnNormalOutAmt , lnNormalTransCnt ,lnNormalTransAmt ,lnEtcCnt ,lnEtcAmt , lnErrCnt ,lnErrAmt, " " ));

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
		if( !pTransDate.equals(hPreActDate)) {
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
			String[] rowValues;

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new String[obj.size()]);
				
				hDealDate = StringUtils.defaultIfEmpty(rowValues[0], "");
				hDealTime = StringUtils.defaultIfEmpty(rowValues[1], "");
				hDealNo = StringUtils.defaultIfEmpty(rowValues[2], "");
				hJoinType = StringUtils.defaultIfEmpty(rowValues[3], "");
				hJoinMojum = StringUtils.defaultIfEmpty(rowValues[4], "");
				hJoinCd = StringUtils.defaultIfEmpty(rowValues[5], "");
				hMacNo = StringUtils.defaultIfEmpty(rowValues[6], "");
				hTradeType = StringUtils.defaultIfEmpty(rowValues[7], "");
				hLastType = StringUtils.defaultIfEmpty(rowValues[8], "");
				hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[9], "");
				hOrgCd = StringUtils.defaultIfEmpty(rowValues[10], "");
				hAccountNo = StringUtils.defaultIfEmpty(rowValues[11], "");
				hDealAmt = StringUtils.defaultIfEmpty(rowValues[12], "");
				hFee = StringUtils.defaultIfEmpty(rowValues[13], "");
				hTransOrgCd = StringUtils.defaultIfEmpty(rowValues[14], "");
				hTransAccountNo = StringUtils.defaultIfEmpty(rowValues[15], "");
				hAtmType = StringUtils.defaultIfEmpty(rowValues[16], "");

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
		if( !pTransDate.equals(hPreActDate)) {
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
			String[] rowValues;

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new String[obj.size()]);
				
				hDealDate = StringUtils.defaultIfEmpty(rowValues[0], "");
				hDealTime = StringUtils.defaultIfEmpty(rowValues[1], "");
				hDealNo = StringUtils.defaultIfEmpty(rowValues[2], "");
				hJoinType = StringUtils.defaultIfEmpty(rowValues[3], "");
				hJoinMojum = StringUtils.defaultIfEmpty(rowValues[4], "");
				hJoinCd = StringUtils.defaultIfEmpty(rowValues[5], "");
				hMacNo = StringUtils.defaultIfEmpty(rowValues[6], "");
				hTradeType = StringUtils.defaultIfEmpty(rowValues[7], "");
				hLastType = StringUtils.defaultIfEmpty(rowValues[8], "");
				hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[9], "");
				hOrgCd = StringUtils.defaultIfEmpty(rowValues[10], "");
				hAccountNo = StringUtils.defaultIfEmpty(rowValues[11], "");
				hDealAmt = StringUtils.defaultIfEmpty(rowValues[12], "");
				hFee = StringUtils.defaultIfEmpty(rowValues[13], "");
				hTransOrgCd = StringUtils.defaultIfEmpty(rowValues[14], "");
				hTransAccountNo = StringUtils.defaultIfEmpty(rowValues[15], "");
				hAtmType = StringUtils.defaultIfEmpty(rowValues[16], "");

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		/* 전송일이 휴일면 전송일을.. 전송일이 영업일이면 전 영업일을 return */
		hPreActDate = fileSendMapper.pickupGetNongHTranData_NEW(pTransDate);

		if(hPreActDate == null) {
			System.out.print(">>> [GetNongHTranData] 전영업일 없음. \n");
			return -1;
		}

		/* 휴일에는 전송하지 않는다. */
		if( !pTransDate.equals(hPreActDate)) {
			System.out.print(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 않음 \n", pTransDate, hPreActDate));
			return -2;
		}

		hPreDate = fileSendMapper.pickupGetNongHTranData_NEW2(pTransDate);

		if(hPreDate == null) {
			System.out.print(">>> [GetNongHTranData] 전일 없음. \n");
			return -1;
		}

		szDate = hPreDate;

		ncount = 0;
		lnTotalAmt = 0;

		// fprintf( fileWriter, "VC0033110000000011NC 0000000%8s%*s\n", hPreDate, 134, " " );
		fileWriter.write(String.format("VC0033110000000011NC 0000000%8s%134s\n", hPreDate, " " ));

		/* 20110609 기관응답코드를 N0, N1,N2 에 대해서도 정상으로 변환처리 */
		while(true) {
			List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetNongHTranData_NEW(szDate);
			String[] rowValues;

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new String[obj.size()]);
				
				hDealDate = StringUtils.defaultIfEmpty(rowValues[0], "");
				hDealTime = StringUtils.defaultIfEmpty(rowValues[1], "");
				hDealNo = StringUtils.defaultIfEmpty(rowValues[2], "");
				hJoinMojum = StringUtils.defaultIfEmpty(rowValues[3], "");
				hJoinCd = StringUtils.defaultIfEmpty(rowValues[4], "");
				hMacNo = StringUtils.defaultIfEmpty(rowValues[5], "");
				hTradeType = StringUtils.defaultIfEmpty(rowValues[6], "");
				hLastType = StringUtils.defaultIfEmpty(rowValues[7], "");
				hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[8], "");
				hOrgCd = StringUtils.defaultIfEmpty(rowValues[9], "");
				hAccountNo = StringUtils.defaultIfEmpty(rowValues[10], "");
				hDealAmt = StringUtils.defaultIfEmpty(rowValues[11], "");
				hFee = StringUtils.defaultIfEmpty(rowValues[12], "");
				hTransOrgCd = StringUtils.defaultIfEmpty(rowValues[13], "");
				hTransAccountNo = StringUtils.defaultIfEmpty(rowValues[14], "");
				hAtmType = StringUtils.defaultIfEmpty(rowValues[15], "");

				// fprintf( fileWriter, "VC003322%07ld", ncount+1 );
				fileWriter.write(String.format("VC003322%07d", ncount+1 ));
				// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 8, hDealDate, 6, hDealTime, 13, hDealNo, 6, hJoinMojum, 3, hJoinCd, 4, hMacNo, 6, hTradeType, 4, hLastType, 2, hOrgRespCd, 3, hOrgCd, 17, hAccountNo, 12, hDealAmt, 5, hFee, 3, hTransOrgCd, 14, hTransAccountNo, 2, hAtmType, 47, " ");
				fileWriter.write(String.format("%8s%6s%13s%6s%3s%4s%6s%4s%2s%3s%17s%12s%5s%3s%14s%2s%47s\n", hDealDate, hDealTime, hDealNo, hJoinMojum, hJoinCd, hMacNo, hTradeType, hLastType, hOrgRespCd, hOrgCd, hAccountNo, hDealAmt, hFee, hTransOrgCd, hTransAccountNo, hAtmType, " "));

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
		fileWriter.write(String.format("VC0033339999999011NC %07d%8s%134s\n", ncount, hPreDate, " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		/* 매월 첫일이 휴일면 익영업일을.. 그렇지 않으면 전송일을 return */
		hActDate = fileSendMapper.pickupGetNongHMacData(pTransDate);

		if(hActDate == null) {
			System.out.print(">>> [GetNongHTranData] 익영업일 없음. \n");
			return -1;
		}

		/* 휴일에는 전송하지 않는다. */
		if( !pTransDate.equals(hActDate)) {
			System.out.print(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 않음, 매달 첫 영업일에만 전송 \n", pTransDate, hActDate));
			return -2;
		}

		ncount = 0;

		// fprintf( fileWriter, "VC0002110000000011NC 0000000%8s%*s\n", pTransDate, 164, " " );
		fileWriter.write(String.format("VC0002110000000011NC 0000000%8s%164s\n", pTransDate, " " ));

		/* 브랜드 제휴가 아닌 일반 CD VAN 일 경우 '096'->'NCJ'로 변경
			NCJ 와 별개로 브랜드 제휴가 아닌 기기를 '096'으로 아래 추가 20120703 BY 이재원 */
		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetNongHMacData(pTransDate);
		String[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hMacNo = StringUtils.defaultIfEmpty(rowValues[1], "");
			hSetPlace = StringUtils.defaultIfEmpty(rowValues[2], "");
			hSetAddr = StringUtils.defaultIfEmpty(rowValues[3], "");
			hInterphoneNo = StringUtils.defaultIfEmpty(rowValues[4], "");
			hZipNo = StringUtils.defaultIfEmpty(rowValues[5], "");
			hJoinMojumCd = StringUtils.defaultIfEmpty(rowValues[6], "");

			// fprintf( fileWriter, "VC000222%07ldI", ncount+1 );
			fileWriter.write(String.format("VC000222%07dI", ncount+1 ));
			// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 3, hType, 4, hMacNo, 30, hSetPlace, 100, hSetAddr, 15, hInterphoneNo, 6, hZipNo, 6, hJoinMojumCd, 20, " ");
			fileWriter.write(String.format("%3s%4s%30s%100s%15s%6s%6s%20s\n", hType, hMacNo, hSetPlace, hSetAddr, hInterphoneNo, hZipNo, hJoinMojumCd, " "));

			ncount++;
	    }

		// fprintf( fileWriter, "VC0002339999999011NC %07ld%.8s%*s\n", ncount, pTransDate, 164, " " );
		fileWriter.write(String.format("VC0002339999999011NC %07d%8s%164s\n", ncount, pTransDate, " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		/* 매월 첫일이 휴일면 익영업일을.. 그렇지 않으면 전송일을 return */
		hActDate = fileSendMapper.pickupGetNongHBrandMacData(pTransDate);

		if(hActDate == null) {
			System.out.print(">>> [GetNongHTranData] 익영업일 없음. \n");
			return -1;
		}

		/* 휴일에는 전송하지 않는다. */
		if( !pTransDate.equals(hActDate)) {
			System.out.print(String.format(">>> [GetNongHTranData][%s]-[%s] 휴일 전송 하지 않음, 매달 첫 영업일에만 전송 \n", pTransDate, hActDate));
			return -2;
		}

		hPreActDate = fileSendMapper.pickupGetNongHBrandMacData2(pTransDate);

		hPreActYM = hPreActDate;

		ncount = 0;

		// fprintf( fileWriter, "VC000111NCI*******%6s%*s\n", hPreActYM, 26, " " );
		fileWriter.write(String.format("VC000111NCI*******%6s%26s\n", hPreActYM, " " ));

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetNongHBrandMacData(hPreActDate);
		String[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hMacNo = StringUtils.defaultIfEmpty(rowValues[0], "");
			hMacModel = StringUtils.defaultIfEmpty(rowValues[1], "");
			hOpenDate = StringUtils.defaultIfEmpty(rowValues[2], "");

			// fprintf( fileWriter, "VC111122%07ld%6sNCI", ncount+1, hPreActYM );
			fileWriter.write(String.format("VC111122%07d%6sNCI", ncount+1, hPreActYM ));
			// fprintf( fileWriter, "%.*s%.*s%.*s1000000000000\n", 4, hMacNo, 1, hMacModel, 8, hOpenDate);
			fileWriter.write(String.format("%4s%1s%8s1000000000000\n", hMacNo, hMacModel, hOpenDate));

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
		String[] rowValues;

		new File(pFileName).createNewFile();
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		szOrgCd = pOrgCd.substring(1);

		/* 전송일이 휴일이면 전송일을 전송일이 영업일이면 전 영업일을 return */
		hPreActDate = fileSendMapper.pickupGetCommonTranData(pTransDate);

		if(hPreActDate == null) {
			System.out.print(String.format(">>> [GetCommonTranData-%s] 전영업일 없음.\n", pOrgCd));
			return -1;
		}

		/* 휴일에는 전송하지 않는다. */
		if( !pTransDate.equals(hPreActDate)) {
			System.out.print(String.format(">>> [GetCommonTranData-%s] 휴일 전송 안함 [%s]\n", szOrgCd,  pTransDate));
			return -2;
		}

		hPreDate = fileSendMapper.pickupGetCommonTranData2(pTransDate);

		if(hPreDate == null) {
			System.out.print(String.format(">>> [GetCommonTranData-%s] 전일 없음.\n", pOrgCd));
			return -1;
		}

		szDate = hPreDate;

		ncount = 0;

		/* 전체 Header */
		// fprintf( fileWriter, "ATM001110000000%2s030000000%.*s%*s\n", szOrgCd, 6, pTransDate.substring(2), 138, " " );
		fileWriter.write(String.format("ATM001110000000%2s030000000%6s%138s\n", szOrgCd, pTransDate.substring(2), " " ));

		while(true) {
			/* 영업내 Data부 Header */
			// fprintf( fileWriter, "ATM00122%07ld%2s03 012200110096%4s%6s%6s%6s%*s\n", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), 116, " " );
			fileWriter.write(String.format("ATM00122%07d%2s03 012200110096%4s%6s%6s%6s%116s\n", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));

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
				rowValues = obj.values().toArray(new String[obj.size()]);
				
				hWorkType = StringUtils.defaultIfEmpty(rowValues[0], "");
				hDataType = StringUtils.defaultIfEmpty(rowValues[1], "");
				hDealDate = StringUtils.defaultIfEmpty(rowValues[2], "");
				hAtmDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
				hInsetOrgCd = StringUtils.defaultIfEmpty(rowValues[4], "");
				hAccountNo = StringUtils.defaultIfEmpty(rowValues[5], "");
				hDealAmt = StringUtils.defaultIfEmpty(rowValues[6], "");
				hCustFee = StringUtils.defaultIfEmpty(rowValues[7], "");
				hBankFee = StringUtils.defaultIfEmpty(rowValues[8], "");
				hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[9], "");
				hDealNo = StringUtils.defaultIfEmpty(rowValues[10], "");
				hDealStatus = StringUtils.defaultIfEmpty(rowValues[11], "");

				if(  pOrgCd.equals("020") ) {
					// memset(hAccountNo.substring(7), '*', strlen(hAccountNo)-7);
					hAccountNo = StringUtils.rightPad(hAccountNo.substring(0, 7), hAccountNo.length(), "*");
				}

				// fprintf( fileWriter, "ATM00122%07ld%2s03 %s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%*s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, 1 ," ");
				fileWriter.write(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus ," "));

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
			fileWriter.write(String.format("ATM00122%07d%2s03 01220033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));
			ncount++;

			/* 영업외 Data부 Header */
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

			list = fileSendMapper.selectGetCommonTranData2(szDate, szOrgCd);
			
			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new String[obj.size()]);
				
				hWorkType = StringUtils.defaultIfEmpty(rowValues[0], "");
				hDataType = StringUtils.defaultIfEmpty(rowValues[1], "");
				hDealDate = StringUtils.defaultIfEmpty(rowValues[2], "");
				hAtmDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
				hInsetOrgCd = StringUtils.defaultIfEmpty(rowValues[4], "");
				hAccountNo = StringUtils.defaultIfEmpty(rowValues[5], "");
				hDealAmt = StringUtils.defaultIfEmpty(rowValues[6], "");
				hCustFee = StringUtils.defaultIfEmpty(rowValues[7], "");
				hBankFee = StringUtils.defaultIfEmpty(rowValues[8], "");
				hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[9], "");
				hDealNo = StringUtils.defaultIfEmpty(rowValues[10], "");
				hDealStatus = StringUtils.defaultIfEmpty(rowValues[11], "");

				if(  pOrgCd.equals("020") ) {
					// memset(hAccountNo.substring(7), '*', strlen(hAccountNo)-7);
					hAccountNo = StringUtils.rightPad(hAccountNo.substring(0, 7), hAccountNo.length(), "*");
				}

				// fprintf( fileWriter, "ATM00122%07ld%2s03 %s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%*s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, 1 ," ");
				fileWriter.write(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus ," "));

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
			fileWriter.write(String.format("ATM00122%07d%2s03 01230033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));

			ncount++;

			hPreDate = fileSendMapper.pickupGetCommonTranData3(szDate);

			if( Long.parseLong(hPreDate) < Long.parseLong(hPreActDate) ) {
				break;
			}

			szDate = hPreDate;
		}

		// fprintf( fileWriter, "ATM001339999999%2s03%07ld%*s\n", szOrgCd, ncount, 144, " " );
		fileWriter.write(String.format("ATM001339999999%2s03%07d%144s\n", szOrgCd, ncount, " " ));

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
		String[] rowValues;

		new File(pFileName).createNewFile();
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		szOrgCd = pOrgCd.substring(1);

		hPreDate = fileSendMapper.pickupGetKiupTranData(pTransDate);

		hPreActDate = hPreDate;

		if(hPreDate == null) {
			System.out.print(String.format(">>> [GetCommonTranData-%s] 전일 없음.\n", pOrgCd));
			return -1;
		}

		szDate = hPreDate;

		ncount = 0;

		/* 전체 Header */
		// fprintf( fileWriter, "ATM001110000000%2s030000000%.*s%*s\n", szOrgCd, 6, pTransDate.substring(2), 138, " " );
		fileWriter.write(String.format("ATM001110000000%2s030000000%6s%138s\n", szOrgCd, pTransDate.substring(2), " " ));

		/* 영업내 Data부 Header */
		// fprintf( fileWriter, "ATM00122%07ld%2s03 012200110096%4s%6s%6s%6s%*s\n", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), 116, " " );
		fileWriter.write(String.format("ATM00122%07d%2s03 012200110096%4s%6s%6s%6s%116s\n", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));

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
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hWorkType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hDataType = StringUtils.defaultIfEmpty(rowValues[1], "");
			hDealDate = StringUtils.defaultIfEmpty(rowValues[2], "");
			hAtmDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
			hInsetOrgCd = StringUtils.defaultIfEmpty(rowValues[4], "");
			hAccountNo = StringUtils.defaultIfEmpty(rowValues[5], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[6], "");
			hCustFee = StringUtils.defaultIfEmpty(rowValues[7], "");
			hBankFee = StringUtils.defaultIfEmpty(rowValues[8], "");
			hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[9], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[10], "");
			hDealStatus = StringUtils.defaultIfEmpty(rowValues[11], "");

			// fprintf( fileWriter, "ATM00122%07ld%2s03 %s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%*s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, 1 ," ");
			fileWriter.write(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus ," "));

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
		fileWriter.write(String.format("ATM00122%07d%2s03 01220033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));
		ncount++;

		/* 영업외 Data부 Header */
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

		list = fileSendMapper.selectGetKiupTranData2(szDate, szOrgCd);

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hWorkType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hDataType = StringUtils.defaultIfEmpty(rowValues[1], "");
			hDealDate = StringUtils.defaultIfEmpty(rowValues[2], "");
			hAtmDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
			hInsetOrgCd = StringUtils.defaultIfEmpty(rowValues[4], "");
			hAccountNo = StringUtils.defaultIfEmpty(rowValues[5], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[6], "");
			hCustFee = StringUtils.defaultIfEmpty(rowValues[7], "");
			hBankFee = StringUtils.defaultIfEmpty(rowValues[8], "");
			hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[9], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[10], "");
			hDealStatus = StringUtils.defaultIfEmpty(rowValues[11], "");

			// fprintf( fileWriter, "ATM00122%07ld%2s03 %s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%*s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, 1 ," ");
			fileWriter.write(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus ," "));

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
		fileWriter.write(String.format("ATM00122%07d%2s03 01230033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));

		ncount++;

		// fprintf( fileWriter, "ATM001339999999%2s03%07ld%*s\n", szOrgCd, ncount, 144, " " );
		fileWriter.write(String.format("ATM001339999999%2s03%07d%144s\n", szOrgCd, ncount, " " ));

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
		String[] rowValues;

		new File(pFileName).createNewFile();
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		szOrgCd = pOrgCd.substring(1);

		hPreDate = fileSendMapper.pickupGetKBSTTranData(pTransDate);

		hPreActDate = hPreDate;

		if(hPreDate == null) {
			System.out.print(String.format(">>> [GetKBSTTranData-%s] 전일 없음.\n", pOrgCd));
			return -1;
		}

		szDate = hPreDate;

		ncount = 0;

		/* 전체 Header */
		// fprintf( fileWriter, "ATM001110000000%2s030000000%.*s%*s\n", szOrgCd, 6, pTransDate.substring(2), 138, " " );
		fileWriter.write(String.format("ATM001110000000%2s030000000%6s%138s\n", szOrgCd, pTransDate.substring(2), " " ));

		/* 영업내 Data부 Header */
		// fprintf( fileWriter, "ATM00122%07ld%2s03 012200110096%4s%6s%6s%6s%*s\n",
		fileWriter.write(String.format("ATM00122%07d%2s03 012200110096%4s%6s%6s%6s%116s\n", ncount + 1, szOrgCd, szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));

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
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hWorkType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hDataType = StringUtils.defaultIfEmpty(rowValues[1], "");
			hDealDate = StringUtils.defaultIfEmpty(rowValues[2], "");
			hAtmDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
			hInsetOrgCd = StringUtils.defaultIfEmpty(rowValues[4], "");
			hAccountNo = StringUtils.defaultIfEmpty(rowValues[5], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[6], "");
			hCustFee = StringUtils.defaultIfEmpty(rowValues[7], "");
			hBankFee = StringUtils.defaultIfEmpty(rowValues[8], "");
			hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[9], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[10], "");
			hDealStatus = StringUtils.defaultIfEmpty(rowValues[11], "");
			hSpace = StringUtils.defaultIfEmpty(rowValues[12], "");

			// fprintf( fileWriter, "ATM00122%07ld%2s03 %s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, hSpace);
			fileWriter.write(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus, hSpace));

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
		fileWriter.write(String.format("ATM00122%07d%2s03 01220033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));
		ncount++;

		/* 영업외 Data부 Header */
		// fprintf( fileWriter, "ATM00122%07ld%2s03 012300110096%4s%6s%6s%6s%*s\n",
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

		list = fileSendMapper.selectGetKBSTTranData2(szDate, szOrgCd);
		
		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hWorkType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hDataType = StringUtils.defaultIfEmpty(rowValues[1], "");
			hDealDate = StringUtils.defaultIfEmpty(rowValues[2], "");
			hAtmDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
			hInsetOrgCd = StringUtils.defaultIfEmpty(rowValues[4], "");
			hAccountNo = StringUtils.defaultIfEmpty(rowValues[5], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[6], "");
			hCustFee = StringUtils.defaultIfEmpty(rowValues[7], "");
			hBankFee = StringUtils.defaultIfEmpty(rowValues[8], "");
			hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[9], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[10], "");
			hDealStatus = StringUtils.defaultIfEmpty(rowValues[11], "");
			hSpace = StringUtils.defaultIfEmpty(rowValues[12], "");

			// fprintf( fileWriter, "ATM00122%07ld%2s03 %s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, hSpace );
			fileWriter.write(String.format("ATM00122%07d%2s03 %s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%s\n", ncount+1, szOrgCd, hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus, hSpace ));

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
		fileWriter.write(String.format("ATM00122%07d%2s03 01230033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", ncount+1, szOrgCd , nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));

		ncount++;

		// fprintf( fileWriter, "ATM001339999999%2s03%07ld%*s\n", szOrgCd, ncount, 144, " " );
		fileWriter.write(String.format("ATM001339999999%2s03%07d%144s\n", szOrgCd, ncount, " " ));

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
		String[] rowValues;
		
		File fileWriter;

		szOrgCd = pOrgCd.substring(1);

		// 임시.. 전송일 10일 거래일 8,9일 데이터 요청 
		hPreActDate = "20061008";

		// 휴일에는 전송하지 않는다.
		if( !pTransDate.equals(hPreActDate)) {
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
				rowValues = obj.values().toArray(new String[obj.size()]);
				
				hWorkType = StringUtils.defaultIfEmpty(rowValues[0], "");
				hDataType = StringUtils.defaultIfEmpty(rowValues[1], "");
				hDealDate = StringUtils.defaultIfEmpty(rowValues[2], "");
				hAtmDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
				hInsetOrgCd = StringUtils.defaultIfEmpty(rowValues[4], "");
				hAccountNo = StringUtils.defaultIfEmpty(rowValues[5], "");
				hDealAmt = StringUtils.defaultIfEmpty(rowValues[6], "");
				hCustFee = StringUtils.defaultIfEmpty(rowValues[7], "");
				hBankFee = StringUtils.defaultIfEmpty(rowValues[8], "");
				hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[9], "");
				hDealNo = StringUtils.defaultIfEmpty(rowValues[10], "");
				hDealStatus = StringUtils.defaultIfEmpty(rowValues[11], "");

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
				rowValues = obj.values().toArray(new String[obj.size()]);
				
				hWorkType = StringUtils.defaultIfEmpty(rowValues[0], "");
				hDataType = StringUtils.defaultIfEmpty(rowValues[1], "");
				hDealDate = StringUtils.defaultIfEmpty(rowValues[2], "");
				hAtmDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
				hInsetOrgCd = StringUtils.defaultIfEmpty(rowValues[4], "");
				hAccountNo = StringUtils.defaultIfEmpty(rowValues[5], "");
				hDealAmt = StringUtils.defaultIfEmpty(rowValues[6], "");
				hCustFee = StringUtils.defaultIfEmpty(rowValues[7], "");
				hBankFee = StringUtils.defaultIfEmpty(rowValues[8], "");
				hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[9], "");
				hDealNo = StringUtils.defaultIfEmpty(rowValues[10], "");
				hDealStatus = StringUtils.defaultIfEmpty(rowValues[11], "");

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
		String[] rowValues;

		new File(pFileName).createNewFile();
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		szOrgCd = pOrgCd.substring(1);

		/* 전송일이 휴일이면 전송일을 전송일이 영업일이면 전 영업일을 return */
		hPreActDate = fileSendMapper.pickupGetKETranData(pTransDate);

		if(hPreActDate == null) {
			System.out.print(String.format(">>> [GetCommonTranData-%s] 전영업일 없음.\n", pOrgCd));
			return -1;
		}

		/* 휴일에는 전송하지 않는다. */
		if( !pTransDate.equals(hPreActDate)) {
			System.out.print(String.format(">>> [GetCommonTranData-%s] 휴일 전송 안함 [%s]\n", szOrgCd,  pTransDate));
			return -2;
		}

		hPreDate = fileSendMapper.pickupGetKETranData2(pTransDate);

		if(hPreDate == null) {
			System.out.print(String.format(">>> [GetCommonTranData-%s] 전일 없음.\n", pOrgCd));
			return -1;
		}

		szDate = hPreDate;

		while(true) {
			/* 영업내 Data부 Header */
			// fprintf( fileWriter, "012200110096%4s%6s%6s%6s%*s\n", szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), 116, " " );
			fileWriter.write(String.format("012200110096%4s%6s%6s%6s%116s\n", szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));

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
				rowValues = obj.values().toArray(new String[obj.size()]);
				
				hWorkType = StringUtils.defaultIfEmpty(rowValues[0], "");
				hDataType = StringUtils.defaultIfEmpty(rowValues[1], "");
				hDealDate = StringUtils.defaultIfEmpty(rowValues[2], "");
				hAtmDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
				hInsetOrgCd = StringUtils.defaultIfEmpty(rowValues[4], "");
				hAccountNo = StringUtils.defaultIfEmpty(rowValues[5], "");
				hDealAmt = StringUtils.defaultIfEmpty(rowValues[6], "");
				hCustFee = StringUtils.defaultIfEmpty(rowValues[7], "");
				hBankFee = StringUtils.defaultIfEmpty(rowValues[8], "");
				hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[9], "");
				hDealNo = StringUtils.defaultIfEmpty(rowValues[10], "");
				hDealStatus = StringUtils.defaultIfEmpty(rowValues[11], "");

				// fprintf( fileWriter, "%s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%*s\n", hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, 1 ," ");
				fileWriter.write(String.format("%s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus ," "));

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
			fileWriter.write(String.format("01220033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));

			/* 영업외 Data부 Header */
			// fprintf( fileWriter, "012300110096%4s%6s%6s%6s%*s\n", szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), 116, " " );
			fileWriter.write(String.format("012300110096%4s%6s%6s%6s%116s\n", szOrgCd, pTransDate.substring(2), hPreActDate.substring(2), hPreDate.substring(2), " " ));

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
				rowValues = obj.values().toArray(new String[obj.size()]);
				
				hWorkType = StringUtils.defaultIfEmpty(rowValues[0], "");
				hDataType = StringUtils.defaultIfEmpty(rowValues[1], "");
				hDealDate = StringUtils.defaultIfEmpty(rowValues[2], "");
				hAtmDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
				hInsetOrgCd = StringUtils.defaultIfEmpty(rowValues[4], "");
				hAccountNo = StringUtils.defaultIfEmpty(rowValues[5], "");
				hDealAmt = StringUtils.defaultIfEmpty(rowValues[6], "");
				hCustFee = StringUtils.defaultIfEmpty(rowValues[7], "");
				hBankFee = StringUtils.defaultIfEmpty(rowValues[8], "");
				hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[9], "");
				hDealNo = StringUtils.defaultIfEmpty(rowValues[10], "");
				hDealStatus = StringUtils.defaultIfEmpty(rowValues[11], "");

				// fprintf( fileWriter, "%s%s%06d%s%*s%s%s%.*s%*s%*s%s%0*d%0*d%*s%s%s%s%*s\n", hWorkType, hDataType, nDataCnt + 1, hDealDate, 6 , " ", hAtmDealNo, hInsetOrgCd, 22, hAccountNo, 4 , " ", 4 , " ", hDealAmt, 6 , Integer.parseInt(hCustFee), 6 , Integer.parseInt(hBankFee), 30 , " ", hOrgRespCd, hDealNo, hDealStatus, 1 ," ");
				fileWriter.write(String.format("%s%s%06d%s%6s%s%s%22s%4s%4s%s%06d%06d%30s%s%s%s%1s\n", hWorkType, hDataType, nDataCnt + 1, hDealDate , " ", hAtmDealNo, hInsetOrgCd, hAccountNo , " " , " ", hDealAmt , Integer.parseInt(hCustFee) , Integer.parseInt(hBankFee) , " ", hOrgRespCd, hDealNo, hDealStatus ," "));
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
			fileWriter.write(String.format("01230033  %06d%014d%010d%010d%06d%014d%06d%014d%06d%014d%40s\n", nTotAcptCnt, lnTotAcptAmt, lnTotCustAmt, lnTotBankAmt, nTotCancelCnt, lnTotCancelAmt , nTotNotMngCnt, lnTotNotMngAmg , nTotRejCnt, lnTotRejAmt, " " ));

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
		String[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hDataType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hRecordType = StringUtils.defaultIfEmpty(rowValues[1], "");
			hOrgCd = StringUtils.defaultIfEmpty(rowValues[2], "");
			hSmDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
			hAccountNo = StringUtils.defaultIfEmpty(rowValues[4], "");
			hStatusCd = StringUtils.defaultIfEmpty(rowValues[5], "");
			hDealTime1 = StringUtils.defaultIfEmpty(rowValues[6], "");
			hDealTime2 = StringUtils.defaultIfEmpty(rowValues[7], "");
			hDealStatus = StringUtils.defaultIfEmpty(rowValues[8], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[9], "");
			hCustFee = StringUtils.defaultIfEmpty(rowValues[10], "");
			hMacNo = StringUtils.defaultIfEmpty(rowValues[11], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[12], "");

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
			String[] rowValues;

			szFileName = String.format("%s/S%sDKS", szPath, szFileDate);

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new String[obj.size()]);
				
				hDataType = StringUtils.defaultIfEmpty(rowValues[0], "");
				hRecordType = StringUtils.defaultIfEmpty(rowValues[1], "");
				hOrgCd = StringUtils.defaultIfEmpty(rowValues[2], "");
				hSmDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
				hAccountNo = StringUtils.defaultIfEmpty(rowValues[4], "");
				hStatusCd = StringUtils.defaultIfEmpty(rowValues[5], "");
				hDealTime1 = StringUtils.defaultIfEmpty(rowValues[6], "");
				hDealTime2 = StringUtils.defaultIfEmpty(rowValues[7], "");
				hDealStatus = StringUtils.defaultIfEmpty(rowValues[8], "");
				hDealAmt = StringUtils.defaultIfEmpty(rowValues[9], "");
				hCustFee = StringUtils.defaultIfEmpty(rowValues[10], "");
				hMacNo = StringUtils.defaultIfEmpty(rowValues[11], "");
				hDealNo = StringUtils.defaultIfEmpty(rowValues[12], "");

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		hPreDate = fileSendMapper.pickupGetHITranData(pTransDate);

		System.out.print(String.format("Deal_Date[%s]\n", hPreDate));

		/* 하이패스의 경우 바로 전 거래( 이체요청=>기관코드 'NI' )의
		   카드번호, 승인번호(제휴기관거래번호 항목을 사용), 승인일자를 같이 보낸다. */
		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetHITranData(hPreDate);
		String[] rowValues;

		ncount = 0;
		lnTotalAmt = 0;

		// fprintf( fileWriter, "V01HPS110000000V01%8s%8s%*s\n", pTransDate, hPreDate, 116, " " );
		fileWriter.write(String.format("V01HPS110000000V01%8s%8s%116s\n", pTransDate, hPreDate, " " ));

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hWorkType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hDataType = StringUtils.defaultIfEmpty(rowValues[1], "");
			hAccountNo = StringUtils.defaultIfEmpty(rowValues[2], "");
			hDealDate = StringUtils.defaultIfEmpty(rowValues[3], "");
			hJijumCd = StringUtils.defaultIfEmpty(rowValues[4], "");
			hMacNo = StringUtils.defaultIfEmpty(rowValues[5], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[6], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[7], "");
			hMacNm = StringUtils.defaultIfEmpty(rowValues[8], "");
			hDealStatus = StringUtils.defaultIfEmpty(rowValues[9], "");
			hCustFee = StringUtils.defaultIfEmpty(rowValues[10], "");
			hAccountNo2 = StringUtils.defaultIfEmpty(rowValues[11], "");
			hJoinsNo = StringUtils.defaultIfEmpty(rowValues[12], "");

			// fprintf( fileWriter, "%.*s%.*s%07ld%.*s%.*s11V01%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 6, hWorkType, 2, hDataType, ncount+1, 16, hAccountNo, 8, hDealDate, 4, hJijumCd, 4, hMacNo, 16, hDealNo, 13, hDealAmt, 8, hMacNm, 1, hDealStatus, 9,hCustFee, 16, hAccountNo2, 10, hJoinsNo, 8, hDealDate, 17, " ");
			fileWriter.write(String.format("%6s%2s%07d%16s%8s11V01%4s%4s%16s%13s%8s%1s%9s%16s%10s%8s%17s\n", hWorkType, hDataType, ncount+1, hAccountNo, hDealDate, hJijumCd, hMacNo, hDealNo, hDealAmt, hMacNm, hDealStatus,hCustFee, hAccountNo2, hJoinsNo, hDealDate, " "));

			if( "1".equals(hDealStatus)) {
				lnTotalAmt = lnTotalAmt + Long.parseLong(hDealAmt);

				ncount++;
			}
	    }

		// fprintf( fileWriter, "V01HPS339999999V01%07ld%015ld%*s\n", ncount, lnTotalAmt, 110, " " );
		fileWriter.write(String.format("V01HPS339999999V01%07d%015d%110s\n", ncount, lnTotalAmt, " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		/* 전송일이 휴일면 전송일을.. 전송일이 영업일이면 전 영업일을 return */
		hPreActDate = fileSendMapper.pickupGetCHTranData(pTransDate);

		if(hPreActDate == null) {
			System.out.print(">>> [GetNongHTranData] 전영업일 없음. \n");
			return -1;
		}

		hPreDate = fileSendMapper.pickupGetCHTranData2(pTransDate);

		if(hPreDate == null) {
			System.out.print(">>> [GetNongHTranData] 전일 없음. \n");
			return -1;
		}

		szDate = hPreDate;

		nTotalDealCnt = 0;
		lnTotalDealAmt = 0;
		nTotalRejectCnt = 0;
		lnTotalRejectAmt = 0;
		nTotalCancelCnt = 0;
		lnTotalCancelAmt = 0;

		// fprintf( fileWriter, "H96%8s%6ld%*s\n", pTransDate, GetCurTime(), 133, " " );
		fileWriter.write(String.format("H96%8s%6s%133s\n", pTransDate, GetCurTime(), " " ));

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetCHTranData(szDate);
		String[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hTradeType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hAccountNo = StringUtils.defaultIfEmpty(rowValues[1], "");
			hDealAmt = StringUtils.defaultIfEmpty(rowValues[2], "");
			hDealNo = StringUtils.defaultIfEmpty(rowValues[3], "");
			hDealNo2 = StringUtils.defaultIfEmpty(rowValues[4], "");
			hDealDate = StringUtils.defaultIfEmpty(rowValues[5], "");
			hDealTime = StringUtils.defaultIfEmpty(rowValues[6], "");
			hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[7], "");
			hMacNo = StringUtils.defaultIfEmpty(rowValues[8], "");
			hDealStatus = StringUtils.defaultIfEmpty(rowValues[9], "");

			// fprintf( fileWriter, "D%.*s013008%.*s%.*s%.*s%.*s%.*s%.*s0000%.*s%.*s410%.*s%*s\n", 4, hTradeType, 16, hAccountNo, 11, hDealAmt, 12, hDealNo, 6, hDealNo2, 8, hDealDate, 6, hDealTime, 2, hOrgRespCd, 16, hMacNo, 1, hDealStatus, 54, " ");
			fileWriter.write(String.format("D%4s013008%16s%11s%12s%6s%8s%6s0000%2s%16s410%1s%54s\n", hTradeType, hAccountNo, hDealAmt, hDealNo, hDealNo2, hDealDate, hDealTime, hOrgRespCd, hMacNo, hDealStatus, " "));

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
		fileWriter.write(String.format("T%08d%08d%08d%08d%08d%08d%101s\n", nTotalDealCnt, lnTotalDealAmt, nTotalRejectCnt, lnTotalRejectAmt, nTotalCancelCnt, lnTotalCancelAmt, " "));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		hPreDate = fileSendMapper.pickupGetSuHTranData(pTransDate);

		hPreActDate = hPreDate;

		if(hPreDate == null) {
			System.out.print(">>> [GetSuHTranData] 전일 없음. \n");
			return -1;
		}

		szDate = hPreDate;

		ncount = 0;

		/* 전체 Header */
		// fprintf( fileWriter, "NI00011100000000070960000000%8s%*s\n", hPreDate, 164, " " );
		fileWriter.write(String.format("NI00011100000000070960000000%8s%164s\n", hPreDate, " " ));

		while(true) {
			List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetSuHTranData(szDate);
			String[] rowValues;

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new String[obj.size()]);
				
				hDealDate = StringUtils.defaultIfEmpty(rowValues[0], "");
				hDealTime = StringUtils.defaultIfEmpty(rowValues[1], "");
				hDealNo = StringUtils.defaultIfEmpty(rowValues[2], "");
				hJijum = StringUtils.defaultIfEmpty(rowValues[3], "");
				hMacNo = StringUtils.defaultIfEmpty(rowValues[4], "");
				hTradeType = StringUtils.defaultIfEmpty(rowValues[5], "");
				hLastType = StringUtils.defaultIfEmpty(rowValues[6], "");
				hOrgRespCd = StringUtils.defaultIfEmpty(rowValues[7], "");
				hOrgCd = StringUtils.defaultIfEmpty(rowValues[8], "");
				hAccountNo = StringUtils.defaultIfEmpty(rowValues[9], "");
				hDealAmt = StringUtils.defaultIfEmpty(rowValues[10], "");
				hFee = StringUtils.defaultIfEmpty(rowValues[11], "");
				hTransOrgCd = StringUtils.defaultIfEmpty(rowValues[12], "");
				hTransAccountNo = StringUtils.defaultIfEmpty(rowValues[13], "");

				// fprintf( fileWriter, "NI000122%07ld", ncount+1 );
				fileWriter.write(String.format("NI000122%07d", ncount+1 ));
				// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 8, hDealDate, 6, hDealTime, 13, hDealNo, 3, hJijum, 4, hMacNo, 6, hTradeType, 4, hLastType, 2, hOrgRespCd, 3, hOrgCd, 16, hAccountNo, 12, hDealAmt, 5, hFee, 3, hTransOrgCd, 14, hTransAccountNo, 86, " ");
				fileWriter.write(String.format("%8s%6s%13s%3s%4s%6s%4s%2s%3s%16s%12s%5s%3s%14s%86s\n", hDealDate, hDealTime, hDealNo, hJijum, hMacNo, hTradeType, hLastType, hOrgRespCd, hOrgCd, hAccountNo, hDealAmt, hFee, hTransOrgCd, hTransAccountNo, " "));

				ncount++;
		    }

			hPreDate = fileSendMapper.pickupGetSuHTranData2(szDate);

			if( Long.parseLong(hPreDate) < Long.parseLong(hPreActDate) ) {
				break;
			}

			szDate = hPreDate;
		}

		// fprintf( fileWriter, "NI0001339999999007096%07ld%*s%*s\n", ncount, 8, pTransDate, 164, " " );
		fileWriter.write(String.format("NI0001339999999007096%07d%8s%164s\n", ncount, pTransDate, " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		/* 매월 첫일이 휴일면 익영업일을.. 그렇지 않으면 전송일을 return */
		hActDate = fileSendMapper.pickupGetSuHMacData(pTransDate);

		if(hActDate == null) {
			System.out.print(">>> [GetSuHMacData] 익영업일 없음. \n");
			return -1;
		}

		/* 휴일에는 전송하지 않는다. */
		if( !pTransDate.equals(hActDate) ) {
			System.out.print(String.format(">>> [GetSuHMacData][%s]-[%s] 휴일 전송 하지 않음, 매달 첫 영업일에만 전송 \n", pTransDate, hActDate));
			return -2;
		}

		ncount = 0;

		// fprintf( fileWriter, "NI00021100000000070960000000%8s%*s\n", pTransDate, 164, " " );
		fileWriter.write(String.format("NI00021100000000070960000000%8s%164s\n", pTransDate, " " ));

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetSuHMacData(pTransDate);
		String[] rowValues;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hMacNo = StringUtils.defaultIfEmpty(rowValues[0], "");
			hType = StringUtils.defaultIfEmpty(rowValues[1], "");
			hSetPlace = StringUtils.defaultIfEmpty(rowValues[2], "");
			hSetAddr = StringUtils.defaultIfEmpty(rowValues[3], "");
			hInterphoneNo = StringUtils.defaultIfEmpty(rowValues[4], "");
			hZipNo = StringUtils.defaultIfEmpty(rowValues[5], "");

			// fprintf( fileWriter, "NI000222%07ld096", ncount+1 );
			fileWriter.write(String.format("NI000222%07d096", ncount+1 ));
			// fprintf( fileWriter, "%.*s%.*s%.*s%.*s%.*s%.*s%*s\n", 4, hMacNo, 1, hType, 30, hSetPlace, 100, hSetAddr, 15, hInterphoneNo, 6, hZipNo, 26, " ");
			fileWriter.write(String.format("%4s%1s%30s%100s%15s%6s%26s\n", hMacNo, hType, hSetPlace, hSetAddr, hInterphoneNo, hZipNo, " "));

			ncount++;
	    }

		// fprintf( fileWriter, "NI0002339999999007096%07ld%.8s%*s\n", ncount, pTransDate, 164, " " );
		fileWriter.write(String.format("NI0002339999999007096%07d%8s%164s\n", ncount, pTransDate, " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		/* 영업일엔 영업일을 반환, 휴일에는 최종영업일을 반환 				*/
		hActDate = fileSendMapper.pickupGetKFTCTranData(pTransDate);

	    if ( hActDate == null ) {
	    	System.out.print(">>> [GetKFTCTranData] 영업일 없음.\n");
	    	return -1;
	    }

		/* 휴일에는 전송하지 않는다.					 */
		/* 값이 다를 경우, 함수 종료.					*/
		if( !hActDate.equals(pTransDate) ) {
			System.out.print(String.format(">>> [GetKFTCTranData][%s]-[%s] 휴일 전송 하지 않음, 영업일에만 전송 \n", pTransDate, hActDate));
			return -2;
		}

		hPreDate = fileSendMapper.pickupGetKFTCTranData2(pTransDate);

		hPreActDate = hPreDate;

		if(hPreDate == null) {
			System.out.print(">>> [GetKFTCTranData] 전일 없음.\n");
			return -1;
		}

		szDate = hPreDate;

		ncount = 0;

		/* 전체 Header */
		// fprintf( fileWriter, "CD111911%07d096*******%8s%*s\n", ncount, pTransDate, 67, " " );
		fileWriter.write(String.format("CD111911%07d096*******%8s%67s\n", ncount, pTransDate, " " ));

		while(true) {
			List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetKFTCTranData(pTransDate);
			String[] rowValues;

			for (LinkedHashMap<String, Object> obj : list) {
				rowValues = obj.values().toArray(new String[obj.size()]);
				
				hOrgCd = StringUtils.defaultIfEmpty(rowValues[0], "");
				hDebitCd = StringUtils.defaultIfEmpty(rowValues[1], "");
				hDealAmt = StringUtils.defaultIfEmpty(rowValues[2], "");

				// fprintf( fileWriter, "CD111922%07d%s%s%015ld%*s\n", ncount+1, hOrgCd ,hDebitCd, Long.parseLong(hDealAmt), 66, " " );
				fileWriter.write(String.format("CD111922%07d%s%s%015d%66s\n", ncount+1, hOrgCd ,hDebitCd, Long.parseLong(hDealAmt), " " ));

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
		fileWriter.write(String.format("CD1119339999999096%07d%020d%020d%35s\n", ncount, Math.abs(nTotalDebitAmt), Math.abs(nTotalCreditAmt), " " ));

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
		OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pFileName));

		List<LinkedHashMap<String, Object>> list = fileSendMapper.selectGetGiftCardInfoData();
		String[] rowValues;

		int nMainIdex = 0;
		int	nSubIdex = 0;
		int i;

		for (LinkedHashMap<String, Object> obj : list) {
			rowValues = obj.values().toArray(new String[obj.size()]);
			
			hInfoType = StringUtils.defaultIfEmpty(rowValues[0], "");
			hCol_2 = StringUtils.defaultIfEmpty(rowValues[1], "");
			hCol_3 = StringUtils.defaultIfEmpty(rowValues[2], "");
			hCol_4 = StringUtils.defaultIfEmpty(rowValues[3], "");
			hCol_5 = StringUtils.defaultIfEmpty(rowValues[4], "");
			hCol_6 = StringUtils.defaultIfEmpty(rowValues[5], "");
			hCol_7 = StringUtils.defaultIfEmpty(rowValues[6], "");
			hCol_8 = StringUtils.defaultIfEmpty(rowValues[7], "");
			hCol_9 = StringUtils.defaultIfEmpty(rowValues[8], "");
			hCol_10 = StringUtils.defaultIfEmpty(rowValues[9], "");

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
						fileWriter.write(String.format("1       0%141s\n", " "));
					}
				}

				if( nMainIdex  <= nQryIndex ) {
					while(true) {
						if( nMainIdex == nQryIndex ) {
							// fprintf( fileWriter, "0%-*s%-*s%-*s%-*s%-*s%-*s%*s\n",1, "1", 3,hCol_3, 4,hCol_4, 2,hCol_5, 20,hCol_6, 80,hCol_7, 39, " " );
							fileWriter.write(String.format("0%1s%-3s%-4s%-2s%-20s%-80s%39s\n", "1",hCol_3,hCol_4,hCol_5,hCol_6,hCol_7, " " ));
							break;
						} else {
							// fprintf( fileWriter, "00%*s\n", 148, " ");
							fileWriter.write(String.format("00%148s\n", " "));

							for( i = 0; i < MAX_SUB_INDEX; i++ ) {
								// fprintf( fileWriter, "1       0%*s\n", 141, " ");
								fileWriter.write(String.format("1       0%141s\n", " "));
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
				szImsi1 = String.format("%02.02f", Float.parseFloat(hCol_8) );
				szImsi2 = String.format("%02.02f", Float.parseFloat(hCol_9) );

				if( nSubIdex  <= nQryIndex ) {
					while(true) {
						if( nSubIdex == nQryIndex ) {
							// fprintf( fileWriter, "1%-*s%-*s%-*s%-*s%-*s%07d%0*s%0*s%*s\n",3,hCol_2, 4,hCol_3, 1, "1", 2,hCol_5, 20,hCol_6, Integer.parseInt(hCol_7), 5, szImsi1, 5, szImsi2, 102, " " );
							fileWriter.write(String.format("1%3s%-4s%-1s%-2s%-20s%07d%5s%5s%102s\n",hCol_2,hCol_3, "1",hCol_5,hCol_6, Integer.parseInt(hCol_7), szImsi1, szImsi2, " " ));
							break;
						} else {
							// fprintf( fileWriter, "1       0%*s\n", 141, " ");
							fileWriter.write(String.format("1       0%141s\n", " "));

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
				fileWriter.write(String.format("1       0%141s\n", " "));
			}
		}

		if( nMainIdex < MAX_MAIN_INDEX ) {
			while( nMainIdex < MAX_MAIN_INDEX ) {
				// fprintf( fileWriter, "00%*s\n", 148, " ");
				fileWriter.write(String.format("00%148s\n", " "));
				for( i = 0; i < MAX_SUB_INDEX; i++ ) {
					// fprintf( fileWriter, "1       0%*s\n", 141, " ");
					fileWriter.write(String.format("1       0%141s\n", " "));
				}
				nMainIdex ++;
			}
		}

		fileWriter.close();

		return 0;
	}
	
	private Object GetCurTime() {
		return new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
	}

}