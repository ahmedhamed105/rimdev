package com.rimdev.user.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.UserRepo;
import com.rimdev.user.Repo.UserTypeRepo;
import com.rimdev.user.entities.Area;
import com.rimdev.user.entities.UserType;

@Service
public class UserTypeServ {
	
	@Autowired 
	private UserTypeRepo userTypeRepo;
	
	
	
	@Autowired 
	private UserRepo userRepo;
	
	
public List<UserType> getall() {
		
		return (List<UserType>) userTypeRepo.findAll();
		
	}

public UserType getbyid(int id) {
	
	try {
		Optional<UserType> flowid =userTypeRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 UserType  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				return null;
			}
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
	

	
}

public UserType check_type(String type) {
	
	try {
		Optional<UserType> flowid =userTypeRepo.findbytype(type);
		 
		 if (flowid.isPresent()){
			 UserType  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				return null;
			}
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
	

	
}



public List<UserType> save(UserType input) {
	
	Date date = new Date();
	input.setTypeCreate(date);
	input.setTypeModify(date);
	UserType ouput =userTypeRepo.save(input);	
	
	return (List<UserType>) userTypeRepo.findAll();
	
}


public List<UserType> update(UserType input) {
	
	try {
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	Date date = new Date();
	input.setTypeModify(date);
	UserType ouput =userTypeRepo.save(input);	
	
	return (List<UserType>) userTypeRepo.findAll();
	
}



}
