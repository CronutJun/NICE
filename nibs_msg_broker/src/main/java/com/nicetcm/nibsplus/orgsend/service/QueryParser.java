package com.nicetcm.nibsplus.orgsend.service;

import java.io.FileNotFoundException;

public interface QueryParser
{
    public String getSql(String queryName, String orgCd) throws FileNotFoundException;
}
