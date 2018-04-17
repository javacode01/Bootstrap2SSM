package com.demo.service.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.client.order.DemoOrderMapper;
import com.demo.model.order.DemoOrder;
import com.demo.model.order.DemoOrderExample;
import com.sys.model.SysRoleFunctionExample;
import com.sys.model.SysRolesExample;
import com.sys.model.SysUserRoleExample;
import com.sys.utils.PageListData;
import com.sys.utils.SysConstant;
import com.sys.utils.SysUtils;

@Service
public class DemoOrderService {
	
	@Autowired
	private DemoOrderMapper demoOrderMapper;
	
	/**
	 * 分页查询
	 * @param pote
	 * @return
	 */
	public PageListData listDemoOrderByPage(DemoOrderExample example) {
		int totalCount = demoOrderMapper.countByExample(example);
		List<DemoOrder> list = demoOrderMapper.selectByExample(example);			
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}
	
	/**
	 * 根据主键获取订单
	 * @param recid
	 * @return
	 */
	public DemoOrder getDemoOrderById(String id) {
		return demoOrderMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 新增订单
	 * @param edit
	 */
	public void addDemoOrder(DemoOrder add) {
		int count = demoOrderMapper.insertSelective(add);
		if(1!=count) {
			throw new RuntimeException(SysConstant.SYS_ERROR_ADD);
		}
	}
	
	/**
	 * 更新订单信息
	 * @param edit
	 */
	@Transactional
	public void updateDemoOrder(DemoOrder edit) {
		int count = demoOrderMapper.updateByPrimaryKeySelective(edit);
		if(1!=count) {
			throw new RuntimeException(SysConstant.SYS_ERROR_UPDATE);
		}
	}
	
	/**
	 * 批量删除订单
	 * @param orderids
	 */
	@Transactional
	public void deleteDemoOrder(String recids) {
		String[] array = recids.split(",");
		for(String recid:array) {
			demoOrderMapper.deleteByPrimaryKey(recid);
		}
		
	}

}
