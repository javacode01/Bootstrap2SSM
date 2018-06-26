package com.sys.action;

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

import com.sys.model.SysDictionariesItem;
import com.sys.model.SysDictionariesItemExample;
import com.sys.service.SysDictionariesItemService;
import com.sys.utils.JsonUtils;
import com.sys.utils.Page;
import com.sys.utils.PageListData;
import com.sys.utils.ResultData;
import com.sys.utils.SysConstant;
import com.sys.utils.SysUtils;

@Controller
public class SysDictionariesItemController {
	
	private static final Logger logger = Logger.getLogger(SysDictionariesItemController.class);
	@Autowired
	private SysDictionariesItemService sysDictionariesItemService;
	
	/**
	 * 字典项分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/dictionariesitem/listDictionariesItemByPage",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody PageListData listDictionariesItemByPage(HttpServletRequest request, HttpServletResponse response) {
		String start = request.getParameter("offset");//当前第几页
		String rows = request.getParameter("limit");//每页显示条数
		String filter = request.getParameter("filter");
		Map<String,Object> filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= new Page();
		SysDictionariesItemExample pote= new SysDictionariesItemExample();
		if(null!=filterMap){
			pote.createCriteria();
			pote.integratedQuery(filterMap);
		}
		
		if(!SysUtils.isNull(sortfield)){
			pote.setOrderByClause(sortfield);
		}else {
			pote.setOrderByClause("seq asc");
		}
		p.setBegin(Integer.parseInt(start));
		p.setEnd(Integer.parseInt(start)+Integer.parseInt(rows));
		pote.setPage(p);
		PageListData pageData = sysDictionariesItemService.listDictionariesItemByPage(pote);
		return pageData;
	}
	
	/**
	 * 跳转字典编辑界面
	 * @return
	 */
	@RequestMapping(value="/sys/dictionariesitem/toEditDicItem",method=RequestMethod.GET)
	public ModelAndView toEditDicItem(HttpServletRequest request, HttpServletResponse response){
		String handle = request.getParameter("handle");
		ModelAndView mv = new ModelAndView();
		mv.addObject("handle",handle);//操作类型
		if(SysConstant.SYS_HANDLE_ADD.equals(handle)){//新增
		}else if(SysConstant.SYS_HANDLE_EDIT.equals(handle)){//修改
			String recid = request.getParameter("recid");
			SysDictionariesItem item = sysDictionariesItemService.getDictionariesItemById(recid);
			mv.addObject("item",item);
		}
		mv.setViewName("jsp/sys/dictionaries/editdicitem");
		return mv;
	}
	
	/**
	 * 保存数据字典项编辑内容
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/dictionariesitem/editDicItem",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData editDicItem(SysDictionariesItem edit,HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			if(null==edit) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			SysDictionariesItemExample example = new SysDictionariesItemExample();
			example.createCriteria().andDicTypeEqualTo(edit.getDicType()).andItemCodeEqualTo(edit.getItemCode());
			List<SysDictionariesItem> list = sysDictionariesItemService.listDictionariesItemByExample(example);
			if(SysUtils.isNull(edit.getRecid())) {//新增
				if(null!=list&&list.size()>0) {
					rd.setCode(SysConstant.SYS_ERROR_EXISTS);
					rd.setData(SysConstant.SYS_ERROR_EXISTS_DESCRIPTION);
					return rd;
				}
				edit.setRecid(SysUtils.getUUID());
				sysDictionariesItemService.addSysDictionariesItem(edit);
			}else {//修改
				if(!edit.getRecid().equals(list.get(0).getRecid())) {
					rd.setCode(SysConstant.SYS_ERROR_EXISTS);
					rd.setData(SysConstant.SYS_ERROR_EXISTS_DESCRIPTION);
					return rd;
				}
				sysDictionariesItemService.updateSysDictionariesItem(edit);
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
	 * 删除数据字典
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/dictionariesitem/deleteDicItems",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData deleteDicItems(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String recids = request.getParameter("recids");
			if(!SysUtils.isNull(recids)) {
				sysDictionariesItemService.deleteSysDictionariesItems(recids);
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
}
