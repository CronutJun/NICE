-- 나이스 실적집계 및 기기별 사용량 예측 프로시져 실행
-- 나이스 24시 재고 및 일괄 기기별 사용량 예측 프로시져 실행

DECLARE 
  PDATE VARCHAR2(200);
  P_STATUS VARCHAR2(200);

BEGIN
  SELECT TO_CHAR(SYSDATE - 1,'YYYYMMDD')
  INTO PDATE
  FROM DUAL;
 
  P_STATUS := NULL;

  NIBS.sp_fn_update_tran_nh ( PDATE );
  COMMIT;

  NIBS.sp_fn_nice_dsum_demand ( PDATE );
  COMMIT;

  NIBS.SP_FN_NICE_DSUM ( PDATE );
  COMMIT;

  NIBS.SP_FN_MAC_DEALTYPE_DSUM ( PDATE, P_STATUS );
  COMMIT;

  NIBS.SP_FN_FORECAST_BASE_NICE ( PDATE, P_STATUS );
  COMMIT;

 -- NIBS.SP_FN_MAC_STOCK ( PDATE, P_STATUS );
 -- COMMIT;

  NIBS.SP_FN_FORECAST_BASE_CLOSE ( PDATE, P_STATUS );
  COMMIT;

  NIBS.SP_FN_CARRY_FEE_DSUM ( PDATE );
  COMMIT;
END;
/
EXIT;
