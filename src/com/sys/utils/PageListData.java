package com.sys.utils;

import java.util.List;
/**
 * 分页数据格式
 * @author Administrator
 *
 */
public class PageListData {
	
	private int total;
	private List<?> rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
