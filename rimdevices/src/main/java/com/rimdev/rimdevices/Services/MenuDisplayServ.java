package com.rimdev.rimdevices.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.rimdev.rimdevices.Repo.MenuDisplayRepo;
import com.rimdev.rimdevices.entities.MenuDisplay;

@Service
public class MenuDisplayServ {
	
	@Autowired
	MenuDisplayRepo menuDisplayRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	

	public List<MenuDisplay> getbycomponent(int parentid,String langcode){
		List<MenuDisplay> com;
		
		try {
			com = (List<MenuDisplay>) menuDisplayRepo.getbyparent(parentid);
			
			for (MenuDisplay menuDisplay : com) {

			menuDisplay.setMenuname(textConvertionServ.search(menuDisplay.getMenuname(), langcode));	

			}
			

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
