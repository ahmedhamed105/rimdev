package com.rimdev.user.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.ConfigurationRepo;
import com.rimdev.user.entities.Configuration;
import com.rimdev.user.entities.DeviceOs;

@Service
public class ConfigurationServ {
	
	
	@Autowired
	ConfigurationRepo configurationRep;
	
	

public Configuration getbykey(String key) {
	
	
	try {
		Optional<Configuration> flowid =configurationRep.findbykey(key);
		 
		 if (flowid.isPresent()){
			 Configuration  ouput = flowid.get();
		
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
