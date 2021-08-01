package tech.qijin.cell.user.db.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.cell.user.db.model.UserProfileExample.Criteria;
import tech.qijin.cell.user.db.model.UserProfileExample.Criterion;
import tech.qijin.cell.user.db.model.UserProfileExample;

public class UserProfileSqlProvider {

    public String countByExample(UserProfileExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("user_profile");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(UserProfileExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("user_profile");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(UserProfile record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user_profile");
        
        if (record.getChannel() != null) {
            sql.VALUES("channel", "#{channel,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getAvatar() != null) {
            sql.VALUES("avatar", "#{avatar,jdbcType=VARCHAR}");
        }
        
        if (record.getGender() != null) {
            sql.VALUES("gender", "#{gender,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            sql.VALUES("mobile", "#{mobile,jdbcType=CHAR}");
        }
        
        if (record.getBirthday() != null) {
            sql.VALUES("birthday", "#{birthday,jdbcType=TIMESTAMP}");
        }
        
        if (record.getBornCity() != null) {
            sql.VALUES("born_city", "#{bornCity,jdbcType=VARCHAR}");
        }
        
        if (record.getLiveCity() != null) {
            sql.VALUES("live_city", "#{liveCity,jdbcType=VARCHAR}");
        }
        
        if (record.getEdu() != null) {
            sql.VALUES("edu", "#{edu,jdbcType=VARCHAR}");
        }
        
        if (record.getEduDegree() != null) {
            sql.VALUES("edu_degree", "#{eduDegree,jdbcType=VARCHAR}");
        }
        
        if (record.getJob() != null) {
            sql.VALUES("job", "#{job,jdbcType=VARCHAR}");
        }
        
        if (record.getHeight() != null) {
            sql.VALUES("height", "#{height,jdbcType=VARCHAR}");
        }
        
        if (record.getWeight() != null) {
            sql.VALUES("weight", "#{weight,jdbcType=VARCHAR}");
        }
        
        if (record.getMaritalStatus() != null) {
            sql.VALUES("marital_status", "#{maritalStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(UserProfileExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("channel");
        sql.SELECT("user_id");
        sql.SELECT("name");
        sql.SELECT("avatar");
        sql.SELECT("gender");
        sql.SELECT("mobile");
        sql.SELECT("birthday");
        sql.SELECT("born_city");
        sql.SELECT("live_city");
        sql.SELECT("edu");
        sql.SELECT("edu_degree");
        sql.SELECT("job");
        sql.SELECT("height");
        sql.SELECT("weight");
        sql.SELECT("marital_status");
        sql.SELECT("create_time");
        sql.SELECT("update_time");
        sql.FROM("user_profile");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        UserProfile record = (UserProfile) parameter.get("record");
        UserProfileExample example = (UserProfileExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("user_profile");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getChannel() != null) {
            sql.SET("channel = #{record.channel,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{record.userId,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        }
        
        if (record.getAvatar() != null) {
            sql.SET("avatar = #{record.avatar,jdbcType=VARCHAR}");
        }
        
        if (record.getGender() != null) {
            sql.SET("gender = #{record.gender,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            sql.SET("mobile = #{record.mobile,jdbcType=CHAR}");
        }
        
        if (record.getBirthday() != null) {
            sql.SET("birthday = #{record.birthday,jdbcType=TIMESTAMP}");
        }
        
        if (record.getBornCity() != null) {
            sql.SET("born_city = #{record.bornCity,jdbcType=VARCHAR}");
        }
        
        if (record.getLiveCity() != null) {
            sql.SET("live_city = #{record.liveCity,jdbcType=VARCHAR}");
        }
        
        if (record.getEdu() != null) {
            sql.SET("edu = #{record.edu,jdbcType=VARCHAR}");
        }
        
        if (record.getEduDegree() != null) {
            sql.SET("edu_degree = #{record.eduDegree,jdbcType=VARCHAR}");
        }
        
        if (record.getJob() != null) {
            sql.SET("job = #{record.job,jdbcType=VARCHAR}");
        }
        
        if (record.getHeight() != null) {
            sql.SET("height = #{record.height,jdbcType=VARCHAR}");
        }
        
        if (record.getWeight() != null) {
            sql.SET("weight = #{record.weight,jdbcType=VARCHAR}");
        }
        
        if (record.getMaritalStatus() != null) {
            sql.SET("marital_status = #{record.maritalStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("user_profile");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("channel = #{record.channel,jdbcType=VARCHAR}");
        sql.SET("user_id = #{record.userId,jdbcType=BIGINT}");
        sql.SET("name = #{record.name,jdbcType=VARCHAR}");
        sql.SET("avatar = #{record.avatar,jdbcType=VARCHAR}");
        sql.SET("gender = #{record.gender,jdbcType=VARCHAR}");
        sql.SET("mobile = #{record.mobile,jdbcType=CHAR}");
        sql.SET("birthday = #{record.birthday,jdbcType=TIMESTAMP}");
        sql.SET("born_city = #{record.bornCity,jdbcType=VARCHAR}");
        sql.SET("live_city = #{record.liveCity,jdbcType=VARCHAR}");
        sql.SET("edu = #{record.edu,jdbcType=VARCHAR}");
        sql.SET("edu_degree = #{record.eduDegree,jdbcType=VARCHAR}");
        sql.SET("job = #{record.job,jdbcType=VARCHAR}");
        sql.SET("height = #{record.height,jdbcType=VARCHAR}");
        sql.SET("weight = #{record.weight,jdbcType=VARCHAR}");
        sql.SET("marital_status = #{record.maritalStatus,jdbcType=VARCHAR}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        
        UserProfileExample example = (UserProfileExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserProfile record) {
        SQL sql = new SQL();
        sql.UPDATE("user_profile");
        
        if (record.getChannel() != null) {
            sql.SET("channel = #{channel,jdbcType=VARCHAR}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=BIGINT}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getAvatar() != null) {
            sql.SET("avatar = #{avatar,jdbcType=VARCHAR}");
        }
        
        if (record.getGender() != null) {
            sql.SET("gender = #{gender,jdbcType=VARCHAR}");
        }
        
        if (record.getMobile() != null) {
            sql.SET("mobile = #{mobile,jdbcType=CHAR}");
        }
        
        if (record.getBirthday() != null) {
            sql.SET("birthday = #{birthday,jdbcType=TIMESTAMP}");
        }
        
        if (record.getBornCity() != null) {
            sql.SET("born_city = #{bornCity,jdbcType=VARCHAR}");
        }
        
        if (record.getLiveCity() != null) {
            sql.SET("live_city = #{liveCity,jdbcType=VARCHAR}");
        }
        
        if (record.getEdu() != null) {
            sql.SET("edu = #{edu,jdbcType=VARCHAR}");
        }
        
        if (record.getEduDegree() != null) {
            sql.SET("edu_degree = #{eduDegree,jdbcType=VARCHAR}");
        }
        
        if (record.getJob() != null) {
            sql.SET("job = #{job,jdbcType=VARCHAR}");
        }
        
        if (record.getHeight() != null) {
            sql.SET("height = #{height,jdbcType=VARCHAR}");
        }
        
        if (record.getWeight() != null) {
            sql.SET("weight = #{weight,jdbcType=VARCHAR}");
        }
        
        if (record.getMaritalStatus() != null) {
            sql.SET("marital_status = #{maritalStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, UserProfileExample example, boolean includeExamplePhrase) {
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