#!/bin/ksh
export LANG=ko_KR
export NLS_LANG=AMERICAN_AMERICA.KO16KSC5601
export ORACLE_BASE=/oracle/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/10g
export ORACLE_SID=NIBSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0

/nibs_dev/app/develop/nibsif/bin/OrgAutoSend 088 COLLECT
/nibs_dev/app/develop/nibsif/bin/OrgAutoSend 088 NOT_MNG
/nibs_dev/app/develop/nibsif/bin/OrgAutoSend 088 CASH_CYCLE

### 20080724 ��ڱݰ� �߰������� 9�� ���� 8��30�� ��ġ ����� ������ �����ϵ��� ���� 
#/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 088 OPER_FUNDS
#/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 088 ADD_CASH

### ���۰�ȹ�� 4:30�п� ���� ����
#/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 088 CASH_PLAN


