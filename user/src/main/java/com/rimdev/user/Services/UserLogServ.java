package com.rimdev.user.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rimdev.user.Repo.UserLogRepo;
import com.rimdev.user.entities.Adress;
import com.rimdev.user.entities.UserLog;

@Service
public class UserLogServ {

	@Autowired 
	private UserLogRepo userLogRepo;
	
public List<UserLog> getall() {
		
		return (List<UserLog>) userLogRepo.findAll();
		
	}


public boolean save(UserLog input) {
	
	Date date = new Date();
	input.setLogDate(date);
	UserLog ouput =userLogRepo.save(input);	
	
	
	return true;
	
}

}
