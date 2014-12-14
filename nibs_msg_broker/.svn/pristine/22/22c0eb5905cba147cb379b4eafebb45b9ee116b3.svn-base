DECLARE 
  PDATE VARCHAR2(200);

BEGIN 
  SELECT TO_CHAR(SYSDATE,'YYYYMMDD')
  INTO PDATE
  FROM DUAL;

  NIBS.sp_fn_forcast_data_dsum_atm( PDATE );
  COMMIT; 
END; 

/
EXIT;
