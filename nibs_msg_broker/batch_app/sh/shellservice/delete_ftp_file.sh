#!/bin/sh
# FTP파일을 정리하는 배치파일 입니다.
# 현재 날짜의 1개월 전 파일을 삭제합니다.

# 현재 등록된 삭제 대상 목록
# [1]이랜드 정산파일 : /nibs_ftp/sap_file
# [2]호스트 로그파일 : /nibs_ftp/host_file



cmd1=`find /nibs_dev/app/develop/nibs_ftp/sap_file/ -mtime +30 -exec rm {} ";"`
cmd2=`find /nibs_dev/app/develop/nibs_ftp/host_file/ -mtime +90 -exec rm {} ";"`


echo $cmd1
echo $cmd2

