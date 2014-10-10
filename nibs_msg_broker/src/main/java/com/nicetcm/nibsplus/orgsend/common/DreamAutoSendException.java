package com.nicetcm.nibsplus.orgsend.common;

import com.nicetcm.nibsplus.orgsend.constant.ExceptionType;

/**
 * DreamAutoSend 모듈의 대표 Exception
 * <pre>
 * ExceptionType를 정의해서 사용함
 * </pre>
 *
 * @author 박상철
 * @version 1.0
 * @see
 */
public class DreamAutoSendException extends Exception {

    /**  */
    private static final long serialVersionUID = -7990354727118618029L;
    private final ExceptionType exceptionType;

    public DreamAutoSendException(ExceptionType exceptionType, CharSequence exceptionDetail) {
        super(exceptionDetail.toString());
        this.exceptionType = exceptionType;
    }

    /**
     * @return the exceptionType
     */
    public ExceptionType getExceptionType()
    {
        return exceptionType;
    }
}