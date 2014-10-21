#!/bin/ksh
export ORACLE_BASE=/oracle
export ORACLE_HOME=$ORACLE_BASE/app/oracle/product/10g
export ORACLE_SID=NIBSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0

sqlplus nibs/nebsnibs @/nibs_dev/app/batch/src/sp_ct_month_use_status_003.sql >> /nibs_dev/app/batch/log/sp_ct_month_use_status_003.log

/nibs_dev/app/develop/nibsif/bin/OrgAutoSend 003 MONTH_USE_STATUS
exit
