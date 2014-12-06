package com.nicetcm.nibsplus.scheduler.logger;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * MsgBrokerTestLogAppender
 *
 *  MsgBroker 서버의 테스트를 위해 로그를 Thread단위로 분리
 *
 *
 * @author  K.D.J
 * @since   2014.09.20
 */


import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Writer;
import java.util.Properties;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import com.nicetcm.nibsplus.util.NibsBatchUtil;

public class SchedulerLogAppender extends FileAppender {
	
	private static String logFilePath = null;

    public SchedulerLogAppender() {
        super();
    }

    public SchedulerLogAppender(Layout layout, String filename, boolean append) throws IOException {
        super(layout, filename, append);
    }

    public SchedulerLogAppender(Layout layout, String filename) throws IOException {
        super(layout, filename);
    }

    public synchronized void setFile(String fileName, boolean append, boolean bufferedIO, int bufferSize) throws IOException {
    	String serverProfile = System.getProperty("spring.profiles.active");
    	serverProfile = serverProfile.startsWith("stage") ? "stage" : serverProfile;
    	
    	Properties pt = new Properties();
    	pt.load(Class.class.getResourceAsStream("/scheduler/properties/context-scheduler." + serverProfile + ".properties"));
    	
    	logFilePath = pt.getProperty("log.path");
    	super.fileName = fileName;
    	
        super.setFile(getLogFileName(), append, this.bufferedIO, this.bufferSize);
    }

    private String getLogFileName() {
        return String.format("%s/%s/%s", logFilePath, NibsBatchUtil.SysDate(), fileName.substring(fileName.lastIndexOf("/") + 1));
    }

    protected void setQWForFiles(Writer writer) {
        this.qw = new CountingQuietWriter(writer, errorHandler);
    }

    protected void subAppend(LoggingEvent event) {
        if( fileName != null && qw != null &&  getLogFileName().compareTo(this.fileName) != 0  ) {
            this.closeFile();
            try {
                this.setFile( getLogFileName(), true, this.bufferedIO, this.bufferSize );
            }
            catch(IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error("setFile("+fileName+", true) call failed.", e);
            }
        }
        super.subAppend(event);
    }
}
