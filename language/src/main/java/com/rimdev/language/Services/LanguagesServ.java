package com.rimdev.language.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.language.Entities.Languages;
import com.rimdev.language.Exception.NoDataException;
import com.rimdev.language.Repo.LanguagesRepo;


@Service
public class LanguagesServ {
	
	@Autowired 
	private LanguagesRepo languagesRepo;
	
	
	public List<Languages> getalllang() {
		
		return (List<Languages>) languagesRepo.findAll();
				
		
	}
	
	
	
	public Languages getbycode(String Code){
		
		try {
			Optional<Languages> flowid =languagesRepo.getbycode(Code);
			
			
			 
			 if (flowid.isPresent()){
				 Languages  ouput = flowid.get();
			
				  return ouput;
						}
				else{
				   // alternative processing....
					throw new NoDataException("no language Code found in "+ this.getClass().getName());
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
