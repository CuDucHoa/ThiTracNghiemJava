package com.cuduchoa.ThiTracNghiem.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NguoidungExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public NguoidungExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
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
     * This method corresponds to the database table nguoidung
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
     * This method corresponds to the database table nguoidung
     *
     * @mbg.generated Sat May 28 11:33:34 ICT 2022
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table nguoidung
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
     * This class corresponds to the database table nguoidung
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andNguoidungPasswordIsNull() {
            addCriterion("nguoidung_password is null");
            return (Criteria) this;
        }

        public Criteria andNguoidungPasswordIsNotNull() {
            addCriterion("nguoidung_password is not null");
            return (Criteria) this;
        }

        public Criteria andNguoidungPasswordEqualTo(String value) {
            addCriterion("nguoidung_password =", value, "nguoidungPassword");
            return (Criteria) this;
        }

        public Criteria andNguoidungPasswordNotEqualTo(String value) {
            addCriterion("nguoidung_password <>", value, "nguoidungPassword");
            return (Criteria) this;
        }

        public Criteria andNguoidungPasswordGreaterThan(String value) {
            addCriterion("nguoidung_password >", value, "nguoidungPassword");
            return (Criteria) this;
        }

        public Criteria andNguoidungPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("nguoidung_password >=", value, "nguoidungPassword");
            return (Criteria) this;
        }

        public Criteria andNguoidungPasswordLessThan(String value) {
            addCriterion("nguoidung_password <", value, "nguoidungPassword");
            return (Criteria) this;
        }

        public Criteria andNguoidungPasswordLessThanOrEqualTo(String value) {
            addCriterion("nguoidung_password <=", value, "nguoidungPassword");
            return (Criteria) this;
        }

        public Criteria andNguoidungPasswordLike(String value) {
            addCriterion("nguoidung_password like", value, "nguoidungPassword");
            return (Criteria) this;
        }

        public Criteria andNguoidungPasswordNotLike(String value) {
            addCriterion("nguoidung_password not like", value, "nguoidungPassword");
            return (Criteria) this;
        }

        public Criteria andNguoidungPasswordIn(List<String> values) {
            addCriterion("nguoidung_password in", values, "nguoidungPassword");
            return (Criteria) this;
        }

        public Criteria andNguoidungPasswordNotIn(List<String> values) {
            addCriterion("nguoidung_password not in", values, "nguoidungPassword");
            return (Criteria) this;
        }

        public Criteria andNguoidungPasswordBetween(String value1, String value2) {
            addCriterion("nguoidung_password between", value1, value2, "nguoidungPassword");
            return (Criteria) this;
        }

        public Criteria andNguoidungPasswordNotBetween(String value1, String value2) {
            addCriterion("nguoidung_password not between", value1, value2, "nguoidungPassword");
            return (Criteria) this;
        }

        public Criteria andNguoidungFullNameIsNull() {
            addCriterion("nguoidung_full_name is null");
            return (Criteria) this;
        }

        public Criteria andNguoidungFullNameIsNotNull() {
            addCriterion("nguoidung_full_name is not null");
            return (Criteria) this;
        }

        public Criteria andNguoidungFullNameEqualTo(String value) {
            addCriterion("nguoidung_full_name =", value, "nguoidungFullName");
            return (Criteria) this;
        }

        public Criteria andNguoidungFullNameNotEqualTo(String value) {
            addCriterion("nguoidung_full_name <>", value, "nguoidungFullName");
            return (Criteria) this;
        }

        public Criteria andNguoidungFullNameGreaterThan(String value) {
            addCriterion("nguoidung_full_name >", value, "nguoidungFullName");
            return (Criteria) this;
        }

        public Criteria andNguoidungFullNameGreaterThanOrEqualTo(String value) {
            addCriterion("nguoidung_full_name >=", value, "nguoidungFullName");
            return (Criteria) this;
        }

        public Criteria andNguoidungFullNameLessThan(String value) {
            addCriterion("nguoidung_full_name <", value, "nguoidungFullName");
            return (Criteria) this;
        }

        public Criteria andNguoidungFullNameLessThanOrEqualTo(String value) {
            addCriterion("nguoidung_full_name <=", value, "nguoidungFullName");
            return (Criteria) this;
        }

        public Criteria andNguoidungFullNameLike(String value) {
            addCriterion("nguoidung_full_name like", value, "nguoidungFullName");
            return (Criteria) this;
        }

        public Criteria andNguoidungFullNameNotLike(String value) {
            addCriterion("nguoidung_full_name not like", value, "nguoidungFullName");
            return (Criteria) this;
        }

        public Criteria andNguoidungFullNameIn(List<String> values) {
            addCriterion("nguoidung_full_name in", values, "nguoidungFullName");
            return (Criteria) this;
        }

        public Criteria andNguoidungFullNameNotIn(List<String> values) {
            addCriterion("nguoidung_full_name not in", values, "nguoidungFullName");
            return (Criteria) this;
        }

        public Criteria andNguoidungFullNameBetween(String value1, String value2) {
            addCriterion("nguoidung_full_name between", value1, value2, "nguoidungFullName");
            return (Criteria) this;
        }

        public Criteria andNguoidungFullNameNotBetween(String value1, String value2) {
            addCriterion("nguoidung_full_name not between", value1, value2, "nguoidungFullName");
            return (Criteria) this;
        }

        public Criteria andNguoidungEmailIsNull() {
            addCriterion("nguoidung_email is null");
            return (Criteria) this;
        }

        public Criteria andNguoidungEmailIsNotNull() {
            addCriterion("nguoidung_email is not null");
            return (Criteria) this;
        }

        public Criteria andNguoidungEmailEqualTo(String value) {
            addCriterion("nguoidung_email =", value, "nguoidungEmail");
            return (Criteria) this;
        }

        public Criteria andNguoidungEmailNotEqualTo(String value) {
            addCriterion("nguoidung_email <>", value, "nguoidungEmail");
            return (Criteria) this;
        }

        public Criteria andNguoidungEmailGreaterThan(String value) {
            addCriterion("nguoidung_email >", value, "nguoidungEmail");
            return (Criteria) this;
        }

        public Criteria andNguoidungEmailGreaterThanOrEqualTo(String value) {
            addCriterion("nguoidung_email >=", value, "nguoidungEmail");
            return (Criteria) this;
        }

        public Criteria andNguoidungEmailLessThan(String value) {
            addCriterion("nguoidung_email <", value, "nguoidungEmail");
            return (Criteria) this;
        }

        public Criteria andNguoidungEmailLessThanOrEqualTo(String value) {
            addCriterion("nguoidung_email <=", value, "nguoidungEmail");
            return (Criteria) this;
        }

        public Criteria andNguoidungEmailLike(String value) {
            addCriterion("nguoidung_email like", value, "nguoidungEmail");
            return (Criteria) this;
        }

        public Criteria andNguoidungEmailNotLike(String value) {
            addCriterion("nguoidung_email not like", value, "nguoidungEmail");
            return (Criteria) this;
        }

        public Criteria andNguoidungEmailIn(List<String> values) {
            addCriterion("nguoidung_email in", values, "nguoidungEmail");
            return (Criteria) this;
        }

        public Criteria andNguoidungEmailNotIn(List<String> values) {
            addCriterion("nguoidung_email not in", values, "nguoidungEmail");
            return (Criteria) this;
        }

        public Criteria andNguoidungEmailBetween(String value1, String value2) {
            addCriterion("nguoidung_email between", value1, value2, "nguoidungEmail");
            return (Criteria) this;
        }

        public Criteria andNguoidungEmailNotBetween(String value1, String value2) {
            addCriterion("nguoidung_email not between", value1, value2, "nguoidungEmail");
            return (Criteria) this;
        }

        public Criteria andNguoidungPhoneNumberIsNull() {
            addCriterion("nguoidung_phone_number is null");
            return (Criteria) this;
        }

        public Criteria andNguoidungPhoneNumberIsNotNull() {
            addCriterion("nguoidung_phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andNguoidungPhoneNumberEqualTo(String value) {
            addCriterion("nguoidung_phone_number =", value, "nguoidungPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andNguoidungPhoneNumberNotEqualTo(String value) {
            addCriterion("nguoidung_phone_number <>", value, "nguoidungPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andNguoidungPhoneNumberGreaterThan(String value) {
            addCriterion("nguoidung_phone_number >", value, "nguoidungPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andNguoidungPhoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("nguoidung_phone_number >=", value, "nguoidungPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andNguoidungPhoneNumberLessThan(String value) {
            addCriterion("nguoidung_phone_number <", value, "nguoidungPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andNguoidungPhoneNumberLessThanOrEqualTo(String value) {
            addCriterion("nguoidung_phone_number <=", value, "nguoidungPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andNguoidungPhoneNumberLike(String value) {
            addCriterion("nguoidung_phone_number like", value, "nguoidungPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andNguoidungPhoneNumberNotLike(String value) {
            addCriterion("nguoidung_phone_number not like", value, "nguoidungPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andNguoidungPhoneNumberIn(List<String> values) {
            addCriterion("nguoidung_phone_number in", values, "nguoidungPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andNguoidungPhoneNumberNotIn(List<String> values) {
            addCriterion("nguoidung_phone_number not in", values, "nguoidungPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andNguoidungPhoneNumberBetween(String value1, String value2) {
            addCriterion("nguoidung_phone_number between", value1, value2, "nguoidungPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andNguoidungPhoneNumberNotBetween(String value1, String value2) {
            addCriterion("nguoidung_phone_number not between", value1, value2, "nguoidungPhoneNumber");
            return (Criteria) this;
        }

        public Criteria andNguoidungRoleIsNull() {
            addCriterion("nguoidung_role is null");
            return (Criteria) this;
        }

        public Criteria andNguoidungRoleIsNotNull() {
            addCriterion("nguoidung_role is not null");
            return (Criteria) this;
        }

        public Criteria andNguoidungRoleEqualTo(String value) {
            addCriterion("nguoidung_role =", value, "nguoidungRole");
            return (Criteria) this;
        }

        public Criteria andNguoidungRoleNotEqualTo(String value) {
            addCriterion("nguoidung_role <>", value, "nguoidungRole");
            return (Criteria) this;
        }

        public Criteria andNguoidungRoleGreaterThan(String value) {
            addCriterion("nguoidung_role >", value, "nguoidungRole");
            return (Criteria) this;
        }

        public Criteria andNguoidungRoleGreaterThanOrEqualTo(String value) {
            addCriterion("nguoidung_role >=", value, "nguoidungRole");
            return (Criteria) this;
        }

        public Criteria andNguoidungRoleLessThan(String value) {
            addCriterion("nguoidung_role <", value, "nguoidungRole");
            return (Criteria) this;
        }

        public Criteria andNguoidungRoleLessThanOrEqualTo(String value) {
            addCriterion("nguoidung_role <=", value, "nguoidungRole");
            return (Criteria) this;
        }

        public Criteria andNguoidungRoleLike(String value) {
            addCriterion("nguoidung_role like", value, "nguoidungRole");
            return (Criteria) this;
        }

        public Criteria andNguoidungRoleNotLike(String value) {
            addCriterion("nguoidung_role not like", value, "nguoidungRole");
            return (Criteria) this;
        }

        public Criteria andNguoidungRoleIn(List<String> values) {
            addCriterion("nguoidung_role in", values, "nguoidungRole");
            return (Criteria) this;
        }

        public Criteria andNguoidungRoleNotIn(List<String> values) {
            addCriterion("nguoidung_role not in", values, "nguoidungRole");
            return (Criteria) this;
        }

        public Criteria andNguoidungRoleBetween(String value1, String value2) {
            addCriterion("nguoidung_role between", value1, value2, "nguoidungRole");
            return (Criteria) this;
        }

        public Criteria andNguoidungRoleNotBetween(String value1, String value2) {
            addCriterion("nguoidung_role not between", value1, value2, "nguoidungRole");
            return (Criteria) this;
        }

        public Criteria andNguoidungDateOfBirthIsNull() {
            addCriterion("nguoidung_date_of_birth is null");
            return (Criteria) this;
        }

        public Criteria andNguoidungDateOfBirthIsNotNull() {
            addCriterion("nguoidung_date_of_birth is not null");
            return (Criteria) this;
        }

        public Criteria andNguoidungDateOfBirthEqualTo(Date value) {
            addCriterionForJDBCDate("nguoidung_date_of_birth =", value, "nguoidungDateOfBirth");
            return (Criteria) this;
        }

        public Criteria andNguoidungDateOfBirthNotEqualTo(Date value) {
            addCriterionForJDBCDate("nguoidung_date_of_birth <>", value, "nguoidungDateOfBirth");
            return (Criteria) this;
        }

        public Criteria andNguoidungDateOfBirthGreaterThan(Date value) {
            addCriterionForJDBCDate("nguoidung_date_of_birth >", value, "nguoidungDateOfBirth");
            return (Criteria) this;
        }

        public Criteria andNguoidungDateOfBirthGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("nguoidung_date_of_birth >=", value, "nguoidungDateOfBirth");
            return (Criteria) this;
        }

        public Criteria andNguoidungDateOfBirthLessThan(Date value) {
            addCriterionForJDBCDate("nguoidung_date_of_birth <", value, "nguoidungDateOfBirth");
            return (Criteria) this;
        }

        public Criteria andNguoidungDateOfBirthLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("nguoidung_date_of_birth <=", value, "nguoidungDateOfBirth");
            return (Criteria) this;
        }

        public Criteria andNguoidungDateOfBirthIn(List<Date> values) {
            addCriterionForJDBCDate("nguoidung_date_of_birth in", values, "nguoidungDateOfBirth");
            return (Criteria) this;
        }

        public Criteria andNguoidungDateOfBirthNotIn(List<Date> values) {
            addCriterionForJDBCDate("nguoidung_date_of_birth not in", values, "nguoidungDateOfBirth");
            return (Criteria) this;
        }

        public Criteria andNguoidungDateOfBirthBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("nguoidung_date_of_birth between", value1, value2, "nguoidungDateOfBirth");
            return (Criteria) this;
        }

        public Criteria andNguoidungDateOfBirthNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("nguoidung_date_of_birth not between", value1, value2, "nguoidungDateOfBirth");
            return (Criteria) this;
        }

        public Criteria andNguoidungAddressIsNull() {
            addCriterion("nguoidung_address is null");
            return (Criteria) this;
        }

        public Criteria andNguoidungAddressIsNotNull() {
            addCriterion("nguoidung_address is not null");
            return (Criteria) this;
        }

        public Criteria andNguoidungAddressEqualTo(String value) {
            addCriterion("nguoidung_address =", value, "nguoidungAddress");
            return (Criteria) this;
        }

        public Criteria andNguoidungAddressNotEqualTo(String value) {
            addCriterion("nguoidung_address <>", value, "nguoidungAddress");
            return (Criteria) this;
        }

        public Criteria andNguoidungAddressGreaterThan(String value) {
            addCriterion("nguoidung_address >", value, "nguoidungAddress");
            return (Criteria) this;
        }

        public Criteria andNguoidungAddressGreaterThanOrEqualTo(String value) {
            addCriterion("nguoidung_address >=", value, "nguoidungAddress");
            return (Criteria) this;
        }

        public Criteria andNguoidungAddressLessThan(String value) {
            addCriterion("nguoidung_address <", value, "nguoidungAddress");
            return (Criteria) this;
        }

        public Criteria andNguoidungAddressLessThanOrEqualTo(String value) {
            addCriterion("nguoidung_address <=", value, "nguoidungAddress");
            return (Criteria) this;
        }

        public Criteria andNguoidungAddressLike(String value) {
            addCriterion("nguoidung_address like", value, "nguoidungAddress");
            return (Criteria) this;
        }

        public Criteria andNguoidungAddressNotLike(String value) {
            addCriterion("nguoidung_address not like", value, "nguoidungAddress");
            return (Criteria) this;
        }

        public Criteria andNguoidungAddressIn(List<String> values) {
            addCriterion("nguoidung_address in", values, "nguoidungAddress");
            return (Criteria) this;
        }

        public Criteria andNguoidungAddressNotIn(List<String> values) {
            addCriterion("nguoidung_address not in", values, "nguoidungAddress");
            return (Criteria) this;
        }

        public Criteria andNguoidungAddressBetween(String value1, String value2) {
            addCriterion("nguoidung_address between", value1, value2, "nguoidungAddress");
            return (Criteria) this;
        }

        public Criteria andNguoidungAddressNotBetween(String value1, String value2) {
            addCriterion("nguoidung_address not between", value1, value2, "nguoidungAddress");
            return (Criteria) this;
        }

        public Criteria andNguoidungGenderIsNull() {
            addCriterion("nguoidung_gender is null");
            return (Criteria) this;
        }

        public Criteria andNguoidungGenderIsNotNull() {
            addCriterion("nguoidung_gender is not null");
            return (Criteria) this;
        }

        public Criteria andNguoidungGenderEqualTo(String value) {
            addCriterion("nguoidung_gender =", value, "nguoidungGender");
            return (Criteria) this;
        }

        public Criteria andNguoidungGenderNotEqualTo(String value) {
            addCriterion("nguoidung_gender <>", value, "nguoidungGender");
            return (Criteria) this;
        }

        public Criteria andNguoidungGenderGreaterThan(String value) {
            addCriterion("nguoidung_gender >", value, "nguoidungGender");
            return (Criteria) this;
        }

        public Criteria andNguoidungGenderGreaterThanOrEqualTo(String value) {
            addCriterion("nguoidung_gender >=", value, "nguoidungGender");
            return (Criteria) this;
        }

        public Criteria andNguoidungGenderLessThan(String value) {
            addCriterion("nguoidung_gender <", value, "nguoidungGender");
            return (Criteria) this;
        }

        public Criteria andNguoidungGenderLessThanOrEqualTo(String value) {
            addCriterion("nguoidung_gender <=", value, "nguoidungGender");
            return (Criteria) this;
        }

        public Criteria andNguoidungGenderLike(String value) {
            addCriterion("nguoidung_gender like", value, "nguoidungGender");
            return (Criteria) this;
        }

        public Criteria andNguoidungGenderNotLike(String value) {
            addCriterion("nguoidung_gender not like", value, "nguoidungGender");
            return (Criteria) this;
        }

        public Criteria andNguoidungGenderIn(List<String> values) {
            addCriterion("nguoidung_gender in", values, "nguoidungGender");
            return (Criteria) this;
        }

        public Criteria andNguoidungGenderNotIn(List<String> values) {
            addCriterion("nguoidung_gender not in", values, "nguoidungGender");
            return (Criteria) this;
        }

        public Criteria andNguoidungGenderBetween(String value1, String value2) {
            addCriterion("nguoidung_gender between", value1, value2, "nguoidungGender");
            return (Criteria) this;
        }

        public Criteria andNguoidungGenderNotBetween(String value1, String value2) {
            addCriterion("nguoidung_gender not between", value1, value2, "nguoidungGender");
            return (Criteria) this;
        }

        public Criteria andNguoidungImageIsNull() {
            addCriterion("nguoidung_image is null");
            return (Criteria) this;
        }

        public Criteria andNguoidungImageIsNotNull() {
            addCriterion("nguoidung_image is not null");
            return (Criteria) this;
        }

        public Criteria andNguoidungImageEqualTo(String value) {
            addCriterion("nguoidung_image =", value, "nguoidungImage");
            return (Criteria) this;
        }

        public Criteria andNguoidungImageNotEqualTo(String value) {
            addCriterion("nguoidung_image <>", value, "nguoidungImage");
            return (Criteria) this;
        }

        public Criteria andNguoidungImageGreaterThan(String value) {
            addCriterion("nguoidung_image >", value, "nguoidungImage");
            return (Criteria) this;
        }

        public Criteria andNguoidungImageGreaterThanOrEqualTo(String value) {
            addCriterion("nguoidung_image >=", value, "nguoidungImage");
            return (Criteria) this;
        }

        public Criteria andNguoidungImageLessThan(String value) {
            addCriterion("nguoidung_image <", value, "nguoidungImage");
            return (Criteria) this;
        }

        public Criteria andNguoidungImageLessThanOrEqualTo(String value) {
            addCriterion("nguoidung_image <=", value, "nguoidungImage");
            return (Criteria) this;
        }

        public Criteria andNguoidungImageLike(String value) {
            addCriterion("nguoidung_image like", value, "nguoidungImage");
            return (Criteria) this;
        }

        public Criteria andNguoidungImageNotLike(String value) {
            addCriterion("nguoidung_image not like", value, "nguoidungImage");
            return (Criteria) this;
        }

        public Criteria andNguoidungImageIn(List<String> values) {
            addCriterion("nguoidung_image in", values, "nguoidungImage");
            return (Criteria) this;
        }

        public Criteria andNguoidungImageNotIn(List<String> values) {
            addCriterion("nguoidung_image not in", values, "nguoidungImage");
            return (Criteria) this;
        }

        public Criteria andNguoidungImageBetween(String value1, String value2) {
            addCriterion("nguoidung_image between", value1, value2, "nguoidungImage");
            return (Criteria) this;
        }

        public Criteria andNguoidungImageNotBetween(String value1, String value2) {
            addCriterion("nguoidung_image not between", value1, value2, "nguoidungImage");
            return (Criteria) this;
        }

        public Criteria andNguoidungStateIsNull() {
            addCriterion("nguoidung_state is null");
            return (Criteria) this;
        }

        public Criteria andNguoidungStateIsNotNull() {
            addCriterion("nguoidung_state is not null");
            return (Criteria) this;
        }

        public Criteria andNguoidungStateEqualTo(Boolean value) {
            addCriterion("nguoidung_state =", value, "nguoidungState");
            return (Criteria) this;
        }

        public Criteria andNguoidungStateNotEqualTo(Boolean value) {
            addCriterion("nguoidung_state <>", value, "nguoidungState");
            return (Criteria) this;
        }

        public Criteria andNguoidungStateGreaterThan(Boolean value) {
            addCriterion("nguoidung_state >", value, "nguoidungState");
            return (Criteria) this;
        }

        public Criteria andNguoidungStateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("nguoidung_state >=", value, "nguoidungState");
            return (Criteria) this;
        }

        public Criteria andNguoidungStateLessThan(Boolean value) {
            addCriterion("nguoidung_state <", value, "nguoidungState");
            return (Criteria) this;
        }

        public Criteria andNguoidungStateLessThanOrEqualTo(Boolean value) {
            addCriterion("nguoidung_state <=", value, "nguoidungState");
            return (Criteria) this;
        }

        public Criteria andNguoidungStateIn(List<Boolean> values) {
            addCriterion("nguoidung_state in", values, "nguoidungState");
            return (Criteria) this;
        }

        public Criteria andNguoidungStateNotIn(List<Boolean> values) {
            addCriterion("nguoidung_state not in", values, "nguoidungState");
            return (Criteria) this;
        }

        public Criteria andNguoidungStateBetween(Boolean value1, Boolean value2) {
            addCriterion("nguoidung_state between", value1, value2, "nguoidungState");
            return (Criteria) this;
        }

        public Criteria andNguoidungStateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("nguoidung_state not between", value1, value2, "nguoidungState");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table nguoidung
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
     * This class corresponds to the database table nguoidung
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