package com.nicetcm.nibsplus.scheduler.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.scheduler.dao.SchedulerMapper;
import com.nicetcm.nibsplus.scheduler.model.JobVO;
import com.nicetcm.nibsplus.scheduler.model.SchedulerVO;
import com.nicetcm.nibsplus.scheduler.service.ScheduleInfoProvider;

/**
 * 스케줄정보 제공
 * <pre>
 * 1개의 스케쥴을 실행하기위한 정보를 DB에서 가져옴
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("ScheduleDBInfoProvider")
public class ScheduleDBInfoProvider implements ScheduleInfoProvider
{
    @Autowired
    private SchedulerMapper schedulerMapper;

    private Map<String, ApplicationContext> applicationContextMap = new ConcurrentHashMap<String, ApplicationContext>();

    @Override
    public List<SchedulerVO> selectEnableSchedule(String quartzNodeName) {
        return schedulerMapper.selectEnableSchedule(quartzNodeName);
    }

    @Override
    public SchedulerVO selectScheduleByPk(JobVO jobVO) {
        return schedulerMapper.selectScheduleByPk(jobVO);
    }

    @Override
    public ApplicationContext getApplicationContext(String springContextXml) {
        ApplicationContext applicationContext;

        synchronized (applicationContextMap)
        {
            applicationContext = applicationContextMap.get(springContextXml);
            if (applicationContext == null)
            {
                try
                {
                    applicationContext = new ClassPathXmlApplicationContext(trimStringArray(springContextXml.split(",")));
                    applicationContextMap.put(springContextXml, applicationContext);
                } catch (BeansException e)
                {
                    // TODO Auto-generated catch block
                    throw e;
                }
            }
        }

        return applicationContext;
    }

    private String[] trimStringArray(String[] tempSpringContextXmlArr) {
        String[] springContextXmlArr = new String[tempSpringContextXmlArr.length];

        int i = 0;
        for(String finalSpringContextXml : tempSpringContextXmlArr) {
            springContextXmlArr[i] = finalSpringContextXml.trim();
            i++;
        }

        return springContextXmlArr;
    }

    public static void main(String[] args) {

        String[] strArr = "classpath:org_send/spring/context-orgsend.xml, classpath:org_send/spring/context-orgsend-bean.xml".split(",");

        int i = 0;
        for(String xml : strArr) {
            System.out.println("[" + i + "][" + xml.trim() + "]");
        }
    }
}
