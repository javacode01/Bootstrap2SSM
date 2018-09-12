package com.sys.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sys.model.SysUserRole;
import com.sys.model.SysUserRoleExample;
import com.sys.model.SysUsers;
import com.sys.service.SysUsersService;
import com.sys.utils.ConfigUtils;

/**
 * 自定义用户认证，并获取用户权限
 * @ClassName: SysAuthenticationProvider 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author AK
 * @date 2017-11-19 上午11:07:10 
 *
 */
public class SysAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider implements Serializable  {
	
	private static final long serialVersionUID = -5077398886211859787L;
	
	@Autowired
	private SysUsersService sysUsersService;
	/**
	 * 用户验证
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails user,	UsernamePasswordAuthenticationToken token)throws AuthenticationException {
		String rawPassword = (String) token.getCredentials(); //登录密码 
		String passwd = user.getPassword(); //数据库中密码
		//验证密码
		if (!passwd.equals(rawPassword)) {  
			throw new AuthenticationServiceException("密码不正确");  
		}
		SysUsers sysUser =(SysUsers) user;
		sysUser.setLoginTime(new Date());//设置登录时间
	}

	/**
	 * 用户初始化
	 */
	@Override
	protected UserDetails retrieveUser(String userName,UsernamePasswordAuthenticationToken token)	throws AuthenticationException {
		
		SysUsers sysuser = sysUsersService.getSysUserByUserName(userName);
		
		SysWebAuthenticationDetails details = (SysWebAuthenticationDetails) token.getDetails();
		if(!details.getRequestAuthCode().equals(details.getSessionAuthCode())) {
			throw new AuthenticationServiceException("验证码错误");
		}
		
		if(null==sysuser){
            throw new AuthenticationServiceException("用户不存在");  
        }else if (!sysuser.isEnabled()){  
            throw new AuthenticationServiceException("用户已被禁用");  
        }else if (!sysuser.isAccountNonExpired()) {  
            throw new AuthenticationServiceException("账号已过期");  
        }else if (!sysuser.isAccountNonLocked()) {  
            throw new AuthenticationServiceException("账号已被锁定");  
        }else if (!sysuser.isCredentialsNonExpired()) {  
            throw new AuthenticationServiceException("凭证已过期");  
        }
		
		token.setDetails(sysuser);
		
		//用户角色
    	ArrayList<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
    	SysUserRoleExample roleExample = new SysUserRoleExample();
    	roleExample.createCriteria().andUserCodeEqualTo(sysuser.getUserCode());
    	List<SysUserRole> roleslist = sysUsersService.getSysUserRole(roleExample);
    	//如果为开发模式，用户都拥有SUPERADMIN角色
    	if("false".equals(ConfigUtils.getValue("sys.productModel"))) {
    		SysUserRole superadmin = new SysUserRole();
    		superadmin.setRoleCode("SUPERADMIN");
    		roleslist.add(superadmin);
    	}
    	for(final SysUserRole role:roleslist){
    		authorities.add(new GrantedAuthority() {
    			private static final long serialVersionUID = 1L;
    			@Override
    			public String getAuthority() {
    				return role.getRoleCode();
    			}
    		});
    	}
    	//用户都拥有ROLE_USER角色
    	authorities.add(new GrantedAuthority() {
			private static final long serialVersionUID = 1L;
			@Override
			public String getAuthority() {
				return "ROLE_USER";
			}
		});
    	//设置用户权限
    	sysuser.setAuthorities(authorities);
	    return sysuser;  
	}

}
