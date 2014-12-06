package com.nicetcm.nibsplus.filemng.service.impl;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicetcm.nibsplus.filemng.dao.ElandMapper;
import com.nicetcm.nibsplus.filemng.model.TFnSapDetailVO;

public class ElandSapDetailItemWriter implements ItemWriter<TFnSapDetailVO>{

    // private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ElandMapper elandMapper;

    @Override
    public void write(List<? extends TFnSapDetailVO> items) throws Exception {
        for(TFnSapDetailVO vo : items) {
    		/* 상품권 추가 전 구버전 */
        	elandMapper.deleteElandSapDetail(vo);
        	elandMapper.insertElandSapDetail(vo);
        }
    }

}
