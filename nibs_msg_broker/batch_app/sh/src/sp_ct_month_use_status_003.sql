-- �ſ� �귣�����ޱ�� ���̿���Ȳ INSERT
-- 5�� : ������ �� �Ǽ� ���� ( û���뿪��� 0 ���� ���� )
-- 20�� : û���뿪�� ���� ( �⺻�뿪�� - ���Ƽ �ݾ� )

/*
���� : ������� �ŷ��Ǽ� �� ������ ��������
���� : ������� ���̿���Ȳ���� ���ۿ� ������ INSERT
���� : �ſ� 5�� ����
���� : �� �� ���� ������� ��⺰ �ŷ��Ǽ� �� ���������踦 ���Ѵ�.
	1. �귣������
	2. �����(�ϳ��� ������('0000')���� ���赥���͸� ��Ƽ� 1�ุ INSERT)
�������̺� :
	T_CT_USE_STATUS_MONTHLY
	T_FN_IBK_BRAND_DSUM
	T_FN_NICE_MAC_ORG_DSUM
	T_CM_MAC
	T_CM_SITE
	T_CM_SITE_STATE
��� :
	����(20130903) TABLE ������ ���¶� ������ȣ(SEQ)�� �ּ�ó���Ѵ�.
*/ 
SET SERVEROUTPUT ON

BEGIN
BEGIN

-- 1. 003 �귣������ ���Ǽ� �� ������

insert into t_ct_use_status_monthly (
YEAR_MON, ORG_CD, JIJUM_CD, MAC_NO, SET_TYPE, SET_DATE, USE_DAY, MONTH_FEE, BOOTH_TYPE
, USE_CNT_TOTAL_WITHDRAW, USE_CNT_TOTAL_TRANSFER, USE_CNT_TOTAL_DEPOSIT	, USE_CNT_TOTAL_SUM
, USE_CNT_SAME_WITHDRAW	, USE_CNT_SAME_TRANSFER	, USE_CNT_SAME_DEPOSIT	, USE_CNT_SAME_SUM
, USE_CNT_DIFF_WITHDRAW	, USE_CNT_DIFF_TRANSFER	, USE_CNT_DIFF_DEPOSIT	, USE_CNT_DIFF_SUM
, BANKBOOK_UPDATE_CNT, EARNED_FEE_IBK, EARNED_FEE_VAN
, ORG_SEND_YN, INSERT_UID, INSERT_DATE, UPDATE_UID, UPDATE_DATE, JIJUM_NM /* , SEQ */
)
select 
  cnt.year_mon																							--YEAR_MON              
  ,nvl(SST.BRAND_org_cd,'003')                                                                                     --ORG_CD                
  ,nvl(SST.mng_jijum_cd,'0784')                                                                                     --JIJUM_CD              
  ,cnt.mac_no                                                                                           --MAC_NO                
  ,nvl(SST.set_type,'99')                                                                                         --SET_TYPE              
  ,SITE.open_date                                                                                       --SET_DATE              
  ,case when to_char(last_day(sysdate-30),'yyyymmdd')-site.open_date >= 31                               --USE_DAY               
        THEN  to_char(last_day(sysdate-30),'dd')                                                        
        ELSE TO_CHAR(to_char(last_day(sysdate-30),'yyyymmdd') - site.open_date)                         
  END USE_DAY                                                                                          
  ,SST.MONTH_FEE                                                                                        --MONTH_FEE              
  ,SST.booth_type                                                                                       --BOOTH_TYPE             
  ,nvl(kbTimeDealCnt,0) + nvl(kbOtDealCnt,0)                            -- ��������Ǽ�                  
      + nvl(otherTimeDealCnt,0) + nvl(otherOtDealCnt,0)                 -- Ÿ������Ǽ�       			--USE_CNT_TOTAL_WITHDRAW
  ,kbajTimeDealCnt + kbajOtDealCnt+kbatTimeDealCnt + kbatOtDealCnt      -- ������ü�Ǽ�                  
      +otheraTimeDealCnt+otheraOtDealCnt                                -- Ÿ����ü�Ǽ�                 --USE_CNT_TOTAL_TRANSFER 
  , nvl(CNT.DEPOCNT,0)                                                                                  --USE_CNT_TOTAL_DEPOSIT   
  , kbTimeDealCnt + kbOtDealCnt                                         -- ��������Ǽ�                   
      +otherTimeDealCnt+otherOtDealCnt                                  -- Ÿ������Ǽ�                   
      +kbajTimeDealCnt + kbajOtDealCnt+kbatTimeDealCnt + kbatOtDealCnt  -- ������ü�Ǽ�                   
      +otheraTimeDealCnt+otheraOtDealCnt                                -- Ÿ����ü�Ǽ�                   
      +nvl(CNT.DEPOCNT,0)                                                  -- �����ԱݰǼ�              --USE_CNT_TOTAL_SUM       
  , kbTimeDealCnt + kbOtDealCnt 									--��������Ǽ�                        --USE_CNT_SAME_WITHDRAW   
  , kbajTimeDealCnt + kbajOtDealCnt+kbatTimeDealCnt + kbatOtDealCnt --������ü�Ǽ�                        --USE_CNT_SAME_TRANSFER   
  , nvl(CNT.DEPOCNT,0) 												--�����ԱݰǼ�                        --USE_CNT_SAME_DEPOSIT    
  , kbTimeDealCnt + kbOtDealCnt                                         -- ��������Ǽ�                
    + kbajTimeDealCnt + kbajOtDealCnt+kbatTimeDealCnt + kbatOtDealCnt   -- ������ü�Ǽ�                
    + nvl(CNT.DEPOcnt,0)                                          --�����ѰǼ�     				      -- �����ԱݰǼ� --USE_CNT_SAME_SUM                
  , otherTimeDealCnt+otherOtDealCnt 								--Ÿ������Ǽ�     				    --USE_CNT_DIFF_WITHDRAW      
  , otheraTimeDealCnt+otheraOtDealCnt 								--Ÿ����ü�Ǽ�  			            --USE_CNT_DIFF_TRANSFER      
  , 0 																--Ÿ���ԱݰǼ�                        --USE_CNT_DIFF_DEPOSIT       
  , otherTimeDealCnt+otherOtDealCnt+ otheraTimeDealCnt+otheraOtDealCnt --Ÿ���ѰǼ�                       --USE_CNT_DIFF_SUM           
  , nvl(CNT.BOOKcnt,0) 												--���������Ǽ�                        --BANKBOOK_UPDATE_CNT        
  , kbTimeBankFee+kbOtBankFee+kbajTimeBankFee+kbajOtBankFee+kbatTimeBankFee+kbatOtBankFee --���������,  --EARNED_FEE_IBK              
    ,( (otherTimeCustFee + otherOtCustFee)                                                                           
        -(otherTimeBankFee + otherOtBankFee) )                                                                      
        + ( (otheraTimeCustFee + otheraOtCustFee)                                                                                     
        -(otheraTimeBankFee + otheraOtBankFee) )                                                        
        + (cashsTimeCustFee + cashsOtCustFee                                                            
        + cashsTimeBankFee + cashsOtBankFee) --Ÿ�������                                                 --EARNED_FEE_VAN  
    , '0' -- org_send_yn                                                                                --ORG_SEND_YN     
    , '1215003' --insert_uid                                                                            --INSERT_UID      
    , sysdate  -- insert_date                                                                           --INSERT_DATE     
    , '' --update_uid                                                                                   --UPDATE_UID      
    , sysdate  -- update_date                                                                                --UPDATE_DATE     
    , '' -- jijum_nm                                                                                    --JIJUM_NM        
    /*,''                                                                                                 --SEQ  */
from
    (
        select
          substr(dsum.deal_date,1,6) year_mon
          ,DSUM.mac_no
          ,sum(kb_time_deal_cnt) kbTimeDealCnt
          ,sum(kb_time_deal_amt) kbTimeDealAmt
          ,sum(kb_time_cust_fee) kbTimeCustFee
          ,-sum(kb_time_bank_fee) kbTimeBankFee
          ,sum(kb_ot_deal_cnt) kbOtDealCnt
          ,sum(kb_ot_deal_amt) kbOtDealAmt
          ,sum(kb_ot_cust_fee) kbOtCustFee
          ,-sum(kb_ot_bank_fee) kbOtBankFee
          ,sum(other_time_deal_cnt) otherTimeDealCnt
          ,sum(other_time_deal_amt) otherTimeDealAmt
          ,sum(other_time_cust_fee) otherTimeCustFee
          ,-sum(other_time_bank_fee) otherTimeBankFee
          ,sum(other_ot_deal_cnt) otherOtDealCnt
          ,sum(other_ot_deal_amt) otherOtDealAmt
          ,sum(other_ot_cust_fee) otherOtCustFee
          ,-sum(other_ot_bank_fee) otherOtBankFee
          ,sum(kbaj_time_deal_cnt) kbajTimeDealCnt
          ,sum(kbaj_time_deal_amt) kbajTimeDealAmt
          ,sum(kbaj_time_cust_fee) kbajTimeCustFee
          ,-sum(kbaj_time_bank_fee) kbajTimeBankFee
          ,sum(kbaj_ot_deal_cnt) kbajOtDealCnt
          ,sum(kbaj_ot_deal_amt) kbajOtDealAmt
          ,sum(kbaj_ot_cust_fee) kbajOtCustFee
          ,-sum(kbaj_ot_bank_fee) kbajOtBankFee
          ,sum(kbat_time_deal_cnt) kbatTimeDealCnt
          ,sum(kbat_time_deal_amt) kbatTimeDealAmt
          ,sum(kbat_time_cust_fee) kbatTimeCustFee
          ,-sum(kbat_time_bank_fee) kbatTimeBankFee
          ,sum(kbat_ot_deal_cnt) kbatOtDealCnt
          ,sum(kbat_ot_deal_amt) kbatOtDealAmt
          ,sum(kbat_ot_cust_fee) kbatOtCustFee
          ,-sum(kbat_ot_bank_fee) kbatOtBankFee
          ,sum(othera_time_deal_cnt) otheraTimeDealCnt
          ,sum(othera_time_deal_amt) otheraTimeDealAmt
          ,sum(othera_time_cust_fee) otheraTimeCustFee
          ,-sum(othera_time_bank_fee) otheraTimeBankFee
          ,sum(othera_ot_deal_cnt) otheraOtDealCnt
          ,sum(othera_ot_deal_amt) otheraOtDealAmt
          ,sum(othera_ot_cust_fee) otheraOtCustFee
          ,-sum(othera_ot_bank_fee) otheraOtBankFee
          ,sum(cashs_time_deal_cnt + kbcashs_time_deal_cnt) cashsTimeDealCnt
          ,sum(cashs_time_deal_amt + kbcashs_time_deal_amt) cashsTimeDealAmt
          ,sum(cashs_time_cust_fee + kbcashs_time_cust_fee) cashsTimeCustFee
          ,sum(cashs_time_bank_fee + kbcashs_time_bank_fee) cashsTimeBankFee
          ,sum(cashs_ot_deal_cnt + kbcashs_ot_deal_cnt) cashsOtDealCnt
          ,sum(cashs_ot_deal_amt + kbcashs_ot_deal_amt) cashsOtDealAmt
          ,sum(cashs_ot_cust_fee + kbcashs_ot_cust_fee) cashsOtCustFee
          ,sum(cashs_ot_bank_fee + kbcashs_ot_bank_fee) cashsOtBankFee
          ,sum(DEPO.time_deal_cnt+DEPO.ot_deal_cnt+DEPO.night_deal_cnt) DEPOcnt 
          ,sum(BOOK.time_deal_cnt+BOOK.ot_deal_cnt+BOOK.night_deal_cnt) BOOKcnt
      from t_fn_ibk_brand_dsum DSUM
          ,T_FN_NICE_MAC_ORG_DSUM DEPO
          ,T_FN_NICE_MAC_ORG_DSUM BOOK
      where DSUM.deal_date between TO_CHAR(SYSDATE-25,'YYYYMM')||'01' AND TO_CHAR(LAST_DAY(SYSDATE-25),'YYYYMMDD')
      and DSUM.mac_no in
        ( select mac_no from t_ct_nice_mac where org_Cd = '096' and jijum_cd = '9600' and join_cd = '003')
      AND DSUM.DEAL_DATE = DEPO.DEAL_DATE(+)
      AND DSUM.MAC_NO = DEPO.MAC_NO(+)
      AND DEPO.ORG_CD(+) = '003'
      AND DEPO.DEAL_TYPE(+) = '1'
      AND DEPO.DEAL_STATUS(+) = '0'
      AND DSUM.DEAL_DATE = BOOK.DEAL_DATE(+)
      AND DSUM.MAC_NO = BOOK.MAC_NO(+)
      AND BOOK.ORG_CD(+) = '003'
      AND BOOK.DEAL_TYPE(+) = '3'
      AND BOOK.DEAL_CLSS(+) = '1004'
      AND BOOK.DEAL_STATUS(+) = '0'
     group by substr(dsum.deal_date,1,6), DSUM.mac_no
    ) cnt
    , T_CM_MAC			MAC
    , T_CM_SITE_STATE 	SST
    , T_CM_SITE     	SITE
where
 cnt.mac_no = MAC.mac_no
 AND MAC.ORG_CD = '096' AND MAC.JIJUM_CD = '9600'
 AND MAC.MAC_MODEL <> '7000'					-- Ű����ũ ����
 AND MAC.ORG_CD = SITE.ORG_CD
 AND MAC.JIJUM_CD = SITE.JIJUM_CD
 AND MAC.SITE_CD = SITE.SITE_CD
 AND SITE.ORG_CD = SST.ORG_CD(+)
 AND SITE.JIJUM_CD = SST.JIJUM_CD(+)
 AND SITE.SITE_CD = SST.SITE_CD(+)
  and sst.org_Cd(+) = '096' and sst.jijum_cd(+) = '9600';


  COMMIT;
  DBMS_OUTPUT.PUT_LINE('�귣���� �ŷ��Ǽ���� �Ϸ�');
  EXCEPTION
    WHEN OTHERS
    THEN
         DBMS_OUTPUT.PUT_LINE('�귣���� �ŷ��Ǽ���� ����!');  
END;

BEGIN  
  
--2. 0BK ����� ������(0000) ���ϱ�
insert into t_ct_use_status_monthly (
YEAR_MON, ORG_CD, JIJUM_CD, MAC_NO, SET_TYPE, SET_DATE, USE_DAY, MONTH_FEE, BOOTH_TYPE
, USE_CNT_TOTAL_WITHDRAW, USE_CNT_TOTAL_TRANSFER, USE_CNT_TOTAL_DEPOSIT	, USE_CNT_TOTAL_SUM
, USE_CNT_SAME_WITHDRAW	, USE_CNT_SAME_TRANSFER	, USE_CNT_SAME_DEPOSIT	, USE_CNT_SAME_SUM
, USE_CNT_DIFF_WITHDRAW	, USE_CNT_DIFF_TRANSFER	, USE_CNT_DIFF_DEPOSIT	, USE_CNT_DIFF_SUM
, BANKBOOK_UPDATE_CNT, EARNED_FEE_IBK, EARNED_FEE_VAN
, ORG_SEND_YN, INSERT_UID, INSERT_DATE, UPDATE_UID, UPDATE_DATE, JIJUM_NM  /*, SEQ */
)
select 
  cnt.year_mon																			--YEAR_MON              
  ,'003'			                                                                                    --ORG_CD                
  ,'0000'			                                                                                    --JIJUM_CD              
  ,'0000'	                                                                                            --MAC_NO                
  ,'99'			                                                                                        --SET_TYPE              
  ,'20130801'                                                                                       --SET_DATE              
  , to_char(last_day(sysdate-25),'dd')                                                      --USE_DAY               
  ,'0' mONTH_FEE                                                                                        --MONTH_FEE              
  ,'2' booth_type                                                                                       --BOOTH_TYPE             
  ,sum(nvl(kbTimeDealCnt,0) + nvl(kbOtDealCnt,0)                            -- ��������Ǽ�                  
      + nvl(otherTimeDealCnt,0) + nvl(otherOtDealCnt,0))                 -- Ÿ������Ǽ�                  �����ѰǼ�                          --USE_CNT_TOTAL_WITHDRAW
      																
  ,sum(kbajTimeDealCnt + kbajOtDealCnt+kbatTimeDealCnt + kbatOtDealCnt      -- ������ü�Ǽ�                  
      +otheraTimeDealCnt+otheraOtDealCnt)                                -- Ÿ����ü�Ǽ�                  ��ü�ѰǼ�       	                --USE_CNT_TOTAL_TRANSFER 
      																
  , sum(nvl(CNT.DEPOCNT,0))                                         --�Ա��ѰǼ�                          --USE_CNT_TOTAL_DEPOSIT                                               
      																
  , sum(kbTimeDealCnt + kbOtDealCnt                                         -- ��������Ǽ�                   
      +otherTimeDealCnt+otherOtDealCnt                                  -- Ÿ������Ǽ�                   
      +kbajTimeDealCnt + kbajOtDealCnt+kbatTimeDealCnt + kbatOtDealCnt  -- ������ü�Ǽ�                   
      +otheraTimeDealCnt+otheraOtDealCnt                                -- Ÿ����ü�Ǽ�                   
      +nvl(CNT.DEPOCNT,0) )                                                 -- �����ԱݰǼ�                   ���հ�		                        --USE_CNT_TOTAL_SUM       
      																
  , sum(kbTimeDealCnt + kbOtDealCnt) 									--��������Ǽ�                        --USE_CNT_SAME_WITHDRAW   
  , sum(kbajTimeDealCnt + kbajOtDealCnt+kbatTimeDealCnt + kbatOtDealCnt) --������ü�Ǽ�                        --USE_CNT_SAME_TRANSFER   
  , sum(nvl(CNT.DEPOCNT,0)) 												--�����ԱݰǼ�                        --USE_CNT_SAME_DEPOSIT    
  , sum(kbTimeDealCnt + kbOtDealCnt                                         -- ��������Ǽ�                
    + kbajTimeDealCnt + kbajOtDealCnt+kbatTimeDealCnt + kbatOtDealCnt   -- ������ü�Ǽ�                
    + nvl(CNT.DEPOcnt,0))                                                -- �����ԱݰǼ�                �����ѰǼ�     					    --USE_CNT_SAME_SUM               																
  , sum(otherTimeDealCnt+otherOtDealCnt) 								--Ÿ������Ǽ�     				    --USE_CNT_DIFF_WITHDRAW      
  , sum(otheraTimeDealCnt+otheraOtDealCnt) 								--Ÿ����ü�Ǽ�  			            --USE_CNT_DIFF_TRANSFER      
  , 0 																--Ÿ���ԱݰǼ�                        --USE_CNT_DIFF_DEPOSIT       
  , sum(otherTimeDealCnt+otherOtDealCnt+ otheraTimeDealCnt+otheraOtDealCnt) --Ÿ���ѰǼ�                       --USE_CNT_DIFF_SUM           
  , sum(nvl(CNT.BOOKcnt,0)) 												--���������Ǽ�                        --BANKBOOK_UPDATE_CNT        
  --cashsTimeDealCnt+cashsOtDealCnt ���ݼ��񽺰Ǽ�_��Ÿ�౸�оȵ�,                                                  
  --kbTimeDealCnt + kbOtDealCnt+ kbajTimeDealCnt + kbajOtDealCnt+kbatTimeDealCnt + kbatOtDealCnt                    
  --+otherTimeDealCnt+otherOtDealCnt+otheraTimeDealCnt+otheraOtDealCnt+cashsTimeDealCnt+cashsOtDealCnt              
  --�ŷ��ѰǼ�,                                                                                                       
	--kbTimeBankFee+kbajTimeBankFee+kbatTimeBankFee ����ð���������,                                               
	--kbOtBankFee+kbajOtBankFee+kbatOtBankFee ����ð��ܼ�����,                                                     
	, sum(kbTimeBankFee+kbOtBankFee+kbajTimeBankFee+kbajOtBankFee+kbatTimeBankFee+kbatOtBankFee) --���������  --EARNED_FEE_IBK              
  , sum( ( (otherTimeCustFee + otherOtCustFee)                                                                           
        -(otherTimeBankFee + otherOtBankFee) )                                                                      
        + ( (otheraTimeCustFee + otheraOtCustFee)                                                                                     
        -(otheraTimeBankFee + otheraOtBankFee) )                                                        
        + (cashsTimeCustFee + cashsOtCustFee                                                            
        + cashsTimeBankFee + cashsOtBankFee) ) --Ÿ�������                                                 --EARNED_FEE_VAN  
    , '0' -- org_send_yn                                                                                --ORG_SEND_YN     
    , '1215003' --insert_uid                                                                            --INSERT_UID      
    , sysdate  -- insert_date                                                                           --INSERT_DATE     
    , '' --update_uid                                                                                   --UPDATE_UID      
    , sysdate  -- update_date                                                                                --UPDATE_DATE     
    , '' -- jijum_nm                                                                                    --JIJUM_NM        
/*    ,''                                                                                                 --SEQ             */
    /*
    ( (otherTimeCustFee + otherOtCustFee)
        -(otherTimeBankFee + otherOtBankFee) )
        + ( (otheraTimeCustFee + otheraOtCustFee)
        -(otheraTimeBankFee + otheraOtBankFee) )
        + (cashsTimeCustFee + cashsOtCustFee
        + cashsTimeBankFee + cashsOtBankFee)
        +kbTimeBankFee+kbOtBankFee+kbajTimeBankFee+kbajOtBankFee+kbatTimeBankFee+kbatOtBankFee ����������
    */
   
from
    (
        select
          substr(dsum.deal_date,1,6) year_mon
          ,DSUM.mac_no
          ,sum(kb_time_deal_cnt) kbTimeDealCnt
          ,sum(kb_time_deal_amt) kbTimeDealAmt
          ,sum(kb_time_cust_fee) kbTimeCustFee
          ,-sum(kb_time_bank_fee) kbTimeBankFee
          ,sum(kb_ot_deal_cnt) kbOtDealCnt
          ,sum(kb_ot_deal_amt) kbOtDealAmt
          ,sum(kb_ot_cust_fee) kbOtCustFee
          ,-sum(kb_ot_bank_fee) kbOtBankFee
          ,sum(other_time_deal_cnt) otherTimeDealCnt
          ,sum(other_time_deal_amt) otherTimeDealAmt
          ,sum(other_time_cust_fee) otherTimeCustFee
          ,-sum(other_time_bank_fee) otherTimeBankFee
          ,sum(other_ot_deal_cnt) otherOtDealCnt
          ,sum(other_ot_deal_amt) otherOtDealAmt
          ,sum(other_ot_cust_fee) otherOtCustFee
          ,-sum(other_ot_bank_fee) otherOtBankFee
          ,sum(kbaj_time_deal_cnt) kbajTimeDealCnt
          ,sum(kbaj_time_deal_amt) kbajTimeDealAmt
          ,sum(kbaj_time_cust_fee) kbajTimeCustFee
          ,-sum(kbaj_time_bank_fee) kbajTimeBankFee
          ,sum(kbaj_ot_deal_cnt) kbajOtDealCnt
          ,sum(kbaj_ot_deal_amt) kbajOtDealAmt
          ,sum(kbaj_ot_cust_fee) kbajOtCustFee
          ,-sum(kbaj_ot_bank_fee) kbajOtBankFee
          ,sum(kbat_time_deal_cnt) kbatTimeDealCnt
          ,sum(kbat_time_deal_amt) kbatTimeDealAmt
          ,sum(kbat_time_cust_fee) kbatTimeCustFee
          ,-sum(kbat_time_bank_fee) kbatTimeBankFee
          ,sum(kbat_ot_deal_cnt) kbatOtDealCnt
          ,sum(kbat_ot_deal_amt) kbatOtDealAmt
          ,sum(kbat_ot_cust_fee) kbatOtCustFee
          ,-sum(kbat_ot_bank_fee) kbatOtBankFee
          ,sum(othera_time_deal_cnt) otheraTimeDealCnt
          ,sum(othera_time_deal_amt) otheraTimeDealAmt
          ,sum(othera_time_cust_fee) otheraTimeCustFee
          ,-sum(othera_time_bank_fee) otheraTimeBankFee
          ,sum(othera_ot_deal_cnt) otheraOtDealCnt
          ,sum(othera_ot_deal_amt) otheraOtDealAmt
          ,sum(othera_ot_cust_fee) otheraOtCustFee
          ,-sum(othera_ot_bank_fee) otheraOtBankFee
          ,sum(cashs_time_deal_cnt + kbcashs_time_deal_cnt) cashsTimeDealCnt
          ,sum(cashs_time_deal_amt + kbcashs_time_deal_amt) cashsTimeDealAmt
          ,sum(cashs_time_cust_fee + kbcashs_time_cust_fee) cashsTimeCustFee
          ,sum(cashs_time_bank_fee + kbcashs_time_bank_fee) cashsTimeBankFee
          ,sum(cashs_ot_deal_cnt + kbcashs_ot_deal_cnt) cashsOtDealCnt
          ,sum(cashs_ot_deal_amt + kbcashs_ot_deal_amt) cashsOtDealAmt
          ,sum(cashs_ot_cust_fee + kbcashs_ot_cust_fee) cashsOtCustFee
          ,sum(cashs_ot_bank_fee + kbcashs_ot_bank_fee) cashsOtBankFee
          ,sum(DEPO.time_deal_cnt+DEPO.ot_deal_cnt+DEPO.night_deal_cnt) DEPOcnt 
          ,sum(BOOK.time_deal_cnt+BOOK.ot_deal_cnt+BOOK.night_deal_cnt) BOOKcnt
      from t_fn_ibk_brand_dsum DSUM
          ,T_FN_NICE_MAC_ORG_DSUM DEPO
          ,T_FN_NICE_MAC_ORG_DSUM BOOK
      where DSUM.deal_date between TO_CHAR(SYSDATE-25,'YYYYMM')||'01' AND TO_CHAR(LAST_DAY(SYSDATE-25),'YYYYMMDD')
      and DSUM.mac_no in
        ( select mac_no from t_ct_nice_mac where org_Cd = '096' and jijum_cd = '9600' and join_cd = '0BK')
      AND DSUM.DEAL_DATE = DEPO.DEAL_DATE(+)
      AND DSUM.MAC_NO = DEPO.MAC_NO(+)
      AND DEPO.ORG_CD(+) = '003'
      AND DEPO.DEAL_TYPE(+) = '1'
      AND DEPO.DEAL_STATUS(+) = '0'
      AND DSUM.DEAL_DATE = BOOK.DEAL_DATE(+)
      AND DSUM.MAC_NO = BOOK.MAC_NO(+)
      AND BOOK.ORG_CD(+) = '003'
      AND BOOK.DEAL_TYPE(+) = '3'
      AND BOOK.DEAL_CLSS(+) = '1004'
      AND BOOK.DEAL_STATUS(+) = '0'
     group by substr(dsum.deal_date,1,6), dsum.mac_no
    ) cnt
   
  group by cnt.year_mon;
  
  
  
	COMMIT;
  DBMS_OUTPUT.PUT_LINE('����� �ŷ��Ǽ���� �Ϸ�');
  EXCEPTION
    WHEN OTHERS
    THEN
         DBMS_OUTPUT.PUT_LINE('����� �ŷ��Ǽ���� ����!');

END;
END;

/
EXIT;
