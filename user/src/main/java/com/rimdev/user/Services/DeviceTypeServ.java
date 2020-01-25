package com.rimdev.user.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rimdev.user.Repo.DeviceTypeRepo;
import com.rimdev.user.entities.DeviceOs;
import com.rimdev.user.entities.DeviceType;


@Service
public class DeviceTypeServ {
	
	@Autowired 
	private DeviceTypeRepo deviceTypeRepo;
	
	
	
public List<DeviceType> getall() {
		
		return (List<DeviceType>) deviceTypeRepo.findAll();
		
	}



public DeviceType getbyname(String name) {
	
	
	try {
		Optional<DeviceType> flowid =deviceTypeRepo.findbyname(name);
		 
		 if (flowid.isPresent()){
			 DeviceType  ouput = flowid.get();
		
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


}
