package com.rimdev.accounting.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.AllStatus;
import com.rimdev.accounting.Enttities.Currency;
import com.rimdev.accounting.Exception.PopupException;
import com.rimdev.accounting.Repo.AllStatusRepo;

@Service
public class AllStatusServ {
	
	@Autowired
	AllStatusRepo allStatusRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
public List<AllStatus> getall(String langcode) {
		
		return (List<AllStatus>) allStatusRepo.findAll();
		
	}


public AllStatus getbyid(int id,String langcode) {
	
	try {
		Optional<AllStatus> flowid =allStatusRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 AllStatus  ouput = flowid.get();
		
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


}
