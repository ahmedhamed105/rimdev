package com.rimdev.accounting.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.Currency;
import com.rimdev.accounting.Exception.PopupException;
import com.rimdev.accounting.Repo.CurrencyRepo;

@Service
public class CurrencyServ {
	
	@Autowired 
	private CurrencyRepo currencyRepo;
	@Autowired
	TextConvertionServ textConvertionServ;
	
	public List<Currency> getall(String langcode) {
		try {
		return (List<Currency>) currencyRepo.findAll();
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
	
	public Currency getcurrency(int id,String langcode) {
		
		try {
			Optional<Currency> flowid =currencyRepo.findById(id);
			 
			 if (flowid.isPresent()){
				 Currency  ouput = flowid.get();
			
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
	

	
	
public Currency Save(Currency input,String langcode) {
	
	try {	
		Date date = new Date();
		input.setCreateDate(date);
		input.setEffectiveDate(date);
		Currency ouput =currencyRepo.save(input);	
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


public Currency update(Currency input,String langcode)  {
	
	try {	
		Date date = new Date();
		input.setEffectiveDate(date);
		Currency ouput1 =currencyRepo.save(input);	
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
