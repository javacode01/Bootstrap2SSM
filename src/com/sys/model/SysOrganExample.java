package com.sys.model;

import com.sys.utils.BeanUtils;
import com.sys.utils.Page;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SysOrganExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public SysOrganExample() {
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

        public Criteria andOrganIdIsNull() {
            addCriterion("ORGAN_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrganIdIsNotNull() {
            addCriterion("ORGAN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrganIdEqualTo(String value) {
            addCriterion("ORGAN_ID =", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdNotEqualTo(String value) {
            addCriterion("ORGAN_ID <>", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdGreaterThan(String value) {
            addCriterion("ORGAN_ID >", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORGAN_ID >=", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdLessThan(String value) {
            addCriterion("ORGAN_ID <", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdLessThanOrEqualTo(String value) {
            addCriterion("ORGAN_ID <=", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdLike(String value) {
            addCriterion("ORGAN_ID like", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdNotLike(String value) {
            addCriterion("ORGAN_ID not like", value, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdIn(List<String> values) {
            addCriterion("ORGAN_ID in", values, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdNotIn(List<String> values) {
            addCriterion("ORGAN_ID not in", values, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdBetween(String value1, String value2) {
            addCriterion("ORGAN_ID between", value1, value2, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganIdNotBetween(String value1, String value2) {
            addCriterion("ORGAN_ID not between", value1, value2, "organId");
            return (Criteria) this;
        }

        public Criteria andOrganCodeIsNull() {
            addCriterion("ORGAN_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrganCodeIsNotNull() {
            addCriterion("ORGAN_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrganCodeEqualTo(String value) {
            addCriterion("ORGAN_CODE =", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeNotEqualTo(String value) {
            addCriterion("ORGAN_CODE <>", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeGreaterThan(String value) {
            addCriterion("ORGAN_CODE >", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORGAN_CODE >=", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeLessThan(String value) {
            addCriterion("ORGAN_CODE <", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeLessThanOrEqualTo(String value) {
            addCriterion("ORGAN_CODE <=", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeLike(String value) {
            addCriterion("ORGAN_CODE like", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeNotLike(String value) {
            addCriterion("ORGAN_CODE not like", value, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeIn(List<String> values) {
            addCriterion("ORGAN_CODE in", values, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeNotIn(List<String> values) {
            addCriterion("ORGAN_CODE not in", values, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeBetween(String value1, String value2) {
            addCriterion("ORGAN_CODE between", value1, value2, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganCodeNotBetween(String value1, String value2) {
            addCriterion("ORGAN_CODE not between", value1, value2, "organCode");
            return (Criteria) this;
        }

        public Criteria andOrganNameIsNull() {
            addCriterion("ORGAN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrganNameIsNotNull() {
            addCriterion("ORGAN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrganNameEqualTo(String value) {
            addCriterion("ORGAN_NAME =", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameNotEqualTo(String value) {
            addCriterion("ORGAN_NAME <>", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameGreaterThan(String value) {
            addCriterion("ORGAN_NAME >", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORGAN_NAME >=", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameLessThan(String value) {
            addCriterion("ORGAN_NAME <", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameLessThanOrEqualTo(String value) {
            addCriterion("ORGAN_NAME <=", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameLike(String value) {
            addCriterion("ORGAN_NAME like", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameNotLike(String value) {
            addCriterion("ORGAN_NAME not like", value, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameIn(List<String> values) {
            addCriterion("ORGAN_NAME in", values, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameNotIn(List<String> values) {
            addCriterion("ORGAN_NAME not in", values, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameBetween(String value1, String value2) {
            addCriterion("ORGAN_NAME between", value1, value2, "organName");
            return (Criteria) this;
        }

        public Criteria andOrganNameNotBetween(String value1, String value2) {
            addCriterion("ORGAN_NAME not between", value1, value2, "organName");
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

        public Criteria andIconUrlIsNull() {
            addCriterion("ICON_URL is null");
            return (Criteria) this;
        }

        public Criteria andIconUrlIsNotNull() {
            addCriterion("ICON_URL is not null");
            return (Criteria) this;
        }

        public Criteria andIconUrlEqualTo(String value) {
            addCriterion("ICON_URL =", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlNotEqualTo(String value) {
            addCriterion("ICON_URL <>", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlGreaterThan(String value) {
            addCriterion("ICON_URL >", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlGreaterThanOrEqualTo(String value) {
            addCriterion("ICON_URL >=", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlLessThan(String value) {
            addCriterion("ICON_URL <", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlLessThanOrEqualTo(String value) {
            addCriterion("ICON_URL <=", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlLike(String value) {
            addCriterion("ICON_URL like", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlNotLike(String value) {
            addCriterion("ICON_URL not like", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlIn(List<String> values) {
            addCriterion("ICON_URL in", values, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlNotIn(List<String> values) {
            addCriterion("ICON_URL not in", values, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlBetween(String value1, String value2) {
            addCriterion("ICON_URL between", value1, value2, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlNotBetween(String value1, String value2) {
            addCriterion("ICON_URL not between", value1, value2, "iconUrl");
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

        public Criteria andOrganLevelIsNull() {
            addCriterion("ORGAN_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andOrganLevelIsNotNull() {
            addCriterion("ORGAN_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andOrganLevelEqualTo(String value) {
            addCriterion("ORGAN_LEVEL =", value, "organLevel");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNotEqualTo(String value) {
            addCriterion("ORGAN_LEVEL <>", value, "organLevel");
            return (Criteria) this;
        }

        public Criteria andOrganLevelGreaterThan(String value) {
            addCriterion("ORGAN_LEVEL >", value, "organLevel");
            return (Criteria) this;
        }

        public Criteria andOrganLevelGreaterThanOrEqualTo(String value) {
            addCriterion("ORGAN_LEVEL >=", value, "organLevel");
            return (Criteria) this;
        }

        public Criteria andOrganLevelLessThan(String value) {
            addCriterion("ORGAN_LEVEL <", value, "organLevel");
            return (Criteria) this;
        }

        public Criteria andOrganLevelLessThanOrEqualTo(String value) {
            addCriterion("ORGAN_LEVEL <=", value, "organLevel");
            return (Criteria) this;
        }

        public Criteria andOrganLevelLike(String value) {
            addCriterion("ORGAN_LEVEL like", value, "organLevel");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNotLike(String value) {
            addCriterion("ORGAN_LEVEL not like", value, "organLevel");
            return (Criteria) this;
        }

        public Criteria andOrganLevelIn(List<String> values) {
            addCriterion("ORGAN_LEVEL in", values, "organLevel");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNotIn(List<String> values) {
            addCriterion("ORGAN_LEVEL not in", values, "organLevel");
            return (Criteria) this;
        }

        public Criteria andOrganLevelBetween(String value1, String value2) {
            addCriterion("ORGAN_LEVEL between", value1, value2, "organLevel");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNotBetween(String value1, String value2) {
            addCriterion("ORGAN_LEVEL not between", value1, value2, "organLevel");
            return (Criteria) this;
        }

        public Criteria andOrganClassIsNull() {
            addCriterion("ORGAN_CLASS is null");
            return (Criteria) this;
        }

        public Criteria andOrganClassIsNotNull() {
            addCriterion("ORGAN_CLASS is not null");
            return (Criteria) this;
        }

        public Criteria andOrganClassEqualTo(String value) {
            addCriterion("ORGAN_CLASS =", value, "organClass");
            return (Criteria) this;
        }

        public Criteria andOrganClassNotEqualTo(String value) {
            addCriterion("ORGAN_CLASS <>", value, "organClass");
            return (Criteria) this;
        }

        public Criteria andOrganClassGreaterThan(String value) {
            addCriterion("ORGAN_CLASS >", value, "organClass");
            return (Criteria) this;
        }

        public Criteria andOrganClassGreaterThanOrEqualTo(String value) {
            addCriterion("ORGAN_CLASS >=", value, "organClass");
            return (Criteria) this;
        }

        public Criteria andOrganClassLessThan(String value) {
            addCriterion("ORGAN_CLASS <", value, "organClass");
            return (Criteria) this;
        }

        public Criteria andOrganClassLessThanOrEqualTo(String value) {
            addCriterion("ORGAN_CLASS <=", value, "organClass");
            return (Criteria) this;
        }

        public Criteria andOrganClassLike(String value) {
            addCriterion("ORGAN_CLASS like", value, "organClass");
            return (Criteria) this;
        }

        public Criteria andOrganClassNotLike(String value) {
            addCriterion("ORGAN_CLASS not like", value, "organClass");
            return (Criteria) this;
        }

        public Criteria andOrganClassIn(List<String> values) {
            addCriterion("ORGAN_CLASS in", values, "organClass");
            return (Criteria) this;
        }

        public Criteria andOrganClassNotIn(List<String> values) {
            addCriterion("ORGAN_CLASS not in", values, "organClass");
            return (Criteria) this;
        }

        public Criteria andOrganClassBetween(String value1, String value2) {
            addCriterion("ORGAN_CLASS between", value1, value2, "organClass");
            return (Criteria) this;
        }

        public Criteria andOrganClassNotBetween(String value1, String value2) {
            addCriterion("ORGAN_CLASS not between", value1, value2, "organClass");
            return (Criteria) this;
        }

        public Criteria andOrganTypeIsNull() {
            addCriterion("ORGAN_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOrganTypeIsNotNull() {
            addCriterion("ORGAN_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOrganTypeEqualTo(String value) {
            addCriterion("ORGAN_TYPE =", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeNotEqualTo(String value) {
            addCriterion("ORGAN_TYPE <>", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeGreaterThan(String value) {
            addCriterion("ORGAN_TYPE >", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ORGAN_TYPE >=", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeLessThan(String value) {
            addCriterion("ORGAN_TYPE <", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeLessThanOrEqualTo(String value) {
            addCriterion("ORGAN_TYPE <=", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeLike(String value) {
            addCriterion("ORGAN_TYPE like", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeNotLike(String value) {
            addCriterion("ORGAN_TYPE not like", value, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeIn(List<String> values) {
            addCriterion("ORGAN_TYPE in", values, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeNotIn(List<String> values) {
            addCriterion("ORGAN_TYPE not in", values, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeBetween(String value1, String value2) {
            addCriterion("ORGAN_TYPE between", value1, value2, "organType");
            return (Criteria) this;
        }

        public Criteria andOrganTypeNotBetween(String value1, String value2) {
            addCriterion("ORGAN_TYPE not between", value1, value2, "organType");
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