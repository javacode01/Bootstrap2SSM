package com.sys.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sys.service.SysQuartzJobService;
/**
 * 定时任务管理
 * @author Administrator
 *
 */
@Controller
public class SysQuartzJobController {
	
	@Autowired
	private SysQuartzJobService sysQuartzJobService;
	
	/**
	 * 跳转数据字典管理界面
	 * @return
	 */
	@RequestMapping(value="/sys/quartzjob/initmanagerquartzjob",method=RequestMethod.GET)
	public String initmanagerquartzjob(){
		return "jsp/sys/quartzjob/managerquartzjob";
	}

}
