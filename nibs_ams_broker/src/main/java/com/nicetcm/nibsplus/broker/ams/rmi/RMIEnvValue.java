package com.nicetcm.nibsplus.broker.ams.rmi;

import java.io.Serializable;

public class RMIEnvValue implements Serializable {

    static final long serialVersionUID = -8621586718025217340L;

    private String envId;
    private String envValue;

    public String getEnvId() {
        return envId;
    }
    public void setEnvId(String envId) {
        this.envId = envId;
    }
    public String getEnvValue() {
        return envValue;
    }
    public void setEnvValue(String envValue) {
        this.envValue = envValue;
    }
}
