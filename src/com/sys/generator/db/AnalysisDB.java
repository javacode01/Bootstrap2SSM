package com.sys.generator.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sys.generator.consts.GeneratorConsts;
import com.sys.generator.model.ColumnMeta;
import com.sys.generator.model.TableMeta;

/**
 * 解析表结构
 * 
 * @author
 * @date
 */
public class AnalysisDB {

	/**
	 * 读取表列表
	 * 
	 * @return
	 */
	public static final List<TableMeta> readDB() {
		List<TableMeta> list = new ArrayList<TableMeta>();
		String sql = "SELECT TABLE_NAME,COMMENTS FROM USER_TAB_COMMENTS "
				+ " WHERE table_type ='TABLE' and TABLE_NAME='"+GeneratorConsts.TABLE_NAME+"'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBHandler.createConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				TableMeta tm = new TableMeta();
				tm.setSchemaName(GeneratorConsts.DB_NAME);
				tm.setTableName(rs.getString("TABLE_NAME"));
				tm.setComment(rs.getString("COMMENTS"));
				list.add(tm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 填充字段信息
	 * 
	 * @param tables
	 */
	public static final void readTables(List<TableMeta> tables) {
		if (tables != null) {
			for (TableMeta tm : tables) {
				readTable(tm);
			}
		}
		DBHandler.close();
	}

	/**
	 * 读取表结构
	 * 
	 * @param table
	 */
	private static final void readTable(TableMeta table) {
		List<ColumnMeta> list = new ArrayList<ColumnMeta>();
		String sql = "SELECT a.COLUMN_NAME,a.NULLABLE,a.DATA_DEFAULT,a.DATA_TYPE,b.COMMENTS "
				+ " FROM USER_TAB_COLUMNS a,USER_COL_COMMENTS b " +
				"	WHERE a.table_name =b.table_name" +
				"	  and a.COLUMN_NAME=b.COLUMN_NAME" +
				"	  and a.table_name='"+table.getTableName()+"'";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBHandler.createConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ColumnMeta fm = new ColumnMeta();

				fm.setColumnName(rs.getString("COLUMN_NAME"));
				fm.setIsNullable(rs.getString("NULLABLE"));
				fm.setColumnDefault(rs.getString("DATA_DEFAULT"));
				fm.setColumnType(rs.getString("DATA_TYPE"));
				fm.setColumnComment(rs.getString("COMMENTS"));

				list.add(fm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		table.setColumns(list);
	}

}
