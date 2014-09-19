package com.nicetcm.nibsplus.scheduler.common;

import com.nicetcm.nibsplus.scheduler.constant.ExceptionType;

/**
 * Schdule모듈의 Exception
 * <pre>
 *
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public class SchduleException extends Exception {

    /**  */
    private static final long serialVersionUID = -5077705237826167451L;
    private final ExceptionType exceptionType;

    public SchduleException(ExceptionType exceptionType, CharSequence exceptionDetail) {
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