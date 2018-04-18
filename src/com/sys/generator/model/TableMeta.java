package com.sys.generator.model;


import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sys.generator.consts.GeneratorConsts;

/**
 * 表结构信息
 * 
 * @author
 * @date
 */
public class TableMeta {

	private String schemaName;
	private String tableName;
	private String className;
	private String comment;
	private List<ColumnMeta> columns;
	private String urlPrefix;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<ColumnMeta> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnMeta> columns) {
		this.columns = columns;
	}

	public String getClassName() {
		if (tableName == null)
			return "";
		StringBuffer className = new StringBuffer();
		if (StringUtils.startsWith(tableName, GeneratorConsts.DB_TABLE_PREFIX)) {
			String newName = StringUtils.substring(tableName,
					GeneratorConsts.DB_TABLE_PREFIX.length());
			String[] names = StringUtils.split(newName.toLowerCase(), "_");
			for (int i = 0, len = names.length; i < len; i++) {
				className.append(names[i].substring(0, 1).toUpperCase()
						+ names[i].substring(1));
			}
		} else {
			System.out.println("==不支持的表前缀==");
		}
		return className.toString();
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	
	public String getBasePackage(){
		return GeneratorConsts.BASE_PACKAGE;
	}
	
	public String getBaseModule(){
		return GeneratorConsts.BASE_MODULE;
	}

	public String getUrlPrefix(){
		return GeneratorConsts.URL_PREFIX;
	}
	public String getTargetDir(){
		return GeneratorConsts.TARGET_DIR;
	}
}