package com.demo.service.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.client.module.DemoOrderMapper;
import com.demo.model.module.DemoOrder;
import com.demo.model.module.DemoOrderExample;
import com.sys.utils.PageListData;
import com.sys.utils.SysConstant;

@Service
public class DemoOrderService {
	
	@Autowired
	private DemoOrderMapper demoorderMapper;
	
	/**
	 * 分页查询
	 * @param pote
	 * @return
	 */
	public PageListData listDemoOrderByPage(DemoOrderExample example) {
		int totalCount = demoorderMapper.countByExample(example);
		List<DemoOrder> list = demoorderMapper.selectByExample(example);			
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
	public DemoOrder getDemoOrderById(String id) {
		return demoorderMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 新增对象
	 * @param edit
	 */
	public void addDemoOrder(DemoOrder add) {
		int count = demoorderMapper.insertSelective(add);
		if(1!=count) {
			throw new RuntimeException(SysConstant.SYS_ERROR_ADD);
		}
	}
	
	/**
	 * 更新对象信息
	 * @param edit
	 */
	@Transactional
	public void updateDemoOrder(DemoOrder edit) {
		int count = demoorderMapper.updateByPrimaryKeySelective(edit);
		if(1!=count) {
			throw new RuntimeException(SysConstant.SYS_ERROR_UPDATE);
		}
	}
	
	/**
	 * 批量删除对象
	 * @param orderids
	 */
	@Transactional
	public void deleteDemoOrder(String recids) {
		String[] array = recids.split(",");
		for(String recid:array) {
			demoorderMapper.deleteByPrimaryKey(recid);
		}
		
	}

}
