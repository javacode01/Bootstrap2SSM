package com.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.client.SysQuartzJobMapper;
import com.sys.model.SysQuartzJob;
import com.sys.model.SysQuartzJobExample;
import com.sys.utils.PageListData;
import com.sys.utils.schedule.QuartzManager;

@Service
public class SysQuartzJobService {
	
	@Autowired
	private SysQuartzJobMapper sysQuartzJobMapper;
	
	/**
	 * 查询定时任务列表
	 * @param example
	 * @return
	 */
	public List<SysQuartzJob> listQuartzJobByExample(SysQuartzJobExample example) {
		// TODO Auto-generated method stub
		return sysQuartzJobMapper.selectByExample(example);
	}
	
	/**
	 * 根据主键获取定时任务
	 * @param recid
	 * @return
	 */
	public SysQuartzJob getQuartzJobById(String recid) {
		// TODO Auto-generated method stub
		return sysQuartzJobMapper.selectByPrimaryKey(recid);
	}
	
	/**
	 * 新增定时任务
	 * @param edit
	 */
	public void addSysQuartzJob(SysQuartzJob add) {
		// TODO Auto-generated method stub
		sysQuartzJobMapper.insertSelective(add);
	}
	
	/**
	 * 编辑定时任务
	 * @param edit
	 */
	public void updateSysQuartzJob(SysQuartzJob edit) {
		// TODO Auto-generated method stub
		sysQuartzJobMapper.updateByPrimaryKeySelective(edit);
	}
	
	/**
	 * 删除定时任务
	 * @param recid
	 */
	@Transactional
	public void deleteSysQuartzJob(String recids) {
		// TODO Auto-generated method stub
		String[] array = recids.split(",");
		for(String recid:array) {
			SysQuartzJob job = sysQuartzJobMapper.selectByPrimaryKey(recid);
			if(null==job) {
				continue;
			}
			String status = QuartzManager.getTriggerState(job);
			if(!"NONE".equals(status)){
				throw new RuntimeException("请先将任务停止后在进行删除");
			}
			sysQuartzJobMapper.deleteByPrimaryKey(recid);
		}
	}
	
	/**
	 * 定时任务分页查询
	 * @param pote
	 * @return
	 */
	public PageListData listQuartzJobByPage(SysQuartzJobExample example) {
		// TODO Auto-generated method stub
		int totalCount = sysQuartzJobMapper.countByExample(example);
		List<SysQuartzJob> list = sysQuartzJobMapper.selectByExample(example);	
		for(SysQuartzJob job:list){
			job.setStatus(QuartzManager.getTriggerState(job));
		}
		PageListData pld = new PageListData();
		pld.setTotal(totalCount);
		pld.setRows(list);
		return pld;
	}
	
}
