package com.nicetcm.nibsplus.filemng.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicetcm.nibsplus.filemng.dao.SapmaMapper;
import com.nicetcm.nibsplus.filemng.model.TFnSapDetailVO;
import com.nicetcm.nibsplus.filemng.model.TFnSapMasterVO;

public class SapDeItemProcessor implements ItemProcessor<TFnSapDetailVO, TFnSapDetailVO>
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SapmaMapper sapmaMapper;

    private String branchCd;

    @Override
    public TFnSapDetailVO process(TFnSapDetailVO tFnSapDetailVO) throws Exception
    {
        if(branchCd != null && branchCd.equals(tFnSapDetailVO.getBranchCd()) == false) {
            logger.info("해당 지점 데이터가 아니라면 skip: job_branchCd={}, file_branchCd={}", branchCd, tFnSapDetailVO.getBranchCd());
            return null;
        }

        TFnSapMasterVO paramTFnSapMasterVO = new TFnSapMasterVO();
        paramTFnSapMasterVO.setDealDate(tFnSapDetailVO.getDealDate());
        paramTFnSapMasterVO.setBranchCd(tFnSapDetailVO.getBranchCd());
        paramTFnSapMasterVO.setMemberId(tFnSapDetailVO.getMemberId());

        TFnSapMasterVO dbTFnSapMasterVO = sapmaMapper.selectTFnSapMaster(paramTFnSapMasterVO);

        if(dbTFnSapMasterVO != null && dbTFnSapMasterVO.getOfficeConfirm().equals("1")) {
            /* 마감완료일 경우, Skip */
            return null;
        } else {
            /* 마감이 되지 않았을 경우, insert 수행 */
            if(tFnSapDetailVO.getDataType().equals("DT")) {
                return tFnSapDetailVO;
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