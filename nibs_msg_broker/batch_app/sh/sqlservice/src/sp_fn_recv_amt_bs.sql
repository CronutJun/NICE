-- ���̽� ���� ���� ����

DECLARE 
  PDATE VARCHAR2(200);

BEGIN 
  SELECT TO_CHAR(SYSDATE,'YYYYMMDD')
  INTO PDATE
  FROM DUAL;

  NIBS.sp_fn_recv_amt_bs ( PDATE );
  COMMIT; 
END; 

/
EXIT;
