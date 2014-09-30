package com.nicetcm.nibsplus.filemng.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.nicetcm.nibsplus.filemng.model.TFnDealNoVO;

public class DealNoItemProcessor implements ItemProcessor<TFnDealNoVO, TFnDealNoVO>
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String dealDate;

    @Override
    public TFnDealNoVO process(TFnDealNoVO tFnDealNoVO) throws Exception
    {
        tFnDealNoVO.setDealDate(dealDate);

        return tFnDealNoVO;
    }

    /**
     * @return the dealDate
     */
    public String getDealDate()
    {
        return dealDate;
    }

    /**
     * @param dealDate the dealDate to set
     */
    public void setDealDate(String dealDate)
    {
        this.dealDate = dealDate;
    }
}