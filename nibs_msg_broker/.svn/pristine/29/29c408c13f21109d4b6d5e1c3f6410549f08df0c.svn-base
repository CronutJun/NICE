#!/bin/ksh
export LANG=ko_KR
export NLS_LANG=AMERICAN_AMERICA.KO16KSC5601
export ORACLE_BASE=/oracle/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/10g
export ORACLE_SID=NIBSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0

# apif에서 출동요청전문 응답을 수신하지 못하는 현상발생 20120924. 문제분석필요.
# 기존 AutoSend에서 임시로 OnlySend 사용. 
/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 0S1 GUARD_ERR
#/nibs_dev/app/develop/nibsif/bin/OrgAutoSend CQ GUARD_ERR
/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 0CP GUARD_ERR
/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 0JE GUARD_ERR
/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 0KT GUARD_ERR
##/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 0TR GUARD_ERR
/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 7HS GUARD_ERR
/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 7CH GUARD_ERR
/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 7LG GUARD_ERR
