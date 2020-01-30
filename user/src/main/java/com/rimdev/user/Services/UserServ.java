package com.rimdev.user.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.UserRepo;
import com.rimdev.user.entities.User;

@Service
public class UserServ {
	
	
	@Autowired 
	private UserRepo userRepo;
	
	
public List<User> getall() {
		
		return (List<User>) userRepo.findAll();
		
	}

}
