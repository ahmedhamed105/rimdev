package com.rimdev.rimfile.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimfile.Repo.FileTypeRepo;
import com.rimdev.rimfile.entities.ComponentInput;
import com.rimdev.rimfile.entities.Email;
import com.rimdev.rimfile.entities.FileType;

@Service
public class FileTypeServ {
	
	@Autowired
	FileTypeRepo fileTypeRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	

public FileType getbymime(String mime,String langcode) {
	
	try {
		Optional<FileType> flowid =fileTypeRepo.findbymime(mime);
		 
		 if (flowid.isPresent()){
			 return	  flowid.get();
		
 
					}
			else{
			   // alternative processing....
				throw new NullPointerException(textConvertionServ.search("E119", langcode));
				
			}
	}  catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }

	
}


public List<FileType> getall(String langcode) {
	
List<FileType> types;
	
	try {
		
		types = (List<FileType>) fileTypeRepo.findAll();

	//    throw new NullPointerException("no data found in users");

	} catch (TransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (ScriptException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NullPointerException(textConvertionServ.search("E104", langcode));
    }
	

	
		return types;
	
		
	}





}
