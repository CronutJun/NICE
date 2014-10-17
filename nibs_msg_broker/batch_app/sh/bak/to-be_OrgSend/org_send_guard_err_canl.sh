#!/bin/ksh
export LANG=ko_KR
export NLS_LANG=AMERICAN_AMERICA.KO16KSC5601
export ORACLE_BASE=/oracle/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/10g
export ORACLE_SID=NIBSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0

/nibs_dev/app/develop/nibsif/bin/OrgAutoSend 0S1 GUARD_ERR_CANL
####/nibs_dev/app/develop/nibsif/bin/OrgAutoSend CQ GUARD_ERR_CANL
/nibs_dev/app/develop/nibsif/bin/OrgAutoSend 0CP GUARD_ERR_CANL
/nibs_dev/app/develop/nibsif/bin/OrgAutoSend 0KT GUARD_ERR_CANL
/nibs_dev/app/develop/nibsif/bin/OrgAutoSend 0JE GUARD_ERR_CANL
##/nibs_dev/app/develop/nibsif/bin/OrgAutoSend 0TR GUARD_ERR_CANL
/nibs_dev/app/develop/nibsif/bin/OrgAutoSend 7HS GUARD_ERR_CANL
/nibs_dev/app/develop/nibsif/bin/OrgAutoSend 7CH GUARD_ERR_CANL
/nibs_dev/app/develop/nibsif/bin/OrgAutoSend 7LG GUARD_ERR_CANL
