package com.rimdev.rimpages.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.rimpages.Repo.PasswordHistoryRepo;
import com.rimdev.rimpages.entities.Adress;
import com.rimdev.rimpages.entities.PasswordHistory;

@Service
public class PasswordHistoryServ {
	
	@Autowired 
	private PasswordHistoryRepo passwordHistoryRepo;
	
	
	
public List<PasswordHistory> getall() {
		
		return (List<PasswordHistory>) passwordHistoryRepo.findAll();
		
	}
	
	
	
	public boolean save(PasswordHistory input) {
		
		Date date = new Date();
		input.setPassCreate(date);
		input.setPassModify(date);
		PasswordHistory ouput =passwordHistoryRepo.save(input);	
		
		
		return true;
		
	}

}
