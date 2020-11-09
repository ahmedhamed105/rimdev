package com.rimdev.rimauth.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimauth.Repo.UserStatusRepo;
import com.rimdev.rimauth.entities.DataStatus;
import com.rimdev.rimauth.entities.UserStatus;
import com.rimdev.rimauth.entities.UserType;

@Service
public class UserStatusServ {
	
	@Autowired
	UserStatusRepo userStatusRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	
	public List<UserStatus> getall(String langcode) {
		try {
			return (List<UserStatus>) userStatusRepo.findAll();
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
	
	

public UserStatus getbyid(int id) {
	
	try {
		Optional<UserStatus> flowid =userStatusRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 UserStatus  ouput = flowid.get();
		
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
