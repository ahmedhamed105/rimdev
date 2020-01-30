package com.rimdev.user.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.TelephonesRepo;
import com.rimdev.user.Repo.UserRepo;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.Telephones;
import com.rimdev.user.entities.User;

@Service
public class TelephonesServ {
	
	@Autowired 
	private TelephonesRepo telephonesRepo;
	
	
	@Autowired 
	private UserRepo userRepo;
	
	
public List<Telephones> getall() {
		
		return (List<Telephones>) telephonesRepo.findAll();
		
	}




public Telephones check_tele(String tele) {
	
	try {
		Optional<Telephones> flowid =telephonesRepo.findbytele(tele);
		 
		 if (flowid.isPresent()){
			 Telephones  ouput = flowid.get();
		
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


public Telephones getbyid(int id) {
	
	try {
		Optional<Telephones> flowid =telephonesRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 Telephones  ouput = flowid.get();
		
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




public List<Telephones> save(Telephones input) {
	
	Date date = new Date();
	input.setTeleCreate(date);
	input.setTeleModify(date);
	Telephones ouput =telephonesRepo.save(input);	
	
	return (List<Telephones>) telephonesRepo.findAll();
	
}


public List<Telephones> update(Telephones input) {
	
	Date date = new Date();
	input.setTeleModify(date);
	Telephones ouput =telephonesRepo.save(input);	
	
	return (List<Telephones>) telephonesRepo.findAll();
	
}

}
