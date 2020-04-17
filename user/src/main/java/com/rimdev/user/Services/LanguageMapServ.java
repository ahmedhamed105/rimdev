package com.rimdev.user.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;
import com.rimdev.user.Repo.LanguageMapRepo;
import com.rimdev.user.entities.LanguageMap;
import com.rimdev.user.entities.Languages;
import com.rimdev.user.ouputobject.lang_object;

@Service
public class LanguageMapServ {
	
	
	@Autowired 
	private LanguageMapRepo languageMapRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
public LanguageMap Save(String Code,String langcode) {
	try {
		LanguageMap map= new LanguageMap();
		Date date = new Date();
		map.setDateCreate(date);
		map.setDateModify(date);
		map.setTextcode(Code);
		LanguageMap ouput =languageMapRepo.save(map);	
		return ouput;
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


public LanguageMap update(LanguageMap map,String langcode) {
	try {
	Date date = new Date();
	map.setDateModify(date);
	LanguageMap ouput =languageMapRepo.save(map);	
	return ouput;	
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


public void delete(LanguageMap map,String langcode) {
	try {
	languageMapRepo.delete(map);	
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
	
	
	public List<LanguageMap> getalllang(String langcode) {
		try {
		return (List<LanguageMap>) languageMapRepo.findAll();
				
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
	
public LanguageMap getbycode(String Code,String langcode){
		
		try {
			Optional<LanguageMap> flowid =languageMapRepo.getbycode(Code);
			
			
			 
			 if (flowid.isPresent()){
				 LanguageMap  ouput = flowid.get();
			
				  return ouput;
						}
				else{
				   // alternative processing....
					
					return null;
					
				//	throw new NoDataException("no Language Map Code found in "+ this.getClass().getName());
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
