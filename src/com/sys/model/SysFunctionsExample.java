package com.sys.model;

import com.sys.utils.BeanUtils;
import com.sys.utils.Page;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SysFunctionsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public SysFunctionsExample() {
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

    public void setPage(Page page) {
        this.page=page;
    }

    public Page getPage() {
        return page;
    }

    public List<Criteria> integratedQuery(Map queryCondationMap) {
            Criteria criteria = createCriteriaInternal();
    for(Object key : queryCondationMap.keySet()) {
		String field = ((String)key).split("@")[0];
		String opt = ((String)key).split("@")[1];
		if(((String)key).contains("between")){
         criteria.addCriterion(BeanUtils.camelToUnderline(field)+" "+opt,((String)queryCondationMap.get(key)).split(",")[0],((String)queryCondationMap.get(key)).split(",")[1],(String)key);
		}else if(((String)key).contains("IS NULL")||((String)key).contains("IS NOT NULL")){
         criteria.addCriterion(BeanUtils.camelToUnderline(field)+" "+opt);
		}else if(((String)key).contains("@IN")||((String)key).contains("@NOT IN")){
         String values = (String)queryCondationMap.get(key);
 		  List val=Arrays.asList(values.split(","));
 		  criteria.addCriterion(BeanUtils.camelToUnderline(field)+" "+opt,val,(String)key);
		}else{
         criteria.addCriterion(BeanUtils.camelToUnderline(field)+" "+opt,queryCondationMap.get(key),(String)key);
		}
    }
	 oredCriteria.add(criteria);
    return oredCriteria;

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

        public Criteria andFunctionIdIsNull() {
            addCriterion("FUNCTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andFunctionIdIsNotNull() {
            addCriterion("FUNCTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionIdEqualTo(String value) {
            addCriterion("FUNCTION_ID =", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdNotEqualTo(String value) {
            addCriterion("FUNCTION_ID <>", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdGreaterThan(String value) {
            addCriterion("FUNCTION_ID >", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdGreaterThanOrEqualTo(String value) {
            addCriterion("FUNCTION_ID >=", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdLessThan(String value) {
            addCriterion("FUNCTION_ID <", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdLessThanOrEqualTo(String value) {
            addCriterion("FUNCTION_ID <=", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdLike(String value) {
            addCriterion("FUNCTION_ID like", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdNotLike(String value) {
            addCriterion("FUNCTION_ID not like", value, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdIn(List<String> values) {
            addCriterion("FUNCTION_ID in", values, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdNotIn(List<String> values) {
            addCriterion("FUNCTION_ID not in", values, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdBetween(String value1, String value2) {
            addCriterion("FUNCTION_ID between", value1, value2, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionIdNotBetween(String value1, String value2) {
            addCriterion("FUNCTION_ID not between", value1, value2, "functionId");
            return (Criteria) this;
        }

        public Criteria andFunctionNameIsNull() {
            addCriterion("FUNCTION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFunctionNameIsNotNull() {
            addCriterion("FUNCTION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionNameEqualTo(String value) {
            addCriterion("FUNCTION_NAME =", value, "functionName");
            return (Criteria) this;
        }

        public Criteria andFunctionNameNotEqualTo(String value) {
            addCriterion("FUNCTION_NAME <>", value, "functionName");
            return (Criteria) this;
        }

        public Criteria andFunctionNameGreaterThan(String value) {
            addCriterion("FUNCTION_NAME >", value, "functionName");
            return (Criteria) this;
        }

        public Criteria andFunctionNameGreaterThanOrEqualTo(String value) {
            addCriterion("FUNCTION_NAME >=", value, "functionName");
            return (Criteria) this;
        }

        public Criteria andFunctionNameLessThan(String value) {
            addCriterion("FUNCTION_NAME <", value, "functionName");
            return (Criteria) this;
        }

        public Criteria andFunctionNameLessThanOrEqualTo(String value) {
            addCriterion("FUNCTION_NAME <=", value, "functionName");
            return (Criteria) this;
        }

        public Criteria andFunctionNameLike(String value) {
            addCriterion("FUNCTION_NAME like", value, "functionName");
            return (Criteria) this;
        }

        public Criteria andFunctionNameNotLike(String value) {
            addCriterion("FUNCTION_NAME not like", value, "functionName");
            return (Criteria) this;
        }

        public Criteria andFunctionNameIn(List<String> values) {
            addCriterion("FUNCTION_NAME in", values, "functionName");
            return (Criteria) this;
        }

        public Criteria andFunctionNameNotIn(List<String> values) {
            addCriterion("FUNCTION_NAME not in", values, "functionName");
            return (Criteria) this;
        }

        public Criteria andFunctionNameBetween(String value1, String value2) {
            addCriterion("FUNCTION_NAME between", value1, value2, "functionName");
            return (Criteria) this;
        }

        public Criteria andFunctionNameNotBetween(String value1, String value2) {
            addCriterion("FUNCTION_NAME not between", value1, value2, "functionName");
            return (Criteria) this;
        }

        public Criteria andFunctionIconIsNull() {
            addCriterion("FUNCTION_ICON is null");
            return (Criteria) this;
        }

        public Criteria andFunctionIconIsNotNull() {
            addCriterion("FUNCTION_ICON is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionIconEqualTo(String value) {
            addCriterion("FUNCTION_ICON =", value, "functionIcon");
            return (Criteria) this;
        }

        public Criteria andFunctionIconNotEqualTo(String value) {
            addCriterion("FUNCTION_ICON <>", value, "functionIcon");
            return (Criteria) this;
        }

        public Criteria andFunctionIconGreaterThan(String value) {
            addCriterion("FUNCTION_ICON >", value, "functionIcon");
            return (Criteria) this;
        }

        public Criteria andFunctionIconGreaterThanOrEqualTo(String value) {
            addCriterion("FUNCTION_ICON >=", value, "functionIcon");
            return (Criteria) this;
        }

        public Criteria andFunctionIconLessThan(String value) {
            addCriterion("FUNCTION_ICON <", value, "functionIcon");
            return (Criteria) this;
        }

        public Criteria andFunctionIconLessThanOrEqualTo(String value) {
            addCriterion("FUNCTION_ICON <=", value, "functionIcon");
            return (Criteria) this;
        }

        public Criteria andFunctionIconLike(String value) {
            addCriterion("FUNCTION_ICON like", value, "functionIcon");
            return (Criteria) this;
        }

        public Criteria andFunctionIconNotLike(String value) {
            addCriterion("FUNCTION_ICON not like", value, "functionIcon");
            return (Criteria) this;
        }

        public Criteria andFunctionIconIn(List<String> values) {
            addCriterion("FUNCTION_ICON in", values, "functionIcon");
            return (Criteria) this;
        }

        public Criteria andFunctionIconNotIn(List<String> values) {
            addCriterion("FUNCTION_ICON not in", values, "functionIcon");
            return (Criteria) this;
        }

        public Criteria andFunctionIconBetween(String value1, String value2) {
            addCriterion("FUNCTION_ICON between", value1, value2, "functionIcon");
            return (Criteria) this;
        }

        public Criteria andFunctionIconNotBetween(String value1, String value2) {
            addCriterion("FUNCTION_ICON not between", value1, value2, "functionIcon");
            return (Criteria) this;
        }

        public Criteria andFunctionLevelIsNull() {
            addCriterion("FUNCTION_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andFunctionLevelIsNotNull() {
            addCriterion("FUNCTION_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionLevelEqualTo(String value) {
            addCriterion("FUNCTION_LEVEL =", value, "functionLevel");
            return (Criteria) this;
        }

        public Criteria andFunctionLevelNotEqualTo(String value) {
            addCriterion("FUNCTION_LEVEL <>", value, "functionLevel");
            return (Criteria) this;
        }

        public Criteria andFunctionLevelGreaterThan(String value) {
            addCriterion("FUNCTION_LEVEL >", value, "functionLevel");
            return (Criteria) this;
        }

        public Criteria andFunctionLevelGreaterThanOrEqualTo(String value) {
            addCriterion("FUNCTION_LEVEL >=", value, "functionLevel");
            return (Criteria) this;
        }

        public Criteria andFunctionLevelLessThan(String value) {
            addCriterion("FUNCTION_LEVEL <", value, "functionLevel");
            return (Criteria) this;
        }

        public Criteria andFunctionLevelLessThanOrEqualTo(String value) {
            addCriterion("FUNCTION_LEVEL <=", value, "functionLevel");
            return (Criteria) this;
        }

        public Criteria andFunctionLevelLike(String value) {
            addCriterion("FUNCTION_LEVEL like", value, "functionLevel");
            return (Criteria) this;
        }

        public Criteria andFunctionLevelNotLike(String value) {
            addCriterion("FUNCTION_LEVEL not like", value, "functionLevel");
            return (Criteria) this;
        }

        public Criteria andFunctionLevelIn(List<String> values) {
            addCriterion("FUNCTION_LEVEL in", values, "functionLevel");
            return (Criteria) this;
        }

        public Criteria andFunctionLevelNotIn(List<String> values) {
            addCriterion("FUNCTION_LEVEL not in", values, "functionLevel");
            return (Criteria) this;
        }

        public Criteria andFunctionLevelBetween(String value1, String value2) {
            addCriterion("FUNCTION_LEVEL between", value1, value2, "functionLevel");
            return (Criteria) this;
        }

        public Criteria andFunctionLevelNotBetween(String value1, String value2) {
            addCriterion("FUNCTION_LEVEL not between", value1, value2, "functionLevel");
            return (Criteria) this;
        }

        public Criteria andParentCodeIsNull() {
            addCriterion("PARENT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andParentCodeIsNotNull() {
            addCriterion("PARENT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andParentCodeEqualTo(String value) {
            addCriterion("PARENT_CODE =", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeNotEqualTo(String value) {
            addCriterion("PARENT_CODE <>", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeGreaterThan(String value) {
            addCriterion("PARENT_CODE >", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_CODE >=", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeLessThan(String value) {
            addCriterion("PARENT_CODE <", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeLessThanOrEqualTo(String value) {
            addCriterion("PARENT_CODE <=", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeLike(String value) {
            addCriterion("PARENT_CODE like", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeNotLike(String value) {
            addCriterion("PARENT_CODE not like", value, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeIn(List<String> values) {
            addCriterion("PARENT_CODE in", values, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeNotIn(List<String> values) {
            addCriterion("PARENT_CODE not in", values, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeBetween(String value1, String value2) {
            addCriterion("PARENT_CODE between", value1, value2, "parentCode");
            return (Criteria) this;
        }

        public Criteria andParentCodeNotBetween(String value1, String value2) {
            addCriterion("PARENT_CODE not between", value1, value2, "parentCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeIsNull() {
            addCriterion("FUNCTION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeIsNotNull() {
            addCriterion("FUNCTION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeEqualTo(String value) {
            addCriterion("FUNCTION_CODE =", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeNotEqualTo(String value) {
            addCriterion("FUNCTION_CODE <>", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeGreaterThan(String value) {
            addCriterion("FUNCTION_CODE >", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("FUNCTION_CODE >=", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeLessThan(String value) {
            addCriterion("FUNCTION_CODE <", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeLessThanOrEqualTo(String value) {
            addCriterion("FUNCTION_CODE <=", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeLike(String value) {
            addCriterion("FUNCTION_CODE like", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeNotLike(String value) {
            addCriterion("FUNCTION_CODE not like", value, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeIn(List<String> values) {
            addCriterion("FUNCTION_CODE in", values, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeNotIn(List<String> values) {
            addCriterion("FUNCTION_CODE not in", values, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeBetween(String value1, String value2) {
            addCriterion("FUNCTION_CODE between", value1, value2, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionCodeNotBetween(String value1, String value2) {
            addCriterion("FUNCTION_CODE not between", value1, value2, "functionCode");
            return (Criteria) this;
        }

        public Criteria andFunctionUrlIsNull() {
            addCriterion("FUNCTION_URL is null");
            return (Criteria) this;
        }

        public Criteria andFunctionUrlIsNotNull() {
            addCriterion("FUNCTION_URL is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionUrlEqualTo(String value) {
            addCriterion("FUNCTION_URL =", value, "functionUrl");
            return (Criteria) this;
        }

        public Criteria andFunctionUrlNotEqualTo(String value) {
            addCriterion("FUNCTION_URL <>", value, "functionUrl");
            return (Criteria) this;
        }

        public Criteria andFunctionUrlGreaterThan(String value) {
            addCriterion("FUNCTION_URL >", value, "functionUrl");
            return (Criteria) this;
        }

        public Criteria andFunctionUrlGreaterThanOrEqualTo(String value) {
            addCriterion("FUNCTION_URL >=", value, "functionUrl");
            return (Criteria) this;
        }

        public Criteria andFunctionUrlLessThan(String value) {
            addCriterion("FUNCTION_URL <", value, "functionUrl");
            return (Criteria) this;
        }

        public Criteria andFunctionUrlLessThanOrEqualTo(String value) {
            addCriterion("FUNCTION_URL <=", value, "functionUrl");
            return (Criteria) this;
        }

        public Criteria andFunctionUrlLike(String value) {
            addCriterion("FUNCTION_URL like", value, "functionUrl");
            return (Criteria) this;
        }

        public Criteria andFunctionUrlNotLike(String value) {
            addCriterion("FUNCTION_URL not like", value, "functionUrl");
            return (Criteria) this;
        }

        public Criteria andFunctionUrlIn(List<String> values) {
            addCriterion("FUNCTION_URL in", values, "functionUrl");
            return (Criteria) this;
        }

        public Criteria andFunctionUrlNotIn(List<String> values) {
            addCriterion("FUNCTION_URL not in", values, "functionUrl");
            return (Criteria) this;
        }

        public Criteria andFunctionUrlBetween(String value1, String value2) {
            addCriterion("FUNCTION_URL between", value1, value2, "functionUrl");
            return (Criteria) this;
        }

        public Criteria andFunctionUrlNotBetween(String value1, String value2) {
            addCriterion("FUNCTION_URL not between", value1, value2, "functionUrl");
            return (Criteria) this;
        }

        public Criteria andFunctionTypeIsNull() {
            addCriterion("FUNCTION_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andFunctionTypeIsNotNull() {
            addCriterion("FUNCTION_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFunctionTypeEqualTo(String value) {
            addCriterion("FUNCTION_TYPE =", value, "functionType");
            return (Criteria) this;
        }

        public Criteria andFunctionTypeNotEqualTo(String value) {
            addCriterion("FUNCTION_TYPE <>", value, "functionType");
            return (Criteria) this;
        }

        public Criteria andFunctionTypeGreaterThan(String value) {
            addCriterion("FUNCTION_TYPE >", value, "functionType");
            return (Criteria) this;
        }

        public Criteria andFunctionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("FUNCTION_TYPE >=", value, "functionType");
            return (Criteria) this;
        }

        public Criteria andFunctionTypeLessThan(String value) {
            addCriterion("FUNCTION_TYPE <", value, "functionType");
            return (Criteria) this;
        }

        public Criteria andFunctionTypeLessThanOrEqualTo(String value) {
            addCriterion("FUNCTION_TYPE <=", value, "functionType");
            return (Criteria) this;
        }

        public Criteria andFunctionTypeLike(String value) {
            addCriterion("FUNCTION_TYPE like", value, "functionType");
            return (Criteria) this;
        }

        public Criteria andFunctionTypeNotLike(String value) {
            addCriterion("FUNCTION_TYPE not like", value, "functionType");
            return (Criteria) this;
        }

        public Criteria andFunctionTypeIn(List<String> values) {
            addCriterion("FUNCTION_TYPE in", values, "functionType");
            return (Criteria) this;
        }

        public Criteria andFunctionTypeNotIn(List<String> values) {
            addCriterion("FUNCTION_TYPE not in", values, "functionType");
            return (Criteria) this;
        }

        public Criteria andFunctionTypeBetween(String value1, String value2) {
            addCriterion("FUNCTION_TYPE between", value1, value2, "functionType");
            return (Criteria) this;
        }

        public Criteria andFunctionTypeNotBetween(String value1, String value2) {
            addCriterion("FUNCTION_TYPE not between", value1, value2, "functionType");
            return (Criteria) this;
        }

        public Criteria andSeqIsNull() {
            addCriterion("SEQ is null");
            return (Criteria) this;
        }

        public Criteria andSeqIsNotNull() {
            addCriterion("SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andSeqEqualTo(BigDecimal value) {
            addCriterion("SEQ =", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotEqualTo(BigDecimal value) {
            addCriterion("SEQ <>", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThan(BigDecimal value) {
            addCriterion("SEQ >", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SEQ >=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThan(BigDecimal value) {
            addCriterion("SEQ <", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SEQ <=", value, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqIn(List<BigDecimal> values) {
            addCriterion("SEQ in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotIn(List<BigDecimal> values) {
            addCriterion("SEQ not in", values, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SEQ between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andSeqNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SEQ not between", value1, value2, "seq");
            return (Criteria) this;
        }

        public Criteria andDefaultActionIsNull() {
            addCriterion("DEFAULT_ACTION is null");
            return (Criteria) this;
        }

        public Criteria andDefaultActionIsNotNull() {
            addCriterion("DEFAULT_ACTION is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultActionEqualTo(String value) {
            addCriterion("DEFAULT_ACTION =", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionNotEqualTo(String value) {
            addCriterion("DEFAULT_ACTION <>", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionGreaterThan(String value) {
            addCriterion("DEFAULT_ACTION >", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionGreaterThanOrEqualTo(String value) {
            addCriterion("DEFAULT_ACTION >=", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionLessThan(String value) {
            addCriterion("DEFAULT_ACTION <", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionLessThanOrEqualTo(String value) {
            addCriterion("DEFAULT_ACTION <=", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionLike(String value) {
            addCriterion("DEFAULT_ACTION like", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionNotLike(String value) {
            addCriterion("DEFAULT_ACTION not like", value, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionIn(List<String> values) {
            addCriterion("DEFAULT_ACTION in", values, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionNotIn(List<String> values) {
            addCriterion("DEFAULT_ACTION not in", values, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionBetween(String value1, String value2) {
            addCriterion("DEFAULT_ACTION between", value1, value2, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andDefaultActionNotBetween(String value1, String value2) {
            addCriterion("DEFAULT_ACTION not between", value1, value2, "defaultAction");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNull() {
            addCriterion("CREATER is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNotNull() {
            addCriterion("CREATER is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterEqualTo(String value) {
            addCriterion("CREATER =", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotEqualTo(String value) {
            addCriterion("CREATER <>", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThan(String value) {
            addCriterion("CREATER >", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("CREATER >=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThan(String value) {
            addCriterion("CREATER <", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThanOrEqualTo(String value) {
            addCriterion("CREATER <=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLike(String value) {
            addCriterion("CREATER like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotLike(String value) {
            addCriterion("CREATER not like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterIn(List<String> values) {
            addCriterion("CREATER in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotIn(List<String> values) {
            addCriterion("CREATER not in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterBetween(String value1, String value2) {
            addCriterion("CREATER between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotBetween(String value1, String value2) {
            addCriterion("CREATER not between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNull() {
            addCriterion("UPDATER is null");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNotNull() {
            addCriterion("UPDATER is not null");
            return (Criteria) this;
        }

        public Criteria andUpdaterEqualTo(String value) {
            addCriterion("UPDATER =", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotEqualTo(String value) {
            addCriterion("UPDATER <>", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThan(String value) {
            addCriterion("UPDATER >", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATER >=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThan(String value) {
            addCriterion("UPDATER <", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThanOrEqualTo(String value) {
            addCriterion("UPDATER <=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLike(String value) {
            addCriterion("UPDATER like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotLike(String value) {
            addCriterion("UPDATER not like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterIn(List<String> values) {
            addCriterion("UPDATER in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotIn(List<String> values) {
            addCriterion("UPDATER not in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterBetween(String value1, String value2) {
            addCriterion("UPDATER between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotBetween(String value1, String value2) {
            addCriterion("UPDATER not between", value1, value2, "updater");
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