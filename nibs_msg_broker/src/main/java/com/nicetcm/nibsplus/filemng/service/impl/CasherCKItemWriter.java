package com.nicetcm.nibsplus.filemng.service.impl;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicetcm.nibsplus.filemng.dao.CasherMapper;
import com.nicetcm.nibsplus.filemng.model.CasherCKVO;

public class CasherCKItemWriter implements ItemWriter<CasherCKVO>
{

    // private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CasherMapper casherMapper;

    @Override
    public void write(List<? extends CasherCKVO> items) throws Exception {
        for(CasherCKVO vo : items) {
        	if (vo.getSzDealDate().length() == 8) {
        		casherMapper.insertCheckDetailData(vo);
        	}
        }
    }

}
