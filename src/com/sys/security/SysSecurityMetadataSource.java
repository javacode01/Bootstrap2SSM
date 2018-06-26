package com.sys.security;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.sys.service.SysRolesService;
import com.sys.utils.SysUtils;

/**
 * @ClassName: SysSecurityMetadataSource
 * @Description: 从数据库提取权限和资源，装配到HashMap中，供Spring Security使用，用于权限校验。
 * @author AK
 * @date 2017-11-15 下午10:24:15
 * 
 */
public class SysSecurityMetadataSource implements FilterInvocationSecurityMetadataSource,InitializingBean {
	
	private static final Logger logger = Logger.getLogger(SysSecurityMetadataSource.class);

	@Autowired
	private SysRolesService sysRolesService;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	private RequestMatcher pathMatcher;
	
	/**
	 * 获取所有权限集合
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return new ArrayList<ConfigAttribute>();
	}
	
	/**
	 * 根据request请求获取访问资源所需权限
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Iterator<String> it = resourceMap.keySet().iterator();
		while (it.hasNext()) {
			String resURL = it.next();
			pathMatcher = new AntPathRequestMatcher(resURL);
			if (pathMatcher.matches(((FilterInvocation) object).getRequest())) {
				Collection<ConfigAttribute> returnCollection = resourceMap
						.get(resURL);
				return returnCollection;
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * 从数据库获取资源权限对应关系
	 * @return
	 */
	private Map<String,String> loadResuorce(){
        Map<String,String> map = new LinkedHashMap<String,String>();  
        List<Map<String,String>> list = sysRolesService.getRoleFunction();
	    Iterator<Map<String,String>> it = list.iterator();  
	    while(it.hasNext()){  
	    	Map<String,String> rs = it.next();  
	        String resourcePath = rs.get("FUNCTION_URL");  
	        String authorityMark = rs.get("AUTHORITY_MARK");  
	        
	        if(map.containsKey(resourcePath)){  
	        	String mark = map.get(resourcePath);  
	            map.put(resourcePath, mark+","+authorityMark);  
	        }else{  
	            map.put(resourcePath, authorityMark);
	        }  
	    }  
        return map;  
    } 
	
	/**
	 * 获取资源权限的关系
	 * @return
	 */
	protected Map<RequestMatcher, Collection<ConfigAttribute>> bindRequestMap(){  
        Map<RequestMatcher, Collection<ConfigAttribute>> map =   
                new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();  
          
        Map<String,String> resMap = loadResuorce();  
        for(Map.Entry<String,String> entry:resMap.entrySet()){  
            String key = entry.getKey();  
            Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();  
            atts = SecurityConfig.createListFromCommaDelimitedString(entry.getValue());  
            map.put(new AntPathRequestMatcher(key), atts);  
        }  
          
        return map;  
    }
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		logger.info("权限的缓存加载开始==================================");
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		List<Map<String,String>> resources = sysRolesService.getRoleFunction();
		for (Map resource : resources) {
			Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
			// 以权限名封装为Spring的security Object
			// resource.getRoleName() 角色名称 可随意 role_admin 或者 admin
			String AUTHORITY_MARK = "";
			if(resource.get("AUTHORITY_MARK") instanceof Clob){
				AUTHORITY_MARK=SysUtils.clob2String((Clob)resource.get("AUTHORITY_MARK"));
			}else{
				AUTHORITY_MARK=(String)resource.get("AUTHORITY_MARK");
			}
			
			if(!"".equals(AUTHORITY_MARK)&&null!=AUTHORITY_MARK){
				String[] roleIds = AUTHORITY_MARK.split(",");
				for(int i=0;i<roleIds.length;i++){
					ConfigAttribute configAttribute = new SecurityConfig(roleIds[i]);
					configAttributes.add(configAttribute);
				}
			}
			// resource.getInterceptUrl() 格式必须是 拦截的包路径
			// 或者是 比如 /manager/**/*.jh 或者 /system/manager/**/*.jsp
			resourceMap.put("/"+(String)resource.get("FUNCTION_URL"), configAttributes);
		}
		logger.info("权限的缓存加载完成==================================");
	}
	
	public void refresh() throws Exception {
		afterPropertiesSet();
	}

}
