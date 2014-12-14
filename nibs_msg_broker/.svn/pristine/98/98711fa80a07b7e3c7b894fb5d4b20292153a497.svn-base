package com.nicetcm.nibsplus.filemng.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicetcm.nibsplus.filemng.dao.ElandMapper;
import com.nicetcm.nibsplus.filemng.model.TFnElandCouponVO;

public class CouponItemProcessor implements ItemProcessor<TFnElandCouponVO, TFnElandCouponVO>
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ElandMapper elandMapper;

    private String branchCd;

    @Override
    public TFnElandCouponVO process(TFnElandCouponVO tFnElandCouponVO) throws Exception
    {
        if(branchCd != null && branchCd.equals(tFnElandCouponVO.getBranchCd()) == false) {
            logger.info("해당 지점 데이터가 아니라면 skip: job_branchCd={}, file_branchCd={}", branchCd, tFnElandCouponVO.getBranchCd());
            return null;
        }

        TFnElandCouponVO dbTFnElandCouponVO = elandMapper.selectTFnElandCoupon(tFnElandCouponVO);

        if(dbTFnElandCouponVO != null && dbTFnElandCouponVO.getConfirm().equals("Y")) {
            /* 마감완료일 경우, Skip */
            return null;
        } else {
            /* 마감이 되지 않았을 경우, insert 수행 */
            if(tFnElandCouponVO.getDataType().equals("DT")) {
                return tFnElandCouponVO;
            } else {
                return null;
            }
        }
    }

    /**
     * @return the branchCd
     */
    public String getBranchCd()
    {
        return branchCd;
    }

    /**
     * @param branchCd the branchCd to set
     */
    public void setBranchCd(String branchCd)
    {
        this.branchCd = branchCd;
    }
}