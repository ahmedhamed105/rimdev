package com.rimdev.rimdevices.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimdevices.Repo.ComponentSelectRepo;
import com.rimdev.rimdevices.entities.Component;
import com.rimdev.rimdevices.entities.ComponentSelect;

@Service
public class ComponentSelectServ {
	
	
	@Autowired
	ComponentSelectRepo componentSelectRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	public List<ComponentSelect> getbycomponent(int compid,String langcode){
		List<ComponentSelect> com;
		
		try {
			com = (List<ComponentSelect>) componentSelectRepo.getbycomponent(compid);

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
