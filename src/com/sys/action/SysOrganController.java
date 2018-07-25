package com.sys.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sys.model.SysOrgan;
import com.sys.model.SysOrganExample;
import com.sys.service.SysOrganService;
import com.sys.utils.BspUtils;
import com.sys.utils.JsonUtils;
import com.sys.utils.ResultData;
import com.sys.utils.SysConstant;
import com.sys.utils.SysUtils;
import com.sys.utils.TreeNode;
import com.sys.utils.ZTreeNode;

@Controller
public class SysOrganController {
	
	private static final Logger logger = Logger.getLogger(SysOrganController.class);
	@Autowired
	private SysOrganService sysOrganService;
	
	/**
	 * 跳转组织结构管理界面
	 * @return
	 */
	@RequestMapping(value="/sys/organ/initmanagerorgan",method=RequestMethod.GET)
	public String initmanagerorgan(){
		return "jsp/sys/organ/managerorgan";
	}
	
	/**
	 * 获取组织结构树节点
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/organ/getOrganNodes",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody List<ZTreeNode> getOrganNodes(HttpServletRequest request, HttpServletResponse response) {
		//获取参数
		String organCode = request.getParameter("id");
		if(null==organCode||"".equals(organCode)) {
			return null;
		}
		SysOrganExample example = new SysOrganExample();
		example.createCriteria().andParentCodeEqualTo(organCode);
		example.setOrderByClause("seq asc");
		List<SysOrgan> list = sysOrganService.listOrganByExample(example);
		//转换Nodes
		List<ZTreeNode> nodes = new ArrayList<ZTreeNode>();
		if(null!=list) {
			for(SysOrgan organ : list) {
				ZTreeNode node = new ZTreeNode();
				node.setId(organ.getOrganCode());
				node.setPid(organ.getParentCode());
				node.setName(organ.getOrganName());
				node.setData(organ);
				node.setIsParent("true");
				nodes.add(node);
			}
		}
		return nodes;
	}
	
	/**
	 * 机构查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/organ/listOrgans",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody List<SysOrgan> listOrgans(HttpServletRequest request, HttpServletResponse response) {
		String filter = request.getParameter("filter");
		Map<String,Object> filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		SysOrganExample pote= new SysOrganExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		
		if(!SysUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}
		List<SysOrgan> list = sysOrganService.listOrganByExample(pote);
		return list;
	}
	
	/**
	 * 跳转机构编辑页面
	 * @return
	 */
	@RequestMapping(value="/sys/organ/toEditOrgan",method=RequestMethod.POST)
	public ModelAndView toEditOrgan(HttpServletRequest request, HttpServletResponse response){
		String handle = request.getParameter("handle");
		String organCode = request.getParameter("organCode");
		SysOrgan organ = null;
		if(!"0".equals(organCode)) {
			SysOrganExample example= new SysOrganExample();
			example.createCriteria().andOrganCodeEqualTo(organCode);
			List<SysOrgan> list = sysOrganService.listOrganByExample(example);
			organ = list.get(0);
		}else {
			organ = new SysOrgan();
			organ.setOrganCode("0");
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("handle", handle);
		if(SysConstant.SYS_HANDLE_ADD.equals(handle)){
			mv.addObject("parentOrgan", organ);
		}else if(SysConstant.SYS_HANDLE_EDIT.equals(handle)){
			mv.addObject("organ",organ);
		}
		mv.setViewName("jsp/sys/organ/editorgan");
		return mv;
	}
	
	/**
	 * 删除机构及其下级机构
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/organ/deleteOrgan",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData deleteOrgan(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String organId = request.getParameter("organId");
			if(SysUtils.isNull(organId)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			sysOrganService.deleteSysOrgan(organId);
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
	 * 保存机构编辑内容
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/organ/editOrgan",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData editOrgan(SysOrgan edit,HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			if(null==edit) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			if(SysUtils.isNull(edit.getOrganId())) {//新增
				SysOrganExample example = new SysOrganExample();
				example.createCriteria().andOrganCodeEqualTo(edit.getOrganCode());
				List<SysOrgan> list = sysOrganService.listOrganByExample(example);
				if(null!=list&&list.size()>0) {
					rd.setCode(SysConstant.SYS_ERROR_EXISTS);
					rd.setData(SysConstant.SYS_ERROR_EXISTS_DESCRIPTION);
					return rd;
				}
				edit.setOrganId(SysUtils.getUUID());
				edit.setCreateTime(new Date());
				edit.setCreater(BspUtils.getLoginUser().getUserId());
				edit.setUpdateTime(new Date());
				edit.setUpdater(BspUtils.getLoginUser().getUserId());
				sysOrganService.addSysOrgan(edit);
			}else {//修改
				edit.setUpdateTime(new Date());
				edit.setUpdater(BspUtils.getLoginUser().getUserId());
				sysOrganService.updateSysOrgan(edit);
			}
			TreeNode node = new TreeNode();
			node.setId(edit.getOrganCode());
			node.setText(edit.getOrganName());
			node.setIcon(edit.getIconUrl());
			node.setSelectable(true);
			Map<String,Object> state = new HashMap<String,Object>();
			state.put("expanded", false);
			node.setNodes(new ArrayList<TreeNode>());
			node.setState(state);
			node.setData(edit);
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(node);
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
	 * 获取机构信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/organ/getOrganDetail/{organCode}",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData editOrgan(@PathVariable("organCode") String organCode,HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			SysOrganExample example = new SysOrganExample();
			example.createCriteria().andOrganCodeEqualTo(organCode);
			List<SysOrgan> list = sysOrganService.listOrganByExample(example);
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(list.get(0));
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
