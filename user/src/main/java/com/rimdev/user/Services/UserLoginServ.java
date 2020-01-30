package com.rimdev.user.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.UserLoginRepo;
import com.rimdev.user.Repo.UserRepo;
import com.rimdev.user.entities.UserLogin;

@Service
public class UserLoginServ {
	
	@Autowired 
	private UserLoginRepo userLoginRepo;
	
	
	@Autowired 
	private UserRepo userRepo;
	
	
public List<UserLogin> getall() {
		
		return (List<UserLogin>) userLoginRepo.findAll();
		
	}

}
