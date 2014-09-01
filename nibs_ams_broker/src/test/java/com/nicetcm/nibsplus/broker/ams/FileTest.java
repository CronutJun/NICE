package com.nicetcm.nibsplus.broker.ams;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileTest {

    public FileTest() {
        try {
            File f = new File("D:/CronutWorks/NICE/Test/Test/Test/");
            FileUtils.forceMkdir(f);
        }
        catch( Exception e ) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new FileTest();
    }

}
