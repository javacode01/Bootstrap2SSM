package com.demo.action.module;

import java.util.Date;
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

import com.demo.model.module.DemoOrder;
import com.demo.model.module.DemoOrderExample;
import com.demo.service.module.DemoOrderService;
import com.sys.utils.BspUtils;
import com.sys.utils.JsonUtils;
import com.sys.utils.Page;
import com.sys.utils.PageListData;
import com.sys.utils.ResultData;
import com.sys.utils.SysConstant;
import com.sys.utils.SysUtils;

@Controller
public class DemoOrderController {

	private static final Logger logger = Logger.getLogger(DemoOrderController.class);
	
	@Autowired
	private DemoOrderService demoorderService;
	
	/**
	 * 跳转订单（示例表）管理界面
	 * @return
	 */
	@RequestMapping(value="/module/demoorder/initManageDemoOrder",method=RequestMethod.GET)
	public String initManageDemoOrder(){
		return "jsp/demo/module/demoorder/managedemoorder";
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/module/demoorder/listDemoOrderByPage",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody PageListData listDemoOrderByPage(HttpServletRequest request, HttpServletResponse response) {
		String start = request.getParameter("offset");//当前第几页
		String rows = request.getParameter("limit");//每页显示条数
		String filter = request.getParameter("filter");
		Map<String,Object> filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= new Page();
		DemoOrderExample pote= new DemoOrderExample();
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
		PageListData pageData = demoorderService.listDemoOrderByPage(pote);
		return pageData;
	}
	
	/**
	 * 跳转订单（示例表）编辑界面
	 * @return
	 */
	@RequestMapping(value="/module/demoorder/toEditDemoOrder",method=RequestMethod.GET)
	public ModelAndView toEditDemoOrder(HttpServletRequest request, HttpServletResponse response){
		String handle = request.getParameter("handle");
		ModelAndView mv = new ModelAndView();
		mv.addObject("handle",handle);//操作类型
		if(SysConstant.SYS_HANDLE_ADD.equals(handle)){//新增
		}else if(SysConstant.SYS_HANDLE_EDIT.equals(handle)){//修改
			String recid = request.getParameter("recid");
			DemoOrder demoorder = demoorderService.getDemoOrderById(recid);
			mv.addObject("demoorder",demoorder);
		}
		mv.setViewName("jsp/demo/module/demoorder//editdemoorder");
		return mv;
	}
	
	/**
	 * 保存订单（示例表）编辑内容
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/module/demoorder/editDemoOrder",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData editDemoOrder(DemoOrder edit,HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			if(null==edit) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			if(SysUtils.isNull(edit.getRecid())) {//新增
				edit.setRecid(SysUtils.getUUID());
				edit.setCreateTime(new Date());
				edit.setCreater(BspUtils.getLoginUser().getUserId());
				edit.setUpdateTime(new Date());
				edit.setUpdater(BspUtils.getLoginUser().getUserId());
				demoorderService.addDemoOrder(edit);
			}else {//修改
				edit.setUpdateTime(new Date());
				edit.setUpdater(BspUtils.getLoginUser().getUserId());
				demoorderService.updateDemoOrder(edit);
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
	 * 删除订单（示例表）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/module/demoorder/deleteDemoOrder",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData deleteDemoOrder(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String recids = request.getParameter("recids");
			if(SysUtils.isNull(recids)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			demoorderService.deleteDemoOrder(recids);
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
