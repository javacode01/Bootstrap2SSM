package com.sys.tag;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.sys.model.SysFunctions;
import com.sys.service.SysFunctionsService;
import com.sys.utils.BspUtils;
import com.sys.utils.SysConstant;

/**
 * @ClassName: SysMenuTag 
 * @Description: 自定义菜单栏
 * @author AK
 * @date 2017-11-22 下午8:06:32 
 *
 */
public class SysMenuTag extends RequestContextAwareTag{

	private static final long serialVersionUID = 8993777943926400477L;
	
	private String id;
	private String path;

	@Override
	protected int doStartTagInternal() throws Exception {
		// TODO Auto-generated method stub
		try {
			//step1.获取用户菜单
			SysFunctionsService sysFunctionsService = BspUtils.getBean(SysFunctionsService.class);
			Map<String, List<SysFunctions>> functionMap = sysFunctionsService.getUserMenus();
			//全部功能
			List<SysFunctions> allList = functionMap.get("allList");
			//step2.拼装菜单列表
			StringBuffer menu = new StringBuffer();
			menu.append("<ul id=\""+id+"\" class=\"sidebar-menu\" data-widget=\"tree\"><li class=\"header\">功能菜单导航</li>");
			menu.append(getNextFunction(allList,"root"));
			menu.append("</ul>");
			pageContext.getOut().write(menu.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	/**
	 * 获取下级功能
	 * @param functionMap
	 * @param parentCode
	 * @return
	 */
	public String getNextFunction(List<SysFunctions> allList,String parentCode) {
		StringBuffer subFunction = new StringBuffer();
		for(SysFunctions function : allList) {
			if(parentCode.equals(function.getParentCode())) {
				if(SysConstant.SYS_FUNCTION_LEVEL_MODULE.equals(function.getFunctionLevel())) {
					subFunction.append("<li class=\"treeview\"><a href=\"javascript:void(0);\"><i class=\""+function.getFunctionIcon()+"\"></i> <span>"+function.getFunctionName()+"</span> <span class=\"pull-right-container\">");
					subFunction.append("<i class=\"fa fa-angle-left pull-right\"></i></span></a>");
					subFunction.append("<ul class=\"treeview-menu\" id=\""+function.getFunctionCode()+"\">");
					subFunction.append(getNextFunction(allList,function.getFunctionCode()));
					subFunction.append("</ul></li>");
				}else if(SysConstant.SYS_FUNCTION_LEVEL_FUNCTION.equals(function.getFunctionLevel())){
					subFunction.append("<li><a href=\"javascript:void(0);\" id=\""+function.getFunctionCode()+"\" data-id=\""+function.getFunctionCode()+"\" data-name=\""+function.getFunctionName()+"\" data-icon=\""+function.getFunctionIcon()+"\" ");
					for(SysFunctions handle : allList) {
						if(function.getFunctionCode().equals(handle.getParentCode())&&SysConstant.SYS_IS.equals(handle.getDefaultAction())) {
							subFunction.append("data-url=\""+path+handle.getFunctionUrl()+"\" onclick=\"tabs.addTab(event)\" ");
							break;
						}
					}
					subFunction.append("><i class=\""+function.getFunctionIcon()+"\"></i>"+function.getFunctionName()+"</a></li>");
				}
				
			}
		}
		return subFunction.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
