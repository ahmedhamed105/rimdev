package com.rimdev.accounting.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.LanguageMap;
import com.rimdev.accounting.Enttities.Languages;
import com.rimdev.accounting.Enttities.TextConvertion;
import com.rimdev.accounting.Repo.TextConvertionRepo;




@Service
public class TextConvertionServ {
	
	
	@Autowired 
	private TextConvertionRepo textConvertionRepo;
	
	
	@Autowired 
	LanguagesServ languagesServ;
	
	@Autowired 
	LanguageMapServ languageMapServ;
	

	
	
	

	public String search(String code,String langcode){
		if(code == null || langcode == null) {	
			 new NullPointerException("please enter code");
		}
		//System.out.println(code + " "+langcode);
		Languages lan= languagesServ.getbycode(langcode,langcode);
		LanguageMap lanmap= languageMapServ.getbycode(code,langcode);
		if(lanmap == null || lan == null) {
			return code;
			
		}
		
		 return getbylangmap(lan.getId(), lanmap.getId(),langcode).getReturnLang();
		
	}
	
	
	
	
	
	public TextConvertion getbylangmap(int Lang,int map,String langcode){
		
		try {
			Optional<TextConvertion> flowid =textConvertionRepo.getbylangandmap(Lang, map);
			
			
			 
			 if (flowid.isPresent()){
				 TextConvertion  ouput = flowid.get();
			
				  return ouput;
						}
				else{
				   // alternative processing....
					throw new NullPointerException("no TextConvertion found in "+ this.getClass().getName());
				}
		} catch (TransientDataAccessException  se) {
			throw new NullPointerException(search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException(search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new NullPointerException(search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException(search("E104", langcode));
	    }
		
		
	}

}
