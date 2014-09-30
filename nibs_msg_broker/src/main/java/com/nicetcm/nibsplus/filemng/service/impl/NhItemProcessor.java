package com.nicetcm.nibsplus.filemng.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicetcm.nibsplus.filemng.dao.NhMapper;
import com.nicetcm.nibsplus.filemng.model.TFnNiceTranNhDataVO;
import com.nicetcm.nibsplus.filemng.model.TFnNiceTranNhHeaderVO;
import com.nicetcm.nibsplus.filemng.model.TFnNiceTranNhTailVO;

public class NhItemProcessor implements ItemProcessor<Object, Object>
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NhMapper nhMapper;

    @Override
    public Object process(Object o) throws Exception
    {
        if(o instanceof TFnNiceTranNhHeaderVO) {
            nhMapper.deleteTFnNiceTranNh((TFnNiceTranNhHeaderVO) o);
            return null;

        } else if(o instanceof TFnNiceTranNhDataVO) {

            ((TFnNiceTranNhDataVO) o).setDealNo("96" + ((TFnNiceTranNhDataVO) o).getDealNo().substring(3));
            return o;

        } else if(o instanceof TFnNiceTranNhTailVO) {
            return null;

        } else {
            return null;

        }

    }


}