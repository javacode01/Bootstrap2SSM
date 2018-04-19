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
			//获取一级菜单
			List<SysFunctions> level1List = functionMap.get("level1List");
			//用户已分配操作
			List<SysFunctions> handleList = functionMap.get("handleList");
			//step2.拼装菜单列表
			StringBuffer menu = new StringBuffer();
			menu.append("<ul id=\""+id+"\" class=\"sidebar-menu\" data-widget=\"tree\"><li class=\"header\">功能菜单</li>");
			for(SysFunctions level1 : level1List) {//添加一级菜单
				if(SysConstant.SYS_FUNCTION_LEVEL_MODULE.equals(level1.getFunctionLevel())) {//如果是模块
					menu.append("<li class=\"treeview\"><a href=\"javascript:void(0);\"><i class=\""+level1.getFunctionIcon()+"\"></i> <span>"+level1.getFunctionName()+"</span> <span class=\"pull-right-container\">");
					menu.append("<i class=\"fa fa-angle-left pull-right\"></i></span></a>");
					menu.append("<ul class=\"treeview-menu\" id=\""+level1.getFunctionCode()+"\">");
					menu.append(getSubModule(functionMap,level1.getFunctionCode()));
					menu.append(getFunction(functionMap,level1.getFunctionCode()));
					menu.append("</ul></li>");
				}else if(SysConstant.SYS_FUNCTION_LEVEL_FUNCTION.equals(level1.getFunctionLevel())) {//如果是功能
					menu.append("<li><a href=\"javascript:void(0);\" id=\""+level1.getFunctionCode()+"\" data-id=\""+level1.getFunctionCode()+"\" data-name=\""+level1.getFunctionName()+"\" ");
					for(SysFunctions handle : handleList) {
						if(level1.getFunctionCode().equals(handle.getParentCode())&&SysConstant.SYS_IS.equals(handle.getDefaultAction())) {
							menu.append("data-url=\""+path+handle.getFunctionUrl()+"\" onclick=\"tabs.addTab(event)\" ");
							break;
						}
					}
					menu.append("><i class=\""+level1.getFunctionIcon()+"\"></i>"+level1.getFunctionName()+"</a></li>");
				}
			}
			menu.append("</ul>");
			pageContext.getOut().write(menu.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	/**
	 * 获取子模块
	 * @param parentCode
	 * @return
	 */
	public String getSubModule(Map<String, List<SysFunctions>> functionMap,String parentCode) {
		StringBuffer subModule = new StringBuffer();
		//用户已分配模块
		List<SysFunctions> moduleList = functionMap.get("moduleList");
		for(SysFunctions module : moduleList) {
			if(parentCode.equals(module.getParentCode())) {
				subModule.append("<li class=\"treeview\"><a href=\"javascript:void(0);\"><i class=\""+module.getFunctionIcon()+"\"></i> <span>"+module.getFunctionName()+"</span> <span class=\"pull-right-container\">");
				subModule.append("<i class=\"fa fa-angle-left pull-right\"></i></span></a>");
				subModule.append("<ul class=\"treeview-menu\" id=\""+module.getFunctionCode()+"\">");
				subModule.append(getSubModule(functionMap,module.getFunctionCode()));
				subModule.append(getFunction(functionMap,module.getFunctionCode()));
				subModule.append("</ul></li>");
			}
		}
		return subModule.toString();
	}
	
	/**
	 * 获取功能
	 * @param functionMap
	 * @param parentCode
	 * @return
	 */
	public String getFunction(Map<String, List<SysFunctions>> functionMap,String parentCode) {
		StringBuffer functionHtml = new StringBuffer();
		//用户已分配功能
		List<SysFunctions> functionList = functionMap.get("functionList");
		//用户已分配操作
		List<SysFunctions> handleList = functionMap.get("handleList");
		for(SysFunctions function : functionList) {
			if(parentCode.equals(function.getParentCode())) {
				functionHtml.append("<li><a href=\"javascript:void(0);\" id=\""+function.getFunctionCode()+"\" data-id=\""+function.getFunctionCode()+"\" data-name=\""+function.getFunctionName()+"\" ");
				for(SysFunctions handle : handleList) {
					if(function.getFunctionCode().equals(handle.getParentCode())&&SysConstant.SYS_IS.equals(handle.getDefaultAction())) {
						functionHtml.append("data-url=\""+path+handle.getFunctionUrl()+"\" onclick=\"tabs.addTab(event)\" ");
						break;
					}
				}
				functionHtml.append("><i class=\""+function.getFunctionIcon()+"\"></i>"+function.getFunctionName()+"</a></li>");
			}
		}
		return functionHtml.toString();
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
