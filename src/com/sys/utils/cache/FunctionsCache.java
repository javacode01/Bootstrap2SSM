package com.sys.utils.cache;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.sys.client.SysFunctionsMapper;
import com.sys.model.SysFunctions;
import com.sys.model.SysFunctionsExample;
import com.sys.utils.SysConstant;

/**
 * @ClassName: FunctionsCache 
 * @Description: 功能缓存
 * @author AK
 * @date 2017-11-22 下午3:38:41 
 *
 */
public class FunctionsCache implements InitializingBean{
	
	private static final Logger logger = Logger.getLogger(FunctionsCache.class);
	
	@Autowired
	private SysFunctionsMapper sysFunctionsMapper;
	
	//模块
	private List<SysFunctions> moduleList;
	//功能
	private List<SysFunctions> functionList;
	//操作
	private List<SysFunctions> handleList;
	
	public List<SysFunctions> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<SysFunctions> moduleList1) {
		moduleList = moduleList1;
	}

	public List<SysFunctions> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<SysFunctions> functionList1) {
		functionList = functionList1;
	}

	public List<SysFunctions> getHandleList() {
		return handleList;
	}

	public void setHandleList(List<SysFunctions> handleList1) {
		handleList = handleList1;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		logger.info("各级功能缓存加载开始==================================");
		SysFunctionsExample example1 = new SysFunctionsExample();
		example1.createCriteria().andFunctionLevelEqualTo(SysConstant.SYS_FUNCTION_LEVEL_MODULE);
		example1.setOrderByClause("seq asc,function_code asc");
		setModuleList(sysFunctionsMapper.selectByExample(example1));
		SysFunctionsExample example2 = new SysFunctionsExample();
		example2.createCriteria().andFunctionLevelEqualTo(SysConstant.SYS_FUNCTION_LEVEL_FUNCTION);
		example2.setOrderByClause("seq asc,function_code asc");
		setFunctionList(sysFunctionsMapper.selectByExample(example2));
		SysFunctionsExample example3 = new SysFunctionsExample();
		example3.createCriteria().andFunctionLevelEqualTo(SysConstant.SYS_FUNCTION_LEVEL_HANDLE);
		example3.setOrderByClause("seq asc,function_code asc");
		setHandleList(sysFunctionsMapper.selectByExample(example3));
		logger.info("各级功能缓存加载完成==================================");
	}
	
	/**
	 * 刷新功能缓存
	 * @throws Exception 
	 */
	public void refresh() throws Exception {
		afterPropertiesSet();
	}
	
	
}
