package com.rimdev.product.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.product.Repo.LanguagesRepo;
import com.rimdev.product.entities.Languages;

@Service
public class LanguagesServ {
	
	@Autowired 
	private LanguagesRepo languagesRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	public List<Languages> getalllang(String langcode) {
		try {
		return (List<Languages>) languagesRepo.findAll();
				
		} catch (TransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }
		
	}
	
	
	
	public Languages getbycode(String Code,String langcode){
		
		try {
			Optional<Languages> flowid =languagesRepo.getbycode(Code);
			
			
			 
			 if (flowid.isPresent()){
				 Languages  ouput = flowid.get();
			
				  return ouput;
						}
				else{
				   // alternative processing....
					return null;
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
		
		
		
		
	}

}
