package com.nicetcm.nibsplus.scheduler.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String name;

    public OrgSendAllTriggerListener(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context)
    {

        logger.info("◈◈◈ triggerFired ◈◈◈ {}", getInfo(trigger, context));

    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context)
    {
        logger.info("◈◈◈ vetoJobExecution ◈◈◈ {}", getInfo(trigger, context));
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger)
    {
        logger.info("◈◈◈ triggerMisfired ◈◈◈");

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, CompletedExecutionInstruction triggerInstructionCode)
    {
        logger.info("◈◈◈ triggerComplete ◈◈◈ {}", getInfo(trigger, context));

    }

    private String getInfo(Trigger trigger, JobExecutionContext context) {
        return new StringBuilder()
        .append(context.getJobDetail().getKey().getGroup() + "/" + context.getJobDetail().getKey().getName())
        .append(" = ")
        .append("S:" + trigger.getStartTime() + " E:" + trigger.getEndTime() + " F:" + trigger.getFinalFireTime())
        .toString();
    }

}
