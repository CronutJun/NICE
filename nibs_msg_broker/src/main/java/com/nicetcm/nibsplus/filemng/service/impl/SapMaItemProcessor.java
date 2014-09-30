package com.nicetcm.nibsplus.filemng.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicetcm.nibsplus.filemng.dao.SapmaMapper;
import com.nicetcm.nibsplus.filemng.model.TFnSapMasterVO;

public class SapMaItemProcessor implements ItemProcessor<TFnSapMasterVO, TFnSapMasterVO>
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SapmaMapper sapmaMapper;

    private String branchCd;

    @Override
    public TFnSapMasterVO process(TFnSapMasterVO tFnSapMasterVO) throws Exception
    {
        if(branchCd != null && branchCd.equals(tFnSapMasterVO.getBranchCd()) == false) {
            logger.info("해당 지점 데이터가 아니라면 skip: job_branchCd={}, file_branchCd={}", branchCd, tFnSapMasterVO.getBranchCd());
            return null;
        }

        TFnSapMasterVO dbTFnSapMasterVO = sapmaMapper.selectTFnSapMaster(tFnSapMasterVO);

        if(dbTFnSapMasterVO != null && dbTFnSapMasterVO.getOfficeConfirm().equals("1")) {
            /* 마감완료일 경우, Skip */
            return null;
        } else {
            /* 마감이 되지 않았을 경우, insert 수행 */
            if(tFnSapMasterVO.getDataType().equals("DT")) {
                return tFnSapMasterVO;
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