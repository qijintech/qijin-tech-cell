package tech.qijin.cell.feed.db.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import tech.qijin.cell.feed.db.model.FeedComment;
import tech.qijin.cell.feed.db.model.FeedCommentExample.Criteria;
import tech.qijin.cell.feed.db.model.FeedCommentExample.Criterion;
import tech.qijin.cell.feed.db.model.FeedCommentExample;

public class FeedCommentSqlProvider {

    public String countByExample(FeedCommentExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("feed_comment");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(FeedCommentExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("feed_comment");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(FeedComment record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("feed_comment");
        
        if (record.getChannel() != null) {
            sql.VALUES("channel", "#{channel,jdbcType=VARCHAR}");
        }
        
        if (record.getFeedItemId() != null) {
            sql.VALUES("feed_item_Id", "#{feedItemId,jdbcType=BIGINT}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=BIGINT}");
        }
        
        if (record.getToCommentId() != null) {
            sql.VALUES("to_comment_id", "#{toCommentId,jdbcType=BIGINT}");
        }
        
        if (record.getContentText() != null) {
            sql.VALUES("content_text", "#{contentText,jdbcType=VARCHAR}");
        }
        
        if (record.getContentImage() != null) {
            sql.VALUES("content_image", "#{contentImage,jdbcType=VARCHAR}");
        }
        
        if (record.getValid() != null) {
            sql.VALUES("valid", "#{valid,jdbcType=TINYINT}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(FeedCommentExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("channel");
        sql.SELECT("feed_item_Id");
        sql.SELECT("user_id");
        sql.SELECT("to_comment_id");
        sql.SELECT("content_text");
        sql.SELECT("content_image");
        sql.SELECT("valid");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.FROM("feed_comment");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        FeedComment record = (FeedComment) parameter.get("record");
        FeedCommentExample example = (FeedCommentExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("feed_comment");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=BIGINT}");
        }
        
        if (record.getChannel() != null) {
            sql.SET("channel = #{record.channel,jdbcType=VARCHAR}");
        }
        
        if (record.getFeedItemId() != null) {
            sql.SET("feed_item_Id = #{record.feedItemId,jdbcType=BIGINT}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{record.userId,jdbcType=BIGINT}");
        }
        
        if (record.getToCommentId() != null) {
            sql.SET("to_comment_id = #{record.toCommentId,jdbcType=BIGINT}");
        }
        
        if (record.getContentText() != null) {
            sql.SET("content_text = #{record.contentText,jdbcType=VARCHAR}");
        }
        
        if (record.getContentImage() != null) {
            sql.SET("content_image = #{record.contentImage,jdbcType=VARCHAR}");
        }
        
        if (record.getValid() != null) {
            sql.SET("valid = #{record.valid,jdbcType=TINYINT}");
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
        sql.UPDATE("feed_comment");
        
        sql.SET("id = #{record.id,jdbcType=BIGINT}");
        sql.SET("channel = #{record.channel,jdbcType=VARCHAR}");
        sql.SET("feed_item_Id = #{record.feedItemId,jdbcType=BIGINT}");
        sql.SET("user_id = #{record.userId,jdbcType=BIGINT}");
        sql.SET("to_comment_id = #{record.toCommentId,jdbcType=BIGINT}");
        sql.SET("content_text = #{record.contentText,jdbcType=VARCHAR}");
        sql.SET("content_image = #{record.contentImage,jdbcType=VARCHAR}");
        sql.SET("valid = #{record.valid,jdbcType=TINYINT}");
        sql.SET("update_time = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("create_time = #{record.createTime,jdbcType=TIMESTAMP}");
        
        FeedCommentExample example = (FeedCommentExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(FeedComment record) {
        SQL sql = new SQL();
        sql.UPDATE("feed_comment");
        
        if (record.getChannel() != null) {
            sql.SET("channel = #{channel,jdbcType=VARCHAR}");
        }
        
        if (record.getFeedItemId() != null) {
            sql.SET("feed_item_Id = #{feedItemId,jdbcType=BIGINT}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=BIGINT}");
        }
        
        if (record.getToCommentId() != null) {
            sql.SET("to_comment_id = #{toCommentId,jdbcType=BIGINT}");
        }
        
        if (record.getContentText() != null) {
            sql.SET("content_text = #{contentText,jdbcType=VARCHAR}");
        }
        
        if (record.getContentImage() != null) {
            sql.SET("content_image = #{contentImage,jdbcType=VARCHAR}");
        }
        
        if (record.getValid() != null) {
            sql.SET("valid = #{valid,jdbcType=TINYINT}");
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

    protected void applyWhere(SQL sql, FeedCommentExample example, boolean includeExamplePhrase) {
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