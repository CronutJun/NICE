#!/bin/ksh
export ORACLE_BASE=/oracle/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/10g
export ORACLE_SID=NIBSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0

sqlplus nibs/nebsnibs @/nibs_dev/app/batch/src/sp_ku_createAtmsCashPlan.sql >> /nibs_dev/app/batch/log/sp_ku_createAtmsCashPlan.log