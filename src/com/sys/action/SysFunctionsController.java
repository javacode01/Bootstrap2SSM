package com.sys.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.sys.model.SysFunctions;
import com.sys.model.SysFunctionsExample;
import com.sys.service.SysFunctionsService;
import com.sys.utils.BspUtils;
import com.sys.utils.JsonUtils;
import com.sys.utils.Page;
import com.sys.utils.PageListData;
import com.sys.utils.ResultData;
import com.sys.utils.SysConstant;
import com.sys.utils.SysUtils;
import com.sys.utils.TreeNode;

/**
 * @ClassName: SysFunctionsController 
 * @Description: 功能管理接口 
 * @author AK
 * @date 2017-11-22 下午4:36:45 
 *
 */
@Controller
public class SysFunctionsController {
	
	@Autowired
	private SysFunctionsService sysFunctionsService;
	
	/**
	 * 用户登录后获取功能列表
	 * @return
	 */
	@RequestMapping(value="/sys/functions/getUserMenus",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody Map<String,List<SysFunctions>> getUserMenus(){
		return sysFunctionsService.getUserMenus();
	}
	
	/**
	 * 跳转功能管理界面
	 * @return
	 */
	@RequestMapping(value="/sys/functions/initmanagerfunctions",method=RequestMethod.GET)
	public String initmanagerfunctions(){
		return "jsp/sys/functions/managerfunctions";
	}
	
	/**
	 * 获取功能树结构节点
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/functions/getFunctionNodes",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody List<TreeNode> getFunctionNodes(HttpServletRequest request, HttpServletResponse response) {
		//获取参数
		String functionCode = request.getParameter("functionCode");
		if(null==functionCode||"".equals(functionCode)) {
			return null;
		}
		SysFunctionsExample example = new SysFunctionsExample();
		example.createCriteria().andParentCodeEqualTo(functionCode);
		example.setOrderByClause("seq asc");
		List<SysFunctions> list = sysFunctionsService.listFunctionsByExample(example);
		//转换Nodes
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		if(null!=list) {
			for(SysFunctions function : list) {
				TreeNode node = new TreeNode();
				node.setId(function.getFunctionCode());
				node.setText(function.getFunctionName());
				node.setIcon(function.getFunctionIcon());
				node.setSelectable(true);
				Map<String,Object> state = new HashMap<String,Object>();
				if("3".equals(function.getFunctionLevel())) {
					state.put("expanded", true);
				}else {
					state.put("expanded", false);
					node.setNodes(new ArrayList<TreeNode>());
				}
				node.setState(state);
				node.setData(function);
				nodes.add(node);
			}
		}
		return nodes;
	}
	
	/**
	 * 功能分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/functions/listFunctionsByPage",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody PageListData listFunctionsByPage(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");//当前第几页
		String rows = request.getParameter("rows");//每页显示条数
		String filter = request.getParameter("filter");
		Map<String,Object> filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= new Page();
		SysFunctionsExample pote= new SysFunctionsExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		
		if(!SysUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}
		p.setBegin((Integer.parseInt(page)-1)*Integer.parseInt(rows));
		p.setEnd(Integer.parseInt(page)*Integer.parseInt(rows));
		pote.setPage(p);
		PageListData pageData = sysFunctionsService.listFunctionsByPage(pote);
		return pageData;
	}
	
	/**
	 * 功能查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/functions/listFunctions",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody List<SysFunctions> listFunctions(HttpServletRequest request, HttpServletResponse response) {
		String filter = request.getParameter("filter");
		Map<String,Object> filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		SysFunctionsExample pote= new SysFunctionsExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		
		if(!SysUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}
		List<SysFunctions> list = sysFunctionsService.listFunctions(pote);
		return list;
	}
	
	/**
	 * 跳转功能编辑页面
	 * @return
	 */
	@RequestMapping(value="/sys/functions/toEditFunctions",method=RequestMethod.POST)
	public ModelAndView toEditFunctions(HttpServletRequest request, HttpServletResponse response){
		String handle = request.getParameter("handle");
		String functionCode = request.getParameter("functionCode");
		SysFunctions function = null;
		if(!"root".equals(functionCode)) {
			SysFunctionsExample example= new SysFunctionsExample();
			example.createCriteria().andFunctionCodeEqualTo(functionCode);
			List<SysFunctions> list = sysFunctionsService.listFunctions(example);
			function = list.get(0);
		}else {
			function = new SysFunctions();
			function.setFunctionCode("root");
			function.setFunctionLevel(SysConstant.SYS_FUNCTION_LEVEL_MODULE);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("handle", handle);
		if(SysConstant.SYS_HANDLE_ADD.equals(handle)){
			mv.addObject("parentFunction", function);
		}else if(SysConstant.SYS_HANDLE_EDIT.equals(handle)){
			mv.addObject("function",function);
		}
		mv.setViewName("jsp/sys/functions/editfunctions");
		return mv;
	}
	
	/**
	 * 保存功能编辑内容
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/functions/editFunctions",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData editFunctions(SysFunctions edit,HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			if(null==edit) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			if(SysUtils.isNull(edit.getFunctionId())) {//新增
				SysFunctionsExample example = new SysFunctionsExample();
				example.createCriteria().andFunctionCodeEqualTo(edit.getFunctionCode());
				List<SysFunctions> list = sysFunctionsService.listFunctionsByExample(example);
				if(null!=list&&list.size()>0) {
					rd.setCode(SysConstant.SYS_ERROR_EXISTS);
					rd.setData(SysConstant.SYS_ERROR_EXISTS_DESCRIPTION);
					return rd;
				}
				edit.setFunctionId(SysUtils.getUUID());
				edit.setCreateTime(new Date());
				edit.setCreater(BspUtils.getLoginUser().getUserId());
				edit.setUpdateTime(new Date());
				edit.setUpdater(BspUtils.getLoginUser().getUserId());
				sysFunctionsService.addSysFunctions(edit);
			}else {//修改
				edit.setUpdateTime(new Date());
				edit.setUpdater(BspUtils.getLoginUser().getUserId());
				sysFunctionsService.updateSysFunctions(edit);
			}
			TreeNode node = new TreeNode();
			node.setId(edit.getFunctionCode());
			node.setText(edit.getFunctionName());
			node.setIcon(edit.getFunctionIcon());
			node.setSelectable(true);
			Map<String,Object> state = new HashMap<String,Object>();
			if("3".equals(edit.getFunctionLevel())) {
				state.put("expanded", true);
			}else {
				state.put("expanded", false);
				node.setNodes(new ArrayList<TreeNode>());
			}
			node.setState(state);
			node.setData(edit);
			//刷新功能缓存
			BspUtils.refreshFunctionsCache();
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(node);
			return rd;
		}catch(Exception e) {
			e.printStackTrace();
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(SysConstant.SYS_ERROR_DESCRIPTION);
			return rd;
		}
	}
	
	/**
	 * 删除功能及其子功能
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/functions/deleteFunctions",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData deleteFunctions(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String functionId = request.getParameter("functionId");
			if(SysUtils.isNull(functionId)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			sysFunctionsService.deleteSysFunctions(functionId);
			//刷新功能缓存
			BspUtils.refreshFunctionsCache();
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
	
}
