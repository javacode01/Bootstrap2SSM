package com.sys.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sys.model.SysQuartzJob;
import com.sys.model.SysQuartzJobExample;
import com.sys.service.SysQuartzJobService;
import com.sys.utils.BspUtils;
import com.sys.utils.JsonUtils;
import com.sys.utils.Page;
import com.sys.utils.PageListData;
import com.sys.utils.ResultData;
import com.sys.utils.SysConstant;
import com.sys.utils.SysUtils;
import com.sys.utils.schedule.QuartzManager;
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
	 * 跳转定时任务管理界面
	 * @return
	 */
	@RequestMapping(value="/sys/quartzjob/initmanagerquartzjob",method=RequestMethod.GET)
	public String initmanagerquartzjob(){
		return "jsp/sys/quartzjob/managerquartzjob";
	}
	
	/**
	 * 定时任务分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/quartzjob/listQuartzJobByPage",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody PageListData listQuartzJobByPage(HttpServletRequest request, HttpServletResponse response) {
		String start = request.getParameter("offset");//当前第几页
		String rows = request.getParameter("limit");//每页显示条数
		String filter = request.getParameter("filter");
		Map<String,Object> filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= new Page();
		SysQuartzJobExample pote= new SysQuartzJobExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		
		if(!SysUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}else {
			pote.setOrderByClause("createtime desc");
		}
		p.setBegin(Integer.parseInt(start));
		p.setEnd(Integer.parseInt(start)+Integer.parseInt(rows));
		pote.setPage(p);
		PageListData pageData = sysQuartzJobService.listQuartzJobByPage(pote);
		return pageData;
	}
	
	/**
	 * 跳转定时任务编辑界面
	 * @return
	 */
	@RequestMapping(value="/sys/quartzjob/toEditQuartzJob",method=RequestMethod.GET)
	public ModelAndView toEditQuartzJob(HttpServletRequest request, HttpServletResponse response){
		String handle = request.getParameter("handle");
		ModelAndView mv = new ModelAndView();
		mv.addObject("handle",handle);//操作类型
		if(SysConstant.SYS_HANDLE_ADD.equals(handle)){//新增
		}else if(SysConstant.SYS_HANDLE_EDIT.equals(handle)){//修改
			String recid = request.getParameter("recid");
			SysQuartzJob quartzJob = sysQuartzJobService.getQuartzJobById(recid);
			mv.addObject("quartzJob",quartzJob);
		}
		mv.setViewName("jsp/sys/quartzjob/editquartzjob");
		return mv;
	}
	
	/**
	 * 保存定时任务编辑内容
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/quartzjob/editQuartzJob",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData editQuartzJob(SysQuartzJob edit,HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			if(null==edit) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			//校验定时任务时间格式是否正确
			boolean isvaild = CronExpression.isValidExpression(edit.getCronExpression());
			if(!isvaild){
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			//校验定时任务类是否存在
			try {
				Class.forName(edit.getJobClass());
			} catch(Exception e) {
				e.printStackTrace();
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			if(SysUtils.isNull(edit.getRecid())) {//新增
				edit.setRecid(SysUtils.getUUID());
				edit.setCreater(BspUtils.getLoginUser().getUserId());
				edit.setCreatetime(new Date());
				edit.setUpdater(BspUtils.getLoginUser().getUserId());
				edit.setUpdatetime(new Date());
				sysQuartzJobService.addSysQuartzJob(edit);
			}else {//修改
				String status = QuartzManager.getTriggerState(edit);
				if(!"NONE".equals(status)){
					rd.setCode(SysConstant.SYS_ERROR_STATUS);
					rd.setData(SysConstant.SYS_ERROR_STATUS_DESCRIPTION);
					return rd;
				}
				edit.setUpdater(BspUtils.getLoginUser().getUserId());
				edit.setUpdatetime(new Date());
				sysQuartzJobService.updateSysQuartzJob(edit);
			}
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(edit);
			return rd;
		}catch(Exception e) {
			e.printStackTrace();
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(SysConstant.SYS_ERROR_DESCRIPTION);
			return rd;
		}
	}
	
	/**
	 * 删除定时任务
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/quartzjob/deleteQuartzJob",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData deleteQuartzJob(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String recids = request.getParameter("recids");
			if(SysUtils.isNull(recids)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			sysQuartzJobService.deleteSysQuartzJob(recids);
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(SysConstant.SYS_SUCCESS_DESCRIPTION);
			return rd;
		}catch(Exception e) {
			e.printStackTrace();
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(e.getMessage());
			return rd;
		}
	}
	
	/**
	 * 启动定时任务
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/quartzjob/startQuartzJob",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData startQuartzJob(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String recids = request.getParameter("recids");
			if(SysUtils.isNull(recids)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			String[] array = recids.split(",");
			for(String recid:array) {
				SysQuartzJob job = sysQuartzJobService.getQuartzJobById(recid);
				if(null==job) {
					continue;
				}
				QuartzManager.runJob(job);
			}
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(SysConstant.SYS_SUCCESS_DESCRIPTION);
			return rd;
		}catch(Exception e) {
			e.printStackTrace();
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(e.getMessage());
			return rd;
		}
	}
	
	/**
	 * 停止定时任务
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/quartzjob/stopQuartzJob",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData stopQuartzJob(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String recids = request.getParameter("recids");
			if(SysUtils.isNull(recids)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			String[] array = recids.split(",");
			for(String recid:array) {
				SysQuartzJob job = sysQuartzJobService.getQuartzJobById(recid);
				if(null==job) {
					continue;
				}
				QuartzManager.delJob(job);
			}
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(SysConstant.SYS_SUCCESS_DESCRIPTION);
			return rd;
		}catch(Exception e) {
			e.printStackTrace();
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(e.getMessage());
			return rd;
		}
	}
	
	/**
	 * 暂停定时任务
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/quartzjob/pauseQuartzJob",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData pauseQuartzJob(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String recids = request.getParameter("recids");
			if(SysUtils.isNull(recids)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			String[] array = recids.split(",");
			for(String recid:array) {
				SysQuartzJob job = sysQuartzJobService.getQuartzJobById(recid);
				if(null==job) {
					continue;
				}
				QuartzManager.pauseJob(job);
			}
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(SysConstant.SYS_SUCCESS_DESCRIPTION);
			return rd;
		}catch(Exception e) {
			e.printStackTrace();
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(e.getMessage());
			return rd;
		}
	}
	
	/**
	 * 恢复定时任务
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/quartzjob/resumeQuartzJob",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData resumeQuartzJob(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String recids = request.getParameter("recids");
			if(SysUtils.isNull(recids)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			String[] array = recids.split(",");
			for(String recid:array) {
				SysQuartzJob job = sysQuartzJobService.getQuartzJobById(recid);
				if(null==job) {
					continue;
				}
				QuartzManager.resumeJob(job);
			}
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(SysConstant.SYS_SUCCESS_DESCRIPTION);
			return rd;
		}catch(Exception e) {
			e.printStackTrace();
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(e.getMessage());
			return rd;
		}
	}
	
	/**
	 * 执行定时任务
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/quartzjob/runQuartzJob",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData runQuartzJob(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String recids = request.getParameter("recids");
			if(SysUtils.isNull(recids)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			String[] array = recids.split(",");
			for(String recid:array) {
				SysQuartzJob job = sysQuartzJobService.getQuartzJobById(recid);
				if(null==job) {
					continue;
				}
				QuartzManager.triggerJob(job);
			}
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(SysConstant.SYS_SUCCESS_DESCRIPTION);
			return rd;
		}catch(Exception e) {
			e.printStackTrace();
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(e.getMessage());
			return rd;
		}
	}
	
}
