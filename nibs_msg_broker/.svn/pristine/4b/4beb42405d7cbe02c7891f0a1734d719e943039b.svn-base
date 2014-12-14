package com.nicetcm.nibsplus.broker.msg;

import java.net.URL;
import java.io.File;
import java.io.FileInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;

public class MsgBrokerClassLoader extends ClassLoader {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerClassLoader.class);

    private URL url;
    private String classNm = null;

    public MsgBrokerClassLoader() throws Exception {

        super(MsgBrokerClassLoader.class.getClassLoader());
        url = new URL(String.format("file://%s", MsgCommon.msgProps.getProperty("hotswap.path", "")));
    }

    protected synchronized Class loadClass(String name, boolean resolve) throws ClassNotFoundException {

        logger.warn("Name: {}, classNm: {}, resolve: {}", name, classNm, resolve);
        if( classNm == null) classNm = name;
        // First, check if the class has already been loaded
        Class c = findLoadedClass(name);
        if( c == null ) {
            if( name.equals(classNm) ) {
                logger.warn("Same class name");
                c = findClass(name);
            }
            else {
                c = super.loadClass(name, resolve);
            }
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }

    protected Class findClass(String name) throws ClassNotFoundException {

        if( name.equals(classNm) ) {
            byte[] buffer = null;
            String path = name.replace('.', '/').concat(".class");
            String filePath = url.getPath() + File.separatorChar + path;
            logger.warn(filePath);
            buffer = readFile(filePath);

            return defineClass(name, buffer, 0, buffer.length);
        }
        else {
            return super.findClass(name);
        }
    }

    private byte[] readFile(String filePath) throws ClassNotFoundException {
        try{
            FileInputStream inStream = new FileInputStream(filePath);
            int length = inStream.available();
            byte[] buffer = new byte[length];

            inStream.read(buffer, 0, length);
            inStream.close();

            return buffer;
        }
        catch( Exception e ) {
            throw new ClassNotFoundException("Could not do IO for "+filePath, e);
        }
    }
}