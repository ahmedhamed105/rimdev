package com.rimdev.user.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Repo.TextConvertionRepo;
import com.rimdev.user.entities.LanguageMap;
import com.rimdev.user.entities.Languages;
import com.rimdev.user.entities.TextConvertion;
import com.rimdev.user.ouputobject.lang_code;
import com.rimdev.user.ouputobject.lang_object;



@Service
public class TextConvertionServ {
	
	
	@Autowired 
	private TextConvertionRepo textConvertionRepo;
	
	
	@Autowired 
	LanguagesServ languagesServ;
	
	@Autowired 
	LanguageMapServ languageMapServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	
	public List<lang_object> getalllang(String langcode) {
		
		List<lang_object> all= new ArrayList<lang_object>();
		List<LanguageMap> languageMaps =languageMapServ.getalllang(langcode);
		
		for (LanguageMap languageMap : languageMaps) {
			lang_object a= new lang_object();
			a.setLangaugecode(languageMap.getTextcode());	
			TextConvertion text1=getbylangmap(1,languageMap.getId(), langcode);
				a.setText1(text1.getReturnLang());
				
				TextConvertion text2=getbylangmap(2,languageMap.getId(), langcode);
				a.setText2(text2.getReturnLang());
							
				TextConvertion text3=getbylangmap(3,languageMap.getId(), langcode);
				a.setText3(text3.getReturnLang());
			all.add(a);
		}
		
		
		return all;
		
	}
	
	
	public String search(String code,String langcode){
		if(code == null || langcode == null) {	
			 new NoDataException("please enter code");
		}
		System.out.println(code + " "+langcode);
		Languages lan= languagesServ.getbycode(langcode,langcode);
		LanguageMap lanmap= languageMapServ.getbycode(code,langcode);
		if(lanmap == null || lan == null) {
			return code;
			
		}
		
		 return getbylangmap(lan.getId(), lanmap.getId(),langcode).getReturnLang();
		
	}
	
	
	public List<TextConvertion> getbymapid(int map,String langcode){
		try {
		List<TextConvertion> listtext=	(List<TextConvertion>) textConvertionRepo.getbymapid(map);
		
		return listtext;
		
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
	
	
	public TextConvertion getbylangmap(int Lang,int map,String langcode){
		
		try {
			Optional<TextConvertion> flowid =textConvertionRepo.getbylangandmap(Lang, map);
			
			
			 
			 if (flowid.isPresent()){
				 TextConvertion  ouput = flowid.get();
			
				  return ouput;
						}
				else{
				   // alternative processing....
					throw new NoDataException("no TextConvertion found in "+ this.getClass().getName());
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
