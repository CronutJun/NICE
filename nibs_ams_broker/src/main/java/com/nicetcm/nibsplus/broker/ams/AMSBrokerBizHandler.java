package com.nicetcm.nibsplus.broker.ams;

import static com.nicetcm.nibsplus.broker.ams.AMSBrokerLib.TEMP_FILE_PATH;

import io.netty.channel.ChannelHandlerContext;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.*;
import com.nicetcm.nibsplus.broker.ams.services.InMsgHandler;
import com.nicetcm.nibsplus.broker.ams.services.FileSaver;

public class AMSBrokerBizHandler {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerBizHandler.class);
    private boolean befBeContinue = false;
    private AMSBrokerData amsSafeData = new AMSBrokerData();

    public void classifyMessage(ChannelHandlerContext ctx, Object msg, MsgParser parsed, AMSBrokerReqJob reqJob, byte[] remain, boolean beContinue) throws Exception {

        try {
            logger.warn("remain bytes = " + remain.length + ", befBeContinue = " + befBeContinue + ", beContinue = " + beContinue);
            String classifiedFileName = "";
            if( (!befBeContinue) && (beContinue) ) {
                logger.warn("file open");
                if( reqJob.getUpComplete() != null )
                    reqJob.getUpComplete().reset();

                if( reqJob.getTempFileName() == null ) {
                    reqJob.setTempFileName( String.format("%stmp_%s_in", TEMP_FILE_PATH,  Thread.currentThread().getId()) );
                    File tg = new File(reqJob.getTempFileName());
                    if( tg.exists() ) {
                        if( tg.delete() )
                            logger.warn("Successful force delete temporary file");
                        else
                            logger.warn("Failed force delete temporary file");
                    }
                }
                reqJob.chkTempFile();
                reqJob.setfOut(new FileOutputStream(reqJob.getTempFileName(), true));
            }
            logger.warn("TEMPORARY FILE NAME = [{}]", reqJob.getTempFileName());
            if( remain.length > 0 && reqJob.getfOut() != null) {
                reqJob.getfOut().write(remain);
                if( reqJob.getUpComplete() != null )
                    reqJob.getUpComplete().update( remain, 0, remain.length );
                //reqJob.initRetryCount();
                AMSBrokerLib.printHexData( remain );
                logger.warn("file write length : " + remain.length);
            }

            if( (befBeContinue) && (!beContinue) ) {
                reqJob.getfOut().flush();
                reqJob.getfOut().close();
                reqJob.setfOut( null );
                logger.warn("file close");
            }
            if( !beContinue ) {
                logger.warn("resonse is "   + parsed.getResponseInfo().getType()
                        +    ", code is "   + parsed.getResponseInfo().getCode()
                        +    ", schema is " + parsed.getResponseInfo().getSchema() );
                if( reqJob.getTempFileName() != null && reqJob.getTempFileName().length() > 0 ) {
                    File tg = new File(reqJob.getTempFileName());
                    if( tg.exists() && tg.length() == 0) reqJob.setTempFileName("");
                    if( !tg.exists() ) reqJob.setTempFileName("");
                    try {
                        if( tg.exists() ) {
                            FileSaver file = (FileSaver)AMSBrokerSpringMain.sprCtx.getBean("fileSaver");
                            classifiedFileName = file.tempFileToClassify(amsSafeData, parsed, reqJob, reqJob.getTempFileName());
                        }
                    }
                    finally {
                        if( tg.exists() ) {
                            if( tg.delete() )
                                logger.warn("Successful delete temporary file");
                            else
                                logger.warn("Failed delete temporary file");
                        }
                    }
                }
                reqJob.setReceiveAns(true);
                InMsgHandler bizBranch = (InMsgHandler)AMSBrokerSpringMain
                        .sprCtx.getBean("in" + parsed.getString("CM._AOCMsgCode") + parsed.getString("CM._AOCServiceCode"));
                bizBranch.inMsgHandle( amsSafeData, parsed, reqJob, classifiedFileName );
            }
        }
        finally {
            befBeContinue = beContinue;
        }
    }

}
