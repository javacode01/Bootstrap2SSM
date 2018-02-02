package com.sys.utils;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sys.model.SysDictionariesItem;
import com.sys.model.SysOrgan;
import com.sys.model.SysUsers;
import com.sys.service.SysOrganService;
import com.sys.utils.cache.DictionariesCache;

/**
 * @ClassName: BspUtils 
 * @Description: 系统工具类
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
		List<SysDictionariesItem> list = DictionariesCache.getDicItemByDicType(dicType);
		return JSON.toJSON(list);
	}
	
}
