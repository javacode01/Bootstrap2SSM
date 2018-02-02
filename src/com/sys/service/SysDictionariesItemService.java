package com.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.client.SysDictionariesItemMapper;
import com.sys.model.SysDictionariesItem;
import com.sys.model.SysDictionariesItemExample;
import com.sys.utils.PageListData;

@Service
public class SysDictionariesItemService {
	
	@Autowired
	private SysDictionariesItemMapper sysDictionariesItemMapper;

	public PageListData listDictionariesItemByPage(SysDictionariesItemExample example) {
		// TODO Auto-generated method stub
		int totalCount = sysDictionariesItemMapper.countByExample(example);
		List<SysDictionariesItem> list = sysDictionariesItemMapper.selectByExample(example);			
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}

	public SysDictionariesItem getDictionariesItemById(String recid) {
		// TODO Auto-generated method stub
		return sysDictionariesItemMapper.selectByPrimaryKey(recid);
	}

	public List<SysDictionariesItem> listDictionariesItemByExample(SysDictionariesItemExample example) {
		// TODO Auto-generated method stub
		return sysDictionariesItemMapper.selectByExample(example);
	}

	public void addSysDictionariesItem(SysDictionariesItem add) {
		// TODO Auto-generated method stub
		sysDictionariesItemMapper.insertSelective(add);
	}

	public void updateSysDictionariesItem(SysDictionariesItem edit) {
		// TODO Auto-generated method stub
		sysDictionariesItemMapper.updateByPrimaryKeySelective(edit);
	}
	
	@Transactional
	public void deleteSysDictionariesItems(String recids) {
		// TODO Auto-generated method stub
		String[] array = recids.split(",");
		for(String recid:array) {
			sysDictionariesItemMapper.deleteByPrimaryKey(recid);
		}
	}

}
