package com.rimdev.user.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.UserTypeRepo;

@Service
public class UserTypeServ {
	
	@Autowired 
	private UserTypeRepo userTypeRepo;

}
