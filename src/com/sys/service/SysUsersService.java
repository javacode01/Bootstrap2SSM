package com.sys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.client.SysUserRoleMapper;
import com.sys.client.SysUsersMapper;
import com.sys.model.SysUserRole;
import com.sys.model.SysUserRoleExample;
import com.sys.model.SysUsers;
import com.sys.model.SysUsersExample;
import com.sys.utils.BspUtils;
import com.sys.utils.PageListData;
import com.sys.utils.SysUtils;

@Service
public class SysUsersService {
	
	@Autowired
	private SysUsersMapper sysUsersMapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	/**
	 * 根据用户名获取用户
	 * @param username
	 * @return
	 */
	public SysUsers getSysUserByUserName(String username) {
		// TODO Auto-generated method stub
		return sysUsersMapper.getUserByName(username);
	}
	
	/**
	 * 获取用户角色关系
	 * @param roleExample
	 * @return
	 */
	public List<SysUserRole> getSysUserRole(SysUserRoleExample roleExample) {
		// TODO Auto-generated method stub
		List<SysUserRole> list = sysUserRoleMapper.selectByExample(roleExample);
		return list;
	}
	
	/**
	 * 根据ID获取用户
	 * @param userId
	 * @return
	 */
	public SysUsers getUsersById(String userId) {
		// TODO Auto-generated method stub
		return sysUsersMapper.selectByPrimaryKey(userId);
	}
	
	/**
	 * 分页查询
	 * @param example
	 * @return
	 */
	public PageListData listUsersByPage(SysUsersExample example) {
		// TODO Auto-generated method stub
		int totalCount = sysUsersMapper.countByExample(example);
		List<SysUsers> list = sysUsersMapper.selectByExample(example);
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}
	
	/**
	 * 列表查询
	 * @param example
	 * @return
	 */
	public List<SysUsers> listUsersByExample(SysUsersExample example) {
		// TODO Auto-generated method stub
		return sysUsersMapper.selectByExample(example);
	}
	
	/**
	 * 新增用户
	 * @param add
	 */
	public void addSysUsers(SysUsers add) {
		// TODO Auto-generated method stub
		sysUsersMapper.insertSelective(add);
	}
	
	/**
	 * 编辑用户
	 * @param edit
	 */
	public void updateSysUsers(SysUsers edit) {
		// TODO Auto-generated method stub
		sysUsersMapper.updateByPrimaryKeySelective(edit);
	}
	
	/**
	 * 批量删除用户
	 * @param userCodes
	 */
	@Transactional
	public void deleteSysUsers(String userCodes) {
		// TODO Auto-generated method stub
		String[] array = userCodes.split(",");
		for(String userCode:array) {
			SysUsersExample example = new SysUsersExample();
			example.createCriteria().andUserCodeEqualTo(userCode);
			sysUsersMapper.deleteByExample(example);
			SysUserRoleExample example1 = new SysUserRoleExample();
			example1.createCriteria().andUserCodeEqualTo(userCode);
			sysUserRoleMapper.deleteByExample(example1);
		}
	}
	
	/**
	 * 获取用户已分配角色
	 * @param userCode
	 * @return
	 */
	public List<SysUserRole> listUserRole(String userCode) {
		// TODO Auto-generated method stub
		SysUserRoleExample example = new SysUserRoleExample();
		example.createCriteria().andUserCodeEqualTo(userCode);
		return sysUserRoleMapper.selectByExample(example);
	}
	
	/**
	 * 保存分配给用户的角色
	 * @param userCode
	 * @param roleCodes
	 */
	@Transactional
	public void saveUserRole(String userCode, String roleCodes) {
		// TODO Auto-generated method stub
		SysUserRoleExample example = new SysUserRoleExample();
		example.createCriteria().andUserCodeEqualTo(userCode);
		sysUserRoleMapper.deleteByExample(example);
		String[] array = roleCodes.split(",");
		for(String roleCode:array) {
			if(!SysUtils.isNull(roleCode)) {
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setId(SysUtils.getUUID());
				sysUserRole.setUserCode(userCode);
				sysUserRole.setRoleCode(roleCode);
				sysUserRole.setCreateTime(new Date());
				sysUserRole.setCreater(BspUtils.getLoginUser().getUserId());
				sysUserRoleMapper.insertSelective(sysUserRole);
			}
		}
	}

}
