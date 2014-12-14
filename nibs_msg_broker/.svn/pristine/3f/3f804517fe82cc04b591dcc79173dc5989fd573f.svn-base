#!/bin/sh

DATE=`TZ=KST+15 date +'%Y%m%d'`

ftp -n 192.168.255.146 29010 << EOF
user ftpnice ftpnice
bi
hash
prompt
lcd /nibs_dev/app/develop/nibs_ftp/sap_file/
cd /NICE_FILE/
mget NICE_$DATE*.dat

bye
EOF

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

