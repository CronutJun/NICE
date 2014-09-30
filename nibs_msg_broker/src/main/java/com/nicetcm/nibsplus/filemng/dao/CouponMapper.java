package com.nicetcm.nibsplus.filemng.dao;

import com.nicetcm.nibsplus.filemng.model.FileMngParameterVO;
import com.nicetcm.nibsplus.filemng.model.TFnElandCouponVO;

public interface CouponMapper
{
    public int deleteTFnElandCoupon(FileMngParameterVO fileMngParameterVO);

    public TFnElandCouponVO selectTFnElandCoupon(TFnElandCouponVO tFnElandCouponVO);
}
