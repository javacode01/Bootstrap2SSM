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
import com.sys.model.SysRoleFunction;
import com.sys.model.SysRoleFunctionExample;
import com.sys.utils.BspUtils;
import com.sys.utils.ConfigUtils;
import com.sys.utils.PageListData;
import com.sys.utils.SysConstant;
import com.sys.utils.ZTreeNode;

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
		List<SysFunctions> userFunctionList = null;
		if("false".equals(ConfigUtils.getValue("sys.productModel"))) {//开发模式，用户拥有全部的功能权限
			SysFunctionsExample exmaple = new SysFunctionsExample();
			exmaple.createCriteria();
			userFunctionList = sysFunctionsMapper.selectByExample(exmaple);
		}else {
			userFunctionList = sysFunctionsMapper.getFunctionsByUserCode(userCode);
		}
		//全部功能排序
		Collections.sort(userFunctionList,new SortBySeq());
		Map<String,List<SysFunctions>> userFunctionMap = new HashMap<String,List<SysFunctions>>();
		userFunctionMap.put("allList", userFunctionList);
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
	 
	/**
	 * 获取功能树结构节点以及是否选中
	 * @param functionCode
	 * @param roleCode
	 * @return
	 */
	public List<ZTreeNode> listZTreeNode(String functionCode, String roleCode) {
		// TODO Auto-generated method stub
		//获取全部功能
		SysFunctionsExample example = new SysFunctionsExample();
		example.createCriteria().andParentCodeEqualTo(functionCode);
		example.setOrderByClause("seq asc");
		List<SysFunctions> list = sysFunctionsMapper.selectByExample(example);
		//获取已分配功能
		SysRoleFunctionExample example1 = new SysRoleFunctionExample();
		example1.createCriteria().andRoleCodeEqualTo(roleCode);
		List<SysRoleFunction> list1 = sysRoleFunctionMapper.selectByExample(example1);
		//转换Nodes
		List<ZTreeNode> nodes = new ArrayList<ZTreeNode>();
		if(null!=list) {
			for(SysFunctions function : list) {
				ZTreeNode node = new ZTreeNode();
				node.setId(function.getFunctionCode());
				node.setPid(function.getParentCode());
				node.setName(function.getFunctionName());
				node.setIcon(function.getFunctionIcon());
				if("3".equals(function.getFunctionLevel())) {
					node.setIsParent("false");
				}else {
					node.setIsParent("true");
				}
				if(null!=list1) {
					for(SysRoleFunction rf:list1) {
						if(function.getFunctionCode().equals(rf.getFunctionCode())) {
							node.setChecked("true");
							break;
						}
					}
				}
				node.setData(function);
				nodes.add(node);
			}
		}
		return nodes;
	}

}
