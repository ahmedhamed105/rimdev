package com.rimdev.rimfile.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimfile.Repo.ComponentFileRepo;
import com.rimdev.rimfile.entities.ComponentFile;
import com.rimdev.rimfile.entities.ComponentInput;

@Service
public class ComponentFileServ {
	
	@Autowired
	ComponentFileRepo componentFileRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	


public List<ComponentFile> getbyinputcomp(ComponentInput input,String langcode) {
	
List<ComponentFile> types;
	
	try {
		
		types = (List<ComponentFile>) componentFileRepo.findbycompinput(input.getId());

	//    throw new NullPointerException("no data found in users");

	} catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }
	

	
		return types;
	
		
	}



}
