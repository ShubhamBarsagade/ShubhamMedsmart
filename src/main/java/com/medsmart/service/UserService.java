package com.medsmart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.medsmart.config.CustomUserDetails;
import com.medsmart.dao.UserRepository;
import com.medsmart.entity.AdminDetail;
import com.medsmart.entity.UserDetail;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository repository;

	public UserDetail getUserByUserUsername(String userUsername) {
		System.out.println("inside getUserByUserUsername");
		UserDetail user = repository.findUserByUserUsername(userUsername);
		System.out.println(user);
		return user;
	}
	
	public UserDetail getUserDetailByUserEmail(String email) {
		System.out.println("inside email" +email);
		UserDetail user = repository.findUserDetailByUserEmail(email);
		System.out.println(user);
		return user;
	}

	public void saveUserDetail(UserDetail userDetail) {
		System.out.println("inside create admin block");
		repository.save(userDetail);
	}

	public UserDetail getUserByUserUsernameAndUserPass(String userUsername, String userPass) {
		System.out.println("inside getUserByUserUsernameAndUserPass "+userUsername+ " "+ userPass);
		UserDetail userDetail = repository.findUserDetailByUserUsernameAndUserPass(userUsername,userPass);
		System.out.println(userDetail);
		return userDetail;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetail user = repository.findUserByUserUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("Could not found user");
		}
		
		CustomUserDetails customUserDetails = new CustomUserDetails(user);
		
		return customUserDetails;
	}

}
