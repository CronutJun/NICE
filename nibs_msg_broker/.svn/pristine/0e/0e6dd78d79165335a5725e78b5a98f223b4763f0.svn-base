package com.nicetcm.nibsplus.orgsend.service;

import com.nicetcm.nibsplus.orgsend.model.OrgSendExternalVO;

/**
 * OrgSend의 중요 Interface
 * <pre>
 * msg_type, org_cd를 조건으로 쿼리XML에서 SQL을 가져옴
 * SQL을 실행하여 결과값을 MsgTransferService를 이용하여 MsgBroker모듈과 RMI통신
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public interface NOrgSendService
{
    public void execute(OrgSendExternalVO orgSendExternalVO) throws Exception;
}
