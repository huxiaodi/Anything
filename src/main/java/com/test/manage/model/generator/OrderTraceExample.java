package com.test.manage.model.generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderTraceExample {
    /**
     * This field was generated by MyBatis Generator, do not modify.
     * This field corresponds to the database table order_trace
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator, do not modify.
     * This field corresponds to the database table order_trace
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator, do not modify.
     * This field corresponds to the database table order_trace
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    public OrderTraceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator, do not modify.
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     *   order_trace
     * @mbg.generated
     */
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

        public Criteria andOrderTraceIdIsNull() {
            addCriterion("order_trace_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderTraceIdIsNotNull() {
            addCriterion("order_trace_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTraceIdEqualTo(String value) {
            addCriterion("order_trace_id =", value, "orderTraceId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceIdNotEqualTo(String value) {
            addCriterion("order_trace_id <>", value, "orderTraceId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceIdGreaterThan(String value) {
            addCriterion("order_trace_id >", value, "orderTraceId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_trace_id >=", value, "orderTraceId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceIdLessThan(String value) {
            addCriterion("order_trace_id <", value, "orderTraceId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceIdLessThanOrEqualTo(String value) {
            addCriterion("order_trace_id <=", value, "orderTraceId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceIdLike(String value) {
            addCriterion("order_trace_id like", value, "orderTraceId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceIdNotLike(String value) {
            addCriterion("order_trace_id not like", value, "orderTraceId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceIdIn(List<String> values) {
            addCriterion("order_trace_id in", values, "orderTraceId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceIdNotIn(List<String> values) {
            addCriterion("order_trace_id not in", values, "orderTraceId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceIdBetween(String value1, String value2) {
            addCriterion("order_trace_id between", value1, value2, "orderTraceId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceIdNotBetween(String value1, String value2) {
            addCriterion("order_trace_id not between", value1, value2, "orderTraceId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceContentIsNull() {
            addCriterion("order_trace_content is null");
            return (Criteria) this;
        }

        public Criteria andOrderTraceContentIsNotNull() {
            addCriterion("order_trace_content is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTraceContentEqualTo(String value) {
            addCriterion("order_trace_content =", value, "orderTraceContent");
            return (Criteria) this;
        }

        public Criteria andOrderTraceContentNotEqualTo(String value) {
            addCriterion("order_trace_content <>", value, "orderTraceContent");
            return (Criteria) this;
        }

        public Criteria andOrderTraceContentGreaterThan(String value) {
            addCriterion("order_trace_content >", value, "orderTraceContent");
            return (Criteria) this;
        }

        public Criteria andOrderTraceContentGreaterThanOrEqualTo(String value) {
            addCriterion("order_trace_content >=", value, "orderTraceContent");
            return (Criteria) this;
        }

        public Criteria andOrderTraceContentLessThan(String value) {
            addCriterion("order_trace_content <", value, "orderTraceContent");
            return (Criteria) this;
        }

        public Criteria andOrderTraceContentLessThanOrEqualTo(String value) {
            addCriterion("order_trace_content <=", value, "orderTraceContent");
            return (Criteria) this;
        }

        public Criteria andOrderTraceContentLike(String value) {
            addCriterion("order_trace_content like", value, "orderTraceContent");
            return (Criteria) this;
        }

        public Criteria andOrderTraceContentNotLike(String value) {
            addCriterion("order_trace_content not like", value, "orderTraceContent");
            return (Criteria) this;
        }

        public Criteria andOrderTraceContentIn(List<String> values) {
            addCriterion("order_trace_content in", values, "orderTraceContent");
            return (Criteria) this;
        }

        public Criteria andOrderTraceContentNotIn(List<String> values) {
            addCriterion("order_trace_content not in", values, "orderTraceContent");
            return (Criteria) this;
        }

        public Criteria andOrderTraceContentBetween(String value1, String value2) {
            addCriterion("order_trace_content between", value1, value2, "orderTraceContent");
            return (Criteria) this;
        }

        public Criteria andOrderTraceContentNotBetween(String value1, String value2) {
            addCriterion("order_trace_content not between", value1, value2, "orderTraceContent");
            return (Criteria) this;
        }

        public Criteria andOrderTraceDetailIsNull() {
            addCriterion("order_trace_detail is null");
            return (Criteria) this;
        }

        public Criteria andOrderTraceDetailIsNotNull() {
            addCriterion("order_trace_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTraceDetailEqualTo(String value) {
            addCriterion("order_trace_detail =", value, "orderTraceDetail");
            return (Criteria) this;
        }

        public Criteria andOrderTraceDetailNotEqualTo(String value) {
            addCriterion("order_trace_detail <>", value, "orderTraceDetail");
            return (Criteria) this;
        }

        public Criteria andOrderTraceDetailGreaterThan(String value) {
            addCriterion("order_trace_detail >", value, "orderTraceDetail");
            return (Criteria) this;
        }

        public Criteria andOrderTraceDetailGreaterThanOrEqualTo(String value) {
            addCriterion("order_trace_detail >=", value, "orderTraceDetail");
            return (Criteria) this;
        }

        public Criteria andOrderTraceDetailLessThan(String value) {
            addCriterion("order_trace_detail <", value, "orderTraceDetail");
            return (Criteria) this;
        }

        public Criteria andOrderTraceDetailLessThanOrEqualTo(String value) {
            addCriterion("order_trace_detail <=", value, "orderTraceDetail");
            return (Criteria) this;
        }

        public Criteria andOrderTraceDetailLike(String value) {
            addCriterion("order_trace_detail like", value, "orderTraceDetail");
            return (Criteria) this;
        }

        public Criteria andOrderTraceDetailNotLike(String value) {
            addCriterion("order_trace_detail not like", value, "orderTraceDetail");
            return (Criteria) this;
        }

        public Criteria andOrderTraceDetailIn(List<String> values) {
            addCriterion("order_trace_detail in", values, "orderTraceDetail");
            return (Criteria) this;
        }

        public Criteria andOrderTraceDetailNotIn(List<String> values) {
            addCriterion("order_trace_detail not in", values, "orderTraceDetail");
            return (Criteria) this;
        }

        public Criteria andOrderTraceDetailBetween(String value1, String value2) {
            addCriterion("order_trace_detail between", value1, value2, "orderTraceDetail");
            return (Criteria) this;
        }

        public Criteria andOrderTraceDetailNotBetween(String value1, String value2) {
            addCriterion("order_trace_detail not between", value1, value2, "orderTraceDetail");
            return (Criteria) this;
        }

        public Criteria andOrderTraceOrderIdIsNull() {
            addCriterion("order_trace_order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderTraceOrderIdIsNotNull() {
            addCriterion("order_trace_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTraceOrderIdEqualTo(String value) {
            addCriterion("order_trace_order_id =", value, "orderTraceOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceOrderIdNotEqualTo(String value) {
            addCriterion("order_trace_order_id <>", value, "orderTraceOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceOrderIdGreaterThan(String value) {
            addCriterion("order_trace_order_id >", value, "orderTraceOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_trace_order_id >=", value, "orderTraceOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceOrderIdLessThan(String value) {
            addCriterion("order_trace_order_id <", value, "orderTraceOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_trace_order_id <=", value, "orderTraceOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceOrderIdLike(String value) {
            addCriterion("order_trace_order_id like", value, "orderTraceOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceOrderIdNotLike(String value) {
            addCriterion("order_trace_order_id not like", value, "orderTraceOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceOrderIdIn(List<String> values) {
            addCriterion("order_trace_order_id in", values, "orderTraceOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceOrderIdNotIn(List<String> values) {
            addCriterion("order_trace_order_id not in", values, "orderTraceOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceOrderIdBetween(String value1, String value2) {
            addCriterion("order_trace_order_id between", value1, value2, "orderTraceOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_trace_order_id not between", value1, value2, "orderTraceOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceCreateUserIdIsNull() {
            addCriterion("order_trace_create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderTraceCreateUserIdIsNotNull() {
            addCriterion("order_trace_create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTraceCreateUserIdEqualTo(String value) {
            addCriterion("order_trace_create_user_id =", value, "orderTraceCreateUserId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceCreateUserIdNotEqualTo(String value) {
            addCriterion("order_trace_create_user_id <>", value, "orderTraceCreateUserId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceCreateUserIdGreaterThan(String value) {
            addCriterion("order_trace_create_user_id >", value, "orderTraceCreateUserId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceCreateUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_trace_create_user_id >=", value, "orderTraceCreateUserId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceCreateUserIdLessThan(String value) {
            addCriterion("order_trace_create_user_id <", value, "orderTraceCreateUserId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceCreateUserIdLessThanOrEqualTo(String value) {
            addCriterion("order_trace_create_user_id <=", value, "orderTraceCreateUserId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceCreateUserIdLike(String value) {
            addCriterion("order_trace_create_user_id like", value, "orderTraceCreateUserId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceCreateUserIdNotLike(String value) {
            addCriterion("order_trace_create_user_id not like", value, "orderTraceCreateUserId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceCreateUserIdIn(List<String> values) {
            addCriterion("order_trace_create_user_id in", values, "orderTraceCreateUserId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceCreateUserIdNotIn(List<String> values) {
            addCriterion("order_trace_create_user_id not in", values, "orderTraceCreateUserId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceCreateUserIdBetween(String value1, String value2) {
            addCriterion("order_trace_create_user_id between", value1, value2, "orderTraceCreateUserId");
            return (Criteria) this;
        }

        public Criteria andOrderTraceCreateUserIdNotBetween(String value1, String value2) {
            addCriterion("order_trace_create_user_id not between", value1, value2, "orderTraceCreateUserId");
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

    /**
     * This class was generated by MyBatis Generator, do not modify.
     *   order_trace
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     *   order_trace
     * @mbg.generated
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