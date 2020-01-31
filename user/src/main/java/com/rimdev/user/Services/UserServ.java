package com.rimdev.user.Services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.UserRepo;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.User;
import com.rimdev.user.entities.UserType;
import com.rimdev.user.ouputobject.response_all;

@Service
public class UserServ {
	
	
	@Autowired 
	private UserRepo userRepo;
	
	
public List<User> getall() {
		
		return (List<User>) userRepo.findAll();
		
	}


public User getuser(int id) {
	
	try {
		Optional<User> flowid =userRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 User  ouput = flowid.get();
		
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


public User check_user(String firstname,String middlename,String lastname) {
	
	try {
		Optional<User> flowid =userRepo.findbyname( firstname, middlename, lastname);
		 
		 if (flowid.isPresent()){
			 User  ouput = flowid.get();
		
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




public User Save(User input) {
	
	try {	
		Date date = new Date();
		String tokean=Generate.token(10);
		input.setUseridnumber(tokean);
		input.setUsercreate(date);
		input.setUsermodify(date);
		User ouput =userRepo.save(input);	
		return ouput;
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}			
		
	}


public User update(User input) {
	

	try {	
		Date date = new Date();
		input.setUsermodify(date);
		User ouput =userRepo.save(input);	
		return ouput;
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}			
		
	}



}
