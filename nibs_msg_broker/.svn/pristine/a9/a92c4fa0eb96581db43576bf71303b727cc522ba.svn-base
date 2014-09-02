package com.nicetcm.nibsplus.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class NiceConfig {
    private static HashMap<String, String> map = null;

    static {
        String springProfiles = System.getProperty("spring.profiles.active");

        try {

            String prefix = "org_send/properties/context-orgsend.";

            if(springProfiles == null || springProfiles.equals("") || springProfiles.startsWith("local")) {
                springProfiles = "local";
            }

            String propFileName = prefix + springProfiles + ".properties";
            System.out.println("profileName: " + propFileName);

            InputStream inputStream = NiceConfig.class.getClassLoader().getResourceAsStream(propFileName);

            Properties prop = new Properties();
            prop.load(inputStream);
            Set<String> set = prop.stringPropertyNames();

            map = new HashMap<String, String>();
            for (String name : set) {
                map.put(name, prop.getProperty(name));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }
    }

    public static String getProp(String name) {
        String val = null;
        try {
            val = map.get(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }

    public static void main(String[] args) throws Exception {
        //System.out.println(PFPropertis.getProp("setl.jdbc.url"));
        //load();
    }
}
