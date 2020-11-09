package com.rimdev.rimdevices.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimdevices.Repo.DeviceStatusRepo;
import com.rimdev.rimdevices.entities.DeviceStatus;

@Service
public class DeviceStatusServ {
	
	@Autowired 
	private DeviceStatusRepo deviceStatusRepo;
	
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
public List<DeviceStatus> getall(String langcode) {
		try {
		return (List<DeviceStatus>) deviceStatusRepo.findAll();
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
	
	
	
	public DeviceStatus getbyid(int id) {
		
		
		try {
			Optional<DeviceStatus> flowid =deviceStatusRepo.findById(id);
			 
			 if (flowid.isPresent()){
				 DeviceStatus  ouput = flowid.get();
			
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
