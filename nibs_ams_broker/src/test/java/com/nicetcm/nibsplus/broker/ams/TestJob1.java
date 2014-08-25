package com.nicetcm.nibsplus.broker.ams;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob1 implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("TestJob1.execute() is Executed... : " + new Date());
    }

}
