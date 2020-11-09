package com.rimdev.rimfile.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimfile.Exception.PopupException;
import com.rimdev.rimfile.Repo.TextConvertionRepo;
import com.rimdev.rimfile.entities.LanguageMap;
import com.rimdev.rimfile.entities.Languages;
import com.rimdev.rimfile.entities.TextConvertion;
import com.rimdev.rimfile.outputobject.Lang_obj;
import com.rimdev.rimfile.outputobject.lang_code;



@Service
public class TextConvertionServ {
	
	
	@Autowired 
	 TextConvertionRepo textConvertionRepo;
	
	
	@Autowired 
	LanguagesServ languagesServ;
	
	@Autowired 
	LanguageMapServ languageMapServ;
	
	

	
	
	public void update(TextConvertion old,Lang_obj input,String langcode) {
		try {
	LanguageMap map=languageMapServ.getbycode(input.getLangcode(), langcode);
	if(map == null) {
	   map=languageMapServ.Save(input.getLangcode(), langcode);
	}else {
	   map=languageMapServ.update(map, langcode);
	}
	
	Languages lang=languagesServ.getlangtext(input.getTxtconv().getLanguagesID().getId(), langcode);

	
	old.setLanguagemapID(map);
	old.setLanguagesID(lang);
	old.setReturnLang(input.getTxtconv().getReturnLang());
	Date date = new Date();
	old.setDateModify(date);
	textConvertionRepo.save(old);
	
		} catch (TransientDataAccessException  se) {
			throw new PopupException(search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new PopupException(search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new PopupException(search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new PopupException(search("E104", langcode));
	    }
		
	}
	
public void Save(Lang_obj input,String langcode) {
	try {
		
		LanguageMap map=languageMapServ.getbycode(input.getLangcode(), langcode);
		if(map == null) {
		   map=languageMapServ.Save(input.getLangcode(), langcode);
		}else {
		   map=languageMapServ.update(map, langcode);
		}
	Languages lang=languagesServ.getlangtext(input.getTxtconv().getLanguagesID().getId(), langcode);
	TextConvertion text=new TextConvertion();
	text.setLanguagemapID(map);
	text.setLanguagesID(lang);
	text.setReturnLang(input.getTxtconv().getReturnLang());
	Date date = new Date();
	text.setDateCreate(date);
	text.setDateModify(date);
	textConvertionRepo.save(text);

	} catch (TransientDataAccessException  se) {
		throw new PopupException(search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new PopupException(search("E104", langcode));
    }catch (ScriptException  se) {
		throw new PopupException(search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new PopupException(search("E104", langcode));
    }
	
	}
	
	
	
	public List<Lang_obj> getalllang(String langcode) {
		
		List<Lang_obj> all= new ArrayList<Lang_obj>();
		List<LanguageMap> languageMaps =languageMapServ.getalllang(langcode);
		
		for (LanguageMap languageMap : languageMaps) {
	
			List<TextConvertion> txtconv=(List<TextConvertion>) textConvertionRepo.getbymap(languageMap.getId());
			for (TextConvertion txtconvtmp : txtconv) {
				Lang_obj a= new Lang_obj();
				a.setLangcode(languageMap.getTextcode());
				a.setTxtconv(txtconvtmp);
				all.add(a);
			}

		}
		
		
		return all;
		
	}
	
	
	public String search(String code,String langcode){
		if(code == null || langcode == null) {	
			 new PopupException("please enter code");
		}
		//System.out.println(code + " "+langcode);
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
			throw new PopupException(search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new PopupException(search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new PopupException(search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new PopupException(search("E104", langcode));
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
					throw new PopupException("no TextConvertion found in "+ this.getClass().getName());
				}
		} catch (TransientDataAccessException  se) {
			throw new PopupException(search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new PopupException(search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new PopupException(search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new PopupException(search("E104", langcode));
	    }
		
		
	}
	
	
public TextConvertion getbylangmaptxt(int Lang,String code,String langcode){
		
		try {
			Optional<TextConvertion> flowid =textConvertionRepo.getbylangandmaptxt(Lang, code);
			
			
			 
			 if (flowid.isPresent()){
				 TextConvertion  ouput = flowid.get();
			
				  return ouput;
						}
				else{
				   // alternative processing....
					return null;
				}
		} catch (TransientDataAccessException  se) {
			throw new PopupException(search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new PopupException(search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new PopupException(search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new PopupException(search("E104", langcode));
	    }
		
		
	}

}
