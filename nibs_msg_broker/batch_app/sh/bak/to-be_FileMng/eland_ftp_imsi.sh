#!/bin/sh
export LANG=ko_KR
export NLS_LANG=AMERICAN_AMERICA.KO16KSC5601
export ORACLE_BASE=/oracle/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/10g
export ORACLE_SID=NIBSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0
export TZ=KST+15 
export DATE2=`/bin/date '+%Y%m%d'`
export TZ=KST-9

/nibs_dev/app/develop/nibsif/bin/FILEMng SAPMA 20120127 8503 
/nibs_dev/app/develop/nibsif/bin/FILEMng SAPDE 20120127 8503

/nibs_dev/app/develop/nibsif/bin/FILEMng SAPMA 20120128 7219
/nibs_dev/app/develop/nibsif/bin/FILEMng SAPDE 20120128 7219

/nibs_dev/app/develop/nibsif/bin/FILEMng SAPMA 20120128 8204
/nibs_dev/app/develop/nibsif/bin/FILEMng SAPDE 20120128 8204
