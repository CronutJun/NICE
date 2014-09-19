package com.nicetcm.nibsplus.orgsend.service;

import java.io.FileNotFoundException;

/**
 * QRY파일 파싱
 * <pre>
 * AS-IS *.qry파일을 파싱하는 경우 사용
 * TO-BE는 Mybatis XML로 변환되어 필요없음
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public interface QueryParser
{
    public String getSql(String queryName, String orgCd) throws FileNotFoundException;
}
