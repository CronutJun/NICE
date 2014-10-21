#!/bin/sh
# ���� : �̷��� ��������ý��� FTP SAP ������ -> NIBS ���� ��ġ
# ���� : ���� NICE�������� �������� ���� ������ ����ý��� ������ NIBS�� �����Ŵ
# ���࿹�� : $ . test.sh   - ȯ�溯�� �б�/���⸦ ���� �̿� ���� ������
# fDATE="["$(date +"%y-%m-%d %T")"]"
# ��������
dstIP=`grep FTP_ELAND_IP /nibs_dev/app/develop/nibsif/code/config.sam | cut -d = -f 2`
dstPORT=`grep FTP_ELAND_PORT /nibs_dev/app/develop/nibsif/code/config.sam | cut -d = -f 2`
dstID=`grep FTP_ELAND_UNAME /nibs_dev/app/develop/nibsif/code/config.sam | cut -d = -f 2`
dstPW=`grep FTP_ELAND_PWD /nibs_dev/app/develop/nibsif/code/config.sam | cut -d = -f 2`
DATE=`TZ=KST-9 date +'%Y%m%d'`
logDir=/nibs_dev/app/develop/nibsif/log/$DATE/
# logDir=/nibs_dev/app/develop/nibsif/test/	

# �ߺ������� �����ϱ� ���� ó�� : [���ϸ�.pid] ������ ����� �ߺ��� �����Ѵ�.
if [ -s "$0.pid" ] # pid ������ �ִ��� üũ
then
	echo "["$(date +"%y-%m-%d %T")"] [$0] is running." >> ${logDir}FTPtest.log
	exit 2
fi
echo $$ > "$0.pid"



# �ߺ������� �����ϱ� ���� ó�� : ps -ef �� �ش� ���μ����� ������ �����Ѵ�.
# esc=`ps -ef | grep $0 | /bin/awk 'NR > 2' | /bin/awk '{print $9}'`
# if [ $esc ]
# then
# 	echo "shell $0 is running!" >> ${logDir}FTPtest.log
# 	exit 1
# fi
# echo $esc >> ${logDir}FTPtest.log




echo "["$(date +"%y-%m-%d %T")"] Process Start." >> ${logDir}FTPtest.log


# ��������
idx=0
tempMax='0'
arr[999]=
update_time[999]=
update_time_ori[999]=
exeIDX[999]=


# FTP�� �����Ͽ� ���ϸ���� �迭�� ��´�.
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

# �ٽ� FTP�� �����Ͽ�, ���ϸ�ϵ��� ���������ð��� �о�´�.
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
 

# ��¥������ YYYYMMDDHHMISS�������� �ٲ۴�. 
# �ֱټ������ڸ� ������Ʈ�Ѵ�.
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


# ���س�¥�� �о�´�.
# ���س�¥�� text���Ͽ��� �о�´�.
FTP_UPDATE_DATE_ELAND=`cat /nibs_dev/app/develop/nibsif/test/ELAND_FTP.dat`
echo "["$(date +"%y-%m-%d %T")"] Latest Update time : $FTP_UPDATE_DATE_ELAND" >> ${logDir}FTPtest.log
if [ $FTP_UPDATE_DATE_ELAND ]
then
	echo "["$(date +"%y-%m-%d %T")"] FTP_UPDATE_DATE_ELAND exhists" >> ${logDir}FTPtest.log
else
	FTP_UPDATE_DATE_ELAND=$tempMax
	echo "["$(date +"%y-%m-%d %T")"] FTP_UPDATE_DATE_ELAND is null. fill up with [$tempMax]" >> ${logDir}FTPtest.log
fi

# �������ں��� ū ���ϸ���� �ɷ���, ElandAdj.sh ��ũ��Ʈ�� �����Ѵ�.
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
		# ���⼭ ��ũ��Ʈ ����
		# ���� ������ NICE_yyyymmdd_xxxx_MASTER.dat �̹Ƿ�, xxxx�κ��� �����Ѵ�.
		
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
	
	
# max���� �����Ͽ�, ������ ���� �� ���س�¥�� �ǵ��� ������ ����Ѵ�.
fi
k=`expr "$k" + "1"` 
done
if [ $tempMax -gt 0 ]
	then
	# FTP�����ð� �ٿ�ε� ��, �̻��� �߻��� ��� tempMax�� ���� �ִ밪�� �ƴ� �� �ִ�.
	# ����, �ѹ� �� �ִ밪���� üũ�ؾ��Ѵ�.
	if [ $FTP_UPDATE_DATE_ELAND -lt $tempMax ]
	then
		FTP_UPDATE_DATE_ELAND=$tempMax
	fi
	echo "["$(date +"%y-%m-%d %T")"] NEXT SET TIME $FTP_UPDATE_DATE_ELAND" >> ${logDir}FTPtest.log
	echo $FTP_UPDATE_DATE_ELAND > /nibs_dev/app/develop/nibsif/test/ELAND_FTP.dat
else
	echo "["$(date +"%y-%m-%d %T")"] FTP_UPDATE_DATE_ELAND Cannot be $tempMax."
fi


# �ߺ����� ������ ���� ������ pid ���� ����
rm -f "$0.pid"