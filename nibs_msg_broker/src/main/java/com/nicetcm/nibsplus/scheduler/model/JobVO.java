package com.nicetcm.nibsplus.scheduler.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Job에 대한 정보 VO
 * <pre>
 *
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public class JobVO implements Serializable, Cloneable
{

    /**  */
    private static final long serialVersionUID = 4659455824528027431L;

    private String quartzNodeName     ;
    private String jobGroup           ;
    private String jobName            ;
    private String type               ;

    @Override
    public String toString()
    {
        //ToStringStyle style = ToStringStyle.MULTI_LINE_STYLE;
        ToStringStyle style = ToStringStyle.DEFAULT_STYLE;
        ReflectionToStringBuilder tsb = new ReflectionToStringBuilder(this, style);
        return tsb.toString();
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
