DECLARE 
  PDATE VARCHAR2(200);
  P_STATUS VARCHAR2(200);

BEGIN
  SELECT TO_CHAR(SYSDATE - 1,'YYYYMMDD')
  INTO PDATE
  FROM DUAL;
 
  P_STATUS := NULL;

  NIBS.sp_fn_lease_msum ( PDATE );
  COMMIT;

  NIBS.sp_fn_cms_close_msum ( PDATE );
  COMMIT;

  NIBS.sp_fn_eis_msum ( PDATE );
  COMMIT;


END;
/
EXIT;
