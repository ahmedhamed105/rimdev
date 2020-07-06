package com.rimdev.user.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.PopupException;
import com.rimdev.user.Repo.DataStatusRepo;
import com.rimdev.user.entities.DataStatus;

@Service
public class DataStatusServ {
	
	@Autowired
	DataStatusRepo dataStatusRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
public List<DataStatus> getall(String langcode) {
	try {
		return (List<DataStatus>) dataStatusRepo.findAll();
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



public DataStatus getbyid(int id,String langcode) {
	
	try {
		Optional<DataStatus> flowid =dataStatusRepo.findById(id);
		 
		 if (flowid.isPresent()){
			 DataStatus  ouput = flowid.get();
		
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
