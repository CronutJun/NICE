package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasicKey;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasicSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasicJoin;
import com.nicetcm.nibsplus.broker.msg.model.TCtError;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxn;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TCtErrorBasicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_BASIC
     *
     * @mbggenerated Tue Jul 22 09:07:21 KST 2014
     */
    @SelectProvider(type=TCtErrorBasicSqlProvider.class, method="countBySpec")
    int countBySpec(TCtErrorBasicSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_BASIC
     *
     * @mbggenerated Tue Jul 22 09:07:21 KST 2014
     */
    @DeleteProvider(type=TCtErrorBasicSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TCtErrorBasicSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_BASIC
     *
     * @mbggenerated Tue Jul 22 09:07:21 KST 2014
     */
    @Delete({
        "delete from OP.T_CT_ERROR_BASIC",
        "where ERROR_NO = #{errorNo,jdbcType=VARCHAR}",
          "and CREATE_DATE = #{createDate,jdbcType=DECIMAL}",
          "and CREATE_TIME = #{createTime,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and SITE_CD = #{siteCd,jdbcType=VARCHAR}",
          "and ERROR_CD = #{errorCd,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TCtErrorBasicKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_BASIC
     *
     * @mbggenerated Tue Jul 22 09:07:21 KST 2014
     */
    @Insert({
        "insert into OP.T_CT_ERROR_BASIC (ERROR_NO, CREATE_DATE, ",
        "CREATE_TIME, ORG_CD, ",
        "BRANCH_CD, MAC_NO, ",
        "SITE_CD, ERROR_CD, ",
        "MADE_ERR_CD, MSG, ",
        "ERROR_STATUS, SEND_YN, ",
        "ORG_MSG_NO, CURRENT_DATE, ",
        "CURRENT_UID, FORMAT_TYPE, ",
        "TRANS_DATE, MID_ERROR_CD, ",
        "GROUP_ERROR_CD, ATM_STATE, ",
        "AS_MEDIUM, ORG_MSG, ",
        "ORG_SEND_YN, DEPT_CD, ",
        "OFFICE_CD, TEAM_CD, ",
        "SEC, ORG_CUST_TEL, ",
        "ORG_CUST_NM, ORG_CUST_MSG, ",
        "ORG_CUST_RECV_YN, SEND_START_TIME, ",
        "SEND_FINISH_TIME, UNLOCK_DATE, ",
        "UNLOCK_TIME, ORG_ERR_SEND_YN, ",
        "CONFLICT_YN, WORK_STATUS, ",
        "SEND_COUNT, REMARK, ",
        "ORG_SITE_CD, LOCK_DATE, ",
        "LOCK_TIME, REAL_ERROR_CD, ",
        "REG_DT, REG_ID, ",
        "UPDATE_DATE, UPDATE_UID, ",
        "ORG_CALL_CNT, CRT_NO, ",
        "CLOSE_YN, IVR_YN)",
        "values (#{errorNo,jdbcType=VARCHAR}, #{createDate,jdbcType=DECIMAL}, ",
        "#{createTime,jdbcType=VARCHAR}, #{orgCd,jdbcType=VARCHAR}, ",
        "#{branchCd,jdbcType=VARCHAR}, #{macNo,jdbcType=VARCHAR}, ",
        "#{siteCd,jdbcType=VARCHAR}, #{errorCd,jdbcType=VARCHAR}, ",
        "#{madeErrCd,jdbcType=VARCHAR}, #{msg,jdbcType=VARCHAR}, ",
        "#{errorStatus,jdbcType=VARCHAR}, #{sendYn,jdbcType=CHAR}, ",
        "#{orgMsgNo,jdbcType=VARCHAR}, #{currentDate,jdbcType=TIMESTAMP}, ",
        "#{currentUid,jdbcType=VARCHAR}, #{formatType,jdbcType=VARCHAR}, ",
        "#{transDate,jdbcType=VARCHAR}, #{midErrorCd,jdbcType=VARCHAR}, ",
        "#{groupErrorCd,jdbcType=VARCHAR}, #{atmState,jdbcType=VARCHAR}, ",
        "#{asMedium,jdbcType=VARCHAR}, #{orgMsg,jdbcType=VARCHAR}, ",
        "#{orgSendYn,jdbcType=CHAR}, #{deptCd,jdbcType=VARCHAR}, ",
        "#{officeCd,jdbcType=VARCHAR}, #{teamCd,jdbcType=VARCHAR}, ",
        "#{sec,jdbcType=VARCHAR}, #{orgCustTel,jdbcType=VARCHAR}, ",
        "#{orgCustNm,jdbcType=VARCHAR}, #{orgCustMsg,jdbcType=VARCHAR}, ",
        "#{orgCustRecvYn,jdbcType=CHAR}, #{sendStartTime,jdbcType=VARCHAR}, ",
        "#{sendFinishTime,jdbcType=VARCHAR}, #{unlockDate,jdbcType=VARCHAR}, ",
        "#{unlockTime,jdbcType=VARCHAR}, #{orgErrSendYn,jdbcType=VARCHAR}, ",
        "#{conflictYn,jdbcType=VARCHAR}, #{workStatus,jdbcType=VARCHAR}, ",
        "#{sendCount,jdbcType=DECIMAL}, #{remark,jdbcType=VARCHAR}, ",
        "#{orgSiteCd,jdbcType=VARCHAR}, #{lockDate,jdbcType=VARCHAR}, ",
        "#{lockTime,jdbcType=VARCHAR}, #{realErrorCd,jdbcType=VARCHAR}, ",
        "#{regDt,jdbcType=TIMESTAMP}, #{regId,jdbcType=VARCHAR}, ",
        "#{updateDate,jdbcType=TIMESTAMP}, #{updateUid,jdbcType=VARCHAR}, ",
        "#{orgCallCnt,jdbcType=OTHER}, #{crtNo,jdbcType=VARCHAR}, ",
        "#{closeYn,jdbcType=CHAR}, #{ivrYn,jdbcType=CHAR})"
    })
    int insert(TCtErrorBasic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_BASIC
     *
     * @mbggenerated Tue Jul 22 09:07:21 KST 2014
     */
    @InsertProvider(type=TCtErrorBasicSqlProvider.class, method="insertSelective")
    int insertSelective(TCtErrorBasic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_BASIC
     *
     * @mbggenerated Tue Jul 22 09:07:21 KST 2014
     */
    @SelectProvider(type=TCtErrorBasicSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ERROR_NO", property="errorNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SITE_CD", property="siteCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ERROR_CD", property="errorCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MADE_ERR_CD", property="madeErrCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="MSG", property="msg", jdbcType=JdbcType.VARCHAR),
        @Result(column="ERROR_STATUS", property="errorStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_YN", property="sendYn", jdbcType=JdbcType.CHAR),
        @Result(column="ORG_MSG_NO", property="orgMsgNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="CURRENT_DATE", property="currentDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CURRENT_UID", property="currentUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="FORMAT_TYPE", property="formatType", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANS_DATE", property="transDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="MID_ERROR_CD", property="midErrorCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="GROUP_ERROR_CD", property="groupErrorCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATM_STATE", property="atmState", jdbcType=JdbcType.VARCHAR),
        @Result(column="AS_MEDIUM", property="asMedium", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_MSG", property="orgMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SEND_YN", property="orgSendYn", jdbcType=JdbcType.CHAR),
        @Result(column="DEPT_CD", property="deptCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_CD", property="officeCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="TEAM_CD", property="teamCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEC", property="sec", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_TEL", property="orgCustTel", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_NM", property="orgCustNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_MSG", property="orgCustMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_RECV_YN", property="orgCustRecvYn", jdbcType=JdbcType.CHAR),
        @Result(column="SEND_START_TIME", property="sendStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_FINISH_TIME", property="sendFinishTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="UNLOCK_DATE", property="unlockDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="UNLOCK_TIME", property="unlockTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_ERR_SEND_YN", property="orgErrSendYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONFLICT_YN", property="conflictYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_STATUS", property="workStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_COUNT", property="sendCount", jdbcType=JdbcType.DECIMAL),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SITE_CD", property="orgSiteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOCK_DATE", property="lockDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOCK_TIME", property="lockTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="REAL_ERROR_CD", property="realErrorCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="REG_DT", property="regDt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REG_ID", property="regId", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CALL_CNT", property="orgCallCnt", jdbcType=JdbcType.OTHER),
        @Result(column="CRT_NO", property="crtNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_YN", property="closeYn", jdbcType=JdbcType.CHAR),
        @Result(column="IVR_YN", property="ivrYn", jdbcType=JdbcType.CHAR)
    })
    List<TCtErrorBasic> selectBySpec(TCtErrorBasicSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_BASIC
     *
     * @mbggenerated Tue Jul 22 09:07:21 KST 2014
     */
    @Select({
        "select",
        "ERROR_NO, CREATE_DATE, CREATE_TIME, ORG_CD, BRANCH_CD, MAC_NO, SITE_CD, ERROR_CD, ",
        "MADE_ERR_CD, MSG, ERROR_STATUS, SEND_YN, ORG_MSG_NO, CURRENT_DATE, CURRENT_UID, ",
        "FORMAT_TYPE, TRANS_DATE, MID_ERROR_CD, GROUP_ERROR_CD, ATM_STATE, AS_MEDIUM, ",
        "ORG_MSG, ORG_SEND_YN, DEPT_CD, OFFICE_CD, TEAM_CD, SEC, ORG_CUST_TEL, ORG_CUST_NM, ",
        "ORG_CUST_MSG, ORG_CUST_RECV_YN, SEND_START_TIME, SEND_FINISH_TIME, UNLOCK_DATE, ",
        "UNLOCK_TIME, ORG_ERR_SEND_YN, CONFLICT_YN, WORK_STATUS, SEND_COUNT, REMARK, ",
        "ORG_SITE_CD, LOCK_DATE, LOCK_TIME, REAL_ERROR_CD, REG_DT, REG_ID, UPDATE_DATE, ",
        "UPDATE_UID, ORG_CALL_CNT, CRT_NO, CLOSE_YN, IVR_YN",
        "from OP.T_CT_ERROR_BASIC",
        "where ERROR_NO = #{errorNo,jdbcType=VARCHAR}",
          "and CREATE_DATE = #{createDate,jdbcType=DECIMAL}",
          "and CREATE_TIME = #{createTime,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and SITE_CD = #{siteCd,jdbcType=VARCHAR}",
          "and ERROR_CD = #{errorCd,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ERROR_NO", property="errorNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SITE_CD", property="siteCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ERROR_CD", property="errorCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MADE_ERR_CD", property="madeErrCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="MSG", property="msg", jdbcType=JdbcType.VARCHAR),
        @Result(column="ERROR_STATUS", property="errorStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_YN", property="sendYn", jdbcType=JdbcType.CHAR),
        @Result(column="ORG_MSG_NO", property="orgMsgNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="CURRENT_DATE", property="currentDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CURRENT_UID", property="currentUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="FORMAT_TYPE", property="formatType", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANS_DATE", property="transDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="MID_ERROR_CD", property="midErrorCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="GROUP_ERROR_CD", property="groupErrorCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATM_STATE", property="atmState", jdbcType=JdbcType.VARCHAR),
        @Result(column="AS_MEDIUM", property="asMedium", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_MSG", property="orgMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SEND_YN", property="orgSendYn", jdbcType=JdbcType.CHAR),
        @Result(column="DEPT_CD", property="deptCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_CD", property="officeCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="TEAM_CD", property="teamCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEC", property="sec", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_TEL", property="orgCustTel", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_NM", property="orgCustNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_MSG", property="orgCustMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_RECV_YN", property="orgCustRecvYn", jdbcType=JdbcType.CHAR),
        @Result(column="SEND_START_TIME", property="sendStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_FINISH_TIME", property="sendFinishTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="UNLOCK_DATE", property="unlockDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="UNLOCK_TIME", property="unlockTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_ERR_SEND_YN", property="orgErrSendYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONFLICT_YN", property="conflictYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_STATUS", property="workStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_COUNT", property="sendCount", jdbcType=JdbcType.DECIMAL),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SITE_CD", property="orgSiteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOCK_DATE", property="lockDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOCK_TIME", property="lockTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="REAL_ERROR_CD", property="realErrorCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="REG_DT", property="regDt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REG_ID", property="regId", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CALL_CNT", property="orgCallCnt", jdbcType=JdbcType.OTHER),
        @Result(column="CRT_NO", property="crtNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_YN", property="closeYn", jdbcType=JdbcType.CHAR),
        @Result(column="IVR_YN", property="ivrYn", jdbcType=JdbcType.CHAR)
    })
    TCtErrorBasic selectByPrimaryKey(TCtErrorBasicKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_BASIC
     *
     * @mbggenerated Tue Jul 22 09:07:21 KST 2014
     */
    @UpdateProvider(type=TCtErrorBasicSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TCtErrorBasic record, @Param("spec") TCtErrorBasicSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_BASIC
     *
     * @mbggenerated Tue Jul 22 09:07:21 KST 2014
     */
    @UpdateProvider(type=TCtErrorBasicSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TCtErrorBasic record, @Param("spec") TCtErrorBasicSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_BASIC
     *
     * @mbggenerated Tue Jul 22 09:07:21 KST 2014
     */
    @UpdateProvider(type=TCtErrorBasicSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCtErrorBasic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_BASIC
     *
     * @mbggenerated Tue Jul 22 09:07:21 KST 2014
     */
    @Update({
        "update OP.T_CT_ERROR_BASIC",
        "set MADE_ERR_CD = #{madeErrCd,jdbcType=VARCHAR},",
          "MSG = #{msg,jdbcType=VARCHAR},",
          "ERROR_STATUS = #{errorStatus,jdbcType=VARCHAR},",
          "SEND_YN = #{sendYn,jdbcType=CHAR},",
          "ORG_MSG_NO = #{orgMsgNo,jdbcType=VARCHAR},",
          "CURRENT_DATE = #{currentDate,jdbcType=TIMESTAMP},",
          "CURRENT_UID = #{currentUid,jdbcType=VARCHAR},",
          "FORMAT_TYPE = #{formatType,jdbcType=VARCHAR},",
          "TRANS_DATE = #{transDate,jdbcType=VARCHAR},",
          "MID_ERROR_CD = #{midErrorCd,jdbcType=VARCHAR},",
          "GROUP_ERROR_CD = #{groupErrorCd,jdbcType=VARCHAR},",
          "ATM_STATE = #{atmState,jdbcType=VARCHAR},",
          "AS_MEDIUM = #{asMedium,jdbcType=VARCHAR},",
          "ORG_MSG = #{orgMsg,jdbcType=VARCHAR},",
          "ORG_SEND_YN = #{orgSendYn,jdbcType=CHAR},",
          "DEPT_CD = #{deptCd,jdbcType=VARCHAR},",
          "OFFICE_CD = #{officeCd,jdbcType=VARCHAR},",
          "TEAM_CD = #{teamCd,jdbcType=VARCHAR},",
          "SEC = #{sec,jdbcType=VARCHAR},",
          "ORG_CUST_TEL = #{orgCustTel,jdbcType=VARCHAR},",
          "ORG_CUST_NM = #{orgCustNm,jdbcType=VARCHAR},",
          "ORG_CUST_MSG = #{orgCustMsg,jdbcType=VARCHAR},",
          "ORG_CUST_RECV_YN = #{orgCustRecvYn,jdbcType=CHAR},",
          "SEND_START_TIME = #{sendStartTime,jdbcType=VARCHAR},",
          "SEND_FINISH_TIME = #{sendFinishTime,jdbcType=VARCHAR},",
          "UNLOCK_DATE = #{unlockDate,jdbcType=VARCHAR},",
          "UNLOCK_TIME = #{unlockTime,jdbcType=VARCHAR},",
          "ORG_ERR_SEND_YN = #{orgErrSendYn,jdbcType=VARCHAR},",
          "CONFLICT_YN = #{conflictYn,jdbcType=VARCHAR},",
          "WORK_STATUS = #{workStatus,jdbcType=VARCHAR},",
          "SEND_COUNT = #{sendCount,jdbcType=DECIMAL},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "ORG_SITE_CD = #{orgSiteCd,jdbcType=VARCHAR},",
          "LOCK_DATE = #{lockDate,jdbcType=VARCHAR},",
          "LOCK_TIME = #{lockTime,jdbcType=VARCHAR},",
          "REAL_ERROR_CD = #{realErrorCd,jdbcType=VARCHAR},",
          "REG_DT = #{regDt,jdbcType=TIMESTAMP},",
          "REG_ID = #{regId,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR},",
          "ORG_CALL_CNT = #{orgCallCnt,jdbcType=OTHER},",
          "CRT_NO = #{crtNo,jdbcType=VARCHAR},",
          "CLOSE_YN = #{closeYn,jdbcType=CHAR},",
          "IVR_YN = #{ivrYn,jdbcType=CHAR}",
        "where ERROR_NO = #{errorNo,jdbcType=VARCHAR}",
          "and CREATE_DATE = #{createDate,jdbcType=DECIMAL}",
          "and CREATE_TIME = #{createTime,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and SITE_CD = #{siteCd,jdbcType=VARCHAR}",
          "and ERROR_CD = #{errorCd,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TCtErrorBasic record);

    /**
     * GetDupErrorMng에서 호출 된다.
     * 
     * @author KDJ on  Fri Jun 27 14:12:12 KST 2014
     */
    @Update({
        "UPDATE /*+ index (T_CT_ERROR_MNG IX_T_CT_ERROR_MNG_03) */ OP.T_CT_ERROR_MNG SET",
        "   ORG_MSG     = #{orgMsg, jdbcType=VARCHAR},",
        "   UPDATE_UID  = 'ERRmng',",
        "   UPDATE_DATE = SYSDATE",
        "WHERE   ORG_CD       = #{orgCd, jdbcType=VARCHAR}",
        "AND     BRANCH_CD    = OP.F_GET_NICE_BRANCH_CD( #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, '', #{macNo, jdbcType=VARCHAR} )",
        "AND     MAC_NO       = #{macNo, jdbcType=VARCHAR}",
        "AND     ERROR_CD     = #{errorCd, jdbcType=VARCHAR}",
        "AND     CREATE_DATE > TO_NUMBER(TO_CHAR( SYSDATE - 10, 'YYYYMMDD' ))",
        "AND     TRANS_DATE  = RTRIM(#{transDate, jdbcType=VARCHAR})",
        "AND     RTRIM(ORG_MSG_NO)   = RTRIM(#{orgMsgNo, jdbcType=VARCHAR})",
        "AND     NVL(ERROR_STATUS, '0') <> '7000'"
    })
    int updateByCond1(TCtErrorBasic record);

    /**
     * Called by In01000130Impl.insertErrMngMadeCom 
     *
     * @author KDJ on Fri Jun 27 KST 2014
     */
    @Select({
        "SELECT  create_date,",
        "        error_no,",
        "        error_cd",
        "FROM    (",
        "         SELECT  create_date,",
        "                 error_no,",
        "                 error_cd",
        "         FROM    OP.T_CT_ERROR_MNG",
        "         WHERE   CREATE_DATE >= TO_CHAR( SYSDATE - 5, 'YYYYMMDD')",
        "         AND     ORG_CD = #{orgCd, jdbcType=VARCHAR}",
        "         AND     BRANCH_CD = #{branchCd, jdbcType=VARCHAR}",
        "         AND     MAC_NO = #{macNo, jdbcType=VARCHAR}",
        "         AND     TRANS_DATE = #{transDate, jdbcType=VARCHAR}",
        "         AND     ORG_MSG_NO = #{orgMsgNo, jdbcType=VARCHAR}",
        "         AND     ORG_CALL_CNT = #{orgCallCnt, jdbcType=VARCHAR}",
        "         ORDER BY REG_DT DESC",
        "        )",
        "WHERE   ROWNUM = 1"
    })
    @Results({
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="ERROR_NO", property="errorNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ERROR_CD", property="errorCd", jdbcType=JdbcType.VARCHAR, id=true)
    })
    TCtErrorBasic selectByCond1(TCtErrorBasic cond);
    
    /**
     * Called by In01000130Impl.inMsgHandle 
     * This method corresponds to the database table OP.T_CT_ERROR_MNG
     *
     * @author KDJ on Fri Jun 27 KST 2014
     */
    @Select({
        "SELECT ERR.GROUP_ERROR_CD",
        "FROM   T_CT_ERROR_MNG MNG, ",
        "       T_CT_ERROR ERR",
        "WHERE  MNG.CREATE_DATE = #{createDate, jdbcType=DECIMAL}",
        "AND    MNG.CREATE_TIME = #{createTime, jdbcType=VARCHAR}",
        "AND    MNG.ORG_CD = #{orgCd, jdbcType=VARCHAR}",
        "AND    MNG.BRANCH_CD = #{branchCd, jdbcType=VARCHAR}",
        "AND    MNG.MAC_NO = #{macNo, jdbcType=VARCHAR}",
        "AND    MNG.ORG_CD = ERR.ORG_CD",
        "AND    MNG.ERROR_CD = ERR.ERROR_CD"
    })
    @Results({
        @Result(column="GROUP_ERROR_CD", property="grpErrorCd", jdbcType=JdbcType.VARCHAR)
    })
    
    TCtError getErrGroupCd(TCtErrorBasic cond);
    
    /**
     * CommonPackImpl.getDupError에서 호출
     *
     * @author KDJ, on Tue Jul 01 15:57:31 KST 2014
     */
    @Select({
        "SELECT  MNG.ERROR_NO                                                                ",
        "FROM    OP.T_CT_ERROR_MNG  MNG,                                                     ",
        "        OP.T_CT_ERROR      ERR,                                                     ",
        "        OP.T_CT_ERROR_NOTI NOTI                                                     ",
        "WHERE   MNG.ORG_CD      = #{orgCd, jdbcType=VARCHAR}                                ",
        "AND     MNG.BRANCH_CD   = OP.F_GET_NICE_BRANCH_CD(#{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, '', #{macNo, jdbcType=VARCHAR})",
        "AND     MNG.MAC_NO      = #{macNo, jdbcType=VARCHAR}                                ",
        "AND     MNG.ORG_CD      = ERR.ORG_CD                                                ",
        "AND     MNG.ERROR_CD    = ERR.ERROR_CD                                              ",
        "AND     MNG.CREATE_DATE = TO_NUMBER(TO_CHAR(SYSDATE, 'YYYYMMDD'))/* 금일 장애 */    ",
        "AND     NOTI.ERROR_NO   = MNG.ERROR_NO                                              ",
        "AND     NOTI.SEND_DATE IS NOT NULL                               /* 통보된 장애 */  ",
        "AND     NOTI.SEND_TIME IS NOT NULL                                                  ",
        "AND     NVL(NOTI.SEND_STATUS, '0')   = '2'                                          ",
        "AND     NVL(MNG.ERROR_STATUS, '0') != '7000'            /* 미완료 장애     */       ",
        "AND     ERR.GROUP_ERROR_CD != '4100'                    /* 기타장애가 아님 */       ",
        "AND     MNG.SEND_CHECK_YN = 'N'         /* 이전 통보장애가 중복장애가 아님 */       ",
        "AND     ROWNUM = 1                                                                  "
    })
    @Results({
        @Result(column="ERROR_NO", property="errorNo", jdbcType=JdbcType.VARCHAR)
    })
    TCtErrorBasic selectByCond2( TCtErrorBasic cond );

    
    /**
     * CommonPackImpl.getDupError에서 호출
     *
     * @author KDJ, on Tue Jul 01 15:57:31 KST 2014
     */
    @Select({
        "SELECT  MNG.ERROR_NO                                                                ",
        "FROM    OP.T_CT_ERROR_MNG  MNG,                                                     ",
        "        OP.T_CT_ERROR      ERR,                                                     ",
        "        OP.T_CT_ERROR_NOTI NOTI                                                     ",
        "WHERE   MNG.ORG_CD      = #{orgCd, jdbcType=VARCHAR}                                ",
        "AND     MNG.BRANCH_CD   = #{branchCd, jdbcType=VARCHAR}                             ",
        "AND     MNG.MAC_NO      = #{macNo, jdbcType=VARCHAR}                                ",
        "AND     MNG.ORG_CD      = ERR.ORG_CD                                                ",
        "AND     MNG.ERROR_CD    = ERR.ERROR_CD                                              ",
        "AND     MNG.CREATE_DATE = TO_NUMBER(TO_CHAR(SYSDATE, 'YYYYMMDD'))/* 금일 장애 */    ",
        "AND     NOTI.ERROR_NO   = MNG.ERROR_NO                                              ",
        "AND     NOTI.SEND_DATE IS NOT NULL                               /* 통보된 장애 */  ",
        "AND     NOTI.SEND_TIME IS NOT NULL                                                  ",
        "AND     NVL(NOTI.SEND_STATUS, '0')   = '2'                                          ",
        "AND     NVL(MNG.ERROR_STATUS, '0') != '7000'            /* 미완료 장애     */       ",
        "AND     ERR.GROUP_ERROR_CD != '4100'                    /* 기타장애가 아님 */       ",
        "AND     NOTI.SEND_CHECK_YN = 'N'        /* 이전 통보장애가 중복장애가 아님 */       ",
        "AND     ROWNUM = 1                                                                  "
    })
    @Results({
        @Result(column="ERROR_NO", property="errorNo", jdbcType=JdbcType.VARCHAR)
    })
    TCtErrorBasic selectByCond3( TCtErrorBasic cond );

    /**
     * CommonPackImpl.updateErrMng 에서 호출
     *
     * @author KDJ, on Sun Jul 20 22:13:31 KST 2014
     */
    @SelectProvider(type=TCtErrorBasicMiscProvider.class, method="selectByJoin1")
    @Results({
        @Result(column="ERROR_NO", property="errorNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.DECIMAL,id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="REPAIR_DATE", property="repairDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="REPAIR_TIME", property="repairTime", jdbcType=JdbcType.VARCHAR)
    })
    List<TCtErrorBasicJoin> selectByJoin1( @Param("basic") TCtErrorBasic basic, @Param("txn") TCtErrorTxn txn );

    /**
     * CommonPackImpl.updateErrMng 에서 호출
     *
     * @author KDJ, on Sun Jul 20 22:13:31 KST 2014
     */
    @SelectProvider(type=TCtErrorBasicMiscProvider.class, method="selectByJoin2")
    @Results({
        @Result(column="ERROR_NO", property="errorNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.DECIMAL,id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="REPAIR_DATE", property="repairDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="REPAIR_TIME", property="repairTime", jdbcType=JdbcType.VARCHAR)
    })
    List<TCtErrorBasicJoin> selectByJoin2( @Param("basic") TCtErrorBasic basic, @Param("txn") TCtErrorTxn txn );

    /**
     * CommonPackImpl.updateErrMng 에서 호출
     *
     * @author KDJ
     * @since  Tue Jul 22 20:13:01 KST 2014
     */
    @Select({
        "SELECT  COUNT(*)                                                         ",
        "FROM    OP.T_CT_ERROR_BASIC BASIC                                        ",
        "        LEFT JOIN OP.T_CT_ERROR_TXN TXN ON                               ",
        "        BASIC.ERROR_NO = TXN.ERROR_NO AND                                ",
        "        BASIC.CREATE_DATE = TXN.CREATE_DATE                              ",
        "WHERE   BASIC.CREATE_DATE > TO_NUMBER(TO_CHAR( SYSDATE-10, 'YYYYMMDD' )) ",
        "AND     ORG_CD    = '096'                                                ",
        "AND     BRANCH_CD = '9600'                                               ",
        "AND     MAC_NO = #{macNo, jdbcType=VARCHAR}                              ",
        "AND     ERROR_CD = #{errorCd, jdbcType=VARCHAR}                          ",
        "AND     NVL(ERROR_STATUS, '0')= '7000'                                   ",
        "AND     REPAIR_TIME = '999999'                                           "        
    })
    int countByCond1( TCtErrorBasic record);
    
}