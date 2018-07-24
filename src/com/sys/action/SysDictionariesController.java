package com.sys.action;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.sys.model.SysDictionaries;
import com.sys.model.SysDictionariesExample;
import com.sys.service.SysDictionariesService;
import com.sys.utils.BspUtils;
import com.sys.utils.JsonUtils;
import com.sys.utils.Page;
import com.sys.utils.PageListData;
import com.sys.utils.ResultData;
import com.sys.utils.SysConstant;
import com.sys.utils.SysUtils;
import com.sys.utils.TreeNode;
/**
 * 数据字典管理
 * @author Administrator
 *
 */
@Controller
public class SysDictionariesController {
	
	private static final Logger logger = Logger.getLogger(SysDictionariesController.class);
	@Autowired
	private SysDictionariesService sysDictionariesService;
	
	/**
	 * 跳转数据字典管理界面
	 * @return
	 */
	@RequestMapping(value="/sys/dictionaries/initmanagerdictionaries",method=RequestMethod.GET)
	public String initmanagerfunctions(){
		return "jsp/sys/dictionaries/managerdictionaries";
	}
	
	/**
	 * 字典项分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/dictionaries/listDictionariesByPage",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody PageListData listDictionariesByPage(HttpServletRequest request, HttpServletResponse response) {
		String start = request.getParameter("offset");//当前第几页
		String rows = request.getParameter("limit");//每页显示条数
		String filter = request.getParameter("filter");
		Map<String,Object> filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= new Page();
		SysDictionariesExample pote= new SysDictionariesExample();
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
		PageListData pageData = sysDictionariesService.listDictionariesByPage(pote);
		return pageData;
	}
	
	/**
	 * 跳转字典编辑界面
	 * @return
	 */
	@RequestMapping(value="/sys/dictionaries/toEditDic",method=RequestMethod.GET)
	public ModelAndView toEditDic(HttpServletRequest request, HttpServletResponse response){
		String handle = request.getParameter("handle");
		ModelAndView mv = new ModelAndView();
		mv.addObject("handle",handle);//操作类型
		if(SysConstant.SYS_HANDLE_ADD.equals(handle)){//新增
		}else if(SysConstant.SYS_HANDLE_EDIT.equals(handle)){//修改
			String recid = request.getParameter("recid");
			String nodeId = request.getParameter("nodeId");
			SysDictionaries dic = sysDictionariesService.getDictionariesById(recid);
			mv.addObject("dic",dic);
			mv.addObject("nodeId",nodeId);
		}
		mv.setViewName("jsp/sys/dictionaries/editdic");
		return mv;
	}
	
	/**
	 * 保存数据字典编辑内容
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/dictionaries/editDic",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData editDic(SysDictionaries edit,HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			if(null==edit) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			if(SysUtils.isNull(edit.getRecid())) {//新增
				SysDictionariesExample example = new SysDictionariesExample();
				example.createCriteria().andDicTypeEqualTo(edit.getDicType());
				List<SysDictionaries> list = sysDictionariesService.listDictionariesByExample(example);
				if(null!=list&&list.size()>0) {
					rd.setCode(SysConstant.SYS_ERROR_EXISTS);
					rd.setData(SysConstant.SYS_ERROR_EXISTS_DESCRIPTION);
					return rd;
				}
				edit.setRecid(SysUtils.getUUID());
				sysDictionariesService.addSysDictionaries(edit);
			}else {//修改
				sysDictionariesService.updateSysDictionaries(edit);
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
	 * 删除数据字典
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/dictionaries/deleteDic",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData deleteDic(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String recid = request.getParameter("recid");
			if(SysUtils.isNull(recid)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			sysDictionariesService.deleteSysDictionaries(recid);
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
	 * 刷新字典缓存
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/dictionaries/refreshDictionaries",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData refreshDictionaries(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			BspUtils.refreshDictionariesCache();
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
