package com.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.client.SysOrganMapper;
import com.sys.model.SysOrgan;
import com.sys.model.SysOrganExample;
import com.sys.utils.SysUtils;

@Service
public class SysOrganService {
	
	@Autowired
	private SysOrganMapper sysOrganMapper;
	
	/**
	 * 获取组织结构列表
	 * @param example
	 * @return
	 */
	public List<SysOrgan> listOrganByExample(SysOrganExample example) {
		// TODO Auto-generated method stub
		return sysOrganMapper.selectByExample(example);
	}
	
	/**
	 * 根据ID删除机构
	 * @param organId
	 */
	@Transactional
	public void deleteSysOrgan(String organId) {
		// TODO Auto-generated method stub
		SysOrgan organ = sysOrganMapper.selectByPrimaryKey(organId);
		if(null!=organ) {
			recursionDeleteSysOrgan(organ);
		}
	}
	
	/**
	 * 递归删除机构及其下级机构
	 * @param organ
	 */
	private void recursionDeleteSysOrgan(SysOrgan organ) {
		// TODO Auto-generated method stub
		SysOrganExample example = new SysOrganExample();
		example.createCriteria().andParentCodeEqualTo(organ.getOrganCode());
		List<SysOrgan> list = sysOrganMapper.selectByExample(example);
		if(null!=list&&list.size()>0) {
			for(SysOrgan temp : list) {
				recursionDeleteSysOrgan(temp);
			}
		}
		sysOrganMapper.deleteByPrimaryKey(organ.getOrganId());
	}

	/**
	 * 新增机构
	 * @param edit
	 */
	public void addSysOrgan(SysOrgan add) {
		// TODO Auto-generated method stub
		sysOrganMapper.insertSelective(add);
	}
	
	/**
	 * 编辑机构
	 * @param edit
	 */
	public void updateSysOrgan(SysOrgan edit) {
		// TODO Auto-generated method stub
		sysOrganMapper.updateByPrimaryKeySelective(edit);
	}
	
	/**
	 * 根据机构编号获取机构信息
	 * @param organCode
	 */
	public SysOrgan getOrganByOrganCode(String organCode) {
		// TODO Auto-generated method stub
		if(SysUtils.isNull(organCode)) {
			return null;
		}
		SysOrganExample example = new SysOrganExample();
		example.createCriteria().andOrganCodeEqualTo(organCode);
		example.setOrderByClause("update_time desc");
		List<SysOrgan> list = sysOrganMapper.selectByExample(example);
		if(null==list||list.size()<1) {
			return null;
		}else {
			return list.get(0);
		}
	}

}
