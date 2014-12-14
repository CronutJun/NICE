package com.nicetcm.nibsplus.scheduler.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 작업에 대한 결과 VO
 * <pre>
 * 스케쥴정보를 외부에서 호출시, 작업결과를 VO에 담아 리턴함
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public class JobResultVO implements Serializable, Cloneable
{
    /**  */
    private static final long serialVersionUID = 3547708160208160920L;

    private JobVO jobVO;

    private String resultMsg;

    public JobResultVO(JobVO jobVO) {
        this.jobVO = jobVO;
    }

    @Override
    public String toString()
    {
        //ToStringStyle style = ToStringStyle.MULTI_LINE_STYLE;
        ToStringStyle style = ToStringStyle.DEFAULT_STYLE;
        ReflectionToStringBuilder tsb = new ReflectionToStringBuilder(this, style);
        return tsb.toString();
    }

    /**
     * @return the jobVO
     */
    public JobVO getJobVO()
    {
        return jobVO;
    }

    /**
     * @param jobVO the jobVO to set
     */
    public void setJobVO(JobVO jobVO)
    {
        this.jobVO = jobVO;
    }

    /**
     * @return the resultMsg
     */
    public String getResultMsg()
    {
        return resultMsg;
    }

    /**
     * @param resultMsg the resultMsg to set
     */
    public void setResultMsg(String resultMsg)
    {
        this.resultMsg = resultMsg;
    }
}
