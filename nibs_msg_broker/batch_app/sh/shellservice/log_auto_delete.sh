#!/bin/ksh

DATE=`TZ=KST+39 date +'%Y%m%d'`

DELDATE=`TZ=KST+183 date +'%Y%m%d'`

mkdir /nibs_log/nh/$DATE
cp /nibs_dev/app/develop/nibsif/log/$DATE/ES05000120.log /nibs_log/nh/$DATE

rm -fr /nibs_log/$DELDATE
mv -f /nibs_dev/app/develop/nibsif/log/$DATE /nibs_log 


###find /nibs_dev/app/develop/nibsif/log -depth -type d -ctime +7 -exec rm -rf {} \;
