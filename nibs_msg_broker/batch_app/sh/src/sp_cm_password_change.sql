-- �űԻ�� ��й�ȣ ��ȣȭ(���� 07�� ����)

UPDATE T_CM_MEMBER
SET PASS_WORD = FN_MD5(member_id), --��� ��ȣȭ�� ����
    UPDATE_UID = '0000000'
WHERE UPDATE_UID = '9999999';

COMMIT;

-- ������ �Ҽ� MIS �������� �ϰ�����(2005��9��13�Ϻ��� ����)
update t_cm_member member
set dept_cd = (select mis.dept_cd 
				from t_cm_dept_mis mis 
				where mis.dept_code = member.mis_org_cd	),
    office_cd = (select mis.office_cd 
				from t_cm_dept_mis mis 
				where mis.dept_code = member.mis_org_cd	),
    jiso_cd = (select mis.jiso_cd 
				from t_cm_dept_mis mis 
				where mis.dept_code = member.mis_org_cd	),
	update_date = sysdate,
	update_uid = 'misdept'								 
where member.member_type = '1100' 
  and member.use_type = '0'
  and member.retire_yn = '0' 
  and member.mis_org_cd not in ('JLHL00','JPIL00','JPJR00','JPLPBA','JPLPBC','JPJDBA');

COMMIT; 

--��������� CMS ������ �Ҽ� ����
update t_cm_member member
set dept_cd = '15',
	update_date = sysdate,
	update_uid = 'misdept'								 
where member.member_type = '1200' 
  and member.use_type = '0'
  and member.retire_yn = '0'
  and substr(member.mis_org_cd,1,3) = 'YDC'
  and member.dept_cd is null
  and member.enter_date > '20091201'; 

COMMIT; 

     delete from t_fn_nice_notend 
      where NOTEND_DATE = to_char(sysdate-1,'yyyymmdd');
            
     insert into t_fn_nice_notend
     select to_char(sysdate-1,'yyyymmdd'), 
            b.dept_cd, 
            b.office_cd, 
            nvl(a.amt,0) amt, 
            'SYSTEM', 
            SYSDATE, 
            nvl(a.cnt,0) cnt
      from
          (SELECT dept_cd, office_cd, count(notend_date) cnt, sum(deal_amt) amt
             FROM t_fn_notend 
            WHERE notend_date >= to_char(sysdate-16,'YYYYMMDD')
            AND owner_org_cd = '096'
            AND process_yn = '0'
            group by dept_cd, office_cd) a,
          (select dept_cd, office_cd
             from t_cm_office
            where enable_yn='1'
            and dept_cd < '20') b
      where b.dept_cd = a.dept_cd(+)
      and b.office_cd = a.office_cd(+);

COMMIT;

--20101221 Ȳ���� ������, ���񼺾� Ȯ�ι��� 
--�ڱݰ��� �����ڵ� reUpdate 
--�繫��  �Ϲ����� 7001, 7130
--�繫����         5000, 6000
--�������(�ڱ�) 1001

update t_cm_member
set dept_cd = case when dept_cd='10' then '05'
                   when dept_cd='11' then '06'
                   when dept_cd='13' then '07'
                   when dept_cd='14' then '08'
                   when dept_cd='15' then '09'
		   else dept_cd
              end,
    update_date = sysdate,
    update_uid = 'fundC' 	      
where fn_right in ('6000','7130','5000','7001','1001')
and   member_type = '1100' 
and   retire_yn ='0';

COMMIT;
/
EXIT;
