package com.rimdev.user.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;
import com.rimdev.user.Repo.MenuDisplayRepo;
import com.rimdev.user.entities.MenuDisplay;

@Service
public class MenuDisplayServ {
	
	@Autowired
	MenuDisplayRepo menuDisplayRepo;
	
	

	public List<MenuDisplay> getbycomponent(int parentid){
		List<MenuDisplay> com;
		
		try {
			com = (List<MenuDisplay>) menuDisplayRepo.getbyparent(parentid);

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
