package com.mobile.utils;

public class Const {
	
	/********************不需要验证token的地址*************************/
	public static final String NO_INTERCEPTOR_PATH = ".*/((test)|(login)|(tokenLogin)|(refreshToken)).*";//不需要验证token地址
	
	/*******************返回代码和代码描述**********************************/
	//返回成功
	public static final String SUCCESS = "SUCCESS_0001";//成功
	public static final String SUCCESS_DESCRIPTION = "操作成功";
	//通用错误类型
	public static final String SYS_ERROR = "SYS_ERROR";
	public static final String SYS_ERROR_DESCRIPTION = "系统错误";
	//token错误类型
	public static final String TOKEN_NULL = "TOKEN_ERROR_0001";
	public static final String TOKEN_NULL_DESCRIPTION = "没有token参数";
	public static final String TOKEN_EXP = "TOKEN_ERROR_0002";
	public static final String TOKEN_EXP_DESCRIPTION = "token过期";
	public static final String TOKEN_ERROR = "TOKEN_ERROR_0003";
	public static final String TOKEN_ERROR_DESCRIPTION = "token无效";
	
	/********************手机模块常量*********************/
	//request中的参数名
	public static final String REQUEST_TOKEN = "token";//request中token参数名称
	public static final String REQUEST_USER_ID = "request_user_id";//request中userid参数名称
	//系统是否常量
	public static final String SYS_NOT = "0";//否
	public static final String SYS_IS = "1";//是
	
}
