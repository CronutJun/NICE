#!/bin/ksh
#큐상태를 읽어와 모니터링을 위한 테이블 t_if_queue_status 에 저장 처리
export LANG=ko_KR
export NLS_LANG=AMERICAN_AMERICA.KO16KSC5601
export ORACLE_BASE=/oracle/app/oracle
export ORACLE_HOME=$ORACLE_BASE/product/10g
export ORACLE_SID=NIBSDB
export PATH=$PATH:$ORACLE_HOME/bin
export LD_LIBRARY_PATH=$ORACLE_HOME/lib
export DISPLAY=localhost:0.0


integer index
index=0


v_QueueNo=""
v_Count=0
v_Size=0

for i in  $( ipcs -qa | egrep '0x0006|0x0007|0x0008' | nawk '{print $3, $10, $9}')
do

    if [ $index -eq 0 ]; then
        v_QueueNo=$i
    elif [ $index -eq 1 ]; then
        v_Count=$(print $i)
    else
        v_Size=$i
#echo "v_Queue=$v_QueueNo"
#echo "v_Count=$v_Count"
#echo "v_Size=$v_Size"

sqlplus -s nibs/nebsnibs > /dev/null 2>&1 << EOF
exec sp_if_queue_status ('$v_QueueNo', '$v_Count','$v_Size');
exit;
EOF
		
        index=-1
        v_QueueNo=""
		v_Count=0
		v_Size=0
    fi

    let index=index+1 
done
