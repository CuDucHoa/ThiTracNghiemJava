package com.cuduchoa.ThiTracNghiem.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public ResultExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
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

        public Criteria andTestIdIsNull() {
            addCriterion("test_id is null");
            return (Criteria) this;
        }

        public Criteria andTestIdIsNotNull() {
            addCriterion("test_id is not null");
            return (Criteria) this;
        }

        public Criteria andTestIdEqualTo(Long value) {
            addCriterion("test_id =", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotEqualTo(Long value) {
            addCriterion("test_id <>", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdGreaterThan(Long value) {
            addCriterion("test_id >", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdGreaterThanOrEqualTo(Long value) {
            addCriterion("test_id >=", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLessThan(Long value) {
            addCriterion("test_id <", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLessThanOrEqualTo(Long value) {
            addCriterion("test_id <=", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdIn(List<Long> values) {
            addCriterion("test_id in", values, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotIn(List<Long> values) {
            addCriterion("test_id not in", values, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdBetween(Long value1, Long value2) {
            addCriterion("test_id between", value1, value2, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotBetween(Long value1, Long value2) {
            addCriterion("test_id not between", value1, value2, "testId");
            return (Criteria) this;
        }

        public Criteria andNguoidungIdIsNull() {
            addCriterion("nguoidung_id is null");
            return (Criteria) this;
        }

        public Criteria andNguoidungIdIsNotNull() {
            addCriterion("nguoidung_id is not null");
            return (Criteria) this;
        }

        public Criteria andNguoidungIdEqualTo(String value) {
            addCriterion("nguoidung_id =", value, "nguoidungId");
            return (Criteria) this;
        }

        public Criteria andNguoidungIdNotEqualTo(String value) {
            addCriterion("nguoidung_id <>", value, "nguoidungId");
            return (Criteria) this;
        }

        public Criteria andNguoidungIdGreaterThan(String value) {
            addCriterion("nguoidung_id >", value, "nguoidungId");
            return (Criteria) this;
        }

        public Criteria andNguoidungIdGreaterThanOrEqualTo(String value) {
            addCriterion("nguoidung_id >=", value, "nguoidungId");
            return (Criteria) this;
        }

        public Criteria andNguoidungIdLessThan(String value) {
            addCriterion("nguoidung_id <", value, "nguoidungId");
            return (Criteria) this;
        }

        public Criteria andNguoidungIdLessThanOrEqualTo(String value) {
            addCriterion("nguoidung_id <=", value, "nguoidungId");
            return (Criteria) this;
        }

        public Criteria andNguoidungIdLike(String value) {
            addCriterion("nguoidung_id like", value, "nguoidungId");
            return (Criteria) this;
        }

        public Criteria andNguoidungIdNotLike(String value) {
            addCriterion("nguoidung_id not like", value, "nguoidungId");
            return (Criteria) this;
        }

        public Criteria andNguoidungIdIn(List<String> values) {
            addCriterion("nguoidung_id in", values, "nguoidungId");
            return (Criteria) this;
        }

        public Criteria andNguoidungIdNotIn(List<String> values) {
            addCriterion("nguoidung_id not in", values, "nguoidungId");
            return (Criteria) this;
        }

        public Criteria andNguoidungIdBetween(String value1, String value2) {
            addCriterion("nguoidung_id between", value1, value2, "nguoidungId");
            return (Criteria) this;
        }

        public Criteria andNguoidungIdNotBetween(String value1, String value2) {
            addCriterion("nguoidung_id not between", value1, value2, "nguoidungId");
            return (Criteria) this;
        }

        public Criteria andUsersScoreIsNull() {
            addCriterion("users_score is null");
            return (Criteria) this;
        }

        public Criteria andUsersScoreIsNotNull() {
            addCriterion("users_score is not null");
            return (Criteria) this;
        }

        public Criteria andUsersScoreEqualTo(Float value) {
            addCriterion("users_score =", value, "usersScore");
            return (Criteria) this;
        }

        public Criteria andUsersScoreNotEqualTo(Float value) {
            addCriterion("users_score <>", value, "usersScore");
            return (Criteria) this;
        }

        public Criteria andUsersScoreGreaterThan(Float value) {
            addCriterion("users_score >", value, "usersScore");
            return (Criteria) this;
        }

        public Criteria andUsersScoreGreaterThanOrEqualTo(Float value) {
            addCriterion("users_score >=", value, "usersScore");
            return (Criteria) this;
        }

        public Criteria andUsersScoreLessThan(Float value) {
            addCriterion("users_score <", value, "usersScore");
            return (Criteria) this;
        }

        public Criteria andUsersScoreLessThanOrEqualTo(Float value) {
            addCriterion("users_score <=", value, "usersScore");
            return (Criteria) this;
        }

        public Criteria andUsersScoreIn(List<Float> values) {
            addCriterion("users_score in", values, "usersScore");
            return (Criteria) this;
        }

        public Criteria andUsersScoreNotIn(List<Float> values) {
            addCriterion("users_score not in", values, "usersScore");
            return (Criteria) this;
        }

        public Criteria andUsersScoreBetween(Float value1, Float value2) {
            addCriterion("users_score between", value1, value2, "usersScore");
            return (Criteria) this;
        }

        public Criteria andUsersScoreNotBetween(Float value1, Float value2) {
            addCriterion("users_score not between", value1, value2, "usersScore");
            return (Criteria) this;
        }

        public Criteria andTestStateIsNull() {
            addCriterion("test_state is null");
            return (Criteria) this;
        }

        public Criteria andTestStateIsNotNull() {
            addCriterion("test_state is not null");
            return (Criteria) this;
        }

        public Criteria andTestStateEqualTo(Boolean value) {
            addCriterion("test_state =", value, "testState");
            return (Criteria) this;
        }

        public Criteria andTestStateNotEqualTo(Boolean value) {
            addCriterion("test_state <>", value, "testState");
            return (Criteria) this;
        }

        public Criteria andTestStateGreaterThan(Boolean value) {
            addCriterion("test_state >", value, "testState");
            return (Criteria) this;
        }

        public Criteria andTestStateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("test_state >=", value, "testState");
            return (Criteria) this;
        }

        public Criteria andTestStateLessThan(Boolean value) {
            addCriterion("test_state <", value, "testState");
            return (Criteria) this;
        }

        public Criteria andTestStateLessThanOrEqualTo(Boolean value) {
            addCriterion("test_state <=", value, "testState");
            return (Criteria) this;
        }

        public Criteria andTestStateIn(List<Boolean> values) {
            addCriterion("test_state in", values, "testState");
            return (Criteria) this;
        }

        public Criteria andTestStateNotIn(List<Boolean> values) {
            addCriterion("test_state not in", values, "testState");
            return (Criteria) this;
        }

        public Criteria andTestStateBetween(Boolean value1, Boolean value2) {
            addCriterion("test_state between", value1, value2, "testState");
            return (Criteria) this;
        }

        public Criteria andTestStateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("test_state not between", value1, value2, "testState");
            return (Criteria) this;
        }

        public Criteria andTestFinishTimeIsNull() {
            addCriterion("test_finish_time is null");
            return (Criteria) this;
        }

        public Criteria andTestFinishTimeIsNotNull() {
            addCriterion("test_finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andTestFinishTimeEqualTo(Date value) {
            addCriterion("test_finish_time =", value, "testFinishTime");
            return (Criteria) this;
        }

        public Criteria andTestFinishTimeNotEqualTo(Date value) {
            addCriterion("test_finish_time <>", value, "testFinishTime");
            return (Criteria) this;
        }

        public Criteria andTestFinishTimeGreaterThan(Date value) {
            addCriterion("test_finish_time >", value, "testFinishTime");
            return (Criteria) this;
        }

        public Criteria andTestFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("test_finish_time >=", value, "testFinishTime");
            return (Criteria) this;
        }

        public Criteria andTestFinishTimeLessThan(Date value) {
            addCriterion("test_finish_time <", value, "testFinishTime");
            return (Criteria) this;
        }

        public Criteria andTestFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("test_finish_time <=", value, "testFinishTime");
            return (Criteria) this;
        }

        public Criteria andTestFinishTimeIn(List<Date> values) {
            addCriterion("test_finish_time in", values, "testFinishTime");
            return (Criteria) this;
        }

        public Criteria andTestFinishTimeNotIn(List<Date> values) {
            addCriterion("test_finish_time not in", values, "testFinishTime");
            return (Criteria) this;
        }

        public Criteria andTestFinishTimeBetween(Date value1, Date value2) {
            addCriterion("test_finish_time between", value1, value2, "testFinishTime");
            return (Criteria) this;
        }

        public Criteria andTestFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("test_finish_time not between", value1, value2, "testFinishTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table result
     *
     * @mbg.generated do_not_delete_during_merge Sat May 28 11:33:34 ICT 2022
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table result
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
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