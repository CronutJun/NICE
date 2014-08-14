package com.nicetcm.nibsplus.broker.ams.services;

import java.nio.ByteBuffer;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqInfo;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerTransaction;
import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;

@Service("reqMsg")
public class ReqMsgHandlerImpl implements ReqMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(ReqMsgHandlerImpl.class);

    @Autowired protected SqlSession sqlSession;
    @Autowired protected DataSourceTransactionManager amsTX;


    @Override
    public void reqMsgHandle(AMSBrokerData safeData, AMSBrokerReqJob reqJob, AMSBrokerReqInfo reqInfo) throws Exception {
        safeData.setTXS(amsTX.getTransaction( AMSBrokerTransaction.defAMSTX ));
        try {
            logger.debug("Start reqMsgHandle");
            reqInfo.setDestIP("10.3.28.114");
            reqInfo.setDestPort(33001);
            logger.debug( "TrxCd = {}, ActCd = {}", reqJob.getTrxCd(), reqJob.getActCd() );
            /**
             * 환경정보 조회
             */
            if( reqJob.getTrxCd().equals("1000") && reqJob.getActCd().equals("1000") ) {
                reqInfo.setMsg( ByteBuffer.allocateDirect(MsgCommon.READ_BUF_SIZE) );
                MsgParser msgPsr = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path") + "21002002.json").newMessage(reqInfo.getMsg());
                reqInfo.getMsg().position(0);
                msgPsr.setString("CM._AOCMsgCode",     "2100")
                      .setString("CM._AOCServiceCode", "2002")
                      .setString("CM._AOCMsgSendDate", "20140812")
                      .setString("CM._AOCMsgSendTime", "172600")
                      .setInt   ("CM._AOCMsgLen", msgPsr.getMessageLength() - 9)
                      .syncMessage();
                logger.debug("Message Length = "  + msgPsr.getMessageLength());
                logger.debug("Last Position = "   + msgPsr.lastPosition());
                reqInfo.getMsg().limit(msgPsr.lastPosition());
                byte[] read = new byte[reqInfo.getMsg().limit()];
                reqInfo.getMsg().position(0);
                reqInfo.getMsg().get(read);
                logger.debug(new String(read));
            }
            /**
             * Registry 정보 조회
             */
            else if( reqJob.getTrxCd().equals("2000") && reqJob.getActCd().equals("1000") ) {
                reqInfo.setMsg( ByteBuffer.allocateDirect(MsgCommon.READ_BUF_SIZE) );
                MsgParser msgPsr = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path") + "21002011.json").newMessage(reqInfo.getMsg());
                reqInfo.getMsg().position(0);
                msgPsr.setString( "CM._AOCMsgCode",          "2100"    )
                      .setString( "CM._AOCServiceCode",      "2011"    )
                      .setString( "CM._AOCMsgSendDate",      "20140814")
                      .setString( "CM._AOCMsgSendTime",       "172600" )
                      .setInt   ( "CM._AOCMsgLen",            msgPsr.getMessageLength() - 9 )
                      .setString( "_AocReqRegBaseKey",        reqJob.getReqRegInfo().getBaseKey() )
                      .setString( "_AocReqRegSubKey",         reqJob.getReqRegInfo().getKeyPath() )
                      .setString( "_AocReqRegValueKey",       reqJob.getReqRegInfo().getKeyName() )
                      .syncMessage();
                logger.debug("Message Length = "  + msgPsr.getMessageLength());
                logger.debug("Last Position = "   + msgPsr.lastPosition());
                reqInfo.getMsg().limit(msgPsr.lastPosition());
                byte[] read = new byte[reqInfo.getMsg().limit()];
                reqInfo.getMsg().position(0);
                reqInfo.getMsg().get(read);
                logger.debug(new String(read));
            }
            amsTX.commit(safeData.getTXS());
        }
        catch( Exception e ) {
            amsTX.rollback(safeData.getTXS());
            throw e;
        }
    }

}
