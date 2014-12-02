package com.nicetcm.nibsplus.filemng.dao;

import com.nicetcm.nibsplus.filemng.model.CasherCKVO;
import com.nicetcm.nibsplus.filemng.model.CasherTKVO;
import com.nicetcm.nibsplus.filemng.model.CasherTRVO;

public interface CasherMapper {

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param vo
	 * @return 
	 */
	int deleteCasherTranDataNew(CasherTRVO vo);
	int insertCasherTranDataNew(CasherTRVO vo);

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param vo
	 * @return 
	 */
	int deleteCasherTranDataOld(CasherTRVO vo);
	int insertCasherTranDataOld(CasherTRVO vo);

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param vo
	 * @return 
	 */
	int deleteCheckDetailData(CasherCKVO vo);
	int insertCheckDetailData(CasherCKVO vo);

	/**
	 * 여기에 메소드(한글)명.
	 * <pre>
	 * 여기에 메소드의 설명 및 변경 이력을 기술하십시오.
	 * </pre>
	 * 
	 * @param vo
	 * @return 
	 */
	int deleteTicketDetailData(CasherTKVO vo);
	int insertTicketDetailData(CasherTKVO vo);
}
