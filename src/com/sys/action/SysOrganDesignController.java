package com.sys.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sys.model.SysOrganDesign;
import com.sys.model.SysOrganDesignExample;
import com.sys.service.SysOrganDesignService;
import com.sys.utils.JsonUtils;
import com.sys.utils.Page;
import com.sys.utils.PageListData;
import com.sys.utils.ResultData;
import com.sys.utils.SysConstant;
import com.sys.utils.SysUtils;

@Controller
public class SysOrganDesignController {

	private static final Logger logger = Logger.getLogger(SysOrganDesignController.class);
	
	@Autowired
	private SysOrganDesignService sysorgandesignService;
	
	/**
	 * 跳转设置组织结构的规则管理界面
	 * @return
	 */
	@RequestMapping(value="/sys/sysorgandesign/initManageSysOrganDesign",method=RequestMethod.GET)
	public String initManageSysOrganDesign(){
		return "jsp/sys/organdesign/managesysorgandesign";
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/sysorgandesign/listSysOrganDesignByPage",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody PageListData listSysOrganDesignByPage(HttpServletRequest request, HttpServletResponse response) {
		String start = request.getParameter("offset");//当前第几页
		String rows = request.getParameter("limit");//每页显示条数
		String filter = request.getParameter("filter");
		Map<String,Object> filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= new Page();
		SysOrganDesignExample pote= new SysOrganDesignExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		
		if(!SysUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}
		p.setBegin(Integer.parseInt(start));
		p.setEnd(Integer.parseInt(start)+Integer.parseInt(rows));
		pote.setPage(p);
		PageListData pageData = sysorgandesignService.listSysOrganDesignByPage(pote);
		return pageData;
	}
	
	/**
	 * 跳转设置组织结构的规则编辑界面
	 * @return
	 */
	@RequestMapping(value="/sys/sysorgandesign/toEditSysOrganDesign",method=RequestMethod.GET)
	public ModelAndView toEditSysOrganDesign(HttpServletRequest request, HttpServletResponse response){
		String handle = request.getParameter("handle");
		ModelAndView mv = new ModelAndView();
		mv.addObject("handle",handle);//操作类型
		if(SysConstant.SYS_HANDLE_ADD.equals(handle)){//新增
		}else if(SysConstant.SYS_HANDLE_EDIT.equals(handle)){//修改
			String recid = request.getParameter("recid");
			SysOrganDesign sysorgandesign = sysorgandesignService.getSysOrganDesignById(recid);
			mv.addObject("sysorgandesign",sysorgandesign);
		}
		mv.setViewName("jsp/sys/organdesign//editsysorgandesign");
		return mv;
	}
	
	/**
	 * 保存设置组织结构的规则编辑内容
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/sysorgandesign/editSysOrganDesign",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData editSysOrganDesign(SysOrganDesign edit,HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			if(null==edit) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			if(SysUtils.isNull(edit.getRecid())) {//新增
				edit.setRecid(SysUtils.getUUID());
				sysorgandesignService.addSysOrganDesign(edit);
			}else {//修改
				sysorgandesignService.updateSysOrganDesign(edit);
			}
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(edit);
			return rd;
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(SysConstant.SYS_ERROR_DESCRIPTION);
			return rd;
		}
	}
	
	/**
	 * 删除设置组织结构的规则
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/sysorgandesign/deleteSysOrganDesign",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData deleteSysOrganDesign(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String recids = request.getParameter("recids");
			if(SysUtils.isNull(recids)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			sysorgandesignService.deleteSysOrganDesign(recids);
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(SysConstant.SYS_SUCCESS_DESCRIPTION);
			return rd;
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(SysConstant.SYS_ERROR_DESCRIPTION);
			return rd;
		}
	}

}
