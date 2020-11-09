package com.rimdev.rimdevices.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimdevices.Repo.ApplicationRepo;
import com.rimdev.rimdevices.entities.Application;

@Service
public class ApplicationServ {
	
	@Autowired
	ApplicationRepo applicationRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	
	public List<Application> getall(String langcode) {

		try {
			return (List<Application>) applicationRepo.findAll();
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

	
	

public Application getbyid(int id) {
	
	
	try {
		Optional<Application> flowid =applicationRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 Application  ouput = flowid.get();
		
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



public Application getbytype(String type) {
	
	
	try {
		Optional<Application> flowid =applicationRepo.findbyname(type);
		 
		 if (flowid.isPresent()){
			 Application  ouput = flowid.get();
		
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
