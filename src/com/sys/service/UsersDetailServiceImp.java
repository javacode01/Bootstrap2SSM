package com.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sys.client.SysUsersMapper;
import com.sys.model.SysUsers;

/**
 * @ClassName: UsersDetailServiceImp 
 * @Description: 通过实现UserDetailsService实现用户验证
 * @author AK
 * @date 2017-11-19 上午11:20:46 
 *
 */
public class UsersDetailServiceImp implements UserDetailsService{
	
	private SysUsersMapper sysUsersMapper;
	
	public UsersDetailServiceImp(SysUsersMapper sysUsersMapper){
		this.sysUsersMapper = sysUsersMapper;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		SysUsers sysUser = sysUsersMapper.getUserByName(username);
		if(null!=sysUser){
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			sysUser.setAuthorities(authorities);
			return sysUser;
		}
		throw new UsernameNotFoundException("用户"+username+"不存在");
	}

}
