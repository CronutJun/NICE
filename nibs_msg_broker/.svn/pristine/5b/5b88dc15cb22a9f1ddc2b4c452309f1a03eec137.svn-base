package com.nicetcm.nibsplus.filemng.dao;

import org.apache.ibatis.annotations.Param;

import com.nicetcm.nibsplus.filemng.model.FileMngParameterVO;
import com.nicetcm.nibsplus.filemng.model.TFnDealNoVO;

public interface DealNoMapper
{
    public int deleteTFnHostDealNo(FileMngParameterVO fileMngParameterVO);

    public int updateTFnHostDealNo(FileMngParameterVO fileMngParameterVO);

    public TFnDealNoVO[] selectTFnHostDealNo(FileMngParameterVO fileMngParameterVO);

    public void spIfSendSMSTranCntMismatch(@Param("command") String command);
}
