package tech.qijin.cell.task.db.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import tech.qijin.cell.task.db.model.Task;
import tech.qijin.cell.task.db.model.TaskExample.Criteria;
import tech.qijin.cell.task.db.model.TaskExample.Criterion;
import tech.qijin.cell.task.db.model.TaskExample;

public class TaskSqlProvider {

    public String countByExample(TaskExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("task");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(TaskExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("task");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(Task record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("task");
        
        if (record.getChannel() != null) {
            sql.VALUES("channel", "#{channel,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("`name`", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getKind() != null) {
            sql.VALUES("kind", "#{kind,jdbcType=VARCHAR}");
        }
        
        if (record.getTarget() != null) {
            sql.VALUES("target", "#{target,jdbcType=BIGINT}");
        }
        
        if (record.getCountingCode() != null) {
            sql.VALUES("counting_code", "#{countingCode,jdbcType=CHAR}");
        }
        
        if (record.getRewardType() != null) {
            sql.VALUES("reward_type", "#{rewardType,jdbcType=VARCHAR}");
        }
        
        if (record.getRewardId() != null) {
            sql.VALUES("reward_id", "#{rewardId,jdbcType=BIGINT}");
        }
        
        if (record.getOrder() != null) {
            sql.VALUES("`order`", "#{order,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(TaskExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("channel");
        sql.SELECT("`name`");
        sql.SELECT("kind");
        sql.SELECT("target");
        sql.SELECT("counting_code");
        sql.SELECT("reward_type");
        sql.SELECT("reward_id");
        sql.SELECT("`order`");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.FROM("task");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Task record = (Task) parameter.get("record");
        TaskExample example = (TaskExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("task");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getChannel() != null) {
            sql.SET("channel = #{record.channel,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("`name` = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getKind() != null) {
            sql.SET("kind = #{record.kind,jdbcType=VARCHAR}");
        }
        
        if (record.getTarget() != null) {
            sql.SET("target = #{record.target,jdbcType=BIGINT}");
        }
        
        if (record.getCountingCode() != null) {
            sql.SET("counting_code = #{record.countingCode,jdbcType=CHAR}");
        }
        
        if (record.getRewardType() != null) {
            sql.SET("reward_type = #{record.rewardType,jdbcType=VARCHAR}");
        }
        
        if (record.getRewardId() != null) {
            sql.SET("reward_id = #{record.rewardId,jdbcType=BIGINT}");
        }
        
        if (record.getOrder() != null) {
            sql.SET("`order` = #{record.order,jdbcType=INTEGER}");
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
        sql.UPDATE("task");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("channel = #{record.channel,jdbcType=VARCHAR}");
        sql.SET("`name` = #{record.name,jdbcType=VARCHAR}");
        sql.SET("kind = #{record.kind,jdbcType=VARCHAR}");
        sql.SET("target = #{record.target,jdbcType=BIGINT}");
        sql.SET("counting_code = #{record.countingCode,jdbcType=CHAR}");
        sql.SET("reward_type = #{record.rewardType,jdbcType=VARCHAR}");
        sql.SET("reward_id = #{record.rewardId,jdbcType=BIGINT}");
        sql.SET("`order` = #{record.order,jdbcType=INTEGER}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        
        TaskExample example = (TaskExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Task record) {
        SQL sql = new SQL();
        sql.UPDATE("task");
        
        if (record.getChannel() != null) {
            sql.SET("channel = #{channel,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("`name` = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getKind() != null) {
            sql.SET("kind = #{kind,jdbcType=VARCHAR}");
        }
        
        if (record.getTarget() != null) {
            sql.SET("target = #{target,jdbcType=BIGINT}");
        }
        
        if (record.getCountingCode() != null) {
            sql.SET("counting_code = #{countingCode,jdbcType=CHAR}");
        }
        
        if (record.getRewardType() != null) {
            sql.SET("reward_type = #{rewardType,jdbcType=VARCHAR}");
        }
        
        if (record.getRewardId() != null) {
            sql.SET("reward_id = #{rewardId,jdbcType=BIGINT}");
        }
        
        if (record.getOrder() != null) {
            sql.SET("`order` = #{order,jdbcType=INTEGER}");
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

    protected void applyWhere(SQL sql, TaskExample example, boolean includeExamplePhrase) {
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