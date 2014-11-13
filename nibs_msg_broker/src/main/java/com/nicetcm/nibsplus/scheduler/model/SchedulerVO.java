package com.nicetcm.nibsplus.scheduler.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 스케쥴정보 DB Table Column과 동일(T_CM_SCHEDULE)
 * <pre>
 *
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public class SchedulerVO
{
    private String quartzNodeName     ;
    private String jobGroup           ;
    private String jobName            ;
    private String jobExplain         ;
    private String cronExpression     ;
    private String status             ;
    private String realTimeCommand    ;
    private String tArg1              ;
    private String tArg2              ;
    private String tArg3              ;
    private String tArg4              ;
    private int jobPriority          ;
    private String jobClass           ;
    private String springContextXml   ;
    private String jobListenerClass   ;
    private String useYn              ;
    private String regmnId            ;
    private String regYmd             ;


    @Override
    public String toString()
    {
        //ToStringStyle style = ToStringStyle.MULTI_LINE_STYLE;
        ToStringStyle style = ToStringStyle.DEFAULT_STYLE;
        ReflectionToStringBuilder tsb = new ReflectionToStringBuilder(this, style);
        return tsb.toString();
    }

    public String toPrettyString()
    {
        return new StringBuilder()
        .append("[")
        .append("NODE_NAME: ").append(quartzNodeName).append(" / ")
        .append("JOB_GROUP: ").append(jobGroup).append(" / ")
        .append("JOB_NAME : ").append(jobName).append(" / ")
        .append("EXPRESSION : ").append(cronExpression).append(" / ")
        .append("]")
        .toString();
    }

    /**
     * @return the quartzNodeName
     */
    public String getQuartzNodeName()
    {
        return quartzNodeName;
    }


    /**
     * @param quartzNodeName the quartzNodeName to set
     */
    public void setQuartzNodeName(String quartzNodeName)
    {
        this.quartzNodeName = quartzNodeName;
    }


    /**
     * @return the jobGroup
     */
    public String getJobGroup()
    {
        return jobGroup;
    }


    /**
     * @param jobGroup the jobGroup to set
     */
    public void setJobGroup(String jobGroup)
    {
        this.jobGroup = jobGroup;
    }


    /**
     * @return the jobName
     */
    public String getJobName()
    {
        return jobName;
    }


    /**
     * @param jobName the jobName to set
     */
    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }


    /**
     * @return the jobExplain
     */
    public String getJobExplain()
    {
        return jobExplain;
    }


    /**
     * @param jobExplain the jobExplain to set
     */
    public void setJobExplain(String jobExplain)
    {
        this.jobExplain = jobExplain;
    }


    /**
     * @return the cronExpression
     */
    public String getCronExpression()
    {
        return cronExpression;
    }


    /**
     * @param cronExpression the cronExpression to set
     */
    public void setCronExpression(String cronExpression)
    {
        this.cronExpression = cronExpression;
    }


    /**
     * @return the status
     */
    public String getStatus()
    {
        return status;
    }


    /**
     * @param status the status to set
     */
    public void setStatus(String status)
    {
        this.status = status;
    }


    /**
     * @return the realTimeCommand
     */
    public String getRealTimeCommand()
    {
        return realTimeCommand;
    }


    /**
     * @param realTimeCommand the realTimeCommand to set
     */
    public void setRealTimeCommand(String realTimeCommand)
    {
        this.realTimeCommand = realTimeCommand;
    }


    /**
     * @return the tArg1
     */
    public String gettArg1()
    {
        return tArg1;
    }


    /**
     * @param tArg1 the tArg1 to set
     */
    public void settArg1(String tArg1)
    {
        this.tArg1 = tArg1;
    }


    /**
     * @return the tArg2
     */
    public String gettArg2()
    {
        return tArg2;
    }


    /**
     * @param tArg2 the tArg2 to set
     */
    public void settArg2(String tArg2)
    {
        this.tArg2 = tArg2;
    }


    /**
     * @return the tArg3
     */
    public String gettArg3()
    {
        return tArg3;
    }


    /**
     * @param tArg3 the tArg3 to set
     */
    public void settArg3(String tArg3)
    {
        this.tArg3 = tArg3;
    }


    /**
     * @return the tArg4
     */
    public String gettArg4()
    {
        return tArg4;
    }


    /**
     * @param tArg4 the tArg4 to set
     */
    public void settArg4(String tArg4)
    {
        this.tArg4 = tArg4;
    }


    /**
     * @return the jobPriority
     */
    public int getJobPriority()
    {
        return jobPriority;
    }


    /**
     * @param jobPriority the jobPriority to set
     */
    public void setJobPriority(int jobPriority)
    {
        this.jobPriority = jobPriority;
    }


    /**
     * @return the jobClass
     */
    public String getJobClass()
    {
        return jobClass;
    }


    /**
     * @param jobClass the jobClass to set
     */
    public void setJobClass(String jobClass)
    {
        this.jobClass = jobClass;
    }


    /**
     * @return the springContextXml
     */
    public String getSpringContextXml()
    {
        return springContextXml;
    }


    /**
     * @param springContextXml the springContextXml to set
     */
    public void setSpringContextXml(String springContextXml)
    {
        this.springContextXml = springContextXml;
    }


    /**
     * @return the jobListenerClass
     */
    public String getJobListenerClass()
    {
        return jobListenerClass;
    }


    /**
     * @param jobListenerClass the jobListenerClass to set
     */
    public void setJobListenerClass(String jobListenerClass)
    {
        this.jobListenerClass = jobListenerClass;
    }


    /**
     * @return the useYn
     */
    public String getUseYn()
    {
        return useYn;
    }


    /**
     * @param useYn the useYn to set
     */
    public void setUseYn(String useYn)
    {
        this.useYn = useYn;
    }


    /**
     * @return the regmnId
     */
    public String getRegmnId()
    {
        return regmnId;
    }


    /**
     * @param regmnId the regmnId to set
     */
    public void setRegmnId(String regmnId)
    {
        this.regmnId = regmnId;
    }


    /**
     * @return the regYmd
     */
    public String getRegYmd()
    {
        return regYmd;
    }


    /**
     * @param regYmd the regYmd to set
     */
    public void setRegYmd(String regYmd)
    {
        this.regYmd = regYmd;
    }
}
