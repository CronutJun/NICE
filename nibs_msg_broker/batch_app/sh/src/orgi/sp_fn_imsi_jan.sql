
DECLARE 
  PDATE VARCHAR2(200);

BEGIN 
  SELECT TO_CHAR(SYSDATE - 1,'YYYYMMDD')
  INTO PDATE
  FROM DUAL;

  NIBS.SP_FN_IMSI_JAN ( PDATE );
  COMMIT; 
END;
/
EXIT;