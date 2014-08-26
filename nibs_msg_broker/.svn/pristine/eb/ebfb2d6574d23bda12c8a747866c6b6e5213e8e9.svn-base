package com.nicetcm.nibsplus.orgsend.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.orgsend.service.QueryParser;

@Service("QryFileParser")
public class QryFileParser implements QueryParser
{
    @Override
    public String getSql(String queryName, String orgCd) throws FileNotFoundException {

        StringBuilder sql = new StringBuilder();

        FileInputStream fis = new FileInputStream("D:\\Project_NIBS\\Workspace_new_nibsplus\\nibs_msg_broker_NEW_SVN\\src\\main\\resources\\org_send\\qry\\ARRIVAL_031.qry");
        Scanner s = new Scanner(fis, "EUC-KR");

        for(int i = 0; s.hasNext(); i++) {
            String line = s.nextLine();
            if(i >= 5) {
                sql.append(line);
            }
        }
        return sql.toString();
    }

    @Test
    public void test() {
        QryFileParser QryFileParser = new QryFileParser();
        try
        {
            System.out.println(QryFileParser.getSql("", ""));
        } catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
