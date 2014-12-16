package com.nicetcm.nibsplus.filemng.service.impl;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicetcm.nibsplus.filemng.dao.CasherMapper;
import com.nicetcm.nibsplus.filemng.model.CasherTRVO;

public class CasherTRItemWriter implements ItemWriter<CasherTRVO>
{

    // private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CasherMapper casherMapper;

    @Override
    public void write(List<? extends CasherTRVO> items) throws Exception {
        for(CasherTRVO vo : items) {
        	if (vo.getSzDeal_Date().length() == 8) {
    	    	if (Integer.parseInt(vo.getSzDevicever_Id()) >= Integer.parseInt("20022")) {
    	        	/* 상품권이 추가된 신 버전 */
    	    		casherMapper.deleteCasherTranDataNew(vo);
    	    		casherMapper.insertCasherTranDataNew(vo);
    	    	} else {
    	    		/* 상품권 추가 전 구버전 */
    	    		casherMapper.deleteCasherTranDataOld(vo);
    	    		casherMapper.insertCasherTranDataOld(vo);
    	    	}
        	}
        }
    }

}
