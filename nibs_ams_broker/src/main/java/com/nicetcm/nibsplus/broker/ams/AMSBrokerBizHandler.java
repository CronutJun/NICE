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

        logger.warn("remain bytes = " + remain.length + ", befBeContinue = " + befBeContinue + ", beContinue = " + beContinue);
        String fileName = String.format( "%stmp_%s_in", TEMP_FILE_PATH,  Thread.currentThread().getId() );
        if( (!befBeContinue) && (beContinue) ) {
            logger.warn("file open");
            reqJob.setfOut(new FileOutputStream(fileName));

        }
        if( remain.length > 0 && reqJob.getfOut() != null) {
            reqJob.getfOut().write(remain);
            logger.warn("file write length : " + remain.length);
        }

        if( (befBeContinue) && (!beContinue) ) {
            reqJob.getfOut().flush();
            reqJob.getfOut().close();
            reqJob.setfOut( null );
            logger.warn("file close");
        }
        if( !beContinue ) {
            logger.warn("resonse is " + parsed.getResponseInfo().getType()
                    +    ", code is " + parsed.getResponseInfo().getCode()
                    +    ", schema is " +  parsed.getResponseInfo().getSchema() );
            File tg = new File(fileName);
            if( tg.exists() && tg.length() == 0) fileName = "";
            if( !tg.exists() ) fileName = "";
            try {
                if( tg.exists() ) {
                    FileSaver file = (FileSaver)AMSBrokerSpringMain.sprCtx.getBean("fileSaver");
                    fileName = file.tempFileToClassify(amsSafeData, parsed, reqJob, fileName);
                }
                InMsgHandler bizBranch = (InMsgHandler)AMSBrokerSpringMain
                        .sprCtx.getBean("in" + parsed.getString("CM._AOCMsgCode") + parsed.getString("CM._AOCServiceCode"));
                bizBranch.inMsgHandle(amsSafeData, parsed, reqJob, fileName);
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

        befBeContinue = beContinue;
    }

}
