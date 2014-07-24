package com.nicetcm.nibsplus.broker.msg.rmi;

import java.util.List;
import com.nicetcm.nibsplus.broker.common.*;

public interface MsgBrokerCallBack<PT>{

    public void doPreCallBroker(MsgParser parsed, PT params) throws Exception;
    public void doPostCallBroker(MsgParser parsed, PT params) throws Exception;

}
