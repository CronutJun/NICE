package com.nicetcm.nibsplus.sqlservice.service;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import org.quartz.SchedulerException;

public interface ScheduleExecuter {

	void startSchedule(LinkedHashMap<String, LinkedList<String>> scheduledJob, LinkedHashMap<String, String> scheduledJobExpression) throws SchedulerException;
}
