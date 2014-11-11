DECLARE 
  PDATE VARCHAR2(200);
  P_STATUS VARCHAR2(200);

BEGIN
  SELECT TO_CHAR(SYSDATE,'YYYYMMDD')
  INTO PDATE
  FROM DUAL;
 
  P_STATUS := NULL;


  NIBS.sp_fn_sendplan_confirm_sh ( PDATE, P_STATUS );
  COMMIT;
END;
/
EXIT;
