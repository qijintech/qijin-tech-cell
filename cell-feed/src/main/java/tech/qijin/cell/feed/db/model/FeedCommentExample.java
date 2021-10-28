package tech.qijin.cell.feed.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tech.qijin.util4j.trace.pojo.Channel;

public class FeedCommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FeedCommentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andChannelIsNull() {
            addCriterion("channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(Channel value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(Channel value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(Channel value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(Channel value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(Channel value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(Channel value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(Channel value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(Channel value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<Channel> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<Channel> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(Channel value1, Channel value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(Channel value1, Channel value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andFeedItemIdIsNull() {
            addCriterion("feed_item_Id is null");
            return (Criteria) this;
        }

        public Criteria andFeedItemIdIsNotNull() {
            addCriterion("feed_item_Id is not null");
            return (Criteria) this;
        }

        public Criteria andFeedItemIdEqualTo(Long value) {
            addCriterion("feed_item_Id =", value, "feedItemId");
            return (Criteria) this;
        }

        public Criteria andFeedItemIdNotEqualTo(Long value) {
            addCriterion("feed_item_Id <>", value, "feedItemId");
            return (Criteria) this;
        }

        public Criteria andFeedItemIdGreaterThan(Long value) {
            addCriterion("feed_item_Id >", value, "feedItemId");
            return (Criteria) this;
        }

        public Criteria andFeedItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("feed_item_Id >=", value, "feedItemId");
            return (Criteria) this;
        }

        public Criteria andFeedItemIdLessThan(Long value) {
            addCriterion("feed_item_Id <", value, "feedItemId");
            return (Criteria) this;
        }

        public Criteria andFeedItemIdLessThanOrEqualTo(Long value) {
            addCriterion("feed_item_Id <=", value, "feedItemId");
            return (Criteria) this;
        }

        public Criteria andFeedItemIdIn(List<Long> values) {
            addCriterion("feed_item_Id in", values, "feedItemId");
            return (Criteria) this;
        }

        public Criteria andFeedItemIdNotIn(List<Long> values) {
            addCriterion("feed_item_Id not in", values, "feedItemId");
            return (Criteria) this;
        }

        public Criteria andFeedItemIdBetween(Long value1, Long value2) {
            addCriterion("feed_item_Id between", value1, value2, "feedItemId");
            return (Criteria) this;
        }

        public Criteria andFeedItemIdNotBetween(Long value1, Long value2) {
            addCriterion("feed_item_Id not between", value1, value2, "feedItemId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andToCommentIdIsNull() {
            addCriterion("to_comment_id is null");
            return (Criteria) this;
        }

        public Criteria andToCommentIdIsNotNull() {
            addCriterion("to_comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andToCommentIdEqualTo(Long value) {
            addCriterion("to_comment_id =", value, "toCommentId");
            return (Criteria) this;
        }

        public Criteria andToCommentIdNotEqualTo(Long value) {
            addCriterion("to_comment_id <>", value, "toCommentId");
            return (Criteria) this;
        }

        public Criteria andToCommentIdGreaterThan(Long value) {
            addCriterion("to_comment_id >", value, "toCommentId");
            return (Criteria) this;
        }

        public Criteria andToCommentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("to_comment_id >=", value, "toCommentId");
            return (Criteria) this;
        }

        public Criteria andToCommentIdLessThan(Long value) {
            addCriterion("to_comment_id <", value, "toCommentId");
            return (Criteria) this;
        }

        public Criteria andToCommentIdLessThanOrEqualTo(Long value) {
            addCriterion("to_comment_id <=", value, "toCommentId");
            return (Criteria) this;
        }

        public Criteria andToCommentIdIn(List<Long> values) {
            addCriterion("to_comment_id in", values, "toCommentId");
            return (Criteria) this;
        }

        public Criteria andToCommentIdNotIn(List<Long> values) {
            addCriterion("to_comment_id not in", values, "toCommentId");
            return (Criteria) this;
        }

        public Criteria andToCommentIdBetween(Long value1, Long value2) {
            addCriterion("to_comment_id between", value1, value2, "toCommentId");
            return (Criteria) this;
        }

        public Criteria andToCommentIdNotBetween(Long value1, Long value2) {
            addCriterion("to_comment_id not between", value1, value2, "toCommentId");
            return (Criteria) this;
        }

        public Criteria andContentTextIsNull() {
            addCriterion("content_text is null");
            return (Criteria) this;
        }

        public Criteria andContentTextIsNotNull() {
            addCriterion("content_text is not null");
            return (Criteria) this;
        }

        public Criteria andContentTextEqualTo(String value) {
            addCriterion("content_text =", value, "contentText");
            return (Criteria) this;
        }

        public Criteria andContentTextNotEqualTo(String value) {
            addCriterion("content_text <>", value, "contentText");
            return (Criteria) this;
        }

        public Criteria andContentTextGreaterThan(String value) {
            addCriterion("content_text >", value, "contentText");
            return (Criteria) this;
        }

        public Criteria andContentTextGreaterThanOrEqualTo(String value) {
            addCriterion("content_text >=", value, "contentText");
            return (Criteria) this;
        }

        public Criteria andContentTextLessThan(String value) {
            addCriterion("content_text <", value, "contentText");
            return (Criteria) this;
        }

        public Criteria andContentTextLessThanOrEqualTo(String value) {
            addCriterion("content_text <=", value, "contentText");
            return (Criteria) this;
        }

        public Criteria andContentTextLike(String value) {
            addCriterion("content_text like", value, "contentText");
            return (Criteria) this;
        }

        public Criteria andContentTextNotLike(String value) {
            addCriterion("content_text not like", value, "contentText");
            return (Criteria) this;
        }

        public Criteria andContentTextIn(List<String> values) {
            addCriterion("content_text in", values, "contentText");
            return (Criteria) this;
        }

        public Criteria andContentTextNotIn(List<String> values) {
            addCriterion("content_text not in", values, "contentText");
            return (Criteria) this;
        }

        public Criteria andContentTextBetween(String value1, String value2) {
            addCriterion("content_text between", value1, value2, "contentText");
            return (Criteria) this;
        }

        public Criteria andContentTextNotBetween(String value1, String value2) {
            addCriterion("content_text not between", value1, value2, "contentText");
            return (Criteria) this;
        }

        public Criteria andContentImageIsNull() {
            addCriterion("content_image is null");
            return (Criteria) this;
        }

        public Criteria andContentImageIsNotNull() {
            addCriterion("content_image is not null");
            return (Criteria) this;
        }

        public Criteria andContentImageEqualTo(String value) {
            addCriterion("content_image =", value, "contentImage");
            return (Criteria) this;
        }

        public Criteria andContentImageNotEqualTo(String value) {
            addCriterion("content_image <>", value, "contentImage");
            return (Criteria) this;
        }

        public Criteria andContentImageGreaterThan(String value) {
            addCriterion("content_image >", value, "contentImage");
            return (Criteria) this;
        }

        public Criteria andContentImageGreaterThanOrEqualTo(String value) {
            addCriterion("content_image >=", value, "contentImage");
            return (Criteria) this;
        }

        public Criteria andContentImageLessThan(String value) {
            addCriterion("content_image <", value, "contentImage");
            return (Criteria) this;
        }

        public Criteria andContentImageLessThanOrEqualTo(String value) {
            addCriterion("content_image <=", value, "contentImage");
            return (Criteria) this;
        }

        public Criteria andContentImageLike(String value) {
            addCriterion("content_image like", value, "contentImage");
            return (Criteria) this;
        }

        public Criteria andContentImageNotLike(String value) {
            addCriterion("content_image not like", value, "contentImage");
            return (Criteria) this;
        }

        public Criteria andContentImageIn(List<String> values) {
            addCriterion("content_image in", values, "contentImage");
            return (Criteria) this;
        }

        public Criteria andContentImageNotIn(List<String> values) {
            addCriterion("content_image not in", values, "contentImage");
            return (Criteria) this;
        }

        public Criteria andContentImageBetween(String value1, String value2) {
            addCriterion("content_image between", value1, value2, "contentImage");
            return (Criteria) this;
        }

        public Criteria andContentImageNotBetween(String value1, String value2) {
            addCriterion("content_image not between", value1, value2, "contentImage");
            return (Criteria) this;
        }

        public Criteria andValidIsNull() {
            addCriterion("valid is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("valid is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(Boolean value) {
            addCriterion("valid =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(Boolean value) {
            addCriterion("valid <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(Boolean value) {
            addCriterion("valid >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(Boolean value) {
            addCriterion("valid >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(Boolean value) {
            addCriterion("valid <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(Boolean value) {
            addCriterion("valid <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<Boolean> values) {
            addCriterion("valid in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<Boolean> values) {
            addCriterion("valid not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(Boolean value1, Boolean value2) {
            addCriterion("valid between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(Boolean value1, Boolean value2) {
            addCriterion("valid not between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}