#!/bin/ksh
export LANG=ko_KR
export NLS_LANG=AMERICAN_AMERICA.KO16KSC5601
export ORACLE_BASE=/oracle/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/10g
export ORACLE_SID=TCSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0

#while [ true ]
#do
   cnt=`ps -e | awk '{print $4}' | sort -u | grep errsvr | wc -l`
   if [ "$cnt" -eq "0" ]
   then
      /ntms_dev/app/develop/ntmsif/bin/errsvr
   fi

#sleep 1
#done

