package com.sys.utils;

/**
 * @ClassName: SysConstant 
 * @Description: 系统常量类
 * @author AK
 * @date 2017-11-22 下午3:58:39 
 *
 */
public class SysConstant {
	
	/***********************系统返回结果***********************/
	public final static String SYS_ERROR = "error";
	public final static String SYS_ERROR_DESCRIPTION = "系统错误";
	public final static String SYS_SUCCESS = "success";
	public final static String SYS_SUCCESS_DESCRIPTION = "操作成功";
	public final static String SYS_ERROR_PARAMETER = "error_0001";
	public final static String SYS_ERROR_PARAMETER_DESCRIPTION = "参数错误";
	public final static String SYS_ERROR_EXISTS = "error_0002";
	public final static String SYS_ERROR_EXISTS_DESCRIPTION = "数据重复";
	public final static String SYS_ERROR_STATUS = "error_0003";
	public final static String SYS_ERROR_STATUS_DESCRIPTION = "数据状态错误";
	public final static String SYS_USER_ERROR_PASSWORD = "error_user_0001";
	public final static String SYS_USER_ERROR_PASSWORD_DESCRIPTION = "密码错误";
	
	/*********功能级别*********/
	public final static String SYS_FUNCTION_LEVEL_MODULE = "1";//模块
	public final static String SYS_FUNCTION_LEVEL_FUNCTION = "2";//功能
	public final static String SYS_FUNCTION_LEVEL_HANDLE = "3";//操作
	/*********根功能编码*********/
	public final static String SYS_FUNCTION_ROOT_CODE = "root";//根功能编码
	/*******************操作类型*********************/
	public final static String SYS_HANDLE_ADD = "add";//新增
	public final static String SYS_HANDLE_EDIT = "edit";//编辑
	/*****************用户状态***********************/
	public final static String SYS_USERS_STATUS_0 = "0";//锁定
	public final static String SYS_USERS_STATUS_1 = "1";//正常
	/*****************是否系统用户***********************/
	public final static String SYS_USERS_SYSTEM_0 = "0";//否
	public final static String SYS_USERS_SYSTEM_1 = "1";//是
	
}
