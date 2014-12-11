package com.nicetcm.nibsplus.scheduler.listener;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

import com.nicetcm.nibsplus.orgsend.common.MsgLogger;

/**
 * Quartz Listener
 * <pre>
 * Listener등록시 현재 Class 가이드를 이용할것
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public class OrgSendAllTriggerListener implements TriggerListener
{

    private final Logger errorLogger = Logger.getLogger("error");

    private MsgLogger msgLogger;
    private String name;

    public OrgSendAllTriggerListener(MsgLogger msgLogger, String name) {
    	this.msgLogger = msgLogger;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
    	JobKey jobKey = trigger.getJobKey();
    	msgLogger.info(jobKey.getGroup(), jobKey.getName(), String.format("%-20s%s", "triggerFired", getInfo(trigger)));
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context){
    	JobKey jobKey = trigger.getJobKey();
    	msgLogger.info(jobKey.getGroup(), jobKey.getName(), String.format("%-20s%s", "vetoJobExecution", getInfo(trigger)));
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
    	// JobKey jobKey = trigger.getJobKey();
    	// msgLogger.info(jobKey.getGroup(), jobKey.getName(), String.format("%-20s%s", "triggerMisfired", getInfo(trigger)));
    	errorLogger.error(String.format("%-20s%s", "triggerMisfired", getInfo(trigger)));
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, CompletedExecutionInstruction triggerInstructionCode) {
    	JobKey jobKey = context.getJobDetail().getKey();
    	msgLogger.info(jobKey.getGroup(), jobKey.getName(), String.format("%-20s%s", "triggerComplete", getInfo(trigger)));

    }

    private String getInfo(Trigger trigger) {
        return new StringBuilder()
        .append(String.format("%-30s %s", trigger.getJobKey().getGroup() + " " + trigger.getJobKey().getName(), "S:" + trigger.getStartTime() + (trigger.getEndTime() != null ? (" E:" + trigger.getEndTime()) : "") + (trigger.getFinalFireTime() != null ? (" F:" + trigger.getFinalFireTime()) : "")))
        .toString();
    }

}
