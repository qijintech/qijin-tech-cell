package tech.qijin.cell.message.db.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import tech.qijin.cell.message.db.model.Message;
import tech.qijin.cell.message.db.model.MessageExample.Criteria;
import tech.qijin.cell.message.db.model.MessageExample.Criterion;
import tech.qijin.cell.message.db.model.MessageExample;

public class MessageSqlProvider {

    public String countByExample(MessageExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("message");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(MessageExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("message");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(Message record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("message");
        
        if (record.getChannel() != null) {
            sql.VALUES("channel", "#{channel,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=BIGINT}");
        }
        
        if (record.getKind() != null) {
            sql.VALUES("kind", "#{kind,jdbcType=VARCHAR}");
        }
        
        if (record.getBrief() != null) {
            sql.VALUES("brief", "#{brief,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("`status`", "#{status,jdbcType=VARCHAR}");
        }
        
        if (record.getRead() != null) {
            sql.VALUES("`read`", "#{read,jdbcType=TINYINT}");
        }
        
        if (record.getHasDrops() != null) {
            sql.VALUES("has_drops", "#{hasDrops,jdbcType=TINYINT}");
        }
        
        if (record.getVersion() != null) {
            sql.VALUES("version", "#{version,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(MessageExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("channel");
        sql.SELECT("user_id");
        sql.SELECT("kind");
        sql.SELECT("brief");
        sql.SELECT("`status`");
        sql.SELECT("`read`");
        sql.SELECT("has_drops");
        sql.SELECT("version");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.FROM("message");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Message record = (Message) parameter.get("record");
        MessageExample example = (MessageExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("message");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getChannel() != null) {
            sql.SET("channel = #{record.channel,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{record.userId,jdbcType=BIGINT}");
        }
        
        if (record.getKind() != null) {
            sql.SET("kind = #{record.kind,jdbcType=VARCHAR}");
        }
        
        if (record.getBrief() != null) {
            sql.SET("brief = #{record.brief,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("`status` = #{record.status,jdbcType=VARCHAR}");
        }
        
        if (record.getRead() != null) {
            sql.SET("`read` = #{record.read,jdbcType=TINYINT}");
        }
        
        if (record.getHasDrops() != null) {
            sql.SET("has_drops = #{record.hasDrops,jdbcType=TINYINT}");
        }
        
        if (record.getVersion() != null) {
            sql.SET("version = #{record.version,jdbcType=INTEGER}");
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
        sql.UPDATE("message");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("channel = #{record.channel,jdbcType=VARCHAR}");
        sql.SET("user_id = #{record.userId,jdbcType=BIGINT}");
        sql.SET("kind = #{record.kind,jdbcType=VARCHAR}");
        sql.SET("brief = #{record.brief,jdbcType=VARCHAR}");
        sql.SET("`status` = #{record.status,jdbcType=VARCHAR}");
        sql.SET("`read` = #{record.read,jdbcType=TINYINT}");
        sql.SET("has_drops = #{record.hasDrops,jdbcType=TINYINT}");
        sql.SET("version = #{record.version,jdbcType=INTEGER}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        
        MessageExample example = (MessageExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Message record) {
        SQL sql = new SQL();
        sql.UPDATE("message");
        
        if (record.getChannel() != null) {
            sql.SET("channel = #{channel,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=BIGINT}");
        }
        
        if (record.getKind() != null) {
            sql.SET("kind = #{kind,jdbcType=VARCHAR}");
        }
        
        if (record.getBrief() != null) {
            sql.SET("brief = #{brief,jdbcType=VARCHAR}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("`status` = #{status,jdbcType=VARCHAR}");
        }
        
        if (record.getRead() != null) {
            sql.SET("`read` = #{read,jdbcType=TINYINT}");
        }
        
        if (record.getHasDrops() != null) {
            sql.SET("has_drops = #{hasDrops,jdbcType=TINYINT}");
        }
        
        if (record.getVersion() != null) {
            sql.SET("version = #{version,jdbcType=INTEGER}");
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

    protected void applyWhere(SQL sql, MessageExample example, boolean includeExamplePhrase) {
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