package com.sys.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.client.SysFunctionsMapper;
import com.sys.client.SysRoleFunctionMapper;
import com.sys.model.SysFunctions;
import com.sys.model.SysFunctionsExample;
import com.sys.model.SysRoleFunctionExample;
import com.sys.utils.BspUtils;
import com.sys.utils.PageListData;
import com.sys.utils.SysConstant;

@Service
public class SysFunctionsService {
	
	@Autowired
	private SysFunctionsMapper sysFunctionsMapper;
	@Autowired
	private SysRoleFunctionMapper sysRoleFunctionMapper;
	
	/**
	 * 获取用户菜单
	 * @return
	 */
	public Map<String, List<SysFunctions>> getUserMenus() {
		// TODO Auto-generated method stub
		//获取当前用户
		String userCode = BspUtils.getLoginUser().getUserCode();
		//获取用户已分配的模块、功能和操作
		List<SysFunctions> userFunctionList = sysFunctionsMapper.getFunctionsByUserCode(userCode);
		//获取一级菜单
		List<SysFunctions> level1List = new ArrayList<SysFunctions>();
		//用户已分配操作
		List<SysFunctions> handleList = new ArrayList<SysFunctions>();
		//用户已分配功能
		List<SysFunctions> functionList = new ArrayList<SysFunctions>();
		//用户已分配模块
		List<SysFunctions> moduleList = new ArrayList<SysFunctions>();
		//区分模块、功能、操作
		if(null!=userFunctionList&&userFunctionList.size()>0) {
			for(SysFunctions function:userFunctionList) {
				if("root".equals(function.getParentCode())) {
					level1List.add(function);
				}else if(SysConstant.SYS_FUNCTION_LEVEL_MODULE.equals(function.getFunctionLevel())) {
					moduleList.add(function);
				}else if(SysConstant.SYS_FUNCTION_LEVEL_FUNCTION.equals(function.getFunctionLevel())) {
					functionList.add(function);
				}else if(SysConstant.SYS_FUNCTION_LEVEL_HANDLE.equals(function.getFunctionLevel())&&SysConstant.SYS_IS.equals(function.getDefaultAction())) {
					handleList.add(function);
				}
			}
		}
		//功能排序
		Collections.sort(functionList, new SortBySeq());
		//模块排序
		Collections.sort(moduleList, new SortBySeq());
		//一级菜单排序
		Collections.sort(level1List, new SortBySeq());
		Map<String,List<SysFunctions>> userFunctionMap = new HashMap<String,List<SysFunctions>>();
		userFunctionMap.put("handleList", handleList);
		userFunctionMap.put("functionList", functionList);
		userFunctionMap.put("moduleList", moduleList);
		userFunctionMap.put("level1List", level1List);
		return userFunctionMap;
	}
	
	/**
	 * 根据查询条件获取全部功能
	 * @param example
	 * @return
	 */
	public List<SysFunctions> listFunctionsByExample(SysFunctionsExample example) {
		// TODO Auto-generated method stub
		return sysFunctionsMapper.selectByExample(example);
	}
	
	/**
	 * 分页查询
	 * @param pote
	 * @return
	 */
	public PageListData listFunctionsByPage(SysFunctionsExample example) {
		// TODO Auto-generated method stub
		int totalCount = sysFunctionsMapper.countByExample(example);
		List<SysFunctions> list = sysFunctionsMapper.selectByExample(example);			
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}
	
	/**
	 * 根据条件查询功能列表
	 * @param pote
	 * @return
	 */
	public List<SysFunctions> listFunctions(SysFunctionsExample example) {
		// TODO Auto-generated method stub
		return sysFunctionsMapper.selectByExample(example);
	}
	
	/**
	 * 新增功能
	 * @param add
	 */
	public void addSysFunctions(SysFunctions add) {
		// TODO Auto-generated method stub
		sysFunctionsMapper.insertSelective(add);
	}

	/**
	 * 保存编辑功能
	 * @param edit
	 */
	public void updateSysFunctions(SysFunctions edit) {
		// TODO Auto-generated method stub
		sysFunctionsMapper.updateByPrimaryKeySelective(edit);
	}
	
	/**
	 * 删除功能及其子功能
	 * @param recid
	 */
	@Transactional
	public void deleteSysFunctions(String functionId) {
		// TODO Auto-generated method stub
		SysFunctions function = sysFunctionsMapper.selectByPrimaryKey(functionId);
		if(null!=function) {
			recursionDeleteSysFunctions(function);
		}
	}
	
	/**
	 * 递归删除功能及其子功能
	 * @param function
	 */
	public void recursionDeleteSysFunctions(SysFunctions function) {
		SysFunctionsExample example = new SysFunctionsExample();
		example.createCriteria().andParentCodeEqualTo(function.getFunctionCode());
		List<SysFunctions> list = sysFunctionsMapper.selectByExample(example);
		if(null!=list&&list.size()>0) {
			for(SysFunctions temp : list) {
				recursionDeleteSysFunctions(temp);
			}
		}
		sysFunctionsMapper.deleteByPrimaryKey(function.getFunctionId());
		SysRoleFunctionExample example1 = new SysRoleFunctionExample();
		example1.createCriteria().andFunctionCodeEqualTo(function.getFunctionCode());
		sysRoleFunctionMapper.deleteByExample(example1);
	}
	
	 class SortBySeq implements Comparator {
         public int compare(Object object1, Object object2) {
	         SysFunctions function1 = (SysFunctions) object1;
	         SysFunctions function2 = (SysFunctions) object2;
	         return function1.getSeq().compareTo(function2.getSeq());
         }
     }

}
