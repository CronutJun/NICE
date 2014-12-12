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
    	msgLogger.info(jobKey.getGroup(), jobKey.getName(), getInfo("triggerFired", trigger, null));
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context){
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
    	// JobKey jobKey = trigger.getJobKey();
    	// msgLogger.info(jobKey.getGroup(), jobKey.getName(), String.format("%-20s%s", "triggerMisfired", getInfo(trigger)));
    	errorLogger.error(String.format("%-20s%s", getInfo("triggerMisfired", trigger, null)));
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, CompletedExecutionInstruction triggerInstructionCode) {
    	JobKey jobKey = context.getJobDetail().getKey();
    	msgLogger.info(jobKey.getGroup(), jobKey.getName(), getInfo("triggerComplete", trigger, context));
    }

    private String getInfo(String type, Trigger trigger, JobExecutionContext context) {
        return String.format("%-20s%-25s%s", type, trigger.getJobKey().getGroup() + " " + trigger.getJobKey().getName(), (context != null ? "NextTime : " + context.getNextFireTime() : ""));
    }

}
