#!/bin/ksh
export ORACLE_BASE=/oracle
export ORACLE_HOME=$ORACLE_BASE/app/oracle/product/10g
export ORACLE_SID=NIBSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0


echo $1 $2 $3 $4 $5

v_CloseDate=$1
v_OrgCd=$2
v_JijumCd=$3
v_MacNo=$4
v_CloseType=$5

v_Rtn=""

v_aaa=0

VALUE=`sqlplus -s nibs/nebsnibs << EOF
	set serveroutput on
	set feedback off
	set heading off
	set feedback off
	set linesize 32767
DECLARE
	v_RtnOut varchar2(100);
	v_RtnOutNum	number;
BEGIN	
sp_fn_ktis_close('$v_CloseDate', '$v_OrgCd','$v_JijumCd','$v_MacNo',$v_CloseType,v_RtnOut);

if length(v_RtnOut) > 2 then
	v_RtnOutNum:=to_number(substr(v_RtnOut, 1, 2));
else v_RtnOutNum :=0;	
end if;	
dbms_output.put_line(v_RtnOutNum);
END;
/
exit
EOF`

echo "VALUE=[$VALUE]\n"

exit $VALUE


