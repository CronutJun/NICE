package com.nicetcm.nibsplus.scheduler.common;

public class AbstractJobVO {
	private String quartzNodeName;
	private String jobGroup;
	private String jobName;
	private String useYn;
	/**
	 * @return the quartzNodeName
	 */
	public String getQuartzNodeName() {
		return quartzNodeName;
	}
	/**
	 * @param quartzNodeName the quartzNodeName to set
	 */
	public void setQuartzNodeName(String quartzNodeName) {
		this.quartzNodeName = quartzNodeName;
	}
	/**
	 * @return the jobGroup
	 */
	public String getJobGroup() {
		return jobGroup;
	}
	/**
	 * @param jobGroup the jobGroup to set
	 */
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}
	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}
	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	
}
