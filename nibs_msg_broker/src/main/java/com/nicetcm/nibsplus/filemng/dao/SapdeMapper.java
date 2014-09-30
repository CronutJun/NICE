package com.nicetcm.nibsplus.filemng.dao;

import com.nicetcm.nibsplus.filemng.model.FileMngParameterVO;
import com.nicetcm.nibsplus.filemng.model.TFnSapDetailVO;
import com.nicetcm.nibsplus.filemng.model.TFnSapMasterVO;

public interface SapdeMapper
{
    public int deleteTFnSapDetail(FileMngParameterVO fileMngParameterVO);

    public TFnSapDetailVO selectTFnSapDetail(TFnSapMasterVO tFnSapMasterVO);


}
