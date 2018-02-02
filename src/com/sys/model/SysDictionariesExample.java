package com.sys.model;

import com.sys.utils.BeanUtils;
import com.sys.utils.Page;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SysDictionariesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public SysDictionariesExample() {
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

        public Criteria andDicTypeIsNull() {
            addCriterion("DIC_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDicTypeIsNotNull() {
            addCriterion("DIC_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDicTypeEqualTo(String value) {
            addCriterion("DIC_TYPE =", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeNotEqualTo(String value) {
            addCriterion("DIC_TYPE <>", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeGreaterThan(String value) {
            addCriterion("DIC_TYPE >", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DIC_TYPE >=", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeLessThan(String value) {
            addCriterion("DIC_TYPE <", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeLessThanOrEqualTo(String value) {
            addCriterion("DIC_TYPE <=", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeLike(String value) {
            addCriterion("DIC_TYPE like", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeNotLike(String value) {
            addCriterion("DIC_TYPE not like", value, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeIn(List<String> values) {
            addCriterion("DIC_TYPE in", values, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeNotIn(List<String> values) {
            addCriterion("DIC_TYPE not in", values, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeBetween(String value1, String value2) {
            addCriterion("DIC_TYPE between", value1, value2, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicTypeNotBetween(String value1, String value2) {
            addCriterion("DIC_TYPE not between", value1, value2, "dicType");
            return (Criteria) this;
        }

        public Criteria andDicNameIsNull() {
            addCriterion("DIC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDicNameIsNotNull() {
            addCriterion("DIC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDicNameEqualTo(String value) {
            addCriterion("DIC_NAME =", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameNotEqualTo(String value) {
            addCriterion("DIC_NAME <>", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameGreaterThan(String value) {
            addCriterion("DIC_NAME >", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameGreaterThanOrEqualTo(String value) {
            addCriterion("DIC_NAME >=", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameLessThan(String value) {
            addCriterion("DIC_NAME <", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameLessThanOrEqualTo(String value) {
            addCriterion("DIC_NAME <=", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameLike(String value) {
            addCriterion("DIC_NAME like", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameNotLike(String value) {
            addCriterion("DIC_NAME not like", value, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameIn(List<String> values) {
            addCriterion("DIC_NAME in", values, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameNotIn(List<String> values) {
            addCriterion("DIC_NAME not in", values, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameBetween(String value1, String value2) {
            addCriterion("DIC_NAME between", value1, value2, "dicName");
            return (Criteria) this;
        }

        public Criteria andDicNameNotBetween(String value1, String value2) {
            addCriterion("DIC_NAME not between", value1, value2, "dicName");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("NOTE is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("NOTE =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("NOTE <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("NOTE >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("NOTE >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("NOTE <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("NOTE <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("NOTE like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("NOTE not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("NOTE in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("NOTE not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("NOTE between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("NOTE not between", value1, value2, "note");
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