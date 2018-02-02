package com.sys.tag;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.sys.model.SysFunctions;
import com.sys.service.SysFunctionsService;
import com.sys.utils.BspUtils;

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

	@Override
	protected int doStartTagInternal() throws Exception {
		// TODO Auto-generated method stub
		SysFunctionsService sysFunctionsService = BspUtils.getBean(SysFunctionsService.class);
		Map<String, List<SysFunctions>> map = sysFunctionsService.getUserMenus();
		List<SysFunctions> moduleList = map.get("moduleList");
		List<SysFunctions> functionList = map.get("functionList");
		List<SysFunctions> handleList = map.get("handleList");
		if(null!=moduleList){
		}
		pageContext.getOut().write("<p>菜单</p>"); 
		return EVAL_PAGE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
