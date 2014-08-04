package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 신규설치
 *
 * <pre>
 * MngSM_SaveMacSetNew
 * </pre>
 *
 *           2014. 08. 04    K.D.J.
 */

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerTransaction;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnNiceTranMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtMacSetMng;

@Service("in02000110")
public class In02000110Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02000110Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnNiceTranMapper fnNiceTranMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TCtMacSetMng macSetMng = new TCtMacSetMng();
        
        macSetMng.setOrgCd      ( parsed.getString("CM.org_cd"      ) ); // 기관코드                                         
        macSetMng.setBranchCd   ( parsed.getString("brch_cd"        ) ); // 지점코드                                         
        macSetMng.setSiteCd     ( parsed.getString("org_site_cd"    ) ); // 사이트코드                                       
        macSetMng.setMacNo      ( parsed.getString("mac_no"         ) ); // 기기 번호                                        
        macSetMng.setSetType    ( "1"                                 ); // 설치구분(2010-신규(1)/이전(2)/폐기(3)/교체(4))   
        macSetMng.setSetDate    ( parsed.getString("set_date"       ) ); // 요청일 (신규/이전/폐기/교체)                     
        macSetMng.setSetTime    ( parsed.getString("set_time"       ) ); // 요청시간                                         
        macSetMng.setOperDate   ( parsed.getString("oper_date"      ) ); // 운영개시예정일                                   
        macSetMng.setSiteNm     ( parsed.getString("org_site_nm"    ) ); // 사이트 명                                        
        macSetMng.setMadeComCd  ( parsed.getString("made_com_cd"    ) ); // 제조사    (2013)                                 
        macSetMng.setMacSeriald ( parsed.getString("mac_seriald"    ) ); // 시리얼번호                                       
        macSetMng.setMacModel   ( parsed.getString("mac_model"      ) ); // 기종      (2012)                                 
        macSetMng.setMacSubModel( parsed.getString("mac_sub_model"  ) ); // 기기 모델                                        
        macSetMng.setOperType   ( parsed.getString("oper_type"      ) ); // 운영 구분 (2014)                                 
        macSetMng.setOperTime   ( parsed.getString("oper_time"      ) ); // 운영 시간 (2015)                                 
        macSetMng.setSetAddr    ( parsed.getString("set_addr"       ) ); // 설치 주소                                        
        macSetMng.setSetPlace   ( parsed.getString("set_place"      ) ); // 설치 장소                                        
        macSetMng.setServiceCd  ( parsed.getString("service_cd"     ) ); // 이전 거리                                        
        macSetMng.setBranchNm   ( parsed.getString("brch_nm"        ) ); // 지점명(지원센터)                                 
        macSetMng.setCircleType ( parsed.getString("circle_type"    ) ); // 회선 TYPE('0':LAN, '1':ASYNC)                    
        macSetMng.setCdMngIp    ( parsed.getString("cd_mng_ip"      ) ); // CD_MANAGER_IP_ADDR                               
        macSetMng.setGwIp       ( parsed.getString("gw_ip"          ) ); // GATEWAY_IP_ADDR                                  
        macSetMng.setBpIp       ( parsed.getString("bp_ip"          ) ); // BP_IP_ADDR                                       
        macSetMng.setBpPort     ( parsed.getString("bp_port"        ) ); // BP_port                                          
        macSetMng.setAtmIp      ( parsed.getString("atm_ip"         ) ); // ATM_IP_ADDR(ASYNC시 LU번호)                      
        macSetMng.setOrgMemo    ( parsed.getString("org_memo"       ) ); // 기관 메모                                        
        macSetMng.setSetSerial  ( parsed.getString("set_seriald"    ) );                                   
        macSetMng.setTransDate  ( parsed.getString("CM.trans_date"  ) );                                   
        macSetMng.setOrgMsgNo   ( parsed.getString("CM.trans_seq_no") );                                   
        macSetMng.setMacLoca    ( parsed.getString("mac_location"   ) ); // 기기위치                                        

        /*
         *  [대구은행]
         *   당일 최종수신으로 UPDATE조건이 있어 다음과 같이 분기한다.
         */
        if( parsed.getString("CM.org_cd").equals(MsgBrokerConst.DGB_CODE) )  {
            if( parsed.getString("info_type").equals(MsgBrokerConst.CD_NEW) ) {
                comPack.insertUpdateMacSet( safeData, MsgBrokerConst.DB_WORK_INSERT, macSetMng );
            }
            else if( parsed.getString("info_type").equals(MsgBrokerConst.CD_MOD) ) {
                comPack.insertUpdateMacSet( safeData, MsgBrokerConst.DB_WORK_UPDATE, macSetMng );
            }
            else if( parsed.getString("info_type").equals(MsgBrokerConst.CD_DEL) ) {
                comPack.insertUpdateMacSet( safeData, MsgBrokerConst.DB_WORK_CANCEL, macSetMng );
            }
        }
        else {
            comPack.insertUpdateMacSet( safeData, MsgBrokerConst.DB_WORK_INSERT, macSetMng );
        }
    }

}
