package com.rimdev.rimlang.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.rimlang.Repo.ConfigurationRepo;
import com.rimdev.rimlang.entities.Component;
import com.rimdev.rimlang.entities.Configuration;
import com.rimdev.rimlang.entities.DeviceOs;

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




public List<Integer> getlist(String key) {
	
List<Integer> in=new ArrayList<Integer>();
		List<Configuration> com = (List<Configuration>) configurationRep.findallbykey(key);
		for (Configuration configuration : com) {
			in.add(configuration.getConfignum());
		}
	
	return in;
	
}


public Configuration setvalue(String key,String value) {
	
	
	try {
		Optional<Configuration> flowid =configurationRep.findbykey(key);
		 
		 if (flowid.isPresent()){
			 Configuration  ouput = flowid.get();
			 ouput.setConfigvalue(value);
			 configurationRep.save(ouput);
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
