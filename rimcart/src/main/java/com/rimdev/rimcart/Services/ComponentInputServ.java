package com.rimdev.rimcart.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimcart.Repo.ComponentInputRepo;
import com.rimdev.rimcart.entities.ComponentInput;
import com.rimdev.rimcart.entities.ComponentSelect;

@Service
public class ComponentInputServ {

	
	@Autowired
	ComponentInputRepo componentInputRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	public List<ComponentInput> getbycomponent(int compid,String langcode){
		List<ComponentInput> com;
		
		try {
			com = (List<ComponentInput>) componentInputRepo.getbycomponent(compid);

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
