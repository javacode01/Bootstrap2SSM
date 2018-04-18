package com.sys.generator.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 字段结构
 * 
 * @author
 * @date
 */
public class ColumnMeta {

	private String columnName;
	private String ordinalPosition;
	private String isNullable;
	private String columnDefault;
	private String columnType;
	private String columnKey;
	private String extra;
	private String columnComment;
	private int columnSize; // 字段长度，将在计算属性类型时赋值

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(String ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	/**
	 * 获取属性名称
	 * 
	 * @return
	 */
	public String getPropertyName() {
		StringBuffer propertyName = new StringBuffer();
		String[] names = StringUtils.split(columnName.toLowerCase(), "_");
		propertyName.append(names[0]); // 第一个单词全小写
		for (int i = 1, len = names.length; i < len; i++) {
			propertyName.append(names[i].substring(0, 1).toUpperCase()
					+ names[i].substring(1));
		}
		return propertyName.toString();
	}

	/**
	 * 获取属性类型
	 * 
	 * @return
	 */
	public String getPropertyType() {
		String type = columnType.toLowerCase();
		String propertyType = null;
		if (StringUtils.startsWith(type, "int")) { // int/long
			columnSize = NumberUtils.toInt(StringUtils.substring(type,
					StringUtils.indexOf(type, "(") + 1,
					StringUtils.indexOf(type, ")")));
			if (columnSize <= 4)
				propertyType = "Integer";
			else
				propertyType = "Long";
		} else if (StringUtils.startsWith(type, "bigint")) { // long
			propertyType = "Long";
		} else if (StringUtils.startsWith(type, "double")) { // double
			propertyType = "Double";
		} else if (StringUtils.startsWith(type, "float")) { // float
			propertyType = "Float";
		} else if (StringUtils.startsWith(type, "varchar")) { // String
			columnSize = NumberUtils.toInt(StringUtils.substring(type,
					StringUtils.indexOf(type, "(") + 1,
					StringUtils.indexOf(type, ")")));
			propertyType = "String";
		} else if (StringUtils.startsWith(type, "char")) { // String
			columnSize = NumberUtils.toInt(StringUtils.substring(type,
					StringUtils.indexOf(type, "(") + 1,
					StringUtils.indexOf(type, ")")));
			propertyType = "String";
		} else if (StringUtils.startsWith(type, "text")) { // String
			propertyType = "String";
		} else if (StringUtils.startsWith(type, "date")) { // date
			propertyType = "java.util.Date";
		} else if (StringUtils.startsWith(type, "datetime")) { // date
			propertyType = "java.util.Date";
		} else if (StringUtils.startsWith(type, "timestamp")) { // date
			propertyType = "java.util.Date";

		} else {
			System.out.println("==类型[" + type + "]解析尚不支持==");
		}
		return propertyType;
	}

	public int getColumnSize() {
		return columnSize;
	}

}