package tech.qijin.cell.im.db.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import tech.qijin.cell.im.db.model.ImConversation;
import tech.qijin.cell.im.db.model.ImConversationExample.Criteria;
import tech.qijin.cell.im.db.model.ImConversationExample.Criterion;
import tech.qijin.cell.im.db.model.ImConversationExample;

public class ImConversationSqlProvider {

    public String countByExample(ImConversationExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("im_conversation");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(ImConversationExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("im_conversation");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(ImConversation record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("im_conversation");
        
        if (record.getOwnerId() != null) {
            sql.VALUES("owner_id", "#{ownerId,jdbcType=BIGINT}");
        }
        
        if (record.getPeerId() != null) {
            sql.VALUES("peer_id", "#{peerId,jdbcType=BIGINT}");
        }
        
        if (record.getLastMsgid() != null) {
            sql.VALUES("last_msgid", "#{lastMsgid,jdbcType=BIGINT}");
        }
        
        if (record.getLastDelMsgid() != null) {
            sql.VALUES("last_del_msgid", "#{lastDelMsgid,jdbcType=BIGINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getVersionId() != null) {
            sql.VALUES("version_id", "#{versionId,jdbcType=BIGINT}");
        }
        
        if (record.getDelStatus() != null) {
            sql.VALUES("del_status", "#{delStatus,jdbcType=TINYINT}");
        }
        
        if (record.getSortId() != null) {
            sql.VALUES("sort_id", "#{sortId,jdbcType=BIGINT}");
        }
        
        if (record.getLastMsg() != null) {
            sql.VALUES("last_msg", "#{lastMsg,jdbcType=VARBINARY}");
        }
        
        return sql.toString();
    }

    public String selectByExampleWithBLOBs(ImConversationExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("owner_id");
        sql.SELECT("peer_id");
        sql.SELECT("last_msgid");
        sql.SELECT("last_del_msgid");
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.SELECT("version_id");
        sql.SELECT("del_status");
        sql.SELECT("sort_id");
        sql.SELECT("last_msg");
        sql.FROM("im_conversation");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String selectByExample(ImConversationExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("owner_id");
        sql.SELECT("peer_id");
        sql.SELECT("last_msgid");
        sql.SELECT("last_del_msgid");
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.SELECT("version_id");
        sql.SELECT("del_status");
        sql.SELECT("sort_id");
        sql.FROM("im_conversation");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ImConversation record = (ImConversation) parameter.get("record");
        ImConversationExample example = (ImConversationExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("im_conversation");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getOwnerId() != null) {
            sql.SET("owner_id = #{record.ownerId,jdbcType=BIGINT}");
        }
        
        if (record.getPeerId() != null) {
            sql.SET("peer_id = #{record.peerId,jdbcType=BIGINT}");
        }
        
        if (record.getLastMsgid() != null) {
            sql.SET("last_msgid = #{record.lastMsgid,jdbcType=BIGINT}");
        }
        
        if (record.getLastDelMsgid() != null) {
            sql.SET("last_del_msgid = #{record.lastDelMsgid,jdbcType=BIGINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getVersionId() != null) {
            sql.SET("version_id = #{record.versionId,jdbcType=BIGINT}");
        }
        
        if (record.getDelStatus() != null) {
            sql.SET("del_status = #{record.delStatus,jdbcType=TINYINT}");
        }
        
        if (record.getSortId() != null) {
            sql.SET("sort_id = #{record.sortId,jdbcType=BIGINT}");
        }
        
        if (record.getLastMsg() != null) {
            sql.SET("last_msg = #{record.lastMsg,jdbcType=VARBINARY}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("im_conversation");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("owner_id = #{record.ownerId,jdbcType=BIGINT}");
        sql.SET("peer_id = #{record.peerId,jdbcType=BIGINT}");
        sql.SET("last_msgid = #{record.lastMsgid,jdbcType=BIGINT}");
        sql.SET("last_del_msgid = #{record.lastDelMsgid,jdbcType=BIGINT}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("version_id = #{record.versionId,jdbcType=BIGINT}");
        sql.SET("del_status = #{record.delStatus,jdbcType=TINYINT}");
        sql.SET("sort_id = #{record.sortId,jdbcType=BIGINT}");
        sql.SET("last_msg = #{record.lastMsg,jdbcType=VARBINARY}");
        
        ImConversationExample example = (ImConversationExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("im_conversation");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("owner_id = #{record.ownerId,jdbcType=BIGINT}");
        sql.SET("peer_id = #{record.peerId,jdbcType=BIGINT}");
        sql.SET("last_msgid = #{record.lastMsgid,jdbcType=BIGINT}");
        sql.SET("last_del_msgid = #{record.lastDelMsgid,jdbcType=BIGINT}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("version_id = #{record.versionId,jdbcType=BIGINT}");
        sql.SET("del_status = #{record.delStatus,jdbcType=TINYINT}");
        sql.SET("sort_id = #{record.sortId,jdbcType=BIGINT}");
        
        ImConversationExample example = (ImConversationExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ImConversation record) {
        SQL sql = new SQL();
        sql.UPDATE("im_conversation");
        
        if (record.getOwnerId() != null) {
            sql.SET("owner_id = #{ownerId,jdbcType=BIGINT}");
        }
        
        if (record.getPeerId() != null) {
            sql.SET("peer_id = #{peerId,jdbcType=BIGINT}");
        }
        
        if (record.getLastMsgid() != null) {
            sql.SET("last_msgid = #{lastMsgid,jdbcType=BIGINT}");
        }
        
        if (record.getLastDelMsgid() != null) {
            sql.SET("last_del_msgid = #{lastDelMsgid,jdbcType=BIGINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getVersionId() != null) {
            sql.SET("version_id = #{versionId,jdbcType=BIGINT}");
        }
        
        if (record.getDelStatus() != null) {
            sql.SET("del_status = #{delStatus,jdbcType=TINYINT}");
        }
        
        if (record.getSortId() != null) {
            sql.SET("sort_id = #{sortId,jdbcType=BIGINT}");
        }
        
        if (record.getLastMsg() != null) {
            sql.SET("last_msg = #{lastMsg,jdbcType=VARBINARY}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, ImConversationExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
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
            sql.WHERE(sb.toString());
        }
    }
}