package com.nicetcm.nibsplus.scheduler.common;

import com.nicetcm.nibsplus.scheduler.constant.ExceptionType;

public class SchduleException extends Exception {

    private final ExceptionType exceptionType;

    public SchduleException(ExceptionType exceptionType, CharSequence exceptionDetail) {
        super(exceptionDetail.toString());
        this.exceptionType = exceptionType;
    }
}