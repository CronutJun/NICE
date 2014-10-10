package com.nicetcm.nibsplus.filemng.model;

/**
 * 
 * <pre>
	case  0 : strcpy( suCasher.szJijum_Cd       , (char *)token); break;
	case  1 : strcpy( suCasher.szDeal_Date      , (char *)token); break;
	case  2 : strcpy( suCasher.szDeal_No        , (char *)token); break;
	case  3 : strcpy( suCasher.szDeal_Type      , (char *)token); break;
	case  4 : strcpy( suCasher.szMac_No         , (char *)token); break;
	case  5 : strcpy( suCasher.szTenant_Cd      , (char *)token); break;
	case  6 : strcpy( suCasher.szPos_No         , (char *)token); break;
	case  7 : strcpy( suCasher.szClose_Date     , (char *)token); break;
	case  8 : strcpy( suCasher.szCasher_Id      , (char *)token); break;
	case  9 : strcpy( suCasher.szDeal_Time      , (char *)token); break;
	case 10 : strcpy( suCasher.szDevicever_Id   , (char *)token); 
			  	if( atoi( suCasher.szDevicever_Id ) >= 20022 )
			  	{
			  		nVersion = VER_NEW;
				}
				else
				{
					nVersion = VER_OLD;
				}
		      break;
	case 11 : strcpy( suCasher.szAcudiv         , (char *)token); break;
	case 12 : strcpy( suCasher.szInmon_Flag     , (char *)token); break;
	case 13 : strcpy( suCasher.szCash_100000_Cnt, (char *)token); break;
	case 14 : strcpy( suCasher.szCash_100000_Amt, (char *)token); break;
	case 15 : strcpy( suCasher.szCash_50000_Cnt , (char *)token); break;
	case 16 : strcpy( suCasher.szCash_50000_Amt , (char *)token); break;
	case 17 : strcpy( suCasher.szCash_10000_Cnt , (char *)token); break;
	case 18 : strcpy( suCasher.szCash_10000_Amt , (char *)token); break;
	case 19 : strcpy( suCasher.szCash_5000_Cnt  , (char *)token); break;
	case 20 : strcpy( suCasher.szCash_5000_Amt  , (char *)token); break;
	case 21 : strcpy( suCasher.szCash_1000_Cnt  , (char *)token); break;
	case 22 : strcpy( suCasher.szCash_1000_Amt  , (char *)token); break;
	case 23 : strcpy( suCasher.szCash_500_Cnt   , (char *)token); break;
	case 24 : strcpy( suCasher.szCash_500_Amt   , (char *)token); break;
	case 25 : strcpy( suCasher.szCash_100_Cnt   , (char *)token); break;
	case 26 : strcpy( suCasher.szCash_100_Amt   , (char *)token); break;
	case 27 : strcpy( suCasher.szCash_50_Cnt    , (char *)token); break;
	case 28 : strcpy( suCasher.szCash_50_Amt    , (char *)token); break;
	case 29 : strcpy( suCasher.szCash_10_Cnt	, (char *)token); break;
	case 30 : strcpy( suCasher.szCash_10_Amt    , (char *)token); break;
	case 31 : strcpy( suCasher.szCheck_100_Cnt  , (char *)token); break;
	case 32 : strcpy( suCasher.szCheck_100_Amt  , (char *)token); break;
	case 33 : strcpy( suCasher.szCheck_50_Cnt   , (char *)token); break;
	case 34 : strcpy( suCasher.szCheck_50_Amt   , (char *)token); break;
	case 35 : strcpy( suCasher.szCheck_30_Cnt   , (char *)token); break;
	case 36 : strcpy( suCasher.szCheck_30_Amt   , (char *)token); break;
	case 37 : strcpy( suCasher.szCheck_10_Cnt   , (char *)token); break;
	case 38 : strcpy( suCasher.szCheck_10_Amt   , (char *)token); break;
	case 39 : strcpy( suCasher.szCheck_Etc_Amt  , (char *)token); break;
	case 40 : strcpy( suCasher.szBox_Cash_Amt   , (char *)token); break;
	case 41 : strcpy( suCasher.szBox_Coin_Amt   , (char *)token); break;
	case 42 : strcpy( suCasher.szBox_Check_Amt  , (char *)token); break;
	case 43 : strcpy( suCasher.szBox_Incom_Amt  , (char *)token); break;
	case 44 : strcpy( suCasher.szBox_Outcom_Amt , (char *)token); break;
	case 45 : strcpy( suCasher.szBox_Ppcard_Amt , (char *)token); break;
	case 46 : strcpy( suCasher.szTick_Own_1000_cnt		 , (char *)token); break;
	case 47 : strcpy( suCasher.szTick_Own_2000_cnt		 , (char *)token); break;
	case 48 : strcpy( suCasher.szTick_Own_3000_cnt		 , (char *)token); break;
	case 49 : strcpy( suCasher.szTick_Own_5000_cnt		 , (char *)token); break;
	case 50 : strcpy( suCasher.szTick_Own_10000_cnt	     , (char *)token); break;
	case 51 : strcpy( suCasher.szTick_Own_30000_cnt	     , (char *)token); break;
	case 52 : strcpy( suCasher.szTick_Own_50000_cnt	     , (char *)token); break;
	case 53 : strcpy( suCasher.szTick_Own_70000_cnt	     , (char *)token); break;
	case 54 : strcpy( suCasher.szTick_Own_100000_cnt	 , (char *)token); break;
	case 55 : strcpy( suCasher.szTick_Own_300000_cnt	 , (char *)token); break;
	case 56 : strcpy( suCasher.szTick_Own_500000_cnt	 , (char *)token); break;
	case 57 : strcpy( suCasher.szTick_Own_1000000_cnt	 , (char *)token); break;
	case 58 : strcpy( suCasher.szTick_Etc_1000_cnt		 , (char *)token); break;
	case 59 : strcpy( suCasher.szTick_Etc_2000_cnt		 , (char *)token); break;
	case 60 : strcpy( suCasher.szTick_Etc_3000_cnt		 , (char *)token); break;
	case 61 : strcpy( suCasher.szTick_Etc_5000_cnt		 , (char *)token); break;
	case 62 : strcpy( suCasher.szTick_Etc_10000_cnt	     , (char *)token); break;
	case 63 : strcpy( suCasher.szTick_Etc_30000_cnt	     , (char *)token); break;
	case 64 : strcpy( suCasher.szTick_Etc_50000_cnt	     , (char *)token); break;
	case 65 : strcpy( suCasher.szTick_Etc_70000_cnt	     , (char *)token); break;
	case 66 : strcpy( suCasher.szTick_Etc_100000_cnt	 , (char *)token); break;
	case 67 : strcpy( suCasher.szTick_Etc_300000_cnt	 , (char *)token); break;
	case 68 : strcpy( suCasher.szTick_Etc_500000_cnt	 , (char *)token); break;
	case 69 : strcpy( suCasher.szTick_Etc_1000000_cnt	 , (char *)token); break;
 * </pre>
 * 
 * @author 박상철
 * @version 1.0
 * @see
 */
public class CasherTRVO
{
	private String szJijum_Cd;
	private String szDeal_Date;
	private String szDeal_No;
	private String szDeal_Type;
	private String szMac_No;
	private String szTenant_Cd;
	private String szPos_No;
	private String szClose_Date;
	private String szCasher_Id;
	private String szDeal_Time;
	private String szDevicever_Id;
	private String szAcudiv;
	private String szInmon_Flag;
	private String szCash_100000_Cnt;
	private String szCash_100000_Amt;
	private String szCash_50000_Cnt;
	private String szCash_50000_Amt;
	private String szCash_10000_Cnt;
	private String szCash_10000_Amt;
	private String szCash_5000_Cnt;
	private String szCash_5000_Amt;
	private String szCash_1000_Cnt;
	private String szCash_1000_Amt;
	private String szCash_500_Cnt;
	private String szCash_500_Amt;
	private String szCash_100_Cnt;
	private String szCash_100_Amt;
	private String szCash_50_Cnt;
	private String szCash_50_Amt;
	private String szCash_10_Cnt;
	private String szCash_10_Amt;
	private String szCheck_100_Cnt;
	private String szCheck_100_Amt;
	private String szCheck_50_Cnt;
	private String szCheck_50_Amt;
	private String szCheck_30_Cnt;
	private String szCheck_30_Amt;
	private String szCheck_10_Cnt;
	private String szCheck_10_Amt;
	private String szCheck_Etc_Amt;
	private String szBox_Cash_Amt;
	private String szBox_Coin_Amt;
	private String szBox_Check_Amt;
	private String szBox_Incom_Amt;
	private String szBox_Outcom_Amt;
	private String szBox_Ppcard_Amt;
	private String szTick_Own_1000_cnt;
	private String szTick_Own_2000_cnt;
	private String szTick_Own_3000_cnt;
	private String szTick_Own_5000_cnt;
	private String szTick_Own_10000_cnt;
	private String szTick_Own_30000_cnt;
	private String szTick_Own_50000_cnt;
	private String szTick_Own_70000_cnt;
	private String szTick_Own_100000_cnt;
	private String szTick_Own_300000_cnt;
	private String szTick_Own_500000_cnt;
	private String szTick_Own_1000000_cnt;
	private String szTick_Etc_1000_cnt;
	private String szTick_Etc_2000_cnt;
	private String szTick_Etc_3000_cnt;
	private String szTick_Etc_5000_cnt;
	private String szTick_Etc_10000_cnt;
	private String szTick_Etc_30000_cnt;
	private String szTick_Etc_50000_cnt;
	private String szTick_Etc_70000_cnt;
	private String szTick_Etc_100000_cnt;
	private String szTick_Etc_300000_cnt;
	private String szTick_Etc_500000_cnt;
	private String szTick_Etc_1000000_cnt;
	/**
	 * @return the szJijum_Cd
	 */
	public String getSzJijum_Cd() {
		return szJijum_Cd;
	}
	/**
	 * @param szJijum_Cd the szJijum_Cd to set
	 */
	public void setSzJijum_Cd(String szJijum_Cd) {
		this.szJijum_Cd = szJijum_Cd;
	}
	/**
	 * @return the szDeal_Date
	 */
	public String getSzDeal_Date() {
		return szDeal_Date;
	}
	/**
	 * @param szDeal_Date the szDeal_Date to set
	 */
	public void setSzDeal_Date(String szDeal_Date) {
		this.szDeal_Date = szDeal_Date;
	}
	/**
	 * @return the szDeal_No
	 */
	public String getSzDeal_No() {
		return szDeal_No;
	}
	/**
	 * @param szDeal_No the szDeal_No to set
	 */
	public void setSzDeal_No(String szDeal_No) {
		this.szDeal_No = szDeal_No;
	}
	/**
	 * @return the szDeal_Type
	 */
	public String getSzDeal_Type() {
		return szDeal_Type;
	}
	/**
	 * @param szDeal_Type the szDeal_Type to set
	 */
	public void setSzDeal_Type(String szDeal_Type) {
		this.szDeal_Type = szDeal_Type;
	}
	/**
	 * @return the szMac_No
	 */
	public String getSzMac_No() {
		return szMac_No;
	}
	/**
	 * @param szMac_No the szMac_No to set
	 */
	public void setSzMac_No(String szMac_No) {
		this.szMac_No = szMac_No;
	}
	/**
	 * @return the szTenant_Cd
	 */
	public String getSzTenant_Cd() {
		return szTenant_Cd;
	}
	/**
	 * @param szTenant_Cd the szTenant_Cd to set
	 */
	public void setSzTenant_Cd(String szTenant_Cd) {
		this.szTenant_Cd = szTenant_Cd;
	}
	/**
	 * @return the szPos_No
	 */
	public String getSzPos_No() {
		return szPos_No;
	}
	/**
	 * @param szPos_No the szPos_No to set
	 */
	public void setSzPos_No(String szPos_No) {
		this.szPos_No = szPos_No;
	}
	/**
	 * @return the szClose_Date
	 */
	public String getSzClose_Date() {
		return szClose_Date;
	}
	/**
	 * @param szClose_Date the szClose_Date to set
	 */
	public void setSzClose_Date(String szClose_Date) {
		this.szClose_Date = szClose_Date;
	}
	/**
	 * @return the szCasher_Id
	 */
	public String getSzCasher_Id() {
		return szCasher_Id;
	}
	/**
	 * @param szCasher_Id the szCasher_Id to set
	 */
	public void setSzCasher_Id(String szCasher_Id) {
		this.szCasher_Id = szCasher_Id;
	}
	/**
	 * @return the szDeal_Time
	 */
	public String getSzDeal_Time() {
		return szDeal_Time;
	}
	/**
	 * @param szDeal_Time the szDeal_Time to set
	 */
	public void setSzDeal_Time(String szDeal_Time) {
		this.szDeal_Time = szDeal_Time;
	}
	/**
	 * @return the szDevicever_Id
	 */
	public String getSzDevicever_Id() {
		return szDevicever_Id;
	}
	/**
	 * @param szDevicever_Id the szDevicever_Id to set
	 */
	public void setSzDevicever_Id(String szDevicever_Id) {
		this.szDevicever_Id = szDevicever_Id;
	}
	/**
	 * @return the szAcudiv
	 */
	public String getSzAcudiv() {
		return szAcudiv;
	}
	/**
	 * @param szAcudiv the szAcudiv to set
	 */
	public void setSzAcudiv(String szAcudiv) {
		this.szAcudiv = szAcudiv;
	}
	/**
	 * @return the szInmon_Flag
	 */
	public String getSzInmon_Flag() {
		return szInmon_Flag;
	}
	/**
	 * @param szInmon_Flag the szInmon_Flag to set
	 */
	public void setSzInmon_Flag(String szInmon_Flag) {
		this.szInmon_Flag = szInmon_Flag;
	}
	/**
	 * @return the szCash_100000_Cnt
	 */
	public String getSzCash_100000_Cnt() {
		return szCash_100000_Cnt;
	}
	/**
	 * @param szCash_100000_Cnt the szCash_100000_Cnt to set
	 */
	public void setSzCash_100000_Cnt(String szCash_100000_Cnt) {
		this.szCash_100000_Cnt = szCash_100000_Cnt;
	}
	/**
	 * @return the szCash_100000_Amt
	 */
	public String getSzCash_100000_Amt() {
		return szCash_100000_Amt;
	}
	/**
	 * @param szCash_100000_Amt the szCash_100000_Amt to set
	 */
	public void setSzCash_100000_Amt(String szCash_100000_Amt) {
		this.szCash_100000_Amt = szCash_100000_Amt;
	}
	/**
	 * @return the szCash_50000_Cnt
	 */
	public String getSzCash_50000_Cnt() {
		return szCash_50000_Cnt;
	}
	/**
	 * @param szCash_50000_Cnt the szCash_50000_Cnt to set
	 */
	public void setSzCash_50000_Cnt(String szCash_50000_Cnt) {
		this.szCash_50000_Cnt = szCash_50000_Cnt;
	}
	/**
	 * @return the szCash_50000_Amt
	 */
	public String getSzCash_50000_Amt() {
		return szCash_50000_Amt;
	}
	/**
	 * @param szCash_50000_Amt the szCash_50000_Amt to set
	 */
	public void setSzCash_50000_Amt(String szCash_50000_Amt) {
		this.szCash_50000_Amt = szCash_50000_Amt;
	}
	/**
	 * @return the szCash_10000_Cnt
	 */
	public String getSzCash_10000_Cnt() {
		return szCash_10000_Cnt;
	}
	/**
	 * @param szCash_10000_Cnt the szCash_10000_Cnt to set
	 */
	public void setSzCash_10000_Cnt(String szCash_10000_Cnt) {
		this.szCash_10000_Cnt = szCash_10000_Cnt;
	}
	/**
	 * @return the szCash_10000_Amt
	 */
	public String getSzCash_10000_Amt() {
		return szCash_10000_Amt;
	}
	/**
	 * @param szCash_10000_Amt the szCash_10000_Amt to set
	 */
	public void setSzCash_10000_Amt(String szCash_10000_Amt) {
		this.szCash_10000_Amt = szCash_10000_Amt;
	}
	/**
	 * @return the szCash_5000_Cnt
	 */
	public String getSzCash_5000_Cnt() {
		return szCash_5000_Cnt;
	}
	/**
	 * @param szCash_5000_Cnt the szCash_5000_Cnt to set
	 */
	public void setSzCash_5000_Cnt(String szCash_5000_Cnt) {
		this.szCash_5000_Cnt = szCash_5000_Cnt;
	}
	/**
	 * @return the szCash_5000_Amt
	 */
	public String getSzCash_5000_Amt() {
		return szCash_5000_Amt;
	}
	/**
	 * @param szCash_5000_Amt the szCash_5000_Amt to set
	 */
	public void setSzCash_5000_Amt(String szCash_5000_Amt) {
		this.szCash_5000_Amt = szCash_5000_Amt;
	}
	/**
	 * @return the szCash_1000_Cnt
	 */
	public String getSzCash_1000_Cnt() {
		return szCash_1000_Cnt;
	}
	/**
	 * @param szCash_1000_Cnt the szCash_1000_Cnt to set
	 */
	public void setSzCash_1000_Cnt(String szCash_1000_Cnt) {
		this.szCash_1000_Cnt = szCash_1000_Cnt;
	}
	/**
	 * @return the szCash_1000_Amt
	 */
	public String getSzCash_1000_Amt() {
		return szCash_1000_Amt;
	}
	/**
	 * @param szCash_1000_Amt the szCash_1000_Amt to set
	 */
	public void setSzCash_1000_Amt(String szCash_1000_Amt) {
		this.szCash_1000_Amt = szCash_1000_Amt;
	}
	/**
	 * @return the szCash_500_Cnt
	 */
	public String getSzCash_500_Cnt() {
		return szCash_500_Cnt;
	}
	/**
	 * @param szCash_500_Cnt the szCash_500_Cnt to set
	 */
	public void setSzCash_500_Cnt(String szCash_500_Cnt) {
		this.szCash_500_Cnt = szCash_500_Cnt;
	}
	/**
	 * @return the szCash_500_Amt
	 */
	public String getSzCash_500_Amt() {
		return szCash_500_Amt;
	}
	/**
	 * @param szCash_500_Amt the szCash_500_Amt to set
	 */
	public void setSzCash_500_Amt(String szCash_500_Amt) {
		this.szCash_500_Amt = szCash_500_Amt;
	}
	/**
	 * @return the szCash_100_Cnt
	 */
	public String getSzCash_100_Cnt() {
		return szCash_100_Cnt;
	}
	/**
	 * @param szCash_100_Cnt the szCash_100_Cnt to set
	 */
	public void setSzCash_100_Cnt(String szCash_100_Cnt) {
		this.szCash_100_Cnt = szCash_100_Cnt;
	}
	/**
	 * @return the szCash_100_Amt
	 */
	public String getSzCash_100_Amt() {
		return szCash_100_Amt;
	}
	/**
	 * @param szCash_100_Amt the szCash_100_Amt to set
	 */
	public void setSzCash_100_Amt(String szCash_100_Amt) {
		this.szCash_100_Amt = szCash_100_Amt;
	}
	/**
	 * @return the szCash_50_Cnt
	 */
	public String getSzCash_50_Cnt() {
		return szCash_50_Cnt;
	}
	/**
	 * @param szCash_50_Cnt the szCash_50_Cnt to set
	 */
	public void setSzCash_50_Cnt(String szCash_50_Cnt) {
		this.szCash_50_Cnt = szCash_50_Cnt;
	}
	/**
	 * @return the szCash_50_Amt
	 */
	public String getSzCash_50_Amt() {
		return szCash_50_Amt;
	}
	/**
	 * @param szCash_50_Amt the szCash_50_Amt to set
	 */
	public void setSzCash_50_Amt(String szCash_50_Amt) {
		this.szCash_50_Amt = szCash_50_Amt;
	}
	/**
	 * @return the szCash_10_Cnt
	 */
	public String getSzCash_10_Cnt() {
		return szCash_10_Cnt;
	}
	/**
	 * @param szCash_10_Cnt the szCash_10_Cnt to set
	 */
	public void setSzCash_10_Cnt(String szCash_10_Cnt) {
		this.szCash_10_Cnt = szCash_10_Cnt;
	}
	/**
	 * @return the szCash_10_Amt
	 */
	public String getSzCash_10_Amt() {
		return szCash_10_Amt;
	}
	/**
	 * @param szCash_10_Amt the szCash_10_Amt to set
	 */
	public void setSzCash_10_Amt(String szCash_10_Amt) {
		this.szCash_10_Amt = szCash_10_Amt;
	}
	/**
	 * @return the szCheck_100_Cnt
	 */
	public String getSzCheck_100_Cnt() {
		return szCheck_100_Cnt;
	}
	/**
	 * @param szCheck_100_Cnt the szCheck_100_Cnt to set
	 */
	public void setSzCheck_100_Cnt(String szCheck_100_Cnt) {
		this.szCheck_100_Cnt = szCheck_100_Cnt;
	}
	/**
	 * @return the szCheck_100_Amt
	 */
	public String getSzCheck_100_Amt() {
		return szCheck_100_Amt;
	}
	/**
	 * @param szCheck_100_Amt the szCheck_100_Amt to set
	 */
	public void setSzCheck_100_Amt(String szCheck_100_Amt) {
		this.szCheck_100_Amt = szCheck_100_Amt;
	}
	/**
	 * @return the szCheck_50_Cnt
	 */
	public String getSzCheck_50_Cnt() {
		return szCheck_50_Cnt;
	}
	/**
	 * @param szCheck_50_Cnt the szCheck_50_Cnt to set
	 */
	public void setSzCheck_50_Cnt(String szCheck_50_Cnt) {
		this.szCheck_50_Cnt = szCheck_50_Cnt;
	}
	/**
	 * @return the szCheck_50_Amt
	 */
	public String getSzCheck_50_Amt() {
		return szCheck_50_Amt;
	}
	/**
	 * @param szCheck_50_Amt the szCheck_50_Amt to set
	 */
	public void setSzCheck_50_Amt(String szCheck_50_Amt) {
		this.szCheck_50_Amt = szCheck_50_Amt;
	}
	/**
	 * @return the szCheck_30_Cnt
	 */
	public String getSzCheck_30_Cnt() {
		return szCheck_30_Cnt;
	}
	/**
	 * @param szCheck_30_Cnt the szCheck_30_Cnt to set
	 */
	public void setSzCheck_30_Cnt(String szCheck_30_Cnt) {
		this.szCheck_30_Cnt = szCheck_30_Cnt;
	}
	/**
	 * @return the szCheck_30_Amt
	 */
	public String getSzCheck_30_Amt() {
		return szCheck_30_Amt;
	}
	/**
	 * @param szCheck_30_Amt the szCheck_30_Amt to set
	 */
	public void setSzCheck_30_Amt(String szCheck_30_Amt) {
		this.szCheck_30_Amt = szCheck_30_Amt;
	}
	/**
	 * @return the szCheck_10_Cnt
	 */
	public String getSzCheck_10_Cnt() {
		return szCheck_10_Cnt;
	}
	/**
	 * @param szCheck_10_Cnt the szCheck_10_Cnt to set
	 */
	public void setSzCheck_10_Cnt(String szCheck_10_Cnt) {
		this.szCheck_10_Cnt = szCheck_10_Cnt;
	}
	/**
	 * @return the szCheck_10_Amt
	 */
	public String getSzCheck_10_Amt() {
		return szCheck_10_Amt;
	}
	/**
	 * @param szCheck_10_Amt the szCheck_10_Amt to set
	 */
	public void setSzCheck_10_Amt(String szCheck_10_Amt) {
		this.szCheck_10_Amt = szCheck_10_Amt;
	}
	/**
	 * @return the szCheck_Etc_Amt
	 */
	public String getSzCheck_Etc_Amt() {
		return szCheck_Etc_Amt;
	}
	/**
	 * @param szCheck_Etc_Amt the szCheck_Etc_Amt to set
	 */
	public void setSzCheck_Etc_Amt(String szCheck_Etc_Amt) {
		this.szCheck_Etc_Amt = szCheck_Etc_Amt;
	}
	/**
	 * @return the szBox_Cash_Amt
	 */
	public String getSzBox_Cash_Amt() {
		return szBox_Cash_Amt;
	}
	/**
	 * @param szBox_Cash_Amt the szBox_Cash_Amt to set
	 */
	public void setSzBox_Cash_Amt(String szBox_Cash_Amt) {
		this.szBox_Cash_Amt = szBox_Cash_Amt;
	}
	/**
	 * @return the szBox_Coin_Amt
	 */
	public String getSzBox_Coin_Amt() {
		return szBox_Coin_Amt;
	}
	/**
	 * @param szBox_Coin_Amt the szBox_Coin_Amt to set
	 */
	public void setSzBox_Coin_Amt(String szBox_Coin_Amt) {
		this.szBox_Coin_Amt = szBox_Coin_Amt;
	}
	/**
	 * @return the szBox_Check_Amt
	 */
	public String getSzBox_Check_Amt() {
		return szBox_Check_Amt;
	}
	/**
	 * @param szBox_Check_Amt the szBox_Check_Amt to set
	 */
	public void setSzBox_Check_Amt(String szBox_Check_Amt) {
		this.szBox_Check_Amt = szBox_Check_Amt;
	}
	/**
	 * @return the szBox_Incom_Amt
	 */
	public String getSzBox_Incom_Amt() {
		return szBox_Incom_Amt;
	}
	/**
	 * @param szBox_Incom_Amt the szBox_Incom_Amt to set
	 */
	public void setSzBox_Incom_Amt(String szBox_Incom_Amt) {
		this.szBox_Incom_Amt = szBox_Incom_Amt;
	}
	/**
	 * @return the szBox_Outcom_Amt
	 */
	public String getSzBox_Outcom_Amt() {
		return szBox_Outcom_Amt;
	}
	/**
	 * @param szBox_Outcom_Amt the szBox_Outcom_Amt to set
	 */
	public void setSzBox_Outcom_Amt(String szBox_Outcom_Amt) {
		this.szBox_Outcom_Amt = szBox_Outcom_Amt;
	}
	/**
	 * @return the szBox_Ppcard_Amt
	 */
	public String getSzBox_Ppcard_Amt() {
		return szBox_Ppcard_Amt;
	}
	/**
	 * @param szBox_Ppcard_Amt the szBox_Ppcard_Amt to set
	 */
	public void setSzBox_Ppcard_Amt(String szBox_Ppcard_Amt) {
		this.szBox_Ppcard_Amt = szBox_Ppcard_Amt;
	}
	/**
	 * @return the szTick_Own_1000_cnt
	 */
	public String getSzTick_Own_1000_cnt() {
		return szTick_Own_1000_cnt;
	}
	/**
	 * @param szTick_Own_1000_cnt the szTick_Own_1000_cnt to set
	 */
	public void setSzTick_Own_1000_cnt(String szTick_Own_1000_cnt) {
		this.szTick_Own_1000_cnt = szTick_Own_1000_cnt;
	}
	/**
	 * @return the szTick_Own_2000_cnt
	 */
	public String getSzTick_Own_2000_cnt() {
		return szTick_Own_2000_cnt;
	}
	/**
	 * @param szTick_Own_2000_cnt the szTick_Own_2000_cnt to set
	 */
	public void setSzTick_Own_2000_cnt(String szTick_Own_2000_cnt) {
		this.szTick_Own_2000_cnt = szTick_Own_2000_cnt;
	}
	/**
	 * @return the szTick_Own_3000_cnt
	 */
	public String getSzTick_Own_3000_cnt() {
		return szTick_Own_3000_cnt;
	}
	/**
	 * @param szTick_Own_3000_cnt the szTick_Own_3000_cnt to set
	 */
	public void setSzTick_Own_3000_cnt(String szTick_Own_3000_cnt) {
		this.szTick_Own_3000_cnt = szTick_Own_3000_cnt;
	}
	/**
	 * @return the szTick_Own_5000_cnt
	 */
	public String getSzTick_Own_5000_cnt() {
		return szTick_Own_5000_cnt;
	}
	/**
	 * @param szTick_Own_5000_cnt the szTick_Own_5000_cnt to set
	 */
	public void setSzTick_Own_5000_cnt(String szTick_Own_5000_cnt) {
		this.szTick_Own_5000_cnt = szTick_Own_5000_cnt;
	}
	/**
	 * @return the szTick_Own_10000_cnt
	 */
	public String getSzTick_Own_10000_cnt() {
		return szTick_Own_10000_cnt;
	}
	/**
	 * @param szTick_Own_10000_cnt the szTick_Own_10000_cnt to set
	 */
	public void setSzTick_Own_10000_cnt(String szTick_Own_10000_cnt) {
		this.szTick_Own_10000_cnt = szTick_Own_10000_cnt;
	}
	/**
	 * @return the szTick_Own_30000_cnt
	 */
	public String getSzTick_Own_30000_cnt() {
		return szTick_Own_30000_cnt;
	}
	/**
	 * @param szTick_Own_30000_cnt the szTick_Own_30000_cnt to set
	 */
	public void setSzTick_Own_30000_cnt(String szTick_Own_30000_cnt) {
		this.szTick_Own_30000_cnt = szTick_Own_30000_cnt;
	}
	/**
	 * @return the szTick_Own_50000_cnt
	 */
	public String getSzTick_Own_50000_cnt() {
		return szTick_Own_50000_cnt;
	}
	/**
	 * @param szTick_Own_50000_cnt the szTick_Own_50000_cnt to set
	 */
	public void setSzTick_Own_50000_cnt(String szTick_Own_50000_cnt) {
		this.szTick_Own_50000_cnt = szTick_Own_50000_cnt;
	}
	/**
	 * @return the szTick_Own_70000_cnt
	 */
	public String getSzTick_Own_70000_cnt() {
		return szTick_Own_70000_cnt;
	}
	/**
	 * @param szTick_Own_70000_cnt the szTick_Own_70000_cnt to set
	 */
	public void setSzTick_Own_70000_cnt(String szTick_Own_70000_cnt) {
		this.szTick_Own_70000_cnt = szTick_Own_70000_cnt;
	}
	/**
	 * @return the szTick_Own_100000_cnt
	 */
	public String getSzTick_Own_100000_cnt() {
		return szTick_Own_100000_cnt;
	}
	/**
	 * @param szTick_Own_100000_cnt the szTick_Own_100000_cnt to set
	 */
	public void setSzTick_Own_100000_cnt(String szTick_Own_100000_cnt) {
		this.szTick_Own_100000_cnt = szTick_Own_100000_cnt;
	}
	/**
	 * @return the szTick_Own_300000_cnt
	 */
	public String getSzTick_Own_300000_cnt() {
		return szTick_Own_300000_cnt;
	}
	/**
	 * @param szTick_Own_300000_cnt the szTick_Own_300000_cnt to set
	 */
	public void setSzTick_Own_300000_cnt(String szTick_Own_300000_cnt) {
		this.szTick_Own_300000_cnt = szTick_Own_300000_cnt;
	}
	/**
	 * @return the szTick_Own_500000_cnt
	 */
	public String getSzTick_Own_500000_cnt() {
		return szTick_Own_500000_cnt;
	}
	/**
	 * @param szTick_Own_500000_cnt the szTick_Own_500000_cnt to set
	 */
	public void setSzTick_Own_500000_cnt(String szTick_Own_500000_cnt) {
		this.szTick_Own_500000_cnt = szTick_Own_500000_cnt;
	}
	/**
	 * @return the szTick_Own_1000000_cnt
	 */
	public String getSzTick_Own_1000000_cnt() {
		return szTick_Own_1000000_cnt;
	}
	/**
	 * @param szTick_Own_1000000_cnt the szTick_Own_1000000_cnt to set
	 */
	public void setSzTick_Own_1000000_cnt(String szTick_Own_1000000_cnt) {
		this.szTick_Own_1000000_cnt = szTick_Own_1000000_cnt;
	}
	/**
	 * @return the szTick_Etc_1000_cnt
	 */
	public String getSzTick_Etc_1000_cnt() {
		return szTick_Etc_1000_cnt;
	}
	/**
	 * @param szTick_Etc_1000_cnt the szTick_Etc_1000_cnt to set
	 */
	public void setSzTick_Etc_1000_cnt(String szTick_Etc_1000_cnt) {
		this.szTick_Etc_1000_cnt = szTick_Etc_1000_cnt;
	}
	/**
	 * @return the szTick_Etc_2000_cnt
	 */
	public String getSzTick_Etc_2000_cnt() {
		return szTick_Etc_2000_cnt;
	}
	/**
	 * @param szTick_Etc_2000_cnt the szTick_Etc_2000_cnt to set
	 */
	public void setSzTick_Etc_2000_cnt(String szTick_Etc_2000_cnt) {
		this.szTick_Etc_2000_cnt = szTick_Etc_2000_cnt;
	}
	/**
	 * @return the szTick_Etc_3000_cnt
	 */
	public String getSzTick_Etc_3000_cnt() {
		return szTick_Etc_3000_cnt;
	}
	/**
	 * @param szTick_Etc_3000_cnt the szTick_Etc_3000_cnt to set
	 */
	public void setSzTick_Etc_3000_cnt(String szTick_Etc_3000_cnt) {
		this.szTick_Etc_3000_cnt = szTick_Etc_3000_cnt;
	}
	/**
	 * @return the szTick_Etc_5000_cnt
	 */
	public String getSzTick_Etc_5000_cnt() {
		return szTick_Etc_5000_cnt;
	}
	/**
	 * @param szTick_Etc_5000_cnt the szTick_Etc_5000_cnt to set
	 */
	public void setSzTick_Etc_5000_cnt(String szTick_Etc_5000_cnt) {
		this.szTick_Etc_5000_cnt = szTick_Etc_5000_cnt;
	}
	/**
	 * @return the szTick_Etc_10000_cnt
	 */
	public String getSzTick_Etc_10000_cnt() {
		return szTick_Etc_10000_cnt;
	}
	/**
	 * @param szTick_Etc_10000_cnt the szTick_Etc_10000_cnt to set
	 */
	public void setSzTick_Etc_10000_cnt(String szTick_Etc_10000_cnt) {
		this.szTick_Etc_10000_cnt = szTick_Etc_10000_cnt;
	}
	/**
	 * @return the szTick_Etc_30000_cnt
	 */
	public String getSzTick_Etc_30000_cnt() {
		return szTick_Etc_30000_cnt;
	}
	/**
	 * @param szTick_Etc_30000_cnt the szTick_Etc_30000_cnt to set
	 */
	public void setSzTick_Etc_30000_cnt(String szTick_Etc_30000_cnt) {
		this.szTick_Etc_30000_cnt = szTick_Etc_30000_cnt;
	}
	/**
	 * @return the szTick_Etc_50000_cnt
	 */
	public String getSzTick_Etc_50000_cnt() {
		return szTick_Etc_50000_cnt;
	}
	/**
	 * @param szTick_Etc_50000_cnt the szTick_Etc_50000_cnt to set
	 */
	public void setSzTick_Etc_50000_cnt(String szTick_Etc_50000_cnt) {
		this.szTick_Etc_50000_cnt = szTick_Etc_50000_cnt;
	}
	/**
	 * @return the szTick_Etc_70000_cnt
	 */
	public String getSzTick_Etc_70000_cnt() {
		return szTick_Etc_70000_cnt;
	}
	/**
	 * @param szTick_Etc_70000_cnt the szTick_Etc_70000_cnt to set
	 */
	public void setSzTick_Etc_70000_cnt(String szTick_Etc_70000_cnt) {
		this.szTick_Etc_70000_cnt = szTick_Etc_70000_cnt;
	}
	/**
	 * @return the szTick_Etc_100000_cnt
	 */
	public String getSzTick_Etc_100000_cnt() {
		return szTick_Etc_100000_cnt;
	}
	/**
	 * @param szTick_Etc_100000_cnt the szTick_Etc_100000_cnt to set
	 */
	public void setSzTick_Etc_100000_cnt(String szTick_Etc_100000_cnt) {
		this.szTick_Etc_100000_cnt = szTick_Etc_100000_cnt;
	}
	/**
	 * @return the szTick_Etc_300000_cnt
	 */
	public String getSzTick_Etc_300000_cnt() {
		return szTick_Etc_300000_cnt;
	}
	/**
	 * @param szTick_Etc_300000_cnt the szTick_Etc_300000_cnt to set
	 */
	public void setSzTick_Etc_300000_cnt(String szTick_Etc_300000_cnt) {
		this.szTick_Etc_300000_cnt = szTick_Etc_300000_cnt;
	}
	/**
	 * @return the szTick_Etc_500000_cnt
	 */
	public String getSzTick_Etc_500000_cnt() {
		return szTick_Etc_500000_cnt;
	}
	/**
	 * @param szTick_Etc_500000_cnt the szTick_Etc_500000_cnt to set
	 */
	public void setSzTick_Etc_500000_cnt(String szTick_Etc_500000_cnt) {
		this.szTick_Etc_500000_cnt = szTick_Etc_500000_cnt;
	}
	/**
	 * @return the szTick_Etc_1000000_cnt
	 */
	public String getSzTick_Etc_1000000_cnt() {
		return szTick_Etc_1000000_cnt;
	}
	/**
	 * @param szTick_Etc_1000000_cnt the szTick_Etc_1000000_cnt to set
	 */
	public void setSzTick_Etc_1000000_cnt(String szTick_Etc_1000000_cnt) {
		this.szTick_Etc_1000000_cnt = szTick_Etc_1000000_cnt;
	}
	
}
