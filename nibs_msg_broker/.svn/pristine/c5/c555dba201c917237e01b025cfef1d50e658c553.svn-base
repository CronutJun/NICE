#!/bin/sh
# 제목 : 이랜드 정산관리시스템 FTP SAP 데이터 -> NIBS 적용 배치
# 내용 : 최종 NICE적용파일 기준으로 이후 생성된 정산시스템 파일을 NIBS에 적용시킴
# 실행예시 : $ . test.sh   - 환경변수 읽기/쓰기를 위해 이와 같이 실행함
# fDATE="["$(date +"%y-%m-%d %T")"]"
# 설정변수
dstIP=`grep FTP_ELAND_IP /nibs_dev/app/develop/nibsif/code/config.sam | cut -d = -f 2`
dstPORT=`grep FTP_ELAND_PORT /nibs_dev/app/develop/nibsif/code/config.sam | cut -d = -f 2`
dstID=`grep FTP_ELAND_UNAME /nibs_dev/app/develop/nibsif/code/config.sam | cut -d = -f 2`
dstPW=`grep FTP_ELAND_PWD /nibs_dev/app/develop/nibsif/code/config.sam | cut -d = -f 2`
DATE=`TZ=KST-9 date +'%Y%m%d'`
logDir=/nibs_dev/app/develop/nibsif/log/$DATE/
# logDir=/nibs_dev/app/develop/nibsif/test/	

# 중복실행을 방지하기 위한 처리 : [파일명.pid] 파일을 만들어 중복을 방지한다.
if [ -s "$0.pid" ] # pid 파일이 있는지 체크
then
	echo "["$(date +"%y-%m-%d %T")"] [$0] is running." >> ${logDir}FTPtest.log
	exit 2
fi
echo $$ > "$0.pid"



# 중복실행을 방지하기 위한 처리 : ps -ef 에 해당 프로세스가 있으면 종료한다.
# esc=`ps -ef | grep $0 | /bin/awk 'NR > 2' | /bin/awk '{print $9}'`
# if [ $esc ]
# then
# 	echo "shell $0 is running!" >> ${logDir}FTPtest.log
# 	exit 1
# fi
# echo $esc >> ${logDir}FTPtest.log




echo "["$(date +"%y-%m-%d %T")"] Process Start." >> ${logDir}FTPtest.log


# 변수선언
idx=0
tempMax='0'
arr[999]=
update_time[999]=
update_time_ori[999]=
exeIDX[999]=


# FTP에 접속하여 파일목록을 배열에 담는다.
echo "["$(date +"%y-%m-%d %T")"] Loading FTP file list..." >> ${logDir}FTPtest.log
for i in  $(
(
for host in $dstIP
do
echo "
open $dstIP $dstPORT
user $dstID $dstPW
ascii
prompt
cd /NICE_FILE/
ls"
done
)|ftp -i -n ); 
do
arr[$idx]=$i
idx=`expr "$idx" + "1"`
done
echo "["$(date +"%y-%m-%d %T")"] DONE." >> ${logDir}FTPtest.log

# 다시 FTP에 접속하여, 파일목록들의 최종수정시각을 읽어온다.
q=0
echo "["$(date +"%y-%m-%d %T")"] Loading Update time..." >> ${logDir}FTPtest.log
for t in $(
(
for host in $dstIP
do
echo "
open $dstIP $dstPORT
user $dstID $dstPW
ascii
prompt
cd /NICE_FILE/
"
j=0
for z in ${arr[@]};
do
# echo "$j"
echo modtime ${arr[$j]} 
j=`expr "$j" + "1"`
done
done
)|ftp -i -n  | awk '{print $2"/"$3}');
do
update_time_ori[$q]=$t
q=`expr "$q" + "1"`
done
echo "["$(date +"%y-%m-%d %T")"] DONE." >> ${logDir}FTPtest.log
 

# 날짜변수를 YYYYMMDDHHMISS형식으로 바꾼다. 
# 최근수정일자를 업데이트한다.
echo "["$(date +"%y-%m-%d %T")"] Transporting Update time..." >> ${logDir}FTPtest.log
k=0
for i in ${update_time_ori[@]};
do
month=`echo $i | cut -d / -f 1`
day=`echo $i | cut -d / -f 2`
year=`echo $i | cut -d / -f 3`
hour=`echo $i | cut -d / -f 4 | cut -d : -f 1`
min=`echo $i | cut -d / -f 4 | cut -d : -f 2`
sec=`echo $i | cut -d / -f 4 | cut -d : -f 3`

update_time[$k]=$year$month$day$hour$min$sec
if [ ${update_time[${k}]} != 'modeon.' -a ${update_time[${k}]} != 'Thesystem' ]
	then 
	if [ ${update_time[${k}]} -gt $tempMax ]
	then
		tempMax=${update_time[$k]}
	fi
fi
k=`expr "$k" + "1"`
done
echo "["$(date +"%y-%m-%d %T")"] DONE." >> ${logDir}FTPtest.log


# 기준날짜를 읽어온다.
# 기준날짜는 text파일에서 읽어온다.
FTP_UPDATE_DATE_ELAND=`cat /nibs_dev/app/develop/nibsif/test/ELAND_FTP.dat`
echo "["$(date +"%y-%m-%d %T")"] Latest Update time : $FTP_UPDATE_DATE_ELAND" >> ${logDir}FTPtest.log
if [ $FTP_UPDATE_DATE_ELAND ]
then
	echo "["$(date +"%y-%m-%d %T")"] FTP_UPDATE_DATE_ELAND exhists" >> ${logDir}FTPtest.log
else
	FTP_UPDATE_DATE_ELAND=$tempMax
	echo "["$(date +"%y-%m-%d %T")"] FTP_UPDATE_DATE_ELAND is null. fill up with [$tempMax]" >> ${logDir}FTPtest.log
fi

# 기준일자보다 큰 파일목록을 걸러서, ElandAdj.sh 스크립트를 실행한다.
echo "["$(date +"%y-%m-%d %T")"] Target Files : " >> ${logDir}FTPtest.log
k=0
# tempMax='0'
for i in ${update_time[@]};
do
cmpData=`echo "${arr[$k]}" | cut -d _ -f 4`
if [ $cmpData ]
	then
	if [ $i != 'modeon.' -a $i != 'Thesystem' ]
	then 
		if [ $i -gt $FTP_UPDATE_DATE_ELAND ]
		then
		# echo "${arr[$k]}"
		# 여기서 스크립트 실행
		# 파일 형식은 NICE_yyyymmdd_xxxx_MASTER.dat 이므로, xxxx부분을 추출한다.
		
			if [ 'MASTER.dat' == $cmpData ]
			then
				adjDate=`echo "${arr[$k]}" | cut -d _ -f 2`
				adjJijum=`echo "${arr[$k]}" | cut -d _ -f 3` 
				echo "["${k}"] ${arr[$k]} $i ${update_time[$k]} ${update_time_ori[$k]}" >> ${logDir}FTPtest.log
				/nibs_dev/app/develop/nibsif/bin/ElandAdj.sh $adjDate $adjJijum >> ${logDir}FTPtest.log
				if [ $i -gt $tempMax  ]
				then
					tempMax=$i
					echo $tempMax
				fi
			fi
				
		fi		
			
	fi
	
	
# max값을 추출하여, 다음번 실행 시 기준날짜가 되도록 파일을 기록한다.
fi
k=`expr "$k" + "1"` 
done
if [ $tempMax -gt 0 ]
	then
	# FTP수정시각 다운로드 중, 이상이 발생할 경우 tempMax가 실제 최대값이 아닐 수 있다.
	# 따라서, 한번 더 최대값인지 체크해야한다.
	if [ $FTP_UPDATE_DATE_ELAND -lt $tempMax ]
	then
		FTP_UPDATE_DATE_ELAND=$tempMax
	fi
	echo "["$(date +"%y-%m-%d %T")"] NEXT SET TIME $FTP_UPDATE_DATE_ELAND" >> ${logDir}FTPtest.log
	echo $FTP_UPDATE_DATE_ELAND > /nibs_dev/app/develop/nibsif/test/ELAND_FTP.dat
else
	echo "["$(date +"%y-%m-%d %T")"] FTP_UPDATE_DATE_ELAND Cannot be $tempMax."
fi


# 중복실행 방지를 위해 생성한 pid 파일 삭제
rm -f "$0.pid"