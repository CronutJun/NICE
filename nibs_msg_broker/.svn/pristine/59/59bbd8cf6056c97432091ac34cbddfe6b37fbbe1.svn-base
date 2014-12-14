#!/bin/ksh
export LANG=ko_KR
export NLS_LANG=AMERICAN_AMERICA.KO16KSC5601
export ORACLE_BASE=/oracle/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/10g
export ORACLE_SID=NIBSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0

/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 092 CASH_CYCLE
/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 092 COLLECT
/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 092 NOT_MNG

### 20080724 운영자금과 추가현송은 9시 이후 8시30분 배치 결과가 나오면 전송하도록 수정 
#/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 092 OPER_FUNDS
#/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 092 ADD_CASH

### 현송계획은 4:30분에 따로 전송
#/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 092 CASH_PLAN


