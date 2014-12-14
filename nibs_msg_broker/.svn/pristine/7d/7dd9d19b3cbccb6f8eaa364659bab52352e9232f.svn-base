package com.nicetcm.nibsplus.filemng.dao;

import com.nicetcm.nibsplus.filemng.model.FileMngParameterVO;
import com.nicetcm.nibsplus.filemng.model.TFnElandCouponVO;
import com.nicetcm.nibsplus.filemng.model.TFnSapDetailVO;
import com.nicetcm.nibsplus.filemng.model.TFnSapMasterVO;


public interface ElandMapper {

    public int deleteTFnSapMaster(FileMngParameterVO fileMngParameterVO);
    public TFnSapMasterVO selectTFnSapMaster(TFnSapMasterVO tFnSapMasterVO);

    public int deleteTFnSapDetail(FileMngParameterVO fileMngParameterVO);
    public TFnSapDetailVO selectTFnSapDetail(TFnSapMasterVO tFnSapMasterVO);

    public int deleteTFnElandCoupon(FileMngParameterVO fileMngParameterVO);
    public TFnElandCouponVO selectTFnElandCoupon(TFnElandCouponVO tFnElandCouponVO);
    
	void deleteElandSapMaster(TFnSapMasterVO vo);
	void insertElandSapMaster(TFnSapMasterVO vo);

	void deleteElandSapDetail(TFnSapDetailVO vo);
	void insertElandSapDetail(TFnSapDetailVO vo);

	void deleteElandCoupon(TFnElandCouponVO vo);
	void insertElandCoupon(TFnElandCouponVO vo);

}
