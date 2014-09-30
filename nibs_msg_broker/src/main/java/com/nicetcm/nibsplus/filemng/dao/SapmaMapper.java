package com.nicetcm.nibsplus.filemng.dao;

import com.nicetcm.nibsplus.filemng.model.FileMngParameterVO;
import com.nicetcm.nibsplus.filemng.model.TFnSapMasterVO;

public interface SapmaMapper
{
    public int deleteTFnSapMaster(FileMngParameterVO fileMngParameterVO);

    public TFnSapMasterVO selectTFnSapMaster(TFnSapMasterVO tFnSapMasterVO);
}
