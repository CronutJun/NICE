/**
 * com.nicetcm.nibsplus.broker.msg.model.MsgBrokerClientVO
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 7. 24.
 *
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.broker.msg.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 여기에 클래스(한글)명.
 * <pre>
 * 여기에 클래스 설명 및 변경 이력을 기술하십시오.
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public class MsgBrokerConf
{
    private String orgCd;
    private String formatType;
    private String msgType;
    private String workType;

    /**
     * <pre>
     * 여기에 Constructors 설명을 삽입하십시오.
     * </pre>
     *
     * @param orgCd
     * @param formatType
     * @param msgType
     * @param workType
     */
    public MsgBrokerConf(String orgCd, String formatType, String msgType, String workType)
    {
        super();
        this.orgCd = orgCd;
        this.formatType = formatType;
        this.msgType = msgType;
        this.workType = workType;
    }

    /**
     * @return the orgCd
     */
    public String getOrgCd()
    {
        return orgCd;
    }
    /**
     * @param orgCd the orgCd to set
     */
    public void setOrgCd(String orgCd)
    {
        this.orgCd = orgCd;
    }
    /**
     * @return the formatType
     */
    public String getFormatType()
    {
        return formatType;
    }
    /**
     * @param formatType the formatType to set
     */
    public void setFormatType(String formatType)
    {
        this.formatType = formatType;
    }
    /**
     * @return the msgType
     */
    public String getMsgType()
    {
        return msgType;
    }
    /**
     * @param msgType the msgType to set
     */
    public void setMsgType(String msgType)
    {
        this.msgType = msgType;
    }
    /**
     * @return the workType
     */
    public String getWorkType()
    {
        return workType;
    }
    /**
     * @param workType the workType to set
     */
    public void setWorkType(String workType)
    {
        this.workType = workType;
    }

    @Override
    public String toString()
    {
        //ToStringStyle style = ToStringStyle.MULTI_LINE_STYLE;
        ToStringStyle style = ToStringStyle.DEFAULT_STYLE;
        ReflectionToStringBuilder tsb = new ReflectionToStringBuilder(this, style);
        return tsb.toString();
    }
}
