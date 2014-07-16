package com.nicetcm.nibsplus.broker.common;

import java.nio.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ThrData {
    int                          pos;
    boolean                      isLive;
    int                          messageLength;
    public ByteBuffer            msg;
    public Map<String, MsgData>  msgDatMap = new LinkedHashMap<String, MsgData>();
}
