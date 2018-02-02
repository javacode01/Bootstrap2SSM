package com.sys.utils.cache;

import java.util.List;

import org.apache.log4j.Logger;

import com.sys.client.SysFunctionsMapper;
import com.sys.model.SysFunctions;
import com.sys.model.SysFunctionsExample;
import com.sys.utils.BspUtils;
import com.sys.utils.SysConstant;

/**
 * @ClassName: FunctionsCache 
 * @Description: 功能缓存
 * @author AK
 * @date 2017-11-22 下午3:38:41 
 *
 */
public class FunctionsCache {
	
	private static final Logger logger = Logger.getLogger(FunctionsCache.class);
	//模块
	private static List<SysFunctions> moduleList;
	//功能
	private static List<SysFunctions> functionList;
	//操作
	private static List<SysFunctions> handleList;
	
	static{
		try{
			System.out.println("功能加载开始==================================");
			logger.info("功能加载开始==================================");
			SysFunctionsMapper functionMapper = BspUtils.getBean(SysFunctionsMapper.class);
			SysFunctionsExample example1 = new SysFunctionsExample();
			example1.createCriteria().andFunctionLevelEqualTo(SysConstant.SYS_FUNCTION_LEVEL_MODULE);
			example1.setOrderByClause("seq asc,function_code asc");
			setModuleList(functionMapper.selectByExample(example1));
			SysFunctionsExample example2 = new SysFunctionsExample();
			example2.createCriteria().andFunctionLevelEqualTo(SysConstant.SYS_FUNCTION_LEVEL_FUNCTION);
			example2.setOrderByClause("seq asc,function_code asc");
			setFunctionList(functionMapper.selectByExample(example2));
			SysFunctionsExample example3 = new SysFunctionsExample();
			example3.createCriteria().andFunctionLevelEqualTo(SysConstant.SYS_FUNCTION_LEVEL_HANDLE);
			example3.setOrderByClause("seq asc,function_code asc");
			setHandleList(functionMapper.selectByExample(example3));
			logger.info("功能加载完成==================================");
			System.out.println("功能加载完成==================================");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static List<SysFunctions> getModuleList() {
		return moduleList;
	}

	public static void setModuleList(List<SysFunctions> moduleList) {
		FunctionsCache.moduleList = moduleList;
	}

	public static List<SysFunctions> getFunctionList() {
		return functionList;
	}

	public static void setFunctionList(List<SysFunctions> functionList) {
		FunctionsCache.functionList = functionList;
	}

	public static List<SysFunctions> getHandleList() {
		return handleList;
	}

	public static void setHandleList(List<SysFunctions> handleList) {
		FunctionsCache.handleList = handleList;
	}
	
	
}
