package com.rimdev.rimpages.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimpages.Exception.PopupException;
import com.rimdev.rimpages.Repo.RelationCompRepo;
import com.rimdev.rimpages.entities.RelationComp;

@Service
public class RelationCompServ {
	
	
	@Autowired
	RelationCompRepo relationCompRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
public RelationComp getbyid(int id,String langcode) {
		
		try {
			Optional<RelationComp> flowid =relationCompRepo.findById(id);
			 
			 if (flowid.isPresent()){
				 RelationComp  ouput = flowid.get();
			
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




public List<RelationComp> getbycomponent(int compid,String langcode) {
	
	try {
		List<RelationComp> com = (List<RelationComp>) relationCompRepo.getbycomponent(compid);
		 
		if(com == null || com.size() <= 0) {
			
			return new ArrayList<>();
			
		}
		
		return com;
		
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
