package com.rimdev.rimlang.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimlang.Exception.PopupException;
import com.rimdev.rimlang.Repo.LanguagesRepo;
import com.rimdev.rimlang.entities.Languages;

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
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
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
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new PopupException(textConvertionServ.search("E104", langcode));
	    }

		
	}
	
	

public Languages getlangtext(int id,String langcode) {
	
	try {
		Optional<Languages> flowid =languagesRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 Languages  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				throw new PopupException("language not found");
			}
	} catch (TransientDataAccessException  se) {
		se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
    	se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
    	se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
    	se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }
}	


public Languages getlang(int id,String langcode) {
	
	try {
		Optional<Languages> flowid =languagesRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 Languages  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				return null;
			}
	} catch (TransientDataAccessException  se) {
		se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
    	se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
    	se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
    	se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }
}


	
public Languages Save(Languages input,String langcode){
	
	try {		
		Date date = new Date();
		input.setDateCreate(date);
		input.setDateModify(date);
		Languages ouput =languagesRepo.save(input);	
		return ouput;
	} catch (TransientDataAccessException  se) {
		se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
    	se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
    	se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
    	se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }
	}


public Languages update(Languages old,Languages input,String langcode)  {
	
	
	try {	
		old.setLanguagecode(input.getLanguagecode());
		old.setLanguagename(input.getLanguagename());
		Date date = new Date();
		old.setDateModify(date);
		Languages ouput1 =languagesRepo.save(old);	
		return ouput1;
	} catch (TransientDataAccessException  se) {
		se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
    	se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
    	se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
    	se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
    }
	
	
	
}

}
