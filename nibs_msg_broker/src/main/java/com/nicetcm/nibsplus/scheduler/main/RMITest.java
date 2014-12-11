/** 
 * com.nicetcm.nibsplus.scheduler.main.RMITest
 * 프로젝트명 : 차세대 운영관리시스템 구축
 * 작성일 : 2014. 12. 9.
 * 
 * Copyright ©2014 NICE TCM All rights reserved.
 */
package com.nicetcm.nibsplus.scheduler.main;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.nicetcm.nibsplus.scheduler.model.JobVO;
import com.nicetcm.nibsplus.scheduler.service.RemoteScheduleExecuter;

/**
 * 여기에 클래스(한글)명.
 * <pre>
 * 여기에 클래스 설명 및 변경 이력을 기술하십시오.
 * </pre>
 * 
 * @author 박상철
 * @version 1.0
 * @see
 */
public class RMITest {

	public static void main(String[] args) throws Exception {
		Registry l = LocateRegistry.getRegistry("127.0.0.1", 10399);
		RemoteScheduleExecuter s = (RemoteScheduleExecuter)l.lookup("NibsRmiScheduleExecuter");
		
		JobVO jobVO = new JobVO();
		jobVO.setQuartzNodeName("OrgSend");
		jobVO.setJobGroup("ADD_CASH");
		jobVO.setJobName("003");
		
		s.executeJob(jobVO);
	}
	
}
