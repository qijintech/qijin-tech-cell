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
        
        if (record.getChannel() != null) {
            sql.VALUES("channel", "#{channel,jdbcType=VARCHAR}");
        }
        
        if (record.getUid() != null) {
            sql.VALUES("uid", "#{uid,jdbcType=BIGINT}");
        }
        
        if (record.getPeerUid() != null) {
            sql.VALUES("peer_uid", "#{peerUid,jdbcType=BIGINT}");
        }
        
        if (record.getVersionId() != null) {
            sql.VALUES("version_id", "#{versionId,jdbcType=BIGINT}");
        }
        
        if (record.getLastMsgId() != null) {
            sql.VALUES("last_msg_id", "#{lastMsgId,jdbcType=BIGINT}");
        }
        
        if (record.getLastMsg() != null) {
            sql.VALUES("last_msg", "#{lastMsg,jdbcType=VARCHAR}");
        }
        
        if (record.getLastClearMsg() != null) {
            sql.VALUES("last_clear_msg", "#{lastClearMsg,jdbcType=BIGINT}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=TINYINT}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
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
        sql.SELECT("channel");
        sql.SELECT("uid");
        sql.SELECT("peer_uid");
        sql.SELECT("version_id");
        sql.SELECT("last_msg_id");
        sql.SELECT("last_msg");
        sql.SELECT("last_clear_msg");
        sql.SELECT("status");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
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
        
        if (record.getChannel() != null) {
            sql.SET("channel = #{record.channel,jdbcType=VARCHAR}");
        }
        
        if (record.getUid() != null) {
            sql.SET("uid = #{record.uid,jdbcType=BIGINT}");
        }
        
        if (record.getPeerUid() != null) {
            sql.SET("peer_uid = #{record.peerUid,jdbcType=BIGINT}");
        }
        
        if (record.getVersionId() != null) {
            sql.SET("version_id = #{record.versionId,jdbcType=BIGINT}");
        }
        
        if (record.getLastMsgId() != null) {
            sql.SET("last_msg_id = #{record.lastMsgId,jdbcType=BIGINT}");
        }
        
        if (record.getLastMsg() != null) {
            sql.SET("last_msg = #{record.lastMsg,jdbcType=VARCHAR}");
        }
        
        if (record.getLastClearMsg() != null) {
            sql.SET("last_clear_msg = #{record.lastClearMsg,jdbcType=BIGINT}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=TINYINT}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("im_conversation");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("channel = #{record.channel,jdbcType=VARCHAR}");
        sql.SET("uid = #{record.uid,jdbcType=BIGINT}");
        sql.SET("peer_uid = #{record.peerUid,jdbcType=BIGINT}");
        sql.SET("version_id = #{record.versionId,jdbcType=BIGINT}");
        sql.SET("last_msg_id = #{record.lastMsgId,jdbcType=BIGINT}");
        sql.SET("last_msg = #{record.lastMsg,jdbcType=VARCHAR}");
        sql.SET("last_clear_msg = #{record.lastClearMsg,jdbcType=BIGINT}");
        sql.SET("status = #{record.status,jdbcType=TINYINT}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        
        ImConversationExample example = (ImConversationExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(ImConversation record) {
        SQL sql = new SQL();
        sql.UPDATE("im_conversation");
        
        if (record.getChannel() != null) {
            sql.SET("channel = #{channel,jdbcType=VARCHAR}");
        }
        
        if (record.getUid() != null) {
            sql.SET("uid = #{uid,jdbcType=BIGINT}");
        }
        
        if (record.getPeerUid() != null) {
            sql.SET("peer_uid = #{peerUid,jdbcType=BIGINT}");
        }
        
        if (record.getVersionId() != null) {
            sql.SET("version_id = #{versionId,jdbcType=BIGINT}");
        }
        
        if (record.getLastMsgId() != null) {
            sql.SET("last_msg_id = #{lastMsgId,jdbcType=BIGINT}");
        }
        
        if (record.getLastMsg() != null) {
            sql.SET("last_msg = #{lastMsg,jdbcType=VARCHAR}");
        }
        
        if (record.getLastClearMsg() != null) {
            sql.SET("last_clear_msg = #{lastClearMsg,jdbcType=BIGINT}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=TINYINT}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
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