package tech.qijin.cell.im.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tech.qijin.cell.im.base.ConversationStatus;

public class ImConversationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ImConversationExample() {
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

        public Criteria andChannelEqualTo(String value) {
            addCriterion("channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(String value) {
            addCriterion("channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(String value) {
            addCriterion("channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(String value) {
            addCriterion("channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(String value) {
            addCriterion("channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<String> values) {
            addCriterion("channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andPeerUidIsNull() {
            addCriterion("peer_uid is null");
            return (Criteria) this;
        }

        public Criteria andPeerUidIsNotNull() {
            addCriterion("peer_uid is not null");
            return (Criteria) this;
        }

        public Criteria andPeerUidEqualTo(Long value) {
            addCriterion("peer_uid =", value, "peerUid");
            return (Criteria) this;
        }

        public Criteria andPeerUidNotEqualTo(Long value) {
            addCriterion("peer_uid <>", value, "peerUid");
            return (Criteria) this;
        }

        public Criteria andPeerUidGreaterThan(Long value) {
            addCriterion("peer_uid >", value, "peerUid");
            return (Criteria) this;
        }

        public Criteria andPeerUidGreaterThanOrEqualTo(Long value) {
            addCriterion("peer_uid >=", value, "peerUid");
            return (Criteria) this;
        }

        public Criteria andPeerUidLessThan(Long value) {
            addCriterion("peer_uid <", value, "peerUid");
            return (Criteria) this;
        }

        public Criteria andPeerUidLessThanOrEqualTo(Long value) {
            addCriterion("peer_uid <=", value, "peerUid");
            return (Criteria) this;
        }

        public Criteria andPeerUidIn(List<Long> values) {
            addCriterion("peer_uid in", values, "peerUid");
            return (Criteria) this;
        }

        public Criteria andPeerUidNotIn(List<Long> values) {
            addCriterion("peer_uid not in", values, "peerUid");
            return (Criteria) this;
        }

        public Criteria andPeerUidBetween(Long value1, Long value2) {
            addCriterion("peer_uid between", value1, value2, "peerUid");
            return (Criteria) this;
        }

        public Criteria andPeerUidNotBetween(Long value1, Long value2) {
            addCriterion("peer_uid not between", value1, value2, "peerUid");
            return (Criteria) this;
        }

        public Criteria andVersionIdIsNull() {
            addCriterion("version_id is null");
            return (Criteria) this;
        }

        public Criteria andVersionIdIsNotNull() {
            addCriterion("version_id is not null");
            return (Criteria) this;
        }

        public Criteria andVersionIdEqualTo(Long value) {
            addCriterion("version_id =", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotEqualTo(Long value) {
            addCriterion("version_id <>", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdGreaterThan(Long value) {
            addCriterion("version_id >", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("version_id >=", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLessThan(Long value) {
            addCriterion("version_id <", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLessThanOrEqualTo(Long value) {
            addCriterion("version_id <=", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdIn(List<Long> values) {
            addCriterion("version_id in", values, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotIn(List<Long> values) {
            addCriterion("version_id not in", values, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdBetween(Long value1, Long value2) {
            addCriterion("version_id between", value1, value2, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotBetween(Long value1, Long value2) {
            addCriterion("version_id not between", value1, value2, "versionId");
            return (Criteria) this;
        }

        public Criteria andLastMsgIdIsNull() {
            addCriterion("last_msg_id is null");
            return (Criteria) this;
        }

        public Criteria andLastMsgIdIsNotNull() {
            addCriterion("last_msg_id is not null");
            return (Criteria) this;
        }

        public Criteria andLastMsgIdEqualTo(Long value) {
            addCriterion("last_msg_id =", value, "lastMsgId");
            return (Criteria) this;
        }

        public Criteria andLastMsgIdNotEqualTo(Long value) {
            addCriterion("last_msg_id <>", value, "lastMsgId");
            return (Criteria) this;
        }

        public Criteria andLastMsgIdGreaterThan(Long value) {
            addCriterion("last_msg_id >", value, "lastMsgId");
            return (Criteria) this;
        }

        public Criteria andLastMsgIdGreaterThanOrEqualTo(Long value) {
            addCriterion("last_msg_id >=", value, "lastMsgId");
            return (Criteria) this;
        }

        public Criteria andLastMsgIdLessThan(Long value) {
            addCriterion("last_msg_id <", value, "lastMsgId");
            return (Criteria) this;
        }

        public Criteria andLastMsgIdLessThanOrEqualTo(Long value) {
            addCriterion("last_msg_id <=", value, "lastMsgId");
            return (Criteria) this;
        }

        public Criteria andLastMsgIdIn(List<Long> values) {
            addCriterion("last_msg_id in", values, "lastMsgId");
            return (Criteria) this;
        }

        public Criteria andLastMsgIdNotIn(List<Long> values) {
            addCriterion("last_msg_id not in", values, "lastMsgId");
            return (Criteria) this;
        }

        public Criteria andLastMsgIdBetween(Long value1, Long value2) {
            addCriterion("last_msg_id between", value1, value2, "lastMsgId");
            return (Criteria) this;
        }

        public Criteria andLastMsgIdNotBetween(Long value1, Long value2) {
            addCriterion("last_msg_id not between", value1, value2, "lastMsgId");
            return (Criteria) this;
        }

        public Criteria andLastMsgIsNull() {
            addCriterion("last_msg is null");
            return (Criteria) this;
        }

        public Criteria andLastMsgIsNotNull() {
            addCriterion("last_msg is not null");
            return (Criteria) this;
        }

        public Criteria andLastMsgEqualTo(String value) {
            addCriterion("last_msg =", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgNotEqualTo(String value) {
            addCriterion("last_msg <>", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgGreaterThan(String value) {
            addCriterion("last_msg >", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgGreaterThanOrEqualTo(String value) {
            addCriterion("last_msg >=", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgLessThan(String value) {
            addCriterion("last_msg <", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgLessThanOrEqualTo(String value) {
            addCriterion("last_msg <=", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgLike(String value) {
            addCriterion("last_msg like", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgNotLike(String value) {
            addCriterion("last_msg not like", value, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgIn(List<String> values) {
            addCriterion("last_msg in", values, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgNotIn(List<String> values) {
            addCriterion("last_msg not in", values, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgBetween(String value1, String value2) {
            addCriterion("last_msg between", value1, value2, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastMsgNotBetween(String value1, String value2) {
            addCriterion("last_msg not between", value1, value2, "lastMsg");
            return (Criteria) this;
        }

        public Criteria andLastClearMsgIsNull() {
            addCriterion("last_clear_msg is null");
            return (Criteria) this;
        }

        public Criteria andLastClearMsgIsNotNull() {
            addCriterion("last_clear_msg is not null");
            return (Criteria) this;
        }

        public Criteria andLastClearMsgEqualTo(Long value) {
            addCriterion("last_clear_msg =", value, "lastClearMsg");
            return (Criteria) this;
        }

        public Criteria andLastClearMsgNotEqualTo(Long value) {
            addCriterion("last_clear_msg <>", value, "lastClearMsg");
            return (Criteria) this;
        }

        public Criteria andLastClearMsgGreaterThan(Long value) {
            addCriterion("last_clear_msg >", value, "lastClearMsg");
            return (Criteria) this;
        }

        public Criteria andLastClearMsgGreaterThanOrEqualTo(Long value) {
            addCriterion("last_clear_msg >=", value, "lastClearMsg");
            return (Criteria) this;
        }

        public Criteria andLastClearMsgLessThan(Long value) {
            addCriterion("last_clear_msg <", value, "lastClearMsg");
            return (Criteria) this;
        }

        public Criteria andLastClearMsgLessThanOrEqualTo(Long value) {
            addCriterion("last_clear_msg <=", value, "lastClearMsg");
            return (Criteria) this;
        }

        public Criteria andLastClearMsgIn(List<Long> values) {
            addCriterion("last_clear_msg in", values, "lastClearMsg");
            return (Criteria) this;
        }

        public Criteria andLastClearMsgNotIn(List<Long> values) {
            addCriterion("last_clear_msg not in", values, "lastClearMsg");
            return (Criteria) this;
        }

        public Criteria andLastClearMsgBetween(Long value1, Long value2) {
            addCriterion("last_clear_msg between", value1, value2, "lastClearMsg");
            return (Criteria) this;
        }

        public Criteria andLastClearMsgNotBetween(Long value1, Long value2) {
            addCriterion("last_clear_msg not between", value1, value2, "lastClearMsg");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(ConversationStatus value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(ConversationStatus value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(ConversationStatus value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(ConversationStatus value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(ConversationStatus value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(ConversationStatus value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<ConversationStatus> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<ConversationStatus> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(ConversationStatus value1, ConversationStatus value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(ConversationStatus value1, ConversationStatus value2) {
            addCriterion("status not between", value1, value2, "status");
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