package com.nicetcm.nibsplus.broker.msg.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.nicetcm.nibsplus.broker.msg.model.TCtNiceMac;
import com.nicetcm.nibsplus.broker.msg.model.TCtNiceMacSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TCtNiceMacSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TCtNiceMacSpec;
import java.util.List;
import java.util.Map;

public class TCtNiceMacSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_NICE_MAC
     *
     * @mbggenerated Tue Jul 01 15:57:31 KST 2014
     */
    public String countBySpec(TCtNiceMacSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_CT_NICE_MAC");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_NICE_MAC
     *
     * @mbggenerated Tue Jul 01 15:57:31 KST 2014
     */
    public String deleteBySpec(TCtNiceMacSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_CT_NICE_MAC");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_NICE_MAC
     *
     * @mbggenerated Tue Jul 01 15:57:31 KST 2014
     */
    public String insertSelective(TCtNiceMac record) {
        BEGIN();
        INSERT_INTO("OP.T_CT_NICE_MAC");
        
        if (record.getMacNo() != null) {
            VALUES("MAC_NO", "#{macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            VALUES("BRANCH_CD", "#{branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgCd() != null) {
            VALUES("ORG_CD", "#{orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getCircuit() != null) {
            VALUES("CIRCUIT", "#{circuit,jdbcType=VARCHAR}");
        }
        
        if (record.getSvcStatus() != null) {
            VALUES("SVC_STATUS", "#{svcStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getOpenCloseYn() != null) {
            VALUES("OPEN_CLOSE_YN", "#{openCloseYn,jdbcType=VARCHAR}");
        }
        
        if (record.getTempYn() != null) {
            VALUES("TEMP_YN", "#{tempYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSrcYn() != null) {
            VALUES("SRC_YN", "#{srcYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSupplyYn() != null) {
            VALUES("SUPPLY_YN", "#{supplyYn,jdbcType=VARCHAR}");
        }
        
        if (record.getTranYn() != null) {
            VALUES("TRAN_YN", "#{tranYn,jdbcType=VARCHAR}");
        }
        
        if (record.getLendYn() != null) {
            VALUES("LEND_YN", "#{lendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSvcSrcYn() != null) {
            VALUES("SVC_SRC_YN", "#{svcSrcYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSvcSupplyYn() != null) {
            VALUES("SVC_SUPPLY_YN", "#{svcSupplyYn,jdbcType=VARCHAR}");
        }
        
        if (record.getInYn() != null) {
            VALUES("IN_YN", "#{inYn,jdbcType=VARCHAR}");
        }
        
        if (record.getArrangeYn() != null) {
            VALUES("ARRANGE_YN", "#{arrangeYn,jdbcType=VARCHAR}");
        }
        
        if (record.getTicketYn() != null) {
            VALUES("TICKET_YN", "#{ticketYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSaleYn() != null) {
            VALUES("SALE_YN", "#{saleYn,jdbcType=VARCHAR}");
        }
        
        if (record.getForeignYn() != null) {
            VALUES("FOREIGN_YN", "#{foreignYn,jdbcType=VARCHAR}");
        }
        
        if (record.getLmkTmk() != null) {
            VALUES("LMK_TMK", "#{lmkTmk,jdbcType=VARCHAR}");
        }
        
        if (record.getLmkTpk() != null) {
            VALUES("LMK_TPK", "#{lmkTpk,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            VALUES("UPDATE_DATE", "#{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCircuitType() != null) {
            VALUES("CIRCUIT_TYPE", "#{circuitType,jdbcType=VARCHAR}");
        }
        
        if (record.getRealIp() != null) {
            VALUES("REAL_IP", "#{realIp,jdbcType=VARCHAR}");
        }
        
        if (record.getVpnIp() != null) {
            VALUES("VPN_IP", "#{vpnIp,jdbcType=VARCHAR}");
        }
        
        if (record.getJoinCd() != null) {
            VALUES("JOIN_CD", "#{joinCd,jdbcType=VARCHAR}");
        }
        
        if (record.getJoinMojumCd() != null) {
            VALUES("JOIN_MOJUM_CD", "#{joinMojumCd,jdbcType=VARCHAR}");
        }
        
        if (record.getInAmtYn() != null) {
            VALUES("IN_AMT_YN", "#{inAmtYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSpecsYn() != null) {
            VALUES("SPECS_YN", "#{specsYn,jdbcType=VARCHAR}");
        }
        
        if (record.getReflux5AllowYn() != null) {
            VALUES("REFLUX_5_ALLOW_YN", "#{reflux5AllowYn,jdbcType=VARCHAR}");
        }
        
        if (record.getRemoteYn() != null) {
            VALUES("REMOTE_YN", "#{remoteYn,jdbcType=VARCHAR}");
        }
        
        if (record.getRpcYn() != null) {
            VALUES("RPC_YN", "#{rpcYn,jdbcType=VARCHAR}");
        }
        
        if (record.getModemRelayYn() != null) {
            VALUES("MODEM_RELAY_YN", "#{modemRelayYn,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_NICE_MAC
     *
     * @mbggenerated Tue Jul 01 15:57:31 KST 2014
     */
    public String selectBySpec(TCtNiceMacSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("MAC_NO");
        } else {
            SELECT("MAC_NO");
        }
        SELECT("BRANCH_CD");
        SELECT("ORG_CD");
        SELECT("CIRCUIT");
        SELECT("SVC_STATUS");
        SELECT("OPEN_CLOSE_YN");
        SELECT("TEMP_YN");
        SELECT("SRC_YN");
        SELECT("SUPPLY_YN");
        SELECT("TRAN_YN");
        SELECT("LEND_YN");
        SELECT("SVC_SRC_YN");
        SELECT("SVC_SUPPLY_YN");
        SELECT("IN_YN");
        SELECT("ARRANGE_YN");
        SELECT("TICKET_YN");
        SELECT("SALE_YN");
        SELECT("FOREIGN_YN");
        SELECT("LMK_TMK");
        SELECT("LMK_TPK");
        SELECT("UPDATE_DATE");
        SELECT("CIRCUIT_TYPE");
        SELECT("REAL_IP");
        SELECT("VPN_IP");
        SELECT("JOIN_CD");
        SELECT("JOIN_MOJUM_CD");
        SELECT("IN_AMT_YN");
        SELECT("SPECS_YN");
        SELECT("REFLUX_5_ALLOW_YN");
        SELECT("REMOTE_YN");
        SELECT("RPC_YN");
        SELECT("MODEM_RELAY_YN");
        FROM("OP.T_CT_NICE_MAC");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_NICE_MAC
     *
     * @mbggenerated Tue Jul 01 15:57:31 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TCtNiceMac record = (TCtNiceMac) parameter.get("record");
        TCtNiceMacSpec spec = (TCtNiceMacSpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_CT_NICE_MAC");
        
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getCircuit() != null) {
            SET("CIRCUIT = #{record.circuit,jdbcType=VARCHAR}");
        }
        
        if (record.getSvcStatus() != null) {
            SET("SVC_STATUS = #{record.svcStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getOpenCloseYn() != null) {
            SET("OPEN_CLOSE_YN = #{record.openCloseYn,jdbcType=VARCHAR}");
        }
        
        if (record.getTempYn() != null) {
            SET("TEMP_YN = #{record.tempYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSrcYn() != null) {
            SET("SRC_YN = #{record.srcYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSupplyYn() != null) {
            SET("SUPPLY_YN = #{record.supplyYn,jdbcType=VARCHAR}");
        }
        
        if (record.getTranYn() != null) {
            SET("TRAN_YN = #{record.tranYn,jdbcType=VARCHAR}");
        }
        
        if (record.getLendYn() != null) {
            SET("LEND_YN = #{record.lendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSvcSrcYn() != null) {
            SET("SVC_SRC_YN = #{record.svcSrcYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSvcSupplyYn() != null) {
            SET("SVC_SUPPLY_YN = #{record.svcSupplyYn,jdbcType=VARCHAR}");
        }
        
        if (record.getInYn() != null) {
            SET("IN_YN = #{record.inYn,jdbcType=VARCHAR}");
        }
        
        if (record.getArrangeYn() != null) {
            SET("ARRANGE_YN = #{record.arrangeYn,jdbcType=VARCHAR}");
        }
        
        if (record.getTicketYn() != null) {
            SET("TICKET_YN = #{record.ticketYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSaleYn() != null) {
            SET("SALE_YN = #{record.saleYn,jdbcType=VARCHAR}");
        }
        
        if (record.getForeignYn() != null) {
            SET("FOREIGN_YN = #{record.foreignYn,jdbcType=VARCHAR}");
        }
        
        if (record.getLmkTmk() != null) {
            SET("LMK_TMK = #{record.lmkTmk,jdbcType=VARCHAR}");
        }
        
        if (record.getLmkTpk() != null) {
            SET("LMK_TPK = #{record.lmkTpk,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCircuitType() != null) {
            SET("CIRCUIT_TYPE = #{record.circuitType,jdbcType=VARCHAR}");
        }
        
        if (record.getRealIp() != null) {
            SET("REAL_IP = #{record.realIp,jdbcType=VARCHAR}");
        }
        
        if (record.getVpnIp() != null) {
            SET("VPN_IP = #{record.vpnIp,jdbcType=VARCHAR}");
        }
        
        if (record.getJoinCd() != null) {
            SET("JOIN_CD = #{record.joinCd,jdbcType=VARCHAR}");
        }
        
        if (record.getJoinMojumCd() != null) {
            SET("JOIN_MOJUM_CD = #{record.joinMojumCd,jdbcType=VARCHAR}");
        }
        
        if (record.getInAmtYn() != null) {
            SET("IN_AMT_YN = #{record.inAmtYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSpecsYn() != null) {
            SET("SPECS_YN = #{record.specsYn,jdbcType=VARCHAR}");
        }
        
        if (record.getReflux5AllowYn() != null) {
            SET("REFLUX_5_ALLOW_YN = #{record.reflux5AllowYn,jdbcType=VARCHAR}");
        }
        
        if (record.getRemoteYn() != null) {
            SET("REMOTE_YN = #{record.remoteYn,jdbcType=VARCHAR}");
        }
        
        if (record.getRpcYn() != null) {
            SET("RPC_YN = #{record.rpcYn,jdbcType=VARCHAR}");
        }
        
        if (record.getModemRelayYn() != null) {
            SET("MODEM_RELAY_YN = #{record.modemRelayYn,jdbcType=VARCHAR}");
        }
        
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_NICE_MAC
     *
     * @mbggenerated Tue Jul 01 15:57:31 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_CT_NICE_MAC");
        
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("CIRCUIT = #{record.circuit,jdbcType=VARCHAR}");
        SET("SVC_STATUS = #{record.svcStatus,jdbcType=VARCHAR}");
        SET("OPEN_CLOSE_YN = #{record.openCloseYn,jdbcType=VARCHAR}");
        SET("TEMP_YN = #{record.tempYn,jdbcType=VARCHAR}");
        SET("SRC_YN = #{record.srcYn,jdbcType=VARCHAR}");
        SET("SUPPLY_YN = #{record.supplyYn,jdbcType=VARCHAR}");
        SET("TRAN_YN = #{record.tranYn,jdbcType=VARCHAR}");
        SET("LEND_YN = #{record.lendYn,jdbcType=VARCHAR}");
        SET("SVC_SRC_YN = #{record.svcSrcYn,jdbcType=VARCHAR}");
        SET("SVC_SUPPLY_YN = #{record.svcSupplyYn,jdbcType=VARCHAR}");
        SET("IN_YN = #{record.inYn,jdbcType=VARCHAR}");
        SET("ARRANGE_YN = #{record.arrangeYn,jdbcType=VARCHAR}");
        SET("TICKET_YN = #{record.ticketYn,jdbcType=VARCHAR}");
        SET("SALE_YN = #{record.saleYn,jdbcType=VARCHAR}");
        SET("FOREIGN_YN = #{record.foreignYn,jdbcType=VARCHAR}");
        SET("LMK_TMK = #{record.lmkTmk,jdbcType=VARCHAR}");
        SET("LMK_TPK = #{record.lmkTpk,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("CIRCUIT_TYPE = #{record.circuitType,jdbcType=VARCHAR}");
        SET("REAL_IP = #{record.realIp,jdbcType=VARCHAR}");
        SET("VPN_IP = #{record.vpnIp,jdbcType=VARCHAR}");
        SET("JOIN_CD = #{record.joinCd,jdbcType=VARCHAR}");
        SET("JOIN_MOJUM_CD = #{record.joinMojumCd,jdbcType=VARCHAR}");
        SET("IN_AMT_YN = #{record.inAmtYn,jdbcType=VARCHAR}");
        SET("SPECS_YN = #{record.specsYn,jdbcType=VARCHAR}");
        SET("REFLUX_5_ALLOW_YN = #{record.reflux5AllowYn,jdbcType=VARCHAR}");
        SET("REMOTE_YN = #{record.remoteYn,jdbcType=VARCHAR}");
        SET("RPC_YN = #{record.rpcYn,jdbcType=VARCHAR}");
        SET("MODEM_RELAY_YN = #{record.modemRelayYn,jdbcType=VARCHAR}");
        
        TCtNiceMacSpec spec = (TCtNiceMacSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_NICE_MAC
     *
     * @mbggenerated Tue Jul 01 15:57:31 KST 2014
     */
    public String updateByPrimaryKeySelective(TCtNiceMac record) {
        BEGIN();
        UPDATE("OP.T_CT_NICE_MAC");
        
        if (record.getCircuit() != null) {
            SET("CIRCUIT = #{circuit,jdbcType=VARCHAR}");
        }
        
        if (record.getSvcStatus() != null) {
            SET("SVC_STATUS = #{svcStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getOpenCloseYn() != null) {
            SET("OPEN_CLOSE_YN = #{openCloseYn,jdbcType=VARCHAR}");
        }
        
        if (record.getTempYn() != null) {
            SET("TEMP_YN = #{tempYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSrcYn() != null) {
            SET("SRC_YN = #{srcYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSupplyYn() != null) {
            SET("SUPPLY_YN = #{supplyYn,jdbcType=VARCHAR}");
        }
        
        if (record.getTranYn() != null) {
            SET("TRAN_YN = #{tranYn,jdbcType=VARCHAR}");
        }
        
        if (record.getLendYn() != null) {
            SET("LEND_YN = #{lendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSvcSrcYn() != null) {
            SET("SVC_SRC_YN = #{svcSrcYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSvcSupplyYn() != null) {
            SET("SVC_SUPPLY_YN = #{svcSupplyYn,jdbcType=VARCHAR}");
        }
        
        if (record.getInYn() != null) {
            SET("IN_YN = #{inYn,jdbcType=VARCHAR}");
        }
        
        if (record.getArrangeYn() != null) {
            SET("ARRANGE_YN = #{arrangeYn,jdbcType=VARCHAR}");
        }
        
        if (record.getTicketYn() != null) {
            SET("TICKET_YN = #{ticketYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSaleYn() != null) {
            SET("SALE_YN = #{saleYn,jdbcType=VARCHAR}");
        }
        
        if (record.getForeignYn() != null) {
            SET("FOREIGN_YN = #{foreignYn,jdbcType=VARCHAR}");
        }
        
        if (record.getLmkTmk() != null) {
            SET("LMK_TMK = #{lmkTmk,jdbcType=VARCHAR}");
        }
        
        if (record.getLmkTpk() != null) {
            SET("LMK_TPK = #{lmkTpk,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCircuitType() != null) {
            SET("CIRCUIT_TYPE = #{circuitType,jdbcType=VARCHAR}");
        }
        
        if (record.getRealIp() != null) {
            SET("REAL_IP = #{realIp,jdbcType=VARCHAR}");
        }
        
        if (record.getVpnIp() != null) {
            SET("VPN_IP = #{vpnIp,jdbcType=VARCHAR}");
        }
        
        if (record.getJoinCd() != null) {
            SET("JOIN_CD = #{joinCd,jdbcType=VARCHAR}");
        }
        
        if (record.getJoinMojumCd() != null) {
            SET("JOIN_MOJUM_CD = #{joinMojumCd,jdbcType=VARCHAR}");
        }
        
        if (record.getInAmtYn() != null) {
            SET("IN_AMT_YN = #{inAmtYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSpecsYn() != null) {
            SET("SPECS_YN = #{specsYn,jdbcType=VARCHAR}");
        }
        
        if (record.getReflux5AllowYn() != null) {
            SET("REFLUX_5_ALLOW_YN = #{reflux5AllowYn,jdbcType=VARCHAR}");
        }
        
        if (record.getRemoteYn() != null) {
            SET("REMOTE_YN = #{remoteYn,jdbcType=VARCHAR}");
        }
        
        if (record.getRpcYn() != null) {
            SET("RPC_YN = #{rpcYn,jdbcType=VARCHAR}");
        }
        
        if (record.getModemRelayYn() != null) {
            SET("MODEM_RELAY_YN = #{modemRelayYn,jdbcType=VARCHAR}");
        }
        
        WHERE("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        WHERE("BRANCH_CD = #{branchCd,jdbcType=VARCHAR}");
        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_NICE_MAC
     *
     * @mbggenerated Tue Jul 01 15:57:31 KST 2014
     */
    protected void applyWhere(TCtNiceMacSpec spec, boolean includeSpecPhrase) {
        if (spec == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeSpecPhrase) {
            parmPhrase1 = "%s #{spec.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{spec.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{spec.oredCriteria[%d].allCriteria[%d].value} and #{spec.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{spec.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{spec.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{spec.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{spec.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = spec.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}