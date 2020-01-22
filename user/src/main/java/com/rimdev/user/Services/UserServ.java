package com.rimdev.user.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.UserRepo;

@Service
public class UserServ {
	
	
	@Autowired 
	private UserRepo userRepo;

}
