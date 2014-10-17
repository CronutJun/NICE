#!/bin/ksh
export LANG=ko_KR
export NLS_LANG=AMERICAN_AMERICA.KO16KSC5601
export ORACLE_BASE=/oracle/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/10g
export ORACLE_SID=NIBSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0

# 응답을 받지 못하는 경우가 발생하여, 일단 OnlySend로 바꾸어 놓음. 최락경 20130910
/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 005 ARR_EST 
/nibs_dev/app/develop/nibsif/bin/OrgAutoSend 005 ARRIVAL
/nibs_dev/app/develop/nibsif/bin/OrgAutoSend 005 FIN_RSLT
