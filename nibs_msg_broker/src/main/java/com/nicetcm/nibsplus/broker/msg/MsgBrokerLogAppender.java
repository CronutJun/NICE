package com.nicetcm.nibsplus.broker.msg;

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

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import com.nicetcm.nibsplus.broker.common.MsgCommon;

public class MsgBrokerLogAppender extends FileAppender {

    public MsgBrokerLogAppender() {
        super();
    }

    public MsgBrokerLogAppender(Layout layout, String filename, boolean append) throws IOException {
        super(layout, filename, append);
    }

    public MsgBrokerLogAppender(Layout layout, String filename) throws IOException {
        super(layout, filename);
    }

    public synchronized void setFile(String fileName, boolean append, boolean bufferedIO, int bufferSize) throws IOException {
        super.setFile(getLogFileName(), append, this.bufferedIO, this.bufferSize);
    }

    private String getLogFileName() {
        String ThrName = Thread.currentThread().getName();
        if( ThrName.indexOf("<T>") >= 0 ) {
            String tkn[] = ThrName.split("-");
            ThrName = String.format("%s-%s", tkn[0].substring(3), tkn[1]);
        }
        else {
            ThrName = String.format("main-%s", MsgBrokerMain.serverNo);
        }
        String fileName = String.format("%s/%s/%s.log", MsgCommon.msgProps.getProperty("file.dir.log", System.getProperty("user.dir") + "/logs"), MsgBrokerLib.SysDate(), ThrName);
        return fileName;
    }

    protected void setQWForFiles(Writer writer) {
        this.qw = new CountingQuietWriter(writer, errorHandler);
    }

    protected void subAppend(LoggingEvent event) {
        if( fileName != null && qw != null
        &&  getLogFileName().compareTo(this.fileName) != 0  ) {
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
