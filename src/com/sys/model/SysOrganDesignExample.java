package com.sys.model;

import com.sys.utils.BeanUtils;
import com.sys.utils.Page;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SysOrganDesignExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public SysOrganDesignExample() {
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

        public Criteria andRecidIsNull() {
            addCriterion("RECID is null");
            return (Criteria) this;
        }

        public Criteria andRecidIsNotNull() {
            addCriterion("RECID is not null");
            return (Criteria) this;
        }

        public Criteria andRecidEqualTo(String value) {
            addCriterion("RECID =", value, "recid");
            return (Criteria) this;
        }

        public Criteria andRecidNotEqualTo(String value) {
            addCriterion("RECID <>", value, "recid");
            return (Criteria) this;
        }

        public Criteria andRecidGreaterThan(String value) {
            addCriterion("RECID >", value, "recid");
            return (Criteria) this;
        }

        public Criteria andRecidGreaterThanOrEqualTo(String value) {
            addCriterion("RECID >=", value, "recid");
            return (Criteria) this;
        }

        public Criteria andRecidLessThan(String value) {
            addCriterion("RECID <", value, "recid");
            return (Criteria) this;
        }

        public Criteria andRecidLessThanOrEqualTo(String value) {
            addCriterion("RECID <=", value, "recid");
            return (Criteria) this;
        }

        public Criteria andRecidLike(String value) {
            addCriterion("RECID like", value, "recid");
            return (Criteria) this;
        }

        public Criteria andRecidNotLike(String value) {
            addCriterion("RECID not like", value, "recid");
            return (Criteria) this;
        }

        public Criteria andRecidIn(List<String> values) {
            addCriterion("RECID in", values, "recid");
            return (Criteria) this;
        }

        public Criteria andRecidNotIn(List<String> values) {
            addCriterion("RECID not in", values, "recid");
            return (Criteria) this;
        }

        public Criteria andRecidBetween(String value1, String value2) {
            addCriterion("RECID between", value1, value2, "recid");
            return (Criteria) this;
        }

        public Criteria andRecidNotBetween(String value1, String value2) {
            addCriterion("RECID not between", value1, value2, "recid");
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

        public Criteria andOrganLevelNameIsNull() {
            addCriterion("ORGAN_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNameIsNotNull() {
            addCriterion("ORGAN_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNameEqualTo(String value) {
            addCriterion("ORGAN_LEVEL_NAME =", value, "organLevelName");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNameNotEqualTo(String value) {
            addCriterion("ORGAN_LEVEL_NAME <>", value, "organLevelName");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNameGreaterThan(String value) {
            addCriterion("ORGAN_LEVEL_NAME >", value, "organLevelName");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORGAN_LEVEL_NAME >=", value, "organLevelName");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNameLessThan(String value) {
            addCriterion("ORGAN_LEVEL_NAME <", value, "organLevelName");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNameLessThanOrEqualTo(String value) {
            addCriterion("ORGAN_LEVEL_NAME <=", value, "organLevelName");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNameLike(String value) {
            addCriterion("ORGAN_LEVEL_NAME like", value, "organLevelName");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNameNotLike(String value) {
            addCriterion("ORGAN_LEVEL_NAME not like", value, "organLevelName");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNameIn(List<String> values) {
            addCriterion("ORGAN_LEVEL_NAME in", values, "organLevelName");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNameNotIn(List<String> values) {
            addCriterion("ORGAN_LEVEL_NAME not in", values, "organLevelName");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNameBetween(String value1, String value2) {
            addCriterion("ORGAN_LEVEL_NAME between", value1, value2, "organLevelName");
            return (Criteria) this;
        }

        public Criteria andOrganLevelNameNotBetween(String value1, String value2) {
            addCriterion("ORGAN_LEVEL_NAME not between", value1, value2, "organLevelName");
            return (Criteria) this;
        }

        public Criteria andNextLevelIsNull() {
            addCriterion("NEXT_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andNextLevelIsNotNull() {
            addCriterion("NEXT_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andNextLevelEqualTo(String value) {
            addCriterion("NEXT_LEVEL =", value, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelNotEqualTo(String value) {
            addCriterion("NEXT_LEVEL <>", value, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelGreaterThan(String value) {
            addCriterion("NEXT_LEVEL >", value, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelGreaterThanOrEqualTo(String value) {
            addCriterion("NEXT_LEVEL >=", value, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelLessThan(String value) {
            addCriterion("NEXT_LEVEL <", value, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelLessThanOrEqualTo(String value) {
            addCriterion("NEXT_LEVEL <=", value, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelLike(String value) {
            addCriterion("NEXT_LEVEL like", value, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelNotLike(String value) {
            addCriterion("NEXT_LEVEL not like", value, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelIn(List<String> values) {
            addCriterion("NEXT_LEVEL in", values, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelNotIn(List<String> values) {
            addCriterion("NEXT_LEVEL not in", values, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelBetween(String value1, String value2) {
            addCriterion("NEXT_LEVEL between", value1, value2, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andNextLevelNotBetween(String value1, String value2) {
            addCriterion("NEXT_LEVEL not between", value1, value2, "nextLevel");
            return (Criteria) this;
        }

        public Criteria andHaveUserIsNull() {
            addCriterion("HAVE_USER is null");
            return (Criteria) this;
        }

        public Criteria andHaveUserIsNotNull() {
            addCriterion("HAVE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andHaveUserEqualTo(String value) {
            addCriterion("HAVE_USER =", value, "haveUser");
            return (Criteria) this;
        }

        public Criteria andHaveUserNotEqualTo(String value) {
            addCriterion("HAVE_USER <>", value, "haveUser");
            return (Criteria) this;
        }

        public Criteria andHaveUserGreaterThan(String value) {
            addCriterion("HAVE_USER >", value, "haveUser");
            return (Criteria) this;
        }

        public Criteria andHaveUserGreaterThanOrEqualTo(String value) {
            addCriterion("HAVE_USER >=", value, "haveUser");
            return (Criteria) this;
        }

        public Criteria andHaveUserLessThan(String value) {
            addCriterion("HAVE_USER <", value, "haveUser");
            return (Criteria) this;
        }

        public Criteria andHaveUserLessThanOrEqualTo(String value) {
            addCriterion("HAVE_USER <=", value, "haveUser");
            return (Criteria) this;
        }

        public Criteria andHaveUserLike(String value) {
            addCriterion("HAVE_USER like", value, "haveUser");
            return (Criteria) this;
        }

        public Criteria andHaveUserNotLike(String value) {
            addCriterion("HAVE_USER not like", value, "haveUser");
            return (Criteria) this;
        }

        public Criteria andHaveUserIn(List<String> values) {
            addCriterion("HAVE_USER in", values, "haveUser");
            return (Criteria) this;
        }

        public Criteria andHaveUserNotIn(List<String> values) {
            addCriterion("HAVE_USER not in", values, "haveUser");
            return (Criteria) this;
        }

        public Criteria andHaveUserBetween(String value1, String value2) {
            addCriterion("HAVE_USER between", value1, value2, "haveUser");
            return (Criteria) this;
        }

        public Criteria andHaveUserNotBetween(String value1, String value2) {
            addCriterion("HAVE_USER not between", value1, value2, "haveUser");
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