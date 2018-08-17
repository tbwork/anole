/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package org.anole.infrastructure.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnoleSysSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AnoleSysSettingExample() {
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

        public Criteria andServingBossIsNull() {
            addCriterion("serving_boss is null");
            return (Criteria) this;
        }

        public Criteria andServingBossIsNotNull() {
            addCriterion("serving_boss is not null");
            return (Criteria) this;
        }

        public Criteria andServingBossEqualTo(Long value) {
            addCriterion("serving_boss =", value, "servingBoss");
            return (Criteria) this;
        }

        public Criteria andServingBossNotEqualTo(Long value) {
            addCriterion("serving_boss <>", value, "servingBoss");
            return (Criteria) this;
        }

        public Criteria andServingBossGreaterThan(Long value) {
            addCriterion("serving_boss >", value, "servingBoss");
            return (Criteria) this;
        }

        public Criteria andServingBossGreaterThanOrEqualTo(Long value) {
            addCriterion("serving_boss >=", value, "servingBoss");
            return (Criteria) this;
        }

        public Criteria andServingBossLessThan(Long value) {
            addCriterion("serving_boss <", value, "servingBoss");
            return (Criteria) this;
        }

        public Criteria andServingBossLessThanOrEqualTo(Long value) {
            addCriterion("serving_boss <=", value, "servingBoss");
            return (Criteria) this;
        }

        public Criteria andServingBossIn(List<Long> values) {
            addCriterion("serving_boss in", values, "servingBoss");
            return (Criteria) this;
        }

        public Criteria andServingBossNotIn(List<Long> values) {
            addCriterion("serving_boss not in", values, "servingBoss");
            return (Criteria) this;
        }

        public Criteria andServingBossBetween(Long value1, Long value2) {
            addCriterion("serving_boss between", value1, value2, "servingBoss");
            return (Criteria) this;
        }

        public Criteria andServingBossNotBetween(Long value1, Long value2) {
            addCriterion("serving_boss not between", value1, value2, "servingBoss");
            return (Criteria) this;
        }

        public Criteria andHeartBeatIntervalIsNull() {
            addCriterion("heart_beat_interval is null");
            return (Criteria) this;
        }

        public Criteria andHeartBeatIntervalIsNotNull() {
            addCriterion("heart_beat_interval is not null");
            return (Criteria) this;
        }

        public Criteria andHeartBeatIntervalEqualTo(Integer value) {
            addCriterion("heart_beat_interval =", value, "heartBeatInterval");
            return (Criteria) this;
        }

        public Criteria andHeartBeatIntervalNotEqualTo(Integer value) {
            addCriterion("heart_beat_interval <>", value, "heartBeatInterval");
            return (Criteria) this;
        }

        public Criteria andHeartBeatIntervalGreaterThan(Integer value) {
            addCriterion("heart_beat_interval >", value, "heartBeatInterval");
            return (Criteria) this;
        }

        public Criteria andHeartBeatIntervalGreaterThanOrEqualTo(Integer value) {
            addCriterion("heart_beat_interval >=", value, "heartBeatInterval");
            return (Criteria) this;
        }

        public Criteria andHeartBeatIntervalLessThan(Integer value) {
            addCriterion("heart_beat_interval <", value, "heartBeatInterval");
            return (Criteria) this;
        }

        public Criteria andHeartBeatIntervalLessThanOrEqualTo(Integer value) {
            addCriterion("heart_beat_interval <=", value, "heartBeatInterval");
            return (Criteria) this;
        }

        public Criteria andHeartBeatIntervalIn(List<Integer> values) {
            addCriterion("heart_beat_interval in", values, "heartBeatInterval");
            return (Criteria) this;
        }

        public Criteria andHeartBeatIntervalNotIn(List<Integer> values) {
            addCriterion("heart_beat_interval not in", values, "heartBeatInterval");
            return (Criteria) this;
        }

        public Criteria andHeartBeatIntervalBetween(Integer value1, Integer value2) {
            addCriterion("heart_beat_interval between", value1, value2, "heartBeatInterval");
            return (Criteria) this;
        }

        public Criteria andHeartBeatIntervalNotBetween(Integer value1, Integer value2) {
            addCriterion("heart_beat_interval not between", value1, value2, "heartBeatInterval");
            return (Criteria) this;
        }

        public Criteria andTouchIntervalIsNull() {
            addCriterion("touch_interval is null");
            return (Criteria) this;
        }

        public Criteria andTouchIntervalIsNotNull() {
            addCriterion("touch_interval is not null");
            return (Criteria) this;
        }

        public Criteria andTouchIntervalEqualTo(Integer value) {
            addCriterion("touch_interval =", value, "touchInterval");
            return (Criteria) this;
        }

        public Criteria andTouchIntervalNotEqualTo(Integer value) {
            addCriterion("touch_interval <>", value, "touchInterval");
            return (Criteria) this;
        }

        public Criteria andTouchIntervalGreaterThan(Integer value) {
            addCriterion("touch_interval >", value, "touchInterval");
            return (Criteria) this;
        }

        public Criteria andTouchIntervalGreaterThanOrEqualTo(Integer value) {
            addCriterion("touch_interval >=", value, "touchInterval");
            return (Criteria) this;
        }

        public Criteria andTouchIntervalLessThan(Integer value) {
            addCriterion("touch_interval <", value, "touchInterval");
            return (Criteria) this;
        }

        public Criteria andTouchIntervalLessThanOrEqualTo(Integer value) {
            addCriterion("touch_interval <=", value, "touchInterval");
            return (Criteria) this;
        }

        public Criteria andTouchIntervalIn(List<Integer> values) {
            addCriterion("touch_interval in", values, "touchInterval");
            return (Criteria) this;
        }

        public Criteria andTouchIntervalNotIn(List<Integer> values) {
            addCriterion("touch_interval not in", values, "touchInterval");
            return (Criteria) this;
        }

        public Criteria andTouchIntervalBetween(Integer value1, Integer value2) {
            addCriterion("touch_interval between", value1, value2, "touchInterval");
            return (Criteria) this;
        }

        public Criteria andTouchIntervalNotBetween(Integer value1, Integer value2) {
            addCriterion("touch_interval not between", value1, value2, "touchInterval");
            return (Criteria) this;
        }

        public Criteria andTouchStopCountIsNull() {
            addCriterion("touch_stop_count is null");
            return (Criteria) this;
        }

        public Criteria andTouchStopCountIsNotNull() {
            addCriterion("touch_stop_count is not null");
            return (Criteria) this;
        }

        public Criteria andTouchStopCountEqualTo(Integer value) {
            addCriterion("touch_stop_count =", value, "touchStopCount");
            return (Criteria) this;
        }

        public Criteria andTouchStopCountNotEqualTo(Integer value) {
            addCriterion("touch_stop_count <>", value, "touchStopCount");
            return (Criteria) this;
        }

        public Criteria andTouchStopCountGreaterThan(Integer value) {
            addCriterion("touch_stop_count >", value, "touchStopCount");
            return (Criteria) this;
        }

        public Criteria andTouchStopCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("touch_stop_count >=", value, "touchStopCount");
            return (Criteria) this;
        }

        public Criteria andTouchStopCountLessThan(Integer value) {
            addCriterion("touch_stop_count <", value, "touchStopCount");
            return (Criteria) this;
        }

        public Criteria andTouchStopCountLessThanOrEqualTo(Integer value) {
            addCriterion("touch_stop_count <=", value, "touchStopCount");
            return (Criteria) this;
        }

        public Criteria andTouchStopCountIn(List<Integer> values) {
            addCriterion("touch_stop_count in", values, "touchStopCount");
            return (Criteria) this;
        }

        public Criteria andTouchStopCountNotIn(List<Integer> values) {
            addCriterion("touch_stop_count not in", values, "touchStopCount");
            return (Criteria) this;
        }

        public Criteria andTouchStopCountBetween(Integer value1, Integer value2) {
            addCriterion("touch_stop_count between", value1, value2, "touchStopCount");
            return (Criteria) this;
        }

        public Criteria andTouchStopCountNotBetween(Integer value1, Integer value2) {
            addCriterion("touch_stop_count not between", value1, value2, "touchStopCount");
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