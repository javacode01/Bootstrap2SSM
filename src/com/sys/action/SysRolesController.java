package com.sys.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sys.model.SysRoleFunction;
import com.sys.model.SysRoles;
import com.sys.model.SysRolesExample;
import com.sys.service.SysRolesService;
import com.sys.utils.BspUtils;
import com.sys.utils.JsonUtils;
import com.sys.utils.Page;
import com.sys.utils.PageListData;
import com.sys.utils.ResultData;
import com.sys.utils.SysConstant;
import com.sys.utils.SysUtils;

/**
 * 角色管理
 * @author Administrator
 *
 */
@Controller
public class SysRolesController {
	
	@Autowired
	private SysRolesService sysRolesService;
	
	/**
	 * 跳转角色管理界面
	 * @return
	 */
	@RequestMapping(value="/sys/roles/initmanagerroles",method=RequestMethod.GET)
	public String initmanagerfunctions(){
		return "jsp/sys/roles/managerroles";
	}
	
	/**
	 * 角色分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/roles/listRolesByPage",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody PageListData listRolesByPage(HttpServletRequest request, HttpServletResponse response) {
		String start = request.getParameter("offset");//当前第几页
		String rows = request.getParameter("limit");//每页显示条数
		String filter = request.getParameter("filter");
		Map<String,Object> filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= new Page();
		SysRolesExample pote= new SysRolesExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		
		if(!SysUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}else {
			pote.setOrderByClause("update_time desc");
		}
		p.setBegin(Integer.parseInt(start));
		p.setEnd(Integer.parseInt(start)+Integer.parseInt(rows));
		pote.setPage(p);
		PageListData pageData = sysRolesService.listRolesByPage(pote);
		return pageData;
	}
	
	/**
	 * 角色列表查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/roles/listRoles",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody List<SysRoles> listRoles(HttpServletRequest request, HttpServletResponse response) {
		String filter = request.getParameter("filter");
		Map<String,Object> filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		SysRolesExample pote= new SysRolesExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		
		if(!SysUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}
		List<SysRoles> list = sysRolesService.listRolesByExample(pote);
		return list;
	}
	
	/**
	 * 跳转角色编辑界面
	 * @return
	 */
	@RequestMapping(value="/sys/roles/toEditRole",method=RequestMethod.GET)
	public ModelAndView toEditRole(HttpServletRequest request, HttpServletResponse response){
		String handle = request.getParameter("handle");
		ModelAndView mv = new ModelAndView();
		mv.addObject("handle",handle);//操作类型
		if(SysConstant.SYS_HANDLE_ADD.equals(handle)){//新增
		}else if(SysConstant.SYS_HANDLE_EDIT.equals(handle)){//修改
			String roleId = request.getParameter("roleId");
			SysRoles role = sysRolesService.getRoleById(roleId);
			mv.addObject("role",role);
		}
		mv.setViewName("jsp/sys/roles/editrole");
		return mv;
	}
	
	/**
	 * 跳转权限分配界面
	 * @return
	 */
	@RequestMapping(value="/sys/roles/toRoleFunction",method=RequestMethod.GET)
	public ModelAndView toRoleFunction(HttpServletRequest request, HttpServletResponse response){
		String roleCode = request.getParameter("roleCode");
		ModelAndView mv = new ModelAndView();
		mv.addObject("roleCode",roleCode);
		mv.setViewName("jsp/sys/roles/rolefunction");
		return mv;
	}
	
	/**
	 * 保存角色编辑内容
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/roles/editRole",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData editRole(SysRoles edit,HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			if(null==edit) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			if(SysUtils.isNull(edit.getRoleId())) {//新增
				SysRolesExample example = new SysRolesExample();
				example.createCriteria().andRoleCodeEqualTo(edit.getRoleCode());
				List<SysRoles> list = sysRolesService.listRolesByExample(example);
				if(null!=list&&list.size()>0) {
					rd.setCode(SysConstant.SYS_ERROR_EXISTS);
					rd.setData(SysConstant.SYS_ERROR_EXISTS_DESCRIPTION);
					return rd;
				}
				edit.setRoleId(SysUtils.getUUID());
				edit.setCreateTime(new Date());
				edit.setCreater(BspUtils.getLoginUser().getUserId());
				edit.setUpdateTime(new Date());
				edit.setUpdater(BspUtils.getLoginUser().getUserId());
				sysRolesService.addRole(edit);
			}else {//修改
				edit.setUpdateTime(new Date());
				edit.setUpdater(BspUtils.getLoginUser().getUserId());
				sysRolesService.updateRole(edit);
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
	 * 删除角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/roles/deleteRoles",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData deleteRoles(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String roleCodes = request.getParameter("roleCodes");
			if(SysUtils.isNull(roleCodes)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			sysRolesService.deleteSysRoles(roleCodes);
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(SysConstant.SYS_SUCCESS_DESCRIPTION);
			return rd;
		}catch(Exception e) {
			e.printStackTrace();
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(SysConstant.SYS_ERROR_DESCRIPTION);
			return rd;
		}
	}
	
	/**
	 * 保存角色功能关系
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/roles/saveRoleFunction",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData saveRoleFunction(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String roleCode = request.getParameter("roleCode");
			String functionCodes = request.getParameter("functionCodes");
			if(SysUtils.isNull(roleCode)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			sysRolesService.saveRoleFunction(roleCode,functionCodes);
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(SysConstant.SYS_SUCCESS_DESCRIPTION);
			return rd;
		}catch(Exception e) {
			e.printStackTrace();
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(SysConstant.SYS_ERROR_DESCRIPTION);
			return rd;
		}
	}
	
	/**
	 * 获取角色已分配权限
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/roles/listRoleFunction",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData listRoleFunction(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String roleCode = request.getParameter("roleCode");
			if(SysUtils.isNull(roleCode)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			List<SysRoleFunction> list = sysRolesService.listRoleFunction(roleCode);
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(list);
			return rd;
		}catch(Exception e) {
			e.printStackTrace();
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(SysConstant.SYS_ERROR_DESCRIPTION);
			return rd;
		}
	}
	
}
