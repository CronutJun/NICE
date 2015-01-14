package com.nicetcm.nibsplus.broker.ams.services;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerLib;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerMain;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerSchedJob;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerTransaction;
import com.nicetcm.nibsplus.broker.ams.mapper.TPmUpdsSchedMapper;
import com.nicetcm.nibsplus.broker.ams.model.TPmUpdsSchedSpec;
import com.nicetcm.nibsplus.broker.ams.model.TPmUpdsSched;
import com.nicetcm.nibsplus.broker.common.MsgCommon;

/*
 * Copyright 2014 The NIBS Project
 *
 * AMS 기기관리시스템 - InitSchedulerImpl
 *
 * 프로그램 시작시 배포 및 저널업로드 스케쥴 초기화 작업
 *
 * @author  K.D.J
 * @since   2014.09.01
 */

@Service("initScheduler")
public class InitSchedulerImpl implements InitScheduler {

    private static final Logger logger = LoggerFactory.getLogger(InitSchedulerImpl.class);

    @Autowired protected SqlSession sqlSession;
    @Autowired protected DataSourceTransactionManager amsTX;

    @Autowired private   CommonPack                   comPack;
    @Autowired private   TPmUpdsSchedMapper           updsSchedMap;

    @Override
    public void initSchedule( AMSBrokerData safeData ) throws Exception {

        safeData.setSysDate( AMSBrokerLib.getSysDate() );

        safeData.setTXS( amsTX.getTransaction( AMSBrokerTransaction.defAMSTX ));
        safeData.setMsgDate( AMSBrokerLib.getMsgDate(safeData.getSysDate()) );
        safeData.setMsgTime( AMSBrokerLib.getMsgTime(safeData.getSysDate()) );

        try {
            /**
             * 배포스케쥴 초기화
             */
            TPmUpdsSchedSpec updsSchedSpec = new TPmUpdsSchedSpec();
            updsSchedSpec.createCriteria().andDeployDateGreaterThanOrEqualTo(safeData.getMsgDate())
                                          .andDeployTimeGreaterThanOrEqualTo(safeData.getMsgTime());
            List<TPmUpdsSched> updsScheds = updsSchedMap.selectBySpec(updsSchedSpec);
            for( TPmUpdsSched updsSched: updsScheds ) {
                AMSBrokerMain.getScheduler().unscheduleJob( TriggerKey.triggerKey(updsSched.getVerId(), updsSched.getGrpCd()) );

                JobDetail updJob = newJob(AMSBrokerSchedJob.class)
                                 .withIdentity("UPDATES",  String.format("%-4.4s%-4.4s", updsSched.getMkrCd(), updsSched.getModelCd()))
                                 .build();

                CronTrigger updTrig = newTrigger()
                                    .withIdentity( updsSched.getVerId(), updsSched.getGrpCd() )
                                    .withSchedule( cronSchedule(String.format("%s %s %s %s %s ? %s",
                                                                              updsSched.getDeployTime().substring(4),
                                                                              updsSched.getDeployTime().substring(2, 4),
                                                                              updsSched.getDeployTime().substring(0, 2),
                                                                              updsSched.getDeployDate().substring(6),
                                                                              updsSched.getDeployDate().substring(4, 6),
                                                                              updsSched.getDeployDate().substring(0, 4))) )
                                    .build();
                AMSBrokerMain.getScheduler().scheduleJob( updJob, updTrig );
                logger.warn("Job is successfully scheduled [Group = {}, Version = {}, deployDate = {}, deployTime = {}",
                            updsSched.getGrpCd(), updsSched.getVerId(), updsSched.getDeployDate(), updsSched.getDeployTime());
            }
            /**
             * 저널스케쥴 초기화
             */
            String uploadTime = MsgCommon.msgProps.getProperty("schedule.journal.upload.time");
            if( uploadTime != null && uploadTime.length() == 6 ) {
                AMSBrokerMain.getScheduler().unscheduleJob( TriggerKey.triggerKey("JOURNAL", "JOURNAL") );

                JobDetail jnlJob = newJob(AMSBrokerSchedJob.class)
                                 .withIdentity("JOURNAL",  "JOURNAL")
                                 .build();

                CronTrigger jnlTrig = newTrigger()
                                    .withIdentity( "JOURNAL", "JOURNAL" )
                                    .withSchedule( cronSchedule(String.format("%s %s %s %s %s ?",
                                                                              uploadTime.substring(4),
                                                                              uploadTime.substring(2, 4),
                                                                              uploadTime.substring(0, 2),
                                                                              "*",
                                                                              "*")) )
                                    .build();
                AMSBrokerMain.getScheduler().scheduleJob( jnlJob, jnlTrig );
                logger.info("Job is successfully scheduled [uploadTime = {}]", String.format("%s %s %s %s %s ?",
                                                                                        uploadTime.substring(4),
                                                                                        uploadTime.substring(2, 4),
                                                                                        uploadTime.substring(0, 2),
                                                                                        "*",
                                                                                        "*"));
            }

            amsTX.commit(safeData.getTXS());
        }
        catch( Exception e ) {
            logger.warn("InitScheduler has error [{}]", e.getMessage() );
            amsTX.rollback(safeData.getTXS());
            throw e;
        }

    }

}
