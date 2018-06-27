package com.sys.utils;

/**
 * ajax请求返回数据格式类
 * @author Administrator
 *
 */
public class ResultData {
	private String code;
	private Object data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
