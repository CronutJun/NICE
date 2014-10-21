#!/bin/ksh
export ORACLE_BASE=/oracle
export ORACLE_HOME=$ORACLE_BASE/app/oracle/product/10g
export ORACLE_SID=NIBSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0

DDATE=`date`

echo $DDATE >> /nibs_dev/app/batch/se/sms.log
sleep 1
sqlplus nibs/nebsnibs @/nibs_dev/app/batch/src/sms1.sql >> /nibs_dev/app/batch/log/sms1.log
