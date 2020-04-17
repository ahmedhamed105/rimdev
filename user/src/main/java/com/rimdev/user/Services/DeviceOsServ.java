package com.rimdev.user.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
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
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
public List<DeviceOs> getall(String langcode) {
		try {
		return (List<DeviceOs>) deviceOsRepo.findAll();
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
