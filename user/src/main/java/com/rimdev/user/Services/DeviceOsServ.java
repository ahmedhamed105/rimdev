package com.rimdev.user.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.AreaRepo;
import com.rimdev.user.Repo.DeviceOsRepo;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.DeviceOs;
import com.rimdev.user.entities.DeviceType;

@Service
public class DeviceOsServ {
	
	@Autowired 
	private DeviceOsRepo deviceOsRepo;
	
	
public List<DeviceOs> getall() {
		
		return (List<DeviceOs>) deviceOsRepo.findAll();
		
	}


public DeviceOs getbyname(String name) {
	
	
	try {
		Optional<DeviceOs> flowid =deviceOsRepo.findbyname(name);
		 
		 if (flowid.isPresent()){
			 DeviceOs  ouput = flowid.get();
		
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
