package com.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.client.SysDictionariesMapper;
import com.sys.model.SysDictionaries;
import com.sys.model.SysDictionariesExample;
import com.sys.utils.PageListData;

@Service
public class SysDictionariesService {
	
	@Autowired
	private SysDictionariesMapper sysDictionariesMapper;
	
	/**
	 * 根据查询条件查询字典
	 * @param example
	 * @return
	 */
	public List<SysDictionaries> listDictionariesByExample(SysDictionariesExample example) {
		// TODO Auto-generated method stub
		return sysDictionariesMapper.selectByExample(example);
	}
	
	/**
	 * 根据主键获取数据字典
	 * @param recid
	 * @return
	 */
	public SysDictionaries getDictionariesById(String recid) {
		// TODO Auto-generated method stub
		return sysDictionariesMapper.selectByPrimaryKey(recid);
	}
	
	/**
	 * 新增数据字典
	 * @param edit
	 */
	public void addSysDictionaries(SysDictionaries add) {
		// TODO Auto-generated method stub
		sysDictionariesMapper.insertSelective(add);
	}
	
	/**
	 * 编辑数据字典
	 * @param edit
	 */
	public void updateSysDictionaries(SysDictionaries edit) {
		// TODO Auto-generated method stub
		sysDictionariesMapper.updateByPrimaryKeySelective(edit);
	}
	
	/**
	 * 删除数据字典
	 * @param recid
	 */
	public void deleteSysDictionaries(String recid) {
		// TODO Auto-generated method stub
		sysDictionariesMapper.deleteByPrimaryKey(recid);
	}
	
	/**
	 * 数据字典分页查询
	 * @param example
	 * @return
	 */
	public PageListData listDictionariesByPage(SysDictionariesExample example) {
		// TODO Auto-generated method stub
		int totalCount = sysDictionariesMapper.countByExample(example);
		List<SysDictionaries> list = sysDictionariesMapper.selectByExample(example);			
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}
	
}
