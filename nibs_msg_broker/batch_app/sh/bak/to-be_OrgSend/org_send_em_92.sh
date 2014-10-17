#!/bin/ksh
export LANG=ko_KR
export NLS_LANG=AMERICAN_AMERICA.KO16KSC5601
export ORACLE_BASE=/oracle/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/10g
export ORACLE_SID=NIBSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0

/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 092 ARR_EST
/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 092 ARRIVAL
/nibs_dev/app/develop/nibsif/bin/OrgOnlySend 092 FIN_RSLT
