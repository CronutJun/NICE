#!/bin/ksh
cronfile="/var/adm/cron/log"
tmp="/nibs_dev/app/batch/se/cron.tmp.log"
# COPY CRON FILE LOG TO TEMP TO SEND IN EMAIL LATER
cp -p $cronfile $tmp
#FILTER
if [[ $(cat $tmp | grep Fail | wc -l) > "1" ]] then
subject=$(print "Daily cron log $(date)" | tr ' ' '-')
uuencode $tmp $tmp | mail -s $subject $USER < $tmp
#end filter
fi
#sleep 5;[[ -e $tmp ]] && rm $tmp
