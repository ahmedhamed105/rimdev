package com.rimdev.rimpages.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.rimpages.Repo.AreaRepo;
import com.rimdev.rimpages.Repo.UserRepo;
import com.rimdev.rimpages.entities.Area;
import com.rimdev.rimpages.entities.Email;
import com.rimdev.rimpages.entities.User;

@Service
public class AreaServ {
	
	@Autowired 
	private AreaRepo areaRepo;
	
	@Autowired 
	private UserRepo userRepo;
	
	
public List<Area> getall() {
		
		return (List<Area>) areaRepo.findAll();
		
	}


public Area check_area(String area) {
	
	try {
		Optional<Area> flowid =areaRepo.findbyarea(area);
		 
		 if (flowid.isPresent()){
			 Area  ouput = flowid.get();
		
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

public Area getbyid(int id) {
	
	try {
		Optional<Area> flowid =areaRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 Area  ouput = flowid.get();
		
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



public List<Area> save(Area input) {
	
	Date date = new Date();
	input.setAreacreate(date);
	input.setAreamodify(date);
	Area ouput =areaRepo.save(input);	
	
	return (List<Area>) areaRepo.findAll();
	
}


public List<Area> update(Area input) {
	
	try {
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	Date date = new Date();
	input.setAreamodify(date);
	Area ouput =areaRepo.save(input);	
	
	return (List<Area>) areaRepo.findAll();
	
}


}
