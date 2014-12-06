package com.nicetcm.nibsplus.filemng.service.impl;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicetcm.nibsplus.filemng.dao.ElandMapper;
import com.nicetcm.nibsplus.filemng.model.TFnSapMasterVO;

public class ElandSapMasterItemWriter implements ItemWriter<TFnSapMasterVO>{

    // private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ElandMapper elandMapper;

    @Override
    public void write(List<? extends TFnSapMasterVO> items) throws Exception {
        for(TFnSapMasterVO vo : items) {
    		/* 상품권 추가 전 구버전 */
        	elandMapper.deleteElandSapMaster(vo);
        	elandMapper.insertElandSapMaster(vo);
        }
    }

}
