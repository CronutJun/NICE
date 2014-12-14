
#!/bin/ksh
export ORACLE_BASE=/oracle
export ORACLE_HOME=$ORACLE_BASE/app/oracle/product/10g
export ORACLE_SID=TCSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0

sqlplus sms/sms @/tcs/batch/src/sp_sms_grant.sql >> /tcs/batch/log/sp_sms_grant.log

