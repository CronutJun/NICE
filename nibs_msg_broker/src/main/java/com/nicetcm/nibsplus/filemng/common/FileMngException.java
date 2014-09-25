package com.nicetcm.nibsplus.filemng.common;

import com.nicetcm.nibsplus.orgsend.constant.ExceptionType;

/**
 * FileMng 모듈의 대표 Exception
 * <pre>
 * ExceptionType를 정의해서 사용함
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public class FileMngException extends Exception {

    /**  */
    private static final long serialVersionUID = -8109487094179365103L;
    private final ExceptionType exceptionType;

    public FileMngException(ExceptionType exceptionType, CharSequence exceptionDetail) {
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