package com.rimdev.user.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Repo.LanguageMapRepo;
import com.rimdev.user.entities.LanguageMap;

@Service
public class LanguageMapServ {
	
	
	@Autowired 
	private LanguageMapRepo languageMapRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
public LanguageMap getbycode(String Code,String langcode){
		
		try {
			Optional<LanguageMap> flowid =languageMapRepo.getbycode(Code);
			
			
			 
			 if (flowid.isPresent()){
				 LanguageMap  ouput = flowid.get();
			
				  return ouput;
						}
				else{
				   // alternative processing....
					throw new NoDataException("no Language Map Code found in "+ this.getClass().getName());
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
