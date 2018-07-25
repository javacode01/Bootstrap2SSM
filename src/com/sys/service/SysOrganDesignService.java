package com.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.client.SysOrganDesignMapper;
import com.sys.model.SysOrganDesign;
import com.sys.model.SysOrganDesignExample;
import com.sys.utils.PageListData;
import com.sys.utils.SysConstant;

@Service
public class SysOrganDesignService {
	
	@Autowired
	private SysOrganDesignMapper sysorgandesignMapper;
	
	/**
	 * 分页查询
	 * @param pote
	 * @return
	 */
	public PageListData listSysOrganDesignByPage(SysOrganDesignExample example) {
		int totalCount = sysorgandesignMapper.countByExample(example);
		List<SysOrganDesign> list = sysorgandesignMapper.selectByExample(example);			
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}
	
	/**
	 * 根据主键获取对象
	 * @param recid
	 * @return
	 */
	public SysOrganDesign getSysOrganDesignById(String id) {
		return sysorgandesignMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 新增对象
	 * @param edit
	 */
	public void addSysOrganDesign(SysOrganDesign add) {
		int count = sysorgandesignMapper.insertSelective(add);
		if(1!=count) {
			throw new RuntimeException(SysConstant.SYS_ERROR_ADD);
		}
	}
	
	/**
	 * 更新对象信息
	 * @param edit
	 */
	@Transactional
	public void updateSysOrganDesign(SysOrganDesign edit) {
		int count = sysorgandesignMapper.updateByPrimaryKey(edit);
		if(1!=count) {
			throw new RuntimeException(SysConstant.SYS_ERROR_UPDATE);
		}
	}
	
	/**
	 * 批量删除对象
	 * @param orderids
	 */
	@Transactional
	public void deleteSysOrganDesign(String recids) {
		String[] array = recids.split(",");
		for(String recid:array) {
			sysorgandesignMapper.deleteByPrimaryKey(recid);
		}
		
	}
	
	/**
	 * 获取组织结构设计列表
	 * @param example
	 * @return
	 */
	public List<SysOrganDesign> listSysOrganDesign(SysOrganDesignExample example) {
		// TODO Auto-generated method stub
		List<SysOrganDesign> list = sysorgandesignMapper.selectByExample(example);
		return list;
	}

}
