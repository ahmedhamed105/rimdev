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
import com.rimdev.language.Repo.TextConvertionRepo;



@Service
public class TextConvertionServ {
	
	
	@Autowired 
	private TextConvertionRepo textConvertionRepo;
	
	
	@Autowired 
	LanguagesServ languagesServ;
	
	@Autowired 
	LanguageMapServ languageMapServ;
	
	
	
	public TextConvertion search(inputsearch search){
		if(search.getCode() == null || search.getLangcode() == null) {	
			 new NoDataException("please enter code");
		}
		
		Languages lan= languagesServ.getbycode(search.getLangcode());
		LanguageMap lanmap= languageMapServ.getbycode(search.getCode());
		
		 return getbylangmap(lan.getId(), lanmap.getId());
		
	}
	
	
	
	
	
	public TextConvertion getbylangmap(int Languages_ID,int language_map_ID){
		
		try {
			Optional<TextConvertion> flowid =textConvertionRepo.getbylangandmap(Languages_ID, language_map_ID);
			
			
			 
			 if (flowid.isPresent()){
				 TextConvertion  ouput = flowid.get();
			
				  return ouput;
						}
				else{
				   // alternative processing....
					throw new NoDataException("no TextConvertion found in "+ this.getClass().getName());
				}
		} catch (TransientDataAccessException  se) {
			throw new NullPointerException("TransientDataAccessException");
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException("RecoverableDataAccessException");
	    }catch (ScriptException  se) {
			throw new NullPointerException("ScriptException");
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException("NonTransientDataAccessException");
	    }
		
		
	}

}
