package com.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.client.SysAttachmentMapper;
import com.sys.model.SysAttachment;
import com.sys.model.SysAttachmentExample;

@Service
public class SysAttachmentService {
	
	@Autowired
	private SysAttachmentMapper sysAttachmentMapper;
	
	/**
	 * 批量保存附件
	 * @param list
	 */
	@Transactional
	public void saveListSysAttachment(List<SysAttachment> list) {
		// TODO Auto-generated method stub
		for(SysAttachment sysAttachment:list) {
			sysAttachmentMapper.insertSelective(sysAttachment);
		}
	}
	
	/**
	 * 列表查询
	 * @param example
	 * @return
	 */
	public List<SysAttachment> listSysAttachmentByExample(SysAttachmentExample example) {
		// TODO Auto-generated method stub
		return sysAttachmentMapper.selectByExample(example);
	}
	
	/**
	 * 根据主键获取附件信息
	 * @param recid
	 * @return
	 */
	public SysAttachment getSysAttachmentById(String recid) {
		// TODO Auto-generated method stub
		return sysAttachmentMapper.selectByPrimaryKey(recid);
	}
}
