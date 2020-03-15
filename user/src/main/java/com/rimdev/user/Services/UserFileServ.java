package com.rimdev.user.Services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.UserFileRepo;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.Component;
import com.rimdev.user.entities.FilesUpload;
import com.rimdev.user.entities.User;
import com.rimdev.user.entities.UserFile;




@Service
public class UserFileServ {
	
	
	@Autowired
	UserFileRepo userFileRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	
	public UserFile Save(User user,FilesUpload file,Component com,String langcode) {
		
		try {	
			UserFile userfile=new UserFile();
			userfile.setFilesuploadID(file);
			userfile.setUserID(user);
			userfile.setComponentID(com);
			UserFile ouput =userFileRepo.save(userfile);	
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

}
