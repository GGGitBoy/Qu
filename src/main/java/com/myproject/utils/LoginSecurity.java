package com.myproject.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.myproject.dao.userMapper;
import com.myproject.pojo.user;


public class LoginSecurity implements UserDetailsService{
	
	@Autowired
	private userMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
        user user = userMapper.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

		System.out.println(username);
		System.out.println(user.toString());

		return new User(user.getUsername(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(user.getUsertypenum()));
	}
	
	
	


}
