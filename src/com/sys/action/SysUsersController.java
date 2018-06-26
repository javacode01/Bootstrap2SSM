package com.sys.action;

import java.util.Date;
import java.util.List;
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

import com.sys.model.SysUserRole;
import com.sys.model.SysUsers;
import com.sys.model.SysUsersExample;
import com.sys.service.SysUsersService;
import com.sys.utils.BspUtils;
import com.sys.utils.JsonUtils;
import com.sys.utils.Page;
import com.sys.utils.PageListData;
import com.sys.utils.ResultData;
import com.sys.utils.SysConstant;
import com.sys.utils.SysUtils;

@Controller
public class SysUsersController {
	
	private static final Logger logger = Logger.getLogger(SysUsersController.class);
	@Autowired
	private SysUsersService sysUsersService;
	
	/**
	 * 跳转用户管理界面
	 * @return
	 */
	@RequestMapping(value="/sys/users/initmanagerusers",method=RequestMethod.GET)
	public String initmanagerusers(){
		return "jsp/sys/users/managerusers";
	}
	
	/**
	 * 跳转用户编辑界面
	 * @return
	 */
	@RequestMapping(value="/sys/users/toEditUsers",method=RequestMethod.GET)
	public ModelAndView toEditUsers(HttpServletRequest request, HttpServletResponse response){
		String handle = request.getParameter("handle");
		ModelAndView mv = new ModelAndView();
		mv.addObject("handle",handle);//操作类型
		if(SysConstant.SYS_HANDLE_ADD.equals(handle)){//新增
			String organCode = request.getParameter("organCode");
			mv.addObject("organCode",organCode);
		}else if(SysConstant.SYS_HANDLE_EDIT.equals(handle)){//修改
			String userId = request.getParameter("userId");
			SysUsers user = sysUsersService.getUsersById(userId);
			mv.addObject("user",user);
		}
		mv.setViewName("jsp/sys/users/editusers");
		return mv;
	}
	
	/**
	 * 字典项分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/users/listUsersByPage",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody PageListData listUsersByPage(HttpServletRequest request, HttpServletResponse response) {
		String start = request.getParameter("offset");//当前第几页
		String rows = request.getParameter("limit");//每页显示条数
		String filter = request.getParameter("filter");
		Map<String,Object> filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= new Page();
		SysUsersExample pote= new SysUsersExample();
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
		PageListData pageData = sysUsersService.listUsersByPage(pote);
		return pageData;
	}
	
	/**
	 * 保存用户编辑内容
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/users/editUsers",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData editUsers(SysUsers edit,HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			if(null==edit) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			SysUsersExample example = new SysUsersExample();
			example.createCriteria().andUserCodeEqualTo(edit.getUserCode());
			List<SysUsers> list = sysUsersService.listUsersByExample(example);
			SysUsers user = sysUsersService.getSysUserByUserName(edit.getUserName());
			if(SysUtils.isNull(edit.getUserId())) {//新增
				if((null!=list&&list.size()>0)||null!=user) {
					rd.setCode(SysConstant.SYS_ERROR_EXISTS);
					rd.setData(SysConstant.SYS_ERROR_EXISTS_DESCRIPTION);
					return rd;
				}
				edit.setUserId(SysUtils.getUUID());
				edit.setPassword(SysUtils.MD5LOWER("123456"));
				edit.setStatus(SysConstant.SYS_USERS_STATUS_1);
				edit.setSystem(SysConstant.SYS_USERS_SYSTEM_0);
				edit.setCreateTime(new Date());
				edit.setCreater(BspUtils.getLoginUser().getUserId());
				edit.setUpdateTime(new Date());
				edit.setUpdater(BspUtils.getLoginUser().getUserId());
				sysUsersService.addSysUsers(edit);
			}else {//修改
				if(!edit.getUserId().equals(list.get(0).getUserId())||!edit.getUserId().equals(user.getUserId())) {
					rd.setCode(SysConstant.SYS_ERROR_EXISTS);
					rd.setData(SysConstant.SYS_ERROR_EXISTS_DESCRIPTION);
					return rd;
				}
				edit.setUpdateTime(new Date());
				edit.setUpdater(BspUtils.getLoginUser().getUserId());
				sysUsersService.updateSysUsers(edit);
			}
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
	
	/**
	 * 删除用户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/users/deleteUsers",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData deleteUsers(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String userCodes = request.getParameter("userCodes");
			if(!SysUtils.isNull(userCodes)) {
				sysUsersService.deleteSysUsers(userCodes);
			}
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
	
	/**
	 * 跳转分配角色界面
	 * @return
	 */
	@RequestMapping(value="/sys/users/toUserRole",method=RequestMethod.GET)
	public ModelAndView toUserRole(HttpServletRequest request, HttpServletResponse response){
		String userCode = request.getParameter("userCode");
		ModelAndView mv = new ModelAndView();
		mv.addObject("userCode",userCode);
		mv.setViewName("jsp/sys/users/userrole");
		return mv;
	}
	
	/**
	 * 获取用户已分配的角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/users/listUserRole",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData listUserRole(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String userCode = request.getParameter("userCode");
			if(SysUtils.isNull(userCode)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			List<SysUserRole> list = sysUsersService.listUserRole(userCode);
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(list);
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
	 * 保存用户分配的角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/users/saveUserRole",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData saveUserRole(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String userCode = request.getParameter("userCode");
			String roleCodes = request.getParameter("roleCodes");
			if(SysUtils.isNull(userCode)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			sysUsersService.saveUserRole(userCode,roleCodes);
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
	
	/**
	 * 跳转密码修改界面
	 * @return
	 */
	@RequestMapping(value="/sys/users/toChangePassword",method=RequestMethod.GET)
	public String toChangePassword(HttpServletRequest request, HttpServletResponse response){
		return "jsp/sys/main/changepassword";
	}
	
	/**
	 * 跳转用户信息修改界面
	 * @return
	 */
	@RequestMapping(value="/sys/users/toEditUserInfo",method=RequestMethod.GET)
	public ModelAndView toEditUserInfo(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		SysUsers user = sysUsersService.getUsersById(BspUtils.getLoginUser().getUserId());
		mv.addObject("user", user);
		mv.setViewName("jsp/sys/main/edituserinfo");
		return mv;
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/users/changePassword",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData changePassword(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String password = request.getParameter("password");
			String newPassword = request.getParameter("newPassword");
			if(SysUtils.isNull(password)||SysUtils.isNull(newPassword)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			SysUsers user = sysUsersService.getUsersById(BspUtils.getLoginUser().getUserId());
			if(!user.getPassword().equals(password)) {
				rd.setCode(SysConstant.SYS_USER_ERROR_PASSWORD);
				rd.setData(SysConstant.SYS_USER_ERROR_PASSWORD_DESCRIPTION);
				return rd;
			}
			user.setPassword(newPassword);
			user.setUpdateTime(new Date());
			user.setUpdater(BspUtils.getLoginUser().getUserId());
			sysUsersService.updateSysUsers(user);
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
