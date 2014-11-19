package com.nicetcm.nibsplus.scheduler.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.scheduler.main.NibsScheduleExecuter;
import com.nicetcm.nibsplus.scheduler.model.JobResultVO;
import com.nicetcm.nibsplus.scheduler.model.JobVO;
import com.nicetcm.nibsplus.scheduler.model.SchedulerVO;
import com.nicetcm.nibsplus.scheduler.service.RemoteScheduleExecuter;
import com.nicetcm.nibsplus.scheduler.service.ScheduleInfoProvider;

/**
 * 스케쥴을 Quartz가 아닌 원격에서 호출
 * <pre>
 * 파라메터로 받은 PK정보를 이용, DB에서 단건 추출후 스케쥴 실행
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("NibsRmiScheduleExecuter")
public class NibsRmiScheduleExecuter implements RemoteScheduleExecuter {

    @Resource(name="ScheduleDBInfoProvider")
    private ScheduleInfoProvider scheduleInfoProvider;

    @Override
    public JobResultVO executeJob(JobVO jobVO) throws Exception {
        JobResultVO jobResultVO = new JobResultVO(jobVO);

        SchedulerVO schedulerVO = scheduleInfoProvider.selectScheduleByPk(jobVO);

        try {
            if(schedulerVO == null) {
                jobResultVO.setResultMsg("해당하는 스케쥴이 존재하지 않습니다.");
            } else {
                NibsScheduleExecuter.executeJob(schedulerVO);
                
                jobResultVO.setResultMsg("정상적으로 실행 완료 되었습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            jobResultVO.setResultMsg(e.getMessage());
        }

        return jobResultVO;
    }
}
