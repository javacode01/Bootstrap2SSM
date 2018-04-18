package com.sys.generator.consts;

/**
 * 数据库常量
 * 
 * @author
 * @date
 */
public interface GeneratorConsts {

	String DB_NAME = "orcl"; // 数据库名称
	String DB_HOST = "localhost"; // 数据库HOST
	int DB_PORT = 1521; // 数据库端口
	String DB_USER = "SDGS12315"; // 用户名
	String DB_PASS = "SDGS12315"; // 密码
	String DB_TABLE_PREFIX = ""; // 表前缀
	String TABLE_NAME = "DEMO_ORDER"; // 表名
	String TARGET_DIR = "jsp/demo/order/"; // 生成代码存放目录
	String URL_PREFIX="demo";//请求前缀
	String BASE_PACKAGE = "com.demo";//生成java类所在的包
	String BASE_MODULE = ".order";//生成java类所属模块
}
