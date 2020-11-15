package com.rimdev.rimnotif.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimnotif.Exception.PopupException;
import com.rimdev.rimnotif.Repo.RelationTypeRepo;
import com.rimdev.rimnotif.entities.RelationType;

@Service
public class RelationTypeServ {
	
	@Autowired
	RelationTypeRepo relationTypeRepo;
	
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
public RelationType getbyid(int id,String langcode) {
		
		try {
			Optional<RelationType> flowid =relationTypeRepo.findById(id);
			 
			 if (flowid.isPresent()){
				 RelationType  ouput = flowid.get();
			
				  return ouput;
						}
				else{
				   // alternative processing....
					 return null;
					 }
		} catch (TransientDataAccessException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }
	}

}
