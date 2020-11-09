package com.rimdev.rimdevices.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.rimdevices.Repo.AdressRepo;
import com.rimdev.rimdevices.Repo.UserRepo;
import com.rimdev.rimdevices.entities.Adress;
import com.rimdev.rimdevices.entities.Email;
import com.rimdev.rimdevices.entities.User;



@Service
public class AdressServ {
	
	@Autowired 
	private AdressRepo adressRepo;
	
	
	@Autowired 
	private UserRepo userRepo;
	
	
public List<Adress> getall() {
		
		return (List<Adress>) adressRepo.findAll();
		
	}




public Adress check_address(String add) {
	
	try {
		Optional<Adress> flowid =adressRepo.findbyaddress(add);
		 
		 if (flowid.isPresent()){
			 Adress  ouput = flowid.get();
		
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


public Adress getbyid(int id) {
	
	try {
		Optional<Adress> flowid =adressRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 Adress  ouput = flowid.get();
		
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



public List<Adress> save(Adress input) {
	
	Date date = new Date();
	input.setAddCreate(date);
	input.setAddModify(date);
	Adress ouput =adressRepo.save(input);	
	
	
	return (List<Adress>) adressRepo.findAll();
	
}


public List<Adress> update(Adress input) {
	
	Date date = new Date();
	input.setAddModify(date);
	Adress ouput =adressRepo.save(input);	
	
	return (List<Adress>) adressRepo.findAll();
	
}

}
