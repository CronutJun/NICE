package com.nicetcm.nibsplus.broker.ams.rmi;

import java.io.Serializable;

public class RMIReqRegInfo implements Serializable {

    static final long serialVersionUID = -8202559472879616531L;

    private String macNo;
    private String baseKey;
    private String keyPath;
    private String keyName;

    public String getMacNo() {
        return macNo;
    }
    
    public void setMacNo(String macNo) {
        this.macNo = macNo;
    }
    
    public String getBaseKey() {
        return baseKey;
    }
    
    public void setBaseKey(String baseKey) {
        this.baseKey = baseKey;
    }
    
    public String getKeyPath() {
        return keyPath;
    }
    
    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }
    
    public String getKeyName() {
        return keyName;
    }
    
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
}
