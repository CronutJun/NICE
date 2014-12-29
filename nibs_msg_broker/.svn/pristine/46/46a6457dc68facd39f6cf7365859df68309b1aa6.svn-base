package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker CD-VAN 환경점검
 *
 * <pre>
 * MngNC_AP_SaveEnvChk
 * </pre>
 *
 *           2014. 07. 31    K.D.J.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmNiceEnvCheckMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmNiceEnvCheck;

@Service("inN3000700")
public class InN3000700Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN3000700Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TCmNiceEnvCheckMapper cmNiceEnvCheckMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmNiceEnvCheck cmNiceEnvCheck = new TCmNiceEnvCheck();

        cmNiceEnvCheck.setOrgCd           ( "096"                                  );
        cmNiceEnvCheck.setBranchCd        ( "9600"                                 );
        cmNiceEnvCheck.setMacNo           ( parsed.getString("mac_no"            ) );
        cmNiceEnvCheck.setCheckDate       ( parsed.getString("check_date"        ) );
        cmNiceEnvCheck.setCheckTime       ( parsed.getString("check_time"        ) );
        cmNiceEnvCheck.setBoothTopFront   ( parsed.getString("booth_top_front"   ) );
        cmNiceEnvCheck.setBoothTopBack    ( parsed.getString("booth_top_back"    ) );
        cmNiceEnvCheck.setFrontBoard      ( parsed.getString("front_board"       ) );
        cmNiceEnvCheck.setLeftBoard       ( parsed.getString("left_board"        ) );
        cmNiceEnvCheck.setRightBoard      ( parsed.getString("right_board"       ) );
        cmNiceEnvCheck.setBackBoard       ( parsed.getString("back_board"        ) );
        cmNiceEnvCheck.setCleanInout      ( parsed.getString("clean_inout"       ) );
        cmNiceEnvCheck.setDoorState       ( parsed.getString("door_state"        ) );
        cmNiceEnvCheck.setLightInsideState( parsed.getString("light_inside_state") );
        cmNiceEnvCheck.setAedState        ( parsed.getString("aed_state"         ) );
        cmNiceEnvCheck.setAirconState     ( parsed.getString("aircon_state"      ) );
        cmNiceEnvCheck.setAtmWaitingState ( parsed.getString("ATM_waiting_state" ) );
        cmNiceEnvCheck.setAtmBroken       ( parsed.getString("ATM_broken"        ) );
        cmNiceEnvCheck.setInterphoneState ( parsed.getString("interphone_state"  ) );
        cmNiceEnvCheck.setExecComp        ( parsed.getString("exec_comp"         ) );
        cmNiceEnvCheck.setExecutant       ( parsed.getString("executant"         ) );
        cmNiceEnvCheck.setOrgSendYn       ( "0"                                    );
        cmNiceEnvCheck.setUpdateDate      ( safeData.getDSysDate()                 );
        cmNiceEnvCheck.setUpdateUid       ( "online"                               );

        try {
            cmNiceEnvCheckMap.insertSelective( cmNiceEnvCheck );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                cmNiceEnvCheckMap.updateByPrimaryKeySelective( cmNiceEnvCheck );
            }
            catch( Exception e ) {
                logger.warn( "[T_CM_NICE_ENV_CHECK] Update Error [%.200s]\n", e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.warn( "[T_CM_NICE_ENV_CHECK] Insert Error {}", e.getLocalizedMessage() );
            throw e;
        }
    }
}
