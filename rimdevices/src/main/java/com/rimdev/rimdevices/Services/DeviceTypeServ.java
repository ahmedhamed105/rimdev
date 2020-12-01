package com.rimdev.rimdevices.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimdevices.Repo.DeviceTypeRepo;
import com.rimdev.rimdevices.entities.DeviceOs;
import com.rimdev.rimdevices.entities.DeviceType;


@Service
public class DeviceTypeServ {
	
	@Autowired 
	 DeviceTypeRepo deviceTypeRepo;
	
	@Autowired
	LangExternalServ textConvertionServ;
	
public List<DeviceType> getall(String langcode) {

	try {
		return (List<DeviceType>) deviceTypeRepo.findAll();
} catch (TransientDataAccessException  se) {
	throw new NullPointerException(textConvertionServ.search("E104", langcode));
} catch (RecoverableDataAccessException  se) {
	throw new NullPointerException(textConvertionServ.search("E104", langcode));
}catch (ScriptException  se) {
	throw new NullPointerException(textConvertionServ.search("E104", langcode));
}catch (NonTransientDataAccessException  se) {
	throw new NullPointerException(textConvertionServ.search("E104", langcode));
}
		
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
