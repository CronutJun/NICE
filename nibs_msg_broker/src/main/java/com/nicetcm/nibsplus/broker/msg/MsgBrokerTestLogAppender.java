package com.nicetcm.nibsplus.broker.msg;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Writer;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class MsgBrokerTestLogAppender extends FileAppender {

    public MsgBrokerTestLogAppender() {
        super();
    }

    public MsgBrokerTestLogAppender(Layout layout, String filename, boolean append) throws IOException {
        super(layout, filename, append);
    }

    public MsgBrokerTestLogAppender(Layout layout, String filename) throws IOException {
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
            ThrName = "main";
        }
        String fileName = String.format("%s/logs/%s/%s.log", System.getProperty("user.dir"), MsgBrokerLib.SysDate(), ThrName);
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
