package com.rimdev.user.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.UserStatusRepo;
import com.rimdev.user.entities.DataStatus;
import com.rimdev.user.entities.UserStatus;

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

	

}
