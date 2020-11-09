package com.rimdev.rimcart.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimcart.Repo.ComponentButtonRepo;
import com.rimdev.rimcart.entities.ComponentButton;

@Service
public class ComponentButtonServ {
	
	

	@Autowired
	ComponentButtonRepo componentButtonRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	public List<ComponentButton> getbycomponent(int compid,String langcode){
		List<ComponentButton> com;
		
		try {
			com = (List<ComponentButton>) componentButtonRepo.getbycomponent(compid);

		} catch (TransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }
		
		
		return com;
	}

}
