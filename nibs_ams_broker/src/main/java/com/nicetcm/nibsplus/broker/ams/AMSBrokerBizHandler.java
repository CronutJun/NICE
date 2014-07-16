package com.nicetcm.nibsplus.broker.ams;

import io.netty.channel.ChannelHandlerContext;
import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.*;
import com.nicetcm.nibsplus.broker.ams.services.InMsgHandler;
import com.nicetcm.nibsplus.broker.ams.services.RespAckNakHandler;

public class AMSBrokerBizHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerBizHandler.class);
    private boolean befBeContinue = false;
    private FileOutputStream fOut = null;
    
    public void classifyMessage(ChannelHandlerContext ctx, Object msg, MsgParser parsed, byte[] remain, boolean beContinue) throws Exception {
        
        logger.debug("remain bytes = " + remain.length + ", befBeContinue = " + befBeContinue + ", beContinue = " + beContinue);
        String fileName = "tmp_" + Thread.currentThread().getId() + "_in";
        if( (!befBeContinue) && (beContinue) ) {
            logger.debug("file open");
            fOut = new FileOutputStream(fileName);
            
        }
        if( remain.length > 0 ) {
            fOut.write(remain);
            logger.debug("file write length : " + remain.length);
        }
        
        if( (befBeContinue) && (!beContinue) ) {
            fOut.flush();
            fOut.close();
            fOut = null;
            logger.debug("file close");
        }
        if( !beContinue ) {
            try {
                logger.debug("resonse is " + parsed.getResponseInfo().getType()
                        +    ", code is " + parsed.getResponseInfo().getCode()
                        +    ", schema is " +  parsed.getResponseInfo().getSchema() );
                File tg = new File(fileName);
                if( tg.exists() && tg.length() == 0) fileName = "";
                if( !tg.exists() ) fileName = "";
                try {
                    InMsgHandler bizBranch = (InMsgHandler)AMSBrokerSpringMain
                            .sprCtx.getBean("in" + parsed.getString("CM._AOCMsgCode") + parsed.getString("CM._AOCServiceCode"));
                    bizBranch.inMsgHandle(parsed, fileName);
                }
                finally {
                    if( tg.exists() ) {
                        if( tg.delete() )
                            logger.debug("Successful delete temporary file");
                        else
                            logger.debug("Failed delete temporary file");
                    }
                    if( parsed.getResponseInfo().getType().equals("COMMON") ) {
                        logger.debug("resonse is COMMON");
                    }
                    RespAckNakHandler resp = (RespAckNakHandler)AMSBrokerSpringMain
                            .sprCtx.getBean("respAckNak");
                    resp.procAckNak( ctx,  parsed );
                }
            }
            finally {
                parsed.clearMessage();
            }
        }
        
        befBeContinue = beContinue;
    }

}
