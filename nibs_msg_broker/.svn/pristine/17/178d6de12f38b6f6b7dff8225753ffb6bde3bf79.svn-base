package com.nicetcm.nibsplus.filemng.service.impl;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;




//import com.nicetcm.nibsplus.filemng.dao.ShinhanMapper;
import com.nicetcm.nibsplus.filemng.model.TFnNiceTranShinhanDataVO;

public class ShinhanItemProcessor implements ItemProcessor<TFnNiceTranShinhanDataVO, TFnNiceTranShinhanDataVO>
{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String contType;

    @Autowired
    //private ShinhanMapper shinhanMapper;    

    public TFnNiceTranShinhanDataVO process(TFnNiceTranShinhanDataVO tFnNiceTranShinhanDataVO) throws Exception
    {
    	
    	if(    tFnNiceTranShinhanDataVO != null 
    		&& tFnNiceTranShinhanDataVO.getJijumMacNo() != null 
    		&& tFnNiceTranShinhanDataVO.getCreateDate() != null 
    		&& tFnNiceTranShinhanDataVO.getCreateTime() != null 
    		&& tFnNiceTranShinhanDataVO.getOrgCallCnt() != null    		
    		&& !"".equals(tFnNiceTranShinhanDataVO.getJijumMacNo()) 
    		&& !"".equals(tFnNiceTranShinhanDataVO.getCreateDate())
    		&& !"".equals(tFnNiceTranShinhanDataVO.getCreateTime()) 
    		&& !"".equals(tFnNiceTranShinhanDataVO.getOrgCallCnt())
    		
    	){
    		if( tFnNiceTranShinhanDataVO.getBranchNm().getBytes().length > 35) {
        		logger.debug(
      	    		  tFnNiceTranShinhanDataVO.getJijumMacNo() + "|"
      	    		+ tFnNiceTranShinhanDataVO.getBranchCd() + "|"
      	    		+ tFnNiceTranShinhanDataVO.getMacNo() + "|"
      	    		+ tFnNiceTranShinhanDataVO.getCreateDate() + "|"
      	    		+ tFnNiceTranShinhanDataVO.getCreateTime() + "|"
      	    		+ tFnNiceTranShinhanDataVO.getOrgCallCnt() + "|"
      	    		+ tFnNiceTranShinhanDataVO.getBranchNm() + "|"
      	    		+ getContType()
      	    		);    			
    		}

    		tFnNiceTranShinhanDataVO.setContType(getContType());
    		return tFnNiceTranShinhanDataVO;
        }else{
        	if( tFnNiceTranShinhanDataVO != null  ){
	        	logger.debug(
	  	    		  tFnNiceTranShinhanDataVO.getJijumMacNo() + "|"
	  	    		+ tFnNiceTranShinhanDataVO.getBranchCd() + "|"
	  	    		+ tFnNiceTranShinhanDataVO.getMacNo() + "|"
	  	    		+ tFnNiceTranShinhanDataVO.getCreateDate() + "|"
	  	    		+ tFnNiceTranShinhanDataVO.getCreateTime() + "|"
	  	    		+ tFnNiceTranShinhanDataVO.getOrgCallCnt() + "|"
	  	    		+ getContType()
	    		);
        	}else{
        		logger.debug("NULL@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        	}
        	return null;
        }

    }
    
    public void setContType(String contType){
    	this.contType = contType;
    }
    private String getContType(){
    	return this.contType;
    }


}
