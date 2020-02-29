package com.rimdev.language.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.language.Entities.LanguageMap;
import com.rimdev.language.Entities.Languages;
import com.rimdev.language.Entities.TextConvertion;
import com.rimdev.language.Exception.NoDataException;
import com.rimdev.language.Object.inputsearch;
import com.rimdev.language.Repo.LanguageMapRepo;

@Service
public class LanguageMapServ {
	
	
	@Autowired 
	private LanguageMapRepo languageMapRepo;
	
public LanguageMap getbycode(String Code){
		
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
			throw new NullPointerException("E104");
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException("E104");
	    }catch (ScriptException  se) {
			throw new NullPointerException("E104");
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException("E104");
	    }
		
		
	
		
	}


}
