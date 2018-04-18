package ${basePackage}.action${baseModule};

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

import ${basePackage}.model${baseModule}.${className};
import ${basePackage}.model${baseModule}.${className}Example;
import ${basePackage}.service${baseModule}.${className}Service;
import com.sys.model.SysRoles;
import com.sys.model.SysRolesExample;
import com.sys.utils.BspUtils;
import com.sys.utils.JsonUtils;
import com.sys.utils.Page;
import com.sys.utils.PageListData;
import com.sys.utils.ResultData;
import com.sys.utils.SysConstant;
import com.sys.utils.SysUtils;

@Controller
public class ${className}Controller {
	
	@Autowired
	private ${className}Service ${className?lower_case}Service;
	
	/**
	 * 跳转${comment}管理界面
	 * @return
	 */
	@RequestMapping(value="/${urlPrefix}/${className?lower_case}/initManage${className}",method=RequestMethod.GET)
	public String initManage${className}(){
		return "${targetDir}manage${className?lower_case}";
	}
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/${urlPrefix}/${className?lower_case}/list${className}ByPage",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody PageListData list${className}ByPage(HttpServletRequest request, HttpServletResponse response) {
		String start = request.getParameter("offset");//当前第几页
		String rows = request.getParameter("limit");//每页显示条数
		String filter = request.getParameter("filter");
		Map<String,Object> filterMap = JsonUtils.json2map(filter);
		String sortfield=request.getParameter("sortfield");
		Page p= new Page();
		${className}Example pote= new ${className}Example();
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
		PageListData pageData = ${className?lower_case}Service.list${className}ByPage(pote);
		return pageData;
	}
	
	/**
	 * 跳转${comment}编辑界面
	 * @return
	 */
	@RequestMapping(value="/${urlPrefix}/${className?lower_case}/toEdit${className}",method=RequestMethod.GET)
	public ModelAndView toEdit${className}(HttpServletRequest request, HttpServletResponse response){
		String handle = request.getParameter("handle");
		ModelAndView mv = new ModelAndView();
		mv.addObject("handle",handle);//操作类型
		if(SysConstant.SYS_HANDLE_ADD.equals(handle)){//新增
		}else if(SysConstant.SYS_HANDLE_EDIT.equals(handle)){//修改
			String recid = request.getParameter("recid");
			${className} ${className?lower_case} = ${className?lower_case}Service.get${className}ById(recid);
			mv.addObject("${className?lower_case}",${className?lower_case});
		}
		mv.setViewName("${targetDir}/edit${className?lower_case}");
		return mv;
	}
	
	/**
	 * 保存${comment}编辑内容
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/${urlPrefix}/${className?lower_case}/edit${className}",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData edit${className}(DemoOrder edit,HttpServletRequest request, HttpServletResponse response) {
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
				${className?lower_case}Service.add${className}(edit);
			}else {//修改
				edit.setUpdateTime(new Date());
				edit.setUpdater(BspUtils.getLoginUser().getUserId());
				${className?lower_case}Service.update${className}(edit);
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
	 * 删除${comment}
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/${urlPrefix}/${className?lower_case}/delete${className}",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResultData delete${className}(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			String recids = request.getParameter("recids");
			if(SysUtils.isNull(recids)) {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
				return rd;
			}
			${className?lower_case}Service.delete${className}(recids);
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
