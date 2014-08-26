package com.nicetcm.nibsplus.orgsend.constant;

public enum TransferType
{
    ONLY_SEND,  //[async]
    AUTO_SEND   //[sync]OrgAutoSend는 연결한 기관에서 특정 메시지(지사마감) 데이터를 줄때까지 계속 socket을 끊지않고 기다림. 내용은 OrgOnlySend와 같음
}
