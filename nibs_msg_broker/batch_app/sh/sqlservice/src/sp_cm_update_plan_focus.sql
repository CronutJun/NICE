DECLARE 
  PDATE VARCHAR2(200);

BEGIN 
  SELECT TO_CHAR(SYSDATE - 1,'YYYYMMDD')
  INTO PDATE
  FROM DUAL;

  NIBS.SP_CM_UPDATE_PLAN_FOCUS ( PDATE );
  COMMIT; 

END;
/
EXIT;