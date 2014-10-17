DECLARE 
  PDATE VARCHAR2(200);
  P_STATUS VARCHAR2(200);

BEGIN
  SELECT TO_CHAR(SYSDATE,'YYYYMMDD')
  INTO PDATE
  FROM DUAL;
 
  P_STATUS := NULL;


  NIBS.sp_fn_cashflow_endamt ( PDATE );
  COMMIT;

END;
/
EXIT;
