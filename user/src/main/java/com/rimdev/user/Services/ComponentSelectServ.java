package com.rimdev.user.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.ComponentSelectRepo;
import com.rimdev.user.entities.Component;
import com.rimdev.user.entities.ComponentSelect;

@Service
public class ComponentSelectServ {
	
	
	@Autowired
	ComponentSelectRepo componentSelectRepo;
	
	
	public List<ComponentSelect> getbycomponent(int compid){
		List<ComponentSelect> com;
		
		try {
			com = (List<ComponentSelect>) componentSelectRepo.getbycomponent(compid);

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
