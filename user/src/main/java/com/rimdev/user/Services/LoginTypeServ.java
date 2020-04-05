package com.rimdev.user.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Repo.LoginTypeRepo;
import com.rimdev.user.entities.DeviceType;
import com.rimdev.user.entities.LoginType;

@Service
public class LoginTypeServ {
	
	@Autowired
	LoginTypeRepo loginTypeRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	
	public List<LoginType> getall(String langcode) {

		try {
			return (List<LoginType>) loginTypeRepo.findAll();
	} catch (TransientDataAccessException  se) {
		throw new NoDataException(textConvertionServ.search("E104", langcode));
	} catch (RecoverableDataAccessException  se) {
		throw new NoDataException(textConvertionServ.search("E104", langcode));
	}catch (ScriptException  se) {
		throw new NoDataException(textConvertionServ.search("E104", langcode));
	}catch (NonTransientDataAccessException  se) {
		throw new NoDataException(textConvertionServ.search("E104", langcode));
	}
			
		}

	
	

public LoginType getbyid(int id) {
	
	
	try {
		Optional<LoginType> flowid =loginTypeRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 LoginType  ouput = flowid.get();
		
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
