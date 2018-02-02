package com.sys.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.client.SysRoleFunctionMapper;
import com.sys.client.SysRolesMapper;
import com.sys.client.SysUserRoleMapper;
import com.sys.model.SysRoleFunction;
import com.sys.model.SysRoleFunctionExample;
import com.sys.model.SysRoles;
import com.sys.model.SysRolesExample;
import com.sys.model.SysUserRoleExample;
import com.sys.utils.BspUtils;
import com.sys.utils.PageListData;
import com.sys.utils.SysUtils;

@Service
public class SysRolesService {
	
	@Autowired
	private SysRolesMapper sysRolesMapper;
	@Autowired
	private SysRoleFunctionMapper sysRoleFunctionMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	/**
	 * 获取所有资源权限关系
	 * @return
	 */
	public List<Map<String, String>> getRoleFunction() {
		// TODO Auto-generated method stub
		List<Map<String, String>> list = sysRoleFunctionMapper.selectAllResource();
		return list;
	}
	
	/**
	 * 角色分页查询
	 * @param example
	 * @return
	 */
	public PageListData listRolesByPage(SysRolesExample example) {
		// TODO Auto-generated method stub
		int totalCount = sysRolesMapper.countByExample(example);
		List<SysRoles> list = sysRolesMapper.selectByExample(example);			
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}
	
	/**
	 * 根据主键获取角色
	 * @param roleId
	 * @return
	 */
	public SysRoles getRoleById(String roleId) {
		// TODO Auto-generated method stub
		return sysRolesMapper.selectByPrimaryKey(roleId);
	}
	
	/**
	 * 角色列表查询
	 * @param example
	 * @return
	 */
	public List<SysRoles> listRolesByExample(SysRolesExample example) {
		// TODO Auto-generated method stub
		return sysRolesMapper.selectByExample(example);
	}
	
	/**
	 * 新增角色
	 * @param edit
	 */
	public void addRole(SysRoles add) {
		// TODO Auto-generated method stub
		sysRolesMapper.insertSelective(add);
	}
	
	/**
	 * 编辑角色
	 * @param edit
	 */
	public void updateRole(SysRoles edit) {
		// TODO Auto-generated method stub
		sysRolesMapper.updateByPrimaryKeySelective(edit);
	}
	
	/**
	 * 批量删除角色
	 * @param roleIds
	 */
	@Transactional
	public void deleteSysRoles(String roleCodes) {
		// TODO Auto-generated method stub
		String[] array = roleCodes.split(",");
		for(String roleCode:array) {
			SysRolesExample roleExample = new SysRolesExample();
			roleExample.createCriteria().andRoleCodeEqualTo(roleCode);
			sysRolesMapper.deleteByExample(roleExample);
			SysRoleFunctionExample example = new SysRoleFunctionExample();
			example.createCriteria().andRoleCodeEqualTo(roleCode);
			sysRoleFunctionMapper.deleteByExample(example);
			SysUserRoleExample example1 = new SysUserRoleExample();
			example1.createCriteria().andRoleCodeEqualTo(roleCode);
			sysUserRoleMapper.deleteByExample(example1);
		}
	}
	
	/**
	 * 保存角色功能关系
	 * @param roleId
	 * @param functionCodes
	 */
	@Transactional
	public void saveRoleFunction(String roleCode, String functionCodes) {
		// TODO Auto-generated method stub
		//删除角色功能关系
		SysRoleFunctionExample example = new SysRoleFunctionExample();
		example.createCriteria().andRoleCodeEqualTo(roleCode);
		sysRoleFunctionMapper.deleteByExample(example);
		//重新插入角色功能关系
		String[] array = functionCodes.split(",");
		//去重
		Map<String,String> functionCodeMap = new HashMap<String,String>();
		for(String functionCode:array) {
			if(!functionCodeMap.containsKey(functionCode)) {
				functionCodeMap.put(functionCode, functionCode);
			}
		}
		Iterator<String> it = functionCodeMap.keySet().iterator();
		while(it.hasNext()) {
			String functionCode = it.next();
			if(!SysUtils.isNull(functionCode)) {
				SysRoleFunction temp = new SysRoleFunction();
				temp.setId(SysUtils.getUUID());
				temp.setRoleCode(roleCode);
				temp.setFunctionCode(functionCode);
				temp.setCreateTime(new Date());
				temp.setCreater(BspUtils.getLoginUser().getUserId());
				sysRoleFunctionMapper.insertSelective(temp);
			}
		}
	}
	
	/**
	 * 获取角色已分配权限
	 * @param roleCode
	 * @return
	 */
	public List<SysRoleFunction> listRoleFunction(String roleCode) {
		// TODO Auto-generated method stub
		SysRoleFunctionExample example = new SysRoleFunctionExample();
		example.createCriteria().andRoleCodeEqualTo(roleCode);
		return sysRoleFunctionMapper.selectByExample(example);
	}

}
