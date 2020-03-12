package com.rimdev.user.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Repo.UserRepo;
import com.rimdev.user.Repo.UserTypeRepo;
import com.rimdev.user.entities.Area;
import com.rimdev.user.entities.Telephones;
import com.rimdev.user.entities.UserType;

@Service
public class UserTypeServ {
	
	@Autowired 
	private UserTypeRepo userTypeRepo;
	
	@Autowired 
	private UserRepo userRepo;
	
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
public List<UserType> getall(String langcode) {
		
List<UserType> teles;
	
	try {
		
		teles = (List<UserType>) userTypeRepo.findAll();

	//    throw new NoDataException("no data found in users");

	} catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }
	
	if(teles == null || teles.size() <= 0) {
		
		throw new NoDataException(textConvertionServ.search("E108", langcode));
		
	}
	
		return teles;

	}

public UserType getbyid(int id) {
	
	try {
		Optional<UserType> flowid =userTypeRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 UserType  ouput = flowid.get();
		
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

public UserType check_type(String type) {
	
	try {
		Optional<UserType> flowid =userTypeRepo.findbytype(type);
		 
		 if (flowid.isPresent()){
			 UserType  ouput = flowid.get();
		
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



public List<UserType> save(UserType input) {
	
	Date date = new Date();
	input.setTypeCreate(date);
	input.setTypeModify(date);
	UserType ouput =userTypeRepo.save(input);	
	
	return (List<UserType>) userTypeRepo.findAll();
	
}


public List<UserType> update(UserType input) {
	
	try {
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	Date date = new Date();
	input.setTypeModify(date);
	UserType ouput =userTypeRepo.save(input);	
	
	return (List<UserType>) userTypeRepo.findAll();
	
}



}
