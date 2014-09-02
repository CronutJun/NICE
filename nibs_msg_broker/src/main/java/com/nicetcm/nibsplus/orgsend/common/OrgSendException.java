package com.nicetcm.nibsplus.orgsend.common;

import com.nicetcm.nibsplus.orgsend.constant.ExceptionType;

public class OrgSendException extends Exception {

    private final ExceptionType exceptionType;

    public OrgSendException(ExceptionType exceptionType, CharSequence exceptionDetail) {
        super(exceptionDetail.toString());
        this.exceptionType = exceptionType;
    }
}