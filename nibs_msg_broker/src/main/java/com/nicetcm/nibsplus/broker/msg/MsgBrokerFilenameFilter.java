package com.nicetcm.nibsplus.broker.msg;

import java.io.File;
import java.io.FilenameFilter;

public class MsgBrokerFilenameFilter implements FilenameFilter {

    private String searchPattern;

    public MsgBrokerFilenameFilter(String pattern) {
        this.searchPattern = pattern;
    }

    @Override
    public boolean accept(File dir, String name) {
        if( name.equals(searchPattern) )
            return true;
        else
            return false;
    }

}
