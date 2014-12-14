DECLARE 
  vDATE VARCHAR2(8);

BEGIN
  SELECT TO_CHAR(SYSDATE - 1, 'yyyymmdd')
  INTO  vDATE
  FROM DUAL;

  UPDATE
      t_ct_error_basic
  SET
      org_send_yn = '9' ,
      update_date = sysdate ,
      update_uid = 'batch'
  WHERE
      (
          error_no, create_date
      )
      IN
      (
          SELECT
              a.error_no,
              a.create_date
          FROM
              t_ct_error_basic a,
              t_ct_error_txn b
          WHERE
              a.error_no = b.error_no
          AND a.create_date = b.create_date
          AND a.org_cd = '096'
          AND a.branch_cd = '9600'
          AND a.org_send_yn IN ('1', '2', '5')
          AND b.finish_time IS NOT NULL
          AND a.create_time = b.finish_time
      );

  COMMIT;
END;
/
exit;
