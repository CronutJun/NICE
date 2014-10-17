#!/bin/ksh
export LANG=ko_KR
export NLS_LANG=AMERICAN_AMERICA.KO16KSC5601
export ORACLE_BASE=/oracle/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/10g
export ORACLE_SID=NIBSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0
export DATE=`/bin/date '+%Y%m%d'`


# 아래처럼 해놓으면 파일 나가고요. 맨 앞에 # 붙이면 주석처리되서 파일 안나가요. 바꾸시면 저장하셔야되요 ㅎㅎ
/nibs_dev/app/develop/nibsif/bin/FILESend $DATE'000'
#/nibs_dev/app/develop/nibsif/bin/FILESend $DATE'000'




