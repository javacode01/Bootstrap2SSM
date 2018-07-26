package com.sys.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.sys.model.SysDictionariesItem;
import com.sys.model.SysFunctions;
import com.sys.model.SysOrgan;
import com.sys.model.SysOrganDesign;
import com.sys.model.SysOrganDesignExample;
import com.sys.model.SysUsers;
import com.sys.security.SysSecurityMetadataSource;
import com.sys.service.SysOrganDesignService;
import com.sys.service.SysOrganService;
import com.sys.utils.cache.DictionariesCache;
import com.sys.utils.cache.FunctionsCache;

/**
 * @ClassName: BspUtils 
 * @Description: 系统逻辑处理工具类
 * @author AK
 * @date 2017-11-19 下午2:51:29 
 *
 */
public class BspUtils {
	
	/**
	 * 获取当前登录的用户
	 * @return
	 */
	public static SysUsers getLoginUser(){
		SysUsers user = (SysUsers) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	/**
	 * 获取当前登录的用户所在机构
	 * @return
	 */
	public static SysOrgan getLoginOrgan(){
		SysUsers user = (SysUsers) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SysOrganService sysOrganService = getBean(SysOrganService.class);
		return sysOrganService.getOrganByOrganCode(user.getOrganCode());
	}
	
	/**
	 * 获取Bean
	 * @param class1
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return wac.getBean(clazz);
	}
	
	/**
	 * 返回数据字典JSON数组
	 * @param dicType
	 * @return
	 */
	public static Object listDicItemJSONByDicType(String dicType) {
		DictionariesCache dictionariesCache = getBean(DictionariesCache.class);
		List<SysDictionariesItem> list = dictionariesCache.getDicItemByDicType(dicType);
		return JSON.toJSON(list);
	}
	
	/**
	 * 获取机构级别JSON数组
	 * @return
	 */
	public static Object listOrganDesign() {
		SysOrganDesignService sysOrganDesignService = getBean(SysOrganDesignService.class);
		SysOrganDesignExample example = new SysOrganDesignExample();
		example.createCriteria();
		example.setOrderByClause("organ_level asc");
		List<SysOrganDesign> list = sysOrganDesignService.listSysOrganDesign(example);
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();
		for(SysOrganDesign obj:list) {
			Map<String,String> temp = new HashMap<String,String>();
			temp.put("itemCode", obj.getOrganLevel());
			temp.put("itemName", obj.getOrganLevelName());
			temp.put("nextLevel", obj.getNextLevel());
			temp.put("haveUser", obj.getHaveUser());
			temp.put("iconUrl", obj.getIconUrl());
			resultList.add(temp);
		}
		return JSON.toJSON(resultList);
	}
	
	/**
	 * 刷新数据字典缓存
	 * @throws Exception 
	 */
	public static void refreshDictionariesCache() throws Exception {
		DictionariesCache dictionariesCache = getBean(DictionariesCache.class);
		dictionariesCache.refresh();
	}
	
	/**
	 * 获取功能列表
	 * @return
	 */
	public static List<SysFunctions> getFunctionList(){
		FunctionsCache functionsCache = getBean(FunctionsCache.class);
		return functionsCache.getFunctionList();
	}
	
	/**
	 * 获取操作列表
	 * @return
	 */
	public static List<SysFunctions> getHandleList(){
		FunctionsCache functionsCache = getBean(FunctionsCache.class);
		return functionsCache.getHandleList();
	}
	
	/**
	 * 获取模块列表
	 * @return
	 */
	public static List<SysFunctions> getModuleList(){
		FunctionsCache functionsCache = getBean(FunctionsCache.class);
		return functionsCache.getModuleList();
	}
	
	/**
	 * 刷新功能缓存
	 * @throws Exception 
	 */
	public static void refreshFunctionsCache() throws Exception {
		FunctionsCache functionsCache = getBean(FunctionsCache.class);
		functionsCache.refresh();
	}
	
	/**
	 * 刷新角色功能关系缓存
	 * @throws Exception 
	 */
	public static void refreshSecurityCache() throws Exception {
		SysSecurityMetadataSource sysSecurityMetadataSource = getBean(SysSecurityMetadataSource.class);
		sysSecurityMetadataSource.refresh();
	}
	
	/**
	 * 获取系统名称
	 * @return
	 */
	public static String getSysName() {
		return ConfigUtils.getValue("sys.productName");
	}
	
	/**
	 * 获取系统简称
	 * @return
	 */
	public static String getSysShortName() {
		return ConfigUtils.getValue("sys.shortName");
	}
	
	/**
	 * 获取系统图标
	 * @return
	 */
	public static String getFavicon() {
		return ConfigUtils.getValue("sys.favicon");
	}
	
}
