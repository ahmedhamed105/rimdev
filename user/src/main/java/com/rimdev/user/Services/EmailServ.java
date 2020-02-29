package com.rimdev.user.Services;

import java.util.Calendar;
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
import com.rimdev.user.Repo.EmailRepo;
import com.rimdev.user.Repo.UserRepo;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.DeviceOs;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.Telephones;
import com.rimdev.user.entities.User;

@Service
public class EmailServ {

	@Autowired
	private EmailRepo emailRepo;
	
	@Autowired 
	private UserServ userServ;
	
	
public List<Email> getall() {
	
List<Email> emails;
	
	try {
		
		emails = (List<Email>) emailRepo.findAll();

	//    throw new NoDataException("no data found in users");

	} catch (TransientDataAccessException  se) {
		throw new NullPointerException("TransientDataAccessException");
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException("RecoverableDataAccessException");
    }catch (ScriptException  se) {
		throw new NullPointerException("ScriptException");
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException("NonTransientDataAccessException");
    }
	
	if(emails == null || emails.size() <= 0) {
		
		throw new NoDataException("no data found in "+ this.getClass().getName());
		
	}
	
		return emails;
	
		
	}



public Email check_email(String email) {
	
	try {
		Optional<Email> flowid =emailRepo.findbyemail(email);
		 
		 if (flowid.isPresent()){
			 Email  ouput = flowid.get();
		
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



public List<Email> getbyuser(int userid) {
	
	try {
		
		List<Email> cu=(List<Email>) emailRepo.findbyuser(userid);
		
		return cu;
		
		
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
	
}



public Email getbyid(int id) {
	
	try {
		Optional<Email> flowid =emailRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 Email  ouput = flowid.get();
		
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



public List<Email> save(Email input) {
	

	
	User  usero ;
	
	try {
		Optional<User> use=userRepo.findById(input.getUserID().getId());
		 
		 if (use.isPresent()){
			   usero = use.get();

			}
			else{
			   // alternative processing....
				return (List<Email>) emailRepo.findAll();
			}
	} catch (Exception e) {
		// TODO: handle exception
		return (List<Email>) emailRepo.findAll();
	}
	
	Date date = new Date();
	input.setEmailCreate(date);
	input.setEmailModify(date);
	input.setUserID(usero);
	Email ouput =emailRepo.save(input);	
	
	return (List<Email>) emailRepo.findAll();
	
}


public void delete(Email input) {	

	emailRepo.delete(input);	
	
}



public List<Email> update(Email input) {
	
	Date date = new Date();
	input.setEmailModify(date);
	Email ouput =emailRepo.save(input);	
	
	return (List<Email>) emailRepo.findAll();
	
}

}
