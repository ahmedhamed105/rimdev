package com.rimdev.user.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rimdev.user.Repo.UserLoginRepo;

@Service
public class UserLoginServ {
	
	@Autowired 
	private UserLoginRepo userLoginRepo;

}
