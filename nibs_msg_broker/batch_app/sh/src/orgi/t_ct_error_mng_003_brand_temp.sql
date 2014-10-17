DECLARE 
  vDATE VARCHAR2(8);

BEGIN
  SELECT TO_CHAR(SYSDATE - 1, 'yyyymmdd')
  INTO	vDATE
  FROM DUAL;

  update t_ct_Error_mng 
    set org_send_yn = '9'
    , update_date = sysdate
    , update_uid = 'batch'
  where create_Date >= to_number(vDATE) 
    and org_cd = '096' 
    and jijum_cd = '9600' 
    and finish_time is not null
    and org_send_yn in ('1','2','5') 
    and create_time = finish_time;

  COMMIT;
END;
/
exit;
