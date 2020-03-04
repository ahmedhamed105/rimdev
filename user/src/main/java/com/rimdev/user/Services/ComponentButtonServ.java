package com.rimdev.user.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.ComponentButtonRepo;
import com.rimdev.user.entities.ComponentButton;

@Service
public class ComponentButtonServ {
	
	

	@Autowired
	ComponentButtonRepo componentButtonRepo;
	
	
	public List<ComponentButton> getbycomponent(int compid){
		List<ComponentButton> com;
		
		try {
			com = (List<ComponentButton>) componentButtonRepo.getbycomponent(compid);

		} catch (TransientDataAccessException  se) {
			throw new NullPointerException("E104");
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException("E104");
	    }catch (ScriptException  se) {
			throw new NullPointerException("E104");
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException("E104");
	    }
		
		
		return com;
	}

}
